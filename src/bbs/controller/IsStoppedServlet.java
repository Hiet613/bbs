package bbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.beans.User;
import bbs.service.UserService;

@WebServlet(urlPatterns = { "/isstopped" })
public class IsStoppedServlet extends HttpServlet{
	private static final long serialVersionUID =1L;


	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{

		User userIsStopped = new User();
		String id = request.getParameter("id");
		int Iid = Integer.parseInt(id);

		String isStopped = request.getParameter("isStopped");

		if(isStopped.equals("0")){
			isStopped = "1";
		} else {
			isStopped = "0";
		}
		userIsStopped.setId(Iid);
		userIsStopped.setIsStopped(isStopped);


		bbs.service.UserService UserService = new UserService();
		UserService.isStopped(userIsStopped);

		if(userIsStopped != null){


			response.sendRedirect("usercontroll");

		}
	}
}