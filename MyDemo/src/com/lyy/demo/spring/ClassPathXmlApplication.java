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

		/*��ȡ����ĵ�ǰĿ¼*/
		String currentPath = this.getClass().getResource("").getPath().toString();
		
		SAXReader reader = new SAXReader();	//dom4j������
		
		Document doc = null;	//�ĵ�����
		Object obj   = null;	//Ŀ�괴��������ʵ��
		
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
