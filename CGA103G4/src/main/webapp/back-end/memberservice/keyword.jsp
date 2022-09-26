<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.teacher.model.*"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>關鍵字管理</title>

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
 .td_introduct{
   display:inline-block;
   width:220px;
    height:200px;
    padding: 1px;
    text-align: center;
  line-height: 20px;
  overflow:scroll;
  }
.picture{
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
								<li class="breadcrumb-item">關鍵字管理</a></li>
								<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/index-back.jsp">後台首頁</a></li>
							</ol>
						</div>
					</div>
				</div>
			</div>

			<div class="row" >
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 header-title">關鍵字管理</h4>
								<script src="js/fetch.js" type="text/javascript"></script>
								</head>
								
								<body>
									<form id = "form">
										<label for ="keyword" > 欲新增或修改的關鍵字名稱</label><br>
										<input id = "keyword"  type = "text" name ="keyword" ><br>
										<label for ="answer" >回應內容</label><br>
										<textarea  id = "answer" name ="answer"></textarea><br>
										<button id = "button" type = "button" >送出</button>
									</form>
									<br>
									<form id = "selectform">
										
										<label for ="selectkeyword" > 欲刪除的關鍵字名稱</label><br>
										<select id = "selectkeyword"  name ="selectkeyword" >
										</select>
										<br>
										<button id = "button2" type = "button" >刪除</button>
									</form>
									<br>
							<table class="table" id="my-table">
								<thead>
									<tr>
										<th >關鍵字key</th>
										<th >回應內容</th>
									</tr>
								</thead>
								<tbody id = "tbody">						

								</tbody>
							</table>
								<script>
									let button = document.getElementById('button');
									let button2 = document.getElementById('button2');
									let form = document.getElementById('form');
									let selectform = document.getElementById("selectform");
									let selectkeyword = document.getElementById("selectkeyword");
																	
									
									var MyPoint = "/back-end/memberservice/KeywordServlet.do";
									var host = window.location.host;
									var path = window.location.pathname;
									var webCtx = path.substring(0, path.indexOf('/', 1));
									var URL = "http://" + host + webCtx + MyPoint;
									
									function result(data) {
										alert(data.Result);
										load();
									}
								
									
									function keyset(data){
										selectkeyword.innerHTML="";
										for(i of data){
										 	let option = document.createElement('option');
										 	option.id = i ;
										 	option.value = i ;
										 	option.innerHTML = i ;		 	
										 	selectkeyword.append(option);
										}
										//查全部資料
										allkey_value();
									}
									
									function showdata(data){
										console.log(data);
										let tbody = document.querySelector("tbody");
										tbody.innerHTML = "";
										
										for (e in data){
											let tr =  document.createElement("tr");
											let td1 =  document.createElement("td");
											let td2 =  document.createElement("td");
											td1.innerHTML = e ;
											td2.innerHTML = data[e];
											tr.append(td1);
											tr.append(td2);
											tbody.append(tr);
										}
										 
									}	
									
									function allkey_value(){
										object={
												Type:"selectAll"
											}
										requestbyform(URL ,showdata, object) ;
										
									}
									
									function load(){
										object={
											Type:"getdata"
										}
										requestbyform(URL , keyset, object) ;
									};
									
									button.addEventListener('click',function(){
										object={};
										let formdata = new FormData(form);
										//form表單元素轉json
										formdata.forEach(function(value,key){
											object[key]=value;
										});
										object.Type = "update"
										requestbyform(URL , result, object) ;
										
									});
								
									window.onload = function(){
										load();
									}
									
									button2.addEventListener('click',function(){
										object={};
										let formdata = new FormData(selectform);
										//form表單元素轉json
										formdata.forEach(function(value,key){
											object[key]=value;
										});
										object.Type = "delete"
										requestbyform(URL ,result, object) ;	
									});
									

									
								</script>

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
</body>

</html>