//fetch請求
function request(URL , callback) {
 	fetch(URL,{
	method: "POST",
	headers: new Headers({
        'Content-Type': 'application/x-www-form-urlencoded'})
}).then(response =>{
         return  response.json()//解析成一個json 物件
     })// 因為轉成json 物件 也回傳一個promise  出來
     .then( jsondata =>{
//		jsondata = JSON.stringify(jsondata);
		console.log("fetch請求");
        callback(jsondata);
 	})
}	

function requestbyform(URL , callback, object) {
 	fetch(URL,{
	method: "POST",
	headers: new Headers({
        'Content-Type': 'application/json'}),
    body:JSON.stringify({"username":"what","123":"321"})
}).then(response =>{
         return  response.json()//解析成一個json 物件
     })// 因為轉成json 物件 也回傳一個promise  出來
     .then( jsondata =>{
//		jsondata = JSON.stringify(jsondata);
		console.log("fetch請求");
        callback(jsondata);
 	})
}	

//XHR 請求
//function request(URL , callback) {
//    var request = new XMLHttpRequest();
//    request.onreadystatechange = function () {
//        // this代表XMLHttpRequest現行物件
//        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
//			console.log("XHR請求");
//			console.log(request.response);
//			callback(request.response);
//			
//        }
//    };
//    request.open("POST", URL, true);
//    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
//    request.send("action=HI");
//}






