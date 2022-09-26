<%@page import="com.productPicture.model.ProductpicService"%>
<%@page import="com.promotions.model.PromotionsService"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productSort.model.*"%>
<%@ page import="com.promotionsdetail.model.*"%>
<%@ page import="com.productPicture.model.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<%@ page import="com.promotionsdetail.*"%>
<%@ page import="com.productPicture.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant" dir="ltr">

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<head>

<meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta content="Admin Dashboard" name="description" />
  <meta content="Mannatthemes" name="author" />
  <title>《See Food》官方網站</title>
<link href="assets/plugins/alertify/css/alertify.css" rel="stylesheet" type="text/css">
  <link href="assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet">
	<link href="assets/plugins/fullcalendar/vanillaCalendar.css" rel="stylesheet" type="text/css" />
	<link href="assets/plugins/morris/morris.css" rel="stylesheet">
	<link href="assets/css/bootstrap.min.css" rel="stylesheet"type="text/css">
	<link href="assets/css/icons.css" rel="stylesheet" type="text/css">
	<link href="assets/css/style.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="css/common/all.css">
  <link rel="stylesheet" href="css/common/header.css">
  <link rel="stylesheet" href="css/common/footer.css">
  <link rel="stylesheet" href="css/common/main.css">
  <link rel="stylesheet" href="css/shop.css">
  <link rel="shortcut icon" href="assets/images/favicon.ico">

</head>

<body>
	<script src="assets/js/fetch.js"></script>
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
	<script src="assets/plugins/tiny-editable/mindmup-editabletable.js"></script>
	<script src="assets/plugins/tiny-editable/numeric-input-example.js"></script>
	<script src="assets/plugins/tabledit/jquery.tabledit.js"></script>
	<script src="assets/pages/tabledit.init.js"></script>
	<script src="assets/pages/modal-animation.init.js"></script>
	 <script src="../js/shop.js"></script>
  	<script src="../js/nav.js"></script>
<%@ include file="tools/header.jsp"%>
					
<div class="wrapper">
<div class="container-fluid"><!-- Page-Title -->
<div class="row">
	<div class="col-sm-12">
		<div class="page-title-box">
			<div class="btn-group pull-right">
				
			</div>
		</div>
	</div><!-- end page title end breadcrumb -->
<!-- ================================================================================================= -->
<div class="col-lg-4" style="width:1000px">
	<div class="card" style="position: relative;top:-50px; left: 35px;">
		<div class="card-body">
			<img class="card-img-top img-fluid" src="Productpic.do?pdid=${productVO.pdid}" width="200" height = "500" ><br>
				<h4 class="card-title font-20 mt-0">${productVO.pdName}</h4>
					<p class="">${productVO.pdDescription}</p>
						<c:set var="string1" value="${productVO.pdUpdate}"/>
						<c:set var="string2" value="${fn:replace(string1, 'T', ' ')}" />
			<p class="card-text"><small class="text-muted">${string2}
			</small>
			</p>
		</div>
			
			
		<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
			<div class="carousel-inner">
				
				<div class="carousel-item active">
					<img class="d-block w-100" src="Productpic.do?pdid=${productVO.pdid}" width = "480" >
				</div>
				
				<c:forEach var="productpicVO" items="${productVO.getPicsNo()}" >
				<div class="carousel-item">
					<img alt="" src="showPicsByPicNo.do?pdPicid=${productpicVO.pdPicid}" width = "480" >
				</div>
				</c:forEach>
				
			</div>
		</div>
				<a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a>
				
				<a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
	</div>
	<div class="row" style="position: absolute; top:0px; left: 1000px; width: 330px; height: 500px" >
		<div class="col-12" >
			<div class="card" >
				<div class="card-body">
				
				<table id="mainTable" class="table table-striped m-b-0">
						<tbody>
							<tr>
								<td>商品名稱</td>
								<td><h4><%=productVO.getPdName()%></h4></td>
							</tr>
							
							<tr>
								<td>商品價格</td>
								<td><h4><%=productVO.getPdPrice()%></h4></td>
							</tr>
							
							<tr>
								<td>優惠價格</td>
								<td><h4><%=productVO.getPdDiscountPrice()%></h4></td>
							</tr>
							
							<tbody>
								<h3>商品規格</h3><%=productVO.getPdDescription()%>
							</tbody>
						</tbody>
					</table>
					<div>
						<br>
			
					 			<div>
					 				<P><input type="number" id="cartNumber" class="" onclick="add()" value = "1"><P><br>
					 				
       									<button type="button" id="addIntoCart" value ="${productVO.pdid}" class="btn btn-outline-success" onclick="addIntoCart()">加入購物車</button>
       									<button type="button" id="removeFromCart" value ="${productVO.pdid}" class="btn btn-secondary waves-effect" onclick="removeFromCart()" style="display:none;">移出購物車</button>
       									<button type="button" id="addIntoCollection" value = "${productVO.pdid}" onclick= "addintoCollection()" class="btn btn-warning waves-effect waves-light">
       									加入收藏清單</button>
       									<button type="button" id="removeFromCollection"value = "${productVO.pdid}" class="btn btn-secondary waves-effect" onclick="removeFromCollection()" style="display:none;">移出收藏清單</button>	 	 
       							</div>       
       						<br>
						<br>	
					<div>
						<h2>相關食譜</h2>
					</div>
				</div>			
			</div>
		</div>
	</div>
</div>

