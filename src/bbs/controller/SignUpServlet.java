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

import org.apache.commons.lang.StringUtils;

import bbs.beans.Branch;
import bbs.beans.Division;
import bbs.beans.User;
import bbs.service.BranchService;
import bbs.service.DivisionService;
import bbs.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Branch> branches = new BranchService().getBranches();
		List<Division> divisions = new DivisionService().getDivisions();
		request.setAttribute("divisions",divisions);
		request.setAttribute("branches", branches);
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		if (isValid(request, messages) == true) {

			User user = new User();
			user.setLoginId(request.getParameter("loginId"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("name"));
			user.setBranch(request.getParameter("branch"));
			user.setDivision(request.getParameter("division"));

			new UserService().register(user);

			//ユーザ管理画面へ
			response.sendRedirect("usercontroll");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");



		if (StringUtils.isEmpty(loginId) == true) {
			messages.add("ログインIDを入力してください");
		}

		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		if(!password.equals(password2)){
			messages.add("入力したパスワードが一致していません");
		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("名称を入力してください");
		}


		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
