<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.teacher.model.*"%>
<%@ page import="java.util.*"%>

<%
  TeacherVO teacherVO = (TeacherVO) request.getAttribute("teacherVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>教師資料</title>

<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/back-end/assets/images/favicon.ico">
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/fullcalendar/vanillaCalendar.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/morris/morris.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/bootstrap-rating/bootstrap-rating.css" rel="stylesheet" type="text/css">


<style>
 .td_introduct{
   display:inline-block;
   width:220px;
    height:200px;
    padding: 1px;
    text-align: center;
  line-height: 20px;
  overflow:scroll;
  }
.picture{
	width:200px;
	height:200px;
}  
  
</style>
</head>

<body>

	<div id="preloader">
		<div id="status">
			<div class="spinner"></div>
		</div>
	</div>
	<!-- Navigation Bar-->
	<%@ include file="../tools/header.jsp"%>

	<div class="wrapper">
		<div class="container-fluid">
			<!-- Page-Title -->
			<div class="row">
				<div class="col-sm-12">
					<div class="page-title-box">
						<div class="btn-group pull-right">
							<ol class="breadcrumb hide-phone p-0 m-0">
								<li class="breadcrumb-item active">教師資料單筆查詢</li>
								<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/teacher/select_page.jsp">教師管理</a></li>
								<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h5><a href="select_page.jsp">教師管理首頁</a></h5>
					</div>
				</div>
			</div>

			<div class="row" >
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 header-title">教師資料單筆查詢</h4>
							<table class="table" id="my-table">
								<thead>
									<tr>
										<th>教師ID</th>
										<th>教師姓名</th>
										<th>教師性別</th>
										<th>教師電話</th>
										<th>教師信箱</th>
										<th>教師狀態</th>
										<th>教師簡介</th>
										<th>教師總星數</th>
										<th>評價總數</th>
										<th>教師個人照</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${teacherVO.thrid}</td>
										<td>${teacherVO.thrName}</td>
										<td>${teacherVO.thrGender}</td>
										<td>${teacherVO.thrPhone}</td>
										<td>${teacherVO.thrEmail}</td>
										<td>${teacherVO.thrStatus}</td>
										<td class="td_introduct">${teacherVO.thrIntroduction}</td>
										<td>
                                   		 <div class="p-4 text-center" style="margin:0;padding:0;">
                                     		<input type="hidden" style="margin:0;padding:0;"
                                            class="rating-tooltip" data-filled="mdi mdi-star font-32 text-primary"
                                            data-empty="mdi mdi-star-outline font-32 text-muted" data-readonly
                                            value="${teacherVO.thrComment}" />
                                 		</div>
                              			 </td>
										<td>${teacherVO.thrCmnumber}</td>
										<td ><img  src= "data:image/jpg;base64,${thrpic}" width="150px" height="150px"> </td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<!-- end col -->
				</div>
				<!-- end row -->
			</div>

		</div>
		<!-- end container -->
	</div>
	<!-- end wrapper -->



	<!-- Footer -->
	<%@ include file="../tools/footer.jsp"%>
	<!-- End Footer -->
	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/back-end/assets/js/jquery.min.js"></script>
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
	<script src="<%=request.getContextPath()%>/back-end/assets/pages/rating-init.js"></script><!-- App js -->
    <script src="<%=request.getContextPath()%>/back-end/assets/plugins/bootstrap-rating/bootstrap-rating.min.js"></script>
</body>

</html>