//*************************************************************************************************************************************
//Test Scenarios Covered:
//Validates the default functionality DS field of newly item created in RMS
//Validates the default functionality BO Lead field of newly item created in RMS
//*************************************************************************************************************************************
package Scripts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import com.prolifics.ProlificsSeleniumAPI;
import FrameWork.DataBase;
import FrameWork.Excel_Reader;
import Library.CreateOrderPage;
import Library.EmailReport;
import Library.Generic1;
import Library.LoginPage1;
import Library.XLStoCSVConverter;

public class CreateOrderWCC3{

	public static Excel_Reader excelReader;
	public static int i = 1;
	int PassCount = 0, FailCount = 0;
	public Connection OraConn;
	boolean ItemDetailsDisplayed = false;
	public static ProlificsSeleniumAPI oPSelFW = null;
	Generic1 gen = new Generic1();
	CreateOrderPage ordercreate = new CreateOrderPage();
	WebElement element;
	String GenItemNumber;
	boolean ItemExistsInWCC = false;
	String OrderCreatedSucc;
	boolean errorDisplayed= false;
	static boolean scriptSucful = false;
	String errorMessage;
	static String OutputFileName = System.getProperty("user.dir") + "\\target\\CreateOrder";
	static WebDriver driver;
	static boolean loginSuccess;
	EmailReport em = new EmailReport();
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
	@DataProvider(name = "createOrderPr1")
	public static Object[][] createOrderProvider() throws FileNotFoundException, IOException {
		System.out.println(System.getProperty("user.dir") + "\\Data\\"+System.getProperty("Environment") + "_CreateOrder_Regression.xlsx");
		excelReader = new Excel_Reader(System.getProperty("user.dir") + "\\Data\\"+System.getProperty("Environment") + "_CreateOrder_Regression.xlsx");
		Object[][] results = asTwoDimensionalArray();
		return(results);
	}
	
	private static Object[][] asTwoDimensionalArray() throws FileNotFoundException, IOException {
		String TestDataPath = 	System.getProperty("user.dir") + "\\Data\\"+System.getProperty("Environment") + "_CreateOrder_Regression.xlsx";
		excelReader = new Excel_Reader(TestDataPath);
		excelReader.cFileNameWithPath = TestDataPath;
		excelReader.cSheetName = "TestData";
		excelReader.cTcID = "TestCaseID";
		excelReader.cTcValue = "1";
		int RowNumber = excelReader.workbook.getSheet("TestData").getLastRowNum();
		int RowCount = excelReader.GetRowCount("TestData");
		Object[][] results = new Object[(RowCount/3)+1][1];
		//Object[][] results = new Object[100][1];
		int j = 0;
		int MaxRowCount = (RowCount/3) + (RowCount/3);
		
		for (int i = MaxRowCount; i <= RowCount-1; i++) {
			HashMap<String, String> XLTestData = new HashMap<String, String>();
			XLTestData = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
			results[j][0] = XLTestData;
			j++;
		}
		return results;
	}

