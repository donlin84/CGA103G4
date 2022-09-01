<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Recipe: Home</title>

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
   <tr><td><h3>Recipe: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Recipe: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllRecipe.jsp'>List</a> all Recipe.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="Recipe.do" >
        <b>��J���нs�� (�p1):</b>
        <input type="text" name="reid">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="recipeSvc" scope="page" class="com.recipe.model.RecipeService" />
   
  <li>
     <FORM METHOD="post" ACTION="Recipe.do" >
       <b>��ܭ��нs��:</b>
       <select size="1" name="reid">
         <c:forEach var="recipeVO" items="${recipeSvc.getAll()}" > 
          <option value="${recipeVO.reid}">${recipeVO.reid}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
</ul>


<h3>���йϤ��޲z</h3>

<ul>
  <li><a href='addRecipe.jsp'>Add</a> a new Recipe.</li>
</ul>

</body>
</html>