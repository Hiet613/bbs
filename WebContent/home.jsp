<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ホーム画面</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		<!--
		function dispComment(){
		if(window.confirm('このコメントを削除します')){
			location.href = "./";
			return true;
		}
			// 「OK」時の処理終了
			return false;
		}


		function disp(){
			if(window.confirm('この投稿を削除します')){
				location.href = "./";
				return true;
			}
				// 「OK」時の処理終了
				return false;
			}

		 -->
		</script>

</head>

<body>
	<c:if test="${ loginUser.division == 1 }">
		<form action="usercontroll" method="get"><br />
		<input type="submit" value="ユーザー管理画面へ" /> <br />
		</form>
	</c:if>
ホーム画面 <br>

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

		<div class = "title">店名：
			<c:forEach items="${branches}" var="branch">
				<c:if test="${ loginUser.branch == branch.id}">
					<c:out value="${branch.name }"/>
				</c:if>
			</c:forEach>
		</div>
		<form action="./" method="get">
			<input type="date" name="startDate" value="${startDate}" >～<input type="date" value="${endDate}" name="endDate" >
			<br>
			カテゴリ:
			<div class="categories">
				<select name="category">
					<option value=""><c:out value=""/></option>
						<c:forEach items = "${categories}" var="category">
					<option value="${category.category}"<c:if test="${category.category == selectedCategory}">selected
					</c:if>
					><c:out value="${category.category}"/></option>
				</c:forEach>
				</select>
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
			<div class = "id"> メッセージID :<c:out value = "${message.id }" /> </div>
			<div class = "title">件名：<c:out value="${message.title }"/></div>
			本文<br>
			<c:forEach var="splitedMessage" items="${fn:split(message.messages,'
			')}">
			<div class = "splitedMessage"><c:out value="${splitedMessage}"/><br></div>
			</c:forEach>
			投稿日時：
			<div class = "date" style="display:inline;" ><fmt:formatDate value="${message.insertDate }"  pattern="yyyy年MM月dd日（E） HH:mm"/></div>
			<c:if test="${ loginUser.division == 2 || message.userId == loginUser.id || message.branch == loginUser.branch && loginUser.division == 3}">
				<form action="delete" method="post">
					<input type="hidden" name="messageId" value="${message.id }" />
					<input type="hidden" name="title" value="${message.title}" />
					<input type="submit" value="削除する" onClick="return disp()">
				</form>
			</c:if>
			<form action="comment" method="post">
				<input type="submit" value="コメントする"/>
				<c:choose>
					<c:when test="${not empty errorComment && errorComment.messageId == message.id}">
							<textarea name="comment" cols="50" rows="1" class="tweet-box"><c:out value="${errorComment.comment}"/></textarea>
						<c:remove var="errorComment" scope="session"/>
					</c:when>
					<c:otherwise>
					<textarea name="comment" cols="50" rows="1" class="tweet-box"></textarea>
					</c:otherwise>
				</c:choose>
				<input type="hidden" name="id" value="${message.id}" />
			</form>
			<c:forEach items = "${comments}" var="comments">
				<c:if test="${comments.messageId == message.id}">

	~~~~~~~~~~~~~~~~~~~~~~~~~~コメント~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					<div class = "comments" >
						<c:forEach var="splitedComment" items="${fn:split(comments.comment,'
						')}">
						<div class = "splitedComment"><c:out value="${splitedComment}"/><br></div>
						</c:forEach>
						<span class ="insertDate">コメント日時：<fmt:formatDate value="${comments.insertDate}"  pattern="yyyy年MM月dd日（E） HH:mm"/></span>
						<br>
						<span class ="commentUser">投稿者：<c:out value="${comments.name}"/></span>
						<span class ="commentId">コメントID：<c:out value="${comments.commentId}"/></span>
						<br>
					</div>
					<c:if test="${ loginUser.division == 2 || comments.userId == loginUser.id || comments.branch == loginUser.branch && loginUser.division == 3 }">
						<form action="deleteComment" method="post">
							<input type="hidden" name="commentId" value="${comments.commentId }" />
							<input type="hidden" name="comment" value="${comments.comment}" />
							<input type="submit" value="削除する" onClick="return dispComment()">
						</form>
					</c:if>
				</c:if>
			</c:forEach>
			<br>
＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿
			<br />
		</c:forEach>
	</div>
	<br>
	</c:if>









	<div class="copyright">Copyright(c)Hitoshi Kawase</div>
</body>
</html>