package Scripts;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileInputStream;
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

import FrameWork.Excel_Reader;
import Library.CreateOrderPage;
import Library.CreateOrderPage1;
import Library.DataBase1;
import Library.DbValidationsAppeasement;
import Library.EmailReport;
import Library.Generic1;
import Library.LoginPage1;
import Library.XLStoCSVConverter;

public class RewardEarning {

	public static Excel_Reader excelReader;
	int PassCount = 0, FailCount = 0;
	public Connection OraConn;
	boolean ItemDetailsDisplayed = false;
	private HashMap<String, String> XLTestDataRE = new HashMap<String, String>();
	static boolean scriptSucful = false;
	DbValidationsAppeasement dbvalidation = new DbValidationsAppeasement();
	static boolean loginSuccess;
	ProlificsSeleniumAPI oPSelFW = null;
	Generic1 gen1 = new Generic1();
	CreateOrderPage1 ordercreate = new CreateOrderPage1();
	CreateOrderPage ordercreate1 = new CreateOrderPage();
	Generic1 gen = new Generic1();
	//ShipmentXmlsAndApis objXmlBuild = new ShipmentXmlsAndApis();
	EmailReport em = new EmailReport();
	WebElement element;
	String GenItemNumber;
	
	String OrderCreatedSucc;
	boolean errorDisplayed = false;
	boolean ItemExistsInWCC = false;
	public static WebDriver driver;
	String errorMessage;
	static String OutputFileName = System.getProperty("user.dir") + "\\target\\CreateOrder";
	static String Prop = System.getProperty("user.dir") + "\\target\\ReturnOrders.properties";
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
		
		oPSelFW = new ProlificsSeleniumAPI("WCC", "REWARD EARNING", "Chrome", "Windows 7", "STST", "Automation",
				"TestAutomation", "Regression");
		oPSelFW.sAutomationPath = System.getProperty("user.dir") + "\\ProlificsReports\\";
		oPSelFW.reportPath(System.getProperty("user.dir") + "\\target\\");
		oPSelFW.startReport();
	}

	@Test(groups = { "smoke", "cov", "reg" })
	public void CreateOrderTestClass()throws Exception 
	//public void WCC_Appeasement() throws FileNotFoundException, IOException, InterruptedException
	{
		if(XLTestDataRE != null)
		{
		InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
		Properties prop = new Properties();
		prop.load(input);
		DataBase1 db1 = new DataBase1();
		LoginPage1 login = new LoginPage1();
		 int i = 3;
		 
		 XLTestDataRE = new HashMap<String, String>();
		 XLTestDataRE = excelReader.readExcel("TC_WCC_" + Integer.toString(i));
		 String getOrderNumber=XLTestDataRE.get("orderNumber").toString();
			String lable4 = "Order Appeasement" + "_" + XLTestDataRE.get("TestCaseTitle").toString();
			oPSelFW.reportStepDetails("Start the New Steps for TestCase", lable4, "Pass");
       if (loginSuccess) {
							
							//test case for loyalty reward earning
							ordercreate1.clickOnOrderLink(driver, oPSelFW);
							ordercreate1.ConceptTypeScroll(driver, XLTestDataRE.get("Concept").toString(), oPSelFW);
							ordercreate1.clickOnUnRegisteredCustomerLink(driver, oPSelFW);
							
							String ItemAdded = ordercreate1.enterItemDetails(driver, oPSelFW, XLTestDataRE); 
							if(!ItemAdded.contains("Items Added"))
							{
								getOrderNumber = ItemAdded;
							}
							else
							{
								//	String OrderTotal = ordercreate.validateOrderTotal(driver, basetest);
								ordercreate1.ClickonNext(driver, oPSelFW);
								Thread.sleep(1000);
								if(driver.findElements(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")).size() > 0)
								{	
									
									String errorMessage = driver.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]")).getAttribute("innerText");
									if(errorMessage.contains("There are errors on the page. You cannot proceed until you fix them") || errorMessage.toLowerCase().contains("error"))
									{
										ordercreate1.ClickonNext(driver, oPSelFW);
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
									ordercreate1.enterAddressDetails(driver,"BillTo", oPSelFW, XLTestDataRE);
									ordercreate1.enterAddressDetails(driver,"ShipTo", oPSelFW, XLTestDataRE);	
									String OrderCreatedSucc = ordercreate.enterCustomerType(driver, oPSelFW, element, XLTestDataRE);				
									if(!OrderCreatedSucc.contains("successfully") || (OrderCreatedSucc.trim().length() == 0))
									{
										Thread.sleep(5000);					
										//calling the loyality enrollment method
										dbvalidation.LoyaltyEnrollment(driver, XLTestDataRE, oPSelFW);
									}
								}
							}
						}
        }
		{
        	
		}
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
       		oPSelFW.stopReport();
       		em.afterSuite();         }
           if(result.getStatus() == ITestResult.SUCCESS)
           {
                 scriptSucful = true;
           }
         if(result.getStatus() == ITestResult.SKIP)
         {
             System.out.println("Failure");
         }
         }

@AfterClass(alwaysRun = true)
public void closeBrowser() throws InterruptedException
{
	driver.close();
	Thread.sleep(100);
	driver.quit();
	
}	
}