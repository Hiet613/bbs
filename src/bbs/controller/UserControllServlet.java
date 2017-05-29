package bbs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.beans.User;
import bbs.service.UserControllService;

@WebServlet(urlPatterns = { "/usercontroll" })
public class UserControllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("loginUser");
		boolean isShowMessageForm;
		if(user != null){
			isShowMessageForm =true;
		} else {
			isShowMessageForm = false;
		}

		List<User> userinfomation = new UserControllService().getUserInfomarion();

		request.setAttribute("userinfomations", userinfomation);
		request.setAttribute("isShowMessageForm", isShowMessageForm);

		request.getRequestDispatcher("/usercontroll.jsp").forward(request,response);

	}

}
