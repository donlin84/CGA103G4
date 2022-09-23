<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.creditcardinformation.model.*"%>
<%
CreditCardInformationService creditCardInformationSvc = new CreditCardInformationService();
List<CreditCardInformationVO> list = creditCardInformationSvc.getAll();
pageContext.setAttribute("list", list);
CreditCardInformationVO creditCardInformationVO = (CreditCardInformationVO) request.getAttribute("creditCardInformationVO");
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
<h1>信用卡資料:</h1>
 <table>
			<c:forEach var="creditCardInformationVO" items="${list}">
<%-- ${creditCardInformationVO.memid} --%>
<%-- ${account} --%>
<%-- ${id} --%>
				<c:choose>

					<c:when test="${id == creditCardInformationVO.memid}">
						<tr>
						
						
				<td>信用卡卡號 : </td>
				<td>${creditCardInformationVO.creditCardNumber}</td>
			<td>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/creditCardInformation/CreditCardInformation.do">
		<input type="submit" value="刪除"> 
		<input type="hidden" name="creditCardid" value="${creditCardInformationVO.creditCardid}"> 
		<input type="hidden" name="action" value="delete">
		</FORM>
		</td>
			</tr>
			
			</c:when>
					<c:otherwise>


					</c:otherwise>
				</c:choose>

			</c:forEach>
		</table>
			<div class="form-group text-center row m-t-20">
							<div class="col-12">
								<button class="btn btn-danger btn-block waves-effect waves-light"><a class="dropdown-item" href='./addCreditCardInformation.jsp'>新增信用卡</a></button>
							</div>
						</div>

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