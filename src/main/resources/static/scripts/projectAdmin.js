'use strict';
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gURLProjectAdmin = "http://localhost:8080/";
var gId = 0;
var projectTable = $("#tableProject").DataTable({

    dom: 'Bfrtip',
    buttons: ['copy', 'csv', 'excel', 'pdf', 'colvis'],
    columns: [
        { data: "id" },
        {
            data: "action"
        },
        {
            data: "title"
        },
        {
            data: "address"
        },
        {
            data: "description"
        },
        {
            data: "customers",
            render: function (data, type, row) {
                var vCustomer = $("<a>", {
                    href: "#",
                    class: "customerId",
                    text: data.contactName,
                    "data-value": data.id
                });
                return vCustomer.prop('outerHTML');;
            }
        },
        {
            data: "dateCreate",
            render: function (data, type, row) {
                return changDataDate(data);
            }
        },
        {
            data: "dateUpdate",
            render: function (data, type, row) {
                return changDataDate(data);
            }
        },
        {
            data: "view"
        },

    ],
    columnDefs: [
        {
            targets: 1,
            defaultContent: `<i class="fas fa-edit text-primary btnProjetctDetail"></i>`

        },
    ],
})
var projectSameCustomerTable = $("#tableProjectSameCustomer").DataTable({
    columns: [
        { data: "id" },
        {
            data: "action"
        },
        {
            data: "title"
        },
        {
            data: "address"
        },
        {
            data: "dateCreate",
            render: function (data, type, row) {
                return changDataDate(data);
            }
        },
        {
            data: "dateUpdate",
            render: function (data, type, row) {
                return changDataDate(data);
            }
        }
    ],
    columnDefs: [
        {
            targets: 1,
            defaultContent: `<i class="fas fa-edit text-primary btnProjetctSameCustomerDetail"></i>`
        },
    ],
})

