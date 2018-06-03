$(function () {
    $("#regform").validate(register);
});

function register(){
    //检查用户是否勾选“我同意《服务协议》”复选框
    var isAgree=$("#cbAgree").get(0).checked;
    if (isAgree) {//同意注册
        //获取form表单参数
        var url = "/p2p_home/RegisterServlet?method=reg";
        var param = $("#regform").serialize();
        $.post(url, param, function (data) {
            var jsonResult=eval(data);
            if (jsonResult.type==1) {//成功
                alert("注册成功,请前去登录!")
            }else{
                alert(jsonResult.errorMsg);//失败，显示错误信息
            }
        }, "json");
    }else{
        alert('请勾选"我同意《服务协议》"');
    }
}


