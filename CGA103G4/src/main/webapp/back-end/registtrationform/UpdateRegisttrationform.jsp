<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ClassIfm.model.*"%>
<html>
<head>
<meta charset="UTF-8">
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
<style>
	
        .top_div{
        	margin-top:140px;
        	margin-bottom:35px;
        	text-align:center;
        	font-size:18px;
        	font-weight:600;
        	letter-spacing:2px;
        }
	.my_form{
        	margin:10px auto 0px auto;
        	text-align:center;
        	font-size:18px;
        	font-weight:600;
        	letter-spacing:2px;
        }
        .my_submit{
        	font-size:15px;
        	padding:5px 10px;
        }
        .update{
        	margin-top:30px;
        	font-weight:600;
        }
</style>
</head>
<body>
	<%@ include file="../tools/header.jsp"%>
	<a href="<%=request.getContextPath()%>/back-end/registtrationform/select_page.jsp">回首頁</a>
	<div class="top_div">
		<h3>更新報名表</h3>
		<br>
		<span>課程 :</span>
		<span>${registtrationFormVO.claid} ${registtrationFormVO.getClassIfmVO().claTitle}</span>
		<br>
		<br>
		<span>會員 :</span>
		<span>${registtrationFormVO.memid} ${registtrationFormVO.getmemberVO().memName}</span>
		<br>
	</div>
	<form action="<%=request.getContextPath()%>/NewRegisttrationformServlet" method="post" class="my_form">
		<span>付款方式</span>
		<select name="regPayment" id="" style="font-size:15px;">
			<option value="0" ${(registtrationFormVO.regPayment==0)?'selected':''}>轉帳</option>
			<option value="1" ${(registtrationFormVO.regPayment==1)?'selected':''}>信用卡</option>
        </select>
        <br>
        <br>
        <span>訂單狀態</span>
        <select name="regStatus" id="" style="font-size:15px;">
			<option value="0" ${(registtrationFormVO.regStatus==0)?'selected':''}>已報名</option>
			<option value="1" ${(registtrationFormVO.regStatus==1)?'selected':''}>取消</option>
			<option value="2" ${(registtrationFormVO.regStatus==2)?'selected':''}>已退款</option>
        </select>
        <br>
        <br>
        <span>訂單人數</span>
        <select name="regPeople" id="" style="width:70px;">
			<c:forEach var="i" begin="1" end="${(classifmvo.claPeopleMax)-(classifmvo.claPeople)+(registtrationFormVO.regPeople)}">
				<option value=${i} ${(registtrationFormVO.regPeople==i)?'selected':''}>${i}</option>
			</c:forEach>
        </select>
        <div class="update">
            <input type="submit" value="確定修改" class="my_submit">
            <input type="hidden" value="${registtrationFormVO.claid}" name="claid">
            <input type="hidden" value="${registtrationFormVO.memid}" name="memid">
        	<input type="hidden" name="action" value="update">
        </div>
    </form>
</body>
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
</html>