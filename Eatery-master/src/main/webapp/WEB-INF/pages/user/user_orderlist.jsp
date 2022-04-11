<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>用户中心--订单列表</title>
    <link href="${ctx}/static/pro/style/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/static/pro/js/public.js"></script>
    <script type="text/javascript" src="${ctx}/static/pro/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/static/pro/js/jqpublic.js"></script>

</head>
<body>
<jsp:include page="../base/head.jsp"></jsp:include>
<!--Start content-->
<section class="Psection MT20">
    <nav class="U-nav Font14 FontW">
        <ul>
            <li><i></i><a href="userCenter" target="_self">用户中心首页</a></li>
            <li><i></i><a href="userOrderList" target="_self">我的订单</a></li>
            <li><i></i><a href="userAddress" target="_self">收货地址</a></li>
            <li><i></i><a href="userAccount" target="_self">账户管理</a></li>
            <li><i></i><a href="userLogout" target="_self">安全退出</a></li>
        </ul>
    </nav>
    <article class="U-article Overflow">
        <!--user order list-->
        <section>
            <table class="Myorder">
                <th class="Font14 FontW">序号</th>
                <th class="Font14 FontW">商品名</th>
                <th class="Font14 FontW">下单时间</th>
                <th class="Font14 FontW">收件人</th>
                <th class="Font14 FontW">单价</th>
                <th class="Font14 FontW">订单总金额</th>
                <th class="Font14 FontW">数量</th>
                <th class="Font14 FontW">订单状态</th>
                <c:forEach items="${orderList}" var="ol" varStatus="status">
                    <tr>
                        <td class="FontW">${status.index+1}</td>
                        <td>${ol.product.productName}</td>
                        <td>${ol.orderDate}</td>
                        <td>
                            <c:if test="${!not empty user.userInfo.realName}">
                                尚未填写用户名
                            </c:if>
                            <c:if test="${not empty user.userInfo.realName}">
                                ${user.userInfo.realName}
                            </c:if>

                        </td>
                        <td>${ol.price}</td>
                        <td>${ol.price*ol.productOrder.buyNum}</td>
                        <td>${ol.productOrder.buyNum}</td>
                        <c:if test="${ol.orderStatus == 4}">
                            <td>已完成</td>
                        </c:if>
                        <c:if test="${ol.orderStatus == 3}">
                            <td>已完成</td>
                        </c:if>
                        <c:if test="${ol.orderStatus == 2}">
                            <td><a href="toEvaluate?productName=${ol.product.productName}&orderId=${ol.orderId}&status=3">去评价</a></td>
                        </c:if>
                        <c:if test="${ol.orderStatus == 1}">
                            <td><a href="updateOrderStatus?status=2&orderId=${ol.orderId}">已发货，点击确认收货</a></td>
                        </c:if>
                        <c:if test="${ol.orderStatus == 0}">
                            <td>已付款，待发货</td>
                        </c:if>
                        <c:if test="${ol.orderStatus == -1}">
                            <td>待付款</td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            <div class="TurnPage">
                    <span class="Prev"><i></i>一共${page.pages}页</span>
                <a href="userOrderList?page=${page.firstPage}">
                    <span class="Prev"><i></i>首页</span>
                </a>
                <a href="userOrderList?page=${page.prePage}"><span class="PNumber">上一页</span></a>
                <a href="userOrderList?page=${page.nextPage}"><span class="PNumber">下一页</span></a>
                <a href="userOrderList?page=${page.lastPage}">
                    <span class="Next">最后一页<i></i></span>
                </a>
            </div>
        </section>
    </article>
</section>
<!--End content-->
<jsp:include page="../base/foot.jsp"></jsp:include>

</body>
</html>
