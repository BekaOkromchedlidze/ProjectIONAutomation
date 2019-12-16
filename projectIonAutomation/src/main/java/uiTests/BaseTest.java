package uiTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentTest;

import utilities.ConfigDataProvider;
import utilities.ExcelDataProvider;
import utilities.GeneratedTestData;
import utilities.Helper;


@Listeners({ utilities.TestListener.class })
public class BaseTest
{
	public WebDriver driver;
	public ConfigDataProvider config = new ConfigDataProvider();
	public GeneratedTestData generatedTestData = new GeneratedTestData();
	public ExcelDataProvider excel = new ExcelDataProvider();
	
	// Initilise logger which will be used in test listener and test methods to log test info
	public static ExtentTest logger;
	
	// URLS
	String currentEnvironment = config.getDataFromConfig("currentEnvironment");
	String wordpressURL = config.getDataFromConfig("wordpressURL").replace("$environment", currentEnvironment.toLowerCase());
	String eShopURL = config.getDataFromConfig("eShopURL").replace("$environment", currentEnvironment.toLowerCase());
	String myAccountURL = config.getDataFromConfig("myAccountURL").replace("$environment", currentEnvironment.toLowerCase());
	
	// Order Details

	/*
	 * public static List<String> email = new ArrayList<String>(); public static
	 * String orderNumber; public static String dateOfBirth;
	 */
	
	@BeforeMethod
	public void setUpMethod() {
		Thread.currentThread();
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE)
		{
			try {
				Helper.captureScreenshot(driver);
			} catch (Exception e) {
			}
		}
	}

	@BeforeSuite
	public void setUpSuite()
	{
		// set chromedriver property
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/res/chromedriver.exe");
	}

	@AfterSuite
	public void afterSuite()
	{
//		kill chromedriver on program shutdown
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "Shutdown-thread"));
	}

	public WebDriver getDriver()
	{
		return driver;
	}
	

}
