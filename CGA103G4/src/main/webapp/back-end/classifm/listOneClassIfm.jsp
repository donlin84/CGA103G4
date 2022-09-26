<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ClassIfm.model.*"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="<%=request.getContextPath()%>/back-end/classifm/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/back-end/assets/images/favicon.ico">
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/fullcalendar/vanillaCalendar.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/morris/morris.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/css/style.css" rel="stylesheet" type="text/css">
<style>
  table {
    width: 1988px;
    background-color: white;
    margin-left: 20px;
    border: 1px solid #CCCCFF;
  }
  th{
	font-weight: 700;
    display:inline-block;
  	width:110px;
    line-height: 50px;
    padding: 5px;
    text-align: center;
    background-color: rgb(87 102 216);
    color: white;
  }
  td {
  	font-weight: 500;
  	display:inline-block;
  	width:110px;
    height:200px;
    padding: 5px;
    text-align: center;
 	line-height: 180px;

  }
  tr{
  	border: 1px solid #CCCCFF;
  }
  .td_introduct{
  	display:inline-block;
  	width:210px;
    height:200px;
    padding: 1px;
    border: #CCCCFF 1px solid;
    text-align: center;
 	line-height: 20px;
 	overflow:scroll;
  }
  .update_colume2{
  	display:inline-block;
    padding-top: 80px;
    text-align: center;
 	line-height: 30px;
  }
  .td_title{ 
  	 line-height: 20px;
	 padding-top:70px;
  }
  .td_time{
  	 line-height: 20px;
	 padding-top:75px;
  	 text-align: left;
  }
  .clapic{
    width: 345px;
  }

  img{
    width: 100%;
    height:100%;
  }
  a{
	text-decoration:none
}
.claxxx{
      font-weight: 700;
      font-size: 20px;
    }
    .head{
      background-color: rgb(248, 184, 110);
      padding: 10px;
    }
    .xxx{
      padding: 10px;
    }
    .head_span{
      margin: 5px;
    }
    .head_span_phone{
    	margin: 12px;
    }
    .head_span_status{
    	margin-left:10px;
    }
    .head_memid{
      margin: 10px;
    }
    .head_memname{
      margin: 20px;
    }
    .head_gender{
      margin: 15px;
    }
    .head_phone{
      margin: 10px;
    }
    .head_people{
      margin: 15px;
    }
    .head_status{
      margin: 15px;
    }
    .modal-content{
    	width:520px;
    	padding-bottom:100px;
    }
    .bigest_div{
	overflow:scroll;
}
</style>
</head>
<body>
<%@ include file="../tools/header.jsp"%>
<br>
<br>
<br>
<br>
<br>
	<a href="<%=request.getContextPath()%>/back-end/classifm/index_ClassIfm.jsp">回首頁</a>
	這是查詢所有課程資訊
	<div class="bigest_div">
		<table>
		<tr>
			<th>課程編號</th>
			<th>課程標題</th>
			<th class="clapic">課程圖片</th>
			<th>教師name</th>
			<th>課程標籤</th>
			<th style="width:210px;">課程簡介</th>
			<th>授課時間</th>
			<th>課程價格</th>
			<th>課程人數上限</th>
			<th>課程人數下限</th>
			<th>已報名人數</th>
			<th>課程狀態</th>
			<th>報名開始時間</th>
			<th>報名結束時間</th>
			<th class="update_colume1">編輯</th>
		</tr>
		<tr>
			<td style="font-weight: 700;">${classIfmVO.claid}</td>
			<td class="td_title">${classIfmVO.claTitle}</td>
			<td class="clapic">
		        <div id="carouselExampleIndicators${abc.index}" class="carousel slide" data-bs-ride="carousel">
		          <div class="carousel-indicators">
		            <button type="button" data-bs-target="#carouselExampleIndicators${abc.index}" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
		            <button type="button" data-bs-target="#carouselExampleIndicators${abc.index}" data-bs-slide-to="1" aria-label="Slide 2"></button>
		            <button type="button" data-bs-target="#carouselExampleIndicators${abc.index}" data-bs-slide-to="2" aria-label="Slide 3"></button>
		          </div>
		          <div class="carousel-inner">
		            <div class="carousel-item active">
		              <img src="<%=request.getContextPath()%>/ClassIfmPic?id=${classIfmVO.claid}&page=0" class="d-block w-100" alt="...">
		            </div>
		            <div class="carousel-item">
		              <img src="<%=request.getContextPath()%>/ClassIfmPic?id=${classIfmVO.claid}&page=1" class="d-block w-100" alt="...">
		            </div>
		            <div class="carousel-item">
		              <img src="<%=request.getContextPath()%>/ClassIfmPic?id=${classIfmVO.claid}&page=2" class="d-block w-100" alt="...">
		            </div>
		          </div>
		          <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators${abc.index}" data-bs-slide="prev">
		            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		            <span class="visually-hidden">Previous</span>
		          </button>
		          <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators${abc.index}" data-bs-slide="next">
		            <span class="carousel-control-next-icon" aria-hidden="true"></span>
		            <span class="visually-hidden">Next</span>
		          </button>
		        </div>
			</td>
			<td>${classIfmVO.thrid} ${classIfmVO.teacherVO.thrName}</td>
			<td>${classIfmVO.claTagid} ${classIfmVO.classTagVO.claTagName}</td>
			<td class="td_introduct">${classIfmVO.claIntroduction}</td>
			<td class="td_time">${fn:replace((classIfmVO.claTime), "T", " ")}</td> 
			<td>
				<span>$</span>
				${classIfmVO.claPrice}
			</td>
			<td>${classIfmVO.claPeopleMax}</td>
			<td>${classIfmVO.claPeopleMin}</td>
			<td><a href="#" style="${(classIfmVO.claPeople<classIfmVO.claPeopleMin)?'color:red;font-weight: 700;':'color:blue;font-weight: 700;'}
			" data-bs-toggle="modal" data-bs-target="#exampleModal${classIfmVO.claid}">
			${classIfmVO.claPeople}</a></td>
			<jsp:include page="/back-end/classifm/click_people.jsp">
				<jsp:param name="claid" value="${classIfmVO.claid}" />
			</jsp:include>
			<td>
				<c:choose>
		            <c:when test="${classIfmVO.claStatus==0}">
		                下架
		            </c:when>
		            <c:when test="${classIfmVO.claStatus==1}">
		                上架
		            </c:when>
		            <c:when test="${classIfmVO.claStatus==2}">
		                已結束
		            </c:when>
		            <c:when test="${classIfmVO.claStatus==3}">
		                取消
		            </c:when>
		            <c:otherwise>
		                取消已通知
		            </c:otherwise>
		        </c:choose>
			</td>
			<td class="td_time">${fn:replace((classIfmVO.claStrTime), "T", " ")}</td>
			<td class="td_time">${fn:replace((classIfmVO.claFinTime), "T", " ")}</td>
			<td class="update_colume2">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ClassIfmServlet">
				     <input type="submit" value="修改">
				     <input type="hidden" name="claid"  value="${classIfmVO.claid}">
				     <input type="hidden" name="action"	value="forward_to_update">
			    </FORM>
			</td>
		</tr>
	</table>
	</div>
	<%@ include file="../tools/footer.jsp"%>
	<!-- End Footer -->
	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/back-end/assets/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/modernizr.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/waves.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/jquery.nicescroll.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/skycons/skycons.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/tiny-editable/mindmup-editabletable.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/tiny-editable/numeric-input-example.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/fullcalendar/vanillaCalendar.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/raphael/raphael-min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/morris/morris.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/app.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>