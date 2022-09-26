<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>管理員管理</title>

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
	background-color:#283179;
	color:white;
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
						
						<code>$().DataTable();</code>
						.
					</p>
					<div id="datatable_wrapper"
						class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer">
						<div class="row">
							<div class="col-sm-12 col-md-6">
<input name="Submit" type="button" id="Submit" onClick="javascript:history.back(1)" value="上一頁" />
							</div>
							<div class="col-sm-12 col-md-6">
								<div id="datatable_filter" class="dataTables_filter">
									
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<table id="datatable"
									class="table table-bordered dataTable no-footer" role="grid"
									aria-describedby="datatable_info">
									<thead>
										<tr>
		<th>管理員編號</th>
		<th>管理員姓名</th>
		<th>聯絡電話</th>
		<th>管理員照片</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>管理員等級</th>
		<th>管理員狀態</th>
		<th>雇用日期</th>
		<th>管理員管理</th>
	</tr>
	<tr>
		<td><%=empVO.getEmpid()%></td>
		<td><%=empVO.getEmpName()%></td>
		<td><%=empVO.getEmpPhone()%></td>
<td><img src="<%= request.getContextPath() %>/EmpShowPic?empid=${empVO.empid}" width="100" heigh="100"></td>
		<td><%=empVO.getEmpAccount()%></td>
		<td><%=empVO.getEmpPassword()%></td>
		<td><%=empVO.getEmpLevel()%></td>
		<td><%=empVO.getEmpStatus()%></td>
		<td><%=empVO.getEmpHiredate()%></td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="empid"  value="${empVO.empid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
	</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 col-md-5">
							</div>
							<div class="col-sm-12 col-md-7">
								
							</div>
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
</html>