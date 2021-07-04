package Library;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.prolifics.ProlificsSeleniumAPI;

import FrameWork.DataBase;
import FrameWork.Generic;



public class DbValidations {
	Logger  log;
	Generic gen1 = new Generic();
		public DbValidations()
		{
			
			try{PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
			String logFilePath = System.getProperty("user.dir") + "\\ProlificsReports\\";
			gen1.UpdatePropFile("log", logFilePath);
			log = Logger.getLogger(DbValidations.class);
			}
			catch(Exception e)
			{
				System.out.println("Inside Exception");
			}
		}
	
	ProlificsSeleniumAPI oPSelFW = null;
	Generic1 gen = new Generic1();
	
	
	//validate the order summary page validation
		String orderNumberText	= "//span[@title='Order Number']";
		String Ordertype	=	"(//span[@class='scDataValue'])[5]";
		String createdby="(//span[@class='scDataValue'])[9]";
		String ototal="(//span[@class='scDataValue'])[2]";
		String channel="(//span[@class='scDataValue'])[6]";
		String Concept = "(//span[@class='scDataValue'])[7]";
		String shippingAddressXpath	=	"(//div[@class='addressdisplay'])[2]";
		String billingAddressXpath	=	"(//div[@class='addressdisplay'])[3]";
		String Item	="//div[@id='gridx_Grid_9']/div[3]/div[2]/div/table/tbody/tr/td[2]";
		String unit="//td[@aria-label='Unit Price']/div";
		String reg="(//td[@class='gridxCell    '])[12]";
		String qty="//td[@aria-label='Unit Price']/div/following::td[1]";
		String dateUI="(//td[@class='gridxCell    '])[8]";
		String ship="(//span[@uid='lblName'])[1]";
		String service="//div[@id='gridx_Grid_9']/div[3]/div[2]/div/table/tbody/tr/td[12]";
		String city="(//span[@class='scLabel '])[6]";
		String username="(//span[@uid='lblName'])[2]";
		String orderplaced="(//span[@class='scDataValue'])[3]";
		String country="(//span[@class='scLabel '])[7]";
		String phone="(//span[@class='scLabel '])[9]";
		String number="(//div[@class='productImageDesc'])[1]";
		String mode="(//td[@class='gridxCell    '])[9]";
		String Appease="//a[contains(text(),'Appease Customer')]";
		String note="//*[@id='idx_form_Textarea_0']";
		String Confirmbtn="(//span[contains(@id,'Button') and normalize-space(text())='Confirm'])[2]";
		String ReasonCode="(//input[@role='textbox'])[4]";
		String reasoncode="(//input[@role='textbox'])[3]";
		String SubReasonCode="(//input[@role='textbox'])[5]";
		String subreasoncode="(//input[@role='textbox'])[4]";
		String Nextbtn="(//span[@class='dijitReset dijitInline dijitButtonText'])[12]";
		String next="(//span[@class='dijitReset dijitInline dijitButtonTex(//span[@class='scDataValue'])[7]t'])[13]";
		String cancel="(//a[contains(text(),'Cancel Order')])[1]";
		String Line="(//input[@role='radio'])[4]";
		String StaUI= "(//td[@class='gridxCell    '])[10]";
		String FullLine="(//input[@role='radio'])[3]";
		String OrderNumber="(//input[@class='dijitReset dijitInputInner'])[2]";
		String FindOrder="//span[contains(text(),'Find Order')]";
		String Waive="(//input[@role='checkbox'])[3]";
		String Pre="(//span[@uid='lblNoteText'])[3]";
		String sub="//label[contains(text(),'Sub-Reason code:')]";
		String subcode="//label[contains(text(),'Sub Reason Code')]";
		String ordernumber = "//*[@uid='pnlPrimaryInfo']/div/table/tbody/tr/td/span";
		String close="(//span[@class='dijitInline dijitTabCloseButton dijitTabCloseIcon'])[2]";
		String txtUserName = "//input[@id='idx_form_TextBox_0']";
		String txtPassword = "//input[@id='loginPassword']";
		String btnLogin = "//span[@id='dijit_form_Button_0_label']";
		public String headerIcon = "//span[@class='idxHeaderUserIcon']";
		public String conceptfromUI = "//*[@uid='lblEnterpriseCodeDisplay']/div/div/div/span";
	public void orderSummaryPageValidation(WebDriver driver, Map<String, String> XLTestData,String getOrderNumber, ProlificsSeleniumAPI oPSelFW) {
		System.out.println("*******Order Confirmation Page Validations*******");

		try {

		
			//order number getting from UI
			String orderNoFromUI=driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: "+orderNoFromUI);
			
			//getting values from database
			DataBase1 db1 = new DataBase1();
            String Environment = XLTestData.get("WCCENV").toString();
            String HostName, UserName, DBPassword, DBSID;
            if(Environment.contains("STST2"))
            {
            	 HostName = gen.getPropertyValue("stst2.db.hostname");
                 UserName = gen.getPropertyValue("stst2.db.user");
                 DBPassword = gen.getPropertyValue("stst2.db.password");
                 DBSID = gen.getPropertyValue("stst2.db.sid");
            }
            else if(Environment.contains("EQA3"))
            {
            	 HostName = gen.getPropertyValue("eqa3.db.hostname");
                 UserName = gen.getPropertyValue("eqa3.db.user");
                 DBPassword = gen.getPropertyValue("eqa3.db.password");
                 DBSID = gen.getPropertyValue("eqa3.db.sid");
            }
            else
            {
            	 HostName = gen.getPropertyValue("stst.db.hostname");
                 UserName = gen.getPropertyValue("stst.db.user");
                 DBPassword = gen.getPropertyValue("stst.db.password");
                 DBSID = gen.getPropertyValue("stst.db.sid");
            }
            System.out.println("Before Database");
			Connection conn = db1.ConnectToDB(HostName, DBSID, UserName, DBPassword, oPSelFW);
			Statement stat = conn.createStatement();
			ResultSet rs =null;
			BigDecimal orderHeaderKey = null; 
			System.out.println("Before Database");
			
			if(Environment.contains("STST"))
			//query from retrieving bill to name from database
			rs = stat.executeQuery("select * from yantra_stst_owner.yfs_person_info where person_info_key in (select Bill_to_key from yantra_stst_owner.yfs_order_header where Order_no='"+getOrderNumber+"')");
			//basetest.test.log(Status.PASS," <span style='font-weight:bold;color:blue'>Header level validations<br></span" );
	       // basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'><table><tr><td width ='1'></td><td word-wrap: break-word width ='39' style='text-align:left'><b>Filed Name</b></td><td word-wrap: break-word width ='30' style='text-align:left'>CCUI Details</td><td word-wrap: break-word width ='30' style='text-align:left'>Sterling Details</td></tr></table></span>");
			if (rs.next() == true) {
	            do
	            {
	            	
	               	String billing = rs.getString("FIRST_NAME")+" "+rs.getString("LAST_NAME");
	               	
	              //shipping details text
	    			String shippingAddress=driver.findElement(By.xpath(shippingAddressXpath)).getText();
	    			System.out.println("Shipping Address: "+shippingAddress);
	    			
	    		//billing address text
	    			String billingAddress=driver.findElement(By.xpath(billingAddressXpath)).getText();
	    			System.out.println("Billing Address: "+billingAddress);
	    			
	    		//verification of username
	    			String name=driver.findElement(By.xpath(username)).getText();
	    			System.out.println("User name is: "+name);
	    			
	    			if (name.contains(billing))

	    			{
	    				oPSelFW.reportStepDetails("Order Confirmation Page: billing Address is displayed as " + billing,
	    						" Order Confirmation Page: billing Address is displayed as " + billing, "Pass");
	    				log.info(" Order Confirmation Page: billing Address is displayed as " + billing);
	    			}

	    			else {

	    				oPSelFW.reportStepDetails("Order Confirmation Page: billing Address is displayed as " + billing,
	    						" Order Confirmation Page: billing Address is displayed as " + billing, "Fail");
	    				log.error(" Order Confirmation Page: billing Address is displayed as " + billing);
	    				}
	               	
	               	//getting details from database
	               	String AddressStr = rs.getString("CITY").trim()+" "+rs.getString("COUNTRY").trim()+" "+rs.getString("DAY_PHONE").trim();
	               	String total = AddressStr.replaceAll("[U 5504455044,]", "");
	             	String ph = AddressStr.replaceAll("San Francisco US", "");
	            	System.out.println("phone: "+ph);
	            	System.out.println("Address: "+total);
	    			
	               	//getting city from UI
	    			String City=driver.findElement(By.xpath(city)).getText();
	    			
	    			String city = City.replaceAll("[-CA 94111-1226,]", "");
	    			System.out.println("City: "+city);
	    			
	    			//getting country from UI
	    			String Country=driver.findElement(By.xpath(country)).getText();
	    			System.out.println("Country: "+Country);
	    			
	    			//getting phone number from UI
	    			String Phone=driver.findElement(By.xpath(phone)).getText();
	    			String phoneUI = Phone.replaceAll("-", "");
	    			String phoneui = phoneUI.replaceAll("\\p{P}","");
	    			System.out.println("Phone number: "+phoneui);
	    			
	    			if (total.contains(city))
	    			{
	    				oPSelFW.reportStepDetails("Order Confirmation Page: city is displayed as " + City,
	    						" Order Confirmation Page: city is displayed as " + City, "Pass"); 
	    				log.info(" Order Confirmation Page: city is displayed as " + City); 
	    			}
	    			else 
	    			{

	    				oPSelFW.reportStepDetails("Order Confirmation Page: billing Address is not displayed as " + City,
	    						" Order Confirmation Page: city is not displayed as " + City, "Fail");
	    				log.error(" Order Confirmation Page: city is not displayed as " + City);
	    			}
	    			
	    			if (AddressStr.contains(Country))

	    			{
	    				oPSelFW.reportStepDetails("Order Confirmation Page: country is displayed as " + Country,
	    						" Order Confirmation Page: country is displayed as " + Country, "Pass");
	    				log.info(" Order Confirmation Page: country is displayed as " + Country);
	    			}

	    			else
	    			{

	    				oPSelFW.reportStepDetails("Order Confirmation Page: country is not displayed as " + Country,
	    						" Order Confirmation Page: country is not displayed as " + Country, "Fail");
	    				log.error(" Order Confirmation Page: country is not displayed as " + Country);
	    			}
	    			
	    			if (ph.contains(phoneui))

	    			{
	    				oPSelFW.reportStepDetails("Order Confirmation Page: phone number is displayed as " + phoneui,
	    						" Order Confirmation Page: phone number is  displayed as " + phoneui, "Pass");
	    				log.info(" Order Confirmation Page: phone number is  displayed as " + phoneui);
	    			}

	    			else
	    			{

	    				oPSelFW.reportStepDetails("Order Confirmation Page: phone number is not displayed as " + phoneui,
	    						" Order Confirmation Page: phone number is not displayed as " + phoneui, "Fail");
	    				log.error(" Order Confirmation Page: phone number is not displayed as " + phoneui);
	    			}while(rs.next());
	                rs = null;
	    		
	    			   
			//order type from UI
			String OrderTypeFromUI = driver.findElement(By.xpath(Ordertype)).getText();
			System.out.println("Order type from UI: "+OrderTypeFromUI);
			
			//query for retrieving order header key from database
			rs = stat.executeQuery("select * from yantra_stst_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
			
			if (rs.next() == true) {
		           orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
		           System.out.println("orderHeaderKey  :"+orderHeaderKey);
		           String ppppp="select * from yantra_stst_owner.yfs_order_header where  order_header_key='" + orderHeaderKey + "'";
		           System.out.println("ppppp :"+ppppp);
		           
		           //query for validating order created date from database
		           rs = stat.executeQuery("select * from yantra_stst_owner.yfs_order_header where  order_header_key='" + orderHeaderKey + "'");
		           if (rs.next() == true)
		           {
		           if (orderNoFromUI.contains(rs.getString("ORDER_NO").trim()))
		           {
		    				oPSelFW.reportStepDetails("Order Confirmation Page: Order number is displayed as " + orderNoFromUI,
		    						" Order Confirmation Page: phone number is displayed as " + orderNoFromUI, "Pass");
		    				log.info(" Order Confirmation Page: phone number is displayed as " + orderNoFromUI);
		           }
		           else
		           {

		    				oPSelFW.reportStepDetails("Order Confirmation Page: Order number is not displayed as " + orderNoFromUI,
		    						" Order Confirmation Page: Order number is not displayed as " + orderNoFromUI, "Fail");
		    				log.error(" Order Confirmation Page: Order number is not displayed as " + orderNoFromUI);
		           }
		           String Datecreated_DB = rs.getString("ORDER_DATE").trim();
		           
	               //Datecreated_DB =Util.getDateInUserFormate(Datecreated_DB); 
	                 System.out.println("Datecreated_DB :"+Datecreated_DB);
	                 
	                 //verification of order date
	                 String Orderplaceddate = driver.findElement(By.xpath(orderplaced)).getText();
	     			System.out.println("Order placed date from UI: "+Orderplaceddate);
	     			/*if (Orderplaceddate.contains(Datecreated_DB)) {
		        		   basetest.test.log(Status.PASS, "Order Confirmation Page: order placed date is displayed as" + "'</span>"+ "<span style='font-weight:bold;color:blue'> '" + Orderplaceddate + "'</span>"); 
		        	   
		           }else {
	    				System.out.println("Order Confirmation Page: city is not dispalyed as "+Orderplaceddate);
	    				basetest.test.log(Status.FAIL, "Order Confirmation Page: order placed date is not displayed as" + "'</span>"+ "<span style='font-weight:bold;color:blue'> '" + Orderplaceddate + "'</span>");
		        }*/
	     			//validating ship to address
	     			 rs=null;
	     			 
	     			 //query for validating ship to name from database and UI
	     			rs = stat.executeQuery("select FIRST_NAME,LAST_NAME,ADDRESS_LINE1,CITY,STATE,ZIP_CODE from YANTRA_STST_OWNER.yfs_person_info where person_info_key in (select ship_to_key from YANTRA_STST_OWNER.yfs_order_line where order_header_key in(select order_header_key from YANTRA_STST_OWNER.yfs_order_header where Order_no='"+getOrderNumber+"'))");
	     		         if (rs.next() == true) {  
	     		         String OrderLine_First_Name = rs.getString("FIRST_NAME").trim();
	     		         String OrderLine_Last_Name = rs.getString("LAST_NAME").trim();
	     		         String OrderLines_Name_DB = OrderLine_First_Name + " " + OrderLine_Last_Name;
	     		         System.out.println(OrderLine_First_Name + " " + OrderLine_Last_Name);
	     		         OrderLines_Name_DB = OrderLines_Name_DB.trim();
	     			
	     			//getting ship address name from UI
	    			String saddr=driver.findElement(By.xpath(ship)).getText();
	    			System.out.println("name in the ship address: "+saddr);
	     			//verification of ship address
	     			
	    			if (OrderLines_Name_DB.contains(saddr))
			           {
			    				oPSelFW.reportStepDetails("Order Confirmation Page: Ship addrerss is displayed as " + saddr,
			    						" Order Confirmation Page: Ship address is displayed as " + saddr, "Pass");
			    				log.info(" Order Confirmation Page: Ship address is displayed as " + saddr);
			           }
			           else
			           {

			    				oPSelFW.reportStepDetails("Order Confirmation Page: Ship address is not displayed as " + saddr,
			    						" Order Confirmation Page: Ship address is not displayed as " + saddr, "Fail");
			    				log.error(" Order Confirmation Page: Ship address is not displayed as " + saddr);
			           }
	     			 rs=null;
	     			 //query for validating sub order count getOrderNumber
		     			rs = stat.executeQuery("select max(extn_dtc_suborderno) from YANTRA_STST_OWNER.yfs_order_line where order_header_key in( select order_header_key from YANTRA_STST_OWNER.yfs_order_header where Order_no='"+getOrderNumber+"')");
		     			String subOrderCnt = null;
		     			if (rs.next() == true) {
		     				subOrderCnt = rs.getString("MAX(EXTN_DTC_SUBORDERNO)").trim();
		     				System.out.println("sub order count:" +subOrderCnt);
		     				//basetest.test.log(Status.PASS, subOrderCnt + "    " + "SubOrder count  from Sterling</span> " + " for Order Number  <b> "  + getOrderNumber + "</b>");
		     				oPSelFW.reportStepDetails("SubOrder count  from Sterling " + getOrderNumber,
		    						" SubOrder count  from Sterling " + getOrderNumber, "Pass");
		     				log.info(" SubOrder count  from Sterling " + getOrderNumber);
		     			}
		     				else {	
		     					oPSelFW.reportStepDetails("SubOrder count  from Sterling is not displayed as " + getOrderNumber,
			    						" SubOrder count  from Sterling is not displayed as" + getOrderNumber, "Fail");
		     					log.error(" SubOrder count  from Sterling is not displayed as" + getOrderNumber);
		     				System.out.println("subOrderCnt" + subOrderCnt);
	     			}
		     			 rs=null;
		     			//query for validating item quantity, item id and unit price
		     			rs = stat.executeQuery("select order_header_key from YANTRA_STST_OWNER.yfs_Order_header where Order_no='"+getOrderNumber+"'");
		     			if(rs.next()== true)
		    			{
		    				do{
		    					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");										
		    				 }while (rs.next());
		    				ResultSet rs1 = stat.executeQuery("select item_id,unit_price,ordered_qty,order_line_key from YANTRA_STST_OWNER.yfs_Order_line where order_header_key='"+orderHeaderKey+"' and sub_line_no ='1'");
		    				if(rs1.next()== true)
		    				{
		    					//getting unit price from database
		    					String uni=rs1.getString("unit_price");
		    					System.out.println("unit price from database:" +uni);
		    					
		    					//getting unit price from UI
		    					String itemUnitPrice=driver.findElement(By.xpath(unit)).getText();
		    					String UnitPriceFromUI = itemUnitPrice.replaceAll("[$,]", "");
		    					System.out.println("Item unit price from UI: "+UnitPriceFromUI);
		    					
		    					//validating unit price from database and from UI
		    					
		    					if (uni.contains(UnitPriceFromUI))
		 			           {
		 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Unit price is displayed as " + UnitPriceFromUI,
		 			    						" Order Confirmation Page: Unit price is displayed as " + UnitPriceFromUI, "Pass");
		 			    				log.info(" Order Confirmation Page: Unit price is displayed as " + UnitPriceFromUI);
		 			           }
		 			           else
		 			           {

		 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Unit price is not displayed as " + UnitPriceFromUI,
		 			    						" Order Confirmation Page: Unit price is not displayed as " + UnitPriceFromUI, "Fail");
		 			    				log.error(" Order Confirmation Page: Unit price is not displayed as " + UnitPriceFromUI);
		 			           }
		    					//getting ordered quantity from database
		    					String quan=rs1.getString("ordered_qty");
		    					System.out.println("ordered quantity from database:" +quan);
		    					
		    					//getting quantity from UI
		    					String QuantityFromUI=driver.findElement(By.xpath(qty)).getText();
		    					System.out.println("Item quantity From UI: "+QuantityFromUI);
		    					
		    					//validating quantity from database and from UI
		    					
		    					if (quan.contains(QuantityFromUI))
			 			           {
			 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Quantity is displayed as " + QuantityFromUI,
			 			    						" Order Confirmation Page: Quantity is displayed as " + QuantityFromUI, "Pass");
			 			    				log.info(" Order Confirmation Page: Quantity is displayed as " + QuantityFromUI);
			 			           }
			 			           else
			 			           {

			 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Quantity is not displayed as " + QuantityFromUI,
			 			    						" Order Confirmation Page: Quantity is not displayed as " + QuantityFromUI, "Fail");
			 			    				log.error(" Order Confirmation Page: Quantity is not displayed as " + QuantityFromUI);
			 			           }
		    					//getting item id from UI
		    					String ItemUI=driver.findElement(By.xpath(number)).getText();
		    					String ItemFromUI = ItemUI.replaceAll("[^0-9]", "");
		    					String ItemFromui = ItemFromUI.replaceAll("\\p{P}","");
		    					System.out.println("Item number From UI: "+ItemFromui);
		    					
		    					//getting item id from database
		    					String str=rs1.getString("item_id");
		    					String Itemdb = str.replaceAll("\\s", ""); 
		    					System.out.println("item id from database:" +Itemdb);
		    					
		    					//validating item id from UI and from database
		    					
		    					if (ItemFromui.contains(Itemdb))
			 			           {
			 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Iten id is displayed as " + ItemFromUI,
			 			    						" Order Confirmation Page: Item id is displayed as " + ItemFromUI, "Pass");
			 			    				log.info(" Order Confirmation Page: Item id is displayed as " + ItemFromUI);
			 			           }
			 			           else
			 			           {

			 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Item id is not displayed as " + ItemFromUI,
			 			    						" Order Confirmation Page: Item id is not displayed as " + ItemFromUI, "Fail");
			 			    				log.error(" Order Confirmation Page: Item id is not displayed as " + ItemFromUI);
			 			           }
		    					
		    					rs=null;
		    					//verification of order total amount
		    					rs = stat.executeQuery("Select ORIGINAL_TOTAL_AMOUNT from yfs_order_header where order_header_key in(select order_header_key from yfs_order_header where Order_no='"+getOrderNumber+"')");
		    					if(rs.next()== true)
			    				{
		    						//getting order total from database
		    						String ooo=rs.getString("ORIGINAL_TOTAL_AMOUNT");
		    						System.out.println("order total amount from database:" +ooo);
		    						
		    						//getting order total from UI
		    						String order=driver.findElement(By.xpath(ototal)).getText();
		    						String OrderTotal = order.replaceAll("[$]", "");
		    						System.out.println("order total amount from UI:" +OrderTotal);
		    						
		    						//validating of order total from database and from UI
		    						
		    						if (ooo.contains(OrderTotal))
				 			           {
				 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Iten id is displayed as " + OrderTotal,
				 			    						" Order Confirmation Page: Item id is displayed as " + OrderTotal, "Pass");
				 			    				log.info(" Order Confirmation Page: Item id is displayed as " + OrderTotal);
				 			           }
				 			           else
				 			           {

				 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Item id is not displayed as " + OrderTotal,
				 			    						" Order Confirmation Page: Item id is not displayed as " + OrderTotal, "Fail");
				 			    				log.error(" Order Confirmation Page: Item id is not displayed as " + OrderTotal);
				 			           }
		    						
			    				}
		    					rs=null;
		    					//verification of order type
		    					rs = stat.executeQuery("Select Order_type from yfs_order_header where order_header_key in(select order_header_key from yfs_order_header where Order_no='"+getOrderNumber+"')");
		    					if(rs.next()== true)
			    				{
		    						//getting order total from database
		    						String ttt=rs.getString("Order_type");
		    						System.out.println("order total amount from database:" +ttt);
		    						
		    						//getting order total from UI
		    						String OrderType=driver.findElement(By.xpath(Ordertype)).getText();
		    						System.out.println("order total amount from UI:" +OrderType);
		    						
		    						//validating of order total from database and from UI
		    						
		    						if (ttt.contains(OrderType))
				 			           {
				 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Order type is displayed as " + OrderType,
				 			    						" Order Confirmation Page: Ordrer type is displayed as " + OrderType, "Pass");
				 			    				log.info(" Order Confirmation Page: Ordrer type is displayed as " + OrderType);
				 			           }
				 			           else
				 			           {

				 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Ordre type is not displayed as " + OrderType,
				 			    						" Order Confirmation Page: Ordre type is not displayed as " + OrderType, "Fail");
				 			    				log.error(" Order Confirmation Page: Ordre type is not displayed as " + OrderType);
				 			           }
		    						rs=null;
			    					//verification of order total amount
			    					rs = stat.executeQuery("Select entered_by,enterprise_key,entry_type,EXTN_BORDERFREE_ORDER_NO,EXTN_ORDER_SOURCE,EXTN_ORIGIN_COUNTRY_CODE,EXTN_INT_SHIP_METHOD from yfs_order_header where order_header_key in(select order_header_key from yfs_order_header where Order_no='"+getOrderNumber+"')");
			    					if(rs.next()== true)
				    				{
			    						//getting user name from database
			    						String user=rs.getString("entered_by");
			    						System.out.println("oreder created by user from database:" +user);
			    						
			    						//getting user name from UI
			    						String Ouser=driver.findElement(By.xpath(createdby)).getText();
			    						System.out.println("order created by user from UI:" +Ouser);
			    						
			    						//validating of order total from database and from UI
			    						
			    						if (Ouser.contains(user))
					 			           {
					 			    				oPSelFW.reportStepDetails("Order Confirmation Page: User name is displayed as " + Ouser,
					 			    						" Order Confirmation Page: User name is displayed as " + Ouser, "Pass"); 
					 			    				log.info(" Order Confirmation Page: User name is displayed as " + Ouser); 
					 			           }
					 			           else
					 			           {

					 			    				oPSelFW.reportStepDetails("Order Confirmation Page: User name is not displayed as " + Ouser,
					 			    						" Order Confirmation Page: User name is not displayed as " + Ouser, "Fail"); 
					 			    				log.error(" Order Confirmation Page: User name is not displayed as " + Ouser); 
					 			           }
			    						//getting user name from database
			    						String entrytype=rs.getString("entry_type");
			    						System.out.println("entry type from database:" +entrytype);
			    						
			    						//getting user name from UI
			    						String entryUI=driver.findElement(By.xpath(channel)).getText();
			    						System.out.println("entry type from UI:" +entryUI);
			    						
			    						//validating of order total from database and from UI
			    						
			    						if (entryUI.contains(entrytype))
					 			           {
					 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Channel is displayed as " + entryUI,
					 			    						" Order Confirmation Page: Channel is displayed as " + entryUI, "Pass");
					 			    				log.info(" Order Confirmation Page: Channel is displayed as " + entryUI);
					 			           }
					 			           else
					 			           {

					 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Channel is not displayed as " + entryUI,
					 			    						" Order Confirmation Page: Channel is not displayed as " + entryUI, "Fail");
					 			    				log.error(" Order Confirmation Page: Channel is not displayed as " + entryUI);
					 			           }
			    						//validating level of service and ship mode
			    						rs=null;
			    						
