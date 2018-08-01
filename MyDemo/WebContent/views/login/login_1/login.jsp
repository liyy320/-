<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>lyy-可配置型管理系统</title>

<link href="../../../_urc/jsp/login/login_1/css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="../../../_urc/jsp/login/login_1/js/canvas-particle.js"></script>

</head>
<body>
	<!-- 背景div -->
	<div id="mydiv">
        <div class="mod_login_top">
            <div class="login_logo"> <img alt="" src="../../../_urc/jsp/login/login_1/images/logo.png"></div><div class="logo_font"><h2>lyy-可配置型管理系统</h2></div><div class="clear"></div>
        </div>
        <div class="login_box">
            <div class="login_font">
                <img src="../../../_urc/jsp/login/login_1/images/img_sm.png">
            </div>
            <div class="login_form">
                <div class="mod_login">
                    <div class="yzt_qrcode">
                        <a class="wx" id="wx" title="证书登录" style="display: block;"></a>
                    </div>
                    <div class="loginbox" id="loginbox" >
                        <span>可配置型管理系统登录</span>
                        <div class="l_form">
                        	<form action ="j_spring_security_check" method="post">
	                            <div class="login_span"><span>用户名或密码错误！</span></div>
	                            <div class="login_input"><input name="j_username" type="text" class="l_input" /></div>
	                            <div class="login_input"><input name="j_password" type="password"  class="l_input" /></div>
	                            <div class="login_input"><input name="" type="submit" class="l_btn" value="登录" /></div>
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
</html>
