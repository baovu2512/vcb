package testCasesDashboard;

import commons.AbstractTest;
import commons.Constants;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import reportConfig.ExtentTestManager;

public class NewTest extends AbstractTest {

  @Test
  public void test1(Method method) {
    ExtentTestManager.startTest(method.getName(), method.getName());
    log.info("PaymentPrecondition: Step 02 - Verify Login Form displays");
    String url = "https://google.com";
    WebDriver driver = openMultiBrowser("chrome", url);
    driver.get(Constants.VCB_DEV_APP_URL);

  }

  @AfterTest
  public void afterTest() {
    closeBrowserAndDriver(getDriver());

  }
}
