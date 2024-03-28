'use strict';
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gURLCommentAdmin = "http://localhost:8080/";
var gId = 0;
var commentTable = $("#tableComment").DataTable({
    dom: 'Bfrtip',
    buttons: ['copy', 'csv', 'excel', 'pdf', 'colvis'],
    columns: [
        { data: "id" },
        { data: "action" },
        {
            data: "approve",
            render: function (data, type, row) {
                if(!data){
                    return "Chưa duyệt";
                }
                else{
                    return "Đã duyệt";
                }
            }
        },
        {
            data: "content",
            render: function (data, type, row) {
                return data.length > 70 ? data.substring(0, 70) + "..." : data;
            }
        },
        {
            data: "dateCreate",
            render: function (data, type, row) {
                return changDataDate(data);
            }
        },
        {
            data: "customers.id",
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
            data: "realestate.id",
            render: function (data, type, row) {
                var vRealEstate=$("<a>",{
                    href:"#",
                    class:"realestateId",
                    text:data
                });
                return vRealEstate.prop('outerHTML');
            }
        },


    ],
    columnDefs: [
        {
            targets: 1,
            defaultContent: `<i class="fas fa-edit text-primary btnCommentDetail"></i>`

        },
    ],
})

/*** REGION 2 - Vùng gán / thực thi sự kiện cho các elements */
$("#tableComment").on("click", ".btnCommentDetail", onBtnCommentDetailClick);
$("#tableComment").on("click", ".customerId", onBtnCustomerClick);
$("#tableComment").on("click", ".realestateId", onBtnRealestateClick);
$("#btnApprove").on("click", onBtnApproveClick);
$("#btnUnApprove").on("click", onBtnUnApproveClick);
/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
$(document).ready(function () {
    onLoadPage(commentTable);
})
function onLoadPage(commentTable) {
    $.get(gURLCommentAdmin + "comments", function (paramComment) {
        loadRealeStateToTable(commentTable, paramComment)
    });
}
function loadRealeStateToTable(paramTable, paramComment) {
    paramTable.clear().rows.add(paramComment).draw();
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
function onBtnCommentDetailClick() {
    $("#modalDetailComment").modal("show");
    let vSelectRow = $(this).parents('tr');
    let vSelectedData = commentTable.row(vSelectRow).data();
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
    loadDataToInput(vSelectedData);
}
function loadDataToInput(paramAccount) {
    resetModal();
    $("#inpContent").val(paramAccount.content);
}
function resetModal() {
    $("#inpContent").val("");
    $("#inpReason").val("");
}
function onBtnApproveClick(){
    $.get(gURLCommentAdmin+"comments/approve/"+gId,function(){
        alert("Đã Duyệt Bình Luận");
        onLoadPage(commentTable);
        $("#modalDetailComment").modal("hide");
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert("Đã xảy ra lỗi");
    });
}
function onBtnUnApproveClick(){
    var vReason=$("#inpReason").val();
    $.get(gURLCommentAdmin+"comments/unapprove/"+gId+"?reason="+vReason,function(){
        alert("Đã Bỏ Duyệt Bình Luận");
        onLoadPage(commentTable);
        $("#modalDetailComment").modal("hide");
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert("Đã xảy ra lỗi");
    });
}
function onBtnCustomerClick(){
    localStorage.setItem('customerId', $(this).text());
    window.location.href = '/admin/account';
}
function onBtnRealestateClick(){
    localStorage.setItem('realestateId', $(this).text());
    window.location.href = '/admin/project';
}