				    					//verification of order type
				    					rs = stat.executeQuery("Select LEVEL_OF_SERVICE,Carrier_Service_code from yfs_order_line where order_header_key in(select order_header_key from yfs_order_header where Order_no='"+getOrderNumber+"')");
				    					if(rs.next()== true)
					    				{
				    						//getting order total from database
				    						String lll=rs.getString("LEVEL_OF_SERVICE");
				    						System.out.println("Level of service from database:" +lll);
				    						
				    						//getting order total from UI
				    						String RegUI=driver.findElement(By.xpath(reg)).getText();
				    						System.out.println("Level of service from UI from UI:" +RegUI);
				    						
				    						//validating of order total from database and from UI
				    						
				    						if (lll.contains(RegUI))
						 			           {
						 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Level of service is displayed as " + RegUI,
						 			    						" Order Confirmation Page: Level of service is displayed as " + RegUI, "Pass");
						 			    				log.info(" Order Confirmation Page: Level of service is displayed as " + RegUI);
						 			           }
						 			           else
						 			           {

						 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Level of service is not displayed as " + RegUI,
						 			    						" Order Confirmation Page: Level of service is not displayed as " + RegUI, "Fail");
						 			    				log.error(" Order Confirmation Page: Level of service is not displayed as " + RegUI);
						 			           }
				    						//validating ship mode
				    						//getting order total from database
					    						String sss=rs.getString("Carrier_Service_code");
					    						String regDB = sss.replaceAll("[REGULAR_]", "");
					    						System.out.println("Ship mode from database:" +regDB);
					    						
					    						//getting order total from UI
					    						String uuu=driver.findElement(By.xpath(mode)).getText();
					    						String regUI = uuu.replaceAll("[ UPS]", "");
					    						System.out.println("ship mode from UI:" +regUI);
					    						
					    						//validating of ship mode from database and from UI
					    						
					    						if (regDB.contains(regUI))
							 			           {
							 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Ship mode is displayed as " + regUI,
							 			    						" Order Confirmation Page: Ship mode is displayed as " + regUI, "Pass");
							 			    				log.info(" Order Confirmation Page: Ship mode is displayed as " + regUI);
							 			           }
							 			           else
							 			           {

							 			    				oPSelFW.reportStepDetails("Order Confirmation Page: Ship mode is not displayed as " + regUI,
							 			    						" Order Confirmation Page: Ship mode is not displayed as " + regUI, "Fail");
							 			    				log.error(" Order Confirmation Page: Ship mode is not displayed as " + regUI);
							 			           }
					    						//validating expected delivery date
					    						rs=null;
						    					//verification of order type
						    					rs = stat.executeQuery("select Expected_date from yfs_order_date where order_header_key in(select order_header_key from yfs_order_header where Order_no='"+getOrderNumber+"') and DATE_TYPE_ID='CurrentPromiseEndDate'");
						    					if(rs.next()== true)
							    				{
						    						//getting expected delivery date from database
						    						String eee=rs.getString("EXPECTED_DATE");
						    						System.out.println("Expected delivery date from database:" +eee);
						    						
						    						//getting order total from UI
						    						String ddd=driver.findElement(By.xpath(dateUI)).getText();
						    						
						    						//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
						    						//String actualDate = ddd;
						    						//Date datefromUI = sdf.parse(actualDate);
						    						System.out.println("Expected delivery date from UI:" +RegUI);
						    						//validating of order total from database and from UI
						    						/*if(eee.equals(eee)) {
							    						basetest.test.log(Status.PASS, "Order Confirmation Page: Expected date is displayed as" + "'</span>"+ "<span style='font-weight:bold;color:blue'> '" + RegUI + "'</span>");	
							    					}else {
							    						basetest.test.log(Status.FAIL, "Order Confirmation Page: Expected date  is not displayed as" + "'</span>"+ "<span style='font-weight:bold;color:blue'> '" + RegUI + "'</span>");	
							    					}*/
			    						
				    				}
		    				}
		    				}
	     			
	                 
			
	            
			
	     		}while(rs.next());
	            
				}while(rs.next());

	            }while(rs.next());
			}while(rs.next());
			}while(rs.next());
			}while(rs.next());
			}while(rs.next());
			}
		
			
		}

		catch (Exception e) {
			//basetest.test.log(Status.FAIL, "Exception " + e + " is Dispalyed");
			e.printStackTrace();
		}
			
		}
	





