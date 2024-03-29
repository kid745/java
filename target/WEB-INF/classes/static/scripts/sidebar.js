const gURLSidebar = "http://localhost:8080/";
$(document).ready(function () {
    onLoadPageSideBar();
    $("#btnSearchSidebar").on("click", onBtnSearchSideBarClick);
})
function onLoadPageSideBar() {
    $.get(gURLSidebar + "province", function (data) {
        data.forEach(province => {
            $("#sltProvince").append($("<option>", {
                text: province.name,
                value: province.id
            }));
        });
    })
}
function onBtnSearchSideBarClick() {
    var vParam = {
        typeProject: "",
        requestProject: "",
        minPrice: "",
        maxPrice: "",
        minAcreage: "",
        maxAcreage: "",
        minBedroom: "",
        maxBedroom: "",
        minBathroom: "",
        maxBathroom: "",
        minGarage: "",
        maxGarage: "",
        statusProject: "",
        sltProvince: "",

    }
    getDataSidebar(vParam);
    var url = addParamUrl(vParam);
    window.location.href = url;
}
function getDataSidebar(paramData) {
    paramData.typeProject = $('input[name="typeProject"]:checked').val();
    paramData.requestProject = $('input[name="requestProject"]:checked').val();
    paramData.minPrice = $("#minPrice").val();
    paramData.maxPrice = $("#maxPrice").val();
    paramData.minAcreage = $("#minAcreage").val();
    paramData.maxAcreage = $("#maxAcreage").val();
    paramData.minBedroom = $("#minBedroom").val();
    paramData.maxBedroom = $("#maxBedroom").val();
    paramData.minBathroom = $("#minBathroom").val();
    paramData.maxBathroom = $("#maxBathroom").val();
    paramData.minGarage = $("#minGarage").val();
    paramData.maxGarage = $("#maxGarage").val();
    paramData.statusProject = $('input[name="statusProject"]:checked').val();
    paramData.sltProvince = $("#sltProvince").val();
}
function addParamUrl(paramData) {
    var url = "/property?";
    for (var key in paramData) {
        if (paramData.hasOwnProperty(key) && paramData[key] !== "") {
            url += key + "=" + encodeURIComponent(paramData[key]) + "&";
        }
    }
    url = url.slice(0, -1);
    console.log(url); // In ra URL hoặc sử dụng nó theo nhu cầu của bạn
    return url;
}