<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orders.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>
 
<%
    OrdersService ordersSvc = new OrdersService();
    List<OrdersVO> list = ordersSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��q���� - listAllOrders.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��q���� - listAllOrders.jsp</h3>
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
		<th>�ק�</th>
<!-- 		<th>�R��</th> -->
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="ordersVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${ordersVO.ordid}</td>
			<td>${ordersVO.memid}</td>
			<td>${(ordersVO.memCpid==0)? "�L�ϥ��u�f��":ordersVO.memCpid}</td>
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
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="ordid"  value="${ordersVO.ordid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�R��"> -->
<%-- 			     <input type="hidden" name="empno"  value="${ordersVO.empno}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>