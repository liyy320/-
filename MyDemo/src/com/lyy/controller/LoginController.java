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

        //获取当前的Subject  
        Subject currentUser = SecurityUtils.getSubject();

        try 
        {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
            currentUser.login(token); 

        }
        catch(UnknownAccountException uae)
        {  
        	returnData.put("status", "1");
            returnData.put("message", "用户不存在");
        }
        catch(IncorrectCredentialsException ice)
        {  
        	returnData.put("status", "1");
            returnData.put("message", "密码不正确");
        }
        catch(LockedAccountException lae)
        {  
        	returnData.put("status", "1");
            returnData.put("message", "账户已锁定");
        }
        catch(ExcessiveAttemptsException eae)
        {  
        	returnData.put("status", "1");
            returnData.put("message", "用户名或密码错误次数过多");
        }
        catch(AuthenticationException ae)
        {  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
        	returnData.put("status", "1");
            returnData.put("message", "用户名或密码不正确");
        }

        //验证是否登录成功  
        if(currentUser.isAuthenticated())
        {
        	//通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
        	returnData.put("status", "0");
            returnData.put("message", "登录成功");
        }
        else
        {  
            token.clear();  
        } 
 
        return renderString(response, returnData);
	}
	
	/** 
     * 	用户登出 
     */  
    @RequestMapping("/logout")  
    public String logout(HttpServletRequest request){

         SecurityUtils.getSubject().logout();

         return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
    } 
}
