package window;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import Utils.HttpUtils;
import Utils.ScriptUtils;
import bean.AddMsgBean;
import bean.ChatJPanelBean;
import bean.ContactBean;
import bean.ContatInfoBean;
import bean.LoginInfoBean;
import bean.UserInfoBean;

public class getData
{

	private int tip = 1;
	
	/**��Ŀ��Ϣ*/
	public final static String PROJECTPATH = System.getProperty("user.dir");
	
	//��½��Ϣ 
	private LoginInfoBean loginInfo = new LoginInfoBean(); 					//��¼��Ϣ
	private UserInfoBean userinfo = new UserInfoBean();						//��¼�û���Ϣ
	private ContatInfoBean contatInfo = new ContatInfoBean();
	private ContactBean contactDataClick = null;							//��ǰѡ�е��������ϵ�˵���Ϣ
	private ChatJPanelBean chatJPanel = new ChatJPanelBean();				//�������Ķ���
	private Map<String, Object> SyncKey = null;								//��ȡ������Ϣʱ�õ�
	private List<AddMsgBean> AddMsgList = null;								//������Ϣ�б�
	private List<ContactBean> allContact = null;							//������ϵ���б�
	/**
	 * ��ȡ��½��ά��ʱ�õ������
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
	 * ��ȡ��ά��
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
	 * ��ѯ����û��Ƿ�ɨ���ά��
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
		
		loginInfo.setSkey(sb.toString().split("skey>")[1].replaceAll("</", ""));
		loginInfo.setWxsid(sb.toString().split("wxsid>")[1].replaceAll("</", ""));
		loginInfo.setWxuin(sb.toString().split("wxuin>")[1].replaceAll("</", ""));
		loginInfo.setPass_ticket(sb.toString().split("pass_ticket>")[1].replaceAll("</", ""));
		loginInfo.setIsgrayscale(sb.toString().split("isgrayscale>")[1].replaceAll("</", ""));

	}

	public StringBuffer webwxinit()
	{
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("r", ScriptUtils.getR());
		params.put("pass_ticket", loginInfo.getPass_ticket());
		
		StringBuffer baserequest = new StringBuffer("{");

		baserequest.append("\"BaseRequest\":{");
		baserequest.append("\"Uin\":\"" + loginInfo.getWxuin() + "\",");
		baserequest.append("\"Sid\":\"" + loginInfo.getWxsid() + "\",");
		baserequest.append("\"Skey\":\"" + loginInfo.getSkey() + "\",");
		baserequest.append("\"DeviceID\":\"" + ScriptUtils.getDeviceID() + "\"}");
		baserequest.append("}");

		StringBuffer sb = HttpUtils.doPostRequestPayload("https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxinit", params, baserequest.toString());
		
		return sb;
	}
	
	public StringBuffer webwxstatusnotify()
	{
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("r", ScriptUtils.getR());
		params.put("pass_ticket", loginInfo.getPass_ticket());
		
		StringBuffer baserequest = new StringBuffer("{");

		baserequest.append("\"BaseRequest\":{");
		baserequest.append("\"Uin\":\"" + loginInfo.getWxuin() + "\",");
		baserequest.append("\"Sid\":\"" + loginInfo.getWxsid() + "\",");
		baserequest.append("\"Skey\":\"" + loginInfo.getSkey() + "\",");
		baserequest.append("\"DeviceID\":\"" + ScriptUtils.getDeviceID() + "\"},");
		baserequest.append("\"ClientMsgId\":\"" + (new Date()).getTime() + "\",");
		baserequest.append("\"Code\":\"3\",");
		baserequest.append("\"FromUserName\":\"" + this.userinfo.getUserName() + "\",");
		baserequest.append("\"ToUserName\":\"" + this.userinfo.getUserName() + "\"");
		baserequest.append("}");
		
		StringBuffer sb = HttpUtils.doPostRequestPayload("https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxstatusnotify", params, baserequest.toString());
		
		
		return sb;
	}
	
	public Map<String, Object> synccheck()
	{
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("sid",  loginInfo.getWxsid());
		params.put("skey", loginInfo.getSkey());
		params.put("uin",  loginInfo.getWxuin());
		params.put("r", ScriptUtils.getR());
		params.put("pass_ticket", loginInfo.getPass_ticket());
		params.put("synckey", synckeyFormat());
		params.put("deviceid", ScriptUtils.getDeviceID());
		params.put("_", (new Date()).getTime());
		
		StringBuffer baserequest = new StringBuffer("{");

		baserequest.append("\"BaseRequest\":{");
		baserequest.append("\"Uin\":\"" + loginInfo.getWxuin() + "\",");
		baserequest.append("\"Sid\":\"" + loginInfo.getWxsid() + "\",");
		baserequest.append("\"Skey\":\"" + loginInfo.getSkey() + "\",");
		baserequest.append("\"DeviceID\":\"" + ScriptUtils.getDeviceID() + "\"}");
		baserequest.append("}");
		
		StringBuffer sb = HttpUtils.doPostRequestPayload("https://webpush.wx.qq.com/cgi-bin/mmwebwx-bin/synccheck", params, baserequest.toString());
		
		String [] syncchecks = sb.toString().split("=");
		String synccheck = syncchecks[1].substring(1, syncchecks[1].length() - 1);
		
		result.put("retcode", synccheck.split(",")[0].split(":")[1].replaceAll("\"", ""));
		result.put("selector", synccheck.split(",")[1].split(":")[1].replaceAll("\"", ""));
		
		return result;
	}
	
	public String synckeyFormat()
	{
		StringBuffer result = new StringBuffer();
		
		boolean isFirst = true;
		for(Map.Entry<String, Object> entry : this.SyncKey.entrySet())
		{
			if(!isFirst) {result.append("|");}
			
			result.append(entry.getKey() + "_" + entry.getValue());
			
			isFirst = false;
		}
		
		return result.toString();
	}
	
	public StringBuffer webwxsync()
	{
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("sid",  loginInfo.getWxsid());
		params.put("skey", loginInfo.getSkey());
		params.put("uin",  loginInfo.getWxuin());
		params.put("pass_ticket", loginInfo.getPass_ticket());
		
		StringBuffer baserequest = new StringBuffer("{");

		baserequest.append("\"BaseRequest\":{");
		baserequest.append("\"Uin\":\"" + loginInfo.getWxuin() + "\",");
		baserequest.append("\"Sid\":\"" + loginInfo.getWxsid() + "\",");
		baserequest.append("\"Skey\":\"" + loginInfo.getSkey() + "\",");
		baserequest.append("\"DeviceID\":\"" + ScriptUtils.getDeviceID() + "\"},");
		baserequest.append("\"SyncKey\":{\"Count\":\"" + this.SyncKey.size() + "\",\"List\":[");
		
		boolean isFirst = true;
		for(Map.Entry<String, Object> entry : this.SyncKey.entrySet())
		{
			if(!isFirst) {baserequest.append(",");}

			baserequest.append("{\"Key\":\"" + entry.getKey() + "\", \"Val\": \"" + entry.getValue() + "\"}");
			
			isFirst = false;
		}

		baserequest.append("]},");
		baserequest.append("\"rr\":\"" + ScriptUtils.getRR() + "\"");
		baserequest.append("}");
		
		StringBuffer sb = HttpUtils.doPostRequestPayload("https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxsync", params, baserequest.toString());
		
		
		return sb;
		
	}
	
	public String webwxgetcontact()
	{
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("lang",  "zh_CN");
		params.put("skey", loginInfo.getSkey());
		params.put("seq",  "0");
		params.put("pass_ticket", loginInfo.getPass_ticket());
		
		StringBuffer baserequest = new StringBuffer("{");

		baserequest.append("\"BaseRequest\":{");
		baserequest.append("\"Uin\":\"" + loginInfo.getWxuin() + "\",");
		baserequest.append("\"Sid\":\"" + loginInfo.getWxsid() + "\",");
		baserequest.append("\"Skey\":\"" + loginInfo.getSkey() + "\",");
		baserequest.append("\"DeviceID\":\"" + ScriptUtils.getDeviceID() + "\"}");
		baserequest.append("}");
		
		StringBuffer sb = HttpUtils.doPostRequestPayload("https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxgetcontact", params, baserequest.toString());
		
		return sb.toString();
	}
	
	public Image getimg(String url, int isLocation)
	{

		Image image = null;

		try 
		{
			if(isLocation == 1)
			{
				File  file = new File(url);
				
				image = ImageIO.read(file);
			}
			else
			{
				image = ImageIO.read(new URL("https://wx.qq.com" + url));
				
				if(image == null) 
				{
					image = ImageIO.read(new URL("https://wx.qq.com" + url));
				}
			}
			
		} catch (Exception e)
		{
			e.toString();
		}
		
		return image;
	}

	
	/********************************************************************************/
	public UserInfoBean getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfoBean userinfo) {
		this.userinfo = userinfo;
	}

	public ContactBean getContactDataClick() {
		return contactDataClick;
	}

	public void setContactDataClick(ContactBean contactDataClick) {
		this.contactDataClick = contactDataClick;
	}

	public ChatJPanelBean getChatJPanel() {
		return chatJPanel;
	}

	public void setChatJPanel(ChatJPanelBean chatJPanel) {
		this.chatJPanel = chatJPanel;
	}

	public Map<String, Object> getSyncKey() {
		return SyncKey;
	}

	public void setSyncKey(Map<String, Object> syncKey) {
		SyncKey = syncKey;
	}

	public List<AddMsgBean> getAddMsgList() {
		return AddMsgList;
	}

	public void setAddMsgList(List<AddMsgBean> addMsgList) {
		AddMsgList = addMsgList;
	}

	public ContatInfoBean getContatInfo() {
		return contatInfo;
	}

	public void setContatInfo(ContatInfoBean contatInfo) {
		this.contatInfo = contatInfo;
	}

	public List<ContactBean> getAllContact() {
		return allContact;
	}

	public void setAllContact(List<ContactBean> allContact) {
		this.allContact = allContact;
	}
	
}
