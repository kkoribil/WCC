package Scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.prolifics.ProlificsSeleniumAPI;

import FrameWork.DataBase;
import FrameWork.Excel_Reader;

import java.io.*;
import java.sql.Connection;
import java.util.*;
import java.util.concurrent.TimeUnit;

import Library.CreateOrderPage1;
import Library.DbValidations;
import Library.EmailReport;
import Library.Generic1;

import Library.LoginPage1;

public class OrderAmendments {

	private static Excel_Reader excelReader;
	public static int i = 1;
	int PassCount = 0, FailCount = 0;
	public static ProlificsSeleniumAPI oPSelFWOA = null;
	public Connection OraConn;
	private HashMap<String, String> XLTestDataOA = new HashMap<String, String>();
	boolean ItemDetailsDisplayed = false;
	public static WebDriver driver;
	Generic1 gen = new Generic1();
	CreateOrderPage1 ordercreate = new CreateOrderPage1();
	LoginPage1 login = new LoginPage1();
	WebElement element;
	String GenItemNumber;
	DataBase db = new DataBase();
	EmailReport em = new EmailReport();
	static boolean scriptSucful = false;
	static boolean loginSuccess;
	boolean ItemExistsInWCC = false;
	static String OutputFileName = System.getProperty("user.dir") + "\\target\\CreateOrder";
	static boolean AppLaunched;
	String getOrderNumber = "";
	DbValidations validation = new DbValidations();
	String orderno = null;

