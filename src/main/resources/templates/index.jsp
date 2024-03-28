<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="/plugin/owlcarousel/assets/owl.carousel.min.css">
    <link rel="stylesheet" href="/plugin/owlcarousel/assets/owl.theme.default.min.css">
    <script src="/plugin/owlcarousel/owl.carousel.min.js"></script>
    <!-- Css Custom -->
    <link rel="stylesheet" href="/css/footer.css">
    <link rel="stylesheet" href="/css/index.css">
    <link rel="stylesheet" href="/css/loginRegister.css">
    
    <script type='text/javascript' src="/scripts/user.js"></script>
    <script type='text/javascript' src="/scripts/index.js"></script>
    <script type='text/javascript' src="/scripts/cart.js"></script>
</head>

<body>
    <!-- Header Start -->
    <div th:replace="~{fragments/header :: header}"></div>
    <!-- Header End -->
    <!-- Content Start -->
    <div class="container">
        <div name="carousel">
            <h2 class="text-warning">Dự án nhiệu lượt xem nhất >></h2>
            <div id="divTop10View" class="owl-carousel mt-3 mb-3">
                <div class="owl-stage-outer">
                    <div class="owl-stage">
                        <div class="owl-item snip" th:each="realEstate:${top10ViewRealestateList}"
                            th:href="@{'/property/' + ${realEstate.id}}">
                            <img th:if="${realEstate.photoMain!=null}" th:src="${realEstate.photoMain}">
                            <img th:if="${realEstate.photoMain==null}"
                                src="/images/Project/0/imgDesc/istockphoto-1409329028-170667a.jpg">
                            <div class="project-info  p-2 text-white bg-dark">
                                <a class="text-white" th:href="@{'/property/' + ${realEstate.id}}">
                                    <h4 th:if="${not #strings.isEmpty(realEstate.title)}" th:text="${realEstate.title}">
                                    </h4>
                                    <h4 th:if="${#strings.isEmpty(realEstate.title)}">Xem chi tiết</h4>
                                </a>
                                <div class="row">
                                    <div class="col">
                                        <h5><i class="fas fa-coins"></i>
                                            Giá: <span th:text="${realEstate.price+ ' VNĐ'}"></span>
                                        </h5>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <h5><i class="fas fa-map-marker-alt"></i> Địa chỉ: <span
                                                th:text="${realEstate.address}"></span></h5>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <h5><i class="fas fa-hand-holding-heart"></i> Nhu cầu:
                                            <span th:if="${realEstate.request==0}">Cần Bán</span>
                                            <span th:if="${realEstate.request==3}">Cho thuê</span>
                                        </h5>
                                    </div>
                                    <div class="col-md-6">
                                        <h5><i class="fas fa-info-circle"></i></i> Tình trạng:
                                            <span th:if="${realEstate.status}">Chờ giao dịch</span>
                                            <span th:unless="${realEstate.status}">Sẵn sàng giao dịch</span>
                                        </h5>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <h5><i class="far fa-eye"></i> Lượt xem: <span
                                                th:text="${realEstate.view}"></span>
                                        </h5>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-auto">
                                        <p><i class="fas fa-user"></i><span
                                                th:text="${' Người đăng: '+realEstate.customers.contactName}"></span>
                                        </p>
                                        <p><i class="fas fa-expand"></i><span
                                                th:text="${' Diện tích: '+realEstate.acreage+'m²'}"></span></p>
                                        <p><i class="fas fa-calendar"></i> Ngày đăng tin:
                                            <span class="date-value" th:data-value="${realEstate.dateCreate}"></span>
                                        </p>
                                    </div>
                                    <div class="col-auto">
                                        <p><i class="fas fa-bed"></i> Phòng ngủ <span
                                                th:text="${realEstate.bedroom}"></span></p>
                                        <p><i class="fas fa-bath"></i> Phòng tắm <span
                                                th:text="${realEstate.bathroom}"></span></p>
                                        <p><i class="fas fa-car"></i> Garage <span
                                                th:text="${realEstate.garage}"></span></p>
                                    </div>
                                </div>
                                <a class="detailProject text-center " th:href="@{'/property/' + ${realEstate.id}}"><i
                                        class="far fa-eye"></i> Xem chi tiết</a>
                                <a class="add-to-cart text-center" th:data-value="${realEstate.id}">Thêm vào giỏ hàng</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div name="newProject" style="overflow: auto;" class="mt-3 mb-3">
            <h2 class="text-warning">Dự án mới nhất >></h2>
            <figure class="snip" th:each="realEstate:${new6RealestateList}">
                <a class="text-white" th:href="@{'/property/' + ${realEstate.id}}">
                    <h4 th:if="${not #strings.isEmpty(realEstate.title)}" th:text="${realEstate.title}"></h4>
                    <h4 th:if="${#strings.isEmpty(realEstate.title)}">Xem chi tiết</h4>
                </a>
                <div class="image imgCarousel" th:data-id="${realEstate.id}">
                    <img th:if="${not #strings.isEmpty(realEstate.photoMain)}" th:src="${realEstate.photoMain}"
                        class="show" />
                    <img th:if="${#strings.isEmpty(realEstate.photoMain)}"
                        src="/images/Project/0/imgDesc/istockphoto-1409329028-170667a.jpg" class="show" />
                </div>
                <figcaption class="pl-1 pb-3">
                    <p><i class="fas fa-map-marker-alt"></i> Địa chỉ: <span th:text="${realEstate.address}"></span></p>
                    <p><i class="fas fa-hand-holding-heart"></i> Nhu cầu:
                        <span th:if="${realEstate.request==0}">CầnBán</span>
                        <span th:if="${realEstate.request==3}">Cho thuê</span>
                    </p>
                    <p><i class="fas fa-info-circle"></i></i> Tình trạng:
                        <span th:if="${realEstate.status}">Chờ giao dịch</span>
                        <span th:unless="${realEstate.status}">Sẵn sàng giao dịch</span>
                    </p>

                    <p><i class="fas fa-calendar"></i>
                        <span class="date-value" th:data-value="${realEstate.dateCreate}"></span>
                    </p>
                    <div class="row">
                        <div class="col-auto">
                            <p><i class="fas fa-user"></i> <span th:text="${realEstate.customers.contactName}"></span>
                            </p>
                            <p><i class="fas fa-expand"></i> <span th:text="${realEstate.acreage+'m²'}"></span></p>
                            <p><i class="fas fa-car"></i> <span th:text="${realEstate.garage}"></span></p>
                        </div>
                        <div class="col-auto">
                            <p><i class="fas fa-bed"></i> <span th:text="${realEstate.bedroom}"></span></p>
                            <p><i class="fas fa-bath"></i> <span th:text="${realEstate.bathroom}"> </span></p>
                            <p><i class="fas fa-coins"></i> <span class="price"
                                    th:text="${realEstate.price+' VNĐ'}"></span></p>
                        </div>
                    </div>
                </figcaption>
                <a class="detailProject text-center " th:href="@{'/property/' + ${realEstate.id}}"><i
                        class="far fa-eye"></i> Xem chi tiết</a>
                <a class="add-to-cart text-center" th:data-value="${realEstate.id}">Thêm vào giỏ hàng</a>
            </figure>
        </div>

        <div id="featuredProject" name="featuredProject" style="overflow: auto;" class="mt-3 ">
            <h2 class="text-warning">Dự án Tiêu biểu (<span th:text="${featuredRealestateListSize}"></span>) >></h2>
            <script id="featuredRealestate-template" type="text/x-handlebars-template">
                <figure class="snip">
                    <a class="text-white" href="/property/{{id}}">
                            {{#if title}}
                                <h4>{{title}}</h4>
                            {{else}}
                                <h4>Xem chi tiết</h4>
                            {{/if}}
                    </a>
                    <div class="image imgCarousel" data-id="{{id}}">
                        {{#if photoMain}}
                            <img src="{{photoMain}}" class="show" />
                        {{else}}
                            <img src="/images/Project/0/imgDesc/istockphoto-1409329028-170667a.jpg" class="show" />
                        {{/if}}
                    </div>
                    
                    <figcaption class="pl-1 pb-3">
                        <p><i class="fas fa-map-marker-alt"></i> Địa chỉ: <span>{{address}}</span></p>
                        <p><i class="fas fa-hand-holding-heart"></i> Nhu cầu:
                            <span class="request" data-request="{{request}}"></span>
                        </p>
                        <p><i class="fas fa-info-circle"></i> Tình trạng:
                            {{#if status}}
                                <span>Chờ giao dịch</span>
                            {{else}}
                                <span>Sẵn sàng giao dịch</span>
                            {{/if}}
                        </p>
                    
                        <p><i class="fas fa-calendar"></i>
                            <span class="date-value" data-value="{{dateCreate}}"></span>
                        </p>
                        <div class="row">
                            <div class="col-auto">
                                <p><i class="fas fa-user"></i> <span>{{customers.contactName}}</span></p>
                                <p><i class="fas fa-expand"></i> <span>{{acreage}}m²</span></p>
                                <p><i class="fas fa-car"></i> <span>{{garage}}</span></p>
                            </div>
                            <div class="col-auto">
                                <p><i class="fas fa-bed"></i> <span>{{bedroom}}</span></p>
                                <p><i class="fas fa-bath"></i> <span>{{bathroom}}</span></p>
                                <p><i class="fas fa-coins"></i> <span class="price">{{price}} VNĐ</span></p>
                            </div>
                        </div>
                    </figcaption>
                    <a class="detailProject text-center " href="/property/{{id}}"><i
                            class="far fa-eye"></i> Xem chi tiết</a>
                    <a class="add-to-cart text-center" data-value="{{id}}">Thêm vào giỏ hàng</a>
                </figure>
                </script>
        </div>
        <div id="paginationFeaturedProject" class="mb-3">
            <nav>
                <ul id="paginationFeaturedRealEstate" class="pagination justify-content-center">
                    <li class="page-item ">
                        <a class="page-link" id="prePagination">&#10094;&#10094;</a>
                    </li>
                    <li th:each="featuredRealEstatePage:${pageNumbers}" class="page-item">
                        <a class="featuredRealEstate-page page-link " th:data-page="${featuredRealEstatePage}"
                            th:text="${featuredRealEstatePage}"></a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" id="nextPagination">&#10095;&#10095;</a>
                    </li>
                </ul>
            </nav>
        </div>
        <div>
            <h2 class="text-warning mb-3">Người đăng uy tín>></h2>
            <div th:each="property : ${agenList}" th:if="${propertyStat.index % 3 == 0 or propertyStat.first}"
                class="row mb-4 mt-4">
                <div th:each="card : ${agenList}"
                    th:if="${cardStat.index >= propertyStat.index and cardStat.index < propertyStat.index + 3}"
                    class="col-md-4">
                    <div class="card">
                        <div class="row no-gutters">
                            <div class="col-lg-4 d-flex align-items-center">
                                <img th:if="${not #strings.isEmpty(card.avatar)}" th:src="${card.avatar}"
                                    class="card-img" alt="Customer Avatar">
                                <img th:if="${#strings.isEmpty(card.avatar)}" src="/images/Customer/0/avatar.jpg"
                                    class="card-img" alt="Default Avatar">
                            </div>
                            <div class="col-lg-8">
                                <div class="card-body">
                                    <a class="text-dark" th:href="@{'/property?agent=' + ${card.id}}">
                                        <h5 class="card-title"><i class="fas fa-user"></i> <span
                                                th:text="${card.contactName}"></span></h5>
                                    </a>
                                    <p><i class="fas fa-calendar"></i>Ngày tham gia: <span class="date-value"
                                            th:data-value="${card.createdAt}"></span></p>
                                    <p><i class="fas fa-building"></i>Dự án đã đăng: <span class="projectCount"
                                            th:data-value="${card.id}"></span></p>
                                    <p><i class="fas fa-shopping-cart"></i>Dự án đã giao dịch: <span
                                            class="projectBuyCount" th:data-value="${card.id}"></span></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <h2 class="text-warning mb-3">Bài Viết &#10095;&#10095;</h2>
            <div id="divBlog" class="owl-carousel mt-3 mb-3">
                <div class="owl-stage-outer">
                    <div class="owl-stage">
                        <div class="owl-item " th:each="blog:${blogs}">
                            <div class="card ml-1 mr-1">
                                <img th:src="${#strings.isEmpty(blog.photoMain)} ? '/images/Blog/0/istockphoto-1409329028-170667a.jpg' : ${blog.photoMain}" class="card-img-top" alt="Card Image">
                                <div class="card-body d-flex flex-column">
                                    <h5 class="card-title" th:text="${blog.name}"></h5>
                                    <p class="card-text mb-4" th:text="${blog.description}"></p>
                                    <a th:href="@{'/blog/' + ${blog.id}}"
                                        class="btn btn-primary text-white mt-auto align-self-start">Xem chi tiết</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Content End -->
    <div th:replace="~{fragments/cart}"></div>
    <!-- Footer Start -->
    <div th:replace="~{fragments/footer :: footer}"></div>
    <!-- Footer End -->
</body>

</html>