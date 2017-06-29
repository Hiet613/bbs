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
import bbs.service.UserControllService;
import bbs.service.UserService;

@WebServlet(urlPatterns = { "/settings" })
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID =1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();

		UserService getUser = new UserService();
		String id = request.getParameter("id");
		User getUserById =  getUser.getUserById(id);

		List<Branch> branches = new BranchService().getBranches();
		List<Division> divisions = new DivisionService().getDivisions();
		request.setAttribute("divisions",divisions);
		request.setAttribute("branches", branches);

		if(getUserById == null){
			messages.add("・不正なアクセスです");
			session.setAttribute("errorMessages", messages);
			List<User> userinfomation = new UserControllService().getUserInfomarion();
			session.setAttribute("userinfomations", userinfomation);
			response.sendRedirect("usercontroll");
			return;
		}

		if (request.getAttribute("userSettings") == null){
			request.setAttribute("userSettings", getUserById);
		}

		request.getRequestDispatcher("settings.jsp").forward(request,response);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//セレクトボックスの値の保持用
		List<Branch> branches = new BranchService().getBranches();
		List<Division> divisions = new DivisionService().getDivisions();

		//セッションの取得（エラーメッセージボックスの取得）
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		//ユーザーIDから情報を引っ張ってくる
		String id = request.getParameter("id");
		UserService getUser = new UserService();
		User getUserById =  getUser.getUserById(id);


		//フォームから入力された情報を引っ張ってきてセットする
		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		String branch = request.getParameter("branch");
		String division = request.getParameter("division");

		getUserById.setLoginId(loginId);
		getUserById.setName(name);
		getUserById.setBranch(branch);
		getUserById.setDivision(division);

		//パスワードの情報をフォームから取得
		String inputPassword = request.getParameter("password");

		//フォームが空だったら、既存のパスワードをセットする。
		if(inputPassword.isEmpty()){
			inputPassword = getUserById.getPassword();
		}

		//バリデーションエラー
		if(isValid(request, messages) == false){
			session.setAttribute("errorMessages", messages);
			request.setAttribute("divisions",divisions);
			request.setAttribute("branches", branches);
			request.setAttribute("userSettings", getUserById);
			request.getRequestDispatcher("settings.jsp").forward(request,response);
			return;
		}

		//入力した内容が空だったら（inputPasswordが既存のパスワード入っていなかったら）
		if(inputPassword.equals(getUserById.getPassword())) {
			//パスワードを暗号化しないメソッドを選択してアプデ
			getUser.upDate2(getUserById);
		}

		//入力内容が空じゃなかったら
		if (!inputPassword.equals(getUserById.getPassword())) {
			//パスワードを暗号化するメソッドを選択してアプデ
			getUserById.setPassword(inputPassword);
			getUser.upDate(getUserById);
		}

		if(getUser != null){

			messages.add("・ユーザー「" +getUserById.getName()+"(更新後の名称)」の情報を更新しました");
			session.setAttribute("errorMessages" ,messages);
		}


		response.sendRedirect("usercontroll");
		return;
		}



	//エラーチェックメソッド
	private boolean isValid(HttpServletRequest request, List<String> messages){
		UserService checkLoginId = new UserService();

		String id = request.getParameter("id");

		UserService getUser = new UserService();
		User getUserById =  getUser.getUserById(id);
		//フォームに登録されたログインID
		String loginId = request.getParameter("loginId");
		User getloginID =  checkLoginId.checkLoginId(loginId);
		String seachedLoginId = "捨て文字列";
		if(getloginID != null){
			 seachedLoginId = getloginID.getLoginId();
		}

		//表示されてたログインID
		String loginId2 = getUserById.getLoginId();


		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String branch = request.getParameter("branch");
		String division = request.getParameter("division");

		//表示されてたログインIDと入力したログインIDが一緒じゃなくて、入力したログインIDがすでにDBに入っていたら
		if(!loginId.equals(loginId2) && seachedLoginId.equals(loginId)){
			messages.add("・このログインIDはすでに使用されています");
		}
		if(StringUtils.isBlank(loginId) == false && !loginId.matches("^\\w{6,20}$")){
			messages.add("・ログインIDは半角英数字6文字以上20字以下を入力してください");
		}
		if(StringUtils.isBlank(loginId) == true){
			messages.add("・ログインIDを入力してください");
		}
		if(StringUtils.isBlank(name) == true){
			messages.add("・名称を入力してください");
		}
		if(10 < name.length()){
			messages.add("・名称は10文字以下を入力してください");
		}
		if(StringUtils.isBlank(password) == false && password.length() < 6 || password.length() > 20){
			messages.add("・パスワードは6文字以上20文字以下を入力してください");
		}
		if(!password.equals(password2)){
			messages.add("・入力したパスワードが一致していません");
		}

		if(branch.equals("1") && division.equals("3")|| division.equals("1") && !branch.equals("1")|| division.equals("2") && !branch.equals("1")){
			messages.add("・支店と部署・役職につき、この組み合わせの登録は許されていません");
		}

		if (messages.size()==0){
			return true;
		} else {
			return false;

		}
	}

}





