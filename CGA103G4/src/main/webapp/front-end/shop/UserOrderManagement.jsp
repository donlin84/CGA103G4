<%@page import="com.favoriteProduct.model.FavoriteProductVO"%>
<%@page import="com.favoriteProduct.model.FavoriteProductService"%>
<%@page import="com.productPicture.model.ProductpicService"%>
<%@page import="com.promotions.model.PromotionsService"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productSort.model.*"%>
<%@ page import="com.promotionsdetail.model.*"%>
<%@ page import="com.productPicture.model.*"%>
<%@page import="com.orderdetail.model.OrderDetailVO"%>
<%@page import="com.orders.model.OrdersVO"%>
<%@page import="com.orders.model.OrdersService"%>
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
			<div class="card" style = "width: 800px; left: 300px">
                 <div class="card-body">
                             <h4 class="mt-0 header-title">
                             訂單明細&emsp;&emsp;
                             	<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                            		查看明細
                            	</button>
                             </h4>
                             <c:forEach var="ordersVO" items="${ordersVO}">
                             <table class="table table-bordered">
								<thead>
									<tr>
										<th style= "font-weight: 0;">訂單編號</th>
										<th style= "font-weight: 0;">收件人</th>
										<th style= "font-weight: 0;">總計</th>
										<th style= "font-weight: 0;">訂單狀態</th>
										<th style= "font-weight: 0;">訂單成立時間</th>
										<th style= "font-weight: 0;">收貨地址</th>
										<th style= "font-weight: 0;">收件人電話</th>
									</tr>
					
									</thead>
									<tbody>
								
									<tr>
										<td>${ordersVO.ordid}</td>
										<td>${ordersVO.ordRecipient}</td>
										<td>${ordersVO.ordTotal}</td>
										
									
										
										<td>
										<c:if test="${ordersVO.ordStatus == 0}" var="未完成" scope="page">
										未完成<br>
										<a onclick="return confirmAct();" href="UserCancelOrder.do?ordStatus=3&ordid=${ordersVO.ordid}">
										<button type="button"  class="btn btn-dark waves-effect waves-light" id = "cancelButton" >取消</button>
										</a>
										</c:if>
										<c:if test="${ordersVO.ordStatus == 1}" var="已完成" scope="page">
										已完成</c:if>
										<c:if test="${ordersVO.ordStatus == 2}" var="配送中" scope="page">
										配送中</c:if>
										<c:if test="${ordersVO.ordStatus == 3}" var="已取消" scope="page">
										已取消</c:if>
			
										
										
										</td>
										
										
										<td>${ordersVO.ordCreate}</td>
										<td>${ordersVO.ordAddress}</td>
										<td>${ordersVO.recPhone}</td>
									<tr>
									</table>
                            				<div class="collapse show" id="collapseExample" style="">
                            				 	<div class="card card-body mb-0">
                            					<table>
												<tr>
													<th>商品名稱</th>
													<th>商品單價</th>
													<th>優惠單價</th>
													<th>數量</th>
												</tr>
												<tr>
												<c:forEach var="orderDetailVO" items="${ordersVO.getAllDetailByOrdid(ordersVO.getOrdid())}">
												<tbody>
												<tr>
													<td class="p-3">${orderDetailVO.productVO.pdName}</td>
													<td class="p-3">${orderDetailVO.detailPrice}</td>
													<td class="p-3">${orderDetailVO.detailGoodPrice}</td>
													<td class="p-3">${orderDetailVO.detailNumber}</td>
												</tr>
												</tbody>
												</c:forEach>
											</table>
										</div>
									</div>
								</c:forEach>
						</div>
					</div>
				</div>
				
			</div>
		</div>
<!-- ================================================================================================= -->
	</div>

                    
                   
        	
     
  <%@ include file="tools/footer.jsp"%>

<script type="text/javascript">

function confirmAct()
{
    if(confirm('確定要執行此操作嗎?'))
    {
        return true;
    }
    return false;
}

</script>

</body>

</html>