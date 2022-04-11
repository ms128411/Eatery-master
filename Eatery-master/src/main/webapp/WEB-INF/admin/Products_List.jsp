<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link href="${pageContext.request.contextPath}/back/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/ace.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/Widget/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link href="${pageContext.request.contextPath}/back/Widget/icheck/icheck.css" rel="stylesheet" type="text/css" />
    <!--[if IE 7]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/font-awesome-ie7.min.css" />
    <![endif]-->
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/ace-ie.min.css" />
    <![endif]-->
    <script src="${pageContext.request.contextPath}/back/js/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/assets/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/assets/js/typeahead-bs2.min.js"></script>
    <!-- page specific plugin scripts -->
    <script src="${pageContext.request.contextPath}/back/assets/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/assets/js/jquery.dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/js/H-ui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/js/H-ui.admin.js"></script>
    <script src="${pageContext.request.contextPath}/back/assets/layer/layer.js" type="text/javascript" ></script>
    <script src="${pageContext.request.contextPath}/back/assets/laydate/laydate.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/Widget/zTree/js/jquery.ztree.all-3.5.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/js/lrtk.js" type="text/javascript" ></script>
    <title>产品列表</title>
</head>
<body>
<form method="post" name="icform">
    <div class=" page-content clearfix">
        <div id="products_style">
            <div class="search_style">
                <div class="title_names">搜索查询</div>
                <ul class="search_content clearfix">
                    <li><label class="l_f">产品名称</label><input name="Pname" type="text"  class="text_add" placeholder="输入菜名"  style=" width:250px"/></li>

                    <li style="width:90px;"><button onclick="formSubmit('toSelect','_self');this.blur();" type="button" class="btn_search"><i class="icon-search"></i>查询</button></li>
                </ul>
            </div>
            <div class="border clearfix">
       <span class="l_f">
        <a onclick="formSubmit('toAdd','_self');this.blur();" title="添加商品" class="btn btn-warning Order_form" ><i class="icon-plus"></i>添加商品</a>

        <a onclick="formSubmit('deleteAllProduct','_self');this.blur();" href="javascript:ovid()" class="btn btn-danger"><i class="icon-trash"></i>批量删除</a>

       </span>

            </div>

            <div class="table_menu_list" id="testIframe">
                <table class="table table-striped table-bordered table-hover" id="sample-table">
                    <thead>
                    <tr>
                        <th width="25px"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
                        <th width="80px">产品编号</th>
                        <th width="250px">产品描述</th>
                        <th width="100px">原价格</th>
                        <th width="100px">产品名称</th>
                        <th width="100px">现价</th>
                        <th width="100px">商品种类</th>
                        <th width="180px">特价</th>

                        <th width="70px">库存数量</th>
                        <th width="200px">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${productList}" var="product">
                        <tr>
                            <td width="25px"><label><input type="checkbox" class="ace" name="productId" value="${product.productId}" ><span class="lbl"></span></label></td>
                            <td width="80px">${product.productId}</td>
                            <td width="250px"><u style="cursor:pointer" class="text-primary" onclick="">${product.description}</u></td>
                            <td width="100px">${product.price}</td>
                            <td width="100px">${product.productName}</td>
                            <td width="100px">${product.sprice}</td>
                            <td width="100px">${product.productKind.name}</td>
                            <td width="180px">${product.special}</td>
                            <td class="td-status"><span class="label label-success radius">${product.buyCount}</span></td>
                            <td class="td-manage">
                                <a title="编辑"  onclick="formSubmit('updateOne','_self');this.blur();"  href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a>
                                <a title="删除" href="/deleteById?thisId=${product.productId}"  class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
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
    /* form处理*/
    /* ----------------------------------------------------------------------汇通处理方式--------------------------------------------打开一个新页面：调用时不加第二个参数 add by tony */
    function formSubmit (url,sTarget){
        document.forms[0].target = sTarget;
        document.forms[0].action = url;
        document.forms[0].submit();
        return true;
    }



</script>
