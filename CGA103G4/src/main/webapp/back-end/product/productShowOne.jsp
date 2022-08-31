<%@page import="com.product.model.ProductVO"%>
<%@ page import="com.productPicture.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<STYLE>
.table{
	text-align: left;
	width: 100%;
}

.productdescription {
	text-align: left;
	WORD-BREAK: break-all;
	WORD-WRAP: break-word;
}
</STYLE>


<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>seefood後台</title>
<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon" href="assets/images/favicon.ico">
<link href="assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css"
	rel="stylesheet">
<link href="assets/plugins/fullcalendar/vanillaCalendar.css"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/morris/morris.css" rel="stylesheet">
<link href="assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="assets/css/style.css" rel="stylesheet" type="text/css">

</head>

<body>
	<!-- Loader -->
	<div id="preloader">
		<div id="status">
			<div class="spinner"></div>
		</div>
	</div>
	<!-- Navigation Bar-->
	<%@ include file="header.jsp"%>
	<div class="wrapper">
		<div class="container-fluid">
			<!-- Page-Title -->
			<div class="row">
				<div class="col-sm-12">
					<div class="page-title-box">
						<div class="btn-group pull-left">
							<ol class="breadcrumb hide-phone p-0 m-0">
								<li class="breadcrumb-item"><a href="#">後台首頁</a></li>
								<li class="breadcrumb-item active"><a href="#">某頁面</a></li>
								<li class="breadcrumb-item active">當前頁面</li>
							</ol>
						</div>
					</div>
				</div>
			</div>
			<!-- end page title end breadcrumb -->
			<!--end row-->
		</div>
		<!-- end container -->
	</div>
	<!-- end wrapper -->
	<!-- Footer -->
	<%@ include file="footer.jsp"%>
	<!-- End Footer -->
	<!-- jQuery -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/popper.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/modernizr.min.js"></script>
	<script src="assets/js/waves.js"></script>
	<script src="assets/js/jquery.nicescroll.js"></script>
	<script src="assets/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script
		src="assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="assets/plugins/skycons/skycons.min.js"></script>
	<script src="assets/plugins/fullcalendar/vanillaCalendar.js"></script>
	<script src="assets/plugins/raphael/raphael-min.js"></script>
	<script src="assets/plugins/morris/morris.min.js"></script>
	<script src="assets/js/app.js"></script>
	<script src="assets/plugins/tiny-editable/mindmup-editabletable.js"></script>
	<script src="assets/plugins/tiny-editable/numeric-input-example.js"></script>
	<script src="assets/plugins/tabledit/jquery.tabledit.js"></script>
	<script src="assets/pages/tabledit.init.js"></script>

	<!-- Page-Title -->
	<div class="row">
		<div class="col-sm-12">
			<div class="page-title-box">
				<div class="btn-group pull-right"></div>

			</div>
		</div>
	</div>
	<!-- end page title end breadcrumb -->
	<div class="row">
		<div class="col-12">
			<div class="card"  style="margin:0 150px">
				<div class="card-body">
					<h4 class="mt-0 header-title">商品資訊</h4>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>


					<table id="mainTable" class="table table-striped m-b-0">
						<tbody>
							<tr>
								<td>商品編號</td>
								<td><%=productVO.getPdid()%></td>
							</tr>
							<tr>
								<td>商品類別</td>
								<td><%=productVO.getPdsid()%></td>
							</tr>
							<tr>
								<td>商品名稱</td>
								<td><%=productVO.getPdName()%></td>
							</tr>
							<tr>
								<td>商品價格</td>
								<td><%=productVO.getPdPrice()%></td>
							</tr>
							<tr>
								<td>優惠價格</td>
								<td><%=productVO.getPdDiscountPrice()%></td>
							</tr>
							<tr>
								<td>商品描述</td>
								<td class="productdescription" width = 70%><%=productVO.getPdDescription()%></td>
							</tr>
							<tr>
								<td>商品狀態</td>
								<td>
									<c:if test="${productVO.pdStatus == 1}" var="上架中" scope="page">
									上架中</c:if>
									<c:if test="${productVO.pdStatus == 0}" var="上架中" scope="page">
									未上架</c:if>
				</td>
							</tr>
							<tr>
								<c:set var="string1" value="${productVO.getPdUpdate()}" />
								<c:set var="string2" value="${fn:replace(string1, 'T', ' ')}" />
								<td>上次修改</td>
								<td>${string2}</td>
							</tr>
							<tr>
								<td>商品圖:</td>
								<td><img
									src="Productpic.do?pdid=<%=productVO.getPdid()%>"
									width="200"></td>
							</tr>
						</tbody>
					</table>
					<h2>
						<a href="productShowAll.jsp">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;繼續查詢</a>
					</h2>
				</div>
			</div>
		</div>
		<!-- end col -->
	</div>
	<!-- end row -->
	
	<!-- end container -->

	<!-- end wrapper -->

	<!-- ============================================================ -->

</body>
</html>