<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}</title>

<link rel="stylesheet" type="text/css" href="${ctxStatic}/H-ui/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/H-ui/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/H-ui/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/H-ui/h-ui.admin/skin/green/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/H-ui/h-ui.admin/css/style.css" />

</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="">${fns:getConfig('productName')}</a> 
			<span class="logo navbar-slogan f-l mr-10 hidden-xs">v1.0</span> 
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
				<ul id="topMenu" class="cl"></ul>
			</nav>
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<li>${fns:getUser().name}</li>
				<li class="dropDown dropDown_hover">
					<a href="#" class="dropDown_A">${fns:getUser().loginName} <i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="${ctx}/sys/user/info">基本资料</a></li>
						<li><a href="${ctx}/sys/user/modifyPwd">安全设置</a></li>
						<li><a href="${ctx}/logout">退出</a></li>
					</ul>	
				</li>
				<li id="Hui-msg"> <a href="javascript:;" onclick="msgClick();" title="微信"><span class="badge badge-danger"></span><i class="Hui-iconfont" style="font-size:18px">&#xe694;</i></a> </li>
				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
</header>
<aside id="leftMenu" class="Hui-aside"></aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl"><li class="active"><span title="我的桌面" data-href="${ctx}/sys/user/info">我的桌面</span><em></em></li></ul>
		</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
	</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div> 
			<iframe scrolling="yes" frameborder="0" src="${ctx}/sys/user/info"></iframe>
		</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="${ctxStatic}/layui/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${ctxStatic}/H-ui/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/config/Treatment.js"></script> 
<script type="text/javascript" src="${ctxStatic}/H-ui/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctxStatic}/jquery-contextmenu/jquery.contextmenu.r2.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
$(function(){
	$("body").Huitab({
		tabBar:".navbar-wrapper .navbar-levelone",
		tabCon:".Hui-aside .menu_dropdown",
		className:"current",
		index:0,
	});
	
	// 获取菜单
	httpGet("a/sys/menu/list", function (data, status){initMenu(data);});
});

var prveParentId = "";
function initMenu(data){

	var list = data.list;
	var topMenuData = forEachMenu("1", data.list);

	var topMenu = "";
	var leftMenu = "";

	for(var i=0;i<topMenuData.length;i++){

		var leftMenuData = forEachMenu(topMenuData[i].id, data.list);
		
		if(topMenu == ""){
			
			prveParentId = topMenuData[i].id;
			
			topMenu += "<li id='" + topMenuData[i].id + "' class='navbar-levelone current' onclick='clickTopMenu(" + topMenuData[i].id + ")'><a href='javascript:;'>" + topMenuData[i].name + "</a></li>";

			leftMenu += "<div id='left_" + topMenuData[i].id + "' class='menu_dropdown bk_2'>";
		}else{
			
			topMenu += "<li id='" + topMenuData[i].id + "' class='navbar-levelone' onclick='clickTopMenu(" + topMenuData[i].id + ")'><a href='javascript:;'>" + topMenuData[i].name + "</a></li>";

			leftMenu += "<div id='left_" + topMenuData[i].id + "' class='menu_dropdown bk_2' style='display:none;'>";
		}

		for(var j=0;j<leftMenuData.length;j++){

			var child = forEachMenu(leftMenuData[j].id, list);

			leftMenu += "<dl>";
			leftMenu += "<dt onclick='clickLeftMenu(this)'><i class='Hui-iconfont'>&#xe616;</i>" + leftMenuData[j].name + "<i class='Hui-iconfont menu_dropdown-arrow'>&#xe6d5;</i></dt>";
			leftMenu += "<dd>";
			leftMenu += "<ul>";

			for(var k=0;k<child.length;k++){
				
				leftMenu += "<li><a data-href='${ctx}" + child[k].href + "' data-title='" + child[k].name + "' href='javascript:void(0)'>" + child[k].name + "</a></li>";
			}

			leftMenu += "</ul>";
			leftMenu += "</dd>";
			leftMenu += "</dl>";


		}

		leftMenu += "</div>";

	}

	$("#topMenu").append(topMenu);
	$("#leftMenu").append(leftMenu);
	
}

function clickTopMenu(parentId){

	$(".current").removeClass("current");
	$("#" + parentId).addClass("current");

	$("#left_" + prveParentId).fadeOut(0);
	$("#left_" + parentId).fadeIn("slow");
	
	prveParentId = parentId;
	
}

function clickLeftMenu(element){
	
	$(element).toggleClass("selected");
	
	$(element).next().slideToggle();
	
}

function forEachMenu(parentId, list){
	
	var child = [];
	
	for(var i=0;i<list.length;i++){
		
		if(list[i].parentId == parentId && list[i].isShow != "0"){
			
			child.push(list[i]);
			
		}
	}
	
	return child;
}

function msgClick(){
	openNoBtnAlert("微信", ['600px','500px'], urlFormat("app/layuiIMLogin", ""));
}

</script> 
</body>
</html>