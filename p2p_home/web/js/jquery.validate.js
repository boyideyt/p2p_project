/*
 * 
 *Jquery 的验证框架
 * 
 *notNull regex ajax equalTo rangeLength
 * 
 */
(function ($) {
    $.fn.validate = function (fun) {
        //这里的this是Jquery的代理对象
        var form = this;
        var flag = true;
        $("#submit").click(function (e) {
            flag = true;
            e.preventDefault();
            form.find("input").each(function () {
                $(this).blur();
            });
            if (flag) {
                fun();
            }
        });
        form.find("input").each(function () {
            //这里的this是当前迭代的dom对象
            var inputObj = $(this);
            //attr 代表的是html标签具备的属性
            //prop 代表的是js对象的属性 disabled checked
            //validate根据有无属性，排出check行
            var valiStr = inputObj.attr("validate");
            if (valiStr) {
                inputObj.blur(function () {
                    //将validate对象的json字符串转换成json对象
                    var validates = $.parseJSON(valiStr);
                    //[{"type":"notNull","msg":"用户名不能为空！"},{"type":"notUsed","msg":"用户名已存在！"}]
                    for (var i = 0; i < validates.length; i++) {
                        var validate = validates[i];
                        //通过eval函数将 方法名执行获得函数实体对象
                        //eval(notNull); 将字符串当作js代码执行
                        var func = eval(validate.type);
                        //调用方法，inputObj参数为当前失焦的对象，validate为json(一个对象)；
                        if (func(inputObj, validate)) {
                            tip(inputObj, '校验通过', 'blue');

                        } else {
                            tip(inputObj, validate.msg, 'red');
                            flag = false;
                            return;
                        }
                    }
                });

            }
        });

        function notUsed(obj, validate) {
            var f = true;
            //获取当前输入框的内容,获取当前输入框的id
            var data =obj.attr("name") +"="+ obj.val();
            $.ajax({
                //这里不能单独用data
                url: "/p2p_home/RegisterServlet?method=notUsed&" + data,

                type: "post",
                dataType: "json",
                async : false,
                //这里的success不能用
                success : function (mg) {
                    var jsonResult = eval(mg);
                    if (jsonResult.type == 0) {
                        f = false;
                    } else {
                        f = true;
                    }
                }
            });
            return f;
        }

        function notNull(obj, validate) {
            var value = $.trim(obj.val());
            if (value.length == 0) {
                return false;
            }
            return true;
        }

        function regex(obj, validate) {
            var regMatch = new RegExp(validate.reg);
            if (regMatch == null || regMatch == "null") {
                return true;
            }
            var result = $.trim(obj.val());
            if (!regMatch.test(result)) {
                return false;
            }
            return true;
        }

        /**
         * 检验密码是否一致
         * target代表第一次输入密码的id这里target:"#password"
         */
        function equalTo(obj, validate) {
            var v1 = $.trim(obj.val());
            var v2 = $.trim($(validate.target).val());
            if (v1 == v2) {
                return true;
            }
            return false;
        }

        function tip(obj, msg, color) {
            var parent = obj.parent();
            var tips = parent.find("span");
            if (tips.length == 0) {
                obj.parent().append("<span><font color=" + color + ">" + msg + "</font></span>");
            } else {
                tips.html("<font color=" + color + ">" + msg + "</font>");
            }
        }

    }


})(jQuery);