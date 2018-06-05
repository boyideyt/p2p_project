<%--
  Created by IntelliJ IDEA.
  User: boyideyt
  Date: 2018/5/31
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>confirmBtn
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>P2P网站后台管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/semantic.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/semantic.js"></script>

    <style type="text/css">
        body {
            font-family: Verdana, Arial, Helvetica, sans-serif;
            margin: 0;
            /* background-color: #fff; */
            background-image: url('./img/bg_login.jpg');
        }

        .ui.grid {
            margin: 0;
        }

        .header-row1 {
            height: 100px;
        }

        .login-header {
            /* background-color: green; */
            height: 160px;
        }

        .ui.white {
            color: #fff;
        }

        .text-center {
            text-align: center;
        }

        .ui.login-div {
            margin: 30px 0 0 0;
        }
    </style>

</head>
<body class="">
<div class="ui login-header">
    <div class="ui grid">
        <div class="row header-row1">
            <div class="row text-center">
                <h1 class="ui header white"
                    style="margin-left: 460px; margin-top: 50px">
                    <i class="settings icon"></i>P2P后台管理-用户登录
                </h1>
            </div>
        </div>
    </div>
</div>
<div class="ui three column stackable grid login-div">
    <div class="column"></div>
    <div class="column">
        <span id="lmsg">${lmsg}</span>
        <form id="login" class="ui fluid form segment"
              action="${pageContext.request.contextPath}/UserServlet?method=login" method="POST" style="border-radius:6px;box-shadow: 6px 6px 3px #181423;">
            <div class="inline field"></div>
            <div class="field">
                <label class="">用户名</label>
                <div class="ui left icon input">
                    <input type="text" name="username" placeholder="Enter username">
                    <i class="user icon"></i>
                    <div class="ui corner label">
                        <i class="icon asterisk"></i>
                    </div>
                </div>
            </div>
            <div class="field">
                <label class="">密码</label>
                <div class="ui left icon input">
                    <input type="password" name="password"
                           placeholder="Enter password"> <i class="lock icon"></i>
                    <div class="ui corner label">
                        <i class="icon asterisk"></i>
                    </div>
                </div>
            </div>
            <div class="inline field" style="display: inline-block;margin-left: 10%">
                <input type="button" value="注 册" class="ui blue button" style="width: 150%">
            </div>
            <div class="inline field" style="display: inline-block; margin-left: 20%">
                <input type="submit" value="登 录" class="ui blue button" style="width: 150%">
            </div>

        </form>
    </div>
    <div class="column"></div>
</div>
</body>

</html>
