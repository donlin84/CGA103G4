<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.teacher.model.*"%>

<%
  TeacherVO teacherVO = (TeacherVO) request.getAttribute("teacherVO");
%>
<html>
<head>
<title>教師資料新增 - addTeacher.jsp</title>
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
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>教師資料新增 - addTeacher.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
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

<FORM METHOD="post"   ACTION="teacher.do" name="form1" enctype= "multipart/form-data">
<table>
	<tr>
		<th>教師姓名</th>
		<th>教師性別</th>
		<th>教師電話</th>
		<th>教師信箱</th>
		<th>教師狀態</th>
		<th>教師簡介</th>
		<th>教師個人照</th>
		<th>教師圖片預覽</th>
		<th>操作</th>
		
		
	</tr>
	<td><input type="TEXT" name="thrName"  value=<%= (teacherVO==null)? "無名": teacherVO.getThrName() %> ></td>
	<td>
	<select  name="thrGender">
  			<option value=<%= (teacherVO==null)? "男": teacherVO.getThrGender() %> selected><%= (teacherVO==null)? "男": teacherVO.getThrGender() %></option>
  			<option value= "男">男</option>
  			<option value= "女">女</option>
	</select>
	</td>
	<td><input type="TEXT" name="thrPhone"  value=<%= (teacherVO==null)? "0912345678": teacherVO.getThrPhone() %> ></td>
	<td><input type="email" name="thrEmail"  value= <%= (teacherVO==null)? "test@gmail.com": teacherVO.getThrEmail() %> ></td>
	<td>			
		<select  name="thrStatus">
   			<option value= <%= (teacherVO==null)? 0:  teacherVO.getThrStatus() %> selected><%= (teacherVO==null)? "在職": (teacherVO.getThrStatus()==0? "在職":"停職")  %></option>
   			<option value= 0>在職</option>
   			<option value= 1>離職</option>
		</select>
	</td>
	
	<td><input type="text" name="thrIntroduction"  value= <%= (teacherVO==null)? "你好": teacherVO.getThrIntroduction() %> ></td>

	<td><input type="file" id=p_file accept="image/jpeg" name="picio" value=<%= (teacherVO==null)? "": teacherVO.getThrPic() %> >
	<input id=post_file type="hidden" name="thrPic" value="" ></td>
	<td><img id = preImg src="" width="200px" height="200px" style="visibility:hidden"></td>
	<td>
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增"></FORM>
	</td>
</table>
<br>

<script>
	let preImg = document.querySelector('#preImg');
	let p_file = document.querySelector('#p_file');
	let post_file = document.querySelector('#post_file');
	let reader = new FileReader();
	
    // change事件
    p_file.addEventListener("change",function () {  
      // 讀取選取檔案
      reader.readAsDataURL(this.files[0]);
    });
    
    // 監聽讀取檔案並預覽圖片
    reader.addEventListener("load", function () {  
      preImg.src = reader.result;
      post_file.value = reader.result;
      preImg.style.visibility="";
//       post_file.value = (reader.result).substring(23);
      
    });    
	
</script>

</body>

</html>