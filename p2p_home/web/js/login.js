//验证码点击更换操作
function changeImage(img) {
    img.src = "/p2p_home/CheckImageServlet?time=" + new Date().getTime();
}

//登录操作
function loglogin() {
    var url = "/p2p_home/LoginServlet?type=login";
    var param = $("#loginform").serialize();
    $.post(url,param, function (data) {
        if (data.type == 1) {
            location.href = "/p2p_home/space.html";
        } else {
            $("#errorSpan").html(data.errorMsg);
        }
    }, "json")
}

