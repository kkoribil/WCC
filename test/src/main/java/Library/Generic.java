package Library;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.DateUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import FrameWork.BaseTest;

import org.testng.Assert;
public class Generic
{	
	String msgDialog = "//*[@id='msgDialog']";
	/* 
	 * 	@Descriptions  -- Selects the Link and SubLine 
		@param  driver -- WebDriver parameter
		@param  LinkName -- Name of the Menu Link
		@param  SubLinkName -- Name of the Sub Menu Link
		@param basetest  -- Report steps
	*/
	public void selectMainLink(WebDriver driver, String LinkName, String SubLinkName, BaseTest basetest) throws InterruptedException
	{
		try {
		/*driver.findElement(By.xpath("//a[text()='"+LinkName+"']")).click();
		basetest.test.log(Status.PASS, LinkName + " is clicked");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[text()='"+SubLinkName+"']")).click();
		basetest.test.log(Status.PASS, SubLinkName + " is clicked");
		Thread.sleep(4000);*/
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			// Click on Itemmaintenance
			WebElement ItemMaintenance = driver.findElement(By.xpath("//a[contains(text(),'"+LinkName+"')]"));
			executor.executeScript("arguments[0].click();", ItemMaintenance);
			Actions action = new Actions(driver);
			action.moveToElement(ItemMaintenance).build().perform();
			ItemMaintenance.click();
			Thread.sleep(5000);
			// Select shipping
			WebElement pricing = driver.findElement(By.xpath("//a[text()='"+SubLinkName+"']"));
			executor.executeScript("arguments[0].click();", pricing);
			Thread.sleep(10000);
		}
		catch(Exception e)
		{
			basetest.test.log(Status.FAIL, " Exception :" + e + " is displayed");
		}
	}
	/* 
	 * 	@Descriptions  -- Verifies if dialog box is displayed
		@param  driver -- WebDriver parameter
		@param  TextInDialog -- Text displayed in the dialog box
		@param basetest  -- Report steps
	*/
	public boolean verifyIfDialogIsDisplayed(WebDriver driver, String TextInDialog, BaseTest basetest) throws InterruptedException
	{
		try {
			int NumberOfDialogBoxes = driver.findElements(By.xpath(msgDialog)).size();
			boolean DialogDisplayedWithSpeMsg = false;
			if(driver.findElements(By.xpath("//*[@id='msgDialog']")).size() != 0) 
			{	
				String DialogText = driver.findElement(By.xpath("("+msgDialog+"/pre)["+NumberOfDialogBoxes+"]")).getText();
				if(DialogText.trim().contains("Blank request can not be processed") == false && DialogText.trim().length() > 0 )
				{
					if(DialogText.trim().indexOf(TextInDialog) == 0)
					{
						basetest.test.log(Status.PASS, "<span style='font-weight:bold;color:blue'>"+TextInDialog + "</span> dialog box is displayed" );
						DialogDisplayedWithSpeMsg = true;
						Thread.sleep(2000);
					}
					else 
					{
						basetest.test.log(Status.FAIL, "<span style='font-weight:bold;color:blue'>"+TextInDialog + "</span> dialog box not is displayed");
						DialogDisplayedWithSpeMsg = false;
					}	
				}
				/*if(DialogText.trim().contains("Blank request can not be processed"))
				{
					if(NumberOfDialogBoxes == 1)
					{
						System.out.println(driver.findElement(By.xpath("//span[contains(text(), 'Ok')]")).isDisplayed());
						driver.findElement(By.xpath("//span[contains(text(), 'Ok')]")).click();
					}
					else
					{
						System.out.println(driver.findElement(By.xpath("(//span[contains(text(), 'Ok')])["+NumberOfDialogBoxes+"]")).isDisplayed());
						driver.findElement(By.xpath("(//span[contains(text(), 'Ok')])["+NumberOfDialogBoxes+"]")).click();
					}
					Thread.sleep(6000);
				}
				else if(DialogText.trim().length() != 0)
				{
					driver.findElement(By.xpath("(//span[contains(text(), 'Ok')])["+NumberOfDialogBoxes+"]")).click();
					Thread.sleep(6000);
				}*/
				driver.switchTo().activeElement().click();
				Thread.sleep(2000);
			}
			return(DialogDisplayedWithSpeMsg);
			}
			catch(Exception e)
			{
				basetest.test.log(Status.FAIL, " Exception :" + e + " is displayed");
			}
		return(false);
	}
	/* 
	 * 	@Descriptions  -- Gets the error message in the dialog box
		@param  driver -- WebDriver parameter
		@param basetest  -- Report steps
	*/
	public String getErrorMessageINDialog(WebDriver driver, BaseTest basetest) throws InterruptedException
	{
		try
		{
			int NumberOfDialogBoxes = driver.findElements(By.xpath("//*[@id='msgDialog']")).size();
			boolean DialogDisplayedWithSpeMsg = false;
			String DialogText = "";
			if(driver.findElements(By.xpath("//*[@id='msgDialog']")).size() != 0) 
			{	
				DialogText = driver.findElement(By.xpath("(//*[@id='msgDialog']/pre)["+NumberOfDialogBoxes+"]")).getText();
				if(DialogText.trim().length() > 0)
				{
					driver.findElement(By.xpath("(//span[contains(text(), 'Ok')])["+NumberOfDialogBoxes+"]")).click();
					Thread.sleep(6000);
				}
				return(DialogText);
			}
			return(DialogText);
		}
		catch(Exception e)
		{
			basetest.test.log(Status.FAIL, "Exception "+ e + " is displayed");
		}
		return("");
	}
	/* 
	 * 	@Descriptions  -- Gets the error message in the dialog box
		@param  driver -- WebDriver parameter
		@param basetest  -- Report steps
	*/
	public String getErrorMsgINDialog(WebDriver driver, BaseTest basetest) throws InterruptedException
	{
		try
		{
			int NumberOfDialogBoxes = driver.findElements(By.xpath("//*[@id='msgDialog']/pre")).size();
			boolean DialogDisplayedWithSpeMsg = false;
			String DialogText = "";
			if(driver.findElements(By.xpath(msgDialog)).size() != 0) 
			{	
				DialogText = driver.findElement(By.xpath("(//*[@id='msgDialog']/pre)["+NumberOfDialogBoxes+"]")).getText();
				driver.findElement(By.xpath("(//span[contains(text(), 'Ok')]["+NumberOfDialogBoxes+"])")).click();
				Thread.sleep(6000);
				return(DialogText);
			}
			return(DialogText);
		}
		catch(Exception e)
		{
			basetest.test.log(Status.FAIL, "Exception "+ e + " is displayed");
		}
		return("");
	}
	/* 
	 * 	@Descriptions  -- Clicks on the Accept Button
		@param  driver -- WebDriver parameter
		@param basetest  -- Report steps
	*/
	public void clickOnAcceptBtn(WebDriver driver, BaseTest basetest) throws InterruptedException
	{
		try
		{
			int NumberOfDialogBoxes = driver.findElements(By.xpath("//span[contains(text(), 'Accept')]")).size();
			boolean DialogDisplayedWithSpeMsg = false;
			String DialogText = "";
			if(driver.findElements(By.xpath("//span[contains(text(), 'Accept')]")).size() != 0) 
			{	
				driver.findElement(By.xpath("(//span[contains(text(), 'Accept')])["+NumberOfDialogBoxes+"]")).click();
				Thread.sleep(6000);
			}
		}
		catch(Exception e)
		{
			basetest.test.log(Status.FAIL, "Exception "+ e + " is displayed");
		}
	}
	/* 
	 * 	@Descriptions  -- Clicks on the OK Button
		@param  driver -- WebDriver parameter
		@param basetest  -- Report steps
	*/
	public void clickOnOKBtn(WebDriver driver, BaseTest basetest) throws InterruptedException
	{
		try
		{
			int NumberOfDialogBoxes = driver.findElements(By.xpath("//span[contains(text(), 'OK')]")).size();
			boolean DialogDisplayedWithSpeMsg = false;
			String DialogText = "";
			if(driver.findElements(By.xpath("//span[contains(text(), 'OK')]")).size() != 0) 
			{	
				String DelConfMsg = driver.findElement(By.xpath("(//*[@id='delConf'])["+NumberOfDialogBoxes+"]")).getText();
				if(DelConfMsg.length() > 0)
				{	
					driver.findElement(By.xpath("(//span[contains(text(), 'OK')])["+NumberOfDialogBoxes+"]")).click();
					Thread.sleep(6000);
				}
			}
		}
		catch(Exception e)
		{
			basetest.test.log(Status.FAIL, "Exception "+ e + " is displayed");
		}
	}
	/* 
	 * 	@Description - 	LogsOut the MDM Application
		@param  driver -- WebDriver parameter*/
	public void fnLogOutApp(WebDriver driver, BaseTest basetest) throws InterruptedException
	{
		driver.switchTo().parentFrame();
		if(!driver.findElements(By.xpath("//a[contains(text(),'Logout')]")).isEmpty()) 
		{
			clickElement(driver, "//a[contains(text(),'Logout')]");
			Thread.sleep(2000);
			clickElement(driver, "//span[contains(.,'Yes')]");
			Thread.sleep(4000);
			basetest.test.log(Status.PASS, "User is successfully Logged out of MDM Application");
			driver.close();
		}
	}
	/* 
	 * 	@Descriptions  -- Sets  the text in the Web Element
		@param  driver -- WebDriver parameter
		@param xpathExpress  -- xpath of the Web Element
		@param Val2Set 	   -- Value to set in the Web Element
	*/
	public void setText(WebDriver driver, String xpathExpress, String Val2Set)
	{
		if(!driver.findElements(By.xpath(xpathExpress)).isEmpty()) 
		{
			driver.findElement(By.xpath(xpathExpress)).clear();
			driver.findElement(By.xpath(xpathExpress)).sendKeys(Val2Set);
			System.out.println("setText "+ xpathExpress);
		}
		else
		{
			System.out.println("setText not "+ xpathExpress);
			driver.close();
			driver.quit();
			Assert.assertNotEquals(0, driver.findElements(By.xpath(xpathExpress)).size());
		}
	}
	/* 
	 * 	@Descriptions  -- Sets  the text in the Web Element
		@param  driver -- WebDriver parameter
		@param xpathExpress  -- xpath of the Web Element
		@param Val2Set 	   -- Value to set in the Web Element
	*/
	public void setText(WebDriver driver, String xpathExpress, String Val2Set, String FieldName, BaseTest basetest)
	{
		if(!driver.findElements(By.xpath(xpathExpress)).isEmpty()) 
		{
			driver.findElement(By.xpath(xpathExpress)).clear();
			driver.findElement(By.xpath(xpathExpress)).sendKeys(Val2Set);
			basetest.test.log(Status.PASS, Val2Set + " is set to " + FieldName);
			System.out.println("setText "+ xpathExpress);
		}
		else
		{
			System.out.println("setText not "+ xpathExpress);
			basetest.test.log(Status.FAIL, xpathExpress + " does not exists");
			basetest.extent.flush();
			driver.close();
			driver.quit();
			Assert.assertNotEquals(0, driver.findElements(By.xpath(xpathExpress)).size());
			
		}
		
	}
	/* 
	 * 	@Descriptions  -- Sets  the text in the Web Element
		@param  driver -- WebDriver parameter
		@param xpathExpress  -- xpath of the Web Element
		@param Val2Set 	   -- Value to set in the Web Element
	*/
	public void setTextAndHitEnter(WebDriver driver, String xpathExpress, String Val2Set, String FieldName, BaseTest basetest)
	{
		if(!driver.findElements(By.xpath(xpathExpress)).isEmpty())
		{
			driver.findElement(By.xpath(xpathExpress)).clear();
			driver.findElement(By.xpath(xpathExpress)).sendKeys(Val2Set);
			driver.findElement(By.xpath(xpathExpress)).sendKeys(Keys.ENTER);
			basetest.test.log(Status.PASS, Val2Set + " is set to " + FieldName);
			System.out.println("setText "+ xpathExpress + " exists");
		}
		else
		{
			System.out.println("setText not "+ xpathExpress);
			basetest.test.log(Status.FAIL, xpathExpress + " does not exists");
			basetest.extent.flush();
			driver.close();
			driver.quit();
			Assert.assertNotEquals(0, driver.findElements(By.xpath(xpathExpress)).size());
			
		}
	}
	/* 
	 * 	@Descriptions  -- Sets  the text in the Web Element
		@param  driver -- WebDriver parameter
		@param xpathExpress  -- xpath of the Web Element
		@param Val2Set 	   -- Value to set in the Web Element
	*/
	public void setTextAndHitTab(WebDriver driver, String xpathExpress, String Val2Set, String FieldName, BaseTest basetest)
	{
		if(!driver.findElements(By.xpath(xpathExpress)).isEmpty())
		{
			driver.findElement(By.xpath(xpathExpress)).clear();
			driver.findElement(By.xpath(xpathExpress)).sendKeys(Val2Set);
			driver.findElement(By.xpath(xpathExpress)).sendKeys(Keys.TAB);
			basetest.test.log(Status.PASS, Val2Set + " is set to " + FieldName);
			System.out.println(xpathExpress + " exists");
		}
		else
		{
			basetest.test.log(Status.FAIL, xpathExpress + " does not exists");
			driver.close();
			basetest.extent.flush();
			driver.quit();
			System.out.println(xpathExpress + " not exists");
			Assert.assertNotEquals(0, driver.findElements(By.xpath(xpathExpress)).size());			
		}
	}

