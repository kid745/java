const gURLProperty = "http://localhost:8080/";
$(document).ready(function () {
    changeDate();
    loadImgDesc();
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
    $(".page-link").on("click", onPageLinkClick);

})
function changeDate() {
    $(".date-value").each(function () {
        var originalDateValue = $(this).data("value");
        var date = new Date(originalDateValue);
        var formattedDate = date.toLocaleDateString();
        $(this).html(formattedDate);
    });
}
function loadImgDesc() {
    $(".imgCarousel").each(function () {
        var vIdProject = $(this).data("id");
        $.get(gURLProperty + "photo?realEstateId=" + vIdProject, function (data) {
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
function onPageLinkClick() {
    var vData = $(this).data("value");
    var currentUrl = window.location.href;
    var url = new URL(currentUrl);
    url.searchParams.delete("page");
    url.searchParams.append("page",vData);
    window.location.href = url;
}
