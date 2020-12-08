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

public class AddCustomerTest extends TestBase {
	
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void addCustomerTest(Hashtable<String,String> data) throws InterruptedException {
		//to skip a single data in a testcase 

		if(!data.get("runmode").equals("Y")){
			throw new SkipException("Skipping the test openAccountTest as the run mode is N");
		}
		
		click("addCustBtn");
		type("firstName",data.get("firstName"));
		type("lastName",data.get("lastName"));
		type("postCode",data.get("postCode"));
	    click("addBtn");
	    Thread.sleep(3000);
	   Alert alert=wait.until(ExpectedConditions.alertIsPresent());
	   Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
	   
	   	alert.accept();
	   	Thread.sleep(3000);
	}

	
	}

