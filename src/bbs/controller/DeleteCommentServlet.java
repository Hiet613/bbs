package bbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.beans.Comment;
import bbs.service.CommentService;

@WebServlet(urlPatterns = { "/deleteComment" })
public class DeleteCommentServlet extends HttpServlet{
	private static final long serialVersionUID =1L;


	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{

		Comment comment = new Comment();
		String i = request.getParameter("commentId");
		int Ii = Integer.parseInt(i);

		comment.setId(Ii);
		bbs.service.CommentService commentService = new CommentService();
		commentService.delete(comment);

		if(comment != null){

			response.sendRedirect("./");

		}
	}
}