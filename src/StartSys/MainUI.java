package StartSys;
import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Info.AccountInfo;
import javax.swing.JRadioButton;
import SqlTran.SqlManage;

import Info.AccountInfo;

public class MainUI{
	public JFrame frame;
	private JTextField textfield_1;
	private JTextField textfield_2;
	private JPanel contentpane;
	private JRadioButton normaluser;
	private JRadioButton administrator;
	
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable(){
//			public void run(){
//				try{
//					MainUI window = new MainUI();
//					window.frame.setVisible(true);
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//		});
		//SqlManage.ConnectToServer();
		MainUI window = new MainUI();
		FrameManage.Mainframe = window.frame;
		window.frame.setVisible(true);
	}
	
	public MainUI(){
		//连接数据库“TicketMS”
		SqlManage.ConnectToServer();
		//初始化图形用户界面
		initialize();
	}
	
	private void initialize(){
		frame = new JFrame();
		frame.setTitle("您好，登录系统");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/myicon.png")));
		frame.setBounds(100,60,800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentpane = new JPanel();
		//将contentpane对象设置为frame的内容面板
		frame.setContentPane(contentpane);
		contentpane.setLayout(null);
		
		JLabel login_title = new JLabel("铁路售票管理系统登录");
		login_title.setFont(new Font("宋体",Font.PLAIN,16));
		login_title.setBounds(300,10,400,30);
		contentpane.add(login_title);
		
		ImageIcon img = new ImageIcon("./src/image/train.png");//创建图片对象
		JLabel image_label = new JLabel();
		image_label.setBounds(320,50,400,400);
		image_label.setIcon(img);
		contentpane.add(image_label);
		
		JLabel account = new JLabel("用户名");
		account.setFont(new Font("宋体",Font.PLAIN,16));
		account.setBounds(40,80,60,100);
		contentpane.add(account);
		
		JLabel password = new JLabel("密码");
		password.setFont(new Font("宋体",Font.PLAIN,16));
		password.setBounds(40,200,60,100);
		contentpane.add(password);
		
		textfield_1 = new JTextField();
		textfield_1.setBounds(120,120,140,30);
		textfield_1.setColumns(10);
		contentpane.add(textfield_1);
		
		textfield_2 = new JTextField();
		textfield_2.setBounds(120,240,140,30);
		textfield_2.setColumns(10);
		contentpane.add(textfield_2);
		
		normaluser = new JRadioButton("普通用户");
		normaluser.setBounds(100,300,100,50);
		contentpane.add(normaluser);
		
		administrator = new JRadioButton("售票管理员");
		administrator.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				normaluser.setSelected(false);
			}
		});
		administrator.setBounds(220,300,100,50);
		contentpane.add(administrator);
		normaluser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				administrator.setSelected(false);
			}
		});
		
		JButton regist = new JButton("注册");
		regist.setBounds(120,400,80,30);
		contentpane.add(regist);
		regist.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				String account = textfield_1.getText();
				String password = textfield_2.getText();
				if(false == AccountInfo.Regist(account,password)){
					JOptionPane.showMessageDialog(frame, "您好，注册失败");
				}
				else{
					JOptionPane.showMessageDialog(frame, "您已成功注册！");
					if(true == normaluser.isSelected()){
						frame.dispose();
						NormalUserUI normaluser_ui = new NormalUserUI();
						//normaluser_ui.frame.setVisible(true);
						
					}
					else{
						frame.dispose();
						AdministratorUI administrator_ui = new AdministratorUI();
						//administrator_ui.frame.setVisible(true);
						
					}
				}
			}
				
		});
		
		
		JButton login = new JButton("登录");
		login.setBounds(260,400,80,30);
		contentpane.add(login);
		login.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				if(!(normaluser.isSelected()) && !(administrator.isSelected())){
					JOptionPane.showMessageDialog(frame, "您好，请选择身份：普通用户/列车管理员");
					return ;
				}
				String account = textfield_1.getText();
				String password = textfield_2.getText();
				if(account.equals("")||account==null){
					JOptionPane.showMessageDialog(frame, "您好，用户名不能为空");
					return ;
				}else if(password.equals("")||password==null){
					JOptionPane.showMessageDialog(frame, "您好，密码不能为空");
					return ;
				}
				
				if(false == AccountInfo.CheckAccount(account, password)){
					JOptionPane.showMessageDialog(frame, "用户或密码错误");
				}
				else{
					JOptionPane.showMessageDialog(frame, "登录成功!");
					if(true == normaluser.isSelected()){
						frame.dispose();
						NormalUserUI normaluser_ui = new NormalUserUI();
						//normaluser_ui.frame.setVisible(true);
						
					}
					else{
						frame.dispose();
						AdministratorUI administrator_ui = new AdministratorUI();
						//administrator_ui.frame.setVisible(true);
						
					}
				}
						
			}
		
		});
		
		//作者信息
		JLabel authorinfo = new JLabel();
		authorinfo.setBounds(400,480,300,30);
		authorinfo.setFont(new Font("宋体",Font.PLAIN,14));
		authorinfo.setText("寇瑞-2015302555-10011501-数据库大作业");
		contentpane.add(authorinfo);
	}

}
