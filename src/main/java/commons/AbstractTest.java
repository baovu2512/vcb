package commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AbstractTest {

	private WebDriver driver;
	protected final Log log;
	private final String workingDir = System.getProperty("user.dir");

	public AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

	public WebDriver getDriver() {
		return driver;
	}

	protected WebDriver openMultiBrowser(String browserName, String url) {

		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, workingDir + "\\FirefoxLog.txt");
			FirefoxProfile profile = new FirefoxProfile();
			FirefoxOptions options = new FirefoxOptions();
			profile.setAcceptUntrustedCertificates(false);
			profile.setAssumeUntrustedCertificateIssuer(true);
			profile.setPreference("dom.webnotifications.enabled", false);
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("browser.download.dir", "C:\\Downloads");
			profile.setPreference("browser.download.downloadDir", "C:\\Downloads");
			profile.setPreference("browser.download.defaultFolder", "C:\\Downloads");
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "pdf");
			options.addPreference("pdfjs.enabledCache.state", false);

			options.setProfile(profile);
			driver = new FirefoxDriver(options);

		} else if (browserName.equalsIgnoreCase("firefoxheadless")) {
			WebDriverManager.firefoxdriver().setup();
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, workingDir + "\\FirefoxLog.txt");
			FirefoxProfile profile = new FirefoxProfile();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			profile.setAcceptUntrustedCertificates(false);
			profile.setAssumeUntrustedCertificateIssuer(true);
			profile.setPreference("dom.webnotifications.enabled", false);
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("browser.download.dir", "C:\\Downloads");
			profile.setPreference("browser.download.downloadDir", "C:\\Downloads");
			profile.setPreference("browser.download.defaultFolder", "C:\\Downloads");
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"text/anytext,text/plain,text/html,application/plain");

			options.setProfile(profile);
			driver = new FirefoxDriver(options);

		} else if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--incognito");
			option.addArguments("--disable-extensions");
			option.addArguments("--disable-infobars");
			driver = new ChromeDriver(option);

		} else if (browserName.equalsIgnoreCase("chromeheadless")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			option.addArguments("headless");
			option.addArguments("window-size=1366x768");
			driver = new ChromeDriver(option);

		} else if (browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();

		}

		driver.manage().timeouts().implicitlyWait(Constants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		if (url.equalsIgnoreCase("vcbchart")) {
			driver.get(Constants.VCB_DEV_APP_URL);
		} else if (url.equalsIgnoreCase("vcb")) {
			driver.get(Constants.VCB_FRONT_END_URL);
		} else if (url.equalsIgnoreCase("vcb")) {
			driver.get(Constants.VCB_BACK_END_URL);
		} else if (url.equalsIgnoreCase("uploadUrl")) {
			driver.get(Constants.UPLOAD_URL);
		}

		if (driver.toString().toLowerCase().contains("internet explorer")) {
			sleepTimeInSecond(5);
		}
		return driver;
	}

	private boolean checkPassed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true)
				log.info("===PASSED===");
			else
				log.info("===FAILED===");
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add failure to ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkPassed(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false)
				log.info("===PASSED===");
			else
				log.info("===FAILED===");
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		boolean status;
		try {
			if (actual instanceof String && expected instanceof String) {
				status = (actual.equals(expected));
			} else {
				status = (actual == expected);
			}

			log.info("Compare value = " + status);
			if (status) {
				log.info("===PASSED===");
			} else {
				log.info("===FAILED===");
			}
			Assert.assertEquals(actual, expected, "Value is not matching!");
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	public void closeBrowserAndDriver(WebDriver driver) {
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name= " + osName);

			String cmd = "";
			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}

			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toString().toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI\"IMAGENAME eq chromedriver*\"";
				}
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI\"IMAGENAME eq chromedriver*\"";
					Process process = Runtime.getRuntime().exec(cmd);
					process.waitFor();
				}
			}
			log.info("-------------QUIT BROWSER SUCCESSFULLY -----------------");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.info(e.getMessage());
		}

	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(9999);
	}

	public String getCurrentDay() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			return "0" + day;
		}
		return String.valueOf(day);
	}

	public String getCurrentMonth() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		int month = nowUTC.getMonthOfYear();
		if (month < 10) {
			return "0" + month;
		}
		return String.valueOf(month);
	}

	public String getCurrentYear() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		return String.valueOf(nowUTC.getYear());
	}

	public String getToday() {
		String today = getCurrentYear() + "-" + getCurrentMonth() + "-" + getCurrentDay();
		return today;
	}

	public void sleepTimeInSecond(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sleepTimeInMiliSecond(int miliSecond) {
		try {
			Thread.sleep(miliSecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean verifySortAscending(String[] list) {
		for (int i = 0; i < list.length - 1; i++) {
			int compare = list[i].compareTo(list[i + 1]);
			if (compare > 0) {
				return false;
			}
		}
		return true;

	}

	public boolean verifySortDescending(String[] list) {
		for (int i = 0; i < list.length - 1; i++) {
			int compare = list[i].compareTo(list[i + 1]);
			if (compare < 0) {
				return false;
			}
		}
		return true;

	}

	public boolean verifyItemIncludeInList(String[] list, String itemNeedToCompare) {
		for (String eachItem : list) {
			if (itemNeedToCompare.equals(eachItem)) {
				return true;
			}
		}
		return false;

	}

	@BeforeSuite
	public void deleteAllFilesInReportNGScreenShots() {
		deleteAllFileInFolder("ReportNGScreenShots");
	}

	public void deleteAllFileInFolder(String expectFolder) {
		try {
			String pathFolderDownload = workingDir + "\\" + expectFolder;
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public void captureAnyScreenshot(String methodName) {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenPath = workingDir + "\\ProjectScreenShots\\" + methodName + "_"
					+ formater.format(calendar.getTime()) + ".png";
			FileUtils.copyFile(source, new File(screenPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
