<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chef.model.*"%>

<%
ChefVO chefVO = (ChefVO) request.getAttribute("chefVO"); //ChefServlet.java (Concroller) 存入req的chefVO物件 (包括幫忙取出的chefVO, 也包括輸入資料錯誤時的chefVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>私廚資料修改 - update_chef_input.jsp</title>
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
<link href="css/chefUpdateDragandDrop.css" rel="stylesheet"
	type="text/css">
<link href="css/chefUpdatePreview.css" rel="stylesheet" type="text/css">
<link href="css/chefUpdateOther.css" rel="stylesheet" type="text/css">


<style>
.formGroup .input {
	border: 0px;
	border-bottom: 1px solid #b2b2b2;
	width: calc(100% - 110px);
}
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

					<a href='addChef.jsp'>新增私廚帳號</a>
											<br>
										<a href='select_page.jsp'>私廚資訊查詢</a>
											<br>
										<a href='../chefSkillsType/select_page.jsp'>專長種類查詢</a>
											<br>
										<a href='../chefSkills/select_page.jsp'>私廚專長查詢</a>
											<br>
										<a href='../chefSubscription/select_page.jsp'>私廚訂閱清單查詢</a>
											<br>
										<a href='../chefSubscription/select_page.jsp'>私廚預約表查詢</a>
											<br>
										<a href='../chefSubscription/select_page.jsp'>預約單查詢</a>
											<br>
								</aside>

								<main class="main">
									<table id="table-1">
										<tr>
											<td>
												<h3>私廚資料修改 - update_chef_input.jsp</h3>
												<h4>
													<a href="select_page.jsp"><img src="images/back1.gif"
														width="100" height="32" border="0">回首頁</a>
												</h4>
											</td>
										</tr>
									</table>

									<h3>資料修改:</h3>

									<%-- 錯誤表列 --%>
									<c:if test="${not empty errorMsgs}">
										<font style="color: red">請修正以下錯誤:</font>
										<ul>
											<c:forEach var="message" items="${errorMsgs}">
												<li style="color: red">${message}</li>
											</c:forEach>
										</ul>
									</c:if>

									<FORM METHOD="post" ACTION="chef.do" name="form1"
										enctype="multipart/form-data">
										<table>
											<tr>
												<td>私廚編號:<font color=red><b>*</b></font></td>
												<td><%=chefVO.getChefid()%></td>
											</tr>
											<tr>
												<td>帳號:</td>
												<td><input type="TEXT" name="chefAccount" size="45"
													value="<%=chefVO.getChefAccount()%>" readonly /></td>
											</tr>
											<tr>
												<td><label for="chefPassword">密碼:</label></td>
												<td><input type="password" name="chefPassword"
													size="45" id="chefPassword"
													value="<%=chefVO.getChefPassword()%>" /></td>
											</tr>
											<tr>
												<td><label for="chefName">私廚姓名:</label></td>
												<td><input type="TEXT" name="chefName" size="45"
													id="chefName" value="<%=chefVO.getChefName()%>" /></td>
											</tr>
											<tr>
												<td><label for="chefNickname">私廚暱稱:</label></td>
												<td><input type="TEXT" name="chefNickname" size="45"
													id="chefNickname" value="<%=chefVO.getChefNickname()%>" /></td>
											</tr>

											<tr>
												<td>狀態:</td>
												<c:if test="${chefVO.getChefStatus() == 0}">
													<td><select name="chefStatus" size="1">
															<option value="0">0
															<option value="1">1
													</select></td>
												</c:if>
												<c:if test="${chefVO.getChefStatus() == 1}">
													<td><select name="chefStatus" size="1">
															<option value="1">1
															<option value="0">0
													</select></td>
												</c:if>
											</tr>

											<tr>
												<td><label for="chefPrice">價格:</label></td>
												<td><input type="TEXT" name="chefPrice" size="45"
													id="chefPrice" value="<%=chefVO.getChefPrice()%>" /></td>
											</tr>
											<tr>
												<td>廚師執照:</td>
												<td><input type="file" name="license" id="p_file1"></td>
												<td id="drop_zone1"><span class="text">圖片拖曳至此處</span></td>
												<td id="preview1"><img
													src="<%=request.getContextPath()%>/showLicensePicture?chefid=${chefVO.chefid}"
													width=100%> <span class="text"></span></td>

											</tr>
											<tr>
												<td>身分證(正):</td>
												<td><input type="file" name="idCard" id="p_file2"></td>
												<td id="drop_zone2"><span class="text">圖片拖曳至此處</span></td>
												<td id="preview2"><img
													src="<%=request.getContextPath()%>/showIdCardPicture?chefid=${chefVO.chefid}"
													width=100%> <span class="text"></span></td>
											</tr>
											<tr>
												<td>身分證(反):</td>
												<td><input type="file" name="idCardBack" id="p_file3"></td>
												<td id="drop_zone3"><span class="text">圖片拖曳至此處</span></td>
												<td id="preview3"><img
													src="<%=request.getContextPath()%>/showIdCardBackPicture?chefid=${chefVO.chefid}"
													width=100%> <span class="text"></span></td>
											</tr>
											<tr>
												<td>個人照:</td>
												<td><input type="file" name="chefPhoto" id="p_file4"></td>
												<td id="drop_zone4"><span class="text">圖片拖曳至此處</span></td>
												<td id="preview4"><img
													src="<%=request.getContextPath()%>/showChefPhotoPicture?chefid=${chefVO.chefid}"
													width=100%> <span class="text"></span></td>
											</tr>

											<tr>
												<td><label for="chefIntroduction">簡介:</label></td>
												<td><input type="TEXT" name="chefIntroduction"
													size="45" id="chefIntroduction"
													value="<%=chefVO.getChefIntroduction()%>" /></td>
											</tr>
											<!-- 			<tr> -->
											<!-- 				<td><label for="chefIntroduction">簡介:</label></td> -->
											<%-- 				<td><textarea rows="10" cols="44" id="chefIntroduction"><%=chefVO.getChefIntroduction()%></textarea></td> --%>
											<!-- 			</tr> -->


										</table>
										<br> <input type="hidden" name="action" value="update">
										<input type="hidden" name="chefid"
											value="<%=chefVO.getChefid()%>"> <input type="submit"
											value="送出修改">
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
		var drop_zone1_el = document.getElementById("drop_zone1");
		var drop_zone2_el = document.getElementById("drop_zone2");
		var drop_zone3_el = document.getElementById("drop_zone3");
		var drop_zone4_el = document.getElementById("drop_zone4");
		var preview1_el = document.getElementById("preview1");
		var preview2_el = document.getElementById("preview2");
		var preview3_el = document.getElementById("preview3");
		var preview4_el = document.getElementById("preview4");
		var p_file1_el = document.getElementById("p_file1");
		var p_file2_el = document.getElementById("p_file2");
		var p_file3_el = document.getElementById("p_file3");
		var p_file4_el = document.getElementById("p_file4");
		// =========================== Drag and Drop ========================= //
		drop_zone1_el.addEventListener("dragover", function(e) {
			e.preventDefault();
			e.target.classList.add("-on");
		});
		drop_zone1_el.addEventListener("dragleave", function(e) {
			e.target.classList.remove("-on");
		});
		drop_zone1_el.addEventListener("drop", function(e) {
			e.preventDefault();

			e.target.classList.remove("-on");

			//console.log(e.dataTransfer.files); // 取得 files

			preview1_img(e.dataTransfer.files[0]);
			p_file1_el.value = ""; // 將 type="file" 那個清空
		});

		drop_zone2_el.addEventListener("dragover", function(e) {
			e.preventDefault();
			e.target.classList.add("-on");
		});
		drop_zone2_el.addEventListener("dragleave", function(e) {
			e.target.classList.remove("-on");
		});
		drop_zone2_el.addEventListener("drop", function(e) {
			e.preventDefault();

			e.target.classList.remove("-on");

			//console.log(e.dataTransfer.files); // 取得 files

			preview2_img(e.dataTransfer.files[0]);
			p_file2_el.value = ""; // 將 type="file" 那個清空
		});

		drop_zone3_el.addEventListener("dragover", function(e) {
			e.preventDefault();
			e.target.classList.add("-on");
		});
		drop_zone3_el.addEventListener("dragleave", function(e) {
			e.target.classList.remove("-on");
		});
		drop_zone3_el.addEventListener("drop", function(e) {
			e.preventDefault();

			e.target.classList.remove("-on");

			//console.log(e.dataTransfer.files); // 取得 files

			preview3_img(e.dataTransfer.files[0]);
			p_file3_el.value = ""; // 將 type="file" 那個清空
		});

		drop_zone4_el.addEventListener("dragover", function(e) {
			e.preventDefault();
			e.target.classList.add("-on");
		});
		drop_zone4_el.addEventListener("dragleave", function(e) {
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

		p_file1_el.addEventListener("change", function(e) {
			if (this.files.length > 0) {
				preview1_img(this.files[0]);
			} else {
				preview1_el.innerHTML = '<span class="text">預覽圖</span>';
			}
		});
		p_file2_el.addEventListener("change", function(e) {
			if (this.files.length > 0) {
				preview2_img(this.files[0]);
			} else {
				preview2_el.innerHTML = '<span class="text">預覽圖</span>';
			}
		});
		p_file3_el.addEventListener("change", function(e) {
			if (this.files.length > 0) {
				preview3_img(this.files[0]);
			} else {
				preview3_el.innerHTML = '<span class="text">預覽圖</span>';
			}
		});
		p_file4_el.addEventListener("change", function(e) {
			if (this.files.length > 0) {
				preview4_img(this.files[0]);
			} else {
				preview4_el.innerHTML = '<span class="text">預覽圖</span>';
			}
		});
	</script>
</body>


</html>