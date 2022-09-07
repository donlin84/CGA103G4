<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chefskillstype.model.*"%>

<%
ChefSkillsTypeVO chefSkillsTypeVO = (ChefSkillsTypeVO) request.getAttribute("chefSkillsTypeVO"); //ChefSkillsTypeServlet.java (Concroller) 存入req的chefSkillsTypeVO物件 (包括幫忙取出的chefSkillsTypeVO, 也包括輸入資料錯誤時的chefSkillsTypeVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>專長資料修改 - update_chefSkillsType_input.jsp</title>
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

<link href="../chef/css/other.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor='white'>

<!-- 	<div id="preloader"> -->
<!-- 		<div id="status"> -->
<!-- 			<div class="spinner"></div> -->
<!-- 		</div> -->
<!-- 	</div> -->
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

									<div class="btn-group mo-mb-2" style="top: 0px; left: 0px;">
										<button type="button"
											class="btn btn-primary btn-lg dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false" style="width:140px">私廚資訊</button>
										<div class="dropdown-menu">
											<a class="dropdown-item" href='../chef/addChef.jsp'>新增私廚帳號</a>
											<a class="dropdown-item" href='../chef/select_page.jsp'>搜尋私廚</a>
											<a class="dropdown-item" href='../chef/listAllChef.jsp'>私廚列表</a>
										</div>
									</div>

									<div class="btn-group mo-mb-2" style="top: 20px; left: 0px;">
										<button type="button"
											class="btn btn-primary btn-lg dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false" style="width:140px">專長種類</button>
										<div class="dropdown-menu">
											<a class="dropdown-item"
												href='../chefSkillsType/addChefSkillsType.jsp'>新增專長</a> <a
												class="dropdown-item"
												href='../chefSkillsType/select_page.jsp'>搜尋專長</a> <a
												class="dropdown-item"
												href='../chefSkillsType/listAllChefSkillsType.jsp'>專長列表</a>
										</div>
									</div>

									<div class="btn-group mo-mb-2" style="top: 40px; left: 0px;">
										<button type="button"
											class="btn btn-primary btn-lg dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false" style="width:140px">私廚專長</button>
										<div class="dropdown-menu">
											                                    <a class="dropdown-item" href='../chefSkills/select_page.jsp'>新增私廚專長</a>
											<a class="dropdown-item" href='../chefSkills/select_page.jsp'>搜尋私廚專長</a>
											<a class="dropdown-item"
												href='../chefSkills/listAllChefSkills.jsp'>私廚專長列表</a>
										</div>
									</div>

									<div class="btn-group mo-mb-2" style="top: 60px; left: 0px;">
										<button type="button"
											class="btn btn-primary btn-lg dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false" style="width:140px">私廚訂閱</button>
										<div class="dropdown-menu">
											                                    <a class="dropdown-item" href='../chefSubscription/select_page.jsp'>新增專長</a>
											<a class="dropdown-item"
												href='../chefSubscription/select_page.jsp'>搜尋私廚訂閱</a> <a
												class="dropdown-item"
												href='../chefSubscription/listAllChefSubscription.jsp'>私廚訂閱列表</a>
										</div>
									</div>
								</aside>

								<main class="main">

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

									<FORM METHOD="post" ACTION="ChefSkillsType.do" name="form1">
										<table>
											<tr>
												<td>專長編號:<font color=red><b>*</b></font></td>
												<td><%=chefSkillsTypeVO.getSkillid()%></td>
											</tr>
											<tr>
												<td>專長:</td>
												<td><input type="TEXT" name="skill" size="45"
													value="<%=chefSkillsTypeVO.getSkill()%>" /></td>
											</tr>

										</table>
										<br> <input type="hidden" name="action" value="update">
										<input type="hidden" name="skillid"
											value="<%=chefSkillsTypeVO.getSkillid()%>"> <input
											type="submit" value="送出修改">
											<br>
											<br>
											<br>
									</FORM>
								</main>
							</div>
						</div>
					</div>
				</div>
<!-- 				end col -->
			</div>
			<!-- end row -->
		</div>

		<!-- end container -->
	</div>
<!-- 	<!-- end wrapper --> 

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
</body>


</html>