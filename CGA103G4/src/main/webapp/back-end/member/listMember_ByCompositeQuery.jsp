<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listMember_ByCompositeQuery" scope="request" type="java.util.List<MemberVO>" /> <!-- 於EL此行可省略 -->
<%-- <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MenberChefService" /> --%>

<!DOCTYPE html>
<html>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<head><title>複合查詢 - listMember_ByCompositeQuery.jsp</title>

<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon" href="../assets/images/favicon.ico">
<link href="../assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css"
	rel="stylesheet">
<link href="../assets/plugins/fullcalendar/vanillaCalendar.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/plugins/morris/morris.css" rel="stylesheet">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="../assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="../assets/css/style.css" rel="stylesheet" type="text/css">

<link href="../chef/css/other.css" rel="stylesheet" type="text/css">

</head>
<body bgcolor='white'>

<!-- <div id="preloader"> -->
<!-- 		<div id="status"> -->
<!-- 			<div class="spinner"></div> -->
<!-- 		</div> -->
<!-- 	</div> -->
	<!-- Navigation Bar -->
	<%@ include file="../tools/header.jsp"%>

	<div class="wrapper">
		<div class="container-fluid">
			<!-- 			Page-Title -->
			<div class="row">
				<div class="col-sm-12">
					<div class="page-title-box">
						<div class="btn-group pull-right">
							<ol class="breadcrumb hide-phone p-0 m-0">
								<li class="breadcrumb-item"><a href="#">Zoter</a></li>
								<li class="breadcrumb-item"><a href="#">Tables</a></li>
								<li class="breadcrumb-item active">Editable</li>
							</ol>
						</div>
						<h4 class="page-title">Editable</h4>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">

							<div class="main_content">
								<aside class="aside">
							<div class="btn-group mo-mb-2" 
                     			style="top: 0px; left: 0px;">
                                <button type="button" class="btn btn-primary btn-lg dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false" style="width:140px">會員資訊 </button>
                                    <div class="dropdown-menu">
                                    <a class="dropdown-item" href='../member/select_page.jsp'>搜尋會員</a>
                                    <a class="dropdown-item" href='../member/listAllMember.jsp'>會員列表</a>
                                    </div>
                                </div>

                                <div class="btn-group mo-mb-2"
                                style="top: 20px; left: 0px;">
                                <button type="button" class="btn btn-primary btn-lg dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false" style="width:140px">信用卡資訊 </button>
                                    <div class="dropdown-menu">
                                     <a class="dropdown-item" href='../creditCardInformation/select_page.jsp'>搜尋信用卡</a>
                                     <a class="dropdown-item" href='../creditCardInformation/listAllCreditCardInformation.jsp'>信用卡列表</a>
                                    </div>
                                </div>
								</aside>

								<main class="main">
								
<table>
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>性別</th>
		<th>電話</th>
		<th>信箱</th>
		<th>地址</th>
		<th>生日</th>
		<th>狀態</th>
		<th>國家</th>
		<th>修改</th>

	</tr>
	<%@ include file="page1_ByCompositeQuery.file" %>

			

	<c:forEach var="memberVO" items="${listMember_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

		<tr align='center' valign='middle' ${(memberVO.memid==param.memid) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${memberVO.memid}</td>
			<td>${memberVO.memName}</td>
			<td>${memberVO.memAccount}</td>
			<td>${memberVO.memPassword}</td>
<%-- 			<td>${memberVO.memGender}</td> --%>
			<c:if test="${memberVO.memGender == 'm'}">
				<td>男</td>	
			</c:if>
			<c:if test="${memberVO.memGender == 'f'}">
				<td>女</td>
			</c:if>
			<td>${memberVO.memPhone}</td> 
			<td>${memberVO.memEmail}</td>
			<td>${memberVO.memAddres}</td>
			<td>${memberVO.memBirthday}</td>
<%-- 			<td>${memberVO.memStatus}</td> --%>
			<c:if test="${memberVO.memStatus == 0}">
				<td>啟用</td>	
			</c:if>
			<c:if test="${memberVO.memStatus == 1}">
				<td>停權</td>
			</c:if>
			<td>${memberVO.memNation}</td>
						<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/member/Member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="memid"      value="${memberVO.memid}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2_ByCompositeQuery.file" %>
</main>
							</div>
						</div>
						<!-- end container -->
					</div>
					<!-- end wrapper -->
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</div>
	<!-- end wrapper -->
	<!-- Footer -->
	<%@ include file="../tools/footer.jsp"%>
	<!-- End Footer -->
	<!-- jQuery -->
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/popper.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script src="../assets/js/modernizr.min.js"></script>
	<script src="../assets/js/waves.js"></script>
	<script src="../assets/js/jquery.nicescroll.js"></script>
	<script
		src="../assets/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script
		src="../assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="../assets/plugins/skycons/skycons.min.js"></script>
	<script src="../assets/plugins/tiny-editable/mindmup-editabletable.js"></script>
	<script src="../assets/plugins/tiny-editable/numeric-input-example.js"></script>
	<script src="../assets/plugins/fullcalendar/vanillaCalendar.js"></script>
	<script src="../assets/plugins/raphael/raphael-min.js"></script>
	<script src="../assets/plugins/morris/morris.min.js"></script>
	<script src="../assets/js/app.js"></script>
<script>
		var bigmodal = document.getElementById('bigmodal');
		var bgImg = document.getElementById('bgImg');
		function showBgImg(e) {
			bigmodal.style.display = 'block';
			bgImg.src = e.src;
		}
		bgImg.onclick = function() {
			bigmodal.style.display = 'none';
		}
	</script>
</body>
</html>