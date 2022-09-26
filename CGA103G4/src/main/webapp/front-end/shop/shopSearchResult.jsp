<%@page import="com.productPicture.model.ProductpicService"%>
<%@page import="com.promotions.model.*"%>
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


<%ProductpicVO productpicVO = (ProductpicVO)request.getAttribute("productpicVO");
%>

<%	
	
List<ProductVO> list = (List<ProductVO>)(request.getAttribute("list"));
    
    List <String> list2 = new ArrayList<String>();
    list.forEach((e) -> {
    	  list2.add(e.getPdid().toString());
    });    
    pageContext.setAttribute("list2",list2);
    
    List <String> list3 = new ArrayList<String>();
    list.forEach((e) -> {
    	  list3.add(e.getPdid().toString()+"000");
    });    
    pageContext.setAttribute("list3",list3);
    
%>
<%
	ProductsortService pdsSvc = new ProductsortService();
    List<ProductsortVO> listAllSort = pdsSvc.getAll();
    pageContext.setAttribute("listAllSort",listAllSort);
%>

<%
	PromotionsService pmSvc = new PromotionsService();
    List<PromotionsVO> promotionsVO = pmSvc.getAll();
    pageContext.setAttribute("listAllPromo",promotionsVO);
%>

<style>
header.header div.block nav.nav ul.nav_list>li>a {
    font-size: 1.8rem;
    text-decoration: none;
    display: inline-block;
    padding: 18px 20px;
    color: gray;
}
</style>




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
<!--   <link rel="shortcut icon" href="assets/images/favicon.ico"> -->

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
	<script src="assets/plugins/alertify/js/alertify.js"></script>
	<script src="assets/pages/alertify-init.js"></script>
	<script src="assets/js/app.js"></script>
	
	 <script src="../js/shop.js"></script>
  	<script src="../js/nav.js"></script>
  
<%@ include file="tools/header.jsp"%>
					
<div class="wrapper">
<div class="container-fluid" style = "margin-top:-410px; margin-left : 400px"><!-- Page-Title -->
<div class="row" >


<div class="col-lg-6" style = "top:440px ;left: -360px;" >
	<div class="card" style = "width: 325px">
		<div class="card-body">
			<h4 class="mt-0 header-title">商品查詢</h4>
			<br>
			<br>
			
				<ul class="nav flex-column">
					<li class="nav-item">價格排序
						<br>
						<a class="nav-link active" href="FrontEndListPdOnShelfByPrice.do">低 - 高</a>
						<a class="nav-link active" href="FrontEndListPdOnShelfByPriceDesc.do">高 - 低</a>
						<br>
						<br>
						<br>
					</li>
					
					<li>
					<a href="ListOneUserFavoritePd.do?memid=202&GoToMyCollection=GoToMyCollection">收藏清單</a>
					<br>
					<br>
					<br>
				
					</li>
					<li class="nav-item">
						<a class="nav-link" href="FrontEndListPdOnShelfByPdUpdate.do">最新上架</a>
						<br>
						<br>
						<br>
					</li>
					
					<li class="nav-item">
						<a class="nav-link" href="shop.jsp">回首頁</a>
						<br>
						<br>
						<br>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="col-sm-12" style = "margin-top:5px ;left: -120px;">
		<div class="page-title-box" style="width:1000px">
			<div class="btn-group pull-right">

			<c:forEach var="productsortVO" items="${listAllSort}">
				<a id = "" href="FrontEndIndexShowPdBSort.do?pdsid=${productsortVO.pdsid}" style = "left:0px">
						<button style = "width:100px; font-size: 2rem; top:4px; left:10px" type="button" class="btn btn-link waves-effect">${productsortVO.pdsName}</button>
					</a>
			</c:forEach>
				
				<ul>
					
					<li class="list-inline-item hide-phone app-search" style = "display: inline-block; margin-top:-5px; width:250px" >
					<form role="search" class="" method = "post" action = "FrontEndListAllPdByName.do" >
						<input type="text" Name = "pdName" placeholder="Search..." class="form-control" style= "display: inline;">
						<input class="btn btn-warning" type="submit" value = "查詢" style= "display: inline;">
					</form>
					</li>
				
				</ul>
			</div>
		</div>
	</div><!-- end page title end breadcrumb -->
		
		

	<div class="row">
         <c:forEach var="productVO" items="${list}">
       	<div class="col-md-6 col-lg-6 col-xl-3" style = "position: absoloute; top:60px;">
        	<div class="card" style = "height: 300px; ">      			
        		<a id = "" href="FrontEndPdDetail.do?pdid=${productVO.pdid}"> 
        			<img class="card-img-top img-fluid" src="Productpic.do?pdid=${productVO.pdid}" width="200" height = "160">
        			${productVO.pdName}
       			</a>${productVO.pdPrice}
       			<div class="card-body">
       				<p class="card-text">${productVO.pdDescription}
       				</p>
       					<div>
       						<button type="button" id="${productVO.pdid}000" class="btn btn-outline-success" >加入購物車</button>
       						<button type="button" id="removeFromCart${productVO.pdid}" class="btn btn-outline-success"  style="display:none;">移出購物車</button>
       						<button type="button" id="${productVO.pdid}"class="btn btn-warning waves-effect waves-light" >加入收藏清單</button>
       						<button type="button" id="removeFromTrack${productVO.pdid}"class="btn btn-warning waves-effect waves-light"  style="display:none;">移出收藏清單</button>	 	 
       					</div>       				     
               		 </div>
              	</div>
          </div>
        </c:forEach>	
     </div>
