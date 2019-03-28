<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>${fns:getConfig('productName')}</title>
  <link href="${ctxStatic}/layui/css/layui.css" rel="stylesheet">
</head>
<body class="layui-layout-body">
<div id="app" class="layui-layout layui-layout-admin" data-anim="layui-anim-up">
  <div class="layui-header layui-bg-blue">
    <div class="layui-logo" style="color:#FFFFFF;">${fns:getConfig('productName')}</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item" v-for="li in menuList" v-if="li.parentId === '1'"><a href="javascript:;" v-on:click="currentTopMenuId = li.id" style="color:#FFFFFF;">{{ li.name }}</a></li>
    </ul>
    <ul class="layui-nav layui-layout-right layui-bg-blue">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">${fns:getUser().name}
        </a>
        <dl class="layui-nav-child">
          <dd><a href="${ctx}/sys/user/info">基本资料</a></dd>
          <dd><a href="${ctx}/sys/user/modifyPwd">安全设置</a></dd>
          <dd><a href="${ctx}/oa/oaNotify/self">我的通知</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="${ctx}/logout">退了</a></li>
    </ul>
  </div>

  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree layui-bg-cyan">
        <li class="layui-nav-item layui-nav-itemed" v-for="li in menuList" v-if="li.parentId == currentTopMenuId && li.isShow === '1' && !li.href">
          <a class="" href="javascript:;">{{ li.name }}</a>
          <dl class="layui-nav-child">
            <dd v-for="li_dd in menuList" v-if="li_dd.parentId === li.id && li_dd.isShow === '1'"><a href="#" v-on:click="clickMenu(li_dd)">{{ li_dd.name }}</a></dd>
          </dl>
        </li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 0px;">
	<div class="layui-tab layui-tab-card" lay-allowClose="true" style="height:99.5%;margin:0px;">
		  <ul class="layui-tab-title">
			    <li v-for="tab in tabElement" :class="tab.li_clas">{{ tab.name }}</li>
		  </ul>
		  <div class="layui-tab-content" style="padding:5px;">
			    <div v-for="tab in tabElement" :class="tab.div_clas">
			    	<iframe  :src="'${ctx}' + tab.href" style="margin:0;border-width:0px;height:92%;width:100%;"></iframe>
			    </div>
		  </div>
	</div>
	</div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>
<script src="${ctxStatic}/vue/vue.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/vue/vue-resource.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/config/Global.js" type="text/javascript"></script>
<script src="${ctxStatic}/config/Treatment.js" type="text/javascript"></script>
<script src="${ctxStatic}/layui/layui.js" type="text/javascript"></script>
<script>
//JavaScript代码区域

window.onload = function(){
    var vm = new Vue({
        el:'#app',
        data:{
        	
        	menuList : [],
        	currentTopMenuId:"27",
        	tabElement:[],
        	
        },
        mounted:function(){

        	layui.use(['element'], function(){var element = layui.element;});
        	
        	VueGet(this, "a/sys/menu/list", this.initMenu, function(data){AjaxErro(data.msg)});
        },
        created:function(){
        	
        },
        methods:{
        	
        	// 初始化菜单
        	initMenu:function(data){
        		
        		this.menuList = data.body.list;
        		
        	},
        	clickMenu:function(element){
        			
        		var isExist = false;
        		
        		// 循环遍历当前菜单是否已经被打开
      			for(var i = 0; i < this.tabElement.length; i++){
         				
       				if(element.id == this.tabElement[i].id){

       					this.tabElement[i]["li_clas"] = "layui-this";
       					this.tabElement[i]["div_clas"] = "layui-tab-item layui-show";
       					
       					isExist = true;
       				} else
       				{
       					this.tabElement[i]["li_clas"] = "";
       					this.tabElement[i]["div_clas"] = "layui-tab-item";
       				}
         		}
        		
        		// 如果没有被打开则打开该菜单的页面
      			if(!isExist){

      				element["li_clas"] = "layui-this";
      				element["div_clas"] = "layui-tab-item layui-show";
      				
      				this.tabElement.push(element);
      			}
        		
      			this.tabElement = this.tabElement;
        		
        	}
        	
        }
    });
}
</script>
</body>
</html>