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
    <title>登录</title>
    <%@ include file="common/tag.jsp" %>
    <%@ include file="common/head.jsp" %>
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">管理登录</div>
    <div id="darkbannerwrap"></div>

    <form method="post" class="layui-form" name="admins" enctype="multipart/form-data"  >
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
        layui.use('form', function(){
            var form = layui.form;

            // layer.msg('玩命卖萌中', function(){
            //   //关闭后的操作
            //   });
            //监听提交
            form.on('submit(login)', function(data){
                $.post('/user/signin', JSON.stringify(data.field), function (data) {
                    if(data.success){
                        layer.msg("登录成功", function(){
                            location.href='index.html'
                        });
                    }else{
                        layer.msg(data.message);
                    }
                })

              return false;
            });
        });
    })
</script>
</body>
</html>
