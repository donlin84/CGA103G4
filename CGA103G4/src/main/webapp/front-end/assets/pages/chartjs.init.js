!function(a){var b=function(){};b.prototype.respChart=function(h,i,e,g){var d=h.get(0).getContext("2d");var c=a(h).parent();a(window).resize(f);function f(){var j=h.attr("width",a(c).width());switch(i){case"Line":new Chart(d,{type:"line",data:e,options:g});break;case"Doughnut":new Chart(d,{type:"doughnut",data:e,options:g});break;case"Pie":new Chart(d,{type:"pie",data:e,options:g});break;case"Bar":new Chart(d,{type:"bar",data:e,options:g});break;case"Radar":new Chart(d,{type:"radar",data:e,options:g});break;case"PolarArea":new Chart(d,{data:e,type:"polarArea",options:g});break}}f()},b.prototype.init=function(){var e={labels:["January","February","March","April","May","June","July","August","September","October"],datasets:[{label:"Sales Analytics",fill:true,lineTension:0.5,backgroundColor:"rgba(40, 49, 121, 0.2)",borderColor:"#283179",borderCapStyle:"butt",borderDash:[],borderDashOffset:0,borderJoinStyle:"miter",pointBorderColor:"#283179",pointBackgroundColor:"#fff",pointBorderWidth:1,pointHoverRadius:5,pointHoverBackgroundColor:"#283179",pointHoverBorderColor:"#3bc9f1",pointHoverBorderWidth:2,pointRadius:1,pointHitRadius:10,data:[65,59,80,81,56,55,40,55,30,80]},{label:"Monthly Earnings",fill:true,lineTension:0.5,backgroundColor:"rgba(235, 239, 242, 0.2)",borderColor:"#ebeff2",borderCapStyle:"butt",borderDash:[],borderDashOffset:0,borderJoinStyle:"miter",pointBorderColor:"#ebeff2",pointBackgroundColor:"#fff",pointBorderWidth:1,pointHoverRadius:5,pointHoverBackgroundColor:"#ebeff2",pointHoverBorderColor:"#eef0f2",pointHoverBorderWidth:2,pointRadius:1,pointHitRadius:10,data:[80,23,56,65,23,35,85,25,92,36]}]};var f={scales:{yAxes:[{ticks:{max:100,min:20,stepSize:10}}]}};this.respChart(a("#lineChart"),"Line",e,f);var d={labels:["Desktops","Tablets"],datasets:[{data:[300,210],backgroundColor:["#7e99f4","#f4e090"],hoverBackgroundColor:["#7e99f4","#f4e090"],hoverBorderColor:"#fff"}]};this.respChart(a("#doughnut"),"Doughnut",d);var g={labels:["Desktops","Tablets"],datasets:[{data:[300,180],backgroundColor:["#7e99f4","#ebeff2"],hoverBackgroundColor:["#7e99f4","#ebeff2"],hoverBorderColor:"#fff"}]};this.respChart(a("#pie"),"Pie",g);var c={labels:["January","February","March","April","May","June","July"],datasets:[{label:"Sales Analytics",backgroundColor:"#f0b271",borderColor:"#f0b271",borderWidth:1,hoverBackgroundColor:"#f0b271",hoverBorderColor:"#f0b271",data:[65,59,81,45,56,80,50,20]}]};this.respChart(a("#bar"),"Bar",c);var i={labels:["Eating","Drinking","Sleeping","Designing","Coding","Cycling","Running"],datasets:[{label:"Desktops",backgroundColor:"rgba(126,153,244,0.2)",borderColor:"#7e99f4",pointBackgroundColor:"#7e99f4",pointBorderColor:"#fff",pointHoverBackgroundColor:"#fff",pointHoverBorderColor:"#7e99f4",data:[65,59,90,81,56,55,40]},{label:"Tablets",backgroundColor:"rgba(240, 178, 113, 0.2)",borderColor:"#f0b271",pointBackgroundColor:"#f0b271",pointBorderColor:"#fff",pointHoverBackgroundColor:"#fff",pointHoverBorderColor:"#f0b271",data:[28,48,40,19,96,27,100]}]};this.respChart(a("#radar"),"Radar",i);var h={datasets:[{data:[11,16,7,18],backgroundColor:["#fbdc92","#f0b271","#4394f4","#52d1ed"],label:"My dataset",hoverBorderColor:"#fff"}],labels:["Series 1","Series 2","Series 3","Series 4"]};this.respChart(a("#polarArea"),"PolarArea",h)},a.ChartJs=new b,a.ChartJs.Constructor=b}(window.jQuery),function(a){a.ChartJs.init()}(window.jQuery);