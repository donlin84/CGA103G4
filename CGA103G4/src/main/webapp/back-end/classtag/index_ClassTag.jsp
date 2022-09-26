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
	這是首頁<br>
	<a href="<%=request.getContextPath()%>/back-end/classtag/listAllClassTag.jsp">這是查看所有課程標籤</a><br>
	<a href="<%=request.getContextPath()%>/back-end/classtag/addClassTag.jsp">這是新增課程標籤</a><br>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<form action="<%=request.getContextPath()%>/ClassTagServlet" method="post">
		<b>輸入課程標籤編號</b>
        <input type="text" name="claTagid">
        <input type="hidden" name="action" value="getone_clatag">
        <input type="submit" value="送出">
	</form>
</body>
</html>