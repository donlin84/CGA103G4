<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcement.model.*"%>
<%@ page import="com.emp.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
AnnouncementService annSvc = new AnnouncementService();
List<AnnouncementVO> list = annSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="zh-Hant" dir="ltr">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

<title>《See Food》官方網站</title>
<link rel="stylesheet" href="./css/common/all.css">
<link rel="stylesheet" href="./css/common/header.css">
<link rel="stylesheet" href="./css/common/footer.css">
<link rel="stylesheet" href="./css/common/main.css">
<link rel="stylesheet" href="./css/index.css">


<script src="./js/image.js"></script>
<script src="./js/nav.js"></script>
<style>
</style>
</head>

<body>

	<header class="header">

		<div class="block">

			<nav class="nav">
				<a href="./index-front.jsp"><img src="./images/SeeFoodLogo.png"></a>
				<ul class="nav_list">
					<li><a href="./announce/announce.jsp" data-target="nav1"
						id="announce" class="nav">公告</a></li>
					<li><a href="./shop/shop.jsp" data-target="nav2" id="shop"
						class="nav">商城</a></li>
					<li><a href="./course/course.jsp" data-target="nav3"
						id="course" class="nav">料理課程</a></li>
					<li><a href="./chef/chef.jsp" data-target="nav4" id="chef"
						class="nav">私廚預約</a></li>
					<li><a href="./forum/forum.jsp" data-target="nav5" id="forum"
						class="nav">討論區</a></li>
					<li><a href="./service/service.jsp" data-target="nav6"
						id="social" class="nav">客服</a></li>

				</ul>

				<div class="sign_block">
					<input class="input" placeholder="找食譜"><input class="input" placeholder="找食材"><button class="button"><img src="./images/icon.png"></button>
					<a href="../sign_register.html">登入</a>
				</div>
				
	       <ul class="navbar_list">
          <div class="navbar">
            <li class="announcebar">
              <div><a href="./announce/newannounce.jsp">最新消息</a></div>
              <div><a href="#">廣告瀏覽</a></div>
              <div><a href="#">關於我們</a></div>
            </li>
            <li class="shopbar">
              <div><a href="#">熱門推薦</a></div>
              <div><a href="#">商品分類</a></div>
              <div><a href="#">購物車</a></div>
              <div><a href="./shop/promotions.jsp">優惠活動</a></div>
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

		<li class="black_block"></li>
		<div class="picture -on">
			<a href="#"><img id="image01" src="./images/01.jpg"></a> <a
				href="#"><img id="image02" src="./images/02.jpg"></a> <a
				href="#"><img id="image03" src="./images/03.jpg"></a> <a
				href="#"><img id="image04" src="./images/04.jpg"></a> <a
				href="#"><img id="image05" src="./images/05.jpg"></a> <a
				href="#"><img id="image06" src="./images/06.jpg"></a> <a
				href="#"><img id="image07" src="./images/07.jpg"></a> <a
				href="#"><img id="image08" src="./images/08.jpg"></a> <a
				href="#"><img id="image09" src="./images/09.jpg"></a>

			<div class="leftright">
				<div id="left"></div>
				<div id="right"></div>
			</div>

		</div>

	</header>

	<div class="main">

		<div class="container nav0 -on" id="indexPage">

			<div id="indexPageContent">

				<main class="main">

					<div class="news_box">
						<div class="news">
							<div class="news_content">最新公告</div>
						</div>
					</div>

					<ul class="item_list">
						<c:set var="count" value="0"></c:set>
						
						
						<c:forEach begin="1" end="${fn:length(list)}" varStatus="stat">
							<c:if test="${count < 3}" var="con" scope="page">
								<li><a href="#">
										<div class="img_block">
											<img
												src="<%=request.getContextPath()%>/AnnouncementPic?annid=${list[fn:length(list)-stat.index].annid}">
										</div> <span class="item_text"><br>
										<b>${list[fn:length(list)-stat.index].annTitle}</b></span> <span
										class="item_text content">${list[fn:length(list)-stat.index].annContent}</span><br>
										<span class="item_text content">發布日期
											${list[fn:length(list)-stat.index].annUpdate}</span>
								</a></li>
								<c:set var="count" value="${count+1}" />
							</c:if>
						</c:forEach>
					</ul>


<!-- ====================================================================================================================================== -->

<!-- 					<div class="news_box"> -->
<!-- 						<div class="news"> -->
<!-- 							<div class="news_content">最新課程</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<ul class="item_list"> -->

<%-- 						<c:set var="ann_count" value="0"></c:set> --%>

<%-- 						<c:forEach var="announcementVO" items="${list}"> --%>

<%-- 							<c:if test="${ann_count < 3}" var="condition" scope="page"> --%>
<%-- 								<c:if test="${announcementVO.annStatus == 1}" var="condition2" --%>
<%-- 									scope="page"> --%>

<!-- 									<li><a href="#"> -->
<!-- 											<div class="img_block"> -->
<!-- 												<img -->
<%-- 													src="<%=request.getContextPath()%>/AnnouncementPic?annid=${announcementVO.annid}"> --%>
<!-- 											</div> <span class="item_text"><br> -->
<%-- 											<b>${announcementVO.annTitle}</b></span> <span --%>
<%-- 											class="item_text content">${announcementVO.annContent}</span><br> --%>
<!-- 											<span class="item_text content">發布日期 -->
<%-- 												${announcementVO.annUpdate}</span> --%>
<!-- 									</a></li> -->
<%-- 									<c:set var="ann_count" value="${ann_count+1}" /> --%>

<%-- 								</c:if> --%>
<%-- 							</c:if> --%>

<%-- 						</c:forEach> --%>

<!-- 					</ul> -->
					
<!-- ====================================================================================================================================== -->
				</main>

			</div>

		</div>

		<footer class="footer">
			<div class="us">
				<a href="#" class="pic"><img src="./images/SeeFoodLogo.png"></a><br>
				<li><a href="#" class="pic"><img src="./images/fb.png"></a></li>
				<li><a href="#" class="pic"><img src="./images/ig.png"></a></li>
				<li><a href="#" class="pic"><img src="./images/tw.png"></a></li>
				<li><a href="#" class="pic"><img src="./images/yt.png"></a></li>
				<div class="about_us">
					<li><a href="#" class="footer_contain">關於SeeFood</a></li>
					<li><a href="#" class="footer_contain">公司資訊</a></li>
					<li><a href="#" class="footer_contain">徵才資訊</a></li>
					<li><a href="#" class="footer_contain">廣告合作</a></li>
				</div>
			</div>

			<div class="copyright">
				<li>copyright&copy;2022 seefood</li>
			</div>

			<div class="another">
				<li><a href="#" class="footer_contain">客服資訊</a></li>
				<li><a href="#" class="footer_contain">信箱:SeeFood@gmail.com</a></li>
			</div>

		</footer>
</body>

</html>