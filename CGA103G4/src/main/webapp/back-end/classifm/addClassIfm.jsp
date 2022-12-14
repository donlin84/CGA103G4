<%@page import="com.ClassTag.model.ClassTagService"%>
<%@page import="java.util.stream.Collectors"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ClassIfm.model.*"%>
<%@ page import="com.teacher.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.util.List"%>
<%@page import="com.ClassTag.model.ClassTagVO"%>
<%
  ClassIfmVO classIfmVO = (ClassIfmVO) request.getAttribute("classIfmVO");
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
<!DOCTYPE html>
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
			<font style="color:red">?????????????????????:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
    <h3 class="my_h3">????????????</h3>
    <form action="<%=request.getContextPath()%>/ClassIfmServlet" method="post" enctype= "multipart/form-data" class="my_form">
        <div class="classtitle">
            <span>???????????? :</span>
            <input type="text" name="clatitle" value="${param.clatitle}">
        </div>
        <div class="my_div">
            <span>???????????? :</span>
            <select name="thrid" id="">
                <c:forEach var="throp" items="${teacher}">
					<option value="${throp.thrid}" ${(throp.thrid==param.thrid)?"selected":" "}>${throp.thrName}
				</c:forEach>
            </select>
            <span>???????????? :</span>
            <select name="clatagid" id="">
                <c:forEach var="a" items="${abc}">
					<option value="${a.claTagid}" ${(a.claTagid==param.clatagid)?"selected":" "} >${a.claTagName}
				</c:forEach>
            </select>
        </div>
        <div class="my_div">
            <span>???????????? :</span>
            <input id="f_date1" type="text" name="clatime" value="${param.clatime}">
        </div>
        <div class="my_div">
            <span>???????????? :</span>
            <input type="range" id="myRange"  value="<%= (classIfmVO==null)?0: classIfmVO.getClaPrice()%>" min="0" max="5000" step="500" name="claprice">
            <span>NT</span>
			<span id="rangeTxt"></span>
        </div>
        <div class="my_div">
            <span>?????????????????? :</span>
            <input type="text" name="clapeoplemin" value="${param.clapeoplemin}">
            <span>?????????????????? :</span>
            <input type="text" name="clapeoplemax" value="${param.clapeoplemax}">
        </div>
        <div class="my_div">
            <span>???????????? :</span>
            <select id="" name="clastatus">
                <option value="0" ${(param.clastatus==0)?"selected":" "} >??????</option>
                <option value="1" ${(param.clastatus==1)?"selected":" "}>??????</option>
                <option value="2" ${(param.clastatus==2)?"selected":" "}>?????????</option>
                <option value="3" ${(param.clastatus==3)?"selected":" "}>??????</option>
                <option value="4" ${(param.clastatus==4)?"selected":" "}>???????????????</option>
            </select>
        </div>
        <div class="my_div">
            <span>?????????????????? :</span>
            <input id="start_date" type="text" name="clastrtime" value="${param.clastrtime}">
            <span>?????????????????? :</span>
            <input id="end_date"   type="text" name="clafintime" value="${param.clafintime}">
        </div>
        <br>
        <div class="my_div">
            <span>???????????? :</span>
            <input type="file" accept="image/*"  name="clapic1" id="clapic1" onchange="loadImageFile1(event)">
            <input type="file" accept="image/*"  name="clapic2" id="clapic2" onchange="loadImageFile2(event)">
            <input type="file" accept="image/*"  name="clapic3" id="clapic3" onchange="loadImageFile3(event)">
            <div class="image_div">
            	<div class="small_div">
            		<img id="image1">
            	</div >
            	<div class="small_div">
            		<img id="image2" >
            	</div>
            	<div class="small_div">
            		<img id="image3"  >
            	</div>
            </div>
        </div>
        <div class="my_div">
            <span>???????????? :</span>
        </div>
        <div class="my_div">
            <textarea id="" cols="40" rows="10" name="claintroduction" >${param.claintroduction}</textarea>
        </div>
        <div class="my_div">
        	<input type="hidden" name="action" value="add">
            <input type="submit" value="????????????" class="my_submit">
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
           step: 60,            //step: 60 (??????timepicker???????????????60??????)
	       format: 'Y-m-d H:i',
// 	       value: new Date()
           //disabledDates:    ['2022/06/08','2022/06/09','2022/06/10'], // ??????????????????
           //startDate:	        '2022/07/10',  // ?????????
           //minDate:           '-1970-01-01', // ????????????(??????)??????
           //maxDate:           '+1970-01-01'  // ????????????(??????)??????
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
        $("#rangeTxt").html($("#myRange").val()); // ??? #rangeTxt ?????? #myRange ??? bar ???
        $("#myRange").on("input", function (){ // ????????? input ?????????????????????
        $("#rangeTxt").html($(this).val()); // ????????????????????? #rangeTxt ???
        });
    </script>
</html>