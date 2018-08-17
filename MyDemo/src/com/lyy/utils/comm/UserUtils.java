package com.lyy.utils.comm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.lyy.po.User;
import com.lyy.utils.shiro.SystemAuthorizingRealm.Principal;

/**
 * 用户工具类
 * @author lyy
 * @version 2018-08-07
 */
public class UserUtils
{

	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static User getUser()
	{

		Principal principal = getPrincipal();

		if (principal != null){

//			User user = get(principal.getId());

//			if (user != null){ return user;}

			return new User();
		}

		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}
	
	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal()
	{
		try
		{

			Subject subject = SecurityUtils.getSubject();

			Principal principal = (Principal) subject.getPrincipal();

			if (principal != null){return principal;}

		}catch (Exception e) {}

		return null;
	}

	public static Session getSession()
	{
		try
		{
			Subject subject = SecurityUtils.getSubject();

			Session session = subject.getSession(false);

			if (session == null){session = subject.getSession();}
			if (session != null){return session;}

		}
		catch (InvalidSessionException e){}

		return null;
	}
	
}
