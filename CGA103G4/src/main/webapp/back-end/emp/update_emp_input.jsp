<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotionsdetail.model.*"%>
<%@ page import="com.productSort.model.*"%>
<%@ page import="com.promotions.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.time.LocalDate"%>

<%
EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%
LocalDate now = LocalDate.now();
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>新增管理員</title>

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
div.main_content {
	width: 100%;
	margin: 0 auto;
	font-size: 0;

	/*   border: 1px solid red; */
}

aside.aside {
	/*   background-color: #ddd; */
	width: 150px;
	display: inline-block;
	vertical-align: top;
	font-size: 1rem;
	margin-right: 10px;
	/*   border: 1px solid #999; */
}

main.main {
	/*   background-color: #ddd; */
	width: calc(100% - 200px - 10px);
	display: inline-block;
	vertical-align: top;
	font-size: 1rem;
	/*   border: 1px solid #999; */
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

.box-left {
	float: left;
	margin-left: 50px;
	margin-top: 7px;
}

.box-right {
	float: right;
	margin-right: 50px;
}

.dis-select {
	display: inline;
	height: 25px;
	line-height: 20px;
	font-size: 14px;
	width: 172px;
	margin: 0 auto;
}

.select {
	appearance: none;
	-webkit-appearance: none;
	-moz-appearance: none;
	-ms-appearance: none;
	-o-appearance: none;
	-khtml-appearance: none;
}

.select::-ms-expand {
	display: none;
}

table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

table {
	width: auto;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th {
	padding: 5px;
	text-align: center;
}

td {
	padding: 5px;
	text-align: center;
}

.pic {
	width: 400px;
	height: 300px;
	display: flex;
	justify-content: center;
	align-items: center;
}

#showPic {
	max-width: 100%;
	max-height: 100%;
}
</style>
</head>

<body>

	<div id="preloader">
		<div id="status">
			<div class="spinner"></div>
		</div>
	</div>
	<!-- Navigation Bar -->
	<%@ include file="../tools/header.jsp"%>
	<div class="row">
		<div class="col-sm-12">
			<div class="page-title-box">
				<div class="btn-group pull-right">
					<ol class="breadcrumb hide-phone p-0 m-0">
						<li class="breadcrumb-item"><a href="#">Zoter</a></li>
						<li class="breadcrumb-item"><a href="#">Tables</a></li>
						<li class="breadcrumb-item active">Datatable</li>
					</ol>
				</div>
				<h4 class="page-title">管理員系統</h4>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-body">
					<h4 class="mt-0 header-title">Default Datatable</h4>
					<p class="text-muted mb-4 font-14">
						DataTables has most features enabled by default,so all you need to
						do to use it with your own tables is to call the construction
						function:
						<code>$().DataTable();</code>
						.
					</p>
					<div id="datatable_wrapper"
						class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer">
						<div class="row">
							<div class="col-md-12 col-lg-12 col-xl-12">

								<h4 class="card-title font-20 mt-0">管理員資料修改</h4>

								<div class="pic" style="margin: auto; width: 500px">
									<img id="showPic"
										src="<%= request.getContextPath() %>/EmpShowPic?empid=${empVO.empid}"
										style="width: 300px; hegiht: 300px">
								</div>
								<div >
								<div style="margin-left:700px">
									<%-- 錯誤表列 --%>
									<c:if test="${not empty errorMsgs}">
										<font style="color: red">請修正以下錯誤:</font>
										<ul>
											<c:forEach var="message" items="${errorMsgs}">
												<li style="color: red">${message}</li>
											</c:forEach>
										</ul>
									</c:if>
									</div>
									<div style="margin: auto; width: 470px">
										<FORM METHOD="post" ACTION="emp.do" name="form1"
											enctype="multipart/form-data">
											<table>
												<tr>
													<td>員工編號:<font color=red><b>*</b></font></td>
													<td><%=empVO.getEmpid()%></td>
												</tr>
												<tr>
													<td>員工姓名:</td>
													<td><input type="TEXT" name="empName" size="45"
														value="<%=empVO.getEmpName()%>" /></td>
												</tr>
												<tr>
													<td>電話:</td>
													<td><input type="TEXT" name="empPhone" size="45"
														value="<%=empVO.getEmpPhone()%>" /></td>
												</tr>
												<tr>
													<td>帳號:</td>
													<td><%=empVO.getEmpAccount()%></td>
												</tr>
												<tr>
													<td>密碼:</td>
													<td><input type="TEXT" name="empPassword" size="45"
														value="" /></td>
												</tr>
												<tr>
													<td>管理員照片:</td>
													<td><input type="file" id="the_file" accept="image/*"
														multiple name="empPicture" value="" /></td>

												</tr>
												<tr>
													<td>權限等級:</td>
													<td><select size="1" name="empLevel">
															<option value="0">最高管理員</option>
															<option value="1">一般管理員</option>
													</select></td>
												</tr>
												<tr>
													<td>狀態:</td>
													<td><select size="1" name="empStatus">
															<option value="0">在職</option>
															<option value="1">離職</option>
													</select></td>
												</tr>
												<tr>
													<td>雇用日期:</td>
													<td><input name="empHiredate" id="f_date1" type="text"></td>
												</tr>

											</table>
											<br> <input type="hidden" name="action" value="update">
											<input type="hidden" name="empid"
												value="<%=empVO.getEmpid()%>"> <input type="hidden"
												name="empAccount" value="<%=empVO.getEmpAccount()%>">
											<input type="hidden" name="empPicture"
												value="<%=empVO.getEmpPicture()%>">
												<input type="submit" value="送出修改">
										</FORM>
										<input name="Submit"
											type="button" id="Submit"
											onClick="javascript:history.back(1)" value="上一頁" />
									</div>
								</div>
								<div class="col-sm-12 col-md-6">
									<div id="datatable_filter" class="dataTables_filter"></div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12"></div>
							</div>
							<div class="row">
								<div class="col-sm-12 col-md-5"></div>
								<div class="col-sm-12 col-md-7"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- end col -->
		</div>


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
<!-- =========================================datetimepicker========================================== -->

<%
java.sql.Date hiredate = null;
try {
	hiredate = empVO.getEmpHiredate();
} catch (Exception e) {
	hiredate = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
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
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%>' // value:   new Date(),
	});
        
      //不能選擇當天之後的日期
        var somedate2 = new Date('<%=now%>');
	$('#f_date1')
			.datetimepicker(
					{
						beforeShowDay : function(date) {
							if (date.getYear() > somedate2.getYear()
									|| (date.getYear() == somedate2.getYear() && date
											.getMonth() > somedate2.getMonth())
									|| (date.getYear() == somedate2.getYear()
											&& date.getMonth() == somedate2
													.getMonth() && date
											.getDate() > somedate2.getDate())) {
								return [ false, "" ]
							}
							return [ true, "" ];
						}
					});
</script>
<script src="./js/pictureView.js"></script>
</html>