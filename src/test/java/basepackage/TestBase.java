package basepackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;

import org.apache.log4j.PropertyConfigurator;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoylogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;

	@BeforeSuite
	public void setUp() throws IOException {
		PropertyConfigurator.configure("./DataDrivenFramework/src/test/resources/properties/log4j.properties");
		if (driver == null) {

			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			config.load(fis);
			log.debug("config file loaded");
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			OR.load(fis);
			log.debug("OR file loaded");
		}
		if (config.getProperty("browser").equals("firefox")) {

			driver = new FirefoxDriver();
		} else if (config.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver_new.exe");
			driver = new ChromeDriver();
			log.debug("Chrome launched");
		} else if (config.getProperty("browser").equals("ie")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.get(config.getProperty("testsiteurl"));
		log.debug("Navigated to :" + config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 5);
	}
	public void click(String locator) {
		
		driver.findElement(By.xpath(OR.getProperty(locator))).click();
		test.log(LogStatus.INFO, "Clicking on " +locator);
	}
public void type(String locator,String value) {
		
		driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		test.log(LogStatus.INFO, "Typing in " +locator + "Entering the value as :" +value);
	}

	public boolean isElementPresent(By by) {

		try {
			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
		public static void verifyEquals(String expected,String actual) throws IOException {
			try {
				Assert.assertEquals(actual, expected);
				
			} catch (Throwable t) {
				
				TestUtil.captureScreenshot();
				
				//testng report
				Reporter.log("capture screenshot");
				Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
				//extent report
				test.log(LogStatus.FAIL, "Verification Failed with exceptions :" + t.getMessage());
				test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
			}
			}
		static WebElement dropdown;
			public void select(String locator,String value) {
				
				dropdown=driver.findElement(By.xpath(OR.getProperty(locator)));
				Select select=new Select(dropdown);
				select.selectByVisibleText(value);
				test.log(LogStatus.INFO, "Selecting from dropdown " +locator + "The value as :" +value);
				
			}
			
		
	

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		log.debug("Test execution completed");

	}
}
