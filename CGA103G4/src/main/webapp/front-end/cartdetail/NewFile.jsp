<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cartdetail.model.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.membercoupon.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.coupontype.model.*"%>
<%@ page import="org.json.*"%>

<%
MemberVO memVO = (MemberVO) session.getAttribute("memVO");
Integer memid = memVO.getMemid();
// 	Integer memid = (Integer) request.getAttribute("memid");
CartDetailService cartdetailSvc = new CartDetailService();
List<CartDetailVO> list = cartdetailSvc.getOnes(memid);
pageContext.setAttribute("list", list);
request.setAttribute("list", list);
JSONArray jsonList = new JSONArray();
list.forEach((e) -> {
	JSONObject json = new JSONObject();
	json.put("pdid", e.getPdid());
	json.put("memid", e.getMemid());
	json.put("pdNumber", e.getPdNumber());
	jsonList.put(json);
});

String jsonstr = jsonList.toString();
request.setAttribute("jsonstr", jsonstr);

List<Integer> pdid = new ArrayList<Integer>();
List<Integer> pdNumber = new ArrayList<Integer>();
List<Integer> pdPrice = new ArrayList<Integer>();
List<Integer> pdDiscount = new ArrayList<Integer>();
list.forEach((e) -> {
	pdid.add(e.getPdid());
	pdNumber.add(e.getPdNumber());
	pdPrice.add(e.getProductVO().getPdPrice());
	pdDiscount.add(e.getProductVO().getPdDiscountPrice());
});
// 	Integer memid2 = (Integer) session.getAttribute("memid");
request.setAttribute("memid", memid);

request.setAttribute("pdid", pdid);
request.setAttribute("pdNumber", pdNumber);
request.setAttribute("pdPrice", pdPrice);
request.setAttribute("pdDiscount", pdDiscount);
%>
<%
MemberCouponService memCpSvc = new MemberCouponService();
List<MemberCouponVO> memCplist = memCpSvc.getAll();
pageContext.setAttribute("memCplist", memCplist);

Date date = new Date();

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String a = sdf.format(date);
Date Now = sdf.parse(a);

List<Integer> list2 = new ArrayList<Integer>();

memCplist.forEach((e) -> {
	if (e.getMemVO().getMemid().equals(memid) && e.getMemCpStatus() == 1 && e.getMemCpDate().after(Now)) {
		list2.add(e.getMemCpid());
	}
});

request.setAttribute("list2", list2);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src='js/fetch.js' type="text/javascript"></script>

<!--     <link rel="stylesheet" href="../css/common/all.css"> -->
<!-- 	<link rel="stylesheet" href="../css/common/header.css"> -->
<!-- 	<link rel="stylesheet" href="../css/common/footer.css"> -->
<!-- 	<link rel="stylesheet" href="../css/common/main.css"> -->
<!-- 	<link rel="stylesheet" href="../css/membercoupon.css"> -->
<link rel="stylesheet" href="../css/common/all.css">
<link rel="stylesheet" href="../css/common/header.css">
<link rel="stylesheet" href="../css/common/footer.css">
<link rel="stylesheet" href="../css/common/main.css">
<link rel="stylesheet" href="../css/chef.css">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="../assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="../assets/css/style.css" rel="stylesheet" type="text/css">
<script src="../js/chef.js"></script>
<script src="../js/nav.js"></script>

<title>購物車</title>
<style>
/* body {
            margin: 0;
            padding: 0;
            background: linear-gradient(to bottom right, #E3F0FF, #FAFCFF);
            background-color: #E3F0FF;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        } */
.Cart-Container::after {
	content: "";
	clear: both;
	display: block;
	/* border: 1px solid red; */
}

.Cart-Container {
	width: 90%;
	margin: 30px 30px;
	padding: 30px 30px;
	background-color: #ffffff;
	border-radius: 20px;
	box-shadow: 0px 25px 40px #1687d933;
}

