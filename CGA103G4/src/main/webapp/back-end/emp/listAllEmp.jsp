<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<!--    以下等同第9行 -->
<%--    <jsp:useBean id="list" scope="session" type="java.util.List<EmpVO>" /> --%>
<%
	EmpDAO dao = new EmpDAO();
    List<EmpVO> list = dao.getAll();       // 此行的list變數(物件)將提供page1.file的第11行取得查詢到的總筆數，再由page1.file進行分頁的需要
    pageContext.setAttribute("list",list); // 將上一行的list變數(物件)存入當前頁面pageContext，再由底下的第83行由JSTL的forEach列印出結果
%>

<html>
<head>
<title>所有管理員資料</title>

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



<table>
	<tr>
		<th>管理員編號</th>
		<th>管理員姓名</th>
		<th>聯絡電話</th>
		<th>管理員照片</th>
		<th>帳號</th>
		<th>管理員等級</th>
		<th>管理員狀態</th>
		<th>雇用日期</th>
		<th>管理員管理</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${empVO.empid}</td>
			<td>${empVO.empName}</td>
			<td>${empVO.empPhone}</td>
			<td><img src="<%= request.getContextPath() %>/EmpShowPic?empid=${empVO.empid}" width="100" heigh="100"></td>
			<td>${empVO.empAccount}</td>
			<td>${empVO.empLevel}</td>			
			<td>${empVO.empStatus}</td>
			<td>${empVO.empHiredate}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="empid"  value="${empVO.empid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>