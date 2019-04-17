<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>菜单管理</title>

<link rel="stylesheet" type="text/css" href="${ctxStatic}/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/layui/treetable-lay/treetable.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/H-ui/Hui-iconfont/1.0.8/iconfont.css" />
<style>
#myDiv{padding-left:10px;padding-right:10px;}
</style>
</head>
<body style="height:100%">
<div id="myDiv">
	<table id="menuList" class="layui-table" lay-filter="menuList"></table>
</div>
<div id="headButton" style="display:none;">
	<div class='layui-btn-group layui-anim layui-anim-scale'>
		<button class='layui-btn layui-btn-primary layui-btn-sm layui-bg-gray' onclick="topButtonClick(this)">全部折叠</button>
	</div>
</div>
<div id="colButton" style="display:none;">
	<div class='layui-anim layui-anim-scale'>
		<button class="layui-btn layui-btn-xs layui-btn-primary">增加下级</button>
		<button class="layui-btn layui-btn-xs layui-btn-primary" lay-event="edit">修改</button>
		<button class="layui-btn layui-btn-xs layui-btn-danger">删除</button>
	</div>
</div>
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="${ctxStatic}/config/Treatment.js"></script> 
<script type="text/javascript" src="${ctxStatic}/layui/layui.js"></script>
<script>

layui.config({
    base: '${ctxStatic}/layui/'
}).extend({
    treetable: 'treetable-lay/treetable'
}).use(['treetable'], function () {
    var treetable = layui.treetable;
});

// 页面初始化JS
$(function(){
	
	initMenuList(); // 获取菜单列表
	
})

// 实例化菜单列表
function initMenuList(){
	
	httpAjaxGet("a/sys/menu/list", false, function (data, status){

		var list = [];
		
		for(var i=0;i<data.list.length;i++){
			
			if(data.list[i].parentId == "1"){
				
				data.list[i].isShow = "<div class='layui-anim layui-anim-scale'><span class='layui-badge-rim layui-bg-blue'>目录</span></div>";
			
			}else if(data.list[i].isShow == "1"){
				
				data.list[i].isShow = "<div class='layui-anim layui-anim-scale'><span class='layui-badge layui-bg-gray'>菜单</span></div>";
			}else{
				
				data.list[i].isShow = "<div class='layui-anim layui-anim-scale'><span class='layui-badge-rim'>按钮</span></div>";
			}
			
			list.push(data.list[i]);
			
		}

		createTreeTable(list);
	});

}

// 生成树形表格
function createTreeTable(list){

	layui.use(['treetable', 'table'], function () {

    	var treetable = layui.treetable;
    	var table = layui.table;
		 // 渲染表格
	    treetable.render({
	        treeColIndex: 0,          // treetable新增参数
	        treeSpid: 1,             // treetable新增参数
	        treeIdName: 'id',       // treetable新增参数
	        treePidName: 'parentId',     // treetable新增参数
	        treeDefaultClose: false,   // treetable新增参数
	        treeLinkage: true,        // treetable新增参数
	        elem: '#menuList',
	        data: list,
	        toolbar: "#headButton",
	        cols: [[
	            {field:'name', title:'菜单名称'},
	            {field:'href', title:'菜单路径'},
	            {field:'permission', title:'权限标识'},
	            {field:'sort', title:'排序号', align:'center'},
	            {field:'isShow', title:'类型', width:90, align:'center'},
	            {title:'操作', align:'center', width:200, toolbar:"#colButton"},
	        ]]
	    });
		
		// 绑定列工具条的事件 
	    table.on('tool(menuList)', function(obj){

	    	var layEvent = obj.event;
	    	
	    	if(layEvent == "edit"){toolEditClick(obj);}
	    	
	    })
		 
	});
}

// 表格上方（全部展开|全部折叠）按钮的点击事件
function topButtonClick(element){
	
	layui.use(['treetable'], function () {
		
		var treetable = layui.treetable;
		
		if($(element)[0].innerText == "全部展开"){
			
			treetable.expandAll('#menuList');
			
			$(element)[0].innerText = "全部折叠";
		}else{
			
			treetable.foldAll('#menuList');
			
			$(element)[0].innerText = "全部展开";
		}
		
	})
	
}

function toolEditClick(obj){
	
	var data = obj.data;
	
	openAlert("【" + data.name + "】-- 菜单修改", ['600px','500px'], urlFormat("Hui/sys/menuEdit", data.id));
	
}
</script>
</body>
</html>