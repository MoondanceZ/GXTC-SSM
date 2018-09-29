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
<div class="layui-row">
    <form class="layui-form layui-col-md12 x-so">
        <input class="layui-input" placeholder="开始日" name="start" id="start">
        <input class="layui-input" placeholder="截止日" name="end" id="end">
        <input type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input">
        <button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
    </form>
</div>
</body>
</html>
