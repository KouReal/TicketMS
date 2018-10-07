package StartSys;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import Info.AccountInfo;
import SqlTran.SqlManage;
import Info.AccountInfo;

public class NormalUserUI {
	public JFrame frame;
	private JPanel contentpane;
	private JTextField textfield_1;
	private JTextField textfield_2;
	private JTextField sele_ticketno_text;
	private JTextField seleticket_text;
	private JRadioButton personal_order;
	private JRadioButton group_order;
	private JTextField purchase_num_text;
	private int surplus_ticket_amounts;
	
	public NormalUserUI(){
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize(){
			frame = new JFrame();
			frame.setTitle("客户购买车票");
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/cust.png")));
			frame.setBounds(100,60,800,600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			contentpane = new JPanel();
			//将contentpane对象设置为frame的内容面板
			frame.setContentPane(contentpane);
			contentpane.setLayout(null);
			
			JLabel admi = new JLabel("客户购买车票");
			admi.setFont(new Font("宋体",Font.PLAIN,16));
			admi.setBounds(70,10,350,30);
			contentpane.add(admi);
			
			//返回按键定位到登录界面
			JButton goback_button = new JButton("返回");
			goback_button.setBounds(500,10,100,30);
			contentpane.add(goback_button);
			goback_button.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					frame.dispose();
					FrameManage.Active_Mainframe();
				}
			});	
			
			//查询车票信息
			JLabel sele_data = new JLabel("查询车票信息");
			sele_data.setFont(new Font("宋体",Font.PLAIN,14));
			sele_data.setBounds(250,50,200,30);
			contentpane.add(sele_data);
			
			JLabel sele_label = new JLabel("查询车票号");
			sele_label.setFont(new Font("宋体",Font.PLAIN,14));
			sele_label.setBounds(20,90,100,30);
			contentpane.add(sele_label);
			
			sele_ticketno_text = new JTextField();
			sele_ticketno_text.setBounds(130,90,250,30);
			sele_ticketno_text.setColumns(40);
			contentpane.add(sele_ticketno_text);
			
			JLabel ticketno_label = new JLabel("车票号");
			ticketno_label.setBounds(20,130,100,20);
			ticketno_label.setFont(new Font("宋体",Font.PLAIN,14));
			contentpane.add(ticketno_label);
			
			final JLabel ticketnodata_label = new JLabel("***");
			ticketnodata_label.setFont(new Font("宋体",Font.PLAIN,14));
			ticketnodata_label.setBounds(20,160,100,20);
			contentpane.add(ticketnodata_label);
			
			JLabel seatno_label = new JLabel("座位号");
			seatno_label.setBounds(140,130,100,20);
			seatno_label.setFont(new Font("宋体",Font.PLAIN,14));
			contentpane.add(seatno_label);
			
			final JLabel seatnodata_label = new JLabel("***");
			seatnodata_label.setFont(new Font("宋体",Font.PLAIN,14));
			seatnodata_label.setBounds(140,160,100,20);
			contentpane.add(seatnodata_label);
			
			JLabel depasta_label = new JLabel("出发站");
			depasta_label.setBounds(260,130,100,20);
			depasta_label.setFont(new Font("宋体",Font.PLAIN,14));
			contentpane.add(depasta_label);
			
			final JLabel depastadata_label = new JLabel("***");
			depastadata_label.setFont(new Font("宋体",Font.PLAIN,14));
			depastadata_label.setBounds(260,160,100,20);
			contentpane.add(depastadata_label);
			
			JLabel deststa_label = new JLabel("目的站");
			deststa_label.setBounds(380,130,100,20);
			deststa_label.setFont(new Font("宋体",Font.PLAIN,14));
			contentpane.add(deststa_label);
			
			final JLabel deststadata_label = new JLabel("***");
			deststadata_label.setFont(new Font("宋体",Font.PLAIN,14));
			deststadata_label.setBounds(380,160,100,20);
			contentpane.add(deststadata_label);
			
			JLabel price_label = new JLabel("价格");
			price_label.setBounds(500,130,100,20);
			price_label.setFont(new Font("宋体",Font.PLAIN,14));
			contentpane.add(price_label);
			
			final JLabel pricedata_label = new JLabel("***");
			pricedata_label.setFont(new Font("宋体",Font.PLAIN,14));
			pricedata_label.setBounds(500,160,100,20);
			contentpane.add(pricedata_label);
			
			JLabel amou_label = new JLabel("余票量");
			amou_label.setBounds(620,130,100,20);
			amou_label.setFont(new Font("宋体",Font.PLAIN,14));
			contentpane.add(amou_label);
			
			final JLabel amoudata_label = new JLabel("***");
			amoudata_label.setFont(new Font("宋体",Font.PLAIN,14));
			amoudata_label.setBounds(620,160,100,20);
			contentpane.add(amoudata_label);
			
