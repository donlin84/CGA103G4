<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chef.model.*"%>

<%
ChefVO chefVO = (ChefVO) request.getAttribute("chefVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>私廚資料新增 - addChef.jsp</title>
<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon" href="../assets/images/favicon.ico">
<link href="../assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css"
	rel="stylesheet">
<link href="../assets/plugins/fullcalendar/vanillaCalendar.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/plugins/morris/morris.css" rel="stylesheet">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="../assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="../assets/css/style.css" rel="stylesheet" type="text/css">
<link href="css/chefAddOther.css" rel="stylesheet" type="text/css">
<link href="css/chefUpdateDragandDrop.css" rel="stylesheet"
	type="text/css">
<link href="css/chefUpdatePreview.css" rel="stylesheet" type="text/css">

<style>
</style>


</head>
<body bgcolor='white'>

	<div id="preloader">
		<div id="status">
			<div class="spinner"></div>
		</div>
	</div>
	<!-- Navigation Bar -->
	<%@ include file="../tools/header.jsp"%>

	<div class="wrapper">
		<div class="container-fluid">
			<!-- 			Page-Title -->
			<div class="row">
				<div class="col-sm-12">
					<div class="page-title-box">
						<div class="btn-group pull-right">
							<ol class="breadcrumb hide-phone p-0 m-0">
								<li class="breadcrumb-item"><a href="#">Zoter</a></li>
								<li class="breadcrumb-item"><a href="#">Tables</a></li>
								<li class="breadcrumb-item active">Editable</li>
							</ol>
						</div>
						<h4 class="page-title">Editable</h4>
					</div>
				</div>
			</div>

			<!-- end page title end breadcrumb -->

			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">



							<div class="main_content">
							<aside class="aside">
							
								<div class="btn-group mo-mb-2" 
                     			style="top: 0px; left: 0px;">
                                <button type="button" class="btn btn-primary btn-lg dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false" style="width:140px">私廚資訊 </button>
                                    <div class="dropdown-menu">
                                    <a class="dropdown-item" href='../chef/addChef.jsp'>新增私廚帳號</a>
                                    <a class="dropdown-item" href='../chef/select_page.jsp'>搜尋私廚</a>
                                    <a class="dropdown-item" href='../chef/listAllChef.jsp'>私廚列表</a>
                                    </div>
                                </div>

                                <div class="btn-group mo-mb-2"
                                style="top: 20px; left: 0px;">
                                <button type="button" class="btn btn-primary btn-lg dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false" style="width:140px">專長種類 </button>
                                    <div class="dropdown-menu">
                                    <a class="dropdown-item" href='../chefSkillsType/addChefSkillsType.jsp'>新增專長</a>
                                    <a class="dropdown-item" href='../chefSkillsType/select_page.jsp'>搜尋專長</a>
                                    <a class="dropdown-item" href='../chefSkillsType/listAllChefSkillsType.jsp'>專長列表</a>
                                    </div>
                                </div>
                                
                                <div class="btn-group mo-mb-2"
                                style="top: 40px; left: 0px;">
                                <button type="button" class="btn btn-primary btn-lg dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false" style="width:140px">私廚專長 </button>
                                    <div class="dropdown-menu">
<!--                                     <a class="dropdown-item" href='../chefSkills/select_page.jsp'>新增私廚專長</a> -->
                                    <a class="dropdown-item" href='../chefSkills/select_page.jsp'>搜尋私廚專長</a>
                                    <a class="dropdown-item" href='../chefSkills/listAllChefSkills.jsp'>私廚專長列表</a>
                                    </div>
                                </div>
                                
                                <div class="btn-group mo-mb-2"
                                style="top: 60px; left: 0px;">
                                <button type="button" class="btn btn-primary btn-lg dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false" style="width:140px">私廚訂閱 </button>
                                    <div class="dropdown-menu">
<!--                                     <a class="dropdown-item" href='../chefSubscription/select_page.jsp'>新增專長</a> -->
                                    <a class="dropdown-item" href='../chefSubscription/select_page.jsp'>搜尋私廚訂閱</a>
                                    <a class="dropdown-item" href='../chefSubscription/listAllChefSubscription.jsp'>私廚訂閱列表</a>
                                    </div>
                                </div>
								
							</aside>

							<main class="main">
						

								<h3>建立私廚帳號 : </h3>

								<%-- 錯誤表列 --%>
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>

								<FORM METHOD="post" ACTION="Chef.do" name="form1"
									id="the_form" enctype="multipart/form-data">
									<!-- //上傳三要素之一 -->
									<table>
										<tr>
											<td><label for="chefName">私廚姓名:</label></td>
											<td><input type="TEXT" name="chefName" size="15"
												value="<%=(chefVO == null) ? "" : chefVO.getChefName()%>"
												id="chefName" placeholder="名字" /></td>
										</tr>
										<tr>
											<td><label for="chefNickname">私廚暱稱:</label></td>
											<td><input type="TEXT" name="chefNickname" size="15"
												value="<%=(chefVO == null) ? "" : chefVO.getChefNickname()%>"
												id="chefNickname" placeholder="暱稱" /></td>
										</tr>
										<tr>
											<td><label for="chefAccount">帳號:</label></td>
											<td><input type="TEXT" name="chefAccount" size="25"
												value="<%=(chefVO == null) ? "" : chefVO.getChefAccount()%>"
												id="chefAccount" placeholder="帳號" /></td>
										</tr>
										<tr>
											<td><label for="chefPassword">密碼:</label></td>
											<td><input type="TEXT" name="chefPassword" size="25"
												value="<%=(chefVO == null) ? "" : chefVO.getChefPassword()%>"
												id="chefPassword" placeholder="密碼" /></td>
										</tr>
										<tr>
											<td><label for="chefPrice">價格:</label></td>
											<td><input type="TEXT" name="chefPrice" size="15"
												value="<%=(chefVO == null) ? "" : chefVO.getChefPrice()%>"
												id="chefPrice" placeholder="價格" /></td>
										</tr>
										<tr>
											<td>廚師執照:</td>
											<td><input type="file" name="license" id="p_file1"></td>
											<td id="drop_zone1"><span class="text">圖片拖曳至此處</span></td>
											<td id="test1"></td>
											<td id="preview1"><span class="text">預覽圖</span></td>

										</tr>
										<tr>
											<td>身分證(正):</td>
											<td><input type="file" name="idCard" id="p_file2"></td>
											<td id="drop_zone2"><span class="text">圖片拖曳至此處</span></td>
											<td id="test1"></td>
											<td id="preview2"><span class="text"><%=(chefVO == null) ? "預覽圖" : chefVO.getIdCard()%></span></td>
										</tr>
										<tr>
											<td>身分證(反):</td>
											<td><input type="file" name="idCardBack" id="p_file3"></td>
											<td id="drop_zone3"><span class="text">圖片拖曳至此處</span></td>
											<td id="test1"></td>
											<td id="preview3"><span class="text"><%=(chefVO == null) ? "預覽圖" : chefVO.getIdCardBack()%></span></td>
										</tr>
										<tr>
											<td>個人照:</td>
											<td><input type="file" name="chefPhoto" id="p_file4"></td>
											<td id="drop_zone4"><span class="text">圖片拖曳至此處</span></td>
											<td id="test1"></td>
											<td id="preview4"><span class="text"><%=(chefVO == null) ? "預覽圖" : chefVO.getChefPhoto()%></span></td>
										</tr>

<!-- 										<tr> -->
<!-- 											<td><label for="chefIntroduction">簡介:</label></td> -->
<!-- 											<td><input type="TEXT" name="chefIntroduction" size="45" -->
<%-- 												value="<%=(chefVO == null) ? "" : chefVO.getChefIntroduction()%>" --%>
<!-- 												id="chefIntroduction" placeholder="簡介" /></td> -->
<!-- 										</tr> -->
										<tr class="form-group">
   									<td><label for="chefIntroduction">簡介:</label></td>
                                    <td><textarea required class="form-control" rows="5" name="chefIntroduction" 
                                    size="45" value="<%=(chefVO == null) ? "" : chefVO.getChefIntroduction()%>"
                                     id="chefIntroduction" placeholder="簡介"></textarea></td>
                              			</tr>

									</table>
									<br> <input type="reset" value="清空資料">
									<input type="hidden" name="action" value="insert">
									<input type="submit" id="btn_submit" value="送出新增">
								</FORM>
							</main>
							</div>
						</div>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>

		<!-- end container -->
	</div>
	<!-- end wrapper -->

	<!-- Footer -->
	<%@ include file="../tools/footer.jsp"%>
	<!-- End Footer -->
	<!-- jQuery -->
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/popper.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script src="../assets/js/modernizr.min.js"></script>
	<script src="../assets/js/waves.js"></script>
	<script src="../assets/js/jquery.nicescroll.js"></script>
	<script
		src="../assets/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script
		src="../assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="../assets/plugins/skycons/skycons.min.js"></script>
	<script src="../assets/plugins/tiny-editable/mindmup-editabletable.js"></script>
	<script src="../assets/plugins/tiny-editable/numeric-input-example.js"></script>
	<script src="../assets/plugins/fullcalendar/vanillaCalendar.js"></script>
	<script src="../assets/plugins/raphael/raphael-min.js"></script>
	<script src="../assets/plugins/morris/morris.min.js"></script>
	<script src="../assets/js/app.js"></script>

	<script>
		window
				.addEventListener(
						"load",
						function(e) {
							var drop_zone1_el = document
									.getElementById("drop_zone1");
							var drop_zone2_el = document
									.getElementById("drop_zone2");
							var drop_zone3_el = document
									.getElementById("drop_zone3");
							var drop_zone4_el = document
									.getElementById("drop_zone4");
							var preview1_el = document
									.getElementById("preview1");
							var preview2_el = document
									.getElementById("preview2");
							var preview3_el = document
									.getElementById("preview3");
							var preview4_el = document
									.getElementById("preview4");
							var p_file1_el = document.getElementById("p_file1");
							var p_file2_el = document.getElementById("p_file2");
							var p_file3_el = document.getElementById("p_file3");
							var p_file4_el = document.getElementById("p_file4");
							var btn_submit_el = document
									.getElementById("btn_submit");
							// =========================== Drag and Drop ========================= //
							drop_zone1_el.addEventListener("dragover",
									function(e) {
										e.preventDefault();
										e.target.classList.add("-on");
									});
							drop_zone1_el.addEventListener("dragleave",
									function(e) {
										e.target.classList.remove("-on");
									});
							drop_zone1_el.addEventListener("drop", function(e) {
								e.preventDefault();

								e.target.classList.remove("-on");

								//console.log(e.dataTransfer.files); // 取得 files

								preview1_img(e.dataTransfer.files[0]);
								p_file1_el.value = ""; // 將 type="file" 那個清空
							});

							drop_zone2_el.addEventListener("dragover",
									function(e) {
										e.preventDefault();
										e.target.classList.add("-on");
									});
							drop_zone2_el.addEventListener("dragleave",
									function(e) {
										e.target.classList.remove("-on");
									});
							drop_zone2_el.addEventListener("drop", function(e) {
								e.preventDefault();

								e.target.classList.remove("-on");

								//console.log(e.dataTransfer.files); // 取得 files

								preview2_img(e.dataTransfer.files[0]);
								p_file2_el.value = ""; // 將 type="file" 那個清空
							});

							drop_zone3_el.addEventListener("dragover",
									function(e) {
										e.preventDefault();
										e.target.classList.add("-on");
									});
							drop_zone3_el.addEventListener("dragleave",
									function(e) {
										e.target.classList.remove("-on");
									});
							drop_zone3_el.addEventListener("drop", function(e) {
								e.preventDefault();

								e.target.classList.remove("-on");

								//console.log(e.dataTransfer.files); // 取得 files

								preview3_img(e.dataTransfer.files[0]);
								p_file3_el.value = ""; // 將 type="file" 那個清空
							});

							drop_zone4_el.addEventListener("dragover",
									function(e) {
										e.preventDefault();
										e.target.classList.add("-on");
									});
							drop_zone4_el.addEventListener("dragleave",
									function(e) {
										e.target.classList.remove("-on");
									});
							drop_zone4_el.addEventListener("drop", function(e) {
								e.preventDefault();

								e.target.classList.remove("-on");

								//console.log(e.dataTransfer.files); // 取得 files

								preview4_img(e.dataTransfer.files[0]);
								p_file4_el.value = ""; // 將 type="file" 那個清空
							});

							// =========================== 透過 File 取得預覽圖 ========================= //
							var preview1_img = function(file) {

								var reader = new FileReader(); // 用來讀取檔案
								reader.readAsDataURL(file); // 讀取檔案
								reader
										.addEventListener(
												"load",
												function() {

													let img_str = '<img src="' + reader.result + '" class="preview_img">';
													preview1_el.innerHTML = img_str;
												});
							};
							var preview2_img = function(file) {

								var reader = new FileReader(); // 用來讀取檔案
								reader.readAsDataURL(file); // 讀取檔案
								reader
										.addEventListener(
												"load",
												function() {

													let img_str = '<img src="' + reader.result + '" class="preview_img">';
													preview2_el.innerHTML = img_str;
												});
							};
							var preview3_img = function(file) {

								var reader = new FileReader(); // 用來讀取檔案
								reader.readAsDataURL(file); // 讀取檔案
								reader
										.addEventListener(
												"load",
												function() {

													let img_str = '<img src="' + reader.result + '" class="preview_img">';
													preview3_el.innerHTML = img_str;
												});
							};
							var preview4_img = function(file) {

								var reader = new FileReader(); // 用來讀取檔案
								reader.readAsDataURL(file); // 讀取檔案
								reader
										.addEventListener(
												"load",
												function() {

													let img_str = '<img src="' + reader.result + '" class="preview_img">';
													preview4_el.innerHTML = img_str;
												});
							};

							p_file1_el
									.addEventListener(
											"change",
											function(e) {
												if (this.files.length > 0) {
													preview1_img(this.files[0]);
												} else {
													preview1_el.innerHTML = '<span class="text">預覽圖</span>';
												}
											});
							p_file2_el
									.addEventListener(
											"change",
											function(e) {
												if (this.files.length > 0) {
													preview2_img(this.files[0]);
												} else {
													preview2_el.innerHTML = '<span class="text">預覽圖</span>';
												}
											});
							p_file3_el
									.addEventListener(
											"change",
											function(e) {
												if (this.files.length > 0) {
													preview3_img(this.files[0]);
												} else {
													preview3_el.innerHTML = '<span class="text">預覽圖</span>';
												}
											});
							p_file4_el
									.addEventListener(
											"change",
											function(e) {
												if (this.files.length > 0) {
													preview4_img(this.files[0]);
												} else {
													preview4_el.innerHTML = '<span class="text">預覽圖</span>';
												}
											});

						});
		// =========================== 清空資料 ========================= //
		  the_form.addEventListener("reset", function(){

	           sessionStorage.clear();

	        });

	</script>
</body>

</html>