<%--
  Created by IntelliJ IDEA.
  User: Qin_Yikai
  Date: 2018-09-20
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>产品分类</title>
    <%@ include file="../common/tag.jsp" %>
    <%@ include file="../common/head.jsp" %>
</head>
<body>

<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addNew"><i class="layui-icon layui-icon-add-circle"></i> 添加
        </button>
        <button class="layui-btn layui-btn-sm" lay-event="delChecked"><i class="layui-icon layui-icon-delete"></i>删除
        </button>
    </div>
</script>

<script type="text/html" id="operate">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/html" id="statusTpl">
    {{#  if(d.status == '0'){ }}
    <span style="color: #f5231b;">{{ "停用" }}</span>
    {{#  } else { }}
    {{ "启用" }}
    {{#  } }}
</script>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" id="searchForm">
            <input type="text" name="queryString" placeholder="请输入名称或代码" autocomplete="off" class="layui-input">
            <button class="layui-btn" lay-submit lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>

    <table class="layui-hide" id="pt" lay-filter="pt"></table>

</div>
<script>
    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var form = layui.form;

        layui.use('table', function () {
            var table = layui.table;

            var tableIns = table.render({
                elem: '#pt'
                , id: 'pt'
                , toolbar: '#toolbar'
                //, height: 'full-10'
                //, id: 'ptid'
                , url: '/productType/pageList'
                , where: getFormObj('#searchForm')
                , cellMinWidth: 80
                , cols: [[
                    {type: 'checkbox'}
                    , {field: 'id', title: 'ID', sort: true}
                    , {field: 'typeName', title: '类型名称'}
                    , {field: 'typeCode', title: '类型代码'}
                    , {field: 'status', title: '状态', templet: '#statusTpl'}
                    , {title: '操作', toolbar: '#operate'}
                ]]
                , page: true
            });


            //头工具栏事件
            table.on('toolbar(pt)', function (obj) {
                //console.log(obj)
                var checkStatus = table.checkStatus(obj.config.id);
                console.log(checkStatus)
                switch (obj.event) {
                    case 'addNew':
                        layer.open({
                            type: 2
                            , title: '添加分类'
                            , area: ['600px', '300px']
                            , fixed: true
                            , shade: 0
                            , maxmin: true
                            , content: '/productType/edit'
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
                            $.post("/productType/delete", param, function (result) {
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
            table.on('tool(pt)', function (obj) {
                var objData = obj.data;
                console.log(obj)
                if (obj.event === 'del') {
                    layer.confirm('真的删除行么', function (index) {
                        var param = {ids: [objData.id]};
                        $.post("/productType/delete", param, function (result) {
                            layer.close(index);
                            if (result.success) {
                                layer.msg(result.message);
                                obj.del();
                            } else {
                                layer.msg(result.message);
                            }
                        });
                    });
                } else if (obj.event === 'edit') {
                    layer.open({
                        type: 2
                        , title: '编辑分类'
                        , area: ['600px', '300px']
                        , fixed: true
                        , shade: 0
                        , maxmin: true
                        , content: '/productType/edit?id=' + objData.id
                        , end: function () {
                            //layer.msg("关闭了");
                            //tableIns.reload();
                        }
                    });
                }
            });

            //监听提交
            form.on('submit(sreach)', function (data) {
                //layer.alert(JSON.stringify(data.field))
                tableIns.reload({
                    where: {
                        queryString: data.field.queryString
                    },
                    page: {
                        curr: 1
                    }
                });
                return false;
            })
        });
    });
</script>

</body>
</html>
