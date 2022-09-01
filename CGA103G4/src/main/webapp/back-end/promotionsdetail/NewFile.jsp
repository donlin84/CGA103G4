<%@ page import="java.sql.Date"%>
<%@ page import="java.io.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.promotionsdetail.model.*"%>
<%@ page import="com.promotions.model.*"%>
<%@ page import="com.product.model.*"%>

<%
PromotionsDetailVO promotionsDetailVO = (PromotionsDetailVO) request.getAttribute("promotionsDetailVO");
PromotionsVO promotionsVO = (PromotionsVO) request.getAttribute("promotionsVO");
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>優惠券新增</title>

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

<style>
#img0 {
	min-width: 100px;
	border: 1px solid lightgray;
	display: inline-block;
	min-height: 80px;
	max-width: 150px;
	position: relative;
	z-index: 1;
}

#preview {
	display: inline-block;
	position: relative;
}

#preview span.text {
	position: absolute;
	display: inline-block;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	color: lightgray;
}
</style>

</head>
<body bgcolor='white'>

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
								<li class="breadcrumb-item active">優惠明細新增</li>
								<li class="breadcrumb-item"><a
									href="../discount-management/discount-management.jsp">優惠方案管理</a></li>
								<li class="breadcrumb-item"><a href="../index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h4 class="page-title">優惠明細新增</h4>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 header-title">優惠明細新增</h4>
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>

							<jsp:useBean id="pmtSvc" scope="page"
								class="com.promotions.model.PromotionsService" />
							<jsp:useBean id="pdSvc" scope="page"
								class="com.product.model.ProductService" />

							<FORM METHOD="post" ACTION="PromotionsDetailServlet" name="form1">
								<table class="table" id="my-table">
									<tbody>
										<tr>
											<td>優惠活動名稱:</td>
											<td><select size="1" name="pmid" id="Promotion">
													<c:forEach var="promotionsVO" items="${pmtSvc.all}">
														<option value="${promotionsVO.pmid}">${promotionsVO.pmName} ${promotionsVO.pmDiscount}</option>
													</c:forEach>
											</select></td>
										</tr>
										<tr>
											<td>產品名稱:</td>
											<td><select size="1" name="pdid" id="Product">
													<c:forEach var="productVO" items="${pdSvc.all}">
														<option value="${productVO.pdid}">${productVO.pdName} ${productVO.pdPrice}</option>
													</c:forEach>
											</select></td>
										</tr>
										<tr>
											<td>折扣後金額:</td>
											<td>
											<input type="TEXT" name="pmPdDiscountPrice" id="DiscountPrice">
									</tbody>
								</table>
								<br> <input type="hidden" name="action" value="insert">
								<input type="submit" value="送出新增">
							</FORM>
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

</body>

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
</html>