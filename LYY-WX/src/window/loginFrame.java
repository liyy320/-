package window;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class loginFrame extends Frame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * 创建界面的方法
	 * 
	 * */
	public JFrame createLoginJFrame(Image image)
	{
		 // 实例化一个JFrame对象
		 JFrame jf = new JFrame("微信");
		
		 jf.setSize(388, 538); // 设置窗体大小
		 
		 // 获取一个容器
		 Container container = jf.getContentPane(); 
		 
		 container.setLayout(null);
		 container.setBackground(Color.white);//设置容器的背景颜色
		 
		 myPanel mp = new myPanel(image, 236, 236);

		 mp.setLocation((jf.getWidth() - mp.getWidth())/2, 80);
		 
		 container.add(mp);
		 
		 jf.setVisible(true); // 使窗体可视
		 jf.setResizable(false);
	     jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);// 设置窗体关闭方式
	     
	     return jf;
	}
	
	public void createMainJFrame(getData getdata)
	{
		 // 实例化一个JFrame对象
		 JFrame jf = new JFrame("微信");
		 
		 jf.dispose();
		 
		 jf.setSize(1103, 781); // 设置窗体大小
		 
		 Container container = jf.getContentPane();
		 
		 container.setLayout(null);
		 container.setBackground(Color.white);//设置容器的背景颜色
		 
		 //界面最左面面板
		 JPanel left_jp = new JPanel();
		 
		 left_jp.setLayout(null);
		 left_jp.setSize(75, jf.getHeight());
		 
		 myPanel mp = new myPanel(getdata.getimg(getdata.getUserinfo().getHeadImgUrl()), 45, 45);

		 mp.setLocation((left_jp.getWidth() - mp.getWidth())/2, 25);
		 
		 left_jp.add(mp);
		 left_jp.setBackground(Color.decode("#393D49"));
		 left_jp.setLocation(0, 0);
		 
		 JPanel center_jp = createCenterJPanel(jf, left_jp, getdata);
		 
		 JPanel right_jp  = createRightJPanel(jf, left_jp, center_jp, getdata);
		 
		 //向容器中添加面板
		 container.add(left_jp);
		 container.add(center_jp);
		 container.add(right_jp);

		 //设置主窗口的属性
		 jf.setSize(1103, 781); // 设置窗体大小
		 jf.setVisible(true); 	// 使窗体可视
		 jf.setResizable(false);
	     jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// 设置窗体关闭方式
	}
	
	/**
	 * 创建中间联系人面板
	 * */
	public JPanel createCenterJPanel(JFrame jf, JPanel left_jp, getData getdata)
	{
		//界面中间面板，显示联系人
		 JPanel center_jp = new JPanel();
		 
		 center_jp.setLayout(null);
		 center_jp.setSize(320, jf.getHeight());
		 
		 
		 //中间面板上方的搜索区域
		 JPanel search_jp = new JPanel();
		 
		 search_jp.setLayout(null);
		 search_jp.setSize(320, 80);
		 search_jp.setBackground(Color.decode("#F2F2F2"));
		 
		 JTextField search_jf = new JTextField();
		 
		 search_jf.setText("搜索");
		 search_jf.setSize((int)(search_jp.getWidth() * 0.8), 30);
		 search_jf.setLocation(10, (search_jp.getHeight() - search_jf.getHeight())/2 );
		 
		 search_jp.add(search_jf);
		
		//中间面板下方的联系人区域
		 JPanel lxr_jp = new JPanel();
		 
		 JScrollPane jScrollPane = new JScrollPane(lxr_jp);
		 
		 jScrollPane.setBounds(0, 80, center_jp.getWidth(), center_jp.getHeight() - search_jp.getHeight() - 33);

		 lxr_jp.setLayout(null);
		 lxr_jp.setPreferredSize(new Dimension(jScrollPane.getWidth() - 20, jf.getHeight() - search_jp.getHeight()));
		 lxr_jp.setLocation(0, search_jp.getHeight());
		 
		 int contact_jp_Y = 0;

		 //循环添加最近联系人
		 for(int i=0;i<getdata.getContatInfo().getContactList().size();i++)
		 {
			 
			 int index = i;
			 
			 if(!"".equals(getdata.getContatInfo().getContactList().get(i).getNickName())) 
			 {
				 JPanel contact_jp = new JPanel();
				 
				 contact_jp.setLayout(null);
				 contact_jp.setSize(center_jp.getWidth(), search_jp.getHeight());
				 contact_jp.setLocation(0, contact_jp_Y * contact_jp.getHeight());
				 contact_jp.setBackground(Color.decode("#F2F2F2"));
				 
				 //添加联系人头像
				 myPanel img = new myPanel(getdata.getimg(getdata.getContatInfo().getContactList().get(i).getHeadImgUrl()), 45, 45);

				 img.setLocation(10, 25);
				 
				 JLabel nickName = new JLabel(getdata.getContatInfo().getContactList().get(i).getNickName());
				 
				 nickName.setFont(new Font("宋体", 0, 14));
				 nickName.setSize(130,20);
				 nickName.setLocation(img.getX() * 2 + img.getWidth(), 25);
				 
				 contact_jp.addMouseListener(new MouseListener()
				 {

					@Override
					public void mouseReleased(MouseEvent e){}
					
					@Override
					public void mousePressed(MouseEvent e){}
					
					@Override
					public void mouseExited(MouseEvent e)
					{
						if(contact_jp.equals(getdata.getContatInfo().getContactJPanelClick())) {return;}
						
						contact_jp.setBackground(Color.decode("#F2F2F2"));
					}
					
					@Override
					public void mouseEntered(MouseEvent e)
					{
						if(contact_jp.equals(getdata.getContatInfo().getContactJPanelClick())) {return;}
						
						contact_jp.setBackground(Color.decode("#D8D8D8"));
					}
					
					@Override
					public void mouseClicked(MouseEvent e)
					{
						if(getdata.getContatInfo().getContactJPanelClick() != null) {getdata.getContatInfo().getContactJPanelClick().setBackground(Color.decode("#F2F2F2"));}
						
						contact_jp.setBackground(Color.decode("#BDBDBD"));
						
						getdata.getChatJPanel().getJlNickName().setText(getdata.getContatInfo().getContactList().get(index).getNickName());
						
						getdata.getContatInfo().setContactJPanelClick(contact_jp);getdata.setContactDataClick(getdata.getContatInfo().getContactList().get(index));
					}
				});
				 
				 getdata.getContatInfo().getContactJPanel().add(contact_jp);
				 
				 contact_jp.add(img);
				 contact_jp.add(nickName);
				 lxr_jp.add(contact_jp);
				 
				 contact_jp_Y++;
			 }

		 }

		 getdata.getContatInfo().setLxrJPanel(lxr_jp);
		 getdata.getContatInfo().setSearchJPanel(search_jp);
		 
		 center_jp.add(search_jp);
		 center_jp.add(jScrollPane);
		 center_jp.setBackground(Color.decode("#eeeeee"));
		 center_jp.setLocation(left_jp.getWidth(), 0);
		 
		 getdata.getContatInfo().setCenterJPanel(center_jp);
		 
		 return center_jp;
	}
	
	public void addMsgJPanel(getData getdata)
	{
		if(getdata.getAddMsgList().size() == 0) {return;}
		
		for(JPanel j : getdata.getContatInfo().getContactJPanel())
		{
			j.setLocation(0, j.getY() + j.getHeight() * getdata.getAddMsgList().size());
			
		}
		
		int contact_jp_Y = 0;
		
		for(int i = 0;i < getdata.getAddMsgList().size();i++)
		{
			 int index = i;
			
			 JPanel contact_jp = new JPanel();
			 
			 contact_jp.setLayout(null);
			 contact_jp.setSize(getdata.getContatInfo().getCenterJPanel().getWidth(), getdata.getContatInfo().getSearchJPanel().getHeight());
			 contact_jp.setLocation(0, contact_jp_Y * contact_jp.getHeight());
			 contact_jp.setBackground(Color.decode("#F2F2F2"));
			 
			 //添加联系人头像
			 myPanel img = new myPanel(getdata.getimg("/cgi-bin/mmwebwx-bin/webwxgeticon?seq=653201387&username=filehelper&skey=@crypt_da540d5a_9e7c32206591c145954f9e9020a8ac27"), 45, 45);
	
			 img.setLocation(10, 25);
			 
			 JLabel nickName = new JLabel("测试新增信息");
			 
			 nickName.setFont(new Font("宋体", 0, 14));
			 nickName.setSize(130,20);
			 nickName.setLocation(img.getX() * 2 + img.getWidth(), 25);
			 
			 contact_jp.addMouseListener(new MouseListener()
			 {
	
				@Override
				public void mouseReleased(MouseEvent e){}
				
				@Override
				public void mousePressed(MouseEvent e){}
				
				@Override
				public void mouseExited(MouseEvent e)
				{
					if(contact_jp.equals(getdata.getContatInfo().getContactJPanelClick())) {return;}
					
					contact_jp.setBackground(Color.decode("#F2F2F2"));
				}
				
				@Override
				public void mouseEntered(MouseEvent e)
				{
					if(contact_jp.equals(getdata.getContatInfo().getContactJPanelClick())) {return;}
					
					contact_jp.setBackground(Color.decode("#D8D8D8"));
				}
				
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if(getdata.getContatInfo().getContactJPanelClick() != null) {getdata.getContatInfo().getContactJPanelClick().setBackground(Color.decode("#F2F2F2"));}
					
					contact_jp.setBackground(Color.decode("#BDBDBD"));
					
					getdata.getChatJPanel().getJlNickName().setText("测试新增信息");
					
					getdata.getContatInfo().setContactJPanelClick(contact_jp);
//					getdata.setContactDataClick(getdata.getContatInfo().getContactList().get(index));
				}
			});
			 
			 getdata.getContatInfo().getContactJPanel().add(contact_jp);
			 
			 contact_jp.add(img);
			 contact_jp.add(nickName);
			 getdata.getContatInfo().getLxrJPanel().add(contact_jp);
			 
			 contact_jp_Y++;
				 
		}
		
	}
	
	/**
	 * 
	 * 创建聊天界面区域
	 * */
	public JPanel createRightJPanel(JFrame jf, JPanel left_jp, JPanel center_jp,  getData getdata)
	{
		 //界面聊天面板
		 JPanel jpRight = new JPanel();
		 
		 jpRight.setLayout(null);
		 jpRight.setSize(jf.getWidth() - left_jp.getWidth() - center_jp.getWidth(), jf.getHeight());
		 jpRight.setBackground(Color.decode("#FAFAFA"));
		 jpRight.setLocation((left_jp.getWidth() + center_jp.getWidth()), 0);

		 JPanel jpTop    = new JPanel();

		 jpTop.setLayout(null);
		 jpTop.setLocation(0, 0);
		 jpTop.setSize(jpRight.getWidth(), 80);
		 jpTop.setBackground(Color.decode("#FAFAFA"));
		 jpTop.setBorder(BorderFactory.createEtchedBorder());

		 JLabel jlNickName = new JLabel();
		 
		 jlNickName.setSize(jpTop.getWidth(), 30);
		 jlNickName.setLocation(30, (jpTop.getHeight() - jlNickName.getHeight())/2);
		 jlNickName.setText("");
		 jlNickName.setFont(new Font("宋体", 0, 18));
		 getdata.getChatJPanel().setJlNickName(jlNickName);

		 jpTop.add(jlNickName);

		 JPanel jpCenter = new JPanel();
		 JPanel jpBottom = new JPanel();
		 
		 jpRight.add(jpTop);
		 
		 return jpRight;
	}
	
	/**
	 * 
	 * 创建一个画布
	 * */
	class myPanel extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private Image image;
		private int width;
		private int height;
		
		public myPanel(Image image, int width, int height)
		{
			setSize(width, height);
			setBackground(Color.black);
			this.image = image;
			this.width = width;
			this.height = height;
			
		}
		
		public void paint(Graphics g)
		{
			g.drawImage(image, 0, 0, width, height, null);
		}
	}
}
