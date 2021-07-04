package Library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.prolifics.ProlificsSeleniumAPI;
import FrameWork.DataBase;
import FrameWork.Generic;

public class DbValidationsAppeasement {

	Logger log;
	Generic gen1 = new Generic();

	public DbValidationsAppeasement() {

		try {
			PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
			String logFilePath = System.getProperty("user.dir") + "\\ProlificsReports\\";
			gen1.UpdatePropFile("log", logFilePath);
			log = Logger.getLogger(DbValidationsAppeasement.class);
		} catch (Exception e) {
			System.out.println("Inside Exception");
		}
	}

	ProlificsSeleniumAPI oPSelFW = null;
	Generic1 gen = new Generic1();
	static boolean loginSuccess;
	static String getOrderNumber;
	boolean errorDisplayed = false;
	WebElement element;
	WebDriver driver;
	static String errorMessage;
	public static Connection stSTCon;
	static String OutputFileName = System.getProperty("user.dir") + "\\target\\CreateOrder";
	static String Prop = System.getProperty("user.dir") + "\\resources\\Orders.properties";
	int RowNum = 1;
	CreateOrderPage ordercreate1 = new CreateOrderPage();

	// validate the order summary page validation
	final By Waive1 = By.xpath("(((//*[@uid='checkbox_list'])[1]/div)[2]/div/div)[1]");
	final By Confirmbtn1 = By.xpath("(//span[contains(@id,'Button') and normalize-space(text())='Confirm'])[2]");
	String Waive2 = "(((//*[@uid='checkbox_list'])[1]/div)[2]/div/div)[2]";
	String Waive3 = "(((//*[@uid='checkbox_list'])[1]/div)[2]/div/div)[3]";
	String Waive4 = "(((//*[@uid='checkbox_list'])[1]/div)[2]/div/div)[4]";
	String Waive5 = "(((//*[@uid='checkbox_list'])[1]/div)[2]/div/div)[5]";
	String orderNumberText = "(//span[@uid='extn_order_no'])";
	String APPamt = "((//*[@uid='extn_txt_DiscountAmount'])/div[2])/div/div/input[1]";
	String cus_id1 = "((//*[@uid=\"txtCustomerID2\"]/div)[2]/div/div/input)[1]";
	String orderNumberText1 = "(//span[@uid='lblReturnNo'])";
	String ord = "(//span[contains(text(),'W191011304')])[4]";
	String Ordertype = "(//span[@class='scDataValue'])[4]";
	String BillTo = "(//a[@data-ibmsc-uid='lnkEditAddress'])[2]";
	String ShipTo = "(//a[@data-ibmsc-uid='lnkEditAddress'])[1]";
	String DayPhone = "//input[@data-ibmsc-uid='txtDayPhone']";
	String PostalCode = "//input[@data-ibmsc-uid='txtZipCode']";
	String AdressLine1 = "//input[@data-ibmsc-uid='txtAddressLine1']";
	String LastNme = "//input[@data-ibmsc-uid='txtLastName']";
	String EmailAddress = "//input[@data-ibmsc-uid='txtEmailAddress']";
	By XpathCancelbtn = By.xpath("(//*[@uid=\"Popup_btnCancel\"]/span/span/span)[3]");
	String FirstName = "(//label[contains(normalize-space(text()),'First Name:')]/following::input[position()=1 and contains(@class,'dijitReset dijitInputInner') and @data-ibmsc-uid='txtFirstName'])[1]";
	String createdby = "(//span[@class='scDataValue'])[5]";
	String ototal = "((//div[@uid='lnkTotalAmountDisplay'])[1]/div)[2]";
	String channel = "(//span[@class='scDataValue'])[2]";
	String shippingAddressXpath = "(//div[@class='addressdisplay'])[2]";
	String billingAddressXpath = "(//div[@class='addressdisplay'])[3]";
	String Item = "//div[@id='gridx_Grid_9']/div[3]/div[2]/div/table/tbody/tr/td[2]";
	String unit = "//td[@aria-label='Unit Price']/div";
	String reg = "(//td[@class='gridxCell    '])[12]";
	String qty = "//td[@aria-label='Unit Price']/div/following::td[1]";
	String dateUI = "(//td[@class='gridxCell    '])[8]";
	String ship = "(//span[@uid='lblName'])[1]";
	String service = "//div[@id='gridx_Grid_9']/div[3]/div[2]/div/table/tbody/tr/td[12]";
	By XPathApplybtn = By.xpath("//span[contains(@id,'Button') and normalize-space(text())='Apply']");
	String Bcity = "(//span[@class='scLabel '])[7]";
	String BaddrUI = "(//span[@class='scLabel '])[5]";
	String emailUI = "(//div[@uid='lnkEmail'])[1]";

