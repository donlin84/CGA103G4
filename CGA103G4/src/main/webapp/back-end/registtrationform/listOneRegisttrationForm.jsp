<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.registtrationform.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>員工資料 - listOneRegisttrationForm.jsp</title>

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
	width: 1000px;
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

<h3>員工資料 - ListOneRegisttrationForm.jsp</h3>
<h4><a href="<%=request.getContextPath()%>/back-end/registtrationform/select_page.jsp">回首頁</a></h4>


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
	<tr>

		<td>${regvo.claid }</td>
		<td>${regvo.memid}</td>
		<td>${(regvo.regPayment==1)?'信用卡':'轉帳'}</td>
		<td>${fn:replace(regvo.regTime, "T", " ")}</td>
		<td>
			<c:choose>
		            <c:when test="${regvo.regStatus==0}">
		                已報名
		            </c:when>
		            <c:when test="${regvo.regStatus==1}">
		                取消
		            </c:when>
		            <c:otherwise>
		                已退款
		            </c:otherwise>
		     </c:choose>
		</td>
		<td>${regvo.regPeople}</td>
		<td>${regvo.regReview}</td>
		<td>${regvo.regReviewContent}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/NewRegisttrationformServlet">
				     <input type="submit" value="修改">
				     <input type="hidden" name="claid"  value="${regvo.claid}">
				     <input type="hidden" name="memid"  value="${regvo.memid}">
				     <input type="hidden" name="action"	value="forward_to_update">
			 </FORM>
		</td>
	</tr>
</table>


</body>
</html>