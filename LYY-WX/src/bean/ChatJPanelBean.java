package bean;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChatJPanelBean
{
	private JPanel jpRight;
	private JLabel jlNickName;
	private JPanel allMsgJPanel;
	
	
	public JPanel getJpRight() {
		return jpRight;
	}

	public void setJpRight(JPanel jpRight) {
		this.jpRight = jpRight;
	}

	public JLabel getJlNickName()
	{
		return jlNickName;
	}

	public void setJlNickName(JLabel jlNickName)
	{
		this.jlNickName = jlNickName;
	}

	public JPanel getAllMsgJPanel() {
		return allMsgJPanel;
	}

	public void setAllMsgJPanel(JPanel allMsgJPanel) {
		this.allMsgJPanel = allMsgJPanel;
	}
	
	
}
