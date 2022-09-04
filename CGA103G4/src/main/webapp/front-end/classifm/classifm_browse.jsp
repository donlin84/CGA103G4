<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>瀏覽頁面</title>
<link rel="stylesheet" href="./classifm_css/classifm.css">
</head>
<body>
	瀏覽頁面
	<div class="main">
    <div class="title">
      <h3>課程</h3>
      <div>Class</div>
    </div>
    <div id="big-content">
      <div class="left-side-1">
        <div class="loge-area">
          <img src="./classifm_image/SeeFoodLogo.png" alt="">
        </div>
        <div class="big-left">
          <div class="left-side-2">
            <div class="left-side-2-1">
              <h3>條件篩選</h3>
              <img src="./classifm_image/clean.svg" alt="" id="broom">
            </div>
          <form method="post" action="<%=request.getContextPath()%>/ClassIfmServlet"></form>
            <div>
              <div class="price_div">
                <span class="little_title">搜尋價格範圍</span>
                <hr>
                <div class="price_div">
                  <span class="price_span_1">min :</span>
                  <input type="text" name="claprice_min" value="0" class="price_input">
                  <span class="price_span_2">max :</span>
                  <input type="text" name="claprice_max" value="5000" class="price_input">
                </div>
              </div>
              <span class="little_title">課程教師</span>
              <hr>
              <div class="checkbox">
                <input type="checkbox" name="teacher" id="楊小弟" value="1" class="aaa">
                <label for="楊小弟" class="bbb">楊小弟</label>
              </div>
              <div class="checkbox">
                <input type="checkbox" name="teacher" id="林某紅" value="2" class="aaa">
                <label for="林某紅" class="bbb">林某紅</label>
              </div>
              <div class="checkbox">
                <input type="checkbox" name="teacher" id="楊大姐" value="3" class="aaa">
                <label for="楊大姐" class="bbb">楊大姐</label>
              </div>
              <span class="little_title">課程分類</span>
              <hr>
              <div class="checkbox">
                <input type="checkbox" name="clatag" id="tag01" value="1" class="aaa">
                <label for="tag01" class="bbb">中式烹飪</label>
              </div>
              <div class="checkbox">
                <input type="checkbox" name="clatag" id="tag02" value="2" class="aaa">
                <label for="tag02" class="bbb">西式法餐</label>
              </div>
              <div class="checkbox">
                <input type="checkbox" name="clatag" id="tag03" value="3" class="aaa">
                <label for="tag03" class="bbb">蛋糕甜點</label>
              </div>
              <div class="checkbox">
                <input type="checkbox" name="clatag" id="tag04" value="4" class="aaa">
                <label for="tag04" class="bbb">義大利菜</label>
              </div>
              <div class="checkbox">
                <input type="checkbox" name="clatag" id="tag05" value="5" class="aaa">
                <label for="tag05" class="bbb">浙江蔡</label>
              </div>
            </div>
            <input type="hidden" name="action" value="browse">
            <input type="submit" value="篩選條件">
          </form>
          </div>
        </div>
      </div>
      
      <div class="right-side">
        <a href="" id="right_a">
          <div class="pic">
              <span class="clatag">蛋糕甜點</span>
              <img class="img1" src="./classifm_image/1062-5092x3395.jpg" alt="">
              <div class="clafintime_div">課程報名截止時間 : 
                  <span class="clafintime">2022/10/01 10:00</span>
              </div>
          </div>
  
          <div class="content">
              <div class="clapeople">
                  <span class="cla_people_now">3</span><span>/</span><span class="cla_people_max">10</span>
                  <span class="peopleimg">
                      <img class="img2" src="./classifm_image/user.svg" alt="">
                  </span>
              </div>
  
              <div class="content_1">
                  <h3 class="cla_title">這夏真芒-芒果巴斯克蛋糕</h3>
                  <span class="teacher_name">楊小弟</span>
                  <p class="cla_text">Instagram上很火紅的「巴斯克乳酪蛋糕」是近年流行全世界的超紅甜點！外表看似不修邊幅的蛋糕，每一口卻是綿密柔滑，如卡士達一樣入口即化的香濃風味！烘烤後焦糖化的蛋糕表層，帶有些微苦味的焦糖，更是將風味層次提升，香濃不膩口！Maggie老師這次更設計結合了台灣夏季最好吃的芒果，將巴斯克蛋糕結合芒果鮮果和濃郁芒果果泥，酸甜的滋味、絕妙的組合是你從未吃過的巴斯克蛋糕！</p>
              </div>
              <br>
              <hr>
  
              <div class="content_2">
                  <div class="price">
                      <h5 class="cla_price1">課程價格</h5>
                      <div class="cla_price2">定價:</div>
                      <span class="cla_price3">$</span>
                      <span class="cla_price4">2000</span>
                  </div>
                  <div class="class_time">
                      <h5 class="clatime1">課程時間</h5>
                      <span class="clatime2">2022/10/07 10:00-12:00</span>
                  </div>
              </div>
  
              <div class="content_3">了解更多</div>
          </div>
        </a> 
        <a href="" id="right_a">
          <div class="pic">
              <span class="clatag">蛋糕甜點</span>
              <img class="img1" src="/course/images/1062-5092x3395.jpg" alt="">
              <div class="clafintime_div">課程報名截止時間 : 
                  <span class="clafintime">2022/10/01 10:00</span>
              </div>
          </div>
  
          <div class="content">
              <div class="clapeople">
                  <span class="cla_people_now">3</span><span>/</span><span class="cla_people_max">10</span>
                  <span class="peopleimg">
                      <img class="img2" src="/course/images/user.svg" alt="">
                  </span>
              </div>
  
              <div class="content_1">
                  <h3 class="cla_title">名廚來晚餐－熱情西班牙餐桌</h3>
                  <span class="teacher_name">楊小弟</span>
                  <p class="cla_text">Instagram上很火紅的「巴斯克乳酪蛋糕」是近年流行全世界的超紅甜點！外表看似不修邊幅的蛋糕，每一口卻是綿密柔滑，如卡士達一樣入口即化的香濃風味！烘烤後焦糖化的蛋糕表層，帶有些微苦味的焦糖，更是將風味層次提升，香濃不膩口！Maggie老師這次更設計結合了台灣夏季最好吃的芒果，將巴斯克蛋糕結合芒果鮮果和濃郁芒果果泥，酸甜的滋味、絕妙的組合是你從未吃過的巴斯克蛋糕！</p>
              </div>
              <br>
              <hr>
  
              <div class="content_2">
                  <div class="price">
                      <h5 class="cla_price1">課程價格</h5>
                      <div class="cla_price2">定價:</div>
                      <span class="cla_price3">$</span>
                      <span class="cla_price4">2000</span>
                  </div>
                  <div class="class_time">
                      <h5 class="clatime1">課程時間</h5>
                      <span class="clatime2">2022/10/07 10:00-12:00</span>
                  </div>
              </div>
  
              <div class="content_3">了解更多</div>
          </div>
        </a> 
      </div>
      <div class="clear"></div>
    </div>
  </div>

</body>
</html>