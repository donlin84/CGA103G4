<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productSort.model.*"%>


<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>
<!DOCTYPE html>
<html lang="zh-Hant-TW">

<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
  <title>seefood後台</title>
  <meta content="Admin Dashboard" name="description" />
  <meta content="Mannatthemes" name="author" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <link rel="shortcut icon" href="assets/images/favicon.ico">
  <link href="assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet">
  <link href="assets/plugins/fullcalendar/vanillaCalendar.css" rel="stylesheet" type="text/css" />
  <link href="assets/plugins/morris/morris.css" rel="stylesheet">
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
  <link href="assets/css/icons.css" rel="stylesheet" type="text/css">
  <link href="assets/css/style.css" rel="stylesheet" type="text/css">

</head>
<style>
      img.preview{
        width: 250px;
      }
      ul{
    list-style: none;
    margin: 0;
    padding: 0;
  } 
    ul > li{
     display: inline-block;
      vertical-align: text-bottom
}
 
</style>
<body>
<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
  <title>seefood後台</title>
  <meta content="Admin Dashboard" name="description" />
  <meta content="Mannatthemes" name="author" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <link rel="shortcut icon" href="assets/images/favicon.ico">
  <link href="assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet">
  <link href="assets/plugins/fullcalendar/vanillaCalendar.css" rel="stylesheet" type="text/css" />
  <link href="assets/plugins/morris/morris.css" rel="stylesheet">
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
  <link href="assets/css/icons.css" rel="stylesheet" type="text/css">
  <link href="assets/css/style.css" rel="stylesheet" type="text/css">

</head>

<body>
  <!-- Loader -->
  <div id="preloader">
    <div id="status">
      <div class="spinner"></div>
    </div>
  </div><!-- Navigation Bar-->
<%@ include file="header.jsp" %>
 	<div class="wrapper">
		<div class="container-fluid">
			<!-- Page-Title -->
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
						<h4 class="page-title">&emsp;新增商品</h4>
					</div>
				</div>
			</div>
			<!-- end page title end breadcrumb -->
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
  <!-- Footer -->
  <%@ include file="footer.jsp" %>
  <!-- jQuery -->
  <script src="assets/js/jquery.min.js"></script>
  <script src="assets/js/popper.min.js"></script>
  <script src="assets/js/bootstrap.min.js"></script>
  <script src="assets/js/modernizr.min.js"></script>
  <script src="assets/js/waves.js"></script>
  <script src="assets/js/jquery.nicescroll.js"></script>
  <script src="assets/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
  <script src="assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
  <script src="assets/plugins/skycons/skycons.min.js"></script>
  <script src="assets/plugins/fullcalendar/vanillaCalendar.js"></script>
  <script src="assets/plugins/raphael/raphael-min.js"></script>
  <script src="assets/plugins/morris/morris.min.js"></script>
  <script src="assets/js/app.js"></script> 

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
 <FORM METHOD="post" action= "ProductServlet.do" name="form1" enctype="multipart/form-data">

<jsp:useBean id="pdSortSvc" scope="page" class="com.productSort.model.ProductsortService" />
<div>
<Label for = "pdsid">&emsp;商品類別: </Label>

<select size="1" name="Pdsid">
<c:forEach var="ProductsortVO" items="${pdSortSvc.all}">
			<option value="${ProductsortVO.pdsid}" ${(ProductsortVO.pdsid==ProductsortVO.pdsid)? 'selected':'' } >${ProductsortVO.pdsName}
</c:forEach>

 </select>
</div>

<div>
<Label>&emsp;商品名稱:
  <input type="text" id="pdName" name = "PdName" 
  		value="<%= (productVO==null)? "" : productVO.getPdName()%>" required/> 
</Label>
</div>


<div>
<Label>&emsp;商品原價:
  <input type="text" id="pdPrice" name = "PdPrice" 
  		value = "<%= (productVO==null)? "" : productVO.getPdPrice()%>" required/>
</Label>
</div>

<div>
<Label>&emsp;優惠價格:
  <input type="text" id="pdDiscountPrice" name = "PdDiscountPrice"  
  		value = "<%= (productVO==null)? "" : productVO.getPdDiscountPrice()%>" />
</Label>
</div>

<div>
  <label for="pdStatus">&emsp;商品狀態</label>
  
  <input type = "radio" id ="pdStatus" name = "PdStatus"
     value = "0" required>
    <label for="productStateUn">暫不上架</label>
  <input type="radio" id="pdStatus" name="PdStatus"
     value="1">
<label for="pdStatus">直接上架</label> 

</div>

<div>
<Label>&emsp;商品資訊:</Label>
  &emsp;
 </div>

 &emsp; <textarea id="pdDescription" name = "PdDescription" 
          rows="10"
          cols="40"
          value="<%= (productVO==null)? "" : productVO.getPdDescription()%>"
           >
</textarea>
<br>
<Label>&emsp;圖片上傳:</Label>
  <input type="file" id="pdPic" name = "PdPic1" multiple>
	<input type="hidden" name="action" value="insert">
	<input type="hidden" name="action2" value="addpic">
	<input type="submit" value="送出新增"> 
</FORM>
<br>
<br>
<br>
<br>


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
<%-- 	<%@ include file="../backend/footer.jsp"%> --%>
	<!-- End Footer -->





<script>





window.addEventListener("load", function(){
  var pdpic_el = document.getElementById("pdPic");
  pdpic_el.addEventListener("change", function(e){          

    var picture_list = document.getElementsByClassName("picture_list")[0];
    picture_list.innerHTML = ""; 
    
 
    for (let i = 0; i < this.files.length; i++) {
      let reader = new FileReader(); 
      reader.readAsDataURL(this.files[i]); 
      reader.addEventListener("load", function () {
        console.log(reader.result);
        let li_html = `
                <li><img src="${reader.result}" class="preview"></li>
              `;
        picture_list.insertAdjacentHTML("beforeend", li_html);
      });
    }
  });
});
</script>

</body>

</html>