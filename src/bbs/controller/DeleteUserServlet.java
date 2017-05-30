package bbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.beans.User;
import bbs.service.UserService;

@WebServlet(urlPatterns = { "/deleteUser" })
public class DeleteUserServlet extends HttpServlet{
	private static final long serialVersionUID =1L;


	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{


		User user = new User();
		String i = request.getParameter("id");
		System.out.println(i);
		int Ii = Integer.parseInt(i);


		user.setId(Ii);
		bbs.service.UserService userService = new UserService();
		userService.delete(user);

		if(user != null){

			response.sendRedirect("usercontroll");

		}
	}
}