package testCasesDashboard;

import commons.AbstractTest;
import commons.PageFactoryManager;
import java.lang.reflect.Method;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pagesObject.DashboardPageObject;
import pagesObject.LoginPageObject;
import pagesObject.PDFPageObject;

public class PDF_SEARCH extends AbstractTest {

  private WebDriver driver;
  private LoginPageObject loginPage;
  private DashboardPageObject dashboardPage;
  private PDFPageObject pdfPage;
  List<WebElement> kyBaoCao;
  static String sUsername = "superuser";
  static String sPassword = "superpass";

  @Parameters({"browser", "url"})
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
  public void Test_KyBaoCao(Method method) {
    verifyTrue(true);
//    ExtentTestManager.startTest(method.getName(), method.getName());
    log.info("Test_KyBaoCao: Step 01 - Click to PDF Menu side-bar");
    dashboardPage.clickPDFMenu();
    pdfPage = PageFactoryManager.getPdfPage(driver);

    log.info("Test_KyBaoCao: Step 02 - Click to dropdownlist Ky Bao Caor");
    kyBaoCao = pdfPage.clickToKyBaoCaoAndGetAllElement();

    log.info("Test_KyBaoCao: Step 03 - Click to Ten Bao Cao and Query");
    for (int i = 1; i < kyBaoCao.size(); i++) {
      String sDropDownValue = kyBaoCao.get(i).getText();
      log.info("Test_KyBaoCao: Step 04 - Click to Ten Bao Cao and Query");
      pdfPage.selectKyBaoCao(kyBaoCao.get(i));
      pdfPage.clickQueryButton();
      verifyTrue(pdfPage.verifyTenBaoCao(sDropDownValue));
      kyBaoCao = pdfPage.clickToKyBaoCaoAndGetAllElement();
    }


  }

  @AfterClass(alwaysRun = true)
  public void aftertest() {
    closeBrowserAndDriver(driver);
  }
}
