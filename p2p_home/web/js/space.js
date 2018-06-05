$(function () {
    //初始化账户信息,资产金额什么的
    $.post("/p2p_home/AccountServlet?method=showAccount", function (data) {
        if (data.type == 0) {
            location.href = "/p2p_home/login.html"
        } else {
            $("#balance").html(data.content.balance);
            $("#total").html(data.content.total);
            $("#interest").html(data.content.interest);
        }
    }, "json");

    /* $.ajax({
         url: "/p2p_home/AccountServlet?type=showAccount",
         type: "post",
         dataType: "json",
         async: false,
         success: function (data) {
             if (data.type == 0) {
                 location.href = "/p2p_home/login.html"
             } else {
                 $("#balance").html(data.content.balance);
                 $("#total").html(data.content.total);
                 $("#interest").html(data.content.interest);
             }
         }
     })*/
    //初始化邮箱的验证信息
    $.post("/p2p_home/LoginServlet?method=findCustomer", function (data) {
        if (data.type == 1) {
            var status = data.content.email_status;
            if(status>0){
                $("#emailSign").attr("class","glyphicon glyphicon-ok-circle");
                $("#emailWord").html("已验证");
                $("#emailWord").attr("class","yes");
                $("#emailBtn").attr("onclick","");

            }
        }
    }, "json");
});

//弹出模态框插件(验证栏tab4)
function showEmailModel() {
    //初始化弹出框
    $.post("/p2p_home/LoginServlet?method=findCustomer", function (data) {
        if (data.type == 1) {
            var customer = data.content;
            $("#emailAddr").attr("value", customer.email);
            //显示弹出框
            $("#emailModel").modal('show');
        } else {
            alert(data.errorMsg);
            location.href = "/p2p_home/login.html";
            return;
        }
    }, "json");

    //绑定发送事件的方法会直接执行,所以这里不行
    /*$("#sendEmailBtn").bind("click", sendEmail());*/
}

var checkedCode = "";

function sendEmail() {
    $.post("/p2p_home/LoginServlet?method=sendEmail", function (data) {
        if (data.type == 1) {
            // 获取返回的验证码
            checkedCode = data.content;
            //绑定检查事件的方法会直接执行,所以这里不行
            /*$("#startCheckBtn").bind("click", checkCode());*/
        } else {
            alert(data.errorMsg)
        }
    }, "json")
}

function checkCode() {
    //获取输入栏的验证码值
    var inputCheckedCode = $("#emailCode").val();
    var email = $("#emailAddr").val();
    //验证码判断
    if (inputCheckedCode == checkedCode) {
        //验证成功需要修改数据库邮箱状态
        $.post("/p2p_home/LoginServlet?method=changeStatus", {"email": email}, function (data) {
            alert("验证成功")
            location.href = "/p2p_home/space.html";
        }, "json")
    } else {
        $("#emailCode").val("");
        alert("验证码输入错误");
    }
}









