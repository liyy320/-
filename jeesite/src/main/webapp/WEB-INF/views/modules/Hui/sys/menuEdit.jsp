<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>菜单管理</title>

<link rel="stylesheet" type="text/css" href="${ctxStatic}/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/H-ui/Hui-iconfont/1.0.8/iconfont.css" />
<style>
#myDiv{padding-left:10px;padding-right:10px;margin-top:10px;}

.downpanel .layui-select-title span {line-height: 38px;}

 .downpanel dl dd:hover {background-color: inherit;}

</style>
</head>
<body>
<div id="myDiv">
<form class="layui-form layui-form-pane" lay-filter="layuiForm">

<input type="hidden" name="id" value="${data}">

  	<div class="layui-form-item">
	 	<label class="layui-form-label">上级菜单</label>
	     <div class="layui-input-block">
	         <div class="layui-unselect layui-form-select downpanel">
	             <div class="layui-select-title">
	                 <span class="layui-input layui-unselect" id="treeclass">选择上级菜单</span>
	                 <input type="hidden" name="parentId" value="22">
	                 <i class="layui-edge"></i>
	             </div>
	             <dl class="layui-anim layui-anim-upbit">
	                 <dd>
	                     <ul id="parentMenu"></ul>
	                 </dd>
	             </dl>
	         </div>
	     </div>
	  </div>
   	<div class="layui-form-item">
	    <label class="layui-form-label">菜单名称</label>
	    <div class="layui-input-block">
	      <input type="text" name="name" required  lay-verify="name" placeholder="请输入菜单名称" autocomplete="off" class="layui-input">
	    </div>
   </div>
   <div class="layui-form-item">
	    <label class="layui-form-label">菜单路径</label>
	    <div class="layui-input-block">
	      <input type="text" name="href" required placeholder="请输入菜单路径" autocomplete="off" class="layui-input">
	    </div>
   </div>
   <div class="layui-form-item">
	    <label class="layui-form-label">权限标识</label>
	    <div class="layui-input-block">
	      <input type="text" name="permission" required placeholder="请输入权限标识" autocomplete="off" class="layui-input">
	    </div>
   </div>
   <div class="layui-form-item">
	    <label class="layui-form-label">权限类型</label>
	    <div class="layui-input-block">
	      <input type="radio" name="isShow" value="1" title="菜单">
	      <input type="radio" name="isShow" value="0" title="按钮">
	    </div>
   </div>
  <div class="layui-form-item">
	    <label class="layui-form-label">排序号</label>
	    <div class="layui-input-block">
	      <input type="text" name="sort" required  lay-verify="required" placeholder="请输入排序号" class="layui-input">
	    </div>
   </div>
   
   <button lay-submit lay-filter="layuiFormSubmit" style="display:none;" id="enterSubmit">提交</button>  
   
</form>
</div>
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="${ctxStatic}/config/Treatment.js"></script> 
<script type="text/javascript" src="${ctxStatic}/layui/layui.js"></script>
<script>

layui.use(['form', 'tree'], function(){
	
	tree = layui.tree, form = layui.form;

$(function(){
	
	//获取菜单信息
	httpAjaxGet("a/sys/menu/form?id=" + getInputValByName("id"), false, function (data, status){initEditMenu(data);});

	//获取父级菜单信息
	httpAjaxGet("a/sys/menu/parentList", false, function (data, status){initParentMenu(data.list);});
	
})

//初始化下拉树
function initParentMenu(data){
	tree({
	      elem: "#parentMenu",
	      nodes: data,
	      click: function (node) {
	          var $select = $($(this)[0].elem).parents(".layui-form-select");
	          $select.removeClass("layui-form-selected").find(".layui-select-title span").html(node.name).end().find("input:hidden[name='parentId']").val(node.id);
	      }
	  });

	$(".downpanel").on("click", ".layui-select-title", function (e) {

	      $(".layui-form-select").not($(this).parents(".layui-form-select")).removeClass("layui-form-selected");
	      $(this).parents(".downpanel").toggleClass("layui-form-selected");
	      layui.stope(e);}).
	      on("click", "dl i", function (e) {layui.stope(e);});

	  $(document).on("click", function (e) {
	      $(".layui-form-select").removeClass("layui-form-selected");
	  });
	  
}

//初始化表单数据
var menu, parentData;
function initEditMenu(data){

	menu = data.menu, parentData = data.parent;
	
	form.val("layuiForm", {
		"name" : menu.name,
		"href":menu.href,
		"permission":menu.permission,
		"sort":menu.sort,
		"isShow":menu.isShow
		
	})
	
	var $select = $(".layui-form-select");
    $select.removeClass("layui-form-selected").find(".layui-select-title span").html(parentData.name).end().find("input:hidden[name='parentId']").val(parentData.id);
}

//form表单验证
form.verify({
		
	name : function(value, item){
		
		if(value == ""){return '菜单名称不能为空';}
	}
		
})

//监听form表单的提交事件
form.on("submit(layuiFormSubmit)", function(data) {
	
		//判断用户是否修改了信息，如果没有修改则不提交
		if(!compareJSONObject(data.field, menu)){
			
			//正在提交动画
			layer.load();
			
			//执行提交操作
			httpPost("a/sys/menu/save", $(data.form).serialize(), function(data, status){
				
				//如果操作成功则刷新父页面数据|提示用户操作成功|关闭该窗口
				if(data.status == '0'){
					
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					
					parent.initMenuList();
					
					parent.MsgAlert(data.message);
					
					parent.layer.close(index); //再执行关闭

				}else{
					parent.ErroAlert(data.message);

				}
			})
			
		} else {
			
			WarringAlert("未修改任何信息！");
			
		}
		
		return false;          
});

});

// 提交form表单
function submit(){$("#enterSubmit").click();}

</script>
</body>
</html>