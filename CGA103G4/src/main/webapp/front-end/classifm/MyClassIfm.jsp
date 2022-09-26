<%@page import="com.registtrationform.model.RegisttrationFormVO"%>
<%@page import="com.member.model.*"%>
<%@page import="java.util.List"%>
<%@page import="com.registtrationform.model.RegisttrationFormService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.stream.Collectors" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
// 	Integer memid = session.getAttribute("會員的id");
//	Integer memid=202;
MemberVO memberVO = (MemberVO) session.getAttribute("memVO");
Integer memid=memberVO.getMemid();
	RegisttrationFormService regSrv = new RegisttrationFormService();
	List<RegisttrationFormVO> list=regSrv.getAll();
	request.setAttribute("MyClassIfm", list.stream()
						.filter(c -> c.getMemid().equals(memid))
						.collect(Collectors.toList()));
%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>《See Food》官方網站</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/common/all.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/common/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/common/footer.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/common/main.css">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="../assets/css/style.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/front-end/assets/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/assets/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/assets/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/nav.js"></script>

<style>
*{
    text-align: center;
  }
   .abc {
   color: #60492d;
    margin: 0px auto;
    width: 910px;
    background-color: white;
    margin-top:10px;
    border: 1px solid black;
        border-collapse: separate;
    
  }
  th{
	font-weight: 700;
    display:inline-block;
  	width:150px;
    line-height: 50px;
    padding: 5px;
    text-align: center;
    background-color: rgb(248, 184, 110);
    color: white;
   }
   td {
  	font-weight: 600;
  	display:inline-block;
  	width:150px;
    height:50px;
    padding: 5px;
    text-align: center;
    line-height: 50px;
  }
  tr{
  	border: 1px solid white;
  }
  .td_introduct{
  	display:inline-block;
  	width:210px;
    height:200px;
    padding: 5px;
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
	 margin-top:10px;
  }
  .td_time{
  	 line-height: 20px;
     margin-top:15px;
  	 text-align: left;
     text-align: center;
  }
  .clapic{
    width: 345px;
  }

  #broom{
  position: relative;
  top: -10px;
  left: 120px;
  width: 40px;
  height: 40px;
}
.select_bar{
	top:110px;
	left:12px;
	position: relative;
	background-color: rgb(248, 184, 110);
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
.bigest_div{
	overflow:scroll;
	padding-bottom:100px;
}
a{
	text-decoration: none;
}
</style>
</head>
<body>
<%@ include file="../tools/header.jsp"%>
<br>
<br>
<br>
<br>
<h1 style="font-size:25px;">我的課程</h3>
<br>
<table class="abc">
	<tr>
		<th>會員</th>
		<th>課程</th>
		<th>上課時間</th>
		<th>報名人數</th>
		<th>狀態</th>
		<th>更改</th>
	</tr>
<c:forEach var="MyClassIfm" items="${MyClassIfm}">
	<tr>
		<td>${MyClassIfm.getmemberVO().memName}</td>
		<td class="td_title">${MyClassIfm.getClassIfmVO().claTitle}</td>
<%-- 		<td class="td_time">${fn:replace((MyClassIfm.getClassIfmVO().claTime), "T", " ")}</td> --%>
		<td class="td_time">${MyClassIfm.getClassIfmVO().claTime.toString().replace("T"," ")}</td>
		<td>${MyClassIfm.regPeople}</td>
		<td>
			<c:choose>
		            <c:when test="${MyClassIfm.regStatus==0}">
		                已報名
		            </c:when>
		            <c:when test="${MyClassIfm.regStatus==1}">
		                取消
		            </c:when>
		            <c:otherwise>
		                已退款
		            </c:otherwise>
		     </c:choose>
		</td>
		<td>
			<c:choose>
		            <c:when test="${MyClassIfm.regStatus==1}">
		                
		            </c:when>
		            <c:otherwise>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/NewRegisttrationformServlet">
				<input type="submit" value="取消訂單" style="margin-top: 10px;cursor: pointer;line-height: 20px;">
				<input type="hidden" name="claid"  value="${MyClassIfm.claid}">
				<input type="hidden" name="memid"  value="${MyClassIfm.memid}">
				<input type="hidden" name="regPeople"  value="${MyClassIfm.regPeople}">
				<input type="hidden" name="action"	value="myclassifm_cancel">
			 </FORM>
		            </c:otherwise>
		     </c:choose>
		</td>
	</tr>
</c:forEach>
</table>
<br>
<br>
<br>
<br>
<br>
<%@ include file="../tools/footer.jsp"%>
</body>
</html>