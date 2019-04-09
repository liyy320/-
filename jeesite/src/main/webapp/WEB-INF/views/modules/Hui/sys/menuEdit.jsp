<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>菜单管理</title>

<link rel="stylesheet" type="text/css" href="${ctxStatic}/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/H-ui/Hui-iconfont/1.0.8/iconfont.css" />
<style>
#myDiv{padding-left:10px;padding-right:10px;}
</style>
</head>
<body>
<div id="myDiv">
<form class="layui-form layui-form-pane" action="">
	
	<div class="layui-form-item">
	    <label class="layui-form-label">上级菜单</label>
	    <div class="layui-input-inline">
	      <select name="city" lay-verify="required">
	        <option value="">请选择上级菜单</option>
	        <option value="0">北京</option>
	        <option value="1">上海</option>
	        <option value="2">广州</option>
	        <option value="3">深圳</option>
	        <option value="4">杭州</option>
	      </select>
	    </div>
  	</div>	
   <div class="layui-form-item">
	    <label class="layui-form-label">菜单名称</label>
	    <div class="layui-input-inline">
	      <input type="text" name="title" required  lay-verify="required" placeholder="请输入菜单名称" autocomplete="off" class="layui-input">
	    </div>
   </div>
   <div class="layui-form-item">
	    <label class="layui-form-label">菜单路径</label>
	    <div class="layui-input-inline">
	      <input type="text" name="title" required  lay-verify="required" placeholder="请输入菜单路径" autocomplete="off" class="layui-input">
	    </div>
   </div>
   <div class="layui-form-item">
	    <label class="layui-form-label">权限标识</label>
	    <div class="layui-input-inline">
	      <input type="text" name="title" required  lay-verify="required" placeholder="请输入权限标识" autocomplete="off" class="layui-input">
	    </div>
   </div>
   <div class="layui-form-item">
	    <label class="layui-form-label">权限类型</label>
	    <div class="layui-input-block">
	      <input type="radio" name="sex" value="1" title="菜单">
	      <input type="radio" name="sex" value="0" title="按钮" checked>
	    </div>
   </div>
   <div class="layui-form-item">
	    <label class="layui-form-label">菜单图标</label>
	    <div class="layui-input-inline">
	      <select name="city" lay-verify="required">
	        <option value="">请选择菜单图标</option>
	        <option value="0">北京</option>
	        <option value="1">上海</option>
	        <option value="2">广州</option>
	        <option value="3">深圳</option>
	        <option value="4">杭州</option>
	      </select>
	    </div>
  </div>
  <div class="layui-form-item">
	    <label class="layui-form-label">排序号</label>
	    <div class="layui-input-inline">
	      <input type="text" name="title" required  lay-verify="required" placeholder="请输入排序号" class="layui-input">
	    </div>
   </div>	
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
</div>
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="${ctxStatic}/config/Treatment.js"></script> 
<script type="text/javascript" src="${ctxStatic}/layui/layui.js"></script>
<script>
//Demo
layui.use('form', function(){
  var form = layui.form;
  
  //监听提交
  form.on('submit(formDemo)', function(data){
    layer.msg(JSON.stringify(data.field));
    return false;
  });
});
</script>
</body>
</html>