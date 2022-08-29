<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.teacher.model.*"%>
<%@ page import="java.util.*"%>


<%
  TeacherVO teacherVO = (TeacherVO) request.getAttribute("teacherVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneTeacher.jsp</title>

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
	width: 1000px;
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

<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneTeacher.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>教師ID</th>
		<th>教師姓名</th>
		<th>教師性別</th>
		<th>教師電話</th>
		<th>教師信箱</th>
		<th>教師狀態</th>
		<th>教師簡介</th>
		<th>教師總星數</th>
		<th>教師評價總人數</th>
		<th>教師個人照</th>
	</tr>
	<tr>
		<td>${teacherVO.thrid}</td>
		<td>${teacherVO.thrName}</td>
		<td>${teacherVO.thrGender}</td>
		<td>${teacherVO.thrPhone}</td>
		<td>${teacherVO.thrEmail}</td>
		<td>${teacherVO.thrStatus}</td>
		<td>${teacherVO.thrIntroduction}</td>
		<td>${teacherVO.thrComment}</td>
		<td>${teacherVO.thrCmnumber}</td>
<%-- 		<td>${teacherVO.thrPic}</td> --%>
		<td><img  src= "data:image/jpg;base64,${thrpic}" width="200px" height="200px"> </td>

		
	</tr>
</table>


</body>
</html>