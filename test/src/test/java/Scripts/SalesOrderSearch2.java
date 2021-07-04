package Scripts;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import com.prolifics.ProlificsSeleniumAPI;

import FrameWork.Excel_Reader;
import FrameWork.Generic;
import Library.EmailReport;
import Library.Sample2;
import Library.Sterling2;
import bsh.util.Util;

public class SalesOrderSearch2{

	public static Excel_Reader excelReader;
	public static Excel_Reader envDetails;
	public static int i = 1;
	public static boolean scriptSucful =false;
	int PassCount = 0, FailCount = 0;
	long totalTime, finish,start;
	public HashMap<String, String>  envTestData;
	public HashMap<String, String>  XLTestData;
	boolean ItemDetailsDisplayed;
	WebDriver driver;
	Sample2 sample=new Sample2();
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	ProlificsSeleniumAPI oPSelFW = null;
	Generic gen = new FrameWork.Generic();
	Sterling2 objStr = new Sterling2();
	EmailReport em=new EmailReport();

	
	 @BeforeClass
		public void Test()
				throws IOException, InterruptedException, SQLException, ParserConfigurationException, SAXException {
		//String TestDataPath = System.getProperty("user.dir") + "\\Data\\"+System.getProperty("InputFileName");
		 start = System.currentTimeMillis();
		 excelReader = new Excel_Reader(System.getProperty("user.dir") + "\\Data\\TestData_STST2.xlsx");
		 String TestDataPath = System.getProperty("user.dir") + "\\Data\\TestData_STST2.xlsx";
		 excelReader.cFileNameWithPath = TestDataPath;
			excelReader.cSheetName = "TestData";
			excelReader.cTcID = "TestCaseID";
			excelReader.cTcValue = "1";
			XLTestData = new HashMap<String, String>();
			XLTestData = excelReader.readExcel("TS_CCUI_Sales_management_Sales_Order");
			envDetails.cFileNameWithPath = TestDataPath;
			envDetails.cSheetName = "Environment";
			envDetails.cTcID = "Execution";
			envTestData = envDetails.readExcel("Y");
			String env = envTestData.get("Enviroment");
			oPSelFW = new ProlificsSeleniumAPI("Consumer Search ", "CCUI_Search_with_Sales_OrderNumber",
					"Chrome", "Windows 7", "STST2", "Automation", "TestAutomation", "Regression");
			oPSelFW.sAutomationPath = System.getProperty("user.dir")+ "\\ProlificsResults\\";
			Util util = new Util();
			oPSelFW.reportPath(System.getProperty("user.dir") + "\\target\\");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/resources/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
			driver.navigate().refresh();

			oPSelFW.startReport();

		}
		
	/* ############################################################## 
	 * # Method Name :VerifyOrderSearch()
	* # Author : Mamatha
	 * # Date : 03/3/2020
	* # Description verify Order search Details
	*  ##############################################################*/
 @Test(groups = { "smoke", "cov", "reg" })
	public void VerifyOrderSearch1() throws Throwable {
	 
		oPSelFW.reportStepDetails("Start the New Steps for TestCase", "Verify search functionalities in CCUI - Search by - Sales order number", "Info");
			
		sample.ccuiLoginRolechange(oPSelFW,envTestData, driver);
		sample.clickOnOrderTab(driver,oPSelFW);
		String orderNo=XLTestData.get("SalesOrder").toString();
		sample.ccuiSalesOrderSearch_salesOrder(driver,orderNo,oPSelFW);
		Thread.sleep(3000);
		objStr.orderSerachDBValidation_SalesOrder(driver,oPSelFW,envTestData,orderNo);
		
		
	}
  

	@AfterMethod(alwaysRun = true)

		public void oTest(ITestResult result) throws InterruptedException, IOException{         

		              oPSelFW.stopReport();

		           // em.afterSuite();

		              if(result.getStatus() == ITestResult.FAILURE)

		              {

		                     driver.close();

		                     Thread.sleep(100);

		                     driver.quit();

		                     Thread.sleep(100);

		                     scriptSucful = false;

		             

		              }

		if(result.getStatus() == ITestResult.SUCCESS)

		{ scriptSucful = true; }

		              if(result.getStatus() == ITestResult.SKIP) { System.out.println("Failure"); }

		          }

     @AfterClass(alwaysRun = true)

     public void afterMethod1() throws IOException, InterruptedException, ParseException

     {

    	 finish = System.currentTimeMillis();
  		totalTime = finish - start;
  		//System.out.println("____________________________________"+exeTime);
  		String ClassName = this.getClass().getName();
  		oPSelFW.stopReport();
  		em.afterSuite();
  		//em.afterSuite(totalTime);
  		driver.close();
         driver.quit();

     }

}


