
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户评价</title>

    <!-- 引入 Bootstrap -->
    <%--<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel = "stylesheet" href="${ctx}/static/pro/css/bootstrap.min.css">
    <link href="${ctx}/static/pro/style/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx}/static/pro/js/public.js"></script>
    <script type="text/javascript" src="${ctx}/static/pro/js/jquery-1.6.2.js"></script>
    <script type="text/javascript" src="${ctx}/static/pro/js/jqpublic.js"></script>
    <script type="text/javascript" src="${ctx}/static/pro/js/jquery-1.4.2.js"></script>
</head>
<body>
<jsp:include page="../base/head.jsp"></jsp:include>
<form action="/saveEva" method="post">
    <input type="hidden" name="orderId" value="${o}"/>
    <input type="hidden" name="productName" value="${p}"/>
    <input type="hidden" name="status" value="${status}">
    <table border="1" width="60%" align="center"  class="table table-striped">
        <tr >
            <th class="tb3_td2 GoBack_Buy FontW">订单编号</th>
            <th class="tb3_td2 GoBack_Buy Font16">${o}</th>
        </tr>
        <tr class="tb1_td1" >
            <th class="tb3_td2 GoBack_Buy FontW">商品名称</th>
            <th class="tb3_td2 GoBack_Buy Font16">${p}</th>
        </tr>
        <tr>
            <td colspan="2" align="center" class="tb3_td2 GoBack_Buy FontW">评价内容</td>
        </tr>
        <tr>
            <td colspan="2" align="center" height="30">
                <textarea id="evaContent" cols="80" rows="10" name="content" onclick="this.focus();this.select()">
                    请输入评价内容
                </textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input class="btn btn-info" type="submit" value="提交评论"></td>
        </tr>
    </table>
</form>
</body>
</html>
