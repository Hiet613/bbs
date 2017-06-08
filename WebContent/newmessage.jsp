<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>新規投稿画面</title>
		<link href="./css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div class="back">
		<a href="./">戻る</a>

		</div>
		<div class="main-contents">
		<h1 >新規投稿画面</h1>
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
			<div class="form-area" style="border-top: 1px groove;">

				<form  class ="from" action="newMessage" method="post" >
					件名（50文字以下）
					<br>
					<input type="text"  class ="text" size="108" value="${messages.title}"placeholder="件名を入力" name="title" >
					<br />
					<div class="mainMessage">
					本文（1000文字以下）
					<c:choose>
						<c:when test="${not empty messages.message}">
							<textarea name="message" cols="102" rows="10" placeholder="本文を入力" class="tweet-box"><c:out value="${messages.message}"/></textarea>
						</c:when>
						<c:otherwise>
							<textarea name="message" cols="102" rows="10"placeholder="本文を入力" class="tweet-box"></textarea>
						</c:otherwise>
					</c:choose>
					</div>

					カテゴリ（10文字以下）
					<br>
					<input type="text"  class ="text" size="50" placeholder="カテゴリを入力" value="${messages.category}" name="category">
					 <br>
					<br>
					<div  align="center">
					<input class="submit" type="submit"align="center" value="投稿する"/>
					</div>
				</form>
			</div>
		</div>


		<div class="copyright">Copyright(c)Hitoshi Kawase</div>
	</body>
</html>
