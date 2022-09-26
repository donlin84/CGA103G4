<%@ page import="com.teacher.model.*"%>
<%@ page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  TeacherVO teacherVO = (TeacherVO) request.getAttribute("teacherVO"); 
  TeacherService teacherSvc = new TeacherService();
  request.setAttribute("teacherSvc", teacherSvc);
%>
<!DOCTYPE html>
<html lang="zh-Hant" dir="ltr">

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
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/announce.css">
<script src="<%=request.getContextPath()%>/front-end/js/announce.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/nav.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/nav.js"></script>

	<style>
	body{
		  background-image: url('../../images/picture_15/background.jpg');
		}
	.picture{
		width:150px;
		height:150px;
	}
		table {
	    width: 1400px;
	    background-color: white;
	/*     margin-left: 100px; */
	    margin-top:-6px;
	    border: 1px solid #CCCCFF;
	  }
	  th{
	 font-weight: 700;
	    display:inline-block;
	   width:110px;
	    line-height: 50px;
	    padding: 5px;
	    text-align: center;
	    background-color: rgb(49, 201, 44);
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
	   border: 1px solid white;
	  }
	  .td_introduct{
	   display:inline-block;
	   width:220px;
	    height:200px;
	    padding: 1px;
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
	  .teacherImg{
	    width: 100%;
	    height:100%;
	  }
	  #broom{
	  position: relative;
	  top: -10px;
	  left: 120px;
	  width: 40px;
	  height: 40px;
	}
	.select_bar{
	 top:110px;
	 left:12px;
	 position: relative;
	 background-color: rgb(248, 184, 110);
	 border-radius: 10px;
	 width:180px;
	}
	.select_span{
	 position: relative;
	 left:10px;
	 display:block;
	 font-size:20px;
	 letter-spacing: 2px;
	 color:white;
	}
	    .head{
	      background-color: rgb(248, 184, 110);
	      padding: 10px;
	      color:white;
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
	 padding-bottom:100px;
	}
	a{
	 text-decoration: none;
	}
	
	
	</style> 
</head>

<body>
	 <script src="../js/course.js"></script>
  	 <script src="../js/nav.js"></script>
  	
<%@ include file="../tools/header.jsp"%>
					
<div class="main">

    <div class="container -on" id="shopPage">

      <div id="shopPageContent">
		<h1>教師資料查詢</h1>
		<h3>請輸入搜尋條件</h3>
<!-- 		<h4><a href="listOneTeacher.jsp">查單筆資料</a></h4> -->
		<h4><a href="listAllTeacher.jsp">列出所有資料</a></h4>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
		    <c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="teacher.do">
		<label for = "thrid">請選擇教師編號</label>
		<select size="1" name="thrid" class="dis-select" >
			<option value="">請選擇編號 
		    <c:forEach var="teacherVO" items="${teacherSvc.all}" > 
			    <option value="${teacherVO.thrid}">${teacherVO.thrid}
        	</c:forEach>   
		</select>
		
		<label for = "thrGender">請選擇教師性別</label>
		<select id="thrGender" size="1" name="thrGender" class="dis-select" >
			<option value="">請選擇性別
			<option value="男">男
			<option value="女">女
			
		</select>
		
		<label for = "thrStatus">請選擇教師狀態</label>

		<select id="thrStatus" size="1" name="thrStatus" class="dis-select" >
			<option value="">請選擇狀態
			<option value="0">在職
			<option value="1">離職
		</select>
	
		<br>
		<br>	
	
		<label for = "thrName">請輸入教師姓名</label>
		<input id="thrName" type="text" name="thrName" placeholder="請輸入姓名"> 					
		
		<label for = "thrIntroduction">請輸入教師簡介關鍵字</label>
		
		<input id="thrIntroduction" type="text" name="thrIntroduction" placeholder="請輸入簡介關鍵字"> 					
		<br>
		<br>		
				
		  													
		
		<label for = "thrComment">請選擇教師評分(高於幾顆星)</label>

		<select id="thrComment" size="1" name="thrComment" class="dis-select" >
			<option value="">請選擇評分
			<option value="1">1
			<option value="2">2
			<option value="3">3
			<option value="4">4
			<option value="5">5
				</select>
			
		
			<label for = "thrCmnumber">請選擇教師總評價數人數(高於多少人)</label>

		<select id="thrCmnumber" size="1" name="thrCmnumber" class="dis-select" >
			<option value="">請選擇總評價數人數
			<option value="10">10
			<option value="50">50
			<option value="100">100
			<option value="150">150
			<option value="200">200
				</select>
		<br>
		<br>
		
		<input type="hidden" name="action" value="getsome_For_condiction"> 
		<input type="submit" class="mybtn" value="搜尋">
		
		<br>
		<br>
		<br>
		<br>
		
	</FORM>	
		
   

      </div>

    </div>

  </div>

<%@ include file="../tools/footer.jsp"%>

	





</body>

</html>

