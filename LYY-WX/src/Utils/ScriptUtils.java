package Utils;

import java.util.ArrayList;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import bean.ContactBean;
import bean.UserInfoBean;

public class ScriptUtils
{

	static ScriptEngineManager manager = new ScriptEngineManager();
	static ScriptEngine engine = manager.getEngineByName("javascript");
	
	static
	{
		
		try 
		{
			
			engine.eval("function getR(){return ~new Date()}"

					+ "function getDeviceID(){return 'e' + ('' + Math.random().toFixed(15)).substring(2, 17)}"
					+ "var webwxinit_data = {};"
					+ "function jsonformat(str)"
					+ "{"
					+ "webwxinit_data = eval('(' + str + ')')"
					+ "}"
					+ "function getUserInfo(field)"
					+ "{"
					+ "return webwxinit_data.User[field]"
					+ "}"
					+ "function getCount(){return webwxinit_data.Count}"
					+ "function ContactList(field, index){return webwxinit_data.ContactList[index][field]}");
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Object getR()
	{
		if(engine instanceof Invocable)
		{    
			try
			{
				return ((Invocable)engine).invokeFunction("getR");

			}
			catch (NoSuchMethodException e)
			{
				e.printStackTrace();
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static Object getDeviceID()
	{
		if(engine instanceof Invocable)
		{    
			try
			{
				return ((Invocable)engine).invokeFunction("getDeviceID");

			}
			catch (NoSuchMethodException e)
			{
				e.printStackTrace();
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static UserInfoBean getUserInfo_json(String str)
	{
		if(engine instanceof Invocable)
		{    
			try
			{
				((Invocable)engine).invokeFunction("jsonformat", str);

				UserInfoBean userinfo = new UserInfoBean();
				
				userinfo.setHeadImgUrl(((Invocable)engine).invokeFunction("getUserInfo", "HeadImgUrl").toString());
				
				return userinfo;

			}
			catch (NoSuchMethodException e)
			{
				e.printStackTrace();
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}
		}
		
		return new UserInfoBean();
	}
	
	public static List<ContactBean> getContactList_json()
	{
		if(engine instanceof Invocable)
		{    
			try
			{
				List<ContactBean> ContactList = new ArrayList<ContactBean>();
				
				int count = (int) ((Invocable)engine).invokeFunction("getCount", "HeadImgUrl");
				
				for(int i=0;i<count;i++)
				{
					ContactBean bean = new ContactBean();
					
					bean.setNickName(((Invocable)engine).invokeFunction("ContactList", "NickName", i).toString());
					bean.setHeadImgUrl(((Invocable)engine).invokeFunction("ContactList", "HeadImgUrl", i).toString());
					
					ContactList.add(bean);
				}
				
				return ContactList;

			}
			catch (NoSuchMethodException e)
			{
				e.printStackTrace();
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}
		}
		
		return new ArrayList<ContactBean>();
	}
	
}
