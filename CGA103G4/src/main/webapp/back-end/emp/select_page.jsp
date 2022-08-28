<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>管理員系統</title>

<style>
  table#table-1 {
	width: auto;
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
   <tr><td><h3>管理員系統</h3><h4></h4></td></tr>
</table>


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
  
  <li><a href='emp.do?action=getAll'> 查詢全部員工</a><br><br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="emp.do" enctype="multipart/form-data">
        <b>輸入管理員編號 (如101):</b>
        <input type="text" name="empid">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit">                   
    </FORM>
  </li>
  


<%--   <jsp:useBean id="dao" scope="page" class="com.emp.model.EmpDAO" /> --%>
  <% com.emp.model.EmpDAO dao = new com.emp.model.EmpDAO(); 
	 pageContext.setAttribute("dao", dao);  
  %>
   
   
  <li>
     <FORM METHOD="post" ACTION="emp.do" enctype="multipart/form-data" >
       <b>選擇管理員編號:</b>
       <select size="1" name="empid">
         <c:forEach var="empVO" items="${dao.all}" > 
          <option value="${empVO.empid}">${empVO.empid}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="emp.do" enctype="multipart/form-data">
       <b>選擇管理員姓名:</b>
       <select size="1" name="empid">
         <c:forEach var="empVO" items="${dao.all}" > 
          <option value="${empVO.empid}">${empVO.empName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<h3>管理員管理</h3>

<ul>
  <li><a href='addEmp.jsp'>Add</a> a new Emp.</li>
</ul>


</body>
</html>