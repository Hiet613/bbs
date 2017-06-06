<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>ユーザー管理画面</title>
		<script type="text/javascript">
		<!--

		function dispUser(){
		if(window.confirm('このユーザーを削除します')){
			location.href = "usercontroll";
			return true;
		}
			// 「OK」時の処理終了
			return false;
		}

		function disp(){
			if(window.confirm('このユーザーを停止します')){
				location.href = "usercontroll";
				return true;
			}
				// 「OK」時の処理終了
				return false;
		}


		function disp2(){
			if(window.confirm('このユーザーを復活させます')){
				location.href = "usercontroll";
				return true;
			}
				// 「OK」時の処理終了
				return false;
		}

		 -->
		</script>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
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
		<div  >
			<p>ユーザーのログインID・名称一覧</p>
			<table border="1">
				<tr>
					<th>名称</th>
					<th>ログインＩＤ</th>
					<th>支店　</th>
					<th>役職部署　</th>
					<th>状況　</th>
					<th>削除 </th>
				</tr>
				<c:forEach items = "${userinfomations}" var="userinfomation">
					<tr>
						<td>
							<c:out value="${userinfomation.name }"/><input type="hidden" name="id" value="${userinfomation.id }" />
						</td>
						<td>
							<c:out value="${userinfomation.loginId }"/></td>
						<td>
							<c:forEach items="${branches}" var="branch">
								<c:if test="${ branch.id == userinfomation.branch}">
									<c:out value="${branch.name }"/>
								</c:if>
							</c:forEach>
						</td>
						<td>
							<c:forEach items="${divisions}" var="division">
								<c:if test="${ division.id == userinfomation.division}">
								<c:out value="${division.name }"/>
								</c:if>
							</c:forEach>
						</td>
						<td>
							<c:if test="${userinfomation.getIsStopped() == 0 }">
								<c:out value="稼働中"/>
							</c:if>
							<c:if test="${userinfomation.getIsStopped() == 1 }">
								<c:out value="停止中"/>
							</c:if>
							<c:if test="${userinfomation.isStopped == 0 && userinfomation.id != loginUser.id }" >
								<form action="isstopped" method="get">
									<input type="hidden" name="isStopped" value="${userinfomation.isStopped }" />
									<input type="hidden" name="id" value="${userinfomation.id }" />
									<script type="text/javascript">
									</script>
									<p style="display:inline;"><input type="submit" value="停止する"  onClick="return disp()"></p>
								</form>
							</c:if>
							<c:if test="${userinfomation.isStopped == 1 }" >
								<form action="isstopped" method="get">
								<input type="hidden" name="isStopped" value="${userinfomation.isStopped }" />
									<input type="hidden" name="id" value="${userinfomation.id }" />
									<p style="display:inline;"><input type="submit" value="復活させる" onClick="return disp2()"></p>
								</form>
							</c:if>
						</td>
						<td>
							<c:if test="${userinfomation.id != loginUser.id }" >
									<%-- ユーザーの削除（ポストメソッド）で送信する  --%>
									<form action="deleteUser" method="post" class="deleteUser">
										<input type="hidden" name= "id" value="${userinfomation.id }" />
										<input type="hidden" name= "name" value="${userinfomation.name }" />
										<p><input type="submit" value="削除する" onClick="return dispUser()"></p>
									</form>
							</c:if>
						</td>
						<td>
							<form action="settings" method="get">
								<input type="hidden" name="id" value="${userinfomation.id }" />
							<input type="submit" value="編集する" />
						</form>
						</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${not empty userinfomations }">
			<div class="userinfomations">
				<ul>
					<c:forEach items="${userinfomations}" var="userinfomation">
					</c:forEach>
				</ul>
			</div>
				<c:remove var="userinfomation" scope="session"/>
			</c:if>
		</div>

			<div class="copyright">Copyright(c)Hitoshi Kawase</div>



	</body>

</html>