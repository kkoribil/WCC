package Library;

import java.io.File; 
import java.io.IOException;
import java.sql.Connection;
import com.aventstack.extentreports.Status;

import junit.framework.Assert;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
public class DataBase{
	public Connection OraConn;
	public static Connection postgreconn = null;
	//@Step("Connect to Database")
	/**
	 * Connects to the DataBase 
	 * 
	 * @param HostName  -- Name of the Database Host
	 * @param SID -- Name of the SID
	 * @param UserName -- UserName used for connecting to the Database
	 * @param Password -- Password used for connecting to the Database
	 */
	 public Connection ConnectToDB(String HostName, String SID, String UserName, String Password) throws SQLException
	 { 
		//test.log(Status.INFO, "Connected to DB Host: " + HostName + " SID: "+SID+ " UserName: "+UserName);
		try{
			OraConn = DriverManager.getConnection("jdbc:oracle:thin:@"+HostName+":1521:"+SID, UserName, Password);
		}
		catch(SQLException e)
		{
			Assert.assertEquals("Verify if exception is displayed in connecting to Database", "Exception is displayed in Connecting to Database");
		}
		return(OraConn);
	 }
	/* public boolean savePayLoadXMLs(String GenItemNumber, HashMap<String, String> XLTestData, BaseTest basetest) throws SQLException, IOException
	 {
		File folder = new File(System.getProperty("user.dir") +"\\target\\MDMXMLs\\PayLoads");	
		if(folder.exists()) {
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
					listOfFiles[i].delete();
			}
		Util util = new Util();
		DataBase db = new DataBase();
	 	Connection CLEOraConn = db.ConnectToDB(XLTestData.get("CLEHostName").toString(), XLTestData.get("CLESID").toString(), XLTestData.get("CLEDBUserName").toString(), XLTestData.get("CLEDBPassword").toString(), basetest);
		Statement CLEstat = CLEOraConn.createStatement();
		
		ResultSet CLErs = CLEstat.executeQuery("select count(*) as count from app_payloadarchive ap, app_audit aa "+ 
		"where aa.payloadid = ap.payloadid "+
		"and aa.STATUS = 'Message received from MDM' and transactionid='"+GenItemNumber+"' order by AUDIT_TIMESTAMP DESC");
		int COUNT = 0;
		while (CLErs.next()) {
			COUNT = Integer.parseInt(CLErs.getString("COUNT"));
		}
		if(COUNT > 0)
		{	CLErs = CLEstat.executeQuery("select * from app_payloadarchive ap, app_audit aa " + 
				"where aa.payloadid = ap.payloadid " + 
				"and aa.STATUS = 'Message received from MDM' and transactionid='"+GenItemNumber+"' order by AUDIT_TIMESTAMP DESC");
			int payloadNum = 1;
			while (CLErs.next()) {
				String MESSAGE = CLErs.getString("PAYLOAD");
				util.WriteTextToFile(MESSAGE, System.getProperty("user.dir") + "\\target\\MDMXMLs\\PayLoads\\PayLoad"+payloadNum+".xml");
				payloadNum++;
			}
			return(true);
		}
		else
		{
			basetest.test.log(Status.INFO, "PayLoad archieve table is not updated with item "+GenItemNumber+" details");
			return(false);
		}
		}
		else
		{
			basetest.test.log(Status.INFO, System.getProperty("user.dir") +"\\target\\MDMXMLs\\PayLoads folder does not exists in target folder");
			return(false);
		}
	 }*/
	/* public boolean verifyIfItemExistsInMDMDataBase(HashMap<String, String> XLTestData, String GenItemNumber, BaseTest basetest) throws SQLException, InterruptedException, IOException
	 {
		 	Connection MDMOraConn = ConnectToDB(XLTestData.get("MDMHostName").toString(), XLTestData.get("MDMSID").toString(), XLTestData.get("MDMDBUserName").toString(), XLTestData.get("MDMDBPassword").toString(), basetest);
			Statement MDMstat = MDMOraConn.createStatement();
			ResultSet MDMrs = MDMstat.executeQuery("SELECT count(*) as count FROM WS_ITEM_MASTER WHERE CPRODUCTID = '"+GenItemNumber+"'");
			MDMrs.next();
			String MDMcount = MDMrs.getString("count");
			if(Integer.parseInt(MDMcount) > 0)
			{
				basetest.test.log(Status.PASS, GenItemNumber + " Item is successfully received to MDM");
				return(true);
			}
			else
			{
				Thread.sleep(5000);
				MDMrs = MDMstat.executeQuery("SELECT count(*) as count FROM WS_ITEM_MASTER WHERE CPRODUCTID = '"+GenItemNumber+"'");
				MDMrs.next();
				MDMcount = MDMrs.getString("count");
				if(Integer.parseInt(MDMcount) > 0)
				{
					basetest.test.log(Status.PASS, GenItemNumber + " Item is successfully received to MDM");
					return(true);
				}
				else
				{
					Thread.sleep(5000);
					MDMrs = MDMstat.executeQuery("SELECT count(*) as count FROM WS_ITEM_MASTER WHERE CPRODUCTID = '"+GenItemNumber+"'");
					MDMrs.next();
					MDMcount = MDMrs.getString("count");
					MDMrs.close();
					MDMstat.close();
					MDMOraConn.close();
					if(Integer.parseInt(MDMcount) > 0)
					{
						basetest.test.log(Status.PASS, GenItemNumber + " Item is successfully received to MDM");
						return(true);
					}
					{
						getErrorMessageInCLE(GenItemNumber, XLTestData, basetest);
						return(false);
					}
				}
			}
	 }
	 public Double getMarkUpValueOfItem(HashMap<String, String> XLTestData, String ItemNumber, BaseTest basetest) throws SQLException
	 {
		 Double mUpVal=0.0;
		 try { Connection MDMOraConn = ConnectToDB(XLTestData.get("MDMHostName").toString(), XLTestData.get("MDMSID").toString(), XLTestData.get("MDMDBUserName").toString(), XLTestData.get("MDMDBPassword").toString(), basetest);
		 Statement MDMstat = MDMOraConn.createStatement();
		 String markUpVal, conceptcode = "";
		 ResultSet MDMrs2 = MDMstat.executeQuery("SELECT CCNCPT_CODE FROM WS_ITEM_MASTER WHERE CPRODUCTID = '"+ItemNumber+"'");
		while (MDMrs2.next()) {
			conceptcode = MDMrs2.getString("CCNCPT_CODE");
		}
		ResultSet MDMrs3 = MDMstat.executeQuery("SELECT * FROM (select * from WS_CONCEPT WHERE CPRODUCTID = '"+conceptcode+"' ORDER BY CMODVERSION DESC) WHERE ROWNUM = 1");
		while (MDMrs3.next()) {
		  markUpVal = MDMrs3.getString("CMAX_MARKUP_CADENCE_PCT");
		   mUpVal=Double.parseDouble(markUpVal) * 0.01;   
		}}
		 catch(Exception e)
		 {
			 basetest.test.log(Status.FAIL, "Exception " + e + " is displayed");
		 }
		return(mUpVal);
	 }
	 public double getMarkDownValueOfItem(HashMap<String, String> XLTestData, String genItemNumber, BaseTest basetest) throws SQLException
	 {
		 double mDownVal=0.0;
		try { Connection MDMOraConn = ConnectToDB(XLTestData.get("MDMHostName").toString(), XLTestData.get("MDMSID").toString(), XLTestData.get("MDMDBUserName").toString(), XLTestData.get("MDMDBPassword").toString(), basetest);
		 Statement MDMstat = MDMOraConn.createStatement();
		 String markDownVal, conceptcode="";
		 ResultSet MDMrs2 = MDMstat.executeQuery("SELECT CCNCPT_CODE FROM WS_ITEM_MASTER WHERE CPRODUCTID = '"+genItemNumber+"'");
		while (MDMrs2.next()) {
			conceptcode = MDMrs2.getString("CCNCPT_CODE");
		}
		ResultSet MDMrs3 = MDMstat.executeQuery("SELECT * FROM (select * from WS_CONCEPT WHERE CPRODUCTID = '"+conceptcode+"' ORDER BY CMODVERSION DESC) WHERE ROWNUM = 1");
		while (MDMrs3.next()) {
		  markDownVal=MDMrs3.getString("CMAX_MARKDOWN_CADENCE_PCT");
		   mDownVal=Double.parseDouble(markDownVal) * 0.01;
	      }
		}
		 catch(Exception e)
		 {
			 basetest.test.log(Status.FAIL, "Exception " + e + " is displayed");
		 }
		return(mDownVal);
	 }
	 public double getClearanceCadenceValueOfItem(HashMap<String, String> XLTestData, String genItemNumber, BaseTest basetest) throws SQLException
	 {
		 double clrCadVal=0.0;
		try { 
			Connection MDMOraConn = ConnectToDB(XLTestData.get("MDMHostName").toString(), XLTestData.get("MDMSID").toString(), XLTestData.get("MDMDBUserName").toString(), XLTestData.get("MDMDBPassword").toString(), basetest);
		 Statement MDMstat = MDMOraConn.createStatement();
		
		 String clrcCadenceVal, conceptcode="";
		 ResultSet MDMrs2 = MDMstat.executeQuery("SELECT CCNCPT_CODE FROM WS_ITEM_MASTER WHERE CPRODUCTID = '"+genItemNumber+"'");
		while (MDMrs2.next()) {
			conceptcode = MDMrs2.getString("CCNCPT_CODE");
		}
		ResultSet MDMrs3 = MDMstat.executeQuery("SELECT * FROM (select * from WS_CONCEPT WHERE CPRODUCTID = '"+conceptcode+"' ORDER BY CMODVERSION DESC) WHERE ROWNUM = 1");
		while (MDMrs3.next()) {
	      
		  clrcCadenceVal=MDMrs3.getString("CMAX_CLEARANCE_CADENCE_PCT");
	       clrCadVal=Double.parseDouble(clrcCadenceVal) * 0.01;
		}}
	 catch(Exception e)
	 {
		 basetest.test.log(Status.FAIL, "Exception " + e + " is displayed");
	 }
		return(clrCadVal);
	 }
	 public double getDiscountToOfItem(HashMap<String, String> XLTestData, String genItemNumber, BaseTest basetest) throws SQLException
	 {
		 double dis2Reg=0.0;
		 try{Connection MDMOraConn = ConnectToDB(XLTestData.get("MDMHostName").toString(), XLTestData.get("MDMSID").toString(), XLTestData.get("MDMDBUserName").toString(), XLTestData.get("MDMDBPassword").toString(), basetest);
		 Statement MDMstat = MDMOraConn.createStatement();
		 String disc2Reg, conceptcode="";
		 ResultSet MDMrs2 = MDMstat.executeQuery("SELECT CCNCPT_CODE FROM WS_ITEM_MASTER WHERE CPRODUCTID = '"+genItemNumber+"'");
		while (MDMrs2.next()) {
			conceptcode = MDMrs2.getString("CCNCPT_CODE");
		}
		ResultSet MDMrs3 = MDMstat.executeQuery("SELECT * FROM (select * from WS_CONCEPT WHERE CPRODUCTID = '"+conceptcode+"' ORDER BY CMODVERSION DESC) WHERE ROWNUM = 1");
		while (MDMrs3.next()) {
	      disc2Reg=MDMrs3.getString("CMAX_DISCOUNT_TO_REGULAR_PCT");
		   dis2Reg=Double.parseDouble(disc2Reg) * 0.01;
		}}
		catch(Exception e)
		 {
			 basetest.test.log(Status.FAIL, "Exception " + e + " is displayed");
		 }
		return(dis2Reg);
	 }
	 public double getClearanceToRegularValueOfItem(HashMap<String, String> XLTestData, String genItemNumber, BaseTest basetest) throws SQLException
	 {
		 double clrc2Reg=0.0;
		 try{Connection MDMOraConn = ConnectToDB(XLTestData.get("MDMHostName").toString(), XLTestData.get("MDMSID").toString(), XLTestData.get("MDMDBUserName").toString(), XLTestData.get("MDMDBPassword").toString(), basetest);
		 Statement MDMstat = MDMOraConn.createStatement();
		 String clr2Reg, conceptcode="";
		 ResultSet MDMrs2 = MDMstat.executeQuery("SELECT CCNCPT_CODE FROM WS_ITEM_MASTER WHERE CPRODUCTID = '"+genItemNumber+"'");
		while (MDMrs2.next()) {
			conceptcode = MDMrs2.getString("CCNCPT_CODE");
		}
		ResultSet MDMrs3 = MDMstat.executeQuery("SELECT * FROM (select * from WS_CONCEPT WHERE CPRODUCTID = '"+conceptcode+"' ORDER BY CMODVERSION DESC) WHERE ROWNUM = 1");
		while (MDMrs3.next()) {
		  
		  clr2Reg=MDMrs3.getString("CMAX_CLEARANCE_TO_REGULAR_PCT");
		   clrc2Reg=Double.parseDouble(clr2Reg)*0.01;
		}}
		catch(Exception e)
		 {
			 basetest.test.log(Status.FAIL, "Exception " + e + " is displayed");
		 }
		return(clrc2Reg);
	 }
	 public double getSpiffToRegularValueOfItem(HashMap<String, String> XLTestData, String genItemNumber, BaseTest basetest) throws SQLException
	 {
		 double spiffToReglar=0.0;
		 try{
			 Connection MDMOraConn = ConnectToDB(XLTestData.get("MDMHostName").toString(), XLTestData.get("MDMSID").toString(), XLTestData.get("MDMDBUserName").toString(), XLTestData.get("MDMDBPassword").toString(), basetest);
			 Statement MDMstat = MDMOraConn.createStatement();
			 String spiff2Reg, conceptcode="";
			 ResultSet MDMrs2 = MDMstat.executeQuery("SELECT CCNCPT_CODE FROM WS_ITEM_MASTER WHERE CPRODUCTID = '"+genItemNumber+"'");
			 while (MDMrs2.next()) {
				 conceptcode = MDMrs2.getString("CCNCPT_CODE");
			 }
			 ResultSet MDMrs3 = MDMstat.executeQuery("SELECT * FROM (select * from WS_CONCEPT WHERE CPRODUCTID = '"+conceptcode+"' ORDER BY CMODVERSION DESC) WHERE ROWNUM = 1");
		while (MDMrs3.next()) {
		  spiff2Reg=MDMrs3.getString("CMAX_SPIFF_TO_REGULAR_PCT");
		   spiffToReglar=Double.parseDouble(spiff2Reg)*0.01;
		}
		 }
		 catch(Exception e)
		 {
			 basetest.test.log(Status.FAIL, "Exception " + e + " is displayed");
		 }
		return(spiffToReglar);
	 }
	 public boolean getErrorMessageInCLE(String GenItemNumber, HashMap<String, String> XLTestData, BaseTest basetest) throws SQLException, IOException
	 {
		DataBase db = new DataBase();
	 	Connection CLEOraConn = db.ConnectToDB(XLTestData.get("CLEHostName").toString(), XLTestData.get("CLESID").toString(), XLTestData.get("CLEDBUserName").toString(), XLTestData.get("CLEDBPassword").toString(), basetest);
		Statement CLEstat = CLEOraConn.createStatement();
		
		ResultSet CLErs = CLEstat.executeQuery("select count(*) as count from app_payloadarchive ap, app_audit aa \n" + 
				"		where aa.payloadid = ap.payloadid \n" + 
				"		and transactionid='"+GenItemNumber+"' and MSG like 'Runtime error: %' order by AUDIT_TIMESTAMP DESC");
		int COUNT = 0;
		String MSG = "";
		while (CLErs.next()) {
			COUNT = Integer.parseInt(CLErs.getString("COUNT"));
		}
		if(COUNT > 0)
		{	String CLEQuery = "select MSG from app_payloadarchive ap, app_audit aa \n" + 
				"		where aa.payloadid = ap.payloadid \n" + 
				"		and transactionid='"+GenItemNumber+"' and MSG like 'Runtime error: %' order by AUDIT_TIMESTAMP DESC";
			CLErs = CLEstat.executeQuery(CLEQuery);		
			while (CLErs.next()) {
				MSG = CLErs.getString("MSG");
			}
			basetest.test.log(Status.FAIL, MSG  + " is displayed in CLE for the item "+GenItemNumber);
			return(true);
		}
		else
		{
			return(false);
		}
	 }*/
	 /*   public void  shipmentdetails(HashMap<String,String> XLTestData, BaseTest basetest) throws SQLException, IOException
	    {    
	    	try
		       {	
				String query ="select * from yfs_shipment";				
				Connection stre_Conn = ConnectToDB(XLTestData.get("DBHOSTNAME").toString(),XLTestData.get("DBSID").toString(), XLTestData.get("DBUSERNAME").toString(),XLTestData.get("DBPASSWORD").toString(), basetest);
				Statement str_Statement = stre_Conn.createStatement();
				ResultSet str_ResultSet = str_Statement.executeQuery(query);

			while(str_ResultSet.next())
				
				{
					String shipnode_key;
					String ship_mode;
					String pro_no;
					String scac;
					String carrier_service_code;
					String total_weight;
					String total_weight_uom;
					String total_volume; 
					String total_quantity;
					String carrier_type;
					String shipment_no;
					String enterprise_code;
					String order_no;
					String extn_source_shipment_no;
					String Ship_date;
				 
					 shipnode_key  = str_ResultSet.getString("shipnode_key"); 
					 ship_mode = str_ResultSet.getString("ship_mode");
					 pro_no = str_ResultSet.getString("pro_no");
					 scac = str_ResultSet.getString("scac");
					 carrier_service_code = str_ResultSet.getString("carrier_service_code");
					 total_weight  = str_ResultSet.getString("total_weight");
					 total_weight_uom = str_ResultSet.getString("total_weight_uom");
					 total_volume = str_ResultSet.getString("total_volume");
					 total_quantity = str_ResultSet.getString("total_quantity");
					 carrier_type = str_ResultSet.getString("carrier_type");
					 shipment_no = str_ResultSet.getString("shipment_no");
					 enterprise_code = str_ResultSet.getString("enterprise_code");
					 order_no = str_ResultSet.getString("order_no");
					 extn_source_shipment_no = str_ResultSet.getString("extn_source_shipment_no");
					 Ship_date = str_ResultSet.getString("Ship_date");
				}	
				
		    }
		      catch(Exception e)
		       {
		    	  basetest.test.log(Status.FAIL, "Exception " + e +  " displayed");
		       }
		}*/

}