package testCases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basepackage.TestBase;
import utilities.TestUtil;

public class OpenAccountTest extends TestBase {
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void openAccountTest(Hashtable<String,String> data) throws InterruptedException {
		
		//to skip the whole tests 
		if(!TestUtil.isTestRunnable("openAccountTest", excel)) {
			throw new SkipException("Skipping the test openAccountTest as the run mode is N");
			
			
		}
		click("openaccount");
		//dropdownlist --use 'select' class
		select("customer", data.get("customer"));
		select("currency", data.get("currency"));
		click("process");
		Thread.sleep(3000);
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

	
	}