.Header {
	margin: auto;
	width: 90%;
	height: 15%;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.Heading {
	font-size: 20px;
	font-family: ‘Open Sans’;
	font-weight: 700;
	color: #2F3841;
}

.Action {
	font-size: 14px;
	font-family: ‘Open Sans’;
	font-weight: 600;
	color: #E44C4C;
	cursor: pointer;
	border-bottom: 1px solid #E44C4C;
}

.Cart-Items {
	margin: auto;
	width: 90%;
	height: 20%;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.image-box {
	width: 15%;
	text-align: center;
}

.about {
	height: 100%;
}

.title {
	padding-top: 5px;
	line-height: 10px;
	font-size: 22px;
	font-family: ‘Open Sans’;
	font-weight: 800;
	color: #202020;
}

.subtitle {
	line-height: 10px;
	font-size: 18px;
	font-family: ‘Open Sans’;
	font-weight: 600;
	color: #909090;
}

.counter {
	width: 15%;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.btn_minus {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	background-color: #d9d9d9;
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 20px;
	font-family: ‘Open Sans’;
	font-weight: 900;
	color: #202020;
	cursor: pointer;
}

.btn_plus {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	background-color: #d9d9d9;
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 20px;
	font-family: ‘Open Sans’;
	font-weight: 900;
	color: #202020;
	cursor: pointer;
}

.count {
	font-size: 20px;
	font-family: ‘Open Sans’;
	font-weight: 900;
	color: #202020;
}

.prices {
	height: 100%;
	text-align: right;
}

.amount {
	padding-top: 20px;
	font-size: 26px;
	font-family: ‘Open Sans’;
	font-weight: 800;
	color: #202020;
}

.save {
	padding-top: 5px;
	font-size: 14px;
	font-family: ‘Open Sans’;
	font-weight: 600;
	color: #1687d9;
	cursor: pointer;
}

.remove {
	padding-top: 5px;
	font-size: 14px;
	font-family: ‘Open Sans’;
	font-weight: 600;
	color: #E44C4C;
	cursor: pointer;
}

hr {
	width: 66%;
	float: right;
	margin-right: 5%;
}

.checkout {
	float: right;
	margin-right: 5%;
	width: 28%;
}

.total {
	width: 100%;
	display: flex;
	justify-content: space-between;
}

.Subtotal {
	font-size: 22px;
	font-family: ‘Open Sans’;
	font-weight: 700;
	color: #202020;
}

.items {
	font-size: 16px;
	font-family: ‘Open Sans’;
	font-weight: 500;
	color: #909090;
	line-height: 10px;
}

.total-amount {
	font-size: 28px;
	font-family: ‘Open Sans’;
	font-weight: 900;
	color: #202020;
}

.button_checkout {
	margin-top: 5px;
	width: 100%;
	height: 40px;
	border: none;
	/*             background: linear-gradient(to bottom right, #B8D7FF, #8EB7EB); */
	border-radius: 20px;
	cursor: pointer;
	font-size: 16px;
	font-family: ‘Open Sans’;
	font-weight: 600;
	color: #202020;
	background-color: #f5b225;
	border: 1px solid #f5b225;
	background: #f5b225;
}
</style>
</head>
<body>
	<%@ include file="../tools/header.jsp"%>
	<jsp:useBean id="memberVO" class="com.member.model.MemberVO" />
	<jsp:useBean id="couponTypeVO"
		class="com.coupontype.model.CouponTypeVO" />
	<jsp:useBean id="now" class="java.util.Date" />
	<fmt:formatDate value="${now}" type="both" dateStyle="long"
		pattern="yyyy-MM-dd" var="nowTime" />
	<!-- ============================================ 取得帳戶 ============================================ -->
	<jsp:useBean id="memberSvc" scope="page"
		class="com.member.model.MemberService" />
	<c:forEach var="memberVO" items="${memberSvc.all}">
		<c:if test="${memberVO.memAccount == account}">
			<c:set var="cpmemid" value="${memberVO.memid}" />
			<%-- 							${memberVO.memAccount} --%>
		</c:if>
	</c:forEach>
	<!-- ==================================== 以帳戶找出能使用的優惠券 ==================================== -->

	<table id="table-1">
	</table>
	<div class="Cart-Container">
		<div class="Cart-Items">
			<div class="image-box"></div>
			<div class="title">商品名稱</div>
			<div class="subtitle">商品原價</div>
			<div class="subtitle">商品折扣價</div>
			<div class="counter">商品數量</div>
			<div></div>
		</div>
		<c:forEach var="cartDetailVO" items="${list}">
			<div class="Cart-Items">
				<div class="image-box">
					<a
						href="<%=request.getContextPath() %>/front-end/shop/FrontEndPdDetail.do?pdid=${cartDetailVO.productVO.pdid}">
						<img
						src="<%=request.getContextPath() %>/front-end/shop/Productpic.do?pdid=${cartDetailVO.productVO.pdid}"
						style="height: 120px">
					</a>
				</div>
				<div class="title">${cartDetailVO.productVO.pdName}</div>
				<div id="${cartDetailVO.productVO.pdid}_price" class="subtitle"
					value="${cartDetailVO.productVO.pdPrice}">${cartDetailVO.productVO.pdPrice}</div>
				<div id="${cartDetailVO.productVO.pdid}_discountprice"
					class="subtitle" value="${cartDetailVO.productVO.pdDiscountPrice}">${cartDetailVO.productVO.pdDiscountPrice}</div>
				<div class="counter">
					<FORM METHOD="post" ACTION="CartDetail.do"
						style="margin-bottom: 0px;">
						<input class="btn_minus" type="submit" value="-"> <input
							type="hidden" name="memid" value="${cartDetailVO.memid}">
						<input type="hidden" name="pdid" value="${cartDetailVO.pdid}">
						<input type="hidden" name="pdNumber"
							value="${cartDetailVO.pdNumber}"> <input type="hidden"
							name="action" value="minus">
					</FORM>
					<div id="${cartDetailVO.productVO.pdid}_num"
						value="${cartDetailVO.pdNumber}">${cartDetailVO.pdNumber}</div>
					<FORM id="plus" METHOD="post" ACTION="CartDetail.do"
						style="margin-bottom: 0px;">
						<input class="btn_plus" type="submit" value="+"> <input
							type="hidden" name="memid" value="${cartDetailVO.memid}">
						<input type="hidden" name="pdid" value="${cartDetailVO.pdid}">
						<input type="hidden" name="action" value="plus">
					</FORM>
				</div>
				<FORM METHOD="post" ACTION="CartDetail.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="刪除"> <input type="hidden"
						name="memid" value="${cartDetailVO.memid}"> <input
						type="hidden" name="pdid" value="${cartDetailVO.pdid}"> <input
						type="hidden" name="action" value="delete">
				</FORM>
			</div>
		</c:forEach>



		<c:choose>
			<c:when test="${empty account}">
				<!-- 						<div class="gotologinbox"> -->
				<!-- 							<a href="../member/frontEndLogin.jsp" class="gotologin">您尚未登入 -->
				<!-- 								無法顯示擁有的優惠券喔!</a> -->
				<!-- 						</div> -->
			</c:when>

			<c:otherwise>
				<ul class="item_list">
					<div style="width: 100%;">
						<div style="float: right; margin: 0 22%;">
							<select name="memCpid" id="memCpid">
								<option value="0">不使用優惠券</option>

								<c:forEach var="memberCouponVO" items="${memCplist}">

									<c:if test="${memberCouponVO.memid == memid}" var="condition"
										scope="page">
										<c:if test="${memberCouponVO.memCpStatus == 1}"
											var="condition" scope="page">
											<c:if test="${memberCouponVO.memCpDate >= nowTime}"
												var="condition" scope="page">
												<option id="${memberCouponVO.memCpid}"
													value="${memberCouponVO.memCpid}">${memberCouponVO.cpTpVO.cpName}</option>
												<%-- 											<option value="">${memberCouponVO.couponTypeVO.cpDiscount}</option> --%>
											</c:if>
										</c:if>
									</c:if>

								</c:forEach>

							</select>
						</div>
					</div>
				</ul>

			</c:otherwise>

		</c:choose>

		<div style="margin-top: 50px;">


			<hr style="width: 87%;">

			<div class="checkout">
				<div class="total">
					<div>
						<div class="Subtotal">總金額</div>
					</div>

				</div>
				<FORM METHOD="post" ACTION="CartDetail.do">
					<input type="text" class="total-amount" readonly name="total" /> <input
						type="hidden" id="subtotal" name="subtotal"> <input
						type="hidden" name="pdid" value="${pdid}"> <input
						type="hidden" name="pdNumber" value="${pdNumber}"> <input
						type="hidden" name="pdPrice" value="${pdPrice}"> <input
						type="hidden" name="pdDiscount" value="${pdDiscount}"> <input
						type="hidden" name="memid" value="${memid}"> <input
						type="hidden" name="jsonstr" value=${jsonstr}> <input
						type="hidden" name="memCpid_form" id="memCpid_form"> <input
						type="hidden" name="action" value="checkout"> <input
						style="background-color: #f5b225; border: 1px solid #f5b225;"
						class="button_checkout" type="submit" value="前往結帳" />

				</FORM>
			</div>

		</div>
	</div>
	<script>
	const total_amount = document.querySelector(".total-amount");
	let subtotal = document.querySelector("#subtotal");
	let total_Item = document.querySelectorAll(".Cart-Items");
	let cptpid = null;
	const memCpid = document.querySelector("#memCpid");
	const memCpid_form = document.querySelector("#memCpid_form");
	let t = 0;
	window.addEventListener("load", function() {
		
		for(oneid of ${pdid}){
			let price = document.getElementById(oneid + "_discountprice").innerHTML;
			let num = document.getElementById(oneid + "_num").innerHTML;
			let totalAmount = price * num;
			t += totalAmount;
		}
		
		subtotal.value = t
		total_amount.value = t;
		
		memCpid_form.value = memCpid.value;
// 		console.log(memcpid.value);
		
	});
	
		
		memCpid.addEventListener('change', function(){
// 			memcpid.value = this.value;
// 			console.log(memcpid.value);
			if(this.value === "0"){
				total_amount.value = t;
			}
			else{
				cptpid = this.value;
				var MyPoint = "/front-end/cartdetail/CartDetail.do";
				var host = window.location.host;
				var path = window.location.pathname;
				var webCtx = path.substring(0, path.indexOf('/', 1));
				var endPointURL = "http://" + host + webCtx + MyPoint;
				
			    let ServletURL = new URL(endPointURL);
			    let searchParams = new URLSearchParams({
			        action: "get_value",
			        cptpid: cptpid
			    });
			    ServletURL.search = searchParams;
				
			   
			    request( ServletURL.href , result );
			}
		})
		
		 function result(data){
		    	console.log(data);
		    	let diss = data[0];
		    	total_amount.value = t - diss;
		    	memCpid_form.value = data[1];
		    	console.log(memCpid_form.value);
		    }		
	</script>
	<%@ include file="../tools/footer.jsp"%>
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/popper.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>
</html>