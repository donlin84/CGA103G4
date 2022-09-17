<%@page import="java.util.ArrayList"%>
<%@page import="com.member.model.MemberVO"%>
<%@page import="com.member.model.MemberService"%>
<%@page import="com.registtrationform.model.RegisttrationFormVO"%>
<%@page import="java.util.List"%>
<%@page import="com.registtrationform.model.RegisttrationFormService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.ClassIfm.model.ClassIfmService"%>
<%@ page import="com.ClassIfm.model.*"%>
<% 
	ClassIfmService claSrv = new ClassIfmService();
	List<ClassIfmVO> list=claSrv.getAll();
	pageContext.setAttribute("list",list);
%>
<html>
<head>
<meta charset=""UTF-8">
<title>Insert title here</title>
<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script src='http://code.jquery.com/jquery-3.6.0.min.js'></script>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/back-end/assets/images/favicon.ico">
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/fullcalendar/vanillaCalendar.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/morris/morris.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../tools/header.jsp"%>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/NewRegisttrationformServlet" >
       <b>列印報名表:</b>
       <select size="1" name="claid">
         <c:forEach var="c" items="${list}"> 
          <option value="${c.claid}" ${(claifmvo.claid==c.claid)?'selected':''}>${c.claid} ${c.claTitle}</option>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getclaid_allmember">
       <input type="submit" value="送出">
       </FORM>
	<table>
		<span>報名課程 :</span>
		<tr>${claifmvo.claid} ${claifmvo.claTitle}</tr>
		<span>  的會員</span>
		<tr>
			<th>會員id</th>
			<th>會員</th>
			<th>會員性別</th>
			<th>付款方式</th>
			<th>會員電話</th>
			<th>報名人數</th>
			<th>報名時間</th>
			<th>國籍</th>
			<th>簽到</th>
		</tr>
		<c:forEach var="regall" items="${regall}">
		<tr>
			<td>${regall.memid}</td>
			<td>${regall.memName}</td>
			<td>${(regall.memGender=='m')?'男性':'女性'}</td>
			<td>${regall.regpayment}</td>
			<td>${regall.memPhone}</td>
			<td>${regall.people}</td>
			<td>${fn:replace((regall.regtime), "T", " ")}</td>
			<td>${regall.memNation}</td>
			<td></td>
		</tr>
		</c:forEach>
	</table>
	<%@ include file="../tools/footer.jsp"%>
	<!-- End Footer -->
	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/back-end/assets/js/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/modernizr.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/waves.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/jquery.nicescroll.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/skycons/skycons.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/tiny-editable/mindmup-editabletable.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/tiny-editable/numeric-input-example.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/fullcalendar/vanillaCalendar.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/raphael/raphael-min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/morris/morris.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/app.js"></script>
</body>
</html>