package testCasesV1;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pagesV1.LoginPage;

public class TC_LoginTest2 extends Base {

	@Test
	public void loginTest2() throws IOException {

		log.info("URL is opened");
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(userName);
		log.info("Entered UserName");
		lp.setPassword(password);
		log.info("Entered Password");
		lp.clickLogin();
		log.info("Login Clicked");
		try {
			if (driver.getTitle().equals("Guru995 Bank Manager HomePage")) {
				Assert.assertTrue(true);
				log.info("Login test passed");
			} else {

				log.info("Login test failed");
				captureScreen(driver, "loginTest2");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			log.info("Login test failed due to : " + e);
			captureScreen(driver, "loginTest2");
			Assert.assertTrue(false);

		}

	}

}
