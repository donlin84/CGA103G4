<%@ page import="com.product.model.*"%>
<%@ page import="com.productSort.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>


<%
List<ProductVO> productVO = (List<ProductVO>)(request.getAttribute("productVO"));
pageContext.setAttribute("productVO", productVO);
%>

<style>

.table{
text-align:center;
text-valign:center;
width:120%;
}

.tableResult{
text-align:center;
text-valign:center;
width:90%;
height: 100%;
table-layout: fixed;
margin-left:auto; 
margin-right:auto;
}

.table_tit{
white-space: nowrap;  
overflow: hidden; 
text-overflow: ellipsis;
}

</style>
<html>
	<html lang="zh-Hant-TW">

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
					<div class="btn-group pull-left">
					  <ol class="breadcrumb hide-phone p-0 m-0">
						<li class="breadcrumb-item"><a href="#">後台首頁</a></li>
						<li class="breadcrumb-item active"><a href="#">某頁面</a></li>
						<li class="breadcrumb-item active">當前頁面</li>
					  </ol>
					</div>
				  </div>
				</div>
			  </div><!-- end page title end breadcrumb -->
			  <!--end row-->
			</div><!-- end container -->
		  </div><!-- end wrapper -->
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
		<!--   ======================================================================== -->
		
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
  <li>
    <FORM METHOD="post" ACTION="product.do" >
        <b>&emsp;&emsp;輸入商品編號:</b>
        <input type="text" name="pdid" required>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
	</li>
	<li>
	<jsp:useBean id="pdSortSvc" scope="page" class="com.productSort.model.ProductsortService" />

<div>
		<FORM METHOD="post" ACTION="PdSearchBackend.do" >
		<b>&emsp;&emsp;商品類別: </b>
			&emsp;&emsp;
			<select size="1" name="pdsid">
				<c:forEach var="ProductsortVO" items="${pdSortSvc.all}">
					<option value="${ProductsortVO.pdsid}" ${(ProductsortVO.pdsid==ProductsortVO.pdsid)? 'selected':'' } >${ProductsortVO.pdsName}
				</c:forEach>

 			</select>
 				<input type="hidden" name="action4" value="list_pd_by_sort">
				<input type="submit" value="送出">
 			</FORM>
		</div>
	</li>
</ul>


<div class="table">
		  <table class="tableResult" id = "table1">
			<tr>
				<th><h4><a href="productAdd.jsp">新增商品</a></h4></th>
			  <th>&emsp;商品編號</th>
			  <th>&emsp;商品分類</th>
			  <th>&emsp;商品名稱</th>
			  <th>&emsp;商品價格</th>
			  <th>&emsp;優惠價格</th>
			  <th>&emsp;商品描述</th>
			  <th>&emsp;商品狀態</th>
			  <th>&emsp;上次修改</th>
			  
			</tr>
 			

			  <c:forEach var="productVO" items="${productVO}">
			<tr>
			  
			  <td> 
				<div class="row">
				 <div>
				  <FORM METHOD="post" ACTION="product.do" style="margin-bottom: 0px;">
					 <input type="submit" value="修改" >
					 <input type="hidden" name="pdid"  value="${productVO.pdid}">
					 <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			     </div>
				  <div>
				 <FORM METHOD="post" ACTION="product.do" style="margin-bottom: 0px;">
					 <input type="submit" value="查詢">
					 <input type="hidden" name="pdid"  value="${productVO.pdid}">
					 <input type="hidden" name="action"	value="getOne_For_Display">
				</FORM>
				  </div>
				  </div>
			</td>
				<td>${productVO.pdid}</td>
				<c:set var = "str1" value = "${productVO.productSortVO.pdsName}(${productVO.pdsid})"/>
                <td>${str1}</td>  
<%-- 				<td>${productVO.pdsid}-[${productVO.productSortVO.pdsName}]</td> --%>
				<td>${productVO.pdName}</td>
				<td>${productVO.pdPrice}</td>
				<td>${productVO.pdDiscountPrice}</td>
				
				<td class="table_tit">${productVO.pdDescription}</td> 
				
				<td>
				<c:if test="${productVO.pdStatus == 1}" var="上架中" scope="page">
				上架中</c:if>
				<c:if test="${productVO.pdStatus == 0}" var="上架中" scope="page">
				未上架</c:if>
				</td>
<%-- 				<c:if test="${productVO.pdStatus == 0}" var="未上架" scope="page"/> --%>
<%-- 				<td>value="${未上架}</td> --%>
					
				<c:set var="string1" value="${productVO.pdUpdate}"/>
				<c:set var="string2" value="${fn:replace(string1, 
                                'T', ' ')}" />
				<td>${string2}</td>
				

			  </tr>
				</c:forEach>
		  </table>

		</div>
		
		<script>
			let test = document.getElementById('test2')
			// let test2 = test.replace("T"," ");

			console.log(typeof test);

		</script>
		
		</body>
		
		</html>