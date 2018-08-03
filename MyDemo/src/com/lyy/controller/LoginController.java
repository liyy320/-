package com.lyy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.lyy.utils.comm.BaseController;

@Controller
@RequestMapping("a")
public class LoginController extends BaseController
{

	@ResponseBody
	@RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		Map<String, Object> returnData = new HashMap<String, Object>();
		
        String username = request.getParameter("username");  
        String password = request.getParameter("password");  
 
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        token.setRememberMe(true);

        //��ȡ��ǰ��Subject  
        Subject currentUser = SecurityUtils.getSubject();

        try 
        {
            //�ڵ�����login������,SecurityManager���յ�AuthenticationToken,�����䷢�͸������õ�Realmִ�б������֤���  
            //ÿ��Realm�����ڱ�Ҫʱ���ύ��AuthenticationTokens������Ӧ  
            //������һ���ڵ���login(token)����ʱ,�����ߵ�MyRealm.doGetAuthenticationInfo()������,������֤��ʽ����˷���  
            currentUser.login(token); 

        }
        catch(UnknownAccountException uae)
        {  
        	returnData.put("status", "1");
            returnData.put("message", "�û�������");
        }
        catch(IncorrectCredentialsException ice)
        {  
        	returnData.put("status", "1");
            returnData.put("message", "���벻��ȷ");
        }
        catch(LockedAccountException lae)
        {  
        	returnData.put("status", "1");
            returnData.put("message", "�˻�������");
        }
        catch(ExcessiveAttemptsException eae)
        {  
        	returnData.put("status", "1");
            returnData.put("message", "�û�������������������");
        }
        catch(AuthenticationException ae)
        {  
            //ͨ������Shiro������ʱAuthenticationException�Ϳ��Կ����û���¼ʧ�ܻ��������ʱ���龰  
        	returnData.put("status", "1");
            returnData.put("message", "�û��������벻��ȷ");
        }

        //��֤�Ƿ��¼�ɹ�  
        if(currentUser.isAuthenticated())
        {
        	//ͨ������Shiro������ʱAuthenticationException�Ϳ��Կ����û���¼ʧ�ܻ��������ʱ���龰  
        	returnData.put("status", "0");
            returnData.put("message", "��¼�ɹ�");
        }
        else
        {  
            token.clear();  
        } 
 
        return renderString(response, returnData);
	}
	
	/** 
     * 	�û��ǳ� 
     */  
    @RequestMapping("/logout")  
    public String logout(HttpServletRequest request){

         SecurityUtils.getSubject().logout();

         return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
    } 
}
