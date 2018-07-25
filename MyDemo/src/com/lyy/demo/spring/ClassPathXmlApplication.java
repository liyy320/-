package com.lyy.demo.spring;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class ClassPathXmlApplication implements ApplicationContext 
{

	private String fileName;
	
	public ClassPathXmlApplication(String fileName) {this.fileName = fileName;}

	@Override
	public Object getBean(String beanId)
	{

		/*获取本类的当前目录*/
		String currentPath = this.getClass().getResource("").getPath().toString();
		
		SAXReader reader = new SAXReader();	//dom4j解释器
		
		Document doc = null;	//文档本身
		Object obj   = null;	//目标创建出来的实例
		
		try
		{

			doc = reader.read(new File(currentPath + fileName));

		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

}
