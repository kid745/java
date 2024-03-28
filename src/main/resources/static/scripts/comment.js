// Vùng khai báo biến toàn cục
let gCurrentPage = 1;
const gURLComment = "http://localhost:8080/realestate/";



/// Vùng xử lí sự kiện
$(document).ready(function () {
    $("#next-page").on("click", function () {
        nextPageClick();
    });

    $("#previous-page").on("click", function () {
        previousPageClick();
    });

    $(".comment-page").on("click", function () {
        commentPageClick(this);
    });
    loadComments();
    loadPreviousAndNext();
});

// Các hàm xử lý sự kiện
function previousPageClick() {
    if (gCurrentPage > 1) { // Kiểm tra trang hiện tại là trang đầu tiên
        gCurrentPage--;
        loadComments();
        loadPreviousAndNext();;
    }

}

function nextPageClick() {
    var commentPage = $("#pagination-comment li").length - 2;
    if (gCurrentPage < commentPage) {// Kiểm tra trang hiện tại là trang cuối
        gCurrentPage++;
        loadComments();
        loadPreviousAndNext();
    }

}

function commentPageClick(paramCommentPage) {
    gCurrentPage = parseInt(paramCommentPage.getAttribute('data-page'));
    loadComments();
    loadPreviousAndNext();
}

function loadComments() {
    var currentURL = window.location.href;
    // Tách URL thành các phần bằng dấu '/'
    var parts = currentURL.split('/');

    // Lấy giá trị cuối cùng trong mảng `parts`
    var valueToGet = parts[parts.length - 1].split('?');

    var propertyId=valueToGet[0];

    // Sử dụng $.get và .then để xử lý kết quả khi nó hoàn thành
    $.get(gURLComment + propertyId + "/comments/page/" + gCurrentPage)
        .then(function (comments) {
            var commentSection = $(".comment-section");
            commentSection.find('li').remove();
            var commentTemplate = Handlebars.compile($("#comment-template").html());
            // Duyệt qua mảng comments để tạo các thẻ <li> tương ứng
            comments.forEach(function (comment) {
                var commentHtml = commentTemplate(comment);
                commentSection.append(commentHtml);
            });
        })
        .catch(function (error) {
            console.error("Lỗi khi tải dữ liệu bình luận: " + error);
        });
}
function loadPreviousAndNext() {
    var commentPage = $("#pagination-comment li").length - 2;

    // Ẩn nút "Previous" nếu là trang đầu tiên
    if (gCurrentPage === 1) {
        $("#previous-page").hide();
    } else {
        $("#previous-page").show();
    }

    // Ẩn nút "Next" nếu là trang cuối
    if (gCurrentPage === commentPage) {
        $("#next-page").hide();
    } else {
        $("#next-page").show();
    }
    //nếu commentPage ẩn lun cả 2
    if (commentPage == 0) {
        $("#previous-page").hide();
        $("#next-page").hide();
    }
}