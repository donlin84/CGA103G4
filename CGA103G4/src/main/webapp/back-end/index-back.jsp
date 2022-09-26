<%@page import="com.chef.model.ChefService"%>
<%@page import="com.chef.model.ChefVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%@page import="com.orders.model.OrdersVO"%>
<%@page import="com.orders.model.OrdersService"%>
<%@page import="com.member.model.MemberVO"%>
<%@page import="com.member.model.MemberService"%>
<%@page import="java.util.List"%>
<%@page import="com.registtrationform.model.RegisttrationFormService"%>
<%@page import="com.ClassIfm.model.ClassIfmVO"%>
<%@page import="com.ClassIfm.model.ClassIfmService"%>
<%@page import="com.registtrationform.model.RegisttrationFormVO"%>
<%@page import="java.util.ArrayList"%>
<%
ClassIfmService classifmSvc = new ClassIfmService();
List<ClassIfmVO> list = classifmSvc.getAll();
pageContext.setAttribute("alllist", list);
for (ClassIfmVO C : list) {

	List<MemberVO> list3 = new ArrayList<MemberVO>();

	RegisttrationFormService regSrv = new RegisttrationFormService();
	List<RegisttrationFormVO> list_click = regSrv.click_people(C.getClaid());//看第幾個課程回傳報名的會員list*********

	for (RegisttrationFormVO reg : list_click) {//用會員list迭代			<再拿出報名表狀態塞進去>
		MemberService memSrv = new MemberService();
		MemberVO membervo = memSrv.getOneMember(reg.getMemid());//透過會員id拿取那個會員整筆資料回傳vo

		RegisttrationFormService refVO = new RegisttrationFormService();
		RegisttrationFormVO regvo = refVO.getOneRegisttrationForm(C.getClaid(), reg.getMemid());

		membervo.setPeople(regvo.getRegPeople());//找人數 塞進去
		membervo.setRegstatus(regvo.getRegStatus());//找狀態
		list3.add(membervo);//把vo加進 上面宣告的list裡 
	}
	pageContext.setAttribute("regall", list3);
}
%>
<%
OrderDetailVO orderDetailVO = (OrderDetailVO) request.getAttribute("orderDetailVO");
%>
<%
OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO");
%>
<%
OrdersService ordSvc = new OrdersService();
List<OrdersVO> ordlist = ordSvc.getAll();
pageContext.setAttribute("ordlist", ordlist);
%>
<%
ChefService chefsvc = new ChefService();
ChefVO chefVO = new ChefVO();
if((ChefVO) session.getAttribute("chefVO")==null){
	chefVO = chefsvc.getOneChef(301);
}else{
    chefVO =(ChefVO) session.getAttribute("chefVO");
}
	
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>後台首頁</title>

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
<link href='<%=request.getContextPath()%>/back-end/chefAppointment/lib/main.css' rel='stylesheet' />
<script src='<%=request.getContextPath()%>/back-end/chefAppointment/lib/main.js'></script>
<script src="<%=request.getContextPath()%>/back-end/chefAppointment/lib/locales-all.js"></script>
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
					<a href="index-back.jsp" class="logo"><img
						src="assets/images/logo.png" alt="" class="logo-large"> </a>
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

						<!-- notification-->

						<li class="list-inline-item dropdown notification-list"><a
							class="nav-link dropdown-toggle arrow-none waves-effect"
							data-toggle="dropdown" href="#" role="button"
							aria-haspopup="false" aria-expanded="true"><i
								class="ti-bell noti-icon"></i><span
								class="badge badge-danger noti-icon-badge"></span></a>
							<div
								class="dropdown-menu dropdown-menu-right dropdown-arrow dropdown-menu-lg"
								style="position: absolute; transform: translate3d(-222px, 70px, 0px); top: 0px; left: 0px; will-change: transform;">
								<!-- item-->

								<div class="dropdown-item noti-title">
									<h5>
										<span class="badge badge-danger float-right"></span>通知
									</h5>
								</div>

								<!-- item-->
								<a href="javascript:void(0);" class="dropdown-item notify-item">
									<div class="notify-icon bg-success">
										<i class="mdi mdi-cart-outline"></i>
									</div>
									<p class="notify-details">
										<b>您的訂單已送出</b> <small class="text-muted">等候服務人員確認訂單</small>
									</p>
								</a>
								<!-- item-->
								<a href="javascript:void(0);" class="dropdown-item notify-item">
									<div class="notify-icon bg-warning">
										<i class="mdi mdi-message"></i>
									</div>
									<p class="notify-details">
										<b>新訊息</b> <small class="text-muted">你有 2 則訊息未讀</small>
									</p>
								</a>
								<!-- item-->
								<a href="javascript:void(0);" class="dropdown-item notify-item">
									<div class="notify-icon bg-info">
										<i class="mdi mdi-martini"></i>
									</div>
									<p class="notify-details">
										<b>您的商品已發貨</b> <small class="text-muted">約於 x 月 x 日內送達</small>
									</p>
								</a>
								<!-- All-->
								<a href="javascript:void(0);" class="dropdown-item notify-item">看全部</a>
							</div></li>



						<!-- User-->
						<li class="list-inline-item dropdown notification-list"><a
							class="nav-link dropdown-toggle arrow-none waves-effect nav-user"
							data-toggle="dropdown" href="#" role="button"
							aria-haspopup="false" aria-expanded="false"> <img
								src="assets/images/users/avatar-1.jpg" alt="user"
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
								<li><a href="discount-management/discount-management.jsp">優惠方案管理</a></li>
								<li><a href="<%=request.getContextPath()%>/back-end/product/productShowAll.jsp">商品管理</a></li>
								<li><a href="<%=request.getContextPath()%>/back-end/product/orderShowAll.jsp">訂單管理</a></li>
							</ul>
						<li class="has-submenu "><a href="#"><i
								class="mdi ion-ios7-people"></i>課程管理</a>

							<ul class="submenu">
								<li class="has-submenu"><a href="<%=request.getContextPath()%>/back-end/classifm/listAllClassIfm.jsp">課程</a>
								<ul class="submenu">
										<li><a href="<%=request.getContextPath()%>/back-end/classifm/listAllClassIfm.jsp">查看全部課程</a></li>
										<li><a href="<%=request.getContextPath()%>/back-end/classifm/addClassIfm.jsp">新增課程</a></li>
									</ul></li>
								<li class="has-submenu"><a href="<%=request.getContextPath()%>/back-end/classtag/listAllClassTag.jsp">課程標籤</a>
								<ul class="submenu">
										<li><a href="<%=request.getContextPath()%>/back-end/classtag/listAllClassTag.jsp">查看全部標籤</a></li>
										<li><a href="<%=request.getContextPath()%>/back-end/classtag/addClassTag.jsp">新增標籤</a></li>
									</ul></li>
								<li><a href="<%=request.getContextPath()%>/back-end/teacher/select_page.jsp">教師管理</a></li>
								<li class="has-submenu"><a href="<%=request.getContextPath()%>/back-end/registtrationform/listAllRegisttrationForm.jsp">查看報名表</a>
								<ul class="submenu">
										<li><a href="<%=request.getContextPath()%>/back-end/registtrationform/listAllRegisttrationForm.jsp">查看全部報名表</a></li>
										<li><a href="<%=request.getContextPath()%>/back-end/registtrationform/getclaid_allmemid.jsp">列印報名表</a></li>
									</ul></li>
							</ul>
						<li class="has-submenu "><a href="#"><i
								class="mdi ion-spoon"></i>私廚管理</a>

							<ul class="submenu">
								<li><a href=${(chefVO==null)? "#" : "./chefAppointment/select_page.jsp" }>預約管理</a></li>
								<li><a href="<%=request.getContextPath()%>/back-end/chef/select_page.jsp">個人資料管理</a></li>
							</ul>
						<li class="has-submenu"><a href="#"><i
								class="mdi dripicons-device-desktop"></i>前台管理</a>
							<ul class="submenu">
								<li><a href="<%=request.getContextPath()%>/back-end/memberservice/chat.jsp">客服管理</a></li>
								<li><a href="<%=request.getContextPath()%>/back-end/memberservice/keyword.jsp">關鍵字管理</a></li>
								<li><a href="announcement/announce-management.jsp">公告管理</a></li>
								
