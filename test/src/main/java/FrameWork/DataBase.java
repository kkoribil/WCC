package FrameWork;

import java.io.IOException;
import java.sql.Connection;
import com.prolifics.ProlificsSeleniumAPI;

import junit.framework.Assert;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class DataBase{
	public Connection OraConn;
	public static Connection postgreconn = null;
	Generic gen = new Generic();
Logger  log;
	
	public DataBase()
	{
		
		try{PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
		String logFilePath = System.getProperty("user.dir") + "\\ProlificsReports\\";
		gen.UpdatePropFile("log", logFilePath);
		log = Logger.getLogger(DataBase.class);
		}
		catch(Exception e)
		{
			System.out.println("Inside Exception");
		}
	}
	//@Step("Connect to Database")
	/**
	 * Connects to the DataBase 
	 * 
	 * @param HostName  -- Name of the Database Host
	 * @param SID -- Name of the SID
	 * @param UserName -- UserName used for connecting to the Database
	 * @param Password -- Password used for connecting to the Database
	 * @throws Exception 
	 */
	public Connection ConnectToDB(String HostName, String SID, String UserName, String Password)
			throws SQLException, IOException {

		// test.log(Status.INFO, "Connected to DB Host: " + HostName + " SID: "+SID+ "
		// UserName: "+UserName);
		try {
			OraConn = DriverManager.getConnection("jdbc:oracle:thin:@" + HostName + ":1521:" + SID, UserName, Password);

		/*	oPSelFW.reportStepDetails("Connect to the DataBase", "Successfully connected to the DataBase with "
					+ HostName + " SID: " + SID + " UserName: " + UserName, "Pass");  log.info(*/
		} catch (SQLException e) {
			/*oPSelFW.reportStepDetails("Connect to the DataBase",
					"Unable to connect to the DataBase with " + HostName + " SID: " + SID + " UserName: " + UserName,
					"Fail");  log.error(*/
		}
		return (OraConn);
	}
	
	 public Connection ConnectToDB(String HostName, String SID, String UserName, String Password, ProlificsSeleniumAPI oPSelFW) throws SQLException, Exception
	 { 
		//test.log(Status.INFO, "Connected to DB Host: " + HostName + " SID: "+SID+ " UserName: "+UserName);
		try{
			OraConn = DriverManager.getConnection("jdbc:oracle:thin:@"+HostName+":1521:"+SID, UserName, Password);
		}
		catch(SQLException e)
		{
			oPSelFW.reportStepDetails("Exception"  , e + " is displayed", "Fail");
			Assert.assertEquals("Exception is displayed in Connecting to Database"  , e + " is displayed");
			log.error( e + " is displayed");
		}
		return(OraConn);
	 }
	 /**
		 * Executes the DataBase Query
		 * 
		 * @param HostName  -- Name of the Database Host
		 * @param SID -- Name of the SID
		 * @param UserName -- UserName used for connecting to the Database
		 * @param Password -- Password used for connecting to the Database
	 * @throws Exception 
	*/
	 public ResultSet ExecuteDBQuery(Connection OrConn, String sqlQuery, ProlificsSeleniumAPI oPSelFW)  throws SQLException, Exception
	 {
		ResultSet rs = null;
       try
       {
    	   Statement stat = OrConn.createStatement();
    	   rs = stat.executeQuery(sqlQuery);
       }
   	   catch(SQLException e)
	   {
   		oPSelFW.reportStepDetails("Verify if Exception is displayed"  , e + " is displayed", "Fail"); 
   		log.error( e + " is displayed"); 
   		Assert.assertEquals("Verify if Exception is displayed"  , e + " is displayed");
	   }
       return(rs);
	 }
	 /**
		 * Gets the ShipNode of the Order Header
		 * 
		 * @param orderHeaderKey -- Order Header Key of the Order 
	 * @throws Exception 
	 */
	 public String GetShipNodeKeyOfOrderHeader(String orderHeaderKey, ProlificsSeleniumAPI oPSelFW) throws Exception
	 {
		String ShipNodeKeyQuery = "select SHIPNODE_KEY from YFS_ORDER_LINE where order_header_key in ('"+orderHeaderKey+"')";
		String ShipNodeKey = "";
		try
		{
			ResultSet rs  = ExecuteDBQuery(OraConn, ShipNodeKeyQuery, oPSelFW);
			while (rs.next()) {
				ShipNodeKey = rs.getString("SHIPNODE_KEY");
			}
		}
		catch(SQLException e)
		{
			oPSelFW.reportStepDetails("Exception"  , e + " is displayed", "Fail");  
			Assert.assertEquals("Exception is displayed in GetShipNodeKeyOfOrderHeader "  , e + " is displayed");
			log.error( e +  " is displayed");	
		}
		return(ShipNodeKey);
	 }
	 public boolean verifyIfItemExistsInMDMDataBase(HashMap<String, String> XLTestData, String GenItemNumber, ProlificsSeleniumAPI oPSelFW) throws Exception
	 {
		 	Connection MDMOraConn = ConnectToDB(XLTestData.get("MDMHostName").toString(), XLTestData.get("MDMSID").toString(), XLTestData.get("MDMDBUserName").toString(), XLTestData.get("MDMDBPassword").toString(), oPSelFW);
			Statement MDMstat = MDMOraConn.createStatement();
			ResultSet MDMrs = MDMstat.executeQuery("SELECT count(*) as count FROM WS_ITEM_MASTER WHERE CPRODUCTID = '"+GenItemNumber+"'");
			MDMrs.next();
			String MDMcount = MDMrs.getString("count");
			MDMrs.close();
			MDMstat.close();
			MDMOraConn.close();
			if(Integer.parseInt(MDMcount) > 0)
			{
				
				
				oPSelFW.reportStepDetails("Item Number should be generated"  , GenItemNumber + "Item is successfully received to MDM", "Pass"); 
				log.info( GenItemNumber + "Item is successfully received to MDM"); 
				
				//basetest.test.log(Status.PASS, GenItemNumber + " Item is successfully received to MDM");
				return(true);
			}
			else
				return(false);
	 }
		public Connection getDBConnection(ProlificsSeleniumAPI oPSelFW) throws IOException, SQLException
		{
			Connection stre_Conn  = null;
			String MCVNHost = "", MCVNSID= "", MCVNuser= "", MCVNPassword= ""; 
			try{// Credentials fetching to connect sterling DB
			String Env = System.getProperty("Environment");
			if(Env.contains("STST2")) {
				MCVNHost = gen.getPropertyValue("stst2.db.hostname", oPSelFW);
				MCVNSID = gen.getPropertyValue("stst2.db.sid", oPSelFW);
				MCVNuser = gen.getPropertyValue("stst2.db.user", oPSelFW);
				MCVNPassword = gen.getPropertyValue("stst2.db.password", oPSelFW);
			}
			else
			{
				MCVNHost = gen.getPropertyValue("stst.db.hostname", oPSelFW);
				MCVNSID = gen.getPropertyValue("stst.db.sid", oPSelFW);
				MCVNuser = gen.getPropertyValue("stst.db.user", oPSelFW);
				MCVNPassword = gen.getPropertyValue("stst.db.password", oPSelFW);
			}
		
			System.out.println("\n");
			System.out.println("***Order Charge Transaction Queries***");
			// connecting to sterling DB
			stre_Conn = ConnectToDB(MCVNHost, MCVNSID, MCVNuser, MCVNPassword);
			
			return(stre_Conn);
			}
			catch(Exception e)
			{
				oPSelFW.reportStepDetails("Connect to the DataBase", "Not able to connect to Sterling DataBase with HostName "+ MCVNHost+ " SID: " + MCVNSID + " UserName: " + MCVNuser, "Pass");
				log.info( "Not able to connect to Sterling DataBase with HostName "+ MCVNHost+ " SID: " + MCVNSID + " UserName: " + MCVNuser);
			}
			return stre_Conn;
		}
		public Connection getDataBaseConnection() throws IOException, SQLException
		{
			Connection stre_Conn  = null;
			String MCVNHost = "", MCVNSID= "", MCVNuser= "", MCVNPassword= ""; 
			// Credentials fetching to connect sterling DB
			String Env = System.getProperty("Environment");
			if(Env.contains("STST2")) {
				MCVNHost = gen.getPropertyValue("stst2.db.hostname");
				MCVNSID = gen.getPropertyValue("stst2.db.sid");
				MCVNuser = gen.getPropertyValue("stst2.db.user");
				MCVNPassword = gen.getPropertyValue("stst2.db.password");
			}
			else
			{
				MCVNHost = gen.getPropertyValue("stst.db.hostname");
				MCVNSID = gen.getPropertyValue("stst.db.sid");
				MCVNuser = gen.getPropertyValue("stst.db.user");
				MCVNPassword = gen.getPropertyValue("stst.db.password");
			}
		
			System.out.println("\n");
			System.out.println("***Order Charge Transaction Queries***");
			// connecting to sterling DB
			stre_Conn = ConnectToDB(MCVNHost, MCVNSID, MCVNuser, MCVNPassword);
			
			return(stre_Conn);
		}
}
