//*************************************************************************************************************************************
//Test Scenarios Covered:
//Creates Order in WCC 
//*************************************************************************************************************************************
package Scripts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import com.aventstack.extentreports.Status;
import com.prolifics.ProlificsSeleniumAPI;
import FrameWork.DataBase;
import FrameWork.Excel_Reader;
import Library.CreateOrderPage;
import Library.EmailReport;
import Library.Generic1;
import Library.LoginPage1;
import Library.XLStoCSVConverter;
import java.nio.file.*; 

public class CreateOrder{

	public static Excel_Reader excelReader;
	public static int i = 1;
	int PassCount = 0, FailCount = 0;
	public Connection OraConn;
	boolean ItemDetailsDisplayed = false;

	public static ProlificsSeleniumAPI oPSelFW = null;
	CreateOrderPage ordercreate = new CreateOrderPage();
	WebElement element;
	String GenItemNumber;
	static WebDriver driver;
	String OrderCreatedSucc;
	static boolean scriptSucful = false; 
	boolean errorDisplayed= false;
	boolean ItemExistsInWCC = false;
	Generic1 gen = new Generic1();
	static String errorMessage;
	static boolean loginSuccess;
	static String getOrderNumber;
	EmailReport em = new EmailReport();
	static String OutputFileName = System.getProperty("user.dir") + "\\target\\CreateOrder";
	static String Prop = System.getProperty("user.dir") + "\\resources\\Orders.properties";
	int RowNum = 1;
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
			System.out.println(gen.getPropertyValue("STST2URL"));
			
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
	@DataProvider(name = "createOrderProvider", parallel = false)
	public static Object[][] createOrderProvider() throws FileNotFoundException, IOException {
		excelReader = new Excel_Reader(System.getProperty("user.dir") + "\\Data\\Orders.xlsx");
		return asTwoDimensionalArray();
	}
	
	private static Object[][] asTwoDimensionalArray() throws FileNotFoundException, IOException {
		String TestDataPath = 	System.getProperty("user.dir") + "\\Data\\Orders.xlsx";
		excelReader = new Excel_Reader(TestDataPath);
		excelReader.cFileNameWithPath = TestDataPath;
		excelReader.cSheetName = "TestData";
		excelReader.cTcID = "TestCaseID";
		excelReader.cTcValue = "1";
		int RowCount = excelReader.GetRowCount("TestData");
		Object[][] results = new Object[RowCount][1];
		HashMap<String, String> XLTestData = new HashMap<String, String>();
		System.out.println("Number of Rows in Excel "+RowCount);
		for (int i = 1; i < RowCount; i++) {			
				XLTestData = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
				results[i] = new Object[] {XLTestData};
		}
		return results;
	}

