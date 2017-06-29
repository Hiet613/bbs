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

import bbs.beans.Comment;
import bbs.service.CommentService;

@WebServlet(urlPatterns = { "/deleteComment" })
public class DeleteCommentServlet extends HttpServlet{
	private static final long serialVersionUID =1L;


	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		messages.add("・不正なアクセスです");
		session.setAttribute("errorMessages", messages);
		response.sendRedirect("./");
		return;

	}



	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();


		Comment comment = new Comment();
		String i = request.getParameter("commentId");
		int Ii = Integer.parseInt(i);

		comment.setId(Ii);
		bbs.service.CommentService commentService = new CommentService();
		commentService.delete(comment);

		if(comment != null){
			messages.add("・コメントを削除しました" );
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");

		}
	}
}