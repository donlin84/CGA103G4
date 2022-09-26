<%@page import="com.promotions.model.PromotionsVO"%>
<%@page import="com.productPicture.model.ProductpicService"%>
<%@page import="com.promotions.model.PromotionsService"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productSort.model.*"%>
<%@ page import="com.promotionsdetail.model.*"%>
<%@ page import="com.productPicture.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.cartdetail.model.*"%>
<%@ page import="com.membercoupon.model.*"%>

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
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<%
	MemberVO memVO =(MemberVO) session.getAttribute("memVO");
	Integer memid = 203;
	CartDetailService cartSvc = new CartDetailService();
	List<CartDetailVO> cartDetailVOs = cartSvc.getOnes(memid);
	List<Integer> cartPdids = new ArrayList<Integer>();
	cartDetailVOs.forEach((e) -> {
		cartPdids.add(e.getPdid());
	});
	request.setAttribute("cartPdids", cartPdids);
	request.setAttribute("memid", memid);
	
	
	ProductService productSvc = new ProductService();
    List<ProductVO> list = productSvc.listByPdStatus(1);
    pageContext.setAttribute("list",list);
    
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

<style type="text/css" id="alertifyCSS">.alertify-logs>*{padding:12px 24px;color:#fff;box-shadow:0 2px 5px 0 rgba(0,0,0,.2);border-radius:1px}.alertify-logs>*,.alertify-logs>.default{background:rgba(0,0,0,.8)}.alertify-logs>.error{background:rgba(244,67,54,.8)}.alertify-logs>.success{background:rgba(76,175,80,.9)}.alertify{position:fixed;background-color:rgba(0,0,0,.3);left:0;right:0;top:0;bottom:0;width:100%;height:100%;z-index:1}.alertify.hide{opacity:0;pointer-events:none}.alertify,.alertify.show{box-sizing:border-box;transition:all .33s cubic-bezier(.25,.8,.25,1)}.alertify,.alertify *{box-sizing:border-box}.alertify .dialog{padding:12px}.alertify .alert,.alertify .dialog{width:100%;margin:0 auto;position:relative;top:50%;transform:translateY(-50%)}.alertify .alert>*,.alertify .dialog>*{width:400px;max-width:95%;margin:0 auto;text-align:center;padding:12px;background:#fff;box-shadow:0 2px 4px -1px rgba(0,0,0,.14),0 4px 5px 0 rgba(0,0,0,.098),0 1px 10px 0 rgba(0,0,0,.084)}.alertify .alert .msg,.alertify .dialog .msg{padding:12px;margin-bottom:12px;margin:0;text-align:left}.alertify .alert input:not(.form-control),.alertify .dialog input:not(.form-control){margin-bottom:15px;width:100%;font-size:100%;padding:12px}.alertify .alert input:not(.form-control):focus,.alertify .dialog input:not(.form-control):focus{outline-offset:-2px}.alertify .alert nav,.alertify .dialog nav{text-align:right}.alertify .alert nav button:not(.btn):not(.pure-button):not(.md-button):not(.mdl-button),.alertify .dialog nav button:not(.btn):not(.pure-button):not(.md-button):not(.mdl-button){background:transparent;box-sizing:border-box;color:rgba(0,0,0,.87);position:relative;outline:0;border:0;display:inline-block;-ms-flex-align:center;-ms-grid-row-align:center;align-items:center;padding:0 6px;margin:6px 8px;line-height:36px;min-height:36px;white-space:nowrap;min-width:88px;text-align:center;text-transform:uppercase;font-size:14px;text-decoration:none;cursor:pointer;border:1px solid transparent;border-radius:2px}.alertify .alert nav button:not(.btn):not(.pure-button):not(.md-button):not(.mdl-button):active,.alertify .alert nav button:not(.btn):not(.pure-button):not(.md-button):not(.mdl-button):hover,.alertify .dialog nav button:not(.btn):not(.pure-button):not(.md-button):not(.mdl-button):active,.alertify .dialog nav button:not(.btn):not(.pure-button):not(.md-button):not(.mdl-button):hover{background-color:rgba(0,0,0,.05)}.alertify .alert nav button:not(.btn):not(.pure-button):not(.md-button):not(.mdl-button):focus,.alertify .dialog nav button:not(.btn):not(.pure-button):not(.md-button):not(.mdl-button):focus{border:1px solid rgba(0,0,0,.1)}.alertify .alert nav button.btn,.alertify .dialog nav button.btn{margin:6px 4px}.alertify-logs{position:fixed;z-index:1}.alertify-logs.bottom,.alertify-logs:not(.top){bottom:16px}.alertify-logs.left,.alertify-logs:not(.right){left:16px}.alertify-logs.left>*,.alertify-logs:not(.right)>*{float:left;transform:translateZ(0);height:auto}.alertify-logs.left>.show,.alertify-logs:not(.right)>.show{left:0}.alertify-logs.left>*,.alertify-logs.left>.hide,.alertify-logs:not(.right)>*,.alertify-logs:not(.right)>.hide{left:-110%}.alertify-logs.right{right:16px}.alertify-logs.right>*{float:right;transform:translateZ(0)}.alertify-logs.right>.show{right:0;opacity:1}.alertify-logs.right>*,.alertify-logs.right>.hide{right:-110%;opacity:0}.alertify-logs.top{top:0}.alertify-logs>*{box-sizing:border-box;transition:all .4s cubic-bezier(.25,.8,.25,1);position:relative;clear:both;backface-visibility:hidden;perspective:1000;max-height:0;margin:0;padding:0;overflow:hidden;opacity:0;pointer-events:none}.alertify-logs>.show{margin-top:12px;opacity:1;max-height:1000px;padding:12px;pointer-events:auto}</style>
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
  	
<%@ include file="../tools/header.jsp"%>
								
<div class="wrapper">
<div class="container-fluid" style = "margin-top:-390px; margin-left : 400px"><!-- Page-Title -->
<div class="row" >


<div class="col-lg-6" style = "top:420px ;left: -360px;" >
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
					
					<li class="nav-item">
						優惠活動
						<br>
						<c:forEach var="promotionsVO" items="${listAllPromo}">
							<a class="nav-link" href=".do?${promotionsVO.pmid}">${promotionsVO.pmName}</a>
						</c:forEach>
						<br>
						<br>
						<br>
					</li>
					
					<li class="nav-item">
						<a class="nav-link" href="FrontEndListPdOnShelfByPdUpdate.do">最新上架</a>
						<br>
						<br><br>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="shop.jsp">回首頁</a>
						<br>			
					</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="col-sm-12" style = "margin-top:0px ;left: -120px;">
		<div class="page-title-box" style="width:1000px">
			<div class="btn-group pull-right">

			<c:forEach var="productsortVO" items="${listAllSort}">

					<a id = "" href="FrontEndIndexShowPdBSort.do?pdsid=${productsortVO.pdsid}" style = "left:0px">
						<button style = "width:100px; font-size: 2rem; top:4px; left:10px" type="button" class="btn btn-link waves-effect">${productsortVO.pdsName}</button>
					</a>

			</c:forEach>
				
				<ul>
					<li class="list-inline-item hide-phone app-search" style = "display: inline-block; margin-top:-5px">
					<form role="search" class="">
						<input type="text" placeholder="Search..." class="form-control">
						<a href=""><i class="fa fa-search"></i></a>
					</form>
					</li>
				
				</ul>
			</div>
		</div>
	</div><!-- end page title end breadcrumb -->
		
		
<div class = "pagecount"><%@include file="FrontEndpage1.file" %>
</div>
	<div class="row" >
	
	
	
	
	
        <c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
       	<div class="col-md-6 col-lg-6 col-xl-3" style = "position: absoloute; top:63px;">
        	<div class="card" style = "height: 300px;">      			
        		<a id = "" href="FrontEndPdDetail.do?pdid=${productVO.pdid}"> 
        			<img class="card-img-top img-fluid" src="Productpic.do?pdid=${productVO.pdid}" width="200" height = "160">
        			${productVO.pdName}
       			</a>${productVO.pdPrice}
       			<div class="card-body">
       				<p class="card-text">${productVO.pdDescription}
       				</p>
       					<div>
       					
<%-- 				<jsp:useBean id="memberSvc" scope="page" lass="com.member.model.MemberService" /> --%>
				<c:forEach var="memberVO" items="${memberSvc.all}">
					<c:if test="${memberVO.memAccount == account}">
						<c:set var="memid" value="${memberVO.memid}" />
						<%-- 							${memberVO.memAccount} --%>
					</c:if>
				</c:forEach>

				<c:choose>

					<c:when test="${empty account}">
					<a href="../member/frontEndLogin.jsp">
					<button type="button" id="${productVO.pdid}000" class="btn btn-outline-success" >加入購物車</button>
					</a>
					<a href="../member/frontEndLogin.jsp">
					<button type="button" id="${productVO.pdid}"class="btn btn-warning waves-effect waves-light" >加入收藏清單</button>
					</a>
					</c:when>

					<c:otherwise>
					
					<button type="button" id="${productVO.pdid}000" class="btn btn-outline-success" >加入購物車</button>
					<button type="button" id="${productVO.pdid}"class="btn btn-warning waves-effect waves-light" >加入收藏清單</button>
					</c:otherwise>
				</c:choose>
       			
       						
       						<button type="button" id="removeFromCart${productVO.pdid}" class="btn btn-outline-success"  style="display:none;">移出購物車</button>
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
	
<div class = "nextpage" style="top:50px; margin-left: 460px; width: 500px" ><%@include file="page2.file" %></div>

<!-- =================================測試用隱藏input=================================			 -->
			<input id="collectPd" type = "hidden" value = "${list2}">
			<input id="collectPd2" type = "hidden" value =  "${list3}">
			
			<FORM method = "post" action = "ListOneUserFavoritePd.do">
				<input type = "hidden" name = "memid" value = "${memid}">
				<input type = "hidden" name = "GoToMyCollection" value = "GoToMyCollection">
				<input type = "submit">
			
			</FORM>
			
			<FORM method = "post" action = "ListMemberAllOrd.do">
			
				<input type = "hidden" name = "memid" value = "201">
				<input type = "hidden" name = "GoToMyOrders" value = "GoToMyOrders">
				<input type = "submit">
			
			</FORM>
<!-- ==================================================================			 -->
<div class="alertify-logs">


</div>

<br>
<br>
<br>
<br>
<br>


<%@ include file="../tools/footer.jsp"%>
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
	{alertify.log("已加入清單")};
}

for (let i of UserPdCollect){
	document.getElementById(i).addEventListener("click",function(){
		
		
		let searchParams = new URLSearchParams({
			 pdid: i,
			 memid:${memid}
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
var webCtx2 = path2.substring(0, path2.indexOf('/', 1));
var endPointURL2 = "http://" + host2 + webCtx2 + MyPoint2;

console.log(${cartPdids});


let collectPd2 = document.getElementById("collectPd2");
let UserPdCollect2 = JSON.parse(collectPd2.value);
let ServletURL2 = new URL(endPointURL2);
// console.log(UserPdCollect2);

function result(){

{alertify.log("已加入清單")};
}

for (let j of UserPdCollect2){
	document.getElementById(j).addEventListener("click",function(){
		
		
		let searchParams2 = new URLSearchParams({
			 pdid: j/1000,
			 memid:${memid},
			 pdNumber:"1",
			 cartPdids:${cartPdids}
			 
			});
		ServletURL2.search = searchParams2;		
		request(ServletURL2.href,result);
		console.log(ServletURL2.href);
	});
}
</script>
<script>


// function addintoCart() {
//     $('#addInto').css("display","none");
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