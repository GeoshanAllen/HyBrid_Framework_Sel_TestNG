package utilitiesV1;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties pro;

	public ReadConfig() {
		File src = new File("./Configuration/config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);

		} catch (Exception e) {
			System.out.println("Error Occured " + e.getMessage());
		}
	}

	public String getApplicationURL() {
		String url = pro.getProperty("url");
		return url;
	}

	public String getUserName() {
		String uname = pro.getProperty("userName");
		return uname;
	}

	public String getPassword() {
		String pswd = pro.getProperty("password");
		return pswd;
	}

	public String getChromepath() {
		String cpath = pro.getProperty("chromePath");
		return cpath;
	}

	public String getFirePath() {
		String fpath = pro.getProperty("firefoxPath");
		return fpath;
	}

	public String getIEPath() {
		String iepath = pro.getProperty("internetExplorerPath");
		return iepath;
	}

	public String getEmail() {
		String email = pro.getProperty("userMail");
		return email;
	}

}
