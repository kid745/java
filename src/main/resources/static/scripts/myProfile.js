$(document).ready(function () {
    var gIdProject = 0;
    var gUrlMyProfile = "http://localhost:8080/";
    var gIdBlog = 0;
    const gUserId = $("#divInfoUser").data("iduser");
    const gPriceInput = new AutoNumeric('#inpPrice', {
        currencySymbol: ' VNĐ',
        decimalCharacter: '.',
        digitGroupSeparator: ',',
        currencySymbolPlacement: 's',
        modifyValueOnWheel: false,
        decimalPlaces: 0,
        decimalPlacesShownOnBlur: 0,
    });
    //vùng sự kiện
    $("#btnUpdateUser").on("click", function () {
        onBtnUpdateUserClick();
        $("#btnUpdateUser").prop("disabled", true);
        $("#btnSaveUser").prop("disabled", false);
    });
    $("#btnSaveUser").on("click", function () {
        onBtnSaveUserClick();
        $("#btnUpdateUser").prop("disabled", false);
        $("#btnSaveUser").prop("disabled", true);
    });
    $(".card-action").on("click", function () {
        $("#btnAddProject").addClass("d-none");
        $("#btnUpdateProject").removeClass("d-none");
        $("#modalUpdateProject").modal("show");
        gIdProject = $(this).data("value");
        postDataProjectToInput(gIdProject);
    });
    $("#btnShowAddModalProject").on("click", function () {
        $("#btnUpdateProject").addClass("d-none");
        $("#btnAddProject").removeClass("d-none");
        resetModal();
        $("#modalUpdateProject").modal("show");
    })
    $("#btnAddProject").on("click", function () {
        btnAddProjectClick();
    });
    $("#btnUpdateProject").on("click", function () {
        btnUpdateProjectClick();
    });
    $("#btnModalUpdateAvatar").on("click", function () {
        $("#modalUploadFileAvatar").modal("show");
    })
    $("#btnUpdateAvatar").on("click", function () {
        uploadAvatarFile();
    })
    $("#sltProvince").on("change", function () {
        getDistrict();
    })
    $("#sltDistrict").on("change", function () {
        getWardAndStreet($("#sltDistrict").val())
    });
    $(".showModalBlog").on("click", function () {
        gIdBlog = $(this).data("value");
        $("#btnConfirmUpdateBlog").show();
        $("#btnConfirmAddUpdateBlog").hide();
        onShowModalBlogClick(gIdBlog);
    });
    $("#btnAddBlog").on("click", function () {
        $("#btnConfirmUpdateBlog").hide();
        $("#btnConfirmAddUpdateBlog").show();
        resetModal();
    });
    $("#btnConfirmAddUpdateBlog").on("click", function () {
        onBtnConfirmAddUpdateBlogClick();
    });
    $("#btnConfirmUpdateBlog").on("click", function () {
        onBtnConfirmUpdateBlogClick();
    });

    //Vùng khai hàm xử lý sự kiện    
    function onBtnUpdateUserClick() {
        $("#divInfoUser input, #divInfoUser textarea").removeAttr("readonly");
        $("#divInfoUser input, #divInfoUser textarea").attr("disabled", false);
    }
    function onBtnSaveUserClick() {
        $("#divInfoUser input, #divInfoUser textarea").attr("readonly", true);
        var vUser = {
            id: gUserId,
            contactName: "",
            email: "",
            mobile: "",
            address: "",
            note: ""
        }
        getUserData(vUser);
        var vResult = validateUser(vUser);
        if (vResult) {
            var formData = new FormData();
            formData.append('customer', new Blob([JSON.stringify(vUser)], {
                type: "application/json"
            }));
            if (avatarValue != null) {
                formData.append("avatar", blobToFile(avatarValue));
            }
            else {
                formData.append("avatar", avatarValue);
            }
            fetch('/upload/exitUser', {
                method: 'post',
                body: formData
            }).then(function (response) {
                if (response.status !== 200) {
                    alert("Cập nhật thông tin không thành công!");
                } else {
                    alert("Cập nhật thông tin thành công");
                    location.reload();
                }
            }).catch(function (err) {
                alert("Hệ thống lỗi!");
            });
        }
    }
    function btnAddProjectClick() {
        var vProject = {
            id: "",
            type: 0,
            title: "",
            request: 0,
            ward: { id: "", district: { id: "" }, province: "" },
            customers: { id: gUserId },
            streetId: 0,
            address: "",
            price: 0,
            acreage: 0,
            bedroom: 0,
            garage: 0,
            bathroom: 0,
            description: ""
        }
        getDataProject(vProject);
        var vResult = validateProject(vProject);
        if (vResult) {
            var formData = new FormData();
            formData.append('realestate', new Blob([JSON.stringify(vProject)], {
                type: "application/json"
            }));
            if (fileValue != null) {
                formData.append("file", blobToFile(fileValue));
            }
            if (filesValue.length > 0) {
                for (var i = 0; i < filesValue.length; i++) {
                    formData.append("files", blobToFile(filesValue[i]));
                }
            }
            if (videoValue != null) {
                formData.append("video", blobToFile(videoValue));
            }

            fetch('/upload/newproject', {
                method: 'post',
                body: formData
            }).then(function (response) {
                if (response.status !== 200) {
                    alert("Thêm dự án thất bại! Vui lòng kiểm tra đẩy đủ thông tin và đảm bảo dung lương các file gửi lên không nhiều hơn 100Mb");
                } else {
                    alert("Thêm dự án thành công");
                    location.reload();
                }
            }).catch(function (err) {
                alert("Hệ thống lỗi!");
            });
        }

    }
    function btnUpdateProjectClick() {
        var vProject = {
            id: "",
            type: 0,
            title: "",
            request: 0,
            ward: { id: "", district: { id: "" }, province: "" },
            streetId: 0,
            address: "",
            price: 0,
            acreage: 0,
            bedroom: 0,
            garage: 0,
            bathroom: 0,
            description: ""
        }
        getDataProject(vProject);
        var vResult = validateProject(vProject);
        if (vResult) {
            var formData = new FormData();
            formData.append('realestate', new Blob([JSON.stringify(vProject)], {
                type: "application/json"
            }));
            if (fileValue != null) {
                formData.append("file", blobToFile(fileValue));
            }
            if (filesValue.length > 0) {
                for (var i = 0; i < filesValue.length; i++) {
                    formData.append("files", blobToFile(filesValue[i]));
                }
            }
            if (videoValue != null) {
                formData.append("video", blobToFile(videoValue));
            }
            fetch('/upload/exitsproject', {
                method: 'post',
                body: formData
            }).then(function (response) {
                if (response.status !== 200) {
                    alert("Cập nhật thất bại! Vui lòng kiểm tra đẩy đủ thông tin và đảm bảo dung lương các file gửi lên không nhiều hơn 100Mb");
                } else {
                    alert("Cập nhật thành công");
                    location.reload();
                }
            }).catch(function (err) {
                alert("Hệ thống lỗi");
            });
        }
    }
    function onBtnConfirmAddUpdateBlogClick() {
        var vBlog = {
            name: "",
            description: "",
            content: "",
            customers: { id: gUserId },
        }
        getDataBlogInModal(vBlog);
        var vResult = validateBlog(vBlog);
        if (vResult) {
            var formData = new FormData();
            formData.append('blog', new Blob([JSON.stringify(vBlog)], {
                type: "application/json"
            }));
            if (imgThumBlogValue != null) {
                formData.append("imgThumBlog", blobToFile(imgThumBlogValue));
            }

            fetch('/upload/newblog', {
                method: 'post',
                body: formData
            }).then(function (response) {
                alert("Cập nhật thành công");
                location.reload();
            }).catch(function (err) {
                alert("Cập nhật thất bại! Vui lòng kiểm tra đẩy đủ thông tin và đảm bảo dung lương các file gửi lên không nhiều hơn 100Mb");
            });
        }
    }
    function onBtnConfirmUpdateBlogClick() {
        var vBlog = {
            id: gIdBlog,
            name: "",
            description: "",
            content: "",
            customers: { id: gUserId },
        }
        getDataBlogInModal(vBlog);
        var vResult = validateBlog(vBlog);
        if (vResult) {
            var formData = new FormData();
            formData.append('blog', new Blob([JSON.stringify(vBlog)], {
                type: "application/json"
            }));
            if (imgThumBlogValue != null) {
                formData.append("imgThumBlog", blobToFile(imgThumBlogValue));
            }

            fetch('/upload/exitblog', {
                method: 'post',
                body: formData
            }).then(function (response) {
                alert("Cập nhật thành công");
                location.reload();
            }).catch(function (err) {
                alert("Cập nhật thất bại! Vui lòng kiểm tra đẩy đủ thông tin và đảm bảo dung lương các file gửi lên không nhiều hơn 100Mb");
            });
        }
    }
    function onShowModalBlogClick(paramId) {
        $.get(gUrlMyProfile + "blog/" + paramId, function (data) {
            postDataBlogToModal(data)
        });
    }
    //vùng khai báo hàm dùng chung
    function getDistrict(callback) {
        var vIdProvince = $("#sltProvince").val();
        $.get("http://localhost:8080/province/" + vIdProvince + "/district").then(function (districts) {
            $("#sltDistrict option").remove();
            $("#sltDistrict").append($("<option>", {
                value: "",
                text: "Chọn Quận"
            }).attr("disabled", true).attr("selected", true));
            $("#sltStreet option").remove();
            $("#sltStreet").append($("<option>", {
                value: "",
                text: "Chọn Đường"
            }));
            $("#sltWard option").remove();
            $("#sltWard").append($("<option>", {
                value: "",
                text: "Chọn Phường"
            }).attr("disabled", true).attr("selected", true));
            districts.forEach(function (district) {
                $("#sltDistrict").append($("<option>", {
                    value: district.id,
                    text: district.prefix + " " + district.name,
                }));
            });

            if (typeof callback === "function") {
                callback();
            }
        });
    }
    function getWardAndStreet(districtId, wardId, streetId) {
        $.get("http://localhost:8080/province/district/" + districtId + "/street").then(function (streets) {
            $("#sltStreet option").remove();
            $("#sltStreet").append($("<option>", {
                value: "",
                text: "Chọn Đường"
            }).attr("disabled", true).attr("selected", true));
            streets.forEach(function (street) {
                $("#sltStreet").append($("<option>", {
                    value: street.id,
                    text: street.prefix + " " + street.name,
                }));
            });

            if (streetId) {
                $("#sltStreet").val(streetId);
            }
        });

        $.get("http://localhost:8080/province/district/" + districtId + "/ward").then(function (wards) {
            $("#sltWard option").remove();
            $("#sltWard").append($("<option>", {
                value: "",
                text: "Chọn Phường"
            }).attr("disabled", true).attr("selected", true));
            wards.forEach(function (ward) {
                $("#sltWard").append($("<option>", {
                    value: ward.id,
                    text: ward.prefix + " " + ward.name,
                }));
            });

            if (wardId) {
                $("#sltWard").val(wardId);
            }
        });
    }
    function getUserData(paramUser) {
        paramUser.contactName = $("#inpFullName").val();
        paramUser.email = $("#inpEmail").val();
        paramUser.mobile = $("#inpMobie").val();
        paramUser.address = $("#inpUserAddress").val();
        paramUser.note = $("#inpUserNote").val();
    }
    function getDataProject(paramProject) {
        paramProject.id = gIdProject;
        paramProject.title = $("#inpTitle").val();
        paramProject.request = $("#sltRequest").val();
        paramProject.type = $("#sltType").val();
        paramProject.ward.id = $("#sltWard").val();
        paramProject.ward.province= $("#sltProvince").val();
        paramProject.ward.district.id= $("#sltDistrict").val();
        paramProject.streetId = $("#sltStreet").val();
        paramProject.address = $("#ipnAddress").val();
        // Loại bỏ ký tự không phải số khỏi chuỗi
        var priceWithoutCurrency = $("#inpPrice").val().replace(/[^0-9.]/g, '');
        paramProject.price = parseFloat(priceWithoutCurrency);
        paramProject.bedroom = $("#inpBed").val();
        paramProject.bathroom = $("#inpBath").val();
        paramProject.garage = $("#inpGarage").val();
        paramProject.description = $("#inpDescription").val();
    }
    function getDataBlogInModal(paramBlog) {
        paramBlog.name = $("#inpNameBlog").val();
        paramBlog.description = $("#inpDescriptionBlog").val();
        paramBlog.content = vBlogContent.getData();
    }
    function validateUser(paramUser) {
        if (paramUser.contactName === "") {
            alert("Không để trống họ và tên");
            return false;
        }

        if (paramUser.email !== "") {
            // Kiểm tra địa chỉ email bằng biểu thức chính quy
            var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
            if (!emailPattern.test(paramUser.email)) {
                alert("Mail không đúng định dạng");
                return false;
            }
        }

        if (paramUser.mobile !== "") {
            // Kiểm tra số điện thoại bằng biểu thức chính quy
            var phonePattern = /^\d{10}$/; // Kiểm tra xem số điện thoại có 10 chữ số
            if (!phonePattern.test(paramUser.mobile)) {
                alert("Số điện thoại không đúng định dạng");
                return false;
            }
        }

        return true;
    }
    function validateProject(paramProject) {
        if (paramProject.title === "") {
            alert("Không được để trống tên dự án");
            return false;
        }

        if (paramProject.address === "") {
            alert("Không được để trống địa chỉ");
            return false;
        }
        if (paramProject.ward.id === null) {
            alert("Chưa chọn khu vực");
            return false;
        }
        if (!isNaturalNumber(paramProject.bedroom) || !isNaturalNumber(paramProject.bathroom)) {
            alert("Giá trị phòng ngủ và phòng tắm phải là số tự nhiên tổi thiểu 0");
            return false;
        }
        return true
    }
    function validateBlog(paramBlog) {
        if (paramBlog.name == "") {
            alert("Tên bài viết không được để trống");
            return false;
        }
        if (paramBlog.description == "") {
            alert("Giới thiệu bài viết không được để trống");
            return false;
        }
        if (paramBlog.content.length < 500) {
            alert("Bài viết quá ngắn. Tối thiểu 500 kí tự");
            return false;
        }
        return true;
    }
    function postDataProjectToInput(paramProject) {
        ($.get(gUrlMyProfile + "realestate/" + paramProject)).then(function (project) {
            $("#inpTitle").val(project.title);
            $("#sltRequest").val(project.request);
            $("#sltWard").val(project.ward);
            $("#sltStreet").val(project.streetId);
            $("#ipnAddress").val(project.address);
            $("#inpBed").val(project.bedroom);
            $("#inpBath").val(project.bathroom);
            $("#inpGarage").val(project.garage);
            $("#sltProvince").val(project.ward.district.province.id);
            getDistrict(function () {
                $("#sltDistrict").val(project.ward.district.id);
                getWardAndStreet(project.ward.district.id, project.ward.id, project.streetId);
            });
            gPriceInput.set(project.price);
            $("#inpDescription").val(project.description);
            imgDescFiles.removeFiles();
            imgThumFile.removeFiles();
            videoThum.removeFiles();
            $.get(gUrlMyProfile + "photo?realEstateId=" + paramProject).then(function (photos) {
                photos.forEach(photo => {
                    imgDescFiles.addFile(photo.path);
                })
            })
            if (project.photoMain) {
                imgThumFile.addFile(project.photoMain);
            }
            if (project.linkVideo) {
                videoThum.addFile(project.linkVideo);
            }
        });
    }
    function postDataBlogToModal(paramData) {
        $("#inpNameBlog").val(paramData.name);
        if (paramData.photoMain) {
            imgThumBlog.addFile(paramData.photoMain);
        }
        $("#inpDescriptionBlog").val(paramData.description);
        vBlogContent.setData(paramData.content);
    }
    function isNaturalNumber(value) {
        return /^\d+$/.test(value);
    }
    function blobToFile(blob) {
        // Lấy thông tin từ đối tượng Blob
        const fileName = blob.name; // Tên tệp
        const mimeType = blob.type; // Kiểu MIME

        // Tạo mảng chứa đối tượng Blob
        const fileParts = [blob];
        const file = new File(fileParts, fileName, { type: mimeType });

        return file;
    }
    function resetModal() {
        $("#inpTitle").val("");
        $("#sltRequest").val("0");
        $("#sltProvince").prop("selectedIndex", 0);
        $("#sltDistrict").empty();
        $("#sltDistrict").append('<option disabled selected>Chọn Quận</option>');
        $("#sltWard").empty();
        $("#sltWard").append('<option disabled selected>Chọn Phường</option>');
        $("#sltStreet").empty();
        $("#sltStreet").append('<option disabled selected>Chọn Đường</option>');
        $("#ipnAddress").val("");
        gPriceInput.set("");
        $("#inpBed").val("");
        $("#inpBath").val("");
        $("#inpDescription").val("");
        imgThumFile.removeFiles({ revert: true });
        imgDescFiles.removeFiles({ revert: true });
        videoThum.removeFiles({ revert: true });
        $("#inpNameBlog").val("");
        imgThumBlog.removeFiles({ revert: true });
        vBlogContent.setData("");
        $("#inpDescriptionBlog").val("");
    }
})