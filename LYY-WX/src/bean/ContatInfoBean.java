package bean;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ContatInfoBean
{
	private JPanel centerJPanel;
	private JPanel searchJPanel;
	private JPanel lxrJPanel;
	private List<JPanel> contactJPanel = new ArrayList<JPanel>();
	private JPanel contactJPanelClick;
	private List<ContactBean> ContactList = new ArrayList<ContactBean>();
	
	
	public JPanel getCenterJPanel() {
		return centerJPanel;
	}
	public void setCenterJPanel(JPanel centerJPanel) {
		this.centerJPanel = centerJPanel;
	}
	
	public JPanel getSearchJPanel() {
		return searchJPanel;
	}
	public void setSearchJPanel(JPanel searchJPanel) {
		this.searchJPanel = searchJPanel;
	}
	public JPanel getLxrJPanel() {
		return lxrJPanel;
	}
	public void setLxrJPanel(JPanel lxrJPanel) {
		this.lxrJPanel = lxrJPanel;
	}
	public JPanel getContactJPanelClick() {
		return contactJPanelClick;
	}
	public void setContactJPanelClick(JPanel contactJPanelClick) {
		this.contactJPanelClick = contactJPanelClick;
	}
	public List<ContactBean> getContactList() {
		return ContactList;
	}
	public void setContactList(List<ContactBean> contactList) {
		ContactList = contactList;
	}
	public List<JPanel> getContactJPanel() {
		return contactJPanel;
	}
	public void setContactJPanel(List<JPanel> contactJPanel) {
		this.contactJPanel = contactJPanel;
	}
	
}
