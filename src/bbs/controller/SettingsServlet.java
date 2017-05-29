package bbs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bbs.beans.User;
import bbs.service.UserControllService;
import bbs.service.UserService;

@WebServlet(urlPatterns = { "/settings" })
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID =1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		User userSettings = new User();
		String id = request.getParameter("id");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String branch = request.getParameter("branch");
		String division = request.getParameter("division");
		System.out.println(division);
		int Iid = Integer.parseInt(id);

		userSettings.setId(Iid);
		userSettings.setLoginId(loginId);
		userSettings.setPassword(password);
		userSettings.setName(name);
		userSettings.setBranch(branch);
		userSettings.setDivision(division);


		if (request.getAttribute("userSettings") == null){
			List <User> usersettings = new UserControllService().getUserInfomarion();
			request.setAttribute("userSettings", userSettings);
			}

		request.getRequestDispatcher("settings.jsp").forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		User changedUserInfomation = new User();
		String id = request.getParameter("id");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String branch = request.getParameter("branch");
		String division = request.getParameter("division");

		int Iid = Integer.parseInt(id);

		changedUserInfomation.setId(Iid);
		changedUserInfomation.setLoginId(loginId);
		changedUserInfomation.setPassword(password);
		changedUserInfomation.setName(name);
		changedUserInfomation.setBranch(branch);
		changedUserInfomation.setDivision(division);


		bbs.service.UserService Settings = new UserService();
		Settings.upDate(changedUserInfomation);

		if(changedUserInfomation != null){


			response.sendRedirect("usercontroll");

		}



		/*エラーメッセージリストの宣言
		List<String> messages = new ArrayList<String>();
		//このリクエストに関連づけられている現在のセッションを返す。
		HttpSession session = request.getSession();

		User editUser = getEditUser(request);

		session.setAttribute("editUser", editUser);


		if (isValid(request, messages) == true ) {

			try{
				new UserService().upDate(editUser);

			} catch (NoRowsUpdatedRuntimeException e) {
				session.removeAttribute("editUser");
				//エラーメッセージをmssagesに加える
				messages.add("他の人によって更新されています。再審のデータを表示しました。データを書くにしてください。");
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("settings");
			}






		} else {
			//エラーメッセージ(messages)をerrorMessagesセッション領域に入れる
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("settings");
		} */
	}

	//リクエスト領域から入力情報を取得するメソッド
	private User getEditUser(HttpServletRequest request) throws IOException, ServletException
	{

		HttpSession session = request.getSession();
		User editUser = (User)session.getAttribute("editUser");

		editUser.setLoginId(request.getParameter("loginId"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setName(request.getParameter("name"));
		editUser.setBranch(request.getParameter("branch"));
		editUser.setDivision(request.getParameter("division"));
		editUser.setDivision(request.getParameter("id"));

		return editUser;

	}

	//エラーチェックメソッド
	private boolean isValid(HttpServletRequest request, List<String> messages){

		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");

		if(StringUtils.isEmpty(loginId) == true){
			messages.add("ログインIDを入力してください");
		}
		if(StringUtils.isEmpty(name) == true){
			messages.add("名称を入力してください");
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





