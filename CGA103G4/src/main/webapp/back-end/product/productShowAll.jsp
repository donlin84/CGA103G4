<%@page import="com.promotions.model.PromotionsService"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productSort.model.*"%>
<%@ page import="com.promotionsdetail.model.*"%>
<%@ page import="com.promotions.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<%@ page import="com.promotionsdetail.*"%>
<%@ page import="com.productPicture.*" %>

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<%
  PromotionsVO promotionsVO = (PromotionsVO) request.getAttribute("promotionsVO");
%>

<%
	ProductService productSvc = new ProductService();
    List<ProductVO> list = productSvc.getAll();
    pageContext.setAttribute("list",list);
%>





<style>

input[type="submit"]
{
    font-size:14px;
}


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
  	width:100%; 
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
width:100%;
}

.tableResult{
text-align:center;
text-valign:center;
width:100%;
height: 100%;
table-layout: fixed;
margin-left:10px; 
margin-right:10px;
}

.table_tit{
white-space: nowrap;  
overflow: hidden; 
text-overflow: ellipsis;
}

</style>
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
<link rel="stylesheet" type="text/css" href="assets/datetimepicker/jquery.datetimepicker.css" />
</head>

<body>

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
								<li class="breadcrumb-item"><a href="index-back.jsp">後台首頁</a></li>
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
	<script src="assets/datetimepicker/jquery.js"></script>
	<script src="assets/datetimepicker/jquery.datetimepicker.full.js"></script>

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
			<div class="card"  style="margin:0 80px">
				<div class="card-body">
					<h4 class="mt-0 header-title">複合查詢
					</h4>
		<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤:</font>
					
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
						<li> style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
		
<!-- 		================================================================= -->



<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>

					<div class="searchoption">
					
    					<FORM METHOD="post" ACTION="PdSearchBackend.do" name="form1">
   							 <br>
        					<b>&emsp;商品編號:</b>
        						<input type="text" size="10" style="font-size:12px" name="pdid" >
       						<b>商品名稱:</b>
       							<input type="text" size="10" style="font-size:12px" name="pdName">
							<b>商品價格:</b>
       							<input type="text" size="10" style="font-size:12px" name="pdPrice">
        					<b>&emsp;商品類別:</b>
	     			
	     		<jsp:useBean id="pdSortSvc" scope="page" class="com.productSort.model.ProductsortService" />
	     						
	     						<select name = "pdsid" size="0.5" style="white-space:nowrap">
       								<option value= ""></option>
									<c:forEach  var="ProductsortVO" items="${pdSortSvc.all}">
										<option value="${ProductsortVO.pdsid}">${ProductsortVO.pdsName}</option>
									</c:forEach>
 								</select>
 							
 							<b>&emsp;商品狀態:</b>
 								<select name = "pdStatus" size="0.5">
 									<option></option>
									<option value=1>上架中</option>
									<option value=0>未上架</option>
								</select>
		        					<input type="submit" value="快速查詢">
        							<input type="hidden" name="action4" value="listProduct_ByCompositeQuery">
        				</FORM>
        
					</div>
					
