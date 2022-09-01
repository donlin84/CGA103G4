<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.promotionsdetail.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
PromotionsDetailVO promotionsDetailVO = (PromotionsDetailVO) request.getAttribute("promotionsDetailVO");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>優惠券查詢首頁</title>

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
								<li class="breadcrumb-item active">優惠券單筆查詢</li>
								<li class="breadcrumb-item"><a href="../discount-management/discount-management.jsp">優惠方案管理</a></li>
								<li class="breadcrumb-item"><a href="../index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h4 class="page-title">優惠券單筆查詢</h4>
					</div>
				</div>
			</div>


			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 header-title">優惠券單筆查詢</h4>
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
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><%=promotionsDetailVO.getPmid()%></td>
										<td><%=promotionsDetailVO.getPdid()%></td>
										<td><%=promotionsDetailVO.getPmPdDiscountPrice()%></td>
									</tr>
								</tbody>
							</table>
							<div class="continueToAdd" style="width: 100%; text-align: center;">
								<a href="<%=request.getContextPath()%>/back-end/promotionsdetail/addPromotionsDetail.jsp"><input type="submit"
									value="繼續新增"></a>
							</div>
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