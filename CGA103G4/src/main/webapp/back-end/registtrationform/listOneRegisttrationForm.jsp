<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.registtrationform.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>員工資料 - listOneRegisttrationForm.jsp</title>
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
    margin:128px auto 0px auto;
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
<table>
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
	<tr>

		<td   style="width:210px;">${regvo.claid} ${regvo.getClassIfmVO().claTitle}</td>
		<td>${regvo.memid} ${regvo.getmemberVO().memName}</td>
		<td>
			<c:choose>
		            <c:when test="${regvo.regPayment==0}">
		                轉帳
		            </c:when>
		            <c:when test="${regvo.regPayment==1}">
		                信用卡
		            </c:when>
		            <c:otherwise>
		                
		            </c:otherwise>
		     </c:choose>
		</td>
		<td>${fn:replace(regvo.regTime, "T", " ")}</td>
		<td>
			<c:choose>
		            <c:when test="${regvo.regStatus==0}">
		                已報名
		            </c:when>
		            <c:when test="${regvo.regStatus==1}">
		                取消
		            </c:when>
		            <c:when test="${regvo.regStatus==2}">
		                已退款
		            </c:when>
		            <c:otherwise>
		                
		            </c:otherwise>
		     </c:choose>
		</td>
		<td>${regvo.regPeople}</td>
		<td>
			<c:choose>
		            <c:when test="${regvo.regReview==1}">
		                待加強
		            </c:when>
		            <c:when test="${regvo.regReview==2}">
		                尚可
		            </c:when>
		            <c:when test="${regvo.regReview==3}">
		                普通
		            </c:when>
		            <c:when test="${regvo.regReview==4}">
		                不錯
		            </c:when>
		            <c:when test="${regvo.regReview==5}">
		                滿意
		            </c:when>
		            <c:otherwise>
		                
		            </c:otherwise>
		     </c:choose>
		</td>
		<td class="td_introduct">${regvo.regReviewContent}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/NewRegisttrationformServlet">
				     <input type="submit" value="修改">
				     <input type="hidden" name="claid"  value="${regvo.claid}">
				     <input type="hidden" name="memid"  value="${regvo.memid}">
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
	<script>
		$("table tr:odd").css("background-color","rgb(236, 231, 231)");
	</script>		
</body>
</html>