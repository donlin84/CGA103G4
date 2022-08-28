<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>

<%
	ChefService chefSvc = new ChefService();
    List<ChefVO> list = chefSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有私廚資料 - listAllChef.jsp</title>

<link href="css/bigPicture.css" rel="stylesheet" type="text/css">
<link href="css/listAllChefOther.css" rel="stylesheet" type="text/css">

</head>
<body bgcolor='white'>

<div style='position:fixed;width:100%;height:100%;background-color:rgb(0,0,0,0.65)' id='modal'>
<div class='modal' id='modalw'>
<img id='bgImg' />
</div>
</div>

<table id="table-1">
	<tr><td>
		 <h3>所有私廚資料 - listAllChef.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>編號</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>狀態</th>
		<th>姓名</th>
		<th>暱稱</th>
		<th>價格</th>
		<th>可預約日</th>
		<th>預約數</th>
		<th>評價總人數</th>
		<th>評分總分數</th>
		<th>廚師執照</th>
		<th>身分證(正)</th>
		<th>身分證(反)</th>
		<th>個人照</th>
		<th>簡介</th>
		<th>修改</th>

	</tr>

	
	<%@ include file="page1.file" %> 
	<c:forEach var="chefVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<c:set var="a" value="2"/>

		<tr>
			<td>${chefVO.chefid}</td>
			<td>${chefVO.chefAccount}</td>
			<td>${chefVO.chefPassword}</td>
			<td>${chefVO.chefStatus}</td>
			<td>${chefVO.chefName}</td>
			<td>${chefVO.chefNickname}</td> 
			<td>${chefVO.chefPrice}</td>
			<td>${chefVO.schDate}</td>
			<td>${chefVO.reserve}</td>
			<td>${chefVO.com}</td>
			<td>${chefVO.gomg}</td>
			<td><img onclick='showBgImg(this)' class='thum-img' src="<%=request.getContextPath()%>/showLicensePicture?chefid=${chefVO.chefid}" ></td>
			<td><img onclick='showBgImg(this)' class='thum-img' src="<%=request.getContextPath()%>/showIdCardPicture?chefid=${chefVO.chefid}"  ></td>
			<td><img onclick='showBgImg(this)' class='thum-img' src="<%=request.getContextPath()%>/showIdCardBackPicture?chefid=${chefVO.chefid}"  ></td>
			<td><img onclick='showBgImg(this)' class='thum-img' src="<%=request.getContextPath()%>/showChefPhotoPicture?chefid=${chefVO.chefid}"  ></td>


			
			<td>${chefVO.chefIntroduction}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/chef/chef.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="chefid"  value="${chefVO.chefid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
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