<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

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
	width: auto;
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
		 <h3>���u��� - ListOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�޲z���s��</th>
		<th>�޲z���m�W</th>
		<th>�p���q��</th>
		<th>�޲z���Ӥ�</th>
		<th>�b��</th>
		<th>�K�X</th>
		<th>�޲z������</th>
		<th>�޲z�����A</th>
		<th>���Τ��</th>
		<th>�޲z���޲z</th>
	</tr>
	<tr>
		<td><%=empVO.getEmpid()%></td>
		<td><%=empVO.getEmpName()%></td>
		<td><%=empVO.getEmpPhone()%></td>
<td><img src="<%= request.getContextPath() %>/EmpShowPic?empid=${empVO.empid}" width="100" heigh="100"></td>
		<td><%=empVO.getEmpAccount()%></td>
		<td><%=empVO.getEmpPassword()%></td>
		<td><%=empVO.getEmpLevel()%></td>
		<td><%=empVO.getEmpStatus()%></td>
		<td><%=empVO.getEmpHiredate()%></td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="empid"  value="${empVO.empid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
	</tr>
</table>

</body>
</html>