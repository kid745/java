const gURLUser = "http://localhost:8080/";

// Vùng khai báo biến toàn cục
let gToken;

// Hàm khởi tạo
$(document).ready(function () {
    onCheckLoginLoad();

    // Xử lý sự kiện click
    $(".aLogin").on("click", function () {
        OnHideOrShowModalClick("login");
    });
    $("#aRegister").on("click", function () {
        OnHideOrShowModalClick("register");
    });
    $("#aForgot").on("click", function () {
        OnHideOrShowModalClick("forgot");
    });
    $("#btnLogin").on("click", function (event) {
        event.preventDefault();
        onBtnLoginClick();
    });
});

// Hàm ẩn/hiện modal
function OnHideOrShowModalClick(paramClick) {
    // Ẩn tất cả các modal trước
    $("#loginModal").modal("hide");
    $("#registerModal").modal("hide");
    $("#forgotModal").modal("hide");
    $("#forgotModalConfirm").modal("hide");

    // Sau đó hiển thị modal tương ứng
    if (paramClick == "login") {
        $("#loginModal").modal("show");
    }
    if (paramClick == "register") {
        $("#registerModal").modal("show");
    }
    if (paramClick == "forgot") {
        $("#forgotModal").modal("show");
    }
}

// Hàm xử lý đăng nhập
function onBtnLoginClick() {
    const vUser = {
        username: "",
        password: ""
    };

    getDataInputToModalLogin(vUser);
    const vResult = validateDataLogin(vUser);
    if (vResult) {
        $.ajax({
            url: gURLUser + "user/login",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(vUser),
            success: function (responseObject) {
                checkLogin(responseObject); 
            },
            error: function (xhr) {
                $("#spanWarning").text("Sai tên đăng nhập hoặc mật khẩu");
            }
        });
    }
}

// Hàm lấy dữ liệu từ modal đăng nhập
function getDataInputToModalLogin(paramUser) {
    paramUser.username = $("#inpLoginUsername").val();
    paramUser.password = $("#inpLoginPassword").val();
}

// Hàm kiểm tra dữ liệu đăng nhập
function validateDataLogin(paramUser) {
    if (paramUser.username == "" || paramUser.password == "") {
        $("#spanWarning").text("Nhập đầy đủ tên đăng nhập và mật khẩu");
        return false;
    }
    else {
        $("#spanWarning").text("");
        return true;
    }
}

// Hàm xử lý sau khi đăng nhập thành công
function checkLogin(data) {
    if (!data) {
        $("#spanWarning").text("Sai tên đăng nhập hoặc mật khẩu");
    }
    else {
        OnHideOrShowModalClick();
        setCookie("token", data, 1);
        gToken = getCookie("token");
        getDataUserByToken()
            .then(function (data) {
                onCheckLoginLoad();
                onLoaDataUser(data.userId);
            })
            .catch(function (error) {
                console.log(error);
            });
    }
}

// Hàm thiết lập cookie
function setCookie(cname, cvalue, exdays) {
    const d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    const expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

// Hàm lấy cookie
function getCookie(cname) {
    const name = cname + "=";
    const decodedCookie = decodeURIComponent(document.cookie);
    const ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

// Hàm kiểm tra đăng nhập và tải dữ liệu người dùng
function onCheckLoginLoad() {
    gToken = getCookie("token");
    $("#menu-login").show();
    $("#menu-user").hide();
    if (gToken) {
        $("#menu-login").hide();
        $("#menu-user").show();
        getDataUserByToken()
            .then(function (data) {
                onLoaDataUser(data.userId);
            })
            .catch(function (error) {
                console.log(error);
            });
    }
}

// Hàm tải dữ liệu người dùng
function onLoaDataUser(paramUserId) {
    $.get(gURLUser + "customers/" + paramUserId)
        .done(function (vUser) {
            if (vUser.avatar != null) {
                $("#menu-avatar").attr("src", vUser.avatar);
            }
            else {
                $("#menu-avatar").attr("src", "/images/Customer/0/avatar.jpg");
            }
        })
        .fail(function (error) {
            console.log(error);
        });
}

// Hàm lấy thông tin người dùng dựa trên token
function getDataUserByToken() {
    return $.get(gURLUser + "userbytoken?data=" + gToken);
}
