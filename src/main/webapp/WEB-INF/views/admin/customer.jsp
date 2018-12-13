<%--
  Created by IntelliJ IDEA.
  User: qyk
  Date: 2018-12-13
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户</title>
    <%@ include file="../common/tag.jsp" %>
    <%@ include file="../common/head.jsp" %>
</head>
<body>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" id="searchForm">
            <div class="layui-input-inline">
                <select name="level">
                    <option value>会员等级</option>
                    <option value="1">青铜会员</option>
                    <option value="2">白银会员</option>
                    <option value="3">黄金会员</option>
                    <option value="4">铂金会员</option>
                    <option value="5">钻石会员</option>
                </select>
            </div>
            <div class="layui-input-inline">
                <select name="status">
                    <option value>产品状态</option>
                    <option value="1">正常</option>
                    <option value="0">禁用</option>
                </select>
            </div>
            <input type="text" name="queryString" placeholder="输入用户名称、邮箱或手机" autocomplete="off" class="layui-input">
            <button class="layui-btn" lay-submit lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>

    <table class="layui-hide" id="p" lay-filter="p"></table>

    <script type="text/html" id="operate">
        <%--<a class="layui-btn layui-btn-xs" lay-event="disable">禁用</a>--%>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>

    <script type="text/html" id="statusTpl">
        <input type="checkbox" name="status" data-id="{{d.id}}" value="{{d.status}}" lay-skin="switch" lay-filter="switchStatus" lay-text="启用|禁用" {{ d.status == '1' ? 'checked' : '' }} >
    </script>

    <script type="text/html" id="levelTpl">
        {{#  if(d.level == '1'){ }}
        {{ "青铜" }}
        {{#  } else if(d.level == '2') { }}
        {{ "白银" }}
        {{#  } else if(d.level == '3') { }}
        {{ "黄金" }}
        {{#  } else if(d.level == '4') { }}
        {{ "铂金" }}
        {{#  } else { }}
        {{ "钻石" }}
        {{#  } }}
    </script>

    <script type="text/html" id="toolbar">
        <div class="layui-btn-container">
 <%--           <button class="layui-btn layui-btn-sm" lay-event="addNew"><i class="layui-icon layui-icon-add-circle"></i>
                添加
            </button>--%>
            <button class="layui-btn layui-btn-sm" lay-event="delChecked"><i class="layui-icon layui-icon-delete"></i>删除
            </button>
        </div>
    </script>
</div>

<script>
    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var form = layui.form;

        var tableIns = table.render({
            elem: '#p'
            , id: 'p'
            , toolbar: '#toolbar'
            //, height: 'full-10'
            //, id: 'ptid'
            , url: '/customer/pageList'
            , where: getFormObj('#searchForm')
            //, cellMinWidth: 40
            , cols: [[
                {type: 'checkbox'}
                , {field: 'id', title: 'ID', sort: true, width: 80}
                , {field: 'uid', title: 'uid'}
                , {field: 'account', title: '账号', width: 80}
                , {field: 'name', title: '名称', width: 80}
                , {field: 'point', title: '积分', width: 80}
                , {field: 'level', title: '会员等级', width: 100, templet: '#levelTpl'}
                , {field: 'balance', title: '余额', width: 80}
                , {field: 'consumptionAmount', title: '消费金额', width: 100}
                , {field: 'email', title: '邮箱', width: 120}
                , {field: 'phone', title: '手机', width: 80}
                , {field: 'birthday', title: '生日', width: 80}
                , {field: 'joinTime', title: '加入时间', width: 160}
                , {field: 'lastLoginTime', title: '最后登录时间', width: 160}
                , {field: 'status', title: '状态', templet: '#statusTpl', width: 100}
                , {title: '操作', toolbar: '#operate', fixed: 'right', width: 80}
            ]]
            , page: true
        });


        //头工具栏事件
        table.on('toolbar(p)', function (obj) {
            //console.log(obj)
            var checkStatus = table.checkStatus(obj.config.id);
            console.log(checkStatus);
            switch (obj.event) {
                case 'addNew':
                    layer.open({
                        type: 2
                        , title: '添加客户'
                        , area: [($(window).width() * 0.9), ($(window).height() - 50)]
                        , fixed: true
                        , shade: 0.4
                        , fix: true
                        , scrollbar: false
                        , maxmin: true
                        , content: '/customer/edit'
                        , end: function () {
                            //layer.msg("关闭了");
                            //tableIns.reload();
                        }
                    });
                    break;
                case 'delChecked':
                    var data = checkStatus.data;
                    var dataLength = data.length;
                    if (dataLength == 0) {
                        layer.msg("请选中要删除行");
                        return;
                    }
                    var ids = [];
                    for (var i = 0; i < dataLength; i++) {
                        ids.push(data[i].id);
                    }
                    layer.confirm('真的删除选中行么', function (index) {
                        var param = {ids: ids};
                        $.post("/customer/delete", param, function (result) {
                            layer.close(index);
                            if (result.success) {
                                layer.msg(result.message);
                                tableIns.reload();
                            } else {
                                layer.msg(result.message);
                            }
                        });
                    });
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(p)', function (obj) {
            var objData = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    var param = {ids: [objData.id]};
                    $.post("/customer/delete", param, function (result) {
                        layer.close(index);
                        if (result.success) {
                            layer.msg(result.message);
                            obj.del();
                        } else {
                            layer.msg(result.message);
                        }
                    });
                });
            } else if (obj.event === 'disable') {
                var param = {ids: [objData.id]};
                $.post("/customer/disabled", param, function (result) {
                    if (result.success) {
                        layer.msg(result.message);
                        console.log(obj);
                        //obj.del();
                    } else {
                        layer.msg(result.message);
                    }
                });
            }
        });

        //监听提交
        form.on('submit(sreach)', function (data) {
            //layer.alert(JSON.stringify(data.field))
            tableIns.reload({
                where: getFormObj('#searchForm'),
                page: {
                    curr: 1
                }
            });
            return false;
        });

        form.on('switch(switchStatus)', function(obj){
            var id = $(this).data('id');
            var param = {ids: [id]};

            if(obj.elem.checked){
                $.post("/customer/enabled", param, function (result) {
                    if (result.success) {
                        layer.msg(result.message);
                    } else {
                        layer.msg(result.message);
                        //$(this).removeAttr('checked');
                        obj.elem.checked = false;
                        form.render('checkbox');
                    }
                });
                form.render('checkbox');
            }else {
                $.post("/customer/disabled", param, function (result) {
                    if (result.success) {
                        layer.msg(result.message);
                    } else {
                        layer.msg(result.message);
                        //$(this).prop('checked', true);
                        obj.elem.checked = true;
                        form.render('checkbox');
                    }
                });
            }
        });
    });
</script>
</body>
</html>
