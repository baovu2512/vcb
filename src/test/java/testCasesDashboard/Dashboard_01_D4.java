package testCasesDashboard;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageFactoryManager;
import pagesObject.DashboardPageObject;
import pagesObject.LoginPageObject;
import reportConfig.ExtentTestManager;

public class Dashboard_01_D4 extends AbstractTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private DashboardPageObject dashboardPage;
	public static String sUsername = "65000004";
	public static String sPassword = "password";

	@Parameters({ "browser", "url" })
	@BeforeTest
	public void RegisterToSystem(String browserName, String url) {

		driver = openMultiBrowser(browserName, url);

		log.info("PaymentPrecondition: Step 01 - Get Login Page");
		loginPage = PageFactoryManager.getLoginPage(driver);

		log.info("PaymentPrecondition: Step 02 - Verify Login Form displays");
		verifyTrue(loginPage.isLoginFormDisplayed());

		log.info("PaymentPrecondition: Step 03 - Input to 'UserID' and 'Password' textboxes");
		loginPage.sendKeyToUserName(sUsername);
		loginPage.sendKeyToPassword(sPassword);

		log.info("PaymentPrecondition: Step 04 - Click to Login button");
		loginPage.clickToLoginButton();
		dashboardPage = PageFactoryManager.getDashboardPage(driver);

		log.info("PaymentPrecondition: Step 05 - Verify Dashboard page displayed");
		verifyTrue(dashboardPage.isDashboardPageDisplayed());

		log.info("PaymentPrecondition: Step 06 - Select Agent code");
		dashboardPage.inputToDynamicSpanAgentCode(driver, sUsername, sUsername);

	}

	@Test
	public void Verify_Dashboard(Method method) {
		ExtentTestManager.startTest(method.getName(), method.getName());
		System.out.println("1");
		log.info("Dashboard_Verify D4: Step 01 - Query with date 31/12/2019");
		dashboardPage.inputDatePicker("31/12/2019");
//		dashboardPage.clickQueryButton();

		log.info("Dashboard_Verify D4: Step 02 - Scroll into D4 chart");
		verifyTrue(false);
		System.out.println("2");
		captureAnyScreenshot(method.getName());
	}

	@AfterClass(alwaysRun = true)
	public void aftertest() {

		System.out.println("3");
		closeBrowserAndDriver(driver);
	}
}
