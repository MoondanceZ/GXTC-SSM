<%--
  Created by IntelliJ IDEA.
  User: Qin_Yikai
  Date: 2018-10-02
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../common/tag.jsp" %>
    <%@ include file="../../common/head.jsp" %>
</head>
<body>
<div class="edit-div">
    <form class="layui-form" id="editPtForm" action="" lay-filter="pt">
        <input type="hidden" name="id" value="${userInfo.id}">
        <div class="layui-form-item">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" lay-verify="notempty" lay-vertype="tips" autocomplete="off"
                       placeholder="请输入类型名称" maxlength="12"
                       class="layui-input" value="${userInfo.name}">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="text" name="password" lay-verify="notempty|pass" lay-vertype="tips" autocomplete="off"
                       placeholder="请输入密码" maxlength="12" minlength="6"
                       class="layui-input" value="${userInfo.password}">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" name="email" lay-verify="email" lay-vertype="tips" autocomplete="off"
                       placeholder="请输入邮箱" maxlength="12"
                       class="layui-input" value="${userInfo.email}">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">电话</label>
            <div class="layui-input-block">
                <input type="text" name="phoneNumber" lay-verify="phone" lay-vertype="tips" autocomplete="off"
                       placeholder="请输入电话"
                       class="layui-input" value="${userInfo.phoneNumber}">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="submit">立即提交</button>
                <button type="reset" id="cancel" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer

        //自定义验证规则
        form.verify({
            notempty: function (value) {
                if (value.match(/^\s*$/)) {
                    return '不能为空';
                }
            }
            , pass: [/(.+){6,12}$/, '密码必须6到12位']
        });

        //监听提交
        form.on('submit(submit)', function (data) {
            //layer.msg(JSON.stringify(data.field));
            $.ajax({
                type: "POST",
                url: "/userInfo/save",
                data: JSON.stringify(data.field),
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    //layer.alert(JSON.stringify(data.field));
                    if (data.success) {
                        //parent 是 JS 自带的全局对象，可用于操作父页面
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

                        layer.msg(data.message, {
                            time: 2000 //2秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            parent.layer.close(index);
                            parent.layui.table.reload('pt')
                        });

                    } else {
                        layer.msg(data.message);
                    }
                }
            });
            return false;
        });

        //监听指定开关
        form.on('switch(status)', function (data) {
            if (this.checked) {
                $('input[name=status]').val(1);
            } else {
                $('input[name=status]').val(0);
            }
        });
    })

</script>
</body>
</html>