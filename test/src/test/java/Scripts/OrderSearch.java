package Scripts;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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
import Library.CreateOrderPage1;
import Library.DbValidations;
import Library.EmailReport;
import Library.Generic1;
import Library.LoginPage1;

public class OrderSearch {

	private static ProlificsSeleniumAPI oPSelFWOS = null;

	private static Excel_Reader excelReader;
	public static int i = 1;
	int PassCount = 0, FailCount = 0;
	public Connection OraConn;
	private HashMap<String, String> XLTestDataOS = new HashMap<String, String>();
	boolean ItemDetailsDisplayed = false;
	public static WebDriver driver;
	Generic1 gen = new Generic1();
	CreateOrderPage1 ordercreate = new CreateOrderPage1();
	DbValidations validation = new DbValidations();

	WebElement element;
	String GenItemNumber;
	DataBase db = new DataBase();
	LoginPage1 login = new LoginPage1();
	boolean ItemExistsInWCC = false;
	static String OutputFileName = System.getProperty("user.dir") + "\\target\\CreateOrder";
	static boolean AppLaunched;
	static boolean loginSuccess;
	String ordernumberfromDB = null;
	String OrderStatusUI = null;
	EmailReport em = new EmailReport();
	String getOrderNumber = "";
	public String OrderNumber = "(//*[@uid='txt_orderNo2']/div[2]/div/div/input)[1]";
	static boolean scriptSucful = false;

	@BeforeMethod(alwaysRun = true)
	public void launchBrowser() throws InterruptedException, IOException {
		if (scriptSucful == false) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String Env = System.getProperty("Environment");
			if (Env.contains("STST2"))
				loginSuccess = gen.launchURL(driver, gen.getPropertyValue("STST2URL"), oPSelFWOS);
			else if (Env.contains("STST"))
				loginSuccess = gen.launchURL(driver, gen.getPropertyValue("STSTURL"), oPSelFWOS);
			else if (Env.contains("EQA3"))
				loginSuccess = gen.launchURL(driver, gen.getPropertyValue("EQA3URL"), oPSelFWOS);
			LoginPage1 login = new LoginPage1();
			login.LoginToWCC(driver, System.getProperty("UserID"), System.getProperty("Password"), oPSelFWOS);
		}
	}

	@BeforeClass(alwaysRun = true)
	public void beforeTest1() throws Exception {
		String Env = System.getProperty("Environment");
		String TestDataPath = System.getProperty("user.dir") + "\\Data\\"+ Env + "_OrderSearch.xlsx";
		excelReader = new Excel_Reader(TestDataPath);
		excelReader.cFileNameWithPath = TestDataPath;
		excelReader.cSheetName = "TestData";
		excelReader.cTcID = "TestCaseID";
		excelReader.cTcValue = "1";
		
		oPSelFWOS = new ProlificsSeleniumAPI("WCC", "Order Search", "Chrome", "Windows 7", "STST", "Automation",
				"TestAutomation", "Regression");
		
		oPSelFWOS.sAutomationPath = System.getProperty("user.dir") + "\\ProlificsReports\\";
		oPSelFWOS.reportPath(System.getProperty("user.dir") + "\\target\\");
		oPSelFWOS.startReport();
	}

