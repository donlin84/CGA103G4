<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.creditcardinformation.model.*"%>

<%
CreditCardInformationVO creditCardInformationVO = (CreditCardInformationVO) request.getAttribute("creditCardInformationVO");
%>

<!DOCTYPE html>
<html lang="zh-Hant" dir="ltr">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>信用卡資料新增 - addMember.jsp</title>
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
<body bgcolor='white'>

<%@ include file="./tools/header.jsp"%>

<div class="main_content">
<main class="main">
	

<h1>添加信用卡資訊:</h1>

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
		<td><label for="creditCardNumber">信用卡卡號 :</label></td>
		<td><input type="TEXT" name="creditCardNumber" id="creditCardNumber" size="45"
			 value="" placeholder="信用卡卡號"/></td>
	</tr>
	<tr>
		<td><label for="creditCardName">持卡人姓名 :</label></td>
		<td><input type="TEXT" name="creditCardName" id="creditCardName" size="45"
			 value="" placeholder="持卡人姓名" /></td>
	</tr>
	<tr>
		<td><label for="creditCardTime">到期月/年份 :</label></td>
		<td><input type="TEXT" name="creditCardTime" id="creditCardTime" size="45"
			 value="" placeholder="MM/YY" /></td>
	</tr>
<!-- 	<div class="form-group row"><label for="example-month-input" -->
<!--                                     class="col-sm-2 col-form-label">Month</label> -->
<!--                                 <div class="col-sm-10"><input class="form-control" type="month" value="2011-08" -->
<!--                                         id="example-month-input"></div> -->
<!--                             </div> -->
	<tr>
		<td><label for="cvcCode">安全驗證碼 :</label></td>
		<td><input type="TEXT" name="cvcCode" id="cvcCode" size="45"
			 value="" placeholder="CVC" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="memid" value="${id}">
<input type="submit" value="送出新增"></FORM>

</main>
	</div>
  
<%@ include file="../tools/footer.jsp"%>
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/popper.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>



</html>