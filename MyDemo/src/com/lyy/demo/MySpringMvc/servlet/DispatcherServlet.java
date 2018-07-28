package com.lyy.demo.MySpringMvc.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyy.demo.MySpringMvc.annotation.Controller;
import com.lyy.demo.MySpringMvc.annotation.Quatifier;
import com.lyy.demo.MySpringMvc.annotation.RequestMapping;
import com.lyy.demo.MySpringMvc.annotation.Service;

/**
 * Servlet implementation class Dispatcher
 */
@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	
	List<String> packageNames = new ArrayList<String>();
	
	Map<String, Object> instanceMap = new HashMap<String, Object>();
	Map<String, Object> handerMap   = new HashMap<String, Object>();

    public DispatcherServlet() {super();}
    
    public void init(ServletConfig config) 
    {
    	scanPackage("com.lyy.demo.MySpringMvc");
    	
    	try
    	{
			filterAndInstance();
		}
    	catch (Exception e)
    	{
			e.printStackTrace();
		}
    	
    	handerMap();
    	
    	ioc();
    }

    /**
	 * 扫描包下的所有文件
	 * 
	 * @param Package
	 */
    private void scanPackage(String Package)
    {
    	URL url = this.getClass().getClassLoader().getResource("/" + replaceTo(Package));
    	
    	String pathFile = url.getFile();
    	
    	File file = new File(pathFile);
    	
    	String [] fileLit = file.list();
    	
    	for (String path : fileLit)
    	{
    		File eachFile = new File(pathFile + path);
    		
    		if(eachFile.isDirectory())
    		{
    			scanPackage(Package + eachFile.getName());
    		}
    		else
    		{
    			packageNames.add(Package + "." + eachFile.getName());
    		}
		}
    	
    }
    
    /**
     * 将扫描到的类进行实例化，并且存放在instanceMap中
     * */
    private void filterAndInstance() throws Exception
    {

    	if(packageNames.size() <= 0) {return;}
    	
    	for (String classname : packageNames)
    	{
    		Class<?> cName = Class.forName(classname.replace(".class", "").trim());
    		
    		if(cName.isAnnotationPresent(Controller.class))
    		{
    			Object instance = cName.newInstance();
    			
    			Controller controller = (Controller) cName.getAnnotation(Controller.class);
    			
    			String key = controller.value();
    			
    			instanceMap.put(key, instance);
    			
    		}
    		else if(cName.isAnnotationPresent(Service.class))
    		{
    			Object instance = cName.newInstance();
    			
    			Service service = cName.getAnnotation(Service.class);
    			
    			String key = service.value();
    			
    			instanceMap.put(key, instance);
    			
    		} else {continue;}
		}
    	
    }

    /**建立映射关系*/
    public void handerMap()
    {
    	if(packageNames.size() <= 0) {return;}

    	for(Map.Entry<String, Object> entry : instanceMap.entrySet())
    	{
    		if(entry.getValue().getClass().isAnnotationPresent(Controller.class))
    		{
    			Controller controller = entry.getValue().getClass().getAnnotation(Controller.class);
    			
    			String ctValue = controller.value();
    			
    			Method [] methods = entry.getValue().getClass().getMethods();
    			
    			for (Method method : methods)
    			{
					if(method.isAnnotationPresent(RequestMapping.class))
					{
						RequestMapping rm = method.getAnnotation(RequestMapping.class);
						
						String rmValue = rm.value();
						
						handerMap.put("/" + ctValue + "/" + rmValue, method);
					} else {continue;}
				}
    		} else {continue;}
    	}
    }

    public void ioc()
    {
    	if(instanceMap.isEmpty()) {return;}
    	
    	for(Map.Entry<String, Object> entry : instanceMap.entrySet())
    	{
    		Field [] fields = entry.getValue().getClass().getDeclaredFields();
    		
    		for(Field field : fields)
    		{
    			field.setAccessible(true);	//可访问私有属性
    			
    			if(field.isAnnotationPresent(Quatifier.class)) 
    			{
    				Quatifier quatifier = field.getAnnotation(Quatifier.class);
    				
    				String value = quatifier.value();
    				
    				field.setAccessible(false);
    				
    				try
    				{
						field.set(entry.getValue(), instanceMap.get(value));
					}
    				catch (Exception e)
    				{
						e.printStackTrace();
					}
    			}
    		}
    	}
    }

    private String replaceTo(String path){return path.replaceAll("\\.", "/");}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		this.doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String url = request.getRequestURI();
		
		String context = request.getContextPath();
		
		String path = url.replace(context, "");
		
		Method method = (Method) handerMap.get(path);
		
//		SpringmvcController controller = instanceMap.get(path.split("/")[1]);
		
//		method.invoke(method, args)
		
		
	}

}
