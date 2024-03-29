<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin</title>
    <!-- AdminLTE 3 CSS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/3.1.0/css/adminlte.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.4.2/css/buttons.bootstrap4.min.css">
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
</head>

<body>
    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
        <!-- Left navbar links -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a href="index3.html" class="nav-link">Trang chủ</a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a href="#" class="nav-link">Bài Viết</a>
            </li>
        </ul>
        <!-- Right navbar links -->
        <ul class="navbar-nav ml-auto">
            <!-- Navbar Search -->
            <li class="nav-item">
                <a class="nav-link" data-widget="navbar-search" href="#" role="button">
                    <i class="fas fa-search"></i>
                </a>
                <div class="navbar-search-block">
                    <form class="form-inline">
                        <div class="input-group input-group-sm">
                            <input class="form-control form-control-navbar" type="search" placeholder="Search"
                                aria-label="Search">
                            <div class="input-group-append">
                                <button class="btn btn-navbar" type="submit">
                                    <i class="fas fa-search"></i>
                                </button>
                                <button class="btn btn-navbar" type="button" data-widget="navbar-search">
                                    <i class="fas fa-times"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </li>
            <!-- Notifications Dropdown Menu -->
        </ul>
    </nav>
    <!-- /.navbar -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <a href="/admin/project" class="brand-link">
            <img src="/images/logo/AdminLTELogo.png" class="brand-image img-circle elevation-3" style="opacity: .8">
            <span class="brand-text font-weight-light">Ironhack</span>
        </a>

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar user panel (optional) -->
            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                <div class="image">
                    <img href="/my-profile" th:if="${profile.avatar==null or profile.avatar==''}"
                        src="/images/Customer/0/avatar.jpg" class="img-circle elevation-2"
                        style="height: 3rem;width: 3rem;object-fit: cover;">
                    <img href="/my-profile" th:if="${profile.avatar!=null and profile.avatar!=''}"
                        th:src="${profile.avatar}" class="img-circle elevation-2"
                        style="height: 3rem;width: 3rem;object-fit: cover;">
                </div>
                <div class="info">
                    <a href="/my-profile" class="d-block" th:text="${profile.contactName}"></a>
                </div>
            </div>
            <!-- Sidebar Menu -->
            <nav class="mt-2">
                <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                    data-accordion="false">
                    <li class="nav-item">
                        <a href="/admin/project" class="nav-link">
                            <i class="nav-icon fas fa-building"></i>
                            <p>Dự án</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/admin/account" class="nav-link ">
                            <i class="nav-icon fas fa-list"></i>
                            <p>Tài Khoản</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/admin/comment" class="nav-link ">
                            <i class=" nav-icon fas fa-comment"></i>
                            <p>Bình Luận</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/admin/blog" class="nav-link active">
                            <i class="fas fa-newspaper"></i>
                            <p>Bài Viết</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/admin/email" class="nav-link">
                            <i class="fas fa-envelope"></i>
                            <p>Gửi mail</p>
                        </a>
                    </li>
                </ul>
            </nav>
            <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->
    </aside>
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0">Bài Viết</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="/admin/project">Trang chủ</a></li>
                            <li class="breadcrumb-item active">Bài Viết</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->

        <!-- Main content -->
        <div class="content">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Danh sách bài viết</h3>
                </div>

                <table id="tableBlog" class="table table-bordered table-striped table-hover text-center">
                    <thead>
                        <tr>
                            <th>Mã Bài Viết</th>
                            <th>Hành Động</th>
                            <th>Tên Bài Viết</th>
                            <th>Người Đăng</th>
                            <th>Trạng Thái</th>
                            <th>Lượt xem</th>
                            <th>Cập Nhật Bởi</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <!-- /.card-body -->
            </div>
        </div>
        <!-- /.content -->
    </div>
    <!-- The Modal -->
    <div class="modal" id="modalUPdateBlog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Sửa bài viết</h4>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Launch demo modal
                      </button>
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
                    <div class="mb-3 row" id="divReason">
                        <label class="col-sm-2 col-form-label">Lý do</label>
                        <div class="col-sm-10">
                            <textarea rows="4" cols="50" style="background: none;" id="inpReason" class="form-control custom-input" placeholder="Lý do không xác nhận"></textarea>
                        </div>
                    </div>
                    <div class="row d-flex justify-content-around">
                        <button id="btnApprove" class="btn btn-primary col-auto">Duyệt</button>
                        <button id="btnUnApprove" class="btn btn-warning col-auto">Bỏ Duyệt</button>
                        <button class="btn btn-danger col-auto" data-bs-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- The Modal -->
    <script>
        var imgThumBlogValue = null;
        FilePond.registerPlugin(
            FilePondPluginFileValidateType,
            FilePondPluginImagePreview,
            FilePondPluginImageResize,
            FilePondPluginImageTransform,
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap4.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.4.2/js/dataTables.buttons.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.bootstrap4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.html5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.print.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.colVis.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/3.1.0/js/adminlte.min.js"></script>
    <!-- Css -->
    <link rel="stylesheet" type="text/css" href="/css/myProfile.css">
    <script type='text/javascript' src="/scripts/blogAdmin.js"></script>
</body>

</html>