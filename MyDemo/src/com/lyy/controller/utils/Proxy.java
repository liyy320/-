package com.lyy.controller.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.lyy.utils.comm.CookieUtils;

@Controller
@RequestMapping("proxy")
public class Proxy extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/doPost", method = {RequestMethod.POST, RequestMethod.GET})
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException 
	{

        String url = request.getParameter("url");
        
        response.setContentType("text/html;charset=UTF-8");
        
        StringBuffer param = new StringBuffer();

        Enumeration enu = request.getParameterNames();

        int total = 0;

        while(enu.hasMoreElements())
        {

            String name = (String)enu.nextElement();

            if(!name.equals("url") && !name.equals("ContentType"))
            {

                if(total == 0)
                {
                    param.append(name).append("=").append(URLEncoder.encode(request.getParameter(name), "UTF-8"));
                }
                else
                {
                    param.append("&").append(name).append("=").append(URLEncoder.encode(request.getParameter(name), "UTF-8"));
                }

                total++;
 
            }
        }

        PrintWriter out = response.getWriter();

        if(url != null)
        {

            URL connect = new URL(url.toString());

            URLConnection connection = connect.openConnection();

            connection.setDoOutput(true);

            OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream());

            paramout.write(param.toString());

            paramout.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

            String line;

            CookieUtils.setResponseCookies(connection, response);
            
            while((line = reader.readLine()) != null)
            {
               out.println(line);
            }

            paramout.close();
            reader.close();
        }
	}
	
	@RequestMapping(value = "/doPostRequestPayload", method = {RequestMethod.POST, RequestMethod.GET})
	public void doPostRequestPayload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException 
	{
		String result = "";

		String url = request.getParameter("url");
		
		Cookie[] cookies = request.getCookies();
		
		for(Cookie c : cookies)
		{
			System.out.println(c.getName() + ":" + c.getValue());
		}

        String BaseRequest = request.getParameter("BaseRequest");
        
        JSONObject josn = JSONObject.parseObject(BaseRequest);
        
        URL connect = new URL(url.toString());

        HttpURLConnection connection = (HttpURLConnection) connect.openConnection();

        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json, text/plain, */*");
        connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

        writer.write(josn.toString());
        writer.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        PrintWriter out = response.getWriter();
 
        String line;
        while ((line = br.readLine()) != null)
        {
        	out.println(new String(line.getBytes("GBK"),"UTF-8"));
        }

        System.out.println(result);
        
        out.close();
        br.close();
        connection.disconnect();

	}
	
}
