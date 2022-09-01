<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orders.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
 
<%
    OrdersService ordersSvc = new OrdersService();
    List<OrdersVO> list = ordersSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有訂單資料 - listAllOrders.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有訂單資料 - listAllOrders.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>會員優惠券編號</th>
		<th>訂單小計</th>
		<th>訂單總計</th>
		<th>訂單狀態</th>
		<th>訂單成立時間</th>
		<th>取貨人姓名</th>
		<th>取貨人聯絡電話</th>
		<th>訂單支付方式</th>
		<th>訂單取貨方式</th>
		<th>取貨地址</th>
		<th>修改</th>
<!-- 		<th>刪除</th> -->
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="ordersVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${ordersVO.ordid}</td>
			<td>${ordersVO.memid}</td>
			<td>${(ordersVO.memCpid==0)? "無使用優惠券":ordersVO.memCpid}</td>
			<td>${ordersVO.ordSubTotal}</td>
			<td>${ordersVO.ordTotal}</td>
			<td>${ordersVO.ordStatus}</td> 
			<td>${ordersVO.ordCreate}</td>
			<td>${ordersVO.ordRecipient}</td>
			<td>${ordersVO.recPhone}</td>
			<td>${ordersVO.ordPayment}</td>
			<td>${ordersVO.ordDelivery}</td>
			<td>${ordersVO.ordAddress}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/orders/orders.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ordid"  value="${ordersVO.ordid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="empno"  value="${ordersVO.empno}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>