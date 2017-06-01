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
	<c:if test="${ loginUser.division == 1 }">
		<form action="usercontroll" method="get"><br />
		<input type="submit" value="ユーザー管理画面へ" /> <br />
		</form>
	</c:if>
ホーム画面 <br>


<%--ログインしてない時 --%>
	<c:if test="${ empty loginUser }">
		<a href="login">ログイン</a>
		<a href="signup">登録する</a>
		<br>
	</c:if>
<%--ログインしてる時 --%>
	<c:if test="${ not empty loginUser }">
		<a href="logout">ログアウト</a>
	<br>
		<a href="newMessage">新規投稿画面へ</a>
		<div class = "title">名前：<c:out value="${loginUser.name }"/></div>
		<div class = "title">部署：<c:out value="${loginUser.division }"/></div>
		<form action="./" method="get">
		<input type="date" name="startDate" >～<input type="date" name="endDate" >
		<br>
		カテゴリ:
		<div class= "category">
			<select name="category">
			<option value=""><c:out value=""/></option>
			<c:forEach items = "${messages}" var="message">
			<option value="${message.category}"><c:out value="${message.category}"/></option>
			</c:forEach>
			</select>
		<br />
		</div>
		<input type="submit" value="絞り込む"/>
		</form>


		<form action="./" method="get">
		<input type="submit" value="リセット"/>
		</form>

	<br>
	＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿__＿＿＿＿＿＿＿＿＿＿__＿
	<br>
	<div class= "messages">
		<c:forEach items = "${messages}" var="message">
			<div class ="name">投稿者：<c:out value="${message.name}"/></div>
			<div class = "category">カテゴリ：<c:out value="${message.category }"/></div>
			<div class = "id"> ID :<c:out value = "${message.id }" /> </div>
			<div class = "title">件名：<c:out value="${message.title }"/></div>
			本文
			<br>
			<div class = "message"><c:out value="${message.messages }"/></div>
			投稿日時：<div class = "date"><fmt:formatDate value="${message.insertDate }"/></div>

			<c:if test="${ loginUser.division == 2 || message.userId == loginUser.id }">
				<form action="delete" method="post">
					<input type="hidden" name="messageId" value="${message.id }" />
					<input type="submit" value="削除する"/>
				</form>
			</c:if>
			<c:forEach items = "${comments}" var="comments">
			<c:if test="${comments.messageId == message.id}">

~~~~~~~~~~~~~~~~~~~~~~~~~~コメント~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				<div class = "comments" >
					<span class ="name">本文：<c:out value="${comments.comment}"/></span>
					<br>
					<span class ="name">コメント日時：<c:out value="${comments.insertDate}"/></span>
					<br>
					<span class ="name">投稿者：<c:out value="${comments.name}"/></span>
					<span class ="name">コメントID：<c:out value="${comments.commentId}"/></span>
				</div>
			<c:if test="${ loginUser.division == 2 || comments.userId == loginUser.id }">
				<form action="deleteComment" method="post">
					<input type="hidden" name="commentId" value="${comments.commentId }" />
					<input type="submit" value="削除する"/>
				</form>
			</c:if>

			</c:if>
			</c:forEach>
			<form action="comment" method="post">

				<input type="submit" value="コメントする"/>
				<textarea name="comment" cols="50" rows="1" class="tweet-box"></textarea>

				<input type="hidden" name="id" value="${message.id }" />

			</form>
			<br>
	＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿
			<br />
		</c:forEach>
	</div>
	<br>
	</c:if>


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








	<div class="copyright">Copyright(c)Hitoshi Kawase</div>
</body>
</html>