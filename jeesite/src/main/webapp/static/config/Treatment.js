
/*********************定义全局变量****************/
var IP_URL = "http://localhost/MySpringMVC/"; //访问后台ip地址
var canGetCookie = 0;//是否支持存储Cookie 0 不支持 1 支持

/*********************发送Http请求****************/

function httpGet(url, callback){
	
	$.get(IP_URL + url, callback);
}

function httpAjaxGet(url, async, callback){
	
	jQuery.ajax({
		url  : IP_URL + url,
		type : "GET",
		async : async,
		dataType: "json",
		success : callback,
		error : function(error){ErroAlert(error);}
		
	})
	
}

function httpPost(url, data, callback){
	
	$.post(IP_URL + url, data, callback);
}

/*********************操作html****************/
function getInputValByName(name){
	
	return $('input[name="' + name + '"]').val();
}

function setInputValByName(name, value){
	
	return $('input[name="' + name + '"]').val(value);
}

/*********************弹框****************/

//弹出窗口
function openAlert(title, area, url){
	layer.open({
		type: 2,
		title: title,
		area: area, //['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0,
		content: url,
		btnAlign : 'c',
		btn: ['立即提交'],
		yes:function(index, layero){
			
			$("#layui-layer-iframe" + index)[0].contentWindow.submit();
			
		}
	});
}

//弹出无按钮窗口
function openNoBtnAlert(title, area, url){
	layer.open({
		type: 2,
		title: title,
		area: area, //['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0,
		content: url,
		btnAlign : 'c',
	});
}

//弹出填充满屏幕的窗口
function fullAlert(title, url){
	
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});

	layer.full(index);
}

//弹出警告提示框
function WarringAlert(e) {
	
	var layer = layui.layer;

 	var index = layer.alert(e, { icon: 7, time: 2000, offset: 't', closeBtn: 0, btn: [], anim: 2, shade: 0,title:false })
    layer.style(index, {color: '#777'});
	
}

//弹出友好提示框
function MsgAlert(e) {
	
	var layer = layui.layer;

 	var index = layer.alert(e, { icon: 1, time: 2000, offset: 't', closeBtn: 0, btn: [], anim: 2, shade: 0,title:false })
    layer.style(index, {color: '#777'});
	
}

//弹出错误框
function ErroAlert(e) {
	
	var layer = layui.layer;

 	var index = layer.alert(e, { icon: 5, time: 2000, offset: 't', closeBtn: 0, title: '错误信息', btn: [], anim: 2, shade: 0 });
    layer.style(index, {color: '#777'});
	
}

/*********************生成验证码****************/
//生成验证码
var code = "";
function createCode(e) {
    code = "";
    var codeLength = 4;
    var selectChar = new Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    for (var i = 0; i < codeLength; i++) {
        var charIndex = Math.floor(Math.random() * 60);
        code += selectChar[charIndex];
    }
    if (code.length != codeLength) {
        createCode(e);
    }
	if(canGetCookie == 1){
    	setCookie(e, code, 60 * 60 * 60, '/');
	}else{
		return code;
	}
}


//hours为空字符串时,cookie的生存期至浏览器会话结束。  
//hours为数字0时,建立的是一个失效的cookie,  
//这个cookie会覆盖已经建立过的同名、同path的cookie（如果这个cookie存在）。     
function setCookie(name, value, hours, path) {
    var name = escape(name);
    var value = escape(value);
    var expires = new Date();
    expires.setTime(expires.getTime() + hours * 3600000);
    path = path == "" ? "" : ";path=" + path;
    _expires = (typeof hours) == "string" ? "" : ";expires=" + expires.toUTCString();
    document.cookie = name + "=" + value + _expires + path;
}
//cookie名获取值  
function getCookieValue(name) {
    var name = escape(name);
    //读cookie属性，这将返回文档的所有cookie     
    var allcookies = document.cookie;
    //查找名为name的cookie的开始位置     
    name += "=";
    var pos = allcookies.indexOf(name);
    //如果找到了具有该名字的cookie，那么提取并使用它的值
    if (pos != -1) {    //如果pos值为-1则说明搜索"version="失败     
        var start = pos + name.length;   //cookie值开始的位置     
        var end = allcookies.indexOf(";", start); //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置     
        if (end == -1) end = allcookies.length; //如果end值为-1说明cookie列表里只有一个cookie     
        var value = allcookies.substring(start, end);  //提取cookie的值     
        return unescape(value);       //对它解码           
    }
    else return "-1";    //搜索失败，返回-1  
}

function urlFormat(url, data){
	
	var params = "";
	
	if(data != ""){
		params = "&data=" + data;
	}
	
	return IP_URL + "a/app/jump?URL=" + url + params;
}

//比较两个json字符串是否相等（以ob1中的key为基础，ob1中没有的key不比较）
function compareJSONObject(ob1, ob2){
	
	var rs = true;
	
	for (var key in ob1) {
		
		if(ob2[key] == undefined){ob2[key] = "";}
		
		if(ob1[key] != ob2[key]){
			
			console.log("ob1["+key+"] -- " + ob1[key])
			console.log("ob2["+key+"] -- " + ob2[key])
			
			rs = false;
		}
		
	}
	
	return rs;
}
