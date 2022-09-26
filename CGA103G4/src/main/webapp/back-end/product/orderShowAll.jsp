<%@page import="com.orderdetail.model.OrderDetailVO"%>
<%@page import="com.orders.model.OrdersVO"%>
<%@page import="com.orders.model.OrdersService"%>
<%@page import="com.promotions.model.PromotionsService"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productSort.model.*"%>
<%@ page import="com.promotionsdetail.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<%@ page import="com.promotionsdetail.*"%>
<%@ page import="com.orderdetail.model.*"%>

<%
  OrderDetailVO orderDetailVO = (OrderDetailVO) request.getAttribute("orderDetailVO");
%>


<%
  OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO");
%>

<%
	OrdersService ordSvc = new OrdersService();
    List<OrdersVO> list = ordSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<style>

  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
.table th, .table td {
text-align: center;
vertical-align: middle!important;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}


.searchoption{
	margin:10px auto;
  	width:120%; 
 	text-align:left;
  	background:	#F0F0F0; 
  	border: 1px solid #F0F0F0;
}
.searchoption2{
	margin:20px auto;
	text-align:left;

}
.table{
text-align:center;
text-valign:center;
width:120%;
}

.tableResult{

text-align:center;
text-valign:center;
width:100%;
height: 120%;
margin-left:0px; 
margin-right:0px;
}

.table_tit{
white-space: nowrap;  
overflow: hidden; 
text-overflow: ellipsis;
}


