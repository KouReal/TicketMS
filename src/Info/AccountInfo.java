package Info;

import SqlTran.SqlManage;
import StartSys.SqlWork;

public class AccountInfo {
	
	public AccountInfo(){
		
	}
	
	public static boolean Regist(String account,String password){
		System.out.println("registing account.................");
		SqlWork registsw = new SqlWork();
		registsw.sql = "insert AccountInfo values (\'"+account+"\',\'"+password+"\')";
		System.out.println(registsw.sql);
		 
		if(false == SqlManage.Insert(registsw)){
			return false;
		}
		else{
			System.out.println("regist successfully.................");
			return true;
		}
	}
	public static boolean CheckAccount(String account,String password){
		System.out.println("checking account.................");
		SqlWork checksw = new SqlWork();
		checksw.sql = "select count(*) from AccountInfo where Account= \'"+account+"\' and Pwd= \'"+password+"\'";
		System.out.println(checksw.sql);
		
		if(0 == SqlManage.Select(checksw)){
			return false;
		}
		else{
			System.out.println("find "+checksw.count+" record(s) successfully");
			return true;
		}
	}
}
