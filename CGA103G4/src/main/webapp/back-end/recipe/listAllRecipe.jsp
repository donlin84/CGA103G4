<%@page import="com.recipe.model.RecipeVO"%>
<%@page import="com.recipe.model.RecipeService"%>
<%-- <%@page import="com.recipe.model.RecipeService"%> --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.recipe.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	RecipeService recipeSvc = new RecipeService();
	List<RecipeVO> list = recipeSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>�Ҧ����йϤ���� - listAllRecipe.jsp</title>

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
		 <h3>�Ҧ����йϤ���� - listAllRecipe.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���нs��</th>
		<th>�o���̽s��</th>
		<th>���м��D</th>
		<th>���Ф��e</th>
		<th>�o���ɶ�</th>
		<th>�̫��s�ɶ�</th>
		<th>�ק�</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="recipeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${recipeVO.reid}</td>
			<td>${recipeVO.memid}</td>
			<td>${recipeVO.reTitle}</td>
			<td>${recipeVO.reContext}</td>
			<td>${recipeVO.reSTime}</td>
			<td>${recipeVO.reLTime}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/recipe/recipe.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="reid"  value="${recipeVO.reid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/recipepicture/recipePicture.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�R��"> -->
<%-- 			     <input type="hidden" name="rePicid"  value="${recipePictureVO.rePicid}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>