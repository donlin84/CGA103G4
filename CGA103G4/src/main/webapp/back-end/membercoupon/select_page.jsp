<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>活動優惠查詢首頁</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>優惠查詢首頁</h3></td>
		</tr>
	</table>

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllPromotions.jsp'>活動優惠清單</a></li>


		<li>
			<FORM METHOD="post" ACTION="promotions.do">
				<b>輸入活動優惠編號:</b> <input type="text" name="pmid"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="pmtSvc" scope="page"
			class="com.promotions.model.PromotionsService" />

		<li>
			<FORM METHOD="post" ACTION="promotions.do">
				<b>選擇活動優惠編號:</b> <select size="1" name="pmid">
					<c:forEach var="promotionsVO" items="${pmtSvc.all}">
						<option value="${promotionsVO.pmid}">${promotionsVO.pmid}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="promotions.do">
				<b>選擇活動名稱:</b> <select size="1" name="pmid">
					<c:forEach var="promotionsVO" items="${pmtSvc.all}">
						<option value="${promotionsVO.pmid}">${promotionsVO.pmName}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>


	<h3>新增活動</h3>

	<ul>
		<li><a href='addPromotions.jsp'>新增活動</a></li>
	</ul>

</body>
</html>