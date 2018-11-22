<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>微信</title>
<link href="/MyDemo/_urc/css/Tencent/WX/b57ceb1fe70efc47d4f0cab10d89d36f.css" rel="stylesheet" />
<link href="/MyDemo/_urc/lib/layui/css/layui.css" rel="stylesheet" />
<script src="/MyDemo/_urc/vue/vue.js"></script>
<script src="/MyDemo/_urc/jQuery/jquery-1.9.1.js"></script>
<script src="/MyDemo/_urc/lib/layui/layui.all.js"></script>
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
	overflow:hidden;
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
	position: relative;
}
.contactTime
{
	right: 0px;
    margin-top: 14px;
    position: absolute;
    margin-right: 10px;
    font-size: 10px;
}	
.contactMsg
{
	margin-left: 60px;
    position: absolute;
    bottom: 10px;
    left: 0px;
    display: block;
    width: 180px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    font-size: 10px;
}
	
#center_bottom li img
{
	width:40px;
	height:40px;
	margin-top:10px;
	margin-left:10px;
	float: left;
	
}

#center_bottom li .emoji
{
	margin:0px 2px 0px 2px
}

#right_top .emoji
{
	margin:4px 2px 0px 2px
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
	width:750px;
	overflow: auto;
}
#right_center img
{
    width: 30px;
    height: 30px;
}
#right_center li
{
	width: 735px;
	margin-bottom: 10px;
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
.flip-list-move {
  transition: transform 1s;
}

/**气泡样式*/
.othertext
{
	 position: relative;
     left:20px;
     background: #ffffff;
     -moz-border-radius: 12px;
     -webkit-border-radius: 12px;
     border-radius: 12px;
     margin-right: 273px;
     width: auto;
    display: inline-block;
}
.othertext:before
{
	position: absolute;
	content: "";
	width: 0;
	height: 0;
	right: 100%;
	top: 10px;
	border-top: 5px solid transparent;
	border-right: 10px solid #ffffff;
	border-bottom: 5px solid transparent;
}

.owntext
{
	 position: relative;
     right:20px;
     background: #5FB878;
     -moz-border-radius: 12px;
     -webkit-border-radius: 12px;
     border-radius: 12px;
     margin-left: 273px;
     width: auto;
    display: inline-block;
    float: right;
}
.owntext:after
{
	position: absolute;
	content: "";
	width: 0;
	height: 0;
	top: 10px;
	border-top: 5px solid transparent;
	border-left: 10px solid #5FB878;
	border-bottom: 5px solid transparent;
}

.context
{
    width: 619px;
}
.contextspan
{
	display: inline-block;
    margin: 10px;
    max-width: 400px;
    word-wrap: break-word;
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
					<div @click="modeclick($event, 0)"><i class="layui-icon" style="font-size: 25px;">&#xe611;</i></div>
					<div @click="modeclick($event, 1)"><i class="layui-icon" style="font-size: 25px;">&#xe612;</i></div>
					<div @click="modeclick($event, 2)"><i class="layui-icon" style="font-size: 25px;">&#xe613;</i></div>
					<div @click="modeclick($event, 3)"><i class="layui-icon" style="font-size: 25px;">&#xe631;</i></div>
				</div>
				<div id="center" class="layui-bg-gray">
					<div id="center_top">
						<input type="text" name="search" placeholder="搜索" class="layui-input" style="width:200px;height:25px;margin-left:10px;margin-top:27px;">
					</div>
					<div id="center_bottom" class="center_bottom_0">
						<transition-group name="flip-list" tag="ul">
							<li v-for="list in ContactList" @click="clickli($event, list)"  v-bind:key="list.UserName">
								<img :src="list.Img"/>
								<span style="margin-left: 10px;margin-top: 10px;display: block;width: 150px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;float: left;" v-html="list.NickName" v-if="list.RemarkName == ''"></span>
								<span style="margin-left: 10px;margin-top: 10px;display: block;width: 150px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;float: left;" v-html="list.RemarkName" v-else></span>
								<span class="contactTime">{{list.newTime}}</span></br>
								<span class="contactMsg" v-html="list.newMsg"></span>
							</li>
  						</transition-group>
					</div>
					<div id="center_bottom" class="center_bottom_1">
						<transition-group name="flip-list" tag="ul">
							<li v-for="list in MemberList" @click="clickli($event, list)"  v-bind:key="list.UserName">
								<img src="/MyDemo/_urc/images/WX/TX.png"/>
								<span style="margin-left: 10px;margin-top: 10px;display: block;width: 150px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;float: left;" v-html="list.NickName" v-if="list.RemarkName == ''"></span>
								<span style="margin-left: 10px;margin-top: 10px;display: block;width: 150px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;float: left;" v-html="list.RemarkName" v-else></span>
							</li>
  						</transition-group>
					</div>
					<div id="center_bottom" class="center_bottom_2">
						<transition-group name="flip-list" tag="ul">
							<li v-for="list in BatchGetContact" @click="clickli($event, list)"  v-bind:key="list.UserName">
								<img src="/MyDemo/_urc/images/WX/QZ.png"/>
								<span style="margin-left: 10px;margin-top: 10px;display: block;width: 150px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;float: left;" v-html="list.NickName" v-if="list.RemarkName == ''"></span>
								<span style="margin-left: 10px;margin-top: 10px;display: block;width: 150px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;float: left;" v-html="list.RemarkName" v-else></span>
							</li>
  						</transition-group>
					</div>
				</div>
				<div id="right">
					<div id="right_top"><span v-html="selectinfo.NickName"></span></div><hr>
					<div id="right_center">
						<transition-group name="flip-list" tag="ul">
							<li v-for="msg in selectinfo.Msg" v-bind:key="msg.Content">
								<img src="/MyDemo/_urc/images/WX/TX.png" style="margin-right: 20px;float: right;" v-if="msg.own"/>
  								<div class="context" style="float: right;" v-if="msg.own">
  									<div class="owntext">
  										<span class="contextspan" v-html="msg.Content"></span>
  									</div>
  								</div>
								<img src="/MyDemo/_urc/images/WX/TX.png" style="margin-left: 20px;float: left;" v-if="msg.own == false"/>
 								<div class="context" style="float: left;" v-if="msg.own == false">
 									<div class="othertext">
 										<span class="contextspan" v-html="msg.Content"></span>
 									</div>
 								</div>
  								<div style="clear:both;"></div>
 							</li> 
						</transition-group>
					</div>
					<div id="right_bottom"><hr>
						<div id="tools">
							<i class="layui-icon" style="font-size: 25px; margin-left:25px;">&#xe664;</i>    
						</div>
						<textarea></textarea>
						<button class="layui-btn layui-bg-gray layui-btn-sm" id="send">发送(S)</button>
					</div>
				</div>
			</div>
		</div>
	</body>

	<script>

	var layer = layui.layer;
	
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
			wxpluginkey : "",
			selectinfo : 
			{
				NickName : "",
				UserName : "",
				Msg      : []
			},
			UserInfo : {},		//当前登录用户信息
			ChatSet : "",		//群Username
			ContactList:[],		//最近联系人列表
			SyncCheckKey:{},
			MemberList : [],	 //所有联系人的信息
			BatchGetContact : [], //群数组
			MPSubscribeMsgList : [] //公众号列表

			
		},
		//网页加载时执行的方法(获取二维码)
		created : function(){this.created();},
		methods : 
		{
			created : function()
			{
				var self = this;
				
				$("#main_div").hide();
				$("#login_div").show();
				
				
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
							$("#QRimg").removeClass("layui-anim");
							
							$("#QRimg")[0].src = "https://login.weixin.qq.com/qrcode/" + self.uuid;
							
							self.QRcode_after();
						}
						
					},
					error : function(res){}
				})
			},
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
						
						//页面初始化

						//隐藏登录页面，显示登录操作页面
						 $("#login_div").slideUp("slow",function(){ $("#main_div").slideDown("slow");});

						//隐藏聊天界面
						 $("#right").hide();
						 
						 $("#right_bottom textarea").focus(function(){$("#right_bottom").css("background","#FFFFFF");$("#right_bottom textarea").css("background","#FFFFFF")});
						 $("#right_bottom textarea").blur(function(){$("#right_bottom").css("background","#f2f2f2");$("#right_bottom textarea").css("background","#f2f2f2")});
						 $("#right_bottom button").click(function(){self.webwxsendmsg();})
						 
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
						self.SyncCheckKey = result.SyncKey;
						self.ChatSet = result.ChatSet;
						self.MPSubscribeMsgList = result.MPSubscribeMsgList;
						self.getImages(result.User.HeadImgUrl);
						
						for(var i = 0;i<result.Count;i++)
						{
							if(result.ContactList[i].VerifyFlag != 24)
							{
								if(result.ContactList[i].KeyWord != "" && result.ContactList[i].KeyWord != "gh_")
								{

									result.ContactList[i].Img = "/MyDemo/_urc/images/WX/" + result.ContactList[i].KeyWord + ".jpg";

								}
								else if(result.ContactList[i].MemberCount > 0)
								{
									result.ContactList[i].Img = "/MyDemo/_urc/images/WX/QZ.png";
								}
								else
								{
									result.ContactList[i].Img = "/MyDemo/_urc/images/WX/TX.png";
								}
								
								self.ContactList.push(result.ContactList[i]);
							}
						}

						self.webwxstatusnotify();
					},
					error : function(res){}
				})
			},
			getImages : function(url)
			{
// 				$.ajax(
// 				{
// 					url  	 : "https://wx.qq.com" + url,
// 					type 	 : "get",
// 					async 	 : true,
// 					success  : function(result)
// 					{
// 						console.log("getImages-------------------")
// 						console.log(result)
// 					},
// 					error : function(res){console.log("getImages-------------------")
// 						console.log(res)}
// 				})
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
				
				self.webwxgetcontact();
				
			},
			clickli : function(event, index)
			{
				/**点击聊天列表*/
				var self = this;
				
				console.log(event);
				
				//如果聊天界面处于隐藏状态则显示
				if($("#right").css("display")=='none')
				{
					$("#right").show(1000);
				}
				
				var e;
				
				if(event.path[0].localName == 'span'){e = $(event.path[1]);}else{e = $(event.path[0]);}
				
				if(self.selectli != ""){$(self.selectli).css("background","#f2f2f2");}
				
				e.css("background","#d2d2d2");
				
				self.selectli = e;

				self.selectinfo.NickName = index.NickName;
				self.selectinfo.UserName = index.UserName;
				self.selectinfo.Msg = index.Msg;
				
				console.log(self.selectinfo)

				if(index.MemberCount != "0"){self.selectinfo.NickName += "("+ index.MemberCount +")"}

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
						
						self.MemberList = result.MemberList;
						
						self.synccheck();
						
						self.webwxbatchgetcontact();
						 
					},
					error : function(res){console.log(res)}
				})
			},
			webwxbatchgetcontact : function()
			{
				var self = this;
				
				var list = [];
				
				
				
				var chatsets = self.ChatSet.split(",");
				
				for(var i = 0;i<chatsets.length;i++)
				{
					var element = {};

					if(chatsets[i].indexOf("@") >= 0)
					{
						element["UserName"] = chatsets[i];
						element["EncryChatRoomId"] = "";
					}
					
					list.push(element);
					
				}

				var BaseRequest  = "{BaseRequest:{Uin:\"" + self.wxuin + "\",Sid:\"" + self.wxsid + "\",Skey:\"" + self.skey + "\",DeviceID:\"e" + ("" + Math.random().toFixed(15)).substring(2, 17) +"\"},";
					BaseRequest += "Count:\"" + list.length + "\",";
					BaseRequest += "List:" + JSON.stringify(list) + "}";
				
				$.ajax(
				{
					type   : "POST",
					url  	 : "/MyDemo/proxy/doPostRequestPayload",
					dataType : "json",
					data 	 :
					{
						"url"  : "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxbatchgetcontact?type=ex&lang=zh_CN&pass_ticket=" + self.pass_ticket + "&r=" + (+new Date),
						"BaseRequest" : BaseRequest
					},
					success  : function(result)
					{
						console.log("webwxbatchgetcontact:---------")
						console.log(result)
						
						for(var i = 0;i < result.Count;i++)
						{
							if(result.ContactList[i].MemberCount > 0)
							{
								self.BatchGetContact.push(result.ContactList[i]);
							}
						}
						console.log("BatchGetContact:---------")
						console.log(self.BatchGetContact)
						
					},
					error : function(res){console.log(res)}
				})
			},
			webwxsync : function()
			{
				var self = this;
				
				var BaseRequest  = "{BaseRequest:{Uin:\"" + self.wxuin + "\",Sid:\"" + self.wxsid + "\",Skey:\"" + self.skey + "\",DeviceID:\"e" + ("" + Math.random().toFixed(15)).substring(2, 17) +"\"},";
					BaseRequest += "SyncKey: " + JSON.stringify(self.SyncCheckKey) + ",rr:\"" + (-new Date().getTime() / 1000) + "\"}";

					$.ajax(
					{
						type   : "POST",
						url  	 : "/MyDemo/proxy/doPostRequestPayload",
						dataType : "json",
						data 	 :
						{
							"url"  : "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxsync?sid=" + self.wxsid + "&skey=" + self.skey + "&uin=" + self.wxuin + "&pass_ticket=" + self.pass_ticket,
							"BaseRequest" : BaseRequest
						},
						success  : function(result)
						{
							console.log(result)
							
							self.SyncCheckKey = result.SyncKey;
							
							console.log(self.SyncCheckKey);
							
							for(var i = 0;i<result.AddMsgCount;i++)
							{
								
								var UserName = result.AddMsgList[i].FromUserName;
								var isOwn = false;
								var no = true;
								var list = {};
								
								if(result.AddMsgList[i].Content == "" && result.AddMsgList[i].MsgType == 51){continue;}
								
								if(result.AddMsgList[i].FromUserName == self.UserInfo.UserName)
								{
									UserName = result.AddMsgList[i].ToUserName;isOwn = true;
								}
								
								for(var j = 0;j < self.ContactList.length;j++)
								{
									if(UserName == self.ContactList[j].UserName)
									{
										list = self.ContactList[j];
										
										self.ContactList.splice(j, 1);
										
										no = false;break;
									}
								}
								
								if(no)
								{
									for(var j = 0;j < self.MemberList.length;j++)
									{
										if(UserName == self.MemberList[j].UserName)
										{
											list = self.MemberList[j];no = false;break;
										}
									}
								}
								
								if(no)
								{
									for(var j = 0;j < self.BatchGetContact.length;j++)
									{
										if(UserName == self.BatchGetContact[j].UserName)
										{
											list = self.BatchGetContact[j];no = false;break;
										}
									}
									
								}
								
								var msg = {};

								//msgType(3:图片，1：文字)
								if(result.AddMsgList[i].MsgType == 3)
								{
									if(list.MemberCount > 0)
									{
										list["newMsg"] = "[图片]";
										msg["Content"] = result.AddMsgList[i].Content.split(":")[1].replace(new RegExp("<br/>"), '');
									}
									else
									{
										list["newMsg"] = "[图片]";
										msg["Content"] = "<img class='msg-img' ng-src='https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxgetmsgimg?MsgID=" + result.AddMsgList[i].MsgId + "&skey=" + self.skey + "&type=slave' src='https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxgetmsgimg?MsgID=" + result.AddMsgList[i].MsgId + "&skey=" + self.skey + "&type=slave' style='height: " + result.AddMsgList[i].ImgHeight + "px; width: " + result.AddMsgList[i].ImgWidth + "px;' />";
									}
									
								}
								else if(result.AddMsgList[i].MsgType == 1)
								{
									if(list.MemberCount > 0)
									{
										list["newMsg"] = result.AddMsgList[i].Content.split(":")[1].replace(new RegExp("<br/>","g"), '');
										msg["Content"] = result.AddMsgList[i].Content.split(":")[1].replace(new RegExp("<br/>"), '');
									}
									else
									{
										list["newMsg"] = result.AddMsgList[i].Content.replace(new RegExp("<br/>","g"), '');
										msg["Content"] = result.AddMsgList[i].Content;
									}
								}
								else
								{
									list["newMsg"] = "[此消息暂不支持]";
									msg["Content"] = "[此消息暂不支持]";
								}
								
								var date = new Date(result.AddMsgList[i].CreateTime * 1000); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
								
								list["newTime"] = date.getHours() + ':' + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes());
								msg["CreateTime"] = date.getHours() + ':' + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes());
								msg["own"] = isOwn;
								
								if(list["Msg"] == undefined){list["Msg"] = [];}

								list["Msg"].push(msg);
								
								if(list.KeyWord != "" && list.KeyWord != "gh_")
								{

									list.Img = "/MyDemo/_urc/images/WX/" + list.KeyWord + ".jpg";

								}
								else if(list.MemberCount > 0)
								{
									list.Img = "/MyDemo/_urc/images/WX/QZ.png";
								}
								else
								{
									list.Img = "/MyDemo/_urc/images/WX/TX.png";
								}

								console.log("list:----------------------------");
								console.log(list);
								
								self.ContactList.splice(0, 0, list);
								
								console.log("ContactList:----------------------------");
								console.log(self.ContactList);
							}
							
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
						"url"  : "https://webpush.wx.qq.com/cgi-bin/mmwebwx-bin/synccheck?uin=" + self.UserInfo.Uin + "&r=" + (+new Date) + "&skey=" + self.skey + "&sid=" + self.wxsid + "&deviceid=e" + ("" + Math.random().toFixed(15)).substring(2, 17) + "&synckey=" + self.getFormateSyncCheckKey() + "&pass_ticket=" + self.pass_ticket + "&_=" + (new Date()).getTime(),
						"BaseRequest" : BaseRequest
					},
					success  : function(result)
					{
						var synccheck = eval("(" + result.split("=")[1] + ")");
						
						if(synccheck.retcode != "0"){self.created();return;}

						if(synccheck.selector == "2"){self.webwxsync();}else if(synccheck.selector == "0"){self.synccheck();}else{self.created();return;}
						
					},
					error : function(res){console.log(res)}
				})
			},
			webwxsendmsg : function()
			{
				var self = this;
				
				var content = $("#right_bottom textarea").val(); $("#right_bottom textarea").val("");
				
				if(content == ""){layui.layer.tips('不能发送空白信息', '#send', {tips: 1});return;}
				
				var ClientMsgId = ((new Date()).getTime() + Math.random().toFixed(3)).replace(".", "");
				
				var BaseRequest  = "{BaseRequest:{Uin:\"" + self.wxuin + "\",Sid:\"" + self.wxsid + "\",Skey:\"" + self.skey + "\",DeviceID:\"e" + ("" + Math.random().toFixed(15)).substring(2, 17) +"\"},";
					BaseRequest += "Msg:{ClientMsgId:\"" + ClientMsgId + "\",";
					BaseRequest += "Content:\"" + content + "\",";
					BaseRequest += "FromUserName:\"" + self.UserInfo.UserName + "\",";
					BaseRequest += "LocalID:\"" + ClientMsgId + "\",";
					BaseRequest += "Type:\"1\",";
					BaseRequest += "ToUserName:\"" + self.selectinfo.UserName + "\"},";
					BaseRequest += "Scene:\"0\"}";
					
					$.ajax(
					{
						type   : "POST",
						url  	 : "/MyDemo/proxy/doPostRequestPayload",
						dataType : "json",
						data 	 :
						{
							"url"  : "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxsendmsg?lang=zh_CN&pass_ticket=" + self.pass_ticket,
							"BaseRequest" : BaseRequest
						},
						success  : function(result)
						{
							console.log("webwxsendmsg:--------------------------------")
							console.log(result)
							
							var list = {};
							var msg = {};
							for(var j = 0;j < self.ContactList.length;j++)
							{
								if(self.selectinfo.UserName == self.ContactList[j].UserName)
								{
									list = self.ContactList[j];
									
									self.ContactList.splice(j, 1);
									
									break;
								}
							}
							
							var date = new Date(); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
							
							list["newTime"] = date.getHours() + ':' + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes());
							list["newMsg"] = content.replace(new RegExp("<br/>","g"), '');

							msg["own"] = true;
							msg["Content"] = content;
							msg["CreateTime"] = date.getHours() + ':' + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes());
							
							if(list["Msg"] == undefined){list["Msg"] = [];self.selectinfo.Msg = list.Msg;}

							list["Msg"].push(msg);
							
							console.log("webwxsendmsg1:--------------------------------")
							console.log(list)

							self.ContactList.splice(0, 0, list);
							
							console.log("webwxsendmsg2:--------------------------------")
							console.log(self.ContactList)
							
						},
						error : function(res){console.log(res)}
					})
				
			},
			modeclick : function(e, index)
			{
				/*界面左侧模块的点击事件**/
				if(index == 0)
				{
					$(".center_bottom_2").slideDown("slow");
					$(".center_bottom_1").slideDown("slow");
					$(".center_bottom_0").slideDown("slow");
				}
				if(index == 1)
				{
					$(".center_bottom_2").slideDown("slow");
					$(".center_bottom_1").slideDown("slow");
					$(".center_bottom_0").slideUp("slow");

					$("#right").hide(1000);
				}
				if(index == 2)
				{
					$(".center_bottom_0").slideUp("slow");
					$(".center_bottom_1").slideUp("slow");
				}
			},
			getFormateSyncCheckKey : function()
			{
				var self = this;
				
				var synckey = [];
				
				for(var i=0;i<self.SyncCheckKey.List.length;i++)
				{
					synckey.push(self.SyncCheckKey.List[i].Key + "_" + self.SyncCheckKey.List[i].Val);
					
					if(self.SyncCheckKey.List[i].Key == "1000")
					{
						self.wxpluginkey = self.SyncCheckKey.List[i].Val;
					}
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