/*** REGION 2 - Vùng gán / thực thi sự kiện cho các elements */
$("#tableProject").on("click", ".btnProjetctDetail", onDetailRealeStateClick);
$("#tableProject").on("click", ".customerId", onCustomerIdClick);
$("#btnFeatured").on("click", function () {
    $("#modalConfirmFeatured").modal("show");
});
$("#btnUnFeatured").on("click", function () {
    $("#modalConfirmUnFeatured").modal("show");
});
$("#btnApprove").on("click", function () {
    $("#modalConfirmApprove").modal("show");
});
$("#btnUnApprove").on("click", function () {
    $("#modalConfirmUnApprove").modal("show");
});
$("#btnConfirmFeatured").on("click", onBtnConfirmFeaturedClick);
$("#btnConfirmUnFeatured").on("click", onBtnConfirmUnFeaturedClick);
$("#btnConfirmApprove").on("click", onBtnConfirmApproveClick);
$("#btnConfirmUnApprove").on("click", onBtnConfirmUnApproveClick);
$(document).on("click", ".btnProjetctSameCustomerDetail", onBtnProjetctSameCustomerDetailClick);
/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
$(document).ready(function () {
    onLoadPage(projectTable);
    var realEstateId = localStorage.getItem('realestateId');
    if (realEstateId) {
        projectTable.columns(0).search('^' + realEstateId + '$', true, false).draw();
        localStorage.removeItem('realestateId');
    }
})
function onLoadPage(projectTable) {
    $.get(gURLProjectAdmin + "all/realestate", function (paramRealeState) {
        loadRealeStateToTable(projectTable, paramRealeState)
    });
}
function changDataDate(paramDate) {
    // Tạo một đối tượng Date từ chuỗi ngày
    var dateObject = new Date(paramDate);

    // Lấy thông tin ngày, tháng, năm và giờ
    var year = dateObject.getFullYear();
    var month = (dateObject.getMonth() + 1).toString().padStart(2, '0'); // Thêm '0' phía trước nếu tháng chỉ có một chữ số
    var day = dateObject.getDate().toString().padStart(2, '0'); // Thêm '0' phía trước nếu ngày chỉ có một chữ số
    var hours = dateObject.getHours().toString().padStart(2, '0'); // Thêm '0' phía trước nếu giờ chỉ có một chữ số
    var minutes = dateObject.getMinutes().toString().padStart(2, '0'); // Thêm '0' phía trước nếu phút chỉ có một chữ số

    // Tạo chuỗi kết quả
    var resultString = `${year}-${month}-${day} ${hours}:${minutes}`;

    return resultString;
}
function loadRealeStateToTable(paramTable, paramRealeState) {
    paramTable.clear().rows.add(paramRealeState).draw();
}
function onDetailRealeStateClick() {
    $("#modalDetailProject").modal("show");
    let vSelectRow = $(this).parents('tr');
    let vSelectedData = projectTable.row(vSelectRow).data();
    gId = vSelectedData.id;
    if (!vSelectedData.approve) {
        $("#btnApprove").show();
        $("#btnUnApprove").hide();
        $("#divReason").hide();
    }
    else {
        $("#btnApprove").hide();
        $("#btnUnApprove").show();
        $("#divReason").show();

    }
    if (!vSelectedData.featured) {
        $("#btnFeatured").show();
        $("#btnUnFeatured").hide();
        $("#divReason").hide();
    }
    else {
        $("#btnFeatured").hide();
        $("#btnUnFeatured").show();
        $("#divReason").show();
    }

    loadDataToInput(vSelectedData);
    getProject();
}
function loadDataToInput(paramProject) {
    resetModal();
    $("#inpTitle").val(paramProject.title);
    if (paramProject.request == 0) {
        $("#sltRequest").append($("<option>", {
            text: "Cần bán",
            disabled: true,
            selected: true
        }));
    }
    if (paramProject.request == 3) {
        $("#sltRequest").append($("<option>", {
            text: "Cho thuê",
            disabled: true,
            selected: true
        }));
    }
    $("#sltProvince").append($("<option>", {
        text: paramProject.ward.district.province.name,
        disabled: true,
        selected: true
    }));
    $("#sltDistrict").append($("<option>", {
        text: paramProject.ward.district.name,
        disabled: true,
        selected: true
    }));
    $("#sltWard").append($("<option>", {
        text: paramProject.ward.name,
        disabled: true,
        selected: true
    }));
    $.get(gURLProjectAdmin + "province/district/street/" + paramProject.streetId, function (data) {
        $("#sltStreet").append($("<option>", {
            text: data.prefix + " " + data.name,
            disabled: true,
            selected: true
        }));
    });
    $("#ipnAddress").val(paramProject.address);
    if (paramProject.price) {
        $("#inpPrice").val(paramProject.price + " VNĐ");
    }
    $("#inpBed").val(paramProject.bedroom);
    $("#inpBath").val(paramProject.bathroom);
    $("#inpDescription").val(paramProject.description);
    if (paramProject.photoMain != null) {
        imgThumFile.addFile(paramProject.photoMain);
    }
    if (paramProject.linkVideo != null) {
        videoThum.addFile(paramProject.linkVideo);
    }
    $.get(gURLProjectAdmin + "photo?realEstateId=" + paramProject.id, function (photos) {
        photos.forEach(photo => {
            imgDescFiles.addFile(photo.path);
        })
    });
}
function getProject() {
    $.get(gURLProjectAdmin + "realestate/" + gId + "/customer", function (data) {
        loadRealeStateToTable(projectSameCustomerTable, data);
    });
}
function resetModal() {
    $("#inpTitle").val("");
    $("#sltRequest option").remove();
    $("#sltProvince option").remove();
    $("#sltDistrict option").remove();
    $("#sltWard option").remove();
    $("#sltStreet option").remove();
    $("#ipnAddress").val("");
    $("#inpPrice").val("");
    $("#inpBed").val("");
    $("#inpBath").val("");
    $("#inpDescription").val("");
    imgDescFiles.removeFiles();
    imgThumFile.removeFiles();
    videoThum.removeFiles();
    $("#inpReason").val("");
}
function onBtnConfirmFeaturedClick() {
    $.get(gURLProjectAdmin + "realestate/featured/" + gId, function () {
        alert("Cập nhật thành công");
    }).fail(function (xhr, textStatus, errorThrown) {
        alert("Cập nhật thất bại! Vui lòng kiểm tra lại.");
    });
    $("#modalConfirmFeatured").modal("hide");
    $("#modalDetailProject").modal("hide");
}
function onBtnConfirmUnFeaturedClick() {
    $.get(gURLProjectAdmin + "realestate/unfeatured/" + gId + "?reason=" + vReason, function () {
        alert("Cập nhật thành công");
    }).fail(function (xhr, textStatus, errorThrown) {
        alert("Cập nhật thất bại! Vui lòng kiểm tra lại.");
    });
    $("#modalConfirmUnFeatured").modal("hide");
    $("#modalDetailProject").modal("hide");
}
function onBtnConfirmApproveClick() {
    $.get(gURLProjectAdmin + "realestate/approve/" + gId, function () {
        alert("Cập nhật thành công");
    }).fail(function (xhr, textStatus, errorThrown) {
        alert("Cập nhật thất bại! Vui lòng kiểm tra lại.");
    });
    $("#modalConfirmApprove").modal("hide");
    $("#modalDetailProject").modal("hide");
}
function onBtnConfirmUnApproveClick() {
    $.get(gURLProjectAdmin + "realestate/unapprove/" + gId + "?reason=" + vReason, function () {
        alert("Cập nhật thành công");
    }).fail(function (xhr, textStatus, errorThrown) {
        alert("Cập nhật thất bại! Vui lòng kiểm tra lại.");
    });
    $("#modalConfirmUnApprove").modal("hide");
    $("#modalDetailProject").modal("hide");
}
function onBtnProjetctSameCustomerDetailClick() {
    let vSelectRow = $(this).parents('tr');
    let vSelectedData = projectSameCustomerTable.row(vSelectRow).data();
    console.log(vSelectedData);
    gId = vSelectedData.id;
    if (!vSelectedData.approve) {
        $("#btnApprove").show();
        $("#btnUnApprove").hide();
    }
    else {
        $("#btnApprove").hide();
        $("#btnUnApprove").show();
    }
    if (!vSelectedData.featured) {
        $("#btnFeatured").show();
        $("#btnUnFeatured").hide();
    }
    else {
        $("#btnFeatured").hide();
        $("#btnUnFeatured").show();
    }
    loadDataToInput(vSelectedData);
    getProject();
}
function onCustomerIdClick() {
    localStorage.setItem('customerId', $(this).data("value"));
    window.location.href = '/admin/account';
}
