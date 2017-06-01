<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ユーザー編集画面</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
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

			<form action="settings" method="post"><br />

				<label for="name">名称</label>
				<input name="name" value="${userSettings.name}" id="name"/><br />

				<input type="hidden" name="id" value="${userSettings.id }" />

				<label for="account">ログインID</label>

				<div class = "id">ID：<c:out value="${userSettings.id }"/></div>
				<input name="loginId" value="${userSettings.loginId}" /> <br />

				<label for="password">パスワード</label>
				<input name="password" type="password" id="password"/> <br />

				<label for="password2">パスワード確認用</label>
				<input name="password2" type="password" id="password2"/> <br />

					支店:
				<div class= "branch">
					<select name="branch">
					<c:forEach items = "${branches}" var="branches">
					<option value="${branches.id}"><c:out value="${branches.name}"/></option>
					</c:forEach>
					</select>
				</div>

				部署・役職:
				<div class= "division">
					<select name="division">
					<c:forEach items = "${divisions}" var="divisions">
					<option value="${divisions.id}"><c:out value="${divisions.name}"/></option>
					</c:forEach>
					</select>
				<br />
				</div>


				<input type="submit" value="登録" /> <br />
				<a href="./">戻る</a>
			</form>
			<div class="copyright">Copyright(c)Satoshi Kimura</div>
		</div>
	</body>
</html>
