'use strict';
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
const gURLIndex = "http://localhost:8080/";
let gCurrentPage = 1;
Handlebars.registerHelper('eq', function (a, b, options) {
    return a === b ? options.fn(this) : options.inverse(this);
});
/*** REGION 2 - Vùng gán / thực thi sự kiện cho các elements */
$(document).on("click", "#nextPagination", function () {
    nextPageClick();
});

$(document).on("click", "#prePagination", function () {
    previousPageClick();
});
$(document).on("click", ".featuredRealEstate-page", function () {
    commentPageClick(this);
});
/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
$(document).ready(function () {
    $("#divTop10View").owlCarousel({
        items: 1, // Hiển thị 1 ảnh mỗi lần
        loop: true,
    });
    $("#divBlog").owlCarousel({
        items: 3, 
        loop: true,
        responsive: {
            0: {
                items: 1, // Hiển thị 1 item khi màn hình có độ rộng ít hơn 600px
            },
            600: {
                items: 2, // Hiển thị 3 item khi màn hình có độ rộng từ 600px trở lên
            },
            1000: {
                items: 3, // Hiển thị 5 item khi màn hình có độ rộng từ 1000px trở lên
            }
            // Các breakpoint và số lượng item khác có thể được thêm vào theo nhu cầu của bạn
        }
    });
    onLoadPage();

    $(document).on("click", ".next", function () {
        var vDivParent = $(this).parents('div').first();
        var imgCollection = vDivParent.find('img');
        var imgShowClas = imgCollection.filter('.show');
        var index = imgCollection.index(imgShowClas);
        if (index + 1 < imgCollection.length) {
            imgCollection.eq(index).removeClass('show');
            imgCollection.eq(index + 1).removeClass('disabled');
            imgCollection.eq(index).addClass('disabled');
            imgCollection.eq(index + 1).addClass('show');
        } else {
            // Nếu không có hình ảnh kế tiếp, chuyển về vị trí đầu tiên
            imgCollection.eq(index).removeClass('show');
            imgCollection.eq(0).removeClass('disabled');
            imgCollection.eq(index).addClass('disabled');
            imgCollection.eq(0).addClass('show');
        }
    });

    $(document).on("click", ".prev", function () {
        var vDivParent = $(this).parents('div').first();
        var imgCollection = vDivParent.find('img');
        var imgShowClas = imgCollection.filter('.show');
        var index = imgCollection.index(imgShowClas);
        // Kiểm tra nếu có hình ảnh trước đó
        if (index - 1 >= 0) {
            imgCollection.eq(index).removeClass('show');
            imgCollection.eq(index - 1).removeClass('disabled');
            imgCollection.eq(index).addClass('disabled');
            imgCollection.eq(index - 1).addClass('show');
        } else {
            // Nếu không có hình ảnh trước đó, chuyển về vị trí cuối cùng
            imgCollection.eq(index).removeClass('show');
            imgCollection.eq(imgCollection.length - 1).removeClass('disabled');
            imgCollection.eq(index).addClass('disabled');
            imgCollection.eq(imgCollection.length - 1).addClass('show');
        }
    });
})
/*** REGION 4 -  Vùng khai báo các hàm dùng chung */
function onLoadPage() {
    loadImgDesc();
    loadFeaturedRealestate();
    changeDate();
    loadPreviousAndNext();
    loadCountProject();
}
function loadCountProject() {
    $(".projectCount").each(function () {
        var $element = $(this); // Lưu giữ $(this) trong một biến
        var vId = $element.data("value");
        $.get(gURLIndex + "realestate/customer/" + vId, function (data) {
            $element.text(data.length); // Sử dụng biến $element thay vì $(this)
        });
    });
    $(".projectBuyCount").each(function () {
        var $element = $(this); // Lưu giữ $(this) trong một biến
        var vId = $element.data("value");
        $.get(gURLIndex + "realestate/customer/sold/" + vId, function (data) {
            $element.text(data.length); // Sử dụng biến $element thay vì $(this)
        });
    });

}
function changeDate() {
    $(".date-value").each(function () {
        var originalDateValue = $(this).data("value");
        var date = new Date(originalDateValue);
        var formattedDate = date.toLocaleDateString();
        $(this).html(formattedDate);
    });
}
function changeRequest() {
    $(".request").each(function () {
        var request = $(this).data("request");
        if (request == 0) {
            $(this).html("Cần bán");
        }
        if (request == 3) {
            $(this).html("Cho thuê");
        }
    });
}
function loadImgDesc() {
    $(".imgCarousel").each(function () {
        var vIdProject = $(this).data("id");
        $.get(gURLIndex + "photo?realEstateId=" + vIdProject, function (data) {
            createImg(data, $(this));
        }.bind(this));
    });
}

