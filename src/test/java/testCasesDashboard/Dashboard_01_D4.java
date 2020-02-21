package testCasesDashboard;

import java.lang.reflect.Method;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;
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
	public static String sUsername = "superuser";
	public static String sPassword = "superpass";

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


	}

	@Test
	public void Verify_Dashboard(Method method) {
		verifyTrue(true);
		ExtentTestManager.startTest(method.getName(),method.getName());
		log.info("PaymentPrecondition: Step 01 - Click to PDF Menu side-bar");
		dashboardPage.clickPDFMenu();
		Boolean trungancuc = true;

		if(trungancuc)
		{
			System.out.println("Trung An Cuc");
		}
	}

	@AfterClass(alwaysRun = true)
	public void aftertest() {
		closeBrowserAndDriver(driver);
	}
}
