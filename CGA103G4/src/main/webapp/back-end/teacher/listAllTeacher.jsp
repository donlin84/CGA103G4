<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.teacher.model.*"%>



<%
    TeacherService teacherSvc = new TeacherService();
    List<TeacherVO> list = teacherSvc.getAll();
    request.setAttribute("list", list);
//     pageContext.setAttribute("list",list);
%>


<%-- <c:set var="show" scope="page" value="${show}"/> --%>
<c:if test = "${show!=0}" > 
	<jsp:forward page="teacher.do">
	    <jsp:param name="action" value = "showpicture"/>
	    <jsp:param name="show" value = "0"/>	    
	       
	</jsp:forward>
</c:if> 

<% HashMap<Integer,String> map = (HashMap<Integer,String>) request.getAttribute("thrPicMap"); %>  


<html>
<head>
<title>所有教師資料 - listAllTeacher.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  

</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>"所有員工資料 - listAllTeacher.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>教師ID</th>
		<th>教師姓名</th>
		<th>教師性別</th>
		<th>教師電話</th>
		<th>教師信箱</th>
		<th>教師狀態</th>
		<th>教師簡介</th>
		<th>教師總星數</th>
		<th>教師評價總人數</th>
		<th>教師個人照</th>
		<th>教師圖片預覽</th>
		<th>操作</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="teacherVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
		<tr>
			<FORM METHOD="post" enctype= "multipart/form-data" ACTION="<%=request.getContextPath()%>/back-end/teacher/teacher.do" style="margin-bottom: 0px;">
			<td><input type="TEXT" name="thrid"  value=${teacherVO.thrid} style="background-color: #EFEFEF;color: #919191"  readonly ></td>
			<td><input type="TEXT" name="thrName"  value=${teacherVO.thrName}></td>
			<td>
			<select  name="thrGender">
    			<option value=${teacherVO.thrGender} selected>${teacherVO.thrGender}</option>
    			<option value= "男">男</option>
    			<option value= "女">女</option>
			</select>
			</td>
			<td><input type="TEXT" name="thrPhone"  value=${teacherVO.thrPhone}></td>
			<td><input type="email" name="thrEmail"  value=${teacherVO.thrEmail}></td>
			<td>			
				<select  name="thrStatus">
	    			<option value=${teacherVO.thrStatus} selected>${teacherVO.thrStatus==1?"離職":"在職"}</option>
	    			<option value= 0>在職</option>
	    			<option value= 1>離職</option>
				</select>
			</td>
			
			<td><input type="TEXT" name="thrIntroduction"  value=${teacherVO.thrIntroduction}></td>
			<td><input type="TEXT" name="thrComment"     style="background-color: #EFEFEF;color: #919191" readonly value=${teacherVO.thrComment}  ></td>
			<td><input type="TEXT" name="thrCmnumber"   style="background-color: #EFEFEF;color: #919191"  readonly value=${teacherVO.thrCmnumber}   ></td>
			
			<td>
			<input type="file" name="picio" id="p_file${teacherVO.thrid}" onchange="change(${teacherVO.thrid})" accept="image/jpeg" value="" >
			<input id="post_file${teacherVO.thrid}" type="hidden" name="thrPic" value="">
			</td>
			<td>
			<img id = "preImg${teacherVO.thrid}" accept="image/jpeg" src= "data:image/jpg;base64,${thrPicMap[teacherVO.thrid]}" width="200px" height="200px">
			</td>
		
			<td>
			  
			     <input type="submit" value="修改">
			     <input type="hidden" name="action"	value="update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/teacher/teacher.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="thrid"  value="${teacherVO.thrid}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

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
    
	
</script>

</body>
</html>