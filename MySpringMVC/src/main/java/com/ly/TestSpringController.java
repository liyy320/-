package com.ly;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestSpringController
{
	String message = "Welcome to Spring MVC!";
	 
    @RequestMapping("/hello")
    public ModelAndView showMessage(@RequestParam(value = "name", required = false, defaultValue = "Spring") String name)
    {
 
    	//ָ����ͼ
        ModelAndView mv = new ModelAndView("hellospring");

        //����ͼ��������Ҫչʾ��ʹ�õ����ݣ�����ҳ����ʹ��
        mv.addObject("message", message);
        mv.addObject("name", name);

        return mv;
    }
}