
        document.addEventListener('DOMContentLoaded', function () {
            var calendarEl = document.getElementById('calendar');

            let MyPoint = "/back-end/chefSchedule/chefsch.do?action=getAllById";
            let host = window.location.host;
            let path = window.location.pathname;
            let webCtx = path.substring(0, path.indexOf('/', 1));
            let URL = "http://" + host + webCtx + MyPoint;
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
                        select: function (arg) {  //新增一筆班次
                            let addPoint = "/back-end/chefSchedule/chefsch.do?action=addOne";
                            let deleteUrl = "http://" + host + webCtx + addPoint;
                            var title = prompt("新增預約時段(請輸入 '午餐' OR '晚餐')");
                            console.log(arg.startStr);
                            if (title === '午餐') {
                                calendar.addEvent({
                                    title: title,
                                    start: arg.start,
                                    end: arg.end,
                                    allDay: arg.allDay
                                })
                                connect('302', arg.startStr, 1);
                            } else if (title === '晚餐') {
                                calendar.addEvent({
                                    title: title,
                                    start: arg.start,
                                    end: arg.end,
                                    allDay: arg.allDay
                                })
                                connect('302', arg.startStr, 2);
                            } else {
                                alert("無新增資料")
                            }
                            function connect(chefid, date, time) {
                                fetch(deleteUrl, {
                                    method: 'POST',
                                    headers: {
                                        'Content-Type': 'application/json'
                                    },
                                    body: JSON.stringify({
                                        chefid: chefid,
                                        schDate: date,
                                        schTime: time
                                    })
                                })
                            }

                            calendar.unselect()
                        },
                        eventClick: function (arg) {  //刪除一筆班次
                            if (confirm('確定要移除此時段嗎?')) {
                                let deletePoint = "/back-end/chefSchedule/chefsch.do?action=deleteOne";
                                let deleteUrl = "http://" + host + webCtx + deletePoint;
                                //轉換成指定日期格式
                                let schDate = new Date(arg.event._instance.range.start);
                                const options = {
                                    year: 'numeric',    // 年: 使用4位數
                                    month: '2-digit',   // 月: 使用2數位
                                    day: '2-digit',     // 日: 使用2數位
                                };
                                let dateStr = schDate.toLocaleDateString('zh-tw', options).replaceAll('/', '-');

                                //傳遞資料
                                fetch(deleteUrl, {
                                    method: 'POST',
                                    headers: {
                                        'Content-Type': 'application/json'
                                    },
                                    body: JSON.stringify({
                                        chefid: "302",
                                        schDate: dateStr
                                    })
                                })
                                arg.event.remove()
                            }
                        },
                        editable: false,
                        dayMaxEvents: true,
                        eventStartEditable:false,
                        events: jsons,
                        dayCellContent: function (arg) {
                            return arg.date.getDate();
                        },
                    });
                    calendar.render();
                });
        });

