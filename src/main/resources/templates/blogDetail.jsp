<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bài Viết</title>
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
    <link rel="stylesheet" href="/css/blogDetail.css">

    <script type='text/javascript' src="/scripts/user.js"></script>
</head>

<body>
    <!-- Header Start -->
    <div th:replace="~{fragments/header :: header}"></div>
    <!-- Header End -->
    <div class="container">
        <div class=" mt-5 text-center">
            <h2 class="blog-name" th:text="${blog.name}"></h2>
            <p class="blog-info">
                <i class="far fa-eye"></i> Lượt xem: <span th:text="${blog.view}"></span> |
                <i class="fas fa-calendar"></i> Ngày đăng: <span class="date-value"
                    th:data-value="${blog.dateCreate}"></span> |
                <i class="fas fa-user"></i> Người đăng: <span th:text="${blog.customers.contactName}"></span>
            </p>
        </div>
        <div class="bg-light" th:text="${blog.content}">
        </div>
    </div>



    <!-- Footer Start -->
    <div th:replace="~{fragments/footer :: footer}"></div>
    <!-- Footer End -->
</body>

</html>