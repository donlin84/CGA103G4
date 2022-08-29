<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>ChefSkillsType: Home</title>

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
   <tr><td><h3>ChefSkillsType: Home</h3><h4>( MVC )</h4></td></tr>
</table>


<p>This is the Home page for ChefSkillsType: Home</p>

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
  <li><a href='listAllChefSkillsType.jsp'>List</a> all Chefs.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="chefSkillsType.do" >
        <b>輸入專長編號 (如1):</b>
        <input type="text" name="skillid">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

<jsp:useBean id="chefSkillsTypeSvc" scope="page" class="com.chefskillstype.model.ChefSkillsTypeService" />
   
  <li>
     <FORM METHOD="post" ACTION="chefSkillsType.do" >
       <b>選擇專長編號:</b>
       <select size="1" name="skillid">
         <c:forEach var="chefSkillsTypeVO" items="${chefSkillsTypeSvc.all}" > 
          <option value="${chefSkillsTypeVO.skillid}">${chefSkillsTypeVO.skillid}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  
</ul>


<h3>專長管理</h3>

<ul>
  <li><a href='addChefSkillsType.jsp'>Add</a> a new ChefSkillsType.</li>
</ul>
</body>
</html>