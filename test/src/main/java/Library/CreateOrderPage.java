package Library;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.prolifics.ProlificsSeleniumAPI;
import FrameWork.DataBase;
import FrameWork.Excel_Reader;
import FrameWork.Generic;

public class CreateOrderPage {
	Logger  log;
	Generic gen1 = new Generic();
		public CreateOrderPage()
		{
			
			try{PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
			String logFilePath = System.getProperty("user.dir") + "\\ProlificsReports\\";
			gen1.UpdatePropFile("log", logFilePath);
			log = Logger.getLogger(CreateOrderPage.class);
			}
			catch(Exception e)
			{
				System.out.println("Inside Exception");
			}
		}
	public static Excel_Reader excelReader;
	public static int i = 1;
	DataBase db = new DataBase();
	ProlificsSeleniumAPI oPSelFW ;
	Generic1 gen = new Generic1();
	Generic generic = new Generic();
	String lblStatus = "//*[@uid='extn_lblStatus']";
	final  By linkCreateOrder = By.xpath("(//a[contains(text(),'Create Order')])[2]");
	String xDayPhone = "//input[@data-ibmsc-uid='txtDayPhone']";
	String xAdressLine1 = "//input[@data-ibmsc-uid='txtAddressLine1']";
	By xPathNextbtn = By.xpath("(//span[contains(@id,'Button') and normalize-space(text())='Next'])[2]");
	By xPathEditBillAddress = By.xpath("//*[@uid='lnkEditAddress']/div[2]/div/div/a"); 
	String xEmailAddress = "//input[@data-ibmsc-uid='txtEmailAddress']";
	By xPathEditBill = By.xpath("//*[@uid='lnkEdit']/div[2]/div/div/a");
	final By changeFillfillmentOption = By.xpath("//*[@uid='changeFulfillmentOptions']/div[2]/div/div/a");
	String xFirstName = "(//label[contains(normalize-space(text()),'First Name:')]/following::input[position()=1 and contains(@class,'dijitReset dijitInputInner') and @data-ibmsc-uid='txtFirstName'])[1]";
	String ItemId = "//*[@uid='addItems']/div/div/div/div[1]/div[2]/div[2]/div/div/input";
	String OverrideLink = "//label[contains(normalize-space(text()),'Override address verification failure')]";
	String OrderTypeX = "//label[contains(normalize-space(text()),'Select Order Type')]/following::input[position()=1 and contains(@class,'ArrowButtonInner') and @role='presentation']";
	final By OrderTypeBy = By.xpath(OrderTypeX);
	String OrderTypeInp = "((//*[@uid='extn_Order_Type']/div)[2]/div/div)[2]";
	String OrderTypeEdit = "(//*[@uid='extn_Order_Type']/div/div/div)[2]/input";
	String StoreNumberX = "//label[contains(normalize-space(text()),'Store Number')]/following::input[position()=1 and contains(@id,'TextBox') and contains(@class,'InputInner')]";
	String AddbtnX = "//span[contains(@id,'Button') and normalize-space(text())='Add']";
	final By AddbtnBy = By.xpath("//span[contains(@id,'Button') and normalize-space(text())='Add']");
	String OrderTypeinput = "//input[@id='idx_form_FilteringSelect_5']";
	String EachX = "//label[normalize-space(text())='Quantity:']/following::input[position()=1 and  @class='dijitReset dijitInputInner']";
	String Nextbtn = "(//span[contains(@id,'Button') and normalize-space(text())='Next'])[2]";
	By xpathNext = By.xpath(Nextbtn);
	String PaymentError = "((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div";
	public String BillTo = "(//a[@data-ibmsc-uid='lnkEditAddress'])[2]";
	String ShipTo = "(//a[@data-ibmsc-uid='lnkEditAddress'])[1]";
	String FirstName = "(//label[contains(normalize-space(text()),'First Name:')]/following::input[position()=1 and contains(@class,'dijitReset dijitInputInner') and @data-ibmsc-uid='txtFirstName'])[1]";
	String LastNme = "//input[@data-ibmsc-uid='txtLastName']";
	String EmailAddress = "//input[@data-ibmsc-uid='txtEmailAddress']";
	String DayPhone = "//input[@data-ibmsc-uid='txtDayPhone']";
	String EveningPhone = "//input[@data-ibmsc-uid='txtEveningPhone']";
	String AdressLine1 = "//input[@data-ibmsc-uid='txtAddressLine1']";
	String PostalCode = "//input[@data-ibmsc-uid='txtZipCode']";
	String Applybtn = "//span[contains(@id,'Button') and normalize-space(text())='Apply']";
	By XPathApplybtn = By.xpath("//span[contains(@id,'Button') and normalize-space(text())='Apply']");
	By XpathCancelbtn = By.xpath("(//*[@uid=\"Popup_btnCancel\"]/span/span/span)[3]");
	String CustomerType = "//label[normalize-space(text())='Customer Type:']/following::input[position()=2 and  @class='dijitReset dijitInputInner']";
	String ID = "(//label[normalize-space(text())='ID:']/following::input[position()=1 and @class='dijitReset dijitInputInner'])[2]";
	String BusinessID = "(//*[@uid='extn_TradeMemberID']/div)[2]/div/div/input";
	String GiftOPt1btn = "(//span[contains(text(),'Go to Gift Options')])[1]";
	String GiftOPt2btn = "(//span[contains(text(),'Go to Gift Options')])[2]";
			//'"(//span[contains(@id,'Button') and normalize-space(text())='Go to Gift Options'])";
	String BackToFulfiilmentSummery = "(//span[contains(@id,'Button') and normalize-space(text())='Back to Fulfillment Summary'])[2]";
	String AddPaymentMethodbtn = "(//span[contains(@id,'Button') and normalize-space(text())='Add Payment Method'])";
	String IDX = "(//label[normalize-space(text())='ID:']/following::input[position()=1 and @class='dijitReset dijitInputInner'])[2]";
	String AmountToPay = "//*[@uid='lblRemainingCharge']/div[2]/div/div/span";
	String PaymentType="//label[normalize-space(text())='Payment type:']/following::input[position()=2 and  @class='dijitReset dijitInputInner']";
	String AmountToCharge="(((//*[@uid='txtMaxChargeLimit']/div)[2]/div/div)[1]/input)[1]";
	String AmountToCharge2="(//label[@dojoattachpoint='compLabelNode' and normalize-space(text())='Amount to charge:']/following::input[position()=1 and  @class='dijitReset dijitInputInner'])[3]";
	String AmountToCharge3="(//label[@dojoattachpoint='compLabelNode' and normalize-space(text())='Amount to charge:']/following::input[position()=1 and  @class='dijitReset dijitInputInner'])[5]";

	final  By unregisteredCustomerLink = By.xpath("//a[contains(text(), 'Continue With Unregistered Customer')]");
	String AddApply="(//span[contains(@id,'Button') and normalize-space(text())='Apply'])[3]";
	String PaymentCancel3 = "(//span[contains(@id,'Button') and normalize-space(text())='Cancel'])[3]";
	String PaymentCancel1 = "(//span[contains(@id,'Button') and normalize-space(text())='Cancel'])[1]";
	String PaymentCancel = "(//span[contains(@id,'Button') and normalize-space(text())='Cancel'])[4]";
	String PopUpCancel = "(//*[@uid='Popup_btnCancel']/span/span/span)[3]";
	String Confirmbtn="(//span[contains(@id,'Button') and normalize-space(text())='Confirm'])[2]";
	String TaxExempt = "//*[@id=\"idx_form_RadioButtonSet_8_RadioItem0\"]";
	String AddBtn = "(//*[@uid='add_item']/span/span/span)[3]";
	String ConceptBox = "(//*[@uid='orgList']/div[2]/div/div[2]/input)[1]";
	String DueBilllPin = "//*[@uid=\\\"txtMaxChargeLimit\\\"]/div[2]/div/div/input";
	
	By CardTypeXpath = By.xpath("//*[@uid='cmbCardType']/div[2]/div[1]/div[2]/input");
	By NameOnCardXpath = By.xpath("//*[@uid='txtNameOnCard']/div[2]/div[1]/div/input");
	final By ItemIDXpath = By.xpath(ItemId);
	final By lockIcon = By.xpath("//*[@uid='pnlStatusHolder']/img");
	final By allActiveHolds = By.xpath("//*[@uid='activeHolds']/div[2]/div/div[2]/table/tbody/tr/td/span");
	final By saveHolds = By.xpath("//*[@uid='update_holds']/span/span/span[3]");
	final By backToFullfillmentOption = By.xpath("//*[@uid='closeBttn2']/span/span/span[3]");
	final By labelStatus = By.xpath("//*[@uid='extn_lblStatus']");
	

	
	WebElement ApplyElement = null;
	public String SetItemID(WebDriver driver, String ItemID, ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException {
		try {
			generic.waitUntilElementIsClickable(driver, 150000, ItemIDXpath);
		// String OrderType = XLTestData.get("OrderType").toString();
		WebElement element = driver.findElement(ItemIDXpath);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", element);
		Thread.sleep(500);
		gen.setText(driver, ItemId, ItemID, "Item Id", oPSelFW);
		Thread.sleep(500);
		driver.findElement(By.xpath(ItemId)).sendKeys(Keys.TAB);
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@uid='extn_text_Catalog_Code']/div/div/div/input")).clear();
 		Thread.sleep(500);
		int i = 0;
		while(!driver.findElements(By.xpath("//*[@id=\"sc_plat_dojo_widgets_ScreenDialogUnderlay_1\"]/div[2]/div[1]")).isEmpty() && i <= 60)
		{
			Thread.sleep(1000);
			i++;
		}
		gen.waitnclickElement(driver, By.xpath(AddBtn), "Add", oPSelFW);
		String ErrorExists = "";
		if(driver.findElements(By.xpath("//*[@id='idx_widget__MasterHoverHelpTooltip_0']/div[2]/div")).size() > 0) 
		{
			By ErrorMessXpath = By.xpath("//*[@id='idx_widget__MasterHoverHelpTooltip_0']/div[2]/div");
			element = driver.findElement(ErrorMessXpath);
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", element);
			Thread.sleep(500);
		}
		//*[@id="widget_idx_form_TextBox_30"]/div[2]/div[3]/div
		if(driver.findElements(By.xpath("//*[@id='idx_widget__MasterHoverHelpTooltip_0']/div[2]/div")).size() > 0)
		{			
			ErrorExists = driver.findElement(By.xpath("//*[@id='idx_widget__MasterHoverHelpTooltip_0']/div[2]/div")).getText();
		}
		if(ErrorExists.length() > 0)
			return("Invalid Item Icon is displayed for "+ItemID);
		else if(driver.findElements(By.xpath("//*[@id='idx_widget_SingleMessage_0']")).size() > 0)
		{
			String errorMessageExists = driver.findElement(By.xpath("//*[@id='idx_widget_SingleMessage_0']")).getText();
			if(errorMessageExists.contains("Unable to add this item"))
				return(errorMessageExists);
			else
				return("");
		}
		else
			return("");
		}
		catch(Exception e)
		{
			oPSelFW.reportStepDetails("Error in Adding the Items"  , "Error in Adding the Item", "Fail");
			log.error("Error in Adding the Item");
			Assert.assertEquals("Error in Adding the Items"  , "Error in Adding the Item");
		}
		return ItemID;
	}

