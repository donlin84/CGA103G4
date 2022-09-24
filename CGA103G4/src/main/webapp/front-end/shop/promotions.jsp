<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.membercoupon.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.coupontype.model.*"%>
<%@ page import="com.promotions.model.*"%>


<%
PromotionsService pmtSvc = new PromotionsService();
List<PromotionsVO> list = pmtSvc.getAll();
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/promotion.css">
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
	<div class="container -on" id="promotionsPage">
		<div id="promotionsPageContent">
			<main class="main">
			
			<div class="title" style="height:80px;"><b> 優惠活動 </b></div>
			
				<jsp:useBean id="now" class="java.util.Date" />
				<jsp:useBean id="pdSvc" scope="page" class="com.product.model.ProductService" />
				<jsp:useBean id="pmtDetailSvc" scope="page" class="com.promotionsdetail.model.PromotionsDetailService" />
				
				
				<fmt:formatDate value="${now}" type="both" dateStyle="long"
					pattern="yyyy-MM-dd" var="nowTime" />

				<ul class="item_list">
					
					<c:forEach var="promotionsVO" items="${list}">
					<c:set var="promotion" value="${promotionsVO.pmid}" />
						<c:if test="${promotionsVO.pmStatus == 1}" var="condition" scope="page">
							<c:if test="${promotionsVO.pmEnd >= nowTime}" var="condition" scope="page">
								<li><a href="#" style="
								margin-left:100px;
								background-color:white;
								border-radius:20px;
  								box-shadow: #8b857e 3px 3px inset;
 								box-shadow: 0 3px 3px 0 rgba(0, 0, 0, 0.15), 0 3px 3px 0 rgba(0, 0, 0, 0.15);">
								
									<span class="item_text"><br><b>${promotionsVO.pmName}</b></span>
									<span class="item_text content">${promotionsVO.pmDescription}</span><br>
									<span class="item_text content">起始時間 ${promotionsVO.pmStart}</span>
									<span class="item_text content">截止時間 ${promotionsVO.pmEnd}</span><br>
									
									
<%-- 									<c:forEach var="promotionsDetailVO" items="${pmtDetailSvc.all}"> --%>
<%-- 										<c:if test="${promotionsDetailVO.pmid != promotion}" var="condition" scope="page"> --%>
<!-- 											<span class="item_text content" style="display:inline-block;">沒有商品做此特惠活動</span> -->
<%-- 										</c:if> --%>
<%-- 									</c:forEach> --%>


									<div style="display:inline-block;vertical-align: text-top;">
									<span class="item_text content" style="display:inline-block;transform: translateY(2px);">活動折扣明細&nbsp;&nbsp;:&nbsp;</span>
									<div style="display:inline-block;vertical-align: text-top;">
										<table>
											<thead>
												<tr>
													<th><span class="item_text content" style="display:inline-block;">商品</span></th>
													<th><span class="item_text content" style="display:inline-block;">原價</span></th>
													<th><span class="item_text content" style="display:inline-block;">折扣價</span></th>
												</tr>
											</thead>
												<tbody>
													<c:forEach var="promotionsDetailVO" items="${pmtDetailSvc.all}">
													<tr>
													<c:if test="${promotionsDetailVO.pmid == promotion}" var="condition" scope="page">
														<td><div style="display:inline-block;"><span class="item_text content"> ${promotionsDetailVO.pdVO.pdName}</span></div></td>
														<td><div style="display:inline-block; text-decoration:line-through;"><span class="item_text content"> ${promotionsDetailVO.pdVO.pdPrice}</span></div></td>
														<td><div style="display:inline-block;"><span class="item_text content"> ${promotionsDetailVO.pmPdDiscountPrice}</span></div><br></td>
													</c:if>
													</tr>
													</c:forEach>
													
												</tbody>
											</table>
											
										</div>
									</div>
									
									
									
								</a></li>
							</c:if>
						</c:if>
<!-- 						<div class="horizen"></div> -->
					</c:forEach>
					
				</ul>

			</main>

		</div>

	</div>
	<%@ include file="../tools/footer.jsp"%>

</body>
</html>