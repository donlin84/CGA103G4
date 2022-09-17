<%@page import="java.time.LocalDateTime"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.registtrationform.model.*"%>
<%@ page import="com.ClassIfm.model.ClassIfmVO"%>
<%@ page import="com.ClassIfm.model.ClassIfmService"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	RegisttrationFormService registtrationFormSvc = new RegisttrationFormService();
    List<RegisttrationFormVO> list = registtrationFormSvc.getAll();
    pageContext.setAttribute("list", list);
    ClassIfmService claSrv = new ClassIfmService();
    List<ClassIfmVO> list_c=claSrv.getAll();
    pageContext.setAttribute("list_c", list_c);
%>

<html>
<head>
<title>所有報名表資料 - listAllRegisttrationForm.jsp</title>
<script src='http://code.jquery.com/jquery-3.6.0.min.js'></script>
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
<style>
  table {
    width: 1193px;
    background-color: white;
    margin-left: 320px;
    margin-top:-65px;
    border: 1px solid #CCCCFF;
  }
  th{
	font-weight: 700;
    display:inline-block;
  	width:110px;
    padding: 5px;
    text-align: center;
    background-color: rgb(248, 184, 110);
    color: white;
    font-size:17px;
   }
   td {
  	font-weight: 500;
  	display:inline-block;
  	width:110px;
    padding: 5px;
    text-align: center;

  }
  tr{
  	border: 1px solid white;
  }
  .td_introduct{
  	display:inline-block;
  	width:210px;
    height:100px;
    padding: 1px;
    text-align: center;
 	line-height: 20px;
 	overflow:scroll;
  }
  .update_colume2{
  	display:inline-block;
    padding-top: 80px;
    text-align: center;
 	line-height: 30px;
  }
  .td_title{ 
  	 line-height: 20px;
	 padding-top:70px;
  }
  .td_time{
  	 line-height: 20px;
	 padding-top:75px;
  	 text-align: left;
  }
  .clapic{
    width: 345px;
  }
  img{
    width: 100%;
    height:100%;
  }
  #broom{
  position: relative;
  top: -10px;
  left: 230px;
  width: 40px;
  height: 40px;
}
.select_bar{
	top:50px;
	left:12px;
	position: relative;
	background-color: rgb(248, 184, 110);
	border-radius: 10px;
	width:290px;
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
.bigest_div{
	overflow:scroll;
	padding-bottom:100px;
}
a{
	text-decoration: none;
}
</style>
</head>
<body bgcolor='white'>
<%@ include file="../tools/header.jsp"%>

<h3>"所有員工資料 - listAllRegisttrationForm.jsp</h3>
<h4><a href="<%=request.getContextPath()%>/back-end/registtrationform/select_page.jsp">回首頁</a></h4>
<div class="select_bar">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/NewRegisttrationformServlet">
			<span class="select_span">查看單一課程 :</span>
			<select size="1" name="claid" style="margin-left:10px;">
				<option value="none" selected disabled hidden>-選擇課程-</option>
		         <c:forEach var="c" items="${list_c}"> 
		          	<option value="${c.claid}" ${(claid==c.claid)?'selected':''}>${c.claTitle}</option>
		         </c:forEach>
		    </select>
	        <input type="hidden" name="action" value="getreview">
	        <input type="submit" value="查詢">
		</FORM>
		<a href="<%=request.getContextPath()%>/back-end/registtrationform/listAllRegisttrationForm.jsp" id="clean_a">
	        <img src="<%=request.getContextPath()%>/front-end/classifm/classifm_image/clean.svg" alt="" id="broom">
	    </a>
	</div>
<table >
	<tr>
		<th style="width:210px;">課程ID</th>
		<th>會員ID</th>
		<th>付款方式</th>
		<th>報名時間</th>
		<th>訂單狀態</th>
		<th>報名人數</th>
		<th>評價</th>
		<th style="width:210px;">評價內容</th>
		<th>操作</th>
	</tr>
	<c:forEach var="list" items="${(getreview==null)?(list):(getreview)}">
	<tr>
		<td  style="width:210px;">${list.claid} ${list.getClassIfmVO().claTitle}</td>
		<td>${list.memid} ${list.getmemberVO().memName}</td>
		<td>${(list.regPayment==1)?'信用卡':'轉帳'}</td>
		<td>${fn:replace((list.regTime), "T", " ")}</td>
		<td>
			<c:choose>
		            <c:when test="${list.regStatus==0}">
		                已報名
		            </c:when>
		            <c:when test="${list.regStatus==1}">
		                取消
		            </c:when>
		            <c:otherwise>
		                已退款
		            </c:otherwise>
		     </c:choose>
		</td>
		<td>${list.regPeople}</td>
		<td>
			<c:choose>
		            <c:when test="${list.regReview==1}">
		                待加強
		            </c:when>
		            <c:when test="${list.regReview==2}">
		                尚可
		            </c:when>
		            <c:when test="${list.regReview==3}">
		                普通
		            </c:when>
		            <c:when test="${list.regReview==4}">
		                不錯
		            </c:when>
		            <c:when test="${list.regReview==5}">
		                滿意
		            </c:when>
		            <c:otherwise>
		                
		            </c:otherwise>
		     </c:choose>
		</td>
		<td class="td_introduct">${list.regReviewContent}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/NewRegisttrationformServlet">
				     <input type="submit" value="修改" class="my_submit">
				     <input type="hidden" name="claid"  value="${list.claid}">
				     <input type="hidden" name="memid"  value="${list.memid}">
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

