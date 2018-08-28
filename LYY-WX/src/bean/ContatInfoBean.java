package bean;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ContatInfoBean
{
	private JPanel centerJPanel;			  //界面中间面板
	private JPanel searchJPanel;			  //搜索面板
	private JScrollPane zjcontactJScrollPane; //最近联系人的滚动面板
	private JPanel lxrJPanel;				  //联系人面板
	private JScrollPane allContackJPanel;	  //所有联系人面板
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
	public JScrollPane getZjcontactJScrollPane() {
		return zjcontactJScrollPane;
	}
	public void setZjcontactJScrollPane(JScrollPane zjcontactJScrollPane) {
		this.zjcontactJScrollPane = zjcontactJScrollPane;
	}
	public JScrollPane getAllContackJPanel() {
		return allContackJPanel;
	}
	public void setAllContackJPanel(JScrollPane allContackJPanel) {
		this.allContackJPanel = allContackJPanel;
	}
	
}
