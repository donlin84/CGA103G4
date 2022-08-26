<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>優惠活動查詢首頁</title>

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

			<table id="table-1">
				<tr>
					<td><h3>優惠活動查詢</h3></td>
				</tr>
			</table>

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<ul>
				<li>
					<FORM METHOD="post" ACTION="PromotionsServlet">
						<b>依優惠活動編號搜尋:</b> <input type="text" name="pmid"> <input type="hidden" name="action"
							value="getOne_For_Display"> <input type="submit" value="送出">
					</FORM>
				</li>

				<li><a href='listAllPromotions.jsp'>優惠活動清單</a></li>

				<jsp:useBean id="pmtSvc" scope="page" class="com.promotions.model.PromotionsService" />
				<li>
					<FORM METHOD="post" ACTION="PromotionsServlet">
						<b>選擇優惠活動編號:</b> <select size="1" name="pmid">
							<c:forEach var="promotionsVO" items="${pmtSvc.all}">
								<option value="${promotionsVO.pmid}">${promotionsVO.pmid}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_For_Display"> <input type="submit" value="送出">
					</FORM>
				</li>

				<li>
					<FORM METHOD="post" ACTION="PromotionsServlet">
						<b>選擇優惠活動名稱:</b> <select size="1" name="pmid">
							<c:forEach var="promotionsVO" items="${pmtSvc.all}">
								<option value="${promotionsVO.pmid}">${promotionsVO.pmName}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_For_Display"> <input type="submit" value="送出">
					</FORM>
				</li>
			</ul>
			<h3>新增活動</h3>

			<ul>
				<li><a href='addPromotions.jsp'>新增活動</a></li>
			</ul>

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