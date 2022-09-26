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
<link rel="shortcut icon" href="../assets/images/favicon.ico">
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/fullcalendar/vanillaCalendar.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/morris/morris.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/css/style.css" rel="stylesheet" type="text/css">

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
								<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/teacher/select_page.jsp">教師管理</a></li>
								<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h5><a href="select_page.jsp">教師管理首頁</a></h5>
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤:</font>
							<ul>
							    <c:forEach var="message" items="${errorMsgs}">
									<li style="color:red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
					</div>
				</div>
			</div>


			
			
			
			<div class="col-sm-9 mx-auto">
				<div class="container nav0 -on" id="indexPage">


					<div id="indexPageContent">
						<div class="container-fluid">
							<!-- end page title end breadcrumb -->
							<div class="row">
								<div class="col-md-6 col-lg-6 col-xl-6">
									<!-- Simple card -->
									<div class="card">
										<div class="card-body">
											<h4 class="card-title font-20 mt-0">教師資料管理</h4>
										</div>
<%-- 										錯誤表列 --%>
<%-- 										<c:if test="${not empty errorMsgs}"> --%>
<!-- 											<font style="color: red">請修正以下錯誤:</font> -->
<!-- 											<ul> -->
<%-- 												<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 													<li style="color: red">${message}</li> --%>
<%-- 												</c:forEach> --%>
<!-- 											</ul> -->
<%-- 										</c:if> --%>
										<jsp:useBean id="teacherSvc" scope="page" class="com.teacher.model.TeacherService" />
										<ul class="list-group list-group-flush">
																				
			
											<li class="list-group-item">
											
												<FORM METHOD="post" ACTION="teacher.do">
													<h4 class="card-title font-20 mt-0">教師資料查詢/修改</h4>
													<div class="box-left" ><label for = "thrid">請選擇教師編號</label></div>
													<div class="box-right" >
													<select size="1" name="thrid" class="dis-select" >
														<option value="">請選擇編號 
													    <c:forEach var="teacherVO" items="${teacherSvc.all}" > 
														    <option value="${teacherVO.thrid}">${teacherVO.thrid}
											        	</c:forEach>   
													</select>
													</div>
													
													<div class="box-right" >
													<label for = "thrName">請輸入教師姓名</label>
													</div>
													<input id="thrName" type="text" name="thrName" placeholder="請輸入姓名"> 					
													
													<div class="box-right" >
													<label for = "thrGender">請選擇教師性別</label></div>
													<div>
													<select id="thrGender" size="1" name="thrGender" class="dis-select" >
														<option value="">請選擇性別
														<option value="男">男
														<option value="女">女
														
													</select>
													
													<div class="box-right" >
													<label for = "thrStatus">請選擇教師狀態</label></div>
													<div>
													<select id="thrStatus" size="1" name="thrStatus" class="dis-select" >
														<option value="">請選擇狀態
														<option value="0">在職
														<option value="1">離職
  													</select>
  													
  													<div class="box-right" >
													<label for = "thrIntroduction">請輸入教師簡介關鍵字</label>
													<div>
													<input id="thrIntroduction" type="text" name="thrIntroduction" placeholder="請輸入簡介關鍵字"> 					
													  													
													<div class="box-right" >
													<label for = "thrComment">請選擇教師評分(高於幾顆星)</label></div>
													<div>
													<select id="thrComment" size="1" name="thrComment" class="dis-select" >
														<option value="">請選擇評分
														<option value="1">1
														<option value="2">2
														<option value="3">3
														<option value="4">4
														<option value="5">5
  													</select>
  												
													<div class="box-right" >
														<label for = "thrCmnumber">請選擇教師總評價數人數(高於多少人)</label></div>
													<div>
													<select id="thrCmnumber" size="1" name="thrCmnumber" class="dis-select" >
														<option value="">請選擇總評價數人數
														<option value="10">10
														<option value="50">50
														<option value="100">100
														<option value="150">150
														<option value="200">200
  													</select>
													
													<input type="hidden" name="action" value="getsome_For_condiction"> 
													<input type="submit" class="mybtn" value="搜尋">
													</div>
  													<br>
  													<br>
  													
													<li class="list-group-item"><a
														href='listAllTeacher.jsp'
														class="btn btn-primary waves-effect waves-light">列出所有教師清單/修改</a>
														<a href="addTeacher.jsp"
														class="btn btn-primary waves-effect waves-light">新增教師</a></li>
												</ul>
												</FORM>
											
											</li>
										</ul>
									</div>
								</div>
								<!-- end col -->

								
							</div>
							<!-- end container -->
						</div>

					</div>

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
</body>
</html>