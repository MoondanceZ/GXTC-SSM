<%--<jsp:useBean id="JsonUtils" scope="request" class="com.rk.util.JsonUtils"/>--%>
<%--
  Created by IntelliJ IDEA.
  User: Qin_Yikai
  Date: 2018-09-29
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page import="com.rk.util.JsonUtils" %>--%>
<html>
<head>
    <%@ include file="../../common/tag.jsp" %>
    <%@ include file="../../common/head.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/kindeditor/kindeditor-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/kindeditor/lang/zh_CN.js"></script>
</head>
<body>
<div class="edit-div">
    <form class="layui-form" id="editForm" action="" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${product.id}">
        <div class="layui-form-item">
            <label class="layui-form-label"><span class="x-red">*</span>产品名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" lay-verify="notempty" lay-vertype="tips" autocomplete="off"
                       placeholder="请输入类型名称"
                       class="layui-input" value="${product.name}">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-upload">
                <label class="layui-form-label">封面一</label>
                <button type="button" class="layui-btn" id="btnFile1">上传图片</button>
                <div class="layui-upload-list">
                    <c:choose>
                        <c:when test="${product.image1 != null }">
                            <img class="layui-upload-img" id="img-file-1" src="/upload/images/${product.image1}">
                        </c:when>
                        <c:otherwise>
                            <img class="layui-upload-img" id="img-file-1">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-upload">
                <label class="layui-form-label">封面二</label>
                <button type="button" class="layui-btn" id="btnFile2">上传图片</button>
                <div class="layui-upload-list">
                    <c:choose>
                        <c:when test="${product.image2 != null }">
                            <img class="layui-upload-img" id="img-file-2" src="/upload/images/${product.image2}">
                        </c:when>
                        <c:otherwise>
                            <img class="layui-upload-img" id="img-file-2">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-upload">
                <label class="layui-form-label">封面三</label>
                <button type="button" class="layui-btn" id="btnFile3">上传图片</button>
                <div class="layui-upload-list">
                    <c:choose>
                        <c:when test="${product.image3 != null }">
                            <img class="layui-upload-img" id="img-file-3" src="/upload/images/${product.image3}">
                        </c:when>
                        <c:otherwise>
                            <img class="layui-upload-img" id="img-file-3">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">产品产地</label>
            <div class="layui-input-inline">
                <input type="text" name="originPlace" autocomplete="off" placeholder="请输入产品产地"
                       class="layui-input" value="${product.originPlace}">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">产品描述</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入产品描述" id="description" name="description"
                          class="layui-textarea">${product.description}</textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label"><span class="x-red">*</span>产品价格</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" name="price" lay-verify="notempty|price" lay-vertype="tips"
                           value="${product.price}"
                           placeholder="￥产品价格" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label"><span class="x-red">*</span>产品原价</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" name="oldPrice" lay-verify="price" lay-vertype="tips" value="${product.oldPrice}"
                           placeholder="￥产品原价"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">统一售价</label>
            <div class="layui-input-block">
                <input type="checkbox" name="unifiedPrice"
                       value="${product.unifiedPrice}" ${product.unifiedPrice == true ? "checked" : ""}
                       lay-skin="switch"
                       lay-text="ON|OFF" lay-filter="unifiedPrice">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label"><span class="x-red">*</span>数量</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" name="count" lay-verify="notempty|number" lay-vertype="tips"
                           value="${product.count}"
                           placeholder="数量" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">购买数量</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" name="limitCount" lay-verify="number" lay-vertype="tips"
                           value="${product.limitCount}"
                           placeholder="购买数量" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label"><span class="x-red">*</span>产品类型</label>
                <div class="layui-input-inline">
                    <select name="typeId" lay-verify="required" lay-vertype="tips">
                        <option value>请选择类型</option>
                        <c:forEach var="item" items="${enableTypes}">
                            <option value="${item.id}" ${product.typeId==item.id?"selected":""}>${item.typeName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="checkbox" name="status"
                       value="${product.status}" ${product.status == 1 ? "checked" : ""} lay-skin="switch"
                       lay-text="ON|OFF" lay-filter="status">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">产品规格</label>
            <div class="layui-input-block">
                <table class="layui-table item-table">
                    <thead>
                    <tr>
                        <td style="display: none">id</td>
                        <td>图片</td>
                        <td>规格</td>
                        <td>名称</td>
                        <td>价格</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${product.productItems}" varStatus="status">
                        <tr>
                            <td style="display: none;">
                                <input type="text" data-type="itemId" value="${item.id}">
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${item.image != null }">
                                        <img class="layui-upload-img" id="item-img-file-${(status.index+1)}"
                                             src="/upload/images/${item.image}">
                                    </c:when>
                                    <c:otherwise>
                                        <img class="layui-upload-img" id="item-img-file-${(status.index+1)}">
                                    </c:otherwise>
                                </c:choose>
                                <button type="button" class="layui-btn" id="btnItemFile${(status.index+1)}">上传图片
                                </button>

                                <script>
                                    layui.use(['upload'], function () {
                                        var upload = layui.upload;
                                        var elem = '#btnItemFile' + ${status.index+1};
                                        var imgElem = '#item-img-file-' + ${status.index+1};
                                        upload.render({
                                            elem: elem
                                            , auto: false
                                            , accept: 'images'
                                            , field: 'itemImgFile'
                                            , acceptMime: 'image/*'
                                            , size: '5120'
                                            , choose: function (obj) {
                                                //预读本地文件示例，不支持ie8
                                                obj.preview(function (index, file, result) {
                                                    debugger;
                                                    $(imgElem).attr('src', result); //图片链接（base64）
                                                });
                                            }
                                        });
                                    })
                                </script>

                            </td>
                            <td>
                                <input type="text" data-type="itemCode" lay-verify="notempty" lay-vertype="tips"
                                       autocomplete="off"
                                       placeholder="请输入规格代码"
                                       class="layui-input" value="${item.itemCode}">
                            </td>
                            <td>
                                <input type="text" data-type="itemName" lay-verify="notempty" lay-vertype="tips"
                                       autocomplete="off"
                                       placeholder="请输入规格名称"
                                       class="layui-input" value="${item.itemName}">
                            </td>
                            <td>
                                <input type="text" data-type="itemPrice" lay-verify="price" lay-vertype="tips"
                                       autocomplete="off"
                                       placeholder="请输入规格价格"
                                       class="layui-input" value="${item.price}">
                            </td>
                            <td>
                                <button type="button" class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">
                                    删除
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <button type="button" class="layui-btn layui-btn-primary" id="add-item">添加规格</button>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="submit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script>
    KindEditor.ready(function (K) {
        var descriptionEditor = CreateEditor('textarea#description');

        function CreateEditor(sel) {
            var editor = K.create(sel, {
                themeType: 'default',
                allowImageRemote: K.undef(self.allowImageRemote, true),
                formatUploadUrl: false,
                width: '100%',
                autoHeightMode: true,
                resizeType: 1,
                /*afterCreate: function () {
                 this.loadPlugin('autoheight');
                 },*/
                showRemote: false,
                //allowImageRemote: false,
                uploadJson: '/file/uploadImage',
                allowFileManager: false,
                items: [
                    'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                    'insertunorderedlist', '|', 'emoticons', 'image', 'link', '|', 'fullscreen'],
                //fillDescAfterUploadImage : K.undef(self.fillDescAfterUploadImage, false),
            });
            return editor;
        }

        layui.use(['form', 'upload', 'table'], function () {
            var form = layui.form
                , table = layui.table
                , upload = layui.upload;

            //自定义验证规则
            form.verify({
                notempty: function (value, obj) {
                    var vname = $(obj).data('lable');
                    if (vname === 'undefined') vname = $(obj).parent().prev().text();

                    if (value.match(/^\s*$/)) {
                        return vname + '不能为空';
                    }
                },
                price: function (value, obj) {
                    if (!value.match(/^\s*$/) && !/(^[1-9]\d*$)|(^[1-9]\d*[.][0-9]{1,2}$)/.test(value)) {
                        var vname = $(obj).data('lable');
                        if (vname === 'undefined') vname = $(obj).parent().prev().text();

                        return vname + '只能输入数字, 小数点后只能保留2位小数'
                    }
                }
                /*    ,pass: [/(.+){6,12}$/, '密码必须6到12位']
                 ,content: function(value){
                 layedit.sync(editIndex);
                 }*/
            });

            function genItemTableData(){
                var data = [];
                $('.item-table tbody tr').each(function () {
                    var $this = $(this);
                    var $tds = $this.find('td');
                    var productItem = {
                        id: $($tds[0]).children('input').val() == '' ? null : $($tds[0]).children('input').val(),
                        itemCode: $($tds[2]).children('input').val() == '' ? null : $($tds[2]).children('input').val(),
                        itemName: $($tds[3]).children('input').val() == '' ? null : $($tds[3]).children('input').val(),
                        price: $($tds[4]).children('input').val() == '' ? null : $($tds[4]).children('input').val()
                    }
                    data.push(productItem);
                })
                return data;
            }

            //监听提交
            form.on('submit(submit)', function (data) {
                //console.log(descriptionEditor.html());
                descriptionEditor.sync();
                var formData = new FormData($('#editForm')[0]);
                var itemData = genItemTableData();
                formData.append('productItem[]', itemData);
                var loadIndex = layer.load(0);
                genItemTableData();
                $.ajax({
                    type: "POST",
                    url: "/product/save",
                    data: formData,
                    //dataType: 'json',
                    processData: false,  //必须false才会避开jQuery对 formdata 的默认处理
                    contentType: false,  //必须false才会自动加上正确的Content-Type
                    success: function (data) {
                        layer.close(loadIndex);
                        if (data.success) {
                            //parent 是 JS 自带的全局对象，可用于操作父页面
                            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

                            layer.msg(data.message, {
                                time: 2000 //2秒关闭（如果不配置，默认是3秒）
                            }, function () {
                                parent.layer.close(index);
                                parent.layui.table.reload('p')
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

            form.on('switch(unifiedPrice)', function (data) {
                if (this.checked) {
                    $('input[name=unifiedPrice]').val(true);
                } else {
                    $('input[name=unifiedPrice]').val(false);
                }
            });


            //普通图片上传
            upload.render({
                elem: '#btnFile1'
                , auto: false
                , accept: 'images'
                , field: 'imgFile1'
                , acceptMime: 'image/*'
                , size: '5120'
                //,exts : 'jpg|png|gif|bmp|jpeg'
                , choose: function (obj) {
                    //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
                    obj.preview(function (index, file, result) {
                        $('#img-file-1').attr('src', result); //图片链接（base64）
                    });
                }
            });

            upload.render({
                elem: '#btnFile2'
                , auto: false
                , accept: 'images'
                , field: 'imgFile2'
                , acceptMime: 'image/*'
                , size: '5120'
                , choose: function (obj) {
                    //预读本地文件示例，不支持ie8
                    obj.preview(function (index, file, result) {
                        $('#img-file-2').attr('src', result); //图片链接（base64）
                    });
                }
            });

            upload.render({
                elem: '#btnFile3'
                , auto: false
                , url: '/file/uploadImage'
                , accept: 'images'
                , field: 'imgFile3'
                , acceptMime: 'image/*'
                , size: '5120'
                , choose: function (obj) {
                    //预读本地文件示例，不支持ie8
                    obj.preview(function (index, file, result) {
                        $('#img-file-3').attr('src', result); //图片链接（base64）
                    });
                }
                , before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                    layer.load(); //上传loading
                }
                , done: function(res, index, upload){ //上传后的回调
                    layer.closeAll('loading'); //关闭loading
                }
                , error: function(index, upload){
                    layer.closeAll('loading'); //关闭loading
                }
            });

            $('#add-item').click(function (e) {
                e.preventDefault();
                var trLength = $('.item-table tbody tr').length;
                var tr = '<tr> <td style="display: none;">\n' +
                    '                                <input type="text" data-type="itemId">\n' +
                    '                            </td>\n' +
                    '                            <td>\n' +
                    '                                <img class="layui-upload-img" id="item-img-file-' + (trLength + 1) + '">\n' +
                    '                                <button type="button" class="layui-btn" id="btnItemFile' + (trLength + 1) + '">上传图片</button>\n' +
                    '                            </td>\n' +
                    '                            <td>\n' +
                    '                                <input data-lable="规格代码" type="text" data-type="itemCode" lay-verify="notempty" lay-vertype="tips"\n' +
                    '                                       autocomplete="off"\n' +
                    '                                       placeholder="请输入规格代码"\n' +
                    '                                       class="layui-input">\n' +
                    '                            </td>\n' +
                    '                            <td>\n' +
                    '                                <input data-lable="规格名称" type="text" data-type="itemName" lay-verify="notempty" lay-vertype="tips"\n' +
                    '                                       autocomplete="off"\n' +
                    '                                       placeholder="请输入规格名称"\n' +
                    '                                       class="layui-input">\n' +
                    '                            </td>\n' +
                    '                            <td>\n' +
                    '                                <input data-lable="规格价格" type="text" data-type="itemPrice" lay-verify="price" lay-vertype="tips"\n' +
                    '                                       autocomplete="off"\n' +
                    '                                       placeholder="请输入规格价格"\n' +
                    '                                       class="layui-input">\n' +
                    '                            </td>\n' +
                    '                            <td>\n' +
                    '                                <button type="button" class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">\n' +
                    '                                    删除\n' +
                    '                                </button>\n' +
                    '                            </td></tr>'
                if (trLength === 0) {
                    $('.item-table tbody').html(tr);
                } else {
                    $('.item-table tbody tr:last').after(tr);
                }

                upload.render({
                    elem: '#btnItemFile' + (trLength + 1)
                    , auto: false
                    , accept: 'images'
                    , field: 'itemImgFile'
                    , acceptMime: 'image/*'
                    , size: '5120'
                    , choose: function (obj) {
                        //预读本地文件示例，不支持ie8
                        obj.preview(function (index, file, result) {
                            $('#item-img-file-' + (trLength + 1)).attr('src', result); //图片链接（base64）
                        });
                    }
                });
                return false;
            });
        });

    });

</script>
</body>
</html>