//*******************************!!!!!!!********************************************************//
	@Test(priority = 1, groups = { "smoke", "cov", "reg" })
	public void OrderSearch() throws Exception {
		try {
			i = 1;
			XLTestDataOS = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String TestCaseIDno = XLTestDataOS.get("TestCaseID").toString();
			String lable = TestCaseIDno + "_" + "Order Search" + "_" + XLTestDataOS.get("TestCaseTitle").toString();
			oPSelFWOS.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
			String orderNo = validation.OrderNumber(driver, XLTestDataOS, ordernumberfromDB, oPSelFWOS);
			oPSelFWOS.reportStepDetails("Order number should display from DB ", "Order Number from DB is " + orderNo,
					"Pass");
			System.out.println(orderNo);
			ordercreate.Clicksearchbutton(driver, element, oPSelFWOS);
			ordercreate.OrderSearch(driver, element, oPSelFWOS);
			gen.waitUntilElementIsPresent(driver, OrderNumber);
			gen.HandlingErrorMessage(driver, oPSelFWOS);
			driver.findElement(By.xpath(OrderNumber)).sendKeys(orderNo);
			Thread.sleep(500);
			driver.findElement(By.xpath(OrderNumber)).sendKeys(Keys.TAB);
			Thread.sleep(500);
			ordercreate.ClickSearchButton(driver, element, oPSelFWOS);
			ordercreate.SearchResults(driver, element, oPSelFWOS);
			gen.waitUntilElementIsPresent(driver, validation.conceptfromUI);
			validation.ValidatingOrderNumberStatus(driver, XLTestDataOS, orderNo, oPSelFWOS);
			ordercreate.ClickClosebtn1(driver, element, oPSelFWOS);
			ordercreate.ClickClosebtn2(driver, element, oPSelFWOS);
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e);
			oPSelFWOS.reportStepDetails("Verify if exception is displayed", "Exception is displayed in Order Search", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Order Search");
		}
	}
	@Test(priority = 2, groups = { "smoke", "cov", "reg" })
	public void OrderSearchwithemail() throws Exception {
		try {
			i = 2;

			XLTestDataOS = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String TestCaseIDno = XLTestDataOS.get("TestCaseID").toString();
			String lable = TestCaseIDno + "_" + "Order Search" + "_" + XLTestDataOS.get("TestCaseTitle").toString();
			oPSelFWOS.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
			ordercreate.Clicksearchbutton(driver, element, oPSelFWOS);
			ordercreate.OrderSearch(driver, element, oPSelFWOS);
			ordercreate.ConceptTypeSet(driver, XLTestDataOS, oPSelFWOS);
			Thread.sleep(500);
			String Emailfromtestdata = XLTestDataOS.get("BillToEmailAddress").toString();
			driver.findElement(By.xpath(
					"(//*[@uid='SST_GenericContentContainer']/div/div/table/tbody/tr/td/div/div[3]/div[2]/div/div/input)[1]"))
					.sendKeys(Emailfromtestdata);
			Thread.sleep(500);
			driver.findElement(By.xpath(
					"(//*[@uid='SST_GenericContentContainer']/div/div/table/tbody/tr/td/div/div[3]/div[2]/div/div/input)[1]"))
					.sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("Email should be entered", Emailfromtestdata + " is entered as mail ID",
					"Pass");
			String FirstName = XLTestDataOS.get("BillToFirstName").toString();
			driver.findElement(By.xpath("((//*[@uid='txt_fName']/div)[2]/div/div/input)[1]")).sendKeys(FirstName);
			Thread.sleep(500);
			driver.findElement(By.xpath("((//*[@uid='txt_fName']/div)[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("FirstName should be entered", FirstName + " is entered as first name", "Pass");
			ordercreate.ClickSearchButton(driver, element, oPSelFWOS);
			gen.HandlingErrorMessage(driver, oPSelFWOS);
			// gen.existsElement(driver, id);
			ordercreate.SearchResults(driver, element, oPSelFWOS);
			gen.waitUntilElementIsPresent(driver, validation.conceptfromUI);
			String Ordernum = ordercreate.OrderNumberfromUI(driver, element, OrderStatusUI, oPSelFWOS);
			Thread.sleep(1000);
			validation.ValidatingOrderNumberStatus(driver, XLTestDataOS, Ordernum, oPSelFWOS);
			ordercreate.ClickClosebtn1(driver, element, oPSelFWOS);
			ordercreate.ClickClosebtn2(driver, element, oPSelFWOS);
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e);
			oPSelFWOS.reportStepDetails("Get Order Number UI", "Order Number is not displayed in the UI", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Order Search");
		}
	}

//*************************************************!!!!!!!*******************************************************//
	@Test(priority = 3, groups = { "smoke", "cov", "reg" })
	public void OrderSearchwithPhone() throws Exception {
		try {
			i = 3;

			XLTestDataOS = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String TestCaseIDno = XLTestDataOS.get("TestCaseID").toString();
			String lable = TestCaseIDno + "_" + "Order Search" + "_" + XLTestDataOS.get("TestCaseTitle").toString();
			oPSelFWOS.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
			ordercreate.Clicksearchbutton(driver, element, oPSelFWOS);
			ordercreate.OrderSearch(driver, element, oPSelFWOS);
			gen.waitUntilElementIsPresent(driver, By.xpath("//*[@uid='txt_orderNo2']"));
			String PhoneNo = XLTestDataOS.get("BillToDayPhone").toString();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//*[@uid='txt_custPhone']/div[2]/div/div/input)[1]")).sendKeys(PhoneNo);
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@uid='txt_custPhone']/div[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("Phone Number should be entered", PhoneNo + " is entered", "Pass");
			String FirstName = XLTestDataOS.get("BillToFirstName").toString();
			driver.findElement(By.xpath("((//*[@uid='txt_fName']/div)[2]/div/div/input)[1]")).sendKeys(FirstName);
			Thread.sleep(500);
			driver.findElement(By.xpath("((//*[@uid='txt_fName']/div)[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
			Thread.sleep(500);
			ordercreate.ClickSearchButton(driver, element, oPSelFWOS);
			Thread.sleep(5000);
			gen.HandlingErrorMessage(driver, oPSelFWOS);
			ordercreate.SearchResults(driver, element, oPSelFWOS);
			gen.waitUntilElementIsPresent(driver, validation.conceptfromUI);
			String Ordernum = ordercreate.OrderNumberfromUI(driver, element, OrderStatusUI, oPSelFWOS);
			Thread.sleep(10000);
			validation.ValidatingOrderNumberStatus(driver, XLTestDataOS, Ordernum, oPSelFWOS);
			ordercreate.ClickClosebtn1(driver, element, oPSelFWOS);
			ordercreate.ClickClosebtn2(driver, element, oPSelFWOS);
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e);
			oPSelFWOS.reportStepDetails("Get Order Number UI", "Order Number is not displayed in the UI", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Order Search");
		}
	}
	/*

//**********************************************!!!******************************************************/
	@Test(priority = 4, groups = { "smoke", "cov", "reg" })
	public void OrderSearchwithname() throws Exception {
		try {
			i = 4;

			XLTestDataOS = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String TestCaseIDno = XLTestDataOS.get("TestCaseID").toString();
			String lable = TestCaseIDno + "_" + "Order Search" + "_" + XLTestDataOS.get("TestCaseTitle").toString();
			oPSelFWOS.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
			ordercreate.Clicksearchbutton(driver, element, oPSelFWOS);
			ordercreate.OrderSearch(driver, element, oPSelFWOS);
			gen.waitUntilElementIsPresent(driver, By.xpath("//*[@uid='txt_orderNo2']"));
			String LastName = XLTestDataOS.get("BillToLastName").toString();
			driver.findElement(By.xpath("((//*[@uid='txt_lName']/div)[2]/div/div/input)[1]")).sendKeys(LastName);
			Thread.sleep(500);
			driver.findElement(By.xpath("((//*[@uid='txt_lName']/div)[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("Last Name should be entered", LastName + " is entered as last name", "Pass");
			String FirstName1 = XLTestDataOS.get("BillToFirstName").toString();
			Thread.sleep(500);
			driver.findElement(By.xpath("((//*[@uid='txt_fName']/div)[2]/div/div/input)[1]")).sendKeys(FirstName1);
			Thread.sleep(500);
			driver.findElement(By.xpath("((//*[@uid='txt_fName']/div)[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("First Name should be entered", FirstName1 + " is entered as first name","Pass");
			ordercreate.ClickSearchButton(driver, element, oPSelFWOS);
			Thread.sleep(5000);
			gen.HandlingErrorMessage(driver, oPSelFWOS);
			ordercreate.SearchResults(driver, element, oPSelFWOS);
			gen.waitUntilElementIsPresent(driver, validation.conceptfromUI);
			String Ordernum = ordercreate.OrderNumberfromUI(driver, element, OrderStatusUI, oPSelFWOS);
			Thread.sleep(10000);
			validation.ValidatingOrderNumberStatus(driver, XLTestDataOS, Ordernum, oPSelFWOS);
			ordercreate.ClickClosebtn1(driver, element, oPSelFWOS);
			ordercreate.ClickClosebtn2(driver, element, oPSelFWOS);
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e);
			oPSelFWOS.reportStepDetails("Get Order Number UI", "Order Number is not displayed in the UI", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Order Search");
		}
	}

	// **********************************************!!!*********************************************************//
	@Test(priority = 5, groups = { "smoke", "cov", "reg" })
	public void OrderSearchwithnameAndPhone() throws Exception {
		try {
			i = 5;

			XLTestDataOS = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String TestCaseIDno = XLTestDataOS.get("TestCaseID").toString();
			String lable = TestCaseIDno + "_" + "Order Search" + "_" + XLTestDataOS.get("TestCaseTitle").toString();
			oPSelFWOS.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
			ordercreate.Clicksearchbutton(driver, element, oPSelFWOS);
			ordercreate.OrderSearch(driver, element, oPSelFWOS);
			gen.waitUntilElementIsPresent(driver, By.xpath("//*[@uid='txt_orderNo2']"));
			gen.HandlingErrorMessage(driver, oPSelFWOS);
			String PhoneNo1 = XLTestDataOS.get("BillToDayPhone").toString();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//*[@uid='txt_custPhone']/div[2]/div/div/input)[1]")).sendKeys(PhoneNo1);
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@uid='txt_custPhone']/div[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("Phone Number should be entered", PhoneNo1 + " is entered", "Pass");
			String FirstName2 = XLTestDataOS.get("BillToFirstName").toString();
			driver.findElement(By.xpath("((//*[@uid='txt_fName']/div)[2]/div/div/input)[1]")).sendKeys(FirstName2);
			Thread.sleep(500);
			driver.findElement(By.xpath("((//*[@uid='txt_fName']/div)[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("First Name should be entered", FirstName2 + " is entered as first name","Pass");
			ordercreate.ClickSearchButton(driver, element, oPSelFWOS);
			Thread.sleep(5000);
			gen.HandlingErrorMessage(driver, oPSelFWOS);
			ordercreate.SearchResults(driver, element, oPSelFWOS);
			gen.waitUntilElementIsPresent(driver, validation.conceptfromUI);
			String Ordernum = ordercreate.OrderNumberfromUI(driver, element, OrderStatusUI, oPSelFWOS);
			Thread.sleep(10000);
			validation.ValidatingOrderNumberStatus(driver, XLTestDataOS, Ordernum, oPSelFWOS);
			ordercreate.ClickClosebtn1(driver, element, oPSelFWOS);
			ordercreate.ClickClosebtn2(driver, element, oPSelFWOS);
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e);
			oPSelFWOS.reportStepDetails("Get Order Number UI", "Order Number is not displayed in the UI", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Order Search");
		}
	}

//******************************************!!!!!!!***************************************************//
		@Test(priority = 6, groups = { "smoke", "cov", "reg" })
public void OrderSearchwithGiftRegistryName() throws Exception {
		try {
			i = 6;

			XLTestDataOS = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String TestCaseIDno = XLTestDataOS.get("TestCaseID").toString();
			String lable = TestCaseIDno + "_" + "Order Search" + "_" + XLTestDataOS.get("TestCaseTitle").toString();
			oPSelFWOS.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
			ordercreate.ClickGiftRegistrySearch(driver, element, oPSelFWOS);
			ordercreate.OrderSearch(driver, element, oPSelFWOS);
			gen.waitUntilElementIsPresent(driver, By.xpath("//*[@uid='txt_fName']"));
			gen.HandlingErrorMessage(driver, oPSelFWOS);
			ordercreate.ConceptTypeSetforgift(driver, XLTestDataOS, oPSelFWOS);
			Thread.sleep(500);
			String FirstName2 = XLTestDataOS.get("BillToFirstName").toString();
			Thread.sleep(500);
			driver.findElement(By.xpath("((//*[@uid='txt_fName'])/div/div/div/input)[1]")).sendKeys(FirstName2);
			Thread.sleep(500);
			driver.findElement(By.xpath("((//*[@uid='txt_fName'])/div/div/div/input)[1]")).sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("First Name should be entered", FirstName2 + " is entered as first name",
					"Pass");
			String LastName = XLTestDataOS.get("BillToLastName").toString();
			driver.findElement(By.xpath("((//*[@uid='txt_lName'])/div/div/div/input)[1]")).sendKeys(LastName);
			Thread.sleep(500);
			driver.findElement(By.xpath("((//*[@uid='txt_lName'])/div/div/div/input)[1]")).sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("Last Name should be entered", LastName + " is entered as first name", "Pass");
			ordercreate.ClickSearchButton(driver, element, oPSelFWOS);
			gen.HandlingErrorMessage(driver, oPSelFWOS);
			ordercreate.SearchResults(driver, element, oPSelFWOS);
			validation.ValidatingOrdergiftregistryStatus(driver, element, XLTestDataOS, oPSelFWOS);
			ordercreate.ClickClosebtnforgift(driver, element, oPSelFWOS);
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e);
			oPSelFWOS.reportStepDetails("Get Order Number UI", "Order Number is not displayed in the UI", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Order Search");
		}
	}

//*******************************************!!!!!!!******************************************//	
	@Test(priority = 7, groups = { "smoke", "cov", "reg" })
	public void OrderSearchwithGiftRegistryNameandState() throws Exception {
		try {
			i = 7;

			XLTestDataOS = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String TestCaseIDno = XLTestDataOS.get("TestCaseID").toString();
			String lable = TestCaseIDno + "_" + "Order Search" + "_" + XLTestDataOS.get("TestCaseTitle").toString();
			oPSelFWOS.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
			ordercreate.ClickGiftRegistrySearch(driver, element, oPSelFWOS);
			ordercreate.OrderSearch(driver, element, oPSelFWOS);
			gen.waitUntilElementIsPresent(driver, By.xpath("//*[@uid='txt_fName']"));
			gen.HandlingErrorMessage(driver, oPSelFWOS);
			ordercreate.ConceptTypeSetforgift(driver, XLTestDataOS, oPSelFWOS);
			Thread.sleep(500);
			String FirstName2 = XLTestDataOS.get("BillToFirstName").toString();
			Thread.sleep(500);
			driver.findElement(By.xpath("((//*[@uid='txt_fName'])/div/div/div/input)[1]")).sendKeys(FirstName2);
			Thread.sleep(500);
			driver.findElement(By.xpath("((//*[@uid='txt_fName'])/div/div/div/input)[1]")).sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("First Name should be entered", FirstName2 + " is entered as first name","Pass");
			String LastName = XLTestDataOS.get("BillToLastName").toString();
			driver.findElement(By.xpath("((//*[@uid='txt_lName'])/div/div/div/input)[1]")).sendKeys(LastName);
			Thread.sleep(500);
			driver.findElement(By.xpath("((//*[@uid='txt_lName'])/div/div/div/input)[1]")).sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("Last Name should be entered", LastName + " is entered as first name", "Pass");
			ordercreate.SelectState(driver, XLTestDataOS, oPSelFWOS);
			Thread.sleep(500);
			ordercreate.ClickSearchButton(driver, element, oPSelFWOS);
			gen.HandlingErrorMessage(driver, oPSelFWOS);
			ordercreate.SearchResults(driver, element, oPSelFWOS);
			validation.ValidatingOrdergiftregistryStatus(driver, element, XLTestDataOS, oPSelFWOS);
			ordercreate.ClickClosebtnforgift(driver, element, oPSelFWOS);
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e);
			oPSelFWOS.reportStepDetails("Get Order Number UI", "Order Number is not displayed in the UI", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Order Search");
		}
	}

//******************************************!!!!*****************************************************//
	// dependsOnMethods= "OrderSearchwithGiftRegistryNameandState",
	@Test(priority = 8, groups = { "smoke", "cov", "reg" })
	public void SearchwithGiftCard() throws Exception {
		try {
			i = 8;

			XLTestDataOS = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String TestCaseIDno = XLTestDataOS.get("TestCaseID").toString();
			String lable = TestCaseIDno + "_" + "Order Search" + "_" + XLTestDataOS.get("TestCaseTitle").toString();
			oPSelFWOS.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
			ordercreate.ClickBalanceInquiry(driver, element, oPSelFWOS);
			ordercreate.BalanceInquirylink(driver, element, oPSelFWOS);
			Thread.sleep(20000);
			gen.HandlingErrorMessage(driver, oPSelFWOS);
			String Paymenttype = XLTestDataOS.get("PaymentType").toString();
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[1]")).click();
			driver.findElement(By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[2]")).sendKeys(Paymenttype);
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[2]")).sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("Payment method should be select",
					Paymenttype + " Payment method selected successfully", "Pass");
			String Cardno = XLTestDataOS.get("CardNumber").toString();
			driver.findElement(By.xpath("(//*[@uid='extn_CardNumber']/div[2]/div/div/input)[1]")).sendKeys(Cardno);
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@uid='extn_CardNumber']/div[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("Card number should be entered", Cardno + " Card number entered successfully","Pass");
			String pinNo = XLTestDataOS.get("GiftCardInformation").toString();
			driver.findElement(By.xpath("(//*[@uid='extn_GCPin']/div[2]/div/div/input)[1]")).sendKeys(pinNo);
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@uid='extn_GCPin']/div[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("Pin number should be entered", pinNo + " Pin number entered successfully","Pass");
			driver.findElement(By.xpath("//*[@uid='extn_btnGetFunds']/span/span/span[3]")).click();
			Thread.sleep(1000);
			oPSelFWOS.reportStepDetails("GetFund button should be clickable", "GetFund button clicked successfully","Pass");
			validation.ValidatingBalanceInquiryStatus(driver, element, oPSelFWOS);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@uid='extn_btnCancel']/span/span")).click();
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
			oPSelFWOS.reportStepDetails("Get Order Number UI", "Order Number is not displayed in the UI", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Order Search");
		}
	}
//****************************************!!!!!!!!!!!!**************************************************//
	@Test(priority = 9, groups = { "smoke", "cov", "reg" })
	public void OrderSearchwithNameemailPhone() throws Exception {
		try {
			i = 9;

			XLTestDataOS = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String TestCaseIDno = XLTestDataOS.get("TestCaseID").toString();
			String lable = TestCaseIDno + "_" + "Order Search" + "_" + XLTestDataOS.get("TestCaseTitle").toString();
			oPSelFWOS.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
			ordercreate.Clicksearchbutton(driver, element, oPSelFWOS);
			ordercreate.OrderSearch(driver, element, oPSelFWOS);
			gen.waitUntilElementIsPresent(driver, By.xpath("//*[@uid='txt_orderNo2']"));
			gen.HandlingErrorMessage(driver, oPSelFWOS);
			String FirstName = XLTestDataOS.get("BillToFirstName").toString();
			Thread.sleep(500);
			gen.waitnclickElement(driver, By.xpath("((//*[@uid='txt_fName']/div)[2]/div/div/input)[1]"), "Search button ", oPSelFWOS);
			driver.findElement(By.xpath("((//*[@uid='txt_fName']/div)[2]/div/div/input)[1]")).sendKeys(FirstName);
			driver.findElement(By.xpath("((//*[@uid='txt_fName']/div)[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
			oPSelFWOS.reportStepDetails("FirstName should be entered", FirstName + " is entered as first name", "Pass");
			String Emailfromtestdata = XLTestDataOS.get("BillToEmailAddress").toString();
			Thread.sleep(500);
			driver.findElement(By.xpath(
					"(//*[@uid='SST_GenericContentContainer']/div/div/table/tbody/tr/td/div/div[3]/div[2]/div/div/input)[1]"))
					.sendKeys(Emailfromtestdata);
			Thread.sleep(500);
			driver.findElement(By.xpath(
					"(//*[@uid='SST_GenericContentContainer']/div/div/table/tbody/tr/td/div/div[3]/div[2]/div/div/input)[1]"))
					.sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("Email should be entered", Emailfromtestdata + " is entered as mail ID",
					"Pass");
			String PhoneNo = XLTestDataOS.get("BillToDayPhone").toString();
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@uid='txt_custPhone']/div[2]/div/div/input)[1]")).sendKeys(PhoneNo);
			driver.findElement(By.xpath("(//*[@uid='txt_custPhone']/div[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
			Thread.sleep(500);
			oPSelFWOS.reportStepDetails("Phone Number should be entered", PhoneNo + " is entered", "Pass");
			Thread.sleep(1000);
			ordercreate.ClickSearchButton(driver, element, oPSelFWOS);
			gen.HandlingErrorMessage(driver, oPSelFWOS);
			ordercreate.SearchResults(driver, element, oPSelFWOS);
			gen.waitUntilElementIsPresent(driver, validation.conceptfromUI);
			String Ordernum = ordercreate.OrderNumberfromUI(driver, element, OrderStatusUI, oPSelFWOS);
			Thread.sleep(2000);
			validation.ValidatingOrderNumberStatus(driver, XLTestDataOS, Ordernum, oPSelFWOS);
			Thread.sleep(2000);
			ordercreate.ClickClosebtn1(driver, element, oPSelFWOS);
			ordercreate.ClickClosebtn2(driver, element, oPSelFWOS);
		} catch (Exception e) {
			System.out.println(e);
			oPSelFWOS.reportStepDetails("Get Order Number UI", "Order Number is not displayed in the UI", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Order Search");
		}
	}
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
		Thread.sleep(100);
		driver.quit();
		oPSelFWOS.stopReport();
	}

	@AfterMethod(alwaysRun = true)
	public void oTest(ITestResult result) throws InterruptedException, IOException {
		oPSelFWOS.stopReport();
		em.afterSuite();
		if (result.getStatus() == ITestResult.FAILURE) {
			Thread.sleep(100);
			driver.close();
			Thread.sleep(100);
			driver.quit();
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
