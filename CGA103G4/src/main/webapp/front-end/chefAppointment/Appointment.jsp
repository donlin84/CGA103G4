<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Appointment</title>
<link href='./lib/main.css' rel='stylesheet' />
<script src='./lib/main.js'></script>
<script src="./lib/locales-all.js"></script>
<!--     <script src="./js/fullcalendar.js"></script> -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>

<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 1100px;
	margin: 0 auto;
}

.shadow {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	opacity: 0;
	background: rgba(0, 0, 0, .3);
	transition: all .3s;
	z-index: -1;
}

.shadow.show {
	z-index: 1;
	opacity: 1;
}

.shadow.show .model {
	transform: translate(-50%, -50%);
}

.model {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -60%);
	width: 650px;
	background: #fff;
	border-radius: 6px;
	transition: all .3s;
}

.model .tit {
	display: flex;
	line-height: 30px;
	padding: 10px;
	border-bottom: 1px solid #ccc;
}

.model .tit .close {
	margin-left: auto;
	cursor: pointer;
}

.model .content {
	padding: 10px;
}

body, div, li {
	margin: 0;
	padding: 0
}

#div1 {
        width: 200px;
        height: 100px;
        position: absolute;
        top: 50px;
        left: 50px;
        border-style: outset;
        border-width: 1px;
        border-color: #2C3E50;
        word-break: keep-all;
    }

    #div2 {
        width: 100px;
        height: 30px;
        background: #2C3E50;
        position: relative;
        top: -10px;
        left: -10px;
        line-height: 30px;
        font-size: 16px;
        color: #FFFFFF;
        text-align: center;
        font-weight: bold;
    }

</style>
</head>

<body>

	<div id='calendar'></div>
<!-- 	<div id="div1"> -->
<!--         <div id="div2">OOO你好</div> -->
<!--         請點選欲預約時間 午餐時間:11:00~13:00 晚餐時間:17:00~20:00 -->
<!--     </div> -->
	<div class="shadow" id="shadow">
		<div class="model">
			<div class="tit">
				<div class="close" id="model-close">關閉</div>
			</div>
			<iframe class="content" width="570" align="center" height="400"
				frameborder="0" scrolling="no" src="./make_appointment.jsp"></iframe>
		</div>
	</div>

	<script>
    document.addEventListener('DOMContentLoaded', function() {
    	var calendarEl = document.getElementById('calendar');

    	let MyPoint = "/front-end/chefAppointment/chefapp.do?action=getAllById";
    	let host = window.location.host;
    	let path = window.location.pathname;
    	let webCtx = path.substring(0, path.indexOf('/', 1));
    	let URL = "http://" + host + webCtx + MyPoint;
    	let shadow = document.getElementById('shadow');
    	
    	//點擊產生iframe視窗
    	shadow.addEventListener('click', function(e) {
    		let target = e.target;
    		let _class = target.className;
    		if (_class.includes('shadow') || _class.includes('close')) {
    			shadow.className = 'shadow'
    		}
    		return false
    	}, false)
    	fetch(URL).then(response => response.json("jsons"))
    		.then(jsons => {
    			console.log(jsons);
    			let calendar = new FullCalendar.Calendar(calendarEl, {
    				headerToolbar: {
    					left: 'prev,next,today',
    					center: 'title',
    					right: 'dayGridMonth,timeGridWeek'
    				},
    				locale: 'zh-tw',
    				navLinks: true, // can click day/week names to navigate views
    				selectable: true,
    				selectMirror: true,
    				eventClick: function(arg) {
						//取得標題時段
						console.log(arg);
						let title = arg.event._def.title;
                        //轉換成指定日期格式
    					let schDate = new Date(arg.event._instance.range.start);
    					const options = {
    						year: 'numeric',    // 年: 使用4位數
    						month: '2-digit',   // 月: 使用2數位
    						day: '2-digit',     // 日: 使用2數位
    					};
    					let dateStr = schDate.toLocaleDateString('zh-tw', options).replaceAll('/', '-');
                        // if(arg.)
						if(title === '午餐'){
							let time = '0';
							localStorage.setItem('date',dateStr);
							localStorage.setItem('time',time);
							shadow.className = 'shadow show'; //進行預約
							return false;
						}else if(title === '晚餐'){
							let time = '1';
							localStorage.setItem('date',dateStr);
							localStorage.setItem('time',time);
							shadow.className = 'shadow show'; //進行預約
							return false;
						}else{
							
						}
    				},
    				editable: false,
    				dayMaxEvents: true,
    				events: jsons,
    				dayCellContent: function(arg) {
    					return arg.date.getDate();
    				}
    			});
    			calendar.render();
    		});
   });
    </script>
</body>

</html>