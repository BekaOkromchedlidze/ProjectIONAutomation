package testNG;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import eShopPages.WordpressPage;

public class WordpressUITests extends BaseTest
{
	String URL = config.getE2eWordpressURL();

	@BeforeClass
	public void beforeClass()
	{
		driver.get(URL);
	}
	
	@Test
	public void WordpressLoadsSuccessfully()
	{
//		logger = report.createTest("Check online status of Wordpress");
		
		WordpressPage wordpressPage = new WordpressPage(driver);
		wordpressPage.waitUntilPageLoads();
		
//		logger.pass("Wordpress loaded successfully.");
	}

}
