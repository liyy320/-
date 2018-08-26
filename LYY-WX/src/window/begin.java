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
			
			JFrame jf = loginFrame.createLoginJFrame(image);
			
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
			
			jf.setVisible(false);
			
			StringBuffer data_webwxinit = getdata.webwxinit();
			
			getdata.userinfo    = ScriptUtils.getUserInfo_json(data_webwxinit.toString());
			getdata.ContactList = ScriptUtils.getContactList_json();

			loginFrame.createMainJFrame(getdata);
			
			
		}
		
	}
	
	//在主方法中调用createJFrame()方法
	public static void main(String args[]){Start();}
}
