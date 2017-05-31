package bbs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.beans.User;
import bbs.beans.UserMessages;
import bbs.beans.UsersMessagesComments;
import bbs.service.CommentService;
import bbs.service.MessageService;


@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String category = request.getParameter("category");

		System.out.println(startDate);
		System.out.println(endDate);
		System.out.println(category);

		if(startDate == null && endDate == null && category == null){
			User user = (User) request.getSession().getAttribute("loginUser");
			boolean isShowMessageForm;
			if(user != null){
				isShowMessageForm =true;
			} else {
				isShowMessageForm = false;
			}

			List<UserMessages> messages = new MessageService().getMessage();
			request.setAttribute("messages", messages);
			List<UsersMessagesComments> usersMessagesComments = new CommentService().getUsersMessagesComments();
			request.setAttribute("comments", usersMessagesComments);

			request.setAttribute("isShowMessageForm", isShowMessageForm);
			request.getRequestDispatcher("/home.jsp").forward(request,response);
		}
		if(startDate.isEmpty()){
			startDate = "2010-01-01";
		}

		if(endDate.isEmpty()){
			endDate = "2100-05-26" + " 23:59:59";
		} else {
			endDate = endDate + " 23:59:59";
		}

		if(category.isEmpty()){
			User user = (User) request.getSession().getAttribute("loginUser");
			boolean isShowMessageForm;
			if(user != null){
				isShowMessageForm =true;
			} else {
				isShowMessageForm = false;
			}
			List<UserMessages> narrowedMessages = new MessageService().getNarrowedMessages(startDate,endDate);
			request.setAttribute("messages", narrowedMessages);
			List<UsersMessagesComments> usersMessagesComments = new CommentService().getUsersMessagesComments();
			request.setAttribute("comments", usersMessagesComments);

			request.setAttribute("isShowMessageForm", isShowMessageForm);
			request.getRequestDispatcher("/home.jsp").forward(request,response);

		} else {
			User user = (User) request.getSession().getAttribute("loginUser");
			boolean isShowMessageForm;
			if(user != null){
				isShowMessageForm =true;
			} else {
				isShowMessageForm = false;
			}
			List<UserMessages> narrowedMessagesCategory = new MessageService().getNarrowedMessagesCategory(startDate,endDate,category);
			request.setAttribute("messages", narrowedMessagesCategory);
			List<UsersMessagesComments> usersMessagesComments = new CommentService().getUsersMessagesComments();
			request.setAttribute("comments", usersMessagesComments);

			request.setAttribute("isShowMessageForm", isShowMessageForm);
			request.getRequestDispatcher("/home.jsp").forward(request,response);
		}
	}

}
