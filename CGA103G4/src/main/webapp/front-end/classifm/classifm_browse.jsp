<%@page import="com.ClassIfm.model.ClassIfmVO"%>
<%@page import="com.ClassIfm.model.ClassIfmService"%>
<%@page import="com.ClassTag.model.ClassTagVO"%>
<%@page import="com.ClassTag.model.ClassTagService"%>
<%@page import="com.teacher.model.TeacherVO"%>
<%@page import="java.util.List"%>
<%@page import="com.teacher.model.TeacherService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	TeacherService teacherSrv = new TeacherService();
	List<TeacherVO> list=teacherSrv.getAll();
	pageContext.setAttribute("teacherall",list);
	
	ClassTagService classSrv = new ClassTagService();
	List<ClassTagVO> list2=classSrv.getAll();
	pageContext.setAttribute("classtagall",list2);
	
	ClassIfmService classSrv1 = new ClassIfmService(); 
	List<ClassIfmVO> list3=classSrv1.front_getall();
	pageContext.setAttribute("classifmall",list3);
	
%>
<html>
<head>
<meta charset="UTF-8">
<title>瀏覽頁面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/classifm/classifm_css/classifm.css">
</head>
<body>
	瀏覽頁面
	<div class="main">
    <div class="title">
      <h3>課程</h3>
      <div>Class</div>
    </div>
    <div id="big-content">
      <div class="left-side-1">
        <div class="loge-area">
          <img src="<%=request.getContextPath()%>/front-end/classifm/classifm_image/SeeFoodLogo.png" alt="">
        </div>
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
                <span class="little_title">搜尋價格範圍</span>
                <hr>
                <div class="price_div">
                  <span class="price_span_1">min :</span>
                  <input type="text" name="claprice_min" value="${(claprice_min==null)?0:(claprice_min)}" class="price_input">
                  <span class="price_span_2">max :</span>
                  <input type="text" name="claprice_max" value="${(claprice_max==null)?5000:(claprice_max)}" class="price_input">
                </div>
                <div class="keyword_div">
                	<span>關鍵字查詢 :</span>
                	<input type="text" name="cla_keyword" value="${param.cla_keyword}" >
                </div>
              </div>
              <span class="little_title">課程教師</span>
              <hr>
              <c:forEach var="teacherall" items="${teacherall}">
              <div class="checkbox">
                <input type="checkbox" name="teacher" id="${teacherall.thrName}" value="${teacherall.thrid}" ${fn:contains(thr,teacherall.thrid)?'checked':''} class="aaa">
                <label for="${teacherall.thrName}" class="bbb">${teacherall.thrName}</label>
              </div>
              </c:forEach>
              <span class="little_title">課程分類</span>
              <hr>
              <c:forEach var="classtagall" items="${classtagall}">
              <div class="checkbox">
                <input type="checkbox" name="clatag" id="${classtagall.claTagName}" value="${classtagall.claTagid}" ${fn:contains(tag,classtagall.claTagid)?'checked':''} class="aaa">
                <label for="${classtagall.claTagName}" class="bbb">${classtagall.claTagName}</label>
              </div>
              </c:forEach>
            </div>
            <input type="hidden" name="action" value="browse">
            <input type="submit" value="篩選條件" id="onload_submit">
		</form>
          </div>
        </div>
      </div>
      <div class="right-side">
      	<c:forEach var="classifmall" items="${(cangetall==null)?(classifmall):(cangetall)}">
        <a href="<%=request.getContextPath()%>/ClassIfmServlet?claid=${classifmall.claid}&action=second_page" id="right_a">
          <div class="pic">
              <span class="clatag">${classifmall.classTagVO.claTagName}</span>
              <img class="img1" src="<%=request.getContextPath()%>/ClassIfmPic?id=${classifmall.claid}&page=0" alt="">
              <div class="clafintime_div">課程報名截止時間 : 
                  <span class="clafintime">${fn:replace((classifmall.claFinTime), "T", " ")}</span>
              </div>
          </div>
          <div class="content">
              <div class="clapeople">
                  <span class="cla_people_now">${classifmall.claPeople}</span><span>/</span><span class="cla_people_max">${classifmall.claPeopleMax}</span>
                  <span class="peopleimg">
                      <img class="img2" src="<%=request.getContextPath()%>/front-end/classifm/classifm_image/user.svg" alt="">
                  </span>
              </div>
  
              <div class="content_1">
                  <h3 class="cla_title">${classifmall.claTitle}</h3>
                  <span class="teacher_name">${classifmall.teacherVO.thrName}</span>
                  <p class="cla_text">${classifmall.claIntroduction}</p>
              </div>
              <br>
              <hr>
  
              <div class="content_2">
                  <div class="price">
                      <h5 class="cla_price1">課程價格</h5>
                      <div class="cla_price2">定價:</div>
                      <span class="cla_price3">$</span>
                      <span class="cla_price4">${classifmall.claPrice}</span>
                  </div>
                  <div class="class_time">
                      <h5 class="clatime1">課程時間</h5>
                      <span class="clatime2">${fn:replace((classifmall.claTime), "T", " ")}</span>
                  </div>
              </div>
  
              <div class="content_3">了解更多</div>
          </div>
        </a>
        </c:forEach>
      </div>
      <div class="clear"></div>
    </div>
  </div>
</body>
</html>