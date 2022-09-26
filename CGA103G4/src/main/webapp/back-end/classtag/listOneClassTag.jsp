<%@page import="com.ClassTag.model.ClassTagVO"%>
<%@page import="java.util.List"%>
<%@page import="com.ClassTag.model.ClassTagService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/back-end/assets/images/favicon.ico">
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/fullcalendar/vanillaCalendar.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/morris/morris.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/css/style.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
 table {
    width: 900px;
    background-color: white;
    margin:130px auto 0px auto;
    border: 1px solid #CCCCFF;
  }
  th{
  	font-size:20px;
  	background-color: rgb(248, 184, 110);
    color: white;
  	font-weight: 700;
    padding: 20px;
    text-align: center;
  }
  td {
  	font-size:18px;
  	font-weight: 600;
    padding: 5px;
    text-align: center;
  }
   .my_submit{
  	font-weight: 700;
  	margin-top:15px;
  	font-size:15px;
  	padding:5px 20px;
  	border-radius: 10px;
  	cursor: pointer;
  }
</style>
</head>
<body>
	<%@ include file="../tools/header.jsp"%>
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
			<td style="${(classTagVO.claTagStatus==0)?'color:red;':''}">
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
				     <input type="submit" value="修改" class="my_submit">
				     <input type="hidden" name="claTagid"  value="${classTagVO.claTagid}">
				     <input type="hidden" name="action"	value="forward_to_update">
			    </FORM>
			</td>
		</tr>
	</table>
	<%@ include file="../tools/footer.jsp"%>
    <!-- End Footer -->
	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/back-end/assets/js/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/modernizr.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/waves.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/jquery.nicescroll.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/skycons/skycons.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/tiny-editable/mindmup-editabletable.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/tiny-editable/numeric-input-example.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/fullcalendar/vanillaCalendar.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/raphael/raphael-min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/plugins/morris/morris.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/app.js"></script>
</body>
</html>