package testNG;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest
{
	public WebDriver driver;


	public WebDriver getDriver()
	{
		return driver;
	}

	@BeforeSuite
	public void beforeSuite()
	{
		/// Extract chromedriver to local directory if it does not exist
		try
		{
			extractChromeDriver();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// set chromedriver property
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
		
		driver = new ChromeDriver();
		
	}

	@AfterSuite
	public void afterSuite()
	{
		//Close chromedriver
		driver.close();
		
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

	public static void extractChromeDriver() throws FileNotFoundException
	{
		// Create temp chromedriver
		File file = new File(System.getProperty("user.dir") + "/chromedriver.exe");
		if (!file.exists())
		{
			InputStream in = BaseTest.class.getClass().getResourceAsStream("/chromedriver.exe");
			FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + "/chromedriver.exe");
			System.out.println(System.getProperty("user.dir"));

			try
			{
				copyStream(in, out);
				out.close();
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public static void copyStream(InputStream in, OutputStream out) throws IOException
	{
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1)
		{
			out.write(buffer, 0, read);
		}
	}

}
