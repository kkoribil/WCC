package Scripts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.testng.ITestResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.prolifics.ProlificsSeleniumAPI;
import FrameWork.Excel_Reader;
import Library.CreateOrderPage;
import Library.CreateOrderPage1;
import Library.DbValidationsAppeasement;
import Library.EmailReport;
import Library.Generic1;
import Library.LoginPage1;
import Library.XLStoCSVConverter;

public class WCC_Order_Appeasements  {

	public static Excel_Reader excelReader;
	//public static int i = 4;
	int PassCount = 0, FailCount = 0;
	static boolean scriptSucful = false;
	static boolean loginSuccess;
	
	public Connection OraConn;
	boolean ItemDetailsDisplayed = false;
	private HashMap<String, String> XLTestDataAP = new HashMap<String, String>();
	public static WebDriver driver;
    DbValidationsAppeasement dbvalidation = new DbValidationsAppeasement();
	ProlificsSeleniumAPI oPSelFW = null;
	Generic1 gen = new Generic1();
	LoginPage1 login = new LoginPage1();
	EmailReport em = new EmailReport();
	CreateOrderPage1 ordercreate = new CreateOrderPage1();
	CreateOrderPage ordercreate1 = new CreateOrderPage();
	WebElement element;
	String GenItemNumber;

	
	String OrderCreatedSucc;
	boolean errorDisplayed = false;
	boolean ItemExistsInWCC = false;
	String errorMessage;
	static String OutputFileName = System.getProperty("user.dir") + "\\target\\CreateOrder";
	static String Prop = System.getProperty("user.dir") + "\\target\\ReturnOrders.properties";
	int RowNum = 1;
	static boolean AppLaunched;	
	
	
	
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
		String TestDataPath = System.getProperty("user.dir") + "\\Data\\EQA3_WCC_OrderAppeasements.xlsx";
		excelReader = new Excel_Reader(TestDataPath);
		excelReader.cFileNameWithPath = TestDataPath;
		excelReader.cSheetName = "TestData";
		excelReader.cTcID = "TestCaseID";
		excelReader.cTcValue = "1";
		File myFile = new File(TestDataPath);
		XLStoCSVConverter xls = new XLStoCSVConverter();
		StringBuffer sbf = new StringBuffer();
		sbf = xls.convertSelectedSheetInXLXSFileToCSV(myFile, 0);
		Path path = Paths.get(Prop);

