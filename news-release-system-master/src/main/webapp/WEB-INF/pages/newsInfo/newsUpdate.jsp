<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加用户</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/library/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="/library/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layui-form layuimini-form">

        <div class="layui-form-item" >
            <label class="layui-form-label required">新闻类别</label>
            <div class="layui-input-block">
                <select class="test" id="newsType" name="newsType" lay-verify="required" lay-search>
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">新闻标题</label>
            <div class="layui-input-block">
                <input type="text" id="title" name="title" lay-verify="required" lay-reqtext="新闻标题不能为空" placeholder="请输入新闻标题" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新闻封面</label>          <%--暂未实现--%>
            <div class="layui-input-block">
                <div class="layui-upload">
                    <button type="button" class="layui-btn layui-btn-normal" id="test1">上传图片</button>
                    <div class="layui-upload-list" > <!--显示上传图片的demo-->
                        <img class="layui-upload-img" id="demo1" style="width: 95px;">
                        <p id="demoText"></p>
                    </div>
                    <div style="width: 95px;">  <!--上传进度条-->
                        <div class="layui-progress layui-progress-big" lay-showpercent="yes" lay-filter="demo">
                            <div class="layui-progress-bar" lay-percent=""></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" required>新闻内容</label>
            <div class="layui-input-block">
                <div class="test2" id="editor" style="margin: 50px 0 50px 0">
                    <p>请输入新闻内容</p>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认提交</button>
            </div>
        </div>
    </div>
</div>

<!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
<script src="/library/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="/library/js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['layer','wangEditor','form','upload', 'element'], function () {
        var $ = layui.jquery,
            layer = layui.layer,
            form = layui.form,
            wangEditor = layui.wangEditor,
            upload = layui.upload,
            element = layui.element;

        form.render('select');
        //常规使用 - 普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            ,url: 'https://httpbin.org/post' //改成您自己的上传接口
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });

                element.progress('demo', '0%'); //进度条复位
                layer.msg('上传中', {icon: 16, time: 0});
            }
            ,done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功的一些操作
                //……
                $('#demoText').html(''); //置空上传失败的状态
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
            //进度条
            ,progress: function(n, elem, e){
                element.progress('demo', n + '%'); //可配合 layui 进度条元素使用
                if(n == 100){
                    layer.msg('上传完毕', {icon: 1});
                }
            }
        });

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            var datas=data.field;//form单中的数据信息
            //向后台发送数据提交添加
            $.ajax({
                url:"/addNews",
                type:"POST",
                data: {//提交数据格式
                    photoUrl:datas.photoUrl,
                    title:datas.title,
                    newsType:datas.newsType,
                    content:editor.txt.text()  //编辑器的内容，返回类型为text
                },
                success:function(result){
                    if(result.code==0){//如果成功
                        layer.msg("添加成功",{
                            icon:6,
                            time:500
                        },function(){  //添加成功后重载表格？？？
                            parent.window.location.reload();
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                        })
                    }else{
                        layer.msg("添加失败");
                    }
                }
            })
            return false;
        });

        //加载新闻类别下拉框
        $.ajax({
            url: '/newsTypeAll2',
            dataType: 'json',
            type: 'get',
            success: function (data) {
                console.log(data.data)
                var datas = data.data;
                for (var i = 0; i < datas.length; i++) {
                    $('#newsType').append('<option value="'+datas[i]+'" >'+datas[i]+'</option>')
                }

                form.render('select');
                //重新渲染 固定写法
            }
        });

        //富编辑器
        var editor = new wangEditor('#editor');

        editor.customConfig.uploadImgServer = "/library/api/upload.json";// 配置图片上传server接口地址，返回一个json接口的文件
        editor.customConfig.uploadFileName = 'image';
        editor.customConfig.pasteFilterStyle = false;
        editor.customConfig.uploadImgMaxLength = 5;
        editor.customConfig.uploadImgHooks = {
            // 上传超时
            timeout: function (xhr, editor) {
                layer.msg('上传超时！')
            },
            // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
            customInsert: function (insertImg, result, editor) {
                console.log(result);
                if (result.code == 1) {
                    var url = result.data.url;
                    url.forEach(function (e) {
                        insertImg(e);
                    })
                } else {
                    layer.msg(result.msg);
                }
            }
        };
        editor.customConfig.customAlert = function (info) {
            layer.msg(info);
        };
        editor.create();

    });
</script>
</body>
</html>