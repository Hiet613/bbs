package bbs.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.beans.User;
import bbs.service.UserService;

@WebFilter("/*")
public class LoginCheckFilter implements Filter {


	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res =(HttpServletResponse)response;
		if(!req.getServletPath().contains("/css")) {
			List<String> messages = new ArrayList<String>();

			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("loginUser");

			if(user == null) {
				if(!req.getServletPath().contains("/login")){
					messages.add("・ログインしていません");
					session.setAttribute("errorMesseges", messages);
					res.sendRedirect("login");
					return;
				}
			} else {
				UserService getUser = new UserService();
				String Sid =  String.valueOf(user.getId());
				User getUserById =  getUser.getUserById(Sid);
				session.setAttribute("loginUser", getUserById);

				if(!req.getServletPath().equals("/login") && getUserById.getIsStopped() == 1 ){
					messages.add("・このユーザーの権限は停止中です");
					session.setAttribute("errorMesseges", messages);
					res.sendRedirect("login");
					return;
				}
			}
		}
		chain.doFilter(request, response); // サーブレットを実行
	}

	@Override
	public void init(FilterConfig config) {
	}

	@Override
	public void destroy() {
	}

}