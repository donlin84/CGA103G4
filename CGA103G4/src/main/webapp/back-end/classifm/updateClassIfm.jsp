<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.ClassTag.model.ClassTagService"%>
<%@page import="java.util.stream.Collectors"%>
<%@ page import="com.ClassIfm.model.*"%>
<%@ page import="com.teacher.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.util.List"%>
<%@page import="com.ClassTag.model.ClassTagVO"%>
<%
		ClassTagService clatagSrv = new ClassTagService();
		List<ClassTagVO> list=clatagSrv.getAll();
		pageContext.setAttribute("abc", list.stream()
				.filter(c -> c.getClaTagStatus()==1)
				.collect(Collectors.toList()));
		TeacherService teaSrv = new TeacherService();
		List<TeacherVO> list_t=teaSrv.getAll();
		pageContext.setAttribute("teacher", list_t.stream()
		.filter(c -> c.getThrStatus()==1)
		.collect(Collectors.toList()));
%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
        <script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.js"></script>
        <script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>
		<link href="<%=request.getContextPath()%>/back-end/classifm/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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
    	div>h1{
    		margin:0px;
    	}
        .xdsoft_datetimepicker .xdsoft_datepicker {
                 width:  300px;   /* width:  300px; */
        }
        .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
                 height: 151px;   /* height:  151px; */
        }
        .my_form{
        	margin-top:10px;
        	font-size:15px;
        	font-weight:600;
        	letter-spacing:2px;
        }
        
        .classtitle{
            padding-top: 30px;
            text-align:center;
        }
        .my_div{
            margin: 20px;
            text-align: center;
        }
        #rangeTxt{
        	display:inline-block;
        	width:40px
        }
        #clapic1{
        	width:170px;
        }
        #clapic2{
        	width:170px;
        }
        #clapic3{
        	width:170px;
        }
        .image_div{
        	background-color:white;
      		display:flex; 
        	width:580px;
        	height:135px;
        	margin:30px auto;
        }
        .small_div{
        	border: 3px dotted lightgray;
        	width:192.5px;
        	height:133px;
        	margin:0px;
        }
        #image1{
        	width:100%;
        	height:100%;
        	padding:3px;
        }
        #image2{
        	width:100%;
        	height:100%;
        	padding:3px;
        }
        #image3{
        	width:100%;
        	height:100%;
        	padding:3px;
        }
        .my_h3{
        	margin-top:170px;
        	text-align:center;
        }
        .error_div{
        	font-size:18px;
        	position: absolute;
        	left:30px;
        	margin-top:10px;
        }
        .my_submit{
        	padding:8px 10px;
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
	<h3 class="my_h3">更新表單</h3>
    <form action="<%=request.getContextPath()%>/ClassIfmServlet" method="post" enctype= "multipart/form-data" class="my_form">
        <div class="classtitle">
            <span>課程標題 :</span>
            <input type="text" name="clatitle" value="${classIfmVO.claTitle}">
        </div>
        <div class="my_div">
            <span>授課教師 :</span>
            <select name="thrid" id="">
                <c:forEach var="throp" items="${teacher}">
					<option value="${throp.thrid}" ${(throp.thrid==classIfmVO.thrid)?"selected":" "}>${throp.thrName}
				</c:forEach>
            </select>
            <span>課程標籤 :</span>
            <select name="clatagid" id="">
                <c:forEach var="clatagop" items="${abc}">
					<option value="${clatagop.claTagid}"  ${(clatagop.claTagid==classIfmVO.claTagid)?"selected":" "} >${clatagop.claTagName}
				</c:forEach>
            </select>
        </div>
        <div class="my_div">
            <span>授課時間 :</span>
            <input id="f_date1" type="text" name="clatime" value="${classIfmVO.claStrTime.toString().replace('T',' ')}">
        </div>
        <div class="my_div">
            <span>課程價格 :</span>
            <input type="range" id="myRange" value="${classIfmVO.claPrice}" min="0" max="5000" step="500" name="claprice">
            <span>NT</span>
			<span id="rangeTxt">${param.myRange}</span>
        </div>
        <div class="my_div">
            <span>課程人數下限 :</span>
            <input type="text" name="clapeoplemin" value="${classIfmVO.claPeopleMin}">
            <span>課程人數上限 :</span>
            <input type="text" name="clapeoplemax" value="${classIfmVO.claPeopleMax}">
        </div>
        <div class="my_div">
            <span>課程狀態 :</span>
            <select id="" name="clastatus">
                <option value="0" ${(classIfmVO.claStatus==0)?"selected":" "}>下架</option>
                <option value="1" ${(classIfmVO.claStatus==1)?"selected":" "}>上架</option>
                <option value="2" ${(classIfmVO.claStatus==2)?"selected":" "} ${(classIfmVO.claPeople)<(classIfmVO.claPeopleMin)?'disabled':''}>已結束</option>
                <option value="3" ${(classIfmVO.claStatus==3)?"selected":" "} ${(classIfmVO.claPeople)>(classIfmVO.claPeopleMin)?'disabled':''}>取消</option>
                <option value="4" ${(classIfmVO.claStatus==4)?"selected":" "} ${(classIfmVO.claPeople)>(classIfmVO.claPeopleMin)?'disabled':''}>取消已通知</option>
            </select>
        </div>
        <div class="my_div">
            <span>報名開始時間 :</span>
            <input id="start_date" type="text" name="clastrtime" value="${classIfmVO.claStrTime.toString().replace('T',' ')}">
            <span>報名結束時間 :</span>
            <input id="end_date"   type="text" name="clafintime" value="${classIfmVO.claStrTime.toString().replace('T',' ')}">
        </div>
        <br>
        <div class="my_div">
            <span>課程圖片 :</span>
            <input type="file" accept="image/*"  name="clapic1" id="clapic1" onchange="loadImageFile1(event)">
            <input type="file" accept="image/*"  name="clapic2" id="clapic2" onchange="loadImageFile2(event)">
            <input type="file" accept="image/*"  name="clapic3" id="clapic3" onchange="loadImageFile3(event)">
            <div class="image_div">
            	<div class="small_div">
            		<img id="image1" src="<%=request.getContextPath()%>/ClassIfmPic?id=${classIfmVO.claid}&page=0">
            	</div >
            	<div class="small_div">
            		<img id="image2" src="<%=request.getContextPath()%>/ClassIfmPic?id=${classIfmVO.claid}&page=1">
            	</div>
            	<div class="small_div">
            		<img id="image3" src="<%=request.getContextPath()%>/ClassIfmPic?id=${classIfmVO.claid}&page=2">
            	</div>
            </div>
        </div>
        <div class="my_div">
            <span>課程簡介 :</span>
        </div>
        <div class="my_div">
            <textarea id="" cols="40" rows="10" name="claintroduction">${classIfmVO.claIntroduction}</textarea>
        </div>
        <div class="my_div">
            <input type="submit" value="確定修改" class="my_submit">
            <input type="hidden" value="${classIfmVO.claid}" name="claid">
        	<input type="hidden" name="action" value="update">
        </div>
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
    <script>
    function loadImageFile1(event){ var image = document.getElementById('image1'); image.src = URL.createObjectURL(event.target.files[0]); };
    function loadImageFile2(event){ var image = document.getElementById('image2'); image.src = URL.createObjectURL(event.target.files[0]); };
    function loadImageFile3(event){ var image = document.getElementById('image3'); image.src = URL.createObjectURL(event.target.files[0]); };
        $.datetimepicker.setLocale('zh'); // kr ko ja en
        $('#f_date1').datetimepicker({
           theme: '',          //theme: 'dark',
           timepicker: true,   //timepicker: false,
           step: 60,            //step: 60 (這是timepicker的預設間隔60分鐘)
	       format: 'Y-m-d H:i',
// 	       value: new Date()
           //disabledDates:    ['2022/06/08','2022/06/09','2022/06/10'], // 去除特定不含
           //startDate:	        '2022/07/10',  // 起始日
           //minDate:           '-1970-01-01', // 去除今日(不含)之前
           //maxDate:           '+1970-01-01'  // 去除今日(不含)之後
        });
        $.datetimepicker.setLocale('zh'); // kr ko ja en
        $(function(){
            $('#start_date').datetimepicker({
            format:'Y-m-d H:i',
            onShow:function(){
            this.setOptions({
                maxDate:$('#end_date').val()?$('#end_date').val():false
            })
            },
            timepicker:true
            });
            
            $('#end_date').datetimepicker({
            format:'Y-m-d H:i',
            onShow:function(){
            this.setOptions({
                minDate:$('#start_date').val()?$('#start_date').val():false
            })
            },
            timepicker:true
            });
        });
        $("#rangeTxt").html($("#myRange").val()); // 在 #rangeTxt 顯示 #myRange 的 bar 值
        $("#myRange").on("input", function(){ // 當輸入 input 時執行以下動作
        $("#rangeTxt").html($(this).val()); // 將本數值顯示在 #rangeTxt 上
        });
    </script>
</html>