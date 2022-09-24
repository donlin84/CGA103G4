<%@ page import="java.util.*"%>
<%@ page import="java.sql.Date"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.membercoupon.model.*"%>
<%@ page import="com.coupontype.model.*"%>
<%@ page import="com.member.model.*"%>

<%
MemberCouponVO memberCouponVO = (MemberCouponVO) request.getAttribute("memberCouponVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>發放優惠券</title>

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
								<li class="breadcrumb-item active">發放優惠券</li>
								<li class="breadcrumb-item"><a
									href="../discount-management/discount-management.jsp">優惠方案管理</a></li>
								<li class="breadcrumb-item"><a href="../index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h4 class="page-title">發放優惠券</h4>
					</div>
				</div>
			</div>
			<!-- end page title end breadcrumb -->
			<div class="row">
				<div class="col-2"></div>
				<div class="col-8">
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 header-title">發放優惠券</h4>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
							<FORM METHOD="post" ACTION="MemberCouponServlet" name="form1">
								<table class="table" id="my-table">
								
									<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
									<tr>
										<td>會員名稱:</td>
										<td><select size="1" name="memid" style="width: 200px;">
											<c:forEach var="memberVO" items="${memSvc.all}">
												<option value="${memberVO.memid}"
														${(memberCouponVO.memid==memberVO.memid)? 'selected':'' }>${memberVO.memName}
											</c:forEach>
										</select></td>
									</tr>
									<jsp:useBean id="cpTpSvc" scope="page" class="com.coupontype.model.CouponTypeService" /> 
									<tr>
										<td>優惠券名稱:</td>
										<td><select size="1" name="cpTpid" style="width: 200px;">
											<c:forEach var="couponTypeVO" items="${cpTpSvc.all}">
												<option value="${couponTypeVO.cpTpid}">${couponTypeVO.cpName}
											</c:forEach>
										</select></td>
									</tr>
									<tr>
										<td>有效日期:</td>
										<td><input name="memCpDate" id="f_date1" type="text"
											style="width: 200px;" autocomplete="off"></td>
									</tr>
									<tr>
										<td>使用狀態:</td>
										<td><select name="memCpStatus" style="width: 200px;">
												<option value="1">可使用</option>
												<option value="0">已使用</option>
										</select></td>
									</tr>
									<tr>
										<td>使用紀錄:</td>
										<td><input name="memCpRecord" id="f_date2" type="text" style="width: 200px;"
											autocomplete="off" readonly></td>
									</tr>

								</table>
								<br>
								<div style="text-align:center;">
								<input type="hidden" name="action" value="insert">
								<input type="submit" class="mybtn" value="送出新增">
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
	<!-- jQuery -->

</body>

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

var annUpdate = new Date();
function getNextDate(date,day) {  
	  var dd = new Date(date);
	  dd.setDate(dd.getDate() + day);
	  var y = dd.getFullYear();
	  var m = dd.getMonth() + 1 < 10 ? "0" + (dd.getMonth() + 1) : dd.getMonth() + 1;
	  var d = dd.getDate() < 10 ? "0" + dd.getDate() : dd.getDate();
	  return y + "-" + m + "-" + d;
	};

$('#f_date1').datetimepicker({
	 format:'Y-m-d',
	 minDate: '1' ,
	 value: getNextDate(annUpdate,14),
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