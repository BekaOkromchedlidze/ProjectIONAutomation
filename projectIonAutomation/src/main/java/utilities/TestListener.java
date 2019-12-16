package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

import uiTests.BaseTest;

@Listeners({ ExtentITestListenerClassAdapter.class })

public class TestListener extends BaseTest implements ITestListener {
//	logger = report.createTest("Check online status of Wordpress");

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public void onStart(ITestContext iTestContext) {
		iTestContext.setAttribute("WebDriver", driver);
	}

	public void onFinish(ITestContext iTestContext) {
		// Do tier down operations for extentreports reporting!
//        logger.
//        ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult iTestResult) {
		logger = ExtentManager.getInstance().createTest(getTestMethodName(iTestResult));
	}

	public void onTestSuccess(ITestResult iTestResult) {
		// ExtentReports log operation for passed tests.
		logger.log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult iTestResult) {
		// Get driver from BaseTest and assign to local webDriver variable.
		logger.info(iTestResult.getThrowable());
		Object testClass = iTestResult.getInstance();
		WebDriver webDriver = ((BaseTest) testClass).getDriver();

		// Take base64Screenshot screenshot.
		String base64Screenshot = null;
		try {
			base64Screenshot = "data:image/png;base64,"
					+ ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
			logger.addScreenCaptureFromBase64String(base64Screenshot);
		} catch (NullPointerException e) {
			System.out.println("Driver not initialised, Screenshot not captured.");
		}

		// ExtentReports log and screenshot operations for failed tests.
		logger.log(Status.FAIL, "Test Failed");

	}

	public void onTestSkipped(ITestResult iTestResult) {
		// ExtentReports log operation for skipped tests.
		logger.log(Status.SKIP, "Test Skipped");
//        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	}


	/*
	 * public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	 * System.out.println("Test failed but it is in defined success ratio " +
	 * getTestMethodName(iTestResult)); }
	 */
}