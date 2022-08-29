<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>ChefSubscription: Home</title>

<style>
  table#table-1 {
	width: 650px;
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
   <tr><td><h3>ChefSubscription: Home</h3><h4>( MVC )</h4></td></tr>
</table>


<p>This is the Home page for ChefSubscription: Home</p>

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
  <li><a href='listAllChefSubscription.jsp'>List</a> all ChefSubscription.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="chefSubscription.do" >
        <b>輸入私廚編號 (如301):</b>
        <input type="text" name="chefid">
        <input type="hidden" name="action" value="getOne_For_Display">
        <b>輸入會員編號 (如1):</b>
        <input type="text" name="memid">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

<jsp:useBean id="chefSubscriptionSvc" scope="page" class="com.chefsubscription.model.ChefSubscriptionService" />
   
  <li>
     <FORM METHOD="post" ACTION="chefSubscription.do" >
       <b>選擇私廚編號:</b>
       <select size="1" name="chefid">
         <c:forEach var="chefSubscriptionVO" items="${chefSubscriptionSvc.all}" > 
          <option value="${chefSubscriptionVO.chefid}">${chefSubscriptionVO.chefid}
         </c:forEach>   
       </select>
       <b>選擇會員編號:</b>
       <select size="1" name="memid">
         <c:forEach var="chefSubscriptionVO" items="${chefSubscriptionSvc.all}" > 
          <option value="${chefSubscriptionVO.memid}">${chefSubscriptionVO.memid}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  
</ul>


<h3>訂閱管理</h3>

<ul>
  <li><a href='addChefSubscription.jsp'>Add</a> a new ChefSubscription.</li>
</ul>
</body>
</html>