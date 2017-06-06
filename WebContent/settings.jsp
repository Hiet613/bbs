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

				<label for="account">ログインID</label>
				<input name="loginId" value="${userSettings.loginId}" />（半角英数6文字以上20字以下）<br />
				<input type="hidden" name="loginId2" value="${userSettings.loginId}" />

				<input type="hidden" name="notChangedPassword" value="${userSettings.password }" />

				<label for="password">パスワード</label>
				<input name="password" type="password" id="password"/>（6文字以上20文字以下) <br />

				<label for="password2">パスワード確認用</label>
				<input name="password2" type="password" id="password2"/> <br />

				<label for="name">名称</label>
				<input name="name" value="${userSettings.name}" id="name"/>（10文字以下）<br />

				<input type="hidden" name="id" value="${userSettings.id }" />

				<c:choose>
				<c:when test= "${userSettings.id != loginUser.id}">
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
					<option value="${divisions.id}" <c:if test="${userSettings.division == divisions.id}">selected
					</c:if>
					><c:out value="${divisions.name}"/></option>
					</c:forEach>
					</select>
				<br />
				</div>
				</c:when>

				<c:otherwise>
				<input type="hidden" name="branch" value="${userSettings.branch }" />
				<input type="hidden" name="division" value="${userSettings.division }" />
				</c:otherwise>
				</c:choose>
				<input type="submit" value="この内容で編集する" /> <br />
				<a href="./">ホームへ</a>
			</form>
				<a href="usercontroll">戻る</a>

		</div>
		<div class="copyright">Copyright(c)Hitoshi Kawase</div>
	</body>
</html>
