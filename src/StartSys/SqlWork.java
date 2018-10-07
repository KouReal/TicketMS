package StartSys;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlWork {
	public String sql;
	public int count=0;
	public ResultSet rs = null;
	public List<String> namelist = new ArrayList<String>();
	public List<String> datalist = new ArrayList<String>();
	public void addtolist(){
		if(rs == null)return;
		String tempstr="";
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columncount = rsmd.getColumnCount();
			for(int j=1;j<=columncount;++j){
				tempstr=tempstr+rsmd.getColumnName(j).trim()+" ";
			}
			namelist.add(tempstr);	
			tempstr="";
			
			while(rs.next()){
				count++; 
				for(int j=1;j<=columncount;++j){
					tempstr=tempstr+rs.getString(j).trim()+" ";
				}
				datalist.add(tempstr);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
