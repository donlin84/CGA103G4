<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

<%--取得最新管理員編號，並給予預設帳號 --%>
<%
  EmpDAO daoid = new EmpDAO();
  EmpVO empVOid = daoid.findLatestId();
  String latestId = String.valueOf((empVOid.getEmpid()+1));
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增管理員</title>

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
</style>

<style>
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
  img.preview {
    width: 200px;
  }

  ul {
    list-style: none;
    margin: 0;
    padding: 0;
}

  ul>li {
    display: inline-block;
    vertical-align: top;
}

</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>管理員資料新增</h3></td><td>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="emp.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>管理員姓名:</td>
		<td><input type="TEXT" name="empName" size="45" 
			 value="<%= (empVO==null)? "" : empVO.getEmpName()%>" /></td>
	</tr>
	<tr>
		<td>管理員電話:</td>
		<td><input type="TEXT" name="empPhone" size="45"
			 value="<%= (empVO==null)? "" : empVO.getEmpPhone()%>" /></td>
	</tr>
	<tr>
		<td>管理員照片:</td>
		<td><input type="file" id="the_file" accept="image/*" multiple name="empPicture" value="${empVO.empPicture} }" /></td>
		<ul class="picture_list"> </ul>
	</tr>
	<tr>
		<td>管理員帳號:</td>
		<td><input type="TEXT" name="empAccount" size="45" class="readonly"
			 value="<%= (empVO==null)? ("seefoodadmin" + latestId) : empVO.getEmpAccount()%>" readonly /></td>
	</tr>
	<tr>
		<td>管理員密碼:</td>
		<td><input type="PASSWORD" name="empPassword" size="45"
			 value="<%= (empVO==null)? "" : empVO.getEmpPassword()%>" /></td>
	</tr>
	<tr>
		<td>管理員權限等級</td>
		<td>
		<select size="1" name="empLevel">
				<option value="0" ${(empVO.empLevel==0)? 'selected':'' } >最高管理員</option>
				<option value="1" ${(empVO.empLevel==0)? 'selected':'' } >一般管理員</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>雇用日期:</td>
		<td><input name="empHiredate" id="f_date1" type="text"></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================datetimepicker========================================== -->

<% 
  java.sql.Date hiredate = null;
  try {
	    hiredate = empVO.getEmpHiredate();
   } catch (Exception e) {
	    hiredate = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
</script>
<script src="./js/pictureView.js"></script>
</html>