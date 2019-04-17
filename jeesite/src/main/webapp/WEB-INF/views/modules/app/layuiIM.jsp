<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>MyIM</title>

<link rel="stylesheet" type="text/css" href="${ctxStatic}/layui/css/layui.css" />
</head>
<body>
<script type="text/javascript" src="${ctxStatic}/layui/layui.js"></script>
<script>
layui.use('layim', function(layim){

  layim.config({

    brief : false,		//是否简约模式（如果true则不显示主面板）
    title : 'MyIM',		//主面板最小化后显示的名称
    min	: false, 		//用于设定主面板是否在页面打开时，始终最小化展现
    right : '0px',		//用于设定主面板右偏移量。该参数可避免遮盖你页面右下角已经的bar
    minRight : '0px',	//用户控制聊天面板最小化时、及新消息提示层的相对right的px坐标。如：minRight: '200px'
    initSkin :'3.jpg',	//设置初始背景，默认不开启。可设置./css/modules/layim/skin目录下的图片文件名。 如：initSkin: '5.jpg'
    isAudio : true,		//是否开启聊天工具栏音频
    isVideo : true,		//是否开启开启聊天工具栏视频
    notice	: false,	//是否开启桌面消息提醒，即在浏览器之外的提醒
    voice 	: false, 	//设定消息提醒的声音文件（所在目录：./layui/css/modules/layim/voice/）  若不开启，设置 false 即可
    isfriend: true, 	//是否开启好友
    isgroup	: true, 	//是否开启群组
    maxLength	: 3000, //可允许的消息最大字符长度
    copyright	: true, //是否授权。如果非授权获得，或将LayIM应用在第三方，建议保留，即不设置。
    
    init:{

    	mine: {
    		"username": "纸飞机", //我的昵称
    		"id": "100000", //我的ID
    		"status": "online", //在线状态 online：在线、hide：隐身
    		"sign": "在深邃的编码世界，做一枚轻盈的纸飞机", //我的签名
    		"avatar": "a.jpg", //我的头像
    	},
    	friend: [{
    		"groupname": "前端码屌", //好友分组名
    		 "id": 1, //分组ID
    		 "list": [{ //分组下的好友列表
    		 	"username": "贤心", //好友昵称
    		    "id": "100001", //好友ID
    		    "avatar": "a.jpg", //好友头像
    		    "sign": "这些都是测试数据，实际使用请严格按照该格式返回", //好友签名
    		    "status": "online", //若值为offline代表离线，online或者不填为在线
    		 }]
    	}],
    	"group": [{
    	     "groupname": "前端群", //群组名
    	     "id": "101", //群组ID
    	     "avatar": "a.jpg", //群组头像
    	}]
    	
    },
    
    
  });
});
</script>
</body>
</html>