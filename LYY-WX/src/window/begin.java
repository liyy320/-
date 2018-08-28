package window;

import java.awt.Image;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.Map;

import javax.swing.JFrame;

import Utils.ScriptUtils;

public class begin
{

	public static void Start()
	{

		CookieManager manager = new CookieManager();
		
        CookieHandler.setDefault(manager);
        
        loginFrame loginFrame = new loginFrame();
        
        getData getdata = new getData();
		
		Map<String, Object> result_uuid = getdata.get_uuid();
		
		if("200".equals(result_uuid.get("code")))
		{
			Image image = getdata.get_qrcode(result_uuid.get("uuid").toString());
			
			JFrame jf = loginFrame.createLoginJFrame(getdata, image);
			
			boolean NoLogin = true;
			
			String redirect_uri = "";

			do 
			{
				Map<String, Object> result_login = getdata.login(result_uuid.get("uuid").toString());
				
				if("200".equals(result_login.get("code")))
				{
					redirect_uri = result_login.get("redirect_uri").toString();
					NoLogin = false;
				}

			}while(NoLogin);
			
			getdata.webwxnewloginpage(redirect_uri);
			
			StringBuffer data_webwxinit = getdata.webwxinit();
			
			getdata.setUserinfo(ScriptUtils.getUserInfo_json(data_webwxinit.toString()));
			getdata.getContatInfo().setContactList(ScriptUtils.getContactList_json());
			getdata.setSyncKey(ScriptUtils.getSyncKey_json());
			getdata.setAllContact(ScriptUtils.getAllContanctList(getdata.webwxgetcontact()));

			jf.setVisible(false);
			loginFrame.createMainJFrame(getdata);

			getdata.webwxstatusnotify();
			
			getdata.webwxsync();

			boolean LoginOut = false;
			
			do 
			{
				Map<String, Object> synccheck_data = getdata.synccheck();
				
				if("2".equals(synccheck_data.get("selector")))
				{
					ScriptUtils.jsonFormatSyncKey(getdata.webwxsync().toString());
					getdata.setSyncKey(ScriptUtils.getSyncKey_json());
					getdata.setAddMsgList(ScriptUtils.getAddMsgList_json());
					loginFrame.addMsgJPanel(getdata);
				}

			}while(!LoginOut);
			
			
		}
		
	}
	
	//在主方法中调用createJFrame()方法
	public static void main(String args[]){Start();}
}
