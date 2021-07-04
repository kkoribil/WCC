package Library;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import FrameWork.BaseTest;

public class OrderAppeasementsPage {
	Generic gen = new Generic();
	String AppeaseCustomer = "//*[@uid='customerAppeasement']/div[2]/div/div/a";
	String AppeaseOrder = "//*[@uid='extn_apply_appeasement_to']/div[2]/div/div[2]/div/input";
	String NextBtn = "(//span[contains(text(),'Next')])[2]";
	String ErrMessage = "//*[@uid='singlemessagelabel']";
	String Reason = "//*[@uid='CustomerAppeasementSelection']/div/div[3]/div/div/div[2]/input[1]";
	
	public void subAppeasementWithNoReasonCode(WebDriver driver, BaseTest basetest) throws InterruptedException
	{
		gen.clickElement(driver, AppeaseCustomer, "Appease Customer", basetest);
		gen.waitnclickElement(driver, By.xpath(AppeaseOrder), "Apply Appeasement Order", basetest);
		Thread.sleep(1000);
		gen.waitnclickElement(driver, By.xpath(NextBtn), "Next", basetest);
		Thread.sleep(5000);
		String ErrorMessage = driver.findElement(By.xpath(ErrMessage)).getText();
		if(ErrorMessage.contains("There are errors on the page. You cannot proceed until you fix them.")) 
			basetest.test.log(Status.PASS, "There are errors on the page. You cannot proceed until you fix them.");
	}
	public void enterAppeaseTotalGreaterThanOrderTotal(WebDriver driver, BaseTest basetest) throws InterruptedException
	{
		gen.waitUntilElementIsPresent(driver, Reason);
		gen.setTextAndHitTab(driver, Reason, "DEFECTIVE", "Reason Code", basetest);
		gen.setTextAndHitTab(driver, "//*[@uid='extn_subreason']/div[2]/div/div[2]/input[1]", " Fabric issue", "Sub Reason Code", basetest);
		gen.clickElement(driver, "//*[@uid='OLST_listGrid']/div[3]/div[4]/div/table/tbody/tr/td/span", "Select Item", basetest);
		gen.waitnclickElement(driver, By.xpath("(//span[contains(text(),'Next')])[2]"), "xpath", basetest);
		Thread.sleep(5000);
		if(driver.findElements(By.xpath("//*[@uid='txtNoteText']/div[2]/div/textarea")).size() > 0) 
		{
			String OrderTotal = driver.findElement(By.xpath("//*[@uid='extn_order_total']/div[2]/div[1]/div/span")).getText();
			String OrderTotalInUI = OrderTotal.substring(1, OrderTotal.length());
			Double OrderTot = Double.parseDouble(OrderTotalInUI);
			OrderTot = OrderTot + 5.0;
			driver.findElement(By.xpath("(//*[@uid='extn_txt_DiscountAmount']/div[2]/div/div/input)[1]")).clear();
			driver.findElement(By.xpath("(//*[@uid='extn_txt_DiscountAmount']/div[2]/div/div/input)[1]")).sendKeys(String.valueOf(OrderTot));
			driver.findElement(By.xpath("(//*[@uid='extn_txt_DiscountAmount']/div[2]/div/div/input)[1]")).sendKeys(String.valueOf(Keys.TAB));
			driver.findElement(By.xpath("//*[@uid='extn_txt_DiscountAmount']/div[2]/div[3]/div")).click();
			String ErrorMessage = driver.findElement(By.xpath("//*[@id='idx_widget__MasterHoverHelpTooltip_0']/div[2]/div")).getText();
			if(ErrorMessage.contains("Dollar amount cannot exceed order total")) 
				basetest.test.log(Status.PASS, "Error message is displayed");
		}
	}
	public void submitOrderAppeasementDetails(WebDriver driver, BaseTest basetest) throws InterruptedException
	{
		if(driver.findElements(By.xpath("//*[@uid='txtNoteText']/div[2]/div/textarea")).size() > 0) 
		{
			String OrderTotal = driver.findElement(By.xpath("//*[@uid='extn_order_total']/div[2]/div[1]/div/span")).getText();
			driver.findElement(By.xpath("(//*[@uid='extn_txt_DiscountAmount']/div[2]/div/div/input)[1]")).clear();
			driver.findElement(By.xpath("(//*[@uid='extn_txt_DiscountAmount']/div[2]/div/div/input)[1]")).sendKeys(OrderTotal.substring(1, OrderTotal.length()));
			driver.findElement(By.xpath("(//*[@uid='extn_txt_DiscountAmount']/div[2]/div/div/input)[1]")).sendKeys(Keys.TAB);
			if(driver.findElements(By.xpath(("(//span[contains(text(),'Ok')])[2]"))).size() > 0)
				gen.waitnclickElement(driver, By.xpath("(//span[contains(text(),'Ok')])[2]"), "confirm", basetest);
			else if(driver.findElements(By.xpath(("(//span[contains(text(),'Ok')])[1]"))).size() > 0)
				gen.waitnclickElement(driver, By.xpath("(//span[contains(text(),'Ok')])[1]"), "Ok", basetest);
			Thread.sleep(5000);
			gen.waitnclickElement(driver, By.xpath("(//span[contains(text(),'Confirm')])[2]"), "Ok", basetest);
			basetest.test.log(Status.PASS, "Order Details are appeased successfully");
		}
	}
}
