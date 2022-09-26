<%@ page import="com.teacher.model.*"%>
<%@ page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  TeacherVO teacherVO = (TeacherVO) request.getAttribute("teacherVO"); 
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
		<h3>查詢單筆教師資料</h3>
		<h4><a href="select_page.jsp">回查詢首頁</a></h4>
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

	
		
	  

	    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/teacher/teacher.do" >
	        <b>輸入教師ID (如1):</b>
	        <input type="text" name="thrid">
	        <input type="hidden" name="action" value="getOne_For_Display">
	        <input type="submit" value="送出">
	    </FORM>

	
	  <jsp:useBean id="teacherSvc" scope="page" class="com.teacher.model.TeacherService" />
	   

	     <FORM id=1 METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/teacher/teacher.do" >
	       <b>選擇教師編號:</b>
	       <select size="1" name="thrid">
	         <c:forEach var="teacherVO" items="${teacherSvc.all}" > 
	          <option value="${teacherVO.thrid}">${teacherVO.thrid}
	         </c:forEach>   
	       </select>
	       <input type="hidden" name="action" value="getOne_For_Display">
	       <input type="submit" value="送出">
	    </FORM>
		<br>
		<br>
		
	<table>
		<tr>
			<th>教師ID</th>
			<th>教師姓名</th>
			<th>教師性別</th>
			<th>教師電話</th>
			<th>教師信箱</th>
			<th>教師狀態</th>
			<th style="width:210px;">教師簡介</th>
			<th>教師總星數</th>
			<th>評價總數</th>
			<th style="width:200px;">教師個人照</th>
		</tr>
		<c:if test="${teacherVO!=null}">
		<tr>
			<td>${teacherVO.thrid}</td>
			<td>${teacherVO.thrName}</td>
			<td>${teacherVO.thrGender}</td>
			<td>${teacherVO.thrPhone}</td>
			<td>${teacherVO.thrEmail}</td>
			<td>${teacherVO.thrStatus}</td>
			<td class="td_introduct">${teacherVO.thrIntroduction}</td>
			<td id = "star">${teacherVO.thrComment}</td>
			<td>${teacherVO.thrCmnumber}</td>
	<%-- 		<td>${teacherVO.thrPic}</td> --%>
			<td class="picture"><img class="teacherImg"  src= "data:image/jpg;base64,${thrpic}"> </td>
		</tr>
		</c:if>
	</table>
	<br>	
	<br>
   

      </div>

    </div>

  </div>

<%@ include file="../tools/footer.jsp"%>

	



	<script>
		
	window.onload = function(){
			let star = document.getElementById("star");
			starnumber = star.innerHTML ;
			console.log("I");
			if (starnumber!== ""){
				staroutput = "";
				nullstaroutput =""; 
				if (staroutput==5){
					staroutput="💖💖💖💖💖";
				}else{
					for(let i=0 ;i<starnumber;i++){
					staroutput += "💖";
					}
					for(let i=0 ;i<5-starnumber;i++){
					nullstaroutput += "🤍";
					}
				}
				
			}	
			star.innerHTML = staroutput + nullstaroutput

	}
		
	</script>

</body>

</html>

