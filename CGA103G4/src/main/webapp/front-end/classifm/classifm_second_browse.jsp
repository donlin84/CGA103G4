<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>《See Food》官方網站</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/common/all.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/common/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/common/footer.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/common/main.css">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="../assets/css/style.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/front-end/assets/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/assets/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/assets/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/nav.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/classifm/classifm_css/classifm_second_browse.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<%@ include file="../tools/header.jsp"%>
    <div class="top_div">
        <img src="<%=request.getContextPath()%>/ClassIfmPic?id=${classifmvo.claid}&page=0" alt="" class="top_image">
    </div>
    <div class="float_content">
        <span class="clatag">${classifmvo.classTagVO.claTagName}</span>
        <div class="clatitle">${classifmvo.claTitle}</div>
        <div class="bottom_div">
            <div class="clatime">
                <span>上課時間 :</span>
                <span>${fn:replace((classifmvo.claTime), "T", " ")}</span>
            </div>
            <div class="clapeople">
                <span>目前人數</span>
                <span >${classifmvo.claPeople}</span><span>/</span><span >${classifmvo.claPeopleMax}</span>
                  <span class="user_span">
                      <img class="user" src="<%=request.getContextPath()%>/front-end/classifm/classifm_image/user.svg" alt="">
                  </span>
            </div>
            <div class="claprice">
                <span>課程價格 :</span>
                <span>$</span>
                <span>${classifmvo.claPrice}</span>
            </div>
        </div>
    </div>
    <div class="big_content">
        <div class="left-side">
            <div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel">
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <img src="<%=request.getContextPath()%>/ClassIfmPic?id=${classifmvo.claid}&page=0" class="d-block w-100" alt="..." style="height: 400px;">
                  </div>
                  <div class="carousel-item">
                    <img src="<%=request.getContextPath()%>/ClassIfmPic?id=${classifmvo.claid}&page=1" class="d-block w-100" alt="..." style="height: 400px;">
                  </div>
                  <div class="carousel-item">
                    <img src="<%=request.getContextPath()%>/ClassIfmPic?id=${classifmvo.claid}&page=2" class="d-block w-100" alt="..." style="height: 400px;">
                  </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Next</span>
                </button>
            </div>
              <br>
              <h2 class="pic_title">${classifmvo.claTitle}</h2>
              <br>
              <div class="claIntroduction">${classifmvo.claIntroduction}</div>
        </div>
        <form action="<%=request.getContextPath()%>/front-end/registtrationform/RegisttrationForm.jsp" class="right-side">
            <h2 class="form_title" style="font-size:30px;">${classifmvo.claTitle}</h2>
            <div class="form_time">
                <span>上課日期 :</span><span>${fn:replace((classifmvo.claTime), "T", " ")}</span>
            </div>
            <div class="form_people">
                <span>人數 :</span>
                <select name="report_poeple" id="" class="form_select">
                	<c:forEach var="i" begin="1" end="${(classifmvo.claPeopleMax)-(classifmvo.claPeople)}">
                    	<option value=${i}>${i}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form_price">
                <span>課程價格 : $</span><span>${classifmvo.claPrice}</span>
            </div>
            <div class="zzz">
                <input type="hidden" name="claid" value="${classifmvo.claid}">
                <input type="submit" value=${(classifmvo.claPeople==classifmvo.claPeopleMax)?'人數已額滿':"我要報名"} class="form_btn" ${(classifmvo.claPeople==classifmvo.claPeopleMax)?'disabled':""}>
            </div>
        </form>
    </div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
    <%@ include file="../tools/footer.jsp"%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>