<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%
MemberService memberSvc = new MemberService();
List<MemberVO> list = memberSvc.getAll();
pageContext.setAttribute("list", list);
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<!DOCTYPE html>
<html lang="zh-Hant" dir="ltr">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

  <title>《See Food》官方網站</title>
  <link rel="stylesheet" href="../css/common/all.css">
  <link rel="stylesheet" href="../css/common/header.css">
  <link rel="stylesheet" href="../css/common/footer.css">
  <link rel="stylesheet" href="../css/common/main.css">
  <link rel="stylesheet" href="../css/chef.css">
  <link href="../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="../assets/css/style.css" rel="stylesheet" type="text/css">
  <script src="../js/chef.js"></script>
  <script src="../js/nav.js"></script>
  <style>
 table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	font-size:20px;
	
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }

  div.main_content {
	width: 50%;
	margin: 0 auto;
	font-size: 0;

	/*   border: 1px solid red; */
}


main.main {
/* 	   background-color: #ddd;  */
	width:800px;
	display: inline-block;
	vertical-align: top;
	font-size: 1rem;
	

	/*   border: 1px solid #999; */
}
</style>
</head>

<body>

<%@ include file="./tools/header.jsp"%>
<div class="main_content">
<main class="main">
<h1>基本會員資料:</h1>
 <table>
			<c:forEach var="memberVO" items="${list}">

				<c:choose>

					<c:when test="${account == memberVO.memAccount}">
						<tr>
						
						
				<td>會員姓名 : </td>
				<td>${memberVO.memName}</td>
			</tr>
			<tr>
				<td>帳號 : </td>
				<td>${memberVO.memAccount}</td>
			</tr>
			<tr>
				<td>密碼 : </td>
				<td>${memberVO.memPassword}</td>
			</tr>

			<tr>
				<td>性別 : </td>
				<td>
				${memberVO.memGender.equals("m") ? "男" : "女"}</td>
			</tr>
			<tr>
				<td>電話 : </td>
				<td>${memberVO.memPhone}</td>
			</tr>
			<tr>
				<td>信箱 : </td>
				<td>${memberVO.memEmail}</td>
			</tr>
			<tr>
				<td>地址 : </td>
				<td>${memberVO.memAddres}</td>
			</tr>
			<tr>
				<td>生日 : </td>
				<td>${memberVO.memBirthday}</td>
			</tr>
			
			<tr>
				<td>國家 : </td>
				<td>${memberVO.memNation}</td>
			</tr>
			<tr>
			<td></td>
			<td>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/member/Member.do">
		<input type="submit" value="修改"> 
		<input type="hidden" name="memid" value="${memberVO.memid}"> 
		<input type="hidden" name="action" value="getOne_For_Update">
		</FORM>
		</td>
		</tr>
					</c:when>
					<c:otherwise>


					</c:otherwise>
				</c:choose>

			</c:forEach>
		</table>
	
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br>
  	</main>
	</div>
<%@ include file="../tools/footer.jsp"%>
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/popper.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>

</html>