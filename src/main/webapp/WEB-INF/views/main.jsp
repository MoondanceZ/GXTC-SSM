<%--
  Created by IntelliJ IDEA.
  User: Qin_Yikai
  Date: 2018/9/19
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理</title>
    <%@ include file="common/tag.jsp" %>
    <%@ include file="common/head.jsp" %>
</head>
<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo"><a href="javascript:;">后台管理</a></div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>

    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">

            <a href="javascript:;" class="iconfont">@Model.code</a>
            <dl class="layui-nav-child">
                <!-- 二级菜单 -->
                <!--
                <dd><a _href="/chen_menber/Index">个人信息</a></dd>
                -->
                <!--
                <dd><a _href="/chen_menber/Index">切换帐号</a></dd>
                -->
                <dd><a href="/">退出</a></dd>
            </dl>
        </li>
        <!--
        <li class="layui-nav-item to-index"><a href="/">首页</a></li>
        -->
    </ul>

</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>会员管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/chen_menber/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>用户列表</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/chen_menber_coupon_log/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>优惠券列表</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/chen_menber_point_log/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>获取积分列表</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/chen_menber_usepoint_log/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>积分使用列表</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/chen_recharge/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>用户充值表</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>订单管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/chen_order/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>订单列表</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>商品管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/admin/productType">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>商品分类</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/admin/product">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>商品列表</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/chen_points/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>积分商品列表</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>店铺管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/chen_store/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>店铺</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/chen_recharge_discount/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>充值优惠</cite>

                        </a>
                    </li>
                    <li>
                        <a _href="/chen_coupon/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>优惠券列表</cite>

                        </a>
                    </li>
                    <li>
                        <a _href="/chen_index/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>轮播图片</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/WX_config/Edit">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>微信配置</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/chen_integral_discount/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>积分金额比例</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/chen_Hnumber/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>核销员</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe726;</i>
                    <cite>管理员管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/admin/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>管理员列表</cite>
                        </a>
                    </li>
                </ul>
            </li>


            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6ce;</i>
                    <cite>收藏评论管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/chen_menber_collection_log/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>收藏列表</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/chen_menber_comment_log/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>评论列表</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6ce;</i>
                    <cite>流水管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/chen_menber_checkmoney_log/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>消费列表</cite>
                        </a>
                    </li>

                    <li>
                        <a _href="/chen_menber_money_log/Index">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>金额流水列表</cite>
                        </a>
                    </li>
                </ul>
            </li>

        </ul>
    </div>
</div>
<!--
<div class="x-slide_left"></div>
-->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <div class="copyright" style="text-align:center;"><a href="http://gxzmr.com"> <font style="color:white;">技术支持
        ©MoondanceZ</font></a></div>
</div>
<!-- 底部结束 -->
</body>
</html>
