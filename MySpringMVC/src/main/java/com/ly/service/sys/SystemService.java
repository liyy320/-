package com.ly.service.sys;

import org.springframework.beans.factory.InitializingBean;

import com.ly.common.config.Global;

public class SystemService extends BaseService implements InitializingBean
{

    /**
     	* 获取Key加载信息
     */
    public static boolean printKeyLoadMessage()
    {

        StringBuilder sb = new StringBuilder();
        
        sb.append("\r\n==============================欢迎使用 ==================================");
        sb.append("\n项目参数");
        sb.append("\n项目名称：" + Global.getConfig("productName"));
        sb.append("\r\n========================================================================\n");

        System.out.println(sb.toString());

        return true;
    }

	public void afterPropertiesSet() throws Exception
	{
		System.out.println("----------------afterPropertiesSet()执行；");
	}
}
