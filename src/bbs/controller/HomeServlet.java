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

}
