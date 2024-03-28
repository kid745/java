<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bài Viế</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <!-- Css Custom -->
    <link rel="stylesheet" href="/css/footer.css">
    <link rel="stylesheet" href="/css/loginRegister.css">
    <link rel="stylesheet" href="/css/blog.css">

    <script type='text/javascript' src="/scripts/user.js"></script>
</head>

<body>
    <!-- Header Start -->
    <div th:replace="~{fragments/header :: header}"></div>
    <!-- Header End -->

    <div class="container">
        <h2 class="text-warning pt-5 pb-5">Danh sách bài viết (<span th:text="${countBlog}"></span>) ❯❯</h2>
        <div class="blogList">
            <div th:each="blog:${blogList}" class="card ml-1 mr-1">
                <img th:src="${blog.photoMain}" class="card-img-top" alt="Card Image">
                <div class="card-body d-flex flex-column">
                    <h5 class="card-title text-truncate" th:text="${blog.name}"></h5>
                    <p class="card-text mb-4 card-text-description" th:text="${blog.description}"></p>
                    <a th:href="@{'/blog/' + ${blog.id}}"
                        class="btn btn-primary text-white mt-auto align-self-start">Xem chi tiết</a>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer Start -->
    <div th:replace="~{fragments/footer :: footer}"></div>
    <!-- Footer End -->
</body>

</html>