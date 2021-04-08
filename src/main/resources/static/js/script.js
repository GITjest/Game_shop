$(window).ready(function () {
    $(".country-flag").click(function (){
        let lang = $(this).attr("alt");
        let path = window.location.origin + window.location.pathname;
        window.location.href = path + '?lang=' + lang;
    });
});