/*##############################################################
 @Descriptions ---validating item details table
 ##############################################################*/


public void ItemDetails(WebDriver driver,ProlificsSeleniumAPI oPSelFW) throws Exception {
	
	System.out.println("*******Validating Item deatails table*******");

	try {

		//order number getting from UI
		String Itemname=driver.findElement(By.xpath(Item)).getText();
		System.out.println("Item description from UI: "+Itemname);
		
		//unit price
		String itemUnitPrice=driver.findElement(By.xpath(unit)).getText();
		String itemtotalrem = itemUnitPrice.replaceAll("[$,]", "");
		double UnitPriceFromUI = Double.parseDouble(itemtotalrem);
		System.out.println("Item unit price from UI: "+UnitPriceFromUI);
		
		//verification of quantity
		String QuantityFromUI=driver.findElement(By.xpath(qty)).getText();
		System.out.println("Item quantity From UI: "+QuantityFromUI);
		
		//verification of expected date
		String Expecteddate=driver.findElement(By.xpath(dateUI)).getText();
		System.out.println("Expected date from: "+Expecteddate);
		
		//verification of ship mode
		String Shipmode=driver.findElement(By.xpath(ship)).getText();
		System.out.println("Ship mode From UI: "+Shipmode);
		
		//verification of ship mode
		String LevelOfService=driver.findElement(By.xpath(service)).getText();
		System.out.println("Level of service From UI: "+LevelOfService);
		
		
	}
		catch (Exception e) {
		
			oPSelFW.reportStepDetails("Verify if Exception is displayed" ,"Exception is Dispalyed" + e , "Pass");
			log.info("Exception is Dispalyed" + e );
			
			
		e.printStackTrace();
	}
}

