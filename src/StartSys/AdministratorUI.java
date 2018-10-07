package StartSys;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Info.AccountInfo;
import SqlTran.SqlManage;

public class AdministratorUI {
	public JFrame select_frame;
	public JFrame traininfo_frame;
	public JFrame ticketinfo_frame;
	private JPanel contentpane;
	//private JTextField addtext_trainno;
	private JTextField addtext_startsta;
	private JTextField addtext_endsta;
	private JTextField addtext_price;
	
	private JTextField deletext_trainno;
	
	private JTextField seletext_trainno;
	
	private JTextField updatext_trainno;
	private JTextField updatext_startsta;
	private JTextField updatext_endsta;
	private JTextField updatext_price;
	private JTextField addticket_text_trainno;
	private JTextField deleticket_text_ticketno;
	private JTextField seleticket_text_ticketno;
	private JTextField updaticket_text_ticketno;
	private JTextField updaticket_text_depasta;
	private JTextField updaticket_text_diststa;
	private JTextField updaticket_text_price;
	
	
	public AdministratorUI(){
		init_selectui();
		FrameManage.AdmiSelectframe = select_frame;
		select_frame.setVisible(true);
	}
	
	private void init_selectui(){
		select_frame = new JFrame();
		select_frame.setTitle("管理员选择功能");
		select_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/admi.png")));
		select_frame.setBounds(100,60,800,600);
		select_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentpane = new JPanel();
		//将contentpane对象设置为frame的内容面板
		select_frame.setContentPane(contentpane);
		contentpane.setLayout(null);
		
		JButton goback_button = new JButton("返回");
		goback_button.setBounds(500,60,100,30);
		contentpane.add(goback_button);
		goback_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				select_frame.dispose();
				FrameManage.Active_Mainframe();
			}
		});
		
		JLabel welcome_label = new JLabel("你好管理员，请选择一个功能");
		welcome_label.setBounds(250,200,400,40);
		welcome_label.setFont(new Font("宋体",Font.PLAIN,16));
		contentpane.add(welcome_label);
		
		JButton traininfo_button = new JButton("管理列车信息");
		traininfo_button.setBounds(250,260,200,40);
		contentpane.add(traininfo_button);
		traininfo_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				select_frame.dispose();
				init_traininfoui();
				traininfo_frame.setVisible(true);
			}
		});
		
		JButton ticketinfo_button = new JButton("管理车票信息");
		ticketinfo_button.setBounds(250,320,200,40);
		contentpane.add(ticketinfo_button);
		ticketinfo_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				select_frame.dispose();
				init_ticketinfoui();
				ticketinfo_frame.setVisible(true);
			}
		});
	}
	private void init_ticketinfoui(){
		ticketinfo_frame = new JFrame();
		ticketinfo_frame.setTitle("管理员操作TicketMS车票信息");
		ticketinfo_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/admi.png")));
		ticketinfo_frame.setBounds(100,60,800,600);
		ticketinfo_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentpane = new JPanel();
		//将contentpane对象设置为frame的内容面板
		ticketinfo_frame.setContentPane(contentpane);
		contentpane.setLayout(null);
		
		JLabel admi = new JLabel("管理员操作TicketMS车票信息");
		admi.setFont(new Font("宋体",Font.PLAIN,16));
		admi.setBounds(100,10,350,30);
		contentpane.add(admi);
		
		JButton goback_button = new JButton("返回");
		goback_button.setBounds(500,10,100,30);
		contentpane.add(goback_button);
		goback_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				ticketinfo_frame.dispose();
				FrameManage.Active_AdmiSelectUI();
			}
		});
		
		JLabel add_data = new JLabel("增加车票信息");
		add_data.setFont(new Font("宋体",Font.PLAIN,14));
		add_data.setBounds(100,50,100,30);
		contentpane.add(add_data);
		
		//列车号
		JLabel addticket_trainno = new JLabel("对应列车号");
		addticket_trainno.setFont(new Font("宋体",Font.PLAIN,10));
		addticket_trainno.setBounds(10,90,50,30);
		contentpane.add(addticket_trainno);
		
		addticket_text_trainno = new JTextField();
		addticket_text_trainno.setBounds(80,90,200,30);
		addticket_text_trainno.setColumns(10);
		contentpane.add(addticket_text_trainno);
		
		//列车信息
		final JLabel addticket_traininfo_tip = new JLabel("train:-------------nothing--------------");
		addticket_traininfo_tip.setFont(new Font("宋体",Font.PLAIN,12));
		addticket_traininfo_tip.setBounds(20,130,300,30);
		contentpane.add(addticket_traininfo_tip);
			
		//车票信息
		final JLabel addticket_ticketinfo_tip = new JLabel("结果:-------------nothing--------------");
		addticket_ticketinfo_tip.setFont(new Font("宋体",Font.PLAIN,12));
		addticket_ticketinfo_tip.setBounds(20,170,300,30);
		contentpane.add(addticket_ticketinfo_tip);
		
		JButton addticket_tipbutton = new JButton("查看提示");
		addticket_tipbutton.setBounds(20,220,100,30);
		contentpane.add(addticket_tipbutton);
		addticket_tipbutton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				SqlWork isw = new SqlWork();
				String trainno=addticket_text_trainno.getText();
				isw.sql="select * from Traininfo where Train_no=\'"+trainno+"\'";
				System.out.println(isw.sql);
				if(1 <= SqlManage.Select(isw)){
					addticket_traininfo_tip.setText(isw.datalist.get(0));
					//addticket_ticketinfo_tip.setText
					//JOptionPane.showMessageDialog(ticketinfo_frame, "添加成功！");
				}
				else{
					JOptionPane.showMessageDialog(ticketinfo_frame, "列车信息不存在");
				}
			}
				
		});
		
		//add_button
		JButton addticket_addbutton = new JButton("确认添加");
		addticket_addbutton.setBounds(130,220,100,30);
		contentpane.add(addticket_addbutton);
		addticket_addbutton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				SqlWork isw = new SqlWork();
				SqlWork ssw = new SqlWork();
				String trainno=addticket_text_trainno.getText();
				isw.sql="exec add_ticket \'"+trainno+"\'";
				ssw.sql="select * from Ticket where Train_no=\'"+trainno+"\'";
				System.out.println(isw.sql);
				if(true == SqlManage.ExecPro(isw)){
					SqlManage.Select(ssw);
					addticket_ticketinfo_tip.setText(ssw.datalist.get(0));
					JOptionPane.showMessageDialog(ticketinfo_frame, "添加成功");
					//addticket_ticketinfo_tip.setText
					//JOptionPane.showMessageDialog(ticketinfo_frame, "添加成功！");
				}
				else{
					JOptionPane.showMessageDialog(ticketinfo_frame, "添加失败");
				}
			}
				
		});
		
		//删除车票信息
		JLabel dele_data = new JLabel("删除车票信息");
		dele_data.setFont(new Font("宋体",Font.PLAIN,14));
		dele_data.setBounds(460,50,100,30);
		contentpane.add(dele_data);
		
		JLabel dele_label = new JLabel("删除的车票号");
		dele_label.setFont(new Font("宋体",Font.PLAIN,14));
		dele_label.setBounds(400,90,100,30);
		contentpane.add(dele_label);
		
		deleticket_text_ticketno = new JTextField();
		deleticket_text_ticketno.setBounds(520,90,150,30);
		deleticket_text_ticketno.setColumns(20);
		contentpane.add(deleticket_text_ticketno);
		
		//删除提示信息（车票号对应的相关信息）
		JLabel dele_tip_label=new JLabel("tip:");
		dele_tip_label.setFont(new Font("宋体",Font.PLAIN,12));
		dele_tip_label.setBounds(400,140,30,30);
		contentpane.add(dele_tip_label);
		
		final JLabel dele_tip = new JLabel("------nothing to show--------");
		dele_tip.setFont(new Font("宋体",Font.PLAIN,12));
		dele_tip.setBounds(440,140,360,30);
		contentpane.add(dele_tip);
		
		
		JButton tip_button = new JButton("查看提示");
		tip_button.setBounds(400,180,100,30);
		contentpane.add(tip_button);
		
		tip_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				String ticketno = deleticket_text_ticketno.getText();
				String tipstr = "";
				SqlWork ssw = new SqlWork();
				ssw.sql="select * from Ticket where Tick_no=\'"+ticketno+"\'";
				int count = SqlManage.Select(ssw);
				System.out.println("querying the tip infomation...............");
				if(count == 0){
					dele_tip.setText("没有找到您要删除的车票号");
				}
				else{
					tipstr=ssw.datalist.get(0);			
					dele_tip.setText(tipstr);					
				}	
				System.out.println("query ok...............");	
			}
		});

		JButton dele_button = new JButton("确认删除");
		dele_button.setBounds(520,180,100,30);
		contentpane.add(dele_button);
		
		dele_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				System.out.println("deleting date.....................");
				SqlWork dsw = new SqlWork();
				String ticket = deleticket_text_ticketno.getText();
				dsw.sql = "delete Ticket where Tick_no=\'"+ticket+"\'";
				System.out.println(dsw.sql);
				if(true == SqlManage.Delete(dsw)){
					JOptionPane.showMessageDialog(ticketinfo_frame, "删除成功！");
				}
				else{
					JOptionPane.showMessageDialog(ticketinfo_frame, "删除失败");
				}
			}
				
		});
		
		//查询列车信息
		JLabel sele_data = new JLabel("查询车票信息");
		sele_data.setFont(new Font("宋体",Font.PLAIN,14));
		sele_data.setBounds(100,280,100,30);
		contentpane.add(sele_data);
		
		JLabel sele_label = new JLabel("查询车票号");
		sele_label.setFont(new Font("宋体",Font.PLAIN,14));
		sele_label.setBounds(20,320,100,30);
		contentpane.add(sele_label);
		
		seleticket_text_ticketno = new JTextField();
		seleticket_text_ticketno.setBounds(130,320,150,30);
		seleticket_text_ticketno.setColumns(20);
		contentpane.add(seleticket_text_ticketno);
		
		final JLabel selename_label = new JLabel("----------name-----------");
		selename_label.setFont(new Font("宋体",Font.PLAIN,12));
		selename_label.setBounds(20,360,380,30);
		contentpane.add(selename_label);
		
		final JLabel seledata_label = new JLabel("----------data------------");
		seledata_label.setFont(new Font("宋体",Font.PLAIN,12));
		seledata_label.setBounds(20,400,380,30);
		contentpane.add(seledata_label);
		
		JButton sele_button = new JButton("查询");
		sele_button.setBounds(70,450,80,30);
		contentpane.add(sele_button);
		
		sele_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				System.out.println("querying data.....................");
				SqlWork ssw = new SqlWork();
				String ticketno = seleticket_text_ticketno.getText();
				String name="没有找到您查询的数据";
				String data="没有找到您查询的数据";
				ssw.sql = "select * from Ticket where Tick_no=\'"+ticketno+"\'";
				System.out.println(ssw.sql);
				if(0 == SqlManage.Select(ssw)){
					selename_label.setText(name);
					seledata_label.setText(data);
					JOptionPane.showMessageDialog(ticketinfo_frame, "没有您查找的数据！");
				}
				else{
					name=ssw.namelist.get(0);
					data=ssw.datalist.get(0);
					selename_label.setText(name);
					seledata_label.setText(data);
				}
			}
		});
		
		//修改车票信息
		JLabel upda_data = new JLabel("修改车票信息");
		upda_data.setFont(new Font("宋体",Font.PLAIN,14));
		upda_data.setBounds(460,280,100,30);
		contentpane.add(upda_data);
		
		JLabel upda_ticketno = new JLabel("更新车票号");
		upda_ticketno.setBounds(400,320,100,30);
		upda_ticketno.setFont(new Font("宋体",Font.PLAIN,14));
		contentpane.add(upda_ticketno);
		
		updaticket_text_ticketno = new JTextField();
		updaticket_text_ticketno.setBounds(510,320,200,30);
		updaticket_text_ticketno.setColumns(20);
		contentpane.add(updaticket_text_ticketno);
		
		JLabel upda_depasta = new JLabel("出发站");
		upda_depasta.setBounds(400,360,100,30);
		upda_depasta.setFont(new Font("宋体",Font.PLAIN,14));
		contentpane.add(upda_depasta);
		
		updaticket_text_depasta = new JTextField();
		updaticket_text_depasta.setBounds(510,360,200,30);
		updaticket_text_depasta.setColumns(20);
		contentpane.add(updaticket_text_depasta);
		
		JLabel upda_diststa = new JLabel("终点站");
		upda_diststa.setBounds(400,400,100,30);
		upda_diststa.setFont(new Font("宋体",Font.PLAIN,14));
		contentpane.add(upda_diststa);
		
		updaticket_text_diststa = new JTextField();
		updaticket_text_diststa.setBounds(500,400,200,30);
		updaticket_text_diststa.setColumns(20);
		contentpane.add(updaticket_text_diststa);
		
		JLabel upda_price = new JLabel("价格");
		upda_price.setBounds(400,440,100,30);
		upda_price.setFont(new Font("宋体",Font.PLAIN,14));
		contentpane.add(upda_price);
		
		updaticket_text_price = new JTextField();
		updaticket_text_price.setBounds(500,440,200,30);
		updaticket_text_price.setColumns(20);
		contentpane.add(updaticket_text_price);
		
		JButton update_button = new JButton("更新");
		update_button.setBounds(520,480,100,30);
		contentpane.add(update_button);
		update_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				System.out.println("updating data.....................");
				SqlWork usw = new SqlWork();
				String ticketno = updaticket_text_ticketno.getText();
				String depa = updaticket_text_depasta.getText();
				String dest = updaticket_text_diststa.getText();
				String price = updaticket_text_price.getText();
				
				usw.sql = "update Ticket "
						+ "set Depa_sta=\'"+depa+"\',Dest_sta=\'"+dest
						+"\',Price=\'"+price+"\' where Tick_no=\'"+ticketno+"\'";
				System.out.println(usw.sql);
				if(true == SqlManage.Update(usw)){
					JOptionPane.showMessageDialog(ticketinfo_frame, "更新成功");
				}
				else{
					JOptionPane.showMessageDialog(ticketinfo_frame, "更新失败");
				}
			}
			
		});
	
	}
	
	private void init_traininfoui(){
		traininfo_frame = new JFrame();
		traininfo_frame.setTitle("管理员操作TicketMS数据库");
		traininfo_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/admi.png")));
		traininfo_frame.setBounds(100,60,800,600);
		traininfo_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentpane = new JPanel();
		//将contentpane对象设置为frame的内容面板
		traininfo_frame.setContentPane(contentpane);
		contentpane.setLayout(null);
		
		JLabel admi = new JLabel("管理员操作TicketMS");
		admi.setFont(new Font("宋体",Font.PLAIN,16));
		admi.setBounds(100,10,350,30);
		contentpane.add(admi);
		
		JButton goback_button = new JButton("返回");
		goback_button.setBounds(500,10,100,30);
		contentpane.add(goback_button);
		goback_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				traininfo_frame.dispose();
				FrameManage.Active_AdmiSelectUI();
			}
		});		
		
		JLabel add_data = new JLabel("增加列车信息");
		add_data.setFont(new Font("宋体",Font.PLAIN,14));
		add_data.setBounds(100,50,100,30);
		contentpane.add(add_data);
		
		//增加始发站信息
		JLabel add_startsta = new JLabel("始发站");
		add_startsta.setFont(new Font("宋体",Font.PLAIN,14));
		add_startsta.setBounds(20,90,50,30);
		contentpane.add(add_startsta);
		
		addtext_startsta = new JTextField();
		addtext_startsta.setBounds(80,90,200,30);
		addtext_startsta.setColumns(10);
		contentpane.add(addtext_startsta);
		
		//增加终点站信息
		JLabel add_endsta = new JLabel("终点站");
		add_endsta.setFont(new Font("宋体",Font.PLAIN,14));
		add_endsta.setBounds(20,130,50,30);
		contentpane.add(add_endsta);
		
		addtext_endsta = new JTextField();
		addtext_endsta.setBounds(80,130,200,30);
		addtext_endsta.setColumns(10);
		contentpane.add(addtext_endsta);
		
		//增加价格信息
		JLabel add_price = new JLabel("价格");
		add_price.setFont(new Font("宋体",Font.PLAIN,14));
		add_price.setBounds(20,170,50,30);
		contentpane.add(add_price);
		
		addtext_price = new JTextField();
		addtext_price.setBounds(80,170,200,30);
		addtext_price.setColumns(10);
		contentpane.add(addtext_price);
		
		//add_button
		JButton add_button = new JButton("确认添加");
		add_button.setBounds(80,220,120,30);
		contentpane.add(add_button);
		add_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				SqlWork isw = new SqlWork();
				String start=addtext_startsta.getText();
				String end = addtext_endsta.getText();
				String price = addtext_price.getText();
				isw.sql="exec add_traininfo \'"+start+"\',\'"+end+"\',"+price;
				System.out.println(isw.sql);
				if(true == SqlManage.ExecPro(isw)){
					JOptionPane.showMessageDialog(traininfo_frame, "添加成功！");
				}
				else{
					JOptionPane.showMessageDialog(traininfo_frame, "添加失败");
				}
			}
				
		});
		
		JLabel dele_data = new JLabel("删除列车信息");
		dele_data.setFont(new Font("宋体",Font.PLAIN,14));
		dele_data.setBounds(460,50,100,30);
		contentpane.add(dele_data);
		
		JLabel dele_label = new JLabel("删除的列车号");
		dele_label.setFont(new Font("宋体",Font.PLAIN,14));
		dele_label.setBounds(400,90,100,30);
		contentpane.add(dele_label);
		
		deletext_trainno = new JTextField();
		deletext_trainno.setBounds(520,90,150,30);
		deletext_trainno.setColumns(20);
		contentpane.add(deletext_trainno);
		
		//删除提示信息（列车号对应的相关信息）
		JLabel tip_label=new JLabel("tip:");
		tip_label.setFont(new Font("宋体",Font.PLAIN,12));
		tip_label.setBounds(400,140,30,30);
		contentpane.add(tip_label);
		
		final JLabel dele_tip = new JLabel("------nothing to show--------");
		dele_tip.setFont(new Font("宋体",Font.PLAIN,12));
		dele_tip.setBounds(440,140,360,30);
		contentpane.add(dele_tip);
		
		
		JButton tip_button = new JButton("查看提示");
		tip_button.setBounds(400,180,100,30);
		contentpane.add(tip_button);
		
		tip_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				String trainno = deletext_trainno.getText();
				String tipstr = "";
				SqlWork ssw = new SqlWork();
				ssw.sql="select * from TrainInfo where Train_no=\'"+trainno+"\'";
				int count = SqlManage.Select(ssw);
				System.out.println("querying the tip infomation...............");
				if(count == 0){
					dele_tip.setText("没有找到您要删除的列车号");
				}
				else{
					tipstr=ssw.datalist.get(0);			
					dele_tip.setText(tipstr);					
				}	
				System.out.println("query ok...............");	
			}
		});

		JButton dele_button = new JButton("确认删除");
		dele_button.setBounds(520,180,100,30);
		contentpane.add(dele_button);
		
		dele_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				System.out.println("deleting date.....................");
				SqlWork dsw = new SqlWork();
				String trainno = deletext_trainno.getText();
				dsw.sql = "delete TrainInfo where Train_no=\'"+trainno+"\'";
				System.out.println(dsw.sql);
				if(true == SqlManage.Delete(dsw)){
					JOptionPane.showMessageDialog(traininfo_frame, "删除成功！");
				}
				else{
					JOptionPane.showMessageDialog(traininfo_frame, "删除失败");
				}
			}
				
		});
		
		//查询列车信息
		JLabel sele_data = new JLabel("查询列车信息");
		sele_data.setFont(new Font("宋体",Font.PLAIN,14));
		sele_data.setBounds(100,280,100,30);
		contentpane.add(sele_data);
		
		JLabel sele_label = new JLabel("查询列车号");
		sele_label.setFont(new Font("宋体",Font.PLAIN,14));
		sele_label.setBounds(20,320,100,30);
		contentpane.add(sele_label);
		
		seletext_trainno = new JTextField();
		seletext_trainno.setBounds(130,320,150,30);
		seletext_trainno.setColumns(20);
		contentpane.add(seletext_trainno);
		
		final JLabel selename_label = new JLabel("----------name-----------");
		selename_label.setFont(new Font("宋体",Font.PLAIN,12));
		selename_label.setBounds(20,360,380,30);
		contentpane.add(selename_label);
		
		final JLabel seledata_label = new JLabel("----------data------------");
		seledata_label.setFont(new Font("宋体",Font.PLAIN,12));
		seledata_label.setBounds(20,400,380,30);
		contentpane.add(seledata_label);
		
		JButton sele_button = new JButton("查询");
		sele_button.setBounds(70,450,80,30);
		contentpane.add(sele_button);
		
		sele_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				System.out.println("querying data.....................");
				SqlWork ssw = new SqlWork();
				String trainno = seletext_trainno.getText();
				String name="没有找到您查询的数据";
				String data="没有找到您查询的数据";
				ssw.sql = "select * from TrainInfo where Train_no=\'"+trainno+"\'";
				System.out.println(ssw.sql);
				if(0 == SqlManage.Select(ssw)){
					selename_label.setText(name);
					seledata_label.setText(data);
					JOptionPane.showMessageDialog(traininfo_frame, "没有您查找的数据！");
				}
				else{
					name=ssw.namelist.get(0);
					data=ssw.datalist.get(0);
					selename_label.setText(name);
					seledata_label.setText(data);
				}
			}
		});
		
		
		JLabel upda_data = new JLabel("修改列车信息");
		upda_data.setFont(new Font("宋体",Font.PLAIN,14));
		upda_data.setBounds(460,280,100,30);
		contentpane.add(upda_data);
		
		JLabel updatrainno_label = new JLabel("更新列车号");
		updatrainno_label.setBounds(400,320,100,30);
		updatrainno_label.setFont(new Font("宋体",Font.PLAIN,14));
		contentpane.add(updatrainno_label);
		
		updatext_trainno = new JTextField();
		updatext_trainno.setBounds(510,320,200,30);
		updatext_trainno.setColumns(20);
		contentpane.add(updatext_trainno);
		
		JLabel updastartsta_label = new JLabel("始发站");
		updastartsta_label.setBounds(400,360,100,30);
		updastartsta_label.setFont(new Font("宋体",Font.PLAIN,14));
		contentpane.add(updastartsta_label);
		
		updatext_startsta = new JTextField();
		updatext_startsta.setBounds(510,360,200,30);
		updatext_startsta.setColumns(20);
		contentpane.add(updatext_startsta);
		
		JLabel updaendsta_label = new JLabel("终点站");
		updaendsta_label.setBounds(400,400,100,30);
		updaendsta_label.setFont(new Font("宋体",Font.PLAIN,14));
		contentpane.add(updaendsta_label);
		
		updatext_endsta = new JTextField();
		updatext_endsta.setBounds(500,400,200,30);
		updatext_endsta.setColumns(20);
		contentpane.add(updatext_endsta);
		
		JLabel updaprice_label = new JLabel("价格");
		updaprice_label.setBounds(400,440,100,30);
		updaprice_label.setFont(new Font("宋体",Font.PLAIN,14));
		contentpane.add(updaprice_label);
		
		updatext_price = new JTextField();
		updatext_price.setBounds(500,440,200,30);
		updatext_price.setColumns(20);
		contentpane.add(updatext_price);
		
		JButton update_button = new JButton("更新");
		update_button.setBounds(520,480,100,30);
		contentpane.add(update_button);
		update_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				System.out.println("updating data.....................");
				SqlWork usw = new SqlWork();
				String trainno = updatext_trainno.getText();
				String start = updatext_startsta.getText();
				String end = updatext_endsta.getText();
				String price = updatext_price.getText();
				
				usw.sql = "update TrainInfo "
						+ "set Start_sta=\'"+start+"\',End_sta=\'"+end
						+"\',price=\'"+price+"\' where Train_no=\'"+trainno+"\'";
				System.out.println(usw.sql);
				if(true == SqlManage.Update(usw)){
					JOptionPane.showMessageDialog(traininfo_frame, "更新成功");
				}
				else{
					JOptionPane.showMessageDialog(traininfo_frame, "更新失败");
				}
			}
			
		});
	
	}
}
