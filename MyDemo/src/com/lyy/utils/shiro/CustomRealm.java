package com.lyy.utils.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

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
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
	{
		
		//��ȡ�����û��������������  
        //ʵ�������authcToken�Ǵ�LoginController����currentUser.login(token)��������  
        //����token�����ö���һ����,��������org.apache.shiro.authc.UsernamePasswordToken@33799a1e  
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;

//      User user = userService.getByUsername(token.getUsername());  
//      if(null != user){  
//          AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), user.getNickname());  
//          this.setSession("currentUser", user);  
//          return authcInfo;  
//      }else{  
//          return null;  
//      }

        //�˴�����ȶ�,�ȶԵ��߼�Shiro����,����ֻ�践��һ����������ص���ȷ����֤��Ϣ  
        //˵���˾��ǵ�һ���������¼�û���,�ڶ���������Ϸ��ĵ�¼����(�����Ǵ����ݿ���ȡ����,������Ϊ����ʾ��Ӳ������)  
        //����һ��,�����ĵ�¼ҳ���Ͼ�ֻ������ָ�����û����������ͨ����֤  
        if("papio".equals(token.getUsername()))
        {  

            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("papio", "papio", this.getName());

            this.setSession("currentUser", "papio");  

            return authcInfo;  

        }
        else if("big".equals(token.getUsername()))
        {

            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("big", "big", this.getName()); 

            this.setSession("currentUser", "big");

            return authcInfo;  

        }  

        //û�з��ص�¼�û�����Ӧ��SimpleAuthenticationInfo����ʱ,�ͻ���LoginController���׳�UnknownAccountException�쳣  

        return null; 
		
	}

	 /**
	  * 
      *	��Ȩ
      * 
      **/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		
		//��ȡ��ǰ��¼���û���,�ȼ���(String)principals.fromRealm(this.getName()).iterator().next()  
        String currentUsername = (String)super.getAvailablePrincipal(principals);

//      List<String> roleList = new ArrayList<String>();  
//      List<String> permissionList = new ArrayList<String>();  
//      //�����ݿ��л�ȡ��ǰ��¼�û�����ϸ��Ϣ  
//      User user = userService.getByUsername(currentUsername);  
//      if(null != user){  
//          //ʵ����User�а������û���ɫ��ʵ������Ϣ  
//          if(null!=user.getRoles() && user.getRoles().size()>0){  
//              //��ȡ��ǰ��¼�û��Ľ�ɫ  
//              for(Role role : user.getRoles()){  
//                  roleList.add(role.getName());  
//                  //ʵ����Role�а����н�ɫȨ�޵�ʵ������Ϣ  
//                  if(null!=role.getPermissions() && role.getPermissions().size()>0){  
//                      //��ȡȨ��  
//                      for(Permission pmss : role.getPermissions()){  
//                          if(!StringUtils.isEmpty(pmss.getPermission())){  
//                              permissionList.add(pmss.getPermission());  
//                          }  
//                      }  
//                  }  
//              }  
//          }  
//      }else{  
//          throw new AuthorizationException();  
//      }  
//      //Ϊ��ǰ�û����ý�ɫ��Ȩ��  
//      SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
//      simpleAuthorInfo.addRoles(roleList);  
//      simpleAuthorInfo.addStringPermissions(permissionList);

        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();

        //ʵ���п��ܻ�������ע�͵����������ݿ�ȡ��  
        if(null != currentUsername && "papio".equals(currentUsername))
        {

            //���һ����ɫ,�������������ϵ����,����֤�����û�ӵ��admin��ɫ    
            simpleAuthorInfo.addRole("admin");

            //���Ȩ��  
            simpleAuthorInfo.addStringPermission("admin:manage");

            System.out.println("��Ϊ�û�[papio]������[admin]��ɫ��[admin:manage]Ȩ��");

            return simpleAuthorInfo;

        }
        else if(null != currentUsername && "big".equals(currentUsername))
        {

            System.out.println("��ǰ�û�[big]����Ȩ"); 

            return simpleAuthorInfo; 

        }

        //���÷���ʲô������ֱ�ӷ���null�Ļ�,�ͻᵼ���κ��û�����/admin/listUser.jspʱ�����Զ���ת��unauthorizedUrlָ���ĵ�ַ  
        //���applicationContext.xml�е�<bean id="shiroFilter">������ 

        return null;  
	}
	
	/** 
     * 	��һЩ���ݷŵ�ShiroSession��,�Ա��������ط�ʹ�� 
     * @see ����Controller,ʹ��ʱֱ����HttpSession.getAttribute(key)�Ϳ���ȡ�� 
     */  
    private void setSession(Object key, Object value)
    {  

        Subject currentUser = SecurityUtils.getSubject();

        if(null != currentUser)
        {

            Session session = currentUser.getSession();

            System.out.println("SessionĬ�ϳ�ʱʱ��Ϊ[" + session.getTimeout() + "]����");

            if(null != session)
            {  
                session.setAttribute(key, value);
            }  
        }  
    } 

}
