<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.creditcardinformation.model.*"%>

<%
CreditCardInformationVO creditCardInformationVO = (CreditCardInformationVO) request.getAttribute("creditCardInformationVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>信用卡資料新增 - addMember.jsp</title>

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
	width: 600px;
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
	<tr><td>
		 <h3>信用卡資料新增 - addCreditCardInformation.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="CreditCardInformation.do" name="form1">
<table>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="memid" size="45" 
			 value="<%= (creditCardInformationVO==null)? "" : creditCardInformationVO.getMemid()%>" /></td>	<!-- "" -->
	</tr>
	<tr>
		<td>信用卡卡號:</td>
		<td><input type="TEXT" name="creditCardNumber" size="45"
			 value="<%= (creditCardInformationVO==null)? "1111222233334444" : creditCardInformationVO.getCreditCardNumber()%>" /></td>
	</tr>
	<tr>
		<td>持卡人姓名:</td>
		<td><input type="TEXT" name="creditCardName" size="45"
			 value="<%= (creditCardInformationVO==null)? "張歪綠" : creditCardInformationVO.getCreditCardName()%>" /></td>
	</tr>
	<tr>
		<td>到期月/年份:</td>
		<td><input type="TEXT" name="creditCardTime" size="45"
			 value="<%= (creditCardInformationVO==null)? "1227" : creditCardInformationVO.getCreditCardTime()%>" /></td>
	</tr>
	<tr>
		<td>安全驗證碼:</td>
		<td><input type="TEXT" name="cvcCode" size="45"
			 value="<%= (creditCardInformationVO==null)? "123" : creditCardInformationVO.getCvcCode()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



</html>