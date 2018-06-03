$(function () {
    $.post("/p2p_home/AccountServlet?type=showAccount",function (data) {
        if(data.type==0){
            location.href="/p2p_home/login.html"
        }else{
            $("#balance").html(data.content.balance);
            $("#total").html(data.content.total);
            $("#interest").html(data.content.interest);
        }
    },"json");
})