<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
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

	<div class="back" style="display:inline;">
				<form action="./" method="get">
				<input type="submit" value="リセット"/>
				</form>
				</div>
		<div class="modoru">
			<div class="personal">

			<h1 align="center" class="home-title">ホーム画面</h1>
			<div class="error">
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
			</div>






		</div>
			<div class = "loginuser">
				名称︙<c:out value="${loginUser.name }"/>

			</div>
			<br>
			<div  class="topmanu">

			<span ><a href="logout">ログアウト</a></span>
			<span  class="newmessage"><a href="newMessage">新規投稿画面へ</a></span>



			<span class="usercontroll">
				<c:if test="${ loginUser.division == 1 }">
					<a href="usercontroll">ユーザー管理画面へ</a>
				</c:if>
			</span>

			</div>


			<div class = "narrow"  align="center">
				<form action="./" method="get">
					<input type="date" name="startDate" value="${startDate}" >～<input type="date" value="${endDate}" name="endDate" >
					<br>

					<div class="categories" >
					カテゴリ<select name="category">
							<option value=""><c:out value=""/></option>
								<c:forEach items = "${categories}" var="category">
							<option value="${category.category}"<c:if test="${category.category == selectedCategory}">selected
							</c:if>
							><c:out value="${category.category}"/></option>
						</c:forEach>
						</select>
						<br>
						<input type="submit" value="絞り込む"/>
					</div>

				</form>
			</div>
		</div>
		<br>
		<div class= "messagecomment">

			<c:forEach items = "${messages}" var="message">
				<div class= "messages">
				<div class="user-category">

					<span class = "category" >＜<c:out value="${message.category }"/>＞</span>	︙<c:out value="${message.name}"/> さんの投稿
					<span class="id-date"  >
							<fmt:formatDate value="${message.insertDate }"  pattern="yyyy年MM月dd日（E） HH:mm"/>
						</span>
					<div class = "title-and-category">
						<span class = "title">
						<p style="
							margin-top:10px;
							margin-left:20px;
							">
						<b><c:out value="${message.title }"/></b></p>
						</span>

					</div>
				</div>

				<div class = "message">
					<span class ="name">

						</span>

					<br>
					<c:forEach var="splitedMessage" items="${fn:split(message.messages,'
					')}">
					<div class = "splitedMessage">
						<p> <c:out value="${splitedMessage}"/></p><br>
					</div>

					</c:forEach>

					<c:if test="${ loginUser.division == 2 || message.userId == loginUser.id || message.branch == loginUser.branch && loginUser.division == 3}">
							<form action="delete" align="right" method="post">
								<input type="hidden" name="messageId" value="${message.id }" />
								<input type="hidden" name="title" value="${message.title}" />
								<input type="submit" value="削除する" onClick="return disp()">
							</form>
						</c:if>
				</div>


					<div class = "commentForm">
						<form action="comment" method="post">
							<c:choose>
								<c:when test="${not empty errorComment && errorComment.messageId == message.id}">
										<textarea name="comment" cols="50" rows="1" class="tweet-box"><c:out value="${errorComment.comment}"/></textarea>
									<c:remove var="errorComment" scope="session"/>
								</c:when>
								<c:otherwise>
									<textarea name="comment"  placeholder="コメントはここに入力" cols="50" rows="1" class="tweet-box2"></textarea>
								</c:otherwise>
							</c:choose>
							<br>
							<input type="submit" value="コメントする"/>
							<input type="hidden" name="id" value="${message.id}" />
						</form>
					</div>
				</div>
				<div class = "comments"  >
					<c:forEach items = "${comments}" var="comments">
						<c:if test="${comments.messageId == message.id}">
						<div class = "comment" >
							<div class="namedate">
								<span class="comment-name" align="left">︙<c:out value="${comments.name}"/>さんのコメント</span>
								<span class ="comment-date2" >
									<fmt:formatDate value="${comments.insertDate}"  pattern="yyyy年MM月dd日（E） HH:mm"/>
								</span>
							</div>

							<c:forEach var="splitedComment" items="${fn:split(comments.comment,'
								')}">
							<div class = "comments2" >
								<c:out value="${splitedComment}"/>
							</div>
							</c:forEach>

							<c:if test="${ loginUser.division == 2 || comments.userId == loginUser.id || comments.branch == loginUser.branch && loginUser.division == 3 }">
									<form action="deleteComment" align="right"method="post">
										<input type="hidden" name="commentId" value="${comments.commentId }" />
										<input type="hidden" name="comment" value="${comments.comment}" />
										<input type="submit" value="削除する" onClick="return dispComment()">
									</form>
								</c:if>
						</div>
						</c:if>
					</c:forEach>
				</div>
				<br>

				<br />
			</c:forEach>
		</div>
		<br>
		<div class="copyright">Copyright(c)Hitoshi Kawase</div>
	</body>
</html>