	/* 
	 * 	@Descriptions  -- Clicks on the Web Element
		@param  driver -- WebDriver parameter
		@param xpathExpress  -- xpath of the Web Element
	*/
	public void clickElement(WebDriver driver, String xpathExpress) throws InterruptedException
	{
		try {
			if(!driver.findElements(By.xpath(xpathExpress)).isEmpty())
			{	
				driver.findElement(By.xpath(xpathExpress)).click();
				Thread.sleep(5000);
				System.out.println(xpathExpress + " exists");
			}
			else
			{
				System.out.println(xpathExpress + " not exists");
				driver.close();
				driver.quit();
				Assert.assertTrue(false);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	/* 
	 * 	@Descriptions  -- Clicks on the Web Element
		@param  driver -- WebDriver parameter
		@param xpathExpress  -- xpath of the Web Element
	*/
	public void clickElement(WebDriver driver, String xpathExpress, String NameOfButton, BaseTest basetest) throws InterruptedException
	{
		try {
			if(!driver.findElements(By.xpath(xpathExpress)).isEmpty())
			{
				driver.findElement(By.xpath(xpathExpress)).click();
				Thread.sleep(5000);
				basetest.test.log(Status.PASS, NameOfButton + " button is clicked");
				System.out.println(xpathExpress + " exists");
			}
			else
			{
				basetest.test.log(Status.FAIL, xpathExpress + " does not exists");
				System.out.println(xpathExpress + " not exists");
				basetest.extent.flush();
				driver.close();
				driver.quit();
				Assert.assertTrue(false);
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	/* 
	 * 	@Descriptions  -- Gets the value displayed on the UI for the Web Element
		@param  driver -- WebDriver parameter
		@param xpathExpress  -- xpath of the Web Element
	*/
	public String getValueOfElement(WebDriver driver, String xpathExpress)
	{
		if(!driver.findElements(By.xpath(xpathExpress)).isEmpty())
		{
			System.out.println(xpathExpress + " exists");
			return(driver.findElement(By.xpath(xpathExpress)).getAttribute("getValue"));
		}
		else
		{
			System.out.println(xpathExpress + " not exists");
			driver.close();
			driver.quit();
			Assert.assertNotEquals(0, driver.findElements(By.xpath(xpathExpress)).size());			
		}
		return "";
	}
	/* 
	 * 	@Descriptions  -- Gets the text of the Web Element
		@param  driver -- WebDriver parameter
		@param xpathExpress  -- xpath of the Web Element
	*/
	public String getTextOfElement(WebDriver driver, String xpathExpress)
	{
		if(!driver.findElements(By.xpath(xpathExpress)).isEmpty())
		{
			System.out.println(xpathExpress + " exists");
			return(driver.findElement(By.xpath(xpathExpress)).getText());
		}
		else
		{
			System.out.println(xpathExpress + " not exists");
			driver.close();
			driver.quit();
			Assert.assertNotEquals(0, driver.findElements(By.xpath(xpathExpress)).size());
		}
		return "";
	}
	/* 
	 * 	@Descriptions  -- Select the value in the Dropdown 
		@param  driver -- WebDriver parameter
		@param xpathExpress  -- xpath of the Web Element
		@param Val2Select - Value to select in the Web Element
	*/
	public void selectElementVal(WebDriver driver, String xpathExpress, String Val2Select)
	{
		if(!driver.findElements(By.xpath(xpathExpress)).isEmpty())
		{
			Select dropdown = new Select(driver.findElement(By.xpath(xpathExpress)));
			dropdown.selectByVisibleText(Val2Select);
			System.out.println(xpathExpress + " exists");
		}
		else
		{
			System.out.println(xpathExpress + " not exists");
			driver.close();
			driver.quit();
			Assert.assertTrue(false);
		}
	}
	/* 
	 * 	@Descriptions  -- Launch the URL 
		@param  driver -- WebDriver parameter
		@param URL  -- URL to be launched
	*/
	public boolean launchURL(WebDriver driver, String URL, BaseTest basetest) throws InterruptedException
	{
		driver.get(URL);
		Thread.sleep(1000);
		if(driver.getTitle().contains("Williams-Sonoma Call Center UI")) 
		{
			By txtUserName = By.xpath("//input[@id='idx_form_TextBox_0']");
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(txtUserName));
			driver.manage().window().maximize();
			basetest.test.log(Status.PASS, URL + " is successfully Launched");
			return true;
		}
		else if(driver.getTitle().contains("Application Console"))
		{
			By txtUserName = By.xpath("//input[@name='UserId']");
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(txtUserName));
			driver.manage().window().maximize();
			basetest.test.log(Status.PASS, URL + " is successfully Launched");
			return true;
		}
		else
		{
			basetest.test.log(Status.FAIL, URL + " is not Launched");
			return false;
		}
		
	}
	/* 
	 * 	@Descriptions  -- Converts Integer to Dae 
		@param  DateInInt -- Date value in the form of Integer
	*/
	public String convertIntToDate(String DateInInt)
	{
		Date javaDate= DateUtil.getJavaDate((double) Double.parseDouble(DateInInt));
	    return(new SimpleDateFormat("MM/dd/yyyy").format(javaDate));
	}

	public boolean verifyDialogIsDisplayed(WebDriver driver, String TextInDialog, BaseTest basetest) throws InterruptedException
	{
		try {
			int NumberOfDialogBoxes = driver.findElements(By.xpath(msgDialog)).size();
			boolean DialogDisplayedWithSpeMsg = false;
			if(driver.findElements(By.xpath("//*[@id='msgDialog']")).size() != 0) 
			{	
				String DialogText = driver.findElement(By.xpath(msgDialog)).getText();
				if(DialogText.trim().contains("Blank request can not be processed") == false && DialogText.trim().length() > 0 )
				{
					if(DialogText.trim().indexOf(TextInDialog) == 0)
					{
						basetest.test.log(Status.PASS, "<span style='font-weight:bold;color:blue'>"+TextInDialog + "</span> dialog box is displayed" );
						DialogDisplayedWithSpeMsg = true;
					}
					else 
					{
						basetest.test.log(Status.FAIL, "<span style='font-weight:bold;color:blue'>"+TextInDialog + "</span> dialog box not is displayed");
						DialogDisplayedWithSpeMsg = false;
					}	
				}
			}
			return(DialogDisplayedWithSpeMsg);
			}
			catch(Exception e)
			{
				basetest.test.log(Status.FAIL, " Exception :" + e + " is displayed");
			}
		return(false);
	}
	public void shortwaitnclickElement(WebDriver driver, By xPathOfElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xPathOfElement));
		wait.until(ExpectedConditions.elementToBeClickable(xPathOfElement));
		WebElement element = driver.findElement(xPathOfElement);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		if(driver.findElements(xPathOfElement).size() > 0)
			executor.executeScript("arguments[0].click()", element);
		else
			Assert.assertNotEquals(0, driver.findElements(xPathOfElement).size());
	}
	public void waitnclickElement(WebDriver driver, By xPathOfElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xPathOfElement));
		wait.until(ExpectedConditions.elementToBeClickable(xPathOfElement));
		WebElement element = driver.findElement(xPathOfElement);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		if(driver.findElements(xPathOfElement).size() > 0)
			executor.executeScript("arguments[0].click()", element);
		else
			Assert.assertNotEquals(0, driver.findElements(xPathOfElement).size());
	}
	public void waitnclickElement(WebDriver driver, By xPathOfElement, String NameOfField, BaseTest basetest)
	{
		System.out.println("wait for element"+NameOfField);
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xPathOfElement));
		wait.until(ExpectedConditions.elementToBeClickable(xPathOfElement));
		WebElement element = driver.findElement(xPathOfElement);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		if(!driver.findElements(xPathOfElement).isEmpty())
		{
			executor.executeScript("arguments[0].click()", element);
			basetest.test.log(Status.PASS, NameOfField + " is clicked");
		}
		else
		{
			basetest.test.log(Status.FAIL, xPathOfElement + " element doesn't exists");
			basetest.extent.flush();
			driver.close();
			driver.quit();
			Assert.assertNotEquals(0, driver.findElements(xPathOfElement).size());
		}
	}
	public void closeTheOrder(WebDriver driver, BaseTest basetest) throws InterruptedException
	{
		try
		{
			if(driver.findElements(By.xpath("//span[contains(text(),'OK')]")).size() > 0)
			{
				waitnclickElement(driver, By.xpath("//span[contains(text(),'OK')]"), "OK", basetest);
				Thread.sleep(500);
			}
			if(driver.findElements(By.xpath("//*[@uid='isccsContainer']/div/div/div[4]/div/div[3]/span[3]")).size() > 0)
			{
				waitnclickElement(driver, By.xpath("//*[@uid='isccsContainer']/div/div/div[4]/div/div[3]/span[3]"), "Order Close", basetest);
				Thread.sleep(1000);
				if(driver.findElements(By.xpath("((//*[@uid='Popup_btnNext']/span/span)[1]/span)[3]")).size() > 0)
				{
					waitnclickElement(driver, By.xpath("((//*[@uid='Popup_btnNext']/span/span)[1]/span)[3]"), "OK", basetest);
					Thread.sleep(5000);
				}
			}
		}
		catch(Exception e)
		{
			
		}
	}
	public String getPropertyValue(String propName) throws IOException
	{
		String propValue = "";
		try{InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
		Properties prop = new Properties();
        prop.load(input);
        propValue = prop.getProperty(propName);
        return(propValue);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return(propValue);
	}
	
	public String getOrderNumber(String Order) throws IOException
	{
		String propValue = "";
		try{InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\resources\\Order.properties");
		Properties prop = new Properties();
        prop.load(input);
        propValue = prop.getProperty(Order);
        return(propValue);
		}
		
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		return(propValue);
	}
	public void waitUntilElementIsPresent(WebDriver driver, String xpathOfElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathOfElement)));
		if(driver.findElements(By.xpath(xpathOfElement)).size() == 0) 
			Assert.assertEquals("Verify if element exists", xpathOfElement + " element is not present");
	}
	public void waitUntilElementIsPresent(WebDriver driver, int webDriverWait, String xpathOfElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, webDriverWait);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathOfElement)));
		if(driver.findElements(By.xpath(xpathOfElement)).size() == 0) 
			Assert.assertEquals("Verify if element exists", xpathOfElement + " element is not present");
	}
	public void waitUntilElementIsPresent(WebDriver driver, int webDriverWait, By xpathOfElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, webDriverWait);
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
}