	String username = "(//span[@uid='lblName'])[2]";
	String refund = "(//span[@class='scDataValue'])[9]";
	String preRefund = "(//span[@class='scDataValue'])[7]";
	String refund1 = "//td[contains(text(),'($48.77)')]";
	String orderplaced = "(//span[@class='scDataValue'])[3]";
	String country = "(//span[@class='scLabel '])[7]";
	String phone = "(//span[@uid='lblDayPhone'])[1]";
	String number = "(//div[@class='productImageDesc'])[1]";
	String mode = "(//td[@class='gridxCell    '])[9]";
	String Appease = "//a[contains(text(),'Appease Customer')]";
	String note = "(//*[@uid='txtNoteText']/div[2]/div)[1]/textarea";
	String Confirmbtn = "(//*[@uid='confirmBttn'])";
	String ReasonCode = "((((//*[@uid='extn_reason'])[1]/div)[2]/div/div)[2]/input)[1]";
	String CanReason = "(//*[@uid='CancelOrderBaseScreen']/div/div/div/div[2]/input)[1]";
	String CanSubreason = "(//*[@uid='CancelOrderBaseScreen']/div/div/div/div[2]/input)[3]";
	String SubReasonCode = "((((//*[@uid='extn_subreason'])[1]/div)[2]/div/div)[2]/input)[1]";
	String AddPaymentMethodbtn = "(//span[contains(@id,'Button') and normalize-space(text())='Add Payment Method'])";
	String PaymentType = "//label[normalize-space(text())='Payment type:']/following::input[position()=2 and  @class='dijitReset dijitInputInner']";
	String Nextbtn = "//span[@uid='nextBttn2']/span/span/span[3]";
	final By next = By.xpath("(//*[@uid='nextBttn'])[1]");
	String AddApply = "(//span[contains(@id,'Button') and normalize-space(text())='Apply'])[3]";
	String cancel = "(//a[contains(text(),'Cancel Order')])[1]";
	String Line = "(((((//*[@uid='cancelType'])/div)[2]/div)[1]/div)[2]/div)";
	String StaUI = "(//td[@class='gridxCell    '])[10]";
	String FullLine = "(//input[@role='radio'])[3]";
	String OrderNumber = "(//input[@class='dijitReset dijitInputInner'])[2]";
	String OrderNumber1 = "(//input[@class='dijitReset dijitInputInner'])[24]";
	String FindOrder = "//span[contains(text(),'Find Order')]";
	String Waive = "(((//*[@uid='checkbox_list'])[1]/div)[2]/div/div)[3]";
	String Pre = "(//*[@uid='titlePrevAchmnts'])";
	// String pre1="(//div[@role='presentation'])[223]";
	String sub = "(//label[contains(text(),'Sub-Reason code:')])[1]";
	String subcode = "//label[contains(text(),'Sub Reason Code')]";
	String chooseoption = "//label[contains(text(),'Choose an option:')]";
	String ordernumber = "//*[@uid='pnlPrimaryInfo']/div/table/tbody/tr/td/span";
	String search = "(//span[@class='dijitReset dijitInline dijitButtonText'])[7]";
	final By searchCust = By.xpath("(//span[contains(text(), 'Search')])[5]");
	String close = "(//span[@class='dijitInline dijitTabCloseButton dijitTabCloseIcon'])[2]";
	String ordercls = "(//span[@class='dijitInline dijitTabCloseButton dijitTabCloseIcon'])[2]";
	String txtUserName = "//input[@id='idx_form_TextBox_0']";
	String txtPassword = "//input[@id='loginPassword']";
	String btnLogin = "//span[@id='dijit_form_Button_0_label']";
	String ItemId = "//*[@uid='addItems']/div/div/div/div[1]/div[2]/div[2]/div/div/input";
	final By ItemIDXpath = By.xpath(ItemId);
	String AddBtn = "(//*[@uid='add_item']/span/span/span)[3]";
	String EachX = "//label[normalize-space(text())='Quantity:']/following::input[position()=1 and  @class='dijitReset dijitInputInner']";
	String headerIcon = "//span[@class='idxHeaderUserIcon']"; 
	public void orderSummaryPageValidation(WebDriver driver, Map<String, String> XLTestData, String getOrderNumber,
			ProlificsSeleniumAPI oPSelFW) {

		try {
			if (driver.findElements(By.xpath(orderNumberText1)).size() > 0) {
				System.out.println("*******Order Confirmation Page Validations*******");
				String orderNoFromUI = driver.findElement(By.xpath(orderNumberText1)).getText();
				System.out.println("Order No From UI: " + orderNoFromUI);
				oPSelFW.reportStepDetails("Verify Order number from DB" + orderNoFromUI,
						"Verify Order number from UI" + orderNoFromUI, "Pass");
				log.info("Verify Order number from UI" + orderNoFromUI);

				// getting values from database
				DataBase1 db1 = new DataBase1();
				String ironment = XLTestData.get("WCCENV").toString();
				String HostName, UserName, DBPassword, DBSID;
				if (ironment.contains("STST2")) {
					HostName = gen.getPropertyValue("stst2.db.hostname");
					UserName = gen.getPropertyValue("stst2.db.user");
					DBPassword = gen.getPropertyValue("stst2.db.password");
					DBSID = gen.getPropertyValue("stst2.db.sid");
				} else if (ironment.contains("EQA3")) {
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
				ResultSet rs = null;
				BigDecimal orderHeaderKey = null;
				System.out.println("Before Database");

				if (ironment.contains("STST"))
					// verifying bill to key address from db and UI
					rs = stat.executeQuery(
							"select * from yantra_owner.yfs_person_info where person_info_key in (select Bill_to_key from yantra_owner.yfs_order_header where Order_no='"
									+ getOrderNumber + "')");
				if (rs.next() == true) {
					do {

						String firstname = rs.getString("FIRST_NAME");
						String lastname = rs.getString("LAST_NAME");

						// shipping details text
						String shippingAddress = driver.findElement(By.xpath(shippingAddressXpath)).getText();
						System.out.println("Shipping Address: " + shippingAddress);

						// billing address text
						String billingAddress = driver.findElement(By.xpath(billingAddressXpath)).getText();
						System.out.println("Billing Address: " + billingAddress);

						// verification of username
						String name = driver.findElement(By.xpath(username)).getText();
						System.out.println("User name is: " + name);

						if (name.contains(firstname))

						{
							oPSelFW.reportStepDetails(
									"shipToFirstName:" + firstname + "has to entered to First Name text box",
									"shipToFirstName:" + firstname + " is entered in First Name text box", "Pass");
							log.info("shipToFirstName:" + firstname + " is entered in First Name text box");
						}

						else {

							oPSelFW.reportStepDetails(
									"shipToFirstName:" + firstname + "has to entered to First Name text box",
									"shipToFirstName:" + firstname + " is entered in First Name text box", "Fail");
							log.error("shipToFirstName:" + firstname + " is entered in First Name text box");
						}

						if (name.contains(lastname))

						{
							oPSelFW.reportStepDetails(
									"shipToLastName:" + lastname + "has to entered to Last Name text box",
									"shipToLastName:" + lastname + " is entered in Last Name text box", "Pass");
							log.info("shipToLastName:" + lastname + " is entered in Last Name text box");
						}

						else {

							oPSelFW.reportStepDetails(
									"shipToLastName:" + lastname + "has to entered to Last Name text box",
									"shipToLastName:" + lastname + " is entered in Last Name text box", "Fail");
							log.error("shipToLastName:" + lastname + " is entered in Last Name text box");
						}

						// getting details from database
						String AddressStr = rs.getString("CITY").trim() + " " + rs.getString("COUNTRY").trim() + " "
								+ rs.getString("DAY_PHONE").trim();
						String BcityUI = rs.getString("CITY");
						String ci = BcityUI.replaceAll("\\s+", " ");
						String cii = ci.replaceAll("[ ,]", "");

						// getting city from UI
						String City = driver.findElement(By.xpath(Bcity)).getText();

						String city = City.replaceAll("[CA 94111 -1226,]", "");
						System.out.println("City: " + city);

						// verification of city
						if (cii.equalsIgnoreCase(city))

						{
							oPSelFW.reportStepDetails("Return from City:" + cii + "has to entered in City text Box",
									"Return from City:" + cii + " is to entered in City text Box", "Pass");
							log.info("Return from City:" + cii + " is to entered in City text Box");
						}

						else {

							oPSelFW.reportStepDetails("Return from City:" + cii + "has not entered in City text Box",
									"Return from City:" + cii + " is to entered in City text Box", "Fail");
							log.error("Return from City:" + cii + " is to entered in City text Box");
						}

						// verification of state
						String BstateUI = rs.getString("STATE");
						String kkk = BstateUI.replaceAll("[ ,]", "");

						// getting state from UI
						String State = driver.findElement(By.xpath(Bcity)).getText();

						String state = State.replaceAll("[San Francisco 94111,]", "");
						System.out.println("State: " + state);

						// verification of state
						if (state.contains(kkk))

						{
							oPSelFW.reportStepDetails("Return from State:" + kkk + "has to entered in State text Box",
									"Return from State:" + kkk + " is to entered in State text Box", "Pass");
							log.info("Return from State:" + kkk + " is to entered in State text Box");
						}

						else {

							oPSelFW.reportStepDetails("Return from State:" + kkk + "has not entered in State text Box",
									"Return from State:" + kkk + " is not entered in State text Box", "Fail");
							log.error("Return from State:" + kkk + " is not entered in State text Box");
						}

						// verification of ship to zip code
						String BZip = rs.getString("ZIP_CODE");
						// getting zip code from UI
						String Zip = driver.findElement(By.xpath(Bcity)).getText();
						String ZipC = Zip.replaceAll("[San Francisco CA,]", "");
						System.out.println("ZIP_Code: " + ZipC);
						if (ZipC.contains(BZip))

						{
							oPSelFW.reportStepDetails(
									"Return from Zip_code:" + BZip + "has to entered in ZipCode text Box",
									"Return from Zip_code:" + BZip + " is to entered in ZipCode text Box", "Pass");
							log.info("Return from Zip_code:" + BZip + " is to entered in ZipCode text Box");
						}

						else {

							oPSelFW.reportStepDetails(
									"Return from Zip_code:" + BZip + "has not entered in Zipcode text Box",
									"Return from Zip_code:" + BZip + " is not entered in Zipcode text Box", "Fail");
							log.error("Return from Zip_code:" + BZip + " is not entered in Zipcode text Box");
						}

						// verification of ship to address
						String Baddr = rs.getString("ADDRESS_LINE1");
						String addrUI = driver.findElement(By.xpath(BaddrUI)).getText();
						System.out.println("Address: " + addrUI);
						if (addrUI.contains(Baddr))

						{
							oPSelFW.reportStepDetails(
									"Return from address:" + Baddr + "has to entered in address text Box",
									"Return from address:" + Baddr + " is to entered in address text Box", "Pass");
							log.info("Return from address:" + Baddr + " is to entered in address text Box");
						}

						else {

							oPSelFW.reportStepDetails(
									"Return from address:" + Baddr + "has not entered in address text Box",
									"Return from address:" + Baddr + " is not entered in address text Box", "Fail");
							log.error("Return from address:" + Baddr + " is not entered in address text Box");
						}
						String phDB = rs.getString("DAY_PHONE");
						String phh = phDB.replaceAll("[ ,]", "");

						// getting phone number from UI
						String Phone = driver.findElement(By.xpath(phone)).getText();
						System.out.println("Phone number: " + Phone);

						if (Phone.contains(phh))

						{
							oPSelFW.reportStepDetails(
									"Return from phone number:" + phh + "has to entered in phone number text Box",
									"Return from phone number:" + phh + " is to entered in phone number text Box",
									"Pass");
							log.info("Return from phone number:" + phh + " is to entered in phone number text Box");
						}

						else {

							oPSelFW.reportStepDetails(
									"Return from phone number:" + phh + "has not entered in phone number text Box",
									"Return from phone number:" + phh + " is not entered in phone number text Box",
									"Fail");
							log.error("Return from phone number:" + phh + " is not entered in phone number text Box");
						}

						// verification of email address
						String emailDB = rs.getString("EMAILID");
						// getting phone number from UI
						String email = driver.findElement(By.xpath(emailUI)).getText();
						System.out.println("Email: " + email);

						if (email.equalsIgnoreCase(emailDB))

						{
							oPSelFW.reportStepDetails(
									"Return from Email address:" + emailDB + "has to entered in Email address text Box",
									"Return from Email address:" + emailDB + " is to entered in Email address text Box",
									"Pass");
							log.info(
									"Return from Email address:" + emailDB + " is to entered in Email address text Box");
						}

						else {

							oPSelFW.reportStepDetails(
									"Return from Email address:" + emailDB
											+ "has not entered in Email address text Box",
									"Return from Email address:" + emailDB + " is not entered in Email address text Box",
									"Fail");
							log.error("Return from Email address:" + emailDB
									+ " is not entered in Email address text Box");
						}

						while (rs.next())
							;
						rs = null;

						rs = stat.executeQuery(
								"select * from yantra_owner.yfs_person_info where person_info_key in (select ship_to_key from yantra_owner.yfs_order_header where Order_no='"
										+ getOrderNumber + "')");

						if (rs.next() == true) {

							String Firstname = rs.getString("FIRST_NAME");
							String Lastname = rs.getString("LAST_NAME");

							// billing address text
							String BillingAddress = driver.findElement(By.xpath(billingAddressXpath)).getText();
							System.out.println("Billing Address: " + BillingAddress);

							// verification of username
							String Name = driver.findElement(By.xpath(username)).getText();
							System.out.println("User name is: " + Name);

							if (name.contains(firstname))

							{
								oPSelFW.reportStepDetails(
										"BillToFirstName:" + Firstname + "has to entered to First Name text box",
										"BillToFirstName:" + Firstname + " is entered in First Name text box", "Pass");
								log.info("BillToFirstName:" + Firstname + " is entered in First Name text box");
							}

							else {

								oPSelFW.reportStepDetails(
										"BillToFirstName:" + Firstname + "has to entered to First Name text box",
										"BillToFirstName:" + Firstname + " is entered in First Name text box", "Fail");
								log.error("BillToFirstName:" + Firstname + " is entered in First Name text box");
							}

							if (Name.contains(Lastname))

							{
								oPSelFW.reportStepDetails(
										"BillToLastName:" + Lastname + "has to entered to Last Name text box",
										"BillToLastName:" + Lastname + " is entered in Last Name text box", "Pass");
								log.info("BillToLastName:" + Lastname + " is entered in Last Name text box");
							}

							else {

								oPSelFW.reportStepDetails(
										"BillToLastName:" + Lastname + "has to entered to Last Name text box",
										"BillToLastName:" + Lastname + " is entered in Last Name text box", "Fail");
								log.error("BillToLastName:" + Lastname + " is entered in Last Name text box");
							}

							// getting details from databas
							String ScityUI = rs.getString("CITY");

							// getting city from UI
							String Ccity = driver.findElement(By.xpath(Bcity)).getText();

							String cityUI = Ccity.replaceAll("[CA 94111,]", "");
							System.out.println("City: " + cityUI);

							// verification of city
							if (cityUI.contains(cii))

							{
								oPSelFW.reportStepDetails("Return to City:" + cii + "has to entered in City text Box",
										"Return to City:" + cii + " is to entered in City text Box", "Pass");
								log.info("Return to City:" + cii + " is to entered in City text Box");
							}

							else {

								oPSelFW.reportStepDetails("Return to City:" + cii + "has not entered in City text Box",
										"Return to City:" + cii + " is to entered in City text Box", "Fail");
								log.error("Return to City:" + cii + " is to entered in City text Box");
							}

							// verification of state
							String Sstatedb = rs.getString("STATE");
							String sss = Sstatedb.replaceAll("[ ,]", "");

							// getting state from UI
							String Sstate = driver.findElement(By.xpath(Bcity)).getText();

							String stateUI = Sstate.replaceAll("[San Francisco 94111,]", "");
							System.out.println("State: " + stateUI);

							// verification of state
							if (stateUI.contains(sss))

							{
								oPSelFW.reportStepDetails("Return to State:" + sss + "has to entered in State text Box",
										"Return to State:" + sss + " is to entered in State text Box", "Pass");
								log.info("Return to State:" + sss + " is to entered in State text Box");
							}

							else {

								oPSelFW.reportStepDetails(
										"Return to State:" + Sstatedb + "has not entered in State text Box",
										"Return to State:" + Sstatedb + " is not entered in State text Box", "Fail");
								log.error("Return to State:" + Sstatedb + " is not entered in State text Box");
							}

							// verification of ship to zip code
							String SZip = rs.getString("ZIP_CODE");
							// getting zip code from UI
							String Zipp = driver.findElement(By.xpath(Bcity)).getText();
							String ZipU = Zipp.replaceAll("[San Francisco CA,]", "");
							System.out.println("ZIP_Code: " + ZipU);
							if (ZipU.contains(SZip))

							{
								oPSelFW.reportStepDetails(
										"Return to Zip_code:" + SZip + "has to entered in ZipCode text Box",
										"Return to Zip_code:" + SZip + " is to entered in ZipCode text Box", "Pass");
								log.info("Return to Zip_code:" + SZip + " is to entered in ZipCode text Box");
							}

							else {

								oPSelFW.reportStepDetails(
										"Return to Zip_code:" + SZip + "has not entered in Zipcode text Box",
										"Return to Zip_code:" + SZip + " is not entered in Zipcode text Box", "Fail");
								log.error("Return to Zip_code:" + SZip + " is not entered in Zipcode text Box");
							}

							// verification of ship to address
							String Saddr = rs.getString("ADDRESS_LINE1");
							String AddrUI = driver.findElement(By.xpath(BaddrUI)).getText();
							System.out.println("Address: " + AddrUI);
							if (AddrUI.contains(Saddr))

							{
								oPSelFW.reportStepDetails(
										"Return to address:" + AddrUI + "has to entered in address text Box",
										"Return to address:" + AddrUI + " is to entered in address text Box", "Pass");
								log.info("Return to address:" + AddrUI + " is to entered in address text Box");
							}

							else {

								oPSelFW.reportStepDetails(
										"Return to address:" + AddrUI + "has not entered in address text Box",
										"Return to address:" + AddrUI + " is not entered in address text Box", "Fail");
								log.error("Return to address:" + AddrUI + " is to entered in address text Box");
							}
							String PhDB = rs.getString("DAY_PHONE");

							// getting phone number from UI
							String pphone = driver.findElement(By.xpath(phone)).getText();
							System.out.println("Phone number: " + pphone);

							if (phh.contains(pphone))

							{
								oPSelFW.reportStepDetails(
										"Return to phone number:" + phh + "has to entered in phone number text Box",
										"Return to phone number:" + phh + " is to entered in phone number text Box",
										"Pass");
								log.info("Return to phone number:" + phh + " is to entered in phone number text Box");
							}

							else {

								oPSelFW.reportStepDetails(
										"Return to phone number:" + phh + "has not entered in phone number text Box",
										"Return to phone number:" + phh + " is not entered in phone number text Box",
										"Fail");
								log.error("Return to phone number:" + phh + " is to entered in phone number text Box");
							}

							// verification of email address
							String EmailDB = rs.getString("EMAILID");
							// getting phone number from UI
							String eemail = driver.findElement(By.xpath(emailUI)).getText();
							System.out.println("Email: " + eemail);

							if (eemail.equalsIgnoreCase(emailDB))

							{
								oPSelFW.reportStepDetails(
										"Return to Email address:" + EmailDB
												+ "has to entered in Email address text Box",
										"Returnto Email address:" + EmailDB + " is to entered in Email address text Box",
										"Pass");
								log.info("Returnto Email address:" + EmailDB
										+ " is to entered in Email address text Box");
							}

							else {

								oPSelFW.reportStepDetails(
										"Return to Email address:" + EmailDB
												+ "has not entered in Email address text Box",
										"Return to Email address:" + EmailDB
												+ " is not entered in Email address text Box",
										"Fail");
								log.error("Return to Email address:" + EmailDB
										+ " is not entered in Email address text Box");
							}

							// order type from UI
							String OrderTypeFromUI = driver.findElement(By.xpath(Ordertype)).getText();
							System.out.println("Order type from UI: " + OrderTypeFromUI);

							// query for retrieving order header key from database
							rs = stat.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='"
									+ getOrderNumber + "'");

							if (rs.next() == true) {
								orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
								System.out.println("orderHeaderKey  :" + orderHeaderKey);
								String ppppp = "select * from yantra_owner.yfs_order_header where  order_header_key='"
										+ orderHeaderKey + "'";
								System.out.println("ppppp :" + ppppp);

								// query for validating order created date from database
								rs = stat.executeQuery(
										"select * from yantra_owner.yfs_order_header where  order_header_key='"
												+ orderHeaderKey + "'");
								if (rs.next() == true) {
									if (orderNoFromUI.contains(rs.getString("ORDER_NO").trim())) {
										oPSelFW.reportStepDetails(
												"Return Order Summary Screen: Order number is displayed as "
														+ orderNoFromUI,
												"Return Order Summary Screen: Order number is displayed as "
														+ orderNoFromUI,
												"Pass");
										log.info("Return Order Summary Screen: Order number is displayed as "
												+ orderNoFromUI);
									} else {

										oPSelFW.reportStepDetails(
												"Return Order Summary Screen: Order number is not displayed as "
														+ orderNoFromUI,
												"Return Order Summary Screen: Order number is not displayed as "
														+ orderNoFromUI,
												"Fail");
										log.error("Return Order Summary Screen: Order number is not displayed as "
												+ orderNoFromUI);
									}
									String Datecreated_DB = rs.getString("ORDER_DATE").trim();

									// Datecreated_DB =Util.getDateInUserFormate(Datecreated_DB);
									System.out.println("Datecreated_DB :" + Datecreated_DB);

									// verification of order date
									String Orderplaceddate = driver.findElement(By.xpath(orderplaced)).getText();
									System.out.println("Order placed date from UI: " + Orderplaceddate);
									/*
									 * if (Orderplaceddate.contains(Datecreated_DB)) {
									 * basetest.test.log(Status.PASS,
									 * "Order Confirmation Page: order placed date is displayed as" + "'</span>"+
									 * "<span style='font-weight:bold;color:blue'> '" + Orderplaceddate +
									 * "'</span>");
									 * 
									 * }else {
									 * System.out.println("Order Confirmation Page: city is not dispalyed as "
									 * +Orderplaceddate); basetest.test.log(Status.FAIL,
									 * "Order Confirmation Page: order placed date is not displayed as" +
									 * "'</span>"+ "<span style='font-weight:bold;color:blue'> '" + Orderplaceddate
									 * + "'</span>"); }
									 */
									// validating ship to address
									rs = null;

									// query for validating ship to name from database and UI
									rs = stat.executeQuery(
											"select FIRST_NAME,LAST_NAME,ADDRESS_LINE1,CITY,STATE,ZIP_CODE from YANTRA_OWNER.yfs_person_info where person_info_key in (select ship_to_key from YANTRA_OWNER.yfs_order_line where order_header_key in(select order_header_key from YANTRA_OWNER.yfs_order_header where Order_no='"
													+ getOrderNumber + "'))");
									if (rs.next() == true) {
										String OrderLine_First_Name = rs.getString("FIRST_NAME").trim();
										String OrderLine_Last_Name = rs.getString("LAST_NAME").trim();
										String OrderLines_Name_DB = OrderLine_First_Name + " " + OrderLine_Last_Name;
										System.out.println(OrderLine_First_Name + " " + OrderLine_Last_Name);
										OrderLines_Name_DB = OrderLines_Name_DB.trim();

										// getting ship address name from UI
										String saddr = driver.findElement(By.xpath(ship)).getText();
										System.out.println("name in the ship address: " + saddr);
										// verification of ship address

										if (OrderLines_Name_DB.contains(saddr)) {
											oPSelFW.reportStepDetails(
													"Return Order Summary Screen: User name is displayed as "
															+ OrderLines_Name_DB,
													"Return Order Summary Screen: User name is displayed as "
															+ OrderLines_Name_DB,
													"Pass");
											log.info("Return Order Summary Screen: User name is displayed as "
													+ OrderLines_Name_DB);
										} else {

											oPSelFW.reportStepDetails(
													"Return Order Summary Screen: User name is not displayed as "
															+ OrderLines_Name_DB,
													"Return Order Summary Screen: User name is not displayed as "
															+ OrderLines_Name_DB,
													"Fail");
											log.error("Return Order Summary Screen: User name is displayed as "
													+ OrderLines_Name_DB);
										}
										rs = null;
										// query for validating sub order count
										rs = stat.executeQuery(
												"select max(extn_dtc_suborderno) from YANTRA_OWNER.yfs_order_line where order_header_key in( select order_header_key from YANTRA_OWNER.yfs_order_header where Order_no='"
														+ getOrderNumber + "')");
										String subOrderCnt = null;
										if (rs.next() == true) {
											subOrderCnt = rs.getString("MAX(EXTN_DTC_SUBORDERNO)").trim();
											System.out.println("sub order count:" + subOrderCnt);
											// basetest.test.log(Status.PASS, subOrderCnt + " " + "SubOrder count from
											// Sterling</span> " + " for Order Number <b> " + getOrderNumber + "</b>");
											oPSelFW.reportStepDetails("SubOrder count  from Sterling " + subOrderCnt,
													"SubOrder count from Sterling " + subOrderCnt, "Pass");
											log.info("SubOrder count from Sterling " + subOrderCnt);
										} else {
											oPSelFW.reportStepDetails(
													"SubOrder count  from Sterling is not displayed as " + subOrderCnt,
													"SubOrder count  from Sterling is not displayed as" + subOrderCnt,
													"Fail");
											log.error(
													"SubOrder count  from Sterling is not displayed as" + subOrderCnt);
											System.out.println("subOrderCnt" + subOrderCnt);
										}
										rs = null;
										// query for validating item quantity, item id and unit price
										rs = stat.executeQuery(
												"select order_header_key from YANTRA_OWNER.yfs_Order_header where Order_no='"
														+ getOrderNumber + "'");
										if (rs.next() == true) {
											do {
												orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
											} while (rs.next());
											ResultSet rs1 = stat.executeQuery(
													"select item_id,unit_price,ordered_qty,order_line_key from YANTRA_OWNER.yfs_Order_line where order_header_key='"
															+ orderHeaderKey + "' and sub_line_no ='1'");
											if (rs1.next() == true) {
												// getting unit price from database
												String uni = rs1.getString("unit_price");
												System.out.println("unit price from database:" + uni);

												// getting unit price from UI
												String itemUnitPrice = driver.findElement(By.xpath(unit)).getText();
												String UnitPriceFromUI = itemUnitPrice.replaceAll("[$,]", "");
												System.out.println("Item unit price from UI: " + UnitPriceFromUI);
												oPSelFW.reportStepDetails(
														"Return Order Summary Screen: Unit price is displayed as "
																+ UnitPriceFromUI,
														"Return Order Summary Screen: Unit price is displayed as "
																+ UnitPriceFromUI,
														"Pass");
												log.info("Return Order Summary Screen: Unit price is displayed as "
														+ UnitPriceFromUI);

												// validating unit price from database and from UI

												/*
												 * if (uni.contains(UnitPriceFromUI)) { oPSelFW.
												 * reportStepDetails("Order Confirmation Page: Unit price is displayed as "
												 * + UnitPriceFromUI,
												 * " Order Confirmation Page: Unit price is displayed as " +
												 * UnitPriceFromUI, "Pass"); log.info( } else {
												 * 
												 * oPSelFW.
												 * reportStepDetails("Order Confirmation Page: Unit price is not displayed as "
												 * + UnitPriceFromUI,
												 * " Order Confirmation Page: Unit price is not displayed as " +
												 * UnitPriceFromUI, "Fail"); log.error( }
												 */
												// getting ordered quantity from database
												String quan = rs1.getString("ordered_qty");
												System.out.println("ordered quantity from database:" + quan);

												// getting quantity from UI
												String QuantityFromUI = driver.findElement(By.xpath(qty)).getText();
												System.out.println("Item quantity From UI: " + QuantityFromUI);

												// validating quantity from database and from UI

												if (quan.contains(QuantityFromUI)) {
													oPSelFW.reportStepDetails(
															"Return Order Summary Screen: Quantity is displayed as "
																	+ QuantityFromUI,
															"Return Order Summary Screen: Quantity is displayed as "
																	+ QuantityFromUI,
															"Pass");
													log.info("Return Order Summary Screen: Quantity is displayed as "
															+ QuantityFromUI);
												} else {

													oPSelFW.reportStepDetails(
															"Return Order Summary Screen: Quantity is not displayed as "
																	+ QuantityFromUI,
															"Return Order Summary Screen: Quantity is not displayed as "
																	+ QuantityFromUI,
															"Fail");
													log.error(
															"Return Order Summary Screen: Quantity is not displayed as "
																	+ QuantityFromUI);
												}
												// getting item id from UI
												String ItemUI = driver.findElement(By.xpath(number)).getText();
												String ItemFromUI = ItemUI.replaceAll("[^0-9]", "");
												String ItemFromui = ItemFromUI.replaceAll("\\p{P}", "");
												System.out.println("Item number From UI: " + ItemFromui);

												// getting item id from database
												String str = rs1.getString("item_id");
												String Itemdb = str.replaceAll("\\s", "");
												System.out.println("item id from database:" + Itemdb);
												oPSelFW.reportStepDetails(
														"Return Order Summary Screen: Iten id is displayed as "
																+ Itemdb,
														"Return Order Summary Screen: Item id is displayed as "
																+ Itemdb,
														"Pass");
												log.info("Return Order Summary Screen: Item id is displayed as "
														+ Itemdb);

												// validating item id from UI and from database

												/*
												 * if (ItemFromui.contains(Itemdb)) { oPSelFW.
												 * reportStepDetails("Order Confirmation Page: Iten id is displayed as "
												 * + ItemFromUI, " Order Confirmation Page: Item id is displayed as " +
												 * ItemFromUI, "Pass"); log.info( } else {
												 * 
												 * oPSelFW.
												 * reportStepDetails("Order Confirmation Page: Item id is not displayed as "
												 * + ItemFromUI,
												 * " Order Confirmation Page: Item id is not displayed as " +
												 * ItemFromUI, "Fail"); log.error( }
												 */

												rs = null;
												// verification of order total amount
												rs = stat.executeQuery(
														"Select ORIGINAL_TOTAL_AMOUNT from yantra_owner.yfs_order_header where order_header_key in(select order_header_key from yantra_owner.yfs_order_header where Order_no='"
																+ getOrderNumber + "')");
												if (rs.next() == true) {
													// getting order total from database
													String ooo = rs.getString("ORIGINAL_TOTAL_AMOUNT");
													System.out.println("order total amount from database:" + ooo);

													// getting order total from UI
													String order = driver.findElement(By.xpath(ototal)).getText();
													String OrderTotal = order.replaceAll("[$]", "");
													System.out.println("order total amount from UI:" + OrderTotal);
													oPSelFW.reportStepDetails(
															"Return Order Summary Screen: Order Total is displayed as "
																	+ OrderTotal,
															"Return Order Summary Screen: Order Total is displayed as "
																	+ OrderTotal,
															"Pass");
													log.info("Return Order Summary Screen: Order Total is displayed as "
															+ OrderTotal);

													/*
													 * //validating of order total from database and from UI
													 * 
													 * if (ooo.contains(OrderTotal)) { oPSelFW.
													 * reportStepDetails("Return Order Summary Screen: Order Total is displayed as "
													 * +
													 * OrderTotal,"Return Order Summary Screen: Order Total is displayed as "
													 * + OrderTotal, "Pass"); log.info( } else {
													 * 
													 * oPSelFW.
													 * reportStepDetails("Return Order Summary Screen: Order Total is not displayed as "
													 * +
													 * OrderTotal,"Return Order Summary Screen: Order Total is not displayed as "
													 * + OrderTotal, "Fail"); log.error( }
													 */
												}
												rs = null;
												// verification of order type
												rs = stat.executeQuery(
														"Select Order_type from yantra_owner.yfs_order_header where order_header_key in(select order_header_key from yantra_owner.yfs_order_header where Order_no='"
																+ getOrderNumber + "')");
												if (rs.next() == true) {
													// getting order total from database
													String ttt = rs.getString("Order_type");
													System.out.println("order total amount from database:" + ttt);
													oPSelFW.reportStepDetails(
															"Return Order Summary Screen: Order type is displayed as "
																	+ ttt,
															"Return Order Summary Screen: Order type is displayed as "
																	+ ttt,
															"Pass");
													log.info("Return Order Summary Screen: Order type is displayed as "
															+ ttt);

													// getting order total from UI
													String OrderType = driver.findElement(By.xpath(Ordertype))
															.getText();
													System.out.println("order total amount from UI:" + OrderType);

													// validating of order total from database and from UI

													/*
													 * if (ttt.contains(OrderType)) { oPSelFW.
													 * reportStepDetails("Order Confirmation Page: Order type is displayed as "
													 * + OrderType,
													 * " Order Confirmation Page: Ordrer type is displayed as " +
													 * OrderType, "Pass"); log.info( } else {
													 * 
													 * oPSelFW.
													 * reportStepDetails("Order Confirmation Page: Ordre type is not displayed as "
													 * + OrderType,
													 * " Order Confirmation Page: Ordre type is not displayed as " +
													 * OrderType, "Fail"); log.error( }
													 */
													rs = null;
													// verification of order total amount
													rs = stat.executeQuery(
															"Select entered_by,enterprise_key,entry_type,EXTN_BORDERFREE_ORDER_NO,EXTN_ORDER_SOURCE,EXTN_ORIGIN_COUNTRY_CODE,EXTN_INT_SHIP_METHOD from yantra_owner.yfs_order_header where order_header_key in(select order_header_key from yantra_owner.yfs_order_header where Order_no='"
																	+ getOrderNumber + "')");
													if (rs.next() == true) {
														// getting user name from database
														String user = rs.getString("entered_by");
														System.out.println(
																"oreder created by user from database:" + user);

														// getting user name from UI
														String Ouser = driver.findElement(By.xpath(createdby))
																.getText();
														System.out.println("order created by user from UI:" + Ouser);

														// validating of order total from database and from UI

														if (Ouser.contains(user)) {
															oPSelFW.reportStepDetails(
																	"Return Order Summary Screen: Created User name is displayed as "
																			+ Ouser,
																	"Return Order Summary Screen: Created User name is displayed as "
																			+ Ouser,
																	"Pass");
															log.info(
																	"Return Order Summary Screen: Created User name is displayed as "
																			+ Ouser);
														} else {

															oPSelFW.reportStepDetails(
																	"Return Order Summary Screen: Created User name is not displayed as "
																			+ Ouser,
																	"Return Order Summary Screen: Created User name is not displayed as "
																			+ Ouser,
																	"Fail");
															log.error(
																	"Return Order Summary Screen: Created User name is not displayed as "
																			+ Ouser);
														}
														// getting user name from database
														String entrytype = rs.getString("entry_type");
														System.out.println("entry type from database:" + entrytype);

														// getting user name from UI
														String entryUI = driver.findElement(By.xpath(channel))
																.getText();
														System.out.println("entry type from UI:" + entryUI);

														// validating of order total from database and from UI

														if (entryUI.contains(entrytype)) {
															oPSelFW.reportStepDetails(
																	"Return Order Summary Screen: Channel is displayed as "
																			+ entryUI,
																	"Return Order Summary Screen: Channel is displayed as "
																			+ entryUI,
																	"Pass");
															log.info(
																	"Return Order Summary Screen: Channel is displayed as "
																			+ entryUI);
														} else {

															oPSelFW.reportStepDetails(
																	"Return Order Summary Screen: Channel is not displayed as "
																			+ entryUI,
																	"Return Order Summary Screen: Channel is not displayed as "
																			+ entryUI,
																	"Fail");
															log.error(
																	"Return Order Summary Screen: Channel is not displayed as "
																			+ entryUI);
														}
														// validating level of service and ship mode
														rs = null;

														// verification of order type
														rs = stat.executeQuery(
																"Select LEVEL_OF_SERVICE,Carrier_Service_code from yantra_owner.yfs_order_line where order_header_key in(select order_header_key from yantra_owner.yfs_order_header where Order_no='"
																		+ getOrderNumber + "')");
														if (rs.next() == true) {

														}
													}
												}

											}

										}

									}
								}
							}
						}
						while (rs.next())
							;
					} while (rs.next());
				}
			} else {
				oPSelFW.reportStepDetails("Verify if error message is displayed", "Order details are not displayed",
						"Fail");
				log.error("Order details are not displayed");
				Assert.assertEquals("Verify if error message is displayed", "Order details are not displayed");
			}
		}

		catch (Exception e) {
			// basetest.test.log(Status.FAIL, "Exception " + e + " is Dispalyed");
			e.printStackTrace();
		}

	}

	/*
	 * ##############################################################
	 * 
	 * @Descriptions ---validating item details table
	 * ##############################################################
	 */

	public void ItemDetails(WebDriver driver) {

		System.out.println("*******Validating Item deatails table*******");

		try {

			// order number getting from UI
			String Itemname = driver.findElement(By.xpath(Item)).getText();
			System.out.println("Item description from UI: " + Itemname);

			// unit price
			String itemUnitPrice = driver.findElement(By.xpath(unit)).getText();
			String itemtotalrem = itemUnitPrice.replaceAll("[$,]", "");
			double UnitPriceFromUI = Double.parseDouble(itemtotalrem);
			System.out.println("Item unit price from UI: " + UnitPriceFromUI);

			// verification of quantity
			String QuantityFromUI = driver.findElement(By.xpath(qty)).getText();
			System.out.println("Item quantity From UI: " + QuantityFromUI);

			// verification of expected date
			String Expecteddate = driver.findElement(By.xpath(dateUI)).getText();
			System.out.println("Expected date from: " + Expecteddate);

			// verification of ship mode
			String Shipmode = driver.findElement(By.xpath(ship)).getText();
			System.out.println("Ship mode From UI: " + Shipmode);

			// verification of ship mode
			String LevelOfService = driver.findElement(By.xpath(service)).getText();
			System.out.println("Level of service From UI: " + LevelOfService);

		} catch (Exception e) {
			// basetest.test.log(Status.FAIL, "Exception " + e + " is Dispalyed");
			e.printStackTrace();
		}
	}

//***************************************************
//Appease the customer class file

	public void AppeaseCustemer(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			WebDriverWait wait = new WebDriverWait(driver, 10000);
			wait.until(
					ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='idxHeaderUserIcon']")));
			System.out.println("Appeasing the Customer.........");
			// Xpath for clicking on the link appease the customer
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath(Appease));
			action.moveToElement(we).click();
			Thread.sleep(1000);
			action.perform();
			oPSelFW.reportStepDetails("Appease customer link should be clicked ",
					"Appease customer link should be clicked successfully ", "Pass");
			log.info("Appease customer link should be clicked successfully ");
			gen.waitnclickElement(driver, By.xpath("((((//*[@uid='extn_reason'])[1]/div)[2]/div/div)[2]/input)[1]"));

