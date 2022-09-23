<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>login</title>
<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon" href="../back-end/assets/images/favicon.ico">
<link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="assets/css/style.css" rel="stylesheet" type="text/css">
<style>

html {
background-color: #FFDEA1;
}
body {
background-color: #FFDEA1;
}
</style>
</head>

<body>
	<!-- Begin page -->
	<div class="accountbg"></div>
	<div class="wrapper-page">
		<div class="card">
			<div class="card-body">
				<div class="text-center mt-2 mb-4">
					<a href="../index-front.jsp" class="logo logo-admin"><img src="assets/images/logo.png" height="50" alt="logo"></a>
				</div>
				<div class="px-3 pb-3">
				<h3>尋找您的帳號與信箱</h3>
					<!-- 錯誤表列 -->

					
					<c:if test="${not empty errorMsgs}">
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					

					<form class="form-horizontal m-t-20" action="alertservice" method="post">
						<div class="form-group row">
							<div class="col-12">
								<label for="name">請輸入您的姓名:</label>
								<input class="form-control" type="text" placeholder="name" name="name" value="" size=15>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12">
								<label for="phone">請輸入您的電話:</label>
								<input class="form-control" type="text" placeholder="phone" name="phone" value="" size=15>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12">
					
								<label for="gender">請選擇您的性別:</label><br>
								<input type="radio" name="gender" size="45"
									value="m" />男
								<input type="radio" name="gender" size="45"
									value="f" />女
							</div>
						</div>
				
						<div class="form-group text-center row m-t-20">
							<div class="col-12">
								<button class="btn btn-danger btn-block waves-effect waves-light" type=submit value="  ok  ">送出</button>
							</div>
						</div>
						
						已經想起帳號或信箱了?<a href="frontEndLogin.jsp">登入</a>
						
					</form>
						</div>

				</div>
			</div>
		</div>
	</div>
	<!-- jQuery -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/popper.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/modernizr.min.js"></script>
	<script src="assets/js/waves.js"></script>
	<script src="assets/js/jquery.slimscroll.js"></script>
	<script src="assets/js/jquery.nicescroll.js"></script>
	<script src="assets/js/jquery.scrollTo.min.js"></script>
	<!-- App js -->
	<script src="assets/js/app.js"></script>
</body>

</html>