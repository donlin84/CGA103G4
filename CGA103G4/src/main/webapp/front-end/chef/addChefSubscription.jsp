<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>
<%@ page import="com.chefsubscription.model.*"%>

<%
ChefService chefSvc = new ChefService();
List<ChefVO> list = chefSvc.getAll();
pageContext.setAttribute("list", list);
%>
<%
ChefSubscriptionService chefSubscriptionSvc = new ChefSubscriptionService();
List<ChefSubscriptionVO> listSubscription = chefSubscriptionSvc.getAll();
pageContext.setAttribute("listSubscription", listSubscription);
%>

<!DOCTYPE html>
<html lang="zh-Hant" dir="ltr">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

  <title>《See Food》官方網站</title>
  <link rel="stylesheet" href="../css/common/all.css">
  <link rel="stylesheet" href="../css/common/header.css">
  <link rel="stylesheet" href="../css/common/footer.css">
  <link rel="stylesheet" href="../css/common/main.css">
  <link rel="stylesheet" href="../css/chef.css">
  <link href="../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="../assets/css/style.css" rel="stylesheet" type="text/css">
  <script src="../js/chef.js"></script>
  <script src="../js/nav.js"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/chef/css/chef.css">
  <style>
*{
        box-sizing: border-box;
      }
      :root{
        --aside-width: 400px;
      }
      body{
        margin: 0;
      }
      img{
        max-width: 100%;
      }

     
    
/* ==================== main 區域 ==================== */
      main.main{
        border: 1px solid red;
/*         margin-left: var(--aside-width); */
        width: calc(100% - var(--aside-width));
        height: 1150px;
        padding: 20px;
/*         background-color: hsl(34, 100%, 84%); */
background: linear-gradient(to bottom, #faf1e3, #f0e3cc 7%);
        min-height: calc(100vh - var(--header-height));
        transition: all 1s;
      }
      @media screen and (max-width: 767px){
        main.main{
          width: 100%;
          margin-left: 0;
        }
      }
      main.main ul.item_list{
        margin: 0;
        padding: 0;
        list-style:none;
        display: flex;
        flex-wrap: wrap;
        height:500px;
      }
      main.main ul.item_list > li{
        width: calc((100% - 60px) / 4);
        margin-bottom:20px;
        margin-right: 20px;
      }
      @media screen and (max-width: 767px){
        main.main ul.item_list > li{
          width: calc((100% - 20px) / 2);
        }
      }
      main.main ul.item_list > li:nth-child(4n){
        margin-right: 0;
      }
      @media screen and (max-width: 767px){
        main.main ul.item_list > li:nth-child(2n){
          margin-right: 0;
        }
      }
      main.main ul.item_list > li > a{
        display: inline-block;
        border: 1px solid red;
        text-decoration: none;
        width: 100%;
      }
      main.main ul.item_list > li > a div.img_block{
        border: 1px solid blue;
        font-size: 0;
      }
      main.main ul.item_list > li > a span.item_text{
        border: 1px solid blue;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        display: block;
        width: 100%;
      }
      

</style>
</head>

<body>

<%@ include file="./tools/header.jsp"%>
 
<!-- =================main========================================================================-->
      	<main class="main">

	<ul class="item_list">
<c:forEach var="ChefSubscriptionVO" items="${listSubscription}">
				<c:choose>
					<c:when test="${id == ChefSubscriptionVO.memid}">
			<c:forEach var="chefVO" items="${list}">
			<c:choose>
					<c:when test="${chefVO.chefid == ChefSubscriptionVO.chefid}">
				<li>
				<a href="#">
            <div class="img_block">
              <img src="<%=request.getContextPath()%>/showChefPhotoPicture?chefid=${chefVO.chefid}" 
              width="200px" height="200px">
           	   <span class="item_text">${chefVO.chefName} ${chefVO.chefNickname}</span>
               <span class="item_text">價格 :  ${chefVO.chefPrice}</span>
               <span class="item_text">簡介 :  ${chefVO.chefIntroduction}</span>
               <span class="item_text">評價人數 :  ${chefVO.com}</span>
               <span class="item_text">評價總分 :  ${chefVO.gomg}</span>
            </div>
          </a>
		<FORM METHOD="post" ACTION="frontEndChefSubscription.do">
		<input type="submit" value="取消訂閱" class="content_3" style="width: 100%;"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="hidden" name="memid" value="${id}"> 
		<input type="hidden" name="action" value="delete">
		</FORM>
 		<FORM METHOD="post" ACTION="frontEndChefSubscription.do">
        <input type="hidden" name="action" value="getOne_For_Display"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="submit" value="了解更多" class="content_3" style="width: 100%;">
        </FORM>
			</li>
               </c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</c:forEach>
 			</ul>

      	       
		

       	</main>

  
<%@ include file="../tools/footer.jsp"%>
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/popper.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>

</html>