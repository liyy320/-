package com.lyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/hello")
public class HelloController
{

	  @RequestMapping(value="/world", method=RequestMethod.GET)
	  public String hello(Model model)
	  {

		  System.out.println("进入成功");
		  
	      model.addAttribute("msg", "你好spring mvc");

	      return "index";
	  }
	
}
