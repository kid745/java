<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Property</title>
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
    <script type='text/javascript' src="/scripts/detail.js"></script>
    <script type='text/javascript' src="/scripts/comment.js"></script>
    <script type='text/javascript' src="/scripts/user.js"></script>
</head>

<body>
    <!-- Header Start -->
    <div th:replace="~{fragments/header :: header}"></div>
    <!-- Header End -->
    <div class="container-fluid"
        style="background: #008080 url('/images/logo/image.jpg') no-repeat fixed;background-size: cover;">
        <div class="header-general-background row">
            <div class="container">
                <a class="cart" href="/my-cart">
                    <img src="/images/cart.png" />
                    <p><span id="count-item">0</span> dự án</p>
                </a>
                <div id="alertWarning" class="alert alert-warning alert-dismissible disabled" role="alert">
                    <strong>Cảnh báo!</strong> Bạn cần đăng nhập trước khi cho vào giỏ hàng.
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="property-heading">
                    <div class="property-info-block-inline">
                        <span class="property-price" th:text="'VNĐ: '+ ${property.price}"></span>
                        <span class="property-status" th:if="${property.request==0}">Cần Bán</span>
                        <span class="property-status" th:if="${property.request==3}">Cho Thuê</span>
                        <h2 th:text="${property.title}"></h2>
                        <div class="property-location">
                            <i class=" fas fa-map-marker-alt"></i>
                            <a href="#"><span th:text="${property.address}"></span></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row detail-info">
            <div class="container-fluid mt-4 mb-4 bg-light">
                <div class="row">
                    <div class="col-md-4 mt-3">
                        <div class="row">
                            <div class="col-6">
                                <p style="font-weight: 400;"><i class='fas fa-barcode'></i> Mã dự án: <span
                                        style="font-weight: 600;" th:text="${property.id}"></span></p>
                            </div>
                            <div class="col-6">
                                <p style="font-weight: 400;"><i class='fa fa-arrows'></i> Diện Tích: <span
                                        th:text="${property.acreage}+'M'"></span><sup>2</sup></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mt-3">
                        <div class="row">
                            <div class="col-6">
                                <p style="font-weight: 400;"><i class='fa fa-hotel'></i> Phòng Ngủ: <span
                                        style="font-weight: 600;" th:text="${property.bedroom}"></span></p>
                            </div>
                            <div class="col-6">
                                <p style="font-weight: 400;"><i class='fa fa-bath'></i> Phòng tắm: <span
                                        style="font-weight: 600;" th:text="${property.bathroom}"></span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-4 mb-4">
            <div class="single-property-image-main owl-carousel manual ere-carousel-manual owl-loaded owl-drag mb-4">
                <div class="owl-stage-outer">
                    <div class="owl-stage">
                        <div class="owl-item" th:if="${photoIds!=null}" th:each="photo:${photoIds}">
                            <div class="property-gallery-item ere-light-gallery">
                                <img th:src="${photo.path}">
                            </div>
                        </div>
                    </div>
                    <div class="owl-stage" th:if="${photoIds==null or photoIds.isEmpty()}">
                        <div class="owl-item">
                            <div class="property-gallery-item ere-light-gallery">
                                <img src="/images/Project/0/imgDesc/istockphoto-1409329028-170667a.jpg">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="single-property-image-thumb owl-carousel manual ere-carousel-manual owl-loaded owl-drag">
                <div class="owl-stage-outer">
                    <div class="owl-stage">
                        <div class="owl-item pl-1 pr-1 current" th:if="${photoIds!=null}" th:each="photo:${photoIds}">
                            <div class="property-gallery-item">
                                <img th:src="${photo.path}">
                            </div>
                        </div>
                    </div>
                    <div class="owl-stage" th:if="${photoIds==null or photoIds.isEmpty()}">
                        <div class="owl-item pl-1 pr-1 current">
                            <div class="property-gallery-item">
                                <img src="/images/Project/0/imgDesc/istockphoto-1409329028-170667a.jpg">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class=" row mt-4 mb-4">
            <div class="col-12">
                <div class="row ">
                    <div class="col-12 ere-heading-style2 mt-4 mb-4">
                        <h2>Mô tả chi tiết</h2>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <p th:if="${not #strings.isEmpty(property.description)}" style="font-weight: 400;"
                            th:text="${property.description}"></p>
                        <p th:if="${#strings.isEmpty(property.description)}" style="font-weight: 400;"
                            th:text="'Người đăng bài chưa cập nhật miêu tả dự án'"></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-4 mb-4">
            <div class="col-12">
                <div class="row ">
                    <div class="col-12 ere-heading-style2 mt-4 mb-4">
                        <h2>Địa Chỉ</h2>
                    </div>
                </div>
                <div class="row mb-n4">
                    <div class="col-12">
                        <p style="font-weight: 600;"> Địa chỉ: <span style="font-weight: 400;"
                                th:text="${property.address}"></span></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <p class="mb-n1" style="font-weight: 600;"> Quốc Gia: <span style="font-weight: 400;">Việt
                                Nam</span></p>
                        <p class="mb-n1" style="font-weight: 600;"> Tỉnh/ Thành Phố: <span style="font-weight: 400;"
                                th:text="${property.ward.district.province.name}"></span></p>

                    </div>
                    <div class="col-md-6">
                        <p class="mb-n1" style="font-weight: 600;"> Quận / Huyện: <span style="font-weight: 400;"
                                th:text="${property.ward.district.name}"></span>
                        </p>
                        <p class="mb-n1" style="font-weight: 600;"> Mã zip: <span style="font-weight: 400;"></span></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-4 mb-4">
            <section class="accordion-tabs js-tabs" data-tabs-allowed="true" data-breakpoint="640"
                data-start-collapsed="true" style="width: 100%;">
                <ul role="tablist" class="tabs-tab-list">
                    <li role="presentation"><a id="tab1" href="#ere_overview" role="tab" aria-controls="ere_overview"
                            aria-selected="true" class="tabs-trigger js-tabs-trigger">Thông tin cơ bản</a></li>
                    <li role="presentation"><a id="tab2" href="#ere_features" role="tab" aria-controls="ere_features"
                            class="tabs-trigger js-tabs-trigger">Tiêu biểu</a></li>
                    <li role="presentation"><a id="tab3" href="#ere_video" role="tab" aria-controls="ere_video"
                            class="tabs-trigger js-tabs-trigger">Video Giới Thiệu</a></li>
                </ul>
                <section id="ere_overview" role="tabpanel" aria-labelledby="tab1" class="tabs-panel js-tabs-panel"
                    tabindex="0">
                    <div class="accordion-trigger js-accordion-trigger" aria-controls="ere_overview"
                        aria-expanded="true" tabindex="0">Tổng quan <div class="accordion-trigger-icon"><span
                                class="label--open">Open</span><span class="label--close">Close</span>
                            <svg aria-hidden="true" focusable="false" viewBox="0 0 20 20">
                                <rect class="vert" height="18" width="2" fill="currentColor" y="1" x="9"></rect>
                                <rect height="2" width="18" fill="currentColor" y="9" x="1"></rect>
                            </svg>
                        </div>
                    </div>
                    <div class="content" aria-hidden="false">
                        <div class="row">
                            <div class="col-md-6 pr-1">
                                <div class="row">
                                    <div class="col-6">
                                        <strong>Mã dự án</strong>
                                    </div>
                                    <div class="col-6">
                                        <span th:text="${property.id}"></span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <strong>Giá</strong>
                                    </div>
                                    <div class="col-6">
                                        <span th:text="${property.price}+' VNĐ'"></span><span
                                            th:if="${property.request==3}" style="font-size: 12px;color: #fb6a19;"> /
                                            Tháng</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <strong>Kiểu dự án</strong>
                                    </div>
                                    <div class="col-6">
                                        <span th:if="${property.type==0}">Đất</span>
                                        <span th:if="${property.type==1}">Nhà ở</span>
                                        <span th:if="${property.type==2}">Căn hộ/Chung cư</span>
                                        <span th:if="${property.type==3}">Văn phòng, Mặt bằng</span>
                                        <span th:if="${property.type==4}">Kinh doanh</span>
                                        <span th:if="${property.type==5}">Phòng trọ</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <strong>Tình trạng dự án</strong>
                                    </div>
                                    <div class="col-6">
                                        <span th:if="${property.request==0}">Cần bán</span>
                                        <span th:if="${property.request==2}">Cần mua</span>
                                        <span th:if="${property.request==3}">Cho thuê</span>
                                        <span th:if="${property.request==4}">Cần thuê</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <strong>Năm đăng tin</strong>
                                    </div>
                                    <div class="col-6">
                                        <span class="year-built" th:value="${property.dateCreate}"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 pl-1">

                                <div class="row">
                                    <div class="col-6">
                                        <strong>Diện tích</strong>
                                    </div>
                                    <div class="col-6">
                                        <span th:text="${property.acreage}+'M'"></span><sup>2</sup>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <strong>Garages</strong>
                                    </div>
                                    <div class="col-6">
                                        <span>1</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <strong>Phòng ngủ</strong>
                                    </div>
                                    <div class="col-6">
                                        <span th:text="${property.bedroom}"></span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <strong>Phòng tắm</strong>
                                    </div>
                                    <div class="col-6">
                                        <span th:text="${property.bathroom}"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <section id="ere_video" role="tabpanel" aria-labelledby="tab3" class="tabs-panel js-tabs-panel">
                    <div class="accordion-trigger js-accordion-trigger" aria-controls="ere_video" aria-expanded="false"
                        tabindex="0">Video <div class="accordion-trigger-icon"><span
                                class="label--open">Open</span><span class="label--close">Close</span><svg
                                aria-hidden="true" focusable="false" viewBox="0 0 20 20">
                                <rect class="vert" height="18" width="2" fill="currentColor" y="1" x="9"></rect>
                                <rect height="2" width="18" fill="currentColor" y="9" x="1"></rect>
                            </svg></div>
                    </div>
                    <div class="content" aria-hidden="true">
                        <div class="row">
                            <div class="col-12">

                            </div>
                        </div>
                    </div>
                </section>
                <section id="ere_virtual_tour" role="tabpanel" aria-labelledby="tab3" class="tabs-panel js-tabs-panel">
                    <div class="accordion-trigger js-accordion-trigger" aria-controls="ere_virtual_tour"
                        aria-expanded="false" tabindex="0">Virtual Tour <div class="accordion-trigger-icon"><span
                                class="label--open">Open</span><span class="label--close">Close</span><svg
                                aria-hidden="true" focusable="false" viewBox="0 0 20 20">
                                <rect class="vert" height="18" width="2" fill="currentColor" y="1" x="9"></rect>
                                <rect height="2" width="18" fill="currentColor" y="9" x="1"></rect>
                            </svg></div>
                    </div>
                    <div class="content" aria-hidden="true">
                        <div class="col-12">
                        </div>
                    </div>
                </section>
            </section>
        </div>
        <div class="row mt-4 mb-4">
            <div class="col-12">
                <div class="row ">
                    <div class="col-12 ere-heading-style2 mt-4 mb-4">
                        <h2>Liên hệ</h2>
                    </div>
                </div>
                <div class="row ">
                    <div class="col-md-6">
                        <a title="Abody Swedey" th:href="'/agent/'+${property.customers.id}">
                            <img style="width: 100%; max-height: 400px; object-fit: cover;overflow: hidden; "
                                th:if="${not #strings.isEmpty(property.customers.avatar)}"
                                th:src="${property.customers.avatar}">
                            <img style="width: 100%; max-height: 400px; object-fit: cover;overflow: hidden; "
                                th:if="${#strings.isEmpty(property.customers.avatar)}"
                                src="/images/customer/0/avatar.jpg">
                        </a>
                    </div>
                    <div class="col-md-6 agent-info">
                        <div class="mb-4">
                            <h4 class="pt-2 mb-1" style="font-weight: 600;">
                                <a th:title="${property.customers.contactName}"
                                    th:href="${'/property?agent='+property.customers.id}"
                                    th:text="${property.customers.contactName}"></a>
                            </h4>
                            <span style="font-weight: 500;color: #8f8f8f;">Người đăng tin</span>
                            <p class="mb-1 " style="font-weight: 400;">
                                <i class="fa fa-map-marker color-icon"></i> Địa Chỉ:
                                <span
                                    th:if="${property.customers.address != null and not #strings.isEmpty(property.customers.address)}"
                                    th:text="${property.customers.address}"></span>
                                <span
                                    th:if="${property.customers.address == null and #strings.isEmpty(property.customers.address)}">Đang
                                    cập nhật</span>
                            </p>

                            <p class="mb-1" style="font-weight: 400;">
                                <i class="fa fa-phone color-icon"></i> Số điện thoại:
                                <span th:if="${not #strings.isEmpty(property.customers.mobile)}"
                                    th:text="${property.customers.mobile}"></span>
                                <span th:unless="${not #strings.isEmpty(property.customers.mobile)}">Đang cập
                                    nhật</span>
                            </p>
                            <p class="mb-1" style="font-weight: 400;">
                                <i class="fa fa-envelope color-icon"></i> Email:
                                <span th:if="${not #strings.isEmpty(property.customers.email)}"
                                    th:text="${property.customers.email}"></span>
                                <span th:if="${#strings.isEmpty(property.customers.email)}">Đang cập nhật</span>
                            </p>
                            <a class="btn btn-primary" th:href="${'/property?agent='+property.customers.id}">Xem người
                                đăng</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-4 mb-4 justify-content-center">
            <div class="col-12 text-center">
                <i class="fa fa-calendar color-icon"></i><span class="property-date date-value"
                    th:data-value="${property.dateCreate}"></span>
                <i class="fa fa-eye color-icon"> </i><span class="property-views-count" th:text="${property.view}">
                </span>
            </div>
        </div>
        <div class="row mt-4 mb-4">
            <div class="col-12">
                <h3>
                    <span th:text="${commentsCount + ' Bình luận trong '}"></span>
                    <a th:href="'/property/' + ${property.id}" th:text="${property.title}"></a>
                </h3>
                <ol class="comment-section">
                    <script id="comment-template" type="text/x-handlebars-template">
                        <li>
                            <div class="comment">
                                <div class="comment-author">
                                    {{#if customers.avatar}}
                                    <img class="avatar avatar-70 photo" height="70" width="70" src="{{customers.avatar}}">
                                    {{/if}}
                                    {{#unless customers.avatar}}
                                    <img class="avatar avatar-70 photo" height="70" width="70" src="/images/customer/0/avatar.jpg">
                                    {{/unless}}
                                </div>
                                <div class="comment-content">
                                    <div class="comment-meta">
                                        <span>{{customers.contactName}}</span>
                                        <p class="date-value" data-value="{{dateCreate}}"></p>
                                    </div>
                                    <div class="comment-text">
                                        <p>{{content}}</p>
                                    </div>
                                    <span class="bg-color"></span>
                                    <hr>
                                </div>
                            </div>
                        </li>
                    </script>
                </ol>
            </div>
        </div>
        <!-- Hiển thị nút điều hướng phân trang -->
        <div class="row mb-4 mt-5">
            <div class="col-md-12">
                <nav aria-label="Page navigation example">
                    <ul id="pagination-comment" class="pagination justify-content-center">
                        <!-- Sử dụng Thymeleaf để tạo nút điều hướng phân trang -->
                        <li class="page-item">
                            <a class="page-link" id="previous-page">Previous</a>
                        </li>
                        <li th:each="commentPage:${pageNumbers}" class="page-item">
                            <a class="comment-page page-link " th:data-page="${commentPage}"
                                th:text="${commentPage}"></a>
                        </li>
                        <li th:class="page-item">
                            <a class="page-link" id="next-page">Next</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
    <!-- Đặt các phần nội dung trang khác ở đây -->
    <!-- Footer Start -->
    <div th:replace="~{fragments/footer :: footer}"></div>
    <!-- Footer End -->

</body>

</html>