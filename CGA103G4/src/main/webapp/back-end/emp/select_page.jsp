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
<link href="../css/discount-management/discount-management.css"
	rel="stylesheet" type="text/css">

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

th{
	padding: 5px;
	text-align: center;
}
td{
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
	<!-- Navigation Bar-->

	<header id="topnav">
		<div class="topbar-main">
			<div class="container-fluid">
				<!-- Logo container-->
				<div class="logo">
					<!-- Text Logo -->
					<!--<a href="index.html" class="logo">-->
					<!-- Image Logo -->
					<a href="../index-back.jsp" class="logo"><img
						src="../assets/images/logo.png" alt="" class="logo-large"> </a>
				</div>
				<!-- End Logo container-->
				<div class="menu-extras topbar-custom">
					<ul class="list-inline float-right mb-0">
						<li class="list-inline-item hide-phone app-search">
							<form role="search" class="">
								<input type="text" placeholder="Search..." class="form-control"><a
									href=""><i class="fa fa-search"></i></a>
							</form>
						</li>





						<!-- User-->
						<li class="list-inline-item dropdown notification-list"><a
							class="nav-link dropdown-toggle arrow-none waves-effect nav-user"
							data-toggle="dropdown" href="#" role="button"
							aria-haspopup="false" aria-expanded="false"> <img
								src="../assets/images/users/avatar-1.jpg" alt="user"
								class="rounded-circle">
						</a>

							<div class="dropdown-menu dropdown-menu-right profile-dropdown ">
								<!-- item-->
								<div class="dropdown-item noti-title">
									<h5>
										<span>歡迎</span><span>xxx</span>
									</h5>
								</div>

								<a class="dropdown-item" href="#"> <i
									class="mdi mdi-settings m-r-5 text-muted"></i>設定
								</a>

								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#"> <i
									class="mdi mdi-logout m-r-5 text-muted"></i>登出
								</a>

							</div></li>
						<li class="menu-item list-inline-item">
							<!-- Mobile menu toggle--> <a class="navbar-toggle nav-link"></a>
							<div class="lines">
								<span></span><span></span><span></span>
							</div> <!-- End mobile menu toggle-->
						</li>
					</ul>
				</div>
				<!-- end menu-extras -->
				<div class="clearfix"></div>
			</div>
			<!-- end container -->
		</div>
		<!-- end topbar-main -->
		<!-- MENU Start -->
		<div class="navbar-custom">
			<div class="container-fluid">
				<div id="navigation">
					<!-- Navigation Menu-->
					<ul class="navigation-menu text-center">

						<li class="has-submenu "><a href="#"><i
								class="mdi mdi-shopping"></i>商城管理</a>

							<ul class="submenu">
								<li><a href="discount-management.jsp" data-target="nav0"
									class="nav">優惠方案管理</a>
								<li><a href="#">商品管理</a></li>
								<li><a href="#">訂單管理</a></li>
							</ul>
						<li class="has-submenu "><a href="#"><i
								class="mdi ion-ios7-people"></i>課程管理</a>

							<ul class="submenu">
								<li><a href="#">課程</a></li>
								<li><a href="#">課程標籤</a></li>
								<li><a href="../teacher/select_page.jsp">教師管理</a></li>
								<li><a href="../registtrationform/select_page.jsp">查看報名表</a></li>
								<li><a href="#">課程紀錄</a></li>
							</ul>
						<li class="has-submenu "><a href="#"><i
								class="mdi ion-spoon"></i>私廚管理</a>

							<ul class="submenu">
								<li><a href="<%=request.getContextPath()%>/back-end/chefAppointment/select_page.jsp">預約管理</a></li>
								<li><a href="#">個人資料管理</a></li>
							</ul>
						<li class="has-submenu"><a href="#"><i
								class="mdi dripicons-device-desktop"></i>前台管理</a>
							<ul class="submenu">
								<li><a href="../service-management/service-management.jsp">客服管理</a></li>
								<li><a href="../announcement/announce-management.jsp">公告管理</a></li>
								<li><a href="#">公司資料管理</a></li>

							</ul>
						<li class="has-submenu "><a href="#"><i
								class="mdi mdi-layers"></i>後台管理</a>
							<ul class="submenu">
								<li><a href="#">管理員系統</a></li>
								<li><a href="#">會員</a></li>
							</ul>
					</ul>
					<!-- End navigation menu -->
				</div>
				<!-- end #navigation -->
			</div>
			<!-- end container -->
		</div>
		<!-- end navbar-custom -->
	</header>

	<div class="wrapper">
		<div class="container-fluid">
			<!-- Page-Title -->
			<div class="row">
				<div class="col-sm-12">
					<div class="page-title-box">
						<div class="btn-group pull-right">
							<ol class="breadcrumb hide-phone p-0 m-0">
								<li class="breadcrumb-item"><a
									href="discount-management.jsp">優惠方案管理</a></li>
								<li class="breadcrumb-item"><a href="../index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h4 class="page-title">
							<a href="select_page.jsp">管理員管理</a>
						</h4>
					</div>
				</div>
			</div>

		</div>
		<!-- end container -->
	</div>
	<!-- end wrapper -->


	<div class="main col-sm-12 mx-auto">

		<div class="row">

			<aside class="col-sm-2">

				<div class="section">
					<a href="#" data-target="nav1" class="nav">管理員查詢</a>
				</div>

				<div class="horizen"></div>

				<div class="section">
					<a href="#" data-target="nav2" class="nav">新增管理員</a>
				</div>

			</aside>

			<div class="col-sm-9 mx-auto">
				<div class="container nav0 -on" id="indexPage">


					<div id="indexPageContent">
						<div class="container-fluid">
							<!-- end page title end breadcrumb -->
							<div class="row">
							<div class="col-md-12 col-lg-12 col-xl-12">

								<div class="card-body">
									<h4 class="card-title font-20 mt-0">管理員列表</h4>
								</div>
								<table>
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
									<%@ include file="page1.file"%>
									<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>"
										end="<%=pageIndex+rowsPerPage-1%>">
										<tr>
											<td>${empVO.empid}</td>
											<td>${empVO.empName}</td>
											<td>${empVO.empPhone}</td>
											<td><img
												src="<%= request.getContextPath() %>/EmpShowPic?empid=${empVO.empid}"
												width="100" heigh="100"></td>
											<td>${empVO.empAccount}</td>
											<td>${empVO.empPassword}</td>
											<td>${empVO.empLevel}</td>
											<td>${empVO.empStatus}</td>
											<td>${empVO.empHiredate}</td>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
													style="margin-bottom: 0px;">
													<input type="submit" value="修改"> <input
														type="hidden" name="empid" value="${empVO.empid}">
													<input type="hidden" name="action"
														value="getOne_For_Update">
												</FORM>
											</td>
										</tr>
									</c:forEach>
								</table>
								<%@ include file="page2.file"%>


							</div>
<!-- 								
								<!-- end col -->


								<!-- end col -->
							</div>
							<!-- end container -->
						</div>

					</div>

				</div>

				<div class="container nav1">

					<div id="shopPageContent">

						<div class="row">
							<div class="col-md-8 col-lg-8 col-xl-8">
		
									<div class="card" style="margin: auto; width: 470px">
										<div class="card-body">
											<h4 class="card-title font-20 mt-0">管理員搜尋</h4>
										</div>
										錯誤表列
										<c:if test="${not empty errorMsgs}">
											<font style="color: red">請修正以下錯誤:</font>
											<ul>
												<c:forEach var="message" items="${errorMsgs}">
													<li style="color: red">${message}</li>
												</c:forEach>
											</ul>
										</c:if>
										<ul class="list-group list-group-flush">
											<li class="list-group-item">

												<FORM METHOD="post" ACTION="emp.do"
													enctype="multipart/form-data" >
													<b>輸入管理員編號 (如101):</b> <input type="text" name="empid">
													<input type="hidden" name="action"
														value="getOne_For_Display"> <input type="submit">
												</FORM>


											</li>
											<jsp:useBean id="cpTpSvc" scope="page"
												class="com.coupontype.model.CouponTypeService" />
									<li class="list-group-item">

											<FORM METHOD="post" ACTION="emp.do" enctype="multipart/form-data">
													<b>選擇管理員編號:</b> <select size="1" name="empid">
													<c:forEach var="empVO" items="${list}">
														<option value="${empVO.empid}">${empVO.empid} 
														</c:forEach> 
													</select> <input type="hidden" name="action"
													value="getOne_For_Display"> <input type="submit"
														value="送出"> 
												</FORM>


											</li>
											<FORM METHOD="post" ACTION="emp.do"
												enctype="multipart/form-data">
												<b>選擇管理員姓名:</b> <select size="1" name="empid">
													<c:forEach var="empVO" items="${list}">
														<option value="${empVO.empid}">${empVO.empName}
													</c:forEach>
												</select> <input type="hidden" name="action"
													value="getOne_For_Display"> <input type="submit"
													value="送出">
											</FORM>

										</ul>
									</div>
								</div>


						</div>
					</div>

				</div>

				<div class="container nav2">

					<div id="coursePageContent">

						<div class="row">
							<div class="col-md-12 col-lg-12 col-xl-12">
								<h4 class="card-title font-20 mt-0">新增管理員</h4>
								<div class="pic" style="margin:auto;width:500px">
									<img id="showPic" src="./images/noImage.jpg" />
								</div>
								<%-- 錯誤表列 --%>
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>
								<div style="margin: auto; width: 470px">
									<FORM METHOD="post" ACTION="emp.do" name="form1"
										enctype="multipart/form-data">
										<table>
											<tr>
												<td>管理員姓名:</td>
												<td><input type="TEXT" name="empName" size="45"
													value="<%=(empVO == null) ? "" : empVO.getEmpName()%>" /></td>
											</tr>
											<tr>
												<td>管理員電話:</td>
												<td><input type="TEXT" name="empPhone" size="45"
													value="<%=(empVO == null) ? "" : empVO.getEmpPhone()%>" /></td>
											</tr>
											<tr>
												<td>管理員照片:</td>
												<td><input type="file" id="the_file" accept="image/*"
													multiple name="empPicture" value="${empVO.empPicture} " /></td>
											</tr>
											<tr>
												<td>管理員帳號:</td>
												<td><input type="TEXT" name="empAccount" size="45"
													class="readonly"
													value="<%=(empVO == null) ? ("seefoodadmin" + latestId) : empVO.getEmpAccount()%>"
													readonly /></td>
											</tr>
											<tr>
												<td>管理員密碼:</td>
												<td><input type="PASSWORD" name="empPassword" size="45"
													value="<%=(empVO == null) ? "" : empVO.getEmpPassword()%>" /></td>
											</tr>
											<tr>
												<td>雇用日期:</td>
												<td><input name="empHiredate" id="f_date1" type="text"></td>
											</tr>
											<tr>
												<td>管理員權限等級</td>
												<td><select size="1" name="empLevel">
														<option value="0" ${(empVO.empLevel==0)? 'selected':'' }>最高管理員</option>
														<option value="1" ${(empVO.empLevel==0)? 'selected':'' }>一般管理員</option>
												</select></td>
											</tr>

										</table>
										<br> <input type="hidden" name="action" value="insert">
										<input type="submit" value="送出新增">
									</FORM>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>




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
	<script src="../js/discount-management/discount-management.js"></script>


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
             $('#f_date1').datetimepicker({
                 beforeShowDay: function(date) {
               	  if (  date.getYear() >  somedate2.getYear() || 
        		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                     ) {
                          return [false, ""]
                     }
                     return [true, ""];
             }});
</script>
<script src="./js/pictureView.js"></script>
</html>