
document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');

	let MyPoint = "/front-end/chefAppointment/chefapp.do?action=getAllById";
	let host = window.location.host;
	let path = window.location.pathname;
	let webCtx = path.substring(0, path.indexOf('/', 1));
	let URL = "http://" + host + webCtx + MyPoint;
	let shadow = document.getElementById('shadow');
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
					console.log(arg);
					//轉換成指定日期格式
					let schDate = new Date(arg.event._instance.range.start);
					const options = {
						year: 'numeric',    // 年: 使用4位數
						month: '2-digit',   // 月: 使用2數位
						day: '2-digit',     // 日: 使用2數位
					};
					let dateStr = schDate.toLocaleDateString('zh-tw', options).replaceAll('/', '-');
					shadow.className = 'shadow show'; //進行預約
					return false;
					function getD(dateStr){
						console.log(dateStr)
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
