<%@page import="java.util.ArrayList"%>
<%@page import="com.member.model.MemberVO"%>
<%@page import="com.member.model.MemberService"%>
<%@page import="com.registtrationform.model.RegisttrationFormVO"%>
<%@page import="java.util.List"%>
<%@page import="com.registtrationform.model.RegisttrationFormService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset=""UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4><a href="<%=request.getContextPath()%>/back-end/registtrationform/select_page.jsp">回首頁</a></h4>
	<table>
		<span>報名課程 :</span>
		<tr>${claid}</tr>
		<span>  的會員</span>
		<tr>
			<th>會員id</th>
			<th>會員name</th>
			<th>會員性別</th>
			<th>會員電話</th>
			<th>報名人數</th>
			<th>編輯</th>
			<th>簽到</th>
		</tr>
		<c:forEach var="regall" items="${regall}">
		<tr>
			<td>${regall.memid}</td>
			<td>${regall.memName}</td>
			<td>${(regall.memGender=='m')?'男性':'女性'}</td>
			<td>${regall.memPhone}</td>
			<td>${regall.people}</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/NewRegisttrationformServlet">
				     <input type="submit" value="修改">
				     <input type="hidden" name="claid"  value="${claid}">
				     <input type="hidden" name="memid"  value="${regall.memid}">
				     <input type="hidden" name="action"	value="forward_to_update">
			 	</FORM>
			</td>
			<td></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>