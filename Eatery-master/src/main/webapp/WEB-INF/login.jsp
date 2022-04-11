<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="zh-cn">
    <head>
        <meta charset="utf-8">
        <title>订餐系统 | 登录</title>
        <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
        <link   rel="icon" href="${ctx}/favicon.ico" type="image/x-icon" />
		<link   rel="shortcut icon" href="${ctx}/favicon.ico" />
        <!-- CSS -->
        <link rel="stylesheet" href="${ctx}/css/login/reset.css"/>
        <link rel="stylesheet" href="${ctx}/css/login/supersized.css"/>
        <link rel="stylesheet" href="${ctx}/css/login/style.css"/>

        <!--[if lt IE 9]>
            <script src="${ctx}/js/common/html5shiv.js"></script>
        <![endif]-->
		<style>
			canvas{position: fixed; top: 0px; left: 0px; }
		</style>
    </head>



    <body id="body">
	    <!--特效js-->
    	<script src="${ctx}/js/texiao.js"></script>


        <div class="page-container">
            <h1>Login</h1>
			<br><br>
            <h1>欢迎订餐，这里美食多多，总有一款满足您</h1>
            <form id="_form" action="loginLogin" method="post">
                <input type="text" name="username" class="username" id="username" value="" placeholder="Account" required>
                <input type="password" name="password" class="password" value="" placeholder="Password" required>
                <div style="text-align: left; margin-left: 10px;">
                <label>
					<span></span>
					<c:if test="${!empty errorInfo}">
						<span type="text"  id="errorInfo">用户名或密码错误~~</span>
					</c:if>
				</label>





                </div>
                <button type="submit" id="login">登录</button>
                <a href="loginToRegister"><button type="button" id="register" class="register"  href="loginToRegister">注册</button></a>


                <div class="error"><span>+</span></div>
            </form>
        </div>
        <!-- Javascript -->
        <!--
         <script  src="${ctx}/js/common/MD5.js"></script>
        <

        -->
		<script  src="${ctx}/js/jquery.js"></script>
		<script  src="${ctx}/js/jquery.validate.min.js"></script>
		<script  src="${ctx}/js/messages_zh.js"></script>



		<script  src="${ctx}/js/common/jquery/jquery1.8.3.min.js"></script>
		<script  src="${ctx}/js/common/layer/layer.js"></script>
		<script  src="${ctx}/js/common/supersized.3.2.7.min.js"></script>
		<script  src="${ctx}/js/common/supersized-init.js"></script>



		<script type="text/javascript">
			$(".username").click(function(){
				$("#errorInfo").hide();
			});
			$(".password").click(function(){
				$("#errorInfo").hide();
			});

		</script>

        <script >

			$("#_form").validate({

			});
			jQuery(document).ready(function() {



				//登录操作
			    $('#login').click(function(){
			    	
			        var username = $('.username').val();
			        var password = $('.password').val();
			        if(username == '') {
			            $('.error').fadeOut('fast', function(){
			                $('.error').css('top', '27px').show();
			            });
			            $('.error').fadeIn('fast', function(){
			                $('.username').focus();
			            });
			            return false;
			        }
			        if(password == '') {
			            $('.error').fadeOut('fast', function(){
			                $('.error').css('top', '96px').show();
			            });
			            $(this).find('.error').fadeIn('fast', function(){
			                $('.password').focus();
			            });
			            return false;
			        }



					//ajax与后台交互
			        var pswd = MD5(username +"#" + password),
			        	data = {pswd:pswd,email:username,rememberMe:$("#rememberMe").is(':checked')};
			        var load = layer.load();
			        
			        $.ajax({
			        	url:"${ctx}/u/submitLogin.shtml",
			        	data:data,
			        	type:"post",
			        	dataType:"json",
			        	beforeSend:function(){
			        		layer.msg('开始登录，请注意后台控制台。');
			        	},
			        	success:function(result){
				        	layer.close(load);
				    		if(result && result.status != 200){
				    			layer.msg(result.message,function(){});
				    			$('.password').val('');
				    			return;
				    		}else{
				    			layer.msg('登录成功！');
				    			setTimeout(function(){
				    				//登录返回
					    			window.location.href= result.back_url || "${ctx}/";
				    			},1000)
				    		}
			        	}

			        });
			    });
			    $('.page-container form .username, .page-container form .password').keyup(function(){
			        $(this).parent().find('.error').fadeOut('fast');
			    });


			    //注册
			    $("#register").click(function(){
			    	window.location.href="loginToRegister";
			    });


			});
        </script>
    </body>

</html>

