<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>新闻编辑</title>
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
            <div class="layui-form-item layui-form ">
                标题：
                <div class="layui-inline">
                    <input class="layui-input" name="title" id="title" autocomplete="off">
                </div>
                作者：
                <div class="layui-inline">
                    <input class="layui-input" name="username" id="username" autocomplete="off">
                </div>
                新闻类别：
                <div class="layui-inline">
                    <select id="newsType" name="newsType" lay-verify="required" lay-search>
                        <option value="">请选择</option>
                    </select>
                </div>
                <button class="layui-btn" data-type="reload">搜索</button>
            </div>
        </div>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i class="layui-icon layui-icon-add-circle"></i> 添加新闻 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"><i class="layui-icon layui-icon-delete"></i> 批量删除 </button>
            </div>
        </script>

        <!--表单，查询出的数据在这里显示-->
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="update">修改</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

    </div>
</div>

<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        //动态获取图书类型的数据，即下拉菜单，跳出图书类型
        // $.get("/newTypeAll",{},function (data) {
        //     var list=data;
        //     console.log(data)
        //     var select=document.getElementById("typeId");
        //     if(list!=null|| list.size()>0){
        //         for(var obj in list){
        //             var option=document.createElement("option");
        //             option.setAttribute("value",list[obj].id);
        //             option.innerText=list[obj].name;
        //             select.appendChild(option);
        //         }
        //     }
        //     form.render('select');
        // },"json")
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

        table.render({
            elem: '#currentTableId',
            url: '/newsInfoAll',//查询类型数据
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50,height:200},
                {field: 'newsID', width: 100, title: 'ID', sort: true},
                {field: 'title', width: 150, title: '标题'},
                {field: 'userName', width: 80, title: '作者'},
                {field:'newsType', width:100,title:'新闻类别'},
                {field: 'content', width: 200, title: '内容'},
                {field: 'date', width: 200, title: '发布时间',sort: true},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,  <!--默认显示15条-->
            page: true,
            skin: 'line',
            id:'testReload'
        });

        var $ = layui.$, active = {
            reload: function(){
                var title = $('#title').val();
                var username = $('#username').val();
                var newsType = $('#newsType').val();
                console.log(title)
                console.log(username)
                console.log(newsType)

                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        title:title,
                        username :username,
                        newsType:newsType
                    },
                    url:"/serachNews",
                    method:"POST"

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
            if (obj.event === 'update') {  // 监听修改操作  ***************************************待修改
                var index = layer.open({
                    title: '新闻修改',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '/newsAlter', //打开编辑页面
                    success:function (layero,index) {  //数据回显
                        var body = layer.getChildFrame('body', index);  //获取子页面的body元素
                        console.log(data);
                        body.find('#newsID').val(data.newsID);
                        body.find('#title').val(data.title);
                        body.find('#newsType').val(data.newsType);
                        body.find('#content').val(data.content)
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                layer.confirm('确定是否删除', function (index) {
                    //调用删除功能
                    console.log(data)
                    deleteInfoByIds(data.newsID,index);
                    layer.close(index);
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
                console.log(data[i].newsID)
                arr.push(data[i].newsID);
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
                url: "/deleteNews",
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
                        layer.msg("删除失败");
                    }
                }
            })
        };

        /**
         * toolbar栏监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作       ***********************************待修改
                var index = layer.open({
                    title: '添加新闻',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '/newsAdd',
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
                    layer.confirm('确定是否删除', function (index) {
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
