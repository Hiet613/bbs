/*package bbs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.beans.Branch;
import bbs.service.BranchService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class BranchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*Branch user = (Branch) request.getSession().getAttribute("loginUser");
		boolean isShowMessageForm;
		if(user != null){
			isShowMessageForm =true;
		} else {
			isShowMessageForm = false;
		}

		List<Branch> branches = new BranchService().getBranches();

		request.setAttribute("branches", branches);
		//request.setAttribute("isShowMessageForm", isShowMessageForm);

		request.getRequestDispatcher("/signup.jsp").forward(request,response);

	}

}*/
