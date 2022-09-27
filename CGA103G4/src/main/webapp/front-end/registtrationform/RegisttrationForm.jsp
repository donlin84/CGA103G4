<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

<head>
  <meta charset="utf-8">
  <title>RegisttrationForm</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bs-stepper@1.7.0/dist/css/bs-stepper.min.css">
  <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
  <script src='assets/js/fetch.js' type="text/javascript"></script>
  
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


  <br>
  <br>
  <h3 class="text-center">
    課程報名
  </h3>
  <h6 class="text-center">請在30分鐘內付款完成，頁面會自動轉跳，如果30分鐘內未完成結帳請重新送出訂單</h6>
  <form class="container">
    <div class="bs-stepper">
      <div class="bs-stepper-header" role="tablist">
        <div class="step" data-target="#step1">
          <button type="button" class="step-trigger" role="tab">
            <span class="bs-stepper-circle">1</span>
            <span class="bs-stepper-label">確認課程資料</span>
          </button>
        </div>
        <div class="line"></div>
        <div class="step" data-target="#step2">
          <button type="button" class="step-trigger" role="tab">
            <span class="bs-stepper-circle">2</span>
            <span class="bs-stepper-label">付款方式</span>
          </button>
        </div>
        <div class="line"></div>
        <div class="step" data-target="#step3">
          <button type="button" class="step-trigger" role="tab">
            <span class="bs-stepper-circle">3</span>
            <span class="bs-stepper-label">等待匯款或輸入信用卡資訊</span>
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
        <div id="step1" class="content" role="tabpanel">
            <table id = classinformation>
                <tr>
                    <td>課程ID</td>
                    <td>課程名稱</td>
                    <td>課程類別</td>
                    <td>教師名稱</td>
                    <td>開課時間</td>
                    <td>課程介紹</td>
                    <td>價格</td>
                </tr>

            </table>

          <div>
            <button type="button" onclick="stepper.next()">下一步</button>
          </div>
        </div>
		<br>
		<br>
        <div id="step2" class="content" role="tabpanel">
            <div class="form-group" id = payment>
                <label for="transfer" >轉帳 </label>
                <input id="transfer" type="radio" name="Payment" value=0>
                <label for="swipe" >信用卡</label>
                <input id="swipe"  type="radio" name="Payment" value=1>
            </div>

            <div >
                <span id = "errormessage" style="color:red;"></span>
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
            <button id="step3_next" type="button" >下一步</button>
          </div>
        </div>

        <div id="step4" class="content" role="tabpanel">
          <div class="alert alert-success">
            已成功報名課程，請確認訂單資訊。
          </div>
        </div>
      </div>
    </div>
  </form>

  <script src="https://cdn.jsdelivr.net/npm/bs-stepper@1.7.0/dist/js/bs-stepper.min.js"></script>
  <script>
	let timecheck = false ;
	setTimeout(function(){
		if (timecheck ==false){
			alert("超過時間，請重新操作");
          	var MyPoint = "/front-end/registtrationform/fullcalendar.html";
        	var host = window.location.host;
        	var path = window.location.pathname;
        	var webCtx = path.substring(0, path.indexOf('/', 1));
        	var returnURL = "http://" + host + webCtx + MyPoint;						
			window.location.replace(returnURL)
		} 
// 	},1000*60*5 );  //1分鐘
	},1000*60*30 ); //30分鐘
  
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
	let loaclURL = new URL(window.location.href); 
  	let people = loaclURL.searchParams.get("report_poeple");
  	console.log(people);
  	let claid = loaclURL.searchParams.get("claid");
  	console.log(claid);
    
// people = 申請報名人數 (暫定)
//     let people = 3;
//     let claid = 1;
    let memid = 203;

	var MyPoint = "/front-end/registtrationform/CheckoutServlet.do";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "http://" + host + webCtx + MyPoint;
	
    let ServletURL = new URL(endPointURL);
