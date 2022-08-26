<%@ page import="java.util.*"%>
<%@ page import="java.sql.Date"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.promotions.model.*"%>

<%
PromotionsVO promotionsVO = (PromotionsVO) request.getAttribute("promotionsVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>優惠活動修改</title>

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
			<!-- end page title end breadcrumb -->
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 header-title">優惠活動修改</h4>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>

							<FORM METHOD="post" ACTION="PromotionsServlet" name="form1">
								<table class="table" id="my-table">
									<tr>
										<td>優惠活動名稱:</td>
										<td><input type="TEXT" name="pmName" size="45"
											value="<%=(promotionsVO == null) ? "活動名稱" : promotionsVO.getPmName()%>" /></td>
									</tr>
									<tr>
										<td>活動描述:</td>
										<td><input type="TEXT" name="pmDescription" size="45"
											value="<%=(promotionsVO == null) ? "活動描述" : promotionsVO.getPmDescription()%>" /></td>
									</tr>
									<tr>
										<td>折扣幅度:</td>
										<td><input type="TEXT" name="pmDiscount" size="45"
											value="<%=(promotionsVO == null) ? "0.00" : promotionsVO.getPmDiscount()%>" /></td>
									</tr>
									<tr>
										<td>起始時間:</td>
										<td><input name="pmStart" id="f_date1" type="text"
											value="<%=(promotionsVO == null) ? "1970-01-01" : promotionsVO.getPmStart()%>" /></td>
									</tr>
									<tr>
										<td>截止時間:</td>
										<td><input name="pmEnd" id="f_date2" type="text"
											value="<%=(promotionsVO == null) ? "2038-12-30" : promotionsVO.getPmEnd()%>"></td>
									</tr>
									<tr>
										<td>活動狀態:</td>
										<td><input type="TEXT" name="pmStatus" size="45"
											value="<%=(promotionsVO == null) ? "1" : promotionsVO.getPmStatus()%>" /></td>
									</tr>

									<jsp:useBean id="pmtSvc" scope="page" class="com.promotions.model.PromotionsService" />
									<tr>
										<td>活動名稱:<font color=red><b>*</b></font></td>
										<td><select size="1" name="pmid">
												<c:forEach var="promotionsVO" items="${pmtSvc.all}">
													<option value="${promotionsVO.pmid}" ${(promotionsVO.pmid==promotionsVO.pmid)?'selected':'' }>${promotionsVO.pmName}
												</c:forEach>
										</select></td>
									</tr>

								</table>
								<br> <input type="hidden" name="action" value="update"> <input type="hidden" name="pmid"
									value="<%=promotionsVO.getPmid()%>"> <input type="submit" value="送出修改">
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
java.sql.Date pmStart = null;
java.sql.Date pmEnd = null;
try {
	pmStart = promotionsVO.getPmStart();
	pmEnd = promotionsVO.getPmEnd();
} catch (Exception e) {
	pmStart = new java.sql.Date(System.currentTimeMillis());
	pmEnd = new java.sql.Date(System.currentTimeMillis());
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
<!-- ============================  pmStartDate  ============================ -->

	 $('#f_date1').datetimepicker({
		 format:'Y-m-d',
		 minDate: '1' ,
		 value: 'pmStart' ,
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

//----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------
//	     1.以下為某一天之前的日期無法選擇
<%-- 	     var somedate1 = new Date('<%=pmStart%>'); --%>
//	     $('#f_date1').datetimepicker({
//	         beforeShowDay: function(date) {
//	       	  if (  date.getYear() <  somedate1.getYear() || 
//			           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
//			           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
//	             ) {
//	                  return [false, ""]
//	             }
//	             return [true, ""];
//	     }});
	     
//	     $.datetimepicker.setLocale('zh');
//	     $('#f_date1').datetimepicker({
//	    	timepicker:false,
//	        format:'Y-m-d',
<%-- 	        value: '<%=pmStart%>', --%>
//	     });

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