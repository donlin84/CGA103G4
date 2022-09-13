<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ClassIfm.model.*"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/back-end/registtrationform/select_page.jsp">回首頁</a>
	<div>
		<h1>這是更新表單</h1>
	</div>
	<span>課程 :</span>
	<span>${registtrationFormVO.claid}</span>
	<br>
	<span>會員 :</span>
	<span>${registtrationFormVO.memid}</span>
	<br>
	<form action="<%=request.getContextPath()%>/NewRegisttrationformServlet" method="post">
		<span>付款方式</span>
		<select name="regPayment" id="">
			<option value="0" ${(registtrationFormVO.regPayment==0)?'selected':''}>轉帳</option>
			<option value="1" ${(registtrationFormVO.regPayment==1)?'selected':''}>信用卡</option>
        </select>
        <span>訂單狀態</span>
        <select name="regStatus" id="">
			<option value="0" ${(registtrationFormVO.regStatus==0)?'selected':''}>已報名</option>
			<option value="1" ${(registtrationFormVO.regStatus==1)?'selected':''}>取消</option>
			<option value="2" ${(registtrationFormVO.regStatus==2)?'selected':''}>已退款</option>
        </select>
        <span>訂單人數</span>
        <select name="regPeople" id="">
			<c:forEach var="i" begin="1" end="${(classifmvo.claPeopleMax)-(classifmvo.claPeople)+(registtrationFormVO.regPeople)}">
				<option value=${i} ${(registtrationFormVO.regPeople==i)?'selected':''}>${i}</option>
			</c:forEach>
        </select>
        <div>
            <input type="submit" value="確定修改">
            <input type="hidden" value="${registtrationFormVO.claid}" name="claid">
            <input type="hidden" value="${registtrationFormVO.memid}" name="memid">
        	<input type="hidden" name="action" value="update">
        </div>
    </form>
</body>
</html>