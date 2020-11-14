package testCasesV1;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilitiesV1.ReadConfig;

public class Base {

	ReadConfig readConf = new ReadConfig();
//	public String url = "http://demo.guru99.com/V4/";
	public String url = readConf.getApplicationURL();

//	public String userName = "mngr293379";
	public String userName = readConf.getUserName();

//	public String userMail = "testdata@test.com";
	public String userMail = readConf.getEmail();

//	public String password = "hudUnEv";
	public String password = readConf.getPassword();

	public static WebDriver driver;
	public static Logger log;

	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {

		log = Logger.getLogger("Base");
		PropertyConfigurator.configure("log4j.properties");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readConf.getChromepath());
			driver = new ChromeDriver();
		}

		if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readConf.getFirePath());
			driver = new FirefoxDriver();
		}

		if (br.equals("ieDriver")) {
			System.setProperty("webdriver.ie.driver", readConf.getIEPath());
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(caps);
		}

		driver.get(url);
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/ScreenShot/" + tname + ".png");
		FileHandler.copy(source, target);
		System.out.println("Screenshot taken");
	}
}
