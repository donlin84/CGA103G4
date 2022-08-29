<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/bootstrap-select.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-select.min.js"></script>
<script src="js/countrypicker.min.js"></script>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>會員資料新增 - addMember.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>
<!-- <select class="selectpicker countrypicker"></select> -->
	<table id="table-1">
		<tr>
			<td>
				<h3>員工資料新增 - addMember.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="member.do" name="form1">
		<table>
			<tr>
				<td><label for="memName">姓名:</label></td>
				<td><input type="TEXT" name="memName" size="45"
					value="<%=(memberVO == null) ? "" : memberVO.getMemName()%>"
					 id="memName" placeholder="使用者姓名"/></td>
				
			</tr>
			<tr>
				<td><label for="memAccount">帳號:</label></td>
				<td><input type="TEXT" name="memAccount" size="45"
					value="<%=(memberVO == null) ? "" : memberVO.getMemAccount()%>"
					 id="memAccount" placeholder="使用者帳號" /></td>
			</tr>
			<tr>
				<td><label for="memPassword">密碼:</label></td>
				<td><input type="TEXT" name="memPassword" size="45"
					value="<%=(memberVO == null) ? "" : memberVO.getMemPassword()%>"
					 id="memPassword" placeholder="使用者密碼" /></td>
			</tr>
			<tr>
				<td>性別:</td>
				<td><input type="radio" name="memGender" size="45"
					value="<%=(memberVO == null) ? "m" : memberVO.getMemGender()%>" />男
				<input type="radio" name="memGender" size="45"
					value="<%=(memberVO == null) ? "f" : memberVO.getMemGender()%>" />女
				</td>
			</tr>
			<tr>
				<td><label for="memBirthday">生日:</label></td>
				<td><input name="memBirthday" id="memBirthday" type="text" placeholder="出生日期" ></td>
			</tr>
			<tr>
				<td><label for="memPhone">電話:</label></td>
				<td><input type="TEXT" name="memPhone" size="45"
					value="<%=(memberVO == null) ? "" : memberVO.getMemPhone()%>"
					id="memPhone" placeholder="市內電話或手機號碼" /></td>
			</tr>
			<tr>
				<td><label for="memEmail">信箱:</label></td>
				<td><input type="TEXT" name="memEmail" size="45"
					value="<%=(memberVO == null) ? "" : memberVO.getMemEmail()%>"
					 id="memEmail" placeholder="常用信箱" /></td>
			</tr>
			<tr>
				<td><label for="memAddres">地址:</label></td>
				<td><input type="TEXT" name="memAddres" size="45"
					value="<%=(memberVO == null) ? "" : memberVO.getMemAddres()%>"
					 id="memAddres" placeholder="戶籍地址" /></td>
			</tr>

			<tr>
				<td>國家:</td>
				<td><input type="TEXT" name="memNation" size="45"
					value="<%=(memberVO == null) ? "非洲" : memberVO.getMemNation()%>" /></td>
			</tr>
		
		

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->
<link rel="stylesheet" type="text/css"
	href="datetimepicker/jquery.datetimepicker.css" />
<script src="datetimepicker/jquery.js"></script>
<script src="datetimepicker/jquery.datetimepicker.full.js"></script>

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
       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '', // value:   new Date(),
       //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
       //startDate:	            '2017/07/10',  // 起始日
       //minDate:               '-1970-01-01', // 去除今日(不含)之前
       //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
    });
</script>


</html>