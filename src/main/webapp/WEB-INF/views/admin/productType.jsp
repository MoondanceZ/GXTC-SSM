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
    <title>商品种类</title>
    <%@ include file="../common/tag.jsp" %>
    <%@ include file="../common/head.jsp" %>
</head>
<body>

<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addNew">+添加</button>
        <button class="layui-btn layui-btn-sm" lay-event="delChecked">删除</button>
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

<%--<xblock>
    <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
    <button class="layui-btn" onclick="x_admin_show('添加用户','./admin-add.html')"><i class="layui-icon"></i>添加</button>
    <span class="x-right" style="line-height:40px">共有数据：88 条</span>
</xblock>--%>

<table class="layui-hide" id="pt" lay-filter="pt"></table>


<script>
    layui.use('table', function () {
        var table = layui.table;

        layui.use('table', function () {
            var table = layui.table;

            table.render({
                elem: '#pt'
                , toolbar: '#toolbar'
                , height: 'full-10'
                ,id: 'ptid'
                , url: '/productType/pageList'
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
        });

         //头工具栏事件
         table.on('toolbar(pt)', function (obj) {
             console.log(obj)
             var checkStatus = table.checkStatus(obj.config.id);
             switch (obj.event) {
                 case 'addNew':
                     var data = checkStatus.data;
                     layer.alert(JSON.stringify(data));
                     break;
                 case 'delChecked':
                     var data = checkStatus.data;
                     layer.msg('选中了：' + data.length + ' 个');
                     break;
                 case 'isAll':
                     layer.msg(checkStatus.isAll ? '全选' : '未全选');
                     break;
             }
             ;
         });

        //监听行工具事件
        table.on('tool(pt)', function (obj) {
            var data = obj.data;
            console.log(obj)
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                layer.prompt({
                    formType: 2
                    , value: data.email
                }, function (value, index) {
                    obj.update({
                        email: value
                    });
                    layer.close(index);
                });
            }
        });
    });
</script>

</body>
</html>
