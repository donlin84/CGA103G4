<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<style>
        .my_form{
            position: relative;
            top: 200px;
            left: 550px;
            background-color: rgb(255, 185, 127);
            width: 500px;
            height: 330px;
            border-radius: 20px;
        }
        .div_title{
            text-align: center;
            padding-top: 1px;
            margin-bottom: 40px;
        }
        .tagname_div{
            margin-bottom: 30px;
        }
        .title{
            margin-left: 50px;
            font-size: 20px;
            font-weight: bolder;
        }
        .tagname_div>input{
            margin-left: 20px;
            width: 200px;
            height: 25px;
            border-radius: 9px;
        }
        .tagstatus_div{
            margin-bottom: 55px;
        }
        .tagstatus_div>select{
            margin-left: 20px;
            padding: 5px;
        }
        .addbutton{
            margin-left: 215px;
            border-radius: 20px;
            cursor: pointer;
            padding: 10px 20px;
            background-color: aliceblue;
        }
        .error_div{
        	font-size:18px;
        	position: absolute;
        	left:30px;
        	margin-top:170px;
        }
    </style>
</head>
<body>
	<%@ include file="../tools/header.jsp"%>
	<c:if test="${not empty errorMsgs}">
		<div class="error_div">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	這是新增課程標籤
	<form action="<%=request.getContextPath()%>/ClassTagServlet" method="post" class="my_form">
        <div class="div_title">
            <h1>新增課程標籤</h1>
        </div>
        <table>
            <div class="tagname_div">
                <span class="title">標籤名稱 :</span>
                <input type="text" name="claTagName" value="${param.claTagName}">
            </div>
            <div class="tagstatus_div">
                <span class="title">標籤狀態 :</span>
                <select name="claTagStatus" id="" value="${param.claTagStatus}">
                    <option value="0">下架</option>
                    <option value="1">上架</option>
                </select>
            </div>
        </table>
        <input type="hidden" value="add" name="action">
        <input type="submit" value="新增資料" class="addbutton">
    </form>
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