<%@	include file="page1.file" %>

		<div class="table">
		  <table class="tableResult" id = "table1">
					<thead>
					<tr>
			  			<th>
			 				<h5><a class="btn btn-outline-primary waves-effect waves-light" href="AddPd.do">新增商品</a></h5>
			  			</th>
			  
			  			<th>
			  				<a href="PdArrangeOrder.do">
			  				<button type="button" class="btn btn-primary dropdown-toggle">商品編號</button>
			  				</a>
			  			</th>
			  			
			  			<th>
			  				<div class="btn-group mo-mb-2">
			  					<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			 				 商品類別 
			  					</button>
			  						<div class="dropdown-menu" x-placement="top-start" style="position: absolute; transform: translate3d(0px, -189px, 0px); top: 0px; left: 0px; will-change: transform;">
			  						<c:forEach  var="ProductsortVO" items="${pdSortSvc.all}">${ProductsortVO.pdsName}
 										<a class="dropdown-item" href="PdSearchBackend.do?pdsid=${ProductsortVO.pdsid}"><input type="hidden" name="action4"	value="list_pd_by_sort"></a>
 			  						</c:forEach>
			  					</div>
			 				 </div> 
 			 			 </th>
 			 			 
			  			<th><button type="button" class="btn btn-primary dropdown-toggle">商品名稱</button></th>
			  			<th>
			  				<a href="ListAllPdOrderByPdPrice.do">
			  					<button type="button" class="btn btn-primary dropdown-toggle">商品價格
			  					</button>
			  				</a>
			  			</th>
			  			
			  			<th>
			  				<button type="button" class="btn btn-dark waves-effect waves-light">
			  				優惠價格
			  				</button>
			  			</th>
			  			
			  			<th>
			  				<button type="button" class="btn btn-dark waves-effect waves-light">優惠方案</button>
			  			</th>
			  			
			  			<th>
			  				<div class="btn-group mo-mb-2">
			  					<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			  						商品狀態
			  					</button>
			  					<div class="dropdown-menu" x-placement="top-start" style="position: absolute; transform: translate3d(0px, -189px, 0px); top: 0px; left: 0px; will-change: transform;">
 									<a class="dropdown-item" href="PdSearchBackendStatus.do?pdStatus=1">上架中</a>
 									<a class="dropdown-item" href="PdSearchBackendStatus.do?pdStatus=0">未上架</a>
								</div>
							</div>
			  			</th>
			  			
						 <th>
						 	<button type="button" class="btn btn-dark waves-effect waves-light">
						 		上次修改
						 	</button>
						 </th>
					</tr>
				</thead>
 			
	<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
			<tr>  
			  <td> 
				<div class="row">
				 <div>
				 	<FORM METHOD="post" ACTION="ProductServlet.do" style="margin-bottom: 0px;">
					 	<input style="width:82px; height:30px " type="submit" value="修改商品" >
					 	<input type="hidden" name="pdid"  value="${productVO.pdid}">
					 	<input type="hidden" name="GetOne_For_Update"	value="getOne_For_Update"></FORM>
			     </div>
			     
				  <div>
				 	
				 	<button type="button" class="btn btn-primary waves-effect waves-light" data-toggle="modal" data-animation="bounce" data-target=".bs-example-modal-lg${productVO.pdid}">
				 		查詢商品
				 	</button>		  
					
					<div class="modal fade bs-example-modal-lg${productVO.pdid}" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" style="display: none;" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title mt-0" id="myLargeModalLabel">商品資訊</h5>
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
									</div>
									
									<div class="modal-body">
										<p>${productVO.pdDescription}</p>
									
										<div>
											<c:forEach var="productpicVO" items="${productVO.getPicsNo()}">
												<img alt="" src="showPicsByPicNo.do?pdPicid=${productpicVO.pdPicid}" width="200">
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</div>		
				  	</div>
				</div>
			</td>
				
				
			<td>${productVO.pdid}</td>
				<c:set var = "str1" value = "${productVO.productSortVO.pdsName}(${productVO.pdsid})"/>
            <td>${str1}</td>  
			<td>${productVO.pdName}</td>
			<td>${productVO.pdPrice}</td>
			<td>${productVO.promotionsDetailVO.pmPdDiscountPrice}</td>
							
				<c:set var="getpmid" value="${(productVO.promotionsDetailVO.pmid eq null) ? 0 : productVO.promotionsDetailVO.pmid}"/>
				<c:if test="${getpmid == 0}" var="123" scope="page">
						
						
			<td class="table_tit">		
				<FORM METHOD="post" ACTION="PdShowAllPageGetPmDiscount.do" name="form1">
						
						<select name="Pmid" size="1px"  style="max-height: 50%; max-width:75%">
							
							<jsp:useBean id="pmtSvc" scope="page" class="com.promotions.model.PromotionsService" />
						 	<c:forEach var="promotionsVO" items="${pmtSvc.all}">
        					
        					<option value="${promotionsVO.pmid}">${promotionsVO.pmName}
       					 </c:forEach>
						</select>
						<br>
						
						<input type="hidden" name="PromoAdd" value="PromoAdd" >
						<input type="hidden" name="PdPrice" value="${productVO.pdPrice}" >
						<input type="hidden" name="Pdid" value="${productVO.pdid}" >
						<input class="btn btn-info" type="submit" value="加入優惠">	
				</FORM>
			</td> 
		</c:if>
				
		<c:if test="${getpmid != 0}" var="123" scope="page">
					
			<td class="table_tit">
				<FORM METHOD="post" ACTION="DeleteOnePromoDetailByPdid.do" name="form1">
					${productVO.getPromotionsVOByPmid(getpmid).pmName}<br>
						<input type="hidden" name="Pdid" value="${productVO.pdid}" >
						<input class="btn btn-danger" type="submit" value="退出優惠">
				</FORM>
			</td>
					 
		</c:if>
				
			<td>
				<c:if test="${productVO.pdStatus == 1}" var="上架中" scope="page">
				上架中<br>
					<Form METHOD="post" ACTION="UpdatePdStatus.do">
						<input type = "hidden" name = "UpdatePdStatus" value = "updatePdStatus" >
						<input type = "hidden" name ="Pdid" value = "${productVO.pdid}">
						<input type = "hidden" name ="PdStatus" value = "0">
						<input class="btn btn-danger" type="submit" value="下架商品">
					</Form>
				</c:if>
				
				<c:if test="${productVO.pdStatus == 0}" var="上架中" scope="page">
				未上架<br>
					<Form METHOD="post" ACTION="UpdatePdStatus.do">
						<input type = "hidden" name = "UpdatePdStatus" value = "updatePdStatus" >
						<input type = "hidden" name ="Pdid" value = "${productVO.pdid}">
						<input type = "hidden" name ="PdStatus" value = "1">
						<input class="btn btn-info" type="submit" value="上架商品">
					</Form>
				</c:if>
			</td>
