<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.teacher.model.*"%>
<%@ page import="java.util.*"%>


<%
  TeacherVO teacherVO = (TeacherVO) request.getAttribute("teacherVO");
%>

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
<link rel="shortcut icon" href="../assets/images/favicon.ico">
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/fullcalendar/vanillaCalendar.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/morris/morris.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/css/style.css" rel="stylesheet" type="text/css">

<style>
 .td_introduct{
   display:inline-block;
    padding: 1px;
    text-align: center;
  line-height: 20px;
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
								<li class="breadcrumb-item active">教師資料新增</li>
								<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/teacher/select_page.jsp">教師管理</a></li>
								<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
						<h5><a href="select_page.jsp">教師管理首頁</a></h5>
						<%-- 錯誤表列 --%>
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
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 header-title">教師資料新增</h4>
							<FORM METHOD="post"   ACTION="teacher.do" name="form1" enctype= "multipart/form-data">
							<table class="table" id="my-table">
								<thead>
									<tr>
										<th>教師姓名</th>
										<th>教師性別</th>
										<th>教師電話</th>
										<th>教師信箱</th>
										<th>教師狀態</th>
										<th>教師簡介</th>
										<th>教師個人照</th>
										<th>教師圖片預覽</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<tr>
									<td><input type="TEXT" name="thrName"  value=<%= (teacherVO==null)? "無名": teacherVO.getThrName() %> ></td>
										
										<td>
										<select  name="thrGender">
									  			<option value=<%= (teacherVO==null)? "男": teacherVO.getThrGender() %> selected><%= (teacherVO==null)? "男": teacherVO.getThrGender() %></option>
									  			<option value= "男">男</option>
									  			<option value= "女">女</option>
										</select>
										</td>
										<td><input type="TEXT" name="thrPhone"  value=<%= (teacherVO==null)? "0912345678": teacherVO.getThrPhone() %> ></td>
										<td><input type="email" name="thrEmail"  value= <%= (teacherVO==null)? "test@gmail.com": teacherVO.getThrEmail() %> ></td>
										<td>			
											<select  name="thrStatus">
									   			<option value= <%= (teacherVO==null)? 0:  teacherVO.getThrStatus() %> selected><%= (teacherVO==null)? "在職": (teacherVO.getThrStatus()==0? "在職":"離職")  %></option>
									   			<option value= 0>在職</option>
									   			<option value= 1>離職</option>
											</select>
										</td>
										
										<td class="td_introduct">
										<textarea name="thrIntroduction" rows="15" cols="22" class="my_textarea"  style="width:200px;height:200px;"><%= (teacherVO==null)? "你好": teacherVO.getThrIntroduction() %></textarea>
										</td>
									
										<td>
										<div style="width:60px;">
										<input type="file" id=p_file accept="image/jpeg" name="picio" value=<%= (teacherVO==null)? "": teacherVO.getThrPic() %> >
										<input id=post_file type="hidden" name="thrPic" value="" >
										<div >
										</td>
										
										<td>
										<div style="width:60px;">
										<img id = preImg src="" width="200px" height="200px" style="visibility:hidden">
										<div >
										</td>
										
										
										<td>
										<input type="hidden" name="action" value="insert">
										<input type="submit" value="送出新增"></FORM>
										</td>
										
									</tr>
								</tbody>
							</table>
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
	
<script>
	let preImg = document.querySelector('#preImg');
	let p_file = document.querySelector('#p_file');
	let post_file = document.querySelector('#post_file');
	let reader = new FileReader();
	
    // change事件
    p_file.addEventListener("change",function () {  
      // 讀取選取檔案
      reader.readAsDataURL(this.files[0]);
    });
    
    // 監聽讀取檔案並預覽圖片
    reader.addEventListener("load", function () {  
      preImg.src = reader.result;
      post_file.value = reader.result;
      preImg.style.visibility="";
//       post_file.value = (reader.result).substring(23);
      
    });    
	
</script>	
	
</body>
</html>