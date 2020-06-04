package commons;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

public class ExtentReportListener implements IReporter {

  private ExtentHtmlReporter extent;
  private ExtentTest test;

  public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
      String outputDirectory) {
    extent = new ExtentHtmlReporter(outputDirectory + File.separator + "ExtentReportResults.html");
    for (ISuite suite : suites) {
      Map<String, ISuiteResult> result = suite.getResults();

      for (ISuiteResult r : result.values()) {
        ITestContext context = r.getTestContext();
        buildTestNodes(context.getPassedTests(), Status.PASS);
        buildTestNodes(context.getFailedTests(), Status.FAIL);
        buildTestNodes(context.getSkippedTests(), Status.SKIP);
      }
    }
    extent.flush();
  }

  private void buildTestNodes(IResultMap tests, Status status) {
    if (tests.size() > 0) {
      for (ITestResult result : tests.getAllResults()) {
        test = extent.(result.getMethod().getMethodName());

        test.(getTime(result.getStartMillis()));
        test.setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
          test.assignCategory(group);
        }

        if (result.getThrowable() != null) {
          test.log(status, result.getThrowable());
        } else {
          test.log(status, "Test " + status.toString().toLowerCase() + "ed");
        }

        extent.endTest(test);
      }
    }
  }

  private Date getTime(long millis) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(millis);
    return calendar.getTime();
  }

  public void extendLog(String message) {
    test.log(LogStatus.INFO, message);
    Reporter.log(message);
  }
}