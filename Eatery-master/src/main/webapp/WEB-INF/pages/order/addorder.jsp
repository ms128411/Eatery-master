<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base/base.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>提交订单</title>
<link href="${ctx}/static/pro/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/pro/js/public.js"></script>
<script type="text/javascript" src="${ctx}/static/pro/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/pro/js/jqpublic.js"></script>
<script type="text/javascript" src="${ctx}/static/pro/js/cart.js"></script>
 <script type="text/javascript" src="${ctx}/static/pro/jquery/jquery-1.4.2.js"></script>
 <script>

 </script>

</head>
<body>
<%--头--%>
<jsp:include page="../base/head.jsp"></jsp:include>
<!--Start content-->
<section class="Psection MT20">
 <div class="Reserve Overflow">
 <form action="toConfirmOrder" method="post">
  <table>
  <th>菜品</th>
  <th>数量</th>
  <th>单价</th>
  <th>小计</th>
   <c:set var="totalMoney" value="0"></c:set>
   <c:forEach items="${productList}" var="p">
    <tr>
     <td colspan="4" ><input type="hidden" name="productId" value="${p.productId}"></td>
    </tr>
    <tr>
     <td><a href="toDetail" title="${p.productName}" target="_blank" name="productName">${p.productName}</a></td>
     <td><b>${p.buyCount}</b></td>
     <td>￥${p.sprice}</td>
     <td><b>￥${p.sprice*p.buyCount}</b></td>
     <c:set  var="totalMoney" value="${totalMoney+p.sprice*p.buyCount}"></c:set>
    </tr>
   </c:forEach>
   <tr>
    <td colspan="4" class="FontW CorRed Font14">共计费用：￥${totalMoney}</td>
    </tr>
   <tr>
     <td colspan="2"><input type="submit" name="" value="确认提交订单" class="Submit">
      <td colspan="2" class="FontW CorRed Font14"><a href="tocart"/>返回购物车 </td>
   </tr>
  </table>
  </form>
 </div>
</section>
<%--头--%>
<jsp:include page="../base/foot.jsp"></jsp:include>
</body>
</html>
