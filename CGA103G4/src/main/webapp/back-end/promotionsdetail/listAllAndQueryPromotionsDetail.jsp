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
								<li class="breadcrumb-item active">優惠明細列表</li>
								<li class="breadcrumb-item"><a
									href="../discount-management/discount-management.jsp">優惠方案管理</a></li>
								<li class="breadcrumb-item"><a href="../index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h4 class="page-title">優惠明細列表</h4>
					</div>
				</div>
			</div>
			<!-- end page title end breadcrumb -->
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 header-title">優惠明細列表</h4>
							
							<jsp:useBean id="pmtSvc" scope="page" class="com.promotions.model.PromotionsService" />
							<jsp:useBean id="pdSvc" scope="page" class="com.product.model.ProductService" />
							
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/promotionsdetail/PromotionsDetailServlet" name="form1">
								<b>優惠活動名稱查詢:</b>
								
								<select size="1" name="pmid">
									<option value="">
										<c:forEach var="PromotionsVO" items="${pmtSvc.all}">
											<option value="${PromotionsVO.pmid}">${PromotionsVO.pmName}
										</c:forEach>
								</select>
								
								<b>產品名稱查詢:</b>
								
								<select size="1" name="pdid">
									<option value="">
										<c:forEach var="ProductVO" items="${pdSvc.all}">
											<option value="${ProductVO.pdid}">${ProductVO.pdName}
										</c:forEach>
								</select>
								<input type="submit" value="查詢">
								<input type="hidden" name="action" value="listPromotionsDetail_ByCompositeQuery">
								
							</FORM>
							<br>

							<table class="table" id="my-table">
								<thead>
									<tr>
										<th>優惠活動名稱</th>
										<th>產品名稱</th>
										<th>折扣</th>
										<th>產品金額</th>
										<th>折扣後金額</th>
<!-- 										<th>優惠活動 - 折扣</th> -->
										<th>修改</th>
									</tr>
								</thead>
								<%@ include file="page1.file"%>
								<%-- 	<%@ include file="/emp/page1.file" %>  --%>
								<c:forEach var="promotionsDetailVO" items="${list}"
									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tbody>
										<tr>
											<td>${promotionsDetailVO.pmtVO.pmName}</td>
											<td>${promotionsDetailVO.pdVO.pdName}</td>

											<td><c:forEach var="pmtVO" items="${pmtSvc.all}">
											<c:if test="${promotionsDetailVO.pmid==pmtVO.pmid}"> ${pmtVO.pmDiscount}</c:if>
											</c:forEach></td>
											
											<td>${promotionsDetailVO.pdVO.pdPrice}</td>

											<td>${promotionsDetailVO.pmPdDiscountPrice}</td>
<%-- 											<td><c:forEach var="pmtVO" items="${pmtSvc.all}"> --%>
<%-- 											<c:if test="${promotionsDetailVO.pmid==pmtVO.pmid}">【${pmtVO.pmName} - ${pmtVO.pmDiscount}】</c:if> --%>
<%-- 											</c:forEach></td> --%>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/back-end/promotionsdetail/PromotionsDetailServlet"
													style="margin-bottom: 0px;">
													<input type="submit" value="修改" class="mybtn">
													<input type="hidden" name="pmid" value="${promotionsDetailVO.pmid}">
													<input type="hidden" value="修改">
													<input type="hidden" name="pdid" value="${promotionsDetailVO.pdid}">
													<input type="hidden" name="action" value="getOne_For_Update">
												</FORM>
											</td>
										</tr>
									</tbody>
								</c:forEach>
							</table>
							<%@ include file="page2.file"%>
							<div class="continueToAdd"
								style="width: 100%; text-align: center;">
								<a href="<%=request.getContextPath()%>/back-end/promotionsdetail/batchQueryPromotionsDetail.jsp">
									<input class="mybtn" type="submit" value="繼續新增"></a>
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