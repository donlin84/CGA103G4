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

     
      /* ==================== aside 區域 ==================== */
      aside.aside{
/*         border: 1px solid blue; */
         position: absolute; 
        top: var(--header-height);
        left: 0;
/*         height: calc(100% - var(--header-height)); */
        height: 950px;
        width: var(--aside-width);
/*         background-color: #efefef; */
background: linear-gradient(to bottom, #faf1e3, #f0e3cc 7%);
        overflow-y: auto;
        padding: 20px 0;
        transition: all 1s;
      }
      aside.aside button.btn_hamburger{
        display: none;
      }
      @media screen and (max-width: 767px){
        aside.aside{
          top: 0;
          height: 100%;
          transform: translateX(-100%);
        }
        aside.aside.-on{
          transform: translateX(0%);
        }
        
      }

      aside.aside > nav.nav > ul.nav_list{
        margin: 0;
        padding: 0;
        list-style: none;
      }
      aside.aside > nav.nav > ul.nav_list > li > a{
        display: inline-block;
/*         border: 1px solid lightblue; */
        width: 100%;
        padding: 3px 10px;
      }

/* ==================== main 區域 ==================== */
      main.main{
/*         border: 1px solid red; */
        margin-left: var(--aside-width);
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
/*         border: 1px solid red; */
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
 <aside class="aside">
      <nav class="nav">


      <div class="left-side-1">

        <div class="big-left">
          <div class="left-side-2">
            <div class="left-side-2-1">
              <h3>條件篩選</h3>

            </div>
            
           
            
            <jsp:useBean id="chefSvc1" scope="page" class="com.chef.model.ChefService" />
            <FORM METHOD="post" ACTION="frontEndChef.do">
												<b>選擇私廚姓名:</b> <select size="1" name="chefid">
												<option value="">
													<c:forEach var="chefVO" items="${chefSvc1.all}">
														<option value="${chefVO.chefid}">${chefVO.chefName}
													</c:forEach>
												</select> 
												
												<input type="hidden" name="action"
													value="getOne_For_Display"> <input type="submit"
													value="送出" class="content_3"  style="width: 100px;">
											</FORM>
            <FORM METHOD="post" ACTION="frontEndChef.do">
												<b>選擇私廚暱稱:</b> <select size="1" name="chefid">
												<option value="">
													<c:forEach var="chefVO" items="${chefSvc1.all}">
														<option value="${chefVO.chefid}">${chefVO.chefNickname}
													</c:forEach>
												</select> 
												<input type="hidden" name="action"
													value="getOne_For_Display"> <input type="submit"
													value="送出"class="content_3"  style="width: 100px;">
											</FORM>
            
            
            
            
            
		<form method="post" action="frontEndChef.do" >
            <div>
              <div class="price_div">
              
                <span class="little_title">價格</span>
                <hr>
                <label data-v-60c2b4db="" role="checkbox" class="el-checkbox">
                <span aria-checked="mixed" class="el-checkbox__input">
                <span class="el-checkbox__inner"></span>
                <input type="checkbox" aria-hidden="true" name="option" class="el-checkbox__original" value="1"></span>
                <span class="el-checkbox__label">$10000 以下<!----></span>
                </label>
                <label data-v-60c2b4db="" role="checkbox" class="el-checkbox">
                <span aria-checked="mixed" class="el-checkbox__input">
                <span class="el-checkbox__inner"></span>
                <input type="checkbox" aria-hidden="true" name="option" class="el-checkbox__original" value="2"></span>
                <span class="el-checkbox__label">$10000 ~ $20000<!----></span>
                </label>
                <label data-v-60c2b4db="" role="checkbox" class="el-checkbox">
                <span aria-checked="mixed" class="el-checkbox__input">
                <span class="el-checkbox__inner"></span>
                <input type="checkbox" aria-hidden="true" name="option" class="el-checkbox__original" value="3"></span>
                <span class="el-checkbox__label">$20000 以上<!----></span>
                </label>
                <br>
     
 
                
<!--                 <div class="keyword_div"> -->
<!--                 	<span>關鍵字查詢 :</span> -->
<%--                 	<input type="text" name="cla_keyword" value="${param.cla_keyword}" > --%>
<!--                 </div> -->
              </div>

            </div>
            <input type="hidden" name="action" value="browse">
            <input type="submit" value="篩選條件" id="onload_submit">
		</form>
		<br>
		<br>
          </div>
        </div>
      </div>
       </nav>
    </aside>
<!-- =================main========================================================================-->
      	<main class="main">




      	        <%
                if (request.getParameterValues("option") == null) {
                	session.setAttribute("test", 0);
                	session.setAttribute("test1", 0);
                } else {
                String[] values = request.getParameterValues("option"); 

                if (values.length == 1){
                	session.setAttribute("test", values[0]);
                	session.setAttribute("test1", 0);
                };
                if (values.length == 2){
                	session.setAttribute("test", values[0]);
                	session.setAttribute("test1", values[1]);
                };	
                if (values.length == 3){
                	session.setAttribute("test", 0);
                	session.setAttribute("test1", 0);
                };	
                };
               %>

 <c:choose>
		<c:when test="${test == 0 && test1 == 0}">
<%@ include file="page1.file" %> 
	<ul class="item_list">				

       <c:forEach var="chefVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

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
<%
session.setAttribute("sstion", 1);
%>
<c:forEach var="chefSubscriptionVO" items="${listSubscription}">
<c:choose>
<c:when test="${chefSubscriptionVO.chefid == chefVO.chefid && chefSubscriptionVO.memid == id}">
<%
session.setAttribute("sstion", 0);
%>
</c:when>
<c:otherwise>

</c:otherwise>
</c:choose>
</c:forEach>


<c:choose>
<c:when test="${sstion == 1}">
          <FORM METHOD="post" ACTION="ChefFrontEndSubscription.do">
        <input type="hidden" name="action" value="insert"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="hidden" name="memid" value="${id}"> 
        <input type="submit" value="訂閱" class="content_3" style="width: 100%;">
        </FORM>
</c:when>
<c:otherwise>
          <FORM METHOD="post" ACTION="ChefFrontEndSubscription.do">
        <input type="hidden" name="action" value="delete"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="hidden" name="memid" value="${id}"> 
        <input type="submit" value="取消訂閱" class="content_3" style="width: 100%;">
        </FORM>
</c:otherwise>
</c:choose>
          <FORM METHOD="post" ACTION="frontEndChef.do">
        <input type="hidden" name="action" value="getOne_For_Display"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="submit" value="了解更多" class="content_3" style="width: 100%;">
        </FORM>
		 </li>
 
		        </c:forEach>
       </ul>
<%@ include file="page2.file" %>
     </c:when>
        <c:otherwise>
		</c:otherwise>
		</c:choose>
<!-- =============================================================================================	 -->
        <c:choose>
		<c:when test="${test == 1 && test1 == 0}">
<%-- <%@ include file="page1.file" %>  --%>
	<ul class="item_list">				
       <c:forEach var="chefVO" items="${list}">
<%--        <c:forEach var="chefVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
		<c:choose>
		<c:when test="${chefVO.chefPrice <= 10000}">
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
          
<%
session.setAttribute("sstion", 1);
%>
<c:forEach var="chefSubscriptionVO" items="${listSubscription}">
<c:choose>
<c:when test="${chefSubscriptionVO.chefid == chefVO.chefid && chefSubscriptionVO.memid == id}">
<%
session.setAttribute("sstion", 0);
%>
</c:when>
<c:otherwise>

</c:otherwise>
</c:choose>
</c:forEach>
          <c:choose>
<c:when test="${sstion == 1}">
          <FORM METHOD="post" ACTION="ChefFrontEndSubscription.do">
        <input type="hidden" name="action" value="insert"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="hidden" name="memid" value="${id}"> 
        <input type="submit" value="訂閱" class="content_3" style="width: 100%;">
        </FORM>
</c:when>
<c:otherwise>
          <FORM METHOD="post" ACTION="ChefFrontEndSubscription.do">
        <input type="hidden" name="action" value="delete"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="hidden" name="memid" value="${id}"> 
        <input type="submit" value="取消訂閱" class="content_3" style="width: 100%;">
        </FORM>
</c:otherwise>
</c:choose>
          <FORM METHOD="post" ACTION="frontEndChef.do">
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
       </ul>
<%-- <%@ include file="page2.file" %> --%>
        </c:when>
		<c:otherwise>
		</c:otherwise>
		</c:choose>
<!-- =============================================================================================	 -->
 <c:choose>
		<c:when test="${test == 2 && test1 == 0}">
<%-- 		<%@ include file="page1.file" %>  --%>
	<ul class="item_list">				
       <c:forEach var="chefVO" items="${list}">
		<c:choose>
		<c:when test="${chefVO.chefPrice <= 20000 && chefVO.chefPrice > 10000}">
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
          
<%
session.setAttribute("sstion", 1);
%>
<c:forEach var="chefSubscriptionVO" items="${listSubscription}">
<c:choose>
<c:when test="${chefSubscriptionVO.chefid == chefVO.chefid && chefSubscriptionVO.memid == id}">
<%
session.setAttribute("sstion", 0);
%>
</c:when>
<c:otherwise>

</c:otherwise>
</c:choose>
</c:forEach>
          <c:choose>
<c:when test="${sstion == 1}">
          <FORM METHOD="post" ACTION="ChefFrontEndSubscription.do">
        <input type="hidden" name="action" value="insert"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="hidden" name="memid" value="${id}"> 
        <input type="submit" value="訂閱" class="content_3" style="width: 100%;">
        </FORM>
</c:when>
<c:otherwise>
          <FORM METHOD="post" ACTION="ChefFrontEndSubscription.do">
        <input type="hidden" name="action" value="delete"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="hidden" name="memid" value="${id}"> 
        <input type="submit" value="取消訂閱" class="content_3" style="width: 100%;">
        </FORM>
</c:otherwise>
</c:choose>
          <FORM METHOD="post" ACTION="frontEndChef.do">
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
       </ul>
<%-- <%@ include file="page2.file" %> --%>
        </c:when>
		<c:otherwise>
		</c:otherwise>
		</c:choose>
<!-- =============================================================================================	 -->		
 <c:choose>
		<c:when test="${test == 3 && test1 == 0}">
<%-- 		<%@ include file="page1.file" %>  --%>
	<ul class="item_list">				
       <c:forEach var="chefVO" items="${list}">
		<c:choose>
		<c:when test="${chefVO.chefPrice > 20000}">
 <li>
        <a href="#">
            <div class="img_block">
              <img src="<%=request.getContextPath()%>/showLicensePicture?chefid=${chefVO.chefid}" 
              width="200px" height="200px">
           	   <span class="item_text">${chefVO.chefName} ${chefVO.chefNickname}</span>
               <span class="item_text">價格 :  ${chefVO.chefPrice}</span>
               <span class="item_text">簡介 :  ${chefVO.chefIntroduction}</span>
               <span class="item_text">評價人數 :  ${chefVO.com}</span>
               <span class="item_text">評價總分 :  ${chefVO.gomg}</span>
            </div>
          </a>
          
<%
session.setAttribute("sstion", 1);
%>
<c:forEach var="chefSubscriptionVO" items="${listSubscription}">
<c:choose>
<c:when test="${chefSubscriptionVO.chefid == chefVO.chefid && chefSubscriptionVO.memid == id}">
<%
session.setAttribute("sstion", 0);
%>
</c:when>
<c:otherwise>

</c:otherwise>
</c:choose>
</c:forEach>
          <c:choose>
<c:when test="${sstion == 1}">
          <FORM METHOD="post" ACTION="ChefFrontEndSubscription.do">
        <input type="hidden" name="action" value="insert"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="hidden" name="memid" value="${id}"> 
        <input type="submit" value="訂閱" class="content_3" style="width: 100%;">
        </FORM>
</c:when>
<c:otherwise>
          <FORM METHOD="post" ACTION="ChefFrontEndSubscription.do">
        <input type="hidden" name="action" value="delete"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="hidden" name="memid" value="${id}"> 
        <input type="submit" value="取消訂閱" class="content_3" style="width: 100%;">
        </FORM>
</c:otherwise>
</c:choose>
          <FORM METHOD="post" ACTION="frontEndChef.do">
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
       </ul>
<%-- <%@ include file="page2.file" %> --%>
        </c:when>
		<c:otherwise>
		</c:otherwise>
		</c:choose>
<!-- =============================================================================================	 -->		
 <c:choose>
		<c:when test="${test == 1 && test1 == 2}">
<%-- 		<%@ include file="page1.file" %>  --%>
	<ul class="item_list">				
       <c:forEach var="chefVO" items="${list}">
		<c:choose>
		<c:when test="${chefVO.chefPrice <= 20000}">
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
          
<%
session.setAttribute("sstion", 1);
%>
<c:forEach var="chefSubscriptionVO" items="${listSubscription}">
<c:choose>
<c:when test="${chefSubscriptionVO.chefid == chefVO.chefid && chefSubscriptionVO.memid == id}">
<%
session.setAttribute("sstion", 0);
%>
</c:when>
<c:otherwise>

</c:otherwise>
</c:choose>
</c:forEach>
          <c:choose>
<c:when test="${sstion == 1}">
          <FORM METHOD="post" ACTION="ChefFrontEndSubscription.do">
        <input type="hidden" name="action" value="insert"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="hidden" name="memid" value="${id}"> 
        <input type="submit" value="訂閱" class="content_3" style="width: 100%;">
        </FORM>
</c:when>
<c:otherwise>
          <FORM METHOD="post" ACTION="ChefFrontEndSubscription.do">
        <input type="hidden" name="action" value="delete"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="hidden" name="memid" value="${id}"> 
        <input type="submit" value="取消訂閱" class="content_3" style="width: 100%;">
        </FORM>
</c:otherwise>
</c:choose>
          <FORM METHOD="post" ACTION="frontEndChef.do">
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
       </ul>
<%-- <%@ include file="page2.file" %> --%>
        </c:when>
		<c:otherwise>
		</c:otherwise>
		</c:choose>
<!-- =============================================================================================	 -->		
 <c:choose>
		<c:when test="${test == 2 && test1 == 3}">
<%-- 		<%@ include file="page1.file" %>  --%>
	<ul class="item_list">				
       <c:forEach var="chefVO" items="${list}">
		<c:choose>
		<c:when test="${chefVO.chefPrice > 10000}">
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
          
<%
session.setAttribute("sstion", 1);
%>
<c:forEach var="chefSubscriptionVO" items="${listSubscription}">
<c:choose>
<c:when test="${chefSubscriptionVO.chefid == chefVO.chefid && chefSubscriptionVO.memid == id}">
<%
session.setAttribute("sstion", 0);
%>
</c:when>
<c:otherwise>

</c:otherwise>
</c:choose>
</c:forEach>
          <c:choose>
<c:when test="${sstion == 1}">
          <FORM METHOD="post" ACTION="ChefFrontEndSubscription.do">
        <input type="hidden" name="action" value="insert"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="hidden" name="memid" value="${id}"> 
        <input type="submit" value="訂閱" class="content_3" style="width: 100%;">
        </FORM>
</c:when>
<c:otherwise>
          <FORM METHOD="post" ACTION="ChefFrontEndSubscription.do">
        <input type="hidden" name="action" value="delete"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="hidden" name="memid" value="${id}"> 
        <input type="submit" value="取消訂閱" class="content_3" style="width: 100%;">
        </FORM>
</c:otherwise>
</c:choose>
          <FORM METHOD="post" ACTION="frontEndChef.do">
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
       </ul>
<%-- <%@ include file="page2.file" %> --%>
        </c:when>
		<c:otherwise>
		</c:otherwise>
		</c:choose>
<!-- =============================================================================================	 -->		
 <c:choose>
		<c:when test="${test == 1 && test1 == 3}">
<%-- 		<%@ include file="page1.file" %>  --%>
	<ul class="item_list">				
       <c:forEach var="chefVO" items="${list}">
		<c:choose>
		<c:when test="${chefVO.chefPrice <= 10000 || chefVO.chefPrice > 20000}">
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
<%
session.setAttribute("sstion", 1);
%>
<c:forEach var="chefSubscriptionVO" items="${listSubscription}">
<c:choose>
<c:when test="${chefSubscriptionVO.chefid == chefVO.chefid && chefSubscriptionVO.memid == id}">
<%
session.setAttribute("sstion", 0);
%>
</c:when>
<c:otherwise>

</c:otherwise>
</c:choose>
</c:forEach>
          <c:choose>
<c:when test="${sstion == 1}">
          <FORM METHOD="post" ACTION="ChefFrontEndSubscription.do">
        <input type="hidden" name="action" value="insert"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="hidden" name="memid" value="${id}"> 
        <input type="submit" value="訂閱" class="content_3" style="width: 100%;">
        </FORM>
</c:when>
<c:otherwise>
          <FORM METHOD="post" ACTION="ChefFrontEndSubscription.do">
        <input type="hidden" name="action" value="delete"> 
        <input type="hidden" name="chefid" value="${chefVO.chefid}"> 
        <input type="hidden" name="memid" value="${id}"> 
        <input type="submit" value="取消訂閱" class="content_3" style="width: 100%;">
        </FORM>
</c:otherwise>
</c:choose>
          <FORM METHOD="post" ACTION="frontEndChef.do">
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
       </ul>
<%-- <%@ include file="page2.file" %> --%>
        </c:when>
		<c:otherwise>
		</c:otherwise>
		</c:choose>
<!-- =============================================================================================	 -->		

       	</main>
<script>
	window.onload=function(){
localStorage.setItem('chefName',"123");
}
<!-- </script> -->
  
<%@ include file="../tools/footer.jsp"%>
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/popper.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>

</html>