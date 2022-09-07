<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>CreditCardInformation: Home</title>
<link rel="shortcut icon" href="../assets/images/favicon.ico">
<link href="../assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet">
<link href="../assets/plugins/fullcalendar/vanillaCalendar.css" rel="stylesheet" type="text/css" />
<link href="../assets/plugins/morris/morris.css" rel="stylesheet">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="../assets/css/style.css" rel="stylesheet" type="text/css">
<link href="../chef/css/other.css" rel="stylesheet" type="text/css">

</head>
<body bgcolor='white'>

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
                                        aria-haspopup="true" aria-expanded="false" style="width:140px">會員資訊 </button>
                                    <div class="dropdown-menu">
                                    <a class="dropdown-item" href='../member/select_page.jsp'>搜尋會員</a>
                                    <a class="dropdown-item" href='../member/listAllMember.jsp'>會員列表</a>
                                    </div>
                                </div>

                                <div class="btn-group mo-mb-2"
                                style="top: 20px; left: 0px;">
                                <button type="button" class="btn btn-primary btn-lg dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false" style="width:140px">信用卡資訊 </button>
                                    <div class="dropdown-menu">
                                    <a class="dropdown-item" href='../creditCardInformation/select_page.jsp'>搜尋信用卡</a>
                                    <a class="dropdown-item" href='../creditCardInformation/listAllCreditCardInformation.jsp'>信用卡列表</a>
                                    </div>
                                </div>
                                

								</aside>

								<main class="main">
<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllCreditCardInformation.jsp'>List</a> all CreditCardInformations.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="CreditCardInformation.do" >
        <b>輸入信用卡編號 (如1):</b>
        <input type="text" name="creditCardid">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="creditCardInformationSvc" scope="page" class="com.creditcardinformation.model.CreditCardInformationService" />
   
  <li>
     <FORM METHOD="post" ACTION="CreditCardInformation.do" >
       <b>選擇信用卡編號:</b>
       <select size="1" name="creditCardid">
         <c:forEach var="creditCardInformationVO" items="${creditCardInformationSvc.all}" > 
          <option value="${creditCardInformationVO.creditCardid}">${creditCardInformationVO.creditCardid}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>
<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
									<ul>
										<li>
											<FORM METHOD="post" ACTION="CreditCardInformation.do"
<%-- 											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/chef/Chef.do" --%>
												name="form1">
											
																							


										
													<b>選擇會員編號:</b> 
													<select size="1" name="memid">
													<option value="">
														<c:forEach var="creditCardInformationVO" items="${creditCardInformationSvc.all}">
															<option value="${creditCardInformationVO.memid}">${creditCardInformationVO.memid}
														</c:forEach>
												</select> <input type="hidden" name="memid"><br> 
												
													<b>選擇信用卡編號:</b> 
													<select size="1" name="creditCardid">
													<option value="">
														<c:forEach var="creditCardInformationVO" items="${creditCardInformationSvc.all}">
															<option value="${creditCardInformationVO.creditCardid}">${creditCardInformationVO.creditCardid}
														</c:forEach>
												</select> <input type="hidden" name="creditCardid"><br> 
 
													<input type="submit" value="送出">
												<input type="hidden" name="action"
													value="listCreditCardInformation_ByCompositeQuery">
											</FORM>
										</li>
									</ul>


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
		$('select option').each(function() {
			$(this).prevAll('option[value="' + this.value + '"]').remove();
		});
	</script>
</body>
</html>