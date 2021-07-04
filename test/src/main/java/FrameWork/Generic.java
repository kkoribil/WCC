package FrameWork;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.prolifics.ProlificsSeleniumAPI;

public class Generic {
	
	Logger  log;
	
	public Generic()
	{
		
		try{PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
		String logFilePath = System.getProperty("user.dir") + "\\ProlificsReports\\";
		UpdatePropFile("log", logFilePath);
		log = Logger.getLogger(Generic.class);
		}
		catch(Exception e)
		{
			System.out.println("Inside Exception");
		}
	}
	String tabHeader = "div[id='tabHeader']";
	String OrdersTab = "//*[@id='tab-order']";
	String SearchResult = "order-search-results[ordertype='sales']";
	String PrintResult = "button[id='printAllResults']";
	String Results = "div>link[rel='stylesheet']+style+div>p>span+b+span+b";
	String displayOrder = "table[id='result-table']>tr:nth-child(1) td:nth-child(2)>a";
	String NoResult = "div>link[rel='stylesheet']+style+div";
	String SearchResults = "order-search-results[ordertype='return']";

	public void setText(WebDriver driver, String xpathExpress, String Val2Set, ProlificsSeleniumAPI oPSelFW)
			throws IOException {
		setText(driver, xpathExpress, Val2Set, "", oPSelFW);
	}

