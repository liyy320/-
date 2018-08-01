package test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * shiroȨ�޲���
 * */
public class ShiroTest
{
    public static void main(String [] args) {

        // ��ȡ shiro.ini �ļ�����
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject currentUser = SecurityUtils.getSubject();

        Session session = currentUser.getSession();

        session.setAttribute("someKey", "aValue");

        String value = (String) session.getAttribute("someKey");

        if (value.equals("aValue"))
        {
            System.out.println("someKey ��ֵ��" + value);
        }

        // ��½
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "zhangsan");

        token.setRememberMe(true);

        try
        {

            currentUser.login(token);

        }
        catch (UnknownAccountException uae)
        {
            System.out.println("�û���������:" + token.getPrincipal());
        } 
        catch (IncorrectCredentialsException ice)
        {
            System.out.println("�˻����� " + token.getPrincipal()  + " ����ȷ!");
        }
        catch (LockedAccountException lae) 
        {
            System.out.println("�û��� " + token.getPrincipal() + " ������ !");
        }

        // ��֤�ɹ���
        if (currentUser.isAuthenticated())
        {
            
            System.out.println("�û� " + currentUser.getPrincipal() + " ��½�ɹ���");
            
//            //���Խ�ɫ
//            System.out.println("�Ƿ�ӵ�� manager ��ɫ��" + currentUser.hasRole("manager"));
//            
//            //����Ȩ��
//            System.out.println("�Ƿ�ӵ�� user:create Ȩ��" + currentUser.isPermitted("user:create"));
            
            //�˳�
            currentUser.logout();
        }

    }
}
