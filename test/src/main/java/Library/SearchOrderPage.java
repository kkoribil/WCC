package Library;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.prolifics.ProlificsSeleniumAPI;

import FrameWork.Generic;

public class SearchOrderPage {
	Logger  log;
	Generic gen1 = new Generic();
		public SearchOrderPage()
		{
			
			try{PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
			String logFilePath = System.getProperty("user.dir") + "\\ProlificsReports\\";
			gen1.UpdatePropFile("log", logFilePath);
			log = Logger.getLogger(SearchOrderPage.class);
			}
			catch(Exception e)
			{
				System.out.println("Inside Exception");
			}
		}
	public static ProlificsSeleniumAPI oPSelFW = null;
	private HashMap<String, String> XLTestDataBI = new HashMap<String, String>();
	Generic1 gen = new Generic1();
	public static WebDriver driver;
	WebElement element;
	DbValidations validation = new DbValidations();
	public String getFunds = "//*[@uid='extn_lnkGetFunds']/div[2]/div/div/a"; 
	public void BalanceSearch(WebDriver driver,HashMap<String, String> XLTestDataBI, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		String  Paymenttype = XLTestDataBI.get("PaymentType").toString();
		Thread.sleep(5000);
		gen.waitnclickElement(driver, By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[1]"), "Search button ", oPSelFW);
		
		driver.findElement(By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[1]")).click();
		driver.findElement(By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[2]")).sendKeys(Paymenttype);
		driver.findElement(By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[2]")).sendKeys(Keys.TAB);
		Thread.sleep(5000);
		String  Certificate = XLTestDataBI.get("LoyaltyRewardsCertificate").toString();
		Thread.sleep(5000);
		oPSelFW.reportStepDetails("LRC Certificate number should be displayed" ,"LRC Certificate number is "+ Certificate, "Pass");
		log.info("LRC Certificate number is "+ Certificate);
		
		driver.findElement(By.xpath("(//*[@uid='extn_Certificate_Number']/div[2]/div/div/input)[1]")).sendKeys(Certificate);
		driver.findElement(By.xpath("(//*[@uid='extn_Certificate_Number']/div[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@uid='extn_btnGetFunds']/span/span/span[3]")).click();
		oPSelFW.reportStepDetails("Balance Inquiry should be display" ,"Balance Inquiry page displayed" , "Pass");
		log.info("Balance Inquiry page displayed" );
		Thread.sleep(20000);
		validation.ValidatingBalanceInquiryStatus(driver, element, oPSelFW);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@uid='extn_btnCancel']/span/span/span[3]")).click();
		gen1.waitUntilElementIsClickable(driver, 1000, getFunds);
		element = driver.findElement(By.xpath("//*[@uid='extn_lnkGetFunds']/div[2]/div/div/a"));

	}
	

	public void BalanceSearch1(WebDriver driver,HashMap<String, String> XLTestDataBI, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		String  Paymenttype = XLTestDataBI.get("PaymentType").toString();
		Thread.sleep(5000);
		gen.waitnclickElement(driver, By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[1]"), "Search button ", oPSelFW);
		
		driver.findElement(By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[1]")).click();
		driver.findElement(By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[2]")).sendKeys(Paymenttype);
		driver.findElement(By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[2]")).sendKeys(Keys.TAB);
		Thread.sleep(5000);
		String  Certificate = XLTestDataBI.get("LoyaltyRewardsCertificate").toString();
		oPSelFW.reportStepDetails("LRC Certificate number should be displayed" ,"LRC Certificate number is "+ Certificate, "Pass");
		log.info("LRC Certificate number is "+ Certificate);
		
		Thread.sleep(20000);
		driver.findElement(By.xpath("(//*[@uid='extn_Certificate_Number']/div[2]/div/div/input)[1]")).sendKeys(Certificate);
		driver.findElement(By.xpath("(//*[@uid='extn_Certificate_Number']/div[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
		
		Thread.sleep(20000);
		driver.findElement(By.xpath("//*[@uid='extn_btnGetFunds']/span/span/span[3]")).click();
		oPSelFW.reportStepDetails("Balance Inquiry should be display" ,"Balance Inquiry page displayed" , "Pass");
		log.info("Balance Inquiry page displayed" );
		Thread.sleep(20000);
		validation.ValidatingBalanceInquiryStatus(driver, element, oPSelFW);
		Thread.sleep(20000);
		driver.findElement(By.xpath("//*[@uid='extn_btnCancel']/span/span/span[3]")).click();
		gen1.waitUntilElementIsClickable(driver, 1000, getFunds);
		element = driver.findElement(By.xpath("//*[@uid='extn_lnkGetFunds']/div[2]/div/div/a"));
		
		
	}
	
	public void BalanceSearch2(WebDriver driver,HashMap<String, String> XLTestDataBI, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		
		String  Paymenttype = XLTestDataBI.get("PaymentType").toString();
		Thread.sleep(5000);
		gen.waitnclickElement(driver, By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[1]"), "Search button ", oPSelFW);
		
		driver.findElement(By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[1]")).click();
		driver.findElement(By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[2]")).sendKeys(Paymenttype);
		driver.findElement(By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[2]")).sendKeys(Keys.TAB);
		Thread.sleep(5000);
		String  Certificate = XLTestDataBI.get("LoyaltyRewardsCertificate").toString();
		oPSelFW.reportStepDetails("LRC Certificate number should be displayed" ,"LRC Certificate number is "+ Certificate, "Pass");
		log.info("LRC Certificate number is "+ Certificate);
		
		Thread.sleep(20000);
		driver.findElement(By.xpath("(//*[@uid='extn_Certificate_Number']/div[2]/div/div/input)[1]")).sendKeys(Certificate);
		driver.findElement(By.xpath("(//*[@uid='extn_Certificate_Number']/div[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
		
		Thread.sleep(20000);
		driver.findElement(By.xpath("//*[@uid='extn_btnGetFunds']/span/span/span[3]")).click();
		oPSelFW.reportStepDetails("Balance Inquiry should be display" ,"Balance Inquiry page displayed" , "Pass");
		log.info("Balance Inquiry page displayed" );
		Thread.sleep(20000);
		validation.ValidatingBalanceInquiryStatus(driver, element, oPSelFW);
		Thread.sleep(20000);
		driver.findElement(By.xpath("//*[@uid='extn_btnCancel']/span/span/span[3]")).click();
		gen1.waitUntilElementIsClickable(driver, 1000, getFunds);
		element = driver.findElement(By.xpath("//*[@uid='extn_lnkGetFunds']/div[2]/div/div/a"));
		
	}	
	
	
	public void DCCancellationBalanceInquery(WebDriver driver,HashMap<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws Exception
	{
		

		Thread.sleep(20000);
		String  Paymenttype = XLTestData.get("Certificate").toString();
		Thread.sleep(5000);
		gen.waitnclickElement(driver, By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[1]"), "Search button ", oPSelFW);
			
		driver.findElement(By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[1]")).click();
		driver.findElement(By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[2]")).sendKeys(Paymenttype);
		driver.findElement(By.xpath("(//*[@uid='extn_cmbPaymentType']/div[2]/div/div/input)[2]")).sendKeys(Keys.TAB);
		Thread.sleep(5000);
		String  Certificate = XLTestData.get("LoyaltyRewardsCertificate").toString();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//*[@uid='extn_Certificate_Number']/div[2]/div/div/input)[1]")).sendKeys(Certificate);
		driver.findElement(By.xpath("(//*[@uid='extn_Certificate_Number']/div[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@uid='extn_btnGetFunds']/span/span/span[3]")).click();
		oPSelFW.reportStepDetails("Balance Inquiry should be display" ,"Balance Inquiry page displayed" , "Pass");
		log.info("Balance Inquiry page displayed" );
		Thread.sleep(20000);
		validation.ValidatingBalanceInquiryStatus(driver, element, oPSelFW);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@uid='extn_btnCancel']/span/span/span[3]")).click();
		gen1.waitUntilElementIsClickable(driver, 1000, getFunds);
		element = driver.findElement(By.xpath("//*[@uid='extn_lnkGetFunds']/div[2]/div/div/a"));
		Thread.sleep(20000);
	}
	
}
