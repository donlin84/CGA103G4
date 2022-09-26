<%@page import="com.product.model.ProductVO"%>
<%@ page import="com.productPicture.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
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
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>seefood後台</title>
<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon" href="assets/images/favicon.ico">
<link href="assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css"
	rel="stylesheet">
<link href="assets/plugins/fullcalendar/vanillaCalendar.css"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/morris/morris.css" rel="stylesheet">
<link href="assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
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
<h3>&emsp;修改商品</h3>

<FORM METHOD="post" action= "ProductServlet.do" name="form1" enctype="multipart/form-data">
<table id="mainTable" class="table table-striped m-b-0">
	<tbody>
        <tr>
        <td>&emsp;商品編號:</td>
        <td><input type="text" id="pdid" name = "Pdid" value ="<%=productVO.getPdid()%>"  style="background-color:rgb(185, 185, 185);" readonly>
        <br></td>
        </tr>
        
        
        <jsp:useBean id="pdSortSvc" scope="page" class="com.productSort.model.ProductsortService" />
        <tr>
        <td>&emsp;商品類別: </td>
        <td>
        <select size="1" name="Pdsid">

        	<c:forEach var="ProductsortVO" items="${pdSortSvc.all}">
            <option value="${ProductsortVO.pdsid}"${(ProductsortVO.pdsid==ProductsortVO.pdsid) }>${ProductsortVO.pdsName}
        	</c:forEach>
        
        </select>
        </td>
        
        

       
        <tr>
        	<td>&emsp;商品名稱: </td>
        	<td>
        	<input type="text" id="pdName" name = "PdName" 
                  value="<%= (productVO==null)? "" : productVO.getPdName()%>" required/> 
        	</td>
        </tr>
        
        
        <tr>
        	<td>&emsp;商品原價:</td>
       			 <td><input type="text" id="pdPrice" name = "PdPrice" 
                  value = "<%= (productVO==null)? "" : productVO.getPdPrice()%>" required/>
       		 </td>
        </tr>
        
        
        <tr>
        <td>&emsp;優惠價格:</td>
          <td><input type="text" id="pdDiscountPrice" name = "PdDiscountPrice"  
                  value = "<%= (productVO==null)? "" : productVO.getPdDiscountPrice()%>" />
        </td>
        </tr>
        
        <tr>
          <td><label for="pdStatus">&emsp;商品狀態</label></td>
          <td>
          		 <input type = "radio" id ="pdStatus" name = "PdStatus"value = "0" required>
            	 <label for="productStateUn">暫不上架</label>
         		 <input type="radio" id="pdStatus" name="PdStatus"value="1">
       			 <label for="pdStatus">直接上架</label>
       	 	</td> 
        </tr>
        
        <tr>
        <td>&emsp;商品資訊: <br></td>
          &emsp;
          <td>
          <textarea id="pdDescription" name = "PdDescription" 
          rows="10"
          cols="40">
          <%= (productVO==null)? "" : productVO.getPdDescription()%>
           
			</textarea>
        
        <tr>
        <td>&emsp;加入新圖片:</td>
        
         <td> <input type="file" id="pdPic" name = "OldPdAddPic" multiple></td>
     	<td><input type="hidden" name="PdInfoUpdate" value="pdInfoUpdate">
            <input type="hidden" name="AddpicForPdUpdate" value="addpicForPdUpdate">
             	<input type="submit" value="送出修改"></td>		
        </tr>
            </tbody>
            </table>
            
            
            <div id="preview">
              
              <ul class="picture_list"></ul>
            </div>
            
        </FORM>
        <div>
							<c:forEach var="productpicVO" items="${productVO.getPicsNo()}" >
								<img alt="" src="showPicsByPicNo.do?pdPicid=${productpicVO.pdPicid}" width="200">
								
							<FORM METHOD="post" action= "DeletePdPic.do" name="form1" style="display: inline">	
								<input type="hidden" name="pdid" value="${productVO.pdid}">
								<input type="hidden" name="pdPicid" value="${productpicVO.pdPicid}">
								<input type="hidden" name="action" value="deletePdPic">
								<input type="submit" name="action" value="刪除圖片"> 
							</FORM>
							</c:forEach>
							
        </div>
        
        
        
					<h2>
						<a href="productShowAll.jsp">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;繼續查詢</a>
					</h2>

		      


</div>
</div>
</div>
</div>
<script>
	window.addEventListener("load", function(){
  var pdpic_element = document.getElementById("pdPic");
  pdpic_element.addEventListener("change", function(e){          

    // 寫在這
    var picture_list = document.getElementsByClassName("picture_list")[0];
    picture_list.innerHTML = "";
    
   
    for (let i = 0; i < this.files.length; i++) {
      let reader = new FileReader(); // 用來讀取檔案
      reader.readAsDataURL(this.files[i]); // 讀取檔案
      reader.addEventListener("load", function () {
        console.log(reader.result);
        let li_html = `
                <li><img src="\${reader.result}" class="preview"></li>
              `;
        picture_list.insertAdjacentHTML("beforeend", li_html); // 加進節點
      });
    }


  });
});
	</script>
</body>
</html>
</body>
</html>