<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productSort.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO"); 
%>
<!--<%= productVO==null %>--${productVO.pdsid}-- -->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">

<title>修改商品</title>
</head>
<body>
 <!-- ============================================================== -->
<h3>&emsp;修改商品</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" action= "product.do" name="form1" enctype="multipart/form-data">

        <div id="pdid">
        <Label>&emsp;商品編號:</Label>
        <input type="text" id="pdid" name = "Pdid" value ="<%=productVO.getPdid()%>"  style="background-color:rgb(185, 185, 185);" readonly>
        <br>
        </div>
        
        <jsp:useBean id="pdSortSvc" scope="page" class="com.productSort.model.ProductsortService" />
        <div>
        
        <Label for = "pdsid">&emsp;商品類別: </Label>
        <select size="1" name="Pdsid">
        <c:forEach var="ProductsortVO" items="${pdSortSvc.all}">
                    <option value="${ProductsortVO.pdsid}" ${(ProductsortVO.pdsid==ProductsortVO.pdsid)? 'selected':'' } >${ProductsortVO.pdsName}
                </c:forEach>
        
         </select>
        
        
        </div>
        
        <div>
        <Label>&emsp;商品名稱:
          <input type="text" id="pdName" name = "PdName" 
                  value="<%= (productVO==null)? "" : productVO.getPdName()%>" required/> 
        </Label>
        </div>
        
        
        <div>
        <Label>&emsp;商品原價:
          <input type="text" id="pdPrice" name = "PdPrice" 
                  value = "<%= (productVO==null)? "" : productVO.getPdPrice()%>" required/>
        </Label>
        </div>
        
        <div>
        <Label>&emsp;優惠價格:
          <input type="text" id="pdDiscountPrice" name = "PdDiscountPrice"  
                  value = "<%= (productVO==null)? "" : productVO.getPdDiscountPrice()%>" />
        </Label>
        </div>
        
        <div>
          <label for="pdStatus">&emsp;商品狀態</label>
          <input type = "radio" id ="pdStatus" name = "PdStatus"
             value = "0" required>
            <label for="productStateUn">暫不上架</label>
          <input type="radio" id="pdStatus" name="PdStatus"
             value="1">
        <label for="pdStatus">直接上架</label> 
        
        </div>
        
        <div>
        <Label>&emsp;商品資訊: <br></Label>
          &emsp;
          <textarea id="pdDescription" name = "PdDescription" 
                  rows="10"
                  cols="40">
                  <%= (productVO==null)? "" : productVO.getPdDescription()%>"
        </textarea>
        </div>
        
        <div>
        <Label>&emsp;加入新圖片:</Label>
          <input type="file" id="pdPic" name = "PdPic2" multiple>
          <div id="pdpicdropzone"><span class="text"> </span></div>
          
              <div id="preview">
              <span class="text">&emsp;預覽圖</span>
              </div>
        </div>
      
        
              <Label>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</Label>
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="action3" value="addpic2">
            <input type="submit" value="送出修改"> 
        </FORM>

</body>
</html>