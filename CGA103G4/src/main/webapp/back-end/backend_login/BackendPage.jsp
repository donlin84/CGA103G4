<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	歡迎來到後台
	管理員id:${empVO.empid}
	管理員權限:${empVO.empLevel}
	<a href="<%=request.getContextPath()%>/BackendLogin?action=remove_account">登出</a>
</body>
</html>