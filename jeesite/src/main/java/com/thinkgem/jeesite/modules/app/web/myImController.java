package com.thinkgem.jeesite.modules.app.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.utils.HttpUtils;
import com.thinkgem.jeesite.common.web.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/app/myIM")
public class myImController extends BaseController{

	@RequestMapping(value = {"/GET", ""})
	public String list(HttpServletRequest request, HttpServletResponse response) {
		
		JSONObject jsonObject = new JSONObject();
		
		String result = HttpUtils.sendGet(request.getParameter("url"), HttpUtils.paramsFormat(request));
		
		jsonObject.put("data", result);
		
		return renderString(response, jsonObject);
	}
	
}