			// selecting the reason code
			driver.findElement(By.xpath(ReasonCode)).click();
			Thread.sleep(1000);
			oPSelFW.reportStepDetails("Reason Code should be clicked ", "Reason Code is successfully clicked ", "Pass");
			log.info("Reason Code is successfully clicked ");
			gen.setText(driver, ReasonCode, XLTestData.get("ReasonCode").toString());
			// Selecting the sub reason code
			Thread.sleep(1000);
			driver.findElement(By.xpath(sub)).click();
			driver.findElement(By.xpath(SubReasonCode)).click();
			Thread.sleep(1000);
			oPSelFW.reportStepDetails("Sub Reason Code should be clicked ", "Sub Reason Code is successfully clicked ",
					"Pass");
			log.info("Sub Reason Code is successfully clicked ");
			gen.setText(driver, SubReasonCode, XLTestData.get("SubReasonCode").toString());
			// we.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			// Selecting the Item
			driver.findElement(By.xpath("(//span[@role='checkbox'])[2]")).click();
			oPSelFW.reportStepDetails("Item should be clicked ", "Item is successfully clicked ", "Pass");
			log.info("Item is successfully clicked ");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
			// Clicking on Next button
			driver.findElement(By.xpath(Nextbtn)).click();
			oPSelFW.reportStepDetails("Next button should be clicked ", "Next button is successfully clicked ", "Pass");
			log.info("Next button is successfully clicked ");
			Thread.sleep(10000);
			js.executeScript("window.scrollBy(0,500)");
			gen.setText(driver, note, XLTestData.get("Note Value").toString());
			oPSelFW.reportStepDetails("Note value should be Passed ", "Note value is successfully Passed successfully ",
					"Pass");
			log.info("Note value is successfully Passed successfully ");
			Thread.sleep(1000);
			// clicking on confirm button
			driver.findElement(By.xpath(Confirmbtn)).click();
			isAlertPresent(driver);
			oPSelFW.reportStepDetails("Confirm button should be clicked successfully",
					"Confirm button is clicked successfully", "Pass");
			log.info("Confirm button is clicked successfully");
			Thread.sleep(10000);

		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed in the Appeasement",
					"Exception is displayed in AppeaseCustomer", "Fail");
			log.error("Exception is displayed in AppeaseCustomer");
			Assert.assertEquals("Verify if Exception is displayed in the Appeasement",
					"Exception is displayed in AppeaseCustomer");
		}
	}

//Method for Selecting Waive charges
	public void WaiveCharges(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			System.out.println("Appeasing the Customer.........");
//Xpath for clicking on the link appease the customer
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath(Appease));
			action.moveToElement(we).click();
			Thread.sleep(1000);
			action.perform();
			oPSelFW.reportStepDetails("Appease customer link should be clicked ",
					"Appease customer link should be clicked successfully ", "Pass");
			log.info("Appease customer link should be clicked successfully ");
			Thread.sleep(10000);
//selecting the reason code
			driver.findElement(By.xpath(ReasonCode)).click();
			oPSelFW.reportStepDetails("Reason Code should be clicked ", "Reason Code is successfully clicked ", "Pass");
			log.info("Reason Code is successfully clicked ");
			gen.setText(driver, ReasonCode, XLTestData.get("ReasonCode").toString());
//Selecting the sub reason code
			Thread.sleep(1000);
			driver.findElement(By.xpath(sub)).click();
			driver.findElement(By.xpath(SubReasonCode)).click();
			oPSelFW.reportStepDetails("Sub Reason Code should be clicked ", "Sub Reason Code is successfully clicked ",
					"Pass");
			log.info("Sub Reason Code is successfully clicked ");
			gen.setText(driver, SubReasonCode, XLTestData.get("SubReasonCode").toString());
//we.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
//Selecting the Item
			driver.findElement(By.xpath("((((//*[@uid='OLST_listGrid'])[1]/div)[3]/div)[4]/div)")).click();
			oPSelFW.reportStepDetails("Item should be clicked ", "Item is successfully clicked ", "Pass");
			log.info("Item is successfully clicked ");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
//Clicking on Next button
			driver.findElement(By.xpath(Nextbtn)).click();
			oPSelFW.reportStepDetails("Next button should be clicked ", "Next button is successfully clicked ", "Pass");
			log.info("Next button is successfully clicked ");
			gen.waitUntilElementIsPresent(driver, headerIcon);
			Thread.sleep(10000);
//Selecting the waive charges option
			js.executeScript("window.scrollBy(0,-1000)");
			Thread.sleep(7000);
			driver.findElement(By.xpath(Waive)).click();
			oPSelFW.reportStepDetails("Waive order line Mono/PZ charges should be selected ",
					"Waive order line Mono/PZ charges is successfully selected ", "Pass");
			log.info("Waive order line Mono/PZ charges is successfully selected ");
//Clicking on confirm button
			js.executeScript("window.scrollBy(0,700)");
			gen.setText(driver, note, XLTestData.get("Note Value").toString());
			Thread.sleep(2000);
			gen.waitUntilElementIsPresent(driver, headerIcon);
			driver.findElement(By.xpath(Confirmbtn)).click();
			isAlertPresent(driver);
			oPSelFW.reportStepDetails("Confirm button should be clicked successfully",
					"Confirm button is clicked successfully", "Pass");
			log.info("Confirm button is clicked successfully");
			gen.waitUntilElementIsPresent(driver, headerIcon);
			oPSelFW.reportStepDetails("Note value should be Passed ", "Note value is successfully Passed successfully ",
					"Pass");
			log.info("Note value is successfully Passed successfully ");
			/*
			 * driver.findElement(By.xpath(next)).click();
			 * oPSelFW.reportStepDetails("Next button should be clicked "
			 * ,"Next button is successfully clicked " , "Pass"); log.info(
			 */

//Clicking on Appease customer link
			WebElement we1 = driver.findElement(By.xpath(Appease));
			action.moveToElement(we1).click();
			action.perform();
			oPSelFW.reportStepDetails("Appease customer link should be clicked ",
					"Appease customer link should be clicked successfully ", "Pass");
			log.info("Appease customer link should be clicked successfully ");
			Thread.sleep(10000);
		} catch (Exception e) {
//oPSelFW.reportStepDetails("Exception got Occured" ," Exception should got displayed successfully " , "Fail");  log.error(
			System.out.println(e);
		}
	}

//Method for validating previous Appeasements
	public void ValidatingPreviousAppeasements(WebDriver driver, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException {
		try {
			/*
			 * JavascriptExecutor js = (JavascriptExecutor) driver;
			 * js.executeScript("window.scrollBy(0,700)");
			 * System.out.println("Validating Previous Appeasements"); //getting previous
			 * appeasement from UI String PreUI=driver.findElement(By.xpath(Pre)).getText();
			 * System.out.println("Previous Appeasement value is" +PreUI);
			 * oPSelFW.reportStepDetails("Verify previous Appeasement message" +
			 * PreUI," Appeasement message is displayed as " + PreUI, "Pass"); log.info(
			 * //closing button driver.findElement(By.xpath(close)).click();
			 * Thread.sleep(10000); //handelling the alert isAlertPresent(driver);
			 * //driver.findElement(By.xpath(ordercls)).click();
			 * //driver.switchTo().alert().accept();
			 */
			// getting previous appeasement from UI

			String AppeasementAddedBy = "(//*[@uid='pnlNoteCreator']/span)[2]";
			String AppeasementAddedByText = driver.findElement(By.xpath(AppeasementAddedBy)).getText();
			String AppeasementAddedDate = "(//*[@uid='pnlNoteCreator']/span)[4]";
			String AppeasementAddedDateText = driver.findElement(By.xpath(AppeasementAddedDate)).getText();
			String AppeasementChanges = "(//*[@uid='pnlNotesHolder']/div)[4]";
			String AppeasementChangesText = driver.findElement(By.xpath(AppeasementChanges)).getText();
			oPSelFW.reportStepDetails("Verify the appeasement performed details",
					"Appeasement is performed by " + AppeasementAddedByText, "Pass");
			log.info("Appeasement is performed by " + AppeasementAddedByText);
			oPSelFW.reportStepDetails("Verify the appeasement date that is displayed",
					"Appeasement is performed at " + AppeasementAddedDateText, "Pass");
			log.info("Appeasement is performed at " + AppeasementAddedDateText);
			oPSelFW.reportStepDetails("Verify the appeasement changes that are displayed",
					"Appeasement changes that are performed " + AppeasementChangesText, "Pass");
			log.info("Appeasement changes that are performed " + AppeasementChangesText);
		} catch (Exception e) {
			// oPSelFW.reportStepDetails("Exception got Occured" ," Exception should got
			// displayed successfully " , "Fail"); log.error(
			System.out.println(e);
		}
	}

//***************************************************
//Searching with the order number

	public void SearchWithOrderNumber(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW,
			String Order) throws InterruptedException, IOException {
		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			System.out.println("Searching with the order number:" + Order);
//Selecting the Order number text box
//driver.findElement(By.xpath(OrderNumber)).click();
			driver.findElement(By.xpath("//*[@uid='lnk_RT_OrderSearch']/div[2]/div/div/a")).click();
			oPSelFW.reportStepDetails("Order number should be clicked " + Order,
					"Order Number successfully clicked " + Order, "Pass");
			log.info("Order Number successfully clicked " + Order);
			gen.waitUntilElementIsPresent(driver, headerIcon);
//Passing the order number from excel
//gen.setText(driver, OrderNumber, XLTestData.get("orderNumber").toString());
//order search criteria
			driver.findElement(By.xpath(OrderNumber1)).click();
			gen.setText(driver, OrderNumber1, Order);
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-500)");
			Thread.sleep(7000);
			driver.findElement(By.xpath(search)).click();
			gen.waitUntilElementIsPresent(driver, headerIcon);
			oPSelFW.reportStepDetails("Search button should be clicked successfully ",
					"Search button is clicked successfully ", "Pass");
			log.info("Search button is clicked successfully ");
			Thread.sleep(10000);
			oPSelFW.reportStepDetails("Order number should be Passed " + Order,
					"Order Number  should be successfully Passed " + Order, "Pass");
			log.info("Order Number  should be successfully Passed " + Order);
//clicking on Find Order button
//driver.findElement(By.xpath(FindOrder)).click();
			oPSelFW.reportStepDetails("Find Order should be clicked " + Order,
					"Find Order should be successfully clicked " + Order, "Pass");
			log.info("Find Order should be successfully clicked " + Order);
			Thread.sleep(3000);
		} catch (Exception e) {
//oPSelFW.reportStepDetails("Exception got Occured" ," Exception should got displayed successfully " , "Fail");  log.error(
			System.out.println(e);
		}
	}

//***************************************************
// Partial Cancelling the Line level appeasement order

	public void CancelOrder(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			Thread.sleep(4000);
			// Clicking on the Cancel Order
			System.out.println("Cancelling the Order.........");
			// Xpath for clicking on the link appease the customer
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath(cancel));
			action.moveToElement(we).click();
			action.perform();
			Thread.sleep(5000);
			oPSelFW.reportStepDetails("Cancel order should be clicked ", "Cancel order is successfully clicked ",
					"Pass");
			log.info("Cancel order is successfully clicked ");
			// selecting the reason code
			driver.findElement(By.xpath(CanReason)).click();
			Thread.sleep(1000);
			oPSelFW.reportStepDetails("Reason code should be clicked", "Reason code is clicked successfully", "Pass");
			log.info("Reason code is clicked successfully");
			String reason = XLTestData.get("ReasonCode1").toString();
			gen.setText(driver, CanReason, XLTestData.get("ReasonCode1").toString());
			oPSelFW.reportStepDetails("BO Cancellation should be Passed in Reason code",
					"BO Cancellation is successfully Passed in Reason code", "Pass");
			log.info("BO Cancellation is successfully Passed in Reason code");
			// driver.findElement(By.xpath(CanReason)).sendKeys(reason);
			Thread.sleep(1000);
			driver.findElement(By.xpath(subcode)).click();
			// Selecting the sub reason code
			driver.findElement(By.xpath(CanSubreason)).click();
			Thread.sleep(1000);
			oPSelFW.reportStepDetails("SubReason code should be clicked", "SubReason code is clicked successfully",
					"Pass");
			log.info("SubReason code is clicked successfully");
			gen.setText(driver, CanSubreason, XLTestData.get("SubReasonCode1").toString());
			oPSelFW.reportStepDetails("Related NLA  should be Passed in SubReason code",
					"Related NLA is successfully Passed in SubReason code", "Pass");
			log.info("Related NLA is successfully Passed in SubReason code");
			Thread.sleep(2000);
			driver.findElement(By.xpath(chooseoption)).click();
			// Selecting line level order
			driver.findElement(By.xpath(Line)).click();
			oPSelFW.reportStepDetails("Partial line cancellation should be selected",
					"Partial line cancellation is selected successfully", "Pass");
			log.info("Partial line cancellation is selected successfully");

			Thread.sleep(1000);
			// Selecting the item
			driver.findElement(By.xpath("//*[@uid='OLST_listGrid']/div[2]/div[1]/div/table/tbody/tr/td/span")).click();
			oPSelFW.reportStepDetails("Item should be clicked", "item is clicked successfully", "Pass");
			log.info("item is clicked successfully");
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking on next button
			driver.findElement(By.xpath(Nextbtn)).click();
			oPSelFW.reportStepDetails("Next button should be clicked", "Next button is clicked successfully", "Pass");
			log.info("Next button is clicked successfully");
			Thread.sleep(10000);
			// Accepting the Alert
			driver.findElement(By.xpath("(//*[@role='alertdialog']/div[4]/span[2]/span/span/span)[1]")).click();// causes
																												// page
																												// to
			oPSelFW.reportStepDetails("Accepting the Alert should be clicked",
					"Accepting the Alert is clicked successfully", "Pass");
			log.info("Accepting the Alert is clicked successfully");
			// Alert alert = driver.switchTo().alert();
			// String alertText = alert.getText();
			// alert.accept();
			Thread.sleep(20000);
			// clicking on confirm button
			driver.findElement(By.xpath(Confirmbtn)).click();
			oPSelFW.reportStepDetails("Confirm button should be clicked", "Confirm button is clicked successfully",
					"Pass");
			log.info("Confirm button is clicked successfully");

		} catch (Exception e) {
			System.out.println(e);
		}
	}
//***************************************************
//Full Cancellation of the Line level appeasement order

	public void FullCancelOrder(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// Clicking on the Cancel Order
			System.out.println("Cancelling the Order.........");
			// Xpath for clicking on the link appease the customer
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath(cancel));
			action.moveToElement(we).click();
			action.perform();
			Thread.sleep(20000);
			oPSelFW.reportStepDetails("Cancel order should be clicked ", "Cancel order is successfully clicked ",
					"Pass");
			log.info("Cancel order is successfully clicked ");

			// selecting the reason code
			driver.findElement(By.xpath(CanReason)).click();
			oPSelFW.reportStepDetails("Reason code should be clicked", "Reason code is clicked successfully", "Pass");
			log.info("Reason code is clicked successfully");
			gen.setText(driver, CanReason, XLTestData.get("ReasonCode1").toString());
			oPSelFW.reportStepDetails("BO Cancellation should be Passed in Reason code",
					"BO Cancellation is successfully Passed in Reason code", "Pass");
			log.info("BO Cancellation is successfully Passed in Reason code");
			driver.findElement(By.xpath(subcode)).click();

			Thread.sleep(3000);
			// Selecting the sub reason code
			driver.findElement(By.xpath(CanSubreason)).click();
			oPSelFW.reportStepDetails("SubReason code should be clicked", "SubReason code is clicked successfully",
					"Pass");
			log.info("SubReason code is clicked successfully");

			gen.setText(driver, CanSubreason, XLTestData.get("SubReasonCode1").toString());
			driver.findElement(By.xpath(subcode)).click();
			oPSelFW.reportStepDetails("Related NLA  should be Passed in SubReason code",
					"Related NLA is successfully Passed in SubReason code", "Pass");
			log.info("Related NLA is successfully Passed in SubReason code");
			Thread.sleep(2000);
			// Selecting Full Cancellation of the items
			// driver.findElement(By.xpath(FullLine)).click();
			oPSelFW.reportStepDetails("Full line cancellation should be selected",
					"Full line cancellation is selected successfully", "Pass");
			log.info("Full line cancellation is selected successfully");
			Thread.sleep(1000);
			// clicking on next button
			driver.findElement(By.xpath(Nextbtn)).click();
			oPSelFW.reportStepDetails("Next button should be clicked", "Next button is clicked successfully", "Pass");
			log.info("Next button is clicked successfully");

			Thread.sleep(10000);
			// Accepting the Alert
			driver.findElement(By.xpath("(//*[@role='alertdialog']/div[4]/span[2]/span/span/span)[1]")).click();// causes
																												// page
																												// to
			oPSelFW.reportStepDetails("Accepting the Alert should be clicked",
					"Accepting the Alert is clicked successfully", "Pass");
			log.info("Accepting the Alert is clicked successfully");
			Thread.sleep(5000);
			// clicking on confirm button
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,700)");
			driver.findElement(By.xpath(Confirmbtn)).click();
			oPSelFW.reportStepDetails("Confirm button should be clicked", "Confirm button is clicked successfully",
					"Pass");
			log.info("Confirm button is clicked successfully");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

