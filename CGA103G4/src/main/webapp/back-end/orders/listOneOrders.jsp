<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.orders.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
 
<%
  OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO"); //EmpServlet.java(Controller), 存入req的ordersVO物件
%>

<html>
<head>
<title>員工資料 - listOneOrders.jsp</title>

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
		 <h3>員工資料 - ListOneOrders.jsp</h3>
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
	</tr>
	<tr>
		<td>${ordersVO.ordid}</td>
		<td>${ordersVO.memid}</td>
		<td>${(ordersVO.memCpid==null)? "無使用優惠券":ordersVO.memCpid}</td>
		<td>${ordersVO.ordSubTotal}</td>
		<td>${ordersVO.ordTotal}</td>
		<td>${ordersVO.ordStatus}</td> 
		<td>${ordersVO.ordCreate}</td>
		<td>${ordersVO.ordRecipient}</td>
		<td>${ordersVO.recPhone}</td>
		<td><%= (ordersVO.getOrdPayment() == 0 ) ? "貨到付款" : "刷卡" %></td>
		<td><%= (ordersVO.getOrdDelivery() == 0) ? "超商取貨" : "宅配到府" %></td>
		<td>${ordersVO.ordAddress}</td>
	</tr>
</table>

</body>
</html>