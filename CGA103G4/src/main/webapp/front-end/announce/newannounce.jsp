<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcement.model.*"%>
<%@ page import="com.emp.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
AnnouncementService annSvc = new AnnouncementService();
List<AnnouncementVO> list = annSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="zh-Hant" dir="ltr">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>《See Food》官方網站</title>
<link rel="stylesheet" href="../css/common/all.css">
<link rel="stylesheet" href="../css/common/header.css">
<link rel="stylesheet" href="../css/common/footer.css">
<link rel="stylesheet" href="../css/common/main.css">
<link rel="stylesheet" href="../css/announce.css">
<script src="../js/announce.js"></script>
<script src="../js/nav.js"></script>
</head>

<body>

	<%@ include file="../tools/header.jsp"%>
	
			<img src="../images/000.jpg" style="width:100%;height:500px;position:absolute;transform:translateY(-1px);margin:0;padding:0;">
			<div class="picture -on" style="text-align:center;margin:0 auto;">
			<a href="#">
			<img src="<%=request.getContextPath()%>/AnnouncementPic?annid=9" style="width:60%;height:500px;position:relative;transform:translateY(-1px);margin:0;padding:0;"></a>
		</div>
		
	<div class="container -on" id="announcePage">
		<div id="announcePageContent">
			<main class="main">
		
				<div class="title" style="height:80px;"><b> 公告 </b></div>
					<ul class="item_list">
						<c:set var="count" value="0"></c:set>
						
						
						<c:forEach begin="1" end="${fn:length(list)}" varStatus="stat">
								<li><a href="#">
										<div class="img_block">
											<img
												src="<%=request.getContextPath()%>/AnnouncementPic?annid=${list[fn:length(list)-stat.index].annid}">
										</div> <span class="item_text"><br>
										<b>${list[fn:length(list)-stat.index].annTitle}</b></span> <span
										class="item_text content">${list[fn:length(list)-stat.index].annContent}</span><br>
										<span class="item_text content">發布日期
											${list[fn:length(list)-stat.index].annUpdate}</span>
								</a></li>
								<c:set var="count" value="${count+1}" />
						</c:forEach>
					</ul>
								
			</main>

		</div>

	</div>
	<%@ include file="../tools/footer.jsp"%>

</body>
</html>