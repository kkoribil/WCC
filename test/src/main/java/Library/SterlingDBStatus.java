package Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.prolifics.ProlificsSeleniumAPI;
import FrameWork.DataBase;
import FrameWork.Excel_Reader;
import FrameWork.Generic;

public class SterlingDBStatus {
	Logger  log;
	Generic gen1 = new Generic();
		public SterlingDBStatus()
		{
			
			try{PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
			String logFilePath = System.getProperty("user.dir") + "\\ProlificsReports\\";
			gen1.UpdatePropFile("log", logFilePath);
			log = Logger.getLogger(SterlingDBStatus.class);
			}
			catch(Exception e)
			{
				System.out.println("Inside Exception");
			}
		}
	public static Excel_Reader excelReader;
	public static int i = 1;
	int PassCount = 0, FailCount = 0;
	public Connection OraConn;
	public HashMap<String, String> XLTestData;
	DataBase db = new DataBase();
	ProlificsSeleniumAPI oPSelFW = null;

	public String SterlingDetails() throws Exception {
		try {
			//String OrderNumber = XLTestData.get("OrderNumber").toString();
			/*String Host = XLTestData.get("HostName").toString();
			String userName = XLTestData.get("DBUserName").toString();
			String Password = XLTestData.get("DBPassword").toString();
			String SID = XLTestData.get("SID").toString();*/
			//excelReader.cFileNameWithPath = TestDataPath;
			String TestDataPath = System.getProperty("user.dir") + "\\Data\\SterlingDC.xlsx";
			excelReader.cFileNameWithPath = TestDataPath;
			excelReader.cSheetName = "TestData";
			excelReader.cSheetName = "TestData";
			excelReader.cTcID = "TestCaseID";
			excelReader.cTcValue = "1";
			XLTestData = new HashMap<String, String>();
			XLTestData = excelReader.readExcel("TC_STR_" + Integer.toString(i));
			//basetest.test = basetest.extent.createTest(" WCC Create Order", "WCC Create Order");
			
			Connection stre_Conn = db.ConnectToDB(XLTestData.get("HostName").toString(), XLTestData.get("SID").toString(),XLTestData.get("DBUserName").toString(),XLTestData.get("DBPassword").toString(), oPSelFW);
			
			String Stat = "";

			String sqlQuery = "select Max(Status) from yfs_order_release_Status where Order_Header_Key in "
					+ "(select Order_Header_Key from  YFS_ORDER_HEADER WHERE ORDER_NO = '" + XLTestData.get("OrderNumber").toString() + "')";

			// Statement st = stre_Conn.createStatement();

			ResultSet rs = db.ExecuteDBQuery(stre_Conn, sqlQuery, oPSelFW);
			while (rs.next()) {
				Stat = rs.getString("Max(Status)");
			}

			String StatusValue;
			if (Stat.contains("1100"))
				StatusValue = "Created";
			else if (Stat.contains("1300"))
				StatusValue = "BackOrder";
			else if (Stat.contains("1500.101"))                                                                                                                      
				StatusValue = "DTCSchedule";
			else if (Stat.contains("1500"))
				StatusValue = "Scheduled";
			else if (Stat.contains("1600"))
				StatusValue = "Awaiting DS PO Creation";
			else if (Stat.contains("2100"))
				StatusValue = "DS PO Created";
			else if (Stat.contains("3700.01.540"))
				StatusValue = "Reprocessed";
			else if (Stat.contains("3200.050"))
				StatusValue = "Dropped For Fulfillment";
			else if (Stat.contains("3200.200"))
			{
				StatusValue = "Invoice Preship";
			System.out.println("Invoice Preship");
			}
			else if (Stat.contains("3200.100"))
				StatusValue = "Acknowledge";
			else if (Stat.contains("3950.01"))
				StatusValue = "Return Invoiced";
			else if (Stat.contains("3700") && Stat.contains("00.03"))
				StatusValue = "Delivered";
			else if (Stat.contains("3700.01.545"))
				StatusValue = "DCcancel";
			else if (Stat.contains("9000"))
				StatusValue = "cancel";
			else if (Stat.contains("3700") == true && Stat.contains("7777") == false)
				StatusValue = "Shipped";
			else if (Stat.contains("3700.7777"))
				StatusValue = "Delivered";
			else if(Stat.contains("3200.100"))
				StatusValue = "Acknowledged";
			else if (Stat.contains("3200.520"))
				StatusValue = "PreReprocessed";
			else {

				StatusValue = "Invalid status";
				
				oPSelFW.reportStepDetails("Order Number status " , XLTestData.get("OrderNumber").toString() +" order is in  in-valid Status" , "Fail"); 
				log.error(XLTestData.get("OrderNumber").toString() +" order is in  in-valid Status"); 
			}
			return (StatusValue);
			
		} catch (SQLException e) {
			oPSelFW.reportStepDetails("Error Occured "  ,e+ " is displayed", "Fail");
			log.error(e+ " is displayed");
		} 
		return null;

	}
	
public String getOrderStatus(String returnOrdernumber,Map<String, String> XLTestData,ProlificsSeleniumAPI oPSelFW) throws Exception
	
