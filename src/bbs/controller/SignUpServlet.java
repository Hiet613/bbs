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

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();

			 if(isValid2(request, messages) == true) {

			List<Branch> branches = new BranchService().getBranches();
			List<Division> divisions = new DivisionService().getDivisions();
			request.setAttribute("divisions",divisions);
			request.setAttribute("branches", branches);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			return;
		 } else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Branch> branches = new BranchService().getBranches();
		List<Division> divisions = new DivisionService().getDivisions();
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		User user = new User();
		user.setLoginId(request.getParameter("loginId"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setBranch(request.getParameter("branch"));
		user.setDivision(request.getParameter("division"));



		if (isValid(request, messages) == true) {

			new UserService().register(user);

			//ユーザ管理画面へ
			response.sendRedirect("usercontroll");
		} else {
			request.setAttribute("divisions",divisions);
			request.setAttribute("branches", branches);
			request.setAttribute("userSettings", user);
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("signup.jsp").forward(request,response);
			return;
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		UserService checkLoginId = new UserService();
		String loginId = request.getParameter("loginId");
		User getloginID =  checkLoginId.checkLoginId(loginId);
		String seachedLoginId = "捨て文字列";
		if(getloginID != null){
			 seachedLoginId = getloginID.getLoginId();
		}




		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");
		String branch = request.getParameter("branch");
		String division = request.getParameter("division");

		if(seachedLoginId.equals(loginId)){
			messages.add("・このログインIDはすでに使用されています");
		}



		if(StringUtils.isBlank(loginId) == false && !loginId.matches("^\\w{6,20}$")){
			messages.add("・ログインIDは半角英数字6文字以上20字以下を入力してください");
		}

		if(StringUtils.isBlank(loginId) == true) {
			messages.add("・ログインIDを入力してください");
		}
		if(StringUtils.isBlank(password) == true) {
			messages.add("・パスワードを入力してください");
		}
		if(!password.equals(password2)){
			messages.add("・入力したパスワードが一致していません");
		}
		if (StringUtils.isBlank(name) == true) {
			messages.add("・名称を入力してください");
		}
		if(10 < name.length()){
			messages.add("・名称は10文字以下を入力してください");
		}
		if(branch.equals("1") && division.equals("3")|| division.equals("1") && !branch.equals("1")|| division.equals("2") && !branch.equals("1")){
			messages.add("・支店と部署・役職につき、この組み合わせの登録は許されていません");
		}

		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isValid2(HttpServletRequest request, List<String> messages){
		User user = (User) request.getSession().getAttribute("loginUser");

		if(user.getDivision() != 1){
			messages.add("・このページにアクセスする権限はありません。");
		}
		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
