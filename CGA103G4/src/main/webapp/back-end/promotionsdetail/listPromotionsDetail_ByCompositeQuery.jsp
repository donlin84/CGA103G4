<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotionsdetail.model.*"%>
<%@ page import="com.promotions.model.*"%>
<%@ page import="com.product.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
PromotionsDetailService pmtDetailSvc = new PromotionsDetailService();
List<PromotionsDetailVO> list = pmtDetailSvc.getAll();
pageContext.setAttribute("list", list);
%>
<jsp:useBean id="listPromotionsDetail_ByCompositeQuery" scope="request"
	type="java.util.List<PromotionsDetailVO>" />
<!-- 於EL此行可省略 -->
<jsp:useBean id="pmtSvc" scope="page"
	class="com.promotions.model.PromotionsService" />
<jsp:useBean id="pdSvc" scope="page"
	class="com.product.model.ProductService" />

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>優惠明細查詢</title>

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
								<li class="breadcrumb-item active">優惠明細查詢</li>
								<li class="breadcrumb-item"><a
									href="../discount-management/discount-management.jsp">優惠方案管理</a></li>
								<li class="breadcrumb-item"><a href="../index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h4 class="page-title">優惠明細查詢</h4>
					</div>
				</div>
			</div>
			<!-- end page title end breadcrumb -->
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 header-title">優惠明細查詢</h4>
							<table class="table" id="my-table">
								<tr>
									<th>優惠活動名稱</th>
									<th>產品名稱</th>
									<th>折扣後金額</th>
									<th>活動折扣與產品金額</th>
									<th>修改</th>
								</tr>
								<%@ include file="page1_ByCompositeQuery.file"%>
								<c:forEach var="promotionsDetailVO"
									items="${listPromotionsDetail_ByCompositeQuery}"
									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr>
										<!--將修改的那一筆加入對比色而已-->
										<td>${promotionsDetailVO.pmtVO.pmName}</td>
										<td>${promotionsDetailVO.pdVO.pdName}</td>
										<td>${promotionsDetailVO.pmPdDiscountPrice}</td>
										<td><c:forEach var="pmtVO" items="${pmtSvc.all}">
											<c:if test="${promotionsDetailVO.pmid==pmtVO.pmid}">【${pmtVO.pmName} - ${pmtVO.pmDiscount}】</c:if>
											</c:forEach></td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/back-end/promotionsdetail/PromotionsDetailServlet">
												<input type="submit" value="修改"> <input
													type="hidden" name="pmid"
													value="${promotionsDetailVO.pmid}"> <input
													type="hidden" value="修改"> <input type="hidden"
													name="pdid" value="${promotionsDetailVO.pdid}"> <input
													type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</td>

									</tr>
								</c:forEach>
							</table>
							<%@ include file="page2_ByCompositeQuery.file"%>

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