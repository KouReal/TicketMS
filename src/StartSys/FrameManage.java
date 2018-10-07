package StartSys;

import javax.swing.JFrame;

public class FrameManage {
	
	public static JFrame Mainframe;
	public static JFrame AdmiSelectframe;
	public static void Dispose_Frame(JFrame frame){
		frame.dispose();
	}
	
	public static void Active_Mainframe(){
		Mainframe.setVisible(true);
	}
	public static void Active_AdmiSelectUI(){
		AdmiSelectframe.setVisible(true);
	}
}
