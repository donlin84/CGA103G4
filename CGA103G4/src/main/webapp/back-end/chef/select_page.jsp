<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Chef: Home</title>

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
<!-- <img src="DBGifReader4"> -->
<table id="table-1">
   <tr><td><h3>Chef: Home</h3><h4>( MVC )</h4></td></tr>
</table>


<p>This is the Home page for Chef: Home</p>

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
  <li><a href='listAllChef.jsp'  >List</a> all Chefs.  <br><br></li>
  
    
  <li>
    <FORM METHOD="post" ACTION="chef.do" >
        <b>輸入私廚編號 (如301):</b>
        <input type="text" name="chefid">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

<jsp:useBean id="chefSvc" scope="page" class="com.chef.model.ChefService" />
   
  <li>
     <FORM METHOD="post" ACTION="chef.do" >
       <b>選擇私廚編號:</b>
       <select size="1" name="chefid">
         <c:forEach var="chefVO" items="${chefSvc.all}" > 
          <option value="${chefVO.chefid}">${chefVO.chefid}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="chef.do" >
       <b>選擇私廚姓名:</b>
       <select size="1" name="chefid">
         <c:forEach var="chefVO" items="${chefSvc.all}" > 
          <option value="${chefVO.chefid}">${chefVO.chefName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>私廚管理</h3>

<ul>
  <li><a href='addChef.jsp'>Add</a> a new Chef.</li>
</ul>
</body>
</html>