<%-- 				<c:if test="${productVO.pdStatus == 0}" var="未上架" scope="page"/> --%>
<%-- 				<td>value="${未上架}</td> --%>
					
			<c:set var="string1" value="${productVO.pdUpdate}"/>
			<c:set var="string2" value="${fn:replace(string1, 'T', ' ')}" />
			
			<td>${string2}</td>
		</tr>
			 
		</c:forEach>
	</table>
		 <%@include file="page2.file" %>
</div>
</div>
</div>
</div>
</div>

		
		<script>

			$.datetimepicker.setLocale('zh'); // kr ko ja en
			$(function(){
				 $('#start_date').datetimepicker({
				  format:'Y-m-d',
				  onShow:function(){
				   this.setOptions({
				    maxDate:$('#end_date').val()?$('#end_date').val():false
				   })
				  },
				  timepicker:false
				 });
				 
				 $('#end_date').datetimepicker({
				  format:'Y-m-d',
				  onShow:function(){
				   this.setOptions({
				    minDate:$('#start_date').val()?$('#start_date').val():false
				   })
				  },
				  timepicker:false
				 });
			});
			
			function getkey(a){
				
				let start_date_el ='between'+ document.getElementById("start_date").value;
				let end_date_el ='and' + document.getElementById("end_date").value;
				
				a.value = start_date_el + end_date_el;
			}
		</script>
		
		<script type="text/javascript">
			function Buttontoggle()
			{
 			 var t = document.getElementById("myButton");
  				if(t.value=="YES"){t.value="NO";}
  					else if(t.value=="NO"){
      				t.value="YES";}
			}
		</script>
		
		</body>
		
		</html>