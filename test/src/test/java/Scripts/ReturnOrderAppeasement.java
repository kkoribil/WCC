package Scripts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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

import FrameWork.Excel_Reader;
import Library.CreateOrderPage;
import Library.CreateOrderPage1;
import Library.DbValidationsAppeasement;
import Library.EmailReport;
import Library.Generic1;
import Library.LoginPage1;
import Library.XLStoCSVConverter;

public class ReturnOrderAppeasement {
	
	public static Excel_Reader excelReader;
	//public static int i = 4;
	int PassCount = 0, FailCount = 0;
	static boolean scriptSucful = false;
	static boolean loginSuccess;
	public Connection OraConn;
	boolean ItemDetailsDisplayed = false;
	private HashMap<String, String> XLTestDataRO = new HashMap<String, String>();
	public static WebDriver driver;
	//public  static ResultSet rs1;
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
		String TestDataPath = System.getProperty("user.dir") + "\\Data\\STST_WCC_OrderAppeasements2.xlsx";
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

		BufferedWriter bwr1 = new BufferedWriter(new FileWriter(new File(Prop)));
		
		oPSelFW = new ProlificsSeleniumAPI("WCC", "RETURN ORDERS APPEASEMENT", "Chrome", "Windows 7", "STST", "Automation",
			"TestAutomation", "Regression");
		oPSelFW.sAutomationPath = System.getProperty("user.dir") + "\\ProlificsReports\\";
		oPSelFW.reportPath(System.getProperty("user.dir") + "\\target\\");
		oPSelFW.startReport();
	}
	
	
	@Test(priority = 1,groups = { "smoke", "cov", "reg" })
	public void TestCase1() throws FileNotFoundException, IOException  
	{
	  int i = 10;
	  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
	  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
	  String lable1 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
	  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable1, "Pass");
      try
      {
    	  
    	//Execute query from DB
    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
    	  
    	//validating return order summary screen
    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
    	 ResultSet rs2= dbvalidation.queryForReturnOrder(oPSelFW, getOrderNumber, XLTestDataRO, driver);
    	  
    	  //validating return order summary screen
    	  dbvalidation.validateReturnOrderSummaryscreen(rs2,oPSelFW, getOrderNumber, XLTestDataRO,driver); 

    	   //validating payment inquiry screen
    	  dbvalidation.validatePaymentInquiryscreenForTC1(oPSelFW, getOrderNumber, XLTestDataRO, driver);
    	  
    	  //closing the window
    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
    	  
    	 }
      catch(Exception e) {
      
      System.out.println(e);
      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
      }
	}
//*******************************************************************************************************
	@Test(priority = 2,groups = { "smoke", "cov", "reg" })
	public void TestCase2() throws FileNotFoundException, IOException  
	{
	  int i = 11;
	  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
	  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
	  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
	  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
      try
      {
    	  
    	//Execute query from DB
    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
    	  
    	//validating return order summary screen
    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
    	  
    	  //validating payment inquiry screen
    	 dbvalidation.validatePaymentInquiryscreenForTC2(oPSelFW, getOrderNumber, XLTestDataRO, driver);
    	  
    	  //closing the window
    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
    	  
	 }
      catch(Exception e)
      {
      System.out.println(e);
      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
      }
	}
//****************************************************************************************************	
	@Test(priority = 3,groups = { "smoke", "cov", "reg" })
	public void TestCase3() throws FileNotFoundException, IOException  
	{
	  int i = 12;
	  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
	  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
	  String lable3 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
	  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable3, "Pass");
      try
      {
    	  
    	//Execute query from DB
    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
    	  
    	//validating return order summary screen
    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
    	  
    	  //validating payment inquiry screen
    	  dbvalidation.validatePaymentInquiryscreenForReplacement(oPSelFW, getOrderNumber, XLTestDataRO, driver);
    	  
    	  //closing the window
    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
    	  
    	  }
      catch(Exception e)
      {
      System.out.println(e);
      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
      }
	}
//****************************************************************************************************	
	
	@Test(priority = 4,groups = { "smoke", "cov", "reg" })
	public void TestCase4() throws FileNotFoundException, IOException  
	{
	  int i = 13;
	  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
	  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
	  String lable4 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
	  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable4, "Pass");
      try
      {
    	  
    	//Execute query from DB
    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
    	  
    	//validating return order summary screen
    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
    	  
    	  //validating payment inquiry screen
    	  dbvalidation.validatePaymentInquiryscreenForReplacement(oPSelFW, getOrderNumber, XLTestDataRO, driver);
    	  
    	  //closing the window
    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
    	  
     }
      catch(Exception e)
      {
      System.out.println(e);
      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
      }
	}

