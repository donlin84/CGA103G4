<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.registtrationform.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

<%
	RegisttrationFormVO registtrationFormVO = (RegisttrationFormVO) request.getAttribute("registtrationFormVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneRegisttrationForm.jsp</title>

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
		 <h3>員工資料 - ListOneRegisttrationForm.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>課程ID</th>
		<th>會員ID</th>
		<th>付款方式</th>
		<th>報名時間</th>
		<th>訂單狀態</th>
		<th>評價</th>
		<th>評價內容</th>
	</tr>
	<tr>

		<td>${registtrationFormVO.claid }</td>
		<td>${registtrationFormVO.memid}</td>
		<td>${registtrationFormVO.regPayment}</td>
		<td>${fn:replace(registtrationFormVO.regTime, "T", " ")}</td>
		<td>${registtrationFormVO.regStatus}</td>
		<td>${registtrationFormVO.regReview}</td>
		<td>${registtrationFormVO.regReviewContent}</td>
	</tr>
</table>


</body>
</html>