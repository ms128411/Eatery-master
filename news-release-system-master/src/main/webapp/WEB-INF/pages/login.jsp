<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新闻发布系统登陆</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="library/lib/layui-v2.5.5/css/layui.css" media="all">
    <!--[if lt IE 9]>
    <script src="library/js/html5.min.js"></script>
    <script src="library/js/respond.min.js"></script>
    <![endif]-->
    <style>
        html, body {width: 100%;height: 100%;overflow: hidden}
        body {background: rgba(25, 114, 153, 0.47);}
        body:after {content:'';background-repeat:no-repeat;background-size:cover;-webkit-filter:blur(3px);-moz-filter:blur(3px);-o-filter:blur(3px);-ms-filter:blur(3px);filter:blur(3px);position:absolute;top:0;left:0;right:0;bottom:0;z-index:-1;}
        .layui-container {width: 100%;height: 100%;overflow: hidden}
        .admin-login-background {width:360px;height:300px;position:absolute;left:50%;top:40%;margin-left:-180px;margin-top:-100px;}
        .logo-title {text-align:center;letter-spacing:2px;padding:14px 0;}
        .logo-title h1 {color:#1E9FFF;font-size:25px;font-weight:bold;}
        .login-form {background-color:#fff;border:1px solid #fff;border-radius:3px;padding:14px 20px;box-shadow:0 0 8px #eeeeee;}
        .login-form .layui-form-item {position:relative;}
        .login-form .layui-form-item label {position:absolute;left:1px;top:1px;width:38px;line-height:36px;text-align:center;color:#d2d2d2;}
        .login-form .layui-form-item input {padding-left:36px;}
        .captcha {width:60%;display:inline-block;}
        .captcha-img {display:inline-block;width:34%;float:right;}
        .captcha-img img {height:34px;border:1px solid #ea9e9e;height:36px;width:100%;}
    </style>
</head>
<body>
<div class="layui-container">
    <div class="admin-login-background">
        <div class="layui-form login-form">
            <form class="layui-form" action="/login" method="post">
                <div class="layui-form-item logo-title">
                    <h1>新闻发布系统</h1>
                    <div style="color: #ff0000;text-align: center;">${msg}</div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-username" ></label>
                    <input type="text" name="userName" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input" value="admin">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" ></label>
                    <input type="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="123">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-username"></label>
                    <select name="userType" lay-verify="required">
                        <option value="">请选择用户类型</option>
                        <option value="1" selected>管理员</option>
                        <option value="0">游客</option>
                    </select>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn layui-btn-normal layui-btn-fluid" lay-submit="" lay-filter="login">登录</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="library//lib/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
<script src="library//lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="library//lib/jq-module/jquery.particleground.min.js" charset="utf-8"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer;

        // 粒子线条背景
        $(document).ready(function(){
            $('.layui-container').particleground({
                dotColor:'#7ec7fd',
                lineColor:'#7ec7fd'
            });
        });
        
        form.on('submit(login)', function (data) {
            data = data.field;
            if (data.userName == '') {
                layer.msg('用户名不能为空');
                return false;
            }
            if (data.password == '') {
                layer.msg('密码不能为空');
                return false;
            }
            if (data.type == '') {
                layer.msg('类型不能为空');
                return false;
            }
        });
    });
</script>
</body>
</html>