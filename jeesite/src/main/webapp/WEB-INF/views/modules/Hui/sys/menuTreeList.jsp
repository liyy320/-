<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>菜单管理</title>

<link rel="stylesheet" type="text/css" href="${ctxStatic}/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/layui/treetable-lay/treetable.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/H-ui/Hui-iconfont/1.0.8/iconfont.css" />

</head>
<body>
<table id="menuList" class="layui-table" lay-filter="menuList"></table>

<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="${ctxStatic}/layui/layer/2.4/layer.js"></script>
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

$(function(){
	
	httpAjaxGet("a/sys/menu/list", false, function (data, status){initMenuList(data);});
	
})



function initMenuList(data){

	var list = [];
	
	for(var i=0;i<data.list.length;i++){
		
		list.push(data.list[i]);
		
	}

	console.log(list);

	layui.use(['treetable'], function () {

    	var treetable = layui.treetable;
		 // 渲染表格
	    treetable.render({
	        treeColIndex: 0,          // treetable新增参数
	        treeSpid: 1,             // treetable新增参数
	        treeIdName: 'id',       // treetable新增参数
	        treePidName: 'parentId',     // treetable新增参数
	        treeDefaultClose: true,   // treetable新增参数
	        treeLinkage: true,        // treetable新增参数
	        elem: '#menuList',
	        data: list,
	        cols: [[
	            {field: 'name', title: '菜单名称'},
	            {field: 'href', title: '菜单路径'},
	            {field: 'icon', title: '菜单图标'},
	            {field: 'permission', title: '权限标识'},
	            {field: 'sort', title: '排序'}
	        ]]
	    });
	 
	});

}
</script>
</body>
</html>