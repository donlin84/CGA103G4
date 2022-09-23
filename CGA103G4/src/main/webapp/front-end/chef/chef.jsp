<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>
<%
ChefService chefSvc = new ChefService();
List<ChefVO> list = chefSvc.getAll();
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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/classifm/classifm_css/classifm.css">
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
        border: 1px solid blue;
         position: absolute; 
        top: var(--header-height);
        left: 0;
/*         height: calc(100% - var(--header-height)); */
        height: 800px;
        width: var(--aside-width);
        background-color: #efefef;
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
        border: 1px solid lightblue;
        width: 100%;
        padding: 3px 10px;
      }

/* ==================== main 區域 ==================== */
      main.main{
        border: 1px solid red;
        margin-left: var(--aside-width);
        width: calc(100% - var(--aside-width));
        height: 800px;
        padding: 20px;
        background-color: hsl(34, 100%, 84%);
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
 <aside class="aside">
      <nav class="nav">


      <div class="left-side-1">

        <div class="big-left">
          <div class="left-side-2">
            <div class="left-side-2-1">
              <h3>條件篩選</h3>
              <a href="<%=request.getContextPath()%>/front-end/classifm/classifm_browse.jsp" id="clean_a">
              	<img src="<%=request.getContextPath()%>/front-end/classifm/classifm_image/clean.svg" alt="" id="broom">
              </a>
            </div>
		<form method="post" action="<%=request.getContextPath()%>/ClassIfmServlet" >
            <div>
              <div class="price_div">
                <span class="little_title">價格</span>
                <hr>
                           
                <label data-v-60c2b4db="" role="checkbox" class="el-checkbox">
                <span aria-checked="mixed" class="el-checkbox__input">
                <span class="el-checkbox__inner"></span>
                <input type="checkbox" aria-hidden="true" name="option" class="el-checkbox__original" value="1"></span>
                <span class="el-checkbox__label">$5000 以下<!----></span>
                </label>
                <label data-v-60c2b4db="" role="checkbox" class="el-checkbox">
                <span aria-checked="mixed" class="el-checkbox__input">
                <span class="el-checkbox__inner"></span>
                <input type="checkbox" aria-hidden="true" name="option" class="el-checkbox__original" value="2"></span>
                <span class="el-checkbox__label">$5000 ~ $10000<!----></span>
                </label>
                <label data-v-60c2b4db="" role="checkbox" class="el-checkbox">
                <span aria-checked="mixed" class="el-checkbox__input">
                <span class="el-checkbox__inner"></span>
                <input type="checkbox" aria-hidden="true" name="option" class="el-checkbox__original" value="2"></span>
                <span class="el-checkbox__label">$10000 ~ $15000<!----></span>
                </label>
                <label data-v-60c2b4db="" role="checkbox" class="el-checkbox">
                <span aria-checked="mixed" class="el-checkbox__input">
                <span class="el-checkbox__inner"></span>
                <input type="checkbox" aria-hidden="true" name="option" class="el-checkbox__original" value="2"></span>
                <span class="el-checkbox__label">$15000 ~ $20000<!----></span>
                </label>
                <label data-v-60c2b4db="" role="checkbox" class="el-checkbox">
                <span aria-checked="mixed" class="el-checkbox__input">
                <span class="el-checkbox__inner"></span>
                <input type="checkbox" aria-hidden="true" name="option" class="el-checkbox__original" value="1"></span>
                <span class="el-checkbox__label">$20000 以上<!----></span>
                </label>
                
                <div class="keyword_div">
                	<span>關鍵字查詢 :</span>
                	<input type="text" name="cla_keyword" value="${param.cla_keyword}" >
                </div>
              </div>
              <span class="little_title">廚師姓名</span>
              <hr>
<!--               <FORM METHOD="post" ACTION="Chef.do"> -->
												<b>選擇私廚姓名:</b> <select size="1" name="chefid">
													<c:forEach var="chefVO" items="${list}">
														<option value="${chefVO.chefid}">${chefVO.chefName}
													</c:forEach>
												</select> 
<!-- 												<input type="hidden" name="action" -->
<!-- 													value="getOne_For_Display"> <input type="submit" -->
<!-- 													value="送出"> -->
<!-- 											</FORM> -->
         
              <hr>
              
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
<%@ include file="page1.file" %> 
	<ul class="item_list">
<!--       <div class="right-side"> -->
      <c:forEach var="chefVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
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
        <div class="content_3">了解更多</div>
        </li>
        </c:forEach>
<!--         </div> -->
       </ul>
<%@ include file="page2.file" %>
       	</main>
<%--       	<c:forEach var="classifmall" items="${(cangetall==null)?(classifmall):(cangetall)}"> --%>
<%--         <a href="<%=request.getContextPath()%>/ClassIfmServlet?claid=${classifmall.claid}&action=second_page" id="right_a"> --%>
<!--           <div class="pic"> -->
<%--               <span class="clatag">${classifmall.classTagVO.claTagName}</span> --%>
<%--               <img class="img1" src="<%=request.getContextPath()%>/ClassIfmPic?id=${classifmall.claid}&page=0" alt=""> --%>
<!--               <div class="clafintime_div">課程報名截止時間 :  -->
<%--                   <span class="clafintime">${fn:replace((classifmall.claFinTime), "T", " ")}</span> --%>
<!--               </div> -->
<!--           </div> -->
<!--           <div class="content"> -->
<!--               <div class="clapeople"> -->
<%--                   <span class="cla_people_now">${classifmall.claPeople}</span><span>/</span><span class="cla_people_max">${classifmall.claPeopleMax}</span> --%>
<!--                   <span class="peopleimg"> -->
<%--                       <img class="img2" src="<%=request.getContextPath()%>/front-end/classifm/classifm_image/user.svg" alt=""> --%>
<!--                   </span> -->
<!--               </div> -->
  
<!--               <div class="content_1"> -->
<%--                   <h3 class="cla_title">${classifmall.claTitle}</h3> --%>
<%--                   <span class="teacher_name">${classifmall.teacherVO.thrName}</span> --%>
<%--                   <p class="cla_text">${classifmall.claIntroduction}</p> --%>
<!--               </div> -->
<!--               <br> -->
<!--               <hr> -->
  
<!--               <div class="content_2"> -->
<!--                   <div class="price"> -->
<!--                       <h5 class="cla_price1">課程價格</h5> -->
<!--                       <div class="cla_price2">定價:</div> -->
<!--                       <span class="cla_price3">$</span> -->
<%--                       <span class="cla_price4">${classifmall.claPrice}</span> --%>
<!--                   </div> -->
<!--                   <div class="class_time"> -->
<!--                       <h5 class="clatime1">課程時間</h5> -->
<%--                       <span class="clatime2">${fn:replace((classifmall.claTime), "T", " ")}</span> --%>
<!--                   </div> -->
<!--               </div> -->
  
<!--               <div class="content_3">了解更多</div> -->
<!--           </div> -->
<!--         </a> -->
<%--         </c:forEach> --%>


		

<!--       <div class="clear"></div> -->
<!--     </div> -->
<!--   </div> -->
  
<%@ include file="../tools/footer.jsp"%>
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/popper.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>

</html>