package com.lyy.utils.comm;


import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils
{
	public static Cookie createCookie(String key, String value, String domain, String path, int expiry, boolean secure)
	{
		Cookie c = new Cookie(key, value);
		
		c.setPath(path);
//		c.setDomain(domain);
		c.setMaxAge(expiry);
		c.setSecure(secure);
		c.setHttpOnly(false);
		
		return c;
	}

	public static List<Map<String, Object>> getResponseCookies(URLConnection connection)
	{
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		Map<String, List<String>> map = connection.getHeaderFields();
        
        List<String> cookie = map.get("Set-Cookie");
        
        if(cookie == null) {return result;}

        for(int i=0;i<cookie.size();i++)
        {
        	
        	Map<String, Object> mape = new HashMap<String, Object>();
        	
        	String elements [] = cookie.get(i).split(";");
        	
        	mape.put("name", elements[0].split("=")[0]);
        	
        	for(String element : elements)
        	{
        		String key = element.split("=")[0];
        		
            	if(element.split("=").length > 1)
            	{
            		String value = element.substring(key.length() + 1).trim();
            		
            		mape.put(key.trim(), value);
            	}
        	}
        	
        	result.add(mape);
        	
        }
        
        return result;
        
	}
	
	public static void setResponseCookies(URLConnection connection, HttpServletResponse response) 
	{
		List<Map<String, Object>> cookies = getResponseCookies(connection);
		
		for(Map<String, Object> map : cookies)
		{
			String key = map.get("name").toString();
			
			response.addCookie(CookieUtils.createCookie(key, map.get(key).toString(), "wx.qq.com", map.get("Path").toString(), 100000000, false));
		}
	}
}
