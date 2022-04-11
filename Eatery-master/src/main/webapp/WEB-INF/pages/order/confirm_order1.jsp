<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base/base.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>确认订单</title>

    <link href="${ctx}/static/pro/style/style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/back/assets/css/ace.min.css"/>
    <link href="${ctx }/css/common/pay.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctx}/static/pro/js/public.js"></script>
    <script type="text/javascript" src="${ctx}/static/pro/js/jquery-1.6.2.js"></script>
    <script type="text/javascript" src="${ctx}/static/pro/js/jqpublic.js"></script>

</head>
<body>


<%--头--%>
<jsp:include page="../base/head.jsp"></jsp:include>
<jsp:include page="getLocation.jsp"></jsp:include>
<form action="order" method="post">
    <!--如果用户未添加收货地址，则显示如下-->
    <div class="confirm_addr_f" style="margin:auto">
        <!--如果未添加地址，则显示此表单-->

        <table cellpadding="0" cellspacing="0" class="gwc_tb2" style="width:100%;height:80px">
            <tr>
                <td><span class="flow_title">收货地址：</span></td>
            </tr>
            <tr>
                <td><textarea id="addresinfo" name="addresinfo">${user.userInfo.address}</textarea></td>
            </tr>
        </table>
    </div>
    <div id='container'></div>
    <div id="tip"></div>
    <div id="iCenter"></div>

    <!--配送方式及支付，则显示如下-->
    <!--check order or add other information-->
    <dl class="payment_page">
        <dd class="payment_page_name">

        </dd>
        </div>

        <div class="inforlist" style="margin:auto">
            <span class="flow_title">商品清单</span>
            <table cellpadding="0" cellspacing="0" class="gwc_tb2">
                <th></th>
                <th>数量</th>
                <th>价格</th>
                <th>小计</th>
                <c:set var="totalMoney" value="0"></c:set>
                <c:forEach items="${productList}" var="p">
                    <tr>
                        <td colspan="4"><input value="${p.productId}" name="productId" type="hidden"></td>
                    </tr>
                    <tr>
                        <td><a href="toDetail" title="${p.productName}" target="_blank"
                               name="productName">${p.productName}</a></td>
                        <td><a name="buyNum">${p.buyCount}</a></td>
                        <td><a name="price">￥${p.sprice}</a></td>
                        <td><a name="">￥${p.sprice*p.buyCount}</a></td>
                        <c:set var="totalMoney" value="${totalMoney+p.sprice*p.buyCount}"></c:set>
                    </tr>
                </c:forEach>
            </table>
            <div class="Sum_infor">
                <a class="p2" name="money">合计：<span>￥${totalMoney}</span></a>
                <p></p>
                <input type="submit" value="提交订单" class="p3button">
            </div>
        </div>
        </div>

        </section>
    </dl>
    <input type="hidden" name="totalMoney" value="${totalMoney}">
</form>
<script>
</script>
<!--End content-->
<%--头--%>
<jsp:include page="../base/foot.jsp"></jsp:include>
</body>
</html>
