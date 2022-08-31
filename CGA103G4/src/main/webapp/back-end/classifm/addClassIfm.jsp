<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ClassIfm.model.*"%>
<%@ page import="com.teacher.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  ClassIfmVO classIfmVO = (ClassIfmVO) request.getAttribute("classIfmVO");
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
        form{
            position: relative;
            top: 50px;
            left: 480px;
            background-color: rgb(255, 185, 127);
            width: 700px;
            height: 875px;
            border-radius: 20px;
        }
        .classtitle{
            padding-top: 30px;
        }
        div{
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
        	width:600px;
        	height:145px;
        	margin-top:40px;
        	position: relative;
        	left:15px;
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
    </style>
    </head>
<body>
<a href="<%=request.getContextPath()%>/back-end/classifm/index_ClassIfm.jsp">回首頁</a>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div>
		<h1>這是新增</h1>
	</div>
    <form action="<%=request.getContextPath()%>/ClassIfmServlet" method="post" enctype= "multipart/form-data">
        <div class="classtitle">
            <span>課程標題 :</span>
            <input type="text" name="clatitle" value="${param.clatitle}">
        </div>
        <div>
        <jsp:useBean id="thrxxx" scope="page" class="com.teacher.model.TeacherService" />
            <span>授課教師 :</span>
            <select name="thrid" id="">
                <c:forEach var="throp" items="${thrxxx.getAll()}">
					<option value="${throp.thrid}" ${(throp.thrid==param.thrid)?"selected":" "}>${throp.thrName}
				</c:forEach>
            </select>
            <span>課程標籤 :</span>
            <jsp:useBean id="clatagxxx" scope="page" class="com.ClassTag.model.ClassTagService" />
            <select name="clatagid" id="">
                <c:forEach var="clatagop" items="${clatagxxx.getAll()}">
					<option value="${clatagop.claTagid}" ${(clatagop.claTagid==param.clatagid)?"selected":" "} >${clatagop.claTagName}
				</c:forEach>
            </select>
        </div>
        <div>
            <span>授課時間 :</span>
            <input id="f_date1" type="text" name="clatime" value="${param.clatime}">
        </div>
        <div>
            <span>課程價格 :</span>
            <input type="range" id="myRange"  value="<%= (classIfmVO==null)?0: classIfmVO.getClaPrice()%>" min="0" max="5000" step="500" name="claprice">
            <span>NT</span>
			<span id="rangeTxt"></span>
        </div>
        <div>
            <span>課程人數下限 :</span>
            <input type="text" name="clapeoplemin" value="${param.clapeoplemin}">
            <span>課程人數上限 :</span>
            <input type="text" name="clapeoplemax" value="${param.clapeoplemax}">
        </div>
        <div>
            <span>課程狀態 :</span>
            <select id="" name="clastatus">
                <option value="0" ${(param.clastatus==0)?"selected":" "} >下架</option>
                <option value="1" ${(param.clastatus==1)?"selected":" "}>上架</option>
                <option value="2" ${(param.clastatus==2)?"selected":" "}>已結束</option>
                <option value="3" ${(param.clastatus==3)?"selected":" "}>取消</option>
            </select>
        </div>
        <div>
            <span>報名開始時間 :</span>
            <input id="start_date" type="text" name="clastrtime" value="${param.clastrtime}">
            <span>報名結束時間 :</span>
            <input id="end_date"   type="text" name="clafintime" value="${param.clafintime}">
        </div>
        <br>
        <div>
            <span>課程圖片 :</span>
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
        <div>
            <span>課程簡介 :</span>
        </div>
        <div>
            <textarea id="" cols="40" rows="10" name="claintroduction" >${param.claintroduction}</textarea>
        </div>
        <div>
        	<input type="hidden" name="action" value="add">
            <input type="submit" value="新增資料">
        </div>
    </form>
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
        $("#myRange").on("input", function (){ // 當輸入 input 時執行以下動作
        $("#rangeTxt").html($(this).val()); // 將本數值顯示在 #rangeTxt 上
        });
    </script>
</html>