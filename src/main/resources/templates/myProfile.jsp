<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hồ sơ của bạn</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/autonumeric/4.10.0/autoNumeric.min.js"></script>
    <link href="https://unpkg.com/filepond/dist/filepond.css" rel="stylesheet">
    <script src="https://unpkg.com/filepond/dist/filepond.js"></script>
    <link href="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.css"
        rel="stylesheet">
    <!-- Add plugin scripts -->
    <script src="https://cdn.ckeditor.com/ckeditor5/40.1.0/classic/ckeditor.js"></script>
    <script src="https://ckeditor.com/apps/ckfinder/3.4.5/ckfinder.js"></script>
    <script src="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.js"></script>
    <script src="https://unpkg.com/filepond-plugin-image-resize/dist/filepond-plugin-image-resize.js"></script>
    <script src="https://unpkg.com/filepond-plugin-image-transform/dist/filepond-plugin-image-transform.js"></script>
    <script
        src="https://unpkg.com/filepond-plugin-file-validate-type/dist/filepond-plugin-file-validate-type.js"></script>
    <link href="https://vjs.zencdn.net/7.14.3/video-js.css" rel="stylesheet">
    <script src="https://vjs.zencdn.net/7.14.3/video.js"></script>
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap" />
    <link rel="stylesheet" href="/plugin/owlcarousel/assets/owl.carousel.min.css">
    <link rel="stylesheet" href="/plugin/owlcarousel/assets/owl.theme.default.min.css">
    <script src="/plugin/a11y_accordion/main.min.js"></script>
    <script src="/plugin/owlcarousel/owl.carousel.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/ScrollMagic/2.0.7/ScrollMagic.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/ScrollMagic/2.0.7/plugins/debug.addIndicators.min.js"></script>
    <link rel='stylesheet' type="text/css" href="/css/styles.css" />
    <!-- Css Custom -->
    <link rel='stylesheet' type="text/css" href="/css/home.css" />
    <link rel='stylesheet' type="text/css" href="/css/detail.css" />
    <link rel="stylesheet" type="text/css" href="/css/loginRegister.css">
    <link rel="stylesheet" type="text/css" href="/css/myProfile.css">
    <script type='text/javascript' src="/scripts/detail.js"></script>
    <script type='text/javascript' src="/scripts/user.js"></script>
    <script type='text/javascript' src="/scripts/myProfile.js"></script>
</head>

