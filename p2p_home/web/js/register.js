$(function () {
    $("#regform").validate(register);
});

function register(){
    var url = "/p2p_home/RegisterServlet?method=reg";
    var param = $("#regform").serialize();
    $.post(url, param, function (data) {
        alert(data);
    },"text");
}


