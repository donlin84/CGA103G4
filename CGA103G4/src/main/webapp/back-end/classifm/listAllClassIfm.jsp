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
<%@page import="com.member.model.MemberVO"%>
<%@page import="com.member.model.MemberService"%>
<%@page import="java.util.List"%>
<%@page import="com.registtrationform.model.RegisttrationFormService"%>
<%@page import="com.ClassIfm.model.ClassIfmVO"%>
<%@page import="com.ClassIfm.model.ClassIfmService"%>
<%@page import="com.registtrationform.model.RegisttrationFormVO"%>
<%@page import="java.util.ArrayList"%>
<% 
	ClassIfmService classifmSvc =  new ClassIfmService();
 	List<ClassIfmVO> list=classifmSvc.getAll();
 	pageContext.setAttribute("alllist", list);
 	for(ClassIfmVO C : list){

 		List<MemberVO>list3= new ArrayList<MemberVO>();

 		RegisttrationFormService regSrv = new RegisttrationFormService();
 		List<RegisttrationFormVO> list_click=regSrv.click_people(C.getClaid());//看第幾個課程回傳報名的會員list*********

 		for(RegisttrationFormVO reg : list_click) {//用會員list迭代			<再拿出報名表狀態塞進去>
 			MemberService memSrv = new MemberService();
 			MemberVO membervo=memSrv.getOneMember(reg.getMemid());//透過會員id拿取那個會員整筆資料回傳vo
 			
 			RegisttrationFormService refVO = new RegisttrationFormService();
 			RegisttrationFormVO regvo=refVO.getOneRegisttrationForm(C.getClaid(), reg.getMemid());
 			
 			membervo.setPeople(regvo.getRegPeople());//找人數 塞進去
 			membervo.setRegstatus(regvo.getRegStatus());//找狀態
 			list3.add(membervo);//把vo加進 上面宣告的list裡 
 		}
 			pageContext.setAttribute("regall",list3);
 	}
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='http://code.jquery.com/jquery-3.6.0.min.js'></script>
<link href="<%=request.getContextPath()%>/back-end/classifm/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/back-end/assets/images/favicon.ico">
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/fullcalendar/vanillaCalendar.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/back-end/assets/plugins/morris/morris.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/assets/css/style.css" rel="stylesheet" type="text/css">
<style>
  table {
    width: 1988px;
    background-color: white;
    margin-left: 200px;
    margin-top:-6px;
    border: 1px solid #CCCCFF;
  }
  th{
	font-weight: 700;
    display:inline-block;
  	width:110px;
    line-height: 50px;
    padding: 5px;
    text-align: center;
    background-color: rgb(248, 184, 110);
    color: white;
   }
   td {
  	font-weight: 500;
  	display:inline-block;
  	width:110px;
    height:200px;
    padding: 5px;
    text-align: center;
 	line-height: 180px;

  }
  tr{
  	border: 1px solid white;
  }
  .td_introduct{
  	display:inline-block;
  	width:210px;
    height:200px;
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
	<div class="bigest_div">
		<a href="<%=request.getContextPath()%>/back-end/classifm/index_ClassIfm.jsp">回首頁</a>
	這是查詢所有課程資訊
	<div class="select_bar">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ClassIfmServlet">
			<span class="select_span">篩選條件 :</span>
			<select name="clastatus" id="" style="margin-left:10px;">
	            <option value="none" selected disabled hidden>-選擇狀態-</option>
	            <option value="0" ${(clastatus==0)?'selected':''}>下架</option>
	            <option value="1" ${(clastatus==1)?'selected':''}>上架</option>
	            <option value="2" ${(clastatus==2)?'selected':''}>已結束</option>
	            <option value="3" ${(clastatus==3)?'selected':''}>取消</option>
	            <option value="4" ${(clastatus==4)?'selected':''}>取消已通知</option>
	        </select>
	        <input type="hidden" name="action" value="getall_status">
	        <input type="submit" value="查詢">
		</FORM>
		<a href="<%=request.getContextPath()%>/back-end/classifm/listAllClassIfm.jsp" id="clean_a">
	        <img src="<%=request.getContextPath()%>/front-end/classifm/classifm_image/clean.svg" alt="" id="broom">
	    </a>
	</div>
	<table>
		<tr>
			<th>課程編號</th>
			<th>課程標題</th>
			<th class="clapic">課程圖片</th>
			<th>教師name</th>
			<th>課程標籤</th>
			<th style="width:210px;">課程簡介</th>
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
		<c:forEach var="alllist" items="${(getallstatus==null)?(alllist):(getallstatus)}" varStatus="abc" >
		<tr>
			<td style="font-weight: 700;">${alllist.claid}</td>
			<td class="td_title">${alllist.claTitle}</td>
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
			<td class="td_introduct">${alllist.claIntroduction}</td>
			<td class="td_time">${fn:replace((alllist.claTime), "T", " ")}</td> 
			<td>
				<span>$</span>
				${alllist.claPrice}
			</td>
			<td>${alllist.claPeopleMax}</td>
			<td>${alllist.claPeopleMin}</td>
			
			<td><a href="#" style="${(alllist.claPeople<alllist.claPeopleMin)?'color:red;font-weight: 700;':'color:blue;font-weight: 700;'}
			" data-bs-toggle="modal" data-bs-target="#exampleModal${alllist.claid}">
			${alllist.claPeople}</a></td>
			<jsp:include page="/back-end/classifm/click_people.jsp">
				<jsp:param name="claid" value="${alllist.claid}" />
			</jsp:include>
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
		            <c:when test="${alllist.claStatus==3}">
		                取消
		            </c:when>
		            <c:otherwise>
		                取消已通知
		            </c:otherwise>
		        </c:choose>
			</td>
			<td class="td_time">${fn:replace((alllist.claStrTime), "T", " ")}</td>
			<td class="td_time">${fn:replace((alllist.claFinTime), "T", " ")}</td>
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
	</div>
	<%@ include file="../tools/footer.jsp"%>
	<!-- End Footer -->
	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/back-end/assets/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/popper.min.js"></script>
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
	<script src="<%=request.getContextPath()%>/back-end/classifm/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script>
		$("table tr:odd").css("background-color","rgb(236, 231, 231)");
	</script>
</body>
</html>