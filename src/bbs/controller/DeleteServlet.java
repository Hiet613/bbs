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

import bbs.beans.Message;
import bbs.service.MessageService;

@WebServlet(urlPatterns = { "/delete" })
public class DeleteServlet extends HttpServlet{
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

//メッセージの削除
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();

		Message message = new Message();
		String i = request.getParameter("messageId");
		int Ii = Integer.parseInt(i);


		message.setId(Ii);
		bbs.service.MessageService messageService = new MessageService();
		messageService.delete(message);

		if(message != null){
			messages.add("・件名が「"+ request.getParameter("title") + "」の投稿を削除しました" );
			session.setAttribute("errorMessages", messages);

			response.sendRedirect("./");

		}
	}


}