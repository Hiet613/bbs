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

@WebServlet(urlPatterns = { "/settings" })
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID =1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Branch> branches = new BranchService().getBranches();
		List<Division> divisions = new DivisionService().getDivisions();
		request.setAttribute("divisions",divisions);
		request.setAttribute("branches", branches);

		User userSettings = new User();

		String id = request.getParameter("id");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String branch = request.getParameter("branch");
		String division = request.getParameter("division");

		int Iid = Integer.parseInt(id);

		userSettings.setId(Iid);
		userSettings.setLoginId(loginId);
		userSettings.setPassword(password);
		userSettings.setName(name);
		userSettings.setBranch(branch);
		userSettings.setDivision(division);


		if (request.getAttribute("userSettings") == null){

			request.setAttribute("userSettings", userSettings);
			}

		request.getRequestDispatcher("settings.jsp").forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		User changedUserInfomation = new User();

		String id = request.getParameter("id");
		int Iid = Integer.parseInt(id);
		changedUserInfomation.setId(Iid);
		changedUserInfomation.setLoginId(request.getParameter("loginId"));
		changedUserInfomation.setPassword(request.getParameter("password"));
		changedUserInfomation.setName(request.getParameter("name"));
		changedUserInfomation.setBranch(request.getParameter("branch"));
		changedUserInfomation.setDivision(request.getParameter("division"));
		if (isValid(request, messages) == true) {


		bbs.service.UserService Settings = new UserService();
		Settings.upDate(changedUserInfomation);

		} else {
			session.setAttribute("errorMessages", messages);
			request.setAttribute("userSettings", changedUserInfomation);
			request.getRequestDispatcher("settings.jsp").forward(request,response);
		}
		if(changedUserInfomation != null){
			response.sendRedirect("usercontroll");
		}
	}
	//エラーチェックメソッド
	private boolean isValid(HttpServletRequest request, List<String> messages){

		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		if(StringUtils.isEmpty(loginId) == true){
			messages.add("ログインIDを入力してください");
		}
		if(StringUtils.isEmpty(name) == true){
			messages.add("名称を入力してください");
		}
		if(!password.equals(password2)){
			messages.add("入力したパスワードが一致していません");
		}
		/*TODOアカウントがすでに利用されていないか、ログインIDが
		 * すでに登録されていないかどうかなどの確認も必要*/
		if (messages.size()==0){
			return true;
		} else {
			return false;

		}
	}
}