		// deleteIfExists File
		try {
			Files.deleteIfExists(path);
		} catch (Exception e) {
			System.out.println("File Not Exists");
		}

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy hh mm ss");
		String time = dateFormat.format(now);
		time = time.replace(" ", "");
		OutputFileName = OutputFileName + time + ".csv";
		BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(OutputFileName)));
		// write contents of StringBuffer to a file
		bwr.write(sbf.toString());
		// flush the stream
		bwr.flush();
		// close the stream
		bwr.close();		
		oPSelFW = new ProlificsSeleniumAPI("WCC", "ORDER APPEASEMENT", "Chrome", "Windows 7", "STST", "Automation",
				"TestAutomation", "Regression");
		oPSelFW.sAutomationPath = System.getProperty("user.dir") + "\\ProlificsReports\\";
		oPSelFW.reportPath(System.getProperty("user.dir") + "\\target\\");
		oPSelFW.startReport();
		
		
	}
	
	
	@Test(priority = 1,groups = { "smoke", "cov", "reg" })
	public void TestCase1() throws FileNotFoundException, IOException  
	{
	  int i = 9;
	  XLTestDataAP = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
	 // String getOrderNumber=XLTestData.get("orderNumber").toString();
	  String lable2 = "Order Appeasement" + "_" + XLTestDataAP.get("TestCaseTitle").toString();
	  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
      try
      {
    	  
    	  //create order for appeasement
    	 dbvalidation.CreateOrderForAppeasement(XLTestDataAP, driver, oPSelFW);
    	
		//calling appease method
		//dbvalidation.AppeaseCustemer(driver, XLTestDataAP, oPSelFW);
    	 dbvalidation.WaiveChargesOrderLine1(driver, XLTestDataAP, oPSelFW);
		  
		//closing of current window
		String close="(//span[@class='dijitInline dijitTabCloseButton dijitTabCloseIcon'])[2]";

		driver.findElement(By.xpath(close)).click();
		Thread.sleep(5000);
		 
		//validating credit memo
		// dbvalidation.ValidateCreditMemo(driver, XLTestData, oPSelFW, Order);

		
	 }
      catch(Exception e)
      {
    System.out.println(e);
      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
      }
	 }
	
	
	@Test(priority = 2,groups = { "smoke", "cov", "reg" })
	//@Test(priority = 1,enabled=true,alwaysRun = true)
	public void TestCase2()throws FileNotFoundException, IOException, InterruptedException 
	//public void WCC_Appeasement() throws FileNotFoundException, IOException, InterruptedException
	{
		InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
		Properties prop = new Properties();
		prop.load(input);
        int  i = 4;
		//XLTestData = new HashMap<String, String>();
        XLTestDataAP = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
		//String getOrderNumber=XLTestData.get("orderNumber").toString();
		String lable = "Order Appeasement" + "_" + XLTestDataAP.get("TestCaseTitle").toString();
		oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable, "Pass");
		try {
			oPSelFW.reportStepDetails("Home page should be clicked " ,"Home page is clicked successfully " , "Pass");
			Thread.sleep(5000);
		 //create order for appeasement
	    dbvalidation.CreateOrderForAppeasement(XLTestDataAP, driver, oPSelFW);

	    // Appeasing and Selecting the Waive charges option
		//dbvalidation.WaiveCharges(driver, XLTestDataAP, oPSelFW);
	    dbvalidation.WaiveChargesOrderLine1(driver, XLTestDataAP, oPSelFW);
							
		//validating the previous appeasements
		dbvalidation.ValidatingPreviousAppeasements(driver, XLTestDataAP, oPSelFW);
		//closing of current window
		String close="(//span[@class='dijitInline dijitTabCloseButton dijitTabCloseIcon'])[2]";

		driver.findElement(By.xpath(close)).click();
		Thread.sleep(5000);
							
		}catch(Exception e)
	      {
		      System.out.println(e);
		      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
		      }
	}
	
		
		
		
		@Test(priority = 3,groups = { "smoke", "cov", "reg" })
		public void TestCase3() throws IOException
		{
		 int i = 5;
		//XLTestData = new HashMap<String, String>();
		 XLTestDataAP = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
		//String getOrderNumber=XLTestData.get("orderNumber").toString();

		String lable1 = "Order Appeasement" + "_" + XLTestDataAP.get("TestCaseTitle").toString();
		oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable1, "Pass");
		try {
			
			
			oPSelFW.reportStepDetails("Home page should be clicked " ,"Home page is clicked successfully " , "Pass");
			Thread.sleep(5000);
			 //create order for appeasement
	    	 dbvalidation.CreateOrderForAppeasement(XLTestDataAP, driver, oPSelFW);
		
			// Appeasing and Selecting the Waive charges oreder line option
			//dbvalidation.WaiveChargesOrderLine(driver, XLTestDataAP, oPSelFW);
	    	 dbvalidation.WaiveChargesOrderLine1(driver, XLTestDataAP, oPSelFW);
			
			//validating the previous appeasements
			dbvalidation.ValidatingPreviousAppeasements(driver, XLTestDataAP, oPSelFW);
			//closing of current window
			String close="(//span[@class='dijitInline dijitTabCloseButton dijitTabCloseIcon'])[2]";

			driver.findElement(By.xpath(close)).click();
			Thread.sleep(5000);
		}catch(Exception e)
	      {
		      System.out.println(e);
		      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
		      }
		
		//Test case  for cancellation
		System.out.println( "" );
		}
		
		
	@Test(priority = 4, groups = { "smoke", "cov", "reg" })
	public void TestCase4() throws FileNotFoundException, IOException  
	{
	  int i = 8;
	  XLTestDataAP = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
	  String getOrderNumber=XLTestDataAP.get("orderNumber").toString();
	  String lable2 = "Order Appeasement" + "_" + XLTestDataAP.get("TestCaseTitle").toString();
	  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
      try
      {
    	  
    	  oPSelFW.reportStepDetails("Home page should be clicked " ,"Home page is clicked successfully " , "Pass");
    	  Thread.sleep(5000);
    	  //create order for appeasement
     	 dbvalidation.CreateOrderForAppeasement(XLTestDataAP, driver, oPSelFW);
    	 
    	//calling appease method
		//dbvalidation.AppeaseCustemer(driver, XLTestDataAP, oPSelFW);
     	dbvalidation.WaiveChargesOrderLine1(driver, XLTestDataAP, oPSelFW);
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
	@Test(priority = 5,groups = { "smoke", "cov", "reg" })
	public void TestCase5() throws FileNotFoundException, IOException  
	{
	  int i = 24;
	  XLTestDataAP = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
	  String lable2 = "Order Appeasement" + "_" + XLTestDataAP.get("TestCaseTitle").toString();
	  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
      try
      {
    	  oPSelFW.reportStepDetails("Home page should be clicked " ,"Home page is clicked successfully " , "Pass");
    	  Thread.sleep(5000);
    	  String getOrderNumber=dbvalidation.CreateOrderForAppeasementTax(XLTestDataAP, driver, oPSelFW);
    	  
    	  //verify the charge type from DB
    	  dbvalidation.verifyAuthorization(oPSelFW, getOrderNumber, XLTestDataAP, driver);
    	  
    	  // Appeasing and Selecting the Waive charges option
  			dbvalidation.WaiveChargesForOrder(driver, XLTestDataAP, oPSelFW);
  			//closing of current window
  			String close="(//span[@class='dijitInline dijitTabCloseButton dijitTabCloseIcon'])[2]";

  				driver.findElement(By.xpath(close)).click();
    	  
    	  
	 }
      catch(Exception e)
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


