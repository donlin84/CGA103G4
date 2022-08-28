<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.chef.model.*"%>

<%
	ChefVO chefVO = (ChefVO) request.getAttribute("chefVO"); //ChefServlet.java(Concroller), 存入req的chefVO物件
%>





<html>
<head>
<title>私廚資料 - listOneChef.jsp</title>
<link href="css/bigPicture.css" rel="stylesheet" type="text/css">
<link href="css/listOneChefOther.css" rel="stylesheet" type="text/css">


</head>
<body bgcolor='white'>

<div style='position:fixed;width:100%;height:100%;background-color:rgb(0,0,0,0.65)' id='modal'>
<div class='modal' id='modalw'>
<img id='bgImg' />
</div>
</div>

<table id="table-1">
	<tr><td>
		 <h3>私廚資料 - ListOneChef.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>私廚編號</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>狀態</th>
		<th>姓名</th>
		<th>暱稱</th>
		<th>價格</th>
		<th>每周服務日</th>
		<th>預約數</th>
		<th>評價總人數</th>
		<th>評價總分數</th>
		<th>廚師執照</th>
		<th>身分證(正)</th>
		<th>身分證(反)</th>
		<th>個人照</th>
		<th>簡介</th>
	</tr>
	<tr>
		<td><%=chefVO.getChefid()%></td>
		<td><%=chefVO.getChefAccount()%></td>
		<td><%=chefVO.getChefPassword()%></td>
		<td><%=chefVO.getChefStatus()%></td>
		<td><%=chefVO.getChefName()%></td>
		<td><%=chefVO.getChefNickname()%></td>
		<td><%=chefVO.getChefPrice()%></td>
		<td><%=chefVO.getSchDate()%></td>
		<td><%=chefVO.getReserve()%></td>
		<td><%=chefVO.getCom()%></td>
		<td><%=chefVO.getGomg()%></td>
		<td><img onclick='showBgImg(this)' class='thum-img' src="data:image/jpeg;base64,${license}"></td>
		<td><img onclick='showBgImg(this)' class='thum-img' src="data:image/jpeg;base64,${idCard}" ></td>
		<td><img onclick='showBgImg(this)' class='thum-img' src="data:image/jpeg;base64,${idCardBack}" ></td>
		<td><img onclick='showBgImg(this)' class='thum-img' src="data:image/jpeg;base64,${chefPhoto}" ></td>
		<td><%=chefVO.getChefIntroduction()%></td>
	</tr>
</table>

<script>
var modal = document.getElementById('modal');
var bgImg = document.getElementById('bgImg');
function showBgImg(e) {
modal.style.display = 'block';
bgImg.src = e.src;
}
bgImg.onclick = function() {
modal.style.display = 'none';
}
</script>
</body>
</html>