<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotionsdetail.model.*"%>
<%@ page import="com.productSort.model.*"%>
<%@ page import="com.promotions.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>優惠方案管理</title>

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
	href="<%=request.getContextPath()%>/back-end/css/discount-management/discount-management.css"
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

						<!-- notification-->

						<li class="list-inline-item dropdown notification-list"><a
							class="nav-link dropdown-toggle arrow-none waves-effect"
							data-toggle="dropdown" href="#" role="button"
							aria-haspopup="false" aria-expanded="true"><i
								class="ti-bell noti-icon"></i><span
								class="badge badge-danger noti-icon-badge">5</span></a>
							<div
								class="dropdown-menu dropdown-menu-right dropdown-arrow dropdown-menu-lg"
								style="position: absolute; transform: translate3d(-222px, 70px, 0px); top: 0px; left: 0px; will-change: transform;">
								<!-- item-->

								<div class="dropdown-item noti-title">
									<h5>
										<span class="badge badge-danger float-right">5</span>通知
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

						<li class="has-submenu "><a href="#"><i class="mdi mdi-shopping"></i>商城管理</a>

							<ul class="submenu">
								<li><a href="/CGA103G4/back-end/discount-management/discount-management.jsp">優惠方案管理</a></li>
								<li><a href="<%=request.getContextPath()%>/back-end/product/productShowAll.jsp">商品管理</a></li>
								<li><a href="<%=request.getContextPath()%>/back-end/product/orderShowAll.jsp ">訂單管理</a></li>
							</ul>
						<li class="has-submenu "><a href="#"><i class="mdi ion-ios7-people"></i>課程管理</a>

							<ul class="submenu">
								<li class="has-submenu"><a href="/CGA103G4/back-end/classifm/listAllClassIfm.jsp">課程</a>
								<ul class="submenu">
										<li><a href="/CGA103G4/back-end/classifm/listAllClassIfm.jsp">查看全部課程</a></li>
										<li><a href="/CGA103G4/back-end/classifm/addClassIfm.jsp">新增課程</a></li>
									</ul></li>
								<li class="has-submenu"><a href="<%=request.getContextPath()%>/back-end/classtag/listAllClassTag.jsp">課程標籤</a>
								<ul class="submenu">
										<li><a href="/CGA103G4/back-end/classtag/listAllClassTag.jsp">查看全部標籤</a></li>
										<li><a href="/CGA103G4/back-end/classtag/addClassTag.jsp">新增標籤</a></li>
									</ul></li>
								<li><a href="<%=request.getContextPath()%>/back-end/teacher/select_page.jsp">教師管理</a></li>
								<li class="has-submenu"><a href="/CGA103G4/back-end/registtrationform/listAllRegisttrationForm.jsp">查看報名表</a>
								<ul class="submenu">
										<li><a href="/CGA103G4/back-end/registtrationform/listAllRegisttrationForm.jsp">查看全部報名表</a></li>
										<li><a href="/CGA103G4/back-end/registtrationform/getclaid_allmemid.jsp">列印報名表</a></li>
									</ul></li>
							</ul>
						<li class="has-submenu "><a href="#"><i class="mdi ion-spoon"></i>私廚管理</a>

							<ul class="submenu">
								<li><a href=${(chefVO==null)? "#" : "./chefAppointment/select_page.jsp" }>預約管理</a></li>
								<li><a href="<%=request.getContextPath()%>/back-end/chef/select_page.jsp">個人資料管理</a></li>
							</ul>
						<li class="has-submenu"><a href="#"><i class="mdi dripicons-device-desktop"></i>前台管理</a>
							<ul class="submenu">
								<li class="has-submenu"><a href="#">客服</a>
								<ul class="submenu">
										<li><a href="<%=request.getContextPath()%>/back-end/memberservice/chat.jsp">客服管理</a></li>
										<li><a href="<%=request.getContextPath()%>/back-end/memberservice/keyword.jsp">關鍵字管理</a></li>
									</ul>
								</li>
								<li><a href="/CGA103G4/back-end/announcement/announce-management.jsp">公告管理</a></li>
								<li><a href="#">公司資料管理</a></li>
							</ul>

						<li class="has-submenu "><a href="#"><i class="mdi mdi-layers"></i>後台管理</a>
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
								<li class="breadcrumb-item"><a
									href="discount-management.jsp">優惠方案管理</a></li>
								<li class="breadcrumb-item"><a href="../index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h4 class="page-title">
							<a href="discount-management.jsp">優惠方案管理</a>
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
					<a href="#" data-target="nav1" class="nav">促銷安排</a>
				</div>

				<div class="horizen"></div>

				<div class="section">
					<a href="#" data-target="nav2" class="nav">發放優惠券</a>
				</div>

			</aside>

			<div class="col-sm-9 mx-auto">
				<div class="container nav0 -on" id="indexPage">


					<div id="indexPageContent">
						<div class="container-fluid">
							<!-- end page title end breadcrumb -->
							<div class="row">
								<div class="col-md-6 col-lg-6 col-xl-6">
									<!-- Simple card -->
									<div class="card">
										<div class="card-body">
											<h4 class="card-title font-20 mt-0">優惠券搜尋</h4>
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
										<ul class="list-group list-group-flush">
											<li class="list-group-item">

												<FORM METHOD="post" ACTION="../coupontype/CouponTypeServlet">
													<div class="box-left">
														<b>輸入編號搜尋:</b>
													</div>
													<div class="box-right">
														<input type="text" name="cpTpid" placeholder="請輸入編號搜尋">
														<input type="hidden" name="action"
															value="getOne_For_Display"> <input type="submit"
															class="mybtn" value="搜尋">
													</div>
												</FORM>

											</li>
											<jsp:useBean id="cpTpSvc" scope="page"
												class="com.coupontype.model.CouponTypeService" />
											<li class="list-group-item">

												<FORM METHOD="post" ACTION="../coupontype/CouponTypeServlet">
													<div class="box-left">
														<b>優惠券名稱搜尋:</b>
													</div>
													<div class="box-right">
														<select size="1" name="cpTpid" class="dis-select">
															<c:forEach var="couponTypeVO" items="${cpTpSvc.all}">
																<option value="${couponTypeVO.cpTpid}">${couponTypeVO.cpName}
															</c:forEach>
														</select> <input type="hidden" name="action"
															value="getOne_For_Display"> <input type="submit"
															class="mybtn" value="搜尋">
													</div>
												</FORM>

											</li>
											<li class="list-group-item"><a
												href='../coupontype/listAllCouponType.jsp'
												class="btn btn-primary waves-effect waves-light">查看優惠券清單</a>
												<a href="../coupontype/addCouponType.jsp"
												class="btn btn-primary waves-effect waves-light">新增優惠券種類</a></li>
										</ul>
									</div>
								</div>
								<!-- end col -->

								<div class="col-md-6 col-lg-6 col-xl-6">

									<div class="card">
										<div class="card-body">
											<h4 class="card-title font-20 mt-0">優惠活動搜尋</h4>
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
										<ul class="list-group list-group-flush">

											<li class="list-group-item">
												<FORM METHOD="post" ACTION="../promotions/PromotionsServlet">
													<div class="box-left">
														<b>輸入編號搜尋:</b>
													</div>
													<div class="box-right">
														<input type="text" name="pmid" placeholder="請輸入編號搜尋">
														<input type="hidden" name="action"
															value="getOne_For_Display"> <input type="submit"
															class="mybtn" value="搜尋">
													</div>
												</FORM>
											</li>
											<jsp:useBean id="pmtSvc" scope="page"
												class="com.promotions.model.PromotionsService" />
											<li class="list-group-item">
												<FORM METHOD="post" ACTION="../promotions/PromotionsServlet">
													<div class="box-left">
														<b>優惠活動名稱搜尋:</b>
													</div>
													<div class="box-right">
														<select size="1" name="pmid" class="dis-select">
															<c:forEach var="promotionsVO" items="${pmtSvc.all}">
																<option value="${promotionsVO.pmid}">${promotionsVO.pmName}
															</c:forEach>
														</select> <input type="hidden" name="action"
															value="getOne_For_Display"> <input type="submit"
															class="mybtn" value="搜尋">
													</div>
												</FORM>
											</li>
											<li class="list-group-item"><a
												href='../promotions/listAllPromotions.jsp'
												class="btn btn-primary waves-effect waves-light">查看優惠活動清單</a>
												<a href="../promotions/addPromotions.jsp"
												class="btn btn-primary waves-effect waves-light">新增優惠活動</a></li>
										</ul>
									</div>
								</div>
								<!-- end col -->
							</div>
							<!-- end container -->
						</div>

					</div>

				</div>

				<div class="container nav1">

					<div id="shopPageContent">

						<div class="row">
							<div class="col-md-4 col-lg-4 col-xl-4">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title font-20 mt-0">促銷安排</h4>
									</div>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/back-end/promotionsdetail/PromotionsDetailServlet"
										name="form1">
										<ul class="list-group list-group-flush">
											<li class="list-group-item"><b>選擇優惠名稱</b></li>
											<li class="list-group-item"><select size="1" name="pmid">
													<c:forEach var="PromotionsVO" items="${pmtSvc.all}">
														<option value="${PromotionsVO.pmid}">${PromotionsVO.pmName}
													</c:forEach>
											</select></li>
											<li class="list-group-item"><input type="submit"
												value="確認優惠活動" class="mybtn"> <input type="hidden"
												name="action" value="batchAddPromotionsDetail"></li>
										</ul>
									</FORM>
								</div>
							</div>

							<div class="col-md-8 col-lg-8 col-xl-8">
								<!-- Simple card -->
								<div class="card" style="height: 225px;">
									<div class="card-body">
										<h4 class="card-title font-20 mt-0">促銷商品搜尋</h4>
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
									<ul class="list-group list-group-flush">

										<jsp:useBean id="pdSvc" scope="page"
											class="com.product.model.ProductService" />

										<li class="list-group-item">
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/back-end/promotionsdetail/PromotionsDetailServlet"
												name="form1">
												<b style="margin: 0 10px;">優惠活動名稱查詢:</b> <select size="1"
													name="pmid">
													<option value="">
														<c:forEach var="PromotionsVO" items="${pmtSvc.all}">
															<option value="${PromotionsVO.pmid}">${PromotionsVO.pmName}
														</c:forEach>
												</select> <b style="margin: 0 10px;">產品名稱查詢:</b> <select size="1"
													name="pdid">
													<option value="">
														<c:forEach var="ProductVO" items="${pdSvc.all}">
															<option value="${ProductVO.pdid}">${ProductVO.pdName}
														</c:forEach>
												</select> <input type="submit" value="依條件查詢" class="mybtn"
													style="margin-left: 20px;"> <input type="hidden"
													name="action" value="listPromotionsDetail_ByCompositeQuery">
											</FORM>
										</li>
										<li class="list-group-item"><a
											href='../promotionsdetail/listAllAndQueryPromotionsDetail.jsp'
											class="btn btn-primary waves-effect waves-light">查看商品促銷清單</a></li>

									</ul>
								</div>
							</div>
						</div>
					</div>

				</div>

				<div class="container nav2">

					<div id="coursePageContent">

						<div class="row">
							<div class="col-md-4 col-lg-4 col-xl-4">
								<div class="card" style="height: 238px;">
									<div class="card-body">
										<h4 class="card-title font-20 mt-0">發放優惠券</h4>
									</div>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/back-end/membercoupon/MemberCouponServlet"
										name="form1">
										<ul class="list-group list-group-flush">

											<li class="list-group-item"><b>選擇優惠券種類</b></li>
											<li class="list-group-item"><select size="1"
												name="cpTpid">
													<c:forEach var="couponTypeVO" items="${cpTpSvc.all}">
														<option value="${couponTypeVO.cpTpid}">${couponTypeVO.cpName}
													</c:forEach>
											</select></li>
											<li class="list-group-item"><input type="submit"
												value="確認優惠券種類" class="mybtn"> <input type="hidden"
												name="action" value="batchAddMemberCoupon"></li>
										</ul>
									</FORM>
								</div>
							</div>
							<div class="col-md-8 col-lg-8 col-xl-8">
								<!-- Simple card -->
								<div class="card">
									<div class="card-body">
										<h4 class="card-title font-20 mt-0">已發放優惠券搜尋</h4>
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
									<ul class="list-group list-group-flush">

										<jsp:useBean id="memCpSvc" scope="page"
											class="com.membercoupon.model.MemberCouponService" />

										<li class="list-group-item"><jsp:useBean id="memSvc"
												scope="page" class="com.member.model.MemberService" />

											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/back-end/membercoupon/MemberCouponServlet"
												name="form1">
												<b>會員名稱:</b> <select size="1" name="memid">
													<option value="">
														<c:forEach var="memberVO" items="${memSvc.all}">
															<option value="${memberVO.memid}">${memberVO.memName}
														</c:forEach>
												</select> <b>優惠券名稱:</b> <select size="1" name="cpTpid">
													<option value="">
														<c:forEach var="couponTypeVO" items="${cpTpSvc.all}">
															<option value="${couponTypeVO.cpTpid}">${couponTypeVO.cpName}
														</c:forEach>
												</select>
												<input type="submit" class="mybtn" value="查詢">
												<input type="hidden" name="action" value="listMemberCoupon_ByCompositeQuery">

											</FORM></li>
										<li class="list-group-item">
											<FORM METHOD="post"
												ACTION="../membercoupon/MemberCouponServlet">
												<div class="box-left" style="margin-left: 150px;">
													<b>已發放優惠券ID查詢:</b>
												</div>
												<div class="box-right" style="margin-right: 150px;">
													<select size="1" name="memCpid" class="dis-select">
														<c:forEach var="memberCouponVO" items="${memCpSvc.all}">
															<option value="${memberCouponVO.memCpid}">${memberCouponVO.memCpid}
														</c:forEach>
													</select> <input type="hidden" name="action"
														value="getOne_For_Display"> <input type="submit"
														class="mybtn" style="margin-left: 20px;" value="搜尋">
												</div>
											</FORM>
										</li>
										<li class="list-group-item"><a
											href='../membercoupon/listAllMemberCoupons.jsp'
											class="btn btn-primary waves-effect waves-light">查看已發放優惠券清單</a>
											<a href="../membercoupon/addMemberCoupons.jsp"
											class="btn btn-primary waves-effect waves-light">發放優惠券</a></li>
									</ul>
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
	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/back-end/assets/js/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/js/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/js/modernizr.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/waves.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/js/jquery.nicescroll.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/plugins/skycons/skycons.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/plugins/tiny-editable/mindmup-editabletable.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/plugins/tiny-editable/numeric-input-example.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/plugins/fullcalendar/vanillaCalendar.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/plugins/raphael/raphael-min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/plugins/morris/morris.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/app.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/js/discount-management/discount-management.js"></script>


</body>
</html>