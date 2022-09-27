<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orders.model.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="com.orders.model.*"%>
<%@ page import="org.json.*"%>

<%
OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO");
List<Integer> pdid = (ArrayList<Integer>) request.getAttribute("pdid");
List<Integer> pdNumber = (ArrayList<Integer>) request.getAttribute("pdNumber");
List<Integer> pdPrice = (ArrayList<Integer>) request.getAttribute("pdPrice");
List<Integer> pdDis = (ArrayList<Integer>) request.getAttribute("pdDis");
Integer memid = (Integer) request.getAttribute("memid");
Integer subtotal = (Integer) request.getAttribute("subtotal");
Integer total = (Integer) request.getAttribute("total");
Integer memCpid = (Integer) request.getAttribute("memCpid");

System.out.print(memCpid);

request.setAttribute("memid", memid);
request.setAttribute("subtotal", subtotal);
request.setAttribute("total", total);
request.setAttribute("memCpid", memCpid);
request.setAttribute("pdid", pdid);
request.setAttribute("pdNumber", pdNumber);
request.setAttribute("pdPrice", pdPrice);
request.setAttribute("pdDis", pdDis);

%>
<html>

<head>
  <meta charset="utf-8">
  <title>結帳</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bs-stepper@1.7.0/dist/css/bs-stepper.min.css">
  <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
  <script src='js/fetch.js' type="text/javascript"></script>
  
  <style>
    table {
      background-color: white;
      margin-top: 5px;
      margin-bottom: 5px;
    }
    table, th, td {
      border: 1px solid #CCCCFF;

      overflow: hidden;
      text-overflow: ellipsis;
      /* display: -webkit-box;
      -webkit-line-clamp: 2; 
      -webkit-box-orient: vertical; */
    }
    th, td {
      padding: 5px;
      text-align: center;
    }

    
  
  </style>
</head>

