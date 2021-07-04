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
import FrameWork.Excel_Reader;
import Library.CreateOrderPage;
import Library.EmailReport;
import Library.Generic1;
import Library.LoginPage1;
import Library.XLStoCSVConverter;

public class CreateOrderWCCBusinessOrder {

	public static Excel_Reader excelReader;
	public static int i = 1;
	int PassCount = 0, FailCount = 0;
	public Connection OraConn;
	boolean ItemDetailsDisplayed = false;
	public static ProlificsSeleniumAPI oPSelFW = null;
	Generic1 gen = new Generic1();
	CreateOrderPage ordercreate = new CreateOrderPage();
	static HashMap<String, String> XLTestData = new HashMap<String, String>();
	WebElement element;
	String GenItemNumber;
	String OrderCreatedSucc;
	boolean errorDisplayed = false;
	boolean ItemExistsInWCC = false;
	static boolean scriptSucful = false;
	static WebDriver driver;
	String errorMessage;
	static boolean loginSuccess;
	static String OutputFileName = System.getProperty("user.dir") + "\\target\\CreateOrder";
	EmailReport em = new EmailReport();
	int RowNum = 1;

	@BeforeMethod(alwaysRun = true)
	public void launchBrowser() throws InterruptedException, IOException {
		if (scriptSucful == false) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String Env = System.getProperty("Environment");
			if (Env.contains("STST2"))
				loginSuccess = gen.launchURL(driver, gen.getPropertyValue("STST2URL"), oPSelFW);
			else if (Env.contains("STST"))
				loginSuccess = gen.launchURL(driver, gen.getPropertyValue("STSTURL"), oPSelFW);
			else if (Env.contains("EQAE3"))
				loginSuccess = gen.launchURL(driver, gen.getPropertyValue("EQAE3URL"), oPSelFW);

			LoginPage1 login = new LoginPage1();
			login.LoginToWCC(driver, System.getProperty("UserID"), System.getProperty("Password"), oPSelFW);
		}
	}

	@DataProvider(name = "createOrderProvider", parallel = false)
	public static Object[][] createOrderProvider() throws FileNotFoundException, IOException {
		System.out.println(System.getProperty("user.dir") + "\\Data\\" + System.getProperty("Environment")
				+ "_CreateOrder_Regression.xlsx");
		excelReader = new Excel_Reader(System.getProperty("user.dir") + "\\Data\\" + System.getProperty("Environment")
				+ "_CreateOrder_Regression.xlsx");
		return asTwoDimensionalArray();

	}

	private static Object[][] asTwoDimensionalArray() throws FileNotFoundException, IOException {
		String TestDataPath = System.getProperty("user.dir") + "\\Data\\" + System.getProperty("Environment")
				+ "_CreateOrder_Regression.xlsx";
		excelReader = new Excel_Reader(TestDataPath);
		excelReader.cFileNameWithPath = TestDataPath;
		excelReader.cSheetName = "TestData";
		excelReader.cTcID = "TestCaseID";
		excelReader.cTcValue = "1";
		int RowCount = excelReader.GetRowCount("TestData");
		Object[][] results = new Object[1][1];
		
		XLTestData = excelReader.readExcel("TC_WCC_2");
		return results;
	}

	@BeforeClass(alwaysRun = true)
	public void beforeTest1() throws Exception {
		String TestDataPath = System.getProperty("user.dir") + "\\Data\\" + System.getProperty("Environment")
				+ "_CreateOrder_Regression.xlsx";
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
		OutputFileName = OutputFileName + time + ".csv";
		BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(OutputFileName)));
		// write contents of StringBuffer to a file
		bwr.write(sbf.toString());
		// flush the stream
		bwr.flush();
		// close the stream
		bwr.close();
		oPSelFW = new ProlificsSeleniumAPI("WCC", "Create Order WCC", "Chrome", "Windows 7", "EQA3", "Automation",
				"TestAutomation", "Regression");

		oPSelFW.sAutomationPath = System.getProperty("user.dir") + "\\ProlificsReports\\";
		oPSelFW.reportPath(System.getProperty("user.dir") + "\\target\\");
		oPSelFW.startReport();
	}

	@Test(dataProvider = "createOrderProvider", groups = { "smoke", "cov", "reg" })
	public void CreateOrderTestClass(HashMap<String, String> XLTestData)
			throws FileNotFoundException, IOException, InterruptedException {
		XLTestData = excelReader.readExcel("TC_WCC_2");

		if (XLTestData != null) {
			InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			oPSelFW.reportStepDetails("Start the New Steps for TestCase",
					"Create Order - " + XLTestData.get("TestCaseTitle").toString(), "Pass");
			try {
				String getOrderNumber;
				if (loginSuccess) {
					errorDisplayed = false;
					ordercreate.clickOnOrderLink(driver, oPSelFW);
					ordercreate.ConceptTypeScroll(driver, XLTestData.get("Concept").toString(), oPSelFW);
					ordercreate.clickOnUnRegisteredCustomerLink(driver, oPSelFW);
					
					String ItemAdded = ordercreate.enterItemDetails(driver, oPSelFW, XLTestData);
					if (ItemAdded.contains("Unable to add this item") || ItemAdded.contains("Invalid Item")
							|| ItemAdded.contains("Item is no longer available") || ItemAdded.contains("Error"))
						getOrderNumber = ItemAdded;
					else {
						ordercreate.ClickonNext(driver, oPSelFW);
						Thread.sleep(1000);
						if (driver.findElements(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]"))
								.size() > 0) {

							String errorMessage = driver
									.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]"))
									.getAttribute("innerText");
							if (errorMessage
									.contains("There are errors on the page. You cannot proceed until you fix them")
									|| errorMessage.toLowerCase().contains("error")) {
								ordercreate.ClickonNext(driver, oPSelFW);
							}
							errorMessage = driver
									.findElement(By.xpath("(//*[@uid='singlemessagelabel']/div[1]/span)[1]"))
									.getAttribute("innerText");
							if (errorMessage.contains("invalid identifier")
									|| errorMessage.toLowerCase().contains("error")
									|| errorMessage.toLowerCase().contains("complete required personalization")
									|| errorMessage.toLowerCase().contains("cannot include a variant")) {
								oPSelFW.reportStepDetails("Verify error message",
										"Error message " + errorMessage + " is displayed", "Pass");
								errorDisplayed = true;
								OrderCreatedSucc = errorMessage;
							}
						}
						if (errorDisplayed == false) {
							ordercreate.enterAddressDetails(driver, "BillTo", oPSelFW, XLTestData);
							ordercreate.enterAddressDetails(driver, "ShipTo", oPSelFW, XLTestData);
							String OrderCreatedSucc = ordercreate.enterCustomerType(driver, oPSelFW, element,
									XLTestData);
							if (!OrderCreatedSucc.contains("successfully") || (OrderCreatedSucc.trim().length() == 0)) {
								String errorInPayment = ordercreate.AddPaymentMethod(driver, oPSelFW, element,
										XLTestData);
								if (errorInPayment.trim().length() == 0) {
									gen.waitUntilElementIsPresent(driver, "//*[@uid='pnlPrimaryInfo']/div/table/tbody/tr/td/span");
									getOrderNumber = driver
											.findElement(
													By.xpath("//*[@uid='pnlPrimaryInfo']/div/table/tbody/tr/td/span"))
											.getText();
									oPSelFW.reportStepDetails("Get Order Number", getOrderNumber + " is generated",
											"Pass");
								} else
									getOrderNumber = errorInPayment;
								
							} else {
								getOrderNumber = OrderCreatedSucc;
								oPSelFW.reportStepDetails("Verify error message",
										"Error message " + getOrderNumber + " is displayed", "Pass");
							}
						} else {
							getOrderNumber = errorMessage;
							oPSelFW.reportStepDetails("Verify error message",
									"Error message " + getOrderNumber + " is displayed", "Pass");
						}
					}
				} else
					getOrderNumber = "User is not able to login";
				File file = new File(OutputFileName);
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = "", oldtext = "";
				int LineNumber = 0;
				while ((line = reader.readLine()) != null) {
					LineNumber++;
					if (line.contains(XLTestData.get("TestCaseTitle").toString() + ","))
						oldtext += line + ",'" + getOrderNumber + "\r\n";
					else
						oldtext += line + "\r\n";
				}
				reader.close();
				RowNum++;
				// To replace a line in a file
				FileWriter writer = new FileWriter(OutputFileName);
				writer.write(oldtext);
				writer.close();
				System.out.println("Order Number Generated" + getOrderNumber);

				gen.closeTheOrder(driver, oPSelFW);
				
				oPSelFW.stopReport();
				em.afterSuite();
			} catch (Exception e) {
				System.out.println(e);
				oPSelFW.reportStepDetails("Verify if exception is displayed", "Exception is displayed in Order Create", "Fail");
				Assert.assertEquals("Verify if exception is displayed", "Exception is displayed in Order Create");
			}
		}
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException, IOException {
		driver.close();
		Thread.sleep(100);
		driver.quit();
		oPSelFW.stopReport();
		em.afterSuite();
	}

	@AfterMethod(alwaysRun = true)
	public void oTest(ITestResult result) throws InterruptedException, IOException {

		oPSelFW.stopReport();
		em.afterSuite();
		if (result.getStatus() == ITestResult.FAILURE) {
			driver.close();
			Thread.sleep(100);
			driver.quit();
			Thread.sleep(2000);
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
