'use strict';
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gURLBlogAdmin = "http://localhost:8080/";
var gId = 0;
var blogTable = $("#tableBlog").DataTable({
    dom: 'Bfrtip',
    buttons: ['copy', 'csv', 'excel', 'pdf', 'colvis'],
    columns: [
        { data: "id" },
        { data: "action" },
        { data: "name" },
        {
            data: "customers.id",
            render: function (data, type, row) {
                var vCustomer = $("<a>", {
                    href: "#",
                    class: "customerId",
                    text: data
                });
                return vCustomer.prop('outerHTML');
            }
        },
        {
            data: "approve",
            render: function (data, type, row) {
                if (!data) {
                    return "Chờ duyệt";
                }
                else {
                    return "Đã duyệt"
                }
            }
        },
        { data: "view" },
        {
            data: "updateBy",
            render: function (data, type, row) {
                var vCustomer = $("<a>", {
                    href: "#",
                    class: "customerId",
                    text: data
                });
                return vCustomer.prop('outerHTML');
            }
        },
    ],
    columnDefs: [
        {
            targets: 1,
            defaultContent: `<i class="fas fa-edit text-primary btnBlogDetail"></i>`

        },
    ],
})

/*** REGION 2 - Vùng gán / thực thi sự kiện cho các elements */
$("#tableBlog").on("click", ".btnBlogDetail", onBtnBlogDetailClick);
$("#tableBlog").on("click", ".customerId", onCustomerIdClick);
$("#btnApprove").on("click", onBtnApproveClick);
$("#btnUnApprove").on("click", onBtnUnApproveClick);
/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
$(document).ready(function () {
    onLoadPage(blogTable);
})

function onLoadPage(blogTable) {
    $.get(gURLBlogAdmin + "blog", function (paramRealeState) {
        loadBlogToTable(blogTable, paramRealeState);
    });
}
function onBtnBlogDetailClick() {
    $("#modalUPdateBlog").modal("show");
    let vSelectRow = $(this).parents('tr');
    let vSelectedData = blogTable.row(vSelectRow).data();
    gId = vSelectedData.id;
    if (!vSelectedData.approve) {
        $("#btnUnApprove").hide();
        $("#divReason").hide();
        $("#btnApprove").show();
    }
    else {
        $("#btnUnApprove").show();
        $("#divReason").show();
        $("#btnApprove").hide();
    }

    loadDataToInput(vSelectedData);
}
function onCustomerIdClick() {
    localStorage.setItem('customerId', $(this).text());
    window.location.href = '/admin/account';
}
function onBtnApproveClick() {
    $.get(gURLBlogAdmin+"blog/approve/"+gId,function(data){
        alert("Cập nhật thành công");
        location.reload();
    }).fail(function (xhr, textStatus, errorThrown) {
        alert("Cập nhật thất bại! Vui lòng kiểm tra lại.");
    });
}
function onBtnUnApproveClick() {
    var vReason=$("#inpReason").val();
    $.get(gURLBlogAdmin+"blog/unapprove/"+gId+"?reason="+vReason,function(data){
        alert("Cập nhật thành công");
        location.reload();
    }).fail(function (xhr, textStatus, errorThrown) {
        alert("Cập nhật thất bại! Vui lòng kiểm tra lại.");
    });
}
/*** REGION 3 - funtion - Vùng khai báo các hàm dùng chung */
function loadBlogToTable(paramTable, paramAccount) {
    paramTable.clear().rows.add(paramAccount).draw();
}
function loadDataToInput(paramBlog) {
    resetModal();
    $("#inpNameBlog").val(paramBlog.name);
    if (paramBlog.photoMain) {
        imgThumBlog.addFile(paramBlog.photoMain);
    }
    $("#inpDescriptionBlog").val(paramBlog.description);
    vBlogContent.setData(paramBlog.content);
}
function resetModal() {
    $("#inpNameBlog").val("");
    imgThumBlog.removeFiles({ revert: true });
    vBlogContent.setData("");
    $("#inpDescriptionBlog").val("");
    $("#inpReason").val("");
}
