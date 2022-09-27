<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<% MemberVO memVO = (MemberVO)session.getAttribute("memVO");
// 	Integer memid = memVO.getMemid();
	String memid = memVO.getMemAccount();
	System.out.print(memid);
	request.setAttribute("userName" , memid );
%> 

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/memberservice/css/friendchat.css" type="text/css" />
<style type="text/css">

</style>
<title>客服聊天室</title>
</head>
<body onload="connect();" onunload="disconnect();">
	<h3 id="statusOutput" class="statusOutput">Service</h3>
	<div id="row"></div>
	<div id="messagesArea" class="panel message-area" ></div>
	<div class="panel input-area">
		<input id="message" class="text-field" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();" /> 
		<input type="submit" id="sendMessage" class="button" value="Send" onclick="sendMessage();" /> 
		<input type="button" id="connect" class="button" value="Connect" onclick="connect();" /> 
		<input type="button" id="disconnect" class="button" value="Disconnect" onclick="disconnect();" />
	</div>
</body>
<script>
	var MyPoint = "/FriendWS/${userName}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	
	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var self = '${userName}';
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			console.log(jsonObj);
			//會員上線撈歷史紀錄&刷新顯示頁面中客服狀態為離線
			if ("open" === jsonObj.type){
				if(jsonObj.user === self){
					addListener();
				}
				//判斷客服是否上線，上線改綠燈
				if (jsonObj.user === "Service" || (jsonObj.users).includes('Service')  ){
					online();
				}
			} else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var li = document.createElement('li');
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					historyData.sender === self ? li.className += 'me' : li.className += 'friend';
					li.innerHTML = showMsg;
					ul.appendChild(li);
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				var li = document.createElement('li');
				jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
				li.innerHTML = jsonObj.message;
				console.log(li);
				document.getElementById("area").appendChild(li);
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("close" === jsonObj.type) {
				offline();
			}
			
		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	
	function sendMessage() {
		var inputMessage = document.getElementById("message");
		var friend = "Service";
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else if (friend === "") {
			alert("Choose a friend");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : friend,
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	
	// 有好友上線或離線就更新列表
// 	function refreshFriendList(jsonObj) {
// 		var friends = jsonObj.users;
// 		var row = document.getElementById("row");
// 		row.innerHTML = '';
// 		for (var i = 0; i < friends.length; i++) {
// 			if ("Service"===friends[i]) { 
// 			row.innerHTML +='<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' ><h2 id = "Service">' + friends[i] + '</h2></div>';
// 			}
// 		}
// 		addListener();
// 	}
	
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
// 	function addListener() {
// 		var container = document.getElementById("row");
// 		container.addEventListener("click", function(e) {
// 			var friend = e.srcElement.textContent;
// 			updateFriendName(friend);
// 			var jsonObj = {
// 					"type" : "history",
// 					"sender" : self,
// 					"receiver" : friend,
// 					"message" : ""
// 				};
// 			webSocket.send(JSON.stringify(jsonObj));
// 		});
// 	}
	function addListener() {
		offline();
		var jsonObj = {
				"type" : "history",
				"sender" : self,
				"receiver" : "Service",
				"message" : ""};
		webSocket.send(JSON.stringify(jsonObj));
	}
	
// 	window.onload = function(){
// 		setTimeout(function(){addListener()},1000)
// 	}
	
	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}
	
// 	function updateFriendName(name) {
// 		statusOutput.innerHTML = name;
// 	}
	function online() {
		statusOutput.innerHTML = "Service🟢";
	}
	function offline() {
		statusOutput.innerHTML = "Service🔴";
	}
</script>
</html>