package bbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.beans.Message;
import bbs.service.MessageService;

@WebServlet(urlPatterns = { "/delete" })
public class DeleteServlet extends HttpServlet{
	private static final long serialVersionUID =1L;

//メッセージの削除
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{

		Message message = new Message();
		String i = request.getParameter("messageId");
		int Ii = Integer.parseInt(i);


		message.setId(Ii);
		bbs.service.MessageService messageService = new MessageService();
		messageService.delete(message);

		if(message != null){

			response.sendRedirect("./");

		}
	}


}