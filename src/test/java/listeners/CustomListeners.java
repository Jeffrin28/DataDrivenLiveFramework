package listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

import basepackage.TestBase;
import utilities.MonitoringMail;
import utilities.TestConfig;
import utilities.TestUtil;



public class CustomListeners  extends TestBase implements ITestListener, ISuiteListener{

	public String messagebody;
	public void onTestStart(ITestResult result) {
		// we have to start the testcase as follows
		test=rep.startTest(result.getName().toUpperCase());
		//runmodes
		
		}
		
	

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	test.log(LogStatus.PASS, result.getName().toUpperCase() +"PASS");
	rep.endTest(test);
	rep.flush();
	
	}

	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL, result.getName().toUpperCase() +"Failed with exceptions :" + result.getThrowable());
		//when a testcase is failed we have to add the screenshot as well,the log for it is
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		
		Reporter.log("Click to see Screenshot");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");
		rep.endTest(test);
		rep.flush();
		
		
	}

	public void onTestSkipped(ITestResult result) {
		
		test.log(LogStatus.INFO, result.getName().toUpperCase() +" Skipped the test as the runmode is N"  );
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}



	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}



	public void onFinish(ISuite suite) {
		//to sent reports to mail we need the below class
				MonitoringMail mail=new MonitoringMail();
				
				//to get the localhost address
				
				try {
					messagebody = "http://"+InetAddress.getLocalHost().getHostAddress()+":8383/job/DataDrivenLiveProject/Extent_20Report/";
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(messagebody);
				try {
					mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messagebody);
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
	}

}