	public void selectOrderType(WebDriver driver, String OrderType, String StoreNumber, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		try {
		String TitleOfPage = getTitleOfPage(driver);
		if(TitleOfPage.contains("Add Items"))
		{
			int i = 0;
			try {
				generic.waitUntilElementIsClickable(driver, 5000, AddbtnBy);
				WebElement element = driver.findElement(AddbtnBy);
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click", element);
				while(!(driver.findElements(By.xpath("//*[@id='sc_plat_dojo_widgets_ScreenDialogUnderlay_1']/div[2]/div[1]")).isEmpty()) && i <= 30)
				{
					Thread.sleep(1000);
					i++;
				}
				if (OrderType.equalsIgnoreCase("Mail Order")) {
					gen1.waitUntilElementIsClickable(driver, OrderTypeInp);
					WebElement input = driver.findElement(By.xpath(OrderTypeInp));
					input.clear();
					Thread.sleep(500);
					input.sendKeys(OrderType);
					Thread.sleep(500);
					input.sendKeys(Keys.ENTER);
					Thread.sleep(500);
					if(! StoreNumber.contains("NoValueCellTypeBLANK"))
					{
						gen.setText(driver, StoreNumberX, StoreNumber);
						Thread.sleep(100);
					}
				}
				else 
				{
					WebElement input = driver.findElement(By.xpath(OrderTypeInp));
					input.click();
					input = driver.findElement(By.xpath(OrderTypeEdit));
					input.clear();
					input.sendKeys(OrderType);
					input.sendKeys(Keys.ENTER);
					Thread.sleep(500);
				}
	
			} catch (Exception e) {
				System.out.println(e);
				oPSelFW.reportStepDetails("Error in Adding the Items"  , "Add Items Page is not displayed", "Fail");
				log.error("Error in Adding the Items");
				Assert.assertEquals("Error in Adding the Items"  , "Add Items Page is not displayed");
			}
		}
		else
		{
			oPSelFW.reportStepDetails("Error in Adding the Element"  , "Add Items Page is not displayed", "Fail");
			log.error( "Add Items Page is not displayed");
			driver.quit();
			Assert.assertTrue(false);
		}
		}
		catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Error in Adding the Items"  , "Add Items Page is not displayed", "Fail");
			log.error("Error in Adding the Items");
			Assert.assertEquals("Error in Adding the Items"  , "Add Items Page is not displayed");
		}
	}

	public void setQuantity(WebDriver driver, String Quantity, ProlificsSeleniumAPI oPSelFW) throws Exception {
		try {
		gen.setText(driver, EachX, Quantity, "Quantity", oPSelFW);
		driver.findElement(By.xpath(EachX)).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		}
		catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Error in setting the quantity for the Item"  , "Error in Setting the quantity for the Item", "Fail");
			log.error("Error in Adding the Items");
			Assert.assertEquals("Error in setting the quantity for the Item"  , "Error in Setting the quantity for the Item");
		}
	}
	public void ClickonNext(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException
	{	
		try{final By XpathNextbtn = By.xpath(Nextbtn);
		generic.waitUntilElementIsClickable(driver, 25000, XpathNextbtn);
		WebElement element = driver.findElement(XpathNextbtn);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", element);
		oPSelFW.reportStepDetails("Click on Next Button"  , "Next Button is Clicked", "Pass");
		log.info( "Next Button is Clicked");
		Thread.sleep(8000);
		}
		catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Error in clicking on Next"  , "Error in clicking on Next button", "Fail");
			log.error("Error in clicking on Next");
			Assert.assertEquals("Error in clicking on Next"  , "Error in clicking on Next button");
		}
	}
	// click on BillTOLink
	public void enterAddressDetails(WebDriver driver, String AddressType, ProlificsSeleniumAPI oPSelFW, Map<String, String> XLTestData)
			throws InterruptedException, FileNotFoundException, IOException {
		try {
			String shipToAddDetails = XLTestData.get("NewShipToaddress").toString();
			String billToFirstName = XLTestData.get("BillToFirstName").toString();
			String billToLastName = XLTestData.get("BillToLastName").toString();
			String billToEmailAddress = XLTestData.get("BillToEmailAddress").toString();
			String billToPhoneNumber = XLTestData.get("BillToDayPhone").toString();
			String billToAddressLine = XLTestData.get("BillToAddressLine1").toString();
			if(AddressType.contains("BillTo"))
					entershipToNBillToAddressDetails(driver, AddressType, oPSelFW, XLTestData);
			else if(!shipToAddDetails.contains(billToFirstName) && !shipToAddDetails.contains(billToLastName) && 
					!shipToAddDetails.contains(billToEmailAddress) && !shipToAddDetails.contains(billToPhoneNumber) && !shipToAddDetails.contains(billToAddressLine))
						entershipToNBillToAddressDetails(driver, AddressType, oPSelFW, XLTestData);
		}
		catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Error in entering the Address Details"  , "Error in entering the Address Detail", "Fail");
			log.error("Error in entering the Address Details");
			Assert.assertEquals("Error in entering the Address Details"  , "Error in entering the Address Detail");
		}
	}

	public String enterCustomerType(WebDriver driver, ProlificsSeleniumAPI oPSelFW, WebElement element, HashMap<String, String> XLTestData)
			throws Exception {
					By OrderTotalLink = By.xpath("(//*[@uid='orderTotalLnk'])[2]/div[2]/div/div/a");
					By OrderFulFilment = By.xpath("//*[@uid='FulfillmentSummaryDetailsScreen']");
					try {			
						selectCustomerTypeAndEmployeeType(driver, element, oPSelFW, XLTestData);
						final By NextbtnXpath = By.xpath(Nextbtn);
						gen.waitnclickElement(driver, NextbtnXpath, "Next", oPSelFW);
						Thread.sleep(2000);
						while(gen.existsElement(driver, By.xpath("//*[starts-with(@id, 'sc_plat_dojo_widgets_ScreenDialogUnderlay_')]")))
							Thread.sleep(1000);
						String errorMessage = checkErrorExists(driver);
						if(errorMessage.trim().length() > 0)
						{	
							if(errorMessage.trim().length() > 0)
							{
							if(errorMessage.contains("Sales force service not available. Please try after some time")
									|| errorMessage.contains("Invalid Trade Member ID")
									|| errorMessage.contains("Record already exists in the database")
									|| errorMessage.contains("Servlet Exception occurred while forwarding the request")
									|| errorMessage.contains("There are errors on the page. You cannot proceed until you fix them")
									|| errorMessage.contains("Error description not available")
									|| errorMessage.contains("Please enter a Trade Member ID")
									|| errorMessage.contains("Please enter an Employee ID")
									|| errorMessage.contains("Provide Bill To address to continue")
									|| errorMessage.contains("Email cannot be used as a contact preference because there is no email address for the customer"))
							{
								oPSelFW.reportStepDetails("Verify if Error Message is displayed"  , errorMessage + " is displayed in 'Add Address' Screen", "Fail");
								log.error( errorMessage + " is displayed in 'Add Address' Screen");
								gen.closeTheOrder(driver, oPSelFW);
								Thread.sleep(1000);
								Assert.assertEquals("Verify if Error Message is displayed"  , errorMessage + " is displayed in 'Add Address' Screen");
								return(errorMessage);
							}	 
							else if(errorMessage.trim().length() == 0 || errorMessage.contains("Order updated successfully"))
							{
								if(XLTestData.get("TestCaseTitle").toString().toLowerCase().contains("rush"))
									rushOrder(driver, oPSelFW);
								if(XLTestData.get("TestCaseTitle").toString().toLowerCase().contains("sub order"))
									subOrders(driver, XLTestData, oPSelFW);
								gen.waitnclickElement(driver, NextbtnXpath, "Next", oPSelFW);
								Thread.sleep(20000);
								if(driver.findElements(By.xpath("//*[@uid='createOrder']")).size() == 1)
								{
									return("");
								}
							}
							}
							WebDriverWait wait = new WebDriverWait(driver, 15000);
							if(!driver.findElements(By.xpath(GiftOPt1btn)).isEmpty())
							{
								if(driver.findElements(By.xpath("//*[@uid='createOrder']")).size() == 0)
								{
									gen.waitnclickElement(driver, By.xpath(GiftOPt1btn), "Go to Gift Options button ", oPSelFW);
									Thread.sleep(5000);
								}
							}
							if(!driver.findElements(By.xpath(GiftOPt2btn)).isEmpty())
							{
								if(driver.findElements(By.xpath("//*[@uid='createOrder']")).size() == 0)
								{
									gen.waitnclickElement(driver, By.xpath(GiftOPt2btn), "Go to Gift Options button ", oPSelFW);
									Thread.sleep(5000);
								}
							}
							gen.waitnclickElement(driver, NextbtnXpath, "Next", oPSelFW);
							if(driver.findElements(By.xpath("//*[@uid='createOrder']")).size() == 0)
							{
								By xpathBackToFulfiilmentSummery = By.xpath(BackToFulfiilmentSummery);
								By xpathNext = By.xpath(Nextbtn);
								gen.waitnclickElement(driver, xpathBackToFulfiilmentSummery, "Back to FullFillment Summary button ", oPSelFW);
								generic.waitUntilElementIsClickable(driver, GiftOPt2btn);
								gen.waitnclickElement(driver, xpathNext, "Next", oPSelFW);
								Thread.sleep(1000);
								if(gen.existsElement(driver, xpathNext))
								{
									if(driver.findElement(xpathNext).isEnabled() && driver.findElement(xpathNext).isDisplayed())
									{
										gen.waitnclickElement(driver, xpathNext, "Next", oPSelFW);
										Thread.sleep(5000);
									}
								}
								return("");
							}
			}			
			else
			{
				WebDriverWait wait = new WebDriverWait(driver, 15000);
				Thread.sleep(4000);
				if(gen.existsElement(driver, OrderFulFilment))
				{
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(OrderFulFilment));
					String getOrderTotal = driver.findElement(OrderTotalLink).getText();
					oPSelFW.reportStepDetails("Verify the Order Total that is displayed"  , "Order Total that is displayed is  "+getOrderTotal, "Pass");
					log.info("Order Total that is displayed is  "+getOrderTotal);
				}
				enterGiftCardDetails(driver, oPSelFW, XLTestData);
				
				return("");
			}
			
		} catch (Exception e) {
			oPSelFW.reportStepDetails("Verify the exception that is displayed", e + " is displayed", "Fail"); 
			log.error( e + " is displayed"); 
			Assert.assertTrue(false);
		}
		return "";
	}
	public String AddPaymentMethod(WebDriver driver, ProlificsSeleniumAPI oPSelFW, WebElement element, Map<String, String> XLTestData) throws Exception {
		gen.waitUntilElementIsPresent(driver, AddPaymentMethodbtn);
		String TitleOfPage = getTitleOfPage(driver);
		if(TitleOfPage.contains("Payment Confirmation"))
		{
		try {
			String amount= gen.getTextOfElement(driver, AmountToPay);
			if(amount.trim().length() > 0)
			{
				amount = amount.replace("$", "");
				amount = amount.replace(",", "");
				Double Due = getDueAmountToEnter(driver);
				if(XLTestData.get("PromCode") != null)
				{
					String PromCode = XLTestData.get("PromCode").toString();
				}
				if(XLTestData.get("PaymentType").toString().toUpperCase().contains("DUE") && XLTestData.get("PaymentType").toString().toUpperCase().contains("CASH"))
				{
					Due = Double.parseDouble(amount)/2;
					enterCashInformation(driver, Double.toString(Due), XLTestData, oPSelFW);
					enterDueAmount(driver, Double.toString(Due), XLTestData, oPSelFW);	
				} 
				else if(XLTestData.get("PaymentType").toString().toUpperCase().contains("CREDIT") && XLTestData.get("PaymentType").toString().toUpperCase().contains("GIFT"))
				{
					Due = Double.parseDouble(amount)/2;
					enterCreditCardInformation(driver, XLTestData, oPSelFW);
					if(XLTestData.get("GiftCardInformation") != null)
					{
						String GiftCardInformation = XLTestData.get("GiftCardInformation").toString();
						EnterGiftCardDetails(driver, GiftCardInformation, XLTestData, oPSelFW);
					}
				}
				//code for LRC and PLCC
				else if(XLTestData.get("PaymentType").toString().toUpperCase().contains("LOYALTY") && XLTestData.get("PaymentType").toString().toUpperCase().contains("PLCC"))
				{
					Due = Double.parseDouble(amount)/2;
					enterCashInformation(driver, Double.toString(Due), XLTestData, oPSelFW);
					if(XLTestData.get("LoyaltyRewardsCertificate") != null)
					{					
						String LoyaltyRewardsCertificate = XLTestData.get("LoyaltyRewardsCertificate").toString();
						boolean errorMsg = enterLoyalityCardDetails(driver, LoyaltyRewardsCertificate, XLTestData, oPSelFW);
						if(errorMsg) 
							deletePayment(driver, oPSelFW);
					}
					Thread.sleep(2000);
					//code for  PLCC
					enterPLCCCardInformation(driver, XLTestData, oPSelFW);
				}
				else if(XLTestData.get("PaymentType").toString().toUpperCase().contains("PLCC"))
				{
					//code for  PLCC
					enterPLCCCardInformation(driver, XLTestData, oPSelFW);
				}
				//code for CBCC & SVC & Cash
				else if(XLTestData.get("PaymentType").toString().toUpperCase().contains("CBCC") && XLTestData.get("PaymentType").toString().toUpperCase().contains("SVC") && XLTestData.get("PaymentType").toString().toUpperCase().contains("CASH"))
				{	
					Due = Double.parseDouble(amount);
					double Remaining = Due-3;	
					enterCashInformation(driver, Double.toString(Remaining), XLTestData, oPSelFW);
					Thread.sleep(2000);
					if(XLTestData.get("GiftCardInformation") != null)
					{
						String GiftCardInformation = XLTestData.get("GiftCardInformation").toString();
						entersvcdetails(driver, GiftCardInformation, XLTestData, oPSelFW);
						Thread.sleep(2000);
						deletePayment(driver, oPSelFW);
						Thread.sleep(2000);
						double payamt = Double.parseDouble(amount)/2;
						String s = String.valueOf(payamt);
						int index = s.indexOf('.');
						if (index == -1)
						{
							i = 0;}
						else
						{ i = Integer.parseInt(s.substring(index + 1));}
						double payamt2 = Double.parseDouble("."+ i);
						enterCashInformation2(driver, Double.toString(payamt-payamt2), XLTestData, oPSelFW);
						Thread.sleep(2000);
						enterCBCCInformation(driver, XLTestData, oPSelFW);
						Thread.sleep(2000);
						//gen.waitnclickElement(driver, By.xpath(Confirmbtn), "Confirm", basetest);
						//Thread.sleep(12000);
					}
				}
				//CC SVC LRC and Cash
				else if(XLTestData.get("PaymentType").toString().toUpperCase().contains("CREDIT") && XLTestData.get("PaymentType").toString().toUpperCase().contains("SVC") && XLTestData.get("PaymentType").toString().toUpperCase().contains("LOYALTY") && XLTestData.get("PaymentType").toString().toUpperCase().contains("CASH"))
				{
					Due = Double.parseDouble(amount);
					double Remaining = Due-3;
					enterCashInformation(driver, Double.toString(Remaining), XLTestData, oPSelFW);
					Thread.sleep(1000);
					if(XLTestData.get("LoyaltyRewardsCertificate") != null)
					{					
						String LoyaltyRewardsCertificate = XLTestData.get("LoyaltyRewardsCertificate").toString();
						enterLoyalityCardDetails(driver, LoyaltyRewardsCertificate, XLTestData, oPSelFW);
					}
					deletePayment(driver, oPSelFW);
					Thread.sleep(2000);
					double payamt = Double.parseDouble(amount);
					double payamt1 = payamt-4;
					enterCashInformation2(driver, Double.toString(payamt1), XLTestData, oPSelFW);
					Thread.sleep(2000);
					if(XLTestData.get("GiftCardInformation") != null)
					{
						String GiftCardInformation = XLTestData.get("GiftCardInformation").toString();
						entersvcdetails(driver, GiftCardInformation, XLTestData, oPSelFW);
						Thread.sleep(2000);
						deletePayment(driver, oPSelFW);
						double payamt2 = Double.parseDouble(amount)/2;
						String s = String.valueOf(payamt2);
						int index = s.indexOf('.');
						if (index == -1)
							{ i = 0; }
						else
						{
							i = Integer.parseInt(s.substring(index + 1));
						}
						String cbcc = "."+ i;		
						double payamt3 = Double.parseDouble("."+ i);	
						enterCashInformation3(driver, Double.toString(payamt2-payamt3),XLTestData, oPSelFW);
						Thread.sleep(2000);
						double payamt4 = Double.parseDouble(amount) + payamt3;	
						enterCreditCardInformation(driver, XLTestData, oPSelFW);
						Thread.sleep(2000);
					}
				}
				else if(XLTestData.get("PaymentType").toString().toUpperCase().contains("CREDIT") && XLTestData.get("PaymentType").toString().toUpperCase().contains("MTL") && XLTestData.get("PaymentType").toString().toUpperCase().contains("CASH"))
				{
					Due = Double.parseDouble(amount);
					double Remaining = Due-3;
					enterCashInformation(driver, Double.toString(Remaining), XLTestData, oPSelFW);
					Thread.sleep(2000);
					if(XLTestData.get("LoyaltyRewardsCertificate") != null)
					{					
						String LoyaltyRewardsCertificate = XLTestData.get("LoyaltyRewardsCertificate").toString();
						enterMTLCardDetails(driver, LoyaltyRewardsCertificate, XLTestData, oPSelFW);
					}
					deletePayment(driver, oPSelFW);
					Thread.sleep(2000);
					double payamt = Double.parseDouble(amount)/2;
					double payamt1 = payamt-3;
					String s = String.valueOf(payamt1);
					int index = s.indexOf('.');
					if (index == -1)
						i = 0; 
					else
						i = Integer.parseInt(s.substring(index + 1));
					String cbcc = "."+ i;
					double payamt2 = Double.parseDouble("."+ i);
					enterCashInformation2(driver, Double.toString(payamt1-payamt2), XLTestData, oPSelFW);
					Thread.sleep(2000);
					String DueAmount = getDueAmount(driver);
					if(DueAmount.trim().length() > 0)
					{
						Due = getDueAmountToEnter(driver);
						if((Due >= 0.01 && Due < 0.26) || Due == 0.59 || (Due > 0.9 && Due < 0.99))
							enterDueAmount(driver, DueAmount, XLTestData, oPSelFW);
					}
					enterCreditCardInformation(driver, XLTestData, oPSelFW);
					Thread.sleep(2000);	
				}
			else
			{
				enterCashInformation(driver, amount, XLTestData, oPSelFW);
				if(XLTestData.get("LoyaltyRewardsCertificate") != null)
				{					
					String LoyaltyRewardsCertificate = XLTestData.get("LoyaltyRewardsCertificate").toString();
					enterLoyalityCardDetails(driver, LoyaltyRewardsCertificate, XLTestData, oPSelFW);
				}
				String DueAmount = getDueAmount(driver);
				Due = getDueAmountToEnter(driver);
				if((Due >= 0.01 && Due < 0.26) || Due == 0.59 || (Due > 0.9 && Due < 0.99))
					enterDueAmount(driver, DueAmount, XLTestData, oPSelFW);
				enterCreditCardInformation(driver, XLTestData, oPSelFW);
			}
			Thread.sleep(2000);
			gen.waitnclickElement(driver, By.xpath(Confirmbtn), "Confirm", oPSelFW);
			Thread.sleep(12000);
			if(driver.findElements(By.xpath("//*[@uid='lblAmountToRefund']")).size() > 0)
			{
				generic.waitUntilElementIsClickable(driver, "(//*[@uid='lb_screenSubTitle'])[2]");
				String errMsg = checkErrorExists(driver);
				if(errMsg.length() > 0)
				{
				if(errMsg.contains("Sufficient funds have not been provided")) 
				{
					gen.closeTheOrder(driver, oPSelFW);
					oPSelFW.reportStepDetails("Check the error Message"  , errMsg + " is displayed", "Fail"); 
					log.error(errMsg + " is displayed"); 
					Assert.assertTrue(false);
				}
				if(errMsg.contains("Credit Card Declined")) 
				{
					gen.closeTheOrder(driver, oPSelFW);
					oPSelFW.reportStepDetails("Check the error Message"  , errMsg + " is displayed", "Fail");
					log.error(errMsg + " is displayed");
					Assert.assertTrue(false);
				}
				if(errMsg.contains("Error description not available"))
				{
					gen.closeTheOrder(driver, oPSelFW);
					String errorMessage = "No error message should be displayed";
					oPSelFW.reportStepDetails("Check the error Message"  , errMsg + " is displayed", "Fail");
					log.error(errMsg + " is displayed");
					Assert.assertEquals(errorMessage, errMsg + " is displayed");
				}
				}
			}
		}
		else
		{	
			oPSelFW.reportStepDetails("Verify if Order Price is displayed"  , "Order Price is not displayed for the Order", "Fail"); 
			log.error("Order Price is not displayed for the Order"); 
			gen.closeTheOrder(driver, oPSelFW);
			Assert.assertTrue(false);
		}
		}
		catch (Exception e) {
			oPSelFW.reportStepDetails("Verify if Payment Confirmation Page is displayed"  , "Payment Confirmation Page is not displayed", "Fail");
			log.error("Payment Confirmation Page is not displayed");
			Assert.assertTrue(false);
		}
		}
		else
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if Payment Confirmation Page is displayed"  , "Payment Confirmation Page is not displayed", "Fail");
			log.error("Payment Confirmation Page is not displayed");
			Assert.assertTrue(false);
		}
		return("");
	}
	public String getOrderNumber(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws IOException
	{
		
		try{String getOrderNumber = driver.findElement(By.xpath("//*[@uid='pnlPrimaryInfo']/div/table/tbody/tr/td/span")).getText();
		oPSelFW.reportStepDetails("Get Order Number that is generated"  , getOrderNumber + " is generated", "Pass");
		log.info( getOrderNumber + " is generated");
		return(getOrderNumber);
		}
		catch (Exception e) {
			oPSelFW.reportStepDetails("Verify if Payment Confirmation Page is displayed"  , "Payment Confirmation Page is not displayed", "Fail");
			log.error("Payment Confirmation Page is not displayed");
			Assert.assertTrue(false);
		}
		return "";
	}
	public void clickOnOrderLink(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		try {
			generic.waitUntilElementIsClickable(driver, 2000, linkCreateOrder);
			WebElement element = driver.findElement(linkCreateOrder);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", element);
			oPSelFW.reportStepDetails("Click on Create Order Link"  , "Create Order Link is clicked", "Pass");
			log.info( "Create Order Link is clicked");
			Thread.sleep(5000);
			}
			catch(Exception e)
			{
			oPSelFW.reportStepDetails("Click on Create Order Link"  , "Create Order Link is not displayed", "Fail");
			log.error("Create Order Link is not displayed");
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.stopReport();
			Assert.assertTrue(false);
			}
	}
	public void clickOnUnRegisteredCustomerLink(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		try{
			generic.waitUntilElementIsPresent(driver, unregisteredCustomerLink);
			String TitleOfPage = getTitleOfPage(driver);
			if(TitleOfPage.contains("Customer Search"))
			{
				generic.waitUntilElementIsClickable(driver, 1000, unregisteredCustomerLink);
				WebElement element = driver.findElement(unregisteredCustomerLink);
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click()", element);
				oPSelFW.reportStepDetails("Click on Unregistered Customer Link "  , "Unregistered Customer Link is clicked", "Pass");
				log.info("Unregistered Customer Link is clicked");
				generic.waitUntilElementIsPresent(driver, ItemId);
				int i = 1;
				WebElement addItems = driver.findElement(By.xpath(ItemId));
				while(!addItems.isDisplayed() && !addItems.isEnabled() && i <= 50)
				{
					i++; 
					Thread.sleep(1000);
				}
			}
			else
			{
				String errorMessage = checkErrorExists(driver);
				if(errorMessage.trim().length() > 0) {
					oPSelFW.reportStepDetails("Check for error Message"  , errorMessage + " is displayed", "Fail");
				log.error( errorMessage + " is displayed");
				}	else
				{	oPSelFW.reportStepDetails("Check for Customer Search Page"  , "Customer Search Page is not displayed", "Fail");
				log.error( "Customer Search Page is not displayed");
				}gen.closeTheOrder(driver, oPSelFW);
				oPSelFW.stopReport();
				Assert.assertTrue(false);
			}
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify If Customer Search Page is displayed"  , "Customer Search Page is not displayed", "Fail"); 
			log.error("Customer Search Page is not displayed"); 
			oPSelFW.stopReport();
			Assert.assertTrue(false);
		}
	}
	public void ConceptTypeScroll(WebDriver driver, String ConceptOPt, ProlificsSeleniumAPI oPSelFW) throws Exception {
		try {	
			int i = 0;
			while(!(driver.findElements(By.xpath("//*[@id='sc_plat_dojo_widgets_ScreenDialogUnderlay_1']/div[2]/div[1]")).isEmpty()) && i <= 30)
			{
				Thread.sleep(1000);
				i++;
			}
			String ConceptType = "//div[@uid='orgList']/div[2]/div/div[2]/input[1]";
			WebElement concept = driver.findElement(By.xpath(ConceptBox));
			gen.highLighterMethod(driver, concept);
			gen.setTextAndHitEnter(driver, ConceptBox, ConceptOPt, "Concept", oPSelFW);
			Thread.sleep(5000);
			String errorExists = checkErrorExists(driver);
			if(errorExists.trim().length() > 0)
			{
				oPSelFW.reportStepDetails("Error exists" + errorExists, "Error displayed", "Fail");
				log.error( "Error displayed");
				gen.closeTheOrder(driver, oPSelFW);
				Assert.assertTrue(false);
			}
	}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if Concept Type Drop down is displayed"  , "Concept Type Drop down is not displayed", "Fail");
			log.error("Concept Type Drop down is not displayed");
			oPSelFW.stopReport();
			Assert.assertTrue(false);
		}	
	}
	public String enterItemDetails(WebDriver driver, ProlificsSeleniumAPI oPSelFW, Map<String, String> XLTestData) throws Exception
	{
		String ItemsAdded = "";
		try
		{
		String ItemIDs = XLTestData.get("ItemID").toString();
		String Items[] = ItemIDs.split(";");
		String Eachs = XLTestData.get("Each").toString();
		String EachsList[] = Eachs.split(";");
		for(int ItemNum=0; ItemNum<Items.length; ItemNum++)
		{	
			String ItemAdded = SetItemID(driver, Items[ItemNum], oPSelFW);
			String Error = "";
            DataBase db = new DataBase();
            String Environment = System.getProperty("Environment");;
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
            Connection conn = db.ConnectToDB(HostName, DBSID, UserName, DBPassword, oPSelFW);
			Statement stat = conn.createStatement();
			ResultSet rs =null;
			System.out.println("Before Database");
			if(Environment.contains("STST2"))
				rs = stat.executeQuery("select count(*) as count from YANTRA_STST_OWNER.YFS_ITEM WHERE ITEM_ID = '"+Items[ItemNum]+"'");
			else
				rs = stat.executeQuery("select count(*) as count from YANTRA_OWNER.YFS_ITEM WHERE ITEM_ID = '"+Items[ItemNum]+"'");
			rs.next();
			String ItemCount = rs.getString("count");
			if(Integer.parseInt(ItemCount) > 0) 
			{
				oPSelFW.reportStepDetails("Verify if Item is present in Database"  , "Item "+ Items[ItemNum].trim() + " is present in " + Environment + " database", "Pass"); 
				log.info("Item "+ Items[ItemNum].trim() + " is present in " + Environment + " database"); 
				if(driver.findElements(By.xpath("//*[@uid='systemMessagePanel']/div/div/span")).size() > 0)
				{	if(driver.findElement(By.xpath("//*[@uid='systemMessagePanel']/div/div/span")).isDisplayed())
				{
					Error = driver.findElement(By.xpath("//*[@uid='systemMessagePanel']/div/div/span")).getAttribute("innerText");
					ItemAdded = Error;
				}
				}
				if(ItemAdded.contains("Unable to add this item") || ItemAdded.contains("Invalid Item") || 
					ItemAdded.contains("Item is no longer available") || ItemAdded.contains("Error") || ItemAdded.contains("Error description not available") )
				{
					oPSelFW.reportStepDetails("Verify if error message is displayed in 'Add Item' Screen"  , ItemAdded + " message is displayed in 'Add Items' Screen" , "Fail");
					log.error(ItemAdded + " message is displayed in 'Add Items' Screen" );
					ItemsAdded = ItemsAdded + ItemAdded;
				}
				else
				{
					oPSelFW.reportStepDetails("Verify if item is added to the cart"  , Items[ItemNum].trim() + " item is added to the cart" , "Pass");
					log.info( Items[ItemNum].trim() + " item is added to the cart" );
					ItemsAdded = ItemsAdded + "Items Added";
					setQuantity(driver, EachsList[ItemNum], oPSelFW);
					Thread.sleep(1000);
				}
			}
			else 
			{
				oPSelFW.reportStepDetails("Verify if item is present in DataBase"  , "Item " + Items[ItemNum] + " is not present to the Database" , "Fail");
				log.error("Item " + Items[ItemNum] + " is not present to the Database" );
				ItemsAdded = ItemsAdded + "Item " + Items[ItemNum].trim() + " is not Added";
			}
		}
		}
		catch(SQLException e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if User is able to connect to Database"  , "Error in connecting to the Database" , "Fail");
			log.error("Error in connecting to the Database" );
			Assert.assertTrue(false);
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if User is able to add Items"  , "Error in Adding the Items is displayed" , "Fail"); 
			log.error("Error in Adding the Items is displayed" ); 
			oPSelFW.stopReport();
			Assert.assertTrue(false);
		}	
		return(ItemsAdded);
	}
	public String validateOrderTotal(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException
	{
		By updateXPath = By.xpath("//*[@uid='update_order']/span/span");
		generic.waitUntilElementIsClickable(driver, 150000, updateXPath);
		// String OrderType = XLTestData.get("OrderType").toString();
		WebElement element = driver.findElement(updateXPath);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", element);
		Thread.sleep(4000);
		String CalculatedTotal = gen.getTextOfElement(driver, "//*[@uid='orderTotalLnk']/div[2]/div/div/a");
		String EstimatedTotal = gen.getTextOfElement(driver, "//*[@uid='extn_datalabel']/div[2]/div[1]/div/span");
		if(CalculatedTotal.contains(EstimatedTotal))
		{
			oPSelFW.reportStepDetails("Verify if Estimated Total is equal to Calculated Total"  , "Estimated Total of the Order is equal to Calculated Total" , "Pass");
			log.info("Estimated Total of the Order is equal to Calculated Total" );
			return(CalculatedTotal);
		}
		else
		{
			oPSelFW.reportStepDetails("Verify if Estimated Total is equal to Calculated Total"  , "Estimated Total of the Order is not equal to Calculated Total" , "Fail");
			log.error("Estimated Total of the Order is not equal to Calculated Total" );
			return(CalculatedTotal);
		}
	}
	public void validateOrderDetails(WebDriver driver, String OrderTotal, HashMap<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws Exception {
		// Get Order Type
		try {
		String xlOrderType = XLTestData.get("OrderType").toString();
		String uiOrderType = driver.findElement(By.xpath("//*[@uid='extn_OrderType']/div[2]/div[1]/div/span"))
				.getText();
		if (uiOrderType.contains("PHONEORDER")) {
			if (xlOrderType.contains("Phone Order") && uiOrderType.contains("PHONEORDER")){
				oPSelFW.reportStepDetails("Order Type should be matching "  , " Order Type is matching ", "Pass");
			log.info(" Order Type is matching ");}
			else{
				oPSelFW.reportStepDetails("Order Type should be matching "  , " Order Type is not matching ", "Fail"); 
			log.error(" Order Type is not matching ");}
		}
		// Get Concept
		String xlConcept = XLTestData.get("Concept").toString();
		String uiConcept = driver.findElement(By.xpath("//*[@uid='lblEnterpriseCodeDisplay']/div[2]/div[1]/div/span"))
				.getText();
		if (xlConcept.contains(uiConcept)){
			oPSelFW.reportStepDetails("Concept displayed in UI is should match with the provided Concept in Test Data excel "  , "Concept displayed in UI is matching with the provided Concept in Test Data excel ", "Pass");
		log.info("Concept displayed in UI is matching with the provided Concept in Test Data excel ");}
		else{
			
			oPSelFW.reportStepDetails("Concept should match"  , "Concept is not matching", "Fail");
		log.error("Concept is not matching");}
		// Get Original Order Total
		String uiOrderTotal = driver.findElement(By.xpath("//*[@uid='extn_OriginalAmount']/div[2]/div[1]/div/span"))
				.getText();
		oPSelFW.reportStepDetails("Order Total shpuld be display in UI"  , "Order Total displayed in UI is"+ uiOrderTotal, "Pass");
		log.info("Order Total displayed in UI is"+ uiOrderTotal);
		// Get Communication Preference
		String uiCommunicationPreference = driver
				.findElement(By.xpath("//*[@uid='extn_CommunicationPreference']/div[2]/div[1]/div/span")).getText();
		String customerPreference = XLTestData.get("CustomerPreference").toString();
		if (uiCommunicationPreference.contains(customerPreference)){
			oPSelFW.reportStepDetails("Communication Preference should be match"  , "Communication Preference displayed in UI is matching with the provided Communication Preference in Test Data excel"+ uiOrderTotal, "Pass");
		log.info("Communication Preference displayed in UI is matching with the provided Communication Preference in Test Data excel"+ uiOrderTotal);}
		else{
			oPSelFW.reportStepDetails("Communication Preference should be match"  , "Communication Preference is not matching", "Fail");
			log.error( "Communication Preference is not matching");}
		
		
		String billToAddress = driver.findElement(By.xpath("//*[@uid='pnlAddressHolder']")).getText();
		
		oPSelFW.reportStepDetails("Order Total should be display in UI "  , "Order Total displayed in UI is"+ billToAddress, "Info");
		log.info( "Order Total displayed in UI is"+ billToAddress);
		}
		catch(Exception e)
		{
			oPSelFW.reportStepDetails("Verify if Exception is displayed in Order Details Screen"  , "Exception is displayed in Order Details" , "Fail");
			log.error("Verify if Exception is displayed in Order Details Screen" );
			Assert.assertEquals("Verify if Exception is displayed in Order Details Screen"  , "Exception is displayed in Order Details" );
		}
	}

	public void changeBillingAddress(WebDriver driver, ProlificsSeleniumAPI oPSelFW, HashMap<String, String> XLTestData) throws Exception
	{
		try {
		unLockTheOrder(driver, oPSelFW);
		gen.waitnclickElement(driver, changeFillfillmentOption, "Change Fullfillment Options", oPSelFW);
		gen.waitnclickElement(driver, xPathNextbtn, "Next", oPSelFW);
		gen.waitnclickElement(driver, xPathEditBill, "Edit Bill", oPSelFW);
		gen.waitnclickElement(driver, xPathEditBillAddress, "Edit Billing Address", oPSelFW);
		Thread.sleep(6000);
		String NewFirstName = XLTestData.get("NewBillFirstName").toString();
		String NewLastName = XLTestData.get("NewBillLastName").toString();
		String NewEmailAddress = XLTestData.get("NewBillEmailAddress").toString();
		String NewDayPhone = XLTestData.get("NewBillDayPhone").toString();
		String NewAddressLine1  = XLTestData.get("NewBillAddressLine1").toString();
		String NewZip = XLTestData.get("NewBillZip").toString();
		enterAddressDetails(driver, NewFirstName, NewLastName, NewEmailAddress, NewDayPhone, NewAddressLine1, NewZip, oPSelFW);
		if(!driver.findElements(By.xpath("(//*[@id='isccs_common_address_capture_AddressCapturePage_0']/div[4]/span/span/span)[1]")).isEmpty())
				gen.clickElement(driver, "(//*[@id='isccs_common_address_capture_AddressCapturePage_0']/div[4]/span/span/span)[1]");
		Thread.sleep(1000);
		gen.clickElement(driver, "(//*[@uid='Popup_btnCancel'])[1]");
		By popUpnext = By.xpath("(//*[@uid='Popup_btnNext'])[1]");
		Thread.sleep(1000);
		if(driver.findElements(popUpnext).size() > 0) 
			driver.findElement(popUpnext).click();
		//gen.shortwaitnclickElement(driver, popUpnext);
		gen.waitnclickElement(driver, By.xpath(Confirmbtn), "Confirm", oPSelFW);
		oPSelFW.reportStepDetails("Verify if Billing Address Details are successfully changed", "Billing Address Details are successfully changed", "Pass");
		log.info( "Billing Address Details are successfully changed");
		}
		catch(Exception e)
		{
			oPSelFW.reportStepDetails("Verify if Exception is displayed in Billing Address Screen"  , "Exception is displayed in Billing Address Screen" , "Fail");
			log.error("Verify if Exception is displayed in Billing Address Screen" );
			Assert.assertEquals("Verify if Exception is displayed in Billing Address Screen"  , "Exception is displayed in Billing Address Screen");
		}
	}
	public void changeShipToAddress(WebDriver driver, ProlificsSeleniumAPI oPSelFW, HashMap<String, String> XLTestData) throws Exception
	{
		try{By ItemSel = By.xpath("//*[@uid='OLST_listGrid']/div[2]/div/div[2]/table/tbody/tr/td");
		unLockTheOrder(driver, oPSelFW);
		gen.waitnclickElement(driver, changeFillfillmentOption);
		generic.waitUntilElementIsClickable(driver, 300, ItemSel);
		Thread.sleep(5000);
		driver.findElement(ItemSel).click();
		//gen.waitnclickElement(driver,By.xpath("//*[@uid='OLST_listGrid']/div[2]/div/div[2]/table/tbody/tr/td"));
		gen.waitnclickElement(driver,By.xpath("//*[@uid='changeAddress']/span/span/span[3]"));
		String NewFirstName = XLTestData.get("NewShipFirstName").toString();
		String NewLastName = XLTestData.get("NewShipLastName").toString();
		String NewEmailAddress = XLTestData.get("NewShipEmailAddress").toString();
		String NewDayPhone = XLTestData.get("NewShipDayPhone").toString();
		String NewAddressLine1  = XLTestData.get("NewShipAddressLine1").toString();
		String NewZip = XLTestData.get("NewShipZip").toString();
		enterAddressDetails(driver, NewFirstName, NewLastName, NewEmailAddress, NewDayPhone, NewAddressLine1, NewZip, oPSelFW);
		driver.findElement(By.xpath("//*[@uid='Popup_navigationPanel']/span/span/span/span[3]")).click();
		}catch(Exception e)
		{
			oPSelFW.reportStepDetails("Verify if Exception is displayed in Shipping Address Screen"  , "Exception is displayed in Shipping Address Screen" , "Fail");
			log.error("Verify if Exception is displayed in Shipping Address Screen" );
			Assert.assertEquals("Verify if Exception is displayed in Shipping Address Screen"  , "Exception is displayed in Shipping Address Screen");
		}
	}
	public void enterAddressDetails(WebDriver driver, String FirstName, String LastName, String EmailAddress, String DayPhone, String AddressLine1, String Zip, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		try{String addressTitle = "";
		if(!driver.findElements(By.xpath("//*[@id='dijit_Dialog_2_title']")).isEmpty())
			addressTitle = gen.getTextOfElement(driver, "//*[@id='dijit_Dialog_2_title']");
		if(!driver.findElements(By.xpath("//*[@id='dijit_Dialog_1_title']")).isEmpty())
			addressTitle = gen.getTextOfElement(driver, "//*[@id='dijit_Dialog_1_title']");
		gen.setText(driver, xFirstName, FirstName, addressTitle + " First Name", oPSelFW);
		gen.setText(driver, LastNme, LastName, addressTitle + " Last Name", oPSelFW);
		gen.setText(driver, xEmailAddress, EmailAddress, addressTitle + " Email Address", oPSelFW);
		gen.setTextAndHitEnter(driver, xDayPhone, DayPhone, addressTitle + " Day Phone", oPSelFW);
		// Address Line
		gen.setText(driver, xAdressLine1, AddressLine1, addressTitle + " Address Line 1", oPSelFW);
		gen.setTextAndHitTab(driver, PostalCode, Zip, addressTitle + " Zip", oPSelFW);
		}
		catch(Exception e)
		{
			oPSelFW.reportStepDetails("Verify if Exception is displayed in Shipping Address Screen"  , "Exception is displayed in Shipping Address Screen" , "Fail");
			log.error("Verify if Exception is displayed in Shipping Address Screen" );
			Assert.assertEquals("Verify if Exception is displayed in Shipping Address Screen"  , "Exception is displayed in Shipping Address Screen");
		}
	}
	public void waitUntilBrowserIsLoading(WebDriver driver) throws InterruptedException
	{
		int i = 1;
		while(!driver.findElements(By.xpath("//*[@id='sc_plat_dojo_widgets_ScreenDialogUnderlay_1']/div[2]/div[1]")).isEmpty() && i <= 20) 
			{
				Thread.sleep(1000);
				i++;
			}
	}
	public void EnterGiftCardDetails(WebDriver driver, String GiftCardInformation, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		try
		{
		if(XLTestData.get("PaymentType").toString().contains("Gift Card") || 
				XLTestData.get("PaymentType").toString().contains("Merchandise Card") || 
				XLTestData.get("PaymentType").toString().contains("E-Gift Card"))
		{
			oPSelFW.reportStepDetails("Select the Payment Type", XLTestData.get("PaymentType").toString() + " is selected as the Payment Type", "Pass");
			log.info( XLTestData.get("PaymentType").toString() + " is selected as the Payment Type");
			gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment", oPSelFW);
			Thread.sleep(5000);
			gen.setTextAndHitTab(driver, PaymentType, "Gift Card/Merchandise Card/E-Gift Card", "Payment Type", oPSelFW);
			Thread.sleep(2000);
			String[] GiftCardDetails = GiftCardInformation.split(":");
			WebElement CertificateNumber = driver.findElement(By.xpath("//*[@uid='txtSvcNo']/div[2]/div/div/input"));
			CertificateNumber.clear();									
			Thread.sleep(1000);
			CertificateNumber.sendKeys(GiftCardDetails[0]);
			Thread.sleep(2000);
			CertificateNumber.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			WebElement PINNumber = driver.findElement(By.xpath("//*[@uid='extn_pin']/div[2]/div/div/input"));
			PINNumber.clear();									
			Thread.sleep(1000);
			PINNumber.sendKeys(GiftCardDetails[1]);
			Thread.sleep(2000);
			PINNumber.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			String errorMessage="";
			if(!driver.findElements(By.xpath("((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div")).isEmpty())  
			{
				errorMessage = gen.getTextOfElement(driver, "((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div");
			}
			if(errorMessage.contains("still remaining on the card"))
			{
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
				Thread.sleep(5000);
				return;
			}
			else if(errorMessage.contains("TimeOut"))
			{
				oPSelFW.reportStepDetails("Verify the error message", errorMessage + " message is displayed", "Fail");
				log.error(errorMessage + " message is displayed");
				gen.waitnclickElement(driver, By.xpath(PaymentCancel), "Cancel", oPSelFW);
				Thread.sleep(5000);
				return;
			}			
			else
			{
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
				if(!driver.findElements(By.xpath("//*[@id='idx_widget_SingleMessage_1_messageTitle']/div")).isEmpty())  
				{
					errorMessage = gen.getTextOfElement(driver, "//*[@id='idx_widget_SingleMessage_1_messageTitle']/div");
				}
				if(errorMessage.trim().length() == 0)
				{
					if(!driver.findElements(By.xpath("//*[@id='idx_widget_SingleMessage_2_messageTitle']/div")).isEmpty())  
					{
						errorMessage = gen.getTextOfElement(driver, "//*[@id='idx_widget_SingleMessage_2_messageTitle']/div");
					}
				}
				if(errorMessage.trim().length() == 0)
				{
					if(!driver.findElements(By.xpath("//*[@id='idx_widget_SingleMessage_3_messageTitle']/div")).isEmpty())  
					{
						errorMessage = gen.getTextOfElement(driver, "//*[@id='idx_widget_SingleMessage_3_messageTitle']/div");
					}
				}
				if(errorMessage.trim().length() > 0)
				{
					oPSelFW.reportStepDetails("Verify if the error message is displayed"  , errorMessage + " is displayed", "Fail");
					log.error(errorMessage + " is displayed");
					gen.shortwaitnclickElement(driver, By.xpath("(//*[@uid='Popup_btnCancel']/span/span/span)[3]"), "Cancel", oPSelFW);
				}
				return;
			}
		}
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if the error message is displayed"  , "Error in Entering the Gift Card Details", "Fail");
			log.error("Error in Entering the Gift Card Details");
			oPSelFW.stopReport();
			Assert.assertTrue(false);
		}	
	}
	
	public boolean enterLoyalityCardDetails(WebDriver driver, String LoyaltyRewardsCertificate, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		try {
		if(XLTestData.get("PaymentType").toString().contains("Loyalty Rewards Certificate"))
		{
			oPSelFW.reportStepDetails("Select the Payment Type"  , "LRC is selected as the Payment Type", "Pass");
			log.info("LRC is selected as the Payment Type");
			gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment", oPSelFW);
			driver.findElement(By.xpath(PaymentType)).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath(PaymentType)).sendKeys("Loyalty Rewards Certificate");
			Thread.sleep(1000);
			oPSelFW.reportStepDetails("Select the Payment Type"  , "Loyalty Rewards Certificate is selected as the Payment Type", "Pass");
			log.info("Loyalty Rewards Certificate is selected as the Payment Type");
			driver.findElement(By.xpath(PaymentType)).sendKeys(Keys.TAB);
			Thread.sleep(1000);
			WebElement CertificateNumber = driver.findElement(By.xpath("//*[@uid='txtSvcNo']/div[2]/div/div/input"));
			CertificateNumber.clear();									
			Thread.sleep(1000);
			CertificateNumber.sendKeys(LoyaltyRewardsCertificate);
			oPSelFW.reportStepDetails("Enter the LRC Card Number"  , LoyaltyRewardsCertificate + " is entered", "Pass");
			log.info( LoyaltyRewardsCertificate + " is entered");
			Thread.sleep(2000);
			CertificateNumber.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			
			String errorMessage="";
			if(!driver.findElements(By.xpath("((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div")).isEmpty())  
			{
				errorMessage = gen.getTextOfElement(driver, "((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div");
			}
			
			if(errorMessage.contains("still remaining on the card"))
			{
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
				Thread.sleep(5000);
				return true;
			}
			else if(errorMessage.contains("TimeOut") || errorMessage.contains("Rewards certificate expired"))
			{
				oPSelFW.reportStepDetails("Verify the error message that is displayed"  , errorMessage  + " message is displayed", "Fail");
				log.error(errorMessage  + " message is displayed");
				gen.waitnclickElement(driver, By.xpath(PopUpCancel), "Cancel", oPSelFW);
				Assert.assertEquals("Verify the error message that is displayed"  , errorMessage  + " message is displayed");
				Thread.sleep(1000);
				return false;
			}
			else if(errorMessage.contains("Bal. Inq. Failed. Technical Issue. Please try"))
			{
				oPSelFW.reportStepDetails("Verify the error message that is displayed"  , errorMessage  + " message is displayed", "Fail");
				log.error( errorMessage  + " message is displayed");
				gen.waitnclickElement(driver, By.xpath(PaymentCancel3), "Cancel", oPSelFW);
				Thread.sleep(1000);
				Assert.assertEquals("Verify the error message that is displayed"  , errorMessage  + " message is displayed");
				return false;
			}
			else
			{
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
				Thread.sleep(5000);
				return true;
			}
		}
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if user able to enter the Loyality Card Details"  , "Error in Entering the Loyalty Card Details", "Fail");
			log.error( "Error in Entering the Loyalty Card Details");
			oPSelFW.stopReport();
			Assert.assertTrue(false);
		}
		return true;	
	}
		
	
	
	
	
	
	public void enterMTLCardDetails(WebDriver driver, String LoyaltyRewardsCertificate, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		try {
		if(XLTestData.get("PaymentType").toString().contains("MTL"))
		{
			oPSelFW.reportStepDetails("Select the Payment Type as MTL"  , "MTL is selected as the Payment Type", "Pass");
			log.info("MTL is selected as the Payment Type");
			gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment", oPSelFW);
			driver.findElement(By.xpath(PaymentType)).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath(PaymentType)).sendKeys("Loyalty Rewards Certificate");
			Thread.sleep(1000);
			driver.findElement(By.xpath(PaymentType)).sendKeys(Keys.TAB);
			Thread.sleep(1000);
			WebElement CertificateNumber = driver.findElement(By.xpath("//*[@uid='txtSvcNo']/div[2]/div/div/input"));
			CertificateNumber.clear();									
			Thread.sleep(1000);
			CertificateNumber.sendKeys(LoyaltyRewardsCertificate);
			Thread.sleep(2000);
			CertificateNumber.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			String errorMessage="";
			if(!driver.findElements(By.xpath("((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div")).isEmpty())  
			{
				errorMessage = gen.getTextOfElement(driver, "((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div");
			}
			if(errorMessage.contains("still remaining on the card"))
			{
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
				Thread.sleep(2000);
				return;
			}
			else if(errorMessage.contains("TimeOut") || errorMessage.contains("Rewards certificate expired"))
			{
				oPSelFW.reportStepDetails("Verify the error message that is displayed"  , errorMessage + " message is displayed for the LRC Card", "Fail");
				log.error(errorMessage + " message is displayed for the LRC Card");
				gen.waitnclickElement(driver, By.xpath(PopUpCancel), "Cancel", oPSelFW);
				Thread.sleep(2000);
				return;
			}			
			else
			{
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
				Thread.sleep(2000);
				return;
			}
		}
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if User is able to enter the MTL Card Details"  , "Error in Entering the MTL Card Details", "Fail");
			log.error("Error in Entering the MTL Card Details");
			oPSelFW.stopReport();
			Assert.assertTrue(false);
		}	
	}
		
			
			/*if(CertificateNumber.isDisplayed() && CertificateNumber.isEnabled() )
			{	if(driver.findElements(By.xpath("(//*[@uid='singlemessagelabelpopup']/div/span)[1]")).size() > 0)
				
			{
				String errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabelpopup']/div/span)[2]/span/div")).getText();
				if(errorMessage.contains("Technical Issue"))
					basetest.test.log(Status.FAIL, errorMessage + " is displayed");
				gen.clickElement(driver, "(//*[@uid='Popup_btnCancel']/span/span/span)[3]");
				//driver.close();
				basetest.extent.flush();
				Assert.assertTrue(false);
			}
			}
			gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", basetest);
			Thread.sleep(10000);
		}
	}*/
	
	
	public void entersvcdetails(WebDriver driver, String GiftCardInformation, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		try
		{
			gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment", oPSelFW);	
			oPSelFW.reportStepDetails("Select the Payment Type"  , XLTestData.get("PaymentType").toString() + " is selected as the Payment Type", "Pass");
			log.info( XLTestData.get("PaymentType").toString() + " is selected as the Payment Type");
			Thread.sleep(5000);
			driver.findElement(By.xpath(PaymentType)).clear();
			Thread.sleep(200);
			
			driver.findElement(By.xpath(PaymentType)).sendKeys("Gift Card/Merchandise Card/E-Gift Card");
			gen.setTextAndHitTab(driver, PaymentType, "Gift Card/Merchandise Card/E-Gift Card", "Payment Type", oPSelFW);
			Thread.sleep(5000);
			
			String[] GiftCardDetails = GiftCardInformation.split(":");
			WebElement CertificateNumber = driver.findElement(By.xpath("((//*[@uid='txtSvcNo']/div)[2]/div/div/input)[1]"));
			CertificateNumber.clear();									
			Thread.sleep(1000);
			
			CertificateNumber.sendKeys(GiftCardDetails[0]);
			Thread.sleep(2000);
			CertificateNumber.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			WebElement PINNumber = driver.findElement(By.xpath("((//*[@uid='extn_pin']/div)[2]/div/div/input)[1]"));
			PINNumber.clear();									
			Thread.sleep(1000);
			PINNumber.sendKeys(GiftCardDetails[1]);
			Thread.sleep(2000);
			PINNumber.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			String errorMessage="";
			if(!driver.findElements(By.xpath("((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div")).isEmpty())  
			{
				errorMessage = gen.getTextOfElement(driver, "((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div");
			}
			if(errorMessage.contains("still remaining on the card"))
			{
				oPSelFW.reportStepDetails("Verify the message displayed"  , errorMessage + " message is displayed", "Pass");
				log.info(errorMessage + " message is displayed");
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
				Thread.sleep(2000);
				return;
			}
			else if(errorMessage.contains("TimeOut"))
			{
				oPSelFW.reportStepDetails("Verify the message displayed"  , errorMessage + " message is displayed", "Fail");
				log.error( errorMessage + " message is displayed");
				gen.waitnclickElement(driver, By.xpath(PaymentCancel), "Cancel", oPSelFW);
				Thread.sleep(1000);
				return;
			}
			else if(errorMessage.contains("Cannot add payment with 0 funds available"))
			{
				oPSelFW.reportStepDetails("Verify the message displayed"  , errorMessage + " message is displayed", "Fail");
				log.error( errorMessage + " message is displayed");
				gen.waitnclickElement(driver, By.xpath(PaymentCancel), "Cancel", oPSelFW);
				Thread.sleep(1000);
				return;
			}
			else
			{
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
				Thread.sleep(1000);
				if(!driver.findElements(By.xpath("((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div")).isEmpty())  
				{
					errorMessage = gen.getTextOfElement(driver, "((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div");
					oPSelFW.reportStepDetails("Verify the message displayed"  , errorMessage + " message is displayed", "Fail");
					log.error(errorMessage + " message is displayed");
					if(!driver.findElements(By.xpath(PaymentCancel)).isEmpty())  
						gen.waitnclickElement(driver, By.xpath(PaymentCancel), "Cancel", oPSelFW);
					if(!driver.findElements(By.xpath(PaymentCancel3)).isEmpty())  
						gen.waitnclickElement(driver, By.xpath(PaymentCancel3), "Cancel", oPSelFW);
					Thread.sleep(1000);
				}
				Thread.sleep(3000);
				return;
			}
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify the message displayed"  , "Error in Entering the SVC Card Details", "Fail");
			log.error( "Error in Entering the SVC Card Details");
			oPSelFW.stopReport();
			Assert.assertTrue(false);
		}	
	}
	
	
	
	
	public void enterCBCCInformation(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		try {
		gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment", oPSelFW);
		if(XLTestData.get("PaymentType").toString().toUpperCase().contains("CBCC"))
		{
			oPSelFW.reportStepDetails("Select the Payment Type"  , "Credit Card is selected as the Payment Type", "Pass");
			log.info( "Credit Card is selected as the Payment Type");	
			//gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment", basetest);
			String CardNumber= XLTestData.get("CardNumber").toString();
			String ExpiryMonth= XLTestData.get("ExpiryMonth").toString();
			String ExpiryYear= XLTestData.get("ExpiryYear").toString();	
			Thread.sleep(6000);
			driver.findElement(By.xpath(PaymentType)).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			driver.findElement(By.xpath(PaymentType)).sendKeys(Keys.TAB);
			Thread.sleep(1000);
			By frame = By.xpath("//iframe[contains(@id,'padssIframe') and normalize-space(@title)='Card number:']");
			driver.switchTo().frame(driver.findElement(frame));
			WebElement eleCardNumber = driver.findElement(By.xpath("//input[@id='ssdcsDataToTokenize']"));
			eleCardNumber.clear();									
			Thread.sleep(1000);
			eleCardNumber.sendKeys(CardNumber);
			Thread.sleep(2000);
			eleCardNumber.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			WebElement CardType = driver.findElement(CardTypeXpath);
			CardType.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			WebElement NameOnCard = driver.findElement(NameOnCardXpath);
			NameOnCard.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			WebElement eleExpiryMonth = driver.findElement(By.xpath("//*[@uid='cmbMonth']/div[2]/div/div[2]/input"));
			eleExpiryMonth.clear();
			Thread.sleep(1000);
			eleExpiryMonth.sendKeys(ExpiryMonth);
			Thread.sleep(2000);
			WebElement eleExpiryYear = driver.findElement(By.xpath("//*[@uid='cmbYear']/div[2]/div/div[2]/input"));
			eleExpiryYear.clear();
			Thread.sleep(1000);
			eleExpiryYear.sendKeys(ExpiryYear);
			Thread.sleep(2000);				
			eleExpiryYear.sendKeys(Keys.TAB);
			Thread.sleep(2000);		
			gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
			Thread.sleep(1000);
		}
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Select the Payment Type as CBCC Card Details"  , "Error in Entering the CBCC Card Details", "Fail");
			log.error("Error in Entering the CBCC Card Details");
			oPSelFW.stopReport();
			Assert.assertTrue(false);
		}	
	}
	
	

	public void enterPLCCCardInformation(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		try {
		if(XLTestData.get("PaymentType").toString().toUpperCase().contains("PLCC"))
		{
			oPSelFW.reportStepDetails("Select Payment Type"  , "PLCC is selected as the Payment Type", "Pass"); 
			log.info("PLCC is selected as the Payment Type"); 
			gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment", oPSelFW);
			String CardNumber= XLTestData.get("CardNumber").toString();
			driver.findElement(By.xpath(PaymentType)).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath(PaymentType)).sendKeys("PLCC");
			Thread.sleep(1000);
			oPSelFW.reportStepDetails("Select Payment Type"  , "PLCC is selected as the Payment Type", "Pass");
			log.info("PLCC is selected as the Payment Type");
			driver.findElement(By.xpath(PaymentType)).sendKeys(Keys.TAB);
			Thread.sleep(1000);
			By frame = By.xpath("//iframe[contains(@id,'padssIframe') and normalize-space(@title)='Card number:']");
			driver.switchTo().frame(driver.findElement(frame));
		    WebElement eleCardNumber = driver.findElement(By.xpath("//input[@id='ssdcsDataToTokenize']"));
			eleCardNumber.clear();									
			Thread.sleep(1000);
			eleCardNumber.sendKeys(CardNumber);
			oPSelFW.reportStepDetails("Enter PLCC Card"  , CardNumber + " is entered as the Card", "Pass");
			log.info( CardNumber + " is entered as the Card");
			Thread.sleep(2000);
			eleCardNumber.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
			Thread.sleep(1000);
		}
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if error message is displayed"  , "Error in Entering the PLCC Card Information", "Fail"); 
			log.error( "Error in Entering the PLCC Card Information"); 
			oPSelFW.stopReport();
			Assert.assertTrue(false);
		}	
	}
	public void enterCreditCardInformation(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws Exception
	{		
		try{String xCardNumber = "//input[@id='ssdcsDataToTokenize']";
		if(XLTestData.get("PaymentType").toString().toUpperCase().contains("CREDIT"))
		{
			oPSelFW.reportStepDetails("Select the Payment Type"  , "Credit Card is selected as the Payment Type", "Pass");
			log.info("Credit Card is selected as the Payment Type");
			gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment", oPSelFW);
			String CardNumber= XLTestData.get("CardNumber").toString();
			String ExpiryMonth= XLTestData.get("ExpiryMonth").toString();
			String ExpiryYear= XLTestData.get("ExpiryYear").toString();	
			generic.waitUntilElementIsClickable(driver,PaymentType);
			driver.findElement(By.xpath(PaymentType)).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			driver.findElement(By.xpath(PaymentType)).sendKeys(Keys.TAB);
			Thread.sleep(1000);
			By frame = By.xpath("//iframe[contains(@id,'padssIframe') and normalize-space(@title)='Card number:']");
			if(driver.findElements(frame).size() > 0)
			{
				driver.switchTo().frame(driver.findElement(frame));
				WebElement eleCardNumber = driver.findElement(By.xpath(xCardNumber));
				eleCardNumber.clear();									
				Thread.sleep(1000);
				eleCardNumber.sendKeys(CardNumber);
				Thread.sleep(2000);
				eleCardNumber.sendKeys(Keys.TAB);
				Thread.sleep(1000);
				driver.switchTo().defaultContent();
				WebElement CardType = driver.findElement(CardTypeXpath);
				Thread.sleep(1000);
				CardType.sendKeys(Keys.TAB);
				Thread.sleep(1000);
				WebElement NameOnCard = driver.findElement(NameOnCardXpath);
				NameOnCard.sendKeys(Keys.TAB);
				Thread.sleep(1000);
				WebElement eleExpiryMonth = driver.findElement(By.xpath("//*[@uid='cmbMonth']/div[2]/div/div[2]/input"));
				eleExpiryMonth.clear();
				Thread.sleep(1000);
				eleExpiryMonth.sendKeys(ExpiryMonth);
				Thread.sleep(2000);
				WebElement eleExpiryYear = driver.findElement(By.xpath("//*[@uid='cmbYear']/div[2]/div/div[2]/input"));
				eleExpiryYear.clear();
				Thread.sleep(1000);
				eleExpiryYear.sendKeys(ExpiryYear);
				Thread.sleep(2000);				
				eleExpiryYear.sendKeys(Keys.TAB);
				Thread.sleep(2000);		
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
				Thread.sleep(1000);
				WebElement AddPaymentbtn = driver.findElement(By.xpath(AddPaymentMethodbtn));
				if(driver.findElements(By.xpath("//*[@uid='cmbYear']/div[2]/div/div[2]/input")).size() > 0)
					if(eleExpiryYear.isDisplayed() && eleExpiryYear.isEnabled() )
					{	if(driver.findElements(By.xpath("(//*[@uid='singlemessagelabelpopup']/div/span)[1]")).size() > 0)
						{
							String errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabelpopup']/div/span)[2]/span/div")).getText();
							if(errorMessage.contains("errors"))
							{	
								oPSelFW.reportStepDetails("Verify the error message that is displayed"  , errorMessage + " is displayed in Add Payment Screen", "Fail");
								log.error( errorMessage + " is displayed in Add Payment Screen");
								gen.clickElement(driver, "(//*[@uid='Popup_btnCancel']/span/span/span)[3]");
								Thread.sleep(1000);
								gen.closeTheOrder(driver, oPSelFW);
								Assert.assertTrue(false);
							}
						}
					}
			}
			else
			{
				if(driver.findElements(By.xpath("//*[@uid='PaymentCapture_CreditCard']/div")).size() > 0) 
				{
					String captureServerError = driver.findElement(By.xpath("//*[@uid='PaymentCapture_CreditCard']/div")).getText();
					if(captureServerError.contains("Connection to the secure data capture server failed"))
					{
						oPSelFW.reportStepDetails("Verify the error message that is displayed"  , captureServerError + " is displayed in Add Payment Screen for Credit Card", "Fail");
						log.error( captureServerError + " is displayed in Add Payment Screen for Credit Card");
						gen.waitnclickElement(driver, By.xpath(PaymentCancel3), "Cancel", oPSelFW);
						gen.closeTheOrder(driver, oPSelFW);
						Assert.assertTrue(false);
					}
				}
			}
		}
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify the error message that is displayed"  , "Error in Entering the Credit Card Information", "Fail");
			log.error("Error in Entering the Credit Card Information");
			oPSelFW.stopReport();
			Assert.assertTrue(false);
		}	
	}
	public void enterCashInformation(WebDriver driver, String amount, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		//gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment", basetest);
		try
		{
		if(XLTestData.get("PaymentType").toString().toUpperCase().contains("CASH"))
		{
			oPSelFW.reportStepDetails("Select the Payment Type"  , "Cash is selected as the Payment Type", "Pass");
			log.info("Cash is selected as the Payment Type");
			gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment", oPSelFW);
			generic.waitUntilElementIsClickable(driver, 100, PaymentType);
			driver.findElement(By.xpath(PaymentType)).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath(PaymentType)).sendKeys("Cash");
			Thread.sleep(1000);
			driver.findElement(By.xpath(PaymentType)).sendKeys(Keys.TAB);
			Thread.sleep(1000);
			driver.findElement(By.xpath(AmountToCharge)).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath(AmountToCharge)).sendKeys(amount);
			Thread.sleep(1000);
			driver.findElement(By.xpath(AmountToCharge)).sendKeys(Keys.TAB);
			Thread.sleep(1000);
			oPSelFW.reportStepDetails("Verify the amount user entered"  , amount  + " amount is added successfully", "Pass");  
			log.info( amount  + " amount is added successfully");  
			gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
			Thread.sleep(1000);
			int i = 1;
			WebElement AddPaymentbtn = driver.findElement(By.xpath(AddPaymentMethodbtn));
			while (!AddPaymentbtn.isDisplayed() && !AddPaymentbtn.isEnabled() && i<=20) {
				Thread.sleep(1000);
				i++;
			}
			String errorExists = checkErrorExists(driver);
			if(errorExists.contains("There are errors on the page. You cannot proceed until you fix them"))
			{
				oPSelFW.reportStepDetails("Verify the error message that is displayed"  , errorExists + " Error message is displayed in Add Payment Screen", "Fail");
				log.error( errorExists + " Error message is displayed in Add Payment Screen");
				gen.waitnclickElement(driver, By.xpath(PaymentCancel3), "Cancel", oPSelFW);
			}
			if(AddPaymentbtn.isDisplayed() && AddPaymentbtn.isEnabled())
				return;	
		}
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if user is able to enter Cash Payment"  , "Error in Entering the Cash Information", "Fail");
			log.error( "Error in Entering the Cash Information");
			oPSelFW.stopReport();
			Assert.assertTrue(false);
		}	
	}
	
	public void enterCashInformation2(WebDriver driver, String amount, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		try {
		if(XLTestData.get("PaymentType").toString().toUpperCase().contains("CASH"))
		{
			oPSelFW.reportStepDetails("Select the Payment Type"  , "Cash is selected as the Payment Type", "Pass");
			log.info("Cash is selected as the Payment Type");
			gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment", oPSelFW);
			Thread.sleep(1000);
			driver.findElement(By.xpath(PaymentType)).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath(PaymentType)).sendKeys("Cash");
			Thread.sleep(2000);
			driver.findElement(By.xpath(PaymentType)).sendKeys(Keys.TAB);
			Thread.sleep(2000);
			driver.findElement(By.xpath(AmountToCharge)).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath(AmountToCharge)).sendKeys(amount);
			Thread.sleep(1000);
			driver.findElement(By.xpath(AmountToCharge)).sendKeys(Keys.TAB);
			Thread.sleep(1000);
			oPSelFW.reportStepDetails("Verify the amount user entered"  , amount  + " amount is added successfully", "Pass");
			log.info(amount  + " amount is added successfully");
			gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
			Thread.sleep(1000);
			int i = 1;
			WebElement AddPaymentbtn = driver.findElement(By.xpath(AddPaymentMethodbtn));
			while (!AddPaymentbtn.isDisplayed() && !AddPaymentbtn.isEnabled() && i<=20) {
				Thread.sleep(1000);
				i++;
			}
			if(AddPaymentbtn.isDisplayed() && AddPaymentbtn.isEnabled())
				return;
			
		}
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if user is able to enter Cash Payment"  , "Error in Entering the Cash Information", "Fail");
			log.error("Error in Entering the Cash Information");
			Assert.assertTrue(false);
		}	
	}
	
	public void enterCashInformation3(WebDriver driver, String amount, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		//gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment", basetest);
		try {
		if(XLTestData.get("PaymentType").toString().toUpperCase().contains("CASH"))
		{
			oPSelFW.reportStepDetails("Select the Payment Type"  , "Cash is selected as the Payment Type", "Pass");
			log.info("Cash is selected as the Payment Type");
			gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment", oPSelFW);
			Thread.sleep(1000);
			driver.findElement(By.xpath(PaymentType)).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath(PaymentType)).sendKeys("Cash");
			Thread.sleep(1000);
			driver.findElement(By.xpath(PaymentType)).sendKeys(Keys.TAB);
			Thread.sleep(1000);
			driver.findElement(By.xpath(AmountToCharge)).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath(AmountToCharge)).sendKeys(amount);
			Thread.sleep(1000);
			driver.findElement(By.xpath(AmountToCharge)).sendKeys(Keys.TAB);
			Thread.sleep(1000);
			
			oPSelFW.reportStepDetails("Verify the amount user entered"  , amount  + " amount is added successfully", "Pass");
			log.info( amount  + " amount is added successfully");
			gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
			Thread.sleep(1000);
			int i = 1;
			WebElement AddPaymentbtn = driver.findElement(By.xpath(AddPaymentMethodbtn));
			while (!AddPaymentbtn.isDisplayed() && !AddPaymentbtn.isEnabled() && i<=20) {
				Thread.sleep(1000);
				i++;
			}
			if(AddPaymentbtn.isDisplayed() && AddPaymentbtn.isEnabled())
				return;
		}
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if user is able to enter Cash Payment"  , "Error in Entering the Cash Information", "Fail");
			log.error( "Error in Entering the Cash Information");
			Assert.assertTrue(false);
		}	
	}
	
	
	public void enterDueAmount(WebDriver driver, String DueAmount, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		try {
		if(!DueAmount.contains("00"))
		{
			oPSelFW.reportStepDetails("Select the Payment Type"  , "Due Bill is selected as the Payment Type", "Pass");
			log.info("Due Bill is selected as the Payment Type");
			gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment", oPSelFW);
			Thread.sleep(6000);
			gen.setTextAndHitTab(driver, PaymentType, "Due Bill", "Payment Type", oPSelFW);
			Thread.sleep(2000);
			WebElement PINNumber = driver.findElement(By.xpath("//*[@uid='txtMaxChargeLimit']/div[2]/div/div/input"));
			PINNumber.clear();									
			Thread.sleep(1000);
			PINNumber.sendKeys(DueAmount);
			Thread.sleep(2000);
			PINNumber.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			gen.waitnclickElement(driver, By.xpath(AddApply), "Apply", oPSelFW);
			Thread.sleep(5000);
			if(driver.findElements(By.xpath("(//*[@uid='singlemessagelabelpopup']/div/span)[1]")).size() > 0)
			{
				String errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabelpopup']/div)[1]")).getText();
				oPSelFW.reportStepDetails("Verify the error message is displayed"  , errorMessage + " is displayed", "Fail");
				log.error(errorMessage + " is displayed");
				gen.waitnclickElement(driver, By.xpath(PopUpCancel), "Cancel", oPSelFW);
			}
			else
			{if(!driver.findElements(By.xpath("//span[contains(text(), 'Ok')]")).isEmpty() && driver.findElement(By.xpath("//span[contains(text(), 'Ok')]")).isDisplayed() == true)
			{
				gen.clickElement(driver, "//span[contains(text(), 'Ok')]");
				Thread.sleep(5000);
			}
			oPSelFW.reportStepDetails("Enter the Due Amount"  , DueAmount + " Due amout is added successfully", "Pass");
			log.info(DueAmount + " Due amout is added successfully");
			}
		}
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if user is able to enter the Due Amount"  , "Error in Entering the Due Amount", "Fail");
			log.error("Error in Entering the Due Amount");
			Assert.assertTrue(false);
		}	
	}
	public void verifyIfOrderIsUpdated(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws IOException
	{
		try{String text = driver.findElement(By.xpath("//*[@uid='OrderEditor']/div[3]/div/div/span[2]/span/div")).getText();
		if(text.contains("Order updated successfully")) 
			oPSelFW.reportStepDetails("Verify if order is updated successfully"  , "Order is updated successfully", "Pass"); 
		log.info("Order is updated successfully"); 
		}
		catch(Exception e)
		{
			oPSelFW.reportStepDetails("Verify if order is updated successfully"  , "Order is not updated succcessfully", "Fail");
			log.error("Order is not updated succcessfully");
			Assert.assertEquals("Verify if order is updated successfully"  , "Order is not updated succcessfully");
		}
	}
	public void getStatusOfOrder(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws IOException
	{
		try
		{
			gen.waitUntilElementIsPresent(driver, lblStatus);
		String OrderStatus = gen.getTextOfElement(driver, lblStatus);
		if(OrderStatus.contains("Cancelled"))
			oPSelFW.reportStepDetails("Verify the status of the Order"  , "Status of the Order is Cancelled", "Pass");
		log.info("Status of the Order is Cancelled");
		}
		catch(Exception e)
		{
			oPSelFW.reportStepDetails("Verify if order status is displayed"  , "Order Status is not displayed", "Fail");
			log.error("Order is not updated succcessfully");
			Assert.assertTrue(false);
		}
		
	}
	public void cancelOrder(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		try {
		String ReasonCodexPath = "(//*[@uid='CancelOrderBaseScreen']/div/div/div/div[2]/input)[1]";
		gen.waitnclickElement(driver, By.xpath("//*[@uid='cancelOrder']/div[2]/div/div/a"));
		WebDriverWait wait = new WebDriverWait(driver, 50000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ReasonCodexPath)));
		Thread.sleep(3000);
		gen.setTextAndHitTab(driver, ReasonCodexPath, "Payment Failure", "Reason Code", oPSelFW);
		Thread.sleep(3000);
		gen.setTextAndHitTab(driver, "(//*[@uid='CancelOrderBaseScreen']/div/div/div/div[2]/input)[3]", "Credit Card Authorization Failure", "Sub Reason Code", oPSelFW);
		Thread.sleep(1000);
		clickOnNextAndConfirmOrder(driver, oPSelFW);		
		getStatusOfOrder(driver, oPSelFW);
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if user is able to Cancel the Order"  , "Error in Cancelling the Order", "Fail"); 
			log.error("Error in Cancelling the Order"); 
			Assert.assertTrue(false);
		}	
	}
	public void clickOnNextAndConfirmOrder(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		try{gen.waitnclickElement(driver, By.xpath("(//span[contains(text(),'Next')])[2]"));
		Thread.sleep(5000);
		if(!driver.findElements(By.xpath("//*[@uid='txtNoteText']/div[2]/div/textarea")).isEmpty()) 
		{
			driver.findElement(By.xpath("//*[@uid='txtNoteText']/div[2]/div/textarea")).sendKeys("Hello Text");
		}
		if(!driver.findElements(By.xpath(("(//span[contains(text(),'Ok')])[2]"))).isEmpty())
			gen.waitnclickElement(driver, By.xpath("(//span[contains(text(),'Ok')])[2]"));
		else if(!driver.findElements(By.xpath(("(//span[contains(text(),'Ok')])[1]"))).isEmpty())
			gen.waitnclickElement(driver, By.xpath("(//span[contains(text(),'Ok')])[1]"));
		Thread.sleep(5000);
		gen.waitnclickElement(driver, By.xpath("(//span[contains(text(),'Confirm')])[2]"));
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if user is able to click on Confirm Order"  , "Error in Clicking the Confirm Order", "Fail");
			log.error("Error in Clicking the Confirm Order");
			Assert.assertTrue(false);
		}	
	}
	public void unLockTheOrder(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, 50000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(lockIcon));
		try {
		if(!driver.findElements(lockIcon).isEmpty())
		{
			if(driver.findElement(lockIcon).isDisplayed())
			{
				gen.waitnclickElement(driver, lockIcon);
				Thread.sleep(3000);
				gen.waitnclickElement(driver, By.xpath("(//*[@uid='activeHolds']/div[3]/div[4]/div/table/tbody/tr/td/span)[1]"));
				//driver.findElement(By.xpath("(//*[@uid='activeHolds']/div[3]/div[4]/div/table/tbody/tr/td/span)[1]")).click();
				Thread.sleep(1000);
				if(!driver.findElements(By.xpath("(//*[@uid='activeHolds']/div[3]/div[4]/div/table/tbody/tr/td/span)[2]")).isEmpty()) 
				{
					gen.waitnclickElement(driver, By.xpath("(//*[@uid='activeHolds']/div[3]/div[4]/div/table/tbody/tr/td/span)[2]"));
					Thread.sleep(1000);
				}
				if(!driver.findElements(By.xpath("(//*[@uid='activeHolds']/div[3]/div[4]/div/table/tbody/tr/td/span)[3]")).isEmpty())
				{
					gen.waitnclickElement(driver, By.xpath("(//*[@uid='activeHolds']/div[3]/div[4]/div/table/tbody/tr/td/span)[3]"));
					Thread.sleep(1000);
				}
				if(driver.findElement(saveHolds).isDisplayed() && driver.findElement(saveHolds).isEnabled())
					gen.waitnclickElement(driver, saveHolds, "Save Holds", oPSelFW);
				Thread.sleep(6000);
				String HoldRemovedSuccessfullyMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div/span)[1]")).getText();
				if(HoldRemovedSuccessfullyMessage.contains("Holds have been resolved successfully"))
					oPSelFW.reportStepDetails("Verify if user is able to resolve the Holds of the Order"  , "Holds have been resolved successfully", "Pass"); 
				log.info( "Holds have been resolved successfully"); 
				gen.waitnclickElement(driver, backToFullfillmentOption);
			}
		}
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if user is able to unlock the Order"  , "Error in Unlocking the Order", "Fail");
			log.error( "Error in Unlocking the Order");
			Assert.assertTrue(false);
		}	
	}
	public void performOrderAppeasement(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		try{
			gen.clickElement(driver, "//*[@uid='customerAppeasement']/div[2]/div/div/a", "Appeasement", oPSelFW);
			String Reason = "//*[@uid='CustomerAppeasementSelection']/div/div[3]/div/div/div[2]/input[1]";
			gen.waitUntilElementIsPresent(driver, Reason);
			gen.setTextAndHitTab(driver, Reason, "DEFECTIVE", "Reason Code", oPSelFW);
			gen.setTextAndHitTab(driver, "//*[@uid='extn_subreason']/div[2]/div/div[2]/input[1]", " Fabric issue", "Sub Reason Code", oPSelFW);
			gen.clickElement(driver, "//*[@uid='OLST_listGrid']/div[3]/div[4]/div/table/tbody/tr/td/span", "Select Item", oPSelFW);
		clickOnNextAndConfirmOrder(driver, oPSelFW);
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Verify if error is displayed"  , "Error in Performing the Order AppeasementsItems is displayed", "Fail");
			log.error("Error in Performing the Order AppeasementsItems is displayed");
			Assert.assertTrue(false);
		}	
	}
	public void verifyStatusOfOrder(String ExpectedStatus, WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		try{
			gen.waitUntilElementIsPresent(driver,lblStatus);
			String OrderStatus = gen.getTextOfElement(driver, lblStatus);
		if(OrderStatus.contains(ExpectedStatus))
			oPSelFW.reportStepDetails("Get Status of the Order"  , "Status of the Order is "+ExpectedStatus, "Pass");
		log.info("Status of the Order is "+ExpectedStatus);
		}
		catch(Exception e)
		{
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Get Status of the Order"  , "Error in getting the status of the Order", "Fail");
			log.error( "Error in getting the status of the Order");
			Assert.assertTrue(false);
		}	
	}
	public String checkErrorExists(WebDriver driver)
	{
		if(gen.existsElement(driver, By.xpath("//*[@id='idx_widget_SingleMessage_0_messageTitle']/div")))
		{
			String ErrorMessage = driver.findElement(By.xpath("//*[@id='idx_widget_SingleMessage_0_messageTitle']/div")).getText();
			return(ErrorMessage);
		}
		else if(gen.existsElement(driver, By.xpath("//*[@id='idx_widget_SingleMessage_1_messageTitle']/div")))
		{
			String ErrorMessage = driver.findElement(By.xpath("//*[@id='idx_widget_SingleMessage_1_messageTitle']/div")).getText();
			return(ErrorMessage);
		}
		else if(gen.existsElement(driver, By.xpath("//*[@id='idx_widget_SingleMessage_2_messageTitle']/div")))
		{
			String ErrorMessage = driver.findElement(By.xpath("//*[@id='idx_widget_SingleMessage_2_messageTitle']/div")).getText();
			return(ErrorMessage);
		}
		else if(gen.existsElement(driver, By.xpath("//*[starts-with(@id, 'idx_widget_SingleMessage_')]/div[1]/span[1]")))
		{
			String ErrorMessage = driver.findElement(By.xpath("//*[starts-with(@id, 'idx_widget_SingleMessage_')]/div[1]/span[1]")).getText();
			return(ErrorMessage);
		}
		else
			return("");
	}
	public String getTitleOfPage(WebDriver driver)
	{
		return(driver.findElement(By.xpath("(//*[@uid='lb_screenSubTitle'])[2]")).getText());
	}
	public String getDueAmount(WebDriver driver)
	{
		String amount= gen.getTextOfElement(driver, AmountToPay);
		if(amount.trim().length() > 0)
		{
			amount = amount.replace("$", "");
			amount = amount.replace(",", "");
			String DueAmount = amount.substring(amount.indexOf("."), amount.length());
			return(DueAmount);
		}
		else
			return("");
	}
	public Double getDueAmountToEnter(WebDriver driver)
	{
		String DueAmount = getDueAmount(driver);
		if(DueAmount.length() > 0)
		{	
			Double Due = Double.parseDouble(DueAmount);
			return(Due);
		}
		else
		{
			return(0.0);
		}
	}
	public void deletePayment(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws InterruptedException
	{
		try {
		gen.clickElement(driver, "(//*[@title='Click on this link to delete the payment method'])[1]");
		Thread.sleep(2000);
		if(!driver.findElements(By.xpath("//span[contains(text(), 'Ok')]")).isEmpty() && driver.findElement(By.xpath("//span[contains(text(), 'Ok')]")).isDisplayed() == true)
		{
			gen.clickElement(driver, "//span[contains(text(), 'Ok')]");
			Thread.sleep(5000);
		}
		}
		catch(Exception e)
		{
			Assert.assertTrue(false);
		}
	}
	public void selectCustomerTypeAndEmployeeType(WebDriver driver, WebElement element, ProlificsSeleniumAPI oPSelFW, Map<String, String> XLTestData) throws Exception
	{
		try {
		String getTextOfCustomerPreference = driver.findElement(By.xpath("//*[@uid='CustomerPrefpnl']")).getAttribute("innerHTML");
		if(getTextOfCustomerPreference.contains("Email"))
		{
			WebElement customerPreference = driver.findElement(By.xpath("(((//*[@uid='extn_CustomerPref']/div)[2]/div/div)[2]/input)[1]"));
			customerPreference.clear();
			Thread.sleep(100);
			customerPreference.sendKeys(XLTestData.get("CustomerPreference").toString());
			oPSelFW.reportStepDetails("Select Customer Preference"  ,  XLTestData.get("CustomerPreference").toString() + " is selected as Customer Preference", "Pass");
			log.info( XLTestData.get("CustomerPreference").toString() + " is selected as Customer Preference");
			Thread.sleep(100);
			customerPreference.sendKeys(Keys.TAB);
			gen.setTextAndHitTab(driver, CustomerType, XLTestData.get("CustomerType").toString(), "Customer Type", oPSelFW);						
			if (XLTestData.get("CustomerType").toString().equalsIgnoreCase("Business")) {
						Thread.sleep(100);
						element = driver.findElement(By.xpath(BusinessID));
						element.clear();
						element.sendKeys(XLTestData.get("BussinessId").toString());
						oPSelFW.reportStepDetails("Select Customer Type"  ,  XLTestData.get("CustomerType").toString() + " is selected as Customer Type", "Pass");
						log.info( XLTestData.get("CustomerType").toString() + " is selected as Customer Type");
						Thread.sleep(100);
						element.sendKeys(Keys.TAB);
						Thread.sleep(100);
						if(!driver.findElements(By.xpath(TaxExempt)).isEmpty())
						{
							gen.clickElement(driver, TaxExempt);
							Thread.sleep(1000);
						}
			} 
			else if (XLTestData.get("CustomerType").toString().equalsIgnoreCase("Employee")) {
				Thread.sleep(1000);
				gen.setTextAndHitTab(driver, IDX, XLTestData.get("EmployeeID").toString(), "Employee ID", oPSelFW);
			}
		}
		else
		{
			oPSelFW.reportStepDetails("Verify if values are displayed in Customer Preference Drop down list"  ,  "No values are displayed in the Customer Preference drop down list", "Fail");
			log.error( "No values are displayed in the Customer Preference drop down list");
		}
		}catch(Exception e)
		{
			Assert.assertTrue(false);
		}
	}
	public void enterGiftCardDetails(WebDriver driver, ProlificsSeleniumAPI oPSelFW, Map<String, String> XLTestData) throws Exception
	{
		try {
			String giftOptions = XLTestData.get("GiftOptions").toString();
			if(giftOptions.length() > 0 && !giftOptions.contains("NoValueCellTypeBLANK"))
			{
				generic.waitUntilElementIsClickable(driver, 5000, "//*[@uid='changeLOS']");
			if(!driver.findElements(By.xpath(GiftOPt1btn)).isEmpty())
			{
				gen.waitnclickElement(driver, By.xpath(GiftOPt1btn), "Go to Gift Options", oPSelFW);
				Thread.sleep(5000);
			}
			if(!driver.findElements(By.xpath(GiftOPt2btn)).isEmpty())
			{
				gen.waitnclickElement(driver, By.xpath(GiftOPt2btn), "Go to Gift Options", oPSelFW);
				Thread.sleep(5000);
			}
			String giftOption[] = giftOptions.split("&");
			for(int opt=0;opt<=giftOption.length-1;opt++)
			{
				String giftOpt[] = giftOption[opt].split(":");
				if(giftOpt.length > 1) 
				{	
					boolean giftSelected = false;
					if(!driver.findElements(By.xpath("(//*[@uid='OLST_listGrid']/div[3]/div[4]/div/table/tbody/tr/td/span)[2]")).isEmpty())
					{if(driver.findElement(By.xpath("(//*[@uid='OLST_listGrid']/div[3]/div[4]/div/table/tbody/tr/td/span)[2]")).isDisplayed())
					{	
						gen.clickElement(driver, "(//*[@uid='OLST_listGrid']/div[3]/div[4]/div/table/tbody/tr/td/span)[2]");
						Thread.sleep(1000);
						giftSelected = true;
					}
					}
					else if(!driver.findElements(By.xpath("//*[@id='gridx_Grid_4']/div[3]/div[4]/div[1]/table/tbody/tr/td/span")).isEmpty())
					{
						gen.clickElement(driver, "//*[@id='gridx_Grid_4']/div[3]/div[4]/div[1]/table/tbody/tr/td/span");
						Thread.sleep(1000);
						if(!driver.findElements(By.xpath("//*[@id='gridx_Grid_4']/div[3]/div[4]/div[2]/table/tbody/tr/td/span")).isEmpty())
						{
							gen.clickElement(driver, "//*[@id='gridx_Grid_4']/div[3]/div[4]/div[2]/table/tbody/tr/td/span");
							Thread.sleep(1000);
						}
						giftSelected = true;
					}
					if(giftSelected = true)
					{
						if(driver.findElement(By.xpath("//*[@uid='giftThisItemCheck']/div/input")).isDisplayed())
						{
							gen.clickElement(driver, "//*[@uid='giftThisItemCheck']/div/input", "Gift This Item", oPSelFW);
							if(!driver.findElements(By.xpath("(//*[@uid='extn_From']/div[2]/div/div/input)[1]")).isEmpty())
								gen.setText(driver, "(//*[@uid='extn_From']/div[2]/div/div/input)[1]", giftOpt[1], "Gift From", oPSelFW);
							else
								gen.setText(driver, "//*[@uid='extn_From']/div[2]/div/div/input", giftOpt[1], "Gift From", oPSelFW);
							Thread.sleep(1000);
							if(!driver.findElements(By.xpath("(//*[@uid='extn_To']/div[2]/div/div/input)[1]")).isEmpty())
								gen.setText(driver, "(//*[@uid='extn_To']/div[2]/div/div/input)[1]", giftOpt[2], "Gift To", oPSelFW);
							else
								gen.setText(driver, "//*[@uid='extn_To']/div[2]/div/div/input", giftOpt[2], "Gift To", oPSelFW);
							Thread.sleep(1000);
							if(driver.findElement(By.xpath("//*[@uid='giftWrapThisItemCheck']/div/input")).isEnabled())
							{
								driver.findElement(By.xpath("//*[@uid='giftWrapThisItemCheck']/div/input")).click();
								Thread.sleep(1000);
								if(!driver.findElements(By.xpath("(//*[@uid='extn_GiftWrapCode']/div[2]/div/div/input)[1]")).isEmpty()) 
									gen.setText(driver, "(//*[@uid='extn_GiftWrapCode']/div[2]/div/div/input)[1]", giftOpt[3], "Gift Wrap Code", oPSelFW);
								else
									gen.setText(driver, "//*[@uid='extn_GiftWrapCode']/div[2]/div/div/input", giftOpt[3], "Gift Wrap Code", oPSelFW);
								Thread.sleep(1000);
							}
							gen.waitnclickElement(driver, By.xpath("(//*[@uid='detailsGiftOptions']/span/div/div)[2]/span/span/span"), "Apply", oPSelFW);
							Thread.sleep(1000);
							gen.waitnclickElement(driver, By.xpath("(//*[@uid='updateButtonContainer'])[3]/span/span/span"), "Save", oPSelFW);
							Thread.sleep(1000);
						}
					}
				}
				if(!driver.findElements(By.xpath("//*[@id='idx_widget_SingleMessage_0_messageTitle']")).isEmpty())
				{
					String MessageTitle = driver.findElement(By.xpath("//*[@id='idx_widget_SingleMessage_0_messageTitle']")).getText();
					if(MessageTitle.contains("Order updated successfully"))
						oPSelFW.reportStepDetails("Verify if gift message is displayed", "Gift Message is updated successfully", "Pass");
					log.info( "Gift Message is updated successfully");
					}
				}
				By xpathBackToFulfiilmentSummery = By.xpath(BackToFulfiilmentSummery);
				gen.waitnclickElement(driver, xpathBackToFulfiilmentSummery, "Back to FullFillment Summary", oPSelFW);
				generic.waitUntilElementIsClickable(driver, 5000,"(//span[contains(text(),'Go to Gift Options')])");
				gen.waitnclickElement(driver, xpathNext, "Next", oPSelFW);
				Thread.sleep(25000);
				if(gen.existsElement(driver, xpathNext))
				{
					if(driver.findElement(xpathNext).isEnabled() && driver.findElement(xpathNext).isDisplayed())
					{
						gen.waitnclickElement(driver, xpathNext, "Next", oPSelFW);
						Thread.sleep(5000);
					}
				}
			}	
			else
			{
				gen.waitnclickElement(driver, xpathNext, "Next", oPSelFW);
				Thread.sleep(25000);
			}
		}
		catch(Exception e)
		{
			Assert.assertFalse(true);
		}
	}
	public void rushOrder(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws InterruptedException
	{
		try {
		driver.findElement(By.xpath("(((//*[@uid='orderlinestitlepane']/div)[2]/div/div/div)[7]/div)[3]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("(//*[@uid='changeLOS']/span/span/span)[3]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("(((//*[@uid='extn_Carrier_Service']/DIV)[2]/div/div)[2]/input)[1]")).sendKeys("Rush");
		Thread.sleep(200);
		driver.findElement(By.xpath("(((//*[@uid='extn_Carrier_Service']/DIV)[2]/div/div)[2]/input)[1]")).sendKeys(Keys.ENTER);
		Thread.sleep(200);
		driver.findElement(By.xpath("(((//*[@uid='extn_Carrier_Service']/DIV)[2]/div/div)[2]/input)[1]")).sendKeys(Keys.TAB);
		Thread.sleep(200);
		driver.findElement(By.xpath("(//*[@uid='Popup_btnNext']/span/span/span)[3]")).click();
		Thread.sleep(3000);
		if(!driver.findElements(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")).isEmpty())
		{	
			String errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")).getAttribute("innerText");
			if(errorMessage.contains("Order updated successfully"))
				oPSelFW.reportStepDetails("Verify if User is able to update Rush", "Rush is updated successfully message is displayed", "Pass");
			log.info("Rush is updated successfully message is displayed");
		}	
		}
		catch(Exception e)
		{
			System.out.println(e);
			Assert.assertTrue(false);
		}
	}
	public void subOrders(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws InterruptedException
	{
		try {
		driver.findElement(By.xpath("(((//*[@uid='orderlinestitlepane']/div)[2]/div/div/div)[7]/div)[3]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("(//*[@uid='changeAddress']/span/span/span)[3]")).click();
		Thread.sleep(200);
		enterShipToAddressDetails(driver, XLTestData, oPSelFW);
		}
		catch(Exception e)
		{
			System.out.println(e);
			Assert.assertTrue(false);
		}
	}
	
	public void enterBillToAddressDetails(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException
	{
		try {
			generic.waitUntilElementIsClickable(driver, 300, FirstName);
		String shipToAddDetails = XLTestData.get("NewShipToaddress").toString();
		String billToFirstName = XLTestData.get("BillToFirstName").toString();
		String billToLastName = XLTestData.get("BillToLastName").toString();
		String billToEmailAddress = XLTestData.get("BillToEmailAddress").toString();
		String billToPhoneNumber = XLTestData.get("BillToDayPhone").toString();
		String billToAddressLine = XLTestData.get("BillToAddressLine1").toString();
		gen.setText(driver, FirstName, billToFirstName, "Bill To First Name", oPSelFW);
		gen.setText(driver, LastNme, billToLastName, "Bill To Last Name", oPSelFW);
		gen.setText(driver, EmailAddress, billToEmailAddress, "Bill To Email Address", oPSelFW);
		gen.setTextAndHitEnter(driver, DayPhone, billToPhoneNumber, "Bill To Day Phone", oPSelFW);
		// Address Line
		gen.setText(driver, AdressLine1, billToAddressLine, "Bill To Address Line 1", oPSelFW);
		WebElement PostalCod = driver.findElement(By.xpath(PostalCode));
		PostalCod.sendKeys(XLTestData.get("BillToZip").toString());
		PostalCod.sendKeys(Keys.TAB);
		Thread.sleep(1000);
		ApplyElement = driver.findElement(XPathApplybtn);
		gen.shortwaitnclickElement(driver, XPathApplybtn, "Apply", oPSelFW);
		Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed in Address Screen", "Exception is displayed in Address Details Screen", "Fail");
			log.error("Exception is displayed in Address Details Screen");
			Assert.assertEquals("Verify if Exception is displayed in Address Screen", "Exception is displayed in Address Details Screen");
		}
	}
	
	public void enterShipToAddressDetails(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws IOException
	{
		try
		{String shipToAddDetails = XLTestData.get("NewShipToaddress").toString();
		if(!shipToAddDetails.contains("NoValueCellTypeBLANK"))
		{
			generic.waitUntilElementIsClickable(driver, 300, FirstName);
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
			Thread.sleep(1000);
			// Address Line
			gen.setText(driver, AdressLine1, ShipAddDetails[5], "Address Line 1", oPSelFW);
			Thread.sleep(100);
			WebElement PostalCod = driver.findElement(By.xpath(PostalCode));
			PostalCod.clear();
			PostalCod.sendKeys(ShipAddDet[2]);
			PostalCod.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			ApplyElement = driver.findElement(XPathApplybtn);
			gen.shortwaitnclickElement(driver, XPathApplybtn, "Apply", oPSelFW);
			Thread.sleep(5000);
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed in Address Screen", "Exception is displayed in Address Details Screen", "Fail");
			log.error("Exception is displayed in Address Details Screen");
			Assert.assertEquals("Verify if Exception is displayed in Address Screen", "Exception is displayed in Address Details Screen");
		}
	}
	
	
	public void clickOnUnRegisteredCustomer(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception
{
	generic.waitUntilElementIsClickable(driver, 1000, unregisteredCustomerLink);
	WebElement element = driver.findElement(unregisteredCustomerLink);
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click()", element);
	oPSelFW.reportStepDetails("Click on Unregistered Customer Link "  , "Unregistered Customer Link is clicked", "Pass");
	log.info("Unregistered Customer Link is clicked");

	}

public void enterAddressDetailsReturn(WebDriver driver, String AddressType, ProlificsSeleniumAPI oPSelFW, Map<String, String> XLTestData)
		throws InterruptedException, FileNotFoundException, IOException {
		String TitleOfPage = getTitleOfPage(driver);
		try
		{
			gen.waitUntilElementIsPresent(driver, BillTo);
		if(TitleOfPage.contains("Return Fulfillment Summary"))
		{
		if(driver.findElements(By.xpath(BillTo)).size() == 0)
		{
			oPSelFW.reportStepDetails("Verify if Address Page is displayed"  , "Address Page is not displayed", "Fail");
			log.error("Address Page is not displayed");
			driver.quit();
			Assert.assertTrue(false);
		}
		
		if(AddressType.contains("BillTo"))
		{
			final By BillToXPath = By.xpath(BillTo);
			gen.waitnclickElement(driver, BillToXPath, "Bill To", oPSelFW);
			enterBillToAddressDetails(driver, XLTestData, oPSelFW);
		}
		if(AddressType.contains("ShipTo"))
		{
			if(!XLTestData.get("TestCaseTitle").toString().toLowerCase().contains("sub order"))
			{
				final By ShipToXPath = By.xpath(ShipTo);
				gen.waitnclickElement(driver, ShipToXPath, "Ship To", oPSelFW);
				enterShipToAddressDetails(driver, XLTestData, oPSelFW);
			}
		}

		}
		}
		catch(Exception e)
		{
			oPSelFW.reportStepDetails("Verify if Exception is displayed"  , "Exception " + e + " is not displayed", "Fail"); 
			log.error( "Exception " + e + " is not displayed"); 			
			Assert.assertTrue(false);
		}}


public void numberOfBox(WebDriver driver,ProlificsSeleniumAPI oPSelFW) throws Exception
{
	Thread.sleep(3000);
	driver.findElement(By.xpath("(//*[@uid='extn_textfield_noboxes']/div[2]/div/div)[1]/input")).click();
	driver.findElement(By.xpath("(//*[@uid='extn_textfield_noboxes']/div[2]/div/div)[1]/input")).clear();
	
	driver.findElement(By.xpath("(//*[@uid='extn_textfield_noboxes']/div[2]/div/div)[1]/input")).sendKeys("4");
	driver.findElement(By.xpath("(//*[@uid='extn_textfield_noboxes']/div[2]/div/div)[1]/input")).sendKeys(Keys.TAB);
	Thread.sleep(3000);

}

public void unLockholds(WebDriver driver,ProlificsSeleniumAPI oPSelFW) throws Exception
{
	Thread.sleep(5000);
	WebDriverWait wait = new WebDriverWait(driver, 50000);
	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(lockIcon));
	gen.waitnclickElement(driver, lockIcon);
	Thread.sleep(5000);
	driver.findElement(By.xpath("//*[@uid='activeHolds']/div[2]/div/div[2]/table/tbody/tr/td/span")).click();
	//gen.waitnclickElement(driver, By.xpath("//*[@uid='activeHolds']/div[2]/div/div[2]/table/tbody/tr/td/span"));
	Thread.sleep(3000);
	gen.waitnclickElement(driver, saveHolds, "Save Holds", oPSelFW);
	Thread.sleep(5000);
	String HoldRemovedSuccessfullyMessage = driver.findElement(By.xpath("//*[@class='messageTitles']")).getText();
	if(HoldRemovedSuccessfullyMessage.contains("Holds have been resolved successfully"))
		Thread.sleep(5000);
		oPSelFW.reportStepDetails("Verify if user is able to resolve the Holds of the Order"  , "Holds have been resolved successfully", "Pass");
		log.info("Holds have been resolved successfully");
		Thread.sleep(5000);
		gen.waitnclickElement(driver, backToFullfillmentOption);

		Thread.sleep(5000);



}	
//click on BillTOLink
	public void entershipToNBillToAddressDetails(WebDriver driver, String AddressType, ProlificsSeleniumAPI oPSelFW, Map<String, String> XLTestData)
			throws InterruptedException, FileNotFoundException, IOException {
			String TitleOfPage = getTitleOfPage(driver);
			String shipToAddDetails = XLTestData.get("NewShipToaddress").toString();
			String billToFirstName = XLTestData.get("BillToFirstName").toString();
			String billToLastName = XLTestData.get("BillToLastName").toString();
			String billToEmailAddress = XLTestData.get("BillToEmailAddress").toString();
			String billToPhoneNumber = XLTestData.get("BillToDayPhone").toString();
			String billToAddressLine = XLTestData.get("BillToAddressLine1").toString();
			try
			{	
						WebDriverWait wait = new WebDriverWait(driver, 5000);
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(BillTo)));
						if(TitleOfPage.contains("Add Address"))
						{
					if(driver.findElements(By.xpath(BillTo)).size() == 0)
					{
						oPSelFW.reportStepDetails("Verify if Address Page is displayed"  , "Address Page is not displayed", "Fail");
						log.error("Address Page is not displayed");
						driver.quit();
						Assert.assertTrue(false);
					}
			
					if(AddressType.contains("BillTo"))
					{
						final By BillToXPath = By.xpath(BillTo);
						gen.waitnclickElement(driver, BillToXPath, "Bill To", oPSelFW);
						enterBillToAddressDetails(driver, XLTestData, oPSelFW);
					}
					if(AddressType.contains("ShipTo"))
					{
						if(!XLTestData.get("TestCaseTitle").toString().toLowerCase().contains("sub order"))
						{
							final By ShipToXPath = By.xpath(ShipTo);
							gen.waitnclickElement(driver, ShipToXPath, "Ship To", oPSelFW);
							enterShipToAddressDetails(driver, XLTestData, oPSelFW);
						}
					}
			
					int i = 1;
					WebElement NextElement = driver.findElement(By.xpath(Nextbtn));
					while (!NextElement.isDisplayed() && !NextElement.isEnabled() && i<=20) {
						Thread.sleep(1000);
						i++;
					}
					if(!driver.findElements(By.xpath("//div[starts-with(@id, 'idx_widget_SingleMessage')]/div")).isEmpty())
					{
						String ErrorMessage = driver.findElement(By.xpath("//div[starts-with(@id, 'idx_widget_SingleMessage')]/div")).getText();
						if(!driver.findElements(By.xpath("//div[starts-with(@id, 'idx_widget_SingleMessage')]/div")).isEmpty())
						{
							if(ErrorMessage.trim().length() > 0)
								oPSelFW.reportStepDetails("Verify if Error message is displayed "  , ErrorMessage + " is displayed", "Fail");
							log.error( ErrorMessage + " is displayed");
					int NumberofCheckBoxes = driver.findElements(By.xpath("//*[starts-with(@id, 'idx_form_CheckBox_')]")).size();
					for(int CheckBoxNum = 1; CheckBoxNum <= NumberofCheckBoxes; CheckBoxNum++)
					{
						By checkBoxXpath = By.xpath("(//*[starts-with(@id, 'idx_form_CheckBox_')])["+CheckBoxNum+"]");
						if (driver.findElement(checkBoxXpath).isDisplayed() && driver.findElement(checkBoxXpath).isEnabled() ) {
							gen.waitnclickElement(driver, checkBoxXpath, "Checkbox", oPSelFW);
						}
					}
					Thread.sleep(2000);
					if (driver.findElements(XPathApplybtn).size() > 0)
					{
						gen.shortwaitnclickElement(driver, XPathApplybtn, "Apply", oPSelFW);
						Thread.sleep(5000);
					}
				}
			}
			if(driver.findElements(XPathApplybtn).size() > 0)
			{
				if(ApplyElement.isDisplayed() && ApplyElement.isEnabled())
				{
					gen.shortwaitnclickElement(driver, XPathApplybtn, "Apply", oPSelFW);
				}
				if(driver.findElements(XPathApplybtn).size() > 0)
				{
					if(ApplyElement.isDisplayed() && ApplyElement.isEnabled())
					{
						gen.shortwaitnclickElement(driver, XPathApplybtn, "Apply", oPSelFW);
					}
				}
			}
			if(gen.existsElement(driver, By.xpath("//span[contains(@id,'dijit_Dialog_')]")))
				gen.shortwaitnclickElement(driver, XpathCancelbtn, "Cancel", oPSelFW);
		}
		else
		{
			oPSelFW.reportStepDetails("Verify if Address Page is displayed or not"  , "Add Address Page is not displayed", "Fail");
			log.error("Add Address Page is not displayed");
			Assert.assertTrue(false);
		}
		}
		catch(Exception e)
		{
			oPSelFW.reportStepDetails("Verify if Exception is displayed"  , "Exception " + e + " is not displayed", "Fail");
			log.error( "Exception " + e + " is not displayed");			
			Assert.assertTrue(false);
		}
	}		
}
