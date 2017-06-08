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

import bbs.beans.User;
import bbs.service.UserService;

@WebServlet(urlPatterns = { "/deleteUser" })
public class DeleteUserServlet extends HttpServlet{
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

		if (isValid(request, messages) == true) {

			User user = new User();
			String i = request.getParameter("id");

			int Ii = Integer.parseInt(i);
			user.setId(Ii);
			bbs.service.UserService userService = new UserService();
			userService.delete(user);

			if(user != null){

				messages.add("・ユーザー「" +request.getParameter("name")+"」を削除しました");
				session.setAttribute("errorMessages" ,messages);
				response.sendRedirect("usercontroll");
			}

		} else {

			session.setAttribute("errorMessages", messages);
			response.sendRedirect("usercontroll");
			return;

		}


	}
	private boolean isValid(HttpServletRequest request, List<String> messages){

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");


		String i = request.getParameter("id");
		int Ii = Integer.parseInt(i);


		if(Ii == user.getId()){
			messages.add("・自分を削除することはできません");
		}

		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}