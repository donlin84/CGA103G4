<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chefappointmentform.model.*"%>

<!--    以下等同第9行 -->
<%--    <jsp:useBean id="list" scope="session" type="java.util.List<EmpVO>" /> --%>
<%
    ChefAppointmentFormJDBCDAO dao = new ChefAppointmentFormJDBCDAO();
    List<ChefAppointmentFormVO> list = dao.getAllByMem(201);       // 此行的list變數(物件)將提供page1.file的第11行取得查詢到的總筆數，再由page1.file進行分頁的需要
    pageContext.setAttribute("list",list); // 將上一行的list變數(物件)存入當前頁面pageContext，再由底下的第83行由JSTL的forEach列印出結果
%>
<!DOCTYPE html>
<html>
<head>
<title>所有管理員資料</title>
 <link rel="stylesheet" href="../css/common/all.css">
 <link rel="stylesheet" href="../css/common/header.css">
 <link rel="stylesheet" href="../css/common/footer.css">
 <link rel="stylesheet" href="../css/common/main.css">
 <link rel="stylesheet" href="../css/chef.css">
 <link href="../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="../assets/css/style.css" rel="stylesheet" type="text/css">
<style>
  h4 {
    color: blue;
    display: inline;
  }

</style>

<style>
  table {
	width: auto;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:25%;
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
<%@ include file="./tools/header.jsp"%>
  <div class="main">
  <div class="container -on" id="chefPage">
  <div id="chefPageContent">
  <div class="sectionOne">
<table>
	<tr>
		<th>預約單編號</th>
		<th>私廚名字</th>
		<th>預約日期</th>
		<th>預約時段</th>
		<th>預約金額</th>
		<th>預約狀態</th>
		<th>預約評價</th>
		<th>評論</th>

	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="chefapp" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>	
			<td>${chefapp.apmid}</td>
			<td>${chefapp.chefVO.chefName}</td>
			<td>${chefapp.apmDate}</td>
			<td>${chefapp.apmTime==0 ? '午餐':'晚餐'}</td>
			<td>${chefapp.apmPrice}</td>			
			<td>
        <c:choose> 
              <c:when test="${chefapp.apmStatus==0}">   
								待審核
              </c:when> 
              <c:when test="${chefapp.apmStatus==1}">   
                    			接受  
			  </c:when> 
              <c:when test="${chefapp.apmStatus==2}">   
                    			完成  
			  </c:when> 
              <c:otherwise>   
                                取消
              </c:otherwise> 
      </c:choose> 
			</td>
			<td>${chefapp.star}</td>
			<td>${chefapp.comments}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/chefAppointment/chefapp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="給予評價">
			     <input type="hidden" name="apmid"  value="${chefapp.apmid}">
			     <input type="hidden" name="action"	value="MemgetOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
<%@ include file="../tools/footer.jsp"%>