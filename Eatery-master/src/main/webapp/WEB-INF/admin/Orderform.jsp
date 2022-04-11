<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link href="${pageContext.request.contextPath}/back/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/style.css"/>
    <link href="${pageContext.request.contextPath}/back/assets/css/codemirror.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/font/css/font-awesome.min.css"/>
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/ace-ie.min.css"/>
    <![endif]-->
    <script src="${pageContext.request.contextPath}/back/js/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/js/H-ui.js"></script>
    <script src="${pageContext.request.contextPath}/back/assets/js/typeahead-bs2.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/assets/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/assets/js/jquery.dataTables.bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/back/assets/layer/layer.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/back/assets/laydate/laydate.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/back/assets/js/jquery.easy-pie-chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/js/lrtk.js" type="text/javascript"></script>
    <title>配餐订单管理</title>
</head>

<body>
<form method="post">
    <div class="margin clearfix">
        <div class="table_menu_list order_style" id="table_order_list">

            <table class="table table-striped table-bordered table-hover" id="sample-table">
                <thead>
                <tr>
                    <th width="25px"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
                    <th width="120px">订单编号</th>
                    <th width="250px">产品名称</th>
                    <th width="80px">数量</th>
                    <th width="100px">总价</th>
                    <th width="100px">订单时间</th>
                    <th width="180px">食品类型</th>

                    <th width="70px">状态</th>
                    <th width="200px">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orderList}" var="o">
                    <tr>
                        <td><label><input type="checkbox" class="ace"><span class="lbl"></span></label></td>
                        <td>${o.orderId}</td>
                        <td class="order_product_name">
                            <a href="#">${o.product.productName}</a>
                        </td>
                        <td>${o.productOrder.buyNum}</td>
                        <td>${o.money}</td>
                        <td>${o.orderDate}</td>
                        <td>${o.product.productKind.name}</td>
                        <td class="td-status">
                            <c:if test="${o.orderStatus == 4}">
                        <span class="label label-success radius">订单完成</span>
                            </c:if>
                            <c:if test="${o.orderStatus == 3}">
                        <span class="label label-success radius"><a
                                href="toAnsEva?orderId=${o.orderId}">去回复评论</a></span>
                            </c:if>
                            <c:if test="${o.orderStatus == 2}">
                                <span class="label label-success radius">已收货，待评价</span>
                            </c:if>
                            <c:if test="${o.orderStatus == 1}">
                                <span class="label label-success radius">已发货，待收货</span>
                            </c:if>
                            <c:if test="${o.orderStatus == 0}">
                            <span class="label label-success radius"><a
                                    href="updateAdminOrderStatus?orderId=${o.orderId}&orderStatus=1">已付款、去发货</a></span>
                            </c:if>
                            <c:if test="${o.orderStatus == -1}">
                            <span class="label label-success radius">买家待付款</span>
                            </c:if>

                        </td>
                        <td>
                            <a href="javascript:;" title="处理订单" class="btn btn-xs btn-success"><i
                                    class="fa fa-cubes bigger-120"></i></a>
                            <a title="订单详细" href="${request.getcontextpath()}/orderDetailed?orderId=${o.orderId}"
                               class="btn btn-xs btn-info order_detailed"><i class="fa fa-list bigger-120"></i></a>
                            <a title="删除" href="${request.getcontextpath()}/deleteOrder?orderId=${o.orderId}"
                               class="btn btn-xs btn-warning"><i class="fa fa-trash  bigger-120"></i></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    </div>
    </div>
</form>
</body>
</html>
<script>
    //时间选择
    laydate({
        elem: '#start',
        event: 'focus'
    });


    //初始化宽度、高度
    $(".hide_style").height($(".hide_style").height());
    var heights = $(".hide_style").outerHeight(true) + 90;
    $(".widget-box").height($(window).height() - heights);
    $(".table_menu_list").width($(window).width() - 250);
    $(".table_menu_list").height($(window).height() - heights);
    /*    //当文档窗口发生改变时 触发
        $(window).resize(function(){
            $(".widget-box").height($(window).height()-heights);
            $(".table_menu_list").width($(window).width()-250);
            $(".table_menu_list").height($(window).height()-heights);
        })*/

    $('[data-rel=tooltip]').tooltip();
    $('[data-rel=popover]').popover({html: true});
</script>
<script>
    //订单列表
    jQuery(function ($) {
        var oTable1 = $('#sample-table').dataTable({
            "aaSorting": [[1, "desc"]],//默认第几个排序
            "bStateSave": true,//状态保存
            "aoColumnDefs": [
                //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
                {"orderable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]}// 制定列不参与排序
            ]
        });


        $('table th input:checkbox').on('click', function () {
            var that = this;
            $(this).closest('table').find('tr > td:first-child input:checkbox')
                .each(function () {
                    this.checked = that.checked;
                    $(this).closest('tr').toggleClass('selected');
                });

        });


        $('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});

        function tooltip_placement(context, source) {
            var $source = $(source);
            var $parent = $source.closest('table')
            var off1 = $parent.offset();
            var w1 = $parent.width();

            var off2 = $source.offset();
            var w2 = $source.width();

            if (parseInt(off2.left) &lt; parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
            return 'left';
        }
    });


    /* form处理*/
    /* ----------------------------------------------------------------------汇通处理方式--------------------------------------------打开一个新页面：调用时不加第二个参数 add by tony */
    function formSubmit(url, sTarget) {

        document.forms[0].target = sTarget;
        document.forms[0].action = url;
        document.forms[0].submit();
        return true;
    }
</script>
