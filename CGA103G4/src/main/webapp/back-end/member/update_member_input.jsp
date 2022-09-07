<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //MemberServlet.java (Concroller) �s�Jreq��memberVO���� (�]�A�������X��memberVO, �]�]�A��J��ƿ��~�ɪ�memberVO����)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>�|����ƭק� - update_member_input.jsp</title>
<link rel="shortcut icon" href="../assets/images/favicon.ico">
<link href="../assets/plugins/jvectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet">
<link href="../assets/plugins/fullcalendar/vanillaCalendar.css" rel="stylesheet" type="text/css" />
<link href="../assets/plugins/morris/morris.css" rel="stylesheet">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="../assets/css/style.css" rel="stylesheet" type="text/css">
<link href="../chef/css/other.css" rel="stylesheet" type="text/css">


</head>
<body bgcolor='white'>

	<!-- Navigation Bar -->
	<%@ include file="../tools/header.jsp"%>

	<div class="wrapper">
		<div class="container-fluid">
			<!-- 			Page-Title -->
			<div class="row">
				<div class="col-sm-12">
					<div class="page-title-box">
						<div class="btn-group pull-right">
							<ol class="breadcrumb hide-phone p-0 m-0">
								<li class="breadcrumb-item"><a href="#">Zoter</a></li>
								<li class="breadcrumb-item"><a href="#">Tables</a></li>
								<li class="breadcrumb-item active">Editable</li>
							</ol>
						</div>
						<h4 class="page-title">Editable</h4>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">

							<div class="main_content">
								<aside class="aside">
									<div class="btn-group mo-mb-2" 
                     			style="top: 0px; left: 0px;">
                                <button type="button" class="btn btn-primary btn-lg dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false" style="width:140px">�|����T </button>
                                    <div class="dropdown-menu">
                                    <a class="dropdown-item" href='../member/select_page.jsp'>�j�M�|��</a>
                                    <a class="dropdown-item" href='../member/listAllMember.jsp'>�|���C��</a>
                                    </div>
                                </div>

                                <div class="btn-group mo-mb-2"
                                style="top: 20px; left: 0px;">
                                <button type="button" class="btn btn-primary btn-lg dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false" style="width:140px">�H�Υd��T </button>
                                    <div class="dropdown-menu">
                                    <a class="dropdown-item" href='../creditCardInformation/select_page.jsp'>�j�M�H�Υd</a>
                                    <a class="dropdown-item" href='../creditCardInformation/listAllCreditCardInformation.jsp'>�H�Υd�C��</a>
                                    </div>
                                </div>
                                

								</aside>

								<main class="main">

	<h3>��ƭק�:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="Member.do" name="form1">
		<table>
			<tr>
				<td>�|���s��:<font color=red><b>*</b></font></td>
				<td><%=memberVO.getMemid()%></td>
			</tr>
			<tr>
				<td>�b��:</td>
				<td><input type="TEXT" name="memAccount" size="45"
					value="<%=memberVO.getMemAccount()%>" id="memAccount" readonly/></td>
			</tr>
			<tr>
				<td><label for="memName">�|���m�W:</label></td>
				<td><input type="TEXT" name="memName" size="45"
					value="<%=memberVO.getMemName()%>" id="memName" /></td>
			</tr>
			<tr>
				<td><label for="memPassword">�K�X:</label></td>
				<td><input type="TEXT" name="memPassword" size="45"
					value="<%=memberVO.getMemPassword()%>" id="memPassword" /></td>
			</tr>

			<tr>
				<td>�ʧO:</td>
				<td>
				<label for="memGender">�k</label>
				<input type="radio" name="memGender" size="45" value="m" <%=memberVO.getMemGender().equals("m")? "checked" : "" %> /> 
				<label for="memGender">�k</label>
				<input type="radio" name="memGender" size="45" value="f"   <%=memberVO.getMemGender().equals("f") ? "checked" : "" %>/>
				</td>
			</tr>
			<tr>
				<td><label for="memPhone">�q��:</label></td>
				<td><input type="TEXT" name="memPhone" size="45"
					value="<%=memberVO.getMemPhone()%>" id="memPhone" /></td>
			</tr>
			<tr>
				<td><label for="memEmail">�H�c:</label></td>
				<td><input type="TEXT" name="memEmail" size="45"
					value="<%=memberVO.getMemEmail()%>" id="memEmail" /></td>
			</tr>
			<tr>
				<td><label for="memAddres">�a�}:</label></td>
				<td><input type="TEXT" name="memAddres" size="45"
					value="<%=memberVO.getMemAddres()%>" id="memAddres" /></td>
			</tr>
			<tr>
				<td><label for="memBirthday">�ͤ�:</label></td>
				<td><input name="memBirthday" id="memBirthday" type="text"></td>
			</tr>
			<tr>
				<td>���A:</td>
				<c:if test="${memberVO.getMemStatus() == 0}">
					<td><select name="memStatus" size="1">
							<option value="0">�ҥ�
							<option value="1">���v
					</select></td>
				</c:if>
				<c:if test="${memberVO.getMemStatus() == 1}">
					<td><select name="memStatus" size="1">
							<option value="1">���v
							<option value="0">�ҥ�
					</select></td>
				</c:if>
			</tr>
			<tr>
				<td>��a:</td>
				<td><input type="TEXT" name="memNation" size="45"
					value="<%=memberVO.getMemNation()%>" /></td>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="memid" value="<%=memberVO.getMemid()%>">
		<input type="submit" value="�e�X�ק�">
	</FORM>
	</main>
							</div>
						</div>
						<!-- end container -->
					</div>
					<!-- end wrapper -->
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</div>
	<!-- end wrapper -->
	<!-- Footer -->
	<%@ include file="../tools/footer.jsp"%>
	<!-- End Footer -->
	<!-- jQuery -->
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/popper.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script src="../assets/js/modernizr.min.js"></script>
	<script src="../assets/js/waves.js"></script>
	<script src="../assets/js/jquery.nicescroll.js"></script>
	<script
		src="../assets/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script
		src="../assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="../assets/plugins/skycons/skycons.min.js"></script>
	<script src="../assets/plugins/tiny-editable/mindmup-editabletable.js"></script>
	<script src="../assets/plugins/tiny-editable/numeric-input-example.js"></script>
	<script src="../assets/plugins/fullcalendar/vanillaCalendar.js"></script>
	<script src="../assets/plugins/raphael/raphael-min.js"></script>
	<script src="../assets/plugins/morris/morris.min.js"></script>
	<script src="../assets/js/app.js"></script>
</body>

<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<!-- �ѦҺ���: https://xdsoft.net/jqplugins/datetimepicker/ -->
<link rel="stylesheet" type="text/css"
	href="datetimepicker/jquery.datetimepicker.css" />
<script src="datetimepicker/jquery.js"></script>
<script src="datetimepicker/jquery.datetimepicker.full.js"></script>

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
$('#memBirthday').datetimepicker({
   theme: '',              //theme: 'dark',
    timepicker:false,       //timepicker:true,
    step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
    format:'Y-m-d',         //format:'Y-m-d H:i:s',
	   value: '<%=memberVO.getMemBirthday()%>', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
   //startDate:	            '2017/07/10',  // �_�l��
   //minDate:               '-1970-01-01', // �h������(���t)���e
   //maxDate:               '+1970-01-01'  // �h������(���t)����
});

</script>


</html>