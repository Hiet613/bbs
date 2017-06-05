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
				<input name="loginId" value="${userSettings.loginId}" id="loginId"/>（あなたの公開プロフィール: http://localhost:8080/bbs/?account=アカウント名）<br />

				<label for="password">パスワード</label>
				<input name="password" type="password" id="password"/> <br />

				<label for="password2">パスワード(確認用)</label>
				<input name="password2" type="password" id="password2"/> <br />

				<label for="name">名称</label>
				<input name="name"  value="${userSettings.name}" id="name"/>（名前はあなたの公開プロフィールに表示されます）<br />
				支店:
				<div class= "branch">
					<select name="branch">
					<c:forEach items = "${branches}" var="branches">

					<option value="${branches.id}" <c:if test="${userSettings.branch == branches.id}">selected
					</c:if>
					><c:out value="${branches.name}"/></option>

					</c:forEach>
					</select>
				</div>
				部署・役職:
				<div class= "division">
					<select name="division">
					<c:forEach items = "${divisions}" var="divisions">
					<option value="${divisions.id}"<c:if test="${userSettings.division == divisions.id}">selected
					</c:if>
					><c:out value="${divisions.name}"/></option>
					</c:forEach>
					</select>
				<br />
				</div>
				<input type="submit" value="登録" /> <br />

			</form>
			<a href="usercontroll">戻る</a>
			<div class="copyright">Copyright(c)Hitoshi Kawase</div>
		</div>
	</body>
</html>