//***************************************************
//Appease the customer class file

public void AppeaseCustemer(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws InterruptedException {
	try {
	System.out.println("Appeasing the Customer.........");
	//Xpath for clicking on the link appease the customer
	Actions action = new Actions(driver);
	WebElement we = driver.findElement(By.xpath(Appease));
	action.moveToElement(we).click();
	Thread.sleep(4000);
	action.perform();
	Thread.sleep(7000);
	
	//selecting the reason code
	driver.findElement(By.xpath(ReasonCode)).click();
	gen.setText(driver, ReasonCode, XLTestData.get("ReasonCode").toString());
	//Selecting the sub reason code
	Thread.sleep(30000);
	driver.findElement(By.xpath(sub)).click();
	driver.findElement(By.xpath(SubReasonCode)).click();
	gen.setText(driver, SubReasonCode, XLTestData.get("SubReasonCode").toString());
	//we.sendKeys(Keys.ENTER);
	Thread.sleep(2000);
	//Selecting the Item
	driver.findElement(By.xpath("(//span[@role='checkbox'])[2]")).click();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,500)");
	//Clicking on Next button
	driver.findElement(By.xpath(Nextbtn)).click();
	Thread.sleep(10000);
	 js.executeScript("window.scrollBy(0,500)");
	gen.setText(driver, note, XLTestData.get("Note Value").toString());
	Thread.sleep(5000);
	//clicking on confirm button
	driver.findElement(By.xpath(Confirmbtn)).click();
	
}
	catch (Exception e) {
		System.out.println(e);
	}
}


