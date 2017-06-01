<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>わったい菜掲示板 新規投稿画面</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
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
	<div class="form-area">

			<form action="newMessage" method="post">

				<a>新規投稿画面</a>
				<br />
				件名（50文字まで）
				<textarea name="title" cols="50" rows="1" class="tweet-box" ><c:out value="${messages.title}"/></textarea>
				<br />
				本文（1000文字まで）
				<textarea name="message" cols="100" rows="10" class="tweet-box"><c:out value="${messages.message}"/></textarea>
				<br />
				カテゴリ（10文字まで）
				<input type="text" size="10" name="category"><c:out value="${messages.category}"/><br>

				<br />
				<input type="submit" value="投稿する"/>
			</form>
	</div>





</div>
<a href="./">戻る</a>
</body>
</html>
