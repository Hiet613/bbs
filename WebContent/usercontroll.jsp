<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ユーザー管理画面</title>
			<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js">
			$(function(){
				$('.deleteUser').submit(function(){
			   	 if(!confirm('本当に削除しますか？')){
			        /* キャンセルの時の処理 */
			        return false;
			   		 }
				});
			});
			</script>

	</head>
	<body>
		<div class="main-contents">
			<%--ログインしてる時 --%>
			<c:if test="${ not empty loginUser }">
				<a href="logout">ログアウト</a>
			<br>
			</c:if>
			</div>
			<a href="./">戻る</a>
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

			<h1>ユーザー管理画面</h1>

			<form action="signup" method="get"><br />
			<input type="submit" value="ユーザー新規登録" /> <br />
			</form>
			<p>ユーザーのログインID・名称一覧</p>

			<div class= "userinfomation">
				<c:forEach items = "${userinfomations}" var="userinfomation">
					<form action="settings" method="get"><br />
						<div class = "userinfomation" >
						<div class ="loginId">ログインID：<c:out value="${userinfomation.loginId }"/></div>
						<input type="hidden" name="loginId" value="${userinfomation.loginId }" />
						<div class = "id">ID：<c:out value="${userinfomation.id }"/>
							<input type="hidden" name="id" value="${userinfomation.id }" />
						</div>
						<div class = "password">
						<input type="hidden" name="password" value="${userinfomation.password }" />
						</div>
						<div class = "name">名称：<c:out value="${userinfomation.name }"/>
						<input type="hidden" name="name" value="${userinfomation.name }" />
						</div>

						<div class ="branchId">支店:
							<input type="hidden" name="branch" value="${userinfomation.branch }" />

							<c:forEach items="${branches}" var="branch">
								<c:if test="${ branch.id == userinfomation.branch}">
								<c:out value="${branch.name }"/>
								</c:if>
							</c:forEach>
						</div>

						<div class ="division">役職部署:
						<input type="hidden" name="division" value="${userinfomation.division }" />
						<c:forEach items="${divisions}" var="division">
							<c:if test="${ division.id == userinfomation.division}">
							<c:out value="${division.name }"/>
							</c:if>
						</c:forEach>
						</div>

						<div class = "isStopped">停止か否か:
						<input type="hidden" name="isStopped" value="${userinfomation.isStopped }" />
						<c:if test="${userinfomation.getIsStopped() == 0 }">
						<c:out value="稼働中"/>
						</c:if>
						<c:if test="${userinfomation.getIsStopped() == 1 }">
						<c:out value="停止中"/>
						</c:if>
						</div>

						<%-- 表示している情報をゲットメソッドで送信する  --%>
						<input type="submit" value="編集する" /> <br />
						</div>
					</form>

					<%-- ユーザーの削除（ポストメソッド）で送信する  --%>
					<form action="deleteUser" method="post" class="deleteUser">
						<input type="hidden" name= "id" value="${userinfomation.id }" />
						<script type="text/javascript">
							document.write()
							function disp(){
								// 「OK」時の処理開始 ＋ 確認ダイアログの表示
								if(window.confirm('本当にいいんですね？')){
									location.href = "http://localhost:8080/bbs/usercontroll";
								}
								// 「OK」時の処理終了
								// 「キャンセル」時の処理開始
								else{
									window.alert('キャンセルされました'); // 警告ダイアログを表示
								}
								// 「キャンセル」時の処理終了
							}
							// -->
							</script>
						<p><input type="submit" value="このユーザーを削除する" onClick="disp()"></p>


					</form>


					<c:if test="${userinfomation.isStopped == 0 }" >
						<form action="isstopped" method="get"><br />
							<input type="hidden" name="isStopped" value="${userinfomation.isStopped }" />
							<input type="hidden" name="id" value="${userinfomation.id }" />
							<script type="text/javascript">
							document.write()
							function disp(){
								// 「OK」時の処理開始 ＋ 確認ダイアログの表示
								if(window.confirm('本当にいいんですね？')){
									location.href = "http://localhost:8080/bbs/usercontroll";
								}
								// 「OK」時の処理終了
								// 「キャンセル」時の処理開始
								else{
									window.alert('キャンセルされました'); // 警告ダイアログを表示
								}
								// 「キャンセル」時の処理終了
							}
							// -->
							</script>
							<p><input type="submit" value="停止する" onClick="disp()"></p>
						</form>
					</c:if>
					<c:if test="${userinfomation.isStopped == 1 }" >
						<form action="isstopped" method="get"><br />
						<input type="hidden" name="isStopped" value="${userinfomation.isStopped }" />
							<input type="hidden" name="id" value="${userinfomation.id }" />
							<script type="text/javascript">
							document.write()
							function disp(){
								// 「OK」時の処理開始 ＋ 確認ダイアログの表示
								if(window.confirm('本当にいいんですね？')){
									location.href = "http://localhost:8080/bbs/usercontroll";
								}
								// 「OK」時の処理終了
								// 「キャンセル」時の処理開始
								else{
									window.alert('キャンセルされました'); // 警告ダイアログを表示
								}
								// 「キャンセル」時の処理終了
							}
							// -->
							</script>
							<p><input type="submit" value="復活させる" onClick="disp()"></p>
						</form>
					</c:if>
					<br />
				</c:forEach>

		</div>
	</body>

</html>