//**********************************************************************************************************
	@Test(priority = 5,groups = { "smoke", "cov", "reg" })
	public void TestCase5() throws FileNotFoundException, IOException  
	{
	  int i = 14;
	  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
	  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
	  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
	  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
      try
      {
    	  
    	//Execute query from DB
    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
    	  
    	//validating return order summary screen
    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
    	  
    	  //validating return order summary screen
    	 // dbvalidation.validateReturnOrderSummaryscreen(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
    	  
    	  //validating payment inquiry screen
    	 // dbvalidation.validatePaymentInquiryscreenForTC5(oPSelFW, getOrderNumber, XLTestDataRO, driver);
    	  
    	  //closing the window
    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);

    	  
     }
      catch(Exception e)
      {
      System.out.println(e);
      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
      }
	}
	
	//****************************************************************************************************************
	
	@Test(priority = 6,groups = { "smoke", "cov", "reg" })
	public void TestCase6() throws FileNotFoundException, IOException  
	{
	  int i = 15;
	  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
	  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
	  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
	  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
      try
      {
    	  
    	//Execute query from DB
    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
    	  
    	//validating return order summary screen
    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
    	  
    	  //validating return order summary screen
    	  dbvalidation.validateReturnOrderSummaryscreenFOROPTORO(oPSelFW, getOrderNumber, XLTestDataRO, driver);
    	  
    	  //validating payment inquiry screen
    	  dbvalidation.validatePaymentInquiryscreenForOPTORO(oPSelFW, getOrderNumber, XLTestDataRO, driver);
    	  
    	  //closing the window
    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);

    	  
     }
      catch(Exception e)
      {
      System.out.println(e);
      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
      }
	}
	
	//*****************************************************8
	@Test(priority = 7,groups = { "smoke", "cov", "reg" })
	public void TestCase7() throws FileNotFoundException, IOException  
	{
	  int i = 16;
	  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
	  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
	  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
	  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
      try
      {
    	  
    	//Execute query from DB
    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
    	  
    	//validating return order summary screen
    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
    	  
    	  //validating return order summary screen
    	  dbvalidation.validateReturnOrderSummaryscreenFOROPTORO(oPSelFW, getOrderNumber, XLTestDataRO, driver);
    	  
    	  //validating payment inquiry screen
    	  dbvalidation.validatePaymentInquiryscreenEcomReturn(oPSelFW, getOrderNumber, XLTestDataRO, driver);
    	  
    	  //closing the window
    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);

    	  
     }
      catch(Exception e)
      {
      System.out.println(e);
      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
      }
	}
	
	
	//**********************************************************************************************************
		@Test(priority = 8,groups = { "smoke", "cov", "reg" })
		public void TestCase8() throws FileNotFoundException, IOException  
		{
		  int i = 17;
		  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
		  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
		  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
		  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
	      try
	      {
	    	  
	    	//Execute query from DB
	    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
	    	  
	    	//validating return order summary screen
	    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
	    	  
	    	  //validating return order summary screen
	    	 // dbvalidation.validateReturnOrderSummaryscreen(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
	    	  
	    	  //validating payment inquiry screen
	    	  dbvalidation.validatePaymentInquiryscreenMultipleQty(oPSelFW, getOrderNumber, XLTestDataRO, driver);
	    	  
	    	  //closing the window
	    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);

	    	  
	     }
	      catch(Exception e)
	      {
	      System.out.println(e);
	      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
	      }
		}
		
		//**********************************************************************************************************
				@Test(priority = 9,groups = { "smoke", "cov", "reg" })
				public void TestCase9() throws FileNotFoundException, IOException  
				{
				  int i = 18;
				  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			      try
			      {
			    	  
			    	//Execute query from DB
			    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			    	  
			    	//validating return order summary screen
			    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			    	  
			    	  //validating return order summary screen
			    	 // dbvalidation.validateReturnOrderSummaryscreen(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
			    	  
			    	  //validating payment inquiry screen
			    	 // dbvalidation.validatePaymentInquiryscreenMultipleQty(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			    	  
			    	  //closing the window
			    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);

			    	  
			     }
			      catch(Exception e)
			      {
			      System.out.println(e);
			      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			      }
				}
	
				//**********************************************************************************************************
				@Test(priority = 10,groups = { "smoke", "cov", "reg" })
				public void TestCase10() throws FileNotFoundException, IOException  
				{
				  int i = 19;
				  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			      try
			      {
			    	  
			    	//Execute query from DB
			    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			    	  
			    	//validating return order summary screen
			    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			    	  
			    	  //validating return order summary screen
			    	//  dbvalidation.validateReturnOrderSummaryscreen(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
			    	  
			    	  //validating payment inquiry screen
			    	 // dbvalidation.validatePaymentInquiryscreenForTC10(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			    	  
			    	  //closing the window
			    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);

			    	  
			     }
			      catch(Exception e)
			      {
			      System.out.println(e);
			      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			      }
				}
				
				//**********************************************************************************************************
				@Test(priority = 11,groups = { "smoke", "cov", "reg" })
				public void TestCase11() throws FileNotFoundException, IOException  
				{
				  int i = 20;
				  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			      try
			      {
			    	  
			    	//Execute query from DB
			    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			    	  
			    	  //validating return order summary screen
			    	// dbvalidation.validateReturnOrderSummaryscreen(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
			    	  
			    	  //validating payment inquiry screen
			    	  dbvalidation.validatePaymentInquiryscreenForTC11(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			    	  
			    	  //closing the window
			    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);

			    	  
			     }
			      catch(Exception e)
			      {
			      System.out.println(e);
			      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			      }
				}
				
				@Test(priority = 12,groups = { "smoke", "cov", "reg" })
				public void TestCase12() throws FileNotFoundException, IOException  
				{
				  int i = 21;
				  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			      try
			      {
			    	  
			    	//Execute query from DB
			    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			    	  
			    	//validating return order summary screen
			    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			    	  
			    	  //validating return order summary screen
			    	  dbvalidation.validateReturnOrderSummaryscreenForTC12(oPSelFW, getOrderNumber, XLTestDataRO,driver);
			    	  
			    	  //validating payment inquiry screen
			    	  dbvalidation.validatePaymentInquiryscreenForTC12(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			    	  
			    	  //closing the window
			    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);

			    	  
			     }
			      catch(Exception e)
			      {
			      System.out.println(e);
			      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			      }
				}
				
				@Test(priority = 13,groups = { "smoke", "cov", "reg" })
				public void TestCase13() throws FileNotFoundException, IOException  
				{
				  int i = 22;
				  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			      try
			      {
			    	  
			    	//Execute query from DB
			    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			    	  
			    	  //validating return order summary screen
			    	 // dbvalidation.validateReturnOrderSummaryscreen(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
			    	  
			    	  //validating payment inquiry screen
			    	 // dbvalidation.validatePaymentInquiryscreenForTC13(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			    	  
			    	  //closing the window
			    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
			      }catch(Exception e)
			      {
			      System.out.println(e);
			      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			      }
			      }
				
