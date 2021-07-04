package Library;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.prolifics.ProlificsSeleniumAPI;
import FrameWork.DataBase;
import FrameWork.Excel_Reader;
import FrameWork.Generic;
import junit.framework.Assert;

public class AlertManagementPage {
Logger  log;
Generic gen1 = new Generic();
	public AlertManagementPage()
	{
		
		try{PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
		String logFilePath = System.getProperty("user.dir") + "\\ProlificsReports\\";
		gen1.UpdatePropFile("log", logFilePath);
		log = Logger.getLogger(AlertManagementPage.class);
		}
		catch(Exception e)
		{
			System.out.println("Inside Exception");
		}
	}
	public static Excel_Reader excelReader;
	DataBase db = new DataBase();
	ProlificsSeleniumAPI oPSelFW;
	Generic1 gen = new Generic1();
	By AlertSearchLink = By.xpath("//*[@uid='lnk_RT_AlertSearch']/div[2]/div/div/a"); 
	public void clickOnAlertLink(WebDriver driver, ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException
	{
		try{driver.findElement(AlertSearchLink).click();
		Thread.sleep(3000);
		oPSelFW.reportStepDetails("Click on Alert Search Link"  , "Alert Search Link is Clicked", "Pass");
		log.info( "Alert Search Link is Clicked");
		String ScreenTitle = driver.findElement(By.xpath("(//*[@uid='titleMessagePanel'])[2]/div[1]")).getAttribute("aria-label");
		if(ScreenTitle.contains("Alert"))
			oPSelFW.reportStepDetails("Verify if Alert Page is displayed"  , "Alert Search Page is Clicked", "Pass");
		log.info("Alert Search Page is Clicked");
		}
		catch(Exception e)
		{
			oPSelFW.reportStepDetails("Verify if Exception is displayed in Alert Click"  , "Exception is displayed in Alert Click function", "Fail");
			log.error("Exception is displayed in Alert Click function");
			Assert.assertEquals("Verify if Exception is displayed in Alert Click"  , "Exception is displayed in Alert Click function");
		}
	}
	public void searchTheAlertType(WebDriver driver, String AlertType, ProlificsSeleniumAPI oPSelFW) throws InterruptedException, IOException
	{
		int count = 0;
		while(gen.existsElement(driver, By.xpath("//*[starts-with(@id, 'sc_plat_dojo_widgets_ScreenDialogUnderlay_')]")) && count <= 100)
		{
			Thread.sleep(1000);
			count++;
		}
		driver.findElement(By.xpath("//*[@uid='cmb_AlertType']/div[2]/div[1]/div[2]/input[1]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@uid='cmb_AlertType']/div[2]/div[1]/div[2]/input[1]")).sendKeys(AlertType);
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@uid='cmb_AlertType']/div[2]/div[1]/div[2]/input[1]")).sendKeys(Keys.TAB);
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@uid='SST_SearchButton']/span/span/span[3]")).click();
		Thread.sleep(3000);
		count = 0;
		while(gen.existsElement(driver, By.xpath("//*[starts-with(@id, 'sc_plat_dojo_widgets_ScreenDialogUnderlay_')]")) && count <= 30)
		{
			Thread.sleep(1000);
			count++;
		}
		oPSelFW.reportStepDetails("Select the Alert Type"  , AlertType + " is selected ", "Pass");
		log.info( AlertType + " is selected ");
		String NumberOfRecords = driver.findElement(By.xpath("//*[@uid='alertListScreen']/div[1]/div[1]/div[2]/div/div/span")).getText();
		oPSelFW.reportStepDetails("Verify the number of Alerts that are displayed"  , NumberOfRecords + " are displayed", "Pass");
		log.info(NumberOfRecords + " are displayed");
		if(!NumberOfRecords.equals("0 Records"))
		{
			driver.findElement(By.xpath("(//a[@title='Click on the link to open alert summary'])[1]")).click();
			Thread.sleep(3009);
			String ScreenTitle = driver.findElement(By.xpath("(//*[@uid='titleMessagePanel'])[3]/div[1]")).getAttribute("aria-label");
			if(ScreenTitle.contains("Manage Alert"))
				oPSelFW.reportStepDetails("Verify if Alert Page is displayed"  , "Alert Search Page is Clicked", "Pass");
			log.info("Alert Search Page is Clicked");
		}
		else 
			oPSelFW.reportStepDetails("Verify if results are displayed in Alert Search Screen"  , "Alerts are displayed in Alert Search Page", "Pass");
		log.info( "Alerts are displayed in Alert Search Page");
	}
	
	
}