	{   
		Generic1 gen = new Generic1();
		String StatusValue = "";
		String sqlQuery = null;
		try {
			
			DataBase db1 = new DataBase();
			String Environment = XLTestData.get("WCCENV").toString();
			String HostName, UserName, DBPassword, DBSID;
			if (Environment.contains("STST2")) {
				HostName = gen.getPropertyValue("stst2.db.hostname");
				UserName = gen.getPropertyValue("stst2.db.user");
				DBPassword = gen.getPropertyValue("stst2.db.password");
				DBSID = gen.getPropertyValue("stst2.db.sid");
			} else if (Environment.contains("EQA3")) {
				HostName = gen.getPropertyValue("eqa3.db.hostname");
				UserName = gen.getPropertyValue("eqa3.db.user");
				DBPassword = gen.getPropertyValue("eqa3.db.password");
				DBSID = gen.getPropertyValue("eqa3.db.sid");
			} else {
				HostName = gen.getPropertyValue("stst.db.hostname");
				UserName = gen.getPropertyValue("stst.db.user");
				DBPassword = gen.getPropertyValue("stst.db.password");
				DBSID = gen.getPropertyValue("stst.db.sid");
			}
			

			Connection conn = db1.ConnectToDB(HostName, DBSID, UserName, DBPassword, oPSelFW);
		
		String Stat = "";
		
		
		if (Environment.contains("STST2"))
		
		{
		
		 sqlQuery = "select Max(Status) from yantra_stst_owner.yfs_order_release_Status where Order_Header_Key in "
			+ "(select Order_Header_Key from  yantra_stst_owner.YFS_ORDER_HEADER WHERE ORDER_NO = '" + returnOrdernumber + "')";
		
		}
		else if(Environment.contains("EQA3"))
		
		{
		 sqlQuery = "select Max(Status) from yantra_owner.yfs_order_release_Status where Order_Header_Key in "
					+ "(select Order_Header_Key from  yantra_owner.YFS_ORDER_HEADER WHERE ORDER_NO = '" + returnOrdernumber + "')";
		}
		
		else
		{
			
			
				 sqlQuery = "select Max(Status) from yantra_owner.yfs_order_release_Status where Order_Header_Key in "
							+ "(select Order_Header_Key from  yantra_owner.YFS_ORDER_HEADER WHERE ORDER_NO = '" + returnOrdernumber + "')";
		
		}
		
		
		
		
		
		
		ResultSet rs = db.ExecuteDBQuery(conn, sqlQuery, oPSelFW);
		while (rs.next()) {
			Stat = rs.getString("Max(Status)");
		}

		if (Stat.contains("1100"))
			StatusValue = "Created";
		else if (Stat.contains("1300"))
			StatusValue = "BackOrder";
		else if (Stat.contains("1500.101"))                                                                                                                      
			StatusValue = "DTCSchedule";
		else if (Stat.contains("1500"))
			StatusValue = "Scheduled";
		else if (Stat.contains("1600"))
			StatusValue = "Awaiting DS PO Creation";
		else if (Stat.contains("2100"))
			StatusValue = "DS PO Created";
		else if (Stat.contains("3700.01.540"))
			StatusValue = "Reprocessed";
		else if (Stat.contains("3200.050"))
			StatusValue = "Dropped For Fulfillment";
		else if (Stat.contains("3200.200"))
		{
			StatusValue = "Invoice Preship";
		System.out.println("Invoice Preship");
		}
		else if (Stat.contains("3200.100"))
			StatusValue = "Acknowledge";
		else if (Stat.contains("3950.01"))
			StatusValue = "Return Invoiced";
		else if (Stat.contains("3700") && Stat.contains("00.03"))
			StatusValue = "Delivered";
		else if (Stat.contains("3700.01.545"))
			StatusValue = "DCcancel";
		else if (Stat.contains("9000"))
			StatusValue = "cancel";
		else if (Stat.contains("3700") == true && Stat.contains("7777") == false)
			StatusValue = "Shipped";
		else if (Stat.contains("3700.7777"))
			StatusValue = "Delivered";
		else if(Stat.contains("3200.100"))
			StatusValue = "Acknowledged";
		else if (Stat.contains("3200.520"))
			StatusValue = "PreReprocessed";
		else {

			StatusValue = "Invalid status";
			
			oPSelFW.reportStepDetails("Order Number status " , XLTestData.get("OrderNumber").toString() +" order is in  in-valid Status" , "Fail");
			log.error( XLTestData.get("OrderNumber").toString() +" order is in  in-valid Status" );
		}
		return (StatusValue);
		
	} catch (SQLException e) {
		oPSelFW.reportStepDetails("Error Occured "  ,e+ " is displayed", "Fail");
		log.error(e+ " is displayed");
	}
	return StatusValue;

	}

	public Connection ConnectToDB(String HostName, String UserName, String Password, String SID, ProlificsSeleniumAPI oPSelFW) throws SQLException, Exception
	 { 
		try{
			OraConn = DriverManager.getConnection("jdbc:oracle:thin:@"+HostName+":1521:"+SID, UserName, Password);
		}
		catch(SQLException e)
		{
			oPSelFW.reportStepDetails("Exception"  , e + " is displayed", "Fail");
			log.error( e + " is displayed");
		}
		return(OraConn);
	 }
}
