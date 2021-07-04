package Scripts;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
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
import Library.KibanaSteringValidation;
import Library.LoginPage1;
import Library.SearchOrderPage;

public class BalanceInquiry {
	
    public static ProlificsSeleniumAPI oPSelFW = null;
	
    private static Excel_Reader excelReader;
	public static int i = 1;
	int PassCount = 0, FailCount = 0;
	public Connection OraConn;
	private HashMap<String, String> XLTestDataBI = new HashMap<String, String>();
	boolean ItemDetailsDisplayed = false;
	public static WebDriver driver;
	Generic1 gen = new Generic1();
	CreateOrderPage1 ordercreate = new CreateOrderPage1();
	DbValidations validation = new DbValidations();
	SearchOrderPage orderPage = new SearchOrderPage();
	
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
	static boolean scriptSucful = false; 
	KibanaSteringValidation kibana = new KibanaSteringValidation();
	
	/*@DataProvider(name = "createOrderProvider", parallel = false)
	public static Object[][] createOrderProvider() throws FileNotFoundException, IOException {
		excelReader = new Excel_Reader(System.getProperty("InputFileName"));
		return asTwoDimensionalArray();
	}

	private static Object[][] asTwoDimensionalArray() throws FileNotFoundException, IOException {
		String TestDataPath = System.getProperty("InputFileName");
		excelReader = new Excel_Reader(TestDataPath);
		excelReader.cFileNameWithPath = TestDataPath;
		excelReader.cSheetName = "TestData";
		excelReader.cTcID = "TestCaseID";
		excelReader.cTcValue = "1";
		Object[][] results = new Object[100][1];
		HashMap<String, String> XLTestData = new HashMap<String, String>();
		int RowCount = excelReader.GetRowCount("TestData");
		System.out.println("Number of Rows in Excel " + RowCount);
		for (int i = 1; i <= RowCount; i++) {
			XLTestData = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			results[i] = new Object[] { XLTestData };
		}
		return results;
	}*/
	@BeforeMethod(alwaysRun = true)
    public void launchBrowser() throws InterruptedException, IOException
    {
          if(scriptSucful == false)
          {
                System.setProperty("webdriver.chrome.driver",
                      System.getProperty("user.dir") + "\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
                String Env = System.getProperty("Environment");
               if(Env.contains("STST2"))
                    loginSuccess = gen.launchURL(driver, gen.getPropertyValue("STST2URL"), oPSelFW);
              else if (Env.contains("STST"))
                          loginSuccess = gen.launchURL(driver, gen.getPropertyValue("STSTURL"), oPSelFW);
              else if(Env.contains("EQA3"))
                          loginSuccess = gen.launchURL(driver, gen.getPropertyValue("EQA3URL"), oPSelFW);
          LoginPage1 login = new LoginPage1();
          login.LoginToWCC(driver, System.getProperty("UserID"), System.getProperty("Password"), oPSelFW);
          }
    }
	@BeforeClass(alwaysRun = true)
	public void beforeTest1() throws Exception {
		String Env = System.getProperty("Environment");
		String TestDataPath = System.getProperty("user.dir") + "\\Data\\"+ Env + "_BalanceInquiry.xlsx";
		excelReader = new Excel_Reader(TestDataPath);
		excelReader.cFileNameWithPath = TestDataPath;
		excelReader.cSheetName = "TestData";
		excelReader.cTcID = "TestCaseID";
		excelReader.cTcValue = "1";
		
		oPSelFW = new ProlificsSeleniumAPI("WCC", "Balance Inquiry", "Chrome", "Windows 7", "STST", "Automation",
				"TestAutomation", "Regression");
		
		oPSelFW.sAutomationPath = System.getProperty("user.dir") + "\\ProlificsReports\\";
		oPSelFW.reportPath(System.getProperty("user.dir") + "\\target\\");
		oPSelFW.startReport();	
	}
	
	@Test(priority=1, groups={ "smoke", "cov", "reg" })
	public void BalanceInquiry() throws Exception
	{	
		try {
		i = 1;
		
		XLTestDataBI = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
		String TestCaseIDno = XLTestDataBI.get("TestCaseID").toString();
		String lable = TestCaseIDno + "_" +"Order Search" + "_" + XLTestDataBI.get("TestCaseTitle").toString();
		oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
		Thread.sleep(10000);
		ordercreate.ClickBalanceInquiry(driver, element, oPSelFW);
		ordercreate.BalanceInquirylink(driver, element, oPSelFW);
		Thread.sleep(5000);
		orderPage.BalanceSearch(driver,XLTestDataBI,oPSelFW);
		//Kibana validation
		kibana.validateKibanaBalanceInquiry(XLTestDataBI,oPSelFW);
	}
		catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if exception is displayed", "Exception is displayed in Balance Inquiry", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Balance Inquiry");
			
		}
	}
	//******************************!!!!!!!!!!!*********************************************************//
	//8767878996014 for REWARD/WELCOME
	@Test(priority=2,  groups = { "smoke", "cov", "reg" })
	public void BirthdayandWelcome() throws Exception
	{	
		try {
		i = 2;
		XLTestDataBI = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
		String TestCaseIDno = XLTestDataBI.get("TestCaseID").toString();
		String lable = TestCaseIDno + "_" +"Order Search" + "_" + XLTestDataBI.get("TestCaseTitle").toString();
		oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
		Thread.sleep(5000);
		ordercreate.ClickBalanceInquiry(driver, element, oPSelFW);
		ordercreate.BalanceInquirylink(driver, element, oPSelFW);
		Thread.sleep(20000);
		
		orderPage.BalanceSearch1(driver,XLTestDataBI,oPSelFW);
		//Kibana validation
		kibana.validateKibanaBalanceInquiry(XLTestDataBI,oPSelFW);
	}
		
		catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if exception is displayed", "Exception is displayed in Balance Inquiry", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Balance Inquiry");
		}
	}
//**************************************!!!!!!!!!!!************************************************************//
	//999210217001 for REWARD/BIRTHDAY
	
	@Test(priority=3,  groups = { "smoke", "cov", "reg" })
	public void Rewardandbirthday() throws Exception
	{	
		try {
		i = 3;
		XLTestDataBI = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
		String TestCaseIDno = XLTestDataBI.get("TestCaseID").toString();
		String lable = TestCaseIDno + "_" +"Order Search" + "_" + XLTestDataBI.get("TestCaseTitle").toString();
		oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
		Thread.sleep(5000);
					ordercreate.ClickBalanceInquiry(driver, element, oPSelFW);
					ordercreate.BalanceInquirylink(driver, element, oPSelFW);
					Thread.sleep(5000);
					//Kibana validation
					orderPage.BalanceSearch2(driver,XLTestDataBI,oPSelFW);
					//Kibana validation
					kibana.validateKibanaBalanceInquiry(XLTestDataBI,oPSelFW);
				}
		catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if exception is displayed", "Exception is displayed in Balance Inquiry", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Balance Inquiry");
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException
	{
		driver.close();
		Thread.sleep(100);
		driver.quit();
		oPSelFW.stopReport();
	}
	@AfterMethod(alwaysRun = true)
	 public void oTest(ITestResult result) throws InterruptedException, IOException{
		oPSelFW.stopReport();
		em.afterSuite();
		if(result.getStatus() == ITestResult.FAILURE)
	    {
			driver.close();
			Thread.sleep(100);
			driver.quit();
			Thread.sleep(100);
			scriptSucful = false; 
	    }
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			scriptSucful = true; 
		}
	    if(result.getStatus() == ITestResult.SKIP)
	    {
	    	  System.out.println("Failure");
	    }
	    }
	 }
