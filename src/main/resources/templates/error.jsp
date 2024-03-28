<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
    <link rel="stylesheet" href="/plugin/owlcarousel/assets/owl.carousel.min.css">
    <link rel="stylesheet" href="/plugin/owlcarousel/assets/owl.theme.default.min.css">
    <script src="/plugin/a11y_accordion/main.min.js"></script>
    <script src="/plugin/owlcarousel/owl.carousel.min.js"></script>
    <link rel='stylesheet' type="text/css" href="/css/styles.css" />
    <!-- Css Custom -->
    <link rel='stylesheet' type="text/css" href="/css/home.css" />
    <link rel='stylesheet' type="text/css" href="/css/detail.css" />
    <link rel="stylesheet" type="text/css" href="/css/loginRegister.css">
    <link rel="stylesheet" type="text/css" href="/css/myProfile.css">
    <script type='text/javascript' src="/scripts/index.js"></script>
    <script type='text/javascript' src="/scripts/detail.js"></script>
    <script type='text/javascript' src="/scripts/user.js"></script>
</head>

<body>
    <!-- Header Start -->
    <div th:replace="~{fragments/header :: header}"></div>
    <!-- Header End -->


    <div class="d-flex align-items-center justify-content-center vh-100">
        <div class="text-center">
            <h1 class="display-1 fw-bold">Error</h1>
            <p class="fs-3"> <span class="text-danger">Cảnh báo!</span> Không thể truy cập trang web bạn vừa yêu cầu.
            </p>
            <p class="lead">
                Trang web bạn vừa yêu cầu không tồn tại hoặc tài khoản của bạn không có quyền truy cập vào trang này.
            </p>
            <a href="#" class="btn btn-primary" id="goBack">Quay về trang trước</a>
        </div>
    </div>



    <!-- Footer Start -->
    <div th:replace="~{fragments/footer :: footer}"></div>
    <!-- Footer End -->

    <script>
        // Lắng nghe sự kiện click trên nút "Quay về trang trước"
        document.getElementById("goBack").addEventListener("click", function () {
            // Sử dụng window.history để quay lại trang trước
            window.history.back();
        });
    </script>
</body>

</html>