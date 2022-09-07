<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>Chef: Home</title>

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
<style>
div.main_content {
	width: 100%;
	margin: 0 auto;
	font-size: 0;

	/*   border: 1px solid red; */
}

aside.aside {
	/*   background-color: #ddd; */
	width: 150px;
	display: inline-block;
	vertical-align: top;
	font-size: 1rem;
	margin-right: 10px;
	/*   border: 1px solid #999; */
}

main.main {
	/*   background-color: #ddd; */
	width: calc(100% - 200px - 10px);
	display: inline-block;
	vertical-align: top;
	font-size: 1rem;
	/*   border: 1px solid #999; */
}
</style>
</head>

<body>

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
									<table id="table-1">
										<tr>
											<td><h3>資料查詢:</h3></td>
										</tr>
									</table>

									<%-- 錯誤表列 --%>
									<c:if test="${not empty errorMsgs}">
										<font style="color: red">請修正以下錯誤:</font>
										<ul>
											<c:forEach var="message" items="${errorMsgs}">
												<li style="color: red">${message}</li>
											</c:forEach>
										</ul>
									</c:if>

									<ul>
										<li><a href='listAllChef.jsp'>List</a> all Chefs. <br>
											<br></li>


										<li>
											<FORM METHOD="post" ACTION="Chef.do">
												<b>輸入私廚編號 (如301):</b> <input type="text" name="chefid">
												<input type="hidden" name="action"
													value="getOne_For_Display"> <input type="submit"
													value="送出">
											</FORM>
										</li>

										<jsp:useBean id="chefSvc" scope="page"
											class="com.chef.model.ChefService" />

										<li>
											<FORM METHOD="post" ACTION="Chef.do">
												<b>選擇私廚編號:</b> <select size="1" name="chefid">
													<c:forEach var="chefVO" items="${chefSvc.all}">
														<option value="${chefVO.chefid}">${chefVO.chefid}
													</c:forEach>
												</select> <input type="hidden" name="action"
													value="getOne_For_Display"> <input type="submit"
													value="送出">
											</FORM>
										</li>

										<li>
											<FORM METHOD="post" ACTION="Chef.do">
												<b>選擇私廚姓名:</b> <select size="1" name="chefid">
													<c:forEach var="chefVO" items="${chefSvc.all}">
														<option value="${chefVO.chefid}">${chefVO.chefName}
													</c:forEach>
												</select> <input type="hidden" name="action"
													value="getOne_For_Display"> <input type="submit"
													value="送出">
											</FORM>
										</li>
									</ul>

									<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
									<ul>
										<li>
											<FORM METHOD="post" ACTION="Chef.do"
<%-- 											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/chef/Chef.do" --%>
												name="form1">
												<b><font color=blue>萬用複合查詢:</font></b> <br> 
																							
<!-- 													<b>選擇私廚編號:</b>  -->
<!-- 													<select size="1" name="chefid"> -->
<%-- 													<c:forEach var="chefVO" items="${chefSvc.all}"> --%>
<%-- 														<option value="${chefVO.chefid}">${chefVO.chefid} --%>
<%-- 													</c:forEach> --%>
<!-- 												</select> <input type="hidden" name="chefid"><br> -->

												<b>輸入私廚姓名:</b> <input type="text" name="chefName" value=""><br>

										
													<b>選擇私廚狀態:</b> 
													<select size="1" name="chefStatus">
														<c:forEach var="chefVO" items="${chefSvc.all}">
															<option value="${chefVO.chefStatus}">${chefVO.chefStatus}
														</c:forEach>
												</select> <input type="hidden" name="chefStatus"><br> 
 
													<input type="submit" value="送出">
												<input type="hidden" name="action"
													value="listChef_ByCompositeQuery">
											</FORM>
										</li>
									</ul>
									
									
<!-- 									<h3>私廚管理</h3> -->

<!-- 									<ul> -->
<!-- 										<li><a href='addChef.jsp'>Add</a> a new Chef.</li> -->
<!-- 									</ul> -->
								</main>
							</div>
						</div>
						<!-- end container -->
					</div>
					<!-- end wrapper -->
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
</body>
</html>