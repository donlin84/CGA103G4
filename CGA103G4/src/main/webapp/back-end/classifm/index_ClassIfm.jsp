<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	<a href='listAllClassIfm.jsp'>查看所有課程</a><br>
<%-- 	<form action="<%=request.getContextPath()%>/ClassIfmPic" method="post" > --%>
<!-- 		<input type="submit" value="查圖片"> -->
<!-- 	</form> -->
	<a href="addClassIfm.jsp">新增課程</a><br>
	
	<form METHOD="post" ACTION="<%=request.getContextPath()%>/ClassIfmServlet" >
        <b>輸入課程編號</b>
        <input type="text" name="claid">
        <input type="hidden" name="action" value="getone">
        <input type="submit" value="送出">
    </form>
	
	
</body>
</html>