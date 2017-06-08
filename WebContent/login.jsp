<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ログイン画面</title>
		<link href="./css/style.css" rel="stylesheet" type="text/css">
	</head>

	<body>
<h1 align="center">わったい菜掲示板</h1>
	<div class="login-main">
		<div class="logintitle">
			<h2 align="center">ログイン画面</h2>
		</div>
			<c:if test="${not empty errorMesseges}">
				<div class="errorMessages">
					<ul>
						<c:forEach items="${errorMesseges}" var="message">
							<li><c:out value="${message}" />
						</c:forEach>
					</ul>
				</div>
				<c:remove var="errorMessages" scope="session"/>
			</c:if>


		<div class="form-area" align="center">
			<form action="login"  method="post"><br />
				<table>
					<tr>
						<td>
							ログインＩＤ
						</td>
						<td>
							<input name="loginId" value = "${loginId}" id="loginId"/>
						</td>
					</tr>
					<tr>
						<td>
							パスワード
						</td>
						<td>
							<input name="password" type="password" id="password"/>
						</td>
					</tr>
				</table>
				<input class="submit"   type="submit" value="ログイン" /> <br />
			</form>
		</div>
	</div>
	<div class ="copyright">Copyright(c)Hitoshi Kawase</div>
	</body>
</html>
