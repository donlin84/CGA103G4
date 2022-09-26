<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cartdetail.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	Integer memid = (Integer) request.getAttribute("memid");
	CartDetailService cartdetailSvc = new CartDetailService();
	List<CartDetailVO> list = cartdetailSvc.getOnes(memid);
	pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>食譜圖片資料 - listOneCartDetail.jsp</title>

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
	width: 1400px;
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>食譜圖片資料 - ListOneRecipe.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>商品編號</th>
		<th>商品數量</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="cartDetailVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
		<td>${cartDetailVO.memid}</td>
		<td>${cartDetailVO.pdid}</td>
		<td>${cartDetailVO.pdNumber}</td>
	</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>