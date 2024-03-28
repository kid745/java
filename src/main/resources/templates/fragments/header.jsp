<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a href="/index" class="navbar-brand">
                <img width="180" height="44" src="/images/logo/logo.png" alt="Real Estate Salient Pro">
            </a>
            <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#myNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item" style="height: 45px;display: flex;align-items: center;">
                        <a href="/index" aria-current="page" class="nav-link" ><span
                                class="glyphicon glyphicon-home"></span> Trang chủ</a>
                    </li>
                    <li class="nav-item" style="height: 45px;display: flex;align-items: center;">
                        <a href="/property" class="nav-link"><span class="glyphicon glyphicon-list" style="height: 45px;"></span>
                            Dự án</a>
                    </li>
                    <li class="nav-item" style="height: 45px;display: flex;align-items: center;">
                        <a href="/blog" class="nav-link"><span class="glyphicon glyphicon-list" style="height: 45px;"></span>
                            Bài viết</a>
                    </li>
                    <li id="menu-login" class="nav-item dropdown" style="height: 45px;display: flex;align-items: center;">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-user"></i>Tài Khoản<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" data-toggle="modal" data-target="#loginModal"><i class="fa fa-sign-in"></i> Đăng Nhập</a></li>
                            <li><a class="dropdown-item" data-toggle="modal" data-target="#registerModal"><i class="fas fa-address-card"></i> Đăng Ký</a></li>
                            <li><a class="dropdown-item" data-toggle="modal" data-target="#forgotModal"><i class="fas fa-exclamation-circle"></i> Quên Mật Khẩu</a></li>
                        </ul>
                    </li>
                    <li id="menu-user" class="nav-item dropdown" style="height: 45px;display: flex;align-items: center;">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown">
                            <img id="menu-avatar" class="rounded-circle" style="height: 45px;width: 45px;object-fit: cover;">
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/my-profile" class="dropdown-item"><i class="fa fa-sign-in"></i>Thông tin tài khoản</a></li>
                            <li><a href="/my-property" class="dropdown-item"><i class="fas fa-address-card"></i>Dự án đã đăng</a></li>
                            <li><a href="/my-cart" class="dropdown-item"><i class="fas fa-address-card"></i>Giỏ hàng</a></li>
                            <li><a href="/logout" class="dropdown-item"><i class="fas fa-address-card"></i>Đăng xuất</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- login modal -->
    <div id="loginModal" class="modal-style-1  modal " role="dialog">
        <div class="modal-dialog modal-login">
            <div class="modal-content">
                <div class="modal-header p-0 mb-3 mt-3">
                    <h4 class="modal-title">Đăng nhập</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <!-- dont forget to add action and action method  -->
                    <form action="" method="">
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                <input id="inpLoginUsername" type="text" class="form-control" name="user" placeholder="Nhập tên đăng nhập "
                                    required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                <input id="inpLoginPassword" type="password" class="form-control" name="password" placeholder="Nhập mật khẩu"
                                    required="required" autocomplete="on">
                                <span id="showPassword"><i class="fa fa-eye"></i></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <span id="spanWarning" style="color: red;"></span>
                        </div>
                        <div class="row pl-1 pr-1">
                            <div class="col text-left">
                                <label class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="item_checkbox"
                                        name="item_checkbox" value="option1">
                                    <span class="custom-control-label">&nbsp;Nhớ tài khoản</span>
                                </label>
                            </div>
                            <div class="col text-right hint-text pt-0">
                                <a class="text-danger" id="aForgot" >Quên mật khẩu?</a>
                            </div>
                        </div>
                        <div class="form-group text-center">
                            <button id="btnLogin"  class="btn btn-primary btn-signin">Đăng nhập</button>
                        </div>
                    </form>
                </div>
                <div class="text-center mb-3">Chưa có tài khoản? <a class="register" id="aRegister"  >Đăng Ký</a></div>
            </div>
        </div>
    </div>

    <!-- register modal  -->
    <div id="registerModal" class="modal-style-1  modal " role="dialog">
        <div class="modal-dialog modal-login">
            <div class="modal-content">
                <div class="modal-header p-0 mb-3 mt-3">
                    <h4 class="modal-title text-center">Đăng Ký</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <!-- dont forget to add action and action method  -->
                    <form action="" method="">
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input type="text" class="form-control" name="user" placeholder="Nhập tên đăng nhập"
                                    required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                <input type="email" class="form-control" name="email" placeholder="Nhập email"
                                    required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                <input type="password" class="form-control" name="password" placeholder="Nhập mật khẩu"
                                    required="required" autocomplete="on">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-eye-slash"></i></span>
                                <input type="password" class="form-control" name="password_confirmation"
                                    placeholder="Nhập lại mật khẩu" required="required" autocomplete="on">
                            </div>
                        </div>
                        <div class="form-group text-center">
                            <button class="btn btn-primary btn-signin">Đăng ký</button>
                        </div>
                    </form>
                </div>
                <div class="text-center mb-3">Đẵ có tài khoản? <a class="login aLogin">Đăng Nhập</a></div>
            </div>
        </div>
    </div>

    <!-- forgot password input email -->
    <div id="forgotModal" class="modal-style-1  modal " role="dialog">
        <div class="modal-dialog modal-login">
            <div class="modal-content">
                <div class="modal-header p-0 mb-3 mt-3">
                    <h4 class="modal-title text-center">Quên mật khẩu</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <!-- dont forget to add action and action method  -->
                    <form action="" method="">
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                <input type="text" class="form-control" name="user" placeholder="Nhập tên đăng nhập"
                                    required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                <input type="email" class="form-control" name="email" placeholder="Nhập email">
                            </div>
                        </div>
                        <div class="form-group text-center">
                            <button class="btn btn-primary btn-signin">Quên mật khẩu</button>
                        </div>
                    </form>
                </div>
                <div class="text-center mb-3">Có tài khoản? <a class="login aLogin" >Đăng nhập</a></div>
            </div>
        </div>
    </div>
    <!-- forgot confirm password  -->
    <div id="forgotModalConfirm" class="modal-style-1  modal " role="dialog">
        <div class="modal-dialog modal-login">
            <div class="modal-content">
                <div class="modal-header p-0 mb-3 mt-3">
                    <h4 class="modal-title text-center">Quên mật khẩu</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <!-- dont forget to add action and action method  -->
                    <form action="" method="">
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                <input type="password" class="form-control" name="password" placeholder="Nhập mật khẩu"
                                    required="required" autocomplete="on">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-eye-slash"></i></span>
                                <input type="password" class="form-control" name="password_confirmation"
                                    placeholder="Nhập lại mật khẩu" required="required" autocomplete="on">
                            </div>
                        </div>
                        <div class="form-group text-center">
                            <button type="submit" class="btn btn-primary btn-signin">Quên mật khẩu</button>
                        </div>
                    </form>
                </div>
                <div class="text-center mb-3">Có tài khoản? <a class="login aLogin">Đăng nhập</a></div>
            </div>
        </div>
    </div>
</header>