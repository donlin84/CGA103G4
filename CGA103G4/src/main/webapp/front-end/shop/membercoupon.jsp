<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.membercoupon.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.coupontype.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
MemberCouponHibernateService memCpHibernateSvc = new MemberCouponHibernateService();
List<MemberCouponVO> list = memCpHibernateSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="zh-Hant" dir="ltr">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>《See Food》官方網站</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/common/all.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/common/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/common/footer.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/common/main.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/membercoupon.css">
<link href="<%=request.getContextPath()%>/front-end/member/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/front-end/member/assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/front-end/member/assets/css/style.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/front-end/assets/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/assets/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/assets/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/nav.js"></script>
</head>

<body>

	<%@ include file="../tools/header.jsp"%>

	<div class="container -on" id="membercouponPage">
		<div id="membercouponPageContent">
			<main class="main">

				<jsp:useBean id="now" class="java.util.Date" />
				<fmt:formatDate value="${now}" type="both" dateStyle="long"
					pattern="yyyy-MM-dd" var="nowTime" />
				<!-- ============================================ 取得帳戶 ============================================ -->
				<jsp:useBean id="memberSvc" scope="page"
					class="com.member.model.MemberService" />
				<c:forEach var="memberVO" items="${memberSvc.all}">
					<c:if test="${memberVO.memAccount == account}">
						<c:set var="memid" value="${memberVO.memid}" />
						<%-- 							${memberVO.memAccount} --%>
					</c:if>
				</c:forEach>

				<c:choose>
					<c:when test="${empty account}">
						<div class="gotologinbox">
							<a href="../member/frontEndLogin.jsp" class="gotologin">您尚未登入
								無法顯示擁有的優惠券喔!</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="gotologinbox">
							<a href="#" class="gotologin">以下為您擁有的優惠券</a>
						</div>
						<ul class="item_list">
							<c:forEach var="memberCouponVO" items="${list}">
								<c:if test="${memberCouponVO.memberVO.memid == memid}" var="condition" scope="page">
									<c:if test="${memberCouponVO.memCpStatus == 1}" var="condition" scope="page">
										<c:if test="${memberCouponVO.memCpDate >= nowTime}" var="condition" scope="page">
											<li><a href="#">
													<div class="img_block">
														<img
															src="<%=request.getContextPath()%>/CouponTypePic?cpTpid=${memberCouponVO.couponTypeVO.cpTpid}"
															width="150px;">
													</div> <span class="item_text"><br> <b>${memberCouponVO.couponTypeVO.cpName}</b></span>
													<span class="item_text content">${memberCouponVO.memCpDate}</span><br>
											</a></li>

										</c:if>
									</c:if>
								</c:if>
							</c:forEach>
						</ul>
					</c:otherwise>

				</c:choose>
			</main>

		</div>

	</div>
	<%@ include file="../tools/footer.jsp"%>

</body>
</html>