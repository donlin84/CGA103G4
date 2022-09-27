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
EmpDAO dao = new EmpDAO();
List<EmpVO> list = dao.getAll(); // 此行的list變數(物件)將提供page1.file的第11行取得查詢到的總筆數，再由page1.file進行分頁的需要
pageContext.setAttribute("list", list); // 將上一行的list變數(物件)存入當前頁面pageContext，再由底下的第83行由JSTL的forEach列印出結果
%>
<%--取得最新管理員編號，並給予預設帳號 --%>
<%
EmpDAO daoid = new EmpDAO();
EmpVO empVOid = daoid.findLatestId();
String latestId = String.valueOf((empVOid.getEmpid() + 1));
%>
<%
EmpVO empVO = (EmpVO) request.getAttribute("empVO");
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
	background-color: #283179;
	color: white;
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

								<label>搜尋員工編號: <c:if test="${not empty errorMsgs}">

										<c:forEach var="message" items="${errorMsgs}">
											<p style="color: red">${message}</p>
										</c:forEach>

									</c:if>
									<FORM METHOD="post" ACTION="emp.do"
										enctype="multipart/form-data">
										<input type="text" name="empid"
											class="form-control form-control-sm"><input
											type="hidden" name="action" value="getOne_For_Display">
										<input type="submit">
									</FORM>
								</label>



							</div>
							<div class="col-sm-12 col-md-6">
								<div id="datatable_filter" class="dataTables_filter"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<table id="datatable"
									class="table table-bordered dataTable no-footer" role="grid"
									aria-describedby="datatable_info">
									<thead>
										<tr role="row">
											<th class="sorting_asc" tabindex="0"
												aria-controls="datatable" rowspan="1" colspan="1"
												aria-sort="ascending"
												aria-label="Name: activate to sort column descending"
												style="width: 50px;">編號</th>
											<th class="sorting" tabindex="0" aria-controls="datatable"
												rowspan="1" colspan="1"
												aria-label="Age: activate to sort column ascending"
												style="width: 300px;">管理員照片</th>
											<th class="sorting" tabindex="0" aria-controls="datatable"
												rowspan="1" colspan="1"
												aria-label="Position: activate to sort column ascending"
												style="width: 100px;">管理員姓名</th>
											<th class="sorting" tabindex="0" aria-controls="datatable"
												rowspan="1" colspan="1"
												aria-label="Office: activate to sort column ascending"
												style="width: 131px;">聯絡電話</th>
											<th class="sorting" tabindex="0" aria-controls="datatable"
												rowspan="1" colspan="1"
												aria-label="Start date: activate to sort column ascending"
												style="width: 115px;">帳號</th>
											<th class="sorting" tabindex="0" aria-controls="datatable"
												rowspan="1" colspan="1"
												aria-label="Salary: activate to sort column ascending"
												style="width: 101px;">密碼</th>
											<th class="sorting" tabindex="0" aria-controls="datatable"
												rowspan="1" colspan="1"
												aria-label="Salary: activate to sort column ascending"
												style="width: 101px;">管理員等級</th>
											<th class="sorting" tabindex="0" aria-controls="datatable"
												rowspan="1" colspan="1"
												aria-label="Salary: activate to sort column ascending"
												style="width: 101px;">管理員狀態</th>
											<th class="sorting" tabindex="0" aria-controls="datatable"
												rowspan="1" colspan="1"
												aria-label="Salary: activate to sort column ascending"
												style="width: 101px;">雇用日期</th>
											<th class="sorting" tabindex="0" aria-controls="datatable"
												rowspan="1" colspan="1"
												aria-label="Salary: activate to sort column ascending"
												style="width: 101px;">管理員管理</th>


										</tr>
									</thead>
										<%@ include file="page1.file"%>
									<tbody>
										<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>"
											end="<%=pageIndex+rowsPerPage-1%>">
											<tr>
												<td>${empVO.empid}</td>
												<td><img
													src="<%= request.getContextPath() %>/EmpShowPic?empid=${empVO.empid}"
													width="100" heigh="100"></td>
												<td>${empVO.empName}</td>
												<td>${empVO.empPhone}</td>
												<td>${empVO.empAccount}</td>
												<td>${(empVO.empPassword!=null)? "*********":"錯誤"}</td>
												<td>${(empVO.empLevel==0)? "最高":"一般"}</td>
												<td>${(empVO.empStatus==0)? "在職":"離職"}</td>
												<td>${empVO.empHiredate}</td>
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
														style="margin-bottom: 0px;">
														<input type="submit" value="修改"
															style="background-color: #283179; border: 2px; color: white;">
														<input type="hidden" name="empid" value="${empVO.empid}">
														<input type="hidden" name="action"
															value="getOne_For_Update">
													</FORM>
												</td>
											</tr>
										</c:forEach>
										<%@ include file="page2.file"%>
									</tbody>
								</table>
							</div>
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
</html>