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

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		User userSettings = new User();

		if (isValid2(request, messages) == true) {
			List<Branch> branches = new BranchService().getBranches();
			List<Division> divisions = new DivisionService().getDivisions();
			request.setAttribute("divisions",divisions);
			request.setAttribute("branches", branches);

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
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
			return;
		}

		if (request.getAttribute("userSettings") == null){
			request.setAttribute("userSettings", userSettings);
			}

		request.getRequestDispatcher("settings.jsp").forward(request,response);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		HttpSession session = request.getSession();
		User changedUserInfomation = new User();
		List<Branch> branches = new BranchService().getBranches();
		List<Division> divisions = new DivisionService().getDivisions();


		String id = request.getParameter("id");
		int Iid = Integer.parseInt(id);
		changedUserInfomation.setId(Iid);
		changedUserInfomation.setLoginId(request.getParameter("loginId"));
		changedUserInfomation.setName(request.getParameter("name"));
		changedUserInfomation.setBranch(request.getParameter("branch"));
		changedUserInfomation.setDivision(request.getParameter("division"));
		//入力した内容を取得する
		String inputPassword = request.getParameter("password");


		String notChangedPassword = request.getParameter("notChangedPassword");
		//入力した内容が空だったら
		if(inputPassword.isEmpty()){
			//暗号化されたパスワードをセット
			changedUserInfomation.setPassword(notChangedPassword);
		//違ったら
		}else{
			//入力した内容をセット
			changedUserInfomation.setPassword(inputPassword);
		}

		List<String> messages = new ArrayList<String>();

		//バリデーションエラーがなくて、入っている値が入力した内容だったら
		if(isValid(request, messages) == true && changedUserInfomation.getPassword().equals(inputPassword)) {

			//パスワードを暗号化するメソッドを選択してアプデ
			bbs.service.UserService Settings = new UserService();
			Settings.upDate(changedUserInfomation);

		//そうじゃなくて、バリデーションエラーがなくて、入っている値が暗号化されたパスワードだったら
		} else if (isValid(request, messages) == true && changedUserInfomation.getPassword().equals(notChangedPassword)) {
			bbs.service.UserService Settings = new UserService();
			//パスワードを暗号化しないメソッドを選択してアプデ
			Settings.upDate2(changedUserInfomation);
		}else{
			session.setAttribute("errorMessages", messages);
			request.setAttribute("divisions",divisions);
			request.setAttribute("branches", branches);
			request.setAttribute("userSettings", changedUserInfomation);
			request.getRequestDispatcher("settings.jsp").forward(request,response);
			return;
		}

		if(changedUserInfomation != null){
			response.sendRedirect("usercontroll");
			return;
		}



	}
	//エラーチェックメソッド
	private boolean isValid(HttpServletRequest request, List<String> messages){
		UserService checkLoginId = new UserService();
		//フォームに入力されたログインID
		String loginId = request.getParameter("loginId");
		User getloginID =  checkLoginId.checkLoginId(loginId);
		String seachedLoginId = "捨て文字列";
		if(getloginID != null){
			 seachedLoginId = getloginID.getLoginId();
		}

		//表示されてたログインID
		String loginId2 = request.getParameter("loginId2");


		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String branch = request.getParameter("branch");
		String division = request.getParameter("division");

		//表示されてたログインIDと入力したログインIDが一緒じゃなくて、入力したログインIDがすでにDBに入っていたら
		if(!loginId.equals(loginId2) && seachedLoginId.equals(loginId)){
			messages.add("このログインIDはすでに使用されています");
		}

		if(branch.equals("1") && division.equals("3")|| division.equals("1") && !branch.equals("1")|| division.equals("2") && !branch.equals("1")){
			messages.add("この組み合わせの登録は許されていません");
		}
		if(!loginId.matches("^\\w{6,20}$")){
			messages.add("ログインIDは半角英数字6文字以上20字以下を入力してください");
		}
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
	private boolean isValid2(HttpServletRequest request, List<String> messages){
		User user = (User) request.getSession().getAttribute("loginUser");

		if(user.getDivision() != 1){
			messages.add("このページにアクセスする権限はありません。");
		}
		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}





