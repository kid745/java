const gURLCart = "http://localhost:8080/";

$(document).ready(function () {
  loadCountCart();
  $(document).on("click", ".add-to-cart", addToCartClick);
  $("#alertWarning .close").click(function (e) {
    e.stopPropagation();
    $(this).closest('#alertWarning').addClass("disabled");
  });
});
function addToCartClick() {
  var vResultCheckUserLogin = checkUserLogin();
  if (vResultCheckUserLogin) {
    var $element = $(this);
    var vId = $element.data("value");
    $.get(gURLCart + "cart/add/" + vId,function(){
      loadCountCart();
    });
  }
  else {
    $("#alertWarning").removeClass("disabled");
    return;
  }
  if ($(this).hasClass('disable')) {    //disable mutiple clicks
    return false;
  }
  $(this).addClass('disable');
  var parent = $(this).parents('.snip');
  var src = parent.find('img').attr('src'); // Lấy đường dẫn ảnh của sản phẩm
  var cart = $('.cart'); // Chọn phần tử giỏ hàng
  var posTop = parent.offset().top; // Lấy vị trí top của sản phẩm
  var posLeft = parent.offset().left; // Lấy vị trí left của sản phẩm 
  // Tạo một thẻ <img> với class 'animation-fly' và đặt đường dẫn ảnh từ sản phẩm vào
  $('<img />', {
    class: 'animation-fly',
    src: src
  }).appendTo('body').css({
    'top': posTop, // Đặt vị trí top của ảnh
    'left': parseInt(posLeft) // Đặt vị trí left của ảnh
  });
  // Thiết lập setTimeout để di chuyển ảnh đến giỏ hàng và sau đó loại bỏ ảnh
  setTimeout(function () {
    $('.animation-fly').css({
      'top': cart.offset().top, // Di chuyển ảnh đến vị trí top của giỏ hàng
      'left': cart.offset().left // Di chuyển ảnh đến vị trí left của giỏ hàng
    });
    setTimeout(function () {
      $('.animation-fly').remove(); // Sau 0.5s, loại bỏ thẻ <img>
    }, 700);
  }, 50); // Sau 50ms, thực hiện đoạn code trong setTimeout
}
function checkUserLogin() {
  var tokenValue = $.cookie('token');
  if (!tokenValue) {
    return false;
  }
  return true;
}
function loadCountCart(){
  $.get(gURLCart+"cart",function(data){
    $("#count-item").text(data.length);
  });
}
