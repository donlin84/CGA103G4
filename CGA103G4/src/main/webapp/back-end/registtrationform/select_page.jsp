<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.registtrationform.model.*"%>
<%@ page import="java.util.*"%>

<html>
<head>
<title> RegisttrationForm: Home</title>

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
   <tr><td><h3> RegisttrationForm: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for  RegisttrationForm: Home</p>

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
  <li><a href='listAllRegisttrationForm.jsp'>List</a> all RegisttrationForms.  <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="RegisttrationForm.do" >
        <b>輸入報名表ID (如1):</b>
        <input type="text" name="claid"><br>
        <b>輸入會員ID (如201):</b>
        <input type="text" name="memid"><br>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="registtrationFormSvc" scope="page" class="com.registtrationform.model.RegisttrationFormService" />
  <%
//  jsp比EL更早執行
  	RegisttrationFormService registtrationFormSvc2 = new RegisttrationFormService();
  	List<RegisttrationFormVO> list = registtrationFormSvc2.getAll();
  %>
  
  <li>
     <FORM METHOD="post" ACTION="RegisttrationForm.do" >
       <b>選擇課程編號:</b>
       <select size="1" name="claid">
         <c:forEach var="registtrationFormVO" items="${registtrationFormSvc.all}" > 
          <option value="${registtrationFormVO.claid}">${registtrationFormVO.claid}
         </c:forEach>   
       </select>
       <b>選擇會員編號:</b>
       <select size="1" name="memid">
         <c:forEach var="registtrationFormVO" items="${registtrationFormSvc.all}" > 
          <option value="${registtrationFormVO.memid}">${registtrationFormVO.memid}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addRegisttrationForm.jsp'>Add</a> a new Teacher</li>
</ul>

</body>
</html>