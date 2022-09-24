<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後台登入頁面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backend_login/BackendLogin.css">
</head>
<body>

<c:if test="${not empty errormsg}">
	<c:forEach var="message" items="${errormsg}">
		<div class="errordiv">
			<p class="errorp">${message}</p>
		</div>
	</c:forEach>
</c:if>
<c:if test="${not empty errormsg2}">
	<c:forEach var="message2" items="${errormsg2}">
		<div class="errordiv">
			<p class="errorp">${message2}</p>
		</div>
	</c:forEach>
</c:if>

    <div class="panel-group">
        <input type="radio" name="panel-radio" id="radio1" class="panel-control" checked>
        <input type="radio" name="panel-radio" id="radio2" class="panel-control"  >
        <div class="tab-group">
          <label id="lab1" for="radio1" class="active">管理員</label>
          <label id="lab2" for="radio2">私廚</label>
        </div>
        <div class="content-group">
            <Form action="<%=request.getContextPath()%>/MemberServlet" method="post">
                <div class="login_1">
                    <div class="small_title">
                        <div>
                            <img src="<%=request.getContextPath()%>/back-end/backend_login/images/user.svg" alt="">
                        </div>
                        <h3>管理員登入</h3>
                    </div>
                    <input type="hidden" name="idname" value="employee">
                    <label class="textfont" for="">Account</label><br>
                    <input type="text" placeholder="請輸入帳號" name="employeename"><br>
                    <label  class="textfont" for="">Password</label><br>
                    <input class="pass1" type="password" placeholder="請輸入密碼" name="employeepassword"><img id="eyes1" src="<%=request.getContextPath()%>/back-end/backend_login/images/eye.svg" alt=""><br>
                    <button type="submit" class="btn_sbm">登入</button>
                </div>
            </Form>
            <Form action="<%=request.getContextPath()%>/BackendLogin" method="post">
                <div class="login_2">
                    <div class="small_title">
                        <div>
                            <img src="<%=request.getContextPath()%>/back-end/backend_login/images/hat-chef.svg" alt="">
                        </div>
                        <h3>私廚登入</h3>
                    </div>
                    <input type="hidden" name="idname" value="chef">
                    <label class="textfont" for="">Account</label><br>
                    <input type="text" placeholder="請輸入帳號" name="employeename"><br>
                    <label class="textfont" for="">Password</label><br>
                    <input  class="pass2" type="password" placeholder="請輸入密碼" name="employeepassword"><img id="eyes2" src="<%=request.getContextPath()%>/back-end/backend_login/images/eye.svg" alt=""><br>
                    <button type="submit" class="btn_sbm">登入</button>
                    </div>
                </div>
            </Form>
        </div>
    </div>
    <script>
        //眼睛事件看到密碼
        document.querySelector('#eyes1').addEventListener('click',function(){
            var password=document.querySelector('.pass1');
            if(password.type=='password'){
                password.type='text';
            }else{
                password.type='password';
            }
        })
        document.querySelector('#eyes2').addEventListener('click',function(){
            var password=document.querySelector('.pass2');
            if(password.type=='password'){
                password.type='text';
            }else{
                password.type='password';
            }
        })
    </script>
</body>
</html>