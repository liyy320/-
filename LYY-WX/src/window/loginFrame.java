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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import bean.ContactBean;

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
	public JFrame createLoginJFrame(getData getdata, Image image)
	{
		 // 实例化一个JFrame对象
		 JFrame jf = new JFrame("微信");
		
		 jf.setSize(388, 538); // 设置窗体大小
		 jf.setIconImage(getdata.getimg(getData.PROJECTPATH + "/images/icon.gif", 1));
		 
		 // 获取一个容器
		 Container container = jf.getContentPane(); 
		 
		 container.setLayout(null);
		 container.setBackground(Color.white);//设置容器的背景颜色
		 
		 myPanel mp = new myPanel(image, 236, 236);

		 mp.setLocation((jf.getWidth() - mp.getWidth())/2, 80);

		 JLabel jl = new JLabel("使用手机微信扫码登录");
		 
		 jl.setSize(mp.getWidth() - 20, 30);
		 jl.setLocation(mp.getX() + (mp.getWidth() - jl.getWidth()) / 2, mp.getY() + mp.getHeight() + mp.getY()/4);

		 container.add(mp);
		 container.add(jl);
		 
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
		 jf.setIconImage(getdata.getimg(getData.PROJECTPATH + "/images/icon.gif", 1));
		 
		 Container container = jf.getContentPane();
		 
		 container.setLayout(null);
		 container.setBackground(Color.white);//设置容器的背景颜色
		 
		 //界面最左面面板
		 JPanel left_jp = new JPanel();
		 
		 left_jp.setLayout(null);
		 left_jp.setSize(75, jf.getHeight());
		 
		 //当前用户的头像
		 myPanel mp = new myPanel(getdata.getimg(getdata.getUserinfo().getHeadImgUrl(), 0), 45, 45);

		 mp.setLocation((left_jp.getWidth() - mp.getWidth())/2, 25);
		 
		 //最近联系人图标
		 myPanel zjlxr = new myPanel(getdata.getimg(getData.PROJECTPATH + "/images/zjlxrClick.gif", 1), 24, 24);
		 
		 zjlxr.setLocation((left_jp.getWidth() - zjlxr.getWidth())/2, 25 + mp.getHeight() + 25);
		 
		//最近联系人没有被点击时图标
		 myPanel zjlxrNo = new myPanel(getdata.getimg(getData.PROJECTPATH + "/images/zjlxrNoClick.gif", 1), 24, 24);
		 
		 zjlxrNo.setLocation((left_jp.getWidth() - zjlxrNo.getWidth())/2, 25 + mp.getHeight() + 25);
		 zjlxrNo.setVisible(false);
		 
		 //所有联系人没有被点击时图标
		 myPanel alllxrNo = new myPanel(getdata.getimg(getData.PROJECTPATH + "/images/allContactNoClick.gif", 1), 24, 24);
		 
		 alllxrNo.setLocation((left_jp.getWidth() - alllxrNo.getWidth())/2, 25 + (mp.getHeight() + alllxrNo.getHeight()*2 + 25));
		 
		 //所有联系人图标
		 myPanel alllxr = new myPanel(getdata.getimg(getData.PROJECTPATH + "/images/allContactClick.gif", 1), 24, 24);
		 
		 alllxr.setLocation((left_jp.getWidth() - alllxr.getWidth())/2, 25 + (mp.getHeight() + alllxr.getHeight()*2 + 25));
		 alllxr.setVisible(false);

		 zjlxrNo.addMouseListener(new MouseListener()
		 {
			
			@Override
			public void mouseReleased(MouseEvent e){}
			
			@Override
			public void mousePressed(MouseEvent e){}
			
			@Override
			public void mouseExited(MouseEvent e){}
			
			@Override
			public void mouseEntered(MouseEvent e){}
			 
			@Override
			public void mouseClicked(MouseEvent e)
			{
				 zjlxr.setVisible(true);zjlxrNo.setVisible(false);
				 alllxrNo.setVisible(true);alllxr.setVisible(false);
				 getdata.getContatInfo().getZjcontactJScrollPane().setVisible(true);
				 getdata.getContatInfo().getAllContackJPanel().setVisible(false);
			}
		 });
		 
		 alllxrNo.addMouseListener(new MouseListener()
		 {
			
			@Override
			public void mouseReleased(MouseEvent e){}
			
			@Override
			public void mousePressed(MouseEvent e){}
			
			@Override
			public void mouseExited(MouseEvent e){}
			
			@Override
			public void mouseEntered(MouseEvent e){}
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				 zjlxr.setVisible(false);zjlxrNo.setVisible(true);
				 alllxrNo.setVisible(false);alllxr.setVisible(true);
				 getdata.getContatInfo().getZjcontactJScrollPane().setVisible(false);
				 getdata.getContatInfo().getAllContackJPanel().setVisible(true);
			}
		 });
		 
		 left_jp.add(mp);
		 left_jp.add(zjlxr);
		 left_jp.add(zjlxrNo);
		 left_jp.add(alllxrNo);
		 left_jp.add(alllxr);
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
		 
		 JScrollPane zjcontactJScrollPane = new JScrollPane(lxr_jp);
		 
		 JScrollBar v = zjcontactJScrollPane.getVerticalScrollBar();

		 v.setUnitIncrement((int)(zjcontactJScrollPane.getHeight() * 0.4));
		 
		 zjcontactJScrollPane.setBounds(0, 80, center_jp.getWidth(), center_jp.getHeight() - search_jp.getHeight() - 33);

		 lxr_jp.setLayout(null);
		 lxr_jp.setPreferredSize(new Dimension(zjcontactJScrollPane.getWidth() - 20, getdata.getContatInfo().getContactList().size() * search_jp.getHeight()));
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
				 myPanel img = new myPanel(getdata.getimg(getdata.getContatInfo().getContactList().get(i).getHeadImgUrl(), 0), 45, 45);

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

		 JScrollPane allContatJPanel = createAllContatJPanel(jf, center_jp, search_jp, getdata);
		 
		 getdata.getContatInfo().setLxrJPanel(lxr_jp);
		 getdata.getContatInfo().setSearchJPanel(search_jp);
		 getdata.getContatInfo().setZjcontactJScrollPane(zjcontactJScrollPane);
		 getdata.getContatInfo().setAllContackJPanel(allContatJPanel);
		 
		 center_jp.add(search_jp);
		 center_jp.add(zjcontactJScrollPane);
		 center_jp.add(allContatJPanel);
		 center_jp.setBackground(Color.decode("#eeeeee"));
		 center_jp.setLocation(left_jp.getWidth(), 0);
		 
		 getdata.getContatInfo().setCenterJPanel(center_jp);
		 
		 return center_jp;
	}
	
	public JScrollPane createAllContatJPanel(JFrame jf, JPanel center_jp,JPanel search_jp, getData getdata)
	{
		 //中间面板下方的联系人区域
		 JPanel lxr_jp = new JPanel();
		 
		 JScrollPane zjcontactJScrollPane = new JScrollPane(lxr_jp);
		 
		 zjcontactJScrollPane.setBounds(0, 80, center_jp.getWidth(), jf.getHeight() - search_jp.getHeight() - 33);
		 
		 JScrollBar v = zjcontactJScrollPane.getVerticalScrollBar();

		 v.setUnitIncrement((int)(zjcontactJScrollPane.getHeight() * 0.4));
		 
		 
		 
		 lxr_jp.setLayout(null);
		 lxr_jp.setPreferredSize(new Dimension(zjcontactJScrollPane.getWidth() - 20,  getdata.getAllContact().size() * search_jp.getHeight()));
			 
		 lxr_jp.setLocation(0, search_jp.getHeight());
		 
		 int step = 100;
		 int start = 0;
		 int end = 100;
		 int c = getdata.getAllContact().size() / step;
		 int last = getdata.getAllContact().size() % step;
		 
		 for(int i = 0; i <= c;i++)
		 {
			 start = step * i;
			 if(i == c - 1)
			 {
				 end = step + step * i + last;
			 }
			 else
			 {
				 end = step * i + step;
			 }
			 
			 addContanctPage(center_jp, search_jp, zjcontactJScrollPane, lxr_jp, getdata, start, end);
		 }

		 zjcontactJScrollPane.setVisible(false);
		 
		 return zjcontactJScrollPane;
	}
	
	//添加所有联系人，用线程添加
	public void addContanctPage(JPanel center_jp, JPanel search_jp,JScrollPane zjcontactJScrollPane, JPanel lxr_jp, getData getdata, int start, int end)
	{

		 new Thread(new Runnable() {

			@Override
			public void run()
			{
				for(int i = start;i < end;i++)
				 {
					 
//					 int index = i;

					 System.out.println(i);
					
					 JPanel contact_jp = new JPanel();
					 
					 contact_jp.setLayout(null);
					 contact_jp.setSize(center_jp.getWidth(), search_jp.getHeight());
					 contact_jp.setLocation(0, i * contact_jp.getHeight());
					 contact_jp.setBackground(Color.decode("#F2F2F2"));
					 
					 //添加联系人头像
					 myPanel img = new myPanel(getdata.getimg(getdata.getAllContact().get(i).getHeadImgUrl(), 0), 45, 45);
	
					 img.setLocation(10, 25);
					 
					 JLabel nickName = new JLabel(getdata.getAllContact().get(i).getNickName());
					 
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
							
	//								getdata.getChatJPanel().getJlNickName().setText(getdata.getContatInfo().getContactList().get(index).getNickName());
							
	//								getdata.getContatInfo().setContactJPanelClick(contact_jp);getdata.setContactDataClick(getdata.getContatInfo().getContactList().get(index));
						}
					});
					 
					 contact_jp.add(img);
					 contact_jp.add(nickName);
					 lxr_jp.add(contact_jp);
	
					}

				 }
			 
		 }).start();
		 
	}
	
	public void addMsgJPanel(getData getdata)
	{
		if(getdata.getAddMsgList().size() == 0) {return;}
		
		int contact_jp_Y = 0;
		
		for(int i = 0;i < getdata.getAddMsgList().size();i++)
		{
			 int index = i;
			 
			 ContactBean contactBean = findContanct(getdata, getdata.getAddMsgList().get(i).getFromUserName());

			 if(contactBean == null) {continue;}

			 JPanel contact_jp = new JPanel();
			 
			 contact_jp.setLayout(null);
			 contact_jp.setSize(getdata.getContatInfo().getCenterJPanel().getWidth(), getdata.getContatInfo().getSearchJPanel().getHeight());
			 contact_jp.setLocation(0, contact_jp_Y * contact_jp.getHeight() - (contact_jp_Y + 1) * contact_jp.getHeight());
			 contact_jp.setBackground(Color.decode("#F2F2F2"));

			 //添加联系人头像
			 myPanel img = new myPanel(getdata.getimg(contactBean.getHeadImgUrl(), 0), 45, 45);
	
			 img.setLocation(10, 25);
			 
			 JLabel nickName = new JLabel(contactBean.getNickName());

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
					getdata.setContactDataClick(getdata.getContatInfo().getContactList().get(index));
				}
			});

			 
			 
			 contact_jp.add(img);
			 contact_jp.add(nickName);
			 getdata.getContatInfo().getLxrJPanel().add(contact_jp);
			 getdata.getContatInfo().getContactJPanel().add(contact_jp);
			 
			 contact_jp_Y++;
				 
		}

		for(JPanel j : getdata.getContatInfo().getContactJPanel())
		{
			j.setLocation(0, j.getY() + j.getHeight() * getdata.getAddMsgList().size());
			
		}
		
	}
	
	public ContactBean findContanct(getData getdata, String username)
	{
		
		for(int i = 0; i < getdata.getContatInfo().getContactList().size(); i++)
		{
			if(getdata.getContatInfo().getContactList().get(i).getUserName() != null && getdata.getContatInfo().getContactList().get(i).getUserName().equals(username))
			{
				return getdata.getContatInfo().getContactList().get(i);
			}
		}
		
		for(int i = 0; i < getdata.getAllContact().size(); i++)
		{
			if(getdata.getAllContact().get(i).getUserName().equals(username))
			{
				return getdata.getAllContact().get(i);
			}
		}
		
		return null;
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
