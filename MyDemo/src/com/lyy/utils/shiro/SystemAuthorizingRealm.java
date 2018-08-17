package com.lyy.utils.shiro;

import java.io.Serializable;

import org.apache.shiro.realm.AuthorizingRealm;

import com.lyy.po.User;
import com.lyy.utils.comm.UserUtils;

public abstract class SystemAuthorizingRealm extends AuthorizingRealm 
{

	/**
	 * ��Ȩ�û���Ϣ
	 */
	public static class Principal implements Serializable
	{

		private static final long serialVersionUID = 1L;
		
		private String id;
		private String loginName; // ��¼��
		private String name; 	  // ����
		
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
		 * ��ȡSESSIONID
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
