package com.lyy.utils.comm;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * Controller父类
 * 
 * */
public class BaseController
{
	
	/**
	 * 客户端返回JSON字符串
	 * 
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderString(HttpServletResponse response, Object object) 
	{
		return renderString(response, String.valueOf(object), "application/json");
	}
	
	/**
	 * 客户端返回字符串
	 * 
	 * @param response
	 * @param string
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string, String type)
	{
		try {

			response.reset();
			response.setContentType(type);
			response.setCharacterEncoding("utf-8");
//			response.addHeader("Access-Control-Allow-Origin", "*"); //处理跨域时使用
			response.getWriter().print(string);

			return null;
		}

		catch (IOException e) {return null;}
	}
}
