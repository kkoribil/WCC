package Scripts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
import Library.DbValidationsAppeasement;
import Library.EmailReport;
import Library.Generic1;
import Library.KibanaSteringValidation;
import Library.LoginPage1;
import Library.SearchOrderPage;

public class Appeasement_Order_Cancellation {
	
    public static ProlificsSeleniumAPI oPSelFW = null;
	
    private static Excel_Reader excelReader;
	public static int i = 1;
	int PassCount = 0, FailCount = 0;
	public Connection OraConn;
	private HashMap<String, String> XLTestDataAC = new HashMap<String, String>();
	boolean ItemDetailsDisplayed = false;
	public static WebDriver driver;
	Generic1 gen = new Generic1();
	CreateOrderPage1 ordercreate = new CreateOrderPage1();
	DbValidations validation = new DbValidations();
	SearchOrderPage orderPage = new SearchOrderPage();
	DbValidationsAppeasement dbvalidation = new DbValidationsAppeasement();
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
		String TestDataPath = System.getProperty("user.dir") + "\\Data\\STST_WCC_OrderAppeasements2.xlsx";
		excelReader = new Excel_Reader(TestDataPath);
		excelReader.cFileNameWithPath = TestDataPath;
		excelReader.cSheetName = "TestData";
		excelReader.cTcID = "TestCaseID";
		excelReader.cTcValue = "1";
		
		oPSelFW = new ProlificsSeleniumAPI("WCC", "APPEASEMENT_CANCELLATION_OF_ORDERS", "Chrome", "Windows 7", "STST", "Automation",
				"TestAutomation", "Regression");
		
		
		oPSelFW.sAutomationPath = System.getProperty("user.dir") + "\\ProlificsReports\\";
		oPSelFW.reportPath(System.getProperty("user.dir") + "\\target\\");
		oPSelFW.startReport();	
	}
	
	@Test(priority = 1,groups = { "smoke", "cov", "reg" })
	public void TestCase1() throws FileNotFoundException, IOException  
	{
	  int i = 6;
	
	  XLTestDataAC = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
	String getOrderNumber=XLTestDataAC.get("orderNumber").toString();
	String lable2 = "Order Appeasement" + "_" + XLTestDataAC.get("TestCaseTitle").toString();
	oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
	try {
		
		//Execute query from DB
    	 // String Order = dbvalidation.getOrderNumberFromDB(driver, XLTestData, oPSelFW);
		 //create order for appeasement
   	    dbvalidation.CreateOrderForAppeasement(XLTestDataAC, driver, oPSelFW);
		
   	    //partially cancelling the order
		dbvalidation.CancelOrder(driver, XLTestDataAC, oPSelFW);
		
		//closing of current window
		Thread.sleep(5000);
		
		String close="(//span[@class='dijitInline dijitTabCloseButton dijitTabCloseIcon'])[2]";

		driver.findElement(By.xpath(close)).click();
		Thread.sleep(5000);
			
	}
	catch(Exception e)
    {
    System.out.println(e);
    oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
    }	
	
	}
	
	//******************************!!!!!!!!!!!*********************************************************/
	@Test(priority = 2,groups = { "smoke", "cov", "reg" })
	public void TestCase2() throws IOException  
	{
		int i = 7;
		//XLTestData = new HashMap<String, String>();
		XLTestDataAC = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
		String getOrderNumber=XLTestDataAC.get("orderNumber").toString();
		String lable3 = "Order Appeasement" + "_" + XLTestDataAC.get("TestCaseTitle").toString();
		oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable3, "Pass");
		try {
			
			//Execute query from DB
	    	  //String Order = dbvalidation.getOrderNumberFromDB(driver, XLTestData, oPSelFW);
			//create order for appeasement
			dbvalidation.CreateOrderForAppeasement(XLTestDataAC, driver, oPSelFW);
			
			//partially cancelling the order
			dbvalidation.FullCancelOrder(driver, XLTestDataAC, oPSelFW);
			
			//closing of current window
			Thread.sleep(5000);
			String close="(//span[@class='dijitInline dijitTabCloseButton dijitTabCloseIcon'])[2]";

			driver.findElement(By.xpath(close)).click();
			Thread.sleep(5000);
			
		}
		catch(Exception e)
	      {
	      System.out.println(e);
	      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
	      }
	}
		
//**************************************!!!!!!!!!!!************************************************************/
	
	@Test(priority = 3,groups = { "smoke", "cov", "reg" })
	public void TestCase3() throws IOException
	{
	int i = 1;
	XLTestDataAC = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
	String label5 = "Order Appeasement" + "_" + XLTestDataAC.get("TestCaseTitle").toString();
	oPSelFW.reportStepDetails("Start the New Steps for TestCase", label5, "Pass");
	try {
		
				//test case for order header level appeasement
				dbvalidation.CreateOrderForPLCC(XLTestDataAC, driver, oPSelFW);
				
				//calling appease method
				dbvalidation.AppeaseCustemer(driver, XLTestDataAC, oPSelFW);
				Thread.sleep(5000);
				//closing of current window
				String close="(//span[@class='dijitInline dijitTabCloseButton dijitTabCloseIcon'])[2]";

				driver.findElement(By.xpath(close)).click();
				Thread.sleep(5000);
				
			}
			catch(Exception e)
			{
				System.out.println(e);
				oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			}
		}
	
	//*******************************************************************************************************
	@Test(priority = 4, groups = { "smoke", "cov", "reg" })
	public void TestCase4() throws IOException
	{
	int i = 2;

	//XLTestData = new HashMap<String, String>();
	XLTestDataAC = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
	String getOrderNumber=XLTestDataAC.get("orderNumber").toString();

	String label4 = "Order Appeasement" + "_" + XLTestDataAC.get("TestCaseTitle").toString();
	oPSelFW.reportStepDetails("Start the New Steps for TestCase", label4, "Pass");
	try {

	//searching with the order number
	dbvalidation.SearchWithOrderNumber(driver, XLTestDataAC, oPSelFW, getOrderNumber);

	//calling the multiple appeasement method
	dbvalidation.MultipleAppeasement(driver, XLTestDataAC, oPSelFW);

	}catch(Exception e)
    {
    System.out.println(e);
    oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
    }

}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException, IOException
	{
		driver.close();
		Thread.sleep(100);
		driver.quit();
		oPSelFW.stopReport();
		em.afterSuite();
	}
	@AfterMethod(alwaysRun = true)
	 public void oTest(ITestResult result) throws InterruptedException, IOException{
		em.afterSuite();
		oPSelFW.stopReport();
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
