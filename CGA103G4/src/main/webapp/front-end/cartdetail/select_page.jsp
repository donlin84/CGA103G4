<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>CartDetail: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>CartDetail: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for CartDetail: Home</p>

<h3>資料查詢:</h3>
	
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
  <li><a href='listAllCartDetail.jsp'>List</a> all CartDetail.  <br><br></li>

  <jsp:useBean id="cartdetailSvc" scope="page" class="com.cartdetail.model.CartDetailService" />
   
  <li>
     <FORM METHOD="post" ACTION="CartDetail.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="memid">
         <c:forEach var="cartDetailVO" items="${cartdetailSvc.getAll()}" > 
          <option value="${cartDetailVO.memid}">${cartDetailVO.memid}
         </c:forEach>   
       </select>
<!--        <b>選擇商品編號:</b> -->
<!--        <select size="1" name="pdid"> -->
<%--          <c:forEach var="cartDetailVO" items="${cartdetailSvc.getAll()}" >  --%>
<%--           <option value="${cartDetailVO.pdid}">${cartDetailVO.pdid} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
</ul>


<!-- <h3>食譜圖片管理</h3> -->

<ul>
  <li><a href='addCartDetail.jsp'>Add</a> a new CartDetail.</li>
</ul>

</body>
</html>