<body>



  <h3 class="text-center">
    結帳
  </h3>
  <form class="container">
    <div class="bs-stepper">
      <div class="bs-stepper-header" role="tablist">
        <div class="step" data-target="#step1">
          <button type="button" class="step-trigger" role="tab">
            <span class="bs-stepper-circle">1</span>
            <span class="bs-stepper-label">確認訂單資料</span>
          </button>
        </div>
        <div class="line"></div>
        <div class="step" data-target="#step2">
          <button type="button" class="step-trigger" role="tab">
            <span class="bs-stepper-circle">2</span>
            <span class="bs-stepper-label">輸入取貨資訊</span>
          </button>
        </div>
        <div class="line"></div>
        <div class="step" data-target="#step3">
          <button type="button" class="step-trigger" role="tab">
            <span class="bs-stepper-circle">3</span>
            <span class="bs-stepper-label">輸入信用卡資訊</span>
          </button>
        </div>
        <div class="line"></div>
        <div class="step" data-target="#step4">
          <button type="button" class="step-trigger" role="tab">
            <span class="bs-stepper-circle">4</span>
            <span class="bs-stepper-label">完成登錄&確認帳單</span>
          </button>
        </div>
      </div>
      <div class="bs-stepper-content">
        <div id="step1" class="content" role="tabpanel" style="width:20%;text-align:center;margin:0 auto;">
            <table id = classinformation>
                <tr>
                    <th>商品名稱</th>
                    <th>商品數量</th>
                    <th>商品價格</th>
                </tr>
					<c:forEach var="e" items="${list}">
					<tr>
						<td>${e.productVO.pdName} </td>
						<td>${e.pdNumber} </td>
						<td>${e.productVO.pdDiscountPrice} </td>
					</tr>
					</c:forEach>
				
            </table>

          <div>
            <button type="button" onclick="stepper.next()">下一步</button>
          </div>
        </div>

        <div id="step2" class="content" role="tabpanel">
            <div class="form-group" id = payment>
                <label for="transfer" >貨到付款 </label>
                <input id="transfer" type="radio" name="Payment" value="0"
                	${(ordersVO.ordPayment == "0") ? 'checked' : '' }>
                <label for="swipe" >信用卡</label>
                <input id="swipe" type="radio" name="Payment" value="1"
                	${(ordersVO.ordPayment == "0") ? 'checked' : '' }>
            </div>
            
            <div id="deli">
            	<label for="conve">超商取貨</label>
            	<input id="conve" type="radio" name="ordDelivery" value="0"
            		${(ordersVO.ordDelivery == "0") ? 'checked' : '' }>
            	<label for="home">宅配到府</label>
            	<input id="home" type="radio" name="ordDelivery" value="1"
            		${(ordersVO.ordDelivery == "0") ? 'checked' : '' }>
            </div>

            <table>
            	<tr>
				<td>取貨人姓名:</td>
				<td><input type="TEXT" name="ordRecipient" size="45" id="recipient"
					value="<%=(ordersVO == null) ? "" : ordersVO.getOrdRecipient()%>" /></td>
			</tr>
			<tr>
				<td>取貨人聯絡電話:</td>
				<td><input type="TEXT" name="ordRecPhone" size="45" id="recphone"
					value="<%=(ordersVO == null) ? "" : ordersVO.getRecPhone()%>" /></td>
			</tr>
			<tr>
			<tr>
				<td>取貨地址:</td>
				<td><input type="TEXT" name="ordAddress" size="45" id="address"
					value="<%=(ordersVO == null) ? "" : ordersVO.getOrdAddress()%>" /></td>
			</tr>
            </table>
            <div >
                <span id = "errormessage" style="color:red;"></span>
            </div>
            <div >
                <span id = "errormessage1" style="color:red;"></span>
            </div>
            <div >
                <span id = "errormessage2" style="color:red;"></span>
            </div>
            <div >
                <span id = "errormessage3" style="color:red;"></span>
            </div>
            <div >
                <span id = "errormessage4" style="color:red;"></span>
            </div>
            <div >
                <span id = "errormessage5" style="color:red;"></span>
            </div>
          <div>
            <button type="button" onclick="stepper.previous()">上一步</button>
            <button type="button" onclick="step2check()">下一步</button>
          </div>
        </div>

        <div id="step3" class="content" role="tabpanel">
          <div id = paymentArea>

          </div>
          
          <div id = step3_controll>
            <button id="step3_return" type="button" onclick="stepper.previous()">上一步</button>
            <button id="step3_next" type="button" onclick="simulateSubmit()">下一步</button>
          </div>
        </div>

        <div id="step4" class="content" role="tabpanel">
          <div class="alert alert-success">
            感謝你的購買!
            
          </div>
          <a href="../index-front.jsp">返回首頁</a>
        </div>
      </div>
    </div>
  </form>

  <script src="https://cdn.jsdelivr.net/npm/bs-stepper@1.7.0/dist/js/bs-stepper.min.js"></script>
  <script>
    var stepperElem = document.querySelector('.bs-stepper');
    var stepper = new Stepper(stepperElem);
    var done = false;
    var currStep = 1;
    history.pushState(currStep, '');
    //切換到步驟前觸發，呼叫e.preventDefault()可阻止切換
    stepperElem.addEventListener("show.bs-stepper", function (e) {
      if (done) { //若程序完成，不再切換
        e.preventDefault();
        return;
      }
    });
    //切換到步驟後觸發，e.detail.indexStep為目前步驟序號(從0開始)
    stepperElem.addEventListener("shown.bs-stepper", function (e) {
      var idx = e.detail.indexStep + 1;
      currStep = idx;
      //pushState()記下歷程以支援瀏覽器回上頁功能
      history.pushState(idx, '');
    })
    //瀏覽器上一頁下一頁觸發
    window.onpopstate = function (e) {
      if (e.state && e.state != currStep)
        stepper.to(e.state);
    };
    //模擬送出表單，註記已完成，不再允許切換步驟
    function simulateSubmit() {
      stepper.next();
      done = true;
    }
  </script>


  <script>
    let memid = 201;

	var MyPoint = "/back-end/cartdetail/CartDetail.do";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "http://" + host + webCtx + MyPoint;
	
    let ServletURL = new URL(endPointURL);
    let searchParams = new URLSearchParams({
        action: "checkoutDisplay"
    });
    ServletURL.search = searchParams;
    
    //step 1
    let classfm = function(response){
        console.log(response); 
        
        //part1 課程資訊
//         let classinformation = document.querySelector("#classinformation");
//         let tr1 = document.createElement("tr");
//         let tr2 = document.createElement("tr");
//         let tr3 = document.createElement("tr");

//         let td1 = document.createElement("td");
//         td1.innerHTML =  response["claid"];
//         let td2 = document.createElement("td");
//         td2.innerHTML =  response["claTitle"];
//         let td3 = document.createElement("td");
//         td3.innerHTML =  response["claTagName"];
//         let td4 = document.createElement("td");
//         td4.innerHTML =  response["thridName"];
//         let td5 = document.createElement("td");
//         td5.innerHTML =  response["claTime"].replace("T",' ');
//         let td6 = document.createElement("td");
//         td6.innerHTML =  response["claIntroduction"];
//         let td7 = document.createElement("td");
//         td7.innerHTML =  response["claPrice"];
        

//         tr1.append(td1);
//         tr1.append(td2);
//         tr1.append(td3);
//         tr1.append(td4);
//         tr1.append(td5);
//         tr1.append(td6);
//         tr1.append(td7);
        
//         //part2 報名人數
        
//         let td31 = document.createElement("td");
//         td31.innerHTML = people ;
//         let td32 = document.createElement("td");
//         td32.innerHTML = people*(parseInt(response["claPrice"])) ;
//         td32.id = "payClaPrice";
        
//         tr3.append(td31);
//         tr3.append(td32);

//         classinformation.append(tr1);
//         classinformation.append(tr2);
//         classinformation.append(tr3);
        
        
    } 

    window.onload = function(){
        console.log("發送請求");
        request(ServletURL.href,classfm);
    }
    

    //step 2
    
    let transfer = document.querySelector("#transfer");
    let swipe = document.querySelector("#swipe");
    let recipient = document.querySelector("#recipient");
    let recphone = document.querySelector("#recphone");
    let address = document.querySelector("#address");
    let paymentcheck=-1;
    let list = [-1,-1,-1,-1,-1]
    payment.onchange = function(){
        if(transfer.checked){
            paymentcheck=0;
            //取貨資訊輸入
        }
        if(swipe.checked){
            paymentcheck=1;

        }
    	console.log(paymentcheck);
        
    };
    const phoneReg = "^09[0-9]{8}$";
    
    let step2check = function(){
        if (paymentcheck===-1){
            errormessage.innerHTML = "請選擇付款方式";
            console.log("錯誤");
        }else{
            errormessage.innerHTML = "";
            console.log("成功");
            list[0]=1;           
        }
        if(delicheck === -1) {
        	errormessage2.innerHTML = " 請選擇取貨方式";
        }else{
            errormessage2.innerHTML = "";
            list[1]=1;
        }
        if(recipient.value === ""){
        	errormessage3.innerHTML = " 取貨人姓名請勿空白";
        } else{
        	errormessage3.innerHTML = "";
        	list[2]=1;
        }
        if(recphone.value === ""){
            errormessage4.innerHTML = " 連絡電話請勿空白";
        }else if (!(recphone.value).match(phoneReg)){
            errormessage4.innerHTML = " 連絡電話請填手機格式";
        }else{
            errormessage4.innerHTML = "";
            list[3]=1;
        }
        if(address.value === "") {
        	errormessage5.innerHTML = " 取貨地址請勿空白";
        }else{
        	errormessage5.innerHTML = "";
        	list[4]=1;
        }
        for (let i=0 ; i<=4 ; i++){
    		console.log(i);
        	if(list[i]===-1){
        		return;
        	}else{
        	    stepper.next();
        	    errormessage.innerHTML = "";
        	    paymenform();
        	    return;
        	}

        }
    }
    
    let conve = document.querySelector("#conve");
    let home = document.querySelector("#home");
    let deli = document.querySelector("#deli");
    let delicheck = -1;
    deli.onchange = function() {
    	if(conve.checked){
    		delicheck = 0;
    	}
    	if(home.checked){
    		delicheck = 1;
    	}
    }
    let errormessage3 = document.querySelector("#errormessage3");
    errormessage3.onchange = function() {
    	
    }
    

