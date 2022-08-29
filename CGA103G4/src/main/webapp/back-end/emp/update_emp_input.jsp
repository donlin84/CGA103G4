<%@page import="java.util.Arrays"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.time.LocalDate"%>

<%
EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<%
LocalDate now = LocalDate.now();
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�޲z����ƭק�</title>

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
</style>

<style>
table {
	width: 450px;
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
				<h3>�޲z����ƭק�</h3>
				<h4>
					<a href="select_page.jsp">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>
       <div class="pic">
       <img src="<%= request.getContextPath() %>/EmpShowPic?empid=${empVO.empid}">
       </div>
	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="emp.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>���u�s��:<font color=red><b>*</b></font></td>
				<td><%=empVO.getEmpid()%></td>
			</tr>
			<tr>
				<td>���u�m�W:</td>
				<td><input type="TEXT" name="empName" size="45"
					value="<%=empVO.getEmpName()%>" /></td>
			</tr>
			<tr>
				<td>�q��:</td>
				<td><input type="TEXT" name="empPhone" size="45"
					value="<%=empVO.getEmpPhone()%>" /></td>
			</tr>
			<tr>
				<td>�b��:</td>
				<td><%=empVO.getEmpAccount()%></td>
			</tr>
			<tr>
				<td>�K�X:</td>
				<td><input type="TEXT" name="empPassword" size="45"
					value="" /></td>
			</tr>
			<tr>
				<td>�޲z���Ӥ�:</td>
				<td><input type="file" id="the_file" accept="image/*" multiple
					name="empPicture" value="" /></td>
				
			</tr>
			<tr>
				<td>�v������:</td>
				<td>
				<select size="1" name="empLevel">
				<option value="0">�̰��޲z��</option>
				<option value="1">�@��޲z��</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>���A:</td>
				<td>
				<select size="1" name="empStatus">
				<option value="0">�b¾</option>
				<option value="1">��¾</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>���Τ��:</td>
				<td><input name="empHiredate" id="f_date1" type="text"></td>
			</tr>

		</table>
		<br> 
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="empid" value="<%=empVO.getEmpid()%>"> 
		<input type="hidden" name="empAccount" value="<%=empVO.getEmpAccount()%>">
		<input type="hidden" name="empPicture" value="<%=empVO.getEmpPicture()%>">
		<input type="submit" value="�e�X�ק�">
	</FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

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
 	       step: 1,                // step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=hiredate%>', //value:   new Date(),
	});
        //�����ܷ�Ѥ��᪺���
        var somedate2 = new Date('<%=now%>');
             $('#f_date1').datetimepicker({
                 beforeShowDay: function(date) {
               	  if (  date.getYear() >  somedate2.getYear() || 
        		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                     ) {
                          return [false, ""]
                     }
                     return [true, ""];
             }});
</script>
<script src="./js/pictureView.js"></script>
</html>