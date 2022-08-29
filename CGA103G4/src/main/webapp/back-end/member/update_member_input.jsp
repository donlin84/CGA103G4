<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //MemberServlet.java (Concroller) 存入req的memberVO物件 (包括幫忙取出的memberVO, 也包括輸入資料錯誤時的memberVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>會員資料修改 - update_member_input.jsp</title>

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

	<table id="table-1">
		<tr>
			<td>
				<h3>會員資料修改 - update_member_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

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
				<td>會員編號:<font color=red><b>*</b></font></td>
				<td><%=memberVO.getMemid()%></td>
			</tr>
			<tr>
				<td>帳號:</td>
				<td><input type="TEXT" name="memAccount" size="45"
					value="<%=memberVO.getMemAccount()%>" id="memAccount" readonly/></td>
			</tr>
			<tr>
				<td><label for="memName">會員姓名:</label></td>
				<td><input type="TEXT" name="memName" size="45"
					value="<%=memberVO.getMemName()%>" id="memName" /></td>
			</tr>
			<tr>
				<td><label for="memPassword">密碼:</label></td>
				<td><input type="TEXT" name="memPassword" size="45"
					value="<%=memberVO.getMemPassword()%>" id="memPassword" /></td>
			</tr>

			<tr>
				<td>性別:</td>
				<td>
				<label for="memGender">男</label>
				<input type="radio" name="memGender" size="45" value="m" <%=memberVO.getMemGender().equals("m")? "checked" : "" %> /> 
				<label for="memGender">女</label>
				<input type="radio" name="memGender" size="45" value="f"   <%=memberVO.getMemGender().equals("f") ? "checked" : "" %>/>
				</td>
			</tr>
			<tr>
				<td><label for="memPhone">電話:</label></td>
				<td><input type="TEXT" name="memPhone" size="45"
					value="<%=memberVO.getMemPhone()%>" id="memPhone" /></td>
			</tr>
			<tr>
				<td><label for="memEmail">信箱:</label></td>
				<td><input type="TEXT" name="memEmail" size="45"
					value="<%=memberVO.getMemEmail()%>" id="memEmail" /></td>
			</tr>
			<tr>
				<td><label for="memAddres">地址:</label></td>
				<td><input type="TEXT" name="memAddres" size="45"
					value="<%=memberVO.getMemAddres()%>" id="memAddres" /></td>
			</tr>
			<tr>
				<td><label for="memBirthday">生日:</label></td>
				<td><input name="memBirthday" id="memBirthday" type="text"></td>
			</tr>
			<tr>
				<td>狀態:</td>
				<c:if test="${memberVO.getMemStatus() == 0}">
					<td><select name="memStatus" size="1">
							<option value="0">啟用
							<option value="1">停權
					</select></td>
				</c:if>
				<c:if test="${memberVO.getMemStatus() == 1}">
					<td><select name="memStatus" size="1">
							<option value="1">停權
							<option value="0">啟用
					</select></td>
				</c:if>
			</tr>
			<tr>
				<td>國家:</td>
				<td><input type="TEXT" name="memNation" size="45"
					value="<%=memberVO.getMemNation()%>" /></td>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="memid" value="<%=memberVO.getMemid()%>">
		<input type="submit" value="送出修改">
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
$.datetimepicker.setLocale('zh');
$('#memBirthday').datetimepicker({
   theme: '',              //theme: 'dark',
    timepicker:false,       //timepicker:true,
    step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
    format:'Y-m-d',         //format:'Y-m-d H:i:s',
	   value: '<%=memberVO.getMemBirthday()%>', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});

</script>


</html>