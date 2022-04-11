<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html lang="en" class="no-js">

<head>
	<meta charset="utf-8">
	<title>订餐系统 | 注册</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">


		<link rel="stylesheet" href="${ctx}/css/login/reset.css"/>
		<link rel="stylesheet" href="${ctx}/css/login/supersized.css"/>
		<link rel="stylesheet" href="${ctx}/css/login/style.css"/>
		<style>
			#vcode >img{cursor:pointer;margin-bottom: -15px;border-radius:5px;}
		</style>



		<!--[if lt IE 9]>
		<script src="${ctx}/js/common/html5shiv.js"></script>
		<![endif]-->
		</head>

<body id="body"   style="overflow: hidden">

<div class="page-container" style="margin: 100px auto 0px;">
	<h1>注册界面</h1>
	<form id="_form" action="/registerUser" method="post">

		<input type="text" name="username" id="username" class="username" placeholder="昵称">
		<input type="text" name="userInfo.email"  id="email" placeholder="电子邮箱" >
		<input type="password" name="upassword" id="password" class="password" placeholder="密码">
		<input type="password" name="rePassword" id="re_password"  placeholder="确认密码">
		<input type="text" name="userInfo.telephone" id="telephone" placeholder="电话号码">

		<div style="text-align: left; margin-left: 10px;" id="vcode">
			<input type="text" name="vcode"   id="inputVcode"  placeholder="验证码" style="width: 110px; margin-left: 7px; margin-right: 8px;">
			<img src="${ctx}/getVCode" />

		</div>

		<button type="submit" class="register">注册</button>
		<button type="button" id="login" >登录</button>
		<div class="error"><span>+</span></div>

	</form>
</div>

<!-- Javascript -->
<script type="text/javascript" src="${ctx}/js/texiao.js"></script>
<script type="text/javascript" src="${ctx}/js/common/jquery/jquery-1.4.2.js"></script>
<script  src="${ctx}/js/common/jquery/jquery1.8.3.min.js"></script>
<script  src="${ctx}/js/common/MD5.js"></script>
<script  src="${ctx}/js/common/supersized.3.2.7.min.js"></script>
<script  src="${ctx}/js/common/supersized-init.js"></script>
<script  src="${ctx}/js/common/layer/layer.js"></script>


<script  src="${ctx}/js/jquery.js"></script>
<script  src="${ctx}/js/jquery.validate.min.js"></script>
<script  src="${ctx}/js/messages_zh.js"></script>





<script >
	jQuery(document).ready(function() {

		$("#email").mouseleave(function () {
			var inputEmail = $("#email").val();
			var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
			if(inputEmail==""){
				return;
			}
				if(reg.test(inputEmail)){
					/*layer.msg("邮箱可以使用",function(){}),!1;*/
				}else{
					layer.msg("邮箱格式错误",function(){}),!1;
				}

		});




		$("#username").mouseleave(function(){
			var username = $("#username").val();

			$.post("registerQuery",
					{"username": username}, function(result){
						if(result=="123"){
							layer.msg("用户已存在",function(){}),!1;
						}
					});
		});


		$("#inputVcode").mouseleave(function () {
			var inputCode = $("#inputVcode").val();

			$.post("inputCode",
					{"inputCode": inputCode}, function(result){
						if(inputCode==""){
							return;
						}
						if(result=="456"){
							layer.msg("验证码错误",function(){}),!1;
						}else{

						}
					});

		});






		//验证码
		$("#vcode").on("click",'img',function(){
			var i = new Image();
			i.src = '${ctx}/getVCode?'  + Math.random();
			$(i).replaceAll(this);
			//$(this).clone(true).attr("src",'${ctx}/open/getGifCode.shtml?'  + Math.random()).replaceAll(this);
		});


		$('.register').click(function(){
			var form = $('#_form');
			var error= form.find(".error");
			var tops = ['27px','96px','165px','235px','304px','372px'];
			var inputs = $("form :text,form :password");
			for(var i=0;i<inputs.length;i++){
				var self = $(inputs[i]);
				if(self.val() == ''){
					error.fadeOut('fast', function(){
						$(this).css('top', tops[i]);
					});
					error.fadeIn('fast', function(){
						self.focus();
					});
					return !1;
				}
			}
			var re_password = $("#re_password").val();
			var password = $("#password").val();
			if(password != re_password){
				return layer.msg('2次密码输出不一样！',function(){}),!1;
			}
			if($('[name=vcode]').val().length !=4){
				return layer.msg('验证码的长度为4位！',function(){}),!1;
			}



			/*var load = layer.load();*/
			$.post("${ctx}/userUpdate.jsp",$("#_form").serialize() ,function(result){
				layer.close(load);
				if(result && result.status!= 200){
					return layer.msg(result.message,function(){}),!1;
				}else{
					layer.msg('注册成功！' );
					window.location.href= result.back_url || "${ctx}/";
				}
			},"json");

		});

		$("form :text,form :password").keyup(function(){
			$(this).parent().find('.error').fadeOut('fast');
		});

		//跳转
		$("#login").click(function(){
			window.location.href="registerToLogin";
		});



	});
</script>


</body>


</html>

