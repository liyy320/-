package com.ly.sys.listener;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ly.service.sys.SystemService;

public class WebContextListener extends ContextLoaderListener
{
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext)
	{
		if (!SystemService.printKeyLoadMessage()) {return null;}
		
		return super.initWebApplicationContext(servletContext);
	}
}
