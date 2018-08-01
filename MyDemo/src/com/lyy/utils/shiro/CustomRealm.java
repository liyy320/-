package com.lyy.utils.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * �Զ���shiro��֤
 * 
 * */
public class CustomRealm extends AuthorizingRealm
{

	/**
	 * 
	 * ��֤
	 * 
	 * */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{
		
		//��token�л�ȡ�û���Ϣ
		String username = (String) token.getPrincipal();
		
		// ͨ�� username �����ݿ��в�ѯ
		
		// �����ѯ�����򷵻� null
        if(!username.equals("zhangsan")){return null;}
        
        //��ȡ�����ݿ��ѯ�������û����� 
        String dbPassword = "zhangsan";//����ʹ�þ�̬����ģ��
        
        //������֤��Ϣ�ɸ��� AuthenticatingRealm ������֤
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, dbPassword, getName());

        return simpleAuthenticationInfo;
		
	}

	 /**
	  * 
      * ��Ȩ
      * 
      **/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		
        // ����������Ϣ�����ݿ��в�ѯȨ������
        // ����ʹ�þ�̬����ģ��
        List<String> permissions = new ArrayList<String>();

        permissions.add("user:*");
        permissions.add("department:*");
        
        // ��Ȩ����Ϣ���ΪAuthorizationInfo
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		
        // ģ�����ݣ����� manager ��ɫ
        simpleAuthorizationInfo.addRole("manager");
        
        for(String permission:permissions)
        {
            simpleAuthorizationInfo.addStringPermission(permission);
        }

        return simpleAuthorizationInfo;
	}

}