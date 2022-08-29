<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.creditcardinformation.model.*"%>

<%
CreditCardInformationService creditCardInformationSvc = new CreditCardInformationService();
    List<CreditCardInformationVO> list = creditCardInformationSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有信用卡資料 - listAllCreditCardInformation.jsp</title>

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
		 <h3>所有信用卡資料 - listAllCreditCardInformation.jsp</h3>
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
		<th>刪除</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="creditCardInformationVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${creditCardInformationVO.creditCardid}</td>
			<td>${creditCardInformationVO.memid}</td>
			<td>${creditCardInformationVO.creditCardNumber}</td>
			<td>${creditCardInformationVO.creditCardName}</td>
			<td>${creditCardInformationVO.creditCardTime}</td>
			<td>${creditCardInformationVO.cvcCode}</td> 
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/creditCardInformation/creditCardInformation.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="creditCardid"  value="${creditCardInformationVO.creditCardid}">
			     <input type="hidden" name="action"	value="delete"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>