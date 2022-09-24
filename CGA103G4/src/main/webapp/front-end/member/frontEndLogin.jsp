<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>


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
ul {
/* border:1px solid gray; */
padding:0;
}
 ul li { 
 height:25px; 
 line-height:25px; 
 position:relative; 
 list-style:none; 
/*  font-size:12px;  */
 } 
.rightStyle {
display:block;
float:right; 
margin-top:0;
position:absolute;
right:0;
top:0;
</style>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>


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
				<h3>登入seefood</h3>


					<!-- 錯誤表列 -->

					
					<c:if test="${not empty errorMsgs}">
<!-- 						<font style="color: red">請修正以下錯誤:</font> -->
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					
					<form class="form-horizontal m-t-20" action="frontendloginhandler" method="post">
						<div class="form-group row">
							<div class="col-12">
								<input class="form-control" type="text" placeholder="Account or E-mail" name="account" value="" size=15>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12">
								<input class="form-control" type="password" placeholder="Password" name="password" value="" size=15>
							</div>
						</div>
						
						
						



	
						<ul>
				
<%
String rand = (String)session.getAttribute("rand");
%>

<li>
						
						
<%
if(rand == null){
	rand = "5928";
// 	out.print(rand); 
} else {
// 	out.print(rand); 
}
%>							
							<input type="text"size="45" class="form-control"
							value="" id="checkmemPassword" required="required" oninput="setCustomValidity('');" 
							onchange="if(document.getElementById('memPassword').value != document.getElementById('checkmemPassword').value) 
							{setCustomValidity('驗證碼不符合');}" placeholder="驗證碼" style="width:100px;"/>
						<p class="rightStyle">
								<input class="form-control" type="text"
									name="memPassword" size="45"
									value="<%=rand%>"
									id="memPassword" style="width:60px;" disabled/>
							


<br>
		<img src="../../vcode">
									</li>
						</ul>
		
						<div class="form-group text-center row m-t-20">
							<div class="col-12">
								<button class="btn btn-danger btn-block waves-effect waves-light" type=submit value="  ok  ">Log In</button>
							</div>
						</div>
						<div class="form-group m-t-10 mb-0 row">
							<div class="col-sm-7 m-t-20">
								<a href="forgotaccount.jsp" class="text-muted"><i class="mdi mdi-lock"></i><small>Forgot your
										account or E-mail?</small></a>
							</div>
							<div class="col-sm-7 m-t-20">
								<a href="forgotpassword.jsp" class="text-muted"><i class="mdi mdi-lock"></i><small>Forgot your
										password ?</small></a>
							</div>
							<div class="col-sm-5 m-t-20">
								<a href="addMember.jsp" class="text-muted"><i class="mdi mdi-account-circle"></i><small>Create
										an account ?</small></a>
							</div>
						</div>
					</form>

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
<script>
// window.onload = function() {
//     if(!window.location.hash) {
//         window.location = window.location + '#loaded';
//         window.location.reload();
//     }
// }

</script>
</body>

</html>