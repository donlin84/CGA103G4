<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>
<%@ page import="com.chefskills.model.*"%>
<%@ page import="com.chefskillstype.model.*"%>
<%
ChefService chefSvc = new ChefService();
List<ChefVO> list2 = chefSvc.getAll();
pageContext.setAttribute("list2", list2);
%>
<%
ChefSkillsService chefSkillsSvc = new ChefSkillsService();
List<ChefSkillsVO> list = chefSkillsSvc.getAll();
pageContext.setAttribute("list", list);
%>
<%
ChefSkillsTypeService chefSkillsTypeSvc = new ChefSkillsTypeService();
List<ChefSkillsTypeVO> listtype = chefSkillsTypeSvc.getAll();
pageContext.setAttribute("listtype", listtype);
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
        border: 1px solid blue;
         position: absolute; 
        top: var(--header-height);
        left: 0;
/*         height: calc(100% - var(--header-height)); */
        height: 800px;
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
      
#tribute_heading {
  width: 100%;
  text-align: center;
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
           
              
		<form method="post" action="frontEndChef.do" >
		<br>
		<a class="content_3" href='../chef/chef.jsp' style="width: 200px;">
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		重&nbsp&nbsp新&nbsp&nbsp查&nbsp&nbsp詢  
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</a>

             <div class="price_div">
                <span class="little_title">預約數 : ${chefVO.reserve}</span>
               </div>
             <div class="price_div">
                <span class="little_title">評價人數 : ${chefVO.com}</span>
               </div>
             <div class="price_div">
                <span class="little_title">評價分數 : ${chefVO.gomg}</span>
               </div>
             <div class="price_div">
                <span class="little_title">價格 : ${chefVO.chefPrice}</span>
               </div>

<br>
              
              

		</form>
		
<!--             <br> -->
<!--             <br> -->
<!--             <br> -->
   
<!-- 		<br> -->
<!-- 		<br> -->
          </div>
                     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/chefAppointment/chefapp.do">  
            <input type="hidden" name="action" value="getchefid">
            <input type="hidden" name="chefid" value="${chefVO.chefid}">
            <input type="submit" value="預約" id="onload_submit">
           </FORM>
        </div>
        </div>
      </div>
       </nav>
    </aside>
<!-- =================main========================================================================-->
      	<main class="main">
<div class = "container">
  <div id = "tribute_heading">
    <h1>${chefVO.chefName}</h1>
    <p class = "italicize">${chefVO.chefNickname}</p>
  </div>
      <p>${chefVO.chefIntroduction}</p>
    <div class = "row">
    <img class = "col-md-4" src="<%=request.getContextPath()%>/showChefPhotoPicture?chefid=${chefVO.chefid}">

      <div class = "col-md-7">
      <p>專長 :</p>  
<!-- ====================================================================================================================================       -->

					<c:forEach var="chefSkillsVO" items="${list}">
<%-- 						      ${chefVO.chefid} --%>
<%-- 						      ${chefVO.chefid == chefSkillsVO.chefid} --%>
<%-- 						     ${chefSkillsVO.chefid} --%>
						<c:choose>
							<c:when test="${chefVO.chefid == chefSkillsVO.chefid}">
								<tr>
									<td><c:forEach var="chefSkillsTypeVO" items="${listtype}">
											<c:choose>
												<c:when
													test="${chefSkillsVO.skillid == chefSkillsTypeVO.skillid}"> 
    							  ${chefSkillsTypeVO.skill}
    							  <br>
    							  </c:when>
												<c:otherwise>
												</c:otherwise>
											</c:choose>
										</c:forEach></td>

 								</tr> 
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>

					</c:forEach>
					<!-- ====================================================================================================================================       -->
   <br>
   <br>

      <p>證照 : </p>
      <img src="<%=request.getContextPath()%>/showLicensePicture?chefid=${chefVO.chefid}" style="width:200px;">
      </div>
  </div>
  </div>


       	</main>
<script>
window.onload=function(){
localStorage.setItem('chefid',${chefVO.chefid});
}
</script>
  
<%@ include file="../tools/footer.jsp"%>
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/popper.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>

</html>