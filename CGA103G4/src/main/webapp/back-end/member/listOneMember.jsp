<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //MemberServlet.java(Concroller), 存入req的memberVO物件
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>會員資料 - listOneMember.jsp</title>
<link rel="shortcut icon" href="../assets/images/favicon.ico">
<link href="../assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet">
<link href="../assets/plugins/fullcalendar/vanillaCalendar.css" rel="stylesheet" type="text/css" />
<link href="../assets/plugins/morris/morris.css" rel="stylesheet">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="../assets/css/style.css" rel="stylesheet" type="text/css">
<link href="../chef/css/other.css" rel="stylesheet" type="text/css">

</head>
<body bgcolor='white'>

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
	<tr>
		<td><%=memberVO.getMemid()%></td>
		<td><%=memberVO.getMemName()%></td>
		<td><%=memberVO.getMemAccount()%></td>
		<td><%=memberVO.getMemPassword()%></td>
<%-- 		<td><%=memberVO.getMemGender()%></td> --%>
		<c:if test="${memberVO.getMemGender() == 'm'}">
				<td>男</td>	
			</c:if>
			<c:if test="${memberVO.getMemGender() == 'f'}">
				<td>女</td>
			</c:if>
		<td><%=memberVO.getMemPhone()%></td>
		<td><%=memberVO.getMemEmail()%></td>
		<td><%=memberVO.getMemAddres()%></td>
		<td><%=memberVO.getMemBirthday()%></td>
<%-- 		<td><%=memberVO.getMemStatus()%></td> --%>
		<c:if test="${memberVO.getMemStatus() == 0}">
				<td>啟用</td>	
			</c:if>
			<c:if test="${memberVO.getMemStatus() == 1}">
				<td>停權</td>
			</c:if>
		<td><%=memberVO.getMemNation()%></td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/member/Member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="memid"  value="${memberVO.memid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
	</tr>
</table>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
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
</body>
</html>