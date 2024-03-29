//Phần khai báo dùng chung



//phần khai báo sự kiện
$(document).ready(function () {


    var tabs = document.getElementById("myTabs");
    new AccordionTabs(tabs, {
        breakpoint: 800,
    });
    // Khởi tạo Owl Carousel cho phần main
    var mainCarousel = $(".single-property-image-main").owlCarousel({
        items: 1, // Hiển thị 1 ảnh mỗi lần
        nav: false, // Ẩn nút điều hướng
        dots: false, // Ẩn dấu chấm thể hiện
        loop: false, // Bật vòng lặp vô hạn
        responsive: {
            0: {
                items: 1 // Hiển thị 1 ảnh khi màn hình có chiều rộng nhỏ hơn hoặc bằng 0px
            }
        }
    });

    var thumbCarousel = $(".single-property-image-thumb").owlCarousel({
        items: 4,
        nav: false,
        dots: false,
        loop: false,
        responsive: {
            0: {
                items: 2
            },
            768: {
                items: 3 // Giữ nguyên số lượng phần tử hiển thị ở 768px
            },
            1200: {
                items: 4
            }
        }
    });

    //phần sự kiện
    // Xử lý sự kiện khi mainCarousel thay đổi
    mainCarousel.on('changed.owl.carousel', function (event) {
        var currentIndex = event.item.index;

        // Xóa class "current" từ tất cả các owl-item thumb
        thumbCarousel.find('.owl-item').removeClass('current');

        // Thêm class "current" vào owl-item thumb tương ứng
        thumbCarousel.find('.owl-item').eq(currentIndex).addClass('current');

        thumbCarousel.trigger("to.owl.carousel", [currentIndex, 300]);
    });

    // Khi bạn click vào một thumbnail, chuyển đến ảnh tương ứng trong phần main
    thumbCarousel.on("click", ".owl-item", function () {
        var currentIndex = $(this).index();

        // Xóa class "current" từ tất cả các owl-item thumb
        thumbCarousel.find('.owl-item').removeClass('current');

        // Thêm class "current" vào owl-item thumb được click
        thumbCarousel.find('.owl-item').eq(currentIndex).addClass('current');

        // Chuyển đến ảnh tương ứng trong mainCarousel
        mainCarousel.trigger("to.owl.carousel", [currentIndex, 300]);
    });
    changeDate();
    function changeDate() {
        $(".date-value").each(function() {
            var originalDateValue = $(this).data("value");
            var date = new Date(originalDateValue);
            var formattedDate = date.toLocaleDateString();
            $(this).html(formattedDate);
        });
    }
});