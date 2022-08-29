<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ClassIfm.model.*"%>
<%@ page import="com.teacher.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html> 
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link   rel="stylesheet" type="text/css" href="backend/datetimepicker/jquery.datetimepicker.css" />
        <script src="backend/datetimepicker/jquery.js"></script>
        <script src="backend/datetimepicker/jquery.datetimepicker.full.js"></script>
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
            background-color: rgb(255, 243, 174);
            width: 700px;
            height: 650px;
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
    </style>
    </head>
<body>
	<a href="backend/classifm/index_ClassIfm.jsp">回首頁</a>
	<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div>
		<h1>這是更新表單</h1>
	</div>
    <form action="<%=request.getContextPath()%>/ClassIfmServlet" method="post">
        <div class="classtitle">
            <span>課程標題 :</span>
            <input type="text" name="clatitle" value="${classIfmVO.claTitle}">
        </div>
        <div>
        	<jsp:useBean id="thrxxx" scope="page" class="com.teacher.model.TeacherService" />
            <span>授課教師 :</span>
            <select name="thrid" id="">
                <c:forEach var="throp" items="${thrxxx.all}">
					<option value="${throp.thrid}" ${(throp.thrid==classIfmVO.thrid)?"selected":" "}>${throp.thrName}
				</c:forEach>
            </select>
            <span>課程標籤 :</span>
            <jsp:useBean id="clatagxxx" scope="page" class="com.ClassTag.model.ClassTagService" />
            <select name="clatagid" id="">
                <c:forEach var="clatagop" items="${clatagxxx.all}">
					<option value="${clatagop.claTagid}"  ${(clatagop.claTagid==classIfmVO.claTagid)?"selected":" "} >${clatagop.claTagName}
				</c:forEach>
            </select>
        </div>
        <div>
            <span>授課時間 :</span>
            <input id="f_date1" type="text" name="clatime" value="${fn:replace((classIfmVO.claTime),"T"," ")}">
        </div>
        <div>
            <span>課程價格 :</span>
            <input type="range" id="myRange" value="${classIfmVO.claPrice}" min="0" max="5000" step="500" name="claprice">
            <span>NT</span>
			<span id="rangeTxt">${param.myRange}</span>
        </div>
        <div>
            <span>課程人數下限 :</span>
            <input type="text" name="clapeoplemax" value="${classIfmVO.claPeopleMin}">
            <span>課程人數上限 :</span>
            <input type="text" name="clapeoplemin" value="${classIfmVO.claPeopleMax}">
        </div>
        <div>
            <span>課程狀態 :</span>
            <select id="" name="clastatus">
                <option value="0" ${(classIfmVO.claStatus==0)?"selected":" "}>下架</option>
                <option value="1" ${(classIfmVO.claStatus==1)?"selected":" "}>上架</option>
                <option value="2" ${(classIfmVO.claStatus==2)?"selected":" "}>已結束</option>
                <option value="3" ${(classIfmVO.claStatus==3)?"selected":" "}>取消</option>
            </select>
        </div>
        <div>
            <span>報名開始時間 :</span>
            <input id="start_date" type="text" name="clastrtime" value="${fn:replace((classIfmVO.claStrTime), "T", " ")}">
            <span>報名結束時間 :</span>
            <input id="end_date"   type="text" name="clafintime" value="${fn:replace((classIfmVO.claFinTime), "T", " ")}">
        </div>
        <div>
            <span>課程圖片 :</span>
            <input type="file" accept="image/*" multiple="multiple" name="clapic1" >
            <input type="file" accept="image/*" multiple="multiple" name="clapic2" >
            <input type="file" accept="image/*" multiple="multiple" name="clapic3" >
        </div>
        <div>
            <span>課程簡介 :</span>
        </div>
        <div>
            <textarea id="" cols="40" rows="10" name="claintroduction">${classIfmVO.claIntroduction}</textarea>
        </div>
        <div>
            <input type="submit" value="確定修改">
            <input type="hidden" value="${classIfmVO.claid}" name="claid">
        	<input type="hidden" name="action" value="update">
        </div>
    </form>
</body>
    <script>
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