</div>
</div> 
</div>
	


<!-- =================================測試用隱藏input=================================			 -->
			<input id="collectPd" type = "hidden" value = "${list2}">
			<input id="collectPd2" type = "hidden" value =  "${list3}">
			
			<FORM method = "post" action = "ListOneUserFavoritePd.do">
				<input type = "hidden" name = "memid" value = "202">
				<input type = "hidden" name = "GoToMyCollection" value = "GoToMyCollection">
				<input type = "hidden">
			
			</FORM>
			
			<FORM method = "post" action = "ListMemberAllOrd.do">
			
				<input type = "hidden" name = "memid" value = "201">
				<input type = "hidden" name = "GoToMyOrders" value = "GoToMyOrders">
				<input type = "hidden">
			
			</FORM>
<!-- ==================================================================			 -->


<br>
<br>
<br>
<br>
<br>


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


let collectPd = document.getElementById("collectPd");
let UserPdCollect = JSON.parse(collectPd.value);
let ServletURL = new URL(endPointURL);

function result(){
	console.log("成功")
		{alertify.log("已加入清單")};
	}


for (let i of UserPdCollect){
	document.getElementById(i).addEventListener("click",function(){
	
		let searchParams = new URLSearchParams({
			 pdid: i,
			 memid:"202"
			});
		ServletURL.search = searchParams;		
		request(ServletURL.href,result);
		console.log(ServletURL.href);
	})
}
</script>

<script>
var MyPoint2 = "/front-end/shop/AddIntoCart.do";
var host2 = window.location.host;
var path2 = window.location.pathname;
var webCtx2 = path.substring(0, path.indexOf('/', 1));
var endPointURL2 = "http://" + host2 + webCtx2 + MyPoint2;


let collectPd2 = document.getElementById("collectPd2");
let UserPdCollect2 = JSON.parse(collectPd2.value);
let ServletURL2 = new URL(endPointURL2);
console.log(UserPdCollect2);

function result(){
	console.log("成功")
	{alertify.log("已加入清單")};
}


for (let j of UserPdCollect2){
	document.getElementById(j).addEventListener("click",function(){
		
		
		let searchParams2 = new URLSearchParams({
			 pdid: j/1000,
			 memid:"202",
			 pdNumber:"1"
			});
		ServletURL2.search = searchParams2;		
		request(ServletURL2.href,result);
		console.log(ServletURL2.href);
	});
}





// function addintoCart() {
//     $('#addIntoCart4093').css("display","none");
//     $('#removeFromCart4093').css("display","inline-block");
// }
// function removeFromCart() {
//     $('#addIntoCart4093').css("display","inline-block");
//     $('#removeFromCart4093').css("display","none");
// }
// function addIntoTrack() {
//     $('#addIntoTrack4093').css("display","none");
//     $('#removeFromTrack4093').css("display","inline-block");
// }
// function removeFromTrack() {
//     $('#addIntoTrack4093').css("display","inline-block");
//     $('#removeFromTrack4093').css("display","none");
// }
// ==========================		
	
</script>





</body>

</html>