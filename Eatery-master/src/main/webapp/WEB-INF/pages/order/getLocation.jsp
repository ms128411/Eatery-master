
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>逆地理编码</title>
    <link rel="stylesheet" type="text/css" href="http://developer.amap.com/Public/css/demo.Default.css" />
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=d47fef5297f4acf7e38231b70ef10691"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
    <script language="javascript">

        /***************************************
         由于Chrome、IOS10等已不再支持非安全域的浏览器定位请求，为保证定位成功率和精度，请尽快升级您的站点到HTTPS。
         ***************************************/
        var map, geolocation;
        var mapObj;
        var lng ,lat;
        //加载地图，调用浏览器定位服务
        map = new AMap.Map('container', {
            resizeEnable: true
        });
        map.plugin('AMap.Geolocation', function() {
            geolocation = new AMap.Geolocation({
                enableHighAccuracy: true,//是否使用高精度定位，默认:true
                timeout: 10000,          //超过10秒后停止定位，默认：无穷大
                buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
                zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
                buttonPosition:'RB'
            });
            map.addControl(geolocation);
            geolocation.getCurrentPosition();
            AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
            AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
        });
        //解析定位结果
        function onComplete(data) {
       /*     var str=['定位成功'];
            str.push('经度：' + data.position.getLng());
            str.push('纬度：' + data.position.getLat());*/
            lng = data.position.getLng();
            lat = data.position.getLat();
          /*  if(data.accuracy){
                str.push('精度：' + data.accuracy + ' 米');
            }//如为IP精确定位结果则没有精度信息
            str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
            document.getElementById('tip').innerHTML = str.join('<br>');*/
        }
        function mapInit() {
            mapObj = new AMap.Map("iCenter", {
                view: new AMap.View2D({
                    center:new AMap.LngLat(121.428000,31.197600),//地图中心点
                    zoom:13 //地图显示的缩放级别
                })
            });
        }
        //已知点坐标
        function geocoder() {
            var lnglatXY = new AMap.LngLat(lng,lat);
            var MGeocoder;
            //加载地理编码插件
            mapObj.plugin(["AMap.Geocoder"], function() {
                MGeocoder = new AMap.Geocoder({
                    radius: 1000,
                    extensions: "all"
                });
                //返回地理编码结果
                AMap.event.addListener(MGeocoder, "complete", geocoder_CallBack);
                //逆地理编码
                MGeocoder.getAddress(lnglatXY);
            });
            //加点
            var marker = new AMap.Marker({
                map:mapObj,
                icon: new AMap.Icon({
                    image: "http://api.amap.com/Public/images/js/mark.png",
                    size:new AMap.Size(58,30),
                    imageOffset: new AMap.Pixel(-32, -0)
                }),
                position: lnglatXY,
                offset: new AMap.Pixel(-5,-30)
            });
            mapObj.setFitView();
        }
        //鼠标划过显示相应点
        var marker;
        function onMouseOver (e) {
            var coor = e.split(','),
                lnglat = new AMap.LngLat(coor[0], coor[1]);
            if (!marker) {
                marker = new AMap.Marker({
                    map:mapObj,
                    icon: "http://webapi.amap.com/images/0.png",
                    position: lnglat,
                    offset: new AMap.Pixel(-10, -34)
                });
            } else {
                marker.setPosition(lnglat);
            }
            mapObj.setFitView();
        }
        //回调函数
        function geocoder_CallBack(data) {
            var resultStr = "";
            var roadinfo ="";
            var poiinfo="";
            var address;
            //返回地址描述
            address = data.regeocode.formattedAddress;
            //返回周边道路信息
            roadinfo +="<table style='width:600px'>";
            for(var i=0;i<data.regeocode.roads.length;i++){
                var color = (i % 2 === 0 ? '#fff' : '#eee');
                roadinfo += "<tr style='background-color:" + color + "; margin:0; padding:0;'><td>道路：" + data.regeocode.roads[i].name + "</td><td>方向：" + data.regeocode.roads[i].direction + "</td><td>距离：" + data.regeocode.roads[i].distance + "米</td></tr>";
            }
            roadinfo +="</table>"
            //返回周边兴趣点信息
            poiinfo += "<table style='width:600px;cursor:pointer;'>";
            for(var j=0;j<data.regeocode.pois.length;j++){
                var color = j % 2 === 0 ? '#fff' : '#eee';
                poiinfo += "<tr onmouseover='onMouseOver(\"" + data.regeocode.pois[j].location.toString() + "\")' style='background-color:" + color + "; margin:0; padding:0;'><td>兴趣点：" + data.regeocode.pois[j].name + "</td><td>类型：" + data.regeocode.pois[j].type + "</td><td>距离：" + data.regeocode.pois[j].distance + "米</td></tr>";
            }
            poiinfo += "</table>";
            //返回结果拼接输出
            /*resultStr = "<div style=\"font-size: 12px;padding:0px 0 4px 2px; border-bottom:1px solid #C1FFC1;\">"+"<b>地址</b>："+ address + "<hr/><b></div>";
            */
            //document.getElementById("result").innerHTML = resultStr;
            document.getElementById("addresinfo").innerHTML = address;
        }
    </script>
</head>
<body onload="mapInit();">
</body>
</html>