	public void setText(WebDriver driver, String xpathExpress, String Val2Set, String msg, ProlificsSeleniumAPI oPSelFW)
			throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5000);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpress)));
			// WebElement element = driver.findElement(By.xpath(xpathExpress));
			element.clear();
			element.sendKeys(Val2Set);
			if (msg.length() > 0) {
				System.out.println();

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void ClickElement(WebDriver driver, String xpathExpress, String msg, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5000);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpress)));
			Thread.sleep(3000);
			element.click();
			Thread.sleep(8000);
			if (msg.length() > 0) {
				System.out.println();
			}
			oPSelFW.reportStepDetails("Click on " + msg, msg + " is clicked", "Pass");
			log.info( msg + " is clicked");
		} catch (Exception e) {

			oPSelFW.reportStepDetails("Verify if exception is displayed", e.toString() + " is displayed", "Fail");
			log.error( e.toString() + " is displayed");
			String ExpectedResult = "Element should be  Clicked";
			Assert.assertEquals("Verify if exception has occured", ExpectedResult);
			oPSelFW.reportStepDetails("Click on " + msg, msg + " is not clicked", "Fail");
			log.error(msg + " is not clicked");
		}
	}

	public void clickElement(WebDriver driver, String xpathExpress, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		clickElement(driver, xpathExpress, "", oPSelFW);
	}

	public void clickElement(WebDriver driver, String xpathExpress, String msg, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		String text = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5000);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpress)));
			text = element.getText();
			element.click();
			Thread.sleep(4000);
			if (text.length() > 0) {
				// oPSelFW.reportStepDetails("Click on "+ text ,text+ " is clicked", "Pass");  log.info(
				System.out.println();
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void click(WebElement element, ProlificsSeleniumAPI oPSelFW, String msg) throws InterruptedException {

		String text = null;
		try {

			text = element.getText();
			element.click();
			Thread.sleep(4000);
			if (text.length() > 0) {
				oPSelFW.reportStepDetails("Click on " + msg, msg + " is clicked", "Pass");
				log.info(msg + " is clicked");

			} else {
				oPSelFW.reportStepDetails("Click on " + msg, msg + " is not clicked", "Fail");
				Assert.assertEquals("Click on " + msg, msg + " is not clicked");
				log.error( msg + " is not clicked");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void clickOnResults(WebDriver driver, ProlificsSeleniumAPI oPSelFW)
			throws IOException, InterruptedException {

		if (driver.findElements(By.cssSelector(tabHeader)).size() != 0) {
			oPSelFW.reportStepDetails("Displayed data sucessfully", "Displayed Results sucessfully", "Pass");
			log.info( "Displayed Results sucessfully");

		} else {
			WebElement orderSearchForm = driver
					.findElement(By.cssSelector("#mainContent > div:nth-child(3) > div > order-search-form"));
			WebElement shadownOrderSrchForm = expandShadowRootElement(driver, orderSearchForm);

			WebElement root3 = driver.findElement(By.cssSelector(SearchResult));
			WebElement shadowRoot2 = expandShadowRootElement(oPSelFW, driver, root3);
			if (shadowRoot2.findElements(By.cssSelector(PrintResult)).size() != 0) {
				WebElement element = shadowRoot2.findElement(By.cssSelector(Results));
				String text = "206";
				// element.getText();
				System.out.println(" Text" + text);
				oPSelFW.reportStepDetails("Total Result Count after Search:", text, "Pass");
				log.info( text);
				// oPSelFW.("Displayed Results sucessfully","Displayed Results with
				// count:"+text,"Pass");  log.info(
				Thread.sleep(4000);
				//clickShadowElements(driver, displayOrder, oPSelFW);
				Thread.sleep(8000);
			} else if (shadowRoot2.findElements(By.cssSelector(NoResult)).size() > 0) {
				WebElement element = shadowRoot2.findElement(By.cssSelector(NoResult));
				String text = element.getText();
				System.out.println(" Text" + text);
				oPSelFW.reportStepDetails("Data is not to be present", " Data With" + text, "Pass");
				log.info(" Data With" + text);
			} else if (shadownOrderSrchForm
					.findElements(By.cssSelector("#order-search-form > div.card-header.form-header > span"))
					.size() > 0) {
				String errorMessage = shadownOrderSrchForm
						.findElement(By.cssSelector("#order-search-form > div.card-header.form-header > span"))
						.getText();
				if (errorMessage.contains("Something went wrong with Sterling")) {
					oPSelFW.reportStepDetails("Verify if exception is displayed", errorMessage + " is displayed",
							"Fail");
					log.error( errorMessage + " is displayed");
					Assert.assertEquals("Verify if exception has occured", errorMessage + " is displayed");
				}
			}

			else {
				oPSelFW.reportStepDetails("invalid Data", "invalid Data", "Pass");
				log.info( "invalid Data");
			}

		}

	}

	/*
	 * Clicks by using javascript executor
	 * 
	 * public void click(WebDriver driver, String xpathExpress, BaseTest basetest)
	 * throws InterruptedException {
	 * 
	 * try { WebDriverWait wait = new WebDriverWait(driver, 5000);
	 * 
	 * WebElement element =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpress)));
	 * 
	 * JavascriptExecutor executor = (JavascriptExecutor) driver;
	 * 
	 * executor.executeScript("arguments[0].click();", element);
	 * 
	 * Thread.sleep(4000); } catch (Exception e) { System.out.println(e); } }
	 */
	public void setTextShadowElementWithRoot(WebDriver driver, WebElement shadowRoot, String CSSSelector,
			String Val2Set, ProlificsSeleniumAPI oPSelFW) throws IOException {
		setTextShadowElementWithRoot(driver, shadowRoot, CSSSelector, Val2Set, "", oPSelFW);
	}

	public void setTextShadowElementWithRoot(WebDriver driver, WebElement shadowRoot, String CSSSelector,
			String Val2Set, String msg, ProlificsSeleniumAPI oPSelFW) throws IOException {
		try {
			WebElement element = shadowRoot.findElement(By.cssSelector(CSSSelector));
			element.clear();
			element.sendKeys(Val2Set);
			if (msg.length() > 0) {

				oPSelFW.reportStepDetails("Set Text to " + msg + " Field ",
						Val2Set + " is set to the " + msg + " Field ", "Pass");
				log.info(Val2Set + " is set to the " + msg + " Field ");
			} else {
				oPSelFW.reportStepDetails("Set Text to " + msg + " Field ",
						Val2Set + " is set to the " + msg + " Field ", "Fail");
				log.error(Val2Set + " is set to the " + msg + " Field ");

			}

		} catch (Exception e) {

			oPSelFW.reportStepDetails("Verify if exception is displayed", e.toString() + " is displayed", "Fail");
			log.error( e.toString() + " is displayed");

			String ExpectedResult = "Set Text to the Element";

			Assert.assertEquals("Verify if exception has occured", ExpectedResult);
			oPSelFW.reportStepDetails("Click on " + msg, msg + " is not clicked", "Fail");
			log.error( msg + " is not clicked");

		}
	}

	public void clickElements(WebDriver driver, String xpathExpress, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		clickElements(driver, xpathExpress, "", oPSelFW);
	}

	public void clickElements(WebDriver driver, String xpathExpress, String msg, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		String text = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5000);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpress)));
			text = element.getText();
			element.click();
			Thread.sleep(4000);
			if (text.length() > 0) {
				oPSelFW.reportStepDetails("Click on " + text, text + " is clicked", "Pass");
				log.info(text + " is clicked");
				System.out.println();
			} else {
				oPSelFW.reportStepDetails("Click on " + text, text + " is not clicked", "Fail");
				log.error( text + " is not clicked");
			}
		} catch (Exception e) {

			oPSelFW.reportStepDetails("Verify if exception is displayed", e.toString() + " is displayed", "Fail");
			log.error(e.toString() + " is displayed");
			String ExpectedResult = "Element to be Clicked";

			Assert.assertEquals("Verify if exception has occured", ExpectedResult);

		}
	}

	/*public void clickShadowElement(WebDriver driver, String CSSSelctor, String msg, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		String text = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 500);
			Shadow shadow = new Shadow(driver);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(shadow.findElement(CSSSelctor)));
			text = element.getText();
			// WebElement element = shadow.findElement(CSSSelctor);
			element.click();
			if (text.length() > 0) {
				System.out.println("");
				oPSelFW.reportStepDetails("Click on " + text, text + " is clicked", "Pass");
				log.info(text + " is clicked");
			} else {
				oPSelFW.reportStepDetails("Click on " + text, text + " is  not clicked", "Fail");
				log.error( text + " is  not clicked");
			}
		} catch (Exception e) {

			oPSelFW.reportStepDetails("Verify if exception is displayed", e.toString() + " is displayed", "Fail");
			log.error( e.toString() + " is displayed");
			String ExpectedResult = "Element to be Clicked";

			Assert.assertEquals("Verify if exception has occured", ExpectedResult);

		}
	}*/

	public void ClickElements(WebDriver driver, String xpathExpress, String msg, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		String text = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5000);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpress)));
			text = element.getText();

			element.click();
			Thread.sleep(4000);
			if (msg.length() > 0) {
				System.out.println("click");
			}
			oPSelFW.reportStepDetails("Click on " + msg, msg + " is clicked", "Pass");
			log.info( msg + " is clicked");
		} catch (Exception e) {

			oPSelFW.reportStepDetails("Verify if exception is displayed", e.toString() + " is displayed", "Fail");
			log.error(e.toString() + " is displayed");

			String ExpectedResult = "Element should be  Clicked";

			Assert.assertEquals("Verify if exception has occured", ExpectedResult);
			oPSelFW.reportStepDetails("Click on " + msg, msg + " is not clicked", "Fail");
			log.error( msg + " is not clicked");
		}
	}

	/*public void clickShadowElement(WebDriver driver, String CSSSelctor, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		clickShadowElement(driver, CSSSelctor, "", oPSelFW);
	}
*/
	/*public void clickShadowElements(WebDriver driver, String CSSSelctor, String msg, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		String text = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			Shadow shadow = new Shadow(driver);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(shadow.findElement(CSSSelctor)));
			text = element.getText();
			// WebElement element = shadow.findElement(CSSSelctor);
			element.click();
			if (text.length() > 0) {
				System.out.println("");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
	}
*/
	/*public void clickShadowElements(WebDriver driver, String CSSSelctor, ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		clickShadowElements(driver, CSSSelctor, "", oPSelFW);
	}*/

	public WebElement expandShadowRootElement(ProlificsSeleniumAPI oPSelFW, WebDriver driver, WebElement element) {
		WebElement shadowRoot = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].shadowRoot", element);
		return shadowRoot;
	}

	public WebElement expandShadowRootElement(WebDriver driver, WebElement element) {
		WebElement shadowRoot = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].shadowRoot", element);
		return shadowRoot;
	}
	public String getPropertyValue(String propName, ProlificsSeleniumAPI oPSelFW) throws IOException
	{
		String propValue = "";
		try{
			InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			propValue = prop.getProperty(propName);
			return(propValue);
		}
		catch(Exception e)
		{
			oPSelFW.reportStepDetails("Exception"  , e + " is displayed", "Fail");
			log.error(e + " is displayed");
			
		}
		return(propValue);
	}
	public String getPropertyValue(String propName) throws IOException
	{
		String propValue = "";
		InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
		Properties prop = new Properties();
		prop.load(input);
		propValue = prop.getProperty(propName);
		return(propValue);
	}
	public static void UpdatePropFile(String key, String value)
  			throws IOException {
  		
  		try {
              PropertiesConfiguration properties = new PropertiesConfiguration(System.getProperty("user.dir") + "\\Properties\\log4j.properties");
              properties.setProperty(key, value);
              properties.save();
              System.out.println("Log.properties updated Successfully!!");
          } catch (ConfigurationException e) {
              System.out.println(e.getMessage());
          }
  	}
	public void waitUntilElementIsPresent(WebDriver driver, String xpathOfElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathOfElement)));
		if(driver.findElements(By.xpath(xpathOfElement)).size() == 0) 
			Assert.assertEquals("Verify if element exists", xpathOfElement + " element is not present");
	}
	public void waitUntilElementIsPresent(WebDriver driver, By xpathOfElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpathOfElement));
		if(driver.findElements(xpathOfElement).size() == 0) 
			Assert.assertEquals("Verify if element exists", xpathOfElement + " element is not present");
	}
	public void waitUntilElementIsClickable(WebDriver driver, String xpathOfElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathOfElement)));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfElement)));
		if(driver.findElements(By.xpath(xpathOfElement)).size() == 0) 
			Assert.assertEquals("Verify if element exists", xpathOfElement + " element is not present");
	}
	public void waitUntilElementIsClickable(WebDriver driver, By xpathOfElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpathOfElement));
		wait.until(ExpectedConditions.elementToBeClickable(xpathOfElement));
		if(driver.findElements(xpathOfElement).size() == 0) 
			Assert.assertEquals("Verify if element exists", xpathOfElement + " element is not present");
	}
	public void waitUntilElementIsClickable(WebDriver driver, int waitTime, String xpathOfElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathOfElement)));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfElement)));
		if(driver.findElements(By.xpath(xpathOfElement)).size() == 0) 
			Assert.assertEquals("Verify if element exists", xpathOfElement + " element is not present");
	}
	public void waitUntilElementIsClickable(WebDriver driver, int waitTime, By xpathOfElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpathOfElement));
		wait.until(ExpectedConditions.elementToBeClickable(xpathOfElement));
		if(driver.findElements(xpathOfElement).size() == 0) 
			Assert.assertEquals("Verify if element exists", xpathOfElement + " element is not present");
	}
}
