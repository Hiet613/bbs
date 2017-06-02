package bbs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		HttpSession session = request.getSession();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String category = request.getParameter("category");
		List<String> errorMessages = new ArrayList<String>();

		//初回の処理
		if(startDate == null && endDate == null && category == null){
			List<UserMessages> messages = new MessageService().getMessage();
			request.setAttribute("messages", messages);
			List<UsersMessagesComments> usersMessagesComments = new CommentService().getUsersMessagesComments();
			request.setAttribute("comments", usersMessagesComments);
			request.getRequestDispatcher("/home.jsp").forward(request,response);
			return;
		}


		int diff = startDate.compareTo(endDate);


		if (diff == 0) {
	System.out.println("日付1と日付2は同じです");
	} else if (diff > 0) {
	System.out.println("日付1は日付2より未来の日付です");
	errorMessages.add("日付1は日付2より未来の日付です");
	} else {
	System.out.println("日付1は日付2より過去の日付です");
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
			List<UserMessages> narrowedMessages = new MessageService().getNarrowedMessages(startDate,endDate);
			request.setAttribute("messages", narrowedMessages);

			//カテゴリセレクトボックス保持のため
			List<UserMessages> messages = new MessageService().getMessage();
			request.setAttribute("categories", messages);

			List<UsersMessagesComments> usersMessagesComments = new CommentService().getUsersMessagesComments();
			request.setAttribute("comments", usersMessagesComments);
		} else {
			List<UserMessages> narrowedMessagesCategory = new MessageService().getNarrowedMessagesCategory(startDate,endDate,category);
			request.setAttribute("messages", narrowedMessagesCategory);
			//カテゴリセレクトボックス保持のため
			List<UserMessages> messages = new MessageService().getMessage();
			request.setAttribute("categories", messages);
			List<UsersMessagesComments> usersMessagesComments = new CommentService().getUsersMessagesComments();
			request.setAttribute("comments", usersMessagesComments);
		}

		session.setAttribute("errorMessages", errorMessages);
		request.getRequestDispatcher("/home.jsp").forward(request,response);
	}

}
