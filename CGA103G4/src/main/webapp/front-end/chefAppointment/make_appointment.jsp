<%@page import="com.chef.model.ChefVO"%>
<%@page import="com.member.model.MemberVO"%>
<%@page import="javassist.compiler.ast.Member"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.time.LocalDate"%>

<%
MemberVO memVO = (MemberVO) request.getAttribute("memVO");
%>
<%
ChefVO chefVO = (ChefVO) request.getAttribute("chefVO");
%>
<%
LocalDate now = LocalDate.now();
%>


<html>
<head>
<script
	src="https://cdn.jsdelivr.net/npm/tw-city-selector@2.1.1/dist/tw-city-selector.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>私廚預約</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
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

.readonly {
	background-color: rgb(197, 195, 195);
	cursor: not-allowed;
}

table {
	width: 500px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}

.pic {
	width: 400px;
	height: 300px;
	display: flex;
	justify-content: center;
	align-items: center;
}

img {
	max-width: 100%;
	max-height: 100%;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>新增預約</h3>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="emp.do" name="form1"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>私廚:</td>
				<td><input type="TEXT" name="chefName" class="readonly" readonly value="小當家" /></td>
			</tr>
			<tr>
				<td>會員姓名:</td>
				<td><input type="TEXT" name="memName" size="45" class="readonly" readonly value="林俊宏" /></td>
			</tr>
			<tr>
				<td>連絡電話:</td>
				<td><input type="TEXT" name="memPhone" size="45" /></td>
			</tr>
			<tr>
				<td>預約日期:</td>
				<td><input name="apmDate" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>服務地址:</td>
				<td><div role="tw-city-selector" ></div><input type="text" ></td>
			</tr>
			<tr>
				<td>預約價格:</td>
				<td><input name="apmPrice" id="" type="text" value="5000" class="readonly" readonly></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>



<!-- =========================================datetimepicker========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<!-- twAddress -->
<script>
        new TwCitySelector();
</script>
<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: new Date(),      // value:   new Date(),
	});
        
      //不能選擇當天之後幾天的日期
        var somedate1 = new Date('<%=now.plusDays(2)%>');
             $('#f_date1').datetimepicker({
                 beforeShowDay: function(date) {
               	  if (  date.getYear() <  somedate1.getYear() || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                     ) {
                          return [false, ""]
                     }
                     return [true, ""];
             }});
</script>
<script src="./js/pictureView.js"></script>
</html>