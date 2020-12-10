package roughpackage;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;


import utilities.MonitoringMail;
import utilities.TestConfig;

public class TestHostAdd {
	
	
	//using the error
	public static void main(String[] args) throws UnknownHostException, AddressException, MessagingException {
		//to sent reports to mail we need the below class
		MonitoringMail mail=new MonitoringMail();
		
		//to get the localhost address
		String messagebody="http://"+InetAddress.getLocalHost().getHostAddress()+":8383/job/DataDrivenLiveProject/Extent_20Report/";
		
		System.out.println(messagebody);
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messagebody);
	}

}
