<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>百度地图</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">  
	    html{height:100%}    
	    body{height:100%;margin:0px;padding:0px}    
    	#container{height:100%}    
</style> 
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=Anvq5VI0bCt5jYSmGvqq6KdxE99rvVgc"></script>
<script>
window.onload = function(){
	var map = new BMap.Map("container"); 
	
	var point = new BMap.Point(116.404, 39.915); 
	
	map.centerAndZoom(point, 15);
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	map.addControl(new BMap.MapTypeControl());
	
}
</script>
</head>
<body>
	<div id="container"></div> 
</body>
</html>
