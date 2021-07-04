package Library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import FrameWork.BaseTest;

public class LoginPage {
	Generic gen = new Generic();
	String txtUserName = "//input[@id='idx_form_TextBox_0']";
	String txtPassword = "//input[@id='loginPassword']";
	String btnLogin = "//span[@id='dijit_form_Button_0_label']";
	String Apply = "//span[@id='dijit_form_Button_6_label']";
	String ConceptArrow = "//div[2]/div/div[2]/div[2]/div/div/input";
	
	String ItemId = "//input[@id='idx_form_TextBox_30']";
	String OrderType = "//label[contains(normalize-space(text()),'Select Order Type')]/following::input[position()=1 and contains(@class,'ArrowButtonInner') and @role='presentation']";
	String StoreNumber="//label[contains(normalize-space(text()),'Store Number')]/following::input[position()=1 and contains(@id,'TextBox') and contains(@class,'InputInner')]";
	String Addbtn="//span[contains(@id,'Button') and normalize-space(text())='Add']";
	public void LoginToWCC(WebDriver driver, String UserName, String Password, BaseTest basetest)
			throws InterruptedException {
		try {
			gen.setText(driver, txtUserName, UserName);
			gen.setText(driver, txtPassword, Password);
			gen.clickElement(driver, btnLogin);
			gen.waitUntilElementIsPresent(driver, 500, By.xpath("//span[@class='idxHeaderUserIcon']"));
			if (driver.findElements(By.xpath("//span[@class='idxHeaderUserIcon']")).size() != 0)
				basetest.test.log(Status.PASS, UserName + " user is successfully Logged in");
			else
			{
				basetest.test.log(Status.FAIL, UserName + " user is not successfully Logged in");
				driver.close();
				driver.quit();
				Assert.assertFalse(true);
			}
		} catch (Exception e) {
			basetest.test.log(Status.FAIL, "Exception " + e + " is displayed");
		}
	}
}
