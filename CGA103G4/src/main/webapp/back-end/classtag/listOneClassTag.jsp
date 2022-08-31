<%@page import="com.ClassTag.model.ClassTagVO"%>
<%@page import="java.util.List"%>
<%@page import="com.ClassTag.model.ClassTagService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
  table {
    width: 500px;
    background-color: white;
    margin-left: 66px;
    border: 1px solid #CCCCFF;
  }
  th{
    border: 1px solid #CCCCFF;
    padding: 5px;
    text-align: center;
  }
  td {
    padding: 5px;
    border: #CCCCFF 1px solid;
    text-align: center;
  }
</style>
</head>
<body>
	<a href="<%=request.getContextPath()%>/back-end/classtag/index_ClassTag.jsp">回首頁</a>
	這是單一查詢課程標籤
	<table>
		<tr>
			<th>標籤編號</th>
			<th>標籤name</th>
			<th>標籤狀態</th>
			<th>編輯</th>
		</tr>
		<tr>
			<td>${classTagVO.claTagid}</td>
			<td>${classTagVO.claTagName}</td>
			<td>
				<c:choose>
		            <c:when test="${classTagVO.claTagStatus==0}">
		                下架
		            </c:when>
		            <c:otherwise>
		                上架
		            </c:otherwise>
		    	</c:choose>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ClassTagServlet">
				     <input type="submit" value="修改">
				     <input type="hidden" name="claTagid"  value="${classTagVO.claTagid}">
				     <input type="hidden" name="action"	value="forward_to_update">
			    </FORM>
			</td>
		</tr>
	</table>
</body>
</html>