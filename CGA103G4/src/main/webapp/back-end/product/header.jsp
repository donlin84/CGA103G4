  <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
 <header id="topnav">
    <div class="topbar-main">
      <div class="container-fluid">
        <!-- Logo container-->
        <div class="logo">
          <!-- Text Logo -->
          <!--<a href="index.html" class="logo">-->
          <!-- Image Logo -->
          <a href="index.html" class="logo">
            <img src="assets/images/logo.png" alt="" class="logo-large">
          </a>
        </div><!-- End Logo container-->
        <div class="menu-extras topbar-custom">

          <ul class="list-inline float-right mb-0">

            <li class="list-inline-item hide-phone app-search">
              <form role="search" class="">
                <input type="text" placeholder="搜尋..." class="form-control"><a href="">
                  <i class="fa fa-search"></i></a>
              </form>
            </li>

            </li><!-- notification-->
            <li class="list-inline-item dropdown notification-list">
              <a class="nav-link dropdown-toggle arrow-none waves-effect" data-toggle="dropdown" href="#" role="button"
                aria-haspopup="false" aria-expanded="false">
                <i class="ti-bell noti-icon"></i>
                <span class="badge badge-success noti-icon-badge">3</span>
              </a>

              <div class="dropdown-menu dropdown-menu-right dropdown-arrow dropdown-menu-lg">
                <!-- item-->
                <div class="dropdown-item noti-title">
                  <h5>通知</h5>
                </div><!-- item-->
                <a href="javascript:void(0);" class="dropdown-item notify-item">
                  <div class="notify-icon bg-success">
                    <i class="mdi mdi-cart-outline"></i>
                  </div>
                  <p class="notify-details">
                    <b>您的訂單已送出</b>
                    <small class="text-muted">等候服務人員確認訂單</small>
                  </p>
                </a>
                <!-- item-->
                <a href="javascript:void(0);" class="dropdown-item notify-item">
                  <div class="notify-icon bg-warning">
                    <i class="mdi mdi-message"></i>
                  </div>
                  <p class="notify-details"><b>新訊息</b>
                    <small class="text-muted">你有 2 則訊息未讀</small>
                  </p>
                </a>
                <!-- item-->
                <a href="javascript:void(0);" class="dropdown-item notify-item">
                  <div class="notify-icon bg-info">
                    <i class="mdi mdi-martini"></i>
                  </div>
                  <p class="notify-details"><b>您的商品已發貨</b>
                    <small class="text-muted">約於 x 月 x 日內送達</small>
                  </p>
                </a>
                <!-- All-->
                <a href="javascript:void(0);" class="dropdown-item notify-item">看全部</a>
              </div>
            </li>
            <!-- User-->
            <li class="list-inline-item dropdown notification-list">
              <a class="nav-link dropdown-toggle arrow-none waves-effect nav-user" data-toggle="dropdown" href="#"
                role="button" aria-haspopup="false" aria-expanded="false">
                <img src="assets/images/users/avatar-1.jpg" alt="user" class="rounded-circle">
              </a>

              <div class="dropdown-menu dropdown-menu-right profile-dropdown ">
                <!-- item-->
                <div class="dropdown-item noti-title">
                  <h5><span>歡迎</span><span>xxx</span></h5>
                </div>

                <a class="dropdown-item" href="#">
                  <i class="mdi mdi-settings m-r-5 text-muted"></i>設定</a>

                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">
                  <i class="mdi mdi-logout m-r-5 text-muted"></i>登出</a>

              </div>
            </li>
            <li class="menu-item list-inline-item">
              <!-- Mobile menu toggle--><a class="navbar-toggle nav-link">
                <div class="lines"><span></span><span></span><span></span></div>
              </a><!-- End mobile menu toggle-->
            </li>
          </ul>
        </div><!-- end menu-extras -->
        <div class="clearfix"></div>
      </div><!-- end container -->
    </div><!-- end topbar-main -->
    <!-- MENU Start -->
    <div class="navbar-custom">
      <div class="container-fluid">
        <div id="navigation">
          <!-- Navigation Menu-->
          <ul class="navigation-menu text-center">

            <li class="has-submenu "><a href="#"><i class="mdi mdi-shopping"></i>商城管理</a>

              <ul class="submenu">
                <li class="has-submenu"><a href="#">優惠方案管理</a>
                  <ul class="submenu">
                    <li><a href="#">行銷紀錄</a></li>
                    <li><a href="#">促銷安排</a></li>
                    <li><a href="#">修改/取消活動</a></li>
                    <li><a href="#">發放優惠券</a></li>
                  </ul>
                </li>
                <li class="has-submenu"><a href="#">商品管理</a>
                  <ul class="submenu">
                    <li><a href="#">上架/下架</a></li>
                    <li><a href="#">修改定價</a></li>
                  </ul>
                </li>
                <li><a href="#">訂單管理</a></li>
              </ul>

            <li class="has-submenu "><a href="#"><i class="mdi ion-ios7-people"></i>課程管理</a>

              <ul class="submenu">
                <li class="has-submenu"><a href="#">課程</a>
                  <ul class="submenu">
                    <li><a href="#">上架/下架</a></li>
                    <li><a href="#">修改課程</a></li>
                  </ul>
                </li>
                <li class="has-submenu"><a href="#">課程標籤</a>
                  <ul class="submenu">
                    <li><a href="#">上架/下架</a></li>
                    <li><a href="#">修改標籤</a></li>
                  </ul>
                </li>
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
                  </ul>
                </li>
                <li class="has-submenu"><a href="#">個人資料管理</a>
                  <ul class="submenu">
                    <li><a href="#">基本資料設定修改</a></li>
                    <li><a href="#">密碼修改</a></li>
                  </ul>
                </li>
              </ul>

            <li class="has-submenu"><a href="#"><i class="mdi dripicons-device-desktop"></i>前台管理</a>
              <ul class="submenu">
                <li class="has-submenu"><a href="#">客服管理</a>
                  <ul class="submenu">
                    <li><a href="#">訂單狀態</a></li>
                    <li><a href="#">自動回覆機器人設定</a></li>
                    <li><a href="#">查看客服紀錄</a></li>
                  </ul>
                </li>
                <li class="has-submenu"><a href="#">公告管理</a>
                  <ul class="submenu">
                    <li><a href="#">新增公告</a></li>
                    <li><a href="#">廣告管理</a></li>
                  </ul>
                </li>
                <li class="has-submenu"><a href="#">公司資料管理</a></li>
                <li class="has-submenu"><a href="#">食譜管理</a>
                  <ul class="submenu">
                    <li><a href="#">食譜審核</a></li>
                    <li><a href="#">熱門食譜編排</a></li>
                  </ul>
                </li>
                <li class="has-submenu"><a href="#">討論區管理</a>
                  <ul class="submenu">
                    <li><a href="#">檢舉管理</a></li>
                  </ul>
                </li>
              </ul>
            </li>

            <li class="has-submenu "><a href="#"><i class="mdi mdi-layers"></i>後台管理</a>

              <ul class="submenu">
                <li class="has-submenu"><a href="#">管理員系統</a>
                  <ul class="submenu">
                    <li><a href="#">新增/停用管理員</a></li>
                    <li><a href="#">權限管理</a></li>
                  </ul>
                </li>
                <li><a href="#">會員</a></li>
              </ul>

          </ul><!-- End navigation menu -->
        </div><!-- end #navigation -->
      </div><!-- end container -->
    </div><!-- end navbar-custom -->
  </header><!-- End Navigation Bar-->