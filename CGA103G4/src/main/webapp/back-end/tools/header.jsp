  <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
 <header id="topnav">
		<div class="topbar-main">
			<div class="container-fluid">
				<!-- Logo container-->
				<div class="logo">
					<!-- Text Logo -->
					<!--<a href="index.html" class="logo">-->
					<!-- Image Logo -->
					<a href="../index-back.jsp" class="logo"><img src="/CGA103G4/back-end/assets/images/logo.png" alt="" class="logo-large"> </a>
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

<!-- 						<li class="list-inline-item dropdown notification-list"><a -->
<!-- 							class="nav-link dropdown-toggle arrow-none waves-effect" data-toggle="dropdown" href="#" role="button" -->
<!-- 							aria-haspopup="false" aria-expanded="true"><i class="ti-bell noti-icon"></i><span -->
<!-- 								class="badge badge-danger noti-icon-badge"></span></a> -->
<!-- 							<div class="dropdown-menu dropdown-menu-right dropdown-arrow dropdown-menu-lg" -->
<!-- 								style="position: absolute; transform: translate3d(-222px, 70px, 0px); top: 0px; left: 0px; will-change: transform;"> -->
<!-- 								item -->

<!-- 								<div class="dropdown-item noti-title"> -->
<!-- 									<h5> -->
<!-- 										<span class="badge badge-danger float-right"></span>通知 -->
<!-- 									</h5> -->
<!-- 								</div> -->

<!-- 								item -->
<!-- 								<a href="javascript:void(0);" class="dropdown-item notify-item"> -->
<!-- 									<div class="notify-icon bg-success"> -->
<!-- 										<i class="mdi mdi-cart-outline"></i> -->
<!-- 									</div> -->
<!-- 									<p class="notify-details"> -->
<!-- 										<b>您的訂單已送出</b> <small class="text-muted">等候服務人員確認訂單</small> -->
<!-- 									</p> -->
<!-- 								</a> -->
<!-- 								item -->
<!-- 								<a href="javascript:void(0);" class="dropdown-item notify-item"> -->
<!-- 									<div class="notify-icon bg-warning"> -->
<!-- 										<i class="mdi mdi-message"></i> -->
<!-- 									</div> -->
<!-- 									<p class="notify-details"> -->
<!-- 										<b>新訊息</b> <small class="text-muted">你有 2 則訊息未讀</small> -->
<!-- 									</p> -->
<!-- 								</a> -->
<!-- 								item -->
<!-- 								<a href="javascript:void(0);" class="dropdown-item notify-item"> -->
<!-- 									<div class="notify-icon bg-info"> -->
<!-- 										<i class="mdi mdi-martini"></i> -->
<!-- 									</div> -->
<!-- 									<p class="notify-details"> -->
<!-- 										<b>您的商品已發貨</b> <small class="text-muted">約於 x 月 x 日內送達</small> -->
<!-- 									</p> -->
<!-- 								</a> -->
<!-- 								All -->
<!-- 								<a href="javascript:void(0);" class="dropdown-item notify-item">看全部</a> -->
<!-- 							</div></li> -->



						<!-- User-->
						<li class="list-inline-item dropdown notification-list"><a
							class="nav-link dropdown-toggle arrow-none waves-effect nav-user" data-toggle="dropdown" href="#" role="button"
							aria-haspopup="false" aria-expanded="false"> <img src="/CGA103G4/back-end/assets/images/users/avatar-1.jpg" alt="user"
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
								<li><a href="/CGA103G4/back-end/discount-management/discount-management.jsp">優惠方案管理</a></li>
								<li><a href="#">商品管理</a></li>
								<li><a href="#">訂單管理</a></li>
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
								<li><a href="/CGA103G4/back-end/teacher/listAllTeacher.jsp">教師管理</a></li>
								<li class="has-submenu"><a href="/CGA103G4/back-end/registtrationform/listAllRegisttrationForm.jsp">查看報名表</a>
								<ul class="submenu">
										<li><a href="/CGA103G4/back-end/registtrationform/listAllRegisttrationForm.jsp">查看全部報名表</a></li>
										<li><a href="/CGA103G4/back-end/registtrationform/getclaid_allmemid.jsp">列印報名表</a></li>
									</ul></li>
							</ul>
						<li class="has-submenu "><a href="#"><i class="mdi ion-spoon"></i>私廚管理</a>

							<ul class="submenu">
								<li><a href="/CGA103G4/back-end/chefAppointment/select_page.jsp">預約管理</a></li>
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