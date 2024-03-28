$(document).ready(function () {
    $("#btnForRent").click(function () {
        swapButtons();
    });

    $("#btnForSale").click(function () {
        swapButtons();
    });

    function swapButtons() {
        var btn1 = $("#btnForRent");
        var btn2 = $("#btnForSale");

        var tempText = btn1.text();
        var tempClass = btn1.attr("class");

        btn1.attr("class", btn2.attr("class"));

        btn2.attr("class", tempClass);
    }
});