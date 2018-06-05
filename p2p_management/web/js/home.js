$(function () {
    //获取所有商品信息
    findAllProduct();
})

/**
 * 搜寻所有商品
 */
function findAllProduct() {
    var url = "/p2p_management/ProductServlet?method=findAll";
    $.post(url, function (data) {
        var list = eval(data);
        $(list).each(function (i) {
            var tr = $("<tr><td>" + i + "</td><td>" + this.proNum + "</td><td>" + this.proName + "</td><td>" + this.proLimit + "</td><td>" + this.annualized + "</td><td>" + this.releaseDate + "</td><td><button type='button' onclick='editPro(" + this.id + ")'>更改</button><br><button type='button' onclick='delPro(this," + this.id + ")'>删除</button></td></tr>")
            $("#tbody").append(tr);
        })
    }, "json");
}

/**
 * 在弹出层列表修改商品
 * @param id
 */
function editPro(id) {
    //显示弹出层
    $(".modal").modal('show');
    $("#uiHeader").html("修改列表");
    $("#uiButton").attr("value", "确认修改");
    var url1 = "/p2p_management/ProductServlet?method=findPro&id=" + id;
    $.post(url1, function (data) {
        var product = eval(data);
        $("#proNum").val(product.proNum);
        $("#proName").val(product.proName);
        $("#proLimit").val(product.proLimit);
        $("#annualized").val(product.annualized);

        //给确认按钮绑定click事件之前先解绑
        $("#confirmBtn").unbind("click");
        //给确认按钮绑定click事件
        $("#confirmBtn").bind("click", function () {
            //通过jquery的serialize() 方法获取表单参数
            var param = $("#modalForm").serialize();
            //注意：没把id传给后台，后台就不知道根据什么修改
            param += "&id=" + id;
            //通过ajax发送请求
            // console.info(param);
            // alert(param);
            var url = "/p2p_management/ProductServlet?method=editPro";
            $.post(url, param, function (data) {
                alert(data);
                //隐藏弹出层
                $('.modal').modal('hide');
                //更新页面
                findAllProduct();
            }, "text");
        });

    }, "json");
}

/*location.href="/p2p_management/views/home.jsp";*/

/* $.ajax({
     url: url2,
     data: param,
     type: "post",
     dataType: "json",
     async: false,
     success: function (data) {
         alert(data);
         var product = eval(data);
         var tdeles = $(item).parents("tr").children();
         tdeles[2].html(product.proNum);
         tdeles[3].html(product.proName);
         tdeles[4].html(product.proLimit);
         tdeles[5].html(product.annualized);
     }
 });
}*/

/**
 * 在弹出层列表添加商品
 */
function addPro() {
    //显示弹出层
    $(".modal").modal('show');
    $("#uiHeader").html("添加列表");
    $("#uiButton").attr("value", "确认添加");
    //清空产品信息
    $("#modalForm :input[name='proNum']").val("");
    $("#modalForm :input[name='proName']").val("");
    $("#modalForm :input[name='proLimit']").val("");
    $("#modalForm :input[name='annualized']").val("");
    //给确认按钮绑定click事件之前先解绑
    $("#confirmBtn").unbind("click");
    //给确认按钮绑定click事件
    $("#confirmBtn").bind("click", function () {
        var url = "/p2p_management/ProductServlet?method=addPro";
        var param = $("#modalForm").serialize();
        $.post(url, param, function (data) {
            alert(data);
            //更新页面
            findAllProduct();
        }, "text");
        $(".modal").modal('hide');
    })
}

/**
 * 在商品列表删除商品
 * @param item
 * @param id
 */
function delPro(item, id) {
    var flag = confirm("确认要删除吗?");
    if (flag) {
        item.parentElement.parentElement.remove(item.parentElement);
        var url = "/p2p_management/ProductServlet?method=delPro&id=" + id;
        $.post(url, function (data) {
            alert(data)
        }, "text");
    }
}

