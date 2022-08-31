<%@page import="com.ClassPicture.model.ClassPictureJDBCDAO"%>
<%@page import="com.ClassPicture.model.ClassPictureVO"%>
<%@page import="java.util.List"%>
<%@page import="com.ClassIfm.model.ClassIfmVO"%>
<%@page import="com.ClassIfm.model.ClassIfmService"%>
<%@page import="com.teacher.model.TeacherVO"%>
<%@page import="com.teacher.model.TeacherService"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% ClassIfmService classifmSvc =  new ClassIfmService();
	List<ClassIfmVO> list=classifmSvc.getAll();
	pageContext.setAttribute("alllist", list);
%>


<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<style>
  table {
    width: 2050px;
    background-color: white;
    margin-left: 66px;
    border: 1px solid #CCCCFF;
  }
  th{
    border: 1px solid #CCCCFF;
    display:inline-block;
  	width:110px;
    line-height: 50px;
    padding: 5px;
    text-align: center;
  }
  td {
  	display:inline-block;
  	width:110px;
    height:200px;
    padding: 5px;
    border: #CCCCFF 1px solid;
    text-align: center;
    vertical-align:bottom;
  }
  .clapic{
    width: 345px;
  }
  .claIntroduction{
    width: 200px;
    overflow:hidden;
  }
  img{
    width: 100%;
    height:100%;
  }
</style>
</head>
<body>
	<a href="<%=request.getContextPath()%>/back-end/classifm/index_ClassIfm.jsp">回首頁</a>
	這是查詢所有課程資訊
	<table>
		<tr>
			<th>課程編號</th>
			<th>課程標題</th>
			<th class="clapic">課程圖片</th>
			<th>教師name</th>
			<th>課程標籤</th>
			<th class="claIntroduction">課程簡介</th>
			<th>授課時間</th>
			<th>課程價格</th>
			<th>課程人數上限</th>
			<th>課程人數下限</th>
			<th>已報名人數</th>
			<th>課程狀態</th>
			<th>報名開始時間</th>
			<th>報名結束時間</th>
			<th class="update_colume1">編輯</th>
		</tr>
		<c:forEach var="alllist" items="${alllist}" varStatus="abc" >
		<tr>
			<td>${alllist.claid}</td>
			<div class="cladiv">
				<td class="clatitle">${alllist.claTitle}</td>
			</div>
			<td class="clapic">
		        <div id="carouselExampleIndicators${abc.index}" class="carousel slide" data-bs-ride="carousel">
		          <div class="carousel-indicators">
		            <button type="button" data-bs-target="#carouselExampleIndicators${abc.index}" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
		            <button type="button" data-bs-target="#carouselExampleIndicators${abc.index}" data-bs-slide-to="1" aria-label="Slide 2"></button>
		            <button type="button" data-bs-target="#carouselExampleIndicators${abc.index}" data-bs-slide-to="2" aria-label="Slide 3"></button>
		          </div>
		          <div class="carousel-inner">
		            <div class="carousel-item active">
		              <img src="<%=request.getContextPath()%>/ClassIfmPic?id=${alllist.claid}&page=0" class="d-block w-100" alt="...">
		            </div>
		            <div class="carousel-item">
		              <img src="<%=request.getContextPath()%>/ClassIfmPic?id=${alllist.claid}&page=1" class="d-block w-100" alt="...">
		            </div>
		            <div class="carousel-item">
		              <img src="<%=request.getContextPath()%>/ClassIfmPic?id=${alllist.claid}&page=2" class="d-block w-100" alt="...">
		            </div>
		          </div>
		          <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators${abc.index}" data-bs-slide="prev">
		            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		            <span class="visually-hidden">Previous</span>
		          </button>
		          <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators${abc.index}" data-bs-slide="next">
		            <span class="carousel-control-next-icon" aria-hidden="true"></span>
		            <span class="visually-hidden">Next</span>
		          </button>
		        </div>
			</td>
			<td>${alllist.thrid} ${alllist.teacherVO.thrName}</td>
			<td>${alllist.claTagid} ${alllist.classTagVO.claTagName}</td>
			<td class="claIntroduction">${alllist.claIntroduction}</td>
			<td>${fn:replace((alllist.claTime), "T", " ")}</td> 
			<td>
				<span>$</span>
				${alllist.claPrice}
			</td>
			<td>${alllist.claPeopleMax}</td>
			<td>${alllist.claPeopleMin}</td>
			<td>${alllist.claPeople}</td>
			<td>
				<c:choose>
		            <c:when test="${alllist.claStatus==0}">
		                下架
		            </c:when>
		            <c:when test="${alllist.claStatus==1}">
		                上架
		            </c:when>
		            <c:when test="${alllist.claStatus==2}">
		                已結束
		            </c:when>
		            <c:otherwise>
		                取消
		            </c:otherwise>
		        </c:choose>
			</td>
			<td>${fn:replace((alllist.claStrTime), "T", " ")}</td>
			<td>${fn:replace((alllist.claFinTime), "T", " ")}</td>
			<td class="update_colume2">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ClassIfmServlet">
				     <input type="submit" value="修改">
				     <input type="hidden" name="claid"  value="${alllist.claid}">
				     <input type="hidden" name="action"	value="forward_to_update">
			    </FORM>
			</td>
		</tr>
	</c:forEach>
	</table>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>