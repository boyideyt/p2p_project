$(function () {

    /**
     * 商品跨域搜索返回后遍历分布
     * 在我要购买设置onclick事件
     */
    $.post("/p2p_management/ProductServlet?method=findAll", function (data) {
        //返回所有商品的json对象
        for (var i = 0; i < data.length; i++) {
            var product = data[i];
            var li = '<li><div class="col-sm-6 col-md-4"><div class="thumbnail">\n' +
                '      <div class="caption pro1"><h4>' + product.proName + '</h4></div>\n' +
                '      <img src="img/help/m-ico_01.png"/><div class="caption">\n' +
                '      <h4>利率:' + product.annualized + '%</h4>\n' +
                '      <h4>期限:' + product.proLimit + '个月</h4>\n' +
                '      <h6>发布日期:' + product.releaseDate + '</h6>\n' +
                '      <a href="#shopArea1" data-toggle="tab" onclick="setByMsg(' + product.id + ')" class="btn btn-info">我要购买</a>\n' +
                '      </div></div><span class="ico"></span></div></li>';
            $("#dowebokul").append(li);
        }
        //自动滚动条
        $('.dowebok').liMarquee({
            direction: 'up',    //设置滚动方向
            scrollamount: '40', // 设置滚动速度
            runshort: false  //内容未超过容器宽度（高度），不滚动。
        });
    }, "json");

});

/**
 * 1.点击'我要购买'按钮后更新显示层数据
 * 2.增加投入金额栏的失焦事件
 * @param pid
 */
function setByMsg(pid) {
    //初始值清空
    $("#jsonResultMsg").html("");
    $("#cost").val("");
    $("#interest").html("0.00");
    $.post("/p2p_management/ProductServlet?method=findPro&id=" + pid, function (product) {
        var interest = "";
        /**
         * 失去焦点事件,临时计算收益值interest,
         * 并保留小数点两位.
         */
        $("#cost").blur(function () {
                //首先计算预计获益
                var interest = $(".num").val() * product.annualized * product.proLimit / 1200;
                //先转换为字符串,找出小数点索引值,再根据结果截断字符串
                interest=interest+"";
                var index = interest.indexOf("\.");
                if(index!=-1){
                   interest = parseFloat(interest.substring(0,index+3));
                }
                //再调用time判定方法
                time(product.id, interest);
            }
        );
        $("#proName").html(product.proName);
        $("#annualized").html(product.annualized);

    }, "json");
}

/**
 * 1.判断是否是100倍整数;2.清空结算信息栏,更新根据投资金额计算的预计收益金额3.使确认按钮增加带有触发交易
 * @param pid
 * @param interest
 */
function time(pid, interest) {
    //结算信息清空
    $("#resultMsg").html("");
    $("#interest").html(interest);
    //取得投资金额
    var nu = $(".num").val();
    if (null == nu || "" == nu) {
        $(".word").html("");
    } else {
        if (nu % 100 == 0) {
            $("#buybuybuy").removeAttr("disabled");
            $(".word").html("");
            $.post("/p2p_management/ProductServlet?method=findPro&id=" + pid, function (product) {

                var td = '<td>配置结果</td><td>您选择了<span class="yellow">' + product.proName + '</span>加入的本金为<span class="yellow">' + $("#cost").val() + '</span>元，投资期限为<span\n' +
                    '    class="yellow">' + product.proLimit + '</span>个月，预期年化收益率为<span class="yellow">' + product.annualized + '</span>%,请确认购买。\n' +
                    '    </td>';

                $("#resultMsg").html(td);
                $("#buybuybuy").click(function () {
                    var flag = confirm("确定了?");
                    if (flag) {
                        var buyMsg = {};
                        buyMsg.pid = product.id;
                        buyMsg.money = nu;
                        //interest不用传

                        $.post("/p2p_home/Product_AccountServlet?method=buy", buyMsg, function (data) {
                            var json = eval(data);
                            if (data.type == 0) {
                                //购买失败
                                $("#jsonResultMsg").html(data.errorMsg);
                            }else{
                                location.href="/p2p_home/moto.html";
                            }
                        }, "json")
                    } else {

                    }
                })
            }, "json");
        } else {
            $(".word").html("请输入100的整数倍");
        }
    }
}