<!-- ======================================================================================= -->
</div>
</div>
</div>
</div>
                    
                   
        	
     
  <%@ include file="tools/footer.jsp"%>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/popper.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>

<script src="assets/js/modernizr.min.js"></script>
<script src="assets/js/waves.js"></script>
<script src="assets/js/jquery.nicescroll.js"></script>
<script src="assets/plugins/alertify/js/alertify.js"></script>
<script src="assets/pages/alertify-init.js"></script>
<script src="assets/js/app.js"></script>
<div class="alertify-logs"></div>

<script>

var MyPoint = "/front-end/shop/FavoriteProductServlet.do";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "http://" + host + webCtx + MyPoint;



let ServletURL = new URL(endPointURL);

function result(){
	{alertify.log("商品已成功加入!!")};
}

var i = document.getElementById("addIntoCollection")
	let pdid = i.value;
	console.log(pdid)	
	i.addEventListener("click",function(e){

		let searchParams = new URLSearchParams({
			 pdid: pdid,
			 memid:"202"
			});
		ServletURL.search = searchParams;		
		request(ServletURL.href,result);
		console.log(ServletURL.href);
	});

function addintoCollection() {
    $('#addIntoCollection').css("display","none");
    $('#removeFromCollection').css("display","inline-block");
}
// ========================================移出收藏========================
	

var MyPoint2 = "/front-end/shop/DeleteCollection.do";
var host2 = window.location.host;
var path2 = window.location.pathname;
var webCtx2 = path.substring(0, path.indexOf('/', 1));
var endPointURL2 = "http://" + host2 + webCtx2 + MyPoint2;

let ServletURL2 = new URL(endPointURL2);


var i2 = document.getElementById("removeFromCollection")
	let pdid2 = i2.value;
	console.log(pdid2)	
	i2.addEventListener("click",function(e){

		let searchParams2 = new URLSearchParams({
			 pdid: pdid2,
			 memid:"202"
			});
		ServletURL2.search = searchParams2;		
		request(ServletURL2.href,result);
		console.log(ServletURL2.href);
	});

function removeFromCollection() {
    $('#addIntoCollection').css("display","inline-block");
    $('#removeFromCollection').css("display","none");
}
</script>

<script>

var MyPoint3 = "/front-end/shop/AddIntoCart.do";
var host3 = window.location.host;
var path3 = window.location.pathname;
var webCtx3 = path.substring(0, path.indexOf('/', 1));
var endPointURL3 = "http://" + host3 + webCtx3 + MyPoint3;

let ServletURL3 = new URL(endPointURL3);


function result(){
	{alertify.log("商品已成功加入!!")};
}

var num = 0;
  $('#cartNumber').val(num);
  function add() {

   if (num >= 10) {
    alert('最多可新增10個');
    return;
   }
   num++;

   $('#cartNumber').val(num);

  }

	var i3 = document.getElementById("addIntoCart");
	var pdNumber = document.getElementById("cartNumber");

	let pdid3 = i3.value;
	i3.addEventListener("click",function(e){

	let searchParams3 = new URLSearchParams({
		 pdid: pdid3,
		 memid:"202",
		 pdNumber:num
		});
	ServletURL3.search = searchParams3;		
	request(ServletURL3.href,result);
	console.log(ServletURL3.href);
});

function addIntoCart() {
$('#addIntoCart').css("display","none");
$('#removeFromCart').css("display","inline-block");
}
</script>
<script>

var MyPoint4 = "/front-end/shop/deleteCartPd.do";
var host4 = window.location.host;
var path4 = window.location.pathname;
var webCtx4 = path.substring(0, path.indexOf('/', 1));
var endPointURL4 = "http://" + host4 + webCtx4 + MyPoint4;

let ServletURL4 = new URL(endPointURL4);

var i4 = document.getElementById("removeFromCart");
// var pdNum = document.getElementById("cartNumber");

	let pdid4 = i4.value;
	// let pdNumber = pdNum.value;
	
	
i4.addEventListener("click",function(e){

	let searchParams4 = new URLSearchParams({
		 pdid: pdid4,
		 memid:"202"
		});
	ServletURL4.search = searchParams4;		
	request(ServletURL4.href,result);
	console.log(ServletURL4.href);
});

function removeFromCart() {
$('#addIntoCart').css("display","inline-block");
$('#removeFromCart').css("display","none");
}














// function removeFromCart() {
// $('#addIntoCart').css("display","inline-block");
// $('#removeFromCart').css("display","none");
// }

</script>




<!-- // var MyPoint = "/front-end/shop/FavoriteProductServlet.do"; -->
<!-- // var host = window.location.host; -->
<!-- // var path = window.location.pathname; -->
<!-- // var webCtx = path.substring(0, path.indexOf('/', 1)); -->
<!-- // var endPointURL = "http://" + host + webCtx + MyPoint; -->

<!-- // let ServletURL = new URL(endPointURL); -->

<!-- // function result(){ -->
<!-- // 	alert("成功"); -->
<!-- // } -->

<!-- // let pdid = document.getElementById("addIntoCollection").addEventListener("click",function(){ -->
	
<!-- // 		let searchParams = new URLSearchParams({ -->
<!-- // 			 pdid: pdid, -->
<!-- // 			 memid:"202" -->
<!-- // 			}); -->
<!-- // 		ServletURL.search = searchParams;		 -->
<!-- // 		request(ServletURL.href,result); -->
<!-- // 		console.log(ServletURL.href); -->
<!-- // 	}) -->


</body>

</html>