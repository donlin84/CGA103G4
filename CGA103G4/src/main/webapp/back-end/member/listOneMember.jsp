<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //MemberServlet.java(Concroller), 存入req的memberVO物件
%>

<html>
<head>
<title>會員資料 - listOneMember.jsp</title>

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
	width: 1000px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>會員資料 - ListOneMember.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>性別</th>
		<th>電話</th>
		<th>信箱</th>
		<th>地址</th>
		<th>生日</th>
		<th>狀態</th>
		<th>國家</th>
	</tr>
	<tr>
		<td><%=memberVO.getMemid()%></td>
		<td><%=memberVO.getMemName()%></td>
		<td><%=memberVO.getMemAccount()%></td>
		<td><%=memberVO.getMemPassword()%></td>
		<td><%=memberVO.getMemGender()%></td>
		<td><%=memberVO.getMemPhone()%></td>
		<td><%=memberVO.getMemEmail()%></td>
		<td><%=memberVO.getMemAddres()%></td>
		<td><%=memberVO.getMemBirthday()%></td>
		<td><%=memberVO.getMemStatus()%></td>
		<td><%=memberVO.getMemNation()%></td>
	</tr>
</table>

</body>
</html>