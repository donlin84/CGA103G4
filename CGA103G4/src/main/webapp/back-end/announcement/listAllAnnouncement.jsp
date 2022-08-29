<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupontype.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
CouponTypeService cpTpSvc = new CouponTypeService();
List<CouponTypeVO> list = cpTpSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>優惠券列表</title>

<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon" href="../assets/images/favicon.ico">
<link href="../assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet">
<link href="../assets/plugins/fullcalendar/vanillaCalendar.css" rel="stylesheet" type="text/css" />
<link href="../assets/plugins/morris/morris.css" rel="stylesheet">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="../assets/css/style.css" rel="stylesheet" type="text/css">

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
								<li class="breadcrumb-item"><a href="#">Zoter</a></li>
								<li class="breadcrumb-item"><a href="#">Tables</a></li>
								<li class="breadcrumb-item active">Editable</li>
							</ol>
						</div>
						<h4 class="page-title">Editable</h4>
					</div>
				</div>
			</div>
			<!-- end page title end breadcrumb -->
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 header-title">優惠券列表</h4>
							<table class="table" id="my-table">
								<thead>
									<tr>
										<th>優惠券種類編號</th>
										<th>優惠券名稱</th>
										<th>優惠券折扣價格</th>
										<th>起始時間</th>
										<th>截止時間</th>
										<th>優惠券狀態</th>
										<th>優惠券圖片</th>
										<th>修改</th>
										<th>刪除</th>
									</tr>
								</thead>
								<%@ include file="page1.file"%>
								<%-- 	<%@ include file="/emp/page1.file" %>  --%>
								<c:forEach var="coupontypeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tbody>
										<tr>
											<td>${coupontypeVO.cpTpid}</td>
											<td>${coupontypeVO.cpName}</td>
											<td>${coupontypeVO.cpDiscount}</td>
											<td>${coupontypeVO.cpStart}</td>
											<td>${coupontypeVO.cpEnd}</td>
											<td>${coupontypeVO.cpStatus==1?"已上架":"下架"}</td>
											<td><img src="<%=request.getContextPath()%>/ImagesServlet?cpTpid=${coupontypeVO.cpTpid}" width="150px;"></td>
											<td>
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/coupontype/CouponTypeServlet"
													style="margin-bottom: 0px;">
													<input type="submit" value="修改"> <input type="hidden" name="cpTpid" value="${coupontypeVO.cpTpid}">
													<input type="hidden" name="action" value="getOne_For_Update">
												</FORM>
											</td>
											<td>
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/coupontype/CouponTypeServlet"
													style="margin-bottom: 0px;">
													<input type="submit" value="刪除"> <input type="hidden" name="cpTpid" value="${coupontypeVO.cpTpid}">
													<input type="hidden" name="action" value="delete">
												</FORM>
											</td>
										</tr>
									</tbody>
								</c:forEach>
							</table>
							<%@ include file="page2.file"%>
							<div class="continueToAdd" style="width:100%;text-align:center;">
								<a href="<%=request.getContextPath()%>/back-end/coupontype/addCouponType.jsp"><input type="submit"
									value="繼續新增"></a>
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
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/popper.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script src="../assets/js/modernizr.min.js"></script>
	<script src="../assets/js/waves.js"></script>
	<script src="../assets/js/jquery.nicescroll.js"></script>
	<script src="../assets/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script src="../assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="../assets/plugins/skycons/skycons.min.js"></script>
	<script src="../assets/plugins/tiny-editable/mindmup-editabletable.js"></script>
	<script src="../assets/plugins/tiny-editable/numeric-input-example.js"></script>
	<script src="../assets/plugins/fullcalendar/vanillaCalendar.js"></script>
	<script src="../assets/plugins/raphael/raphael-min.js"></script>
	<script src="../assets/plugins/morris/morris.min.js"></script>
	<script src="../assets/js/app.js"></script>


</body>
</html>