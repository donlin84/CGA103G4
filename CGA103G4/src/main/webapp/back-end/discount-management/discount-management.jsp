<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>優惠方案管理</title>

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
<link href="../css/discount-management/discount-management.css" rel="stylesheet" type="text/css">

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
					<a href="../index-back.jsp" class="logo"><img src="../assets/images/logo.png" alt="" class="logo-large">
					</a>
				</div>
				<!-- End Logo container-->
				<div class="menu-extras topbar-custom">
					<ul class="list-inline float-right mb-0">
						<li class="list-inline-item hide-phone app-search">
							<form role="search" class="">
								<input type="text" placeholder="Search..." class="form-control"><a href=""><i class="fa fa-search"></i></a>
							</form>
						</li>

						<!-- notification-->

						<li class="list-inline-item dropdown notification-list"><a
							class="nav-link dropdown-toggle arrow-none waves-effect" data-toggle="dropdown" href="#" role="button"
							aria-haspopup="false" aria-expanded="true"><i class="ti-bell noti-icon"></i><span
								class="badge badge-danger noti-icon-badge">5</span></a>
							<div class="dropdown-menu dropdown-menu-right dropdown-arrow dropdown-menu-lg"
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
							class="nav-link dropdown-toggle arrow-none waves-effect nav-user" data-toggle="dropdown" href="#" role="button"
							aria-haspopup="false" aria-expanded="false"> <img src="../assets/images/users/avatar-1.jpg" alt="user"
								class="rounded-circle">
						</a>

							<div class="dropdown-menu dropdown-menu-right profile-dropdown ">
								<!-- item-->
								<div class="dropdown-item noti-title">
									<h5>
										<span>歡迎</span><span>xxx</span>
									</h5>
								</div>

								<a class="dropdown-item" href="#"> <i class="mdi mdi-settings m-r-5 text-muted"></i>設定
								</a>

								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#"> <i class="mdi mdi-logout m-r-5 text-muted"></i>登出
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
								<li class="has-submenu"><a href="discount-management.jsp" data-target="nav0" class="nav">優惠方案管理</a>
									<ul class="submenu">
										<li><a href="#" data-target="nav1" class="nav">行銷紀錄</a></li>
										<li><a href="#" data-target="nav2" class="nav">促銷安排</a></li>
										<li><a href="#" data-target="nav3" class="nav">修改/取消活動</a></li>
										<li><a href="#" data-target="nav4" class="nav">發放優惠券</a></li>
									</ul></li>
								<li class="has-submenu"><a href="#">商品管理</a>
									<ul class="submenu">
										<li><a href="#">上架/下架</a></li>
										<li><a href="#">修改定價</a></li>
									</ul></li>
								<li><a href="#">訂單管理</a></li>
							</ul>
						<li class="has-submenu "><a href="#"><i class="mdi ion-ios7-people"></i>課程管理</a>

							<ul class="submenu">
								<li class="has-submenu"><a href="#">課程</a>
									<ul class="submenu">
										<li><a href="#">上架/下架</a></li>
										<li><a href="#">修改課程</a></li>
									</ul></li>
								<li class="has-submenu"><a href="#">課程標籤</a>
									<ul class="submenu">
										<li><a href="#">上架/下架</a></li>
										<li><a href="#">修改標籤</a></li>
									</ul></li>
								<li><a href="#">教師管理</a></li>
								<li><a href="#">查看報名表</a></li>
								<li><a href="#">課程紀錄</a></li>
							</ul>
						<li class="has-submenu "><a href="#"><i class="mdi ion-spoon"></i>私廚管理</a>

							<ul class="submenu">
								<li class="has-submenu"><a href="#">預約管理</a>
									<ul class="submenu">
										<li><a href="#">設定預約時段</a></li>
										<li><a href="#">查看預約單</a></li>
									</ul></li>
								<li class="has-submenu"><a href="#">個人資料管理</a>
									<ul class="submenu">
										<li><a href="#">基本資料設定修改</a></li>
										<li><a href="#">密碼修改</a></li>
									</ul></li>
							</ul>
						<li class="has-submenu"><a href="#"><i class="mdi dripicons-device-desktop"></i>前台管理</a>
							<ul class="submenu">
								<li class="has-submenu"><a href="#">客服管理</a>
									<ul class="submenu">
										<li><a href="#">訂單狀態</a></li>
										<li><a href="#">自動回覆機器人設定</a></li>
										<li><a href="#">查看客服紀錄</a></li>
									</ul></li>
								<li class="has-submenu"><a href="#">公告管理</a>
									<ul class="submenu">
										<li><a href="#">新增公告</a></li>
										<li><a href="#">廣告管理</a></li>
									</ul></li>
								<li class="has-submenu"><a href="#">公司資料管理</a></li>
								<li class="has-submenu"><a href="#">食譜管理</a>
									<ul class="submenu">
										<li><a href="#">食譜審核</a></li>
										<li><a href="#">熱門食譜編排</a></li>
									</ul></li>
								<li class="has-submenu"><a href="#">討論區管理</a>
									<ul class="submenu">
										<li><a href="#">檢舉管理</a></li>
									</ul></li>
							</ul></li>

						<li class="has-submenu "><a href="#"><i class="mdi mdi-layers"></i>後台管理</a>

							<ul class="submenu">
								<li class="has-submenu"><a href="#">管理員系統</a>
									<ul class="submenu">
										<li><a href="#">新增/停用管理員</a></li>
										<li><a href="#">權限管理</a></li>
									</ul></li>
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
								<li class="breadcrumb-item"><a href="discount-management.jsp">優惠方案管理</a></li>
								<li class="breadcrumb-item"><a href="../index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h4 class="page-title">優惠方案管理</h4>
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
					<a href="../promotions/select_page.jsp">優惠活動管理</a>
				</div>

				<div class="horizen"></div>

				<div class="section">
					<a href="../coupontype/select_page.jsp">優惠券管理</a>
				</div>

				<div class="horizen"></div>

				<div class="section">
					<a href="#">訂單狀態</a>
				</div>
				<div class="horizen"></div>

				<div class="section">
					<a href="#">訂單狀態</a>
				</div>

			</aside>

			<div class="col-sm-9 mx-auto">
				<div class="container nav0 -on" id="indexPage">


					<div id="indexPageContent">

						<div class="title">優惠方案管理</div>

						<div class="section x">分區一</div>

						<div class="horizen"></div>

						<div class="section y">分區二</div>

					</div>



				</div>

				<div class="container nav1" id="announcePage">

					<div id="announcePageContent">

						<div class="title">行銷紀錄</div>

						<div class="section x">分區一</div>

						<div class="horizen"></div>

						<div class="section y">分區二</div>

					</div>

				</div>

				<div class="container nav2" id="shopPage">

					<div id="shopPageContent">

						<div class="title">促銷安排</div>

						<div class="section x">分區一</div>

						<div class="horizen"></div>

						<div class="section y">分區二</div>

					</div>

				</div>

				<div class="container nav3" id="coursePage">

					<div id="coursePageContent">


						<div class="title">修改/取消活動</div>

						<div class="section x">分區一</div>

						<div class="horizen"></div>

						<div class="section y">分區二</div>


					</div>

				</div>

				<div class="container nav4" id="chefPage">

					<div id="chefPageContent">

						<div class="title">發放優惠券</div>

						<div class="section x">分區一</div>

						<div class="horizen"></div>

						<div class="section y">分區二</div>

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
	<script src="../assets/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script src="../assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
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