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

import bbs.beans.Branch;
import bbs.beans.UserMessages;
import bbs.beans.UsersMessagesComments;
import bbs.service.BranchService;
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
		List<Branch> branches = new BranchService().getBranches();
		request.setAttribute("branches", branches);
		//初回の処理
		if(startDate == null && endDate == null && category == null){
			List<UserMessages> messages = new MessageService().getMessage();
			List<UserMessages> categories = new MessageService().getCategories();

			request.setAttribute("categories", categories);
			request.setAttribute("messages", messages);
			List<UsersMessagesComments> usersMessagesComments = new CommentService().getUsersMessagesComments();
			request.setAttribute("comments", usersMessagesComments);
			request.getRequestDispatcher("/home.jsp").forward(request,response);
			return;
		}

		if(startDate == null || startDate.isEmpty() ){
			startDate = "2010-01-01";
		}else{
			request.setAttribute("startDate", startDate);
		}


		if( endDate == null || endDate.isEmpty() ){
			endDate = "2100-05-26" + " 23:59:59";
		} else {
			request.setAttribute("endDate", endDate);
			endDate = endDate + " 23:59:59";
		}

		int diff = startDate.compareTo(endDate);


		if (diff > 0) {
			errorMessages.add("・選択された日付の始期と終期が不正です。リセットボタンを押すか、正しい日付を選択してください。");
			}


		if(category==null|| category.isEmpty()){
			List<UserMessages> narrowedMessages = new MessageService().getNarrowedMessages(startDate,endDate);
			request.setAttribute("messages", narrowedMessages);
			//カテゴリセレクトボックス保持のため
			List<UserMessages> categories = new MessageService().getCategories();
			request.setAttribute("categories", categories);

			List<UsersMessagesComments> usersMessagesComments = new CommentService().getUsersMessagesComments();
			request.setAttribute("comments", usersMessagesComments);

		} else {
			List<UserMessages> narrowedMessagesCategory = new MessageService().getNarrowedMessagesCategory(startDate,endDate,category);
			request.setAttribute("messages", narrowedMessagesCategory);
			//カテゴリセレクトボックス保持のため
			List<UserMessages> categories = new MessageService().getCategories();
			request.setAttribute("categories", categories);

			request.setAttribute("selectedCategory", category);

			List<UsersMessagesComments> usersMessagesComments = new CommentService().getUsersMessagesComments();
			request.setAttribute("comments", usersMessagesComments);
		}

		session.setAttribute("errorMessages", errorMessages);
		request.getRequestDispatcher("/home.jsp").forward(request,response);
	}

}
