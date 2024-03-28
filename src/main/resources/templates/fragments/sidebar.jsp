<button id="fixed_sidebar" class="btn" data-bs-toggle="offcanvas" data-bs-target="#sidebar">
    &#127530;<span class="tooltip">Phân loại</span>
</button>
<div class="offcanvas offcanvas-start" tabindex="-1" id="sidebar" data-bs-keyboard="false" data-bs-backdrop="false">
    <div class="offcanvas-header">
        <h6 class="offcanvas-title d-none d-sm-block" id="sidebar_header">Phân Loại</h6>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body px-0">
        <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-start" id="sidebar_menu">
            <li class="nav-item">
                <a class="nav-link text-dark">
                    <i class="fs-5 bi-house"></i><span class="ms-1 d-none d-sm-inline">Kiểu dự án</span>
                </a>
                <div class="inpItem ms-3">
                    <div class="ms-3">
                        <input id="inpTypeProject" type="radio" name="typeProject" checked class="form-check-input"
                            value="">
                        <label class="form-check-label" for="typeProject">Tất cả</label>
                    </div>
                    <div class=" ms-3">
                        <input id="typeProject1" type="radio" name="typeProject" class="form-check-input" value=1>
                        <label class="form-check-label" for="typeProject1">Nhà Đất</label>
                    </div>
                    <div class="ms-3">
                        <input id="typeProject2" type="radio" name="typeProject" class="form-check-input" value="2">
                        <label class="form-check-label" for="typeProject2">Chung cư</label>
                    </div>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link text-dark">Nhu cầu</a>
                <div class="inpItem ms-3">
                    <div class="ms-3">
                        <input id="requestProject" type="radio" name="requestProject" checked class="form-check-input"
                            value="">
                        <label class="form-check-label" for="requestProject0">Tất cả</label>
                    </div>
                    <div class="ms-3">
                        <input id="requestProject0" type="radio" name="requestProject" class="form-check-input"
                            value="0">
                        <label class="form-check-label" for="requestProject0">Bán</label>
                    </div>
                    <div class="ms-3">
                        <input id="requestProject3" type="radio" name="requestProject" class="form-check-input"
                            value="3">
                        <label class="form-check-label" for="requestProject3">Thuê</label>
                    </div>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link text-dark">Giá tiền</a>
                <div class="inpItem ms-3">
                    <div class="input-group">
                        <span class="input-group-text">Tối thiểu</span>
                        <input type="number" class="form-control" id="minPrice" min="0" inputmode="numeric"
                            pattern="\d*">
                    </div>
                    <div class="input-group me-4">
                        <span class="input-group-text">Tối đa</span>
                        <input type="number" class="form-control" id="maxPrice" min="0" inputmode="numeric"
                            pattern="\d*">
                    </div>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link text-dark">Diện tích</a>
                <div class="inpItem ms-3">
                    <div class="input-group">
                        <span class="input-group-text">Tối thiểu</span>
                        <input type="number" class="form-control" id="minAcreage" min="0" inputmode="numeric"
                            pattern="\d*">
                    </div>
                    <div class="input-group me-4">
                        <span class="input-group-text">Tối đa</span>
                        <input type="number" class="form-control" id="maxAcreage" min="0" inputmode="numeric"
                            pattern="\d*">
                    </div>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link text-dark">Phòng ngủ</a>
                <div class="inpItem ms-3">
                    <div class="input-group">
                        <span class="input-group-text ">Tối thiểu</span>
                        <input type="number" class="form-control" id="minBedroom" min="0" inputmode="numeric"
                            pattern="\d*">
                    </div>
                    <div class="input-group me-4">
                        <span class="input-group-text">Tối đa</span>
                        <input type="number" class="form-control" id="maxBedroom" min="0" inputmode="numeric"
                            pattern="\d*">
                    </div>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link text-dark">Phòng tắm</a>
                <div class="inpItem ms-3">
                    <div class="input-group">
                        <span class="input-group-text">Tối thiểu</span>
                        <input type="number" class="form-control" id="minBathroom" min="0" inputmode="numeric"
                            pattern="\d*">
                    </div>
                    <div class="input-group me-4">
                        <span class="input-group-text">Tối đa</span>
                        <input type="number" class="form-control" id="maxBathroom" min="0" inputmode="numeric"
                            pattern="\d*">
                    </div>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link text-dark">Garage</a>
                <div class="inpItem ms-3">
                    <div class="input-group">
                        <span class="input-group-text">Tối thiểu</span>
                        <input type="number" class="form-control" id="minGarage" min="0" inputmode="numeric"
                            pattern="\d*">
                    </div>
                    <div class="input-group me-4">
                        <span class="input-group-text">Tối đa</span>
                        <input type="number" class="form-control" id="maxGarage" min="0" inputmode="numeric"
                            pattern="\d*">
                    </div>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link text-dark">Tình trạng</a>
                <div class="inpItem ms-3">
                    <div class="ms-3">
                        <input id="statusProject" type="radio" name="statusProject" checked class="form-check-input"
                            value="">
                        <label class="form-check-label" for="statusProject">Tất cả</label>
                    </div>
                    <div class="ms-3">
                        <input id="" type="radio" name="statusProject" class="form-check-input" value="0">
                        <label class="form-check-label" for="statusProject2">Sẵn sàng giao dịch</label>
                    </div>
                    <div class="ms-3">
                        <input id="statusProject1" type="radio" name="statusProject" class="form-check-input" value="1">
                        <label class="form-check-label" for="statusProject1">Chờ giao dịch</label>
                    </div>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link text-dark">Khu vực</a>
                <div class="inpItem ms-3">
                    <div class="input-group">
                        <label class="input-group-text">Khu vực</label>
                        <select class="form-select" id="sltProvince">
                            <option value selected>Khu vực</option>
                        </select>
                    </div>
                </div>
            </li>
            <li class="nav-item mt-3">
                <button class="btn btn-outline-primary ms-4" id="btnSearchSidebar"><i class="fas fa-search"></i> Tìm
                    kiếm</button>
            </li>
        </ul>
    </div>
</div>