<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chefappointmentform.model.*"%>
<%@page import="com.member.model.MemberVO"%>
<!--    以下等同第9行 -->
<%
MemberVO memVO = (MemberVO) session.getAttribute("memVO");
Integer memid = memVO.getMemid();
ChefAppointmentFormJDBCDAO dao = new ChefAppointmentFormJDBCDAO();
List<ChefAppointmentFormVO> list = dao.getAllByMem(memid); 
pageContext.setAttribute("list", list); 
%>
<!DOCTYPE html>
<html>
<head>
<title>我的訂閱</title>
<link rel="stylesheet" href="../css/common/all.css">
<link rel="stylesheet" href="../css/common/header.css">
<link rel="stylesheet" href="../css/common/footer.css">
<link rel="stylesheet" href="../css/common/main.css">
<link rel="stylesheet" href="../css/chef.css">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="../assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="../assets/css/style.css" rel="stylesheet" type="text/css">
<link
	href="<%=request.getContextPath()%>/front-end/member/assets/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=request.getContextPath()%>/front-end/member/assets/css/icons.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=request.getContextPath()%>/front-end/member/assets/css/style.css"
	rel="stylesheet" type="text/css">
<script
	src="<%=request.getContextPath()%>/front-end/assets/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/assets/js/popper.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/assets/js/bootstrap.min.js"></script>
<style>
h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: auto;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: 25%;
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid #212529;
	padding: 5px;
	text-align: center;
}
tr:nth-child(even) {
  background: #cccccc9e
}
tr:nth-child(odd) {
  background-color: #FAFAFA;
}
input{
background-color: #FAFAFA;
}
    

</style>

</head>
<body bgcolor='white'>
	<%@ include file="./tools/header.jsp"%>

	<div class="main">
		<div class="container -on" id="chefPage">
			<div id="chefPageContent">
				<div class="sectionOne">
					<table>
						<tr style="background-color:#f0e3cc">
							<th>預約單編號</th>
							<th>私廚名字</th>
							<th>預約日期</th>
							<th>預約時段</th>
							<th>預約金額</th>
							<th>預約狀態</th>
							<th>預約評價</th>
							<th>評論</th>
							<th>評價</th>

						</tr>
						<%@ include file="page1.file"%>
						<c:forEach var="chefapp" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>${chefapp.apmid}</td>
								<td>${chefapp.chefVO.chefName}</td>
								<td>${chefapp.apmDate}</td>
								<td>${chefapp.apmTime==0 ? '午餐':'晚餐'}</td>
								<td>${chefapp.apmPrice}</td>
								<td><c:choose>
										<c:when test="${chefapp.apmStatus==0}">   
								待審核
              </c:when>
										<c:when test="${chefapp.apmStatus==1}">   
                    			接受  
			  </c:when>
										<c:when test="${chefapp.apmStatus==2}">   
                    			完成  
			  </c:when>
										<c:otherwise>   
                                取消
              </c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${chefapp.star==1}">   
								★
              </c:when>
										<c:when test="${chefapp.star==2}">   
                    			★★  
			  </c:when>
										<c:when test="${chefapp.star==3}">   
                    			★★★  
			  </c:when>
										<c:when test="${chefapp.star==4}">   
                    			★★★★ 
			  </c:when>
										<c:when test="${chefapp.star==5}">   
                    			★★★★★ 
			  </c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose></td>
								<td>${chefapp.comments}</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/front-end/chefAppointment/chefapp.do"
										style="margin-bottom: 0px;">
										<input type="submit" value="修改"
											style=${(chefapp.apmStatus==2)? "":"display:none"}> <input
											type="hidden" name="apmid" value="${chefapp.apmid}">
										<input type="hidden" name="action"
											value="MemgetOne_For_Update">
									</FORM>
								</td>
							</tr>
						</c:forEach>
					</table>
					<%@ include file="page2.file"%>
				</div>
			</div>
		</div>
	</div>
	<script>
		
	</script>
</body>
</html>
<%@ include file="../tools/footer.jsp"%>