			JButton sele_button = new JButton("查询");
			sele_button.setBounds(300,180,100,30);
			contentpane.add(sele_button);
			
			sele_button.addMouseListener(new MouseAdapter(){

				@Override
				public void mouseClicked(MouseEvent e){
					SqlWork ssw = new SqlWork();
					String[] data = new String[5];
					String ticketno = sele_ticketno_text.getText();
					ssw.sql="select Tick_no,Seat_no,Depa_sta,Dest_sta,Price from Ticket where Tick_no=\'"+ticketno+"\'";
					if(SqlManage.Select(ssw) >= 1){
						data = ssw.datalist.get(0).split(" ");
						ticketnodata_label.setText(data[0]);
						seatnodata_label.setText(data[1]);
						depastadata_label.setText(data[2]);
						deststadata_label.setText(data[3]);
						pricedata_label.setText(data[4]);
						//
						SqlWork ssw2 = new SqlWork();
						ssw2.sql = "select Amou from TicketRefer where Tick_no=\'"+ticketno+"\'";
						System.out.println(ssw2.sql);
						SqlManage.Select(ssw2);
						String amount=ssw2.datalist.get(0);
						System.out.println(amount);
						//在SqlWork类中datalist中的字符串末尾都有一个空格
						//一定要对amount进行trim(),否则在转换为int时会抛出NumberFormatException异常
						surplus_ticket_amounts = Integer.valueOf(amount.trim()).intValue();
						System.out.println(surplus_ticket_amounts);
						amoudata_label.setText(amount);
					}
					else{
						//data = ssw.datalist.get(0).split(" ");
						String temp = "***";
						ticketnodata_label.setText(temp);
						seatnodata_label.setText(temp);
						depastadata_label.setText(temp);
						deststadata_label.setText(temp);
						pricedata_label.setText(temp);
						amoudata_label.setText(temp);
						JOptionPane.showMessageDialog(frame, "不存在您要找的车票号");
					}
					
				}
			});
			JLabel createorder_label = new JLabel("创建订单");
			createorder_label.setBounds(250,240,200,30);
			createorder_label.setFont(new Font("宋体",Font.PLAIN,14));
			contentpane.add(createorder_label);
			
			personal_order = new JRadioButton("个人订单");
			personal_order.setBounds(60,290,200,30);
			contentpane.add(personal_order);
			
			group_order = new JRadioButton("团体订单");
			group_order.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					personal_order.setSelected(false);
				}
			});
			group_order.setBounds(60,330,200,30);
			contentpane.add(group_order);
			personal_order.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					group_order.setSelected(false);
				}
			});
			
			JLabel purchase_num_label = new JLabel("购买数量");
			purchase_num_label.setBounds(300,300,100,30);
			contentpane.add(purchase_num_label);
			
			purchase_num_text = new JTextField();
			purchase_num_text.setBounds(420,300,100,30);
			contentpane.add(purchase_num_text);
			
			JLabel order_show_label = new JLabel("订单详情");
			order_show_label.setBounds(20,380,100,30);
			contentpane.add(order_show_label);
			
			final JLabel order_showdata_label = new JLabel("--------------暂无数据-------------");
			order_showdata_label.setBounds(130,380,500,30);
			contentpane.add(order_showdata_label);
			
			JButton confirm_order_button = new JButton("确认订单");
			confirm_order_button.setBounds(300,430,100,30);
			contentpane.add(confirm_order_button);
			confirm_order_button.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					int purchasenum = Integer.parseInt(purchase_num_text.getText());
					String ticketno = sele_ticketno_text.getText();
					String order_info;
					if(purchasenum > surplus_ticket_amounts){
						JOptionPane.showMessageDialog(frame,"车票剩余量不足:"+surplus_ticket_amounts);
					}
					else{
						//int tempsur = surplus_ticket_amounts-purchasenum;
						SqlWork psw = new SqlWork();
						psw.sql = "exec purchase_ticket \'"+ticketno+"\',\'001\',"+purchasenum;
						if(true == SqlManage.ExecPro(psw)){
							SqlWork ssw = new SqlWork();
							ssw.sql="select order_code from SerialNumCode";
							SqlManage.Select(ssw);
							int tempordernum = Integer.parseInt(ssw.datalist.get(0).trim())-1;
							String temporderno = "order-"+tempordernum;
							SqlWork ssw2 = new SqlWork();
							ssw2.sql="select * from CustOrder where Order_no=\'"+temporderno+"\'";
							SqlManage.Select(ssw2);
							String result=ssw2.datalist.get(0);
							order_showdata_label.setText(result);
							JOptionPane.showMessageDialog(frame,"您的订单成功创建！");
						}
						else{
							JOptionPane.showMessageDialog(frame,"订单创建失败");
						}
						
					}
				}
			});
		}
}
