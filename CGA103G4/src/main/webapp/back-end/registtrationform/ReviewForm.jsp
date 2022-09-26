<%@page import="com.member.model.MemberVO"%>
<%@page import="com.member.model.MemberService"%>
<%@page import="com.ClassIfm.model.ClassIfmService"%>
<%@page import="com.ClassIfm.model.ClassIfmVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%
	Integer claid = Integer.valueOf(request.getParameter("claid"));
	request.setAttribute("claid", claid);
	Integer memid = Integer.valueOf(request.getParameter("memid"));
	request.setAttribute("memid", memid);
	
	ClassIfmService claSrv = new ClassIfmService();
	ClassIfmVO classifmvo=claSrv.getOneClassIfm(claid);
	request.setAttribute("classifmvo",classifmvo);
	
	MemberService memSrv = new MemberService();
	MemberVO membervo=memSrv.getOneMember(memid);	
	request.setAttribute("membervo",membervo);
%>
<html>
<head>
<meta charset="UTF-8">
<title>seefood回饋表單</title>
<style>
*{
    margin: 0px;
    padding: 0px;
    font-family: 'docs-Roboto', Helvetica, Arial, sans-serif;
  }
  .div_1{
    background-image: url("<%=request.getContextPath()%>/back-end/registtrationform/images/800_71cdd51bde791d6c81c9b8b044746223.jpg");
    width: 100%;
    height: 300px;
  }
  .div_2{
    background-color: antiquewhite;
    width: 100%;
    height: 100%;
  }
  .div_3{
    width: 100%;
    height: 15px;
    background-color: rgb(255, 151, 53);
  }

.review_form{
  position: absolute;
  top: 200px;
  left: calc(50% - 450px);
  width: 900px;
  height: 730px;
  background-color: white;
  text-align: center;
  font-weight: 700;
  font-size: 20px;
  border-radius: 20px;
  border: 3px solid rgb(214, 213, 213);
  overflow: hidden;
}

.span_title{
  
  font-weight: 700;
  font-size: 40px;
  letter-spacing: 5px;
}

.my_submit{
  font-size: 15px;
  font-weight: 700;
  padding: 5px 10px;
  cursor: pointer;
}

.my_select{
  width: 80px;
  font-weight: 700;
  font-size: 15px;
}

.my_textarea{
  font-size: 16px;
}
</style>
</head>
<body>
  <div class="div_1"></div>
  <div class="div_2"></div>
	<form METHOD="post" ACTION="<%=request.getContextPath()%>/NewRegisttrationformServlet" class="review_form">
    <div class="div_3"></div>
    <br>
		<span class="span_title">回饋表單</span>
		<br>
		<br>
		<br>
    <span>參加課程 :</span>
		<span >${classifmvo.claTitle}</span>
    <br>
    <br>
    <br>
    <span>會員姓名 :</span>
    <span >${membervo.memName}</span>
		<br>
		<br>
		<br>
		<span>請選擇滿意度 :</span>
		<select name="regReview" id="" class="my_select">
			<option value="1" >待加強</option>
			<option value="2" >尚可</option>
			<option value="3"  selected>普通</option>
			<option value="4" >不錯</option>
			<option value="5" >很滿意</option>
        </select>
        <br>
        <br>
        <br>
        <span>課程服務建議 : (建議在50字以內喔)</span>
        <br>
        <br>
        <br>
        <textarea name="regReviewContent" id="" cols="30" rows="10" class="my_textarea"></textarea>
		<input type="hidden" name="action"  value="review_form">
		<input type="hidden" name="claid"  value="${claid}">
		<input type="hidden" name="memid"  value="${memid}">
		<br>
		<br>
        <input type="submit" value="提交表單" class="my_submit">
	</form>
</body>
</html>