<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotionsdetail.model.*"%>
<%@ page import="com.productSort.model.*"%>
<%@ page import="com.promotions.model.*"%>
<%@ page import="com.product.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>


<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>活動條件查詢</title>

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
								<li class="breadcrumb-item active">活動條件查詢</li>
								<li class="breadcrumb-item"><a
									href="../discount-management/discount-management.jsp">優惠方案管理</a></li>
								<li class="breadcrumb-item"><a href="../index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h4 class="page-title">活動條件查詢</h4>
					</div>
				</div>
			</div>
			<!-- end page title end breadcrumb -->
			<div class="row align-items-center">
			    <div class="col align-self-start"></div>
				<div class="col-md-4 col-lg-4 col-xl-4" style="text-align:center;">
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 header-title">活動條件查詢</h4>

							<jsp:useBean id="pmtSvc" scope="page"
								class="com.promotions.model.PromotionsService" />

							<jsp:useBean id="pdSortSvc" scope="page"
								class="com.productSort.model.ProductsortService" />
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back-end/promotionsdetail/PromotionsDetailServlet"
								name="form1">
								<table class="table" id="my-table">
									<thead>
										<tr>
											<th><b>選擇優惠名稱:</b> <select size="1" name="pmid">
													<option value="">
														<c:forEach var="PromotionsVO" items="${pmtSvc.all}">
															<option value="${PromotionsVO.pmid}">${PromotionsVO.pmName}
														</c:forEach>
											</select></th>
										</tr>
										<!-- 										<tr> -->
										<!-- 											<th><b>選擇商品類別:</b> <select size="1" name="pdsid"> -->
										<!-- 													<option value=""> -->
										<%-- 														<c:forEach var="productsortVO" items="${pdSortSvc.all}"> --%>
										<%-- 															<option value="${productsortVO.pdsid}">${productsortVO.pdsName} --%>
										<%-- 														</c:forEach> --%>
										<!-- 											</select></th> -->
										<!-- 										</tr> -->
										<tr>
											<th><input type="submit"
												value="確認優惠活動"
												style="border-radius: 5px; background-color: #242c6d; border: 1px solid #242c6d; color: #fff; border-radius: 3px; font-size: 14px; cursor: pointer; vertical-align: middle; padding: 6px 15px;">
												<input type="hidden" name="action"
												value="batchAddPromotionsDetail"></th>
										</tr>
									</thead>
								</table>
							</FORM>
						</div>
					</div>
				</div>
				<!-- end col -->
				    <div class="col align-self-end"></div>
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