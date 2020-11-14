package testCasesV1;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import pagesV1.LoginPage;

public class TC_LoginTest001 extends Base {

	@Test
	public void loginTest() throws IOException, InterruptedException {

		log.info("URL is opened");

		LoginPage lp = new LoginPage(driver);
		lp.setUsername(userName);
		log.info("Entered UserName");
		lp.setPassword(password);
		log.info("Entered Password");
		lp.clickLogin();
		log.info("Login Clicked");
//		TargetLocator switchTo = driver.switchTo();
//		Options manage = driver.manage();
		Set<Cookie> cookies = driver.manage().getCookies();
		System.out.println(cookies);
		Thread.sleep(5000);

		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {

			Assert.assertTrue(true);
			log.info("Login test passed");
		} else {

			log.info("Login test failed");
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
		}
		if (driver.getTitle().equals("Guru Bank Manager HomePage")) {
			Assert.assertTrue(true);
			log.info("Login test passed");
		} else {

			log.info("Login test failed");
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
		}
	}
}
