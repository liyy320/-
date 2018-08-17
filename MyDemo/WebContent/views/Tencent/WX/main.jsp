<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>微信</title>

<link href="/MyDemo/_urc/lib/layui/css/layui.css" rel="stylesheet" />
<script src="/MyDemo/_urc/vue/vue.js"></script>
<script src="/MyDemo/_urc/jQuery/jquery-1.9.1.js"></script>
<script src="/MyDemo/_urc/lib/layui/layui.js"></script>
<script src="/MyDemo/_urc/jQuery/jquery.cookie.js"></script>
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

#left{height:576px;width:70px;text-align: center;float:left;}

#left img
{
	width:40px;
	height:40px;
	border-radius:20%;
	text-align: center;
	margin-top:20px;
}
#left div{margin-top:20px;}
#center
{
	height:576px;
	width:300px;
	float:left;
}
#center_top
{
	height:40px;
	width:300px;
}
#center_bottom
{
	overflow:auto;
	height:509px;
}
#center_bottom li
{
	height:60px;
	color:#393D49;
}

#center_bottom li img
{
	width:40px;
	height:40px;
	margin-top:10px;
	margin-left:10px;
	
}
#right
{
	width:750px;
	height:576px;
	background:#f2f2f2;
	float:left;
}
#right_top
{
	height:56px;
	width:576px;
	vertical-align: bottom;
    display: table-cell;
}
#right_top span
{
	font-size: 20px;
    margin-left: 10px;
    color: #393D49;
}
#right_center
{
	height:300px;
	width:576px;
}
#right_bottom
{
	height:189px;
}
#tools
{
	width: 750px;
    height: 40px;
}
#right_bottom textarea
{
	border: 0px;
    width: 700px;
    height: 90px;
    resize: none;
    margin-left: 25px;
   background:#f2f2f2;
}
#right_bottom button
{
	position: absolute;
    right: 40px;
    bottom: 10px;
    border: 1px solid #d2d2d2;
}
</style>
</head>
	<body>
		<div id="mydiv">
			<div id="login_div">
				<div class="QRimg_div" id= "img_div">
					<img id="QRimg" src="" width="190" height="190"/>
				</div>
				<div class="QRimg_div">
					<p style="color:#2F4056;">请使用微信扫一扫以登录</p>
				</div>
			</div>
			<div id="main_div">
				<div id="left" class="layui-bg-black">
					<img src="/MyDemo/_urc/images/WX/TX.png"/>
					<div><i class="layui-icon" style="font-size: 25px;">&#xe611;</i></div>
					<div><i class="layui-icon" style="font-size: 25px;">&#xe770;</i></div>
				</div>
				<div id="center" class="layui-bg-gray">
					<div id="center_top">
						<input type="text" name="search" placeholder="搜索" class="layui-input" style="width:200px;height:25px;margin-left:10px;margin-top:27px;">
					</div>
					<div id="center_bottom"><ul></ul></div>
					
				</div>
				<div id="right">
					<div id="right_top"><span>{{selectinfo.NickName}}</span></div><hr>
					<div id="right_center"></div>
					<div id="right_bottom"><hr>
						<div id="tools">
							<i class="layui-icon" style="font-size: 25px; margin-left:25px;">&#xe664;</i>    
						</div>
						<textarea></textarea>
						<button class="layui-btn layui-bg-gray layui-btn-sm">发送(S)</button>
					</div>
				</div>
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
			tip  : 1,
			pass_ticket : "",
			wxuin : "",
			wxsid : "",
			skey : "",
			selectli : "",
			selectinfo : 
			{
				NickName : ""
			},
			UserInfo : {},
			ContactList:[],
			SyncCheckKey:{}

			
		},
		//网页加载时执行的方法(获取二维码)
		created : function()
		{
			
			var self = this;
			
			$("#main_div").hide();
			
			$.ajax(
			{
				url  	 : "/MyDemo/proxy/doPost",
				type 	 : "get",
				async 	 : true,
				dataType : "text",
				data 	 : 
				{
					"url"   : "https://login.wx.qq.com/jslogin",
					"ContentType" : "text/html;charset=gbk",
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

			//获取二维码之后轮询检测用户时候扫描二维码，如果扫描轮询检测用户时候点击确认登录
			QRcode_after : function()
			{
				var self = this;

				$.ajax(
				{
					url  	 : "/MyDemo/proxy/doPost",
					type 	 : "get",
					async 	 : true,
					dataType : "text",
					data 	 : 
					{
						"ContentType" : "text/javascript",
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
							self.getRedirectUri(win[1].substring(win[1].toString().indexOf("=") + 2, win[1].length - 1))
						}
						
						if(code != 200) self.QRcode_after();
						
					},
					error : function(res){}
				})
				
				self.tip = 0;
			},
			//用户点击确认登录之后会返回一个地址，访问改地址获取pass_ticket
			getRedirectUri : function(url)
			{
				var self = this;

				$.ajax(
				{
					url  	 : "/MyDemo/proxy/doPost",
					type 	 : "get",
					async 	 : true,
					dataType : "text",
					data 	 :
					{
						"url"   : url + "&fun=new&version=v2",
						"ContentType" : "text/html;charset=UTF-8"
					},
					success  : function(result)
					{
						
						 $("#login_div").hide();
						 $("#main_div").show();
						 
						 $("#right_bottom textarea").focus(function(){$("#right_bottom").css("background","#FFFFFF");$("#right_bottom textarea").css("background","#FFFFFF")});
						 $("#right_bottom textarea").blur(function(){$("#right_bottom").css("background","#f2f2f2");$("#right_bottom textarea").css("background","#f2f2f2")});
						 $("#right_bottom button").click(function(){webwxsendmsg();})
						 
						 
						 var returnDataXml = self.parseXML(result)
	
						 var param        = $(returnDataXml).find("error");
						 self.pass_ticket = $(param[0]).find("pass_ticket")[0].innerHTML;
						 self.wxuin       = $(param[0]).find("wxuin")[0].innerHTML;
						 self.wxsid 	  = $(param[0]).find("wxsid")[0].innerHTML;
						 self.skey 		  = $(param[0]).find("skey")[0].innerHTML;
						 self.webwxinit();
					},
					error : function(res){}
				})
			},
			//微信初始化，获取十条最近的消息，以及获取用户的信息
			webwxinit : function()
			{
				var self = this;

				$.ajax(
				{
					type   : "POST",
					url  	 : "/MyDemo/proxy/doPostRequestPayload",
					dataType : "json",
					data 	 :
					{
						"url" : "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxinit?r=" + ~new Date + "&pass_ticket=" + self.pass_ticket,
						"BaseRequest" : "{BaseRequest : {Uin:\"" + self.wxuin + "\",Sid:\"" + self.wxsid + "\",Skey:\"" + self.skey + "\",DeviceID:\"e" + ("" + Math.random().toFixed(15)).substring(2, 17) +"\"}}"
					},
					success  : function(result)
					{
						
						console.log(result) 

						self.UserInfo = result.User;
						self.ContactList = result.ContactList;
						self.SyncCheckKey = result.SyncKey;

						self.webwxstatusnotify();
					},
					error : function(res){}
				})
			},
			webwxstatusnotify : function()
			{
				var self = this;
				
				var BaseRequest  = "{BaseRequest:{Uin:\"" + self.wxuin + "\",Sid:\"" + self.wxsid + "\",Skey:\"" + self.skey + "\",DeviceID:\"e" + ("" + Math.random().toFixed(15)).substring(2, 17) +"\"},";
					BaseRequest += "ClientMsgId:\"" + (new Date()).getTime() + "\",";
					BaseRequest += "Code:\"3\",";
					BaseRequest += "FromUserName:\"" + self.UserInfo.UserName + "\",";
					BaseRequest += "ToUserName:\"" + self.UserInfo.UserName + "\"}";

				$.ajax(
				{
					type   : "POST",
					url  	 : "/MyDemo/proxy/doPostRequestPayload",
					dataType : "json",
					data 	 :
					{
						"url" : "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxstatusnotify?lang=zh_CN&pass_ticket=" + self.pass_ticket,
						"BaseRequest" : BaseRequest
					},
					success  : function(result)
					{
						console.log(result) 
						self.excuteinit();
						 
					},
					error : function(res){}
				})
			},
			//根据返回的数据进行渲染
			excuteinit : function()
			{
				var self = this;
				var html = "";
				for(var i=0;i<self.ContactList.length;i++)
				{
					var src = "";

					if(self.ContactList[i].KeyWord != "" && self.ContactList[i].KeyWord != "gh_")
					{
						src = "/MyDemo/_urc/images/WX/" + self.ContactList[i].KeyWord + ".jpg";
					}
					else if(self.ContactList[i].MemberCount > 0)
					{
						src = "/MyDemo/_urc/images/WX/QZ.png";
					}
					else
					{
						src = "/MyDemo/_urc/images/WX/TX.png";
					}
					
					html = "<li membercount='" + self.ContactList[i].MemberCount + "'><a><img src=\"" + src + "\"/><span style=\"margin-left:10px;\"> " + self.ContactList[i].NickName.replace("�?", "").replace("�? ","").replace("�?","").replace("�?","") + "</span></a></li>";

					$("#center_bottom ul").append(html);
				}

				$("#center_bottom li").bind("click", function(){self.clickli(this)});
				
				self.webwxgetcontact();
				
			},
			clickli : function(e)
			{
				var self = this;

				
				console.log($(e));
				var membercount = $(e)[0].attributes[0].value;
				
				if(self.selectli != ""){$(self.selectli).css("background","#f2f2f2");}
				
				$(e).css("background","#d2d2d2");
				
				self.selectli = e;

				self.selectinfo.NickName = $(e).find("span")[0].innerText;
				
				if(membercount != "0"){self.selectinfo.NickName += "("+ membercount +")"}
				
			},
			webwxgetcontact : function()
			{
				var self = this;
				
				var BaseRequest  = "{BaseRequest:{Uin:\"" + self.wxuin + "\",Sid:\"" + self.wxsid + "\",Skey:\"" + self.skey + "\",DeviceID:\"e" + ("" + Math.random().toFixed(15)).substring(2, 17) +"\"}}";
				
				$.ajax(
				{
					type   : "POST",
					url  	 : "/MyDemo/proxy/doPostRequestPayload",
					dataType : "json",
					data 	 :
					{
						"url"  : "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxgetcontact?lang=zh_CN&pass_ticket=" + self.pass_ticket + "&r=" + (+new Date) + "&seq=0&skey=" + self.skey,
						"BaseRequest" : BaseRequest
					},
					success  : function(result)
					{
						console.log(result)
						self.synccheck();
						 
					},
					error : function(res){console.log(res)}
				})
			},
			synccheck : function()
			{
				var self = this;
				
				var BaseRequest  = "{BaseRequest:{Uin:\"" + self.wxuin + "\",Sid:\"" + self.wxsid + "\",Skey:\"" + self.skey + "\",DeviceID:\"e" + ("" + Math.random().toFixed(15)).substring(2, 17) +"\"}}";
				
				$.ajax(
				{
					type   : "POST",
					url  	 : "/MyDemo/proxy/doPostRequestPayload",
					dataType : "script",
					data 	 :
					{
						"url"  : "https://webpush.wx.qq.com/cgi-bin/mmwebwx-bin/synccheck?uin=" + self.UserInfo.Uin + "&r=" + (+new Date) + "&skey=" + self.skey + "&sid=" + self.wxsid + "&deviceid=e" + ("" + Math.random().toFixed(15)).substring(2, 17) + "&synckey=" + self.getFormateSyncCheckKey() + "&_=" + (new Date()).getTime(),
						"BaseRequest" : BaseRequest
					},
					success  : function(result)
					{
						var synccheck = eval("(" + result.split("=")[1] + ")");

// 						if(synccheck.retcode != "0"){self.synccheck();}
						 
					},
					error : function(res){console.log(res)}
				})
			},
			getFormateSyncCheckKey : function()
			{
				var self = this;
				
				var synckey = [];
				
				for(var i=0;i<self.SyncCheckKey.List.length;i++)
				{
					synckey.push(self.SyncCheckKey.List[i].Key + "_" + self.SyncCheckKey.List[i].Val);
				}
				
				return synckey.join("|");
				
				
			},
			//解析xml格式的数据
			parseXML : function(xmlStr)
			{
				if(typeof($.browser) == "undefined")
				{
				         if (!!navigator.userAgent.match(/Trident\/7\./))
				         {// IE11
	
				             xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
				             xmlDoc.async = "false";
				             xmlDoc.loadXML(xmlStr);
	
				         }
				         else
				         {
				             var parser = new DOMParser();xmlDoc = parser.parseFromString(xmlStr, "text/xml");
				         }
				}
				else
				{
				       if($.browser.msie)// IE
				       {
				             xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
				             xmlDoc.async = "false";
				             xmlDoc.loadXML(xmlStr);
				       }
				       else// Other
				       {
				            var parser = new DOMParser();xmlDoc = parser.parseFromString(xmlStr, "text/xml");
				       }
				}

				return xmlDoc;
			}
		}
	})
	
</script>
	
</html>