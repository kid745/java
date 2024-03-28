'use strict';
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
const gURL_SEND_EMAIL = "http://localhost:8080/";
/*** REGION 2 - Vùng gán / thực thi sự kiện cho các elements */
$("#btnSendEmail").on("click", onBtnSendEmailClick);
/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
$(document).ready(function () {

})
function onBtnSendEmailClick() {
    console.log("test");
    var vResult = validateEmail();
    if (vResult) {
        var formData = new FormData();
        formData.append("email", $("#inpEmail").val());
        formData.append("content", vBlogContent.getData());
        fetch('/sendemail', {
            method: 'post',
            body: formData
        }).then(function (response) {
            if (response.status !== 200) {
                alert("Gửi mail không thành công!");
            } else {
                alert("Gửi mail thành công");
                location.reload();
            }
        }).catch(function (err) {
            alert("Hệ thống lỗi!");
        });
    }

}
/*** REGION 4 -  Vùng khai báo các hàm dùng chung */
function validateEmail() {
    if ($("#inpEmail").val() == "") {
        return false;
    }
    return true;
}