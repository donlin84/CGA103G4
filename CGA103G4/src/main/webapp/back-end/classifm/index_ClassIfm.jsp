<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	<a href='<%=request.getContextPath()%>/back-end/classifm/listAllClassIfm.jsp'>查看所有課程</a><br>

	<a href="<%=request.getContextPath()%>/back-end/classifm/addClassIfm.jsp">新增課程</a><br>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<form METHOD="post" ACTION="<%=request.getContextPath()%>/ClassIfmServlet" >
        <b>輸入課程編號</b>
        <input type="text" name="claid">
        <input type="hidden" name="action" value="getone">
        <input type="submit" value="送出">
    </form>
	
	
</body>
</html>