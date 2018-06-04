$(function() {
    /* $.post("/p2p_home/AccountServlet?type=showAccount",function (data) {
         if(data.type==0){
             location.href="/p2p_home/login.html"
         }else{
             $("#balance").html(data.content.balance);
             $("#total").html(data.content.total);
             $("#interest").html(data.content.interest);
         }
     },"json");*/
    $.ajax({
        url: "/p2p_home/AccountServlet?type=showAccount",
        type: "post",
        dataType: "json",
        async: false,
        //这里的success不能用
        success: function (data) {
            if (data.type == 0) {
                location.href = "/p2p_home/login.html"
            } else {
                $("#balance").html(data.content.balance);
                $("#total").html(data.content.total);
                $("#interest").html(data.content.interest);
            }
        }
    })
});