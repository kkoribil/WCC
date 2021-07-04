package Library;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.prolifics.ProlificsSeleniumAPI;

import FrameWork.Generic;

public class Sample2 {
	Logger  log;
	Generic gen1 = new Generic();
		public Sample2()
		{
			
			try{PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
			String logFilePath = System.getProperty("user.dir") + "\\ProlificsReports\\";
			gen1.UpdatePropFile("log", logFilePath);
			log = Logger.getLogger(Sample2.class);
			}
			catch(Exception e)
			{
				System.out.println("Inside Exception");
			}
		}
	FrameWork.Generic gen = new FrameWork.Generic();
	Util util = new Util();
	ProlificsSeleniumAPI oPSelFW = null;
	WebDriver driver;
	public static  Connection stST2Con;
	//String OrdersTab="//li[@id='tab-order']";
	String txtUserName = "//input[@id='ws-user']";
	String txtPassword = "//input[@id='ws-pw']";
	String btnLogin = "//button[@id='ws-sign-in']";
	String popUpRole="//div[@id='popup1']/div";
	String changeRole="//input[@id='confirm_change_role']";
	String tabOrder="//li[@id='tab-order']";
	String resetPara = "//a[contains(text(),'Reset Parameter')]";
	String tabHeader="div[id='tabHeader']";
	String OrdersTab="//*[@id='tab-order']";
	String SearchResult="order-search-results[ordertype='sales']";
	String PrintResult="button[id='printAllResults']";
	String Results="div>link[rel='stylesheet']+style+div>p>span+b+span+b";
	String cancelButn="cr-button[class='cancel-button']";
	String displayOrder="table[id='result-table']>tr:nth-child(2) td:nth-child(2)>a";
	String noRecords = "//table/tbody/tr/td[contains(text(),'No Records found')]";
	//String OrdersTab = "//li[@id='tab-order']";
	String store = "//li[@id='tab-bopis']/a";
	String storeId = "//*[@id='NG2_UPGRADE_3_0']/div[1]/ccui-bopis-change-store-form/form/input";
	String tick = "//span[@class='fa fa-fw fa-check ng-star-inserted']/.";
	String firstname = "//input[@id='fName']";
	String lastName = "//input[@id='lName']";
	String search = "(//button[contains(text(),'Search')])[2]";
	String tableFirstName = "(//table/tbody/tr/td[7]/span)[1]";
	String shipToFirstName = "(//table/tbody/tr/td[8]/span)[1]";
	String shipToLastName = "(//table/tbody/tr/td[9]/span)[1]";
	String tableLastName = "(//table/tbody/tr/td[8]/span)[1]";
	String NoResult="div>link[rel='stylesheet']+style+div";
	boolean status = false;
	public static final String OrderNumber = "//input[@id='ordNo']";
	String BilltocustomerName="//h3[@class='capitalized']//human-readable";
	String Billtocustomeraddress= "//div[@class='grid-3']/div[1]/copyable-text/span[1]";
	String Billtocustomercityandzipcode= "//div[@id='customerDetails']/div/div[2]/div/div/div[2]/div[1]/copyable-text/span[2]";
	String Billtocustomerprimarycontact="//div[@id='customerDetails']/div/div[2]/div/div/div[2]/div[2]/copyable-text/format-text";
	String BilltocustomerEmailid= "//copyable-text[@class='header-email ng-binding']";
	String ReturnNumber= "(//p[contains(text(),'#:')]//copyable-text[@class='ng-binding'])[1]";
	String Brand= "//span[@class='brand-literal ng-binding']";
	String Status="//p[contains(text(),'Status:')]//strong[@class='ng-binding']";
	String FirstName="#order-search-form > fieldset:nth-child(7) > content-tabs > tab-body > div.grid-2 > div:nth-child(1) > input[type=text]";
	String Brands="#order-search-form > fieldset:nth-child(6) > div > div:nth-child(1) > select";
	String LastName="#order-search-form > fieldset:nth-child(7) > content-tabs > tab-body > div.grid-2 > div:nth-child(2) > input[type=text]";
	String OrderNo="#order-search-form > fieldset:nth-child(5) > div > div:nth-child(1) > input[type=text]";
	String workOrder="#order-search-form > fieldset:nth-child(5) > div > div:nth-child(4) > input[type=text]";
	String trackingOrder="#order-search-form > fieldset:nth-child(5) > div > div:nth-child(2) > input[type=text]";
	String daxOrder="#order-search-form > fieldset:nth-child(5) > div > div:nth-child(3) > input[type=text]";
	String zipCode="#order-search-form > fieldset:nth-child(7) > content-tabs > tab-body > div:nth-child(2) > input[type=text]";
	String phoneNo="#order-search-form > fieldset:nth-child(7) > content-tabs > tab-body > div.InputGroup.PhoneInput > input[type=tel]:nth-child(1)";
	String emailId="#order-search-form > fieldset:nth-child(7) > content-tabs > tab-body > div:nth-child(4) > input[type=email]";
	String fromDate="#order-search-form > fieldset:nth-child(6) > div > div:nth-child(2) > input[type=date]";
	String toDate="#order-search-form > fieldset:nth-child(6) > div > div:nth-child(3) > input[type=date]";
	 String Datecreated="//span[contains(text(),'Created')]/parent::p//span[2]//strong[1]";
	 String Searchpayment="tab-header[name='payment']";
	 String creditcards=" #order-search-form > fieldset:nth-child(7) > content-tabs > tab-body > div.InputGroup.CreditCardInput > input[type=text]:nth-child(1)";
	 String giftcards="#order-search-form > fieldset:nth-child(7) > content-tabs > tab-body > div:nth-child(2) > input[type=text]:nth-child(1)";
	 String lrccard="#order-search-form > fieldset:nth-child(7) > content-tabs > tab-body > div.InputGroup.GiftCardInput.LRCInput > input[type=text]:nth-child(1)";
 	String minimumamount="#order-search-form > fieldset:nth-child(7) > content-tabs > tab-body > div:nth-child(4) > input[type=text]:nth-child(1)";
 	String tabReturn="tab-header[name='return']";
 	String itemTab="//*[@id='tab-items']";
 	String itemno="#order-search-form > fieldset:nth-child(5) > div > div:nth-child(1) > input[type=text]";
 	public void ccuiLoginRolechange(ProlificsSeleniumAPI oPSelFW,HashMap<String, String> envTestData, WebDriver driver)
			throws Exception {
		try {
			String url = envTestData.get("CCUIURL").toString();
			String userName = envTestData.get("CCUIUserName").toString();
			String pwd = envTestData.get("CCUIPasword").toString();
			
			driver.get(url);
			oPSelFW.reportStepDetails("Launching CCUI Application", "CCUI Application is Successfully launched", "Pass");
			log.info("CCUI Application is Successfully launched");
			gen.setText(driver, txtUserName, userName, oPSelFW);
			gen.setText(driver, txtPassword, pwd, oPSelFW);
			gen.clickElement(driver, btnLogin, oPSelFW);
			Thread.sleep(1000);
			
			} catch (Exception e) {
				oPSelFW.reportStepDetails("Verify if exception is displayed", e.toString()+ " is displayed", "Fail");
				log.error(e.toString()+ " is displayed");
				String ExpectedResult = "User should be Login in CCUI";
				Assert.assertEquals("Verify if exception has occured", ExpectedResult);
			
			}
 	}
 	public void clickOnOrderTab(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException
    {
           gen.clickElements(driver, OrdersTab, oPSelFW);
           Thread.sleep(5000);
    }
	//Order
	public void ccuiSalesOrderSearch_salesOrder(WebDriver driver,String DBOrderno,ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException
	{
		try {
				
				WebElement orderSearchForm = driver.findElement(By.cssSelector("#mainContent > div:nth-child(3) > div > order-search-form"));
				WebElement shadownOrderSrchForm = expandShadowRootElement(driver,orderSearchForm);
				gen.setTextShadowElementWithRoot(driver, shadownOrderSrchForm,"#order-search-form > fieldset:nth-child(5) > div > div:nth-child(1) > input[type=text]",DBOrderno,"SalesOrder",oPSelFW);
				WebElement Serachbutn=shadownOrderSrchForm.findElement(By.cssSelector("#center-controls > button"));
				gen.click(Serachbutn,oPSelFW,"Search");
				Thread.sleep(8000);
				gen.clickOnResults(driver,oPSelFW); 
				
			}
			catch(Exception e)
			{
				oPSelFW.reportStepDetails("Verify if exception is displayed", e.toString()+ " is displayed", "Fail");
				log.error( e.toString()+ " is displayed");
				String ExpectedResult = "SalesOrder is to be search in CCUI";
				Assert.assertEquals("Verify if exception has occured", ExpectedResult);
			}
	
}
		//
	public WebElement expandShadowRootElement(ProlificsSeleniumAPI oPSelFW, WebDriver driver, WebElement element) {
		WebElement shadowRoot = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].shadowRoot", element);
		return shadowRoot;
	}
	////////
public WebElement expandShadowRootElement(WebDriver driver, WebElement element) {
	WebElement shadowRoot = (WebElement) ((JavascriptExecutor) driver)
			.executeScript("return arguments[0].shadowRoot", element);
	return shadowRoot;
}



}
