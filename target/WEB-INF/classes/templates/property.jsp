<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Property</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Css Custom -->
    <link rel="stylesheet" href="/css/footer.css">
    <link rel="stylesheet" href="/css/loginRegister.css">
    <link rel="stylesheet" href="/css/sidebar.css">
    <link rel="stylesheet" href="/css/index.css">

    <script type='text/javascript' src="/scripts/sidebar.js"></script>
    <script type='text/javascript' src="/scripts/cart.js"></script>
    <script type='text/javascript' src="/scripts/user.js"></script>
    <script type='text/javascript' src="/scripts/property.js"></script>
</head>

<body style="overflow-x: hidden;">
    <!-- Header Start -->
    <div th:replace="~{fragments/header :: header}"></div>
    <!-- Header End   -->

    <!-- Sidebar Start-->
    <div th:replace="~{fragments/sidebar}"></div>
    <!-- Sidebar End  -->
    <!-- Cart Start -->
    <div th:replace="~{fragments/cart}"></div>
    <!-- Cart End -->
    <div class="container">
        <div name="listProject" style="overflow: auto;" class="mt-3 mb-3">
            <h2 class="text-warning" th:text>Danh sách dự án >></h2>
            <figure class="snip" th:each="property:${properties}">
                <a class="text-white" th:href="@{'/property/' + ${property.id}}">
                    <h4 th:if="${not #strings.isEmpty(property.title)}" th:text="${property.title}"></h4>
                    <h4 th:if="${#strings.isEmpty(property.title)}">Xem chi tiết</h4>
                </a>
                <div class="image imgCarousel" th:data-id="${property.id}">
                    <img th:if="${not #strings.isEmpty(property.photoMain)}" th:src="${property.photoMain}"
                        class="show" />
                    <img th:if="${#strings.isEmpty(property.photoMain)}"
                        src="/images/Project/0/imgDesc/istockphoto-1409329028-170667a.jpg" class="show" />
                </div>
                <figcaption class="ps-1 pb-4">
                    <p><i class="fas fa-map-marker-alt"></i> Địa chỉ: <span th:text="${property.address}"></span></p>
                    <p><i class="fas fa-hand-holding-heart"></i> Nhu cầu:
                        <span th:if="${property.request==0}">CầnBán</span>
                        <span th:if="${property.request==3}">Cho thuê</span>
                    </p>
                    <p><i class="fas fa-info-circle"></i></i> Tình trạng:
                        <span th:if="${property.status}">Chờ giao dịch</span>
                        <span th:unless="${property.status}">Sẵn sàng giao dịch</span>
                    </p>

                    <p><i class="fas fa-calendar"></i>
                        <span class="date-value" th:data-value="${property.dateCreate}"></span>
                    </p>
                    <div class="row">
                        <div class="col-auto">
                            <p><i class="fas fa-user"></i> <span th:text="${property.customers.contactName}"></span>
                            </p>
                            <p><i class="fas fa-expand"></i> <span th:text="${property.acreage+'m²'}"></span></p>
                            <p><i class="fas fa-car"></i> <span th:text="${property.garage}"></span></p>
                        </div>
                        <div class="col-auto">
                            <p><i class="fas fa-bed"></i> <span th:text="${property.bedroom}"></span></p>
                            <p><i class="fas fa-bath"></i> <span th:text="${property.bathroom}"> </span></p>
                            <p><i class="fas fa-coins"></i> <span class="price"
                                    th:text="${property.price+' VNĐ'}"></span></p>
                        </div>
                    </div>
                </figcaption>
                <a class="detailProject text-center " th:href="@{'/property/' + ${property.id}}"><i
                        class="far fa-eye"></i> Xem chi tiết</a>
                <a class="add-to-cart text-center" th:data-value="${property.id}">Thêm vào giỏ hàng</a>
            </figure>
        </div>
        <!-- Hiển thị nút điều hướng phân trang -->
        <div class=" col-md-12">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <!-- Sử dụng Thymeleaf để tạo nút điều hướng phân trang -->
                    <li th:class="${'page-item'}" th:if="${currentPage > 1}">
                        <a class="page-link" th:data-value="${currentPage+1}">Trước</a>
                    </li>
                    <li th:each="page: ${pageNumbers}"
                        th:classappend="${page == currentPage} ? 'page-item active' : 'page-item'">
                        <a th:class="'page-link'"th:text="${page}"  th:data-value="${page}"></a>
                    </li>
                    <li th:class="${'page-item'}" th:if="${currentPage < totalPages}">
                        <a class="page-link" th:data-value="${currentPage+1}">Tiếp</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    <!-- Đặt các phần nội dung trang khác ở đây -->
    <!-- Footer Start -->
    <div th:replace="~{fragments/footer :: footer}"></div>
    <!-- Footer End -->

</body>
<script>
    $(document).ready(function () {
    })
</script>

</html>