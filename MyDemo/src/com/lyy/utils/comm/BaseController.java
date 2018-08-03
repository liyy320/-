package com.lyy.utils.comm;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * Controller����
 * 
 * */
public class BaseController
{
	
	/**
	 * �ͻ��˷���JSON�ַ���
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
	 * �ͻ��˷����ַ���
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
//			response.addHeader("Access-Control-Allow-Origin", "*"); //�������ʱʹ��
			response.getWriter().print(string);

			return null;
		}

		catch (IOException e) {return null;}
	}
}
