<%@page import="com.orders.model.OrdersVO"%>
<%@page import="com.product.model.ProductVO"%>
<%@ page import="com.productPicture.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO");
%>

<STYLE>

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


.table{
	text-align: left;
	width: 100%;
}

.productdescription {
	text-align: left;
	WORD-BREAK: break-all;
	WORD-WRAP: break-word;
}
</STYLE>


<html>
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
	</div>
	<!-- Navigation Bar-->
	<%@ include file="header.jsp"%>
	<div class="wrapper">
		<div class="container-fluid">
			<!-- Page-Title -->
			<div class="row">
				<div class="col-sm-12">
					<div class="page-title-box">
						<div class="btn-group pull-left">
							<ol class="breadcrumb hide-phone p-0 m-0">
								<li class="breadcrumb-item"><a href="#">後台首頁</a></li>
								<li class="breadcrumb-item active"><a href="#">某頁面</a></li>
								<li class="breadcrumb-item active">當前頁面</li>
							</ol>
						</div>
					</div>
				</div>
			</div>
			<!-- end page title end breadcrumb -->
			<!--end row-->
		</div>
		<!-- end container -->
	</div>
	<!-- end wrapper -->
	<!-- Footer -->
	<%@ include file="footer.jsp"%>
	<!-- End Footer -->
	<!-- jQuery -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/popper.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/modernizr.min.js"></script>
	<script src="assets/js/waves.js"></script>
	<script src="assets/js/jquery.nicescroll.js"></script>
	<script src="assets/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script
		src="assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="assets/plugins/skycons/skycons.min.js"></script>
	<script src="assets/plugins/fullcalendar/vanillaCalendar.js"></script>
	<script src="assets/plugins/raphael/raphael-min.js"></script>
	<script src="assets/plugins/morris/morris.min.js"></script>
	<script src="assets/js/app.js"></script>
	<script src="assets/plugins/tiny-editable/mindmup-editabletable.js"></script>
	<script src="assets/plugins/tiny-editable/numeric-input-example.js"></script>
	<script src="assets/plugins/tabledit/jquery.tabledit.js"></script>
	<script src="assets/pages/tabledit.init.js"></script>

	<!-- Page-Title -->
	<div class="row">
		<div class="col-sm-12">
			<div class="page-title-box">
				<div class="btn-group pull-right"></div>

			</div>
		</div>
	</div>
	<!-- end page title end breadcrumb -->
	<div class="row">
		<div class="col-12">
			<div class="card"  style="margin:0 150px">
				<div class="card-body">
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
 <!-- ============================================================== -->
<h3>&emsp;修改訂單</h3>

				<FORM METHOD="post" action= "OrderInfoModify.do" name="form1">
					
					<table id="mainTable" class="table table-striped m-b-0">
						<tbody>
       						 <tr>
       						 	<td>&emsp;訂單編號:</td>
       						 	<td>
       						 	
       						 	<input type = "hidden" name = "OrdStatus" value ="${ordersVO.ordStatus}">
       						 	<input type = "hidden" name = "OrdPayment" value ="${ordersVO.ordPayment}">
       						 	<input type = "hidden" name = "OrdSubTotal" value ="${ordersVO.ordSubTotal}">
       						 	
       						 		<input type="text" id="" name = "Ordid" value ="${ordersVO.ordid}"  style="background-color:rgb(185, 185, 185);" readonly>
       						 		<br>
       						 	</td>
       						 </tr>
       						 
       						 <tr>
     					   		<td>&emsp;會員編號:</td>
     					   		
     					   		<td>
     					   			<input type="text" id="" name = "Memid" value ="${ordersVO.memid}"  style="background-color:rgb(185, 185, 185);" readonly>
     					   			<br>
     					   		</td>
     					  	</tr>
     					  	
					     	<tr>
					        	<td>&emsp;總金額:</td>
					        	<td>
					        		<input type="text" id="" name = "OrdTotal" value ="${ordersVO.ordTotal}"  style="background-color:rgb(185, 185, 185);"  readonly>
					        		<br>
					        	</td>
					        </tr>
					        
					        <tr>
					        	<td>&emsp;優惠券編號:</td>
					        	<td>
					        		<input type="text" id="" name = "MemCpid" value ="${ordersVO.memCpid}"  style="background-color:rgb(185, 185, 185);" readonly>
					        		<br>
					        	</td>
        						</tr>
     						 <tr>
     						   	<td>&emsp;收件人:</td>
     						   	<td>
      						  		<input type="text" id="" name = "OrdRecipient" value ="${ordersVO.ordRecipient}">
     						 		<br>
     							</td>
     						 </tr>
     						 
     						 <tr>
     						   	<td>&emsp;收件人地址:</td>
     						   	<td>
      						  		<input type="text" id="" name = "OrdAddress" value ="${ordersVO.ordAddress}">
     						 		<br>
     							</td>
     						 </tr>
     						 
     						 <tr>
     						   	<td>&emsp;收件人電話:</td>
     						   	<td>
     						   		<input type="text" id="pdid" name = "RecPhone" value ="${ordersVO.recPhone}"  >
     						   		<br>
     						   	</td>
     						 </tr>
     						   <tr>
     						   	<td>&emsp;配送方式:</td>
     						   		<td>
          							 	<input type = "radio" id ="" name = "OrdDelivery"value = "1" >
            	 						<label for="productStateUn">超商取貨</label>
         		 						<input type="radio" id="" name="OrdDelivery"value="2">
       			 						<label for="pdStatus">宅配</label>
       	 							</td> 
     						   	
       						 	</tr>
       						 
       						 	<tr>
     						   		<td>
     						   			<br>
     						   			<br>
     						   			<br>
     						   	 	</td>
     						   		<td>
     						   		    <input type="hidden" name="OrderModify" value="OrderModify">
             							<input type="submit" value="送出修改">
     						   			<br>
        							</td>
       						 	</tr>
            				</tbody>
            			</table>
           		 <div id="preview">
             	 <ul class="picture_list"></ul>
            </div>

        </FORM>

					<h2>
						<a href="orderShowAll.jsp">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;繼續查詢</a>
					</h2>

		      


</div>
</div>
</div>
</div>
<script>

	</script>
</body>
</html>
</body>
</html>