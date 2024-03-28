<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ Hàng</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Css Custom -->
    <link rel='stylesheet' type="text/css" href="/css/home.css" />
    <link rel="stylesheet" type="text/css" href="/css/loginRegister.css">
    <link rel='stylesheet' type="text/css" href="/css/myCart.css" />
    <script type='text/javascript' src="/scripts/user.js"></script>
</head>

<body>
    <!-- Header Start -->
    <div th:replace="~{fragments/header :: header}"></div>
    <!-- Header End -->
    <!-- Content Star -->

    <div class="wrapper">
        <div class="d-flex align-items-center justify-content-between">
            <div class="d-flex flex-column">
                <div class="h3">Giỏ hàng</div>
                <div th:text="${cartCount+' Dự án'}" class="text-uppercase"></div>
            </div>
        </div>
        <div id="table" class="bg-white rounded">
            <div class="d-md-flex align-items-md-center px-3 pt-3">
                <div class="d-flex flex-column">
                    <div class="h4 font-weight-bold">Số lượng người đăng</div>
                </div>
                <div class="ml-auto d-flex align-items-center">
                    <div class="editors">
                        <img th:each="customer : ${uniqueCustomers}"
                            th:src="${customer.avatar != null ? customer.avatar : '/images/Customer/0/avatar.jpg'}"
                            alt="Customer Avatar">
                    </div>
                    <div class="text-muted pl-md-0 pl-5" th:text="${customerCount+' Người đăng'}">
                    </div>
                </div>
            </div>
            <hr>
            <div class="table-responsive">
                <table id="tbCart" class="table activitites">
                    <thead>
                        <tr>
                            <th scope="col" class="text-uppercase header">Dự án</th>
                            <th scope="col" class="text-uppercase">Mã dự án</th>
                            <th scope="col" class="text-uppercase">Giá bán</th>
                            <th scope="col" class="text-uppercase">Đặt cọc</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>

        </div>
        <div class="d-flex justify-content-between">
        </div>
        <div class="d-flex flex-column justify-content-end align-items-end">
            <div class="d-flex px-3 pr-md-5 py-1 subtotal">
                <div class="px-4">Tổng tiền</div>
                <div id="divTotalPrice" class="h5 font-weight-bold px-md-2" th:text="${totalPrice*0.5+' VNĐ'}"></div>
            </div>
        </div>
    </div>
    </div>

    <!-- The Modal -->
    <!-- The Modal -->
    <!-- Content End -->

    <!-- Footer Start -->
    <div th:replace="~{fragments/footer :: footer}"></div>
    <!-- Footer End -->
</body>
<script>
    const gURLMyCart = "http://localhost:8080/";
    $(document).ready(function () {
        onLoadPage();
        $(".subtotal").on("click", onSubtotalClick);
        $("#tbCart").on("click", ".close", onTbCloseClick);

    })
    function onLoadPage() {
        $.get(gURLMyCart + "cart", function (data) {
            onLoadTable(data);
        })
    }
    function onSubtotalClick() {
        var totalPriceString = $("#divTotalPrice").text();
        var totalPriceNumber = parseFloat(totalPriceString);
        $.get(gURLMyCart + "api/payment/create_payment/" + totalPriceNumber, function (data) {
            window.location.href = data.url;
        });
    }
    function onTbCloseClick() {
        var tr = $(this).closest('tr');
        var vIdProject = tr.find('td:eq(1)').text();
        deleteCart(vIdProject);
    }
    function deleteCart(paramIdProject) {
        $.ajax({
            url: gURLMyCart + "cart/" + paramIdProject,
            type: "DELETE",
            success: function () {
                // Xử lý khi xóa thành công
                console.log("Cart deleted successfully");
                onLoadPage();
            },
            error: function () {
                // Xử lý khi có lỗi
                console.log("Error deleting cart");
            }
        });
    }
    function onLoadTable(paramData) {
        var tbody = $('#tbCart tbody');

        // Xóa tất cả các dòng hiện tại trong tbody (nếu có)
        tbody.empty();

        // Duyệt qua dữ liệu và thêm từng dòng vào tbody
        $.each(paramData, function (index, cart) {
            var tr = $('<tr>');
            tr.append('<td class="item">' +
                '<div class="d-flex">' +
                '<img ' + (cart.realestate.photoMain != null ? 'src="' + cart.realestate.photoMain + '"' : 'src="/images/Project/0/imgDesc/istockphoto-1409329028-170667a.jpg"') + '>' +
                '<div class="pl-2">' +
                '<h3>' + (cart.realestate.title != null ? cart.realestate.title : 'Xem Chi Tiết') + '</h3>' +
                '<div class="text-uppercase new"><span class="fas fa-star"></span>new</div>' +
                '<div class="d-flex flex-column justify-content-center">' +
                '<div><a href="#"><span class="red text-uppercase">' +
                '<span class="fas fa-search pr-1"></span>' +
                'Xem chi tiết' +
                '</span></a></div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</td>');
            tr.append('<td>' + cart.realestate.id + '</td>');
            tr.append('<td class="d-flex flex-column"><span>' + cart.realestate.price + ' VNĐ</span></td>');
            tr.append('<td class="font-weight-bold"><span>' + cart.realestate.price * 0.5 + ' VNĐ</span><div class="close">&times;</div></td>');
            tbody.append(tr);
        });
    }
</script>

</html>