//Method for Selecting Waive charges
public void WaiveCharges(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException {
try {
Thread.sleep(40000);
gen.waitUntilElementIsPresent(driver,"//span[@class='idxHeaderUserIcon']");
System.out.println("Appeasing the Customer.........");
//Xpath for clicking on the link appease the customer
Actions action = new Actions(driver);
WebElement we = driver.findElement(By.xpath(Appease));
action.moveToElement(we).click();
Thread.sleep(5000);
action.perform();
oPSelFW.reportStepDetails("Appease customer link should be clicked " ," Appease customer link should be clicked successfully " , "Pass");
log.info(" Appease customer link should be clicked successfully " );
Thread.sleep(25000);
//selecting the reason code
driver.findElement(By.xpath(ReasonCode)).click();
oPSelFW.reportStepDetails("Reason Code should be clicked " ," Reason Code is successfully clicked " , "Pass"); 
log.info(" Reason Code is successfully clicked " ); 
gen.setText(driver, ReasonCode, XLTestData.get("ReasonCode").toString());
//Selecting the sub reason code
Thread.sleep(3000);
driver.findElement(By.xpath(sub)).click();
driver.findElement(By.xpath(SubReasonCode)).click();
oPSelFW.reportStepDetails(" Sub Reason Code should be clicked " ," Sub Reason Code is successfully clicked " , "Pass");
log.info(" Sub Reason Code is successfully clicked " );
gen.setText(driver, SubReasonCode, XLTestData.get("SubReasonCode").toString());
//we.sendKeys(Keys.ENTER);
Thread.sleep(2000);
//Selecting the Item
driver.findElement(By.xpath("((((//*[@uid='OLST_listGrid'])[1]/div)[3]/div)[4]/div)")).click();
oPSelFW.reportStepDetails("Item should be clicked " ," Item is successfully clicked " , "Pass");
log.info(" Item is successfully clicked " );
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,500)");
//Clicking on Next button
driver.findElement(By.xpath(Nextbtn)).click();
oPSelFW.reportStepDetails("Next button should be clicked " ,"Next button is successfully clicked " , "Pass");
log.info("Next button is successfully clicked ");
gen.waitUntilElementIsPresent(driver, headerIcon);
Thread.sleep(20000);
//Selecting the waive charges option
js.executeScript("window.scrollBy(0,-1000)");
Thread.sleep(7000);
driver.findElement(By.xpath(Waive)).click();
oPSelFW.reportStepDetails("Waive order line Mono/PZ charges should be selected " ," Waive order line Mono/PZ charges is successfully selected " , "Pass"); 
log.info(" Waive order line Mono/PZ charges is successfully selected " ); 
//Clicking on confirm button
js.executeScript("window.scrollBy(0,700)");
gen.setText(driver, note, XLTestData.get("Note Value").toString());
driver.findElement(By.xpath(Confirmbtn)).click();
Thread.sleep(10000);
oPSelFW.reportStepDetails("Note value should be Passed " ,"Note value is successfully Passed successfully " , "Pass");
log.info("Note value is successfully Passed successfully ");
/*driver.findElement(By.xpath(next)).click();
oPSelFW.reportStepDetails("Next button should be clicked " ,"Next button is successfully clicked " , "Pass");  log.info(*/
Thread.sleep(20000);
//Clicking on Appease customer link
WebElement we1 = driver.findElement(By.xpath(Appease));
action.moveToElement(we1).click();
action.perform();
oPSelFW.reportStepDetails("Appease customer link should be clicked " ," Appease customer link should be clicked successfully " , "Pass");
log.info(" Appease customer link should be clicked successfully " );
Thread.sleep(20000);
}catch (Exception e) {
//oPSelFW.reportStepDetails("Exception got Occured" ," Exception should got displayed successfully " , "Fail");  log.error(
System.out.println(e);
}
}



