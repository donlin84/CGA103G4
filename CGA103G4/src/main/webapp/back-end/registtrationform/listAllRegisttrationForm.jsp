<%@page import="java.time.LocalDateTime"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.registtrationform.model.*"%>


<%
	RegisttrationFormService registtrationFormSvc = new RegisttrationFormService();
    List<RegisttrationFormVO> list = registtrationFormSvc.getAll();
    request.setAttribute("list", list);
//     pageContext.setAttribute("list",list);
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

<table id="table-1">
	<tr><td>
		 <h3>"所有員工資料 - listAllRegisttrationForm.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

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
		<th>評價</th>
		<th>評價內容</th>
		<th>操作</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="registtrationFormVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
		<tr>
			<FORM METHOD="post" enctype= "multipart/form-data" ACTION="<%=request.getContextPath()%>/back-end/registtrationform/RegisttrationForm.do" style="margin-bottom: 0px;">
			<td><input type="TEXT" name="claid"  value=${registtrationFormVO.claid} style="background-color: #EFEFEF;color: #919191"  readonly ></td>
			<td><input type="TEXT" name="memid"  value=${registtrationFormVO.memid} style="background-color: #EFEFEF;color: #919191"  readonly ></td>
			<td>
			<select  name="regPayment">
    			<option value=${registtrationFormVO.regPayment} selected>${registtrationFormVO.regPayment == 0 ?"轉帳":"信用卡"}</option>
    			<option value= 0>轉帳</option>
    			<option value= 1>信用卡</option>
			</select>
			</td>
			<td><input type="TEXT" name="regTime"  value="${registtrationFormVO.regTime.toString().replace("T"," ")}" style="background-color: #EFEFEF;color: #919191"  readonly ></td> 
			<td>			
				<select  name="regStatus">
			
	    			<option value=${registtrationFormVO.regStatus} selected>${registtrationFormVO.regStatus == 0?"已報名":"取消"}</option>
	    			<option value= 0>已報名</option>
	    			<option value= 1>取消</option>

				</select>
			</td>
			<td>			
				<select  name="regReview">
	    			<option value=${registtrationFormVO.regReview == null ?0:registtrationFormVO.regReview} selected>${registtrationFormVO.regReview == null ?"請選擇評價":registtrationFormVO.regReview}</option>
	    			<option value= 1>1</option>
	    			<option value= 2>2</option>
	    			<option value= 3>3</option>
	    			<option value= 4>4</option>
	    			<option value= 5>5</option>
				</select>
			</td>
			<td><input type="TEXT" name="regReviewContent"  value=${registtrationFormVO.regReviewContent}></td>
		
			<td>
			  
			     <input type="submit" value="修改">
			     <input type="hidden" name="action"	value="update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/teacher/teacher.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="thrid"  value="${teacherVO.thrid}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

<script>

    
	
</script>

</body>
</html>

