<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.teacher.model.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    TeacherService teacherSvc = new TeacherService();
    List<TeacherVO> list = teacherSvc.getAll();
    request.setAttribute("list", list);
%>

<c:if test = "${show!=0}" > 
	<jsp:forward page="teacher.do">
	    <jsp:param name="action" value = "showpicture"/>
	    <jsp:param name="show" value = "0"/>	    
	       
	</jsp:forward>
</c:if> 

<% HashMap<Integer,String> map = (HashMap<Integer,String>) request.getAttribute("thrPicMap"); %>  

<!DOCTYPE html>
<html lang="zh-Hant" dir="ltr">


<p style="background-image: url('../../images/picture_15/background.jpg');">
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

<link href="<%=request.getContextPath()%>/front-end/member/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/front-end/member/assets/css/icons.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/front-end/assets/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/assets/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/assets/js/bootstrap.min.js"></script>

<style>

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
    transform:translateX(-160px);
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
      	<br>
      	<br>
      	<br>
		<h3>所有教師資料 </h3>
		<h4><a href="select_page.jsp">回查詢首頁</a></h4>
<!-- 		<h4><a href="listOneTeacher.jsp">查單筆資料</a></h4> -->
		
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
			<%@ include file="page1.file" %> 
			<br>	
			<br>			
		
			<c:forEach var="teacherVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				
				<tr>
					<td>${teacherVO.thrid}</td>
					<td>${teacherVO.thrName}</td>
					<td>${teacherVO.thrGender}</td>
					<td>${teacherVO.thrPhone}</td>
					<td>${teacherVO.thrEmail}</td>
					<td>${teacherVO.thrStatus==1?"離職":"在職"}</td>
					
					<td class="td_introduct">${teacherVO.thrIntroduction}</td>
					<td id = "star">${teacherVO.thrComment}</td>
					<td>${teacherVO.thrCmnumber}</td>
					<td class="picture"><img class="teacherImg" id = "preImg${teacherVO.thrid}" accept="image/jpeg" src= "data:image/jpg;base64,${thrPicMap[teacherVO.thrid]}">
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file" %>
		<br>	
		<br>
   

      </div>

    </div>

  </div>

<%@ include file="../tools/footer.jsp"%>

<script>

	var reader = new FileReader();
	<c:forEach var="teacherVO" items="${list}">
		var preImg${teacherVO.thrid} = document.querySelector('#preImg'+"${teacherVO.thrid}");
		var p_file${teacherVO.thrid} = document.querySelector('#p_file'+"${teacherVO.thrid}");
		var post_file${teacherVO.thrid} = document.querySelector('#post_file'+"${teacherVO.thrid}");	
		var reader${teacherVO.thrid} = new FileReader();
	</c:forEach>
	
	let a =null;
	function change(i){
		reader.readAsDataURL(eval("p_file"+i).files[0]);
// 		eval("preImg"+i).src = reader.result;
		a=i;
	}
    // 監聽讀取檔案並預覽圖片
    reader.addEventListener("load", function () {  
      eval("preImg"+a).src = reader.result;
// 	  eval("post_file"+a).value = (reader.result).substring(23);
	  eval("post_file"+a).value = (reader.result);
      
    });	
    
	
	window.onload = function (){
		let star = document.querySelectorAll("#star")
		
		for (a of star){
			starnumber = a.innerHTML ;
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
			a.innerHTML = staroutput + nullstaroutput;
			
		}
		
			
	}
		
	
</script>	





</body>

</html>

