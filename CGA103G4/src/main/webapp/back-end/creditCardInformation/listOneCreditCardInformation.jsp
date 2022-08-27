<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.creditcardinformation.model.*"%>

<%
CreditCardInformationVO creditCardInformationVO = (CreditCardInformationVO) request.getAttribute("creditCardInformationVO"); //CreditCardInformationServlet.java(Concroller), 存入req的creditCardInformationVO物件
%>

<html>
<head>
<title>信用卡資料 - listOneCreditCardInformation.jsp</title>

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
		 <h3>信用卡資料 - ListOneCreditCardInformation.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>信用卡編號</th>
		<th>會員編號</th>
		<th>信用卡卡號</th>
		<th>持卡人姓名</th>
		<th>到期月/年份</th>
		<th>安全驗證碼</th>
		
	</tr>
	<tr>
		<td><%=creditCardInformationVO.getCreditCardid()%></td>
		<td><%=creditCardInformationVO.getMemid()%></td>
		<td><%=creditCardInformationVO.getCreditCardNumber()%></td>
		<td><%=creditCardInformationVO.getCreditCardName()%></td>
		<td><%=creditCardInformationVO.getCreditCardTime()%></td>
		<td><%=creditCardInformationVO.getCvcCode()%></td>
		
	</tr>
</table>

</body>
</html>