<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/js/html5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/js/respond.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/js/PIE_IE678.js"></script>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/back/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/style.css"/>
    <link href="${pageContext.request.contextPath}/back/assets/css/codemirror.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/Widget/zTree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/font-awesome.min.css"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/back/Widget/icheck/icheck.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/back/Widget/webuploader/0.1.5/webuploader.css" rel="stylesheet"
          type="text/css"/>

    <title>新增图片</title>
</head>
<body>
<div class="clearfix" id="add_picture">
    <div class="page_right_style">
        <div class="widget-header header-color-green2">
            <h4 class="lighter smaller">修改商品</h4>
        </div>
        <form action="" method="post" class="form form-horizontal" id="form-article-add">

            <input type="hidden" name="productId" value="${product.productId}"/>
            <div class=" clearfix cl">
                <label class="form-label col-2">菜品描述：</label>
                <div class="formControls col-10"><input type="text" class="input-text" name="description"
                                                        value="${product.description}" placeholder="" id="" name="">
                </div>
            </div>
            <div class="Add_p_s">
                <label class="form-label col-2">商品名称：</label>
                <div class="formControls col-2"><input type="text" class="input-text" name="productName"
                                                       value="${product.productName}"></div>
            </div>
            <div class=" clearfix cl">
                <div class="Add_p_s">
                    <label class="form-label col-2">优惠价格：</label>
                    <div class="formControls col-2"><input type="text" class="input-text" name="price"
                                                           value="${product.price}" placeholder="" id="" name=""></div>
                </div>
                <div class="Add_p_s">
                    <label class="form-label col-2">会员价格：</label>
                    <div class="formControls col-2"><input type="text" class="input-text" name="sprice"
                                                           value="${product.sprice}" placeholder="" id="" name=""></div>
                </div>
                <div class="Add_p_s">
                    <label class="form-label col-2">商品种类：</label>
                    <select name="productKind.productKindId" style="width:121px">
                        <option value="${product.productKindId}" selected="selected">${product.productKindId}</option>

                        <c:forEach items="${list}" var="p">
                            <option value="${p.productKindId}">${p.productKindId}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="Add_p_s">
                    <label class="form-label col-2">特惠价格：</label>
                    <div class="formControls col-2"><input type="text" class="input-text" name="special"
                                                           value="${product.special}" placeholder="" id="" name="">
                    </div>
                    <div class="Add_p_s">
                        <label class="form-label col-2">库存数量：</label>
                        <div class="formControls col-2"><input type="text" class="input-text" name="buyCount" value="${product.buyCount}" ></div>
                    </div>
                </div>
            </div>
            <div class="clearfix cl">
                <div class="Button_operation">
                    <button onclick="formSubmit('toInsert','_self');this.blur();" class="btn btn-primary radius"
                            type="submit"><i class="icon-save "></i>保存并提交审核
                    </button>
                    <button onclick="window.history.go(-1);this.blur();" class="btn btn-default radius" type="button">
                        &nbsp;&nbsp;返回&nbsp;&nbsp;
                    </button>

                </div>

            </div>
        </form>
    </div>
</div>
</div>

<script>

    function formSubmit(url, sTarget) {
        document.forms[0].target = sTarget;
        document.forms[0].action = url;
        document.forms[0].submit();
        return true;
    }
</script>

</body>
</html>