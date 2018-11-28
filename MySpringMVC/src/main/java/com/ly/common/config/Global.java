package com.ly.common.config;

import java.util.Map;

import com.ly.common.collect.Maps;
import com.ly.common.utils.PropertiesLoader;
import com.ly.common.utils.StringUtils;

/**
 * 全局配置类
 * 
 * @author Ly
 * @version 2018-11-23
 */
public class Global
{

	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("config.properties");
	
	/******项目配置参数START***************************************************************/
	
	/*项目名*/
	public final static String PRODUCTNAME = getConfig("productName");
	
	/******项目配置参数END***************************************************************/
	
	/**
	 * 获取配置
	 * 
	 * @see
	 */
	public static String getConfig(String key)
	{
		String value = map.get(key);

		if (value == null)
		{
			value = loader.getProperty(key);

			map.put(key, value != null ? value : StringUtils.EMPTY);

		}
		return value;
	}
	
}
