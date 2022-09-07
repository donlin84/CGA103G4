<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>



<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>會員資料新增 - addMember.jsp</title>
<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon"
	href="./assets/images/favicon.ico">
<link href="./assets/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="./assets/css/icons.css" rel="stylesheet"
	type="text/css">
<link href="./assets/css/style.css" rel="stylesheet"
	type="text/css">
<style>

html {
background-color: #FFDEA1;
}
body {
background-color: #FFDEA1;
}
</style>

</head>
<body bgcolor='white'>
	<!-- Begin page -->
	<div class="accountbg"></div>
	<div class="wrapper-page">
		<div class="card">
			<div class="card-body">
				<div class="text-center mt-2 mb-4">
					<a href="index.html" class="logo logo-admin"><img
						src="./assets/images/logo.png" height="50" alt="logo"></a>
				</div>
				<div class="px-3 pb-3">


					<h3>加入seefood會員</h3>

					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<FORM class="form-horizontal m-t-20" METHOD="post"
						ACTION="Member.do" name="form1">


						<div class="form-group row">
							<div class="col-12">
								<label for="memName">姓名:</label> <input class="form-control"
									type="TEXT" name="memName" size="15"
									value="<%=(memberVO == null) ? "列車長" : memberVO.getMemName()%>"
									id="memName" placeholder="使用者姓名" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12">
								<label for="memAccount">帳號:</label> <input class="form-control"
									type="TEXT" name="memAccount" size="25"
									value="<%=(memberVO == null) ? "test" : memberVO.getMemAccount()%>"
									id="memAccount" placeholder="使用者帳號" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12">
								<label for="memPassword">密碼:</label> <input class="form-control" type="TEXT"
									name="memPassword" size="45"
									value="<%=(memberVO == null) ? "test" : memberVO.getMemPassword()%>"
									id="memPassword" placeholder="使用者密碼" />
							</div>

						</div>
						<div class="form-group row">
							<div class="col-12">
								性別: 
							</div>
						</div>
								<input type="radio" name="memGender" size="45"
									value="<%=(memberVO == null) ? "m" : memberVO.getMemGender()%>" />男
								<input type="radio" name="memGender" size="45"
									value="<%=(memberVO == null) ? "f" : memberVO.getMemGender()%>" />女

						<div class="form-group row">
							<div class="col-12">
								<label for="memBirthday">生日:</label> <input class="form-control" name="memBirthday"
									id="memBirthday" type="text" placeholder="出生日期">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12">
								<label for="memPhone">電話:</label> <input class="form-control" type="TEXT"
									name="memPhone" size="45"
									value="<%=(memberVO == null) ? "0988111222" : memberVO.getMemPhone()%>"
									id="memPhone" placeholder="市內電話或手機號碼" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12">
								<label for="memEmail">信箱:</label> <input class="form-control" type="TEXT"
									name="memEmail" size="45"
									value="<%=(memberVO == null) ? "test@gmail.com" : memberVO.getMemEmail()%>"
									id="memEmail" placeholder="常用信箱" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12">
								<label for="memAddres">地址:</label> <input class="form-control" type="TEXT"
									name="memAddres" size="45"
									value="<%=(memberVO == null) ? "綠島" : memberVO.getMemAddres()%>"
									id="memAddres" placeholder="戶籍地址" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12">
								國家: <input class="form-control" type="TEXT" name="memNation" size="45"
									value="<%=(memberVO == null) ? "非洲" : memberVO.getMemNation()%>" />
							</div>
						</div>




						<br> <input type="hidden" name="action" value="insert">
						<input type="submit" value="註冊">


					</FORM>

				</div>
			</div>
		</div>
	</div>
	<!-- jQuery -->
	<script src="./assets/js/jquery.min.js"></script>
	<script src="./assets/js/popper.min.js"></script>
	<script src="./assets/js/bootstrap.min.js"></script>
	<script src="./assets/js/modernizr.min.js"></script>
	<script src="./assets/js/waves.js"></script>
	<script src="./assets/js/jquery.slimscroll.js"></script>
	<script src="./assets/js/jquery.nicescroll.js"></script>
	<script src="./assets/js/jquery.scrollTo.min.js"></script>

	<!-- App js -->
	<script src="./assets/js/app.js"></script>

</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->
<link rel="stylesheet" type="text/css"
	href="../../back-end/member/datetimepicker/jquery.datetimepicker.css" />
<script src="../../back-end/member/datetimepicker/jquery.js"></script>
<script src="../../back-end/member/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	// 	$.datetimepicker.setLocale('zh'); // kr ko ja en
	// 	$(function() {
	// 		$('#memBirthday').datetimepicker(
	// 				{
	// 					format : 'Y-m-d',
	// 					onShow : function() {
	// 						this.setOptions({
	// 							maxDate : $('#end_date').val() ? $('#end_date')
	// 									.val() : false
	// 						})
	// 					},
	// 					timepicker : false
	// 				});

	// 	});

	$.datetimepicker.setLocale('zh');
	$('#memBirthday').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>


</html>