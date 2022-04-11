<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${pageContext.request.contextPath}/back/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/font-awesome.min.css" />
    <link href="${pageContext.request.contextPath}/back/assets/css/codemirror.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/font/css/font-awesome.min.css" />
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/ace-ie.min.css" />
    <![endif]-->
    <!--[if IE 7]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/font-awesome-ie7.min.css" />
    <![endif]-->
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/ace-ie.min.css" />
    <![endif]-->
    <script src="${pageContext.request.contextPath}/back/assets/js/ace-extra.min.js"></script>
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/back/assets/js/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/back/assets/js/respond.min.js"></script>
    <![endif]-->
    <!--[if !IE]> -->
    <script src="${pageContext.request.contextPath}/back/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <!-- <![endif]-->
    <script src="${pageContext.request.contextPath}/back/assets/js/bootstrap.min.js"></script>

    <script src="${pageContext.request.contextPath}/back/js/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/js/jquery-3.0.0.min.js"></script>

    <title>订单信息</title>
</head>

<body>
<div class=" page-content clearfix">
    <div class="transaction_style">


    </div>
    <div class="t_Record">
        <div class="title_name">购物产品所占比例</div>
        <hr/>
        <div id="main" style="height:400px; overflow:hidden; width:100%; overflow:auto" >
            <div id="ph1" style="width: 1100px;height:300px;float: left"></div>
            <div id="ph2" style="width:800px;height:300px;float: left"> 这儿就用订单的地方</div>
    </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    //alert("aaaa");
    var myChart = echarts.init(document.getElementById('ph1'));
    //alert(myChart);
    // 显示标题，图例和空的坐标轴
    myChart.setOption({
        title: {
            text: '销售榜单'
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: []
        }]
    });

    myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画

    var names=[];    //类别数组（实际用来盛放X轴坐标值）
    var nums=[];    //销量数组（实际用来盛放Y坐标值）
    //alert("ajax before");
    $.ajax({
        type : "post",
        async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "buyCount",    //请求发送到TestServlet处
        data : {},
        dataType : "json",        //返回数据形式为json
        success : function(result) {
            //alert("ajax ok");
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result) {
                for(var i=0;i<result.length;i++){
                    names.push(result[i].productName);    //挨个取出类别并填入类别数组
                    /*alert(result[i].product_name);*/
                }
                for(var i=0;i<result.length;i++){
                    nums.push(result[i].buyCount);    //挨个取出销量并填入销量数组
                    /*alert("nums ok");*/
                }
                myChart.hideLoading();    //隐藏加载动画
                myChart.setOption({        //加载数据图表
                    xAxis: {
                        data: names
                    },
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '销量',
                        data: nums,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            },
                            normal:{
                                label : {
                                    show : true,
                                    position : 'top',
                                    formatter : "{b} {c}",
                                    textStyle : {
                                        color: 'red'
                                    }
                                }
                            }
                        }
                    }]
                });

            }

        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            //alert("图表请求数据失败!");
            myChart.hideLoading();
        }
    });
    var myChart2 = echarts.init(document.getElementById('ph2'));
   // alert("right start");
    // 显示标题，图例和空的坐标轴
    myChart2.setOption({
        title : {
            text : '订单所在区域占比',
            subtext : '地点',
            x : 'center'
        },
        tooltip : {
            trigger : 'item',
            formatter : "{a} {b} : {c} ({d}%)"
        },
        legend : {
            orient : 'vertical',
            left : 'left',
            data : []
        },
        series : [ {
            name : '区域来源',
            type : 'pie',
            radius : '55%',
            center : [ '50%', '60%' ],
            data : [],
            itemStyle : {
                emphasis : {
                    shadowBlur : 10,
                    shadowOffsetX : 0,
                    shadowColor : 'rgba(0, 0, 0, 0.5)'
                }
            }
        } ]
    });
    //alert("right load");
    myChart2.showLoading();// 加载动画
    //异步加载数据
    //$.post毛病多，别用，不然无法获取到data[i]中的值
    $.ajax({
        type : "post",
        async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "buyAddr",    //请求发送到TestServlet处
        data : {},
        dataType : "json",        //返回数据形式为json
        success : function(data) {
            /*alert(data[2].addresinfo);*/
            var array = [];

            for (i = 0; i < data.length; i++) {
                //alert(str.name[i]+"===========》"+str.id[i]);
                var map = {};
                /*alert(data[i].addresinfo);*/
                map.name = data[i].addresinfo;
                map.value = data[i].count;
                array[i] = map;
            }
            //alert(array);
            myChart2.hideLoading();
            // 填入数据
            myChart2.setOption({
                legend : {
                    data : array.addresinfo
                },
                series : [ {
                    // 根据名字对应到相应的系列
                    name : '区域来源',
                    type : 'pie',
                    radius: '55%',
                    data : array,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        },
                        normal:{
                            label:{
                                show: true,
                                formatter: '{b} : {c} ({d}%)'
                            },
                            labelLine :{show:true}
                        }
                    }
                    /*  roseType: 'angle',
                     label: {
                     normal: {
                     textStyle: {
                     color: 'rgba(255, 255, 255, 0.3)'
                     }
                     }
                     },
                     labelLine: {
                     normal: {
                     lineStyle: {
                     color: 'rgba(255, 255, 255, 0.3)'
                     }
                     }
                     },
                     itemStyle: {
                     normal: {
                     color: '#c23531',
                     shadowBlur: 200,
                     shadowColor: 'rgba(0, 0, 0, 0.5)'
                     }
                     }  */

                } ],
            });
        }
    });
    //----------------------------------------------------------------------------------
    $(document).ready(function(){

        $(".t_Record").width($(window).width()-60);
        //当文档窗口发生改变时 触发
        $(window).resize(function(){
            $(".t_Record").width($(window).width()-60);
        });
    });


    require.config({
        paths: {
            echarts: './assets/dist'
        }
    });
    require(
        [
            'echarts',
            'echarts/theme/macarons',
            'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
            'echarts/chart/bar'
        ],
        function (ec,theme) {
            var myChart = ec.init(document.getElementById('main'),theme);
            option = {
                title : {
                    text: '月购买订单交易记录',
                    subtext: '实时获取用户订单购买记录'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['所有订单','待付款','已付款','代发货']
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'所有订单',
                        type:'bar',
                        data:[120, 49, 70, 232, 256, 767, 1356, 1622, 326, 200,164, 133],
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        }
                    },
                    {
                        name:'待付款',
                        type:'bar',
                        data:[26, 59, 30, 84, 27, 77, 176, 1182, 487, 188, 60, 23],
                        markPoint : {
                            data : [
                                {name : '年最高', value : 1182, xAxis: 7, yAxis: 1182, symbolSize:18},
                                {name : '年最低', value : 23, xAxis: 11, yAxis: 3}
                            ]
                        },


                    }
                    , {
                        name:'已付款',
                        type:'bar',
                        data:[26, 59, 60, 264, 287, 77, 176, 122, 247, 148, 60, 23],
                        markPoint : {
                            data : [
                                {name : '年最高', value : 172, xAxis: 7, yAxis: 172, symbolSize:18},
                                {name : '年最低', value : 23, xAxis: 11, yAxis: 3}
                            ]
                        },

                    }
                    , {
                        name:'代发货',
                        type:'bar',
                        data:[26, 59, 80, 24, 87, 70, 175, 1072, 48, 18, 69, 63],
                        markPoint : {
                            data : [
                                {name : '年最高', value : 1072, xAxis: 7, yAxis: 1072, symbolSize:18},
                                {name : '年最低', value : 22, xAxis: 11, yAxis: 3}
                            ]
                        },

                    }
                ]
            };

            myChart.setOption(option);
        }
    );
</script>