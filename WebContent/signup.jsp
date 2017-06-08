<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ユーザー新規登録画面</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
		<!--

		function insert(){
		if(window.confirm('この内容で登録します')){
			location.href = "usercontroll";
			return true;
		}
			// 「OK」時の処理終了
			return false;
		}

		 -->
		</script>
	</head>
	<body>
	<div class="back">
			<a href="usercontroll">戻る</a>
			<a href="./">ホームへ</a>
		</div>
		<div class="main-contents2">
			<h1>ユーザー新規登録画面</h1>

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
			<div class="form"  style="border-top: 1px groove;">
				<form action="signup" method="post"><br />
					<label for="loginId">ログインID(半角英数6文字以上20字以下)</label>
					<input name="loginId" placeholder="ログインIDを入力"  value="${userSettings.loginId}" id="loginId"/>

					<label for="password">パスワード（6文字以上20文字以下） </label>
					<input name="password" type="password" placeholder="パスワードを入力" id="password"/><br />

					<label for="password2">パスワード(確認用)</label>
					<input name="password2" type="password" id="password2"/> <br />

					<label for="name">名称（10文字以下）</label>
					<input name="name"  placeholder="名称を入力"  value="${userSettings.name}" id="name"/><br />
					支店
					<div >
						<select class= "branchDivision" name="branch">
							<c:forEach items = "${branches}" var="branches">
								<option value="${branches.id}" <c:if test="${userSettings.branch == branches.id}">selected
									</c:if>
									><c:out value="${branches.name}"/>
								</option>
							</c:forEach>
						</select>
					</div>
					部署・役職
					<div class= "division">
						<select  class= "branchDivision" name="division">
							<c:forEach items = "${divisions}" var="divisions">
								<option value="${divisions.id}"<c:if test="${userSettings.division == divisions.id}">selected
									</c:if>
									><c:out value="${divisions.name}"/>
								</option>
							</c:forEach>
						</select>
					<br />
					</div>
					<div align="center">
					<input type="submit" class="submit"  value="この内容で登録" onClick="return insert()" /> <br />
					</div>
				</form>
			</div>
		</div>

		<div class="copyright">Copyright(c)Hitoshi Kawase</div>
	</body>
</html>
