<%@page import="java.util.Arrays"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.fr.model.*"%>
<%@ page import="java.time.LocalDate"%>

<%
ForumVO frVO = (ForumVO) request.getAttribute("frVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�Q�װϭק�</title>

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
	width: 450px;
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

.pic {
	width: 400px;
	height: 300px;
	display: flex;
	justify-content: center;
	align-items: center;
}

img {
	max-width: 100%;
	max-height: 100%;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>�Q�װϸ�ƭק�</h3>
				<h4>
					<a href="select_page.jsp">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>
	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/forum/fr.do" name="form1">
		<table>
			<tr>
				<td>�Q�װϽs��:<font color=red><b>*</b></font></td>
				<td><%=frVO.getFrid()%></td>
			</tr>
			<tr>
				<td>�Q�װϦW��:</td>
				<td><input type="TEXT" name="frname" size="45"
					value="<%=frVO.getFrName()%>" /></td>
			</tr>

		</table>
		<br> 
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="frid" value="<%=frVO.getFrid()%>"> 
		<input type="submit" value="�e�X�ק�">
	</FORM>
</body>
</html>