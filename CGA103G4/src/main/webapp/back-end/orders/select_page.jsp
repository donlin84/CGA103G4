<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Orders: Home</title>

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
   <tr><td><h3>Orders: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Orders: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllOrders.jsp'>List</a> all Orders.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="orders.do" >
        <b>��J�q��s�� (�p1):</b>
        <input type="text" name="ordid">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
 
  <jsp:useBean id="ordersSvc" scope="page" class="com.orders.model.OrdersService" />
   
  <li>
     <FORM METHOD="post" ACTION="orders.do" >
       <b>��ܭq��s��:</b>
       <select size="1" name="ordid">
         <c:forEach var="ordersVO" items="${ordersSvc.getAll()}" > 
          <option value="${ordersVO.ordid}">${ordersVO.ordid}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="orders.do" > -->
<!--        <b>��ܭq��s��:</b> -->
<!--        <select size="1" name="ordid"> -->
<%--          <c:forEach var="ordersVO" items="${ordersSvc.all}" >  --%>
<%--           <option value="${ordersVO.ordid}">${ordersVO.ordid} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>


<h3>�q��޲z</h3>

<ul>
  <li><a href='addOrders.jsp'>Add</a> a new Order.</li>
</ul>

</body>
</html>