package Scripts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import com.prolifics.ProlificsSeleniumAPI;

import FrameWork.DataBase;
import Library.Generic1;

public class APIMethods {
	public Connection stST2Con, ecomCon;
	public Connection OraConn;
	DataBase db = new DataBase();
	public static ProlificsSeleniumAPI oPSelFW = null;
	Generic1 gen = new Generic1();
	private HashMap<String, String> XLTestDataBI = new HashMap<String, String>();
	
	public ResultSet apiStatus(String ordernumber,HashMap<String, String> XLTestData,ProlificsSeleniumAPI oPSelFW) throws Exception 
	{
	
	try {
		
		String Environment = XLTestData.get("WCCENV").toString();
		String HostName, UserName, DBPassword, DBSID;
		String CLEHostName, CLEDBUserName, CLEDBPassword, CLESID;
		
		if (Environment.contains("STST2")) {
			 CLEHostName = gen.getKibanaDetails("stst2.db.hostname");
			 CLEDBUserName = gen.getKibanaDetails("stst2.db.user");
			 CLEDBPassword =gen.getKibanaDetails("stst2.db.password");
			 CLESID = gen.getKibanaDetails("stst2.db.sid");
		} else {
		     CLEHostName = gen.getKibanaDetails("stst.db.hostname");
			 CLEDBUserName = gen.getKibanaDetails("stst.db.user");
			 CLEDBPassword =gen.getKibanaDetails("stst.db.password");
			 CLESID = gen.getKibanaDetails("stst.db.sid");
		}
		
		
		Connection stre_Conn = ConnectToDB(CLEHostName, CLEDBUserName,CLEDBPassword,CLESID,oPSelFW);
		String Stat = "";
		String Query;
	  if (Environment.contains("STST2"))
			
		{
		
		   Query = "select * from yantra_stst_owner.yfs_order_line where order_header_key in(select order_header_key from yantra_stst_owner.yfs_order_header where order_no='"
					+ ordernumber + "') and kit_code=' ' and prime_line_no != '950'";
			
		}else
		{
		 Query = "select * from yantra_owner.yfs_order_line where order_header_key in(select order_header_key from yantra_owner.yfs_order_header where order_no='"
					+ ordernumber + "') and kit_code=' ' and prime_line_no != '950'";
				}
	  

	  ResultSet rs = db.ExecuteDBQuery(stre_Conn, Query, oPSelFW);
	return rs;
	
	}

	catch (SQLException e) {
		oPSelFW.reportStepDetails("Error Occured "  ,e+ " is displayed", "Fail");
	}
	return null;
	}
	
	
	
	public Connection ConnectToDB(String HostName, String UserName, String Password, String SID, ProlificsSeleniumAPI oPSelFW) throws SQLException, Exception
	 { 
		try{
			OraConn = DriverManager.getConnection("jdbc:oracle:thin:@"+HostName+":1521:"+SID, UserName, Password);
		}
		catch(SQLException e)
		{
			
			oPSelFW.reportStepDetails("Exception"  , e + " is displayed", "Fail");
		}
		return(OraConn);
	 }
}
