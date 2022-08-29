<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.orders.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>
 
<%
  OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO"); //EmpServlet.java(Controller), �s�Jreq��ordersVO����
%>

<html>
<head>
<title>���u��� - listOneOrders.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOneOrders.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�q��s��</th>
		<th>�|���s��</th>
		<th>�|���u�f��s��</th>
		<th>�q��p�p</th>
		<th>�q���`�p</th>
		<th>�q�檬�A</th>
		<th>�q�榨�߮ɶ�</th>
		<th>���f�H�m�W</th>
		<th>���f�H�p���q��</th>
		<th>�q���I�覡</th>
		<th>�q����f�覡</th>
		<th>���f�a�}</th>
	</tr>
	<tr>
		<td>${ordersVO.ordid}</td>
		<td>${ordersVO.memid}</td>
		<td>${(ordersVO.memCpid==null)? "�L�ϥ��u�f��":ordersVO.memCpid}</td>
		<td>${ordersVO.ordSubTotal}</td>
		<td>${ordersVO.ordTotal}</td>
		<td>${ordersVO.ordStatus}</td> 
		<td>${ordersVO.ordCreate}</td>
		<td>${ordersVO.ordRecipient}</td>
		<td>${ordersVO.recPhone}</td>
		<td><%= (ordersVO.getOrdPayment() == 0 ) ? "�f��I��" : "��d" %></td>
		<td><%= (ordersVO.getOrdDelivery() == 0) ? "�W�Ө��f" : "�v�t�쩲" %></td>
		<td>${ordersVO.ordAddress}</td>
	</tr>
</table>

</body>
</html>