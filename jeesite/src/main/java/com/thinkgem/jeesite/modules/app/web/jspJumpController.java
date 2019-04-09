package com.thinkgem.jeesite.modules.app.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${adminPath}/app/jump")
public class jspJumpController {
	
	@RequestMapping(value = {"/", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		System.out.println(request.getParameter("data"));
		
		model.addAttribute("data", request.getParameter("data"));
		
		return "modules/" + request.getParameter("URL");
	}

}
