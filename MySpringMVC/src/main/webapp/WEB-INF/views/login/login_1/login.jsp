<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>lyy-可配置型管理系统</title>

<link href="/MySpringMVC/_urc/jsp/login/login_1/css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/MySpringMVC/_urc/jsp/login/login_1/js/canvas-particle.js"></script>
<script src="/MySpringMVC/_urc/vue/vue.js"></script>
<script src="/MySpringMVC/_urc/jQuery/jquery-1.9.1.js"></script>

<style type="text/css" >

	.fade-enter-active, .fade-leave-active {transition: opacity .5s;}

	.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */{opacity: 0;}

	.login_span P{color:#FF5722;}

</style>

</head>
<body>
	<!-- 背景div -->
	<div id="mydiv">
        <div class="login_box">
            <div class="login_font">
                <img src="/MySpringMVC/_urc/jsp/login/login_1/images/img_sm.png">
            </div>
            <div class="login_form">
                <div class="mod_login">
                    <div class="yzt_qrcode">
                        <a class="wx" id="wx" title="证书登录" style="display: block;"></a>
                    </div>
                    <div class="loginbox" id="loginbox" >
                        <span>可配置型管理系统登录</span>
                        <div class="l_form">
                        	<form>
                        		
	                            <div class="login_span">
		                            <transition name="fade">
	                        			<p v-if="show">{{ message }}</p>
	                        		</transition></div>
	                            <div class="login_input"><input name="username" type="text" class="l_input" v-model="username" /></div>
	                            <div class="login_input"><input name="password" type="password"  class="l_input" v-model="password" /></div>
	                            <div class="login_input"><input name="" type="button" class="l_btn" value="登录" v-on:click="loginButton" /></div>
	                        </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
	</div>
	<script type="text/javascript">
	window.onload = function() {
		
		var browser=navigator.appName;
		var trim_Version=navigator.appVersion.split(";")[1].replace(/[ ]/g,""); 
		
		if(browser=="Microsoft Internet Explorer" && trim_Version == "MSIE6.0"){return;}
		if(browser=="Microsoft Internet Explorer" && trim_Version == "MSIE7.0"){return;}
		if(browser=="Microsoft Internet Explorer" && trim_Version == "MSIE8.0"){return;}
		if(browser=="Microsoft Internet Explorer" && trim_Version == "MSIE9.0"){return;}
	
	    //配置
	    var config = {
	        vx: 2,	//小球x轴速度,正为右，负为左
	        vy: 2,	//小球y轴速度
	        height: 2,	//小球高宽，其实为正方形，所以不宜太大
	        width: 2,
	        count: 300,		//点个数
	        color: "255, 255, 255", 	//点颜色
	        stroke: "255,255,255", 		//线条颜色
	        dist: 6000, 	//点吸附距离
	        e_dist: 10000, 	//鼠标吸附加速距离
	        max_conn: 2 	//点到点最大连接数
	    }
	
	    //调用
	    CanvasParticle(config);
	}
	

	

	</script>
</body>

<script>

	//实例化VUE
	var app = new Vue(
	{ 
		el : '#mydiv',
		data : 
		{
			message  : '',
			username : "papio",
			password : "papio",
			show : false
		},
		created : function(){
			
		},
		methods : 
		{
			loginButton : function(event)
			{
				var self = this;

				$.ajax(
				{
					url  	 : "/MySpringMVC/a/login",
					type 	 : "POST",
					async 	 : true,
					dataType : "json",
					data 	 : {"username" : self.username, "password" : self.password},
					success  : function(result)
					{
						
						if(result.status == "1"){self.message = result.message;self.show = true;}

						if(result.status == "0")
						{
							self.show = false;
							$(window).attr('location','/MySpringMVC/views/main/main.jsp');
						}
						
					},
					error : function(res){}
				})
			}
		}
	})
	
</script>

</html>