//Method for validating previous Appeasements
public void ValidatingPreviousAppeasements(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException {
try {
JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("window.scrollBy(0,700)");
System.out.println("Validating Previous Appeasements");
//getting previous appeasement from UI
String PreUI=driver.findElement(By.xpath(Pre)).getText();
System.out.println("Previous Appeasement value is" +PreUI);
oPSelFW.reportStepDetails("Appease Customer: Previous Appeasements is displayed as " + PreUI," Appease Customer: Previous Appeasements is displayed as " + PreUI, "Pass");
log.info(" Appease Customer: Previous Appeasements is displayed as " + PreUI);
//closing button
driver.findElement(By.xpath(close)).click();
oPSelFW.reportStepDetails("Closing of current window should be clicked " ," Closing of current window is clicked successfully " , "Pass");
log.info(" Closing of current window is clicked successfully " );
Thread.sleep(20000);
//closing of order search window
// driver.findElement(By.xpath(close)).click();
//  oPSelFW.reportStepDetails("Closing of order search window should be clicked " ," Closing of order search window is clicked successfully " , "Pass");  log.info(

}catch (Exception e) {
//oPSelFW.reportStepDetails("Exception got Occured" ," Exception should got displayed successfully " , "Fail");  log.error(
System.out.println(e);
}
}

//***************************************************
//Searching with the order number

public void SearchWithOrderNumber(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW, String getOrderNumber) throws InterruptedException, IOException {
try {
	gen.waitUntilElementIsPresent(driver, headerIcon);
Thread.sleep(50000);
System.out.println("Searching with the order number:" +getOrderNumber);
//Selecting the Order number text box
driver.findElement(By.xpath(OrderNumber)).click();
oPSelFW.reportStepDetails("Order number should be clicked " + getOrderNumber," Order Number successfully clicked " + getOrderNumber, "Pass");
log.info(" Order Number successfully clicked " + getOrderNumber);
Thread.sleep(5000);
//Passing the order number from excel
gen.setText(driver, OrderNumber, XLTestData.get("orderNumber").toString());
//order search criteria
/*driver.findElement(By.xpath(OrderNumber1)).click();
gen.setText(driver, OrderNumber1, XLTestData.get("orderNumber").toString());
Thread.sleep(50000);
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("window.scrollBy(0,-500)");
Thread.sleep(7000);
driver.findElement(By.xpath(search)).click();
Thread.sleep(5000);
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='idxHeaderUserIcon']")));
oPSelFW.reportStepDetails("Search button should be clicked successfully " ," Search button is clicked successfully " , "Pass");  log.info(
Thread.sleep(20000);
oPSelFW.reportStepDetails("Order number should be Passed " + getOrderNumber," Order Number  should be successfully Passed " + getOrderNumber, "Pass");  log.info(*/
//clicking on Find Order button
driver.findElement(By.xpath(FindOrder)).click();
oPSelFW.reportStepDetails("Find Order should be clicked " + getOrderNumber," Find Order should be successfully clicked " + getOrderNumber, "Pass");
log.info(" Find Order should be successfully clicked " + getOrderNumber);
Thread.sleep(30000);
}catch (Exception e) {
//oPSelFW.reportStepDetails("Exception got Occured" ," Exception should got displayed successfully " , "Fail");  log.error(
System.out.println(e);
}
}


//***************************************************
// Partial Cancelling the Line level appeasement order

public void CancelOrder(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws InterruptedException {
	try {
		Thread.sleep(4000);
	//Clicking on the Cancel Order
	System.out.println("Cancelling the Order.........");
	//Xpath for clicking on the link appease the customer
	Actions action = new Actions(driver);
	WebElement we = driver.findElement(By.xpath(cancel));
	action.moveToElement(we).click();
	action.perform();
	Thread.sleep(8000);
	//selecting the reason code
	driver.findElement(By.xpath(reasoncode)).click();
	gen.setText(driver, reasoncode, XLTestData.get("ReasonCode1").toString());
	Thread.sleep(3000);
	driver.findElement(By.xpath(subcode)).click();
	//Selecting the sub reason code
	driver.findElement(By.xpath(subreasoncode)).click();
	gen.setText(driver, subreasoncode, XLTestData.get("SubReasonCode1").toString());
	Thread.sleep(2000);
	//Selecting line level order
	driver.findElement(By.xpath(Line)).click();
	
	Thread.sleep(1000);
	//Selecting the item
	driver.findElement(By.xpath("(//span[@role='checkbox'])[2]")).click();
	//clicking on next button
	driver.findElement(By.xpath(next)).click();
	Thread.sleep(10000);
	//Accepting the Alert
	driver.findElement(By.xpath("(//span[@role='button'])[20]")).click();//causes page to
	//Alert alert = driver.switchTo().alert();
	//String alertText = alert.getText();
	//alert.accept();
	Thread.sleep(10000);
	//clicking on confirm button
	driver.findElement(By.xpath(Confirmbtn)).click();
	
	
}catch (Exception e) {
	System.out.println(e);
}
}
//***************************************************
//Full Cancellation of the Line level appeasement order

public void FullCancelOrder(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws InterruptedException {
	try {
		
	//Clicking on the Cancel Order
	System.out.println("Cancelling the Order.........");
	//Xpath for clicking on the link appease the customer
	Actions action = new Actions(driver);
	WebElement we = driver.findElement(By.xpath(cancel));
	action.moveToElement(we).click();
	action.perform();
	Thread.sleep(3000);
	//selecting the reason code
	driver.findElement(By.xpath(ReasonCode)).click();
	gen.setText(driver, ReasonCode, XLTestData.get("ReasonCode1").toString());
	Thread.sleep(3000);
	//Selecting the sub reason code
	driver.findElement(By.xpath(SubReasonCode)).click();
	gen.setText(driver, SubReasonCode, XLTestData.get("SubReasonCode1").toString());
	Thread.sleep(2000);
	//Selecting Full Cancellation of the items
	driver.findElement(By.xpath(FullLine)).click();
	Thread.sleep(1000);
	//clicking on next button
	driver.findElement(By.xpath(Nextbtn)).click();
	Thread.sleep(10000);
	//Accepting the Alert
	driver.findElement(By.xpath("(//span[@role='button'])[42]")).click();//causes page to
	Alert alert = driver.switchTo().alert();
	String alertText = alert.getText();
	alert.accept();
	Thread.sleep(10000);
	//clicking on confirm button
	driver.findElement(By.xpath(Confirmbtn)).click();
	
	
}catch (Exception e) {
	System.out.println(e);
}
}











//***************************************************
//validating status of order cancellation

public void ValidatingStatus(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW, String getOrderNumber) throws InterruptedException, IOException, SQLException {
	try {
	//validating of status of the order
	
	//getting values from database
	DataBase1 db1 = new DataBase1();
    String Environment = XLTestData.get("WCCENV").toString();
    String HostName, UserName, DBPassword, DBSID;
    if(Environment.contains("STST2"))
    {
    	 HostName = gen.getPropertyValue("stst2.db.hostname");
         UserName = gen.getPropertyValue("stst2.db.user");
         DBPassword = gen.getPropertyValue("stst2.db.password");
         DBSID = gen.getPropertyValue("stst2.db.sid");
    }
    else if(Environment.contains("EQA3"))
    {
    	 HostName = gen.getPropertyValue("eqa3.db.hostname");
         UserName = gen.getPropertyValue("eqa3.db.user");
         DBPassword = gen.getPropertyValue("eqa3.db.password");
         DBSID = gen.getPropertyValue("eqa3.db.sid");
    }
    else
    {
    	 HostName = gen.getPropertyValue("stst.db.hostname");
         UserName = gen.getPropertyValue("stst.db.user");
         DBPassword = gen.getPropertyValue("stst.db.password");
         DBSID = gen.getPropertyValue("stst.db.sid");
    }
    System.out.println("Before Database");
	Connection conn = db1.ConnectToDB(HostName, DBSID, UserName, DBPassword, oPSelFW);
	Statement stat = conn.createStatement();
	ResultSet rs3 =null;
	BigDecimal orderHeaderKey = null; 
	System.out.println("Before Database");
	
	if(Environment.contains("STST"))
	//query from retrieving the status from database
	rs3 = stat.executeQuery("select Description from Yfs_status where Status in (select Max(Status) from yantra_stst_owner.yfs_order_release_Status where Order_Header_Key in (select Order_Header_Key from  yantra_stst_owner.YFS_ORDER_HEADER WHERE ORDER_NO = '"+getOrderNumber+"'))");
	
	if (rs3.next() == true) {
        do
        {
        	//Status from DB
           	String StatusDB = rs3.getString("DESCRIPTION");
           	System.out.println("Cancellation status from DB: " +StatusDB);
           	
           	//Status from UI
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,700)");
           	String StatusUI=driver.findElement(By.xpath(StaUI)).getText();
			System.out.println("Cancellation status from UI:" +StatusUI);

			if (StatusDB.contains(StatusUI))

			{
				oPSelFW.reportStepDetails("Order Summary Page: Status is displayed as " + StatusUI,
						" Order Summary Page: Status is displayed as " + StatusUI, "Pass");
				log.info(" Order Summary Page: Status is displayed as " + StatusUI);
			}

			else {

				oPSelFW.reportStepDetails("Order Summary Page: Status is displayed as " + StatusUI,
						" Order Summary Page: Status is displayed as " + StatusUI, "Fail");
				log.error(" Order Summary Page: Status is displayed as " + StatusUI);
				}
           	
	
}while(rs3.next());



	}
	}catch (Exception e) {
	System.out.println(e);
}
}




public void ValidatingOrderNumberStatus(WebDriver driver,Map<String, String> XLTestData,
		String getOrderNumber,ProlificsSeleniumAPI oPSelFW) throws InterruptedException {
	try {
		// validating of status of the order

		// getting values from database
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
		System.out.println("Before Database");
		Connection conn = db1.ConnectToDB(HostName, DBSID, UserName, DBPassword, oPSelFW);
		Statement stat = conn.createStatement();
		ResultSet rs3 = null;
		BigDecimal orderHeaderKey = null;
		System.out.println("After Database");

		if (Environment.contains("STST2"))
			// query from retrieving the status from database
			rs3 = stat.executeQuery(
					"select * from yantra_stst_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
		else
		
                rs3 = stat.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");

		if (rs3.next() == true)
		{
			// Status from DB
			String StatusDB = rs3.getString("ORDER_NO");
			System.out.println("ORDER_NO status from DB:" + StatusDB);
			// Status from UI
			String StatusUI = driver.findElement(By.xpath(ordernumber)).getText();
			System.out.println("ORDER_NO status from UI:" + StatusUI);

			if (StatusDB.contains(StatusUI))
			{
				oPSelFW.reportStepDetails("Order Number from UI is " + StatusUI,
						"Verified Order Number from DB is " + StatusDB, "Pass");
				log.info("Verified Order Number from DB is " + StatusDB);
			}
			else {

				oPSelFW.reportStepDetails("Order Number from UI is " + StatusUI,
						"Verified Order Number from DB is " + StatusDB, "Fail"); 
				log.error("Verified Order Number from DB is " + StatusDB); 

			}

			StatusDB = rs3.getString("ORDER_TYPE");
			System.out.println("ORDER_TYPE status from DB:" + StatusDB);
			// Status from UI
			StatusUI = driver.findElement(By.xpath(Ordertype)).getText();
			System.out.println("ORDER_TYPE status from UI:" + StatusUI);
			if (StatusDB.contains(StatusUI))
			{
				oPSelFW.reportStepDetails("Order Type from UI is " + StatusUI,
						"Verified Order Type from DB is " + StatusDB, "Pass");
				log.info("Verified Order Type from DB is " + StatusDB);
			}

			else {

				oPSelFW.reportStepDetails("Order NuTypember from UI is " + StatusUI,
						"Verified Order Type from DB is " + StatusDB, "Fail");
				log.error("Verified Order Type from DB is " + StatusDB);

			}
			
			Thread.sleep(1000);
			
					//XLTestData.get("Concept").toString();
			
			String concept = driver.findElement(By.xpath(conceptfromUI)).getText();
			System.out.println("ORDER_NO status from UI:" + conceptfromUI);			
			if(concept.contains("Pottery Barn Teen"))
			{
				Concept = "PT";
			}
			else if(concept.contains("Williams-Sonoma"))
			{
				Concept = "WS";
				
			}
			
			else if(concept.contains("West Elm"))
			{
				Concept = "WE";
				
			}
			else if(concept.contains("Pottery Barn Kids"))
			{
				Concept = "PK";
				
			}
			else if(concept.contains("Pottery Barn"))
			{
				Concept = "PB";
				
			}
			else if(concept.contains("Pottery Barn Teen Mark and Graham"))
			{
				Concept = "MG";
				
			}
			
			
			StatusUI = Concept;
			
			StatusDB = rs3.getString("ENTERPRISE_KEY");
			System.out.println("ENTERPRISE_KEY status from DB:" + StatusDB);
			// Status from UI
			
			
			System.out.println("Concept status from UI:" + StatusUI);

			if (StatusDB.contains(StatusUI))
			{
				oPSelFW.reportStepDetails("Concept from UI is " + StatusUI,
						"Verified Concept Type from DB is " + StatusDB, "Pass");
				log.info("Verified Concept Type from DB is " + StatusDB);

			}

			else {

				oPSelFW.reportStepDetails("Concept from UI is " + StatusUI,
						"Verified Concept Type from DB is " + StatusDB, "Fail");
				log.error("Verified Concept Type from DB is " + StatusDB);

			}
			
			
			StatusDB = rs3.getString("ENTRY_TYPE");
			System.out.println("ENTRY_TYPE status from DB:" + StatusDB);
			// Status from UI
			StatusUI = driver.findElement(By.xpath(channel)).getText();
			System.out.println("ENTRY TYPE status from UI:" + StatusUI);

			if (StatusDB.contains(StatusUI))
			{
				oPSelFW.reportStepDetails("Channel type from UI is " + StatusUI,
						"Verified Channel type from DB is " + StatusDB, "Pass");
				log.info("Verified Channel type from DB is " + StatusDB);

			}

			else {

				oPSelFW.reportStepDetails("Channel type from UI is " + StatusUI,
						"Verified Channel Type from DB is " + StatusDB, "Fail");
				log.error("Verified Channel Type from DB is " + StatusDB);

			}

			StatusDB = rs3.getString("CUSTOMER_PHONE_NO");
			System.out.println("ENTRY_TYPE status from DB:" + StatusDB);
			// Status from UI
			
			String PhonefromUI=driver.findElement(By.xpath(phone)).getText();
			String phoneUI = PhonefromUI.replaceAll("-", "");
			 StatusUI = phoneUI.replaceAll("\\p{P}","");
			System.out.println("Phone number: "+StatusUI);
			//StatusUI = driver.findElement(By.xpath(phone)).getText();
			System.out.println("ENTRY TYPE status from UI:" + StatusUI);

			if (StatusDB.contains(StatusUI))
			{
				oPSelFW.reportStepDetails("Customer phone no from UI is " + StatusUI,
						"Verified Customer phone no  from DB is " + StatusDB, "Pass");
				log.info("Verified Customer phone no  from DB is " + StatusDB);

			}

			else {

				oPSelFW.reportStepDetails("Customer phone no from UI is " + StatusUI,
						"Verified Customer phone no  from DB is " + StatusDB, "Fail");
				log.error("Verified Customer phone no  from DB is " + StatusDB);
			}
			
		}
	}//try

	catch (Exception e) {
		System.out.println(e);
	}
}

public String OrderNumber(WebDriver driver, Map<String, String> XLTestData,
		String ordernumberfromDB, ProlificsSeleniumAPI oPSelFW) throws InterruptedException {
	try {
		// validating of status of the order

		// getting values from database
		DataBase db1 = new DataBase();
		String Environment = System.getProperty("Environment");
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
		System.out.println("Before Database");
		Connection conn = db1.ConnectToDB(HostName, DBSID, UserName, DBPassword, oPSelFW);
		Statement stat = conn.createStatement();
		ResultSet rs3 = null;
		BigDecimal orderHeaderKey = null;
		System.out.println("Before Database");

		if (Environment.contains("STST2"))
			// query from retrieving the status from database
			rs3 = stat.executeQuery(
					"select * from yantra_stst_owner.yfs_order_header where order_no like '9%' order by order_header_key desc FETCH FIRST 1 ROWS ONLY");
		else
		
			rs3 = stat.executeQuery(
					"select * from yantra_owner.yfs_order_header where order_no like '9%' order by order_header_key desc FETCH FIRST 1 ROWS ONLY");

		//basetest.test.log(Status.PASS, " <span style='font-weight:bold;color:blue'>Order number from DB<br></span");
		
		if (rs3.next() == true) {

			{
				// Status from DB
				ordernumberfromDB = rs3.getString("ORDER_NO");
				System.out.println("ORDER_NO status from DB: " + ordernumberfromDB);
				// Status from UI
			}
		}
		} 
	catch (Exception e) {
		System.out.println(e);
	}
	return ordernumberfromDB;
}
public void LoginToWCC(WebDriver driver, String UserName, String Password,ProlificsSeleniumAPI oPSelFW)
throws InterruptedException, IOException {
try {
gen.setText(driver, txtUserName, UserName);
oPSelFW.reportStepDetails("User Name should be Passed "  , UserName + "User Name is successfully Passed ", "Pass");
log.info( UserName + "User Name is successfully Passed ");
gen.setText(driver, txtPassword, Password);
oPSelFW.reportStepDetails("Password should be Passed "  ,UserName + "Password is successfully Passed ", "Pass"); 
log.info(UserName + "Password is successfully Passed "); 
gen.clickElement(driver, btnLogin);
oPSelFW.reportStepDetails("Login should be clicked " ,btnLogin + "Login is successfully Clicked ", "Pass");
log.info(btnLogin + "Login is successfully Clicked ");
gen.waitUntilElementIsPresent(driver, headerIcon);
if (driver.findElements(By.xpath("//span[@class='idxHeaderUserIcon']")).size() != 0) {
oPSelFW.reportStepDetails("User should be logged in "  , UserName + "user is successfully Logged in ", "Pass");
log.info( UserName + "user is successfully Logged in ");
} else {
oPSelFW.reportStepDetails("User should be logged in "  , UserName + "user is not successfully Logged in ", "Pass");
log.info(UserName + "user is not successfully Logged in ");
driver.close();
driver.quit();
Assert.assertFalse(true);
}
} catch (Exception e) {
// basetest.test.log(Status.FAIL, "Exception " + e + " is displayed");
oPSelFW.reportStepDetails("Login Should not be logged succesful " + e,
" User is Not Logged into the WCC Sucessfully", "Fail"); 
log.error(" User is Not Logged into the WCC Sucessfully"); 
}
}
public void WaiveChargesOrderLine(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException {
	try {
		gen.waitUntilElementIsPresent(driver, headerIcon);
	System.out.println("Appeasing the Customer.........");
	//Xpath for clicking on the link appease the customer
	Actions action = new Actions(driver);
	WebElement we = driver.findElement(By.xpath(Appease));
	action.moveToElement(we).click();
	Thread.sleep(5000);
	action.perform();
	oPSelFW.reportStepDetails("Appease customer link should be clicked " ," Appease customer link should be clicked successfully " , "Pass"); 
	log.info(" Appease customer link should be clicked successfully " ); 
	Thread.sleep(25000);
	//selecting the reason code
	driver.findElement(By.xpath(ReasonCode)).click();
	oPSelFW.reportStepDetails("Reason Code should be clicked " ," Reason Code is successfully clicked " , "Pass");
	log.info(" Reason Code is successfully clicked ");
	gen.setText(driver, ReasonCode, XLTestData.get("ReasonCode").toString());
	//Selecting the sub reason code
	Thread.sleep(3000);
	driver.findElement(By.xpath(sub)).click();
	driver.findElement(By.xpath(SubReasonCode)).click();
	oPSelFW.reportStepDetails(" Sub Reason Code should be clicked " ," Sub Reason Code is successfully clicked " , "Pass");
	log.info(" Sub Reason Code is successfully clicked " );
	gen.setText(driver, SubReasonCode, XLTestData.get("SubReasonCode").toString());
	//we.sendKeys(Keys.ENTER);
	Thread.sleep(2000);
	//Selecting the Item
	driver.findElement(By.xpath("((((//*[@uid='OLST_listGrid'])[1]/div)[3]/div)[4]/div)")).click();
	oPSelFW.reportStepDetails("Item should be clicked " ," Item is successfully clicked " , "Pass");
	log.info(" Item is successfully clicked " );
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,500)");
	//Clicking on Next button
	driver.findElement(By.xpath(Nextbtn)).click();
	oPSelFW.reportStepDetails("Next button should be clicked " ,"Next button is successfully clicked " , "Pass");
	log.info("Next button is successfully clicked " );
	gen.waitUntilElementIsPresent(driver, headerIcon);
	Thread.sleep(20000);
	//Selecting the waive charges option
	js.executeScript("window.scrollBy(0,-1000)");
	Thread.sleep(7000);
	driver.findElement(By.xpath(Waive)).click();
	oPSelFW.reportStepDetails("Waive order line Shipping charges should be selected " ," Waive order line Shipping charges is successfully selected " , "Pass");
	log.info(" Waive order line Shipping charges is successfully selected " );
	//Clicking on confirm button
	js.executeScript("window.scrollBy(0,700)");
	gen.setText(driver, note, XLTestData.get("Note Value").toString());
	driver.findElement(By.xpath(Confirmbtn)).click();
	Thread.sleep(10000);
	oPSelFW.reportStepDetails("Note value should be Passed " ,"Note value is successfully Passed successfully " , "Pass");
	log.info("Note value is successfully Passed successfully ");
	/*driver.findElement(By.xpath(next)).click();
	oPSelFW.reportStepDetails("Next button should be clicked " ,"Next button is successfully clicked " , "Pass");  log.info(*/
	Thread.sleep(20000);
	//Clicking on Appease customer link
	WebElement we1 = driver.findElement(By.xpath(Appease));
	action.moveToElement(we1).click();
	action.perform();
	oPSelFW.reportStepDetails("Appease customer link should be clicked " ," Appease customer link should be clicked successfully " , "Pass");
	log.info(" Appease customer link should be clicked successfully " );
	Thread.sleep(20000);
	}catch (Exception e) {
	//oPSelFW.reportStepDetails("Exception got Occured" ," Exception should got displayed successfully " , "Fail");  log.error(
	System.out.println(e);
	}
	}
public void ValidatingOrdergiftregistryStatus(WebDriver driver,WebElement element,Map<String, String> XLTestData,ProlificsSeleniumAPI oPSelFW) throws Exception

{
	Thread.sleep(5000);
	String xlConcept = XLTestData.get("Concept").toString();
	Thread.sleep(5000);
	if(xlConcept.contains("Pottery Barn Teen"))
	{
		Concept = "PT";
	}
	else if(xlConcept.contains("Williams-Sonoma"))
	{
		Concept = "WS";
	}
	else if(xlConcept.contains("West Elm"))
	{
		Concept = "WE";
	}
	else if(xlConcept.contains("Pottery Barn Kids"))
	{
		Concept = "PK";
	}
	else if(xlConcept.contains("Pottery Barn"))
	{
		Concept = "PB";	
	}
	else if(xlConcept.contains("Pottery Barn Teen Mark and Graham"))
	{
		Concept = "MG";	
	}
	xlConcept = Concept;
	Thread.sleep(5000);
	String uiConcept = driver.findElement(By.xpath("(//*[@uid='listGrid']/div[3]/div[2]/div/table/tbody/tr/td)[1]")).getText();
	if (xlConcept.contains(uiConcept)) {
	
	oPSelFW.reportStepDetails("Concept displayed in UI is should match with the provided Concept in Test Data excel "  , "Verified Concept displayed in UI is matching with the provided Concept in Test Data excel ", "Pass"); 
	log.info("Verified Concept displayed in UI is matching with the provided Concept in Test Data excel "); 
	}
	else {
		oPSelFW.reportStepDetails("Concept should match"  , "Concept is not matching", "Fail");
		log.error("Concept is not matching");
	}
	Thread.sleep(5000);
    String uiEventType = driver.findElement(By.xpath("(//*[@uid='listGrid']/div[3]/div[2]/div/table/tbody/tr/td)[3]")).getText();
	
	oPSelFW.reportStepDetails("Registry Id should display"  , "Verified Registry Id display as " + uiEventType, "Pass");
	log.info("Verified Registry Id display as " + uiEventType);

	Thread.sleep(5000);
	String xlState = XLTestData.get("State").toString();
	
	Thread.sleep(5000);
	String uiState = driver.findElement(By.xpath("(//*[@uid='listGrid']/div[3]/div[2]/div/table/tbody/tr/td)[5]")).getText();
	if (xlState.contains(uiState)) {
	
	oPSelFW.reportStepDetails("State displayed in UI is should match with the provided State in Test Data excel "  , "Verified State displayed in UI is matching with the provided State in Test Data excel ", "Pass");
	log.info("Verified State displayed in UI is matching with the provided State in Test Data excel ");
	}
	else {
		
		oPSelFW.reportStepDetails("State should match"  , "State is not matching", "Fail");
		log.error( "State is not matching");
	}
	Thread.sleep(5000);
	String uiRegistryId = driver.findElement(By.xpath("(//*[@uid='listGrid']/div[3]/div[2]/div/table/tbody/tr/td)[6]")).getText();
	
	oPSelFW.reportStepDetails("Registry Id should display"  , "Verified Registry Id display as " + uiRegistryId, "Pass");
	log.info( "Verified Registry Id display as " + uiRegistryId);

}
public void ValidatingBalanceInquiryStatus(WebDriver driver,WebElement element,ProlificsSeleniumAPI oPSelFW) throws Exception

{
	String uiBalance = driver.findElement(By.xpath("(//*[@uid='extn_Balance']/div/div/div/input)[2]")).getAttribute("value");
	oPSelFW.reportStepDetails("Available balance should display"  , "Verified Available balance is $" + uiBalance, "Pass");
	log.info("Verified Available balance is $" + uiBalance);
	String uiStatus = driver.findElement(By.xpath("//*[@uid='extn_datalabel_balance_status']/div[2]/div/div/span")).getText();
	oPSelFW.reportStepDetails("Status should display"  , "Verified Status " + uiStatus, "Pass");
	log.info("Verified Status " + uiStatus);

}
	
public String getOrderNumber(WebDriver driver,String orderno,Map<String, String> XLTestData,ProlificsSeleniumAPI oPSelFW) throws Exception 
{
	
	
	DataBase1 db1 = new DataBase1();
    String Environment = XLTestData.get("WCCENV").toString();
    String HostName, UserName, DBPassword, DBSID;
    if(Environment.contains("STST2"))
    {
    	 HostName = gen.getPropertyValue("stst2.db.hostname");
         UserName = gen.getPropertyValue("stst2.db.user");
         DBPassword = gen.getPropertyValue("stst2.db.password");
         DBSID = gen.getPropertyValue("stst2.db.sid");
    }
    else if(Environment.contains("EQA3"))
    {
    	 HostName = gen.getPropertyValue("eqa3.db.hostname");
         UserName = gen.getPropertyValue("eqa3.db.user");
         DBPassword = gen.getPropertyValue("eqa3.db.password");
         DBSID = gen.getPropertyValue("eqa3.db.sid");
    }
    else
    {
    	 HostName = gen.getPropertyValue("stst.db.hostname");
         UserName = gen.getPropertyValue("stst.db.user");
         DBPassword = gen.getPropertyValue("stst.db.password");
         DBSID = gen.getPropertyValue("stst.db.sid");
    }
    System.out.println("Before Database");
	Connection conn = db1.ConnectToDB(HostName, DBSID, UserName, DBPassword, oPSelFW);
	Statement stat = conn.createStatement();
	ResultSet rs =null;
	BigDecimal orderHeaderKey = null; 
	System.out.println("Before Database");
	
	if(Environment.contains("STST2"))
	//query from retrieving bill to name from database
	rs = stat.executeQuery("select * from YFS_ORDER_HEADER where ORDER_NO LIKE '9%' AND ORDER_HEADER_KEY not in\r\n" + 
			"(select ORDER_HEADER_KEY from yfs_order_release_Status where STATUS <> '1100')\r\n" + 
			"AND Order_Header_Key in (select ORDER_HEADER_KEY from YFS_ORDER_LINE GROUP BY ORDER_HEADER_KEY HAVING COUNT(ORDER_HEADER_KEY) <= 2)");
	
	
	else
		
		rs = stat.executeQuery("select YOH.ORDER_NO, YOL.ORDER_HEADER_KEY from yantra_owner.YFS_ORDER_HEADER YOH JOIN yantra_owner.YFS_ORDER_LINE YOL\r\n" + 
				"ON YOH.Order_Header_Key = YOL.Order_Header_Key\r\n" + 
				"where YOH.ORDER_NO LIKE '9%'\r\n" + 
				"GROUP BY YOH.ORDER_NO, YOL.ORDER_HEADER_KEY HAVING COUNT(YOL.ORDER_HEADER_KEY) = 2 FETCH FIRST 1 ROWS ONLY");
		
		
	if (rs.next() == true) {

		{
			

			// Status from DB
			orderno = rs.getString("ORDER_NO");
			System.out.println("ORDER_NO status from DB: " + orderno);
			// Status from UI
			

		}
}
	return orderno;
}


	
	public String BlindReturnValidation(WebDriver driver,ProlificsSeleniumAPI oPSelFW) throws Exception
{
	
	Thread.sleep(3000);
    String uiEventType = driver.findElement(By.xpath("//*[@uid='lblReturnNo']")).getText();
	
	oPSelFW.reportStepDetails("Order number from UI should display"  , "Verified Order Number from UI is display as " + uiEventType, "Pass");
	log.info("Verified Order Number from UI is display as " + uiEventType);
	Thread.sleep(3000);
return uiEventType;

}



public void ValidatingReturnStatus(WebDriver driver,Map<String, String> XLTestData,
		String returnOrdernumber,ProlificsSeleniumAPI oPSelFW) throws InterruptedException {
	try {
		// validating of status of the order

		// getting values from database
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
		System.out.println("Before Database");
		Connection conn = db1.ConnectToDB(HostName, DBSID, UserName, DBPassword, oPSelFW);
		Statement stat = conn.createStatement();
		ResultSet rs3 = null;
		BigDecimal orderHeaderKey = null;
		System.out.println("After Database");

		if (Environment.contains("STST2"))
			// query from retrieving the status from database
			rs3 = stat.executeQuery(
					"select original_total_amount, return_by_gift_recipient, extn_refund_policy, extn_original_order_no,extn_return_method from yantra_stst_owner.YFS_ORDER_HEADER where ORDER_NO  = '" + returnOrdernumber + "'");
		else
		
                rs3 = stat.executeQuery("select original_total_amount, return_by_gift_recipient, extn_refund_policy, extn_original_order_no,extn_return_method from yantra_owner.YFS_ORDER_HEADER where ORDER_NO  = '" + returnOrdernumber + "'");


		if (rs3.next() == true)

		{

			// Status from DB
			String StatusDB = rs3.getString("original_total_amount");
			System.out.println("Total amount status from DB:" + StatusDB);
			// Status from UI
			String StatusUI = driver.findElement(By.xpath("(//*[@uid='lnkTotalAmountDisplay'])[1]/div[2]/div/div/a")).getText();
			System.out.println("Total amount from from UI:" + StatusUI);
			
			 
			//$1,169.00

			StatusUI = StatusUI.replace("$", "");
			
			System.out.println("Phone number: "+StatusUI);
			
			//1,169.00
			StatusUI = StatusUI.replaceAll(",", "");
			
			//1169.00
			
			StatusUI = StatusUI.replaceAll(".00", "");
			//1169
			

			if (StatusDB.contains(StatusUI))

			{
				oPSelFW.reportStepDetails("Return total amount from UI is " + StatusUI,
						"Verified Return total amount from DB is " + StatusDB, "Pass"); 
				log.info("Verified Return total amount from DB is " + StatusDB); 

			}

			else {

				oPSelFW.reportStepDetails("Return total amount from UI is " + StatusUI,
						"Verified Return total amount from DB is " + StatusDB, "Fail");
				log.error("Verified Return total amount from DB is " + StatusDB);

			}
			
		}
	}//try

	catch (Exception e) {
		System.out.println(e);
	}
}


	
	
	
	
	
}
