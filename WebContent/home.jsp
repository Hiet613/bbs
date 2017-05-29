<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ホーム画面</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">

</head>

<body>
	<form action="usercontroll" method="get"><br />
	<input type="submit" value="ユーザー管理画面へ" /> <br />
	</form>
ホーム画面


<%--ログインしてない時 --%>
	<c:if test="${ empty loginUser }">
		<a href="login">ログイン</a>
		<a href="signup">登録する</a>
	</c:if>
<%--ログインしてる時 --%>
	<c:if test="${ not empty loginUser }">
		<a href="logout">ログアウト</a>
<br>
＿＿</c:if>＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿

<div class= "messages">
		<c:forEach items = "${messages}" var="message">
		<div class = "message" >
			<span class ="name">投稿者：<c:out value="${message.name}"/></span>
		</div>
		<div class = "id"> ID :<c:out value = "${message.id }" /> </div>
		<div class = "title">件名：<c:out value="${message.title }"/></div>
		本文
		<br>
		<div class = "message"><c:out value="${message.messages }"/></div>
		投稿日時：<div class = "date"><fmt:formatDate value="${message.insertDate }"/></div>

		<form action="comment" method="post">

		<input type="submit" value="コメントする"/>
		<textarea name="comment" cols="50" rows="1" class="tweet-box"></textarea>
		<input type="hidden" name="id" value="${message.id }" />

		</form>
		<br>
＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿
		<br />
	</c:forEach>
</div>



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





	<a href="http://localhost:8080/bbs/newmessage.jsp">新規投稿画面へ</a>


	<div class="copyright">Copyright(c)Hitoshi Kawase</div>
</body>
</html>