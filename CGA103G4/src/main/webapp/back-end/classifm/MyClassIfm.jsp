<%@page import="com.registtrationform.model.RegisttrationFormVO"%>
<%@page import="java.util.List"%>
<%@page import="com.registtrationform.model.RegisttrationFormService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.stream.Collectors" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
// 	Integer memid = session.getAttribute("會員的id");
	Integer memid=201;
	RegisttrationFormService regSrv = new RegisttrationFormService();
	List<RegisttrationFormVO> list=regSrv.getAll();
	request.setAttribute("MyClassIfm", list.stream()
						.filter(c -> c.getMemid()==202)
						.collect(Collectors.toList()));
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	我的課程
<table>
	<tr>
		<td>會員</td>
		<td>課程</td>
		<td>上課時間</td>
		<td>報名人數</td>
		<td>狀態</td>
		<td>更改</td>
	</tr>
<c:forEach var="MyClassIfm" items="${MyClassIfm}">
	<tr>
		<td>${MyClassIfm.getmemberVO().memName}</td>
		<td>${MyClassIfm.getClassIfmVO().claTitle}</td>
		<td>${fn:replace((MyClassIfm.getClassIfmVO().claTime), "T", " ")}</td>
		<td>${MyClassIfm.regPeople}</td>
		<td>
			<c:choose>
		            <c:when test="${MyClassIfm.regStatus==0}">
		                已報名
		            </c:when>
		            <c:when test="${MyClassIfm.regStatus==1}">
		                取消
		            </c:when>
		            <c:otherwise>
		                已退款
		            </c:otherwise>
		     </c:choose>
		</td>
		<td>
			<c:choose>
		            <c:when test="${MyClassIfm.regStatus==1}">
		                
		            </c:when>
		            <c:otherwise>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/NewRegisttrationformServlet">
				<input type="submit" value="取消訂單">
				<input type="hidden" name="claid"  value="${MyClassIfm.claid}">
				<input type="hidden" name="memid"  value="${MyClassIfm.memid}">
				<input type="hidden" name="regPeople"  value="${MyClassIfm.regPeople}">
				<input type="hidden" name="action"	value="myclassifm_cancel">
			 </FORM>
		            </c:otherwise>
		     </c:choose>
		</td>
	</tr>
</c:forEach>
</table>
</body>
</html>