<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chef.model.*"%>

<%
ChefVO chefVO = (ChefVO) request.getAttribute("chefVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>私廚資料新增 - addChef.jsp</title>

<link href="css/chefAddOther.css" rel="stylesheet" type="text/css">
<link href="css/chefUpdateDragandDrop.css" rel="stylesheet" type="text/css">
<link href="css/chefUpdatePreview.css" rel="stylesheet" type="text/css">

<style>

</style>


</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>私廚資料新增 - addChef.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="chef.do" name="form1"  enctype="multipart/form-data"> 	<!-- //上傳三要素之一 -->
		<table>
			<tr>
				<td><label for="chefName">私廚姓名:</label></td>
				<td><input type="TEXT" name="chefName" size="45"
					value="<%=(chefVO == null) ? "不告訴你" : chefVO.getChefName()%>" 
					id="chefName" placeholder="你的名字"/></td>
			</tr>
			<tr>
				<td><label for="chefNickname">私廚暱稱:</label></td>
				<td><input type="TEXT" name="chefNickname" size="45"
					value="<%=(chefVO == null) ? "也不想告訴你" : chefVO.getChefNickname()%>" 
					id="chefNickname" placeholder="你的暱稱"/></td>
			</tr>
			<tr>
				<td><label for="chefAccount">帳號:</label></td>
				<td><input type="TEXT" name="chefAccount" size="45"
					value="<%=(chefVO == null) ? "AccountTest" : chefVO.getChefAccount()%>" 
					id="chefAccount" placeholder="你的帳號"/></td>
			</tr>
			<tr>
				<td><label for="chefPassword">密碼:</label></td>
				<td><input type="TEXT" name="chefPassword" size="45"
					value="<%=(chefVO == null) ? "passTest" : chefVO.getChefPassword()%>" 
					id="chefPassword" placeholder="你的密碼"/></td>
			</tr>
			<tr>
				<td><label for="chefPrice">價格:</label></td>
				<td><input type="TEXT" name="chefPrice" size="45"
					value="<%=(chefVO == null) ? "100" : chefVO.getChefPrice()%>" 
					id="chefPrice" placeholder="價格"/></td>
			</tr>
						<tr>
				<td>廚師執照:</td>
				<td><input type="file"  name="license" id="p_file1"></td>
				<td id="drop_zone1"><span class="text">圖片拖曳至此處</span></td>
				<td id="preview1"><span class="text">預覽圖</span></td>

			</tr>
			<tr>
				<td>身分證(正):</td>
				<td><input type="file" name="idCard" id="p_file2"></td>
				<td id="drop_zone2"><span class="text">圖片拖曳至此處</span></td>
				<td id="preview2"><span class="text">預覽圖</span></td>
			</tr>
			<tr>
				<td>身分證(反):</td>
				<td><input type="file" name="idCardBack" id="p_file3"></td>
				<td id="drop_zone3"><span class="text">圖片拖曳至此處</span></td>
				<td id="preview3"><span class="text">預覽圖</span></td>
			</tr>
			<tr>
				<td>個人照:</td>
				<td><input type="file" name="chefPhoto" id="p_file4"></td>
				<td id="drop_zone4"><span class="text">圖片拖曳至此處</span></td>
				<td id="preview4"><span class="text">預覽圖</span></td>
			</tr>
			
			<tr>
				<td><label for="chefIntroduction">簡介:</label></td>
				<td><input type="TEXT" name="chefIntroduction" size="45"
					value="<%=(chefVO == null) ? "測試用" : chefVO.getChefIntroduction()%>" 
					id="chefIntroduction" placeholder="簡介"/></td>
			</tr>




		</table>
		<br> <input type="hidden" name="action" value="insert"> 
		<input type="submit" value="送出新增">
	</FORM>
	
	
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