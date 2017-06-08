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

@WebServlet(urlPatterns = { "/isstopped" })
public class IsStoppedServlet extends HttpServlet{
	private static final long serialVersionUID =1L;


	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{

		User userIsStopped = new User();
		String id = request.getParameter("id");
		int Iid = Integer.parseInt(id);

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		String isStopped = request.getParameter("isStopped");

		UserService getUser = new UserService();
		User getUserById =  getUser.getUserById(id);


		if (isValid(request, messages) == false) {
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("usercontroll").forward(request,response);
			return;
		}

		if(isStopped.equals("0")){
			isStopped = "1";
		} else {
			isStopped = "0";
		}
		userIsStopped.setId(Iid);
		userIsStopped.setIsStopped(isStopped);

		bbs.service.UserService UserService = new UserService();
		UserService.isStopped(userIsStopped);

		if(userIsStopped != null && isStopped.equals("0")){
			messages.add("・ユーザー「" +getUserById.getName()+"」を復活させました");
			session.setAttribute("errorMessages" ,messages);
			response.sendRedirect("usercontroll");
			return;
		}

		if(userIsStopped != null && isStopped.equals("1")){
			messages.add("・ユーザー「" +getUserById.getName()+"」を停止しました");
			session.setAttribute("errorMessages" ,messages);
			response.sendRedirect("usercontroll");
			return;
		}
	}


	private boolean isValid(HttpServletRequest request, List<String> messages){
		User user = (User) request.getSession().getAttribute("loginUser");
		String id = request.getParameter("id");
		int Iid = Integer.parseInt(id);
		if(user.getId() == Iid){
			messages.add("・自分を停止することはできません");
		}
		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}