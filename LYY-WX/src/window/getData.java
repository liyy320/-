package window;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import Utils.HttpUtils;
import Utils.ScriptUtils;
import bean.ContactBean;
import bean.UserInfoBean;

public class getData
{

	private int tip = 1;
	//登陆信息
	private Map<String, Object> loginInfo = new HashMap<String, Object>();
	public UserInfoBean userinfo = new UserInfoBean();
	public List<ContactBean> ContactList = new ArrayList<ContactBean>();
	
	/**
	 * 获取登陆二维码时用的随机码
	 * 
	 * */
	public Map<String, Object> get_uuid()
	{
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("appid", "wx782c26e4c19acffb");
		params.put("redirect_uri", "https%3A%2F%2Fwx.qq.com%2Fcgi-bin%2Fmmwebwx-bin%2Fwebwxnewloginpage");
		params.put("fun", "new");
		params.put("lang", "zh_CN");
		params.put("_", (new Date()).getTime());
		
		StringBuffer sb = HttpUtils.doGet("https://login.wx.qq.com/jslogin", params);
		
		result.put("code", sb.toString().split(";")[0].split("=")[1].trim());
		result.put("uuid", sb.toString().split(";")[1].split("\"")[1].trim());
		
		return result;
	}
	
	/**
	 * 
	 * 获取二维码
	 * */
	public Image get_qrcode(String uuid)
	{
		Image image = null;
		
		try 
		{
			image = ImageIO.read(new URL("https://login.weixin.qq.com/qrcode/" + uuid));
			
		} catch (Exception e)
		{
			e.toString();
		}
		
		return image;
	}
	
	/**
	 * 
	 * 轮询检测用户是否扫描二维码
	 * */
	public Map<String, Object> login(String uuid)
	{

		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("loginicon", "true");
		params.put("uuid", uuid);
		params.put("tip", tip);
		params.put("r", ScriptUtils.getR());
		params.put("_", (new Date()).getTime());
		
		StringBuffer sb = HttpUtils.doGet("https://login.wx.qq.com/cgi-bin/mmwebwx-bin/login", params);
		
		tip = 0;
		
		String [] win = sb.toString().split(";");
		
		String code = win[0].split("=")[1];
		
		result.put("code", code);
		
		if("201".equals(code))
		{
			
			String src = (win[1].split("=")[1] + ";" + win[2]).trim().replaceAll("'", "");
			
			result.put("src", src);
			
		}
		else if("200".equals(code))
		{
			result.put("redirect_uri", win[1].substring(win[1].toString().indexOf("=") + 2, win[1].length() - 1));
		}
		
		return result;
	}
	
	public void webwxnewloginpage(String url)
	{
		StringBuffer sb = HttpUtils.doGet(url + "&fun=new&version=v2");
		
		loginInfo.put("skey", sb.toString().split("skey>")[1].replaceAll("</", ""));
		loginInfo.put("wxsid", sb.toString().split("wxsid>")[1].replaceAll("</", ""));
		loginInfo.put("wxuin", sb.toString().split("wxuin>")[1].replaceAll("</", ""));
		loginInfo.put("pass_ticket", sb.toString().split("pass_ticket>")[1].replaceAll("</", ""));
		loginInfo.put("isgrayscale", sb.toString().split("isgrayscale>")[1].replaceAll("</", ""));
		
	}

	public StringBuffer webwxinit()
	{
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("r", ScriptUtils.getR());
		params.put("pass_ticket", loginInfo.get("pass_ticket"));
		
		StringBuffer baserequest = new StringBuffer("{");

		baserequest.append("\"BaseRequest\":{");
		baserequest.append("\"Uin\":\"" + loginInfo.get("wxuin") + "\",");
		baserequest.append("\"Sid\":\"" + loginInfo.get("wxsid") + "\",");
		baserequest.append("\"Skey\":\"" + loginInfo.get("skey") + "\",");
		baserequest.append("\"DeviceID\":\"" + ScriptUtils.getDeviceID() + "\"}");
		baserequest.append("}");

		StringBuffer sb = HttpUtils.doPostRequestPayload("https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxinit", params, baserequest.toString());
		
		return sb;
	}
	
	public Image getimg(String url)
	{

		Image image = null;
		
		try 
		{
			image = ImageIO.read(new URL("https://wx.qq.com" + url));
			
		} catch (Exception e)
		{
			e.toString();
		}
		
		return image;
	}
}
