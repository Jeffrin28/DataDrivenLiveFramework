package roughpackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Testproperties {

	public static void main(String[] args) throws IOException {
		
		Properties config=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
		config.load(fis);
	}
}
