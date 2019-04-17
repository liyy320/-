<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>MyIMLogin</title>

</head>
<body>
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="${ctxStatic}/config/Treatment.js"></script>
<script>

 var url = "https://login.wx.qq.com/jslogin";
 var appid = "wx782c26e4c19acffb";
 var redirect_uri = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxnewloginpage";

 httpPost("a/app/myIM/GET", {url:url,appid:appid,redirect_uri:redirect_uri}, function(data){console.log(data)})

</script>

</body>
</html>