	@BeforeClass(alwaysRun = true)
	public void beforeTest1() throws Exception {
		String TestDataPath = 	System.getProperty("user.dir") + "\\Data\\Orders.xlsx";
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
        }
        catch(Exception e)
        {
        	System.out.println("File Not Exists");
        }
    
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy hh mm ss");
	    String time = dateFormat.format(now);
	    time = time.replace(" ", "");
	    OutputFileName = OutputFileName +  time + ".csv";
	    BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(OutputFileName)));
		//write contents of StringBuffer to a file
	    bwr.write(sbf.toString());
		//flush the stream
		bwr.flush();
		//close the stream
		bwr.close();
		
	    BufferedWriter bwr1 = new BufferedWriter(new FileWriter(new File(Prop)));
		oPSelFW = new ProlificsSeleniumAPI("WCC", "Create Order", "Chrome", "Windows 7", "STST", "Automation",
				"TestAutomation", "Regression");
		
		oPSelFW.sAutomationPath = System.getProperty("user.dir") + "\\ProlificsReports\\";
		oPSelFW.reportPath(System.getProperty("user.dir") + "\\target\\");
		oPSelFW.startReport();
	}
	@Test(dataProvider = "createOrderProvider", groups = { "smoke", "cov", "reg" })
	public void CreateOrderTestClass(HashMap<String, String> XLTestData) throws FileNotFoundException, IOException, InterruptedException
	{
		if(XLTestData != null)
		{
			InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			DataBase db = new DataBase();
			if(!XLTestData.get("TestCaseID").toUpperCase().equals("TC_WCC_1"))
				oPSelFW.reportStepDetails("Start the New Steps for TestCase", "Create Order - "+XLTestData.get("TestCaseTitle").toString(), "Pass");
			try {	
				if(loginSuccess)
				{	
					ordercreate.clickOnOrderLink(driver, oPSelFW);
					ordercreate.ConceptTypeScroll(driver, XLTestData.get("Concept").toString(), oPSelFW);
					ordercreate.clickOnUnRegisteredCustomerLink(driver, oPSelFW);
					ordercreate.selectOrderType(driver, XLTestData.get("OrderType").toString(), XLTestData.get("StoreNumber").toString(), oPSelFW);
					String ItemAdded = ordercreate.enterItemDetails(driver, oPSelFW, XLTestData); 
					if(!ItemAdded.contains("Items Added"))
					{
						getOrderNumber = ItemAdded;
					}
					else
					{
						//	String OrderTotal = ordercreate.validateOrderTotal(driver, basetest);
						ordercreate.ClickonNext(driver, oPSelFW);
						Thread.sleep(1000);
						if(gen.existsElement(driver, By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")))
						{	
							
							String errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")).getAttribute("innerText");
							if(errorMessage.contains("There are errors on the page. You cannot proceed until you fix them") || errorMessage.toLowerCase().contains("error"))
							{
								ordercreate.ClickonNext(driver, oPSelFW);
							}
							errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")).getAttribute("innerText");
							if(errorMessage != null)
							{
								if(errorMessage.contains("invalid identifier") || errorMessage.toLowerCase().contains("error") || errorMessage.toLowerCase().contains("complete required personalization")
										|| errorMessage.toLowerCase().contains("cannot include a variant"))
								{
									oPSelFW.reportStepDetails("Verify error message"  , errorMessage + " is displayed", "Pass");
									errorDisplayed = true;
									getOrderNumber = errorMessage;
								}
							}
						}
						if(errorDisplayed == false)
						{
							ordercreate.enterAddressDetails(driver,"BillTo", oPSelFW, XLTestData);
							ordercreate.enterAddressDetails(driver,"ShipTo", oPSelFW, XLTestData);
							String OrderCreatedSucc = ordercreate.enterCustomerType(driver, oPSelFW, element, XLTestData);				
							if(!OrderCreatedSucc.contains("successfully") || (OrderCreatedSucc.trim().length() == 0))
							{
								String errorInPayment = ordercreate.AddPaymentMethod(driver, oPSelFW, element, XLTestData);
								if(errorInPayment.trim().length() == 0)
								{
									gen.waitUntilElementIsPresent(driver, "//*[@uid='pnlPrimaryInfo']/div/table/tbody/tr/td/span");
									getOrderNumber = driver.findElement(By.xpath("//*[@uid='pnlPrimaryInfo']/div/table/tbody/tr/td/span")).getText();
									oPSelFW.reportStepDetails("Get Order Number"  , getOrderNumber + " is generated", "Pass");
								}
								else
									getOrderNumber = errorInPayment; 
								//ordercreate.validateOrderDetails(driver, OrderTotal, XLTestData, basetest);
								//ordercreate.unLockTheOrder(driver, basetest);
							}
							else 
							{	
								getOrderNumber = OrderCreatedSucc;
								oPSelFW.reportStepDetails("Verify error message"  , "Error message "+ getOrderNumber + " is displayed", "Pass");
							}
						}
						else 
						{	
							if(errorMessage != null)
							{
								getOrderNumber = errorMessage;
								oPSelFW.reportStepDetails("Verify error message"  , "Error message "+ getOrderNumber + " is displayed", "Pass");
							}
						}
						}
					}
					else
						getOrderNumber = "User is not able to login";
					File file = new File(OutputFileName);
	                BufferedReader reader = new BufferedReader(new FileReader(file));
	                String line = "", oldtext = "";
	                int LineNumber = 0;
	                while((line = reader.readLine()) != null)
                    {
	                	LineNumber++;
	                	if(line.contains(XLTestData.get("TestCaseID").toString()+","))
                			oldtext += line + ",'"+ getOrderNumber + "\r\n";
                		else
                			oldtext += line + "\r\n";
                    }
	                reader.close();
	                RowNum++;
	                //To replace a line in a file
	                FileWriter writer = new FileWriter(OutputFileName);
	                writer.write(oldtext);
	                writer.close();
	                
	                file = new File(Prop);
	                reader = new BufferedReader(new FileReader(file));
	                line = "";
	                oldtext = "";
	                while((line = reader.readLine()) != null)
                    {
                		oldtext += line + "\r\n";
                    }
	                oldtext = oldtext + XLTestData.get("TestCaseTitle").toString() + "=" + getOrderNumber + "\r\n";
	                reader.close();
	                writer = new FileWriter(Prop);
	                writer.write(oldtext);
	                writer.close();
				System.out.println("Order Number Generated" +getOrderNumber);
				gen.closeTheOrder(driver, oPSelFW);
				}
		catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if exception is displayed", "Exception is displayed in Order Create", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Order Create");
		}
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
