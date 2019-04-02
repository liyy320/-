<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link href="${ctxStatic}/H-ui/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/H-ui/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/H-ui/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/H-ui/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/layui/css/layui.css" rel="stylesheet">

<title>${fns:getConfig('productName')} 登录</title>

</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header"></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input name="login" type="text" placeholder="用户名" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input name="pwd" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input name="code" class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">
          <img src=""><canvas class="J_codeimg" id="myCanvas" style="width: 118px;height: 40px;">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas><a id="kanbuq" href="javascript:;" onclick="Code();">看不清，换一张</a></div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <label for="online">
            <input type="checkbox" name="online" id="online" value="">
            使我保持登录状态</label>
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input type="button" onclick="submit();" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;注&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;">
        </div>
      </div>
  </div>
</div>
<div class="footer">Copyright MySpringMVC by Liyuan</div>

<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="${ctxStatic}/H-ui/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/config/Treatment.js"></script> 
<script src="${ctxStatic}/layui/layui.js" type="text/javascript"></script>
<script>

	var CodeVal = "";
	$(function(){
		
		Code();
		
		setInputValByName("login", "liyuan");
		setInputValByName("pwd", "admin");
		
	})

	function Code(){
	
		if(canGetCookie == 1){
	
			createCode("AdminCode");
			var AdminCode = getCookieValue("AdminCode");
			showCheck(AdminCode);
		}else{
	
			showCheck(createCode(""));
		}
	}
	
	function showCheck(a){

		setInputValByName("code", a);
		
		CodeVal = a;
       
		var c = document.getElementById("myCanvas");
        var ctx = c.getContext("2d");
        ctx.clearRect(0, 0, 1000, 1000);
        ctx.font = "80px 'Hiragino Sans GB'";
        ctx.fillStyle = "#E8DFE8";
        ctx.fillText(a, 0, 100);
		
	}
	
	function submit(){

		layui.use('layer', function () { 

			 var login = getInputValByName("login");
             var pwd   = getInputValByName("pwd");
             var code  = getInputValByName("code");
			
	        if (login == '') {
	            ErroAlert('请输入您的账号');
	        } else if (pwd == '') {
	            ErroAlert('请输入密码');
	        } else if (code == '' || code.length != 4) {
         		ErroAlert('输入验证码');
	         } else if(code.toUpperCase() != CodeVal.toUpperCase()){
					ErroAlert('验证码输入有误！');
	         } else {
	        	 
	        	//登陆
	           	var JsonData = { username: login, password: pwd, code: code, mobileLogin : 1};

	           	httpPost("a/login",JsonData, function(data, status){window.location.href = IP_URL + "a";});
	         }
	        
		})
		
	}
        	
</script>
</body>
</html>