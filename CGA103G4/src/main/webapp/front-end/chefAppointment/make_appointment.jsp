<%@page import="com.chef.model.ChefVO"%>
<%@page import="com.member.model.MemberVO"%>
<%@page import="javassist.compiler.ast.Member"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
MemberVO memVO = (MemberVO) request.getAttribute("memVO");
%>
<%
ChefVO chefVO = (ChefVO) request.getAttribute("chefVO");
%>


<html>
<head>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>私廚預約</title>

<style>
table#table-1 {
	background-color: #2C3E50;
	border: 2px solid black;
	text-align: center;
	color:white;
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

.readonly {
	background-color: rgb(197, 195, 195);
	cursor: not-allowed;
}

table {
	width: 500px;
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

.pic {
	width: 400px;
	height: 300px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.center {
	position: absolute;
	top: 10%;
	left: 10%;
}

img {
	max-width: 100%;
	max-height: 100%;
}
</style>

</head>
<body bgcolor='white'>

	<div class="center">
		<table id="table-1">
			<tr>
				<td>
					<h3>預約</h3>
				</td>
			</tr>
		</table>

		<h3>資料填寫:</h3>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<FORM METHOD="get" ACTION="chefapp.do" name="form1">
			<table id="tb01">
				<tr>
					<td>私廚:</td>
					<td><input type="TEXT" name="chefName" class="readonly"
						readonly value="小當家" /></td>
				</tr>
				<tr>
					<td>會員姓名:</td>
					<td><input type="TEXT" name="memName" size="45"
						class="readonly" readonly value="林俊宏" /></td>
				</tr>
				<tr>
					<td>連絡電話:</td>
					<td><input type="TEXT" name="memPhone" size="45" /></td>
				</tr>
				<tr>
					<td>預約日期:</td>
					<td><input name="apmDate" type="text" id="Date" readonly></td>
				</tr>
				<tr>
					<td>預約價格:</td>
					<td><input name="apmPrice" id="" type="text" value="5000"
						class="readonly" readonly></td>
				</tr>

			</table>
			<br> <input type="hidden" name="action" value="insert">
				 <input type="hidden" name="chefid" value="302">
				 <input type="hidden" name="apmTime" id="Time">				 
				 <input type="hidden" name="memid" value="201">
				 
			<input type="submit" value="確定">
		</FORM>
	</div>
</body>

<script>
		//監聽會員所選日期、時段
		window.addEventListener('storage',(e)=>{
				let date = localStorage.getItem('date');
				let time = localStorage.getItem('time');				
			$("#Date").attr("value",date);
			$("#Time").attr("value",time);
		})
		

</script>

</html>