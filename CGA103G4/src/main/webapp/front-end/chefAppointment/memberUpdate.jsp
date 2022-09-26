<%@page import="java.util.Arrays"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chefappointmentform.model.*"%>
<%@ page import="java.time.LocalDate"%>
<%
ChefAppointmentFormVO chefAppVO = (ChefAppointmentFormVO) request.getAttribute("chefAppVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%
LocalDate now = LocalDate.now();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>預約單修改</title>
 <link rel="stylesheet" href="../css/common/all.css">
 <link rel="stylesheet" href="../css/common/header.css">
 <link rel="stylesheet" href="../css/common/footer.css">
 <link rel="stylesheet" href="../css/common/main.css">
 <link rel="stylesheet" href="../css/chef.css">
 <link href="../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="../assets/css/style.css" rel="stylesheet" type="text/css">

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
	margin-left: 50%;
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

img {
	max-width: 100%;
	max-height: 100%;
}
</style>

</head>
<body bgcolor='white'>
	<%@ include file="./tools/header.jsp"%>
	
	<div class="main">
		<div class="container -on" id="chefPage">
			<div id="chefPageContent">
				<div class="sectionOne">

					<h3 style="margin-left:100px">給予評價:</h3>
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<FORM METHOD="post" ACTION="chefapp.do" name="form1" style="margin-right:40%">
						<table>
							<tr>
								<td>預約單編號:</td>
								<td><%=chefAppVO.getApmid()%></td>
							</tr>
							<tr>
								<td>私廚名稱:</td>
								<td><%=chefAppVO.getChefVO().getChefName()%></td>
							</tr>

							<tr>
								<td>評價(星):</td>
								<td><select size="1" name="star">
										<option value="1">★</option>
										<option value="2">★★</option>
										<option value="3">★★★</option>
										<option value="4">★★★★</option>
										<option value="5">★★★★★</option>
								</select></td>
							</tr>

							<tr>
								<td>評論:</td>
								<td><textarea rows="15" cols="40" name="comments"
										style="resize: none">${chefAppVO.comments}</textarea></td>
							</tr>



						</table>
						<br> <input type="hidden" name="action" value="updateMem">
						<input type="hidden" name="apmid"
							value="<%=chefAppVO.getApmid()%>"> <input type="submit"
							value="送出修改" style="margin-left:500px">
					</FORM>
				</div>
			</div>
		</div>
	</div>
		<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/popper.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
<%-- 	<%@ include file="./tools/footer.jsp"%> --%>
</body>

</html>