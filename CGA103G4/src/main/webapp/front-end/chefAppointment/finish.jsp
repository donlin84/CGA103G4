<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>預約成功</title>
<style>
body {
	margin: 40px 10px;
	padding: 80px;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 28px;
	color: rgb(43, 64, 226);
}

.timer {
	position: absolute;
	width: 420px;
}
</style>
<script>
	var otimer;
	var second = 5;
	function timer() {
		otimer.innerHTML = "預約成功! 視窗將在 " + second + " 秒內關閉 ";
		if (second > 0) {
			second = second - 1;
			return false;
		}
		window.close();
		window.parent.location.reload();
	}
	window.onload = function() {
		otimer = document.getElementById("timer");
		setInterval(timer, 1000);
	}
</script>
</head>
<body>
	<div id="timer"></div>
</body>
</html>