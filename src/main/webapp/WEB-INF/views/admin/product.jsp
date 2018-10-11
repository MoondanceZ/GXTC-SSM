<%--
  Created by IntelliJ IDEA.
  User: windows10
  Date: 2018/9/19
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>产品</title>
    <%@ include file="../common/tag.jsp" %>
    <%@ include file="../common/head.jsp" %>
</head>
<body>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" id="searchForm">
            <input type="text" name="minPrice" placeholder="￥最低价格" autocomplete="off" class="layui-input">
            <input type="text" name="maxPrice" placeholder="￥最高价格" autocomplete="off" class="layui-input">
            <div class="layui-input-inline">
                <select name="status">
                    <option value>产品状态</option>
                    <option value="1">在售</option>
                    <option value="2">下架</option>
                </select>
            </div>
            <input type="text" name="originPlace" placeholder="输入产品产地" autocomplete="off" class="layui-input">
            <input type="text" name="queryString" placeholder="输入产品名称" autocomplete="off" class="layui-input">
            <button class="layui-btn" lay-submit lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>

    <table class="layui-hide" id="p" lay-filter="p"></table>

    <script type="text/html" id="operate">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>

    <script type="text/html" id="statusTpl">
        {{#  if(d.status == '1'){ }}
        {{ "在售" }}
        {{#  } else { }}
        <span style="color: #f5231b;">{{ "下架" }}</span>
        {{#  } }}
    </script>

    <script type="text/html" id="toolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="addNew"><i class="layui-icon layui-icon-add-circle"></i>
                添加
            </button>
            <button class="layui-btn layui-btn-sm" lay-event="delChecked"><i class="layui-icon layui-icon-delete"></i>删除
            </button>
        </div>
    </script>
</div>

<script>
    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var form = layui.form;

        layui.use('table', function () {
            var table = layui.table;

            var tableIns = table.render({
                elem: '#p'
                , id: 'p'
                , toolbar: '#toolbar'
                //, height: 'full-10'
                //, id: 'ptid'
                , url: '/product/pageList'
                , where: getFormObj('#searchForm')
                //, cellMinWidth: 40
                , cols: [[
                    {type: 'checkbox'}
                    , {field: 'id', title: 'ID', sort: true, width: 80}
                    , {field: 'name', title: '产品名称'}
                    , {field: 'price', title: '价格', width: 80}
                    , {field: 'oldPrice', title: '原价', width: 80}
                    , {field: 'originPlace', title: '产地', width: 80}
                    , {field: 'createDate', title: '创建日期', width: 160}
                    , {field: 'status', title: '状态', templet: '#statusTpl', width: 60}
                    , {title: '操作', toolbar: '#operate', fixed: 'right', width: 120}
                ]]
                , page: true
            });


            //头工具栏事件
            table.on('toolbar(p)', function (obj) {
                //console.log(obj)
                var checkStatus = table.checkStatus(obj.config.id);
                console.log(checkStatus)
                switch (obj.event) {
                    case 'addNew':
                        layer.open({
                            type: 2
                            , title: '添加产品'
                            , area: [($(window).width() * 0.9), ($(window).height() - 50)]
                            , fixed: true
                            , shade: 0.4
                            , fix: true
                            , scrollbar: false
                            , maxmin: true
                            , content: '/product/edit'
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
                            $.post("/product/delete", param, function (result) {
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
                        $.post("/product/delete", param, function (result) {
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
                        , title: '编辑产品'
                        , area: [($(window).width() * 0.9), ($(window).height() - 50)]
                        , fixed: true
                        , shade: 0.4
                        , maxmin: true
                        , content: '/product/edit?id=' + objData.id
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
                    where: getFormObj('#searchForm'),
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
