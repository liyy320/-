package com.lyy.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ManHandler implements InvocationHandler
{
	private Man man;
	
	public ManHandler(Man man)
	{
		this.man = man;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arg) throws Throwable
	{
		
		before();
		method.invoke(man, null);
		after();
		
		return null;
	}
	
	public void before()
	{
		System.out.println("û�ҵ�֮ǰ");
	}
	
	public void after()
	{
		System.out.println("�ҵ�֮��");
	}
	
	
}
