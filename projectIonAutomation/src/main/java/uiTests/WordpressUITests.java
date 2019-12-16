package uiTests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import eShopPages.WordpressPage;

public class WordpressUITests extends BaseTest
{

	@Test
	public void WordpressLoadsSuccessfully()
	{
		System.out.println("TESTING!!");
		logger.info("Check online status of Wordpress");
		WordpressPage wordpressPage = new WordpressPage(driver);
		wordpressPage.waitUntilPageLoads();
		
//		logger.pass("Wordpress loaded successfully.");
	}
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		System.out.println(wordpressURL);
		driver.get(wordpressURL);
	}
	
	@AfterClass
	public void tearDown() {
		//Close chromedriver
		driver.close();
	}

}
