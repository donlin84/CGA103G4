<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listChef_ByCompositeQuery" scope="request" type="java.util.List<ChefVO>" /> <!-- 於EL此行可省略 -->
<%-- <jsp:useBean id="chefSvc" scope="page" class="com.chef.model.ChefService" /> --%>

<!DOCTYPE html>
<html>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<head><title>複合查詢 - listChef_ByCompositeQuery.jsp</title>

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
		<th>編號</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>狀態</th>
		<th>姓名</th>
		<th>暱稱</th>
		<th>價格</th>
		<th>可預約日</th>
		<th>評價總人數</th>
		<th>評分總分數</th>
		<th>廚師執照</th>
		<th>身分證(正)</th>
		<th>身分證(反)</th>
		<th>個人照</th>
		<th>簡介</th>

	</tr>
	<%@ include file="page1_ByCompositeQuery.file" %>

			

	<c:forEach var="chefVO" items="${listChef_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

		<tr align='center' valign='middle' ${(chefVO.chefid==param.chefid) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
						<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/chef/Chef.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="chefid"      value="${chefVO.chefid}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
			<td>${chefVO.chefid}</td>
			<td>${chefVO.chefAccount}</td>
			<td>${chefVO.chefPassword}</td>
<%-- 			<td>${chefVO.chefStatus}</td> --%>
			<c:if test="${chefVO.chefStatus == 0}">
				<td>啟用</td>	
			</c:if>
			<c:if test="${chefVO.chefStatus == 1}">
				<td>停權</td>
			</c:if>
			<td>${chefVO.chefName}</td>
			<td>${chefVO.chefNickname}</td> 
			<td>${chefVO.chefPrice}</td>
			<td>${chefVO.reserve}</td>
			<td>${chefVO.com}</td>
			<td>${chefVO.gomg}</td>
			<td><img onclick='showBgImg(this)' class='thum-img' src="<%=request.getContextPath()%>/showLicensePicture?chefid=${chefVO.chefid}" ></td>
			<td><img onclick='showBgImg(this)' class='thum-img' src="<%=request.getContextPath()%>/showIdCardPicture?chefid=${chefVO.chefid}"  ></td>
			<td><img onclick='showBgImg(this)' class='thum-img' src="<%=request.getContextPath()%>/showIdCardBackPicture?chefid=${chefVO.chefid}"  ></td>
			<td><img onclick='showBgImg(this)' class='thum-img' src="<%=request.getContextPath()%>/showChefPhotoPicture?chefid=${chefVO.chefid}"  ></td>
			<td>${chefVO.chefIntroduction}</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2_ByCompositeQuery.file" %>
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