<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orders.model.*"%>
 
<%
  OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO"); //EmpServlet.java (Concroller) �s�Jreq��ordersVO���� (�]�A�������X��ordersVO, �]�]�A��J��ƿ��~�ɪ�ordersVO����)
%>
<%-- --<%= ordersVO == null %>--${ordersVO.deptno}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>�q���ƭק� - update_orders_input.jsp</title>

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
  .pic {
	width: 250px;
	height: 250px;
	display: flex;
	justify-content: center;
	align-items: center;
	}

	img {
	max-width: 100%;
	max-height: 100%;
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
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�q���ƭק� - update_orders_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="orders.do" name="form1">
<table>
	<tr>
		<td>�q��s��:<font color=red><b>*</b></font></td>
		<td><%=ordersVO.getOrdid()%></td>
	</tr>
	<tr>
		<td>�|���s��:</td>
		<td><%=ordersVO.getMemid()%></td>
	</tr>
	<tr>
		<td>�|���u�f��s��:</td>
		<td><%if(ordersVO == null){ 
				 out.print("");
			 }else if(ordersVO.getMemCpid() == null){
				 out.print("");
			 }else{
				 out.print(ordersVO.getMemCpid());
			 }%></td>
	</tr>
	<tr>
		<td>�q��p�p:</td>
		<td><%=ordersVO.getOrdSubTotal()%></td>
	</tr>
	<tr>
		<td>�q���`�p:</td>
		<td><%=ordersVO.getOrdTotal()%></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>�q�檬�A:</td> -->
<%-- 		<td><input type="TEXT" name="ordStatus" size="45"	value="<%=ordersVO.getOrdStatus()%>" /></td> --%>
<!-- 	</tr> -->

	<tr>
		<td>�q�檬�A:</td>
		<td>
			<select name="ordStatus">
				<option value="0" ${(ordersVO.ordStatus == "0") ? 'selected' : '' } >������</option>
				<option value="1" ${(ordersVO.ordStatus == "1") ? 'selected' : '' } >�w����</option>
				<option value="2" ${(ordersVO.ordStatus == "2") ? 'selected' : '' } >�t�e��</option>
				<option value="3" ${(ordersVO.ordStatus == "3") ? 'selected' : '' } >����</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>�q�榨�߮ɶ�:</td>
		<td><%=ordersVO.getOrdCreate()%></td>
	</tr>
	<tr>
		<td>���f�H�m�W:</td>
		<td><input type="TEXT" name="ordRecipient" size="45" value="<%=ordersVO.getOrdRecipient()%>" /></td>
	</tr>
	<tr>
		<td>���f�H�p���q��:</td>
		<td><input type="TEXT" name="ordRecPhone" size="45" value="<%=ordersVO.getRecPhone()%>" /></td>
	</tr>
	<tr>
		<td>�I�ڤ覡:</td>
		<td>
			<select name="ordPayment">
				<option value="0" ${(ordersVO.ordPayment == "0") ? 'selected' : '' } >�f��I��</option>
				<option value="1" ${(ordersVO.ordPayment == "1") ? 'selected' : '' } >��d</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>���f�覡:</td>
		<td>
			<select name="ordDelivery">
				<option value="0" ${(ordersVO.ordDelivery == "0") ? 'selected' : '' } >�W�Ө��f</option>
				<option value="1" ${(ordersVO.ordDelivery == "1") ? 'selected' : '' } >�v�t</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>���f�a�}:</td>
		<td><input type="TEXT" name="ordAddress" size="45" value="<%=ordersVO.getOrdAddress()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>������f�ɶ�:</td> -->
<%-- 		<td><input type="TEXT" name="ordHopetime" size="45" value="<%=ordersVO.getOrdHopetime()%>" /></td> --%>
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ordid" value="<%=ordersVO.getOrdid()%>">
<input type="hidden" name="memid" value="<%=ordersVO.getMemid()%>">
<input type="hidden" name="memCpid" value="<%=ordersVO.getMemCpid()%>">
<input type="hidden" name="ordSubTotal" value="<%=ordersVO.getOrdSubTotal()%>">
<input type="hidden" name="ordTotal" value="<%=ordersVO.getOrdTotal()%>">
<%-- <input type="hidden" name="ordStatus" value="<%=ordersVO.getOrdStatus()%>"> --%>
<input type="hidden" name="ordCreate" value="<%=ordersVO.getOrdCreate()%>">
<%-- <input type="hidden" name="ordPayment" value="<%=ordersVO.getOrdPayment()%>"> --%>
<%-- <input type="hidden" name="ordDelivery" value="<%=ordersVO.getOrdDelivery()%>"> --%>
<%-- <input type="hidden" name="ordAddress" value="<%=ordersVO.getOrdAddress()%>"> --%>
<%-- <input type="hidden" name="ordHopetime" value="<%=ordersVO.getOrdHopetime()%>"> --%>
<input type="submit" value="�e�X�ק�"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

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
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=ordersVO.getOrdCreate()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>