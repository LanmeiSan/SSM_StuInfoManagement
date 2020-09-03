<%--
  Created by IntelliJ IDEA.
  User: 1329669644
  Date: 2020/6/1
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录</title>
    <meta http-equiv="Content-type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrom=1">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css" type="text/css">
    <style type="text/css">
        body {
            background-color: lightcyan;
        }
    </style>
<%--    <script type="text/javascript">--%>
<%--        $(function () {--%>
<%--            $("#loginBtn").click(function () {--%>
<%--                var val= $('input:radio[name="optRadio"]:checked').val();--%>
<%--                if (val ==null){--%>
<%--                    alert("请选择用户分类")--%>
<%--                    return;--%>
<%--                }--%>
<%--            })--%>
<%--        });--%>
<%--    </script>--%>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
<%--            ${pageContext.request.contextPath }/login/adminLogin--%>
            <form class="form-horizontal" action="${pageContext.request.contextPath }/login/adminLogin" method="post">
                <span class="heading">登录</span>
                <span style="color: red;font-weight: bold">
                    ${error_login}
                </span>
                <div class="form-group">
                    <input type="text" class="form-control" name="adminName" placeholder="用户名" required>
                    <i class="glyphicon glyphicon-user"></i>
                </div>
                <div class="form-group help">
                    <input type="password" class="form-control" name="adminPwd" placeholder="密　码" required>
                    <i class="glyphicon glyphicon-lock"></i>
                </div>
                <div class="form-group" style="font-size: 16px">
                    <div class="radio-inline">
                        <label><input type="radio" name="optRadio" value="1" checked>管理员</label>
                    </div>
                    <div class="radio-inline">
                        <label><input type="radio" name="optRadio" value="0">教师</label>
                    </div>
                    <div class="radio-inline">
                        <label><input type="radio" name="optRadio" value="-1">学生</label>
                    </div>
                    <button type="submit" class="btn btn-default" id="loginBtn" style="outline: none">登录</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
