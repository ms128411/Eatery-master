
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/js/html5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/js/respond.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/js/PIE_IE678.js"></script>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/back/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/style.css"/>
    <link href="${pageContext.request.contextPath}/back/assets/css/codemirror.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/ace.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/Widget/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/font-awesome.min.css" />
    <!--[if IE 7]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/font-awesome-ie7.min.css" />
    <![endif]-->
    <link href="${pageContext.request.contextPath}/back/Widget/icheck/icheck.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/back/Widget/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />

    <title>回复评论</title>
</head>
<body>
<div class="clearfix" id="add_picture">
    <div class="page_left_style">
        <div class="widget-header header-color-green2">
            <h4 class="lighter smaller">回复评论</h4>
        </div>

            <div class=" clearfix cl">
                <label class="form-label col-2">订单编号：</label>
                <div class="formControls col-10"><input type="text" class="input-text" name="description" style="width:1000px;" value="${evaluate.orderId}" disabled = "disabled"></div>
            </div><div class=" clearfix cl">
                <label class="form-label col-2">商品名称：</label>
                <div class="formControls col-10"><input type="text" class="input-text" name="description" style="width:1000px;" value="${evaluate.productName}" disabled = "disabled"></div>
            </div><div class=" clearfix cl">
                <label class="form-label col-2">评价内容：</label>
                <div class="formControls col-10"><input type="text" class="input-text" name="description" style="width:1000px;" value="${evaluate.evaContent}" disabled = "disabled"></div>
            </div><div class=" clearfix cl">
                <label class="form-label col-2">评价时间：</label>
                <div class="formControls col-10"><input type="text" class="input-text" name="description" style="width:1000px;" value="${evaluate.evaDate}" disabled = "disabled"></div>
            </div>


            <div class="widget-header header-color-green2">
                <h4 class="lighter smaller">回复留言</h4>
            </div>
        <form action=""  enctype="multipart/form-data" method="post" class="form form-horizontal" id="form-article-add">
           <div>
                <tr>
                    <td height="32px" colspan="4">
                <textarea name="ansContent" id="ansContent" cols="100" rows="10" onclick="this.focus();this.select()">
                </textarea>
                    </td>
                </tr>
           </div>
            <div class="clearfix cl">
                <div class="Button_operation">
                    <button onclick="formSubmit('ansEva?orderId=${evaluate.orderId}&orderStatus=4','_self');this.blur();" class="btn btn-primary radius" type="submit">确认添加</button>
                </div>
            </div>

        </form>

    </div>

    <script>

        function formSubmit (url,sTarget){
            document.forms[0].target = sTarget;
            document.forms[0].action = url;
            document.forms[0].submit();
            return true;
        }
    </script>










<%--


<html>
<head>
    <title>回复评论</title>
</head>
<body>
<table border="1" width="50%">
    <tr>
        <th>订单编号</th>
        <th>商品名称</th>
        <th>评价内容</th>
        <th>评价时间</th>
    </tr>
    <tr>
        <td>${evaluate.orderId}</td>
        <td>${evaluate.productName}</td>
        <td>${evaluate.evaContent}</td>
        <td>${evaluate.evaDate}</td>
    </tr>
    <tr>
        <th colspan="4" align="center">回复留言</th>
    </tr>
    <form action="ansEva?orderId=${evaluate.orderId}&orderStatus=4" method="post">
        <tr>
            <td height="32px" colspan="4">
            <textarea name="ansContent" id="ansContent" cols="100" rows="10">
            </textarea>
            </td>
        </tr>
        <tr>
            <td align="center" colspan="4"><input type="submit" value="确认回复"/></td>
        </tr>
    </form>
</table>

</body>
</html>
--%>
