package testNG;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import eShopPages.WordpressPage;

public class WordpressUITests extends BaseTest
{
	String URL = "https://wordpress.e2e.ion.comhar.local/";

	@BeforeClass
	public void beforeClass()
	{
		driver.get(URL);
	}
	
	@Test
	public void WordpressLoadsSuccessfully()
	{
		WordpressPage wordpressPage = new WordpressPage(driver);
		wordpressPage.waitUntilPageLoads();
	}

}