//***************************************************************************************************
				@Test(priority = 14,groups = { "smoke", "cov", "reg" })
				public void TestCase14() throws FileNotFoundException, IOException  
				{
				  int i = 23;
				  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			      try
			      {
			    	  
			    	//Execute query from DB
			    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);					    	  
			    	  //validating return order summary screen
			    	  dbvalidation.validateReturnOrderSummaryscreenForTC14(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
			    	  
			    	  //validating payment inquiry screen
			    	  dbvalidation.validatePaymentInquiryscreenForTC14(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			    	  
			    	  //closing the window
			    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
			      }catch(Exception e)
			      {
			      System.out.println(e);
			      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			      }
			      }
				
//**********************************************************************************
				@Test(priority = 15,groups = { "smoke", "cov", "reg" })
				public void TestCase15() throws FileNotFoundException, IOException  
				{
				  int i = 24;
				  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			      try
			      {
			    	  
			    	//Execute query from DB
			    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			    	  
			    	//validating return order summary screen
			    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			    	  
			    	  //validating return order summary screen
		    	  dbvalidation.validateReturnOrderSummaryscreenForTC15(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
			    	  
			    	  //validating payment inquiry screen
			    	  dbvalidation.validatePaymentInquiryscreenForTC15(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			    	  
			    	  //closing the window
			    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
			      }catch(Exception e)
			      {
			      System.out.println(e);
			      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			      }
			      }
				
				
				
				//**********************************************************************************
				@Test(priority = 16,groups = { "smoke", "cov", "reg" })
				public void TestCase16() throws FileNotFoundException, IOException  
				{
				  int i = 25;
				  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			      try
			      {
			    	  
			    	//Execute query from DB
			    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			    	  
			    	  //validating return order summary screen
			    	  dbvalidation.validateReturnOrderSummaryscreenForTC16(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
			    	  
			    	  //validating payment inquiry screen
			    	 // dbvalidation.validatePaymentInquiryscreenForTC16(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			    	  
			    	  //closing the window
			    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
			      }catch(Exception e)
			      {
			      System.out.println(e);
			      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			      }
			      }
				
				//**********************************************************************************
				@Test(priority = 17,groups = { "smoke", "cov", "reg" })
				public void TestCase17() throws FileNotFoundException, IOException  
				{
				  int i = 26;
				  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			      try
			      {
			    	  
			    	//Execute query from DB
			    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			    	  
			    	//validating return order summary screen
			    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			    	  
			    	  //validating return order summary screen
			    	  dbvalidation.validateReturnOrderSummaryscreenForTC17(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
			    	  
			    	  //validating payment inquiry screen
			    	 // dbvalidation.validatePaymentInquiryscreenForTC17(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			    	  
			    	  //closing the window
			    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
			      }catch(Exception e)
			      {
			      System.out.println(e);
			      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			      }
			      }
				
				//**********************************************************************************
				@Test(priority = 18,groups = { "smoke", "cov", "reg" })
				public void TestCase18() throws FileNotFoundException, IOException  
				{
				  int i = 27;
				  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			      try
			      {
			    	  
			    	//Execute query from DB
			    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			    	  
			    	//validating return order summary screen
			    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			    	  
			    	  //validating return order summary screen
			    	  dbvalidation.validateReturnOrderSummaryscreenForTC18(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
			    	  
			    	  //validating payment inquiry screen
			    	  //dbvalidation.validatePaymentInquiryscreenForTC18(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			    	  
			    	  //closing the window
			    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
			      }catch(Exception e)
			      {
			      System.out.println(e);
			      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			      }
			      }
				
				//**********************************************************************************
				@Test(priority = 19,groups = { "smoke", "cov", "reg" })
				public void TestCase19() throws FileNotFoundException, IOException  
				{
				  int i = 28;
				  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			      try
			      {
			    	  
			    	//Execute query from DB
			   	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			    	  
			    	//validating return order summary screen
			    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			    	  
			    	  //validating return order summary screen
			    	  dbvalidation.validateReturnOrderSummaryscreenForTC18(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
			    	  
			    	  //validating payment inquiry screen
			    	//  dbvalidation.validatePaymentInquiryscreenForTC19(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			    	  
			    	  //closing the window
			    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
			      }catch(Exception e)
			      {
			      System.out.println(e);
			      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			      }
			      }
				@Test(priority = 20,groups = { "smoke", "cov", "reg" })
				public void TestCase20() throws FileNotFoundException, IOException  
				{
				  int i = 29;
				  XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				  String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				  String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				  oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			      try
			      {
			    	  
			    	//Execute query from DB
			    	  dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			    	  
			    	//validating return order summary screen
			    	  dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			    	  
			    	  //validating return order summary screen
			    	  dbvalidation.validateReturnOrderSummaryscreenForTC20(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
			    	  
			    	  //validating payment inquiry screen
			    	  dbvalidation.validatePaymentInquiryscreenForTC20(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			    	  
			    	  //closing the window
			    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
			      }catch(Exception e)
			      {
			      System.out.println(e);
			      oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			      }
			      }
	//*************************************************************************************************
		@Test(priority = 21,groups = { "smoke", "cov", "reg" })
		public void TestCase21() throws FileNotFoundException, IOException  
		{
			int i = 30;
			XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
			String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
			oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			try
			{
    	  
				//Execute query from DB
				dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
    	  
				//validating return order summary screen
				dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
    	  
				//validating return order summary screen
				dbvalidation.validateReturnOrderSummaryscreenForTC21(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
    	  
				//validating payment inquiry screen
				//dbvalidation.validatePaymentInquiryscreenForTC21(oPSelFW, getOrderNumber, XLTestDataRO, driver);
    	  
				//closing the window
				dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
				}catch(Exception e)
				{
				System.out.println(e);
				oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
				}
				}
		


		//*************************************************************************************************
		@Test(priority = 22,groups = { "smoke", "cov", "reg" })
		public void TestCase22() throws FileNotFoundException, IOException  
		{
			int i = 31;
			XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
			String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
			oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			try
			{
    	  
				//Execute query from DB
				dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
    	  
				//validating return order summary screen
				dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
    	  
				//validating return order summary screen
				dbvalidation.validateReturnOrderSummaryscreenForTC22(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
    	  
				//validating payment inquiry screen
				//dbvalidation.validatePaymentInquiryscreenForTC22(oPSelFW, getOrderNumber, XLTestDataRO, driver);
    	  
				//closing the window
				dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
				}catch(Exception e)
				{
				System.out.println(e);
				oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
				}
				}
		
		//*************************************************************************************************
		@Test(priority = 23,groups = { "smoke", "cov", "reg" })
		public void TestCase23() throws FileNotFoundException, IOException  
		{
			int i = 32;
			XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
			String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
			oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			try
			{
    	  
				//Execute query from DB
				dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
    	  
				//validating return order summary screen
				//dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
    	  
				//validating return order summary screen
				dbvalidation.validateReturnOrderSummaryscreenForTC23(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
    	  
				//validating payment inquiry screen
				//dbvalidation.validatePaymentInquiryscreenForTC23(oPSelFW, getOrderNumber, XLTestDataRO, driver);
    	  
				//closing the window
				dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
				}catch(Exception e)
				{
				System.out.println(e);
				oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
				}
			}
		
		
		//*************************************************************************************************
				@Test(priority = 24,groups = { "smoke", "cov", "reg" })
				public void TestCase24() throws FileNotFoundException, IOException  
				{
					int i = 33;
					XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
					String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
					String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
					oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
					try
					{
		    	  
						//Execute query from DB
						dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
		    	  
						//validating return order summary screen
						//dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
		    	  
						//validating return order summary screen
						dbvalidation.validateReturnOrderSummaryscreenForTC24(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
		    	  
						//validating payment inquiry screen
						dbvalidation.validatePaymentInquiryscreenForTC24(oPSelFW, getOrderNumber, XLTestDataRO, driver);
		    	  
						//closing the window
						dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
						}catch(Exception e)
						{
						System.out.println(e);
						oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
						}
						}
	
	//*************************************************************************************************
	@Test(priority = 24,groups = { "smoke", "cov", "reg" })
	public void TestCase25() throws FileNotFoundException, IOException  
	{
		int i = 34;
		XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
		String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
		String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
		oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
		try
		{
	  
			//Execute query from DB
			dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
	  
			//validating return order summary screen
			dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
	  
			//validating return order summary screen
			dbvalidation.validateReturnOrderSummaryscreenForTC25(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
	  
			//validating payment inquiry screen
			dbvalidation.validatePaymentInquiryscreenForTC25(oPSelFW, getOrderNumber, XLTestDataRO, driver);
	  
			//closing the window
			dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
			}catch(Exception e)
			{
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			}
			}
	

	
	//*************************************************************************************************
		@Test(priority = 25,groups = { "smoke", "cov", "reg" })
		public void TestCase26() throws FileNotFoundException, IOException  
		{
			int i = 35;
			XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
			String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
			oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			try
			{
		  
				//Execute query from DB
				dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
		  
				//validating return order summary screen
				dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
		  
				//validating return order summary screen
				dbvalidation.validateReturnOrderSummaryscreenForTC26(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
		  
				//validating payment inquiry screen
				//dbvalidation.validatePaymentInquiryscreenForTC26(oPSelFW, getOrderNumber, XLTestDataRO, driver);
		  
				//closing the window
				dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
				}catch(Exception e)
				{
				System.out.println(e);
				oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
				}
				}
	//*************************************************************************************************
			@Test(priority = 26,groups = { "smoke", "cov", "reg" })
			public void TestCase27() throws FileNotFoundException, IOException  
			{
				int i = 36;
				XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
				try
				{
			  
					//Execute query from DB
					dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			  
					//validating return order summary screen
					dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			  
					//validating return order summary screen
					dbvalidation.validateReturnOrderSummaryscreenForTC27(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
			  
					//closing the window
					dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
					}catch(Exception e)
					{
					System.out.println(e);
					oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
					}
					}
			
			//*************************************************************************************************
			@Test(priority = 27,groups = { "smoke", "cov", "reg" })
			public void TestCase28() throws FileNotFoundException, IOException  
			{
				int i = 37;
				XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
				try
				{
			  
					//Execute query from DB
					dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			  
					//validating return order summary screen
					//dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			  
					//validating return order summary screen
					dbvalidation.validateReturnOrderSummaryscreenForTC28(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
					//validating payment inquiry screen
					dbvalidation.validatePaymentInquiryscreenForTC28(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			  
					//closing the window
					dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
					}catch(Exception e)
					{
					System.out.println(e);
					oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
					}
					}
	
	//*************************************************************************************************
	@Test(priority = 28,groups = { "smoke", "cov", "reg" })
	public void TestCase29() throws FileNotFoundException, IOException  
	{
		int i = 38;
		XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
		String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
		String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
		oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
		try
		{
	  
			//Execute query from DB
			dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
	  
			//validating return order summary screen
			dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
	  
			//validating return order summary screen
			dbvalidation.validateReturnOrderSummaryscreenForTC29(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
			//validating payment inquiry screen
			//dbvalidation.validatePaymentInquiryscreenForTC29(oPSelFW, getOrderNumber, XLTestDataRO, driver);
	  
			//closing the window
			dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
			}catch(Exception e)
			{
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			}
			}
	
	//*************************************************************************************************
		@Test(priority = 29,groups = { "smoke", "cov", "reg" })
		public void TestCase30() throws FileNotFoundException, IOException  
		{
			int i = 39;
			XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
			String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
			oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			try
			{
		  
				//Execute query from DB
				dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
		  
				//validating return order summary screen
				//dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
		  
				//validating return order summary screen
				dbvalidation.validateReturnOrderSummaryscreenForTC30(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
				//validating payment inquiry screen
				//dbvalidation.validatePaymentInquiryscreenForTC30(oPSelFW, getOrderNumber, XLTestDataRO, driver);
		  
				//closing the window
				dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
				}catch(Exception e)
				{
				System.out.println(e);
				oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
				}
				}
		
		//*************************************************************************************************
				@Test(priority = 30,groups = { "smoke", "cov", "reg" })
				public void TestCase31() throws FileNotFoundException, IOException  
				{
					int i = 40;
					XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
					String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
					String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
					oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
					try
					{
				  
						//Execute query from DB
						dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
				  
						//validating return order summary screen
						dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
				  
						//validating return order summary screen
						dbvalidation.validateReturnOrderSummaryscreenForTC31(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
						//validating payment inquiry screen
						//dbvalidation.validatePaymentInquiryscreenForTC31(oPSelFW, getOrderNumber, XLTestDataRO, driver);
				  
						//closing the window
						dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
						}catch(Exception e)
						{
						System.out.println(e);
						oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
						}
						}
				
				//*************************************************************************************************
				@Test(priority = 31,groups = { "smoke", "cov", "reg" })
				public void TestCase32() throws FileNotFoundException, IOException  
				{
					int i = 41;
					XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
					String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
					String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
					oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
					try
					{
				  
						//Execute query from DB
						dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
				  
						//validating return order summary screen
						dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
				  
						//validating return order summary screen
						dbvalidation.validateReturnOrderSummaryscreenForTC31(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
						//validating payment inquiry screen
						//dbvalidation.validatePaymentInquiryscreenForTC32(oPSelFW, getOrderNumber, XLTestDataRO, driver);
				  
						//closing the window
						dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
						}catch(Exception e)
						{
						System.out.println(e);
						oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
						}
						}
	
	//*************************************************************************************************
	@Test(priority = 32,groups = { "smoke", "cov", "reg" })
	public void TestCase33() throws FileNotFoundException, IOException  
	{
		int i = 42;
		XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
		String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
		String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
		oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
		try
		{
	  
			//Execute query from DB
			dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
	  
			//validating return order summary screen
			dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
	  
			 //validating return order summary screen
	    	  dbvalidation.validateReturnOrderSummaryscreenFOROPTORO(oPSelFW, getOrderNumber, XLTestDataRO, driver);
	    	  
	    	  //validating payment inquiry screen
	    	//  dbvalidation.validatePaymentInquiryscreenEcomReturn(oPSelFW, getOrderNumber, XLTestDataRO, driver);
	    	  
	    	  //closing the window
	    	  dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
	  
			
			}catch(Exception e)
			{
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			}
	}
		//*************************************************************************************************
		@Test(priority = 33,groups = { "smoke", "cov", "reg" })
		public void TestCase34() throws FileNotFoundException, IOException  
		{
			int i = 43;
			XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
			String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
			oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			try
			{
		  
				//Execute query from DB
				dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
		  
				//validating return order summary screen
				dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
		  
				//validating return order summary screen
				dbvalidation.validateReturnOrderSummaryscreenForTC34(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
				//validating payment inquiry screen
				//dbvalidation.validatePaymentInquiryscreenForTC34(oPSelFW, getOrderNumber, XLTestDataRO, driver);
		  
				//closing the window
				dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
				}catch(Exception e)
				{
				System.out.println(e);
				oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
				}
				}
	
	//*************************************************************************************************
			@Test(priority = 34,groups = { "smoke", "cov", "reg" })
			public void TestCase35() throws FileNotFoundException, IOException  
			{
				int i = 44;
				XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
				try
				{
			  
					//Execute query from DB
					dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			  
					//validating return order summary screen
					//dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			  
					//validating return order summary screen
					dbvalidation.validateReturnOrderSummaryscreenForTC36(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
					//validating payment inquiry screen
					//dbvalidation.validatePaymentInquiryscreenForTC34(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			  
					//closing the window
					dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
					}catch(Exception e)
					{
					System.out.println(e);
					oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
					}
					}
			
			//*************************************************************************************************
			@Test(priority = 35,groups = { "smoke", "cov", "reg" })
			public void TestCase36() throws FileNotFoundException, IOException  
			{
				int i = 45;
				XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
				try
			{
			  
					//Execute query from DB
					dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			  
					//validating return order summary screen
					dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			  
					//validating return order summary screen
					dbvalidation.validateReturnOrderSummaryscreenForTC37(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
					//validating payment inquiry screen
					//dbvalidation.validatePaymentInquiryscreenForTC34(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			  
					//closing the window
					dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
					}catch(Exception e)
					{
					System.out.println(e);
					oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
					}
					}
			
		//*************************************************************************************************
			@Test(priority = 36,groups = { "smoke", "cov", "reg" })
			public void TestCase37() throws FileNotFoundException, IOException  
			{
				int i = 46;
				XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
				try
				{
			  
					//Execute query from DB
					dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			  
					//validating return order summary screen
					//dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			  
					//validating return order summary screen
					dbvalidation.validateReturnOrderSummaryscreenForTC38(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
					//validating payment inquiry screen
					dbvalidation.validatePaymentInquiryscreenForTC35(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			  
					//closing the window
					dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
					}catch(Exception e)
					{
					System.out.println(e);
					oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
					}
					}
			
			//*************************************************************************************************
			@Test(priority = 37,groups = { "smoke", "cov", "reg" })
			public void TestCase38() throws FileNotFoundException, IOException  
			{
				int i = 47;
				XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
				try
				{
			  
					//Execute query from DB
					dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			  
					//validating return order summary screen
					dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			  
					//validating return order summary screen
					dbvalidation.validateReturnOrderSummaryscreenForTC35(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
					//validating payment inquiry screen
					dbvalidation.validatePaymentInquiryscreenForTC36(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			  
					//closing the window
					dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
					}catch(Exception e)
					{
					System.out.println(e);
					oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
					}
					}
			
			//*************************************************************************************************
			@Test(priority = 38,groups = { "smoke", "cov", "reg" })
			public void TestCase39() throws FileNotFoundException, IOException  
			{
				int i = 48;
				XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
				try
				{
			  
					//Execute query from DB
					dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			  
					//validating return order summary screen
					//dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			  
					//validating return order summary screen
					dbvalidation.validateReturnOrderSummaryscreenForTC35(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
					//validating payment inquiry screen
					dbvalidation.validatePaymentInquiryscreenForTC39(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			  
					//closing the window
					dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
					}catch(Exception e)
					{
					System.out.println(e);
					oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
					}
					}
			
			//*************************************************************************************************
			@Test(priority = 40,groups = { "smoke", "cov", "reg" })
			public void TestCase40() throws FileNotFoundException, IOException  
			{
				int i = 49;
				XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
				String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
				oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
				try
				{
			  
					//Execute query from DB
					dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
			  
					//validating return order summary screen
					dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
			  
					//validating return order summary screen
					dbvalidation.validateReturnOrderSummaryscreenForTC40(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
					//validating payment inquiry screen
					dbvalidation.validatePaymentInquiryscreenForTC39(oPSelFW, getOrderNumber, XLTestDataRO, driver);
			  
					//closing the window
					dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
					}catch(Exception e)
					{
					System.out.println(e);
					oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
					}
					}
	
	//*************************************************************************************************
	@Test(priority = 41,groups = { "smoke", "cov", "reg" })
	public void TestCase41() throws FileNotFoundException, IOException  
	{
		int i = 51;
		XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
		String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
		String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
		oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
		try
		{
	  
			//Execute query from DB
			dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
	  
			//validating return order summary screen
			dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
	  
			//validating return order summary screen
			dbvalidation.validateReturnOrderSummaryscreenForTC40(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
	  
			//closing the window
			dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
			}catch(Exception e)
			{
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
			}
			}
	
	//*************************************************************************************************
		@Test(priority = 42,groups = { "smoke", "cov", "reg" })
		public void TestCase42() throws FileNotFoundException, IOException  
		{
			int i = 52;
			XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
			String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
			oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			try
			{
		  
			//Execute query from DB
				dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
		  
				//validating return order summary screen
				dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
		  
				//validating return order summary screen
				dbvalidation.validateReturnOrderSummaryscreenForTC41(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
		  
				//closing the window
				dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
			
				}catch(Exception e)
				{
				System.out.println(e);
				oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
				}
				}

		
		//*************************************************************************************************
		@Test(priority = 43,groups = { "smoke", "cov", "reg" })
		public void TestCase43() throws FileNotFoundException, IOException  
		{
			int i = 50;
			XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
			String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
			oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			try
			{
		  
				//Execute query from DB
				dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
		  
				//validating return order summary screen
				dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
		  
				//validating return order summary screen
				dbvalidation.validateReturnOrderSummaryscreenForTC42(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
				//validating payment inquiry screen
				dbvalidation.validatePaymentInquiryscreenForTC39(oPSelFW, getOrderNumber, XLTestDataRO, driver);
		  
				//closing the window
				dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
				}catch(Exception e)
				{
				System.out.println(e);
				oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
				}
				}
		
		//*************************************************************************************************
		@Test(priority = 44,groups = { "smoke", "cov", "reg" })
		public void TestCase44() throws FileNotFoundException, IOException  
		{
			int i = 53;
			XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
			String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
			oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
			try
			{
		  
				//Execute query from DB
				dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
		  
				//validating return order summary screen
				dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
		  
				//validating return order summary screen
				dbvalidation.validateReturnOrderSummaryscreenForTC25(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
		  
				//validating payment inquiry screen
				dbvalidation.validatePaymentInquiryscreenForTC25(oPSelFW, getOrderNumber, XLTestDataRO, driver);
		  
				//closing the window
				dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
				}catch(Exception e)
				{
				System.out.println(e);
				oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
				}
				}
		
		//*************************************************************************************************
				@Test(priority = 45,groups = { "smoke", "cov", "reg" })
				public void TestCase45() throws FileNotFoundException, IOException  
				{
					int i = 54;
					XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
					String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
					String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
					oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
					try
					{
				  
						//Execute query from DB
						dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
				  
						//validating return order summary screen
						dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
				  
						//validating return order summary screen
						dbvalidation.validateReturnOrderSummaryscreenForTC45(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
				  
						//validating payment inquiry screen
						dbvalidation.validatePaymentInquiryscreenForTC45(oPSelFW, getOrderNumber, XLTestDataRO, driver);
				  
						//closing the window
						dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
						}catch(Exception e)
						{
						System.out.println(e);
						oPSelFW.reportStepDetails("Verify if Exception is displayed" , e + " is displayed" , "Fail");
						}
						}
				
				//*************************************************************************************************
				@Test(priority = 46,groups = { "smoke", "cov", "reg" })
				public void TestCase46() throws FileNotFoundException, IOException  
				{
					int i = 55;
					XLTestDataRO = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
					String getOrderNumber=XLTestDataRO.get("orderNumber").toString();
					String lable2 = "Order Appeasement" + "_" + XLTestDataRO.get("TestCaseTitle").toString();
					oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable2, "Pass");
					try
					{
				  
						//Execute query from DB
						dbvalidation.SearchWithReturnOrder(driver, XLTestDataRO, oPSelFW, getOrderNumber);
				  
						//validating return order summary screen
						dbvalidation.orderSummaryPageValidation(driver, XLTestDataRO, getOrderNumber, oPSelFW);
				  
						//validating return order summary screen
						dbvalidation.validateReturnOrderSummaryscreenForTC46(oPSelFW, getOrderNumber, XLTestDataRO,driver); 
				  
						//validating payment inquiry screen
						dbvalidation.validatePaymentInquiryscreenForTC45(oPSelFW, getOrderNumber, XLTestDataRO, driver);
				  
						//closing the window
						dbvalidation.closeWindow(driver, XLTestDataRO, oPSelFW);
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


