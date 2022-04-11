<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户评价列表</title>
    <link rel = "stylesheet" href="${ctx}/static/pro/css/bootstrap.min.css">
    <link href="${ctx}/static/pro/style/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/static/pro/js/public.js"></script>
    <script type="text/javascript" src="${ctx}/static/pro/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/static/pro/js/jqpublic.js"></script>


</head>
<body>
<jsp:include page="../base/head.jsp"></jsp:include>
<table border="1" width="100%" class="table table-hover">
    <tr>
        <th>订单编号</th>
        <th>商品名称</th>
        <th>评价内容</th>
        <th>评价时间</th>
        <th>回复内容</th>
        <th>回复时间</th>
    </tr>
    <c:forEach items="${evaList}" var="el">
        <tr>
        <td>${el.orderId}</td>
        <td>${el.productName}</td>
        <td>${el.evaContent}</td>
        <td>${el.evaDate}</td>
        <td>${el.ansContent}</td>
        <td>${el.ansDate}</td>
        </tr>
    </c:forEach>
</table>
<div class="TurnPage">
    <span class="Prev"><i></i>一共${page.pages}页</span>
    <a href="toEvaList?page=${page.firstPage}">
        <span class="Prev"><i></i>首页</span>
    </a>
    <a href="toEvaList?page=${page.prePage}"><span class="PNumber">上一页</span></a>
    <a href="toEvaList?page=${page.nextPage}"><span class="PNumber">下一页</span></a>
    <a href="toEvaList?page=${page.lastPage}">
        <span class="Next">最后一页<i></i></span>
    </a>
</div>
</body>
</html>
