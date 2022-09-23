<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>



<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>會員資料新增 - addMember.jsp</title>
<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon"
	href="./assets/images/favicon.ico">
<link href="./assets/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="./assets/css/icons.css" rel="stylesheet"
	type="text/css">
<link href="./assets/css/style.css" rel="stylesheet"
	type="text/css">
<style>

html {
background-color: #FFDEA1;
}
body {
background-color: #FFDEA1;
}

ul {
/* border:1px solid gray; */
padding:0;
}
 ul li { 
 height:25px; 
 line-height:25px; 
 position:relative; 
 list-style:none; 
/*  font-size:12px;  */
 } 
.rightStyle {
display:block;
float:right; 
margin-top:0;
position:absolute;
right:0;
top:0;
}
</style>

</head>
<body bgcolor='white'>
	<!-- Begin page -->
	<div class="accountbg"></div>
	<div class="wrapper-page">
		<div class="card">
			<div class="card-body">
				<div class="text-center mt-2 mb-4">
					<a href="index.html" class="logo logo-admin"><img
						src="./assets/images/logo.png" height="50" alt="logo"></a>
				</div>
				<div class="px-3 pb-3">


					<h3>加入seefood會員</h3>

					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<FORM class="form-horizontal m-t-20" METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/member/Member.do" name="form1">


						<div class="form-group row">
							<div class="col-12">
								<label for="memName">姓名:</label> <input class="form-control"
									type="TEXT" name="memName" size="15"
									value="<%=(memberVO == null) ? "" : memberVO.getMemName()%>"
									id="memName" placeholder="使用者姓名" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12">
								<label for="memAccount">帳號:</label> <input class="form-control"
									type="TEXT" name="memAccount" size="25"
									value="<%=(memberVO == null) ? "" : memberVO.getMemAccount()%>"
									id="memAccount" placeholder="使用者帳號" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12">
								<label for="memPassword">密碼:</label> <input class="form-control" type="password"
									name="memPassword" size="45"
									value="<%=(memberVO == null) ? "" : memberVO.getMemPassword()%>"
									id="memPassword" placeholder="使用者密碼" />
							</div>
								</div>
						<div class="form-group row">
							<div class="col-12">
						<label for="checkmemPassword">確認密碼:</label>
						<input type="password"size="45" class="form-control"
							value="" id="checkmemPassword" required="required" oninput="setCustomValidity('');" 
							onchange="if(document.getElementById('memPassword').value != document.getElementById('checkmemPassword').value) 
							{setCustomValidity('密碼不符合');}" placeholder="再輸入一次密碼" />
				</div>

						</div>
						<div class="form-group row">
							<div class="col-12">
								性別: 
							</div>
						</div>
								<input type="radio" name="memGender" size="45"
									value="<%=(memberVO == null) ? "m" : memberVO.getMemGender()%>" />男
								<input type="radio" name="memGender" size="45"
									value="<%=(memberVO == null) ? "f" : memberVO.getMemGender()%>" />女

						<div class="form-group row">
							<div class="col-12">
								<label for="memBirthday">生日:</label> <input class="form-control" name="memBirthday"
									id="memBirthday" type="text" placeholder="出生日期">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12">
								<label for="memPhone">電話:</label> <input class="form-control" type="TEXT"
									name="memPhone" size="45"
									value="<%=(memberVO == null) ? "" : memberVO.getMemPhone()%>"
									id="memPhone" placeholder="市內電話或手機號碼" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12">
								<label for="memEmail">信箱:</label> <input class="form-control" type="TEXT"
									name="memEmail" size="45"
									value="<%=(memberVO == null) ? "" : memberVO.getMemEmail()%>"
									id="memEmail" placeholder="常用信箱" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12">
								<label for="memAddres">地址:</label> 
								<div>
       							    <select id="select1" name="memAddres1"  class="form-control" style="width:200px;">
         	 						<option value="-1" selected disabled>--選擇縣市--</option>
          							<option value="基隆市">基隆市</option>
          							<option value="台北市">台北市</option>
         						    <option value="新北市">新北市</option>
         						    <option value="桃園市">桃園市</option>
        						    <option value="新竹縣">新竹縣</option>
        						    <option value="新竹市">新竹市</option>
        						    <option value="宜蘭縣">宜蘭縣</option>
     								</select>
    							    <select name="memAddres2" id="select2" class="form-control" style="width:100px;"></select>
  								  </div>
								<input class="form-control" type="TEXT"
									name="memAddres3" size="45"
									value="<%=(memberVO == null) ? "" : memberVO.getMemAddres()%>"
									id="memAddres" placeholder="戶籍地址" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12">
								國家: <input class="form-control" type="TEXT" name="memNation" size="45"
									value="<%=(memberVO == null) ? "" : memberVO.getMemNation()%>" />
							</div>
						</div>
	

