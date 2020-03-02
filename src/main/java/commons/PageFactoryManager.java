package commons;

import org.openqa.selenium.WebDriver;

import pagesObject.DashboardPageObject;
import pagesObject.LoginPageObject;
import pagesObject.PDFPageObject;

public class PageFactoryManager {
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static DashboardPageObject getDashboardPage(WebDriver driver) {
		return new DashboardPageObject(driver);
	}

	public static PDFPageObject getPdfPage(WebDriver driver) {
		return new PDFPageObject(driver);
	}
}
