package Library;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.prolifics.ProlificsSeleniumAPI;
import FrameWork.Generic;



public class LoginPage1 {
	Logger  log;
	Generic gen1 = new Generic();
		public LoginPage1()
		{
			
			try{PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
			String logFilePath = System.getProperty("user.dir") + "\\ProlificsReports\\";
			gen1.UpdatePropFile("log", logFilePath);
			log = Logger.getLogger(LoginPage1.class);
			}
			catch(Exception e)
			{
				System.out.println("Inside Exception");
			}
		}
	
	public ProlificsSeleniumAPI oPSelFW;

	Generic1 gen = new Generic1();
	String txtUserName = "//input[@id='idx_form_TextBox_0']";
	String txtPassword = "//input[@id='loginPassword']";
	String btnLogin = "//span[@id='dijit_form_Button_0_label']";
	String Apply = "//span[@id='dijit_form_Button_6_label']";
	String ConceptArrow = "//div[2]/div/div[2]/div[2]/div/div/input";

	String ItemId = "//input[@id='idx_form_TextBox_30']";
	String OrderType = "//label[contains(normalize-space(text()),'Select Order Type')]/following::input[position()=1 and contains(@class,'ArrowButtonInner') and @role='presentation']";
	String StoreNumber = "//label[contains(normalize-space(text()),'Store Number')]/following::input[position()=1 and contains(@id,'TextBox') and contains(@class,'InputInner')]";
	String Addbtn = "//span[contains(@id,'Button') and normalize-space(text())='Add']";

	public void LoginToWCC(WebDriver driver, String UserName, String Password,ProlificsSeleniumAPI oPSelFW)
			throws InterruptedException, IOException {
		try {
			gen.setText(driver, txtUserName, UserName);
			gen.setText(driver, txtPassword, Password);
			gen.clickElement(driver, btnLogin);
			gen.waitUntilElementIsPresent(driver, 500, By.xpath("//span[@class='idxHeaderUserIcon']"));
			if (driver.findElements(By.xpath("//span[@class='idxHeaderUserIcon']")).size() != 0) {
				oPSelFW.reportStepDetails("User should be logged in "  , UserName + " user is successfully Logged in ", "Pass");
				log.info(UserName + " user is successfully Logged in ");
			} else {
				oPSelFW.reportStepDetails("User should be logged in "  , UserName + " user is not successfully Logged in ", "Fail");
				log.error(UserName + " user is not successfully Logged in ");
				Assert.assertEquals("User should be logged in "  , UserName + " user is not successfully Logged in ");
			}
		} catch (Exception e) {
			// basetest.test.log(Status.FAIL, "Exception " + e + " is displayed");
			oPSelFW.reportStepDetails("Verify if user is logged in succesfully ",
					" User is not Logged into the WCC Sucessfully", "Fail");
			log.error(" User is not Logged into the WCC Sucessfully");
			driver.close();
			Thread.sleep(100);
			driver.quit();
			Assert.assertEquals("User should be logged in "  , UserName + " user is not successfully Logged in ");
		}
	}
}
