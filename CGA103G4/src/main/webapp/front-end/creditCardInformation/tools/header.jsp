  <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <header class="header">

    <div class="block">

      <nav class="nav">
        <a href="../index-front.jsp"><img src="../images/SeeFoodLogo.png"></a>
        <ul class="nav_list">
          <li><a href="../announce/announce.jsp" data-target="nav1" id="announce" class="nav">公告</a></li>
          <li><a href="../shop/shop.jsp" data-target="nav2" id="shop" class="nav">商城</a></li>
          <li><a href="../course/course.jsp" data-target="nav3" id="course" class="nav">料理課程</a></li>
          <li><a href="../chef/chef.jsp" data-target="nav4" id="chef" class="nav">私廚預約</a></li>
          <li><a href="../forum/forum.jsp" data-target="nav5" id="forum" class="nav">討論區</a></li>
          <li><a href="../service/service.jsp" data-target="nav6" id="social" class="nav">客服</a></li>
        </ul>

        <div class="sign_block">
          <input class="input" placeholder="找食譜"><input class="input" placeholder="找食材"><button class="button"><img
              src="../images/icon.png"></button>
         <c:choose>

						<c:when test="${empty account}">
							<a href="./member/frontEndLogin.jsp">登入</a>
						</c:when>
						<c:otherwise>
							<div class="btn-group mo-mb-2"
								style="height: 40px; right: 5px; top: 8px;">
								<button type="button" class="btn btn-warning dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">會員</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="../member/update.jsp">修改資料</a>
									<a class="dropdown-item" href="../creditCardInformation/CreditCardInformation.jsp">信用卡</a> <a
										class="dropdown-item" href="#">查看訂單</a> <a
										class="dropdown-item" href="#">查看商品</a> <a
										class="dropdown-item" href="#">查看優惠卷</a> <a
										class="dropdown-item" href="#">查看課程</a> <a
										class="dropdown-item" href="../chef/addChefSubscription.jsp">查看訂閱</a> <a
										class="dropdown-item" href="../chefAppointment/memberListAll.jsp">查看預約</a> <a
										class="dropdown-item" href="#">查看食譜</a> <a
										class="dropdown-item" href="#">查看通知</a> <a
										class="dropdown-item" href="../member/frontEndLogout.jsp">登出</a>

								</div>
							</div>

						</c:otherwise>
					</c:choose>
        </div>

        <ul class="navbar_list">
          <div class="navbar">
            <li class="announcebar">
              <div><a href="#">最新消息</a></div>
              <div><a href="#">廣告瀏覽</a></div>
              <div><a href="#">關於我們</a></div>
            </li>
            <li class="shopbar">
              <div><a href="#">熱門推薦</a></div>
              <div><a href="#">商品分類</a></div>
              <div><a href="#">購物車</a></div>
              <div><a href="#">優惠方案</a></div>
            </li>
            <li class="coursebar">
              <div><a href="#">課程內容資訊</a></div>
              <div><a href="#">報名</a></div>
            </li>
            <li class="chefbar">
              <div><a href="#">私廚介紹</a></div>
              <div><a href="#">預約系統</a></div>
            </li>
            <li class="forumbar">
              <div><a href="#">食譜</a></div>
              <div><a href="#">上傳食譜</a></div>
              <div><a href="#">飲食交流</a></div>
              <div><a href="#">廚藝烹飪</a></div>
              <div><a href="#">廚具交流</a></div>
              <div><a href="#">收藏文章</a></div>
            </li>
            <li class="socialbar">
              <div><a href="http://www.facebook.com">幫助中心</a></div>
              <div><a href="http://instagram.com">聯絡客服</a></div>
            </li>
          </div>
        </ul>

      </nav>
    </div>
    <li class="showbar"></li>
  </header>