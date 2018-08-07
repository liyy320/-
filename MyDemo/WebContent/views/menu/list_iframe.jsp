<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8" />
    <title>菜单列表</title>
    <link href="/MyDemo/_urc/lib/layui/css/layui.css" rel="stylesheet" />
    <link href="/MyDemo/_urc/lib/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet" />
    <link href="/MyDemo/_urc/lib/winui/css/winui.css" rel="stylesheet" />
</head>
<body>
    <div class="winui-toolbar">
        <div class="winui-tool">
            <button id="reloadTable" class="winui-toolbtn"><i class="fa fa-refresh" aria-hidden="true"></i>刷新数据</button>
            <button id="addMenu" class="winui-toolbtn"><i class="fa fa-plus" aria-hidden="true"></i>新增菜单</button>
            <button id="editMenu" class="winui-toolbtn"><i class="fa fa-pencil" aria-hidden="true"></i>编辑菜单</button>
            <button id="deleteMenu" class="winui-toolbtn"><i class="fa fa-trash" aria-hidden="true"></i>删除选中</button>
        </div>
    </div>
    <div style="margin:auto 10px;">
        <table id="menu" lay-filter="menutable"></table>
        <script type="text/html" id="barMenu">
            <a class="layui-btn layui-btn-xs" lay-event="setting">权限设置</a>
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
        <script type="text/html" id="openTypeTpl">
            {{#  if(d.openType == 1){ }}
            HTML
            {{#  } else if(d.openType==2) { }}
            Iframe
            {{#  } }}
        </script>
        <script type="text/html" id="isNecessary">
            {{#  if(d.isNecessary){ }}
            是
            {{#  } else { }}
            否
            {{#  } }}
        </script>
        <div class="tips">Tips：1.系统菜单不可以删除 2.修改或添加数据后暂不支持自动刷新表格</div>
    </div>
    <script src="/MyDemo/_urc/lib/layui/layui.js"></script>
    <script type="text/javascript">
        layui.config({
            base: '/MyDemo/_urc/js/'
        }).use('menulist');
    </script>
</body>
</html>