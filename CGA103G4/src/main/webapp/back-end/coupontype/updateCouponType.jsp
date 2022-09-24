<%@ page import="java.util.*"%>
<%@ page import="java.sql.Date"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupontype.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
CouponTypeVO couponTypeVO = (CouponTypeVO) request.getAttribute("couponTypeVO");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>優惠券修改</title>

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
#img0 {
	min-width: 140px;
	border: 1px solid lightgray;
	display: inline-block;
	min-height: 100px;
	max-width: 200px;
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
								<li class="breadcrumb-item active">優惠券修改</li>
								<li class="breadcrumb-item"><a
									href="../discount-management/discount-management.jsp">優惠方案管理</a></li>
								<li class="breadcrumb-item"><a href="../index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h4 class="page-title">優惠券修改</h4>
					</div>
				</div>
			</div>
			<!-- end page title end breadcrumb -->
			<div class="row">
				<div class="col-2"></div>
				<div class="col-8">
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 header-title">優惠券修改</h4>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>

							<FORM METHOD="post" ACTION="CouponTypeServlet" name="form1"
								enctype="multipart/form-data">
								<table class="table" id="my-table">
									<tr>
										<td>優惠券種類編號:</td>
										<td><%=couponTypeVO.getCpTpid()%></td>
									</tr>
									<tr>
										<td>優惠券名稱:</td>
										<td><input type="TEXT" name="cpName" size="45" style="width: 200px;"
											value="<%=(couponTypeVO == null) ? "" : couponTypeVO.getCpName()%>" placeholder="請輸入優惠券名稱" /></td>
									</tr>
									<tr>
										<td>優惠券折扣價格:</td>
										<td><input type="TEXT" name="cpDiscount" style="width: 200px;"
											value="<%=(couponTypeVO == null) ? "" : couponTypeVO.getCpDiscount()%>" placeholder="請輸入折扣金額"/></td>
									</tr>
									<tr>
										<td>上次修改時間:</td>
										<td><input name="cpStart" id="f_date1" type="text" style="width: 200px;"
											value="<%=(couponTypeVO == null) ? "1970-01-01" : couponTypeVO.getCpStart()%>"
											autocomplete="off"></td>
									</tr>
									<tr>
										<td>發布時間:</td>
										<td><input name="cpEnd" id="f_date2" type="text" style="width: 200px;"
											value="<%=(couponTypeVO == null) ? "2038-12-30" : couponTypeVO.getCpEnd()%>"
											autocomplete="off"></td>
									</tr>
									<tr>
										<td>活動狀態:</td>
										<td><select name="cpStatus" style="width: 200px;">
												<option value="1" style="width: 200px;">上架</option>
												<option value="0" style="width: 200px;">下架</option>
										</select></td>
									</tr>
									<!-- ///////////////////////////////////////////////////////////////////////////////////////////////////// -->
									<tr>
										<td>優惠券圖片:</td>
										<td><input type="file" name="cpPic" accept="image/*"
											id="file0"></td>
									</tr>
									<tr>
										<td>優惠券圖片預覽:</td>
										<td>
											<div id="preview">
												<img
													src="<%=request.getContextPath()%>/CouponTypePic?cpTpid=${couponTypeVO.cpTpid}"
													class="img" id="img0" >
													<span class="text">預覽圖</span>
											</div>
										</td>
									</tr>

								</table>
								<br>
								<div style="text-align:center;">
								<input type="hidden" name="action" value="update">
								<input type="hidden" name="cpTpid"
									value="<%=couponTypeVO.getCpTpid()%>"> <input
									type="submit" class="mybtn" value="送出修改">
									</div>
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

</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
java.sql.Date cpStart = null;
java.sql.Date cpEnd = null;
try {
	cpStart = couponTypeVO.getCpStart();
	cpEnd = couponTypeVO.getCpEnd();
} catch (Exception e) {
	cpStart = new java.sql.Date(System.currentTimeMillis());
	cpEnd = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
  
function addimage() {
		let img = $("#img0");
		img.attr('src', URL.createObjectURL(this.files[0]));
		console.log(img);
};
$("#file0").change(addimage);

<!-- ============================  pmStartDate  ============================ -->

	 $('#f_date1').datetimepicker({
		 format:'Y-m-d',
		 minDate: '1' ,
		 value: 'cpStart' ,
	  onShow:function( ct ){
	   this.setOptions({
	    maxDate:jQuery('#f_date2').val()?$('#f_date2').val():false
	   })
	  },
	  timepicker:false
	 });
	 
<!-- ============================  pmEndDate  ============================ -->

$('#f_date2').datetimepicker({
	 format:'Y-m-d',
 onShow:function( ct ){
  this.setOptions({
   minDate:$('#f_date1').val()?$('#f_date1').val():false
  })
 },
 timepicker:false
});
</script>
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

</html>