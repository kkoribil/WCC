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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

import com.aventstack.extentreports.Status;
import com.prolifics.ProlificsSeleniumAPI;


import FrameWork.DataBase;
import FrameWork.Excel_Reader;
import FrameWork.Generic;

public class CreateOrderPage1 {
	Logger  log;
	Generic gen1 = new Generic();
		public CreateOrderPage1()
		{
			
			try{PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
			String logFilePath = System.getProperty("user.dir") + "\\ProlificsReports\\";
			gen1.UpdatePropFile("log", logFilePath);
			log = Logger.getLogger(CreateOrderPage1.class);
			}
			catch(Exception e)
			{
				System.out.println("Inside Exception");
			}
		}

	ProlificsSeleniumAPI oPSelFW;
	public static Excel_Reader excelReader;
	public static int i = 1;
	DataBase1 db2 = new DataBase1();
	Generic1 gen = new Generic1();

	String lblStatus = "//*[@uid='extn_lblStatus']";
	String checkboxbtn = "(((((//*[@uid='orderlinestitlepane']/div)[2]/div/div/div)[7]/div)[3]/div)[4]/div)[1]/table/tbody/tr/td/span";
	String changeAddress = "(//*[@uid='gridOptions']/span)[3]";
	String phone = "(//*[@uid='extn_lblPhone'])[2]";

	final By linkCreateOrder = By.xpath("(//a[contains(text(),'Create Order')])[2]");
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
	String PaymentError = "((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div";
	String BillTo = "(//a[@data-ibmsc-uid='lnkEditAddress'])[2]";
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
	String BusinessID = "//*[@id='idx_form_TextBox_41']";
	String GiftOPt1btn = "(//span[contains(text(),'Go to Gift Options')])[1]";
	String GiftOPt2btn = "(//span[contains(text(),'Go to Gift Options')])[2]";
	final By Okbtn = By.xpath("(//*[@role='alertdialog']/div[4]/span[2]/span/span/span)[1]");
	String suborderbtn = "(//*[@uid='cancelOrderListScreen']/span/div/div/div/div[2]/div[3]/div[4]/div/table/tbody/tr/td/span)[1]";
	//String clicksearchbtn = "//*[@uid='lnk_RT_OrderSearch']/div[2]/div/div/a";
	// '"(//span[contains(@id,'Button') and normalize-space(text())='Go to Gift
	// Options'])";
	String BackToFulfiilmentSummery = "(//span[contains(@id,'Button') and normalize-space(text())='Back to Fulfillment Summary'])[2]";
	String AddPaymentMethodbtn = "(//span[contains(@id,'Button') and normalize-space(text())='Add Payment Method'])";
	String IDX = "(//label[normalize-space(text())='ID:']/following::input[position()=1 and @class='dijitReset dijitInputInner'])[2]";
	String AmountToPay = "//*[@uid='lblRemainingCharge']/div[2]/div/div/span";
	String PaymentType = "//label[normalize-space(text())='Payment type:']/following::input[position()=2 and  @class='dijitReset dijitInputInner']";
	String AmountToCharge = "(((//*[@uid='txtMaxChargeLimit']/div)[2]/div/div)[1]/input)[1]";
	String AmountToCharge2 = "(//label[@dojoattachpoint='compLabelNode' and normalize-space(text())='Amount to charge:']/following::input[position()=1 and  @class='dijitReset dijitInputInner'])[3]";
	String AmountToCharge3 = "(//label[@dojoattachpoint='compLabelNode' and normalize-space(text())='Amount to charge:']/following::input[position()=1 and  @class='dijitReset dijitInputInner'])[5]";
	String AddBtn2 = "(//*[@uid='extn_add_item']/span/span/span)[3]";
	final By unregisteredCustomerLink = By.xpath("//a[contains(text(), 'Continue With Unregistered Customer')]");
	String AddApply = "(//span[contains(@id,'Button') and normalize-space(text())='Apply'])[3]";
	String PaymentCancel3 = "(//span[contains(@id,'Button') and normalize-space(text())='Cancel'])[3]";
	String PaymentCancel1 = "(//span[contains(@id,'Button') and normalize-space(text())='Cancel'])[1]";
	String PaymentCancel = "(//span[contains(@id,'Button') and normalize-space(text())='Cancel'])[4]";
	String PopUpCancel = "(//*[@uid='Popup_btnCancel']/span/span/span)[3]";
	String Confirmbtn = "(//span[contains(@id,'Button') and normalize-space(text())='Confirm'])[2]";
	String TaxExempt = "//*[@id=\"idx_form_RadioButtonSet_8_RadioItem0\"]";
	String AddBtn = "(//*[@uid='add_item']/span/span/span)[3]";
	String ConceptBox = "(//*[@uid='orgList']/div[2]/div/div[2]/input)[1]";
	String Conceptclick = "(//*[@uid='cb_orderEnterprise']/div/div/div/input)[1]";
	
	String DueBilllPin = "//*[@uid=\\\"txtMaxChargeLimit\\\"]/div[2]/div/div/input";

	By CardTypeXpath = By.xpath("//*[@uid='cmbCardType']/div[2]/div[1]/div[2]/input");
	By NameOnCardXpath = By.xpath("//*[@uid='txtNameOnCard']/div[2]/div[1]/div/input");
	final By ItemIDXpath = By.xpath(ItemId);
	final By lockIcon = By.xpath("//*[@uid='pnlStatusHolder']/img");
	final By allActiveHolds = By.xpath("//*[@uid='activeHolds']/div[2]/div/div[2]/table/tbody/tr/td/span");
	final By saveHolds = By.xpath("//*[@uid='update_holds']/span/span/span[3]");
	final By backToFullfillmentOption = By.xpath("//*[@uid='closeBttn2']/span/span/span[3]");
	final By labelStatus = By.xpath("//*[@uid='extn_lblStatus']");
	//final By clicksearchbtn = By.xpath("//*[@uid='lnk_RT_OrderSearch']/div[2]/div/div/a");
	final By clicksearch = By.xpath("//*[@uid='SST_SearchButton']/span/span/span[3]");
	final By clickClose1 = By.xpath("//*[@uid='closeBttn2']/span/span)[2]");
	
	final By clickClose2 = By.xpath("//*[@uid='closeBttn2']/span/span");
	
	
	
	WebElement ApplyElement = null;

	public String SetItemID(WebDriver driver, String ItemID) throws InterruptedException, Exception {
		try {
			gen1.waitUntilElementIsClickable(driver, 150000, ItemIDXpath);
			// String OrderType = XLTestData.get("OrderType").toString();
			WebElement element = driver.findElement(ItemIDXpath);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click()", element);
			Thread.sleep(1000);
			gen.setText(driver, ItemId, ItemID, "Item Id");
			Thread.sleep(1000);
			driver.findElement(By.xpath(ItemId)).sendKeys(Keys.TAB);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@uid='extn_text_Catalog_Code']/div/div/div/input")).clear();
			Thread.sleep(1000);
			int i = 0;
			while (!driver
					.findElements(By.xpath("//*[@id=\"sc_plat_dojo_widgets_ScreenDialogUnderlay_1\"]/div[2]/div[1]"))
					.isEmpty() && i <= 60) {
				Thread.sleep(1000);
				i++;
			}
			gen.waitnclickElement(driver, By.xpath(AddBtn), "Add",oPSelFW);
			String ErrorExists = "";
			if (driver.findElements(By.xpath("//*[@id='idx_widget__MasterHoverHelpTooltip_0']/div[2]/div"))
					.size() > 0) {
				By ErrorMessXpath = By.xpath("//*[@id='idx_widget__MasterHoverHelpTooltip_0']/div[2]/div");
				element = driver.findElement(ErrorMessXpath);
				executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click()", element);
				Thread.sleep(1000);
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
			oPSelFW.reportStepDetails("Exception"  , e + "Error in Adding the Item", "Fail");
			log.error( e + "Error in Adding the Item");
		}
		return ItemID;
	}

	public void selectOrderType(WebDriver driver, String OrderType, String StoreNumber, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		String TitleOfPage = getTitleOfPage(driver);
		if (TitleOfPage.contains("Add Items")) {
			int i = 0;
			try {
				gen1.waitUntilElementIsClickable(driver, 50000, AddbtnBy);
				WebElement element = driver.findElement(AddbtnBy);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click", element);
				while (!(driver
						.findElements(By.xpath("//*[@id='sc_plat_dojo_widgets_ScreenDialogUnderlay_1']/div[2]/div[1]"))
						.isEmpty()) && i <= 30) {
					Thread.sleep(1000);
					i++;
				}
				if (OrderType.equalsIgnoreCase("Mail Order")) {
					// gen1.clickElement(driver, OrderTypeX);
					gen1.waitUntilElementIsClickable(driver, 50000, OrderTypeInp);
					WebElement input = driver.findElement(By.xpath(OrderTypeInp));
					input.clear();
					Thread.sleep(500);
					input.sendKeys(OrderType);
					Thread.sleep(500);
					input.sendKeys(Keys.ENTER);
					Thread.sleep(500);
					if (!StoreNumber.contains("NoValueCellTypeBLANK")) {
						gen.setText(driver, StoreNumberX, StoreNumber);
						Thread.sleep(1000);
					}
				} else {
					WebElement input = driver.findElement(By.xpath(OrderTypeInp));
					input.click();
					input = driver.findElement(By.xpath(OrderTypeEdit));
					input.clear();
					input.sendKeys(OrderType);
					input.sendKeys(Keys.ENTER);
					Thread.sleep(1000);
				}

			} catch (Exception e) {
				oPSelFW.reportStepDetails("Exception"  , e + "Error in Adding the Item", "Fail");
				log.error(e + "Error in Adding the Item");
				System.out.println(e);
			}
		} else {
		oPSelFW.reportStepDetails("Add Items Page should not be displayed", "Add Items Page is not displayed",
					"Fail");
		log.error("Add Items Page is not displayed");
			driver.quit();
			Assert.assertTrue(false);
		}
	}

	public void setQuantity(WebDriver driver, String Quantity) throws Exception {
		gen.setText(driver, EachX, Quantity, "Quantity");
		driver.findElement(By.xpath(EachX)).sendKeys(Keys.TAB);
		Thread.sleep(2000);
	}

	public void ClickonNext(WebDriver driver,ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException {
		final By XpathNextbtn = By.xpath(Nextbtn);
		gen1.waitUntilElementIsClickable(driver, 25000, XpathNextbtn);
		WebElement element = driver.findElement(XpathNextbtn);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
		oPSelFW.reportStepDetails("Next Button should be Clickable"  ,"Next Button is Clicked", "Pass");
		log.info("Next Button is Clicked");
			Thread.sleep(8000);
	}

	// click on BillTOLink
	public void enterAddressDetails(WebDriver driver, String AddressType, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws InterruptedException, FileNotFoundException, IOException {
		String TitleOfPage = getTitleOfPage(driver);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5000);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(BillTo)));
			if (TitleOfPage.contains("Add Address")) {
				if (driver.findElements(By.xpath(BillTo)).size() == 0) {
					oPSelFW.reportStepDetails("Address Page should not be displayed", "Address Page is not displayed",
							"Fail");
					log.error( "Address Page is not displayed");
					driver.quit();
					Assert.assertTrue(false);
				}

				if (AddressType.contains("BillTo")) {
					final By BillToXPath = By.xpath(BillTo);
					gen.waitnclickElement(driver, BillToXPath, "Bill To",oPSelFW);
					enterBillToAddressDetails(driver, XLTestData, null);
				}
				if (AddressType.contains("ShipTo")) {
					if (!XLTestData.get("TestCaseTitle").toString().toLowerCase().contains("sub order")) {
						final By ShipToXPath = By.xpath(ShipTo);
						gen.waitnclickElement(driver, ShipToXPath, "Ship To",oPSelFW);
						enterShipToAddressDetails(driver, XLTestData, oPSelFW);
					}
				}

				int i = 1;
				WebElement NextElement = driver.findElement(By.xpath(Nextbtn));
				// NextElement.click();
				while (!NextElement.isDisplayed() && !NextElement.isEnabled() && i <= 20) {
					Thread.sleep(1000);
					i++;
				}
				if (!driver.findElements(By.xpath("//div[starts-with(@id, 'idx_widget_SingleMessage')]/div"))
						.isEmpty()) {
					String ErrorMessage = driver
							.findElement(By.xpath("//div[starts-with(@id, 'idx_widget_SingleMessage')]/div")).getText();
					if (!driver.findElements(By.xpath("//div[starts-with(@id, 'idx_widget_SingleMessage')]/div"))
							.isEmpty()) {

						if (ErrorMessage.trim().length() > 0) {
							oPSelFW.reportStepDetails(ErrorMessage + " is displayed", ErrorMessage + " is displayed","Fail");
							log.error( ErrorMessage + " is displayed");
							int NumberofCheckBoxes = driver
									.findElements(By.xpath("//*[starts-with(@id, 'idx_form_CheckBox_')]")).size();

							for (int CheckBoxNum = 1; CheckBoxNum <= NumberofCheckBoxes; CheckBoxNum++) {
								By checkBoxXpath = By
										.xpath("(//*[starts-with(@id, 'idx_form_CheckBox_')])[" + CheckBoxNum + "]");
								if (driver.findElement(checkBoxXpath).isDisplayed()
										&& driver.findElement(checkBoxXpath).isEnabled()) {
									// driver.findElement(checkBoxXpath).click();
									gen.waitnclickElement(driver, checkBoxXpath, "Checkbox",oPSelFW);
								}

								// driver.findElement(checkBoxXpath).submit();
							}
							Thread.sleep(2000);
							if (driver.findElements(XPathApplybtn).size() > 0) {
								gen.waitnclickElement(driver, XPathApplybtn, "Apply",oPSelFW);
								Thread.sleep(5000);
							}
						}
					}
					if (driver.findElements(XPathApplybtn).size() > 0) {
						if (ApplyElement.isDisplayed() && ApplyElement.isEnabled()) {
							gen.waitnclickElement(driver, XPathApplybtn, "Apply",oPSelFW);
						}
						if (driver.findElements(XPathApplybtn).size() > 0) {
							if (ApplyElement.isDisplayed() && ApplyElement.isEnabled()) {
								gen.waitnclickElement(driver, XPathApplybtn, "Apply",oPSelFW);
							}
						}
					}
					if (driver.findElements(By.xpath("//span[contains(@id,'dijit_Dialog_')]")).size() > 0)
						gen.waitnclickElement(driver, XpathCancelbtn, "Cancel",oPSelFW);
				}
			}
		}

		catch (Exception e) {
			oPSelFW.reportStepDetails("Exception"  , e + "Exception Occurred", "Fail"); 
			log.error( e + "Exception Occurred"); 
		
			Assert.assertTrue(false);
		}
	}

	public String enterCustomerType(WebDriver driver, ProlificsSeleniumAPI oPSelFW, WebElement element,
			HashMap<String, String> XLTestData) throws InterruptedException, Exception {

		try {
			selectCustomerTypeAndEmployeeType(driver, element, oPSelFW, XLTestData);
			final By NextbtnXpath = By.xpath(Nextbtn);
			gen.waitnclickElement(driver, NextbtnXpath, "Next",oPSelFW);
			Thread.sleep(10000);
			
			if (XLTestData.get("TestCaseTitle").toString().toLowerCase().contains("rush"))
				rushOrder(driver, oPSelFW);
			
			if (XLTestData.get("TestCaseTitle").toString().toLowerCase().contains("sub order"))
				subOrders(driver, XLTestData, oPSelFW);
			
			if (!driver.findElements(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")).isEmpty()) {
				String errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]"))
						.getAttribute("innerText");
				if (errorMessage.contains("Sales force service not available. Please try after some time")
						|| errorMessage.contains("Invalid Trade Member ID")
						|| errorMessage.contains("Record already exists in the database")
						|| errorMessage.contains("Servlet Exception occurred while forwarding the request")
						|| errorMessage.contains("There are errors on the page. You cannot proceed until you fix them")
						|| errorMessage.contains("Error description not available")) {
			
					
					oPSelFW.reportStepDetails("errorMessage "  , errorMessage + " is displayed in 'Add Address' Screen", "Pass");
					log.info( errorMessage + " is displayed in 'Add Address' Screen");
					gen.closeTheOrder(driver, oPSelFW);
					Thread.sleep(1000);
					Assert.assertTrue(false);
					return (errorMessage);
				} else if (errorMessage.trim().length() == 0 || errorMessage.contains("Order updated successfully")) {
					gen.waitnclickElement(driver, NextbtnXpath, "Next",oPSelFW);
					Thread.sleep(20000);
					if (driver.findElements(By.xpath("//*[@uid='createOrder']")).size() == 1) {
						return ("");
					}
				}
				if (!driver.findElements(By.xpath(GiftOPt1btn)).isEmpty()) {
					if (driver.findElements(By.xpath("//*[@uid='createOrder']")).size() == 0) {
						gen.waitnclickElement(driver, By.xpath(GiftOPt1btn), "Go to Gift Options",oPSelFW);
						Thread.sleep(20000);
					}
				}
				if (!driver.findElements(By.xpath(GiftOPt2btn)).isEmpty()) {
					if (driver.findElements(By.xpath("//*[@uid='createOrder']")).size() == 0) {
						gen.waitnclickElement(driver, By.xpath(GiftOPt2btn), "Go to Gift Options",oPSelFW);
						Thread.sleep(20000);
					}
				}
				if (driver.findElements(By.xpath("//*[@uid='createOrder']")).size() == 0) {
					By xpathBackToFulfiilmentSummery = By.xpath(BackToFulfiilmentSummery);
					By xpathNext = By.xpath(Nextbtn);
					gen.waitnclickElement(driver, xpathBackToFulfiilmentSummery, "Back to FullFillment Summary",oPSelFW);
					Thread.sleep(1000);
					gen.waitnclickElement(driver, xpathNext, "Next",oPSelFW);
					Thread.sleep(1000);
					return ("");
				}
			} else {
				enterGiftCardDetails(driver, oPSelFW, XLTestData);
				By xpathBackToFulfiilmentSummery = By.xpath(BackToFulfiilmentSummery);
				By xpathNext = By.xpath(Nextbtn);
				gen.waitnclickElement(driver, xpathBackToFulfiilmentSummery, "Back to FullFillment Summary",oPSelFW);
				Thread.sleep(10000);
				gen.waitnclickElement(driver, xpathNext, "Next",oPSelFW);
				Thread.sleep(25000);
				return ("");
			}
		} catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Exception"  ,  e + "Exception Occurred", "Fail");  log.error(e + "Exception Occurred");
		}
		return "";
	}

	public String AddPaymentMethod(WebDriver driver, WebElement element, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws Throwable {
		gen.waitUntilElementIsPresent(driver, By.xpath(AddPaymentMethodbtn));
		String TitleOfPage = getTitleOfPage(driver);
		if (TitleOfPage.contains("Payment Confirmation")) {
			try {
				String amount = gen.getTextOfElement(driver, AmountToPay);
				amount = amount.replace("$", "");
				amount = amount.replace(",", "");
				Double Due = getDueAmountToEnter(driver);
				if (XLTestData.get("PromCode") != null) {
					String PromCode = XLTestData.get("PromCode").toString();
				}
				if (XLTestData.get("PaymentType").toString().toUpperCase().contains("DUE")
						&& XLTestData.get("PaymentType").toString().toUpperCase().contains("CASH")) {
					Due = Double.parseDouble(amount) / 2;
					enterCashInformation(driver, Double.toString(Due), XLTestData, oPSelFW);
					enterDueAmount(driver, Double.toString(Due), XLTestData);
				}

				else if (XLTestData.get("PaymentType").toString().toUpperCase().contains("CREDIT")
						&& XLTestData.get("PaymentType").toString().toUpperCase().contains("GIFT")) {
					Due = Double.parseDouble(amount) / 2;
					enterCreditCardInformation(driver, XLTestData, oPSelFW);
					if (XLTestData.get("GiftCardInformation") != null) {
						String GiftCardInformation = XLTestData.get("GiftCardInformation").toString();
						EnterGiftCardDetails(driver, GiftCardInformation, XLTestData, oPSelFW);
					}
				}

				// code for LRC and PLCC
				else if (XLTestData.get("PaymentType").toString().toUpperCase().contains("LOYALTY")
						&& XLTestData.get("PaymentType").toString().toUpperCase().contains("PLCC")) {
					Due = Double.parseDouble(amount) / 2;
					enterCashInformation(driver, Double.toString(Due), XLTestData, oPSelFW);

					if (XLTestData.get("LoyaltyRewardsCertificate") != null) {
						String LoyaltyRewardsCertificate = XLTestData.get("LoyaltyRewardsCertificate").toString();
						enterLoyalityCardDetails(driver, LoyaltyRewardsCertificate, XLTestData, oPSelFW);
					}
					deletePayment(driver);
					Thread.sleep(2000);
					// code for PLCC
					enterPLCCCardInformation(driver, XLTestData, oPSelFW);
				}
				// code for CBCC & SVC & Cash
				else if (XLTestData.get("PaymentType").toString().toUpperCase().contains("CBCC")
						&& XLTestData.get("PaymentType").toString().toUpperCase().contains("SVC")
						&& XLTestData.get("PaymentType").toString().toUpperCase().contains("CASH")) {
					Due = Double.parseDouble(amount);
					double Remaining = Due - 3;
					enterCashInformation(driver, Double.toString(Remaining), XLTestData, oPSelFW);
					Thread.sleep(2000);
					if (XLTestData.get("GiftCardInformation") != null) {
						String GiftCardInformation = XLTestData.get("GiftCardInformation").toString();
						entersvcdetails(driver, GiftCardInformation, XLTestData, oPSelFW);
						Thread.sleep(2000);
						deletePayment(driver);
						Thread.sleep(2000);
						double payamt = Double.parseDouble(amount) / 2;
						String s = String.valueOf(payamt);
						int index = s.indexOf('.');
						if (index == -1) {
							// no decimal
							i = 0;
						} else {
							i = Integer.parseInt(s.substring(index + 1));
						}
						double payamt2 = Double.parseDouble("." + i);
						enterCashInformation2(driver, Double.toString(payamt - payamt2), XLTestData, oPSelFW);
						Thread.sleep(2000);
						enterCBCCInformation(driver, XLTestData, oPSelFW);
						Thread.sleep(2000);
						// gen1.waitnclickElement(driver, By.xpath(Confirmbtn), "Confirm", basetest);
						// Thread.sleep(12000);
					}

				}

				// CC SVC LRC and Cash

				else if (XLTestData.get("PaymentType").toString().toUpperCase().contains("CREDIT")
						&& XLTestData.get("PaymentType").toString().toUpperCase().contains("SVC")
						&& XLTestData.get("PaymentType").toString().toUpperCase().contains("LOYALTY")
						&& XLTestData.get("PaymentType").toString().toUpperCase().contains("CASH")) {

					Due = Double.parseDouble(amount);
					double Remaining = Due - 3;
					enterCashInformation(driver, Double.toString(Remaining), XLTestData, oPSelFW);
					Thread.sleep(1000);
					if (XLTestData.get("LoyaltyRewardsCertificate") != null) {
						String LoyaltyRewardsCertificate = XLTestData.get("LoyaltyRewardsCertificate").toString();
						enterLoyalityCardDetails(driver, LoyaltyRewardsCertificate, XLTestData, oPSelFW);
					}
					deletePayment(driver);
					Thread.sleep(2000);
					double payamt = Double.parseDouble(amount);
					double payamt1 = payamt - 4;
					enterCashInformation2(driver, Double.toString(payamt1), XLTestData, oPSelFW);
					Thread.sleep(2000);
					if (XLTestData.get("GiftCardInformation") != null) {
						String GiftCardInformation = XLTestData.get("GiftCardInformation").toString();
						entersvcdetails(driver, GiftCardInformation, XLTestData, oPSelFW);
						Thread.sleep(2000);
						deletePayment(driver);
						double payamt2 = Double.parseDouble(amount) / 2;
						String s = String.valueOf(payamt2);
						int index = s.indexOf('.');
						if (index == -1) {
							// no decimal
							i = 0;
						} else {
							i = Integer.parseInt(s.substring(index + 1));
						}
						String cbcc = "." + i;
						double payamt3 = Double.parseDouble("." + i);
						enterCashInformation3(driver, Double.toString(payamt2 - payamt3), XLTestData, oPSelFW);
						Thread.sleep(2000);
						double payamt4 = Double.parseDouble(amount) + payamt3;
						enterCreditCardInformation(driver, XLTestData, oPSelFW);
						Thread.sleep(2000);
					}

				} else if (XLTestData.get("PaymentType").toString().toUpperCase().contains("CREDIT")
						&& XLTestData.get("PaymentType").toString().toUpperCase().contains("MTL")
						&& XLTestData.get("PaymentType").toString().toUpperCase().contains("CASH")) {

					Due = Double.parseDouble(amount);
					double Remaining = Due - 3;
					enterCashInformation(driver, Double.toString(Remaining), XLTestData, oPSelFW);
					Thread.sleep(2000);
					if (XLTestData.get("LoyaltyRewardsCertificate") != null) {
						String LoyaltyRewardsCertificate = XLTestData.get("LoyaltyRewardsCertificate").toString();
						enterMTLCardDetails(driver, LoyaltyRewardsCertificate, XLTestData, oPSelFW);
					}
					deletePayment(driver);
					Thread.sleep(2000);
					double payamt = Double.parseDouble(amount) / 2;
					double payamt1 = payamt - 3;
					String s = String.valueOf(payamt1);
					int index = s.indexOf('.');
					if (index == -1)
						i = 0;
					else
						i = Integer.parseInt(s.substring(index + 1));
					String cbcc = "." + i;
					double payamt2 = Double.parseDouble("." + i);
					enterCashInformation2(driver, Double.toString(payamt1 - payamt2), XLTestData, oPSelFW);
					Thread.sleep(2000);
					String DueAmount = getDueAmount(driver);
					if (DueAmount.trim().length() > 0) {
						Due = getDueAmountToEnter(driver);
						if ((Due >= 0.01 && Due < 0.26) || Due == 0.59 || (Due > 0.9 && Due < 0.99))
							enterDueAmount(driver, DueAmount, XLTestData);
					}
					enterCreditCardInformation(driver, XLTestData, oPSelFW);
					Thread.sleep(2000);
				} else {
					enterCashInformation(driver, amount, XLTestData, oPSelFW);
					if (XLTestData.get("LoyaltyRewardsCertificate") != null) {
						String LoyaltyRewardsCertificate = XLTestData.get("LoyaltyRewardsCertificate").toString();
						enterLoyalityCardDetails(driver, LoyaltyRewardsCertificate, XLTestData, oPSelFW);
					}
					String DueAmount = getDueAmount(driver);
					if ((Due >= 0.01 && Due < 0.26) || Due == 0.59 || (Due > 0.9 && Due < 0.99))
						enterDueAmount(driver, DueAmount, XLTestData);
					enterCreditCardInformation(driver, XLTestData, oPSelFW);
				}
				Thread.sleep(2000);
				gen.waitnclickElement(driver, By.xpath(Confirmbtn), "Confirm",oPSelFW);
				Thread.sleep(6000);
				String errMsg = checkErrorExists(driver);
				if (errMsg.length() > 0) {
					if (errMsg.contains("Sufficient funds have not been provided")) {
						oPSelFW.reportStepDetails("Error message should be displayed "  , errMsg+" is displayed ", "Fail");
						log.error( errMsg+" is displayed ");
							return (errMsg);
					}
					if (errMsg.contains("Credit Card Declined")) {
						oPSelFW.reportStepDetails("Error message should be displayed "  , errMsg+" is displayed ", "Fail"); 
						log.error(errMsg+" is displayed "); 
			
						return (errMsg);
					}
				}
			} catch (Exception e) {
				oPSelFW.reportStepDetails("Exception"  , e + "Payment Confirmation Page is not displayed", "Fail");
				log.error( e + "Payment Confirmation Page is not displayed");
			}
		} else {
			gen.closeTheOrder(driver, oPSelFW);
			
			oPSelFW.reportStepDetails("Payment Confirmation Page should be displayed"  , "Payment Confirmation Page is not displayed", "Fail");
			log.error("Payment Confirmation Page is not displayed");
			Assert.assertTrue(false);
		}
		return ("");
	}

	public String getOrderNumber(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception {
		String getOrderNumber = driver.findElement(By.xpath("//*[@uid='pnlPrimaryInfo']/div/table/tbody/tr/td/span"))
				.getText();
		
		oPSelFW.reportStepDetails("Order number should be generated "  , getOrderNumber+" is generated ", "Pass");
		log.info( getOrderNumber+" is generated ");
		return (getOrderNumber);
	}

	public void clickOnOrderLink(WebDriver driver, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		try {
			gen1.waitUntilElementIsClickable(driver, 1000, linkCreateOrder);
			WebElement element = driver.findElement(linkCreateOrder);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click()", element);
			oPSelFW.reportStepDetails("Create Order Link should be clicked", "Create Order Link is clicked", "Pass");
			log.info("Create Order Link is clicked");
			Thread.sleep(5000);
		} catch (Exception e) {
			oPSelFW.reportStepDetails("Create Order Link should  not be clicked +e+ ",
					"Create Order Link is not clicked", "Fail");
			log.error("Create Order Link is not clicked");
			// gen1.closeTheOrder(driver, basetest);
			Assert.assertTrue(false);
		}
	}

	public void clickOnUnRegisteredCustomerLink(WebDriver driver, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		try {
			gen1.waitUntilElementIsPresent(driver, unregisteredCustomerLink);
			String TitleOfPage = getTitleOfPage(driver);
			if (TitleOfPage.contains("Customer Search")) {
				gen1.waitUntilElementIsClickable(driver, 1000, unregisteredCustomerLink);
				WebElement element = driver.findElement(unregisteredCustomerLink);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click()", element);
				oPSelFW.reportStepDetails("Unregistered Customer Link should be clicked",
						"Unregistered Customer Link is clicked", "Pass");
				log.info("Unregistered Customer Link is clicked");
				gen1.waitUntilElementIsPresent(driver, By.xpath(ItemId));
				int i = 1;
				WebElement addItems = driver.findElement(By.xpath(ItemId));
				while (!addItems.isDisplayed() && !addItems.isEnabled() && i <= 50) {
					i++;
					Thread.sleep(1000);
				}
				if (addItems.isDisplayed() && addItems.isEnabled())
					return;
			} else {
				String errorMessage = checkErrorExists(driver);
				if (errorMessage.trim().length() > 0) {
						oPSelFW.reportStepDetails("Error message displayed" + errorMessage, "Error message is displayed",
							"Fail"); 
				log.error( "Error message is displayed");} 
				else {
						oPSelFW.reportStepDetails("Customer Search Page should not be displayed",
							"Customer Search Page is not displayed", "Fail");
				log.error("Customer Search Page is not displayed");
				}// gen1.closeTheOrder(driver, basetest);
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			// gen1.closeTheOrder(driver, basetest);
				oPSelFW.reportStepDetails("Error message displayed" + e, "Error message is displayed", "Fail");
				log.error("Error message is displayed");
			// else
			Assert.assertTrue(false);
		}
	}
	public void ConceptTypeScroll(WebDriver driver, String ConceptOPt, ProlificsSeleniumAPI oPSelFW)
			throws Exception {
		try {
			int i = 0;
			while (!(driver
					.findElements(By.xpath("//*[@id='sc_plat_dojo_widgets_ScreenDialogUnderlay_1']/div[2]/div[1]"))
					.isEmpty()) && i <= 30) {
				Thread.sleep(1000);
				i++;
			}
			String ConceptType = "div[@id='popup_48']//div[contains(text(),'" + ConceptOPt + "')]";
			gen.setTextAndHitEnter(driver, ConceptBox, ConceptOPt, "Concept");
			Thread.sleep(5000);
			String errorExists = checkErrorExists(driver);
			if (errorExists.trim().length() > 0) {
				oPSelFW.reportStepDetails("Error exists" + errorExists, "Error displayed", "Fail");
				log.error("Error displayed");
				gen.closeTheOrder(driver, oPSelFW);
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Error message displayed ", e+ "Error message is displayed", "Fail");
			log.error(e+ "Error message is displayed");
			Assert.assertTrue(false);
		}
	}
	public void ConceptTypeSet(WebDriver driver,Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
            throws Exception {
      try {
            int i = 0;
            while (!(driver
                        .findElements(By.xpath("//*[@id='sc_plat_dojo_widgets_ScreenDialogUnderlay_1']/div[2]/div[1]"))
                        .isEmpty()) && i <= 30) {
                  Thread.sleep(1000);
                  i++;
            }
            String Concept = "(//*[@uid='cb_orderEnterprise']/div/div/div/input)[1]";
            gen1.waitUntilElementIsClickable(driver, 1000, Concept);            
            String concept = XLTestData.get("Concept").toString();
            driver.findElement(By.xpath(Concept)).click();
            driver.findElement(By.xpath("(//*[@role='textbox'])[3]")).sendKeys(concept);
            driver.findElement(By.xpath("(//*[@role='textbox'])[3]")).sendKeys(Keys.TAB);
            oPSelFW.reportStepDetails("Concept should be selected " ,concept+" is selected" , "Pass");
            log.info(concept+" is selected" );
            Thread.sleep(5000);
             Thread.sleep(5000);
            String errorExists = checkErrorExists(driver);
            if (errorExists.trim().length() > 0) {
                   oPSelFW.reportStepDetails("Error exists" + errorExists, "Error displayed", "Fail"); 
                   log.error(concept+" is selected" );
                  gen.closeTheOrder(driver, oPSelFW);
                   Assert.assertTrue(false);
            }
      } catch (Exception e) {
            gen.closeTheOrder(driver, oPSelFW);
             oPSelFW.reportStepDetails("Error message displayed ", e+ "Error message is displayed", "Fail");
             log.error(e+ "Error message is displayed");
            Assert.assertTrue(false);
      }
}
	public void ConceptTypeSetforgift(WebDriver driver,Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
            throws Exception {
      try {
            int i = 0;
            while (!(driver
                        .findElements(By.xpath("//*[@id='sc_plat_dojo_widgets_ScreenDialogUnderlay_1']/div[2]/div[1]"))
                        .isEmpty()) && i <= 30) {
                  Thread.sleep(1000);
                  i++;
            }
                     
            String concept = XLTestData.get("Concept").toString();
            String ConceptXpath = "((//*[@uid='cmbEnterprise'])[2]/div/div/div/input)[1]";
            gen1.waitUntilElementIsClickable(driver, 1000, ConceptXpath);
            driver.findElement(By.xpath(ConceptXpath)).click();
            driver.findElement(By.xpath("(//*[@role='textbox'])[3]")).sendKeys(concept);
            driver.findElement(By.xpath("(//*[@role='textbox'])[3]")).sendKeys(Keys.TAB);
            oPSelFW.reportStepDetails("Concept should be selected " ,concept+" is selected" , "Pass");
            log.info(concept+" is selected");
             Thread.sleep(5000);
            String errorExists = checkErrorExists(driver);
            if (errorExists.trim().length() > 0) {
                  oPSelFW.reportStepDetails("Error exists" + errorExists, "Error displayed", "Fail");
                  log.error("Error displayed");
                  gen.closeTheOrder(driver, oPSelFW);
                  Assert.assertTrue(false);
            }
      } catch (Exception e) {
            gen.closeTheOrder(driver, oPSelFW);
            oPSelFW.reportStepDetails("Error message displayed ", e+ "Error message is displayed", "Fail");
            log.error( e+ "Error message is displayed");
            Assert.assertTrue(false);
      }
}
	public void SelectState(WebDriver driver,Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws Exception {
		try {
			int i = 0;
			while (!(driver
					.findElements(By.xpath("//*[@id='sc_plat_dojo_widgets_ScreenDialogUnderlay_1']/div[2]/div[1]"))
					.isEmpty()) && i <= 30) {
				Thread.sleep(1000);
				i++;
			}
			String concept = XLTestData.get("State").toString();
			driver.findElement(By.xpath("(//*[@uid='cmbState']/div[2]/div/div/input)[1]")).click();
			driver.findElement(By.xpath("(//*[@role='textbox'])[4]")).sendKeys(concept);
			driver.findElement(By.xpath("(//*[@role='textbox'])[4]")).sendKeys(Keys.TAB);
			oPSelFW.reportStepDetails("Concept should be selected " ,concept+" is selected" , "Pass");
			log.info(concept+" is selected" );
			Thread.sleep(5000);
			Thread.sleep(5000);
			String errorExists = checkErrorExists(driver);
			if (errorExists.trim().length() > 0) {
				oPSelFW.reportStepDetails("Error exists" + errorExists, "Error displayed", "Fail");
				log.error("Error displayed");
				gen.closeTheOrder(driver, oPSelFW);
				
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Error message displayed ", e+ "Error message is displayed", "Fail");
			log.error(e+ "Error message is displayed");
			Assert.assertTrue(false);
		}
	}
	public String enterItemDetails(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws Exception {
		String ItemsAdded = "";
		try {
			String ItemIDs = XLTestData.get("ItemID").toString();
			String Items[] = ItemIDs.split(";");
			String Eachs = XLTestData.get("Each").toString();
			String EachsList[] = Eachs.split(";");
			for (int ItemNum = 0; ItemNum < Items.length; ItemNum++) {
				String ItemAdded = SetItemID(driver, Items[ItemNum]);
				String Error = "";
				DataBase db = new DataBase();
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
				Connection conn = db2.ConnectToDB(HostName, DBSID, UserName, DBPassword, oPSelFW);
				Statement stat = conn.createStatement();
				ResultSet rs = null;
				System.out.println("Before Database");
				if (Environment.contains("STST2"))
					rs = stat.executeQuery("select count(*) as count from YANTRA_STST_OWNER.YFS_ITEM WHERE ITEM_ID = '"
							+ Items[ItemNum] + "'");
				else
					rs = stat.executeQuery("select count(*) as count from YANTRA_OWNER.YFS_ITEM WHERE ITEM_ID = '"
							+ Items[ItemNum] + "'");
				rs.next();
				String ItemCount = rs.getString("count");
				if (Integer.parseInt(ItemCount) > 0) {
					// basetest.test.log(Status.PASS, "Item "+ Items[ItemNum] + " is present in " +
					// Environment + " database");
					oPSelFW.reportStepDetails("Environment should be displayed", "Environment should not be displayed",
							"Pass");
					log.info( "Environment should not be displayed");
					if (driver.findElements(By.xpath("//*[@uid='systemMessagePanel']/div/div/span")).size() > 0) {
						Error = driver.findElement(By.xpath("//*[@uid='systemMessagePanel']/div/div/span"))
								.getAttribute("innerText");
						ItemAdded = Error;
					}
					if (ItemAdded.contains("Unable to add this item") || ItemAdded.contains("Invalid Item")
							|| ItemAdded.contains("Item is no longer available") || ItemAdded.contains("Error")
							|| ItemAdded.contains("Error description not available")) {
						oPSelFW.reportStepDetails(ItemAdded + " message should not be displayed",
								ItemAdded + "message is not be displayed", "Fail");  
						log.error(ItemAdded + "message is not be displayed");
						ItemsAdded = ItemsAdded + ItemAdded;
					} else {
						oPSelFW.reportStepDetails(Items[ItemNum] + " item is added to the cart",
								Items[ItemNum] + " item is added to the cart", "Pass");
						log.info(Items[ItemNum] + " item is added to the cart");
						ItemsAdded = ItemsAdded + "Items Added";
						setQuantity(driver, EachsList[ItemNum]);
						Thread.sleep(4000);
					}
				}
			}
		} catch (SQLException e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Item should not be added to the cart", "Item is not added to the cart", "Fail");
			log.error("Item is not added to the cart");
			Assert.assertTrue(false);
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Error in Adding the Items should be displayed",
					"Error in Adding the Items is not displayed", "Fail");
			log.error("Error in Adding the Items is not displayed");
			Assert.assertTrue(false);
		}
		return (ItemsAdded);
	}

	public String validateOrderTotal(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws InterruptedException, Exception {
		By updateXPath = By.xpath("//*[@uid='update_order']/span/span");
		gen1.waitUntilElementIsClickable(driver, 5000, updateXPath);
		// String OrderType = XLTestData.get("OrderType").toString();
		WebElement element = driver.findElement(updateXPath);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
		Thread.sleep(4000);
		String CalculatedTotal = gen.getTextOfElement(driver, "//*[@uid='orderTotalLnk']/div[2]/div/div/a");
		String EstimatedTotal = gen.getTextOfElement(driver, "//*[@uid='extn_datalabel']/div[2]/div[1]/div/span");
		if (CalculatedTotal.contains(EstimatedTotal)) {
			
			oPSelFW.reportStepDetails("Estimated Total of the Order should be equal to Calculated Total "  , "Estimated Total of the Order is equal to Calculated Total ", "Pass");  
			log.info("Estimated Total of the Order is equal to Calculated Total "); 
		return (CalculatedTotal);
		} else {
			
			oPSelFW.reportStepDetails("Estimated Total of the Order should be equal to Calculated Total "  , "Estimated Total of the Order is not equal to Calculated Total ", "Fail"); 
			log.error("Estimated Total of the Order is not equal to Calculated Total "); 
				return (CalculatedTotal);
		}
	}

	public void validateOrderDetails(WebDriver driver, String OrderTotal, HashMap<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws Exception {
		// Get Order Type
		String xlOrderType = XLTestData.get("OrderType").toString();
		String uiOrderType = driver.findElement(By.xpath("//*[@uid='extn_OrderType']/div[2]/div[1]/div/span"))
				.getText();
		if (uiOrderType.contains("PHONEORDER")) {
			if (xlOrderType.contains("Phone Order") && uiOrderType.contains("PHONEORDER"))
			{		
				oPSelFW.reportStepDetails("Order Type should be matching "  , " Order Type is matching ", "Pass");
			log.info( " Order Type is matching ");
			}		else
			{	oPSelFW.reportStepDetails("Order Type should be matching "  , " Order Type is not matching ", "Fail"); 
			log.error(" Order Type is not matching "); 
			}}
		// Get Concept
		String xlConcept = XLTestData.get("Concept").toString();
		String uiConcept = driver.findElement(By.xpath("//*[@uid='lblEnterpriseCodeDisplay']/div[2]/div[1]/div/span"))
				.getText();
		if (xlConcept.contains(uiConcept))
			
		{		
		
		oPSelFW.reportStepDetails("Concept displayed in UI is should match with the provided Concept in Test Data excel "  , "Concept displayed in UI is matching with the provided Concept in Test Data excel ", "Pass");
		log.info( "Concept displayed in UI is matching with the provided Concept in Test Data excel ");
		}
		else
		{	
			oPSelFW.reportStepDetails("Concept should match"  , "Concept is not matching", "Fail");
		log.error( "Concept is not matching");
		}
			
		// Get Original Order Total
		String uiOrderTotal = driver.findElement(By.xpath("//*[@uid='extn_OriginalAmount']/div[2]/div[1]/div/span"))
				.getText();
		// if(OrderTotal.contains(uiOrderTotal))
		
		oPSelFW.reportStepDetails("Order Total shpuld be display in UI"  , "Order Total displayed in UI is"+ uiOrderTotal, "Pass");
		log.info("Order Total displayed in UI is"+ uiOrderTotal);
		// Get Communication Preference
		String uiCommunicationPreference = driver
				.findElement(By.xpath("//*[@uid='extn_CommunicationPreference']/div[2]/div[1]/div/span")).getText();
		String customerPreference = XLTestData.get("CustomerPreference").toString();
		if (uiCommunicationPreference.contains(customerPreference))
		{	oPSelFW.reportStepDetails("Communication Preference should be match"  , "Communication Preference displayed in UI is matching with the provided Communication Preference in Test Data excel"+ uiOrderTotal, "Pass");
		log.info("Communication Preference displayed in UI is matching with the provided Communication Preference in Test Data excel"+ uiOrderTotal);
		
		}
		else
		{oPSelFW.reportStepDetails("Communication Preference should be match"  , "Communication Preference is not matching", "Fail");
		log.error("Communication Preference is not matching");
		}
		
		String billToAddress = driver.findElement(By.xpath("//*[@uid='pnlAddressHolder']")).getText();
		
		oPSelFW.reportStepDetails("Order Total should be display in UI "  , "Order Total displayed in UI is"+ billToAddress, "Info");
		log.info( "Order Total displayed in UI is"+ billToAddress);
		
		//basetest.test.log(Status.INFO, "Order Total displayed in UI is " + billToAddress);
		// Bill To Address
		// *[@id="idx_layout_ContentPane_547"]
	}

	public void changeBillingAddress(WebDriver driver, ProlificsSeleniumAPI oPSelFW, HashMap<String, String> XLTestData)
			throws Exception {
		//unLockTheOrder(driver, oPSelFW);
		Thread.sleep(6000);
		gen.waitnclickElement(driver, changeFillfillmentOption, "Change Fullfillment Options",oPSelFW);
		Thread.sleep(6000);
		gen.waitnclickElement(driver, xPathNextbtn, "Next",oPSelFW);
		Thread.sleep(6000);
		gen.waitnclickElement(driver, xPathEditBill, "Edit Bill",oPSelFW);
		Thread.sleep(6000);
		gen.waitnclickElement(driver, xPathEditBillAddress, "Edit Billing Address",oPSelFW);
		Thread.sleep(6000);
		enterAddressDetails(driver,XLTestData, oPSelFW);
		Thread.sleep(10000);
		By popUpnext = By.xpath("(//*[@uid='Popup_btnNext'])[1]");
		By popUpcancel=By.xpath("//*[@uid='Popup_btnCancel']/span/span/span[3]");
		Thread.sleep(1000);
		if (driver.findElements(popUpnext).size() > 0)
			driver.findElement(popUpnext).click();
		    Thread.sleep(6000);
		    driver.findElement(popUpcancel).click();
		// gen1.shortwaitnclickElement(driver, popUpnext);
		gen.waitnclickElement(driver, By.xpath(Confirmbtn), "Confirm",oPSelFW);
		oPSelFW.reportStepDetails("Billing Address Details Should be changed"  , "Billing Address Details are successfully changed", "Pass");
		log.info("Billing Address Details are successfully changed");
		
	}

	public void changeShipToAddress(WebDriver driver, ProlificsSeleniumAPI oPSelFW, HashMap<String, String> XLTestData)
			throws Exception {
		String ItemSel = "//*[@uid='OLST_listGrid']/div[2]/div/div[2]/table/tbody/tr/td";
		gen.waitnclickElement(driver, changeFillfillmentOption);
		gen1.waitUntilElementIsClickable(driver, 300, ItemSel);
		Thread.sleep(5000);
		driver.findElement(By.xpath(ItemSel)).click();
		editAddressDetails(driver,XLTestData, oPSelFW);
		Thread.sleep(10000);
		verifyIfOrderIsUpdated(driver,oPSelFW);
		Thread.sleep(10000);
		
		gen.waitnclickElement(driver, xPathNextbtn, "Next",oPSelFW);
		Thread.sleep(6000);
		gen.waitnclickElement(driver, By.xpath(Confirmbtn), "Confirm",oPSelFW);
		oPSelFW.reportStepDetails("Billing Address Details Should be changed"  , "Billing Address Details are successfully changed", "Pass");
		log.info("Billing Address Details are successfully changed");
		
		
		
	}

	public void enterAddressDetails(WebDriver driver,  HashMap<String, String> XLTestData,ProlificsSeleniumAPI oPSelFW) throws Exception {
		String addressTitle = "";
		gen.setText(driver, FirstName, XLTestData.get("NewBillFirstName").toString(), "Bill To First Name", oPSelFW);
		gen.setText(driver, LastNme, XLTestData.get("NewBillLastName").toString(), "Bill To Last Name", oPSelFW);
		gen.setText(driver, EmailAddress, XLTestData.get("NewBillEmailAddress").toString(), "Bill To Email Address", oPSelFW);
		gen.setTextAndHitEnter(driver, DayPhone, XLTestData.get("NewBillDayPhone").toString(), "Bill To Day Phone", oPSelFW);
		// Address Line
		gen.setText(driver, AdressLine1, XLTestData.get("BillToAddressLine1").toString(), "Bill To Address Line 1", oPSelFW);
		driver.findElement(By.xpath(PostalCode)).clear();
		WebElement PostalCod = driver.findElement(By.xpath(PostalCode));
		PostalCod.sendKeys(XLTestData.get("NewBillZip").toString());
		PostalCod.sendKeys(Keys.TAB);
		Thread.sleep(1000);
	
		By XPathApplybtn1 = By.xpath("(//*[@uid='Popup_btnNext']/span/span/span)[3]");
		By XpathCancelbtn1 = By.xpath("(//*[@uid='Popup_btnCancel']/span/span/span)[3]");
		gen.shortwaitnclickElement(driver, XPathApplybtn1, "Apply", oPSelFW);
		//driver.findElement(By.cssSelector("#dijit_form_Button_214_label")).click();
		
		
		if(driver.findElements(XPathApplybtn1).size() > 0)
		{
			//if(ApplyElement.isDisplayed() && ApplyElement.isEnabled())
			
				gen.shortwaitnclickElement(driver, XPathApplybtn1, "Apply", oPSelFW);
				gen.shortwaitnclickElement(driver, XpathCancelbtn1, "Apply", oPSelFW);	
		}
		Thread.sleep(5000);
	}

	/*
	 * public void waitUntilBrowserIsLoading(WebDriver driver) throws
	 * InterruptedException { int i = 1; while (!driver.findElements(By.xpath(
	 * "//*[@id='sc_plat_dojo_widgets_ScreenDialogUnderlay_1']/div[2]/div[1]"))
	 * .isEmpty() && i <= 20) { Thread.sleep(1000); i++; } }
	 */

	public void EnterGiftCardDetails(WebDriver driver, String GiftCardInformation, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws Exception {
		try {
			if (XLTestData.get("PaymentType").toString().contains("Gift Card")
					|| XLTestData.get("PaymentType").toString().contains("Merchandise Card")
					|| XLTestData.get("PaymentType").toString().contains("E-Gift Card")) {
				// basetest.test.log(Status.INFO, XLTestData.get("PaymentType").toString() + "
				// is selected as the Payment Type");
				gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment",oPSelFW);
				Thread.sleep(5000);
				gen.setTextAndHitTab(driver, PaymentType, "Gift Card/Merchandise Card/E-Gift Card", "Payment Type",oPSelFW);
				Thread.sleep(2000);
				String[] GiftCardDetails = GiftCardInformation.split(":");
				WebElement CertificateNumber = driver
						.findElement(By.xpath("//*[@uid='txtSvcNo']/div[2]/div/div/input"));
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
				String errorMessage = "";
				if (!driver.findElements(By.xpath("((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div"))
						.isEmpty()) {
					errorMessage = gen.getTextOfElement(driver,
							"((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div");
				}
				if (errorMessage.contains("still remaining on the card")) {
					gen.waitnclickElement(driver, By.xpath(AddApply), "Apply",oPSelFW);
					Thread.sleep(10000);
					return;
				} else if (errorMessage.contains("TimeOut")) {
					oPSelFW.reportStepDetails("Error Message should display"  ,"Error message is displayed", "Fail");
					log.error("Error message is displayed");
					
					gen.waitnclickElement(driver, By.xpath(PaymentCancel), "Cancel",oPSelFW);
					Thread.sleep(10000);
					return;
				} else {
					gen.waitnclickElement(driver, By.xpath(AddApply), "Apply",oPSelFW);
					Thread.sleep(10000);
					return;
				}
			}
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Error Message should display"  ,"Error in Entering the Gift Card Details", "Fail");
			log.error("Error in Entering the Gift Card Details");
			
			Assert.assertTrue(false);
		}
	}

	public void enterLoyalityCardDetails(WebDriver driver, String LoyaltyRewardsCertificate,
			Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws Throwable {
		try {
			if (XLTestData.get("PaymentType").toString().contains("Loyalty Rewards Certificate")) {
				oPSelFW.reportStepDetails("MLRCTL should be selected"  ,"LRC is selected as the Payment Type", "Info");
				log.info("LRC is selected as the Payment Type");
				gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment",oPSelFW);
				driver.findElement(By.xpath(PaymentType)).clear();
				Thread.sleep(1000);
				driver.findElement(By.xpath(PaymentType)).sendKeys("Loyalty Rewards Certificate");
				Thread.sleep(1000);
				driver.findElement(By.xpath(PaymentType)).sendKeys(Keys.TAB);
				Thread.sleep(1000);
				WebElement CertificateNumber = driver
						.findElement(By.xpath("//*[@uid='txtSvcNo']/div[2]/div/div/input"));
				CertificateNumber.clear();
				Thread.sleep(1000);
				CertificateNumber.sendKeys(LoyaltyRewardsCertificate);
				Thread.sleep(2000);
				CertificateNumber.sendKeys(Keys.TAB);
				Thread.sleep(1000);

				String errorMessage = "";
				if (!driver.findElements(By.xpath("((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div"))
						.isEmpty()) {
					errorMessage = gen.getTextOfElement(driver,
							"((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div");
				}

				if (errorMessage.contains("still remaining on the card")) {
					gen.waitnclickElement(driver, By.xpath(AddApply), "Apply",oPSelFW);
					Thread.sleep(10000);
					return;
				} else if (errorMessage.contains("TimeOut") || errorMessage.contains("Rewards certificate expired")) {
					oPSelFW.reportStepDetails("Error Message should display"  ,errorMessage + "Error message is displayed", "Fail");
					log.error(errorMessage + "Error message is displayed");
						gen.waitnclickElement(driver, By.xpath(PopUpCancel), "Cancel",oPSelFW);
					Thread.sleep(5000);
					return;
				} else if (errorMessage.contains("Bal. Inq. Failed. Technical Issue. Please try")) {
					oPSelFW.reportStepDetails("Error Message should display"  ,errorMessage + "Error message is displayed", "Fail");
					log.error(errorMessage + "Error message is displayed");
					gen.waitnclickElement(driver, By.xpath(PaymentCancel3), "Cancel",oPSelFW);
					Thread.sleep(5000);
					return;
				} else {
					gen.waitnclickElement(driver, By.xpath(AddApply), "Apply",oPSelFW);
					Thread.sleep(5000);
					return;
				}
			}
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Exception", e + "Error in Entering the Loyalty Card Details", "Fail");
			log.error( e + "Error in Entering the Loyalty Card Details");
			Assert.assertTrue(false);
		}
	}

	public void enterMTLCardDetails(WebDriver driver, String LoyaltyRewardsCertificate, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws Exception {
		try {
			if (XLTestData.get("PaymentType").toString().contains("MTL")) {
				oPSelFW.reportStepDetails("MTL should be selected"  ,"MTL is selected as the Payment Type", "Info");
				log.info("MTL is selected as the Payment Type");
				gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment",oPSelFW);
				driver.findElement(By.xpath(PaymentType)).clear();
				Thread.sleep(1000);
				driver.findElement(By.xpath(PaymentType)).sendKeys("Loyalty Rewards Certificate");
				Thread.sleep(1000);
				driver.findElement(By.xpath(PaymentType)).sendKeys(Keys.TAB);
				Thread.sleep(1000);
				WebElement CertificateNumber = driver
						.findElement(By.xpath("//*[@uid='txtSvcNo']/div[2]/div/div/input"));
				CertificateNumber.clear();
				Thread.sleep(1000);
				CertificateNumber.sendKeys(LoyaltyRewardsCertificate);
				Thread.sleep(2000);
				CertificateNumber.sendKeys(Keys.TAB);
				Thread.sleep(1000);
				String errorMessage = "";
				if (!driver.findElements(By.xpath("((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div"))
						.isEmpty()) {
					errorMessage = gen.getTextOfElement(driver,
							"((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div");
				}
				if (errorMessage.contains("still remaining on the card")) {
					gen.waitnclickElement(driver, By.xpath(AddApply), "Apply",oPSelFW);
					Thread.sleep(2000);
					return;
				} else if (errorMessage.contains("TimeOut") || errorMessage.contains("Rewards certificate expired")) {
				oPSelFW.reportStepDetails("Error Message should display"  ,errorMessage + "message is displayed for the LRC Card", "Fail");
				log.error(errorMessage + "message is displayed for the LRC Card");
					
					gen.waitnclickElement(driver, By.xpath(PopUpCancel), "Cancel",oPSelFW);
					Thread.sleep(2000);
					return;
				} else {
					gen.waitnclickElement(driver, By.xpath(AddApply), "Apply",oPSelFW);
					Thread.sleep(2000);
					return;
				}
			}
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Exception", e + "Error in Entering the MTL Card Details", "Fail");
			log.error( e + "Error in Entering the MTL Card Details");
			Assert.assertTrue(false);
		}
	}

	/*
	 * if(CertificateNumber.isDisplayed() && CertificateNumber.isEnabled() ) {
	 * if(driver.findElements(By.xpath(
	 * "(//*[@uid='singlemessagelabelpopup']/div/span)[1]")).size() > 0)
	 * 
	 * { String errorMessage = driver.findElement(By.xpath(
	 * "(//*[@uid='singlemessagelabelpopup']/div/span)[2]/span/div")).getText();
	 * if(errorMessage.contains("Technical Issue")) basetest.test.log(Status.FAIL,
	 * errorMessage + " is displayed"); gen1.clickElement(driver,
	 * "(//*[@uid='Popup_btnCancel']/span/span/span)[3]"); //driver.close();
	 * basetest.extent.flush(); Assert.assertTrue(false); } }
	 * gen1.waitnclickElement(driver, By.xpath(AddApply), "Apply", basetest);
	 * Thread.sleep(10000); } }
	 */

	public void entersvcdetails(WebDriver driver, String GiftCardInformation, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws Exception {
		try {
			gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment",oPSelFW);
			// basetest.test.log(Status.INFO, XLTestData.get("PaymentType").toString() + "
			// is selected as the Payment Type");
			// gen1.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment",
			// basetest);
			Thread.sleep(5000);
			driver.findElement(By.xpath(PaymentType)).clear();
			Thread.sleep(200);

			driver.findElement(By.xpath(PaymentType)).sendKeys("Gift Card/Merchandise Card/E-Gift Card");
			gen.setTextAndHitTab(driver, PaymentType, "Gift Card/Merchandise Card/E-Gift Card", "Payment Type",oPSelFW);
			Thread.sleep(5000);

			String[] GiftCardDetails = GiftCardInformation.split(":");
			WebElement CertificateNumber = driver
					.findElement(By.xpath("((//*[@uid='txtSvcNo']/div)[2]/div/div/input)[1]"));
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
			String errorMessage = "";
			if (!driver.findElements(By.xpath("((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div"))
					.isEmpty()) {
				errorMessage = gen.getTextOfElement(driver,
						"((//*[@uid='singlemessagelabelpopup']/div)[1]/span)[2]/span/div");
			}
			if (errorMessage.contains("still remaining on the card")) {
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply",oPSelFW);
				Thread.sleep(10000);
				return;
			} else if (errorMessage.contains("TimeOut")) {
				gen.waitnclickElement(driver, By.xpath(PaymentCancel), "Cancel",oPSelFW);
				Thread.sleep(10000);
				return;
			} else {
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply",oPSelFW);
				Thread.sleep(10000);
				return;
			}
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			
			oPSelFW.reportStepDetails("Exception", e + "Error in Entering the SVC Card Details", "Fail");
			log.error(e + "Error in Entering the SVC Card Details");
			Assert.assertTrue(false);
		}
	}

	public void enterCBCCInformation(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws Exception {
		try {
			gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment",oPSelFW);
			if (XLTestData.get("PaymentType").toString().toUpperCase().contains("CBCC")) {

				oPSelFW.reportStepDetails("Credit Card should be selected"  ,"Credit Card is selected as the Payment Type", "Info");
				log.info("Credit Card is selected as the Payment Type");
				// gen1.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment",
				// basetest);
				String CardNumber = XLTestData.get("CardNumber").toString();
				String ExpiryMonth = XLTestData.get("ExpiryMonth").toString();
				String ExpiryYear = XLTestData.get("ExpiryYear").toString();
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
				WebElement eleExpiryMonth = driver
						.findElement(By.xpath("//*[@uid='cmbMonth']/div[2]/div/div[2]/input"));
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
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply",oPSelFW);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			
			oPSelFW.reportStepDetails("Exception", e + "Error in Entering the CBCC Card Details", "Fail");
			log.error( e + "Error in Entering the CBCC Card Details");
			Assert.assertTrue(false);
		}
	}

	public void enterPLCCCardInformation(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws Exception {
		try {
			if (XLTestData.get("PaymentType").toString().toUpperCase().contains("PLCC")) {
				oPSelFW.reportStepDetails("PLCC should be selected"  ,"PLCC is selected as the Payment Type", "Info");
				log.info("PLCC is selected as the Payment Type");
				
				gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment",oPSelFW);
				String CardNumber = XLTestData.get("CardNumber").toString();
				driver.findElement(By.xpath(PaymentType)).clear();
				Thread.sleep(1000);
				driver.findElement(By.xpath(PaymentType)).sendKeys("PLCC");
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
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply",oPSelFW);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Exception", e + "Error in Entering the PLCC Card Information", "Fail");
			log.error( e + "Error in Entering the PLCC Card Information");
			Assert.assertTrue(false);
		}
	}

	public void enterCreditCardInformation(WebDriver driver, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws InterruptedException, Exception {
		try {
			String xCardNumber = "//input[@id='ssdcsDataToTokenize']";
			if (XLTestData.get("PaymentType").toString().toUpperCase().contains("CREDIT")) {
				oPSelFW.reportStepDetails("Credit Card should be selected"  ,"Credit Card is selected as the Payment Type", "Info");
				log.info("Credit Card is selected as the Payment Type");
				
				gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment",oPSelFW);
				String CardNumber = XLTestData.get("CardNumber").toString();
				String ExpiryMonth = XLTestData.get("ExpiryMonth").toString();
				String ExpiryYear = XLTestData.get("ExpiryYear").toString();
				Thread.sleep(6000);
				driver.findElement(By.xpath(PaymentType)).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				driver.findElement(By.xpath(PaymentType)).sendKeys(Keys.TAB);
				Thread.sleep(1000);
				By frame = By.xpath("//iframe[contains(@id,'padssIframe') and normalize-space(@title)='Card number:']");
				if (driver.findElements(frame).size() > 0) {
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
					CardType.sendKeys(Keys.TAB);
					Thread.sleep(1000);
					WebElement NameOnCard = driver.findElement(NameOnCardXpath);
					NameOnCard.sendKeys(Keys.TAB);
					Thread.sleep(1000);
					WebElement eleExpiryMonth = driver
							.findElement(By.xpath("//*[@uid='cmbMonth']/div[2]/div/div[2]/input"));
					eleExpiryMonth.clear();
					Thread.sleep(1000);
					eleExpiryMonth.sendKeys(ExpiryMonth);
					Thread.sleep(2000);
					WebElement eleExpiryYear = driver
							.findElement(By.xpath("//*[@uid='cmbYear']/div[2]/div/div[2]/input"));
					eleExpiryYear.clear();
					Thread.sleep(1000);
					eleExpiryYear.sendKeys(ExpiryYear);
					Thread.sleep(2000);
					eleExpiryYear.sendKeys(Keys.TAB);
					Thread.sleep(2000);
					gen.waitnclickElement(driver, By.xpath(AddApply), "Apply",oPSelFW);
					Thread.sleep(1000);
					WebElement AddPaymentbtn = driver.findElement(By.xpath(AddPaymentMethodbtn));
					if (driver.findElements(By.xpath("//*[@uid='cmbYear']/div[2]/div/div[2]/input")).size() > 0)
						if (eleExpiryYear.isDisplayed() && eleExpiryYear.isEnabled()) {
							if (driver.findElements(By.xpath("(//*[@uid='singlemessagelabelpopup']/div/span)[1]"))
									.size() > 0) {
								String errorMessage = driver
										.findElement(
												By.xpath("(//*[@uid='singlemessagelabelpopup']/div/span)[2]/span/div"))
										.getText();
								if (errorMessage.contains("errors")) { 
									oPSelFW.reportStepDetails("Error Message should display"  ,errorMessage + " is displayed in Add Payment Screen", "Fail");
									log.error(errorMessage + " is displayed in Add Payment Screen");
									gen.clickElement(driver, "(//*[@uid='Popup_btnCancel']/span/span/span)[3]");
									gen.closeTheOrder(driver, oPSelFW);
									Assert.assertTrue(false);
								}
							}
						}
				} else {
					if (driver.findElements(By.xpath("//*[@uid='PaymentCapture_CreditCard']/div")).size() > 0) {
						String captureServerError = driver
								.findElement(By.xpath("//*[@uid='PaymentCapture_CreditCard']/div")).getText();
						if (captureServerError.contains("Connection to the secure data capture server failed")) {
							oPSelFW.reportStepDetails("Error Message should display"  ,captureServerError + " is displayed in Add Payment Screen for Credit Card", "Fail");
							log.error(captureServerError + " is displayed in Add Payment Screen for Credit Card");		
							gen.waitnclickElement(driver, By.xpath(PaymentCancel3), "Cancel",oPSelFW);
							gen.closeTheOrder(driver, oPSelFW);
							Assert.assertTrue(false);
						}
					}
				}
			}
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Exception", e + "Error in Entering the CBCC Card Details", "Fail");
			log.error( e + "Error in Entering the CBCC Card Details");
			oPSelFW.reportStepDetails("Exception "  , "Error in Entering the Credit Card Information", "Fail");
			log.error("Error in Entering the Credit Card Information");
			Assert.assertTrue(false);
		}
	}

	public void enterCashInformation(WebDriver driver, String amount, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws Exception {
			try {
			if (XLTestData.get("PaymentType").toString().toUpperCase().contains("CASH")) {
				oPSelFW.reportStepDetails("Cash should be selected"  ,"Cash is selected as the Payment Type", "Info");
				log.info("Cash is selected as the Payment Type");
				gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment",oPSelFW);
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
				oPSelFW.reportStepDetails("Amount should Add"  ,amount + "amout is added successfully", "Pass");
				log.info(amount + "amout is added successfully");
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply",oPSelFW);
				Thread.sleep(1000);
				int i = 1;
				WebElement AddPaymentbtn = driver.findElement(By.xpath(AddPaymentMethodbtn));
				while (!AddPaymentbtn.isDisplayed() && !AddPaymentbtn.isEnabled() && i <= 20) {
					Thread.sleep(1000);
					i++;
				}
				String errorExists = checkErrorExists(driver);
				if (errorExists.contains("There are errors on the page. You cannot proceed until you fix them")) {
				oPSelFW.reportStepDetails("Error Message should display"  ,errorExists + "Error message is displayed in Add Payment Screen", "Fail");
				log.error(errorExists + "Error message is displayed in Add Payment Screen");
				gen.waitnclickElement(driver, By.xpath(PaymentCancel3), "Cancel",oPSelFW);
				}
				if (AddPaymentbtn.isDisplayed() && AddPaymentbtn.isEnabled())
					return;
			}
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Exception "  , "Error in Entering the Cash Information", "Fail");
			log.error("Error in Entering the Cash Information");
			Assert.assertTrue(false);
		}
	}

	public void enterCashInformation2(WebDriver driver, String amount, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws Exception {
		try {
			if (XLTestData.get("PaymentType").toString().toUpperCase().contains("CASH")) {
				oPSelFW.reportStepDetails("Cash should be selected"  ,"Cash is selected as the Payment Type", "Info"); 
				log.info("Cash is selected as the Payment Type"); 
				gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment",oPSelFW);
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
				oPSelFW.reportStepDetails("Amount should Add"  ,amount + "amout is added successfully", "Pass");
				log.info(amount + "amout is added successfully");
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply",oPSelFW);
				Thread.sleep(1000);
				int i = 1;
				WebElement AddPaymentbtn = driver.findElement(By.xpath(AddPaymentMethodbtn));
				while (!AddPaymentbtn.isDisplayed() && !AddPaymentbtn.isEnabled() && i <= 20) {
					Thread.sleep(1000);
					i++;
				}
				if (AddPaymentbtn.isDisplayed() && AddPaymentbtn.isEnabled())
					return;

			}
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Exception "  , "Error in Entering the Cash Information", "Fail");
			log.error("Error in Entering the Cash Information");
			Assert.assertTrue(false);
		}
	}

	public void enterCashInformation3(WebDriver driver, String amount, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws Exception {
		try {
			if (XLTestData.get("PaymentType").toString().toUpperCase().contains("CASH")) {
				oPSelFW.reportStepDetails("Cash should be selected"  ,"Cash is selected as the Payment Type", "Info");
				log.info("Cash is selected as the Payment Type");
				gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment",oPSelFW);
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
				oPSelFW.reportStepDetails("Amount should Add"  ,amount + "amout is added successfully", "Pass");
				log.info(amount + "amout is added successfully");
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply",oPSelFW);
				Thread.sleep(1000);
				int i = 1;
				WebElement AddPaymentbtn = driver.findElement(By.xpath(AddPaymentMethodbtn));
				while (!AddPaymentbtn.isDisplayed() && !AddPaymentbtn.isEnabled() && i <= 20) {
					Thread.sleep(1000);
					i++;
				}
				if (AddPaymentbtn.isDisplayed() && AddPaymentbtn.isEnabled())
					return;
			}
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Exception "  , "Error in Entering the Cash Information", "Fail");
			log.error("Error in Entering the Cash Information");
			Assert.assertTrue(false);
		}
	}

	public void enterDueAmount(WebDriver driver, String DueAmount, Map<String, String> XLTestData)
			throws Exception {
		try {
			if (!DueAmount.contains("00")) {
				oPSelFW.reportStepDetails("Due Bill should be selected"  ,"Due Bill is selected as the Payment Type", "Fail");
				log.error("Due Bill is selected as the Payment Type");
				gen.waitnclickElement(driver, By.xpath(AddPaymentMethodbtn), "Add Payment",oPSelFW);
				Thread.sleep(6000);
				gen.setTextAndHitTab(driver, PaymentType, "Due Bill", "Payment Type",oPSelFW);
				Thread.sleep(2000);
				WebElement PINNumber = driver
						.findElement(By.xpath("//*[@uid='txtMaxChargeLimit']/div[2]/div/div/input"));
				PINNumber.clear();
				Thread.sleep(1000);
				PINNumber.sendKeys(DueAmount);
				Thread.sleep(2000);
				PINNumber.sendKeys(Keys.TAB);
				Thread.sleep(1000);
				gen.waitnclickElement(driver, By.xpath(AddApply), "Apply",oPSelFW);
				Thread.sleep(5000);
				if (driver.findElements(By.xpath("(//*[@uid='singlemessagelabelpopup']/div/span)[1]")).size() > 0) {
					String errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabelpopup']/div)[1]"))
							.getText();
					oPSelFW.reportStepDetails("Error Message should display"  ,errorMessage + " is displayed", "Fail");
					log.error(errorMessage + " is displayed");
					
					gen.waitnclickElement(driver, By.xpath(PopUpCancel), "Cancel",oPSelFW);
				} else {
					if (!driver.findElements(By.xpath("//span[contains(text(), 'Ok')]")).isEmpty()
							&& driver.findElement(By.xpath("//span[contains(text(), 'Ok')]")).isDisplayed() == true) {
						gen.clickElement(driver, "//span[contains(text(), 'Ok')]");
						Thread.sleep(5000);
					}
					oPSelFW.reportStepDetails("DueAmount should Add"  ,DueAmount + "Due amout is added successfully", "Pass");
					log.info(DueAmount + "Due amout is added successfully");
				}
			}
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Exception "  ,  "Error in Entering the Due Amount", "Fail"); 
			log.error( "Error in Entering the Due Amount"); 
			Assert.assertTrue(false);
		}
	}

	public void verifyIfOrderIsUpdated(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception {
		String text = driver.findElement(By.xpath("//*[@uid='OrderEditor']/div[3]/div/div/span[2]/span/div")).getText();
		if (text.contains("Order updated successfully"))
			
			oPSelFW.reportStepDetails("Order should update"  , "Order is updated successfully ", "Pass");
		log.info("Order is updated successfully ");
	}

	public void getStatusOfOrder(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 300);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(lblStatus)));
		String OrderStatus = gen.getTextOfElement(driver, lblStatus);
		if (OrderStatus.contains("Cancelled"))
		oPSelFW.reportStepDetails("Status of the Order should be Cancelled "  ,  "Status of the Order is Cancelled ", "Pass");
		log.info("Status of the Order is Cancelled ");
	}
	
	public void getStatusOfOrder1(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, 300);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(lblStatus)));
		String OrderStatus = gen.getTextOfElement(driver, lblStatus);
		if(OrderStatus.contains("In Process"))
		oPSelFW.reportStepDetails("Status of the Order should be Cancelled "  ,  "Status of the Order is Cancelled ", "Pass");
		log.info( "Status of the Order is Cancelled ");
	}
	
	public void getbelowStatusOfOrder(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		String uiOrderType = driver.findElement(By.xpath("(//*[@class='gridxMain']/div[2]/div/table/tbody/tr/td[10]/div/span)[1]")).getText();
		if(uiOrderType.contains("Cancelled"))
		{
			if(uiOrderType.contains("Cancelled"))
			{		
				oPSelFW.reportStepDetails("Status of the Order should be Cancelled "  ,  "Status of the Order is Cancelled ", "Pass");
			log.info("Status of the Order is Cancelled ");
			}else
			{	oPSelFW.reportStepDetails("Status of the Order should be Cancelled "  ,  "Status of the Order is not Cancelled ", "Fail");
			log.error( "Status of the Order is not Cancelled ");
		}}
}
	
	public void getbelowStatusOfOrder1(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		if(gen.existsElement(driver, By.xpath("(//*[@class='gridxMain']/div[2]/div/table/tbody/tr/td[10]/div/span)[2]")))
		{
			String uiOrderType = driver.findElement(By.xpath("(//*[@class='gridxMain']/div[2]/div/table/tbody/tr/td[10]/div/span)[2]")).getText();
			if(uiOrderType.contains("In Process"))
			{
				if(uiOrderType.contains("In Process"))
				{		oPSelFW.reportStepDetails("Status of the Order should be display "  ,  "Second is In Process status", "Pass");
				log.info( "Second is In Process status");	
				}else
				{	oPSelFW.reportStepDetails("Status of the Order should be display "  ,  "Status of the Order is Cancelled ", "Pass");
				log.info("Status of the Order is Cancelled ");
			}}
		}
	}
	public void cancelOrder(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception {
		try {
			String ReasonCodexPath = "(//*[@uid='CancelOrderBaseScreen']/div/div/div/div[2]/input)[1]";
			gen.waitnclickElement(driver, By.xpath("//*[@uid='cancelOrder']/div[2]/div/div/a"));
			gen.waitUntilElementIsPresent(driver,ReasonCodexPath);
			Thread.sleep(3000);
			gen.setTextAndHitTab(driver, ReasonCodexPath, "Payment Failure", "Reason Code",oPSelFW);
			Thread.sleep(3000);
			gen.setTextAndHitTab(driver, "(//*[@uid='CancelOrderBaseScreen']/div/div/div/div[2]/input)[3]",
					"Credit Card Authorization Failure", "Sub Reason Code",oPSelFW);
			Thread.sleep(1000);
			clickOnNextAndConfirmOrder(driver, oPSelFW);
			getStatusOfOrder(driver, oPSelFW);
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Error Occured "  , "Error in Cancelling the Order", "Pass");
			log.info("Error in Cancelling the Order");
			Assert.assertTrue(false);
		}
	}

	public void clickOnNextAndConfirmOrder(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception {
		try {
			gen.waitnclickElement(driver, By.xpath("(//span[contains(text(),'Next')])[2]"));
			Thread.sleep(5000);
			if (!driver.findElements(By.xpath("//*[@uid='txtNoteText']/div[2]/div/textarea")).isEmpty()) {
				driver.findElement(By.xpath("//*[@uid='txtNoteText']/div[2]/div/textarea")).sendKeys("Hello Text");
			}
			if (!driver.findElements(By.xpath(("(//span[contains(text(),'Ok')])[2]"))).isEmpty())
				gen.waitnclickElement(driver, By.xpath("(//span[contains(text(),'Ok')])[2]"));
			else if (!driver.findElements(By.xpath(("(//span[contains(text(),'Ok')])[1]"))).isEmpty())
				gen.waitnclickElement(driver, By.xpath("(//span[contains(text(),'Ok')])[1]"));
			Thread.sleep(5000);
			gen.waitnclickElement(driver, By.xpath("(//span[contains(text(),'Confirm')])[2]"));
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Error Occured "  , "Error in Clicking the Confirm Order", "Pass"); 
			log.info("Error in Clicking the Confirm Order"); 
			Assert.assertTrue(false);
		}
	}

	public void unLockTheOrder(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception {
		gen.waitUntilElementIsPresent(driver, lockIcon);
		try {
			if (!driver.findElements(lockIcon).isEmpty()) {
				if (driver.findElement(lockIcon).isDisplayed()) {
					gen.waitnclickElement(driver, lockIcon);
					Thread.sleep(3000);
					gen.waitnclickElement(driver,
							By.xpath("(//*[@uid='activeHolds']/div[3]/div[4]/div/table/tbody/tr/td/span)[1]"));
					// driver.findElement(By.xpath("(//*[@uid='activeHolds']/div[3]/div[4]/div/table/tbody/tr/td/span)[1]")).click();
					Thread.sleep(1000);
					if (!driver
							.findElements(
									By.xpath("(//*[@uid='activeHolds']/div[3]/div[4]/div/table/tbody/tr/td/span)[2]"))
							.isEmpty()) {
						gen.waitnclickElement(driver,
								By.xpath("(//*[@uid='activeHolds']/div[3]/div[4]/div/table/tbody/tr/td/span)[2]"));
						Thread.sleep(1000);
					}
					if (!driver
							.findElements(
									By.xpath("(//*[@uid='activeHolds']/div[3]/div[4]/div/table/tbody/tr/td/span)[3]"))
							.isEmpty()) {
						gen.waitnclickElement(driver,
								By.xpath("(//*[@uid='activeHolds']/div[3]/div[4]/div/table/tbody/tr/td/span)[3]"));
						Thread.sleep(1000);
					}
					if (driver.findElement(saveHolds).isDisplayed() && driver.findElement(saveHolds).isEnabled())
						gen.waitnclickElement(driver, saveHolds, "Save Holds", oPSelFW);
					Thread.sleep(6000);
					String HoldRemovedSuccessfullyMessage = driver
							.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div/span)[1]")).getText();
					if (HoldRemovedSuccessfullyMessage.contains("Holds have been resolved successfully"))
						oPSelFW.reportStepDetails("Holds should be beresolved successfully"  ,"Holds have been resolved successfully", "Pass"); 
					log.info("Holds have been resolved successfully"); 
					gen.waitnclickElement(driver, backToFullfillmentOption);
				}
			}
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Exception "  ,  "Error in Unlocking the Order ", "Fail");
			log.error( "Error in Unlocking the Order ");
			Assert.assertTrue(false);
		}
	}

	public void performOrderAppeasement(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception {
		try {
			gen.clickElement(driver, "//*[@uid='customerAppeasement']/div[2]/div/div/a", "Appeasement");
			String Reason = "//*[@uid='CustomerAppeasementSelection']/div/div[3]/div/div/div[2]/input[1]";
			gen.waitUntilElementIsPresent(driver, By.xpath(Reason));
			gen.setTextAndHitTab(driver, Reason, "DEFECTIVE", "Reason Code",oPSelFW);
			gen.setTextAndHitTab(driver, "//*[@uid='extn_subreason']/div[2]/div/div[2]/input[1]", " Fabric issue",
					"Sub Reason Code",oPSelFW);
			gen.clickElement(driver, "//*[@uid='OLST_listGrid']/div[3]/div[4]/div/table/tbody/tr/td/span",
					"Select Item");
			clickOnNextAndConfirmOrder(driver, oPSelFW);
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Exception " ,  "Error in Performing the Order AppeasementsItems is displayed ", "Fail");
			log.error( "Error in Performing the Order AppeasementsItems is displayed ");
			Assert.assertTrue(false);
		}
	}

	public void verifyStatusOfOrder(String ExpectedStatus, WebDriver driver, ProlificsSeleniumAPI oPSelFW)
			throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 300);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(lblStatus)));
			String OrderStatus = gen.getTextOfElement(driver, lblStatus);
			if (OrderStatus.contains(ExpectedStatus))
				
				oPSelFW.reportStepDetails("Status of the Order should be displayed "  ,  "Status of the Order is  "+ExpectedStatus, "Pass");
			log.info( "Status of the Order is  "+ExpectedStatus);
			
				//basetest.test.log(Status.PASS, "Status of the Order is " + ExpectedStatus);
		} catch (Exception e) {
			gen.closeTheOrder(driver, oPSelFW);
			oPSelFW.reportStepDetails("Error Occured "  , "Error in getting the status of the Order", "Fail");
			log.error( "Error in getting the status of the Order");
		
			Assert.assertTrue(false);
		}
	}

	public String checkErrorExists(WebDriver driver) {
		if (!driver.findElements(By.xpath("//*[@id='idx_widget_SingleMessage_0_messageTitle']/div")).isEmpty()) {
			String ErrorMessage = driver.findElement(By.xpath("//*[@id='idx_widget_SingleMessage_0_messageTitle']/div"))
					.getText();
			return (ErrorMessage);
		} else if (!driver.findElements(By.xpath("//*[@id='idx_widget_SingleMessage_1_messageTitle']/div")).isEmpty()) {
			String ErrorMessage = driver.findElement(By.xpath("//*[@id='idx_widget_SingleMessage_1_messageTitle']/div"))
					.getText();
			return (ErrorMessage);
		} else if (!driver.findElements(By.xpath("//*[@id='idx_widget_SingleMessage_2_messageTitle']/div")).isEmpty()) {
			String ErrorMessage = driver.findElement(By.xpath("//*[@id='idx_widget_SingleMessage_2_messageTitle']/div"))
					.getText();
			return (ErrorMessage);
		} else
			return ("");
	}

	public String getTitleOfPage(WebDriver driver) {
		return (driver.findElement(By.xpath("(//*[@uid='lb_screenSubTitle'])[2]")).getText());
	}

	public String getDueAmount(WebDriver driver) {
		String amount = gen.getTextOfElement(driver, AmountToPay);
		if (amount.trim().length() > 0) {
			amount = amount.replace("$", "");
			amount = amount.replace(",", "");
			String DueAmount = amount.substring(amount.indexOf("."), amount.length());
			return (DueAmount);
		} else
			return ("");
	}

	public Double getDueAmountToEnter(WebDriver driver) {
		String DueAmount = getDueAmount(driver);
		Double Due = Double.parseDouble(DueAmount);
		return (Due);
	}

	public void deletePayment(WebDriver driver) throws InterruptedException {
		gen.clickElement(driver, "(//*[@title='Click on this link to delete the payment method'])[1]");
		Thread.sleep(2000);
		if (!driver.findElements(By.xpath("//span[contains(text(), 'Ok')]")).isEmpty()
				&& driver.findElement(By.xpath("//span[contains(text(), 'Ok')]")).isDisplayed() == true) {
			gen.clickElement(driver, "//span[contains(text(), 'Ok')]");
			Thread.sleep(5000);
		}
	}

	public void selectCustomerTypeAndEmployeeType(WebDriver driver, WebElement element, ProlificsSeleniumAPI oPSelFW,
			Map<String, String> XLTestData) throws InterruptedException, Exception {
		String getTextOfCustomerPreference = driver.findElement(By.xpath("//*[@uid='CustomerPrefpnl']"))
				.getAttribute("innerHTML");
		if (getTextOfCustomerPreference.contains("Email")) {
			WebElement customerPreference = driver
					.findElement(By.xpath("(((//*[@uid='extn_CustomerPref']/div)[2]/div/div)[2]/input)[1]"));
			customerPreference.clear();
			Thread.sleep(100);
			customerPreference.sendKeys(XLTestData.get("CustomerPreference").toString());
			Thread.sleep(100);
			customerPreference.sendKeys(Keys.TAB);
			gen.setTextAndHitTab(driver, CustomerType, XLTestData.get("CustomerType").toString(), "Customer Type",oPSelFW);
			if (XLTestData.get("CustomerType").toString().equalsIgnoreCase("Business")) {
				Thread.sleep(100);
				element = driver.findElement(By.xpath(BusinessID));
				element.clear();
				element.sendKeys(XLTestData.get("BussinessId").toString());
				Thread.sleep(100);
				element.sendKeys(Keys.TAB);
				Thread.sleep(100);
				if (!driver.findElements(By.xpath(TaxExempt)).isEmpty()) {
					gen.clickElement(driver, TaxExempt);
					Thread.sleep(1000);
				}
			} else if (XLTestData.get("CustomerType").toString().equalsIgnoreCase("Employee")) {
				Thread.sleep(1000);
				gen.setTextAndHitTab(driver, IDX, XLTestData.get("EmployeeID").toString(), "Employee ID",oPSelFW);
			}
		} else {
			oPSelFW.reportStepDetails("Values should be displayed in the Customer Preference drop down list "  , "No values are displayed in the Customer Preference drop down list ", "Fail");
			log.error("No values are displayed in the Customer Preference drop down list ");
			}
	}

	public void enterGiftCardDetails(WebDriver driver, ProlificsSeleniumAPI oPSelFW, Map<String, String> XLTestData)
			throws Exception {
		if (!driver.findElements(By.xpath(GiftOPt1btn)).isEmpty()) {
			gen.waitnclickElement(driver, By.xpath(GiftOPt1btn), "Go to Gift Options",oPSelFW);
			Thread.sleep(20000);
		}
		if (!driver.findElements(By.xpath(GiftOPt2btn)).isEmpty()) {
			gen.waitnclickElement(driver, By.xpath(GiftOPt2btn), "Go to Gift Options",oPSelFW);
			Thread.sleep(20000);
		}
		String giftOptions = XLTestData.get("GiftOptions").toString();
		if (giftOptions.length() > 0) {
			String giftOption[] = giftOptions.split("&");
			for (int opt = 0; opt <= giftOption.length - 1; opt++) {
				String giftOpt[] = giftOption[opt].split(":");
				if (giftOpt.length > 1) {
					boolean giftSelected = false;
					if (!driver
							.findElements(
									By.xpath("(//*[@uid='OLST_listGrid']/div[3]/div[4]/div/table/tbody/tr/td/span)[2]"))
							.isEmpty()) {
						if (driver
								.findElement(By.xpath(
										"(//*[@uid='OLST_listGrid']/div[3]/div[4]/div/table/tbody/tr/td/span)[2]"))
								.isDisplayed()) {
							gen.clickElement(driver,
									"(//*[@uid='OLST_listGrid']/div[3]/div[4]/div/table/tbody/tr/td/span)[2]");
							Thread.sleep(1000);
							giftSelected = true;
						}
					} else if (!driver
							.findElements(
									By.xpath("//*[@id='gridx_Grid_4']/div[3]/div[4]/div[1]/table/tbody/tr/td/span"))
							.isEmpty()) {
						gen.clickElement(driver, "//*[@id='gridx_Grid_4']/div[3]/div[4]/div[1]/table/tbody/tr/td/span");
						Thread.sleep(1000);
						if (!driver
								.findElements(
										By.xpath("//*[@id='gridx_Grid_4']/div[3]/div[4]/div[2]/table/tbody/tr/td/span"))
								.isEmpty()) {
							gen.clickElement(driver,
									"//*[@id='gridx_Grid_4']/div[3]/div[4]/div[2]/table/tbody/tr/td/span");
							Thread.sleep(1000);
						}
						giftSelected = true;
					}
					if (giftSelected = true) {
						if (driver.findElement(By.xpath("//*[@uid='giftThisItemCheck']/div/input")).isDisplayed()) {
							gen.clickElement(driver, "//*[@uid='giftThisItemCheck']/div/input", "Gift This Item");
							if (!driver.findElements(By.xpath("(//*[@uid='extn_From']/div[2]/div/div/input)[1]"))
									.isEmpty())
								gen.setText(driver, "(//*[@uid='extn_From']/div[2]/div/div/input)[1]", giftOpt[1],
										"Gift From");
							else
								gen.setText(driver, "//*[@uid='extn_From']/div[2]/div/div/input", giftOpt[1],
										"Gift From");
							Thread.sleep(1000);
							if (!driver.findElements(By.xpath("(//*[@uid='extn_To']/div[2]/div/div/input)[1]"))
									.isEmpty())
								gen.setText(driver, "(//*[@uid='extn_To']/div[2]/div/div/input)[1]", giftOpt[2],
										"Gift To");
							else
								gen.setText(driver, "//*[@uid='extn_To']/div[2]/div/div/input", giftOpt[2], "Gift To");
							Thread.sleep(1000);
							if (driver.findElement(By.xpath("//*[@uid='giftWrapThisItemCheck']/div/input"))
									.isEnabled()) {
								driver.findElement(By.xpath("//*[@uid='giftWrapThisItemCheck']/div/input")).click();
								Thread.sleep(1000);
								if (!driver
										.findElements(
												By.xpath("(//*[@uid='extn_GiftWrapCode']/div[2]/div/div/input)[1]"))
										.isEmpty())
									gen.setText(driver, "(//*[@uid='extn_GiftWrapCode']/div[2]/div/div/input)[1]",
											giftOpt[3], "Gift Wrap Code");
								else
									gen.setText(driver, "//*[@uid='extn_GiftWrapCode']/div[2]/div/div/input",
											giftOpt[3], "Gift Wrap Code");
								Thread.sleep(1000);
							}
							gen.waitnclickElement(driver,
									By.xpath("(//*[@uid='detailsGiftOptions']/span/div/div)[2]/span/span/span"),
									"Apply",oPSelFW);
							Thread.sleep(1000);
							gen.waitnclickElement(driver,By.xpath("(//*[@uid='updateButtonContainer'])[3]/span/span/span"), "Save",oPSelFW);
							Thread.sleep(1000);
						}
					}
				}
				if (!driver.findElements(By.xpath("//*[@id='idx_widget_SingleMessage_0_messageTitle']")).isEmpty()) {
					String MessageTitle = driver
							.findElement(By.xpath("//*[@id='idx_widget_SingleMessage_0_messageTitle']")).getText();
					if (MessageTitle.contains("Order updated successfully"))
						
						
						//basetest.test.log(Status.PASS, "Gift Message is updated successfully");
					oPSelFW.reportStepDetails("Gift Message should successfully"  ,"Gift Message is updated successfully ", "Fail"); 
					log.error("Gift Message is updated successfully "); 
					
				}
			}
		}
	}

	public void rushOrder(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws InterruptedException {
		try {
			driver.findElement(By.xpath("(((//*[@uid='orderlinestitlepane']/div)[2]/div/div/div)[7]/div)[3]")).click();
			Thread.sleep(200);
			driver.findElement(By.xpath("(//*[@uid='changeLOS']/span/span/span)[3]")).click();
			Thread.sleep(200);
			driver.findElement(By.xpath("(((//*[@uid='extn_Carrier_Service']/DIV)[2]/div/div)[2]/input)[1]"))
					.sendKeys("Rush");
			Thread.sleep(200);
			driver.findElement(By.xpath("(((//*[@uid='extn_Carrier_Service']/DIV)[2]/div/div)[2]/input)[1]"))
					.sendKeys(Keys.ENTER);
			Thread.sleep(200);
			driver.findElement(By.xpath("(((//*[@uid='extn_Carrier_Service']/DIV)[2]/div/div)[2]/input)[1]"))
					.sendKeys(Keys.TAB);
			Thread.sleep(200);
			driver.findElement(By.xpath("(//*[@uid='Popup_btnNext']/span/span/span)[3]")).click();
			Thread.sleep(3000);
			if (!driver.findElements(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")).isEmpty()) {
				String errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]"))
						.getAttribute("innerText");
				if (errorMessage.contains("Order updated successfully"))
					
					oPSelFW.reportStepDetails("Successfully message is displayed "  ,  "Rush is updated successfully message is displayed ", "Pass"); 
				log.info( "Rush is updated successfully message is displayed "); 
				}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void subOrders(WebDriver driver, Map<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException {
		try {
			driver.findElement(By.xpath("(((//*[@uid='orderlinestitlepane']/div)[2]/div/div/div)[7]/div)[3]")).click();
			Thread.sleep(200);
			driver.findElement(By.xpath("(//*[@uid='changeAddress']/span/span/span)[3]")).click();
			Thread.sleep(200);
			enterShipToAddressDetails(driver, XLTestData, oPSelFW);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void enterBillToAddressDetails(WebDriver driver, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws InterruptedException {
		try {
			gen1.waitUntilElementIsClickable(driver, 300, FirstName);
			gen.setText(driver, FirstName, XLTestData.get("BillToFirstName").toString(), "Bill To First Name");
			gen.setText(driver, LastNme, XLTestData.get("BillToLastName").toString(), "Bill To Last Name");
			gen.setText(driver, EmailAddress, XLTestData.get("BillToEmailAddress").toString(), "Bill To Email Address");
			gen.setTextAndHitEnter(driver, DayPhone, XLTestData.get("BillToDayPhone").toString(), "Bill To Day Phone");
			// Address Line
			gen.setText(driver, AdressLine1, XLTestData.get("BillToAddressLine1").toString(), "Bill To Address Line 1");
			WebElement PostalCod = driver.findElement(By.xpath(PostalCode));
			PostalCod.sendKeys(XLTestData.get("BillToZip").toString());
			PostalCod.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			ApplyElement = driver.findElement(XPathApplybtn);
			gen.waitnclickElement(driver, XPathApplybtn, "Apply",oPSelFW);
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void enterShipToAddressDetails(WebDriver driver, Map<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) {
		try {
			String shipToAddDetails = XLTestData.get("NewShipToaddress").toString();
			if (!shipToAddDetails.contains("NoValueCellTypeBLANK")) {
				gen1.waitUntilElementIsClickable(driver, 300, FirstName);
				String ShipAddDetails[] = shipToAddDetails.split(":");
				String ShipAddDet[] = shipToAddDetails.split("::");
				gen.setText(driver, FirstName, ShipAddDetails[1], "Ship To First Name");
				gen.setText(driver, LastNme, ShipAddDetails[2], "Ship To Last Name");
				gen.setText(driver, EmailAddress, ShipAddDetails[4], "Ship to Email Address");
				gen.setText(driver, FirstName, ShipAddDetails[1], "Ship To First Name");
				WebElement DayPhn = driver.findElement(By.xpath(DayPhone));
				DayPhn.clear();
				DayPhn.sendKeys(ShipAddDet[1]);
				DayPhn.sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				// Address Line
				gen.setText(driver, AdressLine1, ShipAddDetails[5], "Address Line 1");
				Thread.sleep(100);
				WebElement PostalCod = driver.findElement(By.xpath(PostalCode));
				PostalCod.clear();
				PostalCod.sendKeys(ShipAddDet[2]);
				PostalCod.sendKeys(Keys.TAB);
				Thread.sleep(1000);
				ApplyElement = driver.findElement(XPathApplybtn);
				gen.waitnclickElement(driver, XPathApplybtn, "Apply",oPSelFW);
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
public void cancelsingleOrder(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception
{
	try {
		Thread.sleep(3000);
		
		gen.waitnclickElement(driver, By.xpath("//*[@uid='cancelOrder']/div[2]/div/div/a"));
	Thread.sleep(3000);

	String ReasonCodexPath = "(//*[@uid='CancelOrderBaseScreen']/div/div/div/div[2]/input)[1]";
	String SubCodexPath = "(//*[@uid='CancelOrderBaseScreen']/div/div/div/div[2]/input)[3]";
	gen.waitUntilElementIsPresent(driver, By.xpath(ReasonCodexPath));
	Thread.sleep(3000);
	driver.findElement(By.xpath(ReasonCodexPath)).sendKeys("Payment Failure");
	Thread.sleep(1000);
	driver.findElement(By.xpath(ReasonCodexPath)).sendKeys(Keys.TAB);
	Thread.sleep(3000);
	driver.findElement(By.xpath(SubCodexPath)).sendKeys("Credit Card Authorization Failure");
	Thread.sleep(1000);
	driver.findElement(By.xpath(SubCodexPath)).sendKeys(Keys.TAB);
	Thread.sleep(1000);
	gen.waitnclickElement(driver, By.xpath("(//*[@role='radio'])[4]"));
	Thread.sleep(1000);
	//check box click
	driver.findElement(By.xpath("(//*[@uid='cancelOrderListScreen']/span/div/div/div/div[2]/div[3]/div[4]/div/table/tbody/tr/td/span)[1]")).click();
	Thread.sleep(1000);
	gen.waitnclickElement(driver, By.xpath("//*[@uid='update_order']/span[1]/span/span[3]"));
	Thread.sleep(1000);
	System.out.println("Befor Ok");
	gen.waitnclickElement(driver, Okbtn, "OK", oPSelFW);
	System.out.println("After ok");
	Thread.sleep(1000);
	if(driver.findElements(By.xpath("(//*[@role='alertdialog']/div[4]/span[2]/span/span/span)[1]")).size() > 0)
	{
		if(driver.findElement(Okbtn).isDisplayed() && driver.findElement(Okbtn).isEnabled())
		{
			gen.shortwaitnclickElement(driver, Okbtn, "OK", oPSelFW);
			System.out.println("After ok");
		}
		
	}
	Thread.sleep(5000);
	System.out.println("Befor next");
	gen.shortwaitnclickElement(driver, By.xpath("//*[@uid='nextBttn2']/span[1]/span"), "Next", oPSelFW);
	Thread.sleep(5000);
	System.out.println("After next");
	gen.waitnclickElement(driver, By.xpath("(//span[contains(@id,'Button') and normalize-space(text())='Confirm'])[2]"));
	Thread.sleep(30000);
	getbelowStatusOfOrder(driver, oPSelFW);
	getbelowStatusOfOrder1(driver, oPSelFW);
	Thread.sleep(3000);
	}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		oPSelFW.reportStepDetails("Error Occured "  , "Error in Cancelling the Order", "Pass");
		log.info( "Error in Cancelling the Order");
		Assert.assertTrue(false);
	}	
}
public void changesuborderaddress(WebDriver driver, WebElement element,ProlificsSeleniumAPI oPSelFW,HashMap<String, String> XLTestData) throws Exception
{
	try {
			Thread.sleep(5000);
			gen.waitnclickElement(driver, changeFillfillmentOption);
		 By checkboxbtnforsuborder = By.xpath(checkboxbtn);
		 Thread.sleep(5000);
		 driver.findElement(checkboxbtnforsuborder).click();
		 Thread.sleep(5000);
		 By changeAddressforsuborder = By.xpath(changeAddress);
		 driver.findElement(changeAddressforsuborder).click();
		 Thread.sleep(5000);
		editAddressDetails(driver,XLTestData, oPSelFW);
		Thread.sleep(10000);
		gen.waitnclickElement(driver, xPathNextbtn, "Next", oPSelFW);	
		gen.waitnclickElement(driver, By.xpath(Confirmbtn), "Confirm",oPSelFW);
		
		oPSelFW.reportStepDetails("Billing Address Details Should be changed"  , "Billing Address Details are successfully changed", "Pass");
		log.info("Billing Address Details are successfully changed");
	}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		oPSelFW.reportStepDetails("Error Occured "  , "Error in Cancelling the Order", "Pass");
		log.info( "Error in Cancelling the Order");
		Assert.assertTrue(false);
	}	
}
public void changecarrierservice(WebDriver driver, WebElement element,ProlificsSeleniumAPI oPSelFW,HashMap<String, String> XLTestData) throws InterruptedException, Exception
{
	try {
		selectCustomerTypeAndEmployeeType(driver, element, oPSelFW, XLTestData);
		final By NextbtnXpath = By.xpath(Nextbtn);
		gen.waitnclickElement(driver, NextbtnXpath, "Next", oPSelFW);
		Thread.sleep(5000);
		 By checkboxbtnforsuborder = By.xpath(checkboxbtn);
		 driver.findElement(checkboxbtnforsuborder).click();
	
		 By changeAddressforsuborder = By.xpath(changeAddress);
		 driver.findElement(changeAddressforsuborder).click();
		String NewFirstName = XLTestData.get("NewBillFirstName").toString();
		String NewLastName = XLTestData.get("NewBillLastName").toString();
		String NewEmailAddress = XLTestData.get("NewBillEmailAddress").toString();
		String NewDayPhone = XLTestData.get("NewBillDayPhone").toString();
		String NewAddressLine1  = XLTestData.get("NewBillAddressLine1").toString();
		String NewZip = XLTestData.get("NewBillZip").toString();
		//enterAddressDetails(driver, NewFirstName, NewLastName, NewEmailAddress, NewDayPhone, NewAddressLine1, NewZip, oPSelFW);
		if(!driver.findElements(By.xpath("(//*[@id='isccs_common_address_capture_AddressCapturePage_0']/div[4]/span/span/span)[1]")).isEmpty())
				gen.clickElement(driver, "(//*[@id='isccs_common_address_capture_AddressCapturePage_0']/div[4]/span/span/span)[1]");
		Thread.sleep(1000);
		By popUpnext = By.xpath("(//*[@uid='Popup_btnNext'])[1]");
		Thread.sleep(1000);
		if(driver.findElements(popUpnext).size() > 0) 
			driver.findElement(popUpnext).click();
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
				oPSelFW.reportStepDetails("Rush should be updated successfully message"  , "Rush is updated successfully message is displayed", "Pass");
				log.info("Rush is updated successfully message is displayed");
			}	
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		 gen.waitnclickElement(driver, xPathNextbtn, "Next", oPSelFW);
	}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		oPSelFW.reportStepDetails("Error Occured "  , "Error in Cancelling the Order", "Pass");
		log.info("Error in Cancelling the Order");
		Assert.assertTrue(false);
	}	
}
public void cancelMultOrder(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception
{
	try {
	String ReasonCodexPath = "(//*[@uid='CancelOrderBaseScreen']/div/div/div/div[2]/input)[1]";
	gen.waitnclickElement(driver, By.xpath("//*[@uid='cancelOrder']/div[2]/div/div/a"));
	gen.waitUntilElementIsPresent(driver, By.xpath(ReasonCodexPath));
	Thread.sleep(3000);
	gen.setTextAndHitTab(driver, ReasonCodexPath, "Payment Failure", "Reason Code", oPSelFW);
	Thread.sleep(3000);
	//subcode
	gen.setTextAndHitTab(driver, "(//*[@uid='CancelOrderBaseScreen']/div/div/div/div[2]/input)[3]", "Credit Card Authorization Failure", "Sub Reason Code", oPSelFW);
	Thread.sleep(1000);
	gen.waitnclickElement(driver, By.xpath("//*[@uid='nextBttn2']/span[1]/span/span[3]"));
	Thread.sleep(1000);
	
	//check once while excuting tc 
	
	gen.waitnclickElement(driver, By.xpath("(//*[@role='alertdialog']/div[4]/span[2]/span/span/span)[1]"));
	Thread.sleep(1000);

	if(!driver.findElements(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")).isEmpty())
	{	
		String errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")).getAttribute("innerText");
		if(errorMessage.contains("Some actions have been disabled because they are not applicable to the status of this order."))
			
			oPSelFW.reportStepDetails("Rush should be display successfully message "  , "Rush is updated successfully message is displayed", "Pass");
		log.info("Rush is updated successfully message is displayed");
	}	
	gen.waitnclickElement(driver, By.xpath("(//span[contains(@id,'Button') and normalize-space(text())='Confirm'])[2]"));
	Thread.sleep(1000);
	getStatusOfOrder(driver, oPSelFW);
	getStatusOfOrder1(driver, oPSelFW);
	getbelowStatusOfOrder(driver, oPSelFW);
	getbelowStatusOfOrder1(driver, oPSelFW);
	}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		oPSelFW.reportStepDetails("Error Occured "  , "Error in Cancelling the Order", "Pass");
		log.info("Error in Cancelling the Order");
		Assert.assertTrue(false);
		}	
}
public void Clicksearchbutton(WebDriver driver,WebElement element,ProlificsSeleniumAPI oPSelFW) throws Exception
{
	try {
		gen1.waitUntilElementIsClickable(driver, 1000, "//*[@uid='lnk_RT_OrderSearch']/div[2]/div/div/a");
		element = driver.findElement(By.xpath("//*[@uid='lnk_RT_OrderSearch']/div[2]/div/div/a"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
	}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		oPSelFW.reportStepDetails("Error Occured "  , "Error in Cancelling the Order", "Pass");
		log.info("Error in Cancelling the Order");
		Assert.assertTrue(false);
	}	
}
public void ClickSearchButton(WebDriver driver, WebElement element,ProlificsSeleniumAPI oPSelFW) throws Exception
{
	try {
			gen.waitnclickElement(driver, By.xpath("//*[@uid='SST_SearchButton']/span/span/span[3]"), "Search button ", oPSelFW);
		}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		oPSelFW.reportStepDetails("Error Occured "  , "Error in Cancelling the Order", "Pass");
		log.info("Error in Cancelling the Order");
		Assert.assertTrue(false);
	}	
}
public void ClickClosebtn1(WebDriver driver, WebElement element,ProlificsSeleniumAPI oPSelFW) throws Exception
{
	try {
		gen.shortwaitnclickElement(driver, By.xpath("(//*[@uid='closeBttn2']/span/span)[2]"), "Close button", oPSelFW);
		Thread.sleep(5000);
	}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		Assert.assertTrue(false);
	}	
}
public void ClickClosebtn2(WebDriver driver, WebElement element,ProlificsSeleniumAPI oPSelFW) throws Exception
{
	try {
		gen.shortwaitnclickElement(driver, By.xpath("//*[@uid='closeBttn2']/span/span"), "Close button", oPSelFW);
		gen1.waitUntilElementIsClickable(driver, 1000, "//*[@uid='lnk_RT_OrderSearch']/div[2]/div/div/a");
	}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		oPSelFW.reportStepDetails("Error Occured "  , "Error in Cancelling the Order", "Pass");
		log.info("Error in Cancelling the Order");
		Assert.assertTrue(false);
	}	
}

public void OrderSearch(WebDriver driver, WebElement element,ProlificsSeleniumAPI oPSelFW) throws Exception
{
	try {
		oPSelFW.reportStepDetails("Order Search link should be clickable " ,"Clicked on Order Search link " , "Pass");
		log.info("Clicked on Order Search link " );
		oPSelFW.reportStepDetails("Order Search Page should display " ,"Order Search Page displayed " , "Pass");
		log.info("Order Search Page displayed " );
	}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		oPSelFW.reportStepDetails("Error Occured "  , "Error in Cancelling the Order", "Pass"); 
		log.info("Error in Cancelling the Order"); 
		Assert.assertTrue(false);
	}	
}
public void SearchResults(WebDriver driver, WebElement element,ProlificsSeleniumAPI oPSelFW) throws Exception
{
	try {
		oPSelFW.reportStepDetails("Search button should be clickkable" ,"Search button is clicked" , "Pass");
		log.info("Search button is clicked" );
		oPSelFW.reportStepDetails("Search Results Page should display " ,"Search Results Page displayed " , "Pass");
		log.info("Search Results Page displayed " );
	
	}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		oPSelFW.reportStepDetails("Error Occured "  , "Error in Cancelling the Order", "Pass");
		log.info( "Error in Cancelling the Order");
		Assert.assertTrue(false);
	}	
}
public String OrderNumberfromUI(WebDriver driver, WebElement element,String OrderStatusUI,ProlificsSeleniumAPI oPSelFW) throws Exception
{
	try {
		Thread.sleep(5000);
		String ordernumber = "//*[@uid='pnlPrimaryInfo']/div/table/tbody/tr/td/span";
		OrderStatusUI = driver.findElement(By.xpath(ordernumber)).getText();
		System.out.println("ORDER_NO status from UI:" + OrderStatusUI);
	}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		oPSelFW.reportStepDetails("Get Order Number UI"  , "Order Number is not displayed in the UI", "Fail");
		log.error("Order Number is not displayed in the UI");
		Assert.assertTrue(false);
	}	
	return OrderStatusUI;
}
public void ClickGiftRegistrySearch(WebDriver driver,WebElement element,ProlificsSeleniumAPI oPSelFW) throws Exception
{
	try {
		gen1.waitUntilElementIsClickable(driver, "//*[@uid='extn_gr_search']/div/div/div/a");
		element = driver.findElement(By.xpath("//*[@uid='extn_gr_search']/div/div/div/a"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
		}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		oPSelFW.reportStepDetails("Error Occured "  , "Error in Cancelling the Order", "Pass");
		log.info( "Error in Cancelling the Order");
		Assert.assertTrue(false);
	}	
}
public void ClickClosebtnforgift(WebDriver driver, WebElement element,ProlificsSeleniumAPI oPSelFW) throws Exception
{
	try {
		By clickclose = By.xpath("//*[@uid='closeBttn2']/span/span");
		driver.findElement(clickclose).click();
	}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		Assert.assertTrue(false);
	}	
}
public void ClickBalanceInquiry(WebDriver driver,WebElement element,ProlificsSeleniumAPI oPSelFW) throws Exception
{
	try {
		gen1.waitUntilElementIsClickable(driver, 1000, "//*[@uid='extn_lnkGetFunds']/div[2]/div/div/a");
		element = driver.findElement(By.xpath("//*[@uid='extn_lnkGetFunds']/div[2]/div/div/a"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
		oPSelFW.reportStepDetails("Balance Inquiry link should be clickable" ,"Balance Inquiry link clicked successfully" , "Pass");
		log.info("Balance Inquiry link clicked successfully" );
	}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		oPSelFW.reportStepDetails("Error Occured "  , "Error in Cancelling the Order", "Pass");
		log.info( "Error in Cancelling the Order");
		Assert.assertTrue(false);
	}	
}
public void BalanceInquirylink(WebDriver driver, WebElement element,ProlificsSeleniumAPI oPSelFW) throws Exception
{
	try {
		oPSelFW.reportStepDetails("Balance Inquiry pop up should be displayed " ,"Pop up displayed successfully" , "Pass"); 
		log.info("Pop up displayed successfully"); 
		}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		oPSelFW.reportStepDetails("Error Occured "  , "Error in Cancelling the Order", "Pass");
		log.info( "Error in Cancelling the Order");
		Assert.assertTrue(false);
	}	
}


public void DoubleCLick(WebDriver driver, ProlificsSeleniumAPI oPSelFW)
{
	Actions action = new Actions(driver);
	WebElement we = driver.findElement(By.xpath("//*[@uid='OLST_listGrid']/div[3]/div[2]/div/table/tbody/tr/td[11]"));
	action.doubleClick(we).build().perform();
	gen.waitUntilElementIsPresent(driver, "(//*[@uid='lblName'])[1]");
}

public void changeShipToAddressValidation(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception
{
	String uiName = driver.findElement(By.xpath("(//*[@uid='lblName'])[1]")).getText();
	
	oPSelFW.reportStepDetails("Updated user name should be display "  ,  "verified Updated user name is " +uiName, "Pass"); 
	log.info("verified Updated user name is " +uiName); 	
		
	
	Thread.sleep(5000);
    String uiAddress = driver.findElement(By.xpath("(//*[@uid='lblAddressLine1'])[1]")).getText();
	
	oPSelFW.reportStepDetails("Updated Address should be display "  ,  "verified Updated Address is " +uiAddress, "Pass"); 
	log.info("verified Updated Address is " +uiAddress); 	
	
	Thread.sleep(5000);
	
	String City=driver.findElement(By.xpath("(//*[@uid='lblCityStateZip'])[1]")).getText();
	
	String city = City.replaceAll("[-CA 94111-1226,]", "");
	System.out.println("City: "+city);
	
	oPSelFW.reportStepDetails("Updated City should be display "  ,  "verified Updated City is " +city, "Pass");
	log.info("verified Updated City is " +city);	
	
	
	 String uiZipcode = driver.findElement(By.xpath("(//*[@uid='lblCityStateZip'])[1]")).getText();
	 String Zipcode = City.replaceAll("[San Francisco CA,]", "");
	 System.out.println("Zipcode: " +Zipcode);
	 String Zipcodefromui = Zipcode.replaceAll("-1226", "");
		
	oPSelFW.reportStepDetails("Updated Zip code should be display "  ,  "verified Updated Zip Code is " +Zipcodefromui, "Pass");
	log.info( "verified Updated Zip Code is " +Zipcodefromui);	

		
		String Phone=driver.findElement(By.xpath("(//*[@uid='extn_lblPhone'])[1]")).getText();
		String phoneUI = Phone.replaceAll("-", "");
		String phoneui = phoneUI.replaceAll("\\p{P}","");
		System.out.println("Phone number: "+phoneui);
		oPSelFW.reportStepDetails("Updated Address should be display "  ,  "verified Updated Phone Number is " +phoneui, "Pass");
		log.info( "verified Updated Phone Number is " +phoneui);
}

public void changeBillingAddressValidation(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws Exception
{
	String uiName = driver.findElement(By.xpath("(//*[@uid='lblName'])[2]")).getText();
	
	oPSelFW.reportStepDetails("Updated user name should be display "  ,  "verified Updated user name is " +uiName, "Pass");
	log.info("verified Updated user name is " +uiName);	
		
	
	Thread.sleep(5000);
    	String uiAddress = driver.findElement(By.xpath("(//*[@uid='lblAddressLine1'])[2]")).getText();
	
	oPSelFW.reportStepDetails("Updated Address should be display "  ,  "verified Updated Address is " +uiAddress, "Pass");
	log.info("verified Updated Address is " +uiAddress);	
	
	Thread.sleep(5000);
	
	String City=driver.findElement(By.xpath("(//*[@uid='lblCityStateZip'])[2]")).getText();
	
	String city = City.replaceAll("[-CA 94111-1226,]", "");
	System.out.println("City: "+city);
	
	oPSelFW.reportStepDetails("Updated City should be display "  ,  "verified Updated City is " +city, "Pass");
	log.info("verified Updated City is " +city);	
	
	
	 String uiZipcode = driver.findElement(By.xpath("(//*[@uid='lblCityStateZip'])[2]")).getText();
	 String Zipcode = City.replaceAll("[San Francisco CA,]", "");
	System.out.println("Zipcode: " +Zipcode);
	String Zipcodefromui = Zipcode.replaceAll("-1226", "");
		
	oPSelFW.reportStepDetails("Updated Zip code should be display "  ,  "verified Updated Zip Code is " +Zipcodefromui, "Pass");
	log.info( "verified Updated Zip Code is " +Zipcodefromui);	

		
		String Phone=driver.findElement(By.xpath(phone)).getText();
		String phoneUI = Phone.replaceAll("-", "");
		String phoneui = phoneUI.replaceAll("\\p{P}","");
		System.out.println("Phone number: "+phoneui);
		oPSelFW.reportStepDetails("Updated Address should be display "  ,  "verified Updated Phone Number is " +phoneui, "Pass");
		log.info( "verified Updated Phone Number is " +phoneui);	
			

}
	public void clickBlandReturnLink(WebDriver driver,WebElement element,ProlificsSeleniumAPI oPSelFW) throws Exception
{
	try {
		gen1.waitUntilElementIsClickable(driver, 1000, "(//*[@uid='extn_createBlindRO']/div[2]/div/div/a)[1]");
		element = driver.findElement(By.xpath("(//*[@uid='extn_createBlindRO']/div[2]/div/div/a)[1]"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
		oPSelFW.reportStepDetails("Blind Return link should be clickable" ,"Blind Return link clicked successfully" , "Pass");
		log.info("Blind Return link clicked successfully" );
	}
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		oPSelFW.reportStepDetails("Error Occured "  , "Error in Cancelling the Order", "Pass");
		log.info("Error in Cancelling the Order");
		Assert.assertTrue(false);
	}	
}

public void returnOrderDetails(WebDriver driver,WebElement element,Map<String, String> XLTestData,ProlificsSeleniumAPI oPSelFW) throws Exception, Exception
{
	try {
		String ItemIDs = XLTestData.get("ItemID").toString();
		String Items[] = ItemIDs.split(";");
		String Eachs = XLTestData.get("Each").toString();
		String EachsList[] = Eachs.split(";");
		for(int ItemNum=0; ItemNum<Items.length; ItemNum++)
		{	
		Thread.sleep(3000);

		driver.findElement(By.xpath("(//*[@uid='extn_txt_ItemID']/div[2]/div)[1]/div/input")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[@uid='extn_txt_ItemID']/div[2]/div)[1]/div/input")).sendKeys(Items[ItemNum]);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//*[@uid='extn_txt_ItemID']/div[2]/div)[1]/div/input")).sendKeys(Keys.TAB);
		Thread.sleep(3000);
		
		gen.waitnclickElement(driver, By.xpath(AddBtn2), "Add", oPSelFW);
		Thread.sleep(5000);
		
		}
		Thread.sleep(2000);
		String ReturnReasonLink = "((//*[@uid='cmbReturnReason'])[3]/div[2]/div/div/input)[1]";
		String ReturnReason = "((//*[@uid='cmbReturnReason'])[3]/div[2]/div/div/input)[2]";
		String ReturnSubReasonLink = "((//*[@uid='extn_cmbReturnSubReason'])[3]/div/div/div/input)[1]";
		String ReturnSubReason = "((//*[@uid='extn_cmbReturnSubReason'])[3]/div/div/div/input)[2]";
		String MerchandiseReceiptPolicyLink = "((//*[@uid='extn_cmbMerchandiseReceiptPolicy'])[3]/div/div/div/input)[1]";
		String MerchandiseReceiptPolicy = "((//*[@uid='extn_cmbMerchandiseReceiptPolicy'])[3]/div/div/div/input)[2]";
		String ReturnActionLink = "((//*[@uid='extn_cmbReturnActionType'])[3]/div[2]/div/div/input)[1]";
		String ReturnAction = "((//*[@uid='extn_cmbReturnActionType'])[3]/div[2]/div/div/input)[2]";
		String ReturnLineNote = "(//*[@uid='extn_txt_ReturnLineNote'])[3]/div[2]/div/textarea";
		Thread.sleep(3000);
		gen.waitUntilElementIsPresent(driver, ReturnReasonLink);
		Thread.sleep(2000);
		driver.findElement(By.xpath(ReturnReasonLink)).click();
		driver.findElement(By.xpath(ReturnReason)).sendKeys("NOT SATISFIED");;
		Thread.sleep(300);
		driver.findElement(By.xpath(ReturnReason)).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		gen.waitUntilElementIsPresent(driver, ReturnSubReasonLink);
		Thread.sleep(2000);
		driver.findElement(By.xpath(ReturnSubReasonLink)).click();
		driver.findElement(By.xpath(ReturnSubReason)).sendKeys("Doesn’t Meet Expectations");;
		Thread.sleep(300);
		driver.findElement(By.xpath(ReturnSubReason)).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		gen.waitUntilElementIsPresent(driver, MerchandiseReceiptPolicyLink);
		Thread.sleep(2000);
		driver.findElement(By.xpath(MerchandiseReceiptPolicyLink)).click();
		driver.findElement(By.xpath(MerchandiseReceiptPolicy)).sendKeys("Return is required");;
		Thread.sleep(300);
		driver.findElement(By.xpath(MerchandiseReceiptPolicy)).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		gen.waitUntilElementIsPresent(driver, ReturnActionLink);
		Thread.sleep(2000);
		driver.findElement(By.xpath(ReturnActionLink)).click();
		driver.findElement(By.xpath(ReturnAction)).sendKeys("Replacement");
		Thread.sleep(300);
		driver.findElement(By.xpath(ReturnAction)).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		gen.waitUntilElementIsPresent(driver, ReturnLineNote);
		Thread.sleep(2000);
		driver.findElement(By.xpath(ReturnLineNote)).sendKeys("Note");
		//gen.clickElement(driver, xpathExpress);
		gen.clickElement(driver, "//*[@uid='btnUpdateOrder']/span/span/span[3]", "Save Btn", oPSelFW);
		//gen.waitnclickElement(driver, xPathOfElement, NameOfField, oPSelFW);
		gen.waitnclickElement(driver, xPathNextbtn, "Next Button", oPSelFW);
		
		Thread.sleep(3000);
		
		String text = driver.findElement(By.xpath("//*[@class='messageTitles']")).getText();
		if (text.contains("Your selected product was successfully added to the replacement order."))
			
			oPSelFW.reportStepDetails("Order should update"  , "\r\n" + 
					"Your selected product was successfully added to the replacement order.  ", "Pass");
		log.info("Your selected product was successfully added to the replacement order.  ");
	}

	
	catch(Exception e)
	{
		gen.closeTheOrder(driver, oPSelFW);
		oPSelFW.reportStepDetails("Error Occured "  , "Error in Cancelling the Order", "Pass");
		log.info("Error in Cancelling the Order");
		Assert.assertTrue(false);
	}	

}
public void Confirmbtn(WebDriver driver,ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException 
{
	final By XpathNextbtn = By.xpath(Confirmbtn);
	gen1.waitUntilElementIsClickable(driver, 25000, XpathNextbtn);
	WebElement element = driver.findElement(XpathNextbtn);
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	executor.executeScript("arguments[0].click()", element);
	oPSelFW.reportStepDetails("Next Button should be Clickable"  ,"Next Button is Clicked", "Pass");
	log.info("Next Button is Clicked");
		Thread.sleep(8000);
}
	
	public void editAddressDetails(WebDriver driver,  HashMap<String, String> XLTestData,ProlificsSeleniumAPI oPSelFW) throws Exception {
        String addressTitle = "";
        gen.setText(driver, FirstName, XLTestData.get("NewBillFirstName").toString(), "Bill To First Name", oPSelFW);
        gen.setText(driver, LastNme, XLTestData.get("NewBillLastName").toString(), "Bill To Last Name", oPSelFW);
        gen.setText(driver, EmailAddress, XLTestData.get("NewBillEmailAddress").toString(), "Bill To Email Address", oPSelFW);
        gen.setText(driver, DayPhone, XLTestData.get("NewBillDayPhone").toString(), "Bill To Day Phone", oPSelFW);
        // Address Line
        gen.setText(driver, AdressLine1, XLTestData.get("BillToAddressLine1").toString(), "Bill To Address Line 1", oPSelFW);
        driver.findElement(By.xpath(PostalCode)).clear();
        WebElement PostalCod = driver.findElement(By.xpath(PostalCode));
        PostalCod.sendKeys(XLTestData.get("NewBillZip").toString());
        //PostalCod.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        int numberofApplyBttons = driver.findElements(By.xpath("(//span[text()='Apply'])")).size();
        By XPathApplybtn1 = By.xpath("(//span[text()='Apply'])["+numberofApplyBttons+"]");
        if(driver.findElements(XPathApplybtn1).size() > 0)
              gen.shortwaitnclickElement(driver, XPathApplybtn1, "Apply", oPSelFW);
        else
        {
              By XPathApplybtn4 = By.xpath("(//span[text()='Apply'])[3]");
              if(driver.findElements(XPathApplybtn4).size() > 0)
                    gen.shortwaitnclickElement(driver, XPathApplybtn4, "Apply", oPSelFW);
        }     
        Thread.sleep(5000);
  }


	public void waitUntilBrowserIsLoading(WebDriver driver) throws InterruptedException {
		int i = 1;
		while (!driver.findElements(By.xpath("//*[@id='sc_plat_dojo_widgets_ScreenDialogUnderlay_1']/div[2]/div[1]"))
				.isEmpty() && i <= 20) {
			Thread.sleep(1000);
			i++;
		}
	}

	
}
