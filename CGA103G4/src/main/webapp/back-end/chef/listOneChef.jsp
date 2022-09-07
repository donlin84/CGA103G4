<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.chef.model.*"%>

<%
ChefVO chefVO = (ChefVO) request.getAttribute("chefVO"); //ChefServlet.java(Concroller), 存入req的chefVO物件
%>




<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>私廚資料 - listOneChef.jsp</title>
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

<link href="css/bigPicture.css" rel="stylesheet" type="text/css">
<link href="css/listOneChefOther.css" rel="stylesheet" type="text/css">


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
									<div
										style='position: fixed; width: 100%; height: 100%; background-color: rgb(0, 0, 0, 0)'
										id='bigmodal'>
										<div class='bigmodal' id='bigmodalw'>
											<img id='bgImg' />
										</div>
									</div>

									

									<table>
										<tr>
											<th>修改</th>
											<th width="70px">私廚編號</th>
											<th>帳號</th>
											<th>密碼</th>
											<th width="40px">狀態</th>
											<th width="60px">姓名</th>
											<th width="40px">暱稱</th>
											<th>價格</th>
											<th>預約數</th>
											<th>評價總人數</th>
											<th>評價總分數</th>
											<th>廚師執照</th>
											<th>身分證(正)</th>
											<th>身分證(反)</th>
											<th>個人照</th>
											<th>簡介</th>
										</tr>
										<tr>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/back-end/chef/Chef.do"
													style="margin-bottom: 0px;">
													<input type="submit" value="修改"> <input
														type="hidden" name="chefid" value="${chefVO.chefid}">
													<input type="hidden" name="action"
														value="getOne_For_Update">
												</FORM>
											</td>
											<td><%=chefVO.getChefid()%></td>
											<td><%=chefVO.getChefAccount()%></td>
											<td><%=chefVO.getChefPassword()%></td>
											<td><%=chefVO.getChefStatus()%></td>
											<td><%=chefVO.getChefName()%></td>
											<td><%=chefVO.getChefNickname()%></td>
											<td><%=chefVO.getChefPrice()%></td>
											<td><%=chefVO.getReserve()%></td>
											<td><%=chefVO.getCom()%></td>
											<td><%=chefVO.getGomg()%></td>
											<td><img onclick='showBgImg(this)' class='thum-img'
												src="<%=request.getContextPath()%>/showLicensePicture?chefid=${chefVO.chefid}"></td>
											<td><img onclick='showBgImg(this)' class='thum-img'
												src="<%=request.getContextPath()%>/showIdCardPicture?chefid=${chefVO.chefid}"></td>
											<td><img onclick='showBgImg(this)' class='thum-img'
												src="<%=request.getContextPath()%>/showIdCardBackPicture?chefid=${chefVO.chefid}"></td>
											<td><img onclick='showBgImg(this)' class='thum-img'
												src="<%=request.getContextPath()%>/showChefPhotoPicture?chefid=${chefVO.chefid}"></td>
											<%-- 		<td><img onclick='showBgImg(this)' class='thum-img' src="data:image/jpeg;base64,${license}"></td> --%>
											<%-- 		<td><img onclick='showBgImg(this)' class='thum-img' src="data:image/jpeg;base64,${idCard}" ></td> --%>
											<%-- 		<td><img onclick='showBgImg(this)' class='thum-img' src="data:image/jpeg;base64,${idCardBack}" ></td> --%>
											<%-- 		<td><img onclick='showBgImg(this)' class='thum-img' src="data:image/jpeg;base64,${chefPhoto}" ></td> --%>
											<td><%=chefVO.getChefIntroduction()%></td>

										</tr>
									</table>
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
		var bigmodal = document.getElementById('bigmodal');
		var bgImg = document.getElementById('bgImg');
		function showBgImg(e) {
			bigmodal.style.display = 'block';
			bgImg.src = e.src;
		}
		bgImg.onclick = function() {
			bigmodal.style.display = 'none';
		}
	</script>
</body>
</html>