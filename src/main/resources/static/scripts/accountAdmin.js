'use strict';
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gURLBlogAdmin = "http://localhost:8080/";
var gId = 0;
var blogTable = $("#tableAccount").DataTable({
    dom: 'Bfrtip',
    buttons: ['copy', 'csv', 'excel', 'pdf', 'colvis'],
    columns: [
        { data: "id" },
        { data: "action" },
        { data: "contactName" },
        { data: "address" },
        { data: "note" },
        { data: "createdBy" },
        {
            data: "updatedBy",
            render: function (data, type, row) {
                var vCustomer=$("<a>",{
                    href:"#",
                    class:"customerId",
                    text:data
                });
                return vCustomer.prop('outerHTML');
            }
        },
        {
            data: "deleted",
            render: function (data, type, row) {
                if (!data) {
                    return "Hoạt động";
                }
                else {
                    return "Đã Khóa";
                }
            }
        },
        { data: "roles[0].roleName" },
        {
            data: "roles[0].id",
            render: function (data, type, row) {
                if (data == 1) {
                    return "Tạo, Xem, Sửa, Xóa";
                }
                if (data == 2) {
                    return "Xem, Sửa";
                }
                if (data == 3) {
                    return "Xem, Sửa, Xóa";
                }
            }
        },


    ],
    columnDefs: [
        {
            targets: 1,
            defaultContent: `<i class="fas fa-edit text-primary btnAccountDetail"></i>`

        },
    ],
})

/*** REGION 2 - Vùng gán / thực thi sự kiện cho các elements */
$("#tableAccount").on("click", ".btnAccountDetail", onBtnBlogDetailClick);
$("#tableAccount").on("click", ".customerId", onCustomerIdClick);
$("#btnUnlock").on("click", onBtnUnlockClick);
$("#btnLock").on("click", onBtnLockClick);
/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
$(document).ready(function () {
    onLoadPage(blogTable);
    var customerId = localStorage.getItem('customerId');
    if (customerId) {
        // Lọc bảng accountTable dựa trên customerId
        blogTable.columns(0).search('^' + customerId + '$', true, false).draw();
        localStorage.removeItem('customerId');
    }
})
function onLoadPage(accountTable) {
    $.get(gURLBlogAdmin + "customers", function (paramRealeState) {
        loadBlogToTable(accountTable, paramRealeState)
    });
}
function loadBlogToTable(paramTable, paramAccount) {
    paramTable.clear().rows.add(paramAccount).draw();
}
function onBtnBlogDetailClick() {
    $("#modalDetailAccount").modal("show");
    let vSelectRow = $(this).parents('tr');
    let vSelectedData = blogTable.row(vSelectRow).data();
    gId = vSelectedData.id;
    if (!vSelectedData.deleted) {
        $("#btnLock").show();
        $("#btnUnlock").hide();
        $("#divReason").hide();
    }
    else {
        $("#btnLock").hide();
        $("#btnUnlock").show();
        $("#divReason").show();
    }
    if (vSelectedData.roles[0].id) {
        $("#btnUpLevelUser").show();
    }
    else {
        $("#btnUpLevelUser").hide();
    }
    loadDataToInput(vSelectedData);
}
function loadDataToInput(paramAccount) {
    resetModal();
    $("#inpContactName").val(paramAccount.contactName);
    $("#ipnAddress").val(paramAccount.address);
    $("#inpNote").val(paramAccount.note);
    if (paramAccount.avatar) {
        imgAvatarFile.addFile(paramAccount.avatar);
    }
}
function resetModal() {
    $("#inpContactName").val("");
    $("#ipnAddress").val("");
    $("#inpNote").val("");
    $("#inpReason").val("");
    imgAvatarFile.removeFiles();
}
function onBtnLockClick() {
    $.get(gURLBlogAdmin + "accout/lock/" + gId, function (data, status) {
        alert("Đã khóa tài khoản");
        onLoadPage(blogTable);
        $("#modalDetailAccount").modal("hide");
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert("Bạn không có quyền sửa tài khoản này");
    });
}
function onBtnUnlockClick() {
    var vReason=$("#inpReason").val();
    $.get(gURLBlogAdmin + "accout/unlock/" + gId+"?reason="+vReason, function (data, status) {
        alert("Đã Mở Khóa Tài Khoản");
        onLoadPage(blogTable);
        $("#modalDetailAccount").modal("hide");
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert("Bạn không có quyền sửa tài khoản này");
    });
}
function onCustomerIdClick(){
    blogTable.columns(0).search('^' + $(this).text() + '$', true, false).draw();
}