function createImg(paramData, element) {
    $.each(paramData, function (index, data) {
        var imgElement = $("<img>", {
            src: data.path,
            class: "disabled"
        });
        imgElement.appendTo(element);
    });
    // Thêm nút prev
    var prevButton = $("<button>", {
        class: "prev",
        text: "❮❮"
    });
    prevButton.appendTo(element);

    // Thêm nút next
    var nextButton = $("<button>", {
        class: "next",
        text: "❯❯"
    });
    nextButton.appendTo(element);
}
function loadFeaturedRealestate() {
    $.get(gURLIndex + "realestate/featured/page/" + gCurrentPage, function (data) {
        var featuredRealestateSection = $("#featuredProject");
        featuredRealestateSection.find("figure").remove();

        // Kiểm tra nếu danh sách không rỗng và có đủ phần tử
        if (data && data.length > 0) {
            var featuredRealestateTemplate = Handlebars.compile($("#featuredRealestate-template").html());
            data.forEach(function (realEstate) {
                var realEstateHtml = featuredRealestateTemplate(realEstate);
                featuredRealestateSection.append(realEstateHtml);
            });
            loadImgDesc();;
            changeDate();
            changeRequest();
        } else {
            // Xử lý khi danh sách không đủ phần tử
            featuredRealestateSection.append("<p>Danh sách trống hoặc không đủ phần tử.</p>");
        }
    }).fail(function () {
        // Xử lý khi có lỗi trong quá trình lấy dữ liệu
        var featuredRealestateSection = $("#featuredProject");
        featuredRealestateSection.append("<p>Có lỗi xảy ra khi lấy dữ liệu.</p>");
    });
}
function previousPageClick() {
    if (gCurrentPage > 1) { // Kiểm tra trang hiện tại là trang đầu tiên
        gCurrentPage--;
        loadFeaturedRealestate();
        loadPreviousAndNext();
    }
}
function nextPageClick() {
    var commentPage = $("#paginationFeaturedRealEstate li").length - 2;
    if (gCurrentPage < commentPage) {// Kiểm tra trang hiện tại là trang cuối
        gCurrentPage++;
        loadFeaturedRealestate();
        loadPreviousAndNext();
    }
}
function loadPreviousAndNext() {
    var commentPage = $("#paginationFeaturedRealEstate li").length - 2;
    if (gCurrentPage === 1) {
        $("#prePagination").hide();
    } else {
        $("#prePagination").show();
    }

    // Ẩn nút "Next" nếu là trang cuối
    if (gCurrentPage === commentPage) {
        $("#nextPagination").hide();
        $("#prePagination").show();
    } else {
        $("#nextPagination").show();
    }
    // Hiển thị cả hai nút nếu không phải ở trang đầu hoặc cuối
    if (gCurrentPage !== 1 && gCurrentPage !== commentPage) {
        $("#prePagination").show();
        $("#nextPagination").show();
    }
    if (commentPage == 0) {
        $("#prePagination").hide();
        $("#prePagination").hide();
    }
}
function commentPageClick(paramCommentPage) {
    gCurrentPage = parseInt(paramCommentPage.getAttribute('data-page'));
    loadFeaturedRealestate();
    loadPreviousAndNext();
}