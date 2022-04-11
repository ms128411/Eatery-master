<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base/base.jsp"%>
<html>
<head>
    <meta charset="utf-8" />
    <title>菜品分类页</title>

    <link href="${ctx}/static/pro/style/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/pro/style/prodList.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctx}/static/pro/js/public.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-1.6.2.js"></script>
    <script type="text/javascript" src="${ctx}/static/pro/js/jqpublic.js"></script>



</head>
<body>
<form method="post" name="icform">
    <%--头--%>
    <jsp:include page="../base/head.jsp"></jsp:include>
    <!--Start content-->
    <section class="Psection">


        <div id="content">
            <div id="search_div">
                <form method="post" action="findProductByCondition">
                    <span class="input_span">商品名：<input type="text" name="product_name" value=""/></span>
                    <span class="input_span">商品种类：<input type="text" name="name" value=""/></span>
				<span class="input_span">商品价格区间：
					<input type="text" name="minprice" value=""/> -
					<input type="text" name="maxprice" value=""/></span>
                    <input type="submit" value="查 询" >
                    <input type="reset" id="reset" value="重 置">
                </form>
            </div>
        </div>


        <section class="Fsl">
            <ul>
                <c:set var="num" value="0"></c:set>
                <c:forEach items="${productList}" var="productList" varStatus="status">
                        <li>
                            <a href="/toDetail?productId=${productList.productId}"  title="菜名"><img src="${ctx}${productList.pic}"></a>
                            <p>菜名：${productList.productName}</p>
                            <hr>
                            <p>价格:${productList.price}</p>
                            <hr>
                            <p>销量:${productList.buyCount}</p>
                            <p>
                                <span class="DSBUTTON" style="float: right"><a href="/toDetail?productId=${productList.productId}"  class="Fontfff">商品详情</a></span>
                            </p>
                        </li>
                </c:forEach>
            </ul>


            <!--将买的最多的菜放到此处，两个既好-->
            <aside>
                <div class="title">热门菜</div>
                <c:forEach items="${HotProductList}" var="HotProductList" varStatus="status">
                    <div class="C-list">
                        <a href="/toDetail?productId=${HotProductList.productId}" title="菜名"><img src="${ctx}${HotProductList.pic}" width="100%" height="30%"></a>
                        <p><a href="/toDetail?productId=${HotProductList.productId}" >${HotProductList.productName}</a></p>
                        <p>
                            <span>价格：${HotProductList.price}</span>
                        </p>
                    </div>

                </c:forEach>
            </aside>

            <!--分页-->
            <div class="TurnPage">
                <span class="Prev"><i></i>一共${pages.pages}页</span>
                <a href="findProductByCategory?page=${pages.firstPage}&">
                    <span class="Prev"><i></i>首页</span>
                </a>
                <a href="findProductByCategory?page=${pages.prePage}"><span class="PNumber">上一页</span></a>
                <a href="findProductByCategory?page=${pages.nextPage}"><span class="PNumber">下一页</span></a>
                <a href="findProductByCategory?page=${pages.lastPage}">
                    <span class="Next">最后一页<i></i></span>
                </a>
            </div>
        </section>
    </section>

    <%--头--%>
    <jsp:include page="../base/foot.jsp"></jsp:include>
    <!--End content-->
</form>
</body>

</html>
