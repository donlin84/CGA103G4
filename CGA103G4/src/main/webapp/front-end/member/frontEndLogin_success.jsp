<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="zh-Hant" dir="ltr">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
  <title>�mSee Food�n�x�����</title>
  <link rel="stylesheet" href="./css/common/all.css">
  <link rel="stylesheet" href="./css/common/header.css">
  <link rel="stylesheet" href="./css/common/footer.css">
  <link rel="stylesheet" href="./css/common/main.css">
  <link rel="stylesheet" href="./css/index.css">
  <script src="./js/image.js"></script>
</head>

<body>

  <header class="header">

    <div class="block">

      <nav class="nav">
        <a href="./index.html"><img src="./images/SeeFoodLogo.png"></a>
        <ul class="nav_list">
          <li><a href="./announce/announce.html" data-target="nav1" id="announce" class="nav">���i</a></li>
          <li><a href="./shop/shop.html" data-target="nav2" id="shop" class="nav">�ӫ�</a></li>
          <li><a href="./course/course.html" data-target="nav3" id="course" class="nav">�Ʋz�ҵ{</a></li>
          <li><a href="./chef/chef.html" data-target="nav4" id="chef" class="nav">�p�p�w��</a></li>
          <li><a href="./forum/forum.html" data-target="nav5" id="forum" class="nav">�Q�װ�</a></li>
          <li><a href="./service/service.html" data-target="nav6" id="social" class="nav">�ȪA</a></li>
        </ul>

        <div class="sign_block">
          <input class="input" placeholder="�䭹��"><input class="input" placeholder="�䭹��"><button class="button"><img
              src="./images/icon.png"></button>
              ${account}
<!--           <a href="./frontEndLogin.html">�n�J</a> -->
<%--           <h3> �w��:<font color=red> ${account} </font>�z�n</h3> --%>
        </div>
        <ul class="navbar_list">
          <div class="navbar">
            <li class="announcebar">
              <div><a href="#">�̷s����</a></div>
              <div><a href="#">�s�i�s��</a></div>
              <div><a href="#">����ڭ�</a></div>
            </li>
            <li class="shopbar">
              <div><a href="#">��������</a></div>
              <div><a href="#">�ӫ~����</a></div>
              <div><a href="#">�ʪ���</a></div>
              <div><a href="#">�u�f���</a></div>
            </li>
            <li class="coursebar">
              <div><a href="#">�ҵ{���e��T</a></div>
              <div><a href="#">���W</a></div>
            </li>
            <li class="chefbar">
              <div><a href="#">�p�p����</a></div>
              <div><a href="#">�w���t��</a></div>
            </li>
            <li class="forumbar">
              <div><a href="#">����</a></div>
              <div><a href="#">�W�ǭ���</a></div>
              <div><a href="#">������y</a></div>
              <div><a href="#">�p���i��</a></div>
              <div><a href="#">�p���y</a></div>
              <div><a href="#">���ä峹</a></div>
            </li>
            <li class="socialbar">
              <div><a href="http://www.facebook.com">���U����</a></div>
              <div><a href="http://instagram.com">�p���ȪA</a></div>
            </li>
          </div>
        </ul>

      </nav>
    </div>
    <li class="black_block"></li>
    <div class="picture -on">
      <a href="#"><img id="image01" src="./images/01.jpg"></a>
      <a href="#"><img id="image02" src="./images/02.jpg"></a>
      <a href="#"><img id="image03" src="./images/03.jpg"></a>
      <a href="#"><img id="image04" src="./images/04.jpg"></a>
      <a href="#"><img id="image05" src="./images/05.jpg"></a>
      <a href="#"><img id="image06" src="./images/06.jpg"></a>
      <a href="#"><img id="image07" src="./images/07.jpg"></a>
      <a href="#"><img id="image08" src="./images/08.jpg"></a>
      <a href="#"><img id="image09" src="./images/09.jpg"></a>

      <div class="leftright">
        <div id="left"></div>
        <div id="right"></div>
      </div>

    </div>

  </header>

  <div class="main">

    <div class="container nav0 -on" id="indexPage">

      <div id="indexPageContent">

        <div class="title">
          �o�̬O����
        </div>

        <div class="sectionOne">
          ���Ϥ@
        </div>

        <div class="horizen"></div>

        <div class="sectionTwo">
          ���ϤG
        </div>

        <div class="horizen"></div>

        <div class="sectionThree">
          ���ϤT
        </div>

      </div>

    </div>

  </div>


  <footer class="footer">
    <div class="us">
      <a href="#" class="pic"><img src="./images/SeeFoodLogo.png"></a><br>
      <li><a href="#" class="pic"><img src="./images/fb.png"></a></li>
      <li><a href="#" class="pic"><img src="./images/ig.png"></a></li>
      <li><a href="#" class="pic"><img src="./images/tw.png"></a></li>
      <li><a href="#" class="pic"><img src="./images/yt.png"></a></li>
      <div class="about_us">
        <li><a href="#" class="footer_contain">����SeeFood</a></li>
        <li><a href="#" class="footer_contain">���q��T</a></li>
        <li><a href="#" class="footer_contain">�x�~��T</a></li>
        <li><a href="#" class="footer_contain">�s�i�X�@</a></li>
      </div>
    </div>

    <div class="copyright">
      <li>copyright&copy;2022 seefood</li>
    </div>

    <div class="another">
      <li><a href="#" class="footer_contain">�ȪA��T</a></li>
      <li><a href="#" class="footer_contain">�H�c:SeeFood@gmail.com</a></li>
    </div>

  </footer>


</body>

</html>