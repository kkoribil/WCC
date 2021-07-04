package Library;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.prolifics.ProlificsSeleniumAPI;

import FrameWork.Generic;

public class Sterling2 {
	Logger  log;
	Generic gen1 = new Generic();
		public Sterling2()
		{
			
			try{PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
			String logFilePath = System.getProperty("user.dir") + "\\ProlificsReports\\";
			gen1.UpdatePropFile("log", logFilePath);
			log = Logger.getLogger(Sterling2.class);
			}
			catch(Exception e)
			{
				System.out.println("Inside Exception");
			}
		}
     public static  Connection stST2Con;
	public Connection ecomCon;
	Generic gen = new Generic();
 	Util util = new Util();
 	
 	protected static String apiOut;
 	public static String DBFirstName; 
	public static String DBLastName;
	public static String DBEmail;
	public static String DBPhone;
	public static String DBBrand;
	public static String DBBrands;
	public static String DBOrderno;
	public static String WorkOrder;
	public static String DBStatus;
	public static String DBZipCode;
	public static String DBFromDate;
	public static String DBToDate;
	public static String DBLast;
	public static String DBEMail;
	public static String DBFirst;
	public static String DBpostalCode;
	public static String DBphnumber;
	public static String DBCity;
	public static String DBstate;
	public static String Datecreated_DB;
	public static String DBreturnno;
		
	String tabHeader="div[id='tabHeader']";
	String noRecords = "//table/tbody/tr/td[contains(text(),'No Records found')]";
	String OrderNo = "(//table/tbody/tr/td[3]/span)[1]";
/*###################################################################################################################################################################*/
          /* ############################################################## 
      * # Method Name : getOrderInvoicedDetails() 
      * # Author : Mamatha
      * # Date : 16/12/2019
     * # Description: getting Sales OrderDetails from Sterling 
      * ##############################################################*/
     
    
	public void orderSerachDBValidation_SalesOrder(WebDriver driver,ProlificsSeleniumAPI oPSelFW,HashMap<String, String> envTestData,String orderNo) throws SQLException, IOException {
		try {
			if(driver.findElements(By.cssSelector(tabHeader)).size()>0) {

			String OrderNumber=driver.findElement(By.xpath("(//p[contains(text(),'#:')]//copyable-text[@class='ng-binding'])[1]")).getText();
			stST2Con = DriverManager.getConnection("jdbc:oracle:thin:@" + envTestData.get("HostName") + ":1521:"+envTestData.get("SID"),
									envTestData.get("DBUserName"), envTestData.get("DBPassword"));
			    Statement stat = stST2Con.createStatement();
			    BigDecimal orderHeaderKey = null;
			    ResultSet rs = stat.executeQuery("select * from " + envTestData.get("Schema") + ".yfs_Order_header where order_no='" + orderNo + "'");
			    if (rs.next() == true) {
			           orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
			        }
			      
			  String ppppp="select * from "+ envTestData.get("Schema") + ".yfs_order_header where  order_header_key='" + orderHeaderKey + "'";
			  System.out.println("ppppp :"+ppppp);
			  ResultSet rs1 = stat.executeQuery("select * from "+ envTestData.get("Schema") + ".yfs_order_header where  order_header_key='" + orderHeaderKey + "'");
			  if (rs1.next() == true) {
			                 if (OrderNumber.contains(rs1.getString("ORDER_NO").trim())) {

	                	oPSelFW.reportStepDetails(" Verify Sales Order Number Displayed in CCUI is :" + OrderNumber,
	        					"Sales Order Displayed in the Database  is:" + rs1.getString("ORDER_NO").trim(), "Pass");
	                	log.info("Sales Order Displayed in the Database  is:" + rs1.getString("ORDER_NO").trim());
			                 }else
	        	               {
	        			 oPSelFW.reportStepDetails(" Verify Sales Order Number Displayed in CCUI is :" + OrderNumber,
	         					"Sales Order Displayed in the Database  is:" +rs1.getString("ORDER_NO").trim(), "Fail");
	        			 log.error("Sales Order Displayed in the Database  is:" +rs1.getString("ORDER_NO").trim());
	        	               }
	            
	         }
			}
		}
	    
	 catch (Exception e) {
		oPSelFW.reportStepDetails("Exception to be occur",
					"Exception is occur", "Fail");
		log.error("Exception is occur");
		    } 
}
} 

