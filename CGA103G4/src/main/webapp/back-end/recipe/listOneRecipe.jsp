<%@page import="com.recipe.model.RecipeVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.recipe.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	RecipeVO recipeVO = (RecipeVO) request.getAttribute("recipeVO");
// 	OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO"); //EmpServlet.java(Controller), �s�Jreq��ordersVO����
%>

<html>
<head>
<title>���йϤ���� - listOneRecipe.jsp</title>

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
		 <h3>���йϤ���� - ListOneRecipe.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���нs��</th>
		<th>�|���s��</th>
		<th>���м��D</th>
		<th>���Ф��e</th>
		<th>�W�Ǯɶ�</th>
		<th>�̫��s�ɶ�</th>
	</tr>
	<tr>
		<td>${recipeVO.reid}</td>
		<td>${recipeVO.memid}</td>
		<td>${recipeVO.reTitle}</td>
		<td>${recipeVO.reContext}</td>
		<td>${recipeVO.reSTime}</td>
		<td>${recipeVO.reLTime}</td>
	</tr>
</table>

</body>
</html>