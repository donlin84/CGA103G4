<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>
<%@ page import="com.chefappointmentform.model.*"%>

<%
ChefVO chefVO = (ChefVO) session.getAttribute("chefVO");
Integer chefid = chefVO.getChefid();
ChefAppointmentFormJDBCDAO dao = new ChefAppointmentFormJDBCDAO();
List<ChefAppointmentFormVO> list = dao.getAllByChef(chefid); // 此行的list變數(物件)將提供page1.file的第11行取得查詢到的總筆數，再由page1.file進行分頁的需要
pageContext.setAttribute("list", list); // 將上一行的list變數(物件)存入當前頁面pageContext，再由底下的第83行由JSTL的forEach列印出結果
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>預約管理</title>

<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/back-end/assets/images/favicon.ico">
<link
	href="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/back-end/assets/plugins/fullcalendar/vanillaCalendar.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/back-end/assets/plugins/morris/morris.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/back-end/assets/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/css/icons.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/css/style.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=request.getContextPath()%>/back-end/css/chefApp/chefsch.css"
	rel="stylesheet" type="text/css">
<link
	href='<%=request.getContextPath()%>/back-end/chefAppointment/lib/main.css'
	rel='stylesheet' />
<script
	src='<%=request.getContextPath()%>/back-end/chefAppointment/lib/main.js'></script>
<script
	src="<%=request.getContextPath()%>/back-end/chefAppointment/lib/locales-all.js"></script>
<script
	src="<%=request.getContextPath()%>/back-end/chefAppointment/js/fullcalendar.js"></script>
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 1100px;
	margin: 0 auto;
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

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>

<body>
	<script>
		let xxx =
	<%=chefVO.getChefid()%>
		
	</script>
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
								<a class="dropdown-item" href="<%=request.getContextPath()%>/BackendLogin?action=remove_account"> <i
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
								<li><a href="#">教師管理</a></li>
								<li><a href="#">查看報名表</a></li>
								<li><a href="#">課程紀錄</a></li>
							</ul>
						<li class="has-submenu "><a href="#"><i
								class="mdi ion-spoon"></i>私廚管理</a>

							<ul class="submenu">
								<li><a href="./select_page.jsp">預約管理</a></li>
								<li><a href="#">個人資料管理</a></li>
							</ul>
						<li class="has-submenu"><a href="#"><i
								class="mdi dripicons-device-desktop"></i>前台管理</a>
							<ul class="submenu">
								<li><a href="#">客服管理</a></li>
								<li><a href="#">公告管理</a></li>
								<li><a href="#">公司資料管理</a></li>
								<li><a href="#">食譜管理</a></li>
								<li><a href="#">討論區管理</a>
							</ul>
						<li class="has-submenu "><a href="#"><i
								class="mdi mdi-layers"></i>後台管理</a>
							<ul class="submenu">
								<li><a
									href="<%=request.getContextPath()%>/back-end/emp/select_page.jsp">管理員系統</a></li>
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
									href="<%=request.getContextPath()%>/back-end/chefAppointment/select_page.jsp">預約管理</a></li>
								<li class="breadcrumb-item"><a href="../index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h4 class="page-title">
							<a href="select_page.jsp">預約管理</a>
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


				<div class="horizen"></div>

				<div class="section">
					<a href="#" data-target="nav2" class="nav">我的預約單</a>
				</div>

				<div class="horizen"></div>

			</aside>

			<div class="col-sm-9 mx-auto">
				<div class="container nav0 -on" id="indexPage">


					<div id="indexPageContent">
						<div class="container-fluid">
							<!-- 							end page title end breadcrumb -->
							<div class="row">
								<div class="col-md-12 col-lg-12 col-xl-12"
									style="margin: 0 0 100px">
									<div id='calendar'></div>

									<!-- 									Simple card -->

								</div>
							</div>
							<!-- 							end container -->
						</div>

					</div>

				</div>


				<div class="container nav2">

					<div id="coursePageContent">

						<div class="row">
							<div class="col-md-12 col-lg-12 col-xl-12">
								<div class="card" style="height: 238px;">
									<table>
										<tr>
											<th>預約單編號</th>
											<th>會員名字</th>
											<th>聯絡電話</th>
											<th>預約日期</th>
											<th>預約時段</th>
											<th>預約金額</th>
											<th>預約狀態</th>
											<th>預約評價</th>
											<th>評論</th>

										</tr>
										<%@ include file="page1.file"%>
										<c:forEach var="chefapp" items="${list}"
											begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
											<tr>
												<td>${chefapp.apmid}</td>
												<td>${chefapp.memberVO.memName}</td>
												<td>${chefapp.memberVO.memPhone}</td>
												<td>${chefapp.apmDate}</td>
												<td>${chefapp.apmTime==0 ? '午餐':'晚餐'}</td>
												<td>${chefapp.apmPrice}</td>
												<td><c:choose>
														<c:when test="${chefapp.apmStatus==0}">   
								待審核
              </c:when>
														<c:when test="${chefapp.apmStatus==1}">   
                    			接受  
			  </c:when>
														<c:when test="${chefapp.apmStatus==2}">   
                    			完成  
			  </c:when>
														<c:otherwise>   
                                取消
              </c:otherwise>
													</c:choose></td>
												<td><c:choose>
														<c:when test="${chefapp.star==1}">   
								★
              </c:when>
														<c:when test="${chefapp.star==2}">   
                    			★★  
			  </c:when>
														<c:when test="${chefapp.star==3}">   
                    			★★★  
			  </c:when>
														<c:when test="${chefapp.star==4}">   
                    			★★★★ 
			  </c:when>
														<c:when test="${chefapp.star==5}">   
                    			★★★★★ 
			  </c:when>
														<c:otherwise>
														</c:otherwise>
													</c:choose></td>
												<td>${chefapp.comments}</td>
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/back-end/chefAppointment/chefappb.do"
														style="margin-bottom: 0px;">
														<input type="submit" value="修改"> <input
															type="hidden" name="apmid" value="${chefapp.apmid}">
														<input type="hidden" name="action"
															value="ChefgetOne_For_Update">
													</FORM>
												</td>
											</tr>
										</c:forEach>
									</table>
									<%@ include file="page2.file"%>

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
</html>