<!-- 						<div class="form-group row"> -->
<!-- 							<div class="col-12"> -->
						<ul>
				
						<li>已經有帳號了?<a href="frontEndLogin.jsp">登入</a>
						<a href="" class="rightStyle">

						<input type="hidden" name="action" value="insert">
						<input type="submit" value="註冊">
						</a></li>
						</ul>

<!-- 							</div> -->
<!-- 						</div> -->
						


					</FORM>

				</div>
			</div>
		</div>
	</div>
	<!-- jQuery -->
	<script src="./assets/js/jquery.min.js"></script>
	<script src="./assets/js/popper.min.js"></script>
	<script src="./assets/js/bootstrap.min.js"></script>
	<script src="./assets/js/modernizr.min.js"></script>
	<script src="./assets/js/waves.js"></script>
	<script src="./assets/js/jquery.slimscroll.js"></script>
	<script src="./assets/js/jquery.nicescroll.js"></script>
	<script src="./assets/js/jquery.scrollTo.min.js"></script>

	<!-- App js -->
	<script src="./assets/js/app.js"></script>
	 <script>
        const stationObj = {
            '基隆市': ['仁愛區', '中正區', '信義區', '中山區', '安樂區', '七堵區', '暖暖區'],
            '台北市': ['中正區', '萬華區', '大同區', '中山區', '松山區', '大安區', '信義區', '內湖區', '南港區', '士林區', '北投區', '文山區'],
            '新北市': ['萬里區', '金山區', '板橋區', '汐止區', '深坑區', '石碇區', '瑞芳區', '雙溪區', '貢寮區', '新店區', '坪林區', '烏來區'
            	, '永和區', '中和區', '土城區', '三峽區', '樹林區', '鶯歌區', '三重區', '新莊區', '泰山區', '林口區', '蘆洲區', '五股區'
            	, '八里區', '淡水區', '三芝區', '石門區'],
            '桃園市': ['桃園區', '八德區', '龜山區', '蘆竹區', '大園區', '大溪區', '中壢區', '平鎮區', '楊梅區', '龍潭區', '新屋區', '觀音區', '復興區'],
            '新竹縣': ['竹北市', '竹東鎮', '新埔鎮', '關西鎮', '新豐鄉', '峨眉鄉', '寶山鄉', '五峰鄉', '橫山鄉', '北埔鄉', '尖石鄉', '芎林鄉', '湖口鄉'],
            '新竹市': ['東區', '北區', '香山區']
        };
        const select2 = document.querySelector('#select2');
        document.querySelector('#select1').addEventListener('click', e => {
            const city = e.target.value;
            const stationArr = stationObj[city];
            select2.textContent = ''
            for (let station of stationArr) {
                const option = document.createElement('option');
                option.textContent = station;
                select2.append(option);
            }
        });
    </script>

</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->
<link rel="stylesheet" type="text/css"
	href="../../back-end/member/datetimepicker/jquery.datetimepicker.css" />
<script src="../../back-end/member/datetimepicker/jquery.js"></script>
<script src="../../back-end/member/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	// 	$.datetimepicker.setLocale('zh'); // kr ko ja en
	// 	$(function() {
	// 		$('#memBirthday').datetimepicker(
	// 				{
	// 					format : 'Y-m-d',
	// 					onShow : function() {
	// 						this.setOptions({
	// 							maxDate : $('#end_date').val() ? $('#end_date')
	// 									.val() : false
	// 						})
	// 					},
	// 					timepicker : false
	// 				});

	// 	});

	$.datetimepicker.setLocale('zh');
	$('#memBirthday').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>


</html>