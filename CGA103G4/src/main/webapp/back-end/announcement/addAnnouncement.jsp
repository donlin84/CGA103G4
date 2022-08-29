<%@ page import="java.sql.Date"%>
<%@ page import="java.io.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.announcement.model.*"%>
<%@ page import="com.emp.model.*"%>

<%
AnnouncementVO announcementVO = (AnnouncementVO) request.getAttribute("announcementVO");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>公告新增</title>

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
								<li class="breadcrumb-item"><a href="#">Zoter</a></li>
								<li class="breadcrumb-item"><a href="#">Tables</a></li>
								<li class="breadcrumb-item active">Editable</li>
							</ol>
						</div>
						<h4 class="page-title">Editable</h4>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 header-title">優惠券新增</h4>
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>

							<FORM METHOD="post" ACTION="AnnouncementServlet" name="form1" enctype="multipart/form-data">
								<table class="table" id="my-table">
									<tr>
										<td>公告名稱:</td>
										<td><input type="TEXT" name="annContent"
											value="<%=(announcementVO == null) ? "公告名稱" : announcementVO.getAnnContent()%>" /></td>
									</tr>
									<tr>
										<td>公告圖片:</td>
										<td><input type="file" name="annPic" accept="image/*" id="file0"></td>
									</tr>
									<tr>
										<td>公告圖片預覽:</td>
										<td>
											<div id="preview">
												<img class="img" id="img0"><span class="text">預覽圖</span>
											</div>
										</td>
									</tr>
									<tr>
										<td>活動狀態:</td>
										<td><input type="TEXT" name="annStatus"
											value="<%=(announcementVO == null) ? "1" : announcementVO.getAnnStatus()%>" /></td>
									</tr>
									<tr>
										<td>起始時間:</td>
										<td><input name="annUpdate" id="f_date1" type="text" autocomplete="off"></td>
									</tr>
									<tr>
										<td>截止時間:</td>
										<td><input name="annTime" id="f_date2" type="text" autocomplete="off"></td>
									</tr>
									<tr>
										<td>員工編號:</td>
										<td><input type="TEXT" name="empid"
											value="<%=(announcementVO == null) ? "0" : announcementVO.getEmpVO().getEmpid()%>" /></td>
									</tr>


									<!-- ///////////////////////////////////////////////////////////////////////////////////////////////////// -->
									<!-- 	private Integer annid; -->
									<!-- 	private String annContent; -->
									<!-- 	private byte[] annPic; -->
									<!-- 	private Integer annStatus; -->
									<!-- 	private Date annUpdate; -->
									<!-- 	private Date annTime; -->
									<!-- 	private EmpVO empVO; -->
									<!-- ///////////////////////////////////////////////////////////////////////////////////////////////////// -->
								</table>
								<br> <input type="hidden" name="action" value="insert"> <input type="submit" value="送出新增">
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

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
java.sql.Date annUpdate = null;
java.sql.Date annTime = null;
try {
	annUpdate = announcementVO.getAnnUpdate();
	annTime = announcementVO.getAnnTime();
} catch (Exception e) {
	annUpdate = new java.sql.Date(System.currentTimeMillis());
	annTime = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

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
		 value: 'annUpdate' ,
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

// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------
// 	     1.以下為某一天之前的日期無法選擇
<%-- 	     var somedate1 = new Date('<%=pmStart%>'); --%>
// 	     $('#f_date1').datetimepicker({
// 	         beforeShowDay: function(date) {
// 	       	  if (  date.getYear() <  somedate1.getYear() || 
// 			           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
// 			           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
// 	             ) {
// 	                  return [false, ""]
// 	             }
// 	             return [true, ""];
// 	     }});
	     
// 	     $.datetimepicker.setLocale('zh');
// 	     $('#f_date1').datetimepicker({
// 	    	timepicker:false,
// 	        format:'Y-m-d',
<%-- 	        value: '<%=pmStart%>', --%>
// 	     });

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
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
</html>