ul,li{margin:0; padding:0; }
.counter li{float:left; list-style-type:none; width:30px; height:30px; text-align:center; line-height:30px; border:#999 thin solid; background-color:#fff}
.counter li input{font-size:20px; width:100%; height:100%; outline:none; -webkit-appearance:none; background:none; margin:0; padding:0; border: 1px solid transparent; border-radius: 0;}
#countnum{ border-left:hidden; border-right:hidden; color:#666}

</style>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>seefood後台</title>
<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />


<link href="assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css"
	rel="stylesheet">
<link href="assets/plugins/fullcalendar/vanillaCalendar.css"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/morris/morris.css" rel="stylesheet">
<link href="assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="assets/css/style.css" rel="stylesheet" type="text/css">
<link   rel="stylesheet" type="text/css" href="assets/datetimepicker/jquery.datetimepicker.css" />
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
	<script src="assets/pages/modal-animation.init.js"></script>
	<script src="assets/datetimepicker/jquery.js"></script>
	<script src="assets/datetimepicker/jquery.datetimepicker.full.js"></script>

	<!-- Page-Title -->
	<div class="row">
		<div class="col-sm-12">
			<div class="page-title-box">
				<div class="btn-group pull-right">
				</div>
			</div>
		</div>
	</div>
	<!-- end page title end breadcrumb -->
	<div class="row">
		<div class="col-12">
			<div class="card"  style="margin:0 80px">
				<div class="card-body">
				
					</div>
				<%-- 錯誤表列 --%>
		
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
				
	
	<%@	include file="page1.file" %>
		<div class="table">
		



		  <table class="tableResult" id = "table1">
			
			<tr>
			  <th>訂單編號</th>
			  <th>會員編號</th>
			  <th>優惠券ID</th>
			  <th>小計</th>
			  <th>總計</th>
			  <th>訂單狀態</th>
			  <th>收件人</th>
			  <th>連絡電話</th>
			  <th>付款方式</th>
			  <th>送貨方式</th>
			  <th>收件地址</th>
			  <th>成立時間</th>
			  
<!-- 	private Integer ordid; -->
<!-- 	private Integer memid; -->
<!-- 	private Integer memCpid; -->
<!-- 	private Integer ordSubTotal; -->
<!-- 	private Integer ordTotal; -->
<!-- 	private Integer ordStatus; -->
<!-- 	private LocalDateTime ordCreate; -->
<!-- 	private String ordRecipient; -->
<!-- 	private String recPhone; -->
<!-- 	private Integer ordPayment; -->
<!-- 	private Integer ordDelivery; -->
<!-- 	private String ordAddress; -->
			  


	  
			  
	<c:forEach var="ordersVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
			
			 <td> 
			 <a href = "GetOneOrderForModify.do?ordid=${ordersVO.ordid}">
				<button type="button" class="btn btn-dark waves-effect waves-light">
					修改
				</button>
			</a>
			 
			 
		<button type="button" class="btn btn-primary waves-effect waves-light" data-toggle="modal" data-animation="bounce" data-target=".bs-example-modal-lg${ordersVO.ordid}">明細</button>		  
			<div class="modal fade bs-example-modal-lg${ordersVO.ordid}" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" style="display: none;" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">

						<h5 class="modal-title mt-0" id="myLargeModalLabel">
						訂單明細
						</h5>

				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				</div>

				<div class="modal-body">
					
					<table class="table table-bordered table-hover mb-0">
									<thead class="text-700 bg-gray-200">
										
										<tr>
											
											<th class="fw-600">訂單編號</th>
											<th class="fw-600">商品編號</th>
											<th class="fw-600">商品名稱</th>
											<th class="fw-600">商品單價</th>
											<th class="fw-600">商品活動優惠單價</th>
											<th class="fw-600">商品個數</th>
										</tr>
									</thead>
									
 									<c:forEach var="orderDetailVO" items="${ordersVO.getAllDetailByOrdid(ordersVO.getOrdid())}">
										
										<tbody>
										
											<tr>
												<td class="p-3">
													${ordersVO.ordid}
			  									</td>
			  									
			  									<td class="p-3">${orderDetailVO.pdid}</td>
			  									<td class="p-3">${orderDetailVO.productVO.pdName}</td>
												<td class="p-3">${orderDetailVO.detailPrice}</td>
												<td class="p-3">${orderDetailVO.detailGoodPrice}</td>
												
												
												<td class="p-3">
												<c:if test="${ordersVO.ordStatus == 0}" var="未完成" scope="page">
													<input type= "number" id = "ordPdNumber" name = "OrdPdNumber" value = "${orderDetailVO.detailNumber}"/>
												</c:if>
												
												<c:if test="${ordersVO.ordStatus == 1}" var="已完成" scope="page">
													<input type= "number" id = "ordPdNumber" name = "OrdPdNumber" value = "${orderDetailVO.detailNumber}" readonly/>
												</c:if>
												
												<c:if test="${ordersVO.ordStatus == 2}" var="配送中" scope="page">
													<input type= "number" id = "ordPdNumber" name = "OrdPdNumber" value = "${orderDetailVO.detailNumber}"readonly/>
												</c:if>
												
												<c:if test="${ordersVO.ordStatus == 3}" var="取消" scope="page">
													<input type= "number" id = "ordPdNumber" name = "OrdPdNumber" value = "${orderDetailVO.detailNumber}"readonly/>
												</c:if>
												
												
												</td>
											
											</tr>
										
										</tbody>
										
									</c:forEach>	
								</table>
							
						</div>
					</div>
				</div>
			</div>		
				
			</td>
			<td>
			${ordersVO.memid}
			</td>
			
             <td>
            ${ordersVO.memCpid}
             </td>  
			
			<td>
			${ordersVO.ordSubTotal}
			</td>
				
			<td>
			${ordersVO.ordTotal}
			</td>
				
			<td>
			<c:if test="${ordersVO.ordStatus == 0}" var="未完成" scope="page">
				未完成</c:if>
			<c:if test="${ordersVO.ordStatus == 1}" var="已完成" scope="page">
				已完成</c:if>
			<c:if test="${ordersVO.ordStatus == 2}" var="配送中" scope="page">
				配送中</c:if>
			<c:if test="${ordersVO.ordStatus == 3}" var="取消" scope="page">
				取消</c:if>
			</td>
				
			<td>
			${ordersVO.ordRecipient}
			</td>

			<td>
			${ordersVO.recPhone}
			</td>
			
			<td>
			<c:if test="${ordersVO.ordPayment == 1}" var="信用卡" scope="page">
				信用卡</c:if>
			<c:if test="${ordersVO.ordPayment == 2}" var="信用卡" scope="page">
				貨到付款</c:if>
			</td>
			
			<td>
			<c:if test="${ordersVO.ordDelivery == 1}" var="超商取貨" scope="page">
				超商取貨</c:if>
			<c:if test="${ordersVO.ordDelivery == 2}" var="超商取貨" scope="page">
				宅配</c:if>
			</td>
			
			<td class="table_tit">
			${ordersVO.ordAddress}
			</td>
			
			<td>
			<c:set var="string1" value="${ordersVO.ordCreate}"/>
			<c:set var="string2" value="${fn:replace(string1, 
                                'T', ' ')}" />
			${string2}
			</td> 

			  </tr>
		</c:forEach>
		
		  </table>
		 
		</div>
						</div>
			</div>
			</div>
			
		

		<script>
		function adder(){
			var count=document.getElementById("countnum").innerHTML;
			count=parseInt(count)+1;
			document.getElementById("countnum").innerHTML=count;
		}
		function minuser(){
			var count=document.getElementById("countnum").innerHTML;
			if(count<=0){
				count=0;
			}else{
				count=parseInt(count)-1;
			}	
			document.getElementById("countnum").innerHTML=count;
		}
		
		</script>

		
		</body>
		
		</html>