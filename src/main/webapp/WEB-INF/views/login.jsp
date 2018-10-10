<%--
  Created by IntelliJ IDEA.
  User: Qin_Yikai
  Date: 2018-09-16
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GXTC - 登录</title>
    <%@ include file="common/tag.jsp" %>
    <%@ include file="common/head.jsp" %>
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">管理登录</div>
    <div id="darkbannerwrap"></div>

    <form method="post" class="layui-form" name="admins" enctype="multipart/form-data">
        <input name="account" placeholder="账号" type="text" lay-verify="required" class="layui-input">
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码" type="password" class="layui-input">
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20">
    </form>

    <div>
        <a href="#"> <font style="color:black;text-align:right;">技术支持 ©MoondanceZ</font></a>
    </div>
</div>

<script>
    $(function () {
        layui.use('form', function () {
            var form = layui.form;

            //监听提交
            form.on('submit(login)', function (data) {
                var index = layer.load(1, {
                    shade: [0.6, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    type: "POST",
                    url: "/user/signin",
                    data: JSON.stringify(data.field),
                    dataType: "json",
                    contentType: "application/json",
                    success: function (data) {
                        //layer.close(index);
                        if (data.success) {
                            layer.msg(data.message, {
                                time: 1000 //2秒关闭（如果不配置，默认是3秒）
                            }, function () {
                                layer.msg('正在跳转...', {time: 5000});
                                location.href = '/admin/main';
                            });

                        } else {
                            layer.msg(data.message);
                        }
                    },
                    complete: function () {
                        layer.closeAll();
                    }
                });

                return false;
            });
        });
    })
</script>
</body>
</html>
