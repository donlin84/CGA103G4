<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.registtrationform.model.*"%>

<%
	RegisttrationFormVO registtrationFormVO = (RegisttrationFormVO) request.getAttribute("registtrationFormVO");
%>
<html>
<head>
<title>教師資料新增 - addRegisttrationForm.jsp</title>
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

<table id="table-1">
	<tr><td>
		 <h3>報名表資料新增 - addRegisttrationForm.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post"   ACTION="RegisttrationForm.do" name="form1" enctype= "multipart/form-data">
<table>
	<tr>
		<th>課程ID</th>
		<th>會員ID</th>
		<th>付款方式</th>
		<th>訂單狀態</th>
		<th>評價</th>
		<th>評價內容</th>
		<th>操作</th>
		
	</tr>
	<td><input type="TEXT" name="claid"  value=${registtrationFormVO == null? "": registtrationFormVO.claid} ></td>
	<td><input type="TEXT" name="memid"  value=${registtrationFormVO == null? "": registtrationFormVO.memid} ></td>
	<td>
	<select  name="regPayment">
  			<option value=${registtrationFormVO == null? "": registtrationFormVO.regPayment}  selected>${registtrationFormVO == null? "請選擇付款方式": (registtrationFormVO.regPayment == 0 ?"轉帳":"信用卡")} </option>
  			<option value= 0>轉帳</option>
  			<option value= 1>信用卡</option>
	</select>
	</td> 
	<td>
	<select  name="regStatus">
  			<option value=${registtrationFormVO == null? "": registtrationFormVO.regStatus}  selected>${registtrationFormVO == null? "請選擇訂單狀態": (registtrationFormVO.regStatus == 0 ?"已報名":"取消")} </option>
  			<option value= 0>已報名</option>
  			<option value= 1>取消</option>
	</select>
	</td> 
	<td>
	<select  name="regReview">
  			<option value=${registtrationFormVO == null? 0 : (registtrationFormVO.regReview==null ? 0: registtrationFormVO.regReview)}  selected>${registtrationFormVO == null? "請選擇評價" : (registtrationFormVO.regReview==null ? "請選擇評價": registtrationFormVO.regReview)} </option>
  			<option value= 1>1</option>
  			<option value= 2>2</option>
  			<option value= 3>3</option>
  			<option value= 4>4</option>
  			<option value= 5>5</option>
	</select>
	</td> 
	<td><input type="TEXT" name="regReviewContent"  value=${registtrationFormVO.regReviewContent}></td>
	<td>
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增"></FORM>
	</td>
</table>
<br>

<script>

</script>

</body>

</html>