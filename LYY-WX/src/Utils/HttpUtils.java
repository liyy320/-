package Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class HttpUtils
{
	
	public static StringBuffer doGet(String url, Map<String, Object> params) 
	{
		
		StringBuffer sb = new StringBuffer();
		
		StringBuffer param = new StringBuffer();
		
		try
		{
			for(Map.Entry<String, Object> entry : params.entrySet())
			{
				if(param.length() > 0){param.append("&");}
				
				param.append(entry.getKey() + "=" + entry.getValue());
			}
			
			URL connect = new URL(url + "?" + param);
			
			// 此时cnnection只是为一个连接对象,待连接中
			URLConnection connection = connect.openConnection();
			
			connection.setDoOutput(true);
			
			connection.connect();//建立连接
			
			OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream());

            paramout.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

            String line;

            System.out.println("java发送http请求----------------------");
            System.out.println("URL：");
            System.out.println(connect);
            System.out.println("返回结果为");
            
            while((line = reader.readLine()) != null)
            {

               System.out.println(line);
               sb.append(line);

            }
            
            System.out.println("---------------------------------------");
            
            paramout.close();
            reader.close();
		        
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return sb;
	}
	
	public static StringBuffer doGet(String url) 
	{
		
		StringBuffer sb = new StringBuffer();
		
		try
		{
			
			
			URL connect = new URL(url);
			
			// 此时cnnection只是为一个连接对象,待连接中
			URLConnection connection = connect.openConnection();
			
			connection.setDoOutput(true);

			connection.connect();//建立连接
			
			OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream());

            paramout.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

            String line;

            System.out.println("java发送http请求----------------------");
            System.out.println("URL：");
            System.out.println(connect); 
            System.out.println("返回结果为:");
            
            while((line = reader.readLine()) != null)
            {


               System.out.println(line);
               sb.append(line);

            }
            
            System.out.println("---------------------------------------");
            
            paramout.close();
            reader.close();
		        
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return sb;
	}
	
	public static StringBuffer doPostRequestPayload(String url, Map<String, Object> params, String baserequest)
	{
		StringBuffer sb = new StringBuffer();
		
		StringBuffer param = new StringBuffer();
		
		try
		{
			
			for(Map.Entry<String, Object> entry : params.entrySet())
			{
				if(param.length() > 0){param.append("&");}
				
				param.append(entry.getKey() + "=" + entry.getValue());
			}
			
			URL connect = new URL(url.toString() + "?" + param);
			
			HttpsURLConnection connection = (HttpsURLConnection) connect.openConnection();

	        connection.setDoInput(true);
	        connection.setDoOutput(true);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Accept", "application/json, text/plain, */*");
	        connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

	        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

	        writer.write(baserequest);
	        writer.close();

	        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        
	        System.out.println("java发送http请求----------------------");
            System.out.println("URL：");
            System.out.println(connect);
            System.out.println("返回结果为");

	        String line;
	        
	        while ((line = br.readLine()) != null)
	        {
	        	sb.append(new String(line.getBytes("GBK"),"UTF-8"));
	        	
	            System.out.println(new String(line.getBytes("GBK"),"UTF-8"));
	        }

	        System.out.println("---------------------------------------");
	        
	        br.close();
	        connection.disconnect();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return sb;
	}
	
	public static StringBuffer doPostRequestPayload(String url)
	{
		StringBuffer sb = new StringBuffer();
		
		try
		{
			URL connect = new URL(url.toString());
			
			HttpsURLConnection connection = (HttpsURLConnection) connect.openConnection();

	        connection.setDoInput(true);
	        connection.setDoOutput(true);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Accept", "application/json, text/plain, */*");
	        connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

	        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

	        writer.close();

	        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        
	        System.out.println("java发送http请求----------------------");
            System.out.println("URL：");
            System.out.println(connect);
            System.out.println("返回结果为");

	        String line;
	        
	        while ((line = br.readLine()) != null)
	        {
	        	sb.append(new String(line.getBytes("GBK"),"UTF-8"));
	        	
	            System.out.println(new String(line.getBytes("GBK"),"UTF-8"));
	        }

	        System.out.println("---------------------------------------");
	        
	        br.close();
	        connection.disconnect();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return sb;
	}
	
}
