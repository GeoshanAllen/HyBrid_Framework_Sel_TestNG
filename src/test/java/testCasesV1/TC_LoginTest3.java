package testCasesV1;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pagesV1.LoginPage;
import utilitiesV1.XLUtils;

public class TC_LoginTest3 extends Base {
	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd, String testCase) throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(user);
		log.info("user name provided");
		lp.setPassword(pwd);
		log.info("password provided");
		lp.clickLogin();
		System.out.println(testCase);

		Thread.sleep(3000);

		if (isAlertPresent() == true) {
			captureScreen(driver, "loginTest3_" + testCase.toString());
			driver.switchTo().alert().accept();// close alert
			driver.switchTo().defaultContent();
			log.warn("Login failed");
			Assert.assertTrue(false);

		} else {
			Assert.assertTrue(true);
			log.info("Login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();// close logout alert
			driver.switchTo().defaultContent();

		}

	}

	public boolean isAlertPresent() // user defined method created to check alert is presetn or not
	{
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}

	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "./src/test/java/testDataV1/LoginData.xlsx";

		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

		String logindata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);// 1 0
			}

		}
		return logindata;
	}

}
