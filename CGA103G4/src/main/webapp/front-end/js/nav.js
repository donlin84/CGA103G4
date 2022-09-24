$(document).ready(function () {

  $("ul.navbar_list").hover(function () {

    $(this).closest("header.header").find("li.showbar");

    $(".showbar").css({
      visibility: 'visible'
    })

  });

  $("li.showbar").mouseleave(function () {

    $(".showbar").css({
      visibility: 'hidden'
    })

  });

  $("ul.navbar_list").mouseleave(function () {

    $(".showbar").css({
      visibility: 'hidden'
    })

  });

});