<body>
    <!-- Header Start -->
    <div th:replace="~{fragments/header :: header}"></div>
    <!-- Header End -->

    <!-- Content Star -->
    <section style="background-color: #eee;">
        <div class="container py-5 position-relative">
            <div class="row">
                <div class="col">
                    <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                        <ol class="breadcrumb mb-0">
                            <h1>Thông tin tài khoản</h1>
                        </ol>
                    </nav>
                </div>
            </div>
            <div id="divInfoUser" class="row" th:data-iduser="${profile.id}">
                <div class="col-lg-4">
                    <div class="card mb-4">
                        <div class="card-body text-center">
                            <input id="inputAvatar" type="file" />
                            <h5 class="my-3" th:if="${profile.contactName!=null and !profile.contactName.isEmpty()}"
                                th:text="${profile.contactName}"></h5>
                            <h5 class="my-3" th:if="${profile.contactName==null or profile.contactName.isEmpty()}">Đang
                                cập nhật</h5>
                            <p class="mb-4">Địa chỉ:
                                <span class="text-muted" th:if="${profile.address!=null}"
                                    th:text="${profile.address}"></span>
                            </p>
                            <p class="text-muted mb-4" th:if="${profile.address==null or profile.address == ''}">Đang
                                cập nhật</p>
                            <div class="d-flex justify-content-around mb-2">
                                <button id="btnUpdateUser" class="btn btn-outline-primary ms-1">Sửa</button>
                                <button id="btnSaveUser" class="btn btn-outline-info ms-1" disabled>Lưu</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8">
                    <div class="row">
                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Họ và Tên</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <input style="background: none; " type="text" id="inpFullName"
                                                class="form-control custom-input"
                                                th:if="${profile.contactName!=null and !profile.contactName.isEmpty()}"
                                                th:value="${profile.contactName}" placeholder="Đang Cập Nhật" readonly>
                                            <input style="background: none; " type="text" id="inpFullName"
                                                class="form-control custom-input"
                                                th:if="${profile.contactName==null or profile.contactName.isEmpty()}"
                                                placeholder="Đang cập nhật" readonly>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Email</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <input style="background: none; " type="text" id="inpEmail"
                                                class="form-control custom-input"
                                                th:if="${profile.email!=null and !profile.email.isEmpty()}"
                                                th:value="${profile.email}" placeholder="Đang Cập Nhật" readonly>
                                            <input style="background: none; " type="text" id="inpEmail"
                                                class="form-control custom-input"
                                                th:if="${profile.email==null or profile.email.isEmpty()}"
                                                placeholder="Đang Cập Nhật" readonly>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Số điện thoại</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <input style="background: none; " type="text" id="inpMobie"
                                                class="form-control custom-input"
                                                th:if="${profile.mobile!=null and !profile.mobile.isEmpty()}"
                                                th:value="${profile.mobile}" placeholder="Đang Cập Nhật" readonly>
                                            <input style="background: none; " type="text" id="inpMobie"
                                                class="form-control custom-input"
                                                th:if="${profile.mobile==null or profile.mobile.isEmpty()}"
                                                placeholder="Đang Cập Nhật" readonly>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Địa chỉ</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <input style="background: none; " type="text" id="inpUserAddress"
                                                class="form-control custom-input"
                                                th:if="${profile.address!=null and !profile.address.isEmpty()}"
                                                th:value="${profile.address}" placeholder="Đang Cập Nhật" readonly>
                                            <input style="background: none;" type="text" id="inpUserAddress"
                                                class="form-control custom-input"
                                                th:if="${profile.address==null or profile.address.isEmpty()}"
                                                placeholder="Đang Cập Nhật" readonly>

                                        </div>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Ghi chú</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <textarea rows="4" cols="50" style="background: none;" id="inpUserNote"
                                                class="form-control custom-input"
                                                th:if="${profile.note!=null and !profile.note.isEmpty()}" readonly
                                                th:text="${profile.note}" placeholder="Đang Cập Nhật"></textarea>
                                            <textarea rows="3" cols="50" style="background: none;" type="text"
                                                id="inpUserNote" class="form-control custom-input"
                                                th:if="${profile.note==null or profile.note.isEmpty()}"
                                                placeholder="Đang Cập Nhật" readonly></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                        <ol class="breadcrumb mb-0">
                            <h1>Dự Án Đã Đăng</h1>
                        </ol>
                    </nav>
                </div>
            </div>
            <button id="btnShowAddModalProject" class="btn btn-primary"><i class="fas fa-plus"></i> Thêm dự án</button>

            <div id="divProject">
                <div class="card" th:each="cardProperty : ${properties}">
                    <span th:unless="${cardProperty.approve}" class="ribbon2"> Chờ duyệt</span>
                    <img th:if="${cardProperty.photoMain == null}" class="card-img-top"
                        src="/images/Project/0/imgDesc/istockphoto-1409329028-170667a.jpg">
                    <img th:if="${cardProperty.photoMain != null}" class="card-img-top"
                        th:src="${cardProperty.photoMain}">
                    <div class="card-img-overlay d-flex flex-column justify-content-start align-items-end">
                        <p th:if="${cardProperty.featured==true}"
                            th:class='${"bg-warning rounded-left card-text text-white m-0 mb-1"}'>Tiêu Biểu</p>
                        <p th:if="${cardProperty.request == 3}"
                            th:class='${"bg-success rounded-left card-text text-white m-0 mb-1"}'>Cho thuê</p>
                        <p th:if="${cardProperty.request == 0}"
                            th:class='${"bg-info rounded-left card-text text-white m-0 mb-1"}'>Cần Bán
                        </p>
                    </div>
                    <a class="card-action" th:data-value="${cardProperty.id}"><i class="fas fa-cog"></i></a>
                    <div class="card-body text-left">
                        <div class="col-auto ml-2">
                            <h5 class="card-title"
                                th:if="${cardProperty.title != null && !#strings.isEmpty(cardProperty.title)}">
                                <i class="text-info fas fa-search fa-xs"></i>
                                <span th:text="${' '+cardProperty.title}"></span>
                            </h5>
                            <h5 class="card-title"
                                th:if="${cardProperty.title==null|| #strings.isEmpty(cardProperty.title)}">
                                <i class=" text-info fas fa-search fa-xs"></i> Xem chi tiết
                            </h5>
                        </div>
                        <div class="row justify-content-start">
                            <div class="col-auto ml-4">
                                <p th:class="${'card-text'}">
                                    <i class="text-info fas fa-money-bill"></i>
                                    <span th:text="'VND: ' + ${cardProperty.price}"></span>
                                </p>
                            </div>
                        </div>
                        <div class="row justify-content-start">
                            <div class="col-auto ml-4">
                                <p href="#">
                                    <i class="text-info fas fa-map-marker-alt"></i>
                                    <span th:text="${cardProperty.address}"></span>
                                </p>
                            </div>
                        </div>
                        <div class="row d-flex justify-content-around">
                            <div class="col-auto">
                                <p href="#">
                                    <i class="text-info fas fa-user"></i>
                                    <span
                                        th:text="${#strings.length(cardProperty.customers.contactName) > 20 ? #strings.substring(cardProperty.customers.contactName, 0, 17) + '...' : cardProperty.customers.contactName}"></span>
                                </p>
                            </div>
                            <div class="col-auto">
                                <i class="text-info fas fa-calendar"></i>
                                <span class="date-value" th:if="${cardProperty.dateUpdate == null}"
                                    th:data-value="${cardProperty.dateCreate}"></span>
                                <span class="date-value" th:if="${cardProperty.dateUpdate != null}"
                                    th:data-value="${cardProperty.dateUpdate}"></span>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer bg-info">
                        <div class="row d-flex justify-content-around">
                            <div class="col-auto">
                                <p th:if="${cardProperty.acreage!=null}" data-tooltip="Diện tích"> <span
                                        class="text-light fas fa-expand"></span><span
                                        th:text="${' '+cardProperty.acreage + ' M²'}"></span></p>
                                <p th:if="${cardProperty.acreage==null}" data-tooltip="Diện tích"><span
                                        class="text-light fas fa-expand"></span><span>0 M²</span></p>
                            </div>
                            <div class="col-auto">
                                <p th:if="${cardProperty.bedroom!=null}" data-tooltip="Phòng ngủ"><span
                                        class="text-light fas fa-lightbulb"></span><span
                                        th:text="${' '+cardProperty.bedroom}"></span></p>
                                <p th:if="${cardProperty.bedroom==null}" data-tooltip="Phòng ngủ"><span
                                        class="text-light fas fa-lightbulb"></span> 0</p>
                            </div>
                            <div class="col-auto">
                                <p th:if="${cardProperty.bathroom!=null}" data-tooltip="Phòng tắm"><span
                                        class="text-light fas fa-tint"></span><span
                                        th:text="${' ' +cardProperty.bathroom }"></span></p>
                                <p th:if="${cardProperty.bathroom==null}" data-tooltip="Phòng tắm"><span
                                        class="text-light fas fa-tint"></span> 0</p>
                            </div>
                            <div class="col-auto">
                                <p data-tooltip="Garage">
                                    <i class="text-light fas fa-car"></i>
                                    <span th:text="${' '+cardProperty.garage}"></span>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class=" container">
            <div class="row">
                <div class="col">
                    <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                        <ol class="breadcrumb mb-0">
                            <h1>Bài Viết Đã Đăng</h1>
                        </ol>
                    </nav>  
                    <button id="btnAddBlog" class="btn btn-primary" data-bs-toggle="modal"
                        data-bs-target="#modalUPdateBlog"><i class="fas fa-plus"></i> Thêm Bài Viết</button>
                    <div class="blogList">
                        <div th:each="blog:${blogList}" class="card ml-1 mr-1">
                            <span th:unless="${blog.approve}" class="ribbon2">Chờ duyệt</span>
                            <img th:if="${not #strings.isEmpty(blog.photoMain)}" th:src="${blog.photoMain}"
                                class="card-img-top" alt="Card Image" />
                            <img th:if="${#strings.isEmpty(blog.photoMain)}"
                                src="/images/Blog/0/istockphoto-1409329028-170667a.jpg" class="card-img-top"
                                alt="Card Image" />
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title text-truncate" th:text="${blog.name}"></h5>
                                <p class="card-text mb-4 card-text-description" th:text="${blog.description}"></p>
                                <a th:data-value="${blog.id}"
                                    class="btn btn-primary text-white mt-auto align-self-start showModalBlog"
                                    data-bs-toggle="modal" data-bs-target="#modalUPdateBlog">Sửa</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- The Modal -->
    <div class="modal" id="modalUpdateProject">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Sửa dự án</h4>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Tên dự án</label>
                        <div class="col">
                            <input type="text" class="form-control" id="inpTitle" placeholder="Đang cập nhật">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Loại Bất Động Sản</label>
                        <div class="col">
                            <select id="sltType" class="form-control">
                                <option value="1">Nhà Đất</option>
                                <option value="2">Chung cư</option>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Nhu cầu</label>
                        <div class="col">
                            <select id="sltRequest" class="form-control">
                                <option value="0">Cần Bán</option>
                                <option value="3">Cho Thuê</option>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Tỉnh</label>
                        <div class="col">
                            <select id="sltProvince" class="form-control">
                                <option disabled selected>Chọn Tỉnh</option>
                                <option th:each="province : ${provinces}" th:value="${province.id}"
                                    th:text="${province.name}"></option>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Quận</label>
                        <div class="col">
                            <select id="sltDistrict" class="form-control">
                                <option disabled selected>Chọn Quận</option>
                                <!-- các option khác -->
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Phường</label>
                        <div class="col">
                            <select id="sltWard" class="form-control">
                                <option disabled selected>Chọn Phường</option>
                                <!-- các option khác -->
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Đường</label>
                        <div class="col">
                            <select id="sltStreet" class="form-control">
                                <option disabled selected>Chọn Đường</option>
                                <!-- các option khác -->
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Địa chỉ </label>
                        <div class="col">
                            <input type="text" class="form-control" id="ipnAddress" placeholder="Đang cập nhật">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Giá tiền</label>
                        <div class="col">
                            <input class="form-control" id="inpPrice" placeholder="Đang cập nhật">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Phòng ngủ</label>
                        <div class="col">
                            <input type="number" class="form-control" id="inpBed" placeholder="Đang cập nhật">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Phòng tắm</label>
                        <div class="col">
                            <input type="number" class="form-control" id="inpBath" placeholder="Đang cập nhật">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Garage</label>
                        <div class="col">
                            <input type="number" class="form-control" id="inpGarage" placeholder="Đang cập nhật">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Giới thiệu</label>
                        <div class="col">
                            <textarea class="form-control" rows="4" cols="50" id="inpDescription"
                                placeholder="Đang cập nhật"></textarea>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Ảnh nền</label>
                        <div class="col">
                            <input id="inpImgThum" type="file">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Ảnh Miêu Tả</label>
                        <div class="col">
                            <input id="inpImgDesc" type="file">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Video miêu tả</label>
                        <div class="col">
                            <input id="inpVideoDesc" type="file">
                            <video id="videoPlayer" class="video-js vjs-default-skin" controls preload="auto"
                                style="display: none; height: auto; width: 100%;"></video>
                        </div>
                    </div>
                    <div class="row d-flex justify-content-around">
                        <button id="btnUpdateProject" class="btn btn-primary col-auto">Sửa</button>
                        <button id="btnAddProject" class="btn btn-primary col-auto">Thêm dự án</button>
                        <button id="data-bs-dismiss" class="btn btn-primary col-auto">Ẩn</button>
                        <button class="btn btn-danger col-auto" data-bs-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal" id="modalUPdateBlog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Sửa bài viết</h4>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Tên Bài Viết</label>
                        <div class="col">
                            <input type="text" class="form-control" id="inpNameBlog" placeholder="Đang cập nhật">
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Ảnh nền</label>
                        <div class="col">
                            <input id="inpThumBlog" type="file">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Giới thiệu</label>
                        <div class="col">
                            <textarea class="form-control" rows="4" cols="50" id="inpDescriptionBlog"
                                placeholder="Đang cập nhật"></textarea>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Nội dung</label>
                        <div class="col-sm-10">
                            <div id="editor"></div>
                        </div>
                    </div>
                    <div class="row d-flex justify-content-around">
                        <button id="btnConfirmUpdateBlog" class="btn btn-primary col-auto">Sửa</button>
                        <button id="btnConfirmAddUpdateBlog" class="btn btn-primary col-auto">Thêm dự án</button>
                        <button id="btnHidenTrueBlog" class="btn btn-primary col-auto">Ẩn</button>
                        <button class="btn btn-danger col-auto" data-bs-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- The Modal -->
    <!-- Content End -->

    <!-- Footer Start -->
    <div th:replace="~{fragments/footer :: footer}"></div>
    <!-- Footer End -->
    <script>
        var fileValue = null;
        var filesValue = [];
        var videoValue = null;
        var avatarValue = null;
        var imgThumBlogValue = null;
        FilePond.registerPlugin(
            FilePondPluginFileValidateType,
            FilePondPluginImagePreview,
            FilePondPluginImageResize,
            FilePondPluginImageTransform,

        );
        var inputThum = document.getElementById('inpImgThum');
        var imgThumFile = FilePond.create(inputThum, {
            labelIdle: 'Kéo & Thả hoặc Click để chọn tệp',
            labelFileProcessing: 'Đang xử lý tệp...',
            labelFileProcessingComplete: 'Xử lý hoàn tất',
            acceptedFileTypes: ['image/*'],
            imagePreviewMaxHeight: 300, // Chiều cao tối đa của hình ảnh trước
            imagePreviewMaxWidth: 300,  // Chiều rộng tối đa của hình ảnh trước
            onaddfile: function (error, item) {
                fileValue = item.file;
            },
            onremovefile: function (error, item) {
                fileValue = null;
            }
        });
        var inputDesc = document.getElementById("inpImgDesc");
        var imgDescFiles = FilePond.create(inputDesc, {
            allowMultiple: true,
            labelIdle: 'Kéo & Thả hoặc Click để chọn tệp',
            labelFileProcessing: 'Đang xử lý tệp... ',
            labelFileProcessingComplete: 'Xử lý hoàn tất',
            acceptedFileTypes: ['image/*'],
            imagePreviewMaxHeight: 300,
            imagePreviewMaxWidth: 300,
            onaddfile: function (error, item) {
                filesValue.push(item.file);
            },
            onremovefile: function (error, item) {
                const index = filesValue.indexOf(item.file);
                if (index > -1) {
                    filesValue.splice(index, 1);
                }
            },
        });
        var inputVideoThum = document.getElementById('inpVideoDesc');
        var videoPlayer = document.getElementById('videoPlayer');
        var videoThum = FilePond.create(inputVideoThum, {
            labelIdle: 'Kéo & Thả hoặc Click để chọn tệp',
            labelFileProcessing: 'Đang xử lý tệp...',
            labelFileProcessingComplete: 'Xử lý hoàn tất',
            acceptedFileTypes: ['video/*'],
            onaddfile: function (error, item) {
                videoPlayer.style.display = 'block';
                videoPlayer.src = URL.createObjectURL(item.file);
                videoPlayer.load();
                videoValue = item.file;
            },
            onremovefile: function (error, item) {
                videoPlayer.style.display = 'none';
                videoPlayer.src = "";
                videoValue = null;
            }
        });
        var inpAvatar = document.getElementById("inputAvatar")
        var avatarProfile = FilePond.create(inpAvatar,
            {
                labelIdle: 'Kéo & Thả hoặc Click để chọn tệp',
                acceptedFileTypes: ['image/*'],
                imagePreviewHeight: 170,
                imageCropAspectRatio: '1:1',
                imageResizeTargetWidth: 200,
                imageResizeTargetHeight: 200,
                stylePanelLayout: 'compact circle',
                styleLoadIndicatorPosition: 'center bottom',
                styleProgressIndicatorPosition: 'right bottom',
                styleButtonRemoveItemPosition: 'left bottom',
                styleButtonProcessItemPosition: 'right bottom',
                onaddfile: function (error, item) {
                    avatarValue = item.file;
                },
                onremovefile: function (error, item) {
                    avatarValue = null;
                }
            }
        );
        var inputThumBlog = document.getElementById('inpThumBlog');
        var imgThumBlog = FilePond.create(inputThumBlog, {
            labelIdle: 'Kéo & Thả hoặc Click để chọn tệp',
            labelFileProcessing: 'Đang xử lý tệp...',
            labelFileProcessingComplete: 'Xử lý hoàn tất',
            acceptedFileTypes: ['image/*'],
            imagePreviewMaxHeight: 300, // Chiều cao tối đa của hình ảnh trước
            imagePreviewMaxWidth: 300,  // Chiều rộng tối đa của hình ảnh trước
            onaddfile: function (error, item) {
                imgThumBlogValue = item.file;
            },
            onremovefile: function (error, item) {
                imgThumBlogValue = null;
            }
        });
        var vBlogContent = "";
        ClassicEditor
            .create(document.querySelector('#editor'), {
                ckfinder: {
                    uploadUrl: "/upload/ckfinder/images",
                }
            }).then(createdEditor => {
                vBlogContent = createdEditor;
            })
            .catch(error => {
                console.error(error);
            });
    </script>
    <script th:inline="javascript">
        var profileAvatar = /*[[${profile.avatar}]]*/ null;
        if (profileAvatar === null || profileAvatar === '') {
            avatarProfile.addFile('images/Customer/0/avatar.jpg');
        } else {
            avatarProfile.addFile(profileAvatar);
        }
    </script>
</body>

</html>