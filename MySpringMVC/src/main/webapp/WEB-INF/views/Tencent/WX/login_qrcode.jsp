<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>微信</title>

<link href="/MyDemo/_urc/lib/layui/css/layui.css" rel="stylesheet" />
<script src="/MyDemo/_urc/vue/vue.js"></script>
<script src="/MyDemo/_urc/jQuery/jquery-1.9.1.js"></script>
<script src="/MyDemo/_urc/lib/layui/layui.all.js"></script>
<style>

#mydiv{width:100%;height:100%}

.QRimg_div
{
	width:100%;height:100%;text-align: center;padding-top:20px;
}

#img_div{width:100%;height:190px;}

.layui-anim
{
	border-radius: 50%;
}

</style>
</head>
	<body>
		<div id="mydiv">
			<div class="QRimg_div" id= "img_div">
				<img id="QRimg" src="" width="190" height="190"/>
			</div>
			<div class="QRimg_div">
				<p style="color:#2F4056;">请使用微信扫一扫以登录</p>
			</div>
		</div>
	</body>

	<script>

	//实例化VUE
	var app = new Vue(
	{ 
		el : '#mydiv',
		data : 
		{
			uuid : "",
			tip  : 1
		},
		created : function(){
			
			var self = this;
			
			$.ajax(
			{
				url  	 : "/MyDemo/proxy/doPost",
				type 	 : "get",
				async 	 : true,
				dataType : "text",
				data 	 : 
				{
					"url"   : "https://login.wx.qq.com/jslogin",
					"appid" : "wx782c26e4c19acffb",
					"redirect_uri" : "https%3A%2F%2Fwx.qq.com%2Fcgi-bin%2Fmmwebwx-bin%2Fwebwxnewloginpage",
					"fun"  : "new",
					"lang" : "zh_CN",
					"_"    : (new Date()).getTime()
					
				},
				success  : function(result)
				{
					var QRLogin = result.split(";")
					var code  = QRLogin[0].split("=")[1].replace(" ", "")
					self.uuid = QRLogin[1].split("\"")[1];
					
					if(code == 200)
					{
						$("#QRimg")[0].src = "https://login.weixin.qq.com/qrcode/" + self.uuid;
						
						self.QRcode_after();
					}
					
				},
				error : function(res){}
			})
		},
		methods : 
		{

			QRcode_after : function()
			{
				var self = this;
				var layer = layui.layer;

				$.ajax(
				{
					url  	 : "/MyDemo/proxy/doPost",
					type 	 : "get",
					async 	 : true,
					dataType : "text",
					data 	 : 
					{
						"url"   : "https://login.wx.qq.com/cgi-bin/mmwebwx-bin/login",
						"loginicon" : "true",
						"uuid" : self.uuid,
						"tip"  : self.tip,
						"r"    : ~new Date,
						"_"    : (new Date()).getTime()
						
					},
					success  : function(result)
					{
						var win = result.split(";")
						var code = win[0].split("=")[1];

						if(code == "201")
						{
							var src = win[1].split("=")[1] + ";" + win[2]
							
							var html = "<img id='QRimg' class='layui-anim' src=" + src + " width='150' height='150'/>";

							$("#img_div").html(html);
							
						}
						else if(code == 200)
						{
// 							console.log(win[1].substring(win[1].toString().indexOf("=") + 2, win[1].length - 1))
// 							self.getRedirectUri(win[1].substring(win[1].toString().indexOf("=") + 2, win[1].length - 1))

							layer.open({
								  type: 2,
// 								  title: false,
// 								  closeBtn: 0, //不显示关闭按钮
// 								  shade: [0],
								  area: ['340px', '215px'],
// 								  offset: 'rb', //右下角弹出
								  anim: 2,
								  content: ['main.jsp', 'no'], //iframe的url，no代表不显示滚动条
								});
						}

						if(code != 200) self.QRcode_after();
						
					},
					error : function(res){}
				})
				
				self.tip = 0;
			},
			getRedirectUri : function(url)
			{
				$.ajax(
						{
							url  	 : "/MyDemo/proxy/doPost",
							type 	 : "get",
							async 	 : true,
							dataType : "text",
							data 	 :
							{
								"url"   : url + "&fun=new&version=v2"
							},
							success  : function(result)
							{
								console.log(result)
								
							},
							error : function(res){}
						})
			}
		}
	})
	
</script>
	
</html>