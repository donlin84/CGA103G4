<%@page import="com.ClassIfm.model.ClassIfmService"%>
<%@page import="com.ClassIfm.model.ClassIfmVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%
	Integer claid = Integer.valueOf(request.getParameter("claid"));
	request.setAttribute("claid", claid);
	
	ClassIfmService claSrv = new ClassIfmService();
	ClassIfmVO classifmvo=claSrv.getOneClassIfm(claid);
	request.setAttribute("classifmvo",classifmvo);
%>
<html>
<head>
<meta charset="UTF-8">
<title>seefood回饋表單</title>
</head>
<body>
	<form METHOD="post" ACTION="<%=request.getContextPath()%>/NewRegisttrationformServlet">
		<h3>這是回饋表單</h3>
		<span>您參加的課程 :</span>
		<span value="${claid}" name="claid">${classifmvo.claTitle}</span>
        <br>
        <span>請輸入您的名字或電話號碼 :</span>
		<input name="memname">
		<br>
		<span>請選擇滿意度 :</span>
		<select name="regReview" id="">
			<option value="1" >待加強</option>
			<option value="2" >尚可</option>
			<option value="3"  selected>普通</option>
			<option value="4" >不錯</option>
			<option value="5" >很滿意</option>
        </select>
        <br>
        <span>課程服務建議 : (建議在50字以內喔)</span>
        <br>
        <textarea name="regReviewContent" id="" cols="30" rows="10"></textarea>
		<input type="hidden" name="action"  value="review_form">
		<br>
        <input type="submit" value="提交表單">
	</form>
</body>
</html>