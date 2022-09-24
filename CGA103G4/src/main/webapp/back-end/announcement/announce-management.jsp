<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcement.model.*"%>
<%@ page import="com.emp.model.*"%>
<%
AnnouncementService annSvc = new AnnouncementService();
List<AnnouncementVO> list = annSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>公告管理</title>

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

<style>
.mybtn {
	border-radius: 5px;
	background-color: #242c6d;
	border: 1px solid #242c6d;
	color: #fff;
	border-radius: 3px;
	font-size: 14px;
	cursor: pointer;
	vertical-align: middle;
	padding: 5px 12px;
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

				<div class="col-12">
					<div class="page-title-box">
						<div class="btn-group pull-right">
							<ol class="breadcrumb hide-phone p-0 m-0">
								<li class="breadcrumb-item"><a
									href="announce-management.jsp">公告管理</a></li>
								<li class="breadcrumb-item"><a href="../index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h4 class="page-title">公告管理</h4>
					</div>
				</div>
			</div>
			
			<div class="middle" style="text-align:center;"><br>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<div class="col-md-6 col-lg-6 col-xl-12" style="margin:0 auto;padding:10px 20px">
					<div class="col-md-6 col-lg-6 col-xl-5" style="display: inline-block;">
						<div class="card">
							<div class="card-body">
								<h3><a href='addAnnouncement.jsp'>新增公告</a></h3>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-6 col-xl-5" style="display: inline-block;">
						<div class="card">
							<div class="card-body">
								<h3><a href='listAllAnnouncement.jsp'>公告列表</a></h3>
							</div>
						</div>
					</div>
				</div>
				
				<c:forEach var="announcementVO" items="${list}">

					<div class="col-md-6 col-lg-6 col-xl-3"
						style="display: inline-block;">

						<div class="card">
							<div class="card-body">
								<h4 class="card-title font-20 mt-0">${announcementVO.annTitle}</h4>
								<h6 class="card-subtitle text-muted">上次修改時間 ${announcementVO.annUpdate}</h6>
							</div>

							<img class="img-fluid"
								src="<%=request.getContextPath()%>/AnnouncementPic?annid=${announcementVO.annid}"
								alt="Card image cap" style="height:180px;">

							<div class="card-body">
								<p class="card-text" style="display: block; white-space: nowrap; overflow: hidden;text-overflow: ellipsis;">${announcementVO.annContent}</p>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/announcement/AnnouncementServlet" style="margin-bottom: 0px;">
										<input type="submit" value="修改" class="mybtn">
										<input type="hidden" name="annid" value="${announcementVO.annid}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
							</div>

						</div>
					</div>
					<!-- end col -->
				</c:forEach>
			</div>
		</div>
	</div>

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