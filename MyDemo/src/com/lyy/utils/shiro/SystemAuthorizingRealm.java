package com.lyy.utils.shiro;

import java.io.Serializable;

import org.apache.shiro.realm.AuthorizingRealm;

import com.lyy.po.User;
import com.lyy.utils.comm.UserUtils;

public abstract class SystemAuthorizingRealm extends AuthorizingRealm 
{

	/**
	 * 授权用户信息
	 */
	public static class Principal implements Serializable
	{

		private static final long serialVersionUID = 1L;
		
		private String id;
		private String loginName; // 登录名
		private String name; 	  // 姓名
		
		public Principal(User user, boolean mobileLogin)
		{
			this.id = user.getId();
			this.loginName = user.getUsername();
			this.name = user.getName();
		}

		public String getId()
		{
			return id;
		}
		
		public String getLoginName()
		{
			return loginName;
		}

		public String getName()
		{
			return name;
		}


		/**
		 * 获取SESSIONID
		 */
		public String getSessionid()
		{
			try
			{
				return (String) UserUtils.getSession().getId();

			}
			catch (Exception e)
			{
				return "";
			}
		}
		
	}
}
