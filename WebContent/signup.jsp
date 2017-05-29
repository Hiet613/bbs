<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ユーザー新規登録画面</title>

	</head>
	<body>
		<div class="main-contents">
			<c:if test="${ not empty errorMessages }">
				<div class="errorMessages">
					<ul>
						<c:forEach items="${errorMessages}" var="message">
							<li><c:out value="${message}" />
						</c:forEach>
					</ul>
				</div>
				<c:remove var="errorMessages" scope="session"/>
			</c:if>
			<form action="signup" method="post"><br />
				<label for="loginId">ログインID</label>
				<input name="loginId" id="loginId"/>（あなたの公開プロフィール: http://localhost:8080/bbs/?account=アカウント名）<br />

				<label for="password">パスワード</label>
				<input name="password" type="password" id="password"/> <br />

				<label for="name">名称</label>
				<input name="name" id="name"/>（名前はあなたの公開プロフィールに表示されます）<br />

				支店:
				<select name="branch">
					<option value="1">本社</option>
					<option value="2">支店１</option>
					<option value="3">支店２</option>
				</select>
				<br />

				部署・役職
				<select name="division">
					<option value="1">人事</option>
					<option value="2">情報管理</option>
					<option value="3">店長</option>
					<option value="4">社員</option>
				</select>
				<br />
				<input type="submit" value="登録" /> <br />
				<a href="./">戻る</a>
			</form>
			<div class="copyright">Copyright(c)Satoshi Kimura</div>
		</div>
	</body>
</html>
