<%@page import="com.favoriteProduct.model.FavoriteProductVO"%>
<%@page import="com.favoriteProduct.model.FavoriteProductService"%>
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

<style>




</style>

<head>

  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta content="Admin Dashboard" name="description" />
  <meta content="Mannatthemes" name="author" />
  <title>《See Food》官方網站</title>

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
<div class="col-lg-6">
	<div class="card" Style="width:1100px; left: 150px; top: -30px">
		<div class="card-body">
			<h2 class="mt-0 header-title">商品收藏</h2>
				
				<table class="table table-bordered">
					<thead>
					<tr>
						<th style= "font-weight: 0;">商品名稱</th>
						<th style= "font-weight: 0;">商品價格</th>
						<th style= "font-weight: 0;">優惠價格</th>
						<th style= "font-weight: 0;"></th>
						<th style= "font-weight: 0;"></th>
						<th style= "font-weight: 0;"></th>
					</tr>
					
					</thead>
					<tbody>
					<c:forEach var="favoriteProductVO" items="${favoriteProductVO}">
						<tr>
							<td>${favoriteProductVO.productVO.pdName}</td>
							<td>${favoriteProductVO.productVO.pdPrice}</td>
							<td>${favoriteProductVO.productVO.pdDiscountPrice}</td>
							<td style="word-wrap:break-word;word-break:break-all;" width="400px">${favoriteProductVO.productVO.pdDescription}</td>
							
							<td>
							<c:if test="${favoriteProductVO.productVO.pdStatus == 1}" var="上架中" scope="page">上架中
							</c:if>
							<c:if test="${favoriteProductVO.productVO.pdStatus == 0}" var="上架中" scope="page">已下架
							</c:if>
							</td>
							<td><img class="" src="Productpic.do?pdid=${favoriteProductVO.productVO.pdid}" width="200" height = "160"></td>
							<td>
							<a id = "cancelCollect" href= "DeleteCollection.do?memid=203&pdid=${favoriteProductVO.productVO.pdid}"><button type="button" class="btn btn-outline-danger waves-effect waves-light">刪除</button></a>
							</td>
						</tr>
					</c:forEach>
					</table>
				</div>
			</div>
		</div>
<!-- ================================================================================================= -->
	</div>
	</div>
</div>
                    
                   
        	
     
  <%@ include file="tools/footer.jsp"%>



</body>

</html>