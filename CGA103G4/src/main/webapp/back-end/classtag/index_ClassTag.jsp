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
	�o�O����<br>
	<a href="<%=request.getContextPath()%>/back-end/classtag/listAllClassTag.jsp">�o�O�d�ݩҦ��ҵ{����</a><br>
	<a href="<%=request.getContextPath()%>/back-end/classtag/addClassTag.jsp">�o�O�s�W�ҵ{����</a><br>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<form action="<%=request.getContextPath()%>/ClassTagServlet" method="post">
		<b>��J�ҵ{���ҽs��</b>
        <input type="text" name="claTagid">
        <input type="hidden" name="action" value="getone_clatag">
        <input type="submit" value="�e�X">
	</form>
</body>
</html>