//***************************************************
//validating status of order cancellation

	public void ValidatingStatus(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW,
			String Order) throws InterruptedException, IOException, SQLException {
		try {
			// validating of status of the order
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// getting values from database
			DataBase1 db1 = new DataBase1();
			String ironment = XLTestData.get("WCCENV").toString();
			String HostName, UserName, DBPassword, DBSID;
			if (ironment.contains("STST2")) {
				HostName = gen.getPropertyValue("stst2.db.hostname");
				UserName = gen.getPropertyValue("stst2.db.user");
				DBPassword = gen.getPropertyValue("stst2.db.password");
				DBSID = gen.getPropertyValue("stst2.db.sid");
				oPSelFW.reportStepDetails("STST2 ironment should be selected",
						"STST2 ironment is successfully selected", "Pass");
				log.info("STST2 ironment is successfully selected");
			} else if (ironment.contains("EQA3")) {
				HostName = gen.getPropertyValue("eqa3.db.hostname");
				UserName = gen.getPropertyValue("eqa3.db.user");
				DBPassword = gen.getPropertyValue("eqa3.db.password");
				DBSID = gen.getPropertyValue("eqa3.db.sid");
				oPSelFW.reportStepDetails("STST1 ironment should be selected",
						"STST2 ironment is successfully selected", "Pass");
				log.info("STST2 ironment is successfully selected");
			} else {
				HostName = gen.getPropertyValue("stst.db.hostname");
				UserName = gen.getPropertyValue("stst.db.user");
				DBPassword = gen.getPropertyValue("stst.db.password");
				DBSID = gen.getPropertyValue("stst.db.sid");
			}
			System.out.println("Before Database");
			Connection conn = db1.ConnectToDB(HostName, DBSID, UserName, DBPassword, oPSelFW);
			oPSelFW.reportStepDetails("Database connection should be done", "Database connection is successfully done",
					"Pass");
			log.info("Database connection is successfully done");
			Statement stat = conn.createStatement();
			ResultSet rs3 = null;
			BigDecimal orderHeaderKey = null;
			System.out.println("Before Database");

			if (ironment.contains("STST"))
				// query from retrieving the status from database
				rs3 = stat.executeQuery(
						"select Description from Yfs_status where Status in (select Max(Status) from yantra_owner.yfs_order_release_Status where Order_Header_Key in (select Order_Header_Key from  yantra_owner.YFS_ORDER_HEADER WHERE ORDER_NO = '"
								+ Order + "'))");

			oPSelFW.reportStepDetails("Execution of Query should be done", "Execution of Query is successfully done",
					"Pass");
			log.info("Execution of Query is successfully done");
			if (rs3.next() == true) {

				// Status from DB
				String StatusDB = rs3.getString("DESCRIPTION");
				System.out.println("Cancellation status from DB: " + StatusDB);
				Thread.sleep(10000);
				oPSelFW.reportStepDetails("Status from DB should be get " + StatusDB,
						"Status from DB is successfully get " + StatusDB, "Pass");
				log.info("Status from DB is successfully get " + StatusDB);

				// Status from UI
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,700)");
				String StatusUI = driver.findElement(By.xpath(StaUI)).getText();
				System.out.println("Cancellation status from UI:" + StatusUI);

				oPSelFW.reportStepDetails("Status from UI should be get " + StatusUI,
						"Status from UI is successfully get " + StatusUI, "Pass");
				log.info("Status from UI is successfully get " + StatusUI);
				if (StatusDB.contains(StatusUI))

				{
					Thread.sleep(5000);
					oPSelFW.reportStepDetails("Order Summary Page: Status is displayed as " + StatusUI,
							" Order Summary Page: Status is displayed as " + StatusUI, "Pass");
					log.info(" Order Summary Page: Status is displayed as " + StatusUI);
				}

				else {

					oPSelFW.reportStepDetails("Order Summary Page: Status is displayed as " + StatusUI,
							" Order Summary Page: Status is displayed as " + StatusUI, "Fail");
					log.error(" Order Summary Page: Status is displayed as " + StatusUI);
				}

				Thread.sleep(20000);
				// closing of current window
				driver.findElement(By.xpath(close)).click();
				// handelling the alert
				isAlertPresent(driver);
				Thread.sleep(5000);
				// closing of current window
				driver.findElement(By.xpath(ordercls)).click();
				Thread.sleep(10000);

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void ValidatingOrderNumberStatus(WebDriver driver, Map<String, String> XLTestData, String getOrderNumber,
			ProlificsSeleniumAPI oPSelFW) throws InterruptedException {
		try {
			// validating of status of the order

			// getting values from database
			DataBase db1 = new DataBase();
			String ironment = XLTestData.get("WCCENV").toString();
			String HostName, UserName, DBPassword, DBSID;
			if (ironment.contains("STST2")) {
				HostName = gen.getPropertyValue("stst2.db.hostname");
				UserName = gen.getPropertyValue("stst2.db.user");
				DBPassword = gen.getPropertyValue("stst2.db.password");
				DBSID = gen.getPropertyValue("stst2.db.sid");
			} else if (ironment.contains("EQA3")) {
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

			if (ironment.contains("STST2"))
				// query from retrieving the status from database
				rs3 = stat.executeQuery(
						"select * from yantra_stst_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");

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

				if (StatusDB.contains(StatusUI)) {
					oPSelFW.reportStepDetails("Order Type from UI is " + StatusUI,
							"Verified Order Type from DB is " + StatusDB, "Pass");
					log.info("Verified Order Type from DB is " + StatusDB);

				}

				else {

					oPSelFW.reportStepDetails("Order NuTypember from UI is " + StatusUI,
							"Verified Order Type from DB is " + StatusDB, "Fail");
					log.error("Verified Order Type from DB is " + StatusDB);

				}

				Thread.sleep(5000);

				String conceptfromUI = "//*[@uid='lblEnterpriseCodeDisplay']/div/div/div/span";
				// XLTestData.get("Concept").toString();

				String concept = driver.findElement(By.xpath(conceptfromUI)).getText();
				System.out.println("ORDER_NO status from UI:" + conceptfromUI);

				if (concept.contains("Pottery Barn Teen")) {
					// Concept = "PT";
				} else if (concept.contains("Williams-Sonoma")) {
					// Concept = "WS";

				}

				else if (concept.contains("West Elm")) {
					// Concept = "WE";

				} else if (concept.contains("Pottery Barn Kids")) {
					// Concept = "PK";

				} else if (concept.contains("Pottery Barn")) {
					// Concept = "PB";

				}

				else if (concept.contains("Pottery Barn Teen Mark and Graham")) {
					// Concept = "MG";

				}

				// StatusUI = Concept;

				StatusDB = rs3.getString("ENTERPRISE_KEY");
				System.out.println("ENTERPRISE_KEY status from DB:" + StatusDB);
				// Status from UI

				System.out.println("Concept status from UI:" + StatusUI);

				if (StatusDB.contains(StatusUI)) {
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

				if (StatusDB.contains(StatusUI)) {
					oPSelFW.reportStepDetails("Channel type from UI is " + StatusUI,
							"Verified Channel type from DB is " + StatusDB, "Pass");
					log.info("Verified Channel type from DB is " + StatusDB);

				}

				else {

					oPSelFW.reportStepDetails("Channel type from UI is " + StatusUI,
							"Verified Channel Type from DB is " + StatusDB, "Fail");
					log.error("Verified Channel type from DB is " + StatusDB);

				}

				StatusDB = rs3.getString("CUSTOMER_PHONE_NO");
				System.out.println("ENTRY_TYPE status from DB:" + StatusDB);
				// Status from UI

				String PhonefromUI = driver.findElement(By.xpath(phone)).getText();
				String phoneUI = PhonefromUI.replaceAll("-", "");
				StatusUI = phoneUI.replaceAll("\\p{P}", "");
				System.out.println("Phone number: " + StatusUI);
				// StatusUI = driver.findElement(By.xpath(phone)).getText();
				System.out.println("ENTRY TYPE status from UI:" + StatusUI);

				if (StatusDB.contains(StatusUI)) {
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
		} // try

		catch (Exception e) {
			System.out.println(e);
		}
	}

	public String fetchOrderNumberFromDB(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException {
		String ordernumberDB = null;
		String OrderNumber[] = null;
		int rowcount = 0;
		try {
			// validating of status of the order

			// getting values from database
			DataBase db1 = new DataBase();
			String ironment = XLTestData.get("WCCENV").toString();
			String HostName, UserName, DBPassword, DBSID;
			if (ironment.contains("STST2")) {
				HostName = gen.getPropertyValue("stst2.db.hostname");
				UserName = gen.getPropertyValue("stst2.db.user");
				DBPassword = gen.getPropertyValue("stst2.db.password");
				DBSID = gen.getPropertyValue("stst2.db.sid");
			} else if (ironment.contains("EQA3")) {
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
			oPSelFW.reportStepDetails("DataBase connection should be done",
					"DataBase connection established successfully", "Pass");
			log.info("DataBase connection established successfully");
			Statement stat = conn.createStatement();
			ResultSet rs3 = null;
			BigDecimal orderHeaderKey = null;
			System.out.println("Before Database");

			if (ironment.contains("STST2")) {
				// query from retrieving the status from database
				rs3 = stat.executeQuery("select * from yantra_stst_owner.yfs_order_header where payment_status='PAID' "
						+ "and order_header_key like '2020%' and order_no not in (select order_no from yantra_stst_owner.yfs_order_invoice "
						+ "where invoice_type='CREDIT_MEMO' and order_header_key like '2020%') and order_type in('PHONEORDER','OTHERS') "
						+ "AND ORDER_HEADER_KEY NOT IN (select ORDER_HEADER_KEY from yantra_stst_owner.yfs_order_release_Status WHERE STATUS >  ('3200'))");
				oPSelFW.reportStepDetails("Execution of SQL query should be done",
						"Execution of SQL query is successfully done", "Pass");
				log.info("Execution of SQL query is successfully done");

				if (rs3.next() == true) {

					{
						// rowcount++;
						// Status from DB
						ordernumberDB = rs3.getString("ORDER_NO");
						// OrderNumber[rowcount] = ordernumberDB;
						oPSelFW.reportStepDetails("Order number from DB should be" + ordernumberDB,
								"Order number from DB is" + ordernumberDB, "Pass");
						log.info("Order number from DB is" + ordernumberDB);
						System.out.println("ORDER_NO status from DB: " + ordernumberDB);
						// Status from UI

					}

				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return ordernumberDB;

	}

	public void LoginToWCC(WebDriver driver, String UserName, String Password, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		try {
			gen.setText(driver, txtUserName, UserName);
			oPSelFW.reportStepDetails("User Name should be Passed ", UserName + "User Name is successfully Passed ",
					"Pass");
			log.info(UserName + "User Name is successfully Passed ");
			gen.setText(driver, txtPassword, Password);
			oPSelFW.reportStepDetails("Password should be Passed ", UserName + "Password is successfully Passed ",
					"Pass");
			log.info(UserName + "Password is successfully Passed ");
			gen.clickElement(driver, btnLogin);
			oPSelFW.reportStepDetails("Login should be clicked ", btnLogin + "Login is successfully Clicked ", "Pass");
			log.info(btnLogin + "Login is successfully Clicked ");
			gen.waitUntilElementIsPresent(driver, headerIcon);
			if (driver.findElements(By.xpath("//span[@class='idxHeaderUserIcon']")).size() != 0) {
				oPSelFW.reportStepDetails("User should be logged in ", UserName + "user is successfully Logged in ",
						"Pass");
				log.info(UserName + "user is successfully Logged in ");
			} else {
				oPSelFW.reportStepDetails("User should be logged in ", UserName + "user is not successfully Logged in ",
						"Pass");
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

	public void WaiveChargesOrderLine(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			System.out.println("Appeasing the Customer.........");
			// Xpath for clicking on the link appease the customer
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath(Appease));
			action.moveToElement(we).click();
			Thread.sleep(5000);
			action.perform();
			oPSelFW.reportStepDetails("Appease customer link should be clicked ",
					" Appease customer link should be clicked successfully ", "Pass");
			log.info(" Appease customer link should be clicked successfully ");
			Thread.sleep(25000);
			// selecting the reason code
			driver.findElement(By.xpath(ReasonCode)).click();
			oPSelFW.reportStepDetails("Reason Code should be clicked ", " Reason Code is successfully clicked ",
					"Pass");
			log.info(" Reason Code is successfully clicked ");
			gen.setText(driver, ReasonCode, XLTestData.get("ReasonCode").toString());
			// Selecting the sub reason code
			Thread.sleep(3000);
			driver.findElement(By.xpath(sub)).click();
			driver.findElement(By.xpath(SubReasonCode)).click();
			oPSelFW.reportStepDetails(" Sub Reason Code should be clicked ",
					" Sub Reason Code is successfully clicked ", "Pass");
			log.info(" Sub Reason Code is successfully clicked ");
			gen.setText(driver, SubReasonCode, XLTestData.get("SubReasonCode").toString());
			// we.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			// Selecting the Item
			driver.findElement(By.xpath("((((//*[@uid='OLST_listGrid'])[1]/div)[3]/div)[4]/div)")).click();
			oPSelFW.reportStepDetails("Item should be clicked ", " Item is successfully clicked ", "Pass");
			log.info(" Item is successfully clicked ");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
			// Clicking on Next button
			driver.findElement(By.xpath(Nextbtn)).click();
			oPSelFW.reportStepDetails("Next button should be clicked ", "Next button is successfully clicked ", "Pass");
			log.info("Next button is successfully clicked ");
			gen.waitUntilElementIsPresent(driver, headerIcon);
			Thread.sleep(20000);
			// Selecting the waive charges option
			js.executeScript("window.scrollBy(0,-1000)");
			Thread.sleep(7000);
			driver.findElement(By.xpath(Waive)).click();
			oPSelFW.reportStepDetails("Waive order line Shipping charges should be selected ",
					" Waive order line Shipping charges is successfully selected ", "Pass");
			log.info(" Waive order line Shipping charges is successfully selected ");
			// Clicking on confirm button
			js.executeScript("window.scrollBy(0,700)");
			gen.setText(driver, note, XLTestData.get("Note Value").toString());
			driver.findElement(By.xpath(Confirmbtn)).click();
			Thread.sleep(10000);
			oPSelFW.reportStepDetails("Note value should be Passed ", "Note value is successfully Passed successfully ",
					"Pass");
			log.info("Note value is successfully Passed successfully ");
			/*
			 * driver.findElement(By.xpath(next)).click();
			 * oPSelFW.reportStepDetails("Next button should be clicked "
			 * ,"Next button is successfully clicked " , "Pass"); log.info(
			 */
			Thread.sleep(20000);
			// Clicking on Appease customer link
			WebElement we1 = driver.findElement(By.xpath(Appease));
			action.moveToElement(we1).click();
			action.perform();
			oPSelFW.reportStepDetails("Appease customer link should be clicked ",
					" Appease customer link should be clicked successfully ", "Pass");
			log.info(" Appease customer link should be clicked successfully ");
			Thread.sleep(20000);
		} catch (Exception e) {
			// oPSelFW.reportStepDetails("Exception got Occured" ," Exception should got
			// displayed successfully " , "Fail"); log.error(
			System.out.println(e);
		}
	}

//method for reward earning
	public void LoyaltyEnrollment(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		try {
			// click on enroll customer
			gen.waitUntilElementIsPresent(driver, headerIcon);
			driver.findElement(By.xpath("(//a[contains(text(),'Enroll Customer')])[2]")).click();
			oPSelFW.reportStepDetails("Enroll Customer link should be clicked ",
					"Screen should navigate to 'The Key Enrollment' tab", "Pass");
			log.info("Screen should navigate to 'The Key Enrollment' tab");
			gen.waitUntilElementIsPresent(driver, headerIcon);
			Thread.sleep(5000);
			oPSelFW.reportStepDetails("Enter valid Phone number for which Loyalty Account already exist",
					"User should be able to provide the invalid Email Address ", "Pass");
			log.info("User should be able to provide the invalid Email Address ");
			oPSelFW.reportStepDetails("Enter valid Email address for which Loyalty Account already exist",
					"User should be able to provide the invalid Email Address ", "Pass");
			log.info("User should be able to provide the invalid Email Address ");
			oPSelFW.reportStepDetails("Enter valid First and Last name for which Loyalty Account already exist",
					"User should be able to provide the First and Last name", "Pass");
			log.info("User should be able to provide the First and Last name");
			// click on enroll button
			driver.findElement(By.xpath("//*[@uid='extn_btnApply']")).click();
			oPSelFW.reportStepDetails("Enroll button should be clicked", "Enroll button is successfully clicked",
					"Pass");
			log.info("Enroll button is successfully clicked");
			Thread.sleep(10000);
			String key = driver.findElement(By.xpath("//*[@uid='singlemessagelabelpopup']")).getText();
			String keycal = "The Key Enrollment Failed. Loyalty Account Already Exist For This Phone Number";
			// verification of the error message
			if (key.contains(keycal)) {
				oPSelFW.reportStepDetails("Verify Message should be displayed: " + key,
						"Message is successfully displayed:" + key, "Pass");
				log.info("Message is successfully displayed:" + key);

			}
			// clicking on closing enroll button
			driver.findElement(By.xpath("//*[@uid='extn_btnCancel']")).click();
			oPSelFW.reportStepDetails("The key Enroll window should be closed",
					"The key Enroll window is closed successfully", "Pass");
			log.info("The key Enroll window is closed successfully");
			Thread.sleep(1000);
			// closing of window
			driver.findElement(By.xpath(close)).click();
			// Accepting the alert
			driver.findElement(By.xpath("//*[@uid='Popup_btnNext']")).click();
			oPSelFW.reportStepDetails("Accept the Alert should be clicked", "Accept the Alert is clicked successfully",
					"Pass");
			log.info("Accept the Alert is clicked successfully");

			Thread.sleep(5000);

		} catch (Exception e) {
			// oPSelFW.reportStepDetails("Exception got Occured" ," Exception should got
			// displayed successfully " , "Fail"); log.error(
			System.out.println(e);
		}
	}

//method for multiple appeasements
	public void MultipleAppeasement(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException {
		try {
			// clicking the appease customer link for order line number1
			gen.waitUntilElementIsPresent(driver, headerIcon);
			System.out.println("Appeasing the Customer.........");
			// Xpath for clicking on the link appease the customer
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath(Appease));
			action.moveToElement(we).click();
			Thread.sleep(5000);
			action.perform();
			oPSelFW.reportStepDetails("Appease customer link should be clicked ",
					" Appease customer link should be clicked successfully ", "Pass");
			log.info(" Appease customer link should be clicked successfully ");
			Thread.sleep(25000);
			// selecting the reason code
			driver.findElement(By.xpath(ReasonCode)).click();
			oPSelFW.reportStepDetails("Reason Code should be clicked ", " Reason Code is successfully clicked ",
					"Pass");
			log.info(" Reason Code is successfully clicked ");
			gen.setText(driver, ReasonCode, XLTestData.get("ReasonCode").toString());
			// Selecting the sub reason code
			Thread.sleep(3000);
			driver.findElement(By.xpath(sub)).click();
			driver.findElement(By.xpath(SubReasonCode)).click();
			oPSelFW.reportStepDetails("Sub Reason Code should be clicked ", " Sub Reason Code is successfully clicked ",
					"Pass");
			log.info(" Sub Reason Code is successfully clicked ");
			gen.setText(driver, SubReasonCode, XLTestData.get("SubReasonCode").toString());
			Thread.sleep(2000);
			// Selecting the Item
			driver.findElement(By.xpath("((((//*[@uid='OLST_listGrid'])[1]/div)[3]/div)[4]/div)[1]")).click();
			oPSelFW.reportStepDetails("Item should be clicked ", " Item is successfully clicked ", "Pass");
			log.info(" Item is successfully clicked ");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
			// Clicking on Next button
			driver.findElement(By.xpath(Nextbtn)).click();
			oPSelFW.reportStepDetails("Next button should be clicked ", "Next button is successfully clicked ", "Pass");
			log.info("Next button is successfully clicked ");
			gen.waitUntilElementIsPresent(driver, headerIcon);
			Thread.sleep(20000);
			// select waive shipping charges and gift wrap charges
			driver.findElement(By.xpath("(((//*[@uid='checkbox_list'])[1]/div)[2]/div/div)[2]")).click();
			oPSelFW.reportStepDetails("Waive Order Line Gift Wrap charges checkbox should be selected",
					"Waive Order line Gift Wrap charges checkbox is selected successfully", "Pass");
			log.info("Waive Order line Gift Wrap charges checkbox is selected successfully");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(((//*[@uid='checkbox_list'])[1]/div)[2]/div/div)[3]")).click();
			oPSelFW.reportStepDetails("Waive Order Line shipping charges checkbox should be selected",
					"Waive Order Line shipping charges checkbox is selected successfully", "Pass");
			log.info("Waive Order Line shipping charges checkbox is selected successfully");
			Thread.sleep(2000);
			js.executeScript("window.scrollBy(0,700)");
			driver.findElement(By.xpath(Confirmbtn)).click();
			Thread.sleep(20000);
			oPSelFW.reportStepDetails("Note value should be Passed ", "Note value is successfully Passed successfully ",
					"Pass");
			log.info("Note value is successfully Passed successfully ");

			// clicking the Appease customer order line number 2
			System.out.println("Appeasing the Customer.........");
			// Xpath for clicking on the link appease the customer
			Actions action1 = new Actions(driver);
			WebElement we1 = driver.findElement(By.xpath(Appease));
			action.moveToElement(we1).click();
			Thread.sleep(5000);
			action.perform();
			oPSelFW.reportStepDetails("Appease customer link should be clicked ",
					" Appease customer link should be clicked successfully ", "Pass");
			log.info(" Appease customer link should be clicked successfully ");
			Thread.sleep(25000);
			// selecting the reason code
			driver.findElement(By.xpath(ReasonCode)).click();
			oPSelFW.reportStepDetails("Reason Code should be clicked ", " Reason Code is successfully clicked ",
					"Pass");
			log.info(" Reason Code is successfully clicked ");
			gen.setText(driver, ReasonCode, XLTestData.get("ReasonCode").toString());
			// Selecting the sub reason code
			Thread.sleep(3000);
			driver.findElement(By.xpath(sub)).click();
			driver.findElement(By.xpath(SubReasonCode)).click();
			oPSelFW.reportStepDetails("Sub Reason Code should be clicked ", " Sub Reason Code is successfully clicked ",
					"Pass");
			log.info(" Sub Reason Code is successfully clicked ");
			gen.setText(driver, SubReasonCode, XLTestData.get("SubReasonCode").toString());
			Thread.sleep(2000);
			// Selecting the Item
			driver.findElement(By.xpath("((((//*[@uid='OLST_listGrid'])[1]/div)[3]/div)[4]/div)[2]")).click();
			oPSelFW.reportStepDetails("Item should be clicked ", " Item is successfully clicked ", "Pass");
			log.info(" Item is successfully clicked ");
			js.executeScript("window.scrollBy(0,500)");
			// Clicking on Next button
			driver.findElement(By.xpath(Nextbtn)).click();
			oPSelFW.reportStepDetails("Next button should be clicked ", "Next button is successfully clicked ", "Pass");
			log.info("Next button is successfully clicked ");
			gen.waitUntilElementIsPresent(driver, headerIcon);
			Thread.sleep(20000);
			// select waive shipping charges and waive mono/PZ charges
			driver.findElement(By.xpath("(((//*[@uid='checkbox_list'])[1]/div)[2]/div/div)[1]")).click();
			oPSelFW.reportStepDetails("Waive Mono/PZ charges checkbox should be selected",
					"Waive Mono/PZ charges checkbox is selected successfully", "Pass");
			log.info("Waive Mono/PZ charges checkbox is selected successfully");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(((//*[@uid='checkbox_list'])[1]/div)[2]/div/div)[3]")).click();
			oPSelFW.reportStepDetails("Waive Order Line shipping charges checkbox should be selected",
					"Waive Order Line shipping charges checkbox is selected successfully", "Pass");
			log.info("Waive Order Line shipping charges checkbox is selected successfully");
			Thread.sleep(2000);
			js.executeScript("window.scrollBy(0,700)");
			driver.findElement(By.xpath(Confirmbtn)).click();
			Thread.sleep(20000);
			oPSelFW.reportStepDetails("Note value should be Passed ", "Note value is successfully Passed successfully ",
					"Pass");
			log.info("Note value is successfully Passed successfully ");

			// clicking the Appease customer order line number 3
			System.out.println("Appeasing the Customer.........");
			// Xpath for clicking on the link appease the customer
			Actions action2 = new Actions(driver);
			WebElement we2 = driver.findElement(By.xpath(Appease));
			action.moveToElement(we2).click();
			Thread.sleep(5000);
			action.perform();
			oPSelFW.reportStepDetails("Appease customer link should be clicked ",
					" Appease customer link should be clicked successfully ", "Pass");
			log.info(" Appease customer link should be clicked successfully ");
			Thread.sleep(25000);
			// selecting the reason code
			driver.findElement(By.xpath(ReasonCode)).click();
			oPSelFW.reportStepDetails("Reason Code should be clicked ", " Reason Code is successfully clicked ",
					"Pass");
			log.info(" Reason Code is successfully clicked ");
			gen.setText(driver, ReasonCode, XLTestData.get("ReasonCode").toString());
			// Selecting the sub reason code
			Thread.sleep(3000);
			driver.findElement(By.xpath(sub)).click();
			driver.findElement(By.xpath(SubReasonCode)).click();
			oPSelFW.reportStepDetails("Sub Reason Code should be clicked ", " Sub Reason Code is successfully clicked ",
					"Pass");
			log.info(" Sub Reason Code is successfully clicked ");
			gen.setText(driver, SubReasonCode, XLTestData.get("SubReasonCode").toString());
			Thread.sleep(2000);
			// Selecting the Item
			driver.findElement(By.xpath("((((//*[@uid='OLST_listGrid'])[1]/div)[3]/div)[4]/div)[3]")).click();
			oPSelFW.reportStepDetails("Item should be clicked ", " Item is successfully clicked ", "Pass");
			log.info(" Item is successfully clicked ");
			js.executeScript("window.scrollBy(0,500)");
			// Clicking on Next button
			driver.findElement(By.xpath(Nextbtn)).click();
			oPSelFW.reportStepDetails("Next button should be clicked ", "Next button is successfully clicked ", "Pass");
			log.info("Next button is successfully clicked ");
			gen.waitUntilElementIsPresent(driver, headerIcon);
			Thread.sleep(20000);
			// select waive shipping charges and waive mono/PZ charges
			driver.findElement(By.xpath("(((//*[@uid='checkbox_list'])[1]/div)[2]/div/div)[1]")).click();
			oPSelFW.reportStepDetails("Waive Mono/PZ charges checkbox should be selected",
					"Waive Mono/PZ charges checkbox is selected successfully", "Pass");
			log.info("Waive Mono/PZ charges checkbox is selected successfully");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(((//*[@uid='checkbox_list'])[1]/div)[2]/div/div)[3]")).click();
			oPSelFW.reportStepDetails("Waive Order Line shipping charges checkbox should be selected",
					"Waive Order Line shipping charges checkbox is selected successfully", "Pass");
			log.info("Waive Order Line shipping charges checkbox is selected successfully");
			Thread.sleep(2000);
			js.executeScript("window.scrollBy(0,700)");
			driver.findElement(By.xpath(Confirmbtn)).click();
			Thread.sleep(20000);
			oPSelFW.reportStepDetails("Note value should be Passed ", "Note value is successfully Passed successfully ",
					"Pass");
			log.info("Note value is successfully Passed successfully ");

			// clicking the Appease customer order line number 4
			System.out.println("Appeasing the Customer.........");
			// Xpath for clicking on the link appease the customer
			Actions action3 = new Actions(driver);
			WebElement we3 = driver.findElement(By.xpath(Appease));
			action.moveToElement(we3).click();
			Thread.sleep(5000);
			action.perform();
			oPSelFW.reportStepDetails("Appease customer link should be clicked ",
					" Appease customer link should be clicked successfully ", "Pass");
			log.info(" Appease customer link should be clicked successfully ");
			Thread.sleep(25000);
			// selecting the reason code
			driver.findElement(By.xpath(ReasonCode)).click();
			oPSelFW.reportStepDetails("Reason Code should be clicked ", " Reason Code is successfully clicked ",
					"Pass");
			log.info(" Reason Code is successfully clicked ");
			gen.setText(driver, ReasonCode, XLTestData.get("ReasonCode").toString());
			// Selecting the sub reason code
			Thread.sleep(3000);
			driver.findElement(By.xpath(sub)).click();
			driver.findElement(By.xpath(SubReasonCode)).click();
			oPSelFW.reportStepDetails("Sub Reason Code should be clicked ", " Sub Reason Code is successfully clicked ",
					"Pass");
			log.info(" Sub Reason Code is successfully clicked ");
			gen.setText(driver, SubReasonCode, XLTestData.get("SubReasonCode").toString());
			Thread.sleep(2000);
			// Selecting the Item
			driver.findElement(By.xpath("((((//*[@uid='OLST_listGrid'])[1]/div)[3]/div)[4]/div)[4]")).click();
			oPSelFW.reportStepDetails("Item should be clicked ", " Item is successfully clicked ", "Pass");
			log.info(" Item is successfully clicked ");
			js.executeScript("window.scrollBy(0,700)");
			// Clicking on Next button
			driver.findElement(By.xpath(Nextbtn)).click();
			oPSelFW.reportStepDetails("Next button should be clicked ", "Next button is successfully clicked ", "Pass");
			log.info("Next button is successfully clicked ");
			gen.waitUntilElementIsPresent(driver, headerIcon);
			Thread.sleep(20000);
			// select waive shipping charges and waive mono/PZ charges
			driver.findElement(By.xpath("(((//*[@uid='checkbox_list'])[1]/div)[2]/div/div)[1]")).click();
			oPSelFW.reportStepDetails("Waive Mono/PZ charges checkbox should be selected",
					"Waive Mono/PZ charges checkbox is selected successfully", "Pass");
			log.info("Waive Mono/PZ charges checkbox is selected successfully");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(((//*[@uid='checkbox_list'])[1]/div)[2]/div/div)[3]")).click();
			oPSelFW.reportStepDetails("Waive Order Line shipping charges checkbox should be selected",
					"Waive Order Line shipping charges checkbox is selected successfully", "Pass");
			log.info("Waive Order Line shipping charges checkbox is selected successfully");
			Thread.sleep(2000);
			js.executeScript("window.scrollBy(0,700)");
			driver.findElement(By.xpath(Confirmbtn)).click();
			Thread.sleep(20000);
			oPSelFW.reportStepDetails("Note value should be Passed ", "Note value is successfully Passed successfully ",
					"Pass");
			log.info("Note value is successfully Passed successfully ");

			// clicking the Appease customer order line number 5
			System.out.println("Appeasing the Customer.........");
			// Xpath for clicking on the link appease the customer
			Actions action4 = new Actions(driver);
			WebElement we4 = driver.findElement(By.xpath(Appease));
			action.moveToElement(we4).click();
			Thread.sleep(5000);
			action.perform();
			oPSelFW.reportStepDetails("Appease customer link should be clicked ",
					" Appease customer link should be clicked successfully ", "Pass");
			log.info(" Appease customer link should be clicked successfully ");
			Thread.sleep(25000);
			// selecting the reason code
			driver.findElement(By.xpath(ReasonCode)).click();
			oPSelFW.reportStepDetails("Reason Code should be clicked ", " Reason Code is successfully clicked ",
					"Pass");
			log.info(" Reason Code is successfully clicked ");
			gen.setText(driver, ReasonCode, XLTestData.get("ReasonCode").toString());
			// Selecting the sub reason code
			Thread.sleep(3000);
			driver.findElement(By.xpath(sub)).click();
			driver.findElement(By.xpath(SubReasonCode)).click();
			oPSelFW.reportStepDetails("Sub Reason Code should be clicked ", " Sub Reason Code is successfully clicked ",
					"Pass");
			log.info(" Sub Reason Code is successfully clicked ");
			gen.setText(driver, SubReasonCode, XLTestData.get("SubReasonCode").toString());
			Thread.sleep(2000);
			// Selecting the Item
			driver.findElement(By.xpath("((((//*[@uid='OLST_listGrid'])[1]/div)[3]/div)[4]/div)[1]")).click();
			oPSelFW.reportStepDetails("Item should be clicked ", " Item is successfully clicked ", "Pass");
			log.info(" Item is successfully clicked ");
			js.executeScript("window.scrollBy(0,700)");
			// Clicking on Next button
			driver.findElement(By.xpath(Nextbtn)).click();
			oPSelFW.reportStepDetails("Next button should be clicked ", "Next button is successfully clicked ", "Pass");
			log.info("Next button is successfully clicked ");
			gen.waitUntilElementIsPresent(driver, headerIcon);
			Thread.sleep(20000);
			// select waive shipping charges and waive mono/PZ charges
			driver.findElement(By.xpath("(((//*[@uid='checkbox_list'])[1]/div)[2]/div/div)[1]")).click();
			oPSelFW.reportStepDetails("Waive Mono/PZ charges checkbox should be selected",
					"Waive Mono/PZ charges checkbox is selected successfully", "Pass");
			log.info("Waive Mono/PZ charges checkbox is selected successfully");
			Thread.sleep(2000);
			oPSelFW.reportStepDetails("Waive Order Line shipping charges checkbox should be selected",
					"Waive Order Line shipping charges checkbox is selected successfully", "Pass");
			log.info("Waive Order Line shipping charges checkbox is selected successfully");
			Thread.sleep(2000);
			js.executeScript("window.scrollBy(0,700)");
			driver.findElement(By.xpath(Confirmbtn)).click();
			Thread.sleep(20000);
			oPSelFW.reportStepDetails("Note value should be Passed ", "Note value is successfully Passed successfully ",
					"Pass");
			log.info("Note value is successfully Passed successfully ");

			// closing of current window
			driver.findElement(By.xpath(close)).click();
			// handelling the alert
			isAlertPresent(driver);
			oPSelFW.reportStepDetails("Current window should be closed", "Current window is closed successfully",
					"Pass");
			log.info("Current window is closed successfully");
			Thread.sleep(2000);

			// closing of order search window
			driver.findElement(By.xpath(ordercls)).click();
			// handelling the alert
			isAlertPresent(driver);
			oPSelFW.reportStepDetails("Order search window should be closed",
					"Order search window is closed successfully", "Pass");
			log.info("Order search window is closed successfully");
			Thread.sleep(2000);

		} catch (Exception e) {
			// oPSelFW.reportStepDetails("Exception got Occured" ," Exception should got
			// displayed successfully " , "Fail"); log.error(
			System.out.println(e);
		}
	}

//validating the credit memo from DB
	public void ValidateCreditMemo(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW,
			String Order) throws InterruptedException, IOException, SQLException {
		try {

			// getting values from database
			DataBase1 db1 = new DataBase1();
			String ironment = XLTestData.get("WCCENV").toString();
			String HostName, UserName, DBPassword, DBSID;
			if (ironment.contains("STST2")) {
				HostName = gen.getPropertyValue("stst2.db.hostname");
				UserName = gen.getPropertyValue("stst2.db.user");
				DBPassword = gen.getPropertyValue("stst2.db.password");
				DBSID = gen.getPropertyValue("stst2.db.sid");
				oPSelFW.reportStepDetails("STST2 ironment should be selected",
						"STST2 ironment is successfully selected", "Pass");
				log.info("STST2 ironment is successfully selected");
			} else if (ironment.contains("EQA3")) {
				HostName = gen.getPropertyValue("eqa3.db.hostname");
				UserName = gen.getPropertyValue("eqa3.db.user");
				DBPassword = gen.getPropertyValue("eqa3.db.password");
				DBSID = gen.getPropertyValue("eqa3.db.sid");
				oPSelFW.reportStepDetails("STST1 ironment should be selected",
						"STST2 ironment is successfully selected", "Pass");
				log.info("STST2 ironment is successfully selected");
			} else {
				HostName = gen.getPropertyValue("stst.db.hostname");
				UserName = gen.getPropertyValue("stst.db.user");
				DBPassword = gen.getPropertyValue("stst.db.password");
				DBSID = gen.getPropertyValue("stst.db.sid");
			}
			System.out.println("Before Database");
			Connection conn = db1.ConnectToDB(HostName, DBSID, UserName, DBPassword, oPSelFW);
			oPSelFW.reportStepDetails("Database connection should be done", "Database connection is successfully done",
					"Pass");
			log.info("Database connection is successfully done");
			Statement stat = conn.createStatement();
			ResultSet rs3 = null;
			ResultSet rs4 = null;
			BigDecimal orderHeaderKey = null;
			System.out.println("Before Database");

			if (ironment.contains("STST"))
				// query from retrieving the status from database
				rs3 = stat.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + Order + "'");

			if (rs3.next() == true) {
				orderHeaderKey = rs3.getBigDecimal("ORDER_HEADER_KEY");
				System.out.println("orderHeaderKey  :" + orderHeaderKey);
				rs4 = stat.executeQuery(
						"Select PAYMENT_KEY, PAYMENT_TYPE, TOTAL_REFUNDED_AMOUNT FROM yantra_owner.yfs_payment where order_header_key='"
								+ orderHeaderKey + "'");
				System.out.println("ppppp :" + rs4);

				oPSelFW.reportStepDetails("Execution of Query should be done",
						"Execution of Query is successfully done", "Pass");
				log.info("Execution of Query is successfully done");

				// Status from DB
				String paymentDB = rs3.getString("PAYMENT_KEY");
				System.out.println("Invoice_type is: " + paymentDB);

				oPSelFW.reportStepDetails("Invoice type should be " + paymentDB,
						"Verified the credit memo issued on the Order number " + paymentDB, "Pass");
				log.info("Verified the credit memo issued on the Order number " + paymentDB);

			}
		} catch (Exception e) {
			// oPSelFW.reportStepDetails("Exception got Occured" ," Exception should got
			// displayed successfully " , "Fail"); log.error(
			System.out.println(e);
		}
	}

//method for PLCC card information
	public String enterPLCCCardInformation(WebDriver driver, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws Exception {
		try {
			if (XLTestData.get("PaymentType").toString().toUpperCase().contains("PLCC")) {
				gen.waitUntilElementIsPresent(driver, headerIcon);
				oPSelFW.reportStepDetails("Select Payment Type", "PLCC is selected as the Payment Type", "Pass");
				log.info("PLCC is selected as the Payment Type");
				gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment", oPSelFW);
				Thread.sleep(5000);
				String CardNumber = XLTestData.get("CardNumber").toString();
				driver.findElement(By.xpath(PaymentType)).clear();
				Thread.sleep(3000);
				driver.findElement(By.xpath(PaymentType)).sendKeys("PLCC");
				Thread.sleep(3000);
				oPSelFW.reportStepDetails("Select Payment Type", "PLCC is selected as the Payment Type", "Pass");
				log.info("PLCC is selected as the Payment Type");
				driver.findElement(By.xpath(PaymentType)).sendKeys(Keys.TAB);
				Thread.sleep(3000);
				By frame = By.xpath("//iframe[contains(@id,'padssIframe') and normalize-space(@title)='Card number:']");
				driver.switchTo().frame(driver.findElement(frame));
				WebElement eleCardNumber = driver.findElement(By.xpath("//input[@id='ssdcsDataToTokenize']"));
				eleCardNumber.clear();
				Thread.sleep(3000);
				eleCardNumber.sendKeys(CardNumber);
				oPSelFW.reportStepDetails("Enter PLCC Card", CardNumber + " is entered as the Card", "Pass");
				log.info(CardNumber + " is entered as the Card");
				Thread.sleep(3000);
				// eleCardNumber.sendKeys(Keys.TAB);

				driver.switchTo().defaultContent();
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,600)");
				Thread.sleep(3000);
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
				Thread.sleep(5000);
				if (gen.existsElement(driver, By.xpath(AddApply)))
					gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
			}
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if error message is displayed",
					"Error in Entering the PLCC Card Information", "Fail");
			log.error("Error in Entering the PLCC Card Information");
			oPSelFW.stopReport();
			Assert.assertTrue(false);
		}
		return "";
	}

//method for adding xpath

	public String SetItemID(WebDriver driver, String ItemID, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		try {
			gen1.waitUntilElementIsClickable(driver, 10000, ItemIDXpath);
			// String OrderType = XLTestData.get("OrderType").toString();
			WebElement element = driver.findElement(ItemIDXpath);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click()", element);
			Thread.sleep(5000);
			gen.setText(driver, ItemId, ItemID, "Item Id", oPSelFW);
			Thread.sleep(5000);
			driver.findElement(By.xpath(ItemId)).sendKeys(Keys.TAB);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@uid='extn_text_Catalog_Code']/div/div/div/input")).clear();
			Thread.sleep(5000);
			int i = 0;
			while (!driver
					.findElements(By.xpath("//*[@id=\"sc_plat_dojo_widgets_ScreenDialogUnderlay_1\"]/div[2]/div[1]"))
					.isEmpty() && i <= 60) {
				Thread.sleep(5000);
				i++;
			}
			gen.waitnclickElement(driver, By.xpath(AddBtn), "Add", oPSelFW);
			String ErrorExists = "";
			if (driver.findElements(By.xpath("//*[@id='idx_widget__MasterHoverHelpTooltip_0']/div[2]/div"))
					.size() > 0) {
				By ErrorMessXpath = By.xpath("//*[@id='idx_widget__MasterHoverHelpTooltip_0']/div[2]/div");
				element = driver.findElement(ErrorMessXpath);
				executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click()", element);
				Thread.sleep(5000);
			}
			// *[@id="widget_idx_form_TextBox_30"]/div[2]/div[3]/div
			if (driver.findElements(By.xpath("//*[@id='idx_widget__MasterHoverHelpTooltip_0']/div[2]/div"))
					.size() > 0) {
				ErrorExists = driver.findElement(By.xpath("//*[@id='idx_widget__MasterHoverHelpTooltip_0']/div[2]/div"))
						.getText();
			}
			if (ErrorExists.length() > 0)
				return ("Invalid Item Icon is displayed for " + ItemID);
			else if (driver.findElements(By.xpath("//*[@id='idx_widget_SingleMessage_0']")).size() > 0) {
				String errorMessageExists = driver.findElement(By.xpath("//*[@id='idx_widget_SingleMessage_0']"))
						.getText();
				if (errorMessageExists.contains("Unable to add this item"))
					return (errorMessageExists);
				else
					return ("");
			} else
				return ("");
		} catch (Exception e) {
			oPSelFW.reportStepDetails("Error in Adding the Items", "Error in Adding the Item", "Fail");
			log.error("Error in Adding the Item");
		}
		return ItemID;
	}

	public String enterItemDetails(WebDriver driver, ProlificsSeleniumAPI oPSelFW, Map<String, String> XLTestData)
			throws Exception {
		String ItemsAdded = "";
		try {
			String ItemIDs = XLTestData.get("ItemID").toString();
			String Items[] = ItemIDs.split(";");
			String Eachs = XLTestData.get("Each").toString();
			String EachsList[] = Eachs.split(";");
			for (int ItemNum = 0; ItemNum < Items.length; ItemNum++) {
				String ItemAdded = SetItemID(driver, Items[ItemNum], oPSelFW);
				String Error = "";
				DataBase db = new DataBase();
				String ironment = XLTestData.get("WCCENV").toString();
				String HostName, UserName, DBPassword, DBSID;
				if (ironment.contains("STST2")) {
					HostName = gen.getPropertyValue("stst2.db.hostname");
					UserName = gen.getPropertyValue("stst2.db.user");
					DBPassword = gen.getPropertyValue("stst2.db.password");
					DBSID = gen.getPropertyValue("stst2.db.sid");
				} else if (ironment.contains("EQA3")) {
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
				Connection conn = db.ConnectToDB(HostName, DBSID, UserName, DBPassword, oPSelFW);
				Statement stat = conn.createStatement();
				ResultSet rs = null;
				System.out.println("Before Database");
				if (ironment.contains("STST2"))
					rs = stat.executeQuery("select count(*) as count from YANTRA_STST_OWNER.YFS_ITEM WHERE ITEM_ID = '"
							+ Items[ItemNum] + "'");
				else
					rs = stat.executeQuery("select count(*) as count from YANTRA_OWNER.YFS_ITEM WHERE ITEM_ID = '"
							+ Items[ItemNum] + "'");
				rs.next();
				String ItemCount = rs.getString("count");
				if (Integer.parseInt(ItemCount) > 0) {
					oPSelFW.reportStepDetails("Verify if Item is present in Database",
							"Item " + Items[ItemNum].trim() + " is present in " + ironment + " database", "Pass");
					log.info("Item " + Items[ItemNum].trim() + " is present in " + ironment + " database");
					if (driver.findElements(By.xpath("//*[@uid='systemMessagePanel']/div/div/span")).size() > 0) {
						Error = driver.findElement(By.xpath("//*[@uid='systemMessagePanel']/div/div/span"))
								.getAttribute("innerText");
						ItemAdded = Error;
					}
					if (ItemAdded.contains("Unable to add this item") || ItemAdded.contains("Invalid Item")
							|| ItemAdded.contains("Item is no longer available") || ItemAdded.contains("Error")
							|| ItemAdded.contains("Error description not available")) {
						oPSelFW.reportStepDetails("Verify if error message is displayed in 'Add Item' Screen",
								ItemAdded + " message is displayed in 'Add Items' Screen", "Fail");
						log.error(ItemAdded + " message is displayed in 'Add Items' Screen");
						ItemsAdded = ItemsAdded + ItemAdded;
					} else {
						oPSelFW.reportStepDetails("Verify if item is added to the cart",
								Items[ItemNum].trim() + " item is added to the cart", "Pass");
						log.info(Items[ItemNum].trim() + " item is added to the cart");
						ItemsAdded = ItemsAdded + "Items Added";
						setQuantity(driver, EachsList[ItemNum], oPSelFW);
						Thread.sleep(1000);
					}
				} else {
					oPSelFW.reportStepDetails("Verify if item is present in DataBase",
							"Item " + Items[ItemNum] + " is not present to the Database", "Fail");
					log.error("Item " + Items[ItemNum] + " is not present to the Database");
					ItemsAdded = ItemsAdded + "Item " + Items[ItemNum].trim() + " is not Added";
				}
			}
		} catch (SQLException e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if User is able to connect to Database",
					"Error in connecting to the Database", "Fail");
			log.error("Error in connecting to the Database");
			Assert.assertTrue(false);
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if User is able to add Items", "Error in Adding the Items is displayed",
					"Fail");
			log.error("Error in Adding the Items is displayed");
			oPSelFW.stopReport();
			Assert.assertTrue(false);
		}
		return (ItemsAdded);
	}

	public void setQuantity(WebDriver driver, String Quantity, ProlificsSeleniumAPI oPSelFW) throws Exception {
		gen.setText(driver, EachX, Quantity, "Quantity", oPSelFW);
		driver.findElement(By.xpath(EachX)).sendKeys(Keys.TAB);
		Thread.sleep(2000);
	}

//click on BillTOLink
	public void enterAddressDetails(WebDriver driver, String AddressType, ProlificsSeleniumAPI oPSelFW,
			Map<String, String> XLTestData) throws InterruptedException, FileNotFoundException, IOException {
		String TitleOfPage = getTitleOfPage(driver);
		WebElement ApplyElement = null;
		try {
			gen.waitUntilElementIsPresent(driver, By.xpath(BillTo));
			if (TitleOfPage.contains("Add Address")) {
				if (driver.findElements(By.xpath(BillTo)).size() == 0) {
					oPSelFW.reportStepDetails("Verify if Address Page is displayed", "Address Page is not displayed",
							"Fail");
					log.error("Address Page is not displayed");
					driver.quit();
					Assert.assertTrue(false);
				}

				if (AddressType.contains("BillTo")) {
					final By BillToXPath = By.xpath(BillTo);
					gen.waitnclickElement(driver, BillToXPath, "Bill To", oPSelFW);
					enterBillToAddressDetails(driver, XLTestData, oPSelFW);
				}
				if (AddressType.contains("ShipTo")) {
					if (!XLTestData.get("TestCaseTitle").toString().toLowerCase().contains("sub order")) {
						final By ShipToXPath = By.xpath(ShipTo);
						gen.waitnclickElement(driver, ShipToXPath, "Ship To", oPSelFW);
						enterShipToAddressDetails(driver, XLTestData, oPSelFW);
					}
				}

				int i = 1;
				WebElement NextElement = driver.findElement(By.xpath(Nextbtn));
				while (!NextElement.isDisplayed() && !NextElement.isEnabled() && i <= 20) {
					Thread.sleep(5000);
					i++;
				}
				if (!driver.findElements(By.xpath("//div[starts-with(@id, 'idx_widget_SingleMessage')]/div"))
						.isEmpty()) {
					String ErrorMessage = driver
							.findElement(By.xpath("//div[starts-with(@id, 'idx_widget_SingleMessage')]/div")).getText();
					if (!driver.findElements(By.xpath("//div[starts-with(@id, 'idx_widget_SingleMessage')]/div"))
							.isEmpty()) {
						if (ErrorMessage.trim().length() > 0)
							oPSelFW.reportStepDetails("Verify if Error message is displayed ",
									ErrorMessage + " is displayed", "Fail");
						log.error(ErrorMessage + " is displayed");

						int NumberofCheckBoxes = driver
								.findElements(By.xpath("//*[starts-with(@id, 'idx_form_CheckBox_')]")).size();
						for (int CheckBoxNum = 1; CheckBoxNum <= NumberofCheckBoxes; CheckBoxNum++) {
							By checkBoxXpath = By
									.xpath("(//*[starts-with(@id, 'idx_form_CheckBox_')])[" + CheckBoxNum + "]");
							if (driver.findElement(checkBoxXpath).isDisplayed()
									&& driver.findElement(checkBoxXpath).isEnabled()) {
								// driver.findElement(checkBoxXpath).click();
								gen.waitnclickElement(driver, checkBoxXpath, "Checkbox", oPSelFW);
							}

							// driver.findElement(checkBoxXpath).submit();
						}
						Thread.sleep(5000);
						if (driver.findElements(XPathApplybtn).size() > 0) {
							gen.shortwaitnclickElement(driver, XPathApplybtn, "Apply", oPSelFW);
							Thread.sleep(5000);
						}
					}
				}
				if (driver.findElements(XPathApplybtn).size() > 0) {
					if (ApplyElement.isDisplayed() && ApplyElement.isEnabled()) {
						gen.shortwaitnclickElement(driver, XPathApplybtn, "Apply", oPSelFW);
					}
					if (driver.findElements(XPathApplybtn).size() > 0) {
						if (ApplyElement.isDisplayed() && ApplyElement.isEnabled()) {
							gen.shortwaitnclickElement(driver, XPathApplybtn, "Apply", oPSelFW);
						}
					}
				}
				if (driver.findElements(By.xpath("//span[contains(@id,'dijit_Dialog_')]")).size() > 0)
					gen.shortwaitnclickElement(driver, XpathCancelbtn, "Cancel", oPSelFW);
			} else {
				oPSelFW.reportStepDetails("Verify if Address Page is displayed or not",
						"Add Address Page is not displayed", "Fail");
				log.error("Add Address Page is not displayed");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			oPSelFW.reportStepDetails("Verify if Exception is displayed", "Exception " + e + " is not displayed",
					"Fail");
			log.error("Exception " + e + " is not displayed");
			Assert.assertTrue(false);
		}
	}

	public String getTitleOfPage(WebDriver driver) {
		return (driver.findElement(By.xpath("(//*[@uid='lb_screenSubTitle'])[2]")).getText());
	}

	public void enterShipToAddressDetails(WebDriver driver, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) {
		WebElement ApplyElement = null;
		try {
			String shipToAddDetails = XLTestData.get("NewShipToaddress").toString();
			if (!shipToAddDetails.contains("NoValueCellTypeBLANK")) {
				gen1.waitUntilElementIsClickable(driver, 10000, FirstName);
				String ShipAddDetails[] = shipToAddDetails.split(":");
				String ShipAddDet[] = shipToAddDetails.split("::");
				gen.setText(driver, FirstName, ShipAddDetails[1], "Ship To First Name", oPSelFW);
				gen.setText(driver, LastNme, ShipAddDetails[2], "Ship To Last Name", oPSelFW);
				gen.setText(driver, EmailAddress, ShipAddDetails[4], "Ship to Email Address", oPSelFW);
				gen.setText(driver, FirstName, ShipAddDetails[1], "Ship To First Name", oPSelFW);
				WebElement DayPhn = driver.findElement(By.xpath(DayPhone));
				DayPhn.clear();
				DayPhn.sendKeys(ShipAddDet[1]);
				DayPhn.sendKeys(Keys.ENTER);
				Thread.sleep(5000);
				// Address Line
				gen.setText(driver, AdressLine1, ShipAddDetails[5], "Address Line 1", oPSelFW);
				Thread.sleep(5000);
				WebElement PostalCod = driver.findElement(By.xpath(PostalCode));
				PostalCod.clear();
				PostalCod.sendKeys(ShipAddDet[2]);
				PostalCod.sendKeys(Keys.TAB);
				Thread.sleep(5000);
				ApplyElement = driver.findElement(XPathApplybtn);
				gen.shortwaitnclickElement(driver, XPathApplybtn, "Apply", oPSelFW);
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void enterBillToAddressDetails(WebDriver driver, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws InterruptedException {
		WebElement ApplyElement = null;
		try {
			gen1.waitUntilElementIsClickable(driver, 20000,FirstName);
			gen.setText(driver, FirstName, XLTestData.get("BillToFirstName").toString(), "Bill To First Name", oPSelFW);
			gen.setText(driver, LastNme, XLTestData.get("BillToLastName").toString(), "Bill To Last Name", oPSelFW);
			gen.setText(driver, EmailAddress, XLTestData.get("BillToEmailAddress").toString(), "Bill To Email Address",
					oPSelFW);
			gen.setTextAndHitEnter(driver, DayPhone, XLTestData.get("BillToDayPhone").toString(), "Bill To Day Phone",
					oPSelFW);
			// Address Line
			gen.setText(driver, AdressLine1, XLTestData.get("BillToAddressLine1").toString(), "Bill To Address Line 1",
					oPSelFW);
			WebElement PostalCod = driver.findElement(By.xpath(PostalCode));
			PostalCod.sendKeys(XLTestData.get("BillToZip").toString());
			PostalCod.sendKeys(Keys.TAB);
			Thread.sleep(10000);
			ApplyElement = driver.findElement(XPathApplybtn);
			gen.shortwaitnclickElement(driver, XPathApplybtn, "Apply", oPSelFW);
			Thread.sleep(10000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// method for alert handeling
	public boolean isAlertPresent(WebDriver driver) throws IOException, InterruptedException {

		boolean presentFlag = false;

		try {

			// Check the presence of alert
			Alert alert = driver.switchTo().alert();
			// Alert present; set the flag
			presentFlag = true;
			// if present consume the alert
			Thread.sleep(10000);
			alert.accept();
			String Error = alert.getText();
			// ( Now, click on ok or cancel button )
			oPSelFW.reportStepDetails("Accepting the Alert should be clicked",
					"Accepting the Alert is clicked successfully", "Pass");
			log.info("Accepting the Alert is clicked successfully");

		} catch (NoAlertPresentException ex) {
			// Alert not present
			// ex.printStackTrace();
			System.out.println("Alert not found");
		}

		return presentFlag;
	}

	public String getOrderNumberFromDB(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException {
		String ordernumberDB = null;

		try {
			// validating of status of the order

			// getting values from database
			DataBase db1 = new DataBase();
			String ironment = XLTestData.get("WCCENV").toString();
			String HostName, UserName, DBPassword, DBSID;
			if (ironment.contains("STST2")) {
				HostName = gen.getPropertyValue("stst2.db.hostname");
				UserName = gen.getPropertyValue("stst2.db.user");
				DBPassword = gen.getPropertyValue("stst2.db.password");
				DBSID = gen.getPropertyValue("stst2.db.sid");
			} else if (ironment.contains("EQA3")) {
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
			oPSelFW.reportStepDetails("DataBase connection should be done",
					"DataBase connection established successfully", "Pass");
			log.info("DataBase connection established successfully");
			Statement stat = conn.createStatement();
			ResultSet rs3 = null;
			BigDecimal orderHeaderKey = null;
			System.out.println("Before Database");

			if (ironment.contains("STST"))
				// query from retrieving the status from database
				rs3 = stat.executeQuery(
						"select * from yantra_stst_owner.YFS_ORDER_HEADER where ORDER_NO LIKE '9%' AND ORDER_HEADER_KEY not in (select ORDER_HEADER_KEY from yantra_stst_owner.yfs_order_release_Status where STATUS <> '1100')");
			oPSelFW.reportStepDetails("Execution of SQL query should be done",
					"Execution of SQL query is successfully done", "Pass");
			log.info("Execution of SQL query is successfully done");

			if (rs3.next() == true) {

				{
					// rowcount++;
					// Status from DB
					ordernumberDB = rs3.getString("ORDER_NO");
					// OrderNumber[rowcount] = ordernumberDB;
					oPSelFW.reportStepDetails("Order number from DB should be" + ordernumberDB,
							"Order number from DB is" + ordernumberDB, "Pass");
					log.info("Order number from DB is" + ordernumberDB);
					System.out.println("ORDER_NO status from DB: " + ordernumberDB);
					// Status from UI

				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return ordernumberDB;

	}

//closing of current window
	public void closeWindow(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		// closing of current window
		driver.findElement(By.xpath(close)).click();
		// handelling the alert
		isAlertPresent(driver);
		Thread.sleep(5000);
		// closing of current window
		driver.findElement(By.xpath(ordercls)).click();
		Thread.sleep(10000);
	}

//method for fetching the return oredr from DB
	public String fetchReturnOrderNumberFromDB(WebDriver driver, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws InterruptedException {
		String ordernumberDB = null;
		String OrderNumber[] = null;
		int rowcount = 0;
		try {
			// validating of status of the order

			// getting values from database
			DataBase db1 = new DataBase();
			String ironment = XLTestData.get("WCCENV").toString();
			String HostName, UserName, DBPassword, DBSID;
			if (ironment.contains("STST2")) {
				HostName = gen.getPropertyValue("stst2.db.hostname");
				UserName = gen.getPropertyValue("stst2.db.user");
				DBPassword = gen.getPropertyValue("stst2.db.password");
				DBSID = gen.getPropertyValue("stst2.db.sid");
			} else if (ironment.contains("EQA3")) {
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
			oPSelFW.reportStepDetails("DataBase connection should be done",
					"DataBase connection established successfully", "Pass");
			log.info("DataBase connection established successfully");
			Statement stat = conn.createStatement();
			ResultSet rs3 = null;
			BigDecimal orderHeaderKey = null;
			System.out.println("Before Database");

			if (ironment.contains("STST2")) {
				// query from retrieving the status from database
				rs3 = stat.executeQuery("Select * from Yfs_order_header where order_no like 'W19%'");
				oPSelFW.reportStepDetails("Execution of SQL query should be done",
						"Execution of SQL query is successfully done", "Pass");
				log.info("Execution of SQL query is successfully done");

				if (rs3.next() == true) {

					{
						// rowcount++;
						// Status from DB
						ordernumberDB = rs3.getString("ORDER_NO");
						// OrderNumber[rowcount] = ordernumberDB;
						oPSelFW.reportStepDetails("Order number from DB should be" + ordernumberDB,
								"Order number from DB is" + ordernumberDB, "Pass");
						log.info("Order number from DB is" + ordernumberDB);
						System.out.println("ORDER_NO status from DB: " + ordernumberDB);
						// Status from UI

					}

				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return ordernumberDB;

	}

//create order for Appeasement functionality
	public void CreateOrderForAppeasement(HashMap<String, String> XLTestData, WebDriver driver,
			ProlificsSeleniumAPI oPSelFW) throws Exception {
		if (XLTestData != null) {
			InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			try {
				gen.waitUntilElementIsPresent(driver, "//span[@class='idxHeaderUserIcon']");
				ordercreate1.clickOnOrderLink(driver, oPSelFW);
				ordercreate1.ConceptTypeScroll(driver, XLTestData.get("Concept").toString(), oPSelFW);
				ordercreate1.clickOnUnRegisteredCustomerLink(driver, oPSelFW);
				// ordercreate1.selectOrderType(driver, XLTestData.get("OrderType").toString(),
				// XLTestData.get("StoreNumber").toString(), oPSelFW);
				String ItemAdded = ordercreate1.enterItemDetails(driver, oPSelFW, XLTestData);
				if (!ItemAdded.contains("Items Added")) {
					getOrderNumber = ItemAdded;
				} else {
					// String OrderTotal = ordercreate.validateOrderTotal(driver, basetest);
					ordercreate1.ClickonNext(driver, oPSelFW);
					Thread.sleep(1000);
					if (driver.findElements(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")).size() > 0) {

						String errorMessage = driver
								.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]"))
								.getAttribute("innerText");
						if (errorMessage.contains("There are errors on the page. You cannot proceed until you fix them")
								|| errorMessage.toLowerCase().contains("error")) {
							ordercreate1.ClickonNext(driver, oPSelFW);
						}
						errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]"))
								.getAttribute("innerText");
						if (errorMessage != null) {
							if (errorMessage.contains("invalid identifier")
									|| errorMessage.toLowerCase().contains("error")
									|| errorMessage.toLowerCase().contains("complete required personalization")
									|| errorMessage.toLowerCase().contains("cannot include a variant")) {
								oPSelFW.reportStepDetails("Verify error message", errorMessage + " is displayed",
										"Pass");
								log.info(errorMessage + " is displayed");
								errorDisplayed = true;
								getOrderNumber = errorMessage;
							}
						}
					}
					if (errorDisplayed == false) {
						ordercreate1.enterAddressDetails(driver, "BillTo", oPSelFW, XLTestData);
						ordercreate1.enterAddressDetails(driver, "ShipTo", oPSelFW, XLTestData);
						// String OrderCreatedSucc = ordercreate1.enterCustomerType(driver, oPSelFW,
						// element, XLTestData);
						// if(!OrderCreatedSucc.contains("successfully") ||
						// (OrderCreatedSucc.trim().length() == 0))
						{
							Thread.sleep(10000);
							WebDriverWait wait = new WebDriverWait(driver, 5000);
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
									.xpath("(//span[contains(@id,'Button') and normalize-space(text())='Next'])[2]")));
							ordercreate1.ClickonNext(driver, oPSelFW);
							Thread.sleep(10000);
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
									.xpath("(//span[contains(@id,'Button') and normalize-space(text())='Next'])[1]")));
							ordercreate1.ClickonNext(driver, oPSelFW);
							Thread.sleep(5000);
							String errorInPayment = ordercreate1.AddPaymentMethod(driver, oPSelFW, element, XLTestData);
							if (errorInPayment.trim().length() == 0) {
								WebDriverWait wait1 = new WebDriverWait(driver, 500);
								wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
										By.xpath("//*[@uid='pnlPrimaryInfo']/div/table/tbody/tr/td/span")));
								getOrderNumber = driver
										.findElement(By.xpath("//*[@uid='pnlPrimaryInfo']/div/table/tbody/tr/td/span"))
										.getText();
								oPSelFW.reportStepDetails("Get Order Number", getOrderNumber + " is generated", "Pass");
								log.info(getOrderNumber + " is generated");
							} else
								getOrderNumber = errorInPayment;

						}

					} else {

					}
				}
			} catch (Exception e) {
			}
		}

	}

//***************************************************
//Searching with the order number

	public void SearchWithReturnOrder(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW,
			String getOrderNumber) throws InterruptedException, IOException {
		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			Thread.sleep(5000);
			System.out.println("Searching with the order number:" + getOrderNumber);
//Selecting the Order number text box
			driver.findElement(By.xpath("//*[@uid='lnk_RT_ReturnSearch']/div[2]/div/div/a")).click();
			oPSelFW.reportStepDetails("Return Order link should be clicked ", "Return Order link successfully clicked ",
					"Pass");
			log.info("Return Order link successfully clicked");
			gen.waitUntilElementIsPresent(driver, headerIcon);
//Passing the order number from excel
//gen.setText(driver, OrderNumber, XLTestData.get("orderNumber").toString());
//order search criteria
			driver.findElement(By.xpath(OrderNumber1)).click();
			gen.setText(driver, OrderNumber1, getOrderNumber);
			Thread.sleep(7000);
			driver.findElement(By.xpath(search)).click();
			gen.waitUntilElementIsPresent(driver, headerIcon);
			oPSelFW.reportStepDetails("Search button should be clicked successfully ",
					"Search button is clicked successfully ", "Pass");
			log.info("Search button is clicked successfully ");
			Thread.sleep(2000);
			oPSelFW.reportStepDetails("Order number should be Passed ", "Order Number should be successfully Passed ",
					"Pass");
			log.info("Order Number should be successfully Passed ");
//clicking on Find Order button
//driver.findElement(By.xpath(FindOrder)).click();
			oPSelFW.reportStepDetails("Find Order should be clicked ", "Find Order should be successfully clicked ",
					"Pass");
			log.info("Find Order should be successfully clicked ");
			Thread.sleep(3000);
		} catch (Exception e) {
//oPSelFW.reportStepDetails("Exception got Occured" ," Exception should got displayed successfully " , "Fail");  log.error(
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if exception is displayed", "Exception is displayed in Search With Return Order",
					"Pass");
			log.error("Exception is displayed in Search With Return Order");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Search With Return Order");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreen(ResultSet rs2, ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			rs2.next();
			rs2.getString("PAYMENT_TYPE");
			String refundDB = rs2.getString("total_refunded_amount");
			oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
					"Pass");
			log.info("Verify Refunded amount " + refundDB);
			rs2.next();
			String PrevirefundDB = rs2.getString("total_refunded_amount");

			// verification of refund
			String Refund = driver.findElement(By.xpath(refund)).getText();
			String refundUI = Refund.replaceAll("[$,]", "");
			System.out.println("Refund amount is: " + refundUI);

			// verification of previously refunded
			String Ref = driver.findElement(By.xpath(preRefund)).getText();
			String prerefundUI = Ref.replaceAll("[$,]", "");
			System.out.println("Refund amount is: " + prerefundUI);

			if (prerefundUI.contains(PrevirefundDB))

			{
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);
			}

			else {

				oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Fail");
				log.error("Verify previously Refunded amount " + prerefundUI);
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}

	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreen(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refundDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[1]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4452, -4706]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				System.out.println("Payment type is: " + PaymentUI);

				if (PaymentDB.contains(PaymentUI))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
					Assert.assertEquals("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//create order for Appeasement functionality
	public void CreateOrderForPLCC(HashMap<String, String> XLTestData, WebDriver driver, ProlificsSeleniumAPI oPSelFW)
			throws Exception {
		gen.waitUntilElementIsPresent(driver, headerIcon);		ordercreate1.clickOnOrderLink(driver, oPSelFW);
		ordercreate1.ConceptTypeScroll(driver, XLTestData.get("Concept").toString(), oPSelFW);
		ordercreate1.clickOnUnRegisteredCustomerLink(driver, oPSelFW);
		String ItemIDs = XLTestData.get("ItemID").toString();
		String Items[] = ItemIDs.split(";");
		String Eachs = XLTestData.get("Each").toString();
		for (int ItemNum = 0; ItemNum < Items.length; ItemNum++) {
			SetItemID(driver, Items[ItemNum], oPSelFW);

			enterItemDetails(driver, oPSelFW, XLTestData);
			ordercreate1.ClickonNext(driver, oPSelFW);
			Thread.sleep(7000);
			if (driver.findElements(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")).size() > 0) {

				String errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]"))
						.getAttribute("innerText");
				if (errorMessage.contains("There are errors on the page. You cannot proceed until you fix them")
						|| errorMessage.toLowerCase().contains("error")) {
					ordercreate1.ClickonNext(driver, oPSelFW);
				}
				errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]"))
						.getAttribute("innerText");
				if (errorMessage != null) {
					if (errorMessage.contains("invalid identifier") || errorMessage.toLowerCase().contains("error")
							|| errorMessage.toLowerCase().contains("complete required personalization")
							|| errorMessage.toLowerCase().contains("cannot include a variant")) {
						oPSelFW.reportStepDetails("Verify error message", errorMessage + " is displayed", "Pass");
						log.info(errorMessage + " is displayed");
						errorDisplayed = true;
						// getOrderNumber = errorMessage;
					}
				}
			}
			if (errorDisplayed == false) {
				enterAddressDetails(driver, "BillTo", oPSelFW, XLTestData);

				enterAddressDetails(driver, "ShipTo", oPSelFW, XLTestData);
				Thread.sleep(5000);
				// click on next
				ordercreate1.ClickonNext(driver, oPSelFW);
				Thread.sleep(5000);
				// click on next
				ordercreate1.ClickonNext(driver, oPSelFW);
				Thread.sleep(5000);
				// click on next
				// ordercreate1.ClickonNext(driver, oPSelFW);
				Thread.sleep(10000);
				// calling payment method
				enterPLCCCardInformation(driver, XLTestData, oPSelFW);

				// clicking on confirm button
				Thread.sleep(5000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,600)");
				Thread.sleep(5000);
				String Confirmbtn = "(//*[@uid='confirmBttn'])";
				driver.findElement(By.xpath(Confirmbtn)).click();
				CreateOrderPage ordercreate = new CreateOrderPage();
				String errorExists = ordercreate.checkErrorExists(driver);
				if (errorExists.contains("Credit Card Declined")) {
					oPSelFW.reportStepDetails("Verify if exception is displayed in Payment",
							errorExists + " is displayed", "Fail");
					log.error(errorExists + " is displayed");
					Assert.assertEquals("Verify if exception is displayed in Payment", errorExists + " is displayed");
				} else
				{
					oPSelFW.reportStepDetails("Confirm button should be clicked successfully",
							"Confirm button is clicked successfully", "Pass");
					log.info("Confirm button is clicked successfully");
					Thread.sleep(25000);
				}
			}
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForReplacement(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				rs.next();
				String ordDB = rs.getString("ORDER_NO");
				String TotalDB = rs.getString("TOTAL_AMOUNT");

				// getting order number from UI
				String orde = driver.findElement(By.xpath(orderNumberText)).getText();
				String ordUI = orde.replaceAll("[Order # ,]", "");
				System.out.println("order number:" + ordUI);

				if (ordUI.contains(ordDB))
				{
					oPSelFW.reportStepDetails("Verify Order number from DB in Payment Inquiry screen " + ordDB,
							"Verify Order number from UI in Payment Inquiry screen " + ordDB, "Pass");
					log.info("Verify Order number from UI in Payment Inquiry screen " + ordDB);
				}
				else {
					oPSelFW.reportStepDetails(
							"Verify Order number from DB is failed in Payment inquiry screen " + ordDB,
							"Verify Order number from UI is failed in Payment Inquiry screen" + ordDB, "Fail");
					log.error("Verify Order number from UI is failed in Payment Inquiry screen" + ordDB);
				}

				// verification payment type amount
				String tot = "((//div[@uid='extn_OriginalAmount'])/div)[2]";
				String totUI = driver.findElement(By.xpath(tot)).getText();
				String totaUI = totUI.replaceAll("[$,]", "");
				System.out.println("Payment type is: " + totaUI);

				if (TotalDB.contains(totaUI))
				{
					oPSelFW.reportStepDetails("Verify Order Total amount in Payment Inquiry screen " + totaUI,
							"Verify Order Total amount in Payment Inquiry screen " + totaUI, "Pass");
					log.info("Verify Order Total amount in Payment Inquiry screen " + totaUI);
				}
				else {
					oPSelFW.reportStepDetails("Verify Order Total amount in Payment Inquiry screen is failed " + totaUI,
							"Verify Order Total amount in Payment Inquiry screen is failed" + totaUI, "Fail");
					log.error("Verify Order Total amount in Payment Inquiry screen is failed" + totaUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return order summary screen
	public void validateReturnOrderSummaryscreenFOROPTORO(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String PrevirefundDB = rs1.getString("total_refunded_amount");

				// verification of refund
				String hh = "(//span[@class='scDataValue'])[7]";
				String Refund = driver.findElement(By.xpath(hh)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(PrevirefundDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount " + refundUI, "Fail");
					log.error("Verify Refunded amount " + refundUI);
				}

			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating payment inquiry screen
//validate payment inquiry screen
	public void validatePaymentInquiryscreenForOPTORO(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[2]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refundDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[1]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4312,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				System.out.println("Payment type is: " + PaymentUI);

				if (PaymentDB.contains(PaymentUI))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenEcomReturn(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[2]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))
				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[2]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4333,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				System.out.println("Payment type is: " + PaymentUI);

				if (PaymentDB.contains(PaymentUI))
				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}
				else {
					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenMultipleQty(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[1]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4706,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				System.out.println("Payment type is: " + PaymentUI);
				if (PaymentDB.contains(PaymentUI))
				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}
				else {
					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC1(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		ResultSet rs3 = null;
		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);
			rs3 = paymentOrder(oPSelFW, getOrderNumber, XLTestData, driver);

			rs3.next();
			String PaymentDB = rs3.getString("PAYMENT_TYPE");
			String refundDB = rs3.getString("total_refunded_amount");
			rs3.next();
			String rrr = rs3.getString("total_refunded_amount");

			// verification ofrefund amount
			String Refund = driver.findElement(By.xpath(
					"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[2]"))
					.getText();
			String refundd = Refund.replaceAll("[$,]", "");
			String refundUI = refundd.replaceAll("[(),]", "");
			System.out.println("Refund amount is: " + refundUI);

			if (refundUI.contains(rrr))
			{
				oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
						"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
				log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
			}

			else {

				oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
						"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
				log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
			}

			// verification payment type amount
			String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[2]";
			String PaymUI = driver.findElement(By.xpath(pay)).getText();
			String Payme = PaymUI.replaceAll("[-4452, -4706]", "");
			String PaymentUI = Payme.replaceAll("[ ,]", "");
			System.out.println("Payment type is: " + PaymentUI);

			if (PaymentDB.contains(PaymentUI))
			{
				oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
						"Pass");
				log.info("Verify Payment type " + PaymentDB);
			}
			else {
				oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
						"Verify Payment type is failed" + PaymentDB, "Fail");
				log.error("Verify Payment type is failed" + PaymentDB);
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed",
					e + " is displayed in validate Payment Inquiry Screen");
		}

	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC2(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				rs.next();
				String ordDB = rs.getString("ORDER_NO");
				String TotalDB = rs.getString("TOTAL_AMOUNT");

				// getting order number from UI
				String orde = driver.findElement(By.xpath(orderNumberText)).getText();
				String ordUI = orde.replaceAll("[Order # ,]", "");
				System.out.println("order number:" + ordUI);
				if (ordUI.contains(ordDB))

				{
					oPSelFW.reportStepDetails("Verify Order number from DB in Payment Inquiry screen " + ordDB,
							"Verify Order number from UI in Payment Inquiry screen " + ordDB, "Pass");
					log.info("Verify Order number from UI in Payment Inquiry screen " + ordDB);
				}

				else {

					oPSelFW.reportStepDetails(
							"Verify Order number from DB is failed in Payment inquiry screen " + ordDB,
							"Verify Order number from UI is failed in Payment Inquiry screen" + ordDB, "Fail");
					log.error("Verify Order number from UI is failed in Payment Inquiry screen" + ordDB);
				}

				// verification payment type amount
				String tot = "((//div[@uid='extn_OriginalAmount'])/div)[2]";
				String totUI = driver.findElement(By.xpath(tot)).getText();
				String totaUI = totUI.replaceAll("[$,]", "");
				System.out.println("Payment type is: " + totaUI);
				if (TotalDB.contains(totaUI))
				{
					oPSelFW.reportStepDetails("Verify Order Total amount in Payment Inquiry screen " + totaUI,
							"Verify Order Total amount in Payment Inquiry screen " + totaUI, "Pass");
					log.info("Verify Order Total amount in Payment Inquiry screen " + totaUI);
				}
				else {

					oPSelFW.reportStepDetails("Verify Order Total amount in Payment Inquiry screen is failed " + totaUI,
							"Verify Order Total amount in Payment Inquiry screen is failed" + totaUI, "Fail");
					log.error("Verify Order Total amount in Payment Inquiry screen is failed" + totaUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC10(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refundDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[2]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4612,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				System.out.println("Payment type is: " + PaymentUI);

				if (PaymentDB.contains(PaymentUI))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC11(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"Select ORIGINAL_TOTAL_AMOUNT from yantra_owner.yfs_order_header where order_header_key in(select order_header_key from yantra_owner.yfs_order_header where Order_no='"
								+ getOrderNumber + "')");
				rs.next();
				String TttDB = rs.getString("ORIGINAL_TOTAL_AMOUNT");
				String TotDB = TttDB.replaceAll("[.36,]", "");

				// verification of total amount

				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdCreditTowardsExchanges_vwExchangeDistributeAmount_')])";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String TpUI = PaymUI.replaceAll("[$,]", "");
				String TotUI = TpUI.replaceAll("[.30,]", "");

				System.out.println("Payment type is: " + TotUI);

				if (TotDB.contains(TotUI))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + TotUI, "Verify Payment type " + TotUI, "Pass");
					log.info("Verify Payment type " + TotUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + TotUI,
							"Verify Payment type is failed" + TotUI, "Fail");
					log.error("Verify Payment type is failed" + TotUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC12(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"Select ORIGINAL_TOTAL_AMOUNT from yantra_owner.yfs_order_header where order_header_key in(select order_header_key from yantra_owner.yfs_order_header where Order_no='"
								+ getOrderNumber + "')");
				rs.next();
				String TttDB = rs.getString("ORIGINAL_TOTAL_AMOUNT");

				// verification of total amount

				String pay = "((//div[@uid='extn_OriginalAmount'])/div)[2]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String TpUI = PaymUI.replaceAll("[$,]", "");

				System.out.println("Payment type is: " + TpUI);

				if (TttDB.contains(TpUI))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + TpUI, "Verify Payment type " + TpUI, "Pass");
					log.info("Verify Payment type " + TpUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + TpUI,
							"Verify Payment type is failed" + TpUI, "Fail");
					log.error("Verify Payment type is failed" + TpUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC12(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_alt_refunded_amount");

				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refundDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount " + refundUI, "Fail");
					log.error("Verify Refunded amount " + refundUI);
				}
				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify Previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC13(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[1]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4472,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				System.out.println("Payment type is: " + PaymentUI);

				if (PaymentDB.contains(PaymentUI))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC14(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String PrevirefundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String PrevirefundDB1 = rs1.getString("total_refunded_amount");

				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				if (refundDB.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount " + refundUI, "Fail");
					log.error("Verify Refunded amount " + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);

				if (prerefundUI.contains(PrevirefundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount " + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC14(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refundDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[2]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4332,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[01,]", "");
				System.out.println("Payment type is: " + PaymentUI);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//create order for Appeasement functionality
	public String CreateOrderForAppeasementTax(HashMap<String, String> XLTestData, WebDriver driver,
			ProlificsSeleniumAPI oPSelFW) throws Exception {
		if (XLTestData != null) {
			InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			DataBase db = new DataBase();

			try {
				gen.waitUntilElementIsPresent(driver, By.xpath("//span[@class='idxHeaderUserIcon']"));

				ordercreate1.clickOnOrderLink(driver, oPSelFW);
				Thread.sleep(10000);
				ordercreate1.ConceptTypeScroll(driver, XLTestData.get("Concept").toString(), oPSelFW);
				// Passing the customer id
				String TitleOfPage = getTitleOfPage(driver);
				if (TitleOfPage.contains("Customer Search")) {
					driver.findElement(By.xpath(cus_id1)).click();
					oPSelFW.reportStepDetails("Click on Customer ID", "Customer Id link is clicked", "Pass");
					log.info("Customer Id link is clicked");
					// gen.setText(driver, cus_id, XLTestData.get("CustomerId").toString());
					element = driver.findElement(By.xpath(cus_id1));
					element.sendKeys(XLTestData.get("CustomerId").toString());
					oPSelFW.reportStepDetails("Customer ID should be clicked ", "Customer ID is clicked successfully ",
							"Pass");
					log.info("Customer ID is clicked successfully ");
					// clicking on search button
					// WebElement search = driver.findElement(searchCust);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					// executor.executeScript("arguments[0].click()", search);
					Thread.sleep(2000);
					oPSelFW.reportStepDetails("Search button should be clicked ",
							"Search button is clicked successfully ", "Pass");
					log.info("Search button is clicked successfully ");
					// valiadting the customer details
					String rrr = driver.findElement(By.xpath("//span[contains(text(), '981501782')]")).getText();
					oPSelFW.reportStepDetails("Customer id is displayed as" + rrr,
							"Customer id should be displayed as" + rrr, "Pass");
					log.info("Customer id should be displayed as" + rrr);
					String aaa = driver.findElement(By.xpath("(//span[contains(text(), 'kaitlyn  powers')])[1]"))
							.getText();
					oPSelFW.reportStepDetails("Customer name is displayed as" + aaa,
							"Customer name should be displayed as" + aaa, "Pass");
					log.info("Customer name should be displayed as" + aaa);
					String eee = driver
							.findElement(By.xpath("(//span[contains(text(), 'barefootlady89@yahoo.com')])[1]"))
							.getText();
					oPSelFW.reportStepDetails("Customer Email address is displayed as" + eee,
							"Customer Email address should be displayed as" + eee, "Pass");
					log.info("Customer Email address should be displayed as" + eee);
					String ppp = driver.findElement(By.xpath("(//span[contains(text(), '2077855305 ')])[1]")).getText();
					oPSelFW.reportStepDetails("Customer Phone number is displayed as" + ppp,
							"Customer Phone number should be displayed as" + ppp, "Pass");
					log.info("Customer Phone number should be displayed as" + ppp);
					String ship = driver.findElement(By.xpath("(//*[@uid='shippingAddressPanel'])")).getText();
					oPSelFW.reportStepDetails("Shipping address is displayed as" + ship,
							"Shipping address should be displayed as" + ship, "Pass");
					log.info("Shipping address should be displayed as" + ship);
					String bill = driver.findElement(By.xpath("(//*[@uid='billingAddressPanel'])")).getText();
					oPSelFW.reportStepDetails("Billing address is displayed as" + bill,
							"Billing address should be displayed as" + bill, "Pass");
					log.info("Billing address should be displayed as" + bill);
					Thread.sleep(2000);
					// clicking on next button
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollBy(0,900)");
					driver.findElement(next).click();
					oPSelFW.reportStepDetails("Next button should be clicked successfully",
							"Next button is clicked successfully" + bill, "Pass");
					log.info("Next button is clicked successfully" + bill);
					Thread.sleep(2000);
					// entering the regular item
					String ItemAdded = ordercreate1.enterItemDetails(driver, oPSelFW, XLTestData);
					if (!ItemAdded.contains("Items Added")) {
						getOrderNumber = ItemAdded;
					} else {
						// String OrderTotal = ordercreate.validateOrderTotal(driver, basetest);
						ordercreate1.ClickonNext(driver, oPSelFW);
						Thread.sleep(1000);
						// verification of order total
						String oi = driver.findElement(By.xpath("(//div[@uid='orderTotalLnk'])[2]")).getText();
						String OT = oi.replaceAll("[$,]", "");
						oPSelFW.reportStepDetails("Verify Order total" + OT,
								"Order total is verified successfully" + OT, "Pass");
						log.info("Order total is verified successfully" + OT);
						js.executeScript("window.scrollBy(0,900)");
						driver.findElement(next).click();
						oPSelFW.reportStepDetails("Next button should be clicked successfully",
								"Next button is clicked successfully", "Pass");
						log.info("Next button is clicked successfully");
						Thread.sleep(4000);
						String errorInPayment = ordercreate1.AddPaymentMethod(driver, oPSelFW, element, XLTestData);
						if (errorInPayment.trim().length() == 0) {
							gen.waitUntilElementIsPresent(driver, By.xpath("//*[@uid='pnlPrimaryInfo']/div/table/tbody/tr/td/span"));
							getOrderNumber = driver
									.findElement(By.xpath("//*[@uid='pnlPrimaryInfo']/div/table/tbody/tr/td/span"))
									.getText();
							oPSelFW.reportStepDetails("Get Order Number", getOrderNumber + " is generated", "Pass");
							log.info(getOrderNumber + " is generated");
						} else
							getOrderNumber = errorInPayment;
						// clicking on confirm button
						oPSelFW.reportStepDetails("Confirm button should be clicked successfully",
								"Confirm button is clicked successfully", "Pass");
						log.info("Confirm button is clicked successfully");
						Thread.sleep(3000);

					}

				}
			} catch (Exception e) {
				System.out.println(e);
				oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
				log.error(e + " is displayed");
				Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
			}
		}
		return getOrderNumber;
	}

//verify authorization
	public void verifyAuthorization(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat
						.executeQuery("Select * FROM yantra_owner.yfs_charge_transaction where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String chageDB = rs1.getString("CHARGE_TYPE");
				rs1.next();
				String chargeDB = rs1.getString("CHARGE_TYPE");
				oPSelFW.reportStepDetails("Verify charge type" + chageDB,
						"Verify charge type is displayed as" + chageDB, "Pass");
				log.info("Verify charge type is displayed as" + chageDB);

			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}

	}

//appeasement for tax 
//Method for Selecting Waive charges
	public void WaiveChargesForOrder(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			System.out.println("Appeasing the Customer.........");
//Xpath for clicking on the link appease the customer
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath(Appease));
			action.moveToElement(we).click();
			Thread.sleep(5000);
			action.perform();
			oPSelFW.reportStepDetails("Appease customer link should be clicked ",
					"Appease customer link should be clicked successfully ", "Pass");
			log.info("Appease customer link should be clicked successfully ");
			Thread.sleep(25000);
//selecting the Order
			driver.findElement(By.xpath("((((//*[@uid='extn_apply_appeasement_to'])/div[2])/div[1])/div[2])/div"))
					.click();
			Thread.sleep(3000);
			oPSelFW.reportStepDetails("ORDER should be clicked ", "ORDER is successfully clicked ", "Pass");
			log.info("ORDER is successfully clicked ");
//selecting the reason code
			driver.findElement(By.xpath(ReasonCode)).click();
			oPSelFW.reportStepDetails("Reason Code should be clicked ", "Reason Code is successfully clicked ", "Pass");
			log.info("Reason Code is successfully clicked");
			gen.setText(driver, ReasonCode, XLTestData.get("ReasonCode").toString());
//Selecting the sub reason code
			Thread.sleep(3000);
			driver.findElement(By.xpath(sub)).click();
			driver.findElement(By.xpath(SubReasonCode)).click();
			oPSelFW.reportStepDetails("Sub Reason Code should be clicked ", "Sub Reason Code is successfully clicked ",
					"Pass");
			log.info("Sub Reason Code is successfully clicked ");
			gen.setText(driver, SubReasonCode, XLTestData.get("SubReasonCode").toString());
//we.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
//Selecting the Item
			driver.findElement(By.xpath("((((//*[@uid='OLST_listGrid'])[1]/div)[3]/div)[4]/div)")).click();
			oPSelFW.reportStepDetails("Item should be clicked ", "Item is successfully clicked ", "Pass");
			log.info("Item is successfully clicked ");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
//Clicking on Next button
			driver.findElement(By.xpath(Nextbtn)).click();
			oPSelFW.reportStepDetails("Next button should be clicked ", "Next button is successfully clicked ", "Pass");
			log.info("Next button is successfully clicked ");
			gen.waitUntilElementIsPresent(driver, headerIcon);
			Thread.sleep(4000);
//entering the appeasement amount
			driver.findElement(By.xpath(APPamt)).click();
			oPSelFW.reportStepDetails("Appeased amount should be entered", "Appeased amount is entered successfully",
					"Pass");
			log.info("Appeased amount is entered successfully");
			gen.setText(driver, APPamt, XLTestData.get("Note Value").toString());
//Clicking on confirm button
			js.executeScript("window.scrollBy(0,700)");
			gen.waitUntilElementIsPresent(driver, headerIcon);
			driver.findElement(By.xpath(Confirmbtn)).click();
			isAlertPresent(driver);
			oPSelFW.reportStepDetails("Confirm button should be clicked successfully",
					"Confirm button is clicked successfully", "Pass");
			log.info("Confirm button is clicked successfully");
			Thread.sleep(10000);
		} catch (Exception e) {
//oPSelFW.reportStepDetails("Exception got Occured" ," Exception should got displayed successfully " , "Fail");  log.error(
			System.out.println(e);
		}
	}

	public void WaiveChargesOrderLine1(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		By OrderLevelXpath = By.xpath("((((//div[@uid='extn_apply_appeasement_to'])/div/div)[1]/div/div)[2])/input");
		By OrderLineLevelXpath = By.xpath("(((//div[@uid='extn_apply_appeasement_to'])/div/div)[1]/div/div)[1]/input");
		try {
			gen.shortwaitnclickElement(driver, By.xpath(Appease), "Appease Customer", oPSelFW);
			Thread.sleep(3000);
			gen1.waitUntilElementIsClickable(driver, 1000, OrderLevelXpath);
			if (XLTestData.get("OrderLevel").toString().toLowerCase().contains("yes")) {
				gen.shortwaitnclickElement(driver, OrderLevelXpath, "Order", oPSelFW);
			} else {
				gen.shortwaitnclickElement(driver, OrderLineLevelXpath, "Order Line", oPSelFW);
			}
			gen1.waitUntilElementIsClickable(driver, 1000,Nextbtn);
			WebElement element = driver.findElement(By.xpath(ReasonCode));
			element.clear();
			String ReasonCodeText = XLTestData.get("ReasonCode").toString();
			for (int j = 0; j < ReasonCodeText.length(); j++) {
				element.sendKeys(Character.toString(ReasonCodeText.charAt(j)));
			}
			oPSelFW.reportStepDetails("Reason Code should be clicked ", "Reason Code is successfully clicked ", "Pass");
			log.info("Reason Code is successfully clicked ");
			driver.findElement(By.xpath(ReasonCode)).sendKeys(Keys.TAB);
			;
			// Selecting the sub reason code
			Thread.sleep(3000);
			// driver.findElement(By.xpath(sub)).click();
			// driver.findElement(By.xpath(SubReasonCode)).click();
			oPSelFW.reportStepDetails("Sub Reason Code should be clicked ", "Sub Reason Code is successfully clicked ",
					"Pass");
			log.info("Sub Reason Code is successfully clicked ");
			element = driver.findElement(By.xpath(SubReasonCode));
			String SubReasonCodeText = XLTestData.get("SubReasonCode").toString();
			for (int j = 0; j < SubReasonCodeText.length(); j++) {
				element.sendKeys(Character.toString(SubReasonCodeText.charAt(j)));
				Thread.sleep(3);
			}
			driver.findElement(By.xpath(SubReasonCode)).sendKeys(Keys.TAB);
			Thread.sleep(2000);
			if (XLTestData.get("OrderLevel").toString().toLowerCase().contains("yes")) {
				gen.shortwaitnclickElement(driver, By.xpath(Nextbtn), "Next", oPSelFW);
				Thread.sleep(2000);
				gen1.waitUntilElementIsClickable(driver, 500, Confirmbtn1);
				driver.findElement(By.xpath("(//*[@uid='extn_txt_DiscountAmount']/div)[2]/div/div/input")).clear();
				driver.findElement(By.xpath("(//*[@uid='extn_txt_DiscountAmount']/div)[2]/div/div/input"))
						.sendKeys(Keys.ENTER);
				;
				driver.findElement(By.xpath("(//*[@uid='extn_txt_DiscountAmount']/div)[2]/div/div/input"))
						.sendKeys(XLTestData.get("OrderAppeasementAmountorPercentage").toString());
			} else {
				// Selecting the Item
				driver.findElement(By.xpath("//*[@uid='OLST_listGrid']/div[3]/div[4]/div[1]/table/tbody/tr/td/span"))
						.click();
				Thread.sleep(2000);
				oPSelFW.reportStepDetails("Item should be clicked ", " Item is selected successfully", "Pass");
				log.info(" Item is selected successfully");
				// Clicking on Next button

				gen.shortwaitnclickElement(driver, By.xpath(Nextbtn), "Next", oPSelFW);
				gen1.waitUntilElementIsClickable(driver, 100, Waive1);
				gen1.waitUntilElementIsPresent(driver, headerIcon);
				Thread.sleep(5000);
				if (XLTestData.get("WaveOrderMonoPZ").toString().toLowerCase().contains("yes"))
					driver.findElement(Waive1).click();
				if (XLTestData.get("WaveOrderGiftWrap").toString().toLowerCase().contains("yes"))
					driver.findElement(By.xpath(Waive2)).click();
				if (XLTestData.get("WaveOrderShipppingCharges").toString().toLowerCase().contains("yes"))
					driver.findElement(By.xpath(Waive3)).click();
				if (XLTestData.get("WaveOrderItemSurcharge").toString().toLowerCase().contains("yes"))
					driver.findElement(By.xpath(Waive4)).click();
				if (XLTestData.get("WaveOrderDestinationSurchange").toString().toLowerCase().contains("yes"))
					driver.findElement(By.xpath(Waive5)).click();
				oPSelFW.reportStepDetails("Waive order line Shipping charges should be selected ",
						" Waive order line Shipping charges is successfully selected ", "Pass");
				log.info(" Waive order line Shipping charges is successfully selected ");
			}
			// Clicking on confirm button
			gen.setText(driver, note, XLTestData.get("NoteValue").toString(), "Note Value", oPSelFW);
			Thread.sleep(500);
			gen.shortwaitnclickElement(driver, Confirmbtn1, "Confirm", oPSelFW);
			;
			Thread.sleep(500);
			gen.shortwaitnclickElement(driver, By.xpath(Appease), "Appease Customer", oPSelFW);
			Thread.sleep(3000);
			gen1.waitUntilElementIsClickable(driver, 1000, Nextbtn);
		} catch (Exception e) {
			oPSelFW.reportStepDetails("Verify if Exception is displayed",
					"Exception is displayed while appeasing the Order", "Fail");
			log.error("Exception is displayed while appeasing the Order");
			String ExpectedResult = "No Exception should be displayed";
			Assert.assertEquals("Exception is displayed", ExpectedResult);
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC15(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String PrevirefundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String PrevirefundDB1 = rs1.getString("total_refunded_amount");

				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				if (PrevirefundDB1.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount " + refundUI, "Fail");
					log.error("Verify Refunded amount " + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);

				if (prerefundUI.contains(PrevirefundDB))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount " + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC15(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[1]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4513,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				// String PaymentUI1 = PaymentUI.replaceAll("[01,]", "");
				System.out.println("Payment type is: " + PaymentUI);

				if (PaymentDB.contains(PaymentUI))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC17(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String PrevirefundDB = rs1.getString("total_refunded_amount");

				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify Refunded amount " + refundUI,
						"Pass");
				log.info("Verify Refunded amount " + refundUI);

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);

				if (prerefundUI.contains(PrevirefundDB))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount " + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC17(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");

					ResultSet rs1 = stat.executeQuery(
							"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
									+ orderHeaderKey + "'");
					rs1.next();
					String PaymentDB = rs1.getString("PAYMENT_TYPE");
					String refundDB = rs1.getString("total_refunded_amount");
					rs1.next();
					String refDB = rs1.getString("total_refunded_amount");

					// verification ofrefund amount
					String Refund = driver.findElement(By.xpath(
							"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[3]"))
							.getText();
					String refundd = Refund.replaceAll("[$,]", "");
					String refundUI = refundd.replaceAll("[(),]", "");
					System.out.println("Refund amount is: " + refundUI);

					if (refundUI.contains(refDB))

					{
						oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
								"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
						log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
					}

					else {

						oPSelFW.reportStepDetails(
								"Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
								"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
						log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
					}

					// verification payment type amount
					String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[3]";
					String PaymUI = driver.findElement(By.xpath(pay)).getText();
					String Payme = PaymUI.replaceAll("[-4513,]", "");
					String PaymentUI = Payme.replaceAll("[ ,]", "");
					String PaymentUI1 = PaymentUI.replaceAll("[2,]", "");
					System.out.println("Payment type is: " + PaymentUI);

					if (PaymentDB.contains(PaymentUI1))

					{
						oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB,
								"Verify Payment type " + PaymentDB, "Pass");
						log.info("Verify Payment type " + PaymentDB);
					}

					else {

						oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
								"Verify Payment type is failed" + PaymentDB, "Fail");
						log.error("Verify Payment type is failed" + PaymentDB);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC16(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String PrevirefundDB = rs1.getString("total_refunded_amount");

				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				if (refundDB.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount " + refundUI, "Fail");
					log.error("Verify Refunded amount " + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);

				if (prerefundUI.contains(PrevirefundDB))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount " + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC16(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC18(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[2]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[2]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4513,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				// String PaymentUI1 = PaymentUI.replaceAll("[01,]", "");
				System.out.println("Payment type is: " + PaymentUI);

				if (PaymentDB.contains(PaymentUI))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

	// validating return summary screen
	public void validateReturnOrderSummaryscreenForTC18(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String PrevirefundDB = rs1.getString("total_refunded_amount");

				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				if (refundDB.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount " + refundUI, "Fail");
					log.error("Verify Refunded amount " + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);

				if (prerefundUI.contains(PrevirefundDB))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount " + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

	// validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC19(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[2]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[2]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4513,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[2,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

	// validating return summary screen
	public void validateReturnOrderSummaryscreenForTC20(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String PrevirefundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String PrevirefundDB1 = rs1.getString("total_refunded_amount");

				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				/*
				 * if (refundDB.contains(refundUI))
				 * 
				 * { oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
				 * "Verify Refunded amount " + refundUI, "Pass");
				 * log.info("Verify Refunded amount " + refundUI); }
				 * 
				 * else {
				 * 
				 * oPSelFW.reportStepDetails("Verify Refunded amount is failed " +
				 * refundUI,"Verify Refunded amount" + refundUI, "Fail"); log.error(
				 * "Verify Refunded amount" + refundUI); }
				 */

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);

				if (prerefundUI.contains(PrevirefundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount " + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

	// validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC20(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my fefund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB1 = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[3]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);
				if (refundUI.contains(refDB1)) {
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				} else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen " + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[1]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[2,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed " + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

	// validating return summary screen
	public void validateReturnOrderSummaryscreenForTC21(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String PrevirefundDB = rs1.getString("total_refunded_amount");

				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				/*
				 * if (refundDB.contains(refundUI))
				 * 
				 * { oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
				 * "Verify Refunded amount " + refundUI, "Pass");
				 * log.info("Verify Refunded amount " + refundUI); }
				 * 
				 * else {
				 * 
				 * oPSelFW.reportStepDetails("Verify Refunded amount is failed " +
				 * refundUI,"Verify Refunded amount" + refundUI, "Fail"); log.error(
				 * "Verify Refunded amount" + refundUI); }
				 */
				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);

				if (prerefundUI.contains(PrevirefundDB))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount " + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC21(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[3]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[1]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[0,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC22(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_alt_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_alt_refunded_amount");
				rs1.next();
				String PrevirefundDB = rs1.getString("total_refunded_amount");

				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				if (refundDB1.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount " + refundUI, "Fail");
					log.error("Verify Refunded amount " + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				/*
				 * if (prerefundUI.contains(PrevirefundDB))
				 * 
				 * { oPSelFW.reportStepDetails("Verify previously Refunded amount " +
				 * prerefundUI, "Verify previously Refunded amount " + prerefundUI, "Pass");
				 * log.info("Verify previously Refunded amount " + prerefundUI); }
				 * 
				 * else {
				 * 
				 * oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " +
				 * prerefundUI, "Verify previously Refunded amount" + prerefundUI, "Fail");
				 * log.error("Verify previously Refunded amount" + prerefundUI); }
				 */ }
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC22(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[3]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[1]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[0,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC23(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[3]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refundDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[1]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[0,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC23(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify Refunded amount " + refundUI,
						"Pass");
				log.info("Verify Refunded amount " + refundUI);
				/*
				 * if (refundDB1.contains(refundUI))
				 * 
				 * { oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
				 * "Verify Refunded amount " + refundUI, "Pass");
				 * log.info("Verify Refunded amount " + refundUI); }
				 * 
				 * else {
				 * 
				 * oPSelFW.reportStepDetails("Verify Refunded amount is failed " +
				 * refundUI,"Verify Refunded amount" + refundUI, "Fail"); log.error(
				 * "Verify Refunded amount" + refundUI); }
				 */

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC24(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify Refunded amount " + refundUI,
						"Pass");
				log.info("Verify Refunded amount " + refundUI);
				if (refundDB.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC24(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refundDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[2]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[11,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC25(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify Refunded amount " + refundUI,
						"Pass");
				log.info("Verify Refunded amount " + refundUI);
				if (refund_alt_DB.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC25(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[2]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[11,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC26(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify Refunded amount " + refundUI,
						"Pass");
				log.info("Verify Refunded amount " + refundUI);
				if (refundDB.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC26(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refundDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[2]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[13,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC27(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				String refundDB2 = rs1.getString("total_refunded_amount");
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				// oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify
				// Refunded amount " + refundUI, "Pass"); log.info("Verify Refunded amount " +
				// refundUI);
				/*
				 * if (refundDB2.contains(refundUI))
				 * 
				 * { oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
				 * "Verify Refunded amount " + refundUI, "Pass");
				 * log.info("Verify Refunded amount " + refundUI); }
				 * 
				 * else {
				 * 
				 * oPSelFW.reportStepDetails("Verify Refunded amount is failed " +
				 * refundUI,"Verify Refunded amount" + refundUI, "Fail"); log.error(
				 * "Verify Refunded amount" + refundUI); }
				 */

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB2))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC28(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refundDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[2]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[13,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
		
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC28(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				// oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify
				// Refunded amount " + refundUI, "Pass"); log.info("Verify Refunded amount " +
				// refundUI);
				if (refundDB.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC29(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				// oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify
				// Refunded amount " + refundUI, "Pass"); log.info("Verify Refunded amount " +
				// refundUI);
				if (refundDB.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC29(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[1]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[2,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC30(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				// oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify
				// Refunded amount " + refundUI, "Pass"); log.info("Verify Refunded amount " +
				// refundUI);
				if (refundDB.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC30(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[3]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[1]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[2,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}
				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}

	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC31(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				// oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify
				// Refunded amount " + refundUI, "Pass"); log.info("Verify Refunded amount " +
				// refundUI);
				if (refundDB.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC31(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[1]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[08,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC32(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {

			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[2]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[2]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[12,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC34(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				// oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify
				// Refunded amount " + refundUI, "Pass"); log.info("Verify Refunded amount " +
				// refundUI);
				if (refundDB.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC34(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[1]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[12,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC35(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				// oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify
				// Refunded amount " + refundUI, "Pass"); log.info("Verify Refunded amount " +
				// refundUI);
				if (refundDB.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC35(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[1]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[12,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//query for return order summary screen
	public ResultSet queryForReturnOrder(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		ResultSet rs1 = null;
		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");

			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
		return rs1;
	}

//query for return order summary screen
	public ResultSet paymentOrder(ProlificsSeleniumAPI oPSelFW, String orderNo, HashMap<String, String> XLTestData,
			WebDriver driver) throws IOException {

		ResultSet rs1 = null;
		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");

			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
		return rs1;
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC36(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refundDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[2]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[12,]", "");
				System.out.println("Payment type is: " + PaymentUI1);
				if (PaymentDB.contains(PaymentUI1))
				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC41(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				String w = new DecimalFormat("#.#").format(766.30);
				System.out.println("Refund amount is: " + refundUI);
				// oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify
				// Refunded amount " + refundUI, "Pass"); log.info("Verify Refunded amount " +
				// refundUI);
				if (refund_alt_DB.equalsIgnoreCase(w))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC42(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db2 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				// oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify
				// Refunded amount " + refundUI, "Pass"); log.info("Verify Refunded amount " +
				// refundUI);
				if (refund_alt_db2.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC39(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				rs1.next();
				String refDB = rs1.getString("total_refunded_amount");

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				// verification payment type amount
				String pay = "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[1]";
				String PaymUI = driver.findElement(By.xpath(pay)).getText();
				String Payme = PaymUI.replaceAll("[-4476,]", "");
				String PaymentUI = Payme.replaceAll("[ ,]", "");
				String PaymentUI1 = PaymentUI.replaceAll("[93,]", "");
				System.out.println("Payment type is: " + PaymentUI1);

				if (PaymentDB.contains(PaymentUI1))

				{
					oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB, "Verify Payment type " + PaymentDB,
							"Pass");
					log.info("Verify Payment type " + PaymentDB);
				}

				else {

					oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
							"Verify Payment type is failed" + PaymentDB, "Fail");
					log.error("Verify Payment type is failed" + PaymentDB);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC40(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				String w = new DecimalFormat("#.#").format(70.20);
				System.out.println("Refund amount is: " + refundUI);
				// oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify
				// Refunded amount " + refundUI, "Pass"); log.info("Verify Refunded amount " +
				// refundUI);
				if (refundDB.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC36(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				String w = new DecimalFormat("#.#").format(183.20);
				System.out.println("Refund amount is: " + refundUI);
				// oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify
				// Refunded amount " + refundUI, "Pass"); log.info("Verify Refunded amount " +
				// refundUI);
				if (refundDB.contains(w))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC37(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				// oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify
				// Refunded amount " + refundUI, "Pass"); log.info("Verify Refunded amount " +
				// refundUI);
				if (refundDB.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC38(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				// oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify
				// Refunded amount " + refundUI, "Pass"); log.info("Verify Refunded amount " +
				// refundUI);
				if (refundDB1.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (refundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
			
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC45(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat
						.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				String refundDB1 = rs1.getString("total_refunded_amount");
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				rs2.next();
				String refund_alt_db1 = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify Refunded amount " + refundUI,
						"Pass");
				log.info("Verify Refunded amount " + refundUI);
				if (refundDB.contains(refundUI))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI,
							"Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,
							"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
						"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				if (prerefundUI.contains(refundDB1))

				{
					oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,
							"Verify previously Refunded amount " + prerefundUI, "Pass");
					log.info("Verify previously Refunded amount " + prerefundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " + prerefundUI,
							"Verify previously Refunded amount" + prerefundUI, "Fail");
					log.error("Verify previously Refunded amount" + prerefundUI);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validate payment inquiry screen
	public void validatePaymentInquiryscreenForTC45(ProlificsSeleniumAPI oPSelFW, String getOrderNumber,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {
		try {
			gen.waitUntilElementIsPresent(driver, headerIcon);
			// clicking where is my refund
			driver.findElement(By.xpath("//a[contains(text(),'Where is my refund?')]")).click();
			//// div[@uid='WhyWasICharged_Return']
			Thread.sleep(7000);
			// verification of order number
			String orderNoFromUI = driver.findElement(By.xpath(orderNumberText)).getText();
			System.out.println("Order No From UI: " + orderNoFromUI);
			oPSelFW.reportStepDetails("Verify Order from DB" + orderNoFromUI,
					"Verify Order number from UI done" + orderNoFromUI, "Pass");
			log.info("Verify Order number from UI done" + orderNoFromUI);

			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery(
						"select * from yantra_owner.yfs_Order_header where order_no='" + getOrderNumber + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key, payment_type, total_refunded_amount from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				String PaymentDB = rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				/*
				 * rs1.next(); String refDB = rs1.getString("total_refunded_amount");
				 */

				// verification ofrefund amount
				String Refund = driver.findElement(By.xpath(
						"(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwCreditAmount_')])[1]"))
						.getText();
				String refundd = Refund.replaceAll("[$,]", "");
				String refundUI = refundd.replaceAll("[(),]", "");
				System.out.println("Refund amount is: " + refundUI);

				if (refundUI.contains(refundDB))

				{
					oPSelFW.reportStepDetails("Verify Refunded amount in Payment Inquiry screen " + refundUI,
							"Verify Refunded amount in Payment Inquiry screen " + refundUI, "Pass");
					log.info("Verify Refunded amount in Payment Inquiry screen " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed in Payment inquiry screen " + refundUI,
							"Verify Refunded amount is failed in Payment Inquiry screen" + refundUI, "Fail");
					log.error("Verify Refunded amount is failed in Payment Inquiry screen" + refundUI);
				}

				/*
				 * //verification payment type amount String pay=
				 * "(//table[@class='gridxRowTable']//tr/td[contains(@sc_platform_celluid,'grdChargesAndRefunds_vwDisplayPaymentType_')])[2]";
				 * String PaymUI=driver.findElement(By.xpath(pay)).getText(); String Payme =
				 * PaymUI.replaceAll("[-4476,]", ""); String PaymentUI =
				 * Payme.replaceAll("[ ,]", ""); String PaymentUI1 =
				 * PaymentUI.replaceAll("[12,]", "");
				 * System.out.println("Payment type is: "+PaymentUI1);
				 * 
				 * if (PaymentDB.contains(PaymentUI1))
				 * 
				 * { oPSelFW.reportStepDetails("Verify Payment type " + PaymentDB,
				 * "Verify Payment type " + PaymentDB, "Pass"); log.info("Verify Payment type "
				 * + PaymentDB); }
				 * 
				 * else {
				 * 
				 * oPSelFW.reportStepDetails("Verify Payment type is failed " + PaymentDB,
				 * "Verify Payment type is failed" + PaymentDB, "Fail");
				 * log.error("Verify Payment type is failed" + PaymentDB); }
				 */
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}

//validating return summary screen
	public void validateReturnOrderSummaryscreenForTC46(ProlificsSeleniumAPI oPSelFW, String orderNo,
			HashMap<String, String> XLTestData, WebDriver driver) throws IOException {

		try {
			// getting values from database
			DataBase1 db1 = new DataBase1();
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
			System.out.println("Before Database");

			if (Environment.contains("STST")) {
				ResultSet rs = stat.executeQuery("select * from yantra_owner.yfs_Order_header where order_no='" + orderNo + "'");
				BigDecimal orderHeaderKey = null;
				while (rs.next()) {
					orderHeaderKey = rs.getBigDecimal("ORDER_HEADER_KEY");
				}
				ResultSet rs1 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs1.next();
				rs1.getString("PAYMENT_TYPE");
				String refundDB = rs1.getString("total_refunded_amount");
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundDB, "Verify Refunded amount " + refundDB,
						"Pass");
				log.info("Verify Refunded amount " + refundDB);
				rs1.next();
				ResultSet rs2 = stat.executeQuery(
						"select payment_key,total_alt_refunded_amount,total_refunded_amount, payment_type from yantra_owner.yfs_payment where Order_header_key='"
								+ orderHeaderKey + "'");
				rs2.next();
				String refund_alt_DB = rs2.getString("total_alt_refunded_amount");
				// verification of refund
				String Refund = driver.findElement(By.xpath(refund)).getText();
				String refundUI = Refund.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + refundUI);
				oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify Refunded amount " + refundUI,"Pass");
				log.info("Verify Refunded amount " + refundUI);
				if (refundDB.contains(refundUI))
				{
					oPSelFW.reportStepDetails("Verify Refunded amount " + refundUI, "Verify Refunded amount " + refundUI, "Pass");
					log.info("Verify Refunded amount " + refundUI);
				}

				else {

					oPSelFW.reportStepDetails("Verify Refunded amount is failed " + refundUI,"Verify Refunded amount" + refundUI, "Fail");
					log.error("Verify Refunded amount" + refundUI);
				}

				// verification of previously refunded
				String Ref = driver.findElement(By.xpath(preRefund)).getText();
				String prerefundUI = Ref.replaceAll("[$,]", "");
				System.out.println("Refund amount is: " + prerefundUI);
				oPSelFW.reportStepDetails("Verify previously Refunded amount " + prerefundUI,"Verify previously Refunded amount " + prerefundUI, "Pass");
				log.info("Verify previously Refunded amount " + prerefundUI);

				/*
				 * if (prerefundUI.contains(refundDB1))
				 * 
				 * { oPSelFW.reportStepDetails("Verify previously Refunded amount " +
				 * prerefundUI, "Verify previously Refunded amount " + prerefundUI, "Pass");
				 * log.info("Verify previously Refunded amount " + prerefundUI); }
				 * 
				 * else {
				 * 
				 * oPSelFW.reportStepDetails("Verify previously Refunded amount is failed " +
				 * prerefundUI, "Verify previously Refunded amount" + prerefundUI, "Fail");
				 * log.error("Verify previously Refunded amount" + prerefundUI); }
				 */
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed", e + " is displayed", "Fail");
			log.error(e + " is displayed");
			Assert.assertEquals("Verify if Exception is displayed", e + " is displayed");
		}
	}
}
