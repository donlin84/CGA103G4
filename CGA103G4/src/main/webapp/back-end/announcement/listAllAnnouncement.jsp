<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcement.model.*"%>
<%@ page import="com.emp.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

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
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>公告列表</title>

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
				<div class="col-sm-12">
					<div class="page-title-box">
						<div class="btn-group pull-right">
							<ol class="breadcrumb hide-phone p-0 m-0">
								<li class="breadcrumb-item active">公告列表</li>
								<li class="breadcrumb-item"><a href="announce-management.jsp">公告管理</a></li>
								<li class="breadcrumb-item"><a href="../index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h4 class="page-title">公告列表</h4>
					</div>
				</div>
			</div>
			<!-- end page title end breadcrumb -->
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 header-title">公告列表</h4>
							<table class="table" id="my-table">
								<thead>
									<tr>
										<th>公告編號</th>
										<th>管理員編號</th>
										<th>公告標題</th>
										<th>公告內容</th>
										<th>公告圖片</th>
										<th>公告狀態</th>
										<th>上次修改狀態時間</th>
										<th>發布時間</th>
										<th>修改</th>
										<th>刪除</th>
									</tr>
								</thead>
								<%@ include file="page1.file"%>
								<%-- 	<%@ include file="/emp/page1.file" %>  --%>
								<c:forEach var="announcementVO" items="${list}"
									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tbody>
										<tr>
											<td>${announcementVO.annid}</td>
											<td>${announcementVO.empVO.empName}</td>
											<td>${announcementVO.annTitle}</td>
											<td>
											<div style="width:250px;height:80px;text-align:center;">
											<div style="display: block; white-space: nowrap; overflow: hidden;text-overflow: ellipsis;transform: translateY(140%);">
											${announcementVO.annContent}
											</div>
											</div>
											</td>
											<td><img
												src="<%=request.getContextPath()%>/AnnouncementPic?annid=${announcementVO.annid}"
												width="150px;"></td>
											<td>${announcementVO.annStatus==1?"已上架":"下架"}</td>
											<td>${announcementVO.annUpdate}</td>
											<td>${announcementVO.annTime}</td>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/back-end/announcement/AnnouncementServlet"
													style="margin-bottom: 0px;">
													<input type="submit" class="mybtn" value="修改"> <input
														type="hidden" name="annid" value="${announcementVO.annid}">
													<input type="hidden" name="action"
														value="getOne_For_Update">
												</FORM>
											</td>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/back-end/announcement/AnnouncementServlet"
													style="margin-bottom: 0px;">
													<input type="submit" class="mybtn" value="刪除"> <input
														type="hidden" name="annid" value="${announcementVO.annid}">
													<input type="hidden" name="action" value="delete">
												</FORM>
											</td>
										</tr>
									</tbody>
								</c:forEach>
							</table>
							<%@ include file="page2.file"%>
							<div class="continueToAdd"
								style="width: 100%; text-align: center;">
								<a href="<%=request.getContextPath()%>/back-end/announcement/addAnnouncement.jsp">
								<input type="submit" class="mybtn" value="繼續新增"></a>
							</div>
						</div>
					</div>
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