<!-- 								<li><a href="#">食譜管理</a></li> -->
<!-- 								<li><a href="#">討論區管理</a> -->
							</ul>
						<li class="has-submenu "><a href="#"><i
								class="mdi mdi-layers"></i>後台管理</a>
							<ul class="submenu">
								<li class="has-submenu"><a href="<%=request.getContextPath()%>/back-end/emp/select_page.jsp">管理員系統</a>
								<ul class="submenu">
										<li><a href="<%=request.getContextPath()%>/back-end/emp/addEmp.jsp">新增管理員</a></li>
									</ul>
								</li>
								<li><a href="<%=request.getContextPath()%>/back-end/member/select_page.jsp">會員</a></li>
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
								<li class="breadcrumb-item"><a href="index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h4 class="page-title">後台首頁</h4>
					</div>
				</div>


				<div class="col-md-6 col-lg-6 col-xl-12"
					style="margin: 0 auto; padding: 10px 20px">
					<div class="col-md-12 col-lg-12 col-xl-12"
						style="display: inline-block;">

						<div class="row">
							<div class="col-md-6 col-lg-6 col-xl-6" style="margin: 0 0">
								<div class="card">
									<div class="card-body">
										<h5>
											<span class="round "><i class="mdi mdi-cart"></i></span>商品訂單概況
										</h5>
										<hr>

										<div class="card">
											<div class="card-body">
												<div class="search-type-arrow"></div>
												<div class="d-flex flex-row">
													<div class="col-12 align-self-center text-right">
														<div class="m-l-10 ">
															<h5 class="mt-0">
																<c:set var="count" value="0"></c:set>
																<c:forEach var="ordersVO" items="${ordlist}">
																	<c:set var="count" value="${count+1}" />
																</c:forEach>
																${count}
															</h5>
															<p class="mb-0 text-muted">累積訂單數</p>
														</div>
													</div>
												</div>
												<div class="progress mt-3" style="height: 3px;">
													<div class="progress-bar bg-danger" role="progressbar"
														style="width: ${count}%;" aria-valuenow="${count}"
														aria-valuemin="0" aria-valuemax="100"></div>
												</div>
											</div>
											<!--end card-body-->
										</div>
										<!--end card-->
									</div>
								</div>
							</div>


							<div class="col-md-6 col-lg-6 col-xl-6" style="margin: 0 0">
								<div class="card">
									<div class="card-body">
										<h5>
											<span class="round "><i class="mdi mdi-library"></i></span>課程訂單概況
										</h5>
										<hr>

										<div class="card">
											<div class="card-body">
												<div class="search-type-arrow"></div>
												<div class="d-flex flex-row">
													<div class="col-12 align-self-center text-right">
														<div class="m-l-10 ">
															<h5 class="mt-0">
																<c:set var="count" value="0"></c:set>
																<c:forEach var="alllist"
																	items="${(getallstatus==null)?(alllist):(getallstatus)}"
																	varStatus="abc">
																	<c:set var="count" value="${count+1}" />
																</c:forEach>
																${count}
															</h5>
															<p class="mb-0 text-muted">累積訂單數</p>
														</div>
													</div>
												</div>
												<div class="progress mt-3" style="height: 3px;">
													<div class="progress-bar bg-danger" role="progressbar"
														style="width:${count}%;" aria-valuenow="${count}"
														aria-valuemin="0" aria-valuemax="100"></div>
												</div>
											</div>
											<!--end card-body-->
										</div>
										<!--end card-->

									</div>
								</div>
							</div>
						</div>



						<div class="card">
							<div class="card-body">
								<h5>
									<span class="round "><i class="mdi mdi-calendar"></i></span>行程表
								</h5>

								<hr>
								<div class="row">
									<div class="col-md-12 col-lg-12 col-xl-12"
										style="margin: 0 0 100px">
										<!-- Simple card -->
										<div id='calendar'></div>
										<div id='data' type="hidden" value="${chefscheduleVO.schDate}"></div>
										<script>
        								document.addEventListener('DOMContentLoaded', function () {
											
            								var calendarEl = document.getElementById('calendar');
											
            								let chefid = <%=chefVO.getChefid() %>
            								
            								var MyPoint = "/back-end/chefSchedule/chefsch.do?action=getAllById&chefid="+chefid;
            								var host = window.location.host;
            								var path = window.location.pathname;
            								var webCtx = path.substring(0, path.indexOf('/', 1));
            								var URL = "http://" + host + webCtx + MyPoint;
											if(chefid!==null){
            								fetch(URL).then(response => response.json("jsons"))
                								.then(jsons => {
                    								console.log(jsons)
                    								console.log(jsons[0].schDate)

                    								var calendar = new FullCalendar.Calendar(calendarEl, {
                        							headerToolbar: {
                            							left: 'prev,next today',
                            							center: 'title',
                            							right: 'dayGridMonth,timeGridWeek'
                        							},
                        							locale: 'zh-tw',
                        							navLinks: true, // can click day/week names to navigate views
                        							selectable: true,
                      								selectMirror: true,
                     								editable: false,
                    								dayMaxEvents: true,
                     								events: jsons,
													dayCellContent: function (arg) {
                        							return arg.date.getDate();
                        							},
                   								 });
                  								  calendar.render();
              								  });
            								console.log(calendar.event)}else{
												
											}
      									  });
									
										</script>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>

			</div>

		</div>
		<!-- end container -->
	</div>
	<!-- end wrapper -->

	<!-- Footer -->
	<%@ include file="tools/footer.jsp"%>
	<!-- End Footer -->
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


</body>
</html>