//     let ServletURL = new URL("http://localhost:8081/CGA103G4/front-end/registtrationform/CheckoutServlet.do");
    let searchParams = new URLSearchParams({
        action: "ClassIinfo",
        claid:claid
    });
    ServletURL.search = searchParams;
    
    //step 1
    let classfm = function(response){
        console.log(response); 
        
        //part1 課程資訊
        let classinformation = document.querySelector("#classinformation");
        let tr1 = document.createElement("tr");
        let tr2 = document.createElement("tr");
        let tr3 = document.createElement("tr");

        let td1 = document.createElement("td");
        td1.innerHTML =  response["claid"];
        let td2 = document.createElement("td");
        td2.innerHTML =  response["claTitle"];
        let td3 = document.createElement("td");
        td3.innerHTML =  response["claTagName"];
        let td4 = document.createElement("td");
        td4.innerHTML =  response["thridName"];
        let td5 = document.createElement("td");
        td5.innerHTML =  response["claTime"].replace("T",' ');
        let td6 = document.createElement("td");
        td6.innerHTML =  response["claIntroduction"];
        let td7 = document.createElement("td");
        td7.innerHTML =  response["claPrice"];
        

        tr1.append(td1);
        tr1.append(td2);
        tr1.append(td3);
        tr1.append(td4);
        tr1.append(td5);
        tr1.append(td6);
        tr1.append(td7);
        
        //part2 報名人數
        let td21 = document.createElement("td");
        td21.innerHTML = "報名人數";
        let td22 = document.createElement("td");
        td22.innerHTML = "總金額";
        
        tr2.append(td21);
        tr2.append(td22);


        

        let td31 = document.createElement("td");
        td31.innerHTML = people ;
        let td32 = document.createElement("td");
        td32.innerHTML = people*(parseInt(response["claPrice"])) ;
        td32.id = "payClaPrice";
        
        tr3.append(td31);
        tr3.append(td32);

        classinformation.append(tr1);
        classinformation.append(tr2);
        classinformation.append(tr3);
        
        
    } 

    window.onload = function(){
        console.log("發送請求");
        request(ServletURL.href,classfm);
    }
    

    //step 2
    
    let transfer = document.querySelector("#transfer");
    let swipe = document.querySelector("#swipe");
    let paymentcheck=-1;
    payment.onchange = function(){
        if(transfer.checked){
            paymentcheck=0;
        }
        if(swipe.checked){
            paymentcheck=1;

        }
        
    };
    let step2check = function(){
        if (paymentcheck===-1){
            errormessage.innerHTML = "請選擇付款方式";
            console.log("錯誤");
        }else{
            stepper.next()
            errormessage.innerHTML = "";
            console.log("成功");
            paymenform();
        }
    }

    //step 3
    
    //返回轉帳請求的callback
    let transfer_finish = function(response){
        console.log(response);
        if(response["payment_commit"]==="success"){
            done = false;
            stepper.next();
            done = true;
            timecheck = true;
          //導向會員課程
          	
            setTimeout(function(){
            	alert("報名成功");
              	var MyPoint = "/front-end/classifm/MyClassIfm.jsp";
            	var host = window.location.host;
            	var path = window.location.pathname;
            	var webCtx = path.substring(0, path.indexOf('/', 1));
            	var successURL = "http://" + host + webCtx + MyPoint;						
    			window.location.replace(successURL)            	
            },100);
            
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
            timecheck = true;
            
            //導向會員課程
            setTimeout(function(){
            	alert("報名成功");
              	var MyPoint = "/front-end/classifm/MyClassIfm.jsp";
            	var host = window.location.host;
            	var path = window.location.pathname;
            	var webCtx = path.substring(0, path.indexOf('/', 1));
            	var successURL = "http://" + host + webCtx + MyPoint;						
    			window.location.replace(successURL)            	
            },100);
        
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

            done = true;
            let payment = document.querySelector("#payment");
            let paymentArea = document.querySelector("#paymentArea");
            let payClaPrice = document.querySelector("#payClaPrice");
            let step3 = document.querySelector("#step3");

            let div = document.createElement('div');
            let transfertable = document.createElement('table');
            let tbody1 = document.createElement('tbody');
            let tr11 = document.createElement('tr');
            let tr12 = document.createElement('tr');
            tr11.innerHTML = "總金額為"+payClaPrice.innerHTML+"元";
            tbody1.append(tr11);
            tbody1.append(tr12);

            let tbody2 = document.createElement('tbody');
            let tr21 = document.createElement('tr');
            tr21.innerHTML = "玉山商業銀行";
            let tr22 = document.createElement('tr');
            tr22.innerHTML = "銀行帳號: 9213707270856120";
            let tr23 = document.createElement('tr');
            tr23.innerHTML = "銀行代碼: 808";
            tbody2.append(tr21);
            tbody2.append(tr22);
            tbody2.append(tr23);

            let tbody3 = document.createElement('tbody');
            let tr31 = document.createElement('tr');
            tbody3.append(tr31);


            transfertable.append(tbody1);
            transfertable.append(tbody2);
            transfertable.append(tbody3);

            div.append(transfertable);

            paymentArea.innerHTML ="";
            paymentArea.append(div);
            
            //控制不可返回
            document.querySelector("#step3_return").style="display:none";
            document.querySelector("#step3_next").style="display:none";


            //轉帳按鈕
            let transfer_submit = function(){
            	var MyPoint = "/front-end/registtrationform/CheckoutServlet.do";
            	var host = window.location.host;
            	var path = window.location.pathname;
            	var webCtx = path.substring(0, path.indexOf('/', 1));
            	var endPointURL = "http://" + host + webCtx + MyPoint;
            	
                let ServletURL2 = new URL(endPointURL);
                let searchParams = new URLSearchParams({
                    action: "transfer_finish",
                    claid: claid,
                    people:people,
                    regPayment:paymentcheck
                });
                ServletURL2.search = searchParams;

                request(ServletURL2.href,transfer_finish);
            }   

            let button = document.createElement("button");
            button.type = "button";
            button.id = "button";
            button.innerHTML = "執行轉帳";
            document.querySelector("#step3_controll").append(button);
            //querySelector 無法執行 需使用getElementById
            button = document.getElementById("button");
            button.onclick = transfer_submit;
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
            step3.style="position: absolute;left: 45%;"

//             document.querySelector("#step3_return").style="display:none";
            document.querySelector("#step3_next").style="display:none";
            
            setTimeout(function(){
              Riframe = document.getElementById("iframe").contentWindow;
              let form = Riframe.document.getElementById('form');
              
              let button = Riframe.document.getElementById('button');
              console.log(button);

              
              button.onclick = function(){
              	var MyPoint = "/front-end/registtrationform/CheckoutServlet.do";
            	var host = window.location.host;
            	var path = window.location.pathname;
            	var webCtx = path.substring(0, path.indexOf('/', 1));
            	var endPointURL = "http://" + host + webCtx + MyPoint;				
            	  
                let ServletURL3 = new URL(endPointURL);
                let searchParams = new URLSearchParams({
                    action: "card",
                    claid: claid,
                    people:people,
                    regPayment:paymentcheck,
                    //form表單資料
                    cardNumber1:Riframe.document.getElementById('card-number').value,
                    cardNumber2:Riframe.document.getElementById('card-number-1').value,
                    cardNumber3:Riframe.document.getElementById('card-number-2').value,
                    cardNumber4:Riframe.document.getElementById('card-number-3').value,
                    cardHolder:Riframe.document.getElementById('card-holder').value,
                    cardMonth:Riframe.document.getElementById('card-expiration-month').value,
                    cardYear:Riframe.document.getElementById('card-expiration-year').value,
                    cardCcv:Riframe.document.getElementById('card-ccv').value
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