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

	public static String INIT_PARAMETER_NAME_ENCODING = "encoding";

	public static String DEFAULT_ENCODING = "UTF-8";

	private String encoding;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res =(HttpServletResponse)response;
		List<String> messages = new ArrayList<String>();
		User user = new User();
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(encoding);
		}

		if(!(req.getServletPath().contains("/login") || req.getServletPath().contains("/css"))){
			HttpSession session = req.getSession();
			user = (User) session.getAttribute("loginUser");
			if(user == null){
				messages.add("・ログインしていません");
				session.setAttribute("errorMesseges", messages);
				res.sendRedirect("login");
				return;
			}
		}

		HttpSession session = req.getSession();

		if(user != null && !req.getServletPath().equals("/login")){
			user = (User)session.getAttribute("loginUser");
			UserService getUser = new UserService();
			String Sid =  String.valueOf(user.getId());
			User getUserById =  getUser.getUserById(Sid);
			session.setAttribute("loginUser", getUserById);
		}



		if(!req.getServletPath().equals("/login") && user.getIsStopped() == 1 ){
			messages.add("・このユーザーの権限は停止中です");
			session.setAttribute("errorMesseges", messages);
			res.sendRedirect("login");
			return;

		}



		chain.doFilter(request, response); // サーブレットを実行

	}

	@Override
	public void init(FilterConfig config) {
		encoding = config.getInitParameter(INIT_PARAMETER_NAME_ENCODING);
		if (encoding == null) {
			System.out.println("EncodingFilter# デフォルトのエンコーディング(UTF-8)を利用します。");
			encoding = DEFAULT_ENCODING;
		} else {
			System.out.println("EncodingFilter# 設定されたエンコーディング(" + encoding
					+ ")を利用します。。");
		}
	}

	@Override
	public void destroy() {
	}

}