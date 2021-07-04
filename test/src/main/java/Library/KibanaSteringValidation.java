package Library;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.prolifics.ProlificsSeleniumAPI;

import FrameWork.Generic;

public class KibanaSteringValidation {
	Logger  log;
	Generic gen1 = new Generic();
		public KibanaSteringValidation()
		{
			
			try{PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
			String logFilePath = System.getProperty("user.dir") + "\\ProlificsReports\\";
			gen1.UpdatePropFile("log", logFilePath);
			log = Logger.getLogger(KibanaSteringValidation.class);
			}
			catch(Exception e)
			{
				System.out.println("Inside Exception");
			}
		}
	public static ProlificsSeleniumAPI oPSelFW = null;
	public Connection OraConn;
	private HashMap<String, String> XLTestData = new HashMap<String, String>();
	Generic1 gen = new Generic1(); 
	String ItemNumberOfDays = "2";
	
	public void validateKibanaBalanceInquiry(HashMap<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws SQLException, IOException {
		
		String CLEHostName = gen.getKibanaDetails("CLEHostName");
		String CLEDBUserName = gen.getKibanaDetails("CLEDBUserName");
		String CLEDBPassword =gen.getKibanaDetails("CLEDBPassword");
		String CLESID = gen.getKibanaDetails("CLESID");
	
		Connection CLOraConn = ConnectToDB(CLEHostName, CLESID, CLEDBUserName, CLEDBPassword, oPSelFW);
		
		Statement CLEstat = CLOraConn.createStatement();
		
		Util util = new Util();
		
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(ItemNumberOfDays));
		Date currentDatePlusOne = (Date) c.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String strDate = formatter.format(currentDatePlusOne);
		
		
		String RMSQuery = "select * from app_payloadarchive where payloadid in(select PAYLOADID from app_audit where MSG LIKE 'SENT RESPONSE TO PAYMENT SERVICE CONSUMER'\r\n" + 
				"and transactiontype='LRCBALANCEINQUIRY') and PAYLOAD_TIMESTAMP > '"+strDate+"' order by PAYLOAD_TIMESTAMP DESC";
				
				
		ResultSet RMSrs = CLEstat.executeQuery(RMSQuery);
		
		
		oPSelFW.reportStepDetails("Execute Kibana XML Query", "Executed the Query " + RMSQuery, "Pass");
		log.info("Executed the Query " + RMSQuery);
		String MESSAGE = "";
		
		File file1 = new File(System.getProperty("user.dir") + "\\target\\PayloadsXmls\\");
		if (file1.exists() == false)
			file1.mkdir();
		FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "\\target\\PayloadsXmls\\" + ""));
		boolean errorFound = false;
		while (RMSrs.next()) {
				MESSAGE = RMSrs.getString("PAYLOAD");
				System.out.println(MESSAGE);
				if (MESSAGE.contains(XLTestData.get("LoyaltyRewardsCertificate").toString())) {
					if(MESSAGE.contains("Error")) 
					{
						oPSelFW.reportStepDetails("Error should be display" ,"Error is in balance inquiry xml page displayed" , "Fail");
						log.error("Error is in balance inquiry xml page displayed" );			
						 errorFound = true;
					}
					break;
				}
			}
			if(errorFound  == false)
			{
				oPSelFW.reportStepDetails("LRC Balance inqury xml should be successfull" ,"Verified LRC Balance inqury xml successfully in Kibana" , "Pass");
				log.info("Verified LRC Balance inqury xml successfully in Kibana" );
				
			}
				 
	}
public Connection ConnectToDB(String HostName, String SID, String UserName, String Password,
		ProlificsSeleniumAPI oPSelFW) throws SQLException, IOException {
	// test.log(Status.INFO, "Connected to DB Host: " + HostName + " SID: "+SID+ "
	// UserName: "+UserName);
	try {
		OraConn = DriverManager.getConnection("jdbc:oracle:thin:@" + HostName + ":1521:" + SID, UserName, Password);
		oPSelFW.reportStepDetails("Connect to the DataBase", "Successfully connected to the DataBase with "
				+ HostName + " SID: " + SID + " UserName: " + UserName, "Pass");
		log.info("Successfully connected to the DataBase with "
				+ HostName + " SID: " + SID + " UserName: " + UserName);
	} catch (SQLException e) {
		oPSelFW.reportStepDetails("Connect to the DataBase",
				"Unable to connect to the DataBase with " + HostName + " SID: " + SID + " UserName: " + UserName,
				"Fail");
		log.error("Unable to connect to the DataBase with " + HostName + " SID: " + SID + " UserName: " + UserName);
	}
	return (OraConn);
}
 
	
	
	
	
	

}
