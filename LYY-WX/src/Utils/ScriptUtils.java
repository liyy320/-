package Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import bean.AddMsgBean;
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
					+ "function getRR(){return (-new Date().getTime() / 1000)}"

					+ "function getDeviceID(){return 'e' + ('' + Math.random().toFixed(15)).substring(2, 17)}"
					+ "var webwxinit_data = {};"
					+ "var SyncKey = {};"
					+ "var AddMsgCount = 0;"
					+ "var AddMsgList = [];"
					+ "var AllContanct = {};"
					+ "function jsonformat(str)"
					+ "{"
					+ "webwxinit_data = eval('(' + str + ')');"
					+ "SyncKey = webwxinit_data.SyncKey;"
					+ "}"
					+ "function getUserInfo(field)"
					+ "{"
					+ "return webwxinit_data.User[field]"
					+ "}"
					+ "function getCount(){return webwxinit_data.Count}"
					+ "function ContactList(field, index){return webwxinit_data.ContactList[index][field]}"
					+ "function jsonFormatSyncKey(str)"
					+ "{"
					+ "var res = eval('(' + str + ')');"
					+ "SyncKey = res.SyncKey;"
					+ "AddMsgCount = res.AddMsgCount;"
					+ "AddMsgList = res.AddMsgList;"
					+ "}"
					+ "function getSyncKeyCount(){return SyncKey.Count}"
					+ "function getSyncKey(field, index){return SyncKey.List[index][field]}"
					+ "function getAddMsgCount(){return AddMsgCount;}"
					+ "function getAddMsgList(field, index){return AddMsgList[index][field]}"
					+ "function jsonFormatAllContanct(str)"
					+ "{"
					+ "AllContanct = eval('(' + str + ')');"
					+ "}"
					+ "function getAllContanctCount(){return AllContanct.MemberCount;}"
					+ "function getAllContanctList(field, index){return AllContanct.MemberList[index][field]}"
					+ "");
			
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
	
	public static Object getRR()
	{
		if(engine instanceof Invocable)
		{    
			try
			{
				return ((Invocable)engine).invokeFunction("getRR");

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
				userinfo.setUserName(((Invocable)engine).invokeFunction("getUserInfo", "UserName").toString());
				
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
				
				int count = (int) ((Invocable)engine).invokeFunction("getCount");
				
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
	
	public static void jsonFormatSyncKey(String str)
	{
		if(engine instanceof Invocable)
		{    
			try
			{
				((Invocable)engine).invokeFunction("jsonFormatSyncKey", str);
				
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
	}
	
	public static Map<String,Object> getSyncKey_json()
	{
		if(engine instanceof Invocable)
		{    
			try
			{
				Map<String,Object> SyncKey = new HashMap<String,Object>();
				
				int count = (int) ((Invocable)engine).invokeFunction("getSyncKeyCount");
				
				for(int i = 0;i < count;i++)
				{
					SyncKey.put(((Invocable)engine).invokeFunction("getSyncKey", "Key", i).toString(), ((Invocable)engine).invokeFunction("getSyncKey", "Val", i).toString());
				}
				
				return SyncKey;

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
		
		return new HashMap<String,Object>();
	}
	
	public static List<AddMsgBean> getAddMsgList_json()
	{
		if(engine instanceof Invocable)
		{    
			try
			{
				List<AddMsgBean> AddMsgList = new ArrayList<AddMsgBean>();
				
				int count = (int) ((Invocable)engine).invokeFunction("getAddMsgCount");
				
				for(int i = 0;i < count;i++)
				{
					AddMsgBean bean = new AddMsgBean();

					bean.setMsgId(((Invocable)engine).invokeFunction("getAddMsgList", "MsgId", i).toString());
					bean.setFromUserName(((Invocable)engine).invokeFunction("getAddMsgList", "FromUserName", i).toString());
					bean.setToUserName(((Invocable)engine).invokeFunction("getAddMsgList", "ToUserName", i).toString());
					bean.setMsgType(((Invocable)engine).invokeFunction("getAddMsgList", "MsgType", i).toString());
					bean.setContent(((Invocable)engine).invokeFunction("getAddMsgList", "Content", i).toString());
					bean.setStatus(((Invocable)engine).invokeFunction("getAddMsgList", "Status", i).toString());
					bean.setCreateTime(((Invocable)engine).invokeFunction("getAddMsgList", "CreateTime", i).toString());
					bean.setNewMsgId(((Invocable)engine).invokeFunction("getAddMsgList", "NewMsgId", i).toString());
					bean.setImgStatus(((Invocable)engine).invokeFunction("getAddMsgList", "ImgStatus", i).toString());
					
					AddMsgList.add(bean);

				}

				return AddMsgList;

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
		
		return new ArrayList<AddMsgBean>();
	}
	
	public static List<ContactBean> getAllContanctList(String str)
	{
		
		if(engine instanceof Invocable)
		{    
			try
			{
				((Invocable)engine).invokeFunction("jsonFormatAllContanct", str);
				
				List<ContactBean> AllContanctList = new ArrayList<ContactBean>();
				
				int count = (int) ((Invocable)engine).invokeFunction("getAllContanctCount");
				
				for(int i = 0; i < count; i++)
				{
					ContactBean bean = new ContactBean();
					
					bean.setNickName(((Invocable)engine).invokeFunction("getAllContanctList", "NickName", i).toString());
					bean.setUserName(((Invocable)engine).invokeFunction("getAllContanctList", "UserName", i).toString());
					bean.setHeadImgUrl(((Invocable)engine).invokeFunction("getAllContanctList", "HeadImgUrl", i).toString());
					
					AllContanctList.add(bean);
				}

				return AllContanctList;

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
