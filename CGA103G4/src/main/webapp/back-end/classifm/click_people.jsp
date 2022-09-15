<%@page import="java.util.ArrayList"%>
<%@page import="com.member.model.MemberVO"%>
<%@page import="com.member.model.MemberService"%>
<%@page import="com.registtrationform.model.RegisttrationFormVO"%>
<%@page import="java.util.List"%>
<%@page import="com.registtrationform.model.RegisttrationFormService"%>
<%@page import="com.ClassIfm.model.ClassIfmVO"%>
<%@page import="com.ClassIfm.model.ClassIfmService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Integer claid = Integer.valueOf(request.getParameter("claid"));
ClassIfmService claSrv = new ClassIfmService();
ClassIfmVO claifmvo=claSrv.getOneClassIfm(claid);

List<MemberVO>list3= new ArrayList<MemberVO>();

RegisttrationFormService regSrv = new RegisttrationFormService();
List<RegisttrationFormVO> list=regSrv.click_people(claid);//看第幾個課程回傳報名的會員list*********

for(RegisttrationFormVO reg : list) {//用會員list迭代			<再拿出報名表狀態塞進去>
	MemberService memSrv = new MemberService();
	MemberVO membervo=memSrv.getOneMember(reg.getMemid());//透過會員id拿取那個會員整筆資料回傳vo
	
	RegisttrationFormService refVO = new RegisttrationFormService();
	RegisttrationFormVO regvo=refVO.getOneRegisttrationForm(claid, reg.getMemid());
	
	membervo.setPeople(regvo.getRegPeople());//找人數 塞進去
	membervo.setRegstatus(regvo.getRegStatus());//找狀態
	list3.add(membervo);//把vo加進 上面宣告的list裡 
	pageContext.setAttribute("regall",list3);
}
pageContext.setAttribute("claifmvo", claifmvo);
%>
<html>
<head>
<meta charset=""UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Button trigger modal -->

<!-- Modal -->
<div class="modal fade" id="exampleModal${param.claid}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <table class="click_people">
		<h3>這是clickpeople</h3>
		<span>報名課程 :</span>
		<tr>${claifmvo.claid} ${claifmvo.claTitle}</tr>
		<span>  的會員</span>
		<tr>
			<th>會員id</th>
			<th>會員name</th>
			<th>會員性別</th>
			<th>會員電話</th>
			<th>報名人數</th>
			<th>狀態</th>
		</tr>
		<c:forEach var="regall" items="${regall}">
		<tr>
			<td>${regall.memid}</td>
			<td>${regall.memName}</td>
			<td>${(regall.memGender=='m')?'男性':'女性'}</td>
			<td>${regall.memPhone}</td>
			<td>${regall.people}</td>
			<td>
				<c:choose>
		            <c:when test="${regall.regstatus==0}">
		                已報名
		            </c:when>
		            <c:when test="${regall.regstatus==1}">
		                取消
		            </c:when>
		            <c:otherwise>
		                已退款
		        </c:otherwise>
		     </c:choose>
			</td>
		</tr>
		</c:forEach>
	</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>