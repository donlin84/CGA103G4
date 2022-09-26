<%@page import="com.ClassTag.model.ClassTagVO"%>
<%@page import="java.util.List"%>
<%@page import="com.ClassTag.model.ClassTagService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ClassTagService classtagSrv = new ClassTagService();
	List<ClassTagVO> list=classtagSrv.getAll();
	pageContext.setAttribute("alllist", list);
%>
<html>
<head>
<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script src='http://code.jquery.com/jquery-3.6.0.min.js'></script>
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
    margin:18px auto 0px auto;
    border: 1px solid #CCCCFF;
  }
  th{
  	font-size:20px;
  	background-color:rgb(87 102 216);
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
  .select_bar{
	top:130px;
	left:70px;
	position: relative;
	background-color: rgb(87 102 216);
	border-radius: 10px;
	width:180px;
}
.select_span{
	position: relative;
	left:10px;
	display:block;
	font-size:20px;
	letter-spacing: 2px;
	color:white;
}
  .head{
      background-color: rgb(248, 184, 110);
      padding: 10px;
      color:white;
    }
    .xxx{
      padding: 10px;
    }
    .head_span{
      margin: 5px;
    }
    .head_span_phone{
    	margin: 12px;
    }
    .head_span_status{
    	margin-left:10px;
    }
    .head_memid{
      margin: 10px;
    }
    .head_memname{
      margin: 20px;
    }
    .head_gender{
      margin: 15px;
    }
    .head_phone{
      margin: 10px;
    }
    .head_people{
      margin: 15px;
    }
    .head_status{
      margin: 15px;
    }
    .modal-content{
    	width:520px;
    	padding-bottom:100px;
    }
    img{
    width: 100%;
    height:100%;
  }
  #broom{
  position: relative;
  top: -10px;
  left: 120px;
  width: 40px;
  height: 40px;
}
</style>
</head>
<body>
	<%@ include file="../tools/header.jsp"%>
	<div class="select_bar">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ClassTagServlet">
			<span class="select_span">篩選條件 :</span>
			<select name="claTagStatus" id="" style="margin-left:10px;">
	            <option value="none" selected disabled hidden>-選擇狀態-</option>
	            <option value="1" ${(claTagStatus==1)?'selected':''}>上架</option>
	            <option value="0" ${(claTagStatus==0)?'selected':''}>下架</option>
	        </select>
	        <input type="hidden" name="action" value="getall_status">
	        <input type="submit" value="查詢">
		</FORM>
		<a href="<%=request.getContextPath()%>/back-end/classtag/listAllClassTag.jsp" id="clean_a">
	        <img src="<%=request.getContextPath()%>/front-end/classifm/classifm_image/clean.svg" alt="" id="broom">
	    </a>
	</div>
	<table>
		<tr>
			<th>標籤編號</th>
			<th>標籤名稱</th>
			<th>標籤狀態</th>
			<th>編輯</th>
		</tr>
		<c:forEach var="alllist" items="${(getallstatus==null)?(alllist):(getallstatus)}">
		<tr>
			<td>${alllist.claTagid}</td>
			<td>${alllist.claTagName}</td>
			<td style="${(alllist.claTagStatus==0)?'color:red;':''}">
				<c:choose>
		            <c:when test="${alllist.claTagStatus==0}">
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
				     <input type="hidden" name="claTagid"  value="${alllist.claTagid}">
				     <input type="hidden" name="action"	value="forward_to_update">
			    </FORM>
			</td>
		</tr>
		</c:forEach>
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
	<script>
		$("table tr:odd").css("background-color","rgb(236, 231, 231)");
	</script>
</body>
</html>