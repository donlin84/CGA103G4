<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	<a href='listAllClassIfm.jsp'>�d�ݩҦ��ҵ{</a><br>
<%-- 	<form action="<%=request.getContextPath()%>/ClassIfmPic" method="post" > --%>
<!-- 		<input type="submit" value="�d�Ϥ�"> -->
<!-- 	</form> -->
	<a href="addClassIfm.jsp">�s�W�ҵ{</a><br>
	
	<form METHOD="post" ACTION="<%=request.getContextPath()%>/ClassIfmServlet" >
        <b>��J�ҵ{�s��</b>
        <input type="text" name="claid">
        <input type="hidden" name="action" value="getone">
        <input type="submit" value="�e�X">
    </form>
	
	
</body>
</html>