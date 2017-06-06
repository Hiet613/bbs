package bbs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.beans.Branch;
import bbs.beans.Division;
import bbs.beans.User;
import bbs.service.BranchService;
import bbs.service.DivisionService;
import bbs.service.UserControllService;

@WebServlet(urlPatterns = { "/usercontroll" })
public class UserControllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Branch> branches = new BranchService().getBranches();
		List<Division> divisions = new DivisionService().getDivisions();
		request.setAttribute("divisions",divisions);
		request.setAttribute("branches", branches);

		List<User> userinfomation = new UserControllService().getUserInfomarion();
		request.setAttribute("userinfomations", userinfomation);
		request.getRequestDispatcher("/usercontroll.jsp").forward(request,response);
			return;

		}

}
