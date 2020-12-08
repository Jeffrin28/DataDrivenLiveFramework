package testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import basepackage.TestBase;

public class BankManagerLoginTest extends TestBase {

	@Test
	public void bankManagerLoginTest() throws InterruptedException, IOException {

		//verifyEquals("abc","xyz");
		Thread.sleep(3000);
		log.debug("Inside Login Test");
		click("bmlBtn");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);
		
		//Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("addCustBtn"))), "login not successful");

		log.debug("Login successful");
		//Assert.fail("login not successful");
	}

}
