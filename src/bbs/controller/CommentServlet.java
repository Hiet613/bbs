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

import org.apache.commons.lang.StringUtils;

import bbs.beans.Comment;
import bbs.beans.User;
import bbs.service.CommentService;



@WebServlet(urlPatterns = { "/comment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;





	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		Comment comment = new Comment();
		comment.setComment(request.getParameter("comment"));
		comment.setMessageId(request.getParameter("id"));
		if (isValid(request, messages) == true) {

			User user = (User) session.getAttribute("loginUser");

			comment.setUserId(user.getId());
			//このIDはメッセージのID

			new CommentService().register(comment);

			response.sendRedirect("./");
			return;
		} else {
			session.setAttribute("errorMessages", messages);
			session.setAttribute("errorComment", comment);
			response.sendRedirect("./");
			return;

		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages){
		String comment = request.getParameter("comment");

		if(StringUtils.isEmpty(comment) == true){
			messages.add("コメントを入力してください");
		}
		if(500 < comment.length()) {
			messages.add("コメントは500文字以下で入力してください");
		}
		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
