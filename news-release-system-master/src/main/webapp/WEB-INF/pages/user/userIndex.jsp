<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户编辑</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/library/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="/library/css/public.css" media="all">
    <script src="/library/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <div class="demoTable">
            <div class="layui-form-item layui-form">
                用户ID：
                <div class="layui-inline">
                    <input class="layui-input" name="userID" id="userID" autocomplete="off">
                </div>
                用户名：
                <div class="layui-inline">
                    <input class="layui-input" name="userName" id="userName" autocomplete="off">
                </div>
                用户类型：
                <div class="layui-inline">
                    <select id="userType" name="userType" class="layui-input">
                        <option value="">请选择</option>
                        <option value="0">游客</option>
                        <option value="1">普通管理员</option>
                        <option value="2">超级管理员</option>
                    </select>
                </div>
                <button class="layui-btn" data-type="reload" id="searchSubmit">搜索</button>
            </div>
        </div>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i class="layui-icon layui-icon-add-circle"></i>添加用户</button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>批量删除</button>
            </div>
        </script>

        <!--表单，查询出的数据在这里显示-->
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">修改密码</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>
        <%--动态显示工具条--%>
        <script type="text/html" id="secondTableBar" >
            {{#  if(d.userType == 0){ }}
            <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="editPower" title="点击修改权限">游客</a>
            {{# } }}
            {{#  if(d.userType == 1){ }}
            <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="editPower" title="点击修改权限" style="background-color: #e78383;">普通管理员</a>
            {{# } }}
            {{#  if(d.userType == 2){ }}
            <button class="layui-btn layui-btn-warm layui-btn-xs" lay-event="editPower" title="点击修改权限" style="background-color: orangered;">超级管理员</button>
            {{# } }}
        </script>

    </div>
</div>

<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;


        table.render({
            elem: '#currentTableId',
            url: '/userAll',//查询全部数据
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 100},
                {field: 'userID', width: 150, title: 'ID', sort: true},
                {field: 'userName', width: 200, title: '用户名'},
                {title: '用户类型', width: 200, toolbar: '#secondTableBar'},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,  <!--默认显示15条-->
            page: true,
            skin: 'line',
            id:'testReload'
        });



        /*查询功能*/
        var $ = layui.$, active = {
            reload: function(){
                var userID = $('#userID').val();
                var userName = $('#userName').val();
                var userType = $('#userType').val();
                console.log(name)
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: { //请求参数
                        userID: userID,
                        userName: userName,
                        userType: userType,
                    },
                    url: "/searchUser",   //请求路径
                    method: "POST"
                }, 'data');
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        /**
         * tool操作栏监听事件
         */
        table.on('tool(currentTableFilter)', function (obj) {
            var data=obj.data;
            if (obj.event === 'edit') {  // 监听修改操作
                var index = layer.open({
                    title: '修改用户密码',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['60%', '60%'],
                    content: '/queryUserById?userID='+data.userID+'&userType='+data.userType,
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                layer.confirm("确定要删除[<font color='red'>"+data.userName+"</font>]用户吗？",{icon:3,title:"提示"}, function (index) {
                    //调用删除功能
                    deleteInfoByIds(data.userID,index);
                    layer.close(index);
                });
            } else if(obj.event === 'editPower'){ //监听修改权限操作
                var index1 = layer.open({
                    title: '修改用户权限',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['60%', '60%'],
                    content: '/queryUserById?userID='+data.userID+'&userType='+data.userType+'&userName='+data.userName,
                });
                $(window).on("resize", function () {
                    layer.full(index1);
                });
            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        /**
         * 获取选中记录的id信息
         */
        function getCheackId(data){
            var arr=new Array();
            for(var i=0;i<data.length;i++){
                arr.push(data[i].userID);
            }
            //拼接id,变成一个字符串
            return arr.join(",");
        };


        /**
         * 提交删除功能
         */
        function deleteInfoByIds(ids ,index){
            //向后台发送请求
            $.ajax({
                url: "deleteUserByIds",
                type: "POST",
                data: {ids: ids},
                success: function (result) {
                    if (result.code == 0) {//如果成功
                        layer.msg('删除成功', {
                            icon: 6,
                            time: 500
                        }, function () {
                            parent.window.location.reload();
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                        });
                    } else {
                        layer.msg("权限不足，删除失败！");
                    }
                }
            })
        };

        /**
         * toolbar栏监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加管理员',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['60%', '60%'],
                    content: '/userAdd',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {
                /*
                  1、提示内容，必须删除大于0条
                  2、获取要删除记录的id信息
                  3、提交删除功能 ajax
                */
                //获取选中的记录信息
                var checkStatus=table.checkStatus(obj.config.id);
                var data=checkStatus.data;
                if(data.length==0){//如果没有选中信息
                    layer.msg("请选择要删除的记录信息");
                }else{
                    //获取记录信息的id集合,拼接的ids
                    var ids=getCheackId(data);
                    layer.confirm("确定要删除这<font color='red'>"+checkStatus.data.length+"</font>个用户吗？", function (index) {
                        //调用删除功能
                        deleteInfoByIds(ids,index);
                        layer.close(index);
                    });
                }
            }
        });

    });
</script>

</body>
</html>
