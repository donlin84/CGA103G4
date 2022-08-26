<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Emp: Home</title>

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
   <tr><td><h3>IBM Emp: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Emp: Home</p>

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
  
  <li><a href='emp.do?action=getAll'> List</a> all Emps    <h4>(getFromSession).</h4> <br><br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="emp.do" >
        <b>輸入管理員編號 (如101):</b>
        <input type="text" name="empid">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit">                   <h4>(資料格式驗證  by Controller ).</h4> 
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="emp.do" name="form1">
        <b>輸入管理員編號 (如101):</b>
        <input type="text" name="empid">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="button" value="送出" onclick="fun1()">  <h4>(資料格式驗證  by Java Script).</h4> 
    </FORM>
  </li>

<%--   <jsp:useBean id="dao" scope="page" class="com.emp.model.EmpDAO" /> --%>
  <% com.emp.model.EmpDAO dao = new com.emp.model.EmpDAO(); 
	 pageContext.setAttribute("dao", dao);  
  %>
   
   
  <li>
     <FORM METHOD="post" ACTION="emp.do" >
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
     <FORM METHOD="post" ACTION="emp.do" >
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

<script>    
   function fun1(){
      with(document.form1){
         if (empid.value=="") 
             alert("請輸入管理員編號!");
         else if (isNaN(empid.value)) 
             alert("管理員編號格式不正確!");
         else if ((empid.value < 100) || (empid.value > 200)) 
             alert("請填寫介於100和200之間的數量!");
         else
             submit();
      }
   }
</script>

</body>
</html>