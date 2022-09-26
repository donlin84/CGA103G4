<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.teacher.model.*"%>

<%
  TeacherVO teacherVO = (TeacherVO) request.getAttribute("teacherVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

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
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>教師資料</title>

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
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/bootstrap-rating/bootstrap-rating.css" rel="stylesheet" type="text/css">


<style>

 
  input{width:110px;}

 .my_textarea{
	margin-top:-10px;
	width:200px;
	height:200px;
	} 
</style>
</head>

<body>

	<div id="preloader">
		<div id="status">
			<div class="spinner"></div>
		</div>
	</div>
	<!-- Navigation Bar-->
	<%@ include file="../tools/header.jsp"%>

	<div class="wrapper">
		<div class="container-fluid">
			<!-- Page-Title -->
			<div class="row">
				<div class="col-sm-12">
					<div class="page-title-box">
						<div class="btn-group pull-right">
							<ol class="breadcrumb hide-phone p-0 m-0">
								<li class="breadcrumb-item active">所有教師資料</li>
								<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/teacher/select_page.jsp">教師管理</a></li>
								<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						
						
						<h5><a href="select_page.jsp">教師管理首頁</a></h5>
						<c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color:red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
					</div>
				</div>
			</div>


			<div class="row">
				<div class="col-13">
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 header-title">所有教師資料</h4>
							<table class="table" id="my-table">
								<thead>
									<tr>
										<th>教師ID</th>
										<th>教師姓名</th>
										<th>教師性別</th>
										<th>教師電話</th>
										<th>教師信箱</th>
										<th>教師狀態</th>
										<th>教師簡介</th>
										<th>教師總星數</th>
										<th>評價總數</th>
										<th>教師個人照</th>
										<th>教師圖片預覽</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<%@ include file="page1.file" %> 
								<c:forEach var="teacherVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr>
										<FORM METHOD="post" enctype= "multipart/form-data" ACTION="<%=request.getContextPath()%>/back-end/teacher/teacher.do" style="margin-bottom: 0px;">
										<td>
										<div style="width:50px;">
										<input type="hidden" name="thrid"  value=${teacherVO.thrid} style="background-color: #EFEFEF;color: #919191"  readonly >${teacherVO.thrid}
										</div>
										</td>
										<td>
										<div style="width:60px;">
										<input type="TEXT" name="thrName" style="width:50px;" value=${teacherVO.thrName}>
										</div>
										</td>
										<td>
										<div style="width:60px;">
										<select name="thrGender">
							    			<option value=${teacherVO.thrGender} selected>${teacherVO.thrGender}</option>
											<c:choose>
											
											   <c:when test='${"男"==teacherVO.thrGender}'>   
												   <option value= "女">女</option>
											   </c:when>
											   
											   <c:otherwise>  
													<option value= "男">男</option>
											   </c:otherwise>
											  
											</c:choose>
							    				
										</select>
										</div>
										</td>
										<td><div style="width:90px;"><input type="TEXT" style="width:90px;" name="thrPhone"  value=${teacherVO.thrPhone}></div></td>
										<td><input type="email" name="thrEmail"  value=${teacherVO.thrEmail}></td>
										<td>	
											<div style="width:60px;">		
											<select  name="thrStatus">
								    			<option value=${teacherVO.thrStatus} selected>${teacherVO.thrStatus==1?"離職":"在職"}</option>
												<c:choose>
												
												   <c:when test='${ 0 ==teacherVO.thrStatus}'>   
													   	<option value= 1>離職</option>
												   </c:when>
												  
												   <c:otherwise>  
														<option value= 0>在職</option>
												   </c:otherwise>
												  
												</c:choose>								    			
								    			
											</select>
											</div>
										</td>
										<td class="td_introduct"><textarea id="story" name="thrIntroduction" rows="15" cols="22" class="my_textarea">${teacherVO.thrIntroduction}</textarea></td>
							<!-- 			<td class="td_introduct"><input type="TEXT" name="thrIntroduction"  value=${teacherVO.thrIntroduction}></td> -->
										<td><input type="hidden" name="thrComment"     style="background-color: #EFEFEF;color: #919191" readonly value="${teacherVO.thrComment} " >
                                   		 <div class="p-4 text-center" style="margin:0;padding:0;">
                                     		<input type="hidden" style="margin:0;padding:0;"
                                            class="rating-tooltip" data-filled="mdi mdi-star font-32 text-primary"
                                            data-empty="mdi mdi-star-outline font-32 text-muted" data-readonly
                                            value="${teacherVO.thrComment}" />
                                   			 </div>
                              			  </td>
                              			  
                              			  
										<td>
										<div style="width:60px;">
										<input type="hidden" name="thrCmnumber"   style="background-color: #EFEFEF;color: #919191"  readonly value=${teacherVO.thrCmnumber}   >${teacherVO.thrCmnumber}</td>
										</div>
										<td>
										<input type="file" name="picio" id="p_file${teacherVO.thrid}" onchange="change(${teacherVO.thrid})" accept="image/jpeg" value="" >
										<input id="post_file${teacherVO.thrid}" type="hidden" name="thrPic" value="">
										</td>
										<td class="picture">
										<img id = "preImg${teacherVO.thrid}" accept="image/jpeg" src= "data:image/jpg;base64,${thrPicMap[teacherVO.thrid]}" width="150px" height="150px">
										</td>
										<td>
											<div style="width:50px;">
											    <input type="submit" value="修改" style="width:50px">
											    <input type="hidden" name="action"	value="update">
										    </div>
										    </FORM>
										</td>
									</tr>
								</c:forEach>	
								</tbody>
							</table>
							<%@ include file="page2.file" %>
						</div>
					</div>			            
                                
					<!-- end col -->
				</div>
				<!-- end row -->
			</div>

		</div>
		<!-- end container -->
	</div>
	<!-- end wrapper -->



	<!-- Footer -->
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
	<script src="<%=request.getContextPath()%>/back-end/assets/pages/rating-init.js"></script><!-- App js -->
    <script src="<%=request.getContextPath()%>/back-end/assets/plugins/bootstrap-rating/bootstrap-rating.min.js"></script>
	
	
	
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