	@BeforeMethod(alwaysRun = true)
	public void launchBrowser() throws InterruptedException, IOException {
		if (scriptSucful == false) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String Env = System.getProperty("Environment");
			if (Env.contains("STST2"))
				loginSuccess = gen.launchURL(driver, gen.getPropertyValue("STST2URL"), oPSelFWOA);
			else if (Env.contains("STST"))
				loginSuccess = gen.launchURL(driver, gen.getPropertyValue("STSTURL"), oPSelFWOA);
			else if (Env.contains("EQA3"))
				loginSuccess = gen.launchURL(driver, gen.getPropertyValue("EQA3URL"), oPSelFWOA);
			LoginPage1 login = new LoginPage1();
			login.LoginToWCC(driver, System.getProperty("UserID"), System.getProperty("Password"), oPSelFWOA);
		}
	}

	@BeforeClass(alwaysRun = true)
	public void beforeTest1() throws Exception {
		String Env = System.getProperty("Environment");
		String TestDataPath = System.getProperty("user.dir") + "\\Data\\" + Env + "_OrderAmendments.xlsx";
		excelReader = new Excel_Reader(TestDataPath);
		excelReader.cFileNameWithPath = TestDataPath;
		excelReader.cSheetName = "TestData";
		excelReader.cTcID = "TestCaseID";
		excelReader.cTcValue = "1";

		oPSelFWOA = new ProlificsSeleniumAPI("WCC", "ORDER AMENDMENTS", "Chrome", "Windows 7", "STST", "Automation",
				"TestAutomation", "Regression");
		oPSelFWOA.sAutomationPath = System.getProperty("user.dir") + "\\ProlificsReports\\";
		oPSelFWOA.reportPath(System.getProperty("user.dir") + "\\target\\");
		oPSelFWOA.startReport();

		/*
		 * try { System.setProperty("webdriver.chrome.driver",
		 * System.getProperty("user.dir") + "\\resources\\chromedriver.exe"); driver =
		 * new ChromeDriver(); Env = System.getProperty("Environment");
		 * if(Env.contains("STST2")) AppLaunched = gen.launchURL(driver,
		 * gen.getPropertyValue("STST2URL"), oPSelFWOA); else AppLaunched =
		 * gen.launchURL(driver, gen.getPropertyValue("STSTURL"), oPSelFWOA);
		 * login.LoginToWCC(driver, System.getProperty("UserID"),
		 * System.getProperty("Password"),oPSelFWOA);
		 * 
		 * //login.LoginToWCC(driver, System.getProperty("UserID"),
		 * System.getProperty("Password"),basetest);// kept for situaltional execution
		 * out side need to remove if(Env.contains("STST2"))
		 * oPSelFWOA.reportStepDetails("The requested test environment " +
		 * Env,"The responded test environment is " + Env , "Pass"); else
		 * oPSelFWOA.reportStepDetails("The requested test environment " +
		 * Env,"The responded test environment is " + Env, "Pass");
		 * 
		 * } catch (Exception e) { System.out.println(e); }
		 */
	}

	@Test(priority = 1, enabled = true)
	public void changeBillingAddress() throws Throwable {
		try {
			i = 3;
			XLTestDataOA = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String TestCaseIDno = XLTestDataOA.get("TestCaseID").toString();
			String lable = TestCaseIDno + "_" + "Order Search" + "_" + XLTestDataOA.get("TestCaseTitle").toString();
			oPSelFWOA.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
			String Orderno = validation.getOrderNumber(driver, orderno, XLTestDataOA, oPSelFWOA);
			/*
			 * String Order = "Order"; String ordernumber = gen.getOrderNumber(Order);
			 * System.out.println(ordernumber); Thread.sleep(5000);
			 */
			oPSelFWOA.reportStepDetails("Order number should be displyed", "Order number is " + Orderno, "Pass");
			driver.findElement(By.xpath("(//*[@uid='txtOrderNo']/div/div/div/input)[1]")).sendKeys(Orderno);
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@uid='txtOrderNo']/div/div/div/input)[1]")).sendKeys(Keys.TAB);
			gen.waitnclickElement(driver, By.xpath("//*[@uid='bFindOrder']/span"));
			ordercreate.changeBillingAddress(driver, oPSelFWOA, XLTestDataOA);
			Thread.sleep(5000);
			ordercreate.changeBillingAddressValidation(driver, oPSelFWOA);
			gen.closeTheOrder(driver, oPSelFWOA);
		}

		catch (Exception e) {
			System.out.println(e);
			oPSelFWOA.reportStepDetails("Verify if exception is displayed", "Exception is displayed in Order Amendments", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Order Amendments");
		}
	}

	@Test(priority = 2, enabled = true)
	public void changeShippingAddress() throws Throwable {
		try {
			i = 4;
			XLTestDataOA = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String TestCaseIDno = XLTestDataOA.get("TestCaseID").toString();
			String lable = TestCaseIDno + "_" + "Order Search" + "_" + XLTestDataOA.get("TestCaseTitle").toString();
			oPSelFWOA.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
			String Orderno = validation.getOrderNumber(driver, orderno, XLTestDataOA, oPSelFWOA);
			/*
			 * String Order = "Order"; String ordernumber = gen.getOrderNumber(Order);
			 * System.out.println(ordernumber); Thread.sleep(5000);
			 */
			oPSelFWOA.reportStepDetails("Order number should be displyed", "Order number is " + Orderno, "Pass");
			driver.findElement(By.xpath("(//*[@uid='txtOrderNo']/div/div/div/input)[1]")).sendKeys(Orderno);
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@uid='txtOrderNo']/div/div/div/input)[1]")).sendKeys(Keys.TAB);
			gen.waitnclickElement(driver, By.xpath("//*[@uid='bFindOrder']/span"));
			ordercreate.changeShipToAddress(driver, oPSelFWOA, XLTestDataOA);
			System.out.println("Order Number Generated" + getOrderNumber);
			Thread.sleep(5000);
			ordercreate.verifyIfOrderIsUpdated(driver, oPSelFWOA);
			ordercreate.ClickonNext(driver, oPSelFWOA);
			gen.waitnclickElement(driver,By.xpath("(//span[contains(@id,'Button') and normalize-space(text())='Confirm'])[2]"), "Confirm",oPSelFWOA);
			Thread.sleep(10000);
			ordercreate.DoubleCLick(driver, oPSelFWOA);
			Thread.sleep(5000);
			ordercreate.changeShipToAddressValidation(driver, oPSelFWOA);
			gen.closeTheOrder(driver, oPSelFWOA);
		}
		catch (Exception e) {
			System.out.println(e);
			oPSelFWOA.reportStepDetails("Verify if exception is displayed", "Exception is displayed in Order Amendments", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Order Amendments");
		}

	}

	/*
	 * @Test(priority = 3, enabled=true) public void Cancellation_LinesofSubOrder()
	 * throws Throwable { try { i = 5; XLTestData = new HashMap<String, String>();
	 * XLTestData = excelReader.readExcel("TC_WCC_" + Integer.toString(i)); String
	 * TestCaseIDno = XLTestData.get("TestCaseID").toString(); String lable =
	 * TestCaseIDno + "_" +"Order Search" + "_" +
	 * XLTestData.get("TestCaseTitle").toString();
	 * oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
	 * 
	 * String Order = "Order"; String ordernumber = gen.getOrderNumber(Order);
	 * System.out.println(ordernumber); Thread.sleep(5000);
	 * 
	 * oPSelFW.reportStepDetails("Order number should be displyed" ,
	 * "Order number is "+ordernumber, "Pass");
	 * 
	 * 
	 * driver.findElement(By.xpath("(//*[@uid='txtOrderNo']/div/div/div/input)[1]"))
	 * .sendKeys(ordernumber); Thread.sleep(5000);
	 * driver.findElement(By.xpath("(//*[@uid='txtOrderNo']/div/div/div/input)[1]"))
	 * .sendKeys(Keys.TAB);
	 * 
	 * gen.waitnclickElement(driver, By.xpath("//*[@uid='bFindOrder']/span"));
	 * 
	 * ordercreate.changesuborderaddress(driver, element, oPSelFW, XLTestData );
	 * 
	 * ordercreate.AddPaymentMethod(driver, element, XLTestData,oPSelFW);
	 * Thread.sleep(5000);
	 * 
	 * getOrderNumber = ordercreate.getOrderNumber(driver, oPSelFW);
	 * //ordercreate.validateOrderDetails(driver, OrderTotal, XLTestData, oPSelFW);
	 * 
	 * System.out.println("Order Number Generated" +getOrderNumber);
	 * ordercreate.cancelsingleOrder(driver, oPSelFW);
	 * 
	 * 
	 * 
	 * gen.closeTheOrder(driver, oPSelFW); Thread.sleep(5000);
	 * //basetest.extent.flush();
	 * 
	 * } catch (Exception e) { System.out.println(e); //basetest.extent.flush(); } }
	 */
	/*
	 * @Test(priority = 4,enabled=true) public void Cancellation_ModifyofSubOrder()
	 * throws Throwable { String OrderTotal="0"; i = 6; XLTestData =
	 * excelReader.readExcel("TC_WCC_" + Integer.toString(i));
	 * 
	 * 
	 * String lable = "Order Amendments - " + "_" +
	 * XLTestData.get("TestCaseTitle").toString();
	 * 
	 * oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
	 * 
	 * String getOrderNumber; try {
	 * 
	 * if(AppLaunched) { login.LoginToWCC(driver, System.getProperty("UserID"),
	 * System.getProperty("Password"),oPSelFW);
	 * 
	 * ordercreate.clickOnOrderLink(driver, oPSelFW);
	 * ordercreate.ConceptTypeScroll(driver, XLTestData.get("Concept").toString(),
	 * oPSelFW); ordercreate.clickOnUnRegisteredCustomerLink(driver, oPSelFW);
	 * ordercreate.selectOrderType(driver, XLTestData.get("OrderType").toString(),
	 * XLTestData.get("StoreNumber").toString(), oPSelFW); String ItemsAdded =
	 * ordercreate.enterItemDetails(driver, XLTestData, oPSelFW);
	 * 
	 * Thread.sleep(10000); if(ItemsAdded.contains("Items Added")) { OrderTotal =
	 * ordercreate.validateOrderTotal(driver, oPSelFW);
	 * ordercreate.ClickonNext(driver, oPSelFW);
	 * ordercreate.enterAddressDetails(driver,"BillTo", XLTestData,oPSelFW);
	 * 
	 * ordercreate.enterAddressDetails(driver,"ShipTo", XLTestData, oPSelFW);
	 * 
	 * ordercreate.changecarrierservice(driver, element, oPSelFW, XLTestData);
	 * 
	 * ordercreate.AddPaymentMethod(driver, element, XLTestData,oPSelFW);
	 * Thread.sleep(5000);
	 * 
	 * getOrderNumber = ordercreate.getOrderNumber(driver, oPSelFW);
	 * ordercreate.validateOrderDetails(driver, OrderTotal, XLTestData, oPSelFW);
	 * 
	 * System.out.println("Order Number Generated" +getOrderNumber);
	 * ordercreate.cancelMultOrder(driver, oPSelFW);
	 * 
	 * 
	 * 
	 * gen.closeTheOrder(driver, oPSelFW); Thread.sleep(5000);
	 * //basetest.extent.flush();
	 * 
	 * 
	 * 
	 * 
	 * } else getOrderNumber = ItemsAdded; } else getOrderNumber =
	 * "Application Not Launched"; } catch (Exception e) { System.out.println(e);
	 * //basetest.extent.flush(); } }
	 */
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
		Thread.sleep(100);
		driver.quit();
		oPSelFWOA.stopReport();
	}

	@AfterMethod(alwaysRun = true)
	public void oTest(ITestResult result) throws InterruptedException, IOException {
		oPSelFWOA.stopReport();
		em.afterSuite();
		if (result.getStatus() == ITestResult.FAILURE) {
			driver.close();
			Thread.sleep(100);
			driver.quit();
			Thread.sleep(100);
			scriptSucful = false;
		}
		if (result.getStatus() == ITestResult.SUCCESS) {
			scriptSucful = true;
		}
		if (result.getStatus() == ITestResult.SKIP) {
			System.out.println("Failure");
		}
	}
}