	@BeforeClass(alwaysRun = true)
	public void beforeTest1() throws Exception {
		String TestDataPath = 	System.getProperty("user.dir") + "\\Data\\"+System.getProperty("Environment") + "_CreateOrder_Regression.xlsx";
		excelReader = new Excel_Reader(TestDataPath);
		excelReader.cFileNameWithPath = TestDataPath;
		excelReader.cSheetName = "TestData";
		excelReader.cTcID = "TestCaseID";
		excelReader.cTcValue = "1";
		File myFile = new File(TestDataPath);
        XLStoCSVConverter xls = new XLStoCSVConverter();
        StringBuffer sbf = new StringBuffer();
        sbf = xls.convertSelectedSheetInXLXSFileToCSV(myFile, 0);
		
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
		oPSelFW = new ProlificsSeleniumAPI("WCC", "Create Order WCC3", "Chrome", "Windows 7", "STST", "Automation",
				"TestAutomation", "Regression");
		
		oPSelFW.sAutomationPath = System.getProperty("user.dir") + "\\ProlificsReports\\";
		oPSelFW.reportPath(System.getProperty("user.dir") + "\\target\\");
		oPSelFW.startReport();
	}
	@Test(dataProvider = "createOrderPr1", groups = { "smoke", "cov", "reg" })
	public void CreateOrderTestClass(HashMap<String, String> XLTestData1) throws FileNotFoundException, IOException, InterruptedException
	{
		int RowNum=1;
	 	InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
		Properties prop = new Properties();
        prop.load(input);
		DataBase db = new DataBase();
		LoginPage1 login = new LoginPage1();
		System.out.println(XLTestData1.get("TestCaseID").toString());
		if(!XLTestData1.get("TestCaseID").toUpperCase().equals("TC_WCC_66"))
			oPSelFW.reportStepDetails("Start the New Steps for TestCase", "Create Order - "+XLTestData1.get("TestCaseTitle").toString(), "Pass");
		try {
				String getOrderNumber;
				if(loginSuccess)
				{	
					ordercreate.clickOnOrderLink(driver, oPSelFW);
					ordercreate.ConceptTypeScroll(driver, XLTestData1.get("Concept").toString(), oPSelFW);
					ordercreate.clickOnUnRegisteredCustomerLink(driver, oPSelFW);
					ordercreate.selectOrderType(driver, XLTestData1.get("OrderType").toString(), XLTestData1.get("StoreNumber").toString(), oPSelFW);
					String ItemAdded = ordercreate.enterItemDetails(driver, oPSelFW, XLTestData1); 
					if(ItemAdded.contains("Unable to add this item") || ItemAdded.contains("Invalid Item") || 
						ItemAdded.contains("Item is no longer available") || ItemAdded.contains("Error"))
						getOrderNumber = ItemAdded;
					else
					{
						//	String OrderTotal = ordercreate.validateOrderTotal(driver, basetest);
						ordercreate.ClickonNext(driver, oPSelFW);
						Thread.sleep(1000);
						if(gen.existsElement(driver, By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")))
						{	
							
							errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")).getAttribute("innerText");
							if(errorMessage.contains("There are errors on the page. You cannot proceed until you fix them") || errorMessage.toLowerCase().contains("error"))
							{
								ordercreate.ClickonNext(driver, oPSelFW);
							}
							errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")).getAttribute("innerText");
							if(errorMessage.contains("invalid identifier") || errorMessage.toLowerCase().contains("error") || errorMessage.toLowerCase().contains("complete required personalization")
									|| errorMessage.toLowerCase().contains("cannot include a variant"))
							{
								oPSelFW.reportStepDetails("Verify error message"  , "Error message "+ errorMessage + " is displayed", "Pass");
								errorDisplayed = true;
								OrderCreatedSucc = errorMessage;
							}
						}
						if(errorDisplayed == false)
						{
							ordercreate.enterAddressDetails(driver,"BillTo", oPSelFW, XLTestData1);
							ordercreate.enterAddressDetails(driver,"ShipTo", oPSelFW, XLTestData1);
							String OrderCreatedSucc = ordercreate.enterCustomerType(driver, oPSelFW, element, XLTestData1);				
							if(!OrderCreatedSucc.contains("successfully") || (OrderCreatedSucc.trim().length() == 0))
							{
								String errorInPayment = ordercreate.AddPaymentMethod(driver, oPSelFW, element, XLTestData1);
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
							{	getOrderNumber = OrderCreatedSucc;
								oPSelFW.reportStepDetails("Verify error message"  , "Error message "+ getOrderNumber + " is displayed", "Pass");
							}
						}
						else 
						{	
							getOrderNumber = errorMessage;
							oPSelFW.reportStepDetails("Verify error message"  , "Error message "+ getOrderNumber + " is displayed", "Pass");
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
                	if(line.contains(XLTestData1.get("TestCaseID").toString()+","))
                	{
                		oldtext += line + ",'"+ getOrderNumber + "\r\n";
                		//line.indexOf(",", 51)
                		//line.substring(line.indexOf(",", 51), endIndex)
                	}
                    	
                    else
                    	oldtext += line + "\r\n";
                }
                reader.close();

                //To replace a line in a file
                FileWriter writer = new FileWriter(OutputFileName);
                writer.write(oldtext);
                writer.close();

				//excelReader.writeExcel(RowNum, "OrderNumber", getOrderNumber, System.getProperty("user.dir") + "\\Data\\CreateOrder-Copy2", XLTestData);
				System.out.println("Order Number Generated" +getOrderNumber);
				
				gen.closeTheOrder(driver, oPSelFW);
				
				oPSelFW.stopReport();
				em.afterSuite();	
		}
		catch (Exception e) {
			System.out.println(e);
			oPSelFW.reportStepDetails("Verify if exception is displayed", "Exception is displayed in Order Create", "Fail");
			Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Order Create");
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
			Thread.sleep(2000);
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
