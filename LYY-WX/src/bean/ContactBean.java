package bean;

import java.util.ArrayList;
import java.util.List;

public class ContactBean
{
	private String userName;
	private String headImgUrl;
	private String nickName;
	private String remarkName;
	private List<AddMsgBean> ChatContent = new ArrayList<AddMsgBean>();

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	public List<AddMsgBean> getChatContent() {
		return ChatContent;
	}

	public void setChatContent(List<AddMsgBean> chatContent) {
		ChatContent = chatContent;
	}
	
}