//step 3
    
    //返回轉帳請求的callback
    let transfer_finish = function(response){
        console.log(response);
        if(response["payment_commit"]==="success"){
            done = false;
            stepper.next();
            done = true;
            
        }else{
            alert(response["payment_commit"])
        }
    }
    //返回刷卡請求的callback
    let card_finish = function(response){
        console.log(response);
        if(response["payment_commit"]==="success"){
            done = false;
            stepper.next();
            done = true;
            
        }else if(typeof response["payment_commit"]==='undefined'){
          alert("資料格式錯誤");
          Riframe = document.getElementById("iframe").contentWindow;
          let cardNumberLabel = Riframe.document.getElementById("card-number-label");
          let cardHolderLabel = Riframe.document.getElementById("card-holder-label");
          let cardExpirationMonthLabel = Riframe.document.getElementById("card-expiration-month-label");
          let cardCcvLabel = Riframe.document.getElementById("card-ccv-label");

          //卡號
          if (typeof response["cardNumber1"]!= 'undefined'||
              typeof response["cardNumber2"]!= 'undefined'||
              typeof response["cardNumber3"]!= 'undefined'||
              typeof response["cardNumber4"]!= 'undefined'){
            cardNumberLabel.style="color:red";
          }else{
            cardNumberLabel.style="color:''";
          }
          //持卡人
          if (typeof response["cardHolder"]!= 'undefined'){
            cardHolderLabel.style="color:red";
          }else{
            cardHolderLabel.style="color:''";
          }
          //有效日期
          if (typeof response["cardMonth"]!= 'undefined'||
             typeof response["cardYear"]!= 'undefined'
          ){
            cardExpirationMonthLabel.style="color:red";
          }else{
            cardExpirationMonthLabel.style="color:''";
          }
          //CCV
          if (typeof response["cardCcv"]!= 'undefined'){
            cardCcvLabel.style="color:red";
          }else{
            cardCcvLabel.style="color:''";
          }


        }else{
          alert(response["payment_commit"]);
          Riframe = document.getElementById("iframe").contentWindow;
          let cardNumberLabel = Riframe.document.getElementById("card-number-label");
          let cardHolderLabel = Riframe.document.getElementById("card-holder-label");
          let cardExpirationMonthLabel = Riframe.document.getElementById("card-expiration-month-label");
          let cardCcvLabel = Riframe.document.getElementById("card-ccv-label");
          cardNumberLabel.style="color:''";
          cardHolderLabel.style="color:''";
          cardExpirationMonthLabel.style="color:''";
          cardCcvLabel.style="color:''";
        }
    }

    let paymenform = function(){
        if(paymentcheck===0){
           	var MyPoint = "/back-end/orders/Orders.do";
        	var host = window.location.host;
        	var path = window.location.pathname;
        	var webCtx = path.substring(0, path.indexOf('/', 1));
        	var endPointURL = "http://" + host + webCtx + MyPoint;				
        	  
            let ServletURL3 = new URL(endPointURL);
            let searchParams = new URLSearchParams({
                action:"checkout",

                regPayment:paymentcheck,
                //form表單資料
                memid:${memid},
                memCpid:${memCpid},
                total:${total},
                subtotal:${subtotal},
                pdid:${pdid},
                pdNumber:${pdNumber},
                pdPrice:${pdPrice},
                pdDis:${pdDis},
                payment:paymentcheck,
                delivery:delicheck,
                recipient:document.getElementById('recipient').value,
                recphone:document.getElementById('recphone').value,
                address:document.getElementById('address').value
            });
            ServletURL3.search = searchParams;

          request(ServletURL3.href,card_finish);
       	
        	stepper.next();
        	stepper.next();
        	
        	
        	
        }
        if(paymentcheck===1){
            //如果iframe存在的話移除元素
            let ORiframe = document.getElementById("iframe");
            if (ORiframe!=null){
                document.removeChild(ORiframe);
            }

            iframe = document.createElement('iframe');
            iframe.src="card.html";  
            iframe.id="iframe"; 
            iframe.style="height:700px;width:500px;";
            let step3 = document.querySelector("#step3");
            step3.prepend(iframe);
            step3.style="position: absolute;left: 30%;"

//             document.querySelector("#step3_return").style="display:none";
            document.querySelector("#step3_next").style="display:none";
            
            setTimeout(function(){
              Riframe = document.getElementById("iframe").contentWindow;
              let form = Riframe.document.getElementById('form');
              
              let button = Riframe.document.getElementById('button');
              console.log(button);

              
              button.onclick = function(){
              	var MyPoint = "/back-end/orders/Orders.do";
            	var host = window.location.host;
            	var path = window.location.pathname;
            	var webCtx = path.substring(0, path.indexOf('/', 1));
            	var endPointURL = "http://" + host + webCtx + MyPoint;				
            	  
                let ServletURL3 = new URL(endPointURL);
                let searchParams = new URLSearchParams({
                    action:"checkout",
                	action2: "card",

                    regPayment:paymentcheck,
                    //form表單資料
                    cardNumber1:Riframe.document.getElementById('card-number').value,
                    cardNumber2:Riframe.document.getElementById('card-number-1').value,
                    cardNumber3:Riframe.document.getElementById('card-number-2').value,
                    cardNumber4:Riframe.document.getElementById('card-number-3').value,
                    cardHolder:Riframe.document.getElementById('card-holder').value,
                    cardMonth:Riframe.document.getElementById('card-expiration-month').value,
                    cardYear:Riframe.document.getElementById('card-expiration-year').value,
                    cardCcv:Riframe.document.getElementById('card-ccv').value,
                    memid:${memid},
                    memCpid:${memCpid},
                    total:${total},
                    subtotal:${subtotal},
                    pdid:${pdid},
                    pdNumber:${pdNumber},
                    pdPrice:${pdPrice},
                    pdDis:${pdDis},
                    payment:paymentcheck,
                    delivery:delicheck,
                    recipient:document.getElementById('recipient').value,
                    recphone:document.getElementById('recphone').value,
                    address:document.getElementById('address').value
                });
                ServletURL3.search = searchParams;



              request(ServletURL3.href,card_finish);


            }
              
              
            },1000)
 

        }
    

    }
    
    </script>



</body>

</html>