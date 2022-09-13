<%@page import="java.time.LocalDateTime"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.registtrationform.model.*"%>
<%@ page import="com.ClassIfm.model.ClassIfmVO"%>
<%@ page import="com.ClassIfm.model.ClassIfmService"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	RegisttrationFormService registtrationFormSvc = new RegisttrationFormService();
    List<RegisttrationFormVO> list = registtrationFormSvc.getAll();
    pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有報名表資料 - listAllRegisttrationForm.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  

</style>

</head>
<body bgcolor='white'>

<h3>"所有員工資料 - listAllRegisttrationForm.jsp</h3>
<h4><a href="<%=request.getContextPath()%>/back-end/registtrationform/select_page.jsp">回首頁</a></h4>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<table>
	<tr>
		<th>課程ID</th>
		<th>會員ID</th>
		<th>付款方式</th>
		<th>報名時間</th>
		<th>訂單狀態</th>
		<th>報名人數</th>
		<th>評價</th>
		<th>評價內容</th>
		<th>操作</th>
	</tr>
	<c:forEach var="list" items="${list}">
	<tr>
		<td>${list.claid}</td>
		<td>${list.memid}</td>
		<td>${(list.regPayment==1)?'信用卡':'轉帳'}</td>
		<td>${fn:replace((list.regTime), "T", " ")}</td>
		<td>
			<c:choose>
		            <c:when test="${list.regStatus==0}">
		                已報名
		            </c:when>
		            <c:when test="${list.regStatus==1}">
		                取消
		            </c:when>
		            <c:otherwise>
		                已退款
		            </c:otherwise>
		     </c:choose>
		</td>
		<td>${list.regPeople}</td>
		<td>${list.regReview}</td>
		<td>${list.regReviewContent}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/NewRegisttrationformServlet">
				     <input type="submit" value="修改">
				     <input type="hidden" name="claid"  value="${list.claid}">
				     <input type="hidden" name="memid"  value="${list.memid}">
				     <input type="hidden" name="action"	value="forward_to_update">
			 </FORM>
		</td>
	</tr>
	</c:forEach>
</table>

<script>

    
	
</script>

</body>
</html>

