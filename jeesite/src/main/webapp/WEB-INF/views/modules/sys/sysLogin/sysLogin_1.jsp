<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
	<meta name="decorator" content="blank"/>

    <link href="${ctxStatic}/login/sysLogin_1/css/default.css" rel="stylesheet" type="text/css" />
	<!--必要样式-->
    <link href="${ctxStatic}/login/sysLogin_1/css/styles.css" rel="stylesheet" type="text/css" />
    <link href="${ctxStatic}/login/sysLogin_1/css/demo.css" rel="stylesheet" type="text/css" />
    <link href="${ctxStatic}/login/sysLogin_1/css/loaders.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<div class='login'>
	  <div class='login_title'>
	    <span>${fns:getConfig('productName')}</span>
	  </div>
	  <div class='login_fields'>
	    <div class='login_fields__user'>
	      <div class='icon'>
	        <img alt="" src='img/user_icon_copy.png'>
	      </div>
	      <input name="login" placeholder='用户名' maxlength="16" type='text' autocomplete="off" value="kbcxy"/>
	        <div class='validation'>
	          <img alt="" src='img/tick.png'>
	        </div>
	    </div>
	    <div class='login_fields__password'>
	      <div class='icon'>
	        <img alt="" src='img/lock_icon_copy.png'>
	      </div>
	      <input name="pwd" placeholder='密码' maxlength="16" type='text' autocomplete="off">
	      <div class='validation'>
	        <img alt="" src='img/tick.png'>
	      </div>
	    </div>
	    <div class='login_fields__password'>
	      <div class='icon'>
	        <img alt="" src='img/key.png'>
	      </div>
	      <input name="code" placeholder='验证码' maxlength="4" type='text' name="ValidateNum" autocomplete="off">
	      <div class='validation' style="opacity: 1; right: -5px;top: -3px;">
          <canvas class="J_codeimg" id="myCanvas" onclick="Code();">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
	      </div>
	    </div>
	    <div class='login_fields__submit'>
	      <input type='button' value='登录'>
	    </div>
	  </div>
	  <div class='success'>
	  </div>
	  <div class='disclaimer'>
	    <p>Powered By - LiYuan ${fns:getConfig('version')} </p>
	  </div>
	</div>
	<div class='authent'>
	  <div class="loader" style="height: 44px;width: 44px;margin-left: 28px;">
        <div class="loader-inner ball-clip-rotate-multiple">
            <div></div>
            <div></div>
            <div></div>
        </div>
        </div>
	  <p>认证中...</p>
	</div>
	<div class="OverWindows"></div>
    <link href="${ctxStatic}/layui/css/layui.css" rel="stylesheet" type="text/css" />
	<script src="${ctxStatic}/jquery/jquery-2.1.1.min.js"></script>
	<script src="${ctxStatic}/jquery-ui/jquery-ui.min.js" type="text/javascript" ></script>
	<script src='${ctxStatic}/login/sysLogin_1/js/stopExecutionOnTimeout.js?t=1' type="text/javascript" ></script>
    <script src="${ctxStatic}/layui/layui.js" type="text/javascript"></script>
    <script src="${ctxStatic}/login/sysLogin_1/js/Particleground.js" type="text/javascript"></script>
    <script src="${ctxStatic}/login/sysLogin_1/js/Treatment.js" type="text/javascript"></script>
    <script src="${ctxStatic}/jquery-ui/jquery.mockjax.js" type="text/javascript"></script>
	<script type="text/javascript">
		var canGetCookie = 0;//是否支持存储Cookie 0 不支持 1 支持
		var ajaxmockjax = 1;//是否启用虚拟Ajax的请求响 0 不启用  1 启用
		
		var CodeVal = 0;
	    Code();
	    function Code() {
			if(canGetCookie == 1){
				createCode("AdminCode");
				var AdminCode = getCookieValue("AdminCode");
				showCheck(AdminCode);
			}else{
				showCheck(createCode(""));
			}
	    }
	    function showCheck(a) {
			CodeVal = a;
	        var c = document.getElementById("myCanvas");
	        var ctx = c.getContext("2d");
	        ctx.clearRect(0, 0, 1000, 1000);
	        ctx.font = "80px 'Hiragino Sans GB'";
	        ctx.fillStyle = "#E8DFE8";
	        ctx.fillText(a, 0, 100);
	    }
	    $(document).keypress(function (e) {
	        // 回车键事件  
	        if (e.which == 13) {
	            $('input[type="button"]').click();
	        }
	    });
	    //粒子背景特效
	    $('body').particleground({
	        dotColor: '#E8DFE8',
	        lineColor: '#133b88'
	    });
	    $('input[name="pwd"]').focus(function () {
	        $(this).attr('type', 'password');
	    });
	    $('input[type="text"]').focus(function () {
	        $(this).prev().animate({ 'opacity': '1' }, 200);
	    });
	    $('input[type="text"],input[type="password"]').blur(function () {
	        $(this).prev().animate({ 'opacity': '.5' }, 200);
	    });
	    $('input[name="login"],input[name="pwd"]').keyup(function () {
	        var Len = $(this).val().length;
	        if (!$(this).val() == '' && Len >= 5) {
	            $(this).next().animate({
	                'opacity': '1',
	                'right': '30'
	            }, 200);
	        } else {
	            $(this).next().animate({
	                'opacity': '0',
	                'right': '20'
	            }, 200);
	        }
	    });
	    var open = 0;
	    layui.use('layer', function () { 
	        //非空验证
	        $('input[type="button"]').click(function () {
	            var login = $('input[name="login"]').val();
	            var pwd = $('input[name="pwd"]').val();
	            var code = $('input[name="code"]').val();
	            if (login == '') {
	                ErroAlert('请输入您的账号');
	            } else if (pwd == '') {
	                ErroAlert('请输入密码');
// 	            } else if (code == '' || code.length != 4) {
// 	                ErroAlert('输入验证码');
	            } else {
	                //认证中..
	                fullscreen();
	                $('.login').addClass('test'); //倾斜特效
	                setTimeout(function () {
	                    $('.login').addClass('testtwo'); //平移特效
	                }, 300);
	                setTimeout(function () {
	                    $('.authent').show().animate({ right: -320 }, {
	                        easing: 'easeOutQuint',
	                        duration: 600,
	                        queue: false
	                    });
	                    $('.authent').animate({ opacity: 1 }, {
	                        duration: 200,
	                        queue: false
	                    }).addClass('visible');
	                }, 500);

	                //登陆
	                var JsonData = { username: login, password: pwd, code: code };
					//此处做为ajax内部判断
					var url = "";
// 					if(JsonData.login == truelogin && JsonData.pwd == truepwd && JsonData.code.toUpperCase() == CodeVal.toUpperCase()){
						url = "";
// 					}
					
					
	                AjaxPost("a/login", JsonData, function () {},
	                         function (data) {

	                        	 data = JSON.parse(data);

	                             //ajax返回 
	                             //认证完成
	                             setTimeout(function () {
	                                 $('.authent').show().animate({ right: 90 }, {
	                                     easing: 'easeOutQuint',
	                                     duration: 600,
	                                     queue: false
	                                 });
	                                 $('.authent').animate({ opacity: 0 }, {
	                                     duration: 200,
	                                     queue: false
	                                 }).addClass('visible');
	                                 $('.login').removeClass('testtwo'); //平移特效
	                             }, 2000);
	                             setTimeout(function () {
	                                 $('.authent').hide();
	                                 $('.login').removeClass('test');
	                                 
	                                 if (data.Status == 'ok') {
	                                     //登录成功
	                                     $('.login div').fadeOut(100);
	                                     $('.success').fadeIn(1000);
	                                     $('.success').html(data.msg); 
	                                     //跳转操作
	                                     window.location.href="http://localhost:80/MySpringMVC/views/modules/sys/sysIndex.jsp";
	                                     

	                                 } else {
	                                     AjaxErro(data);
	                                 }
	                             }, 2400);
	                         })
	            }
	        })
	    })
	    var fullscreen = function () {
	        elem = document.body;
	        if (elem.webkitRequestFullScreen) {
	            elem.webkitRequestFullScreen();
	        } else if (elem.mozRequestFullScreen) {
	            elem.mozRequestFullScreen();
	        } else if (elem.requestFullScreen) {
	            elem.requestFullscreen();
	        } else {
	            //浏览器不支持全屏API或已被禁用  
	        }
	    }  
    </script>
</body>
</html>
