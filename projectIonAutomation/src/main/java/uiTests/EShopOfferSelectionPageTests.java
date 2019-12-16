package uiTests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import eShopPages.OfferSelectionPage;


public class EShopOfferSelectionPageTests extends BaseTest
{

	public OfferSelectionPage offerSelectionPage;
	WebDriverWait wait;
	
	@BeforeClass
	public void beforeClass()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		offerSelectionPage =  PageFactory.initElements(driver, OfferSelectionPage.class);
		wait = new WebDriverWait(driver, 10);
	}
	
	@AfterClass
	public void tearDown() {
		//Close chromedriver
		driver.close();
	}


	@Test(priority = 0)
	public void acceptCookies()
	{
//		logger = report.createTest("Load eShop and Accept Cookies");
		
//		logger.info("Loading eShop");
		driver.get(eShopURL);
//		logger.pass("eShop Loaded.");

//		logger.info("Accepting Cookies");
		offerSelectionPage.clickAcceptCookies();
//		logger.pass("Cookies Accepted.");

	}

	@Test(priority = 1)
	public void addAnOffer()
	{
//		logger = report.createTest("Add Single Offer");

		offerSelectionPage.selectOffer();
		wait.until(ExpectedConditions.textToBePresentInElement(offerSelectionPage.pageItemsInCart, "1"));
		
//		logger.pass("Single offer added.");

	}

	@Test(priority = 2)
	public void addAnotherOffer()
	{
//		logger = report.createTest("Add Second Offer");
		
		offerSelectionPage.selectAnotherProduct();
		offerSelectionPage.selectOffer();
		wait.until(ExpectedConditions.textToBePresentInElement(offerSelectionPage.pageItemsInCart, "2"));
		
//		logger.pass("Multiline offers added.");
	}
	
	@Test(priority = 3)
	public void deleteOfferLine2()
	{
//		logger = report.createTest("Delete Second Offer");

		offerSelectionPage.deleteSelectedOfferLine2();
		wait.until(ExpectedConditions.textToBePresentInElement(offerSelectionPage.pageItemsInCart, "1"));
		
//		logger.pass("Second offer deleted.");
	}
	
	@Test(priority = 4)
	public void deleteOfferLine1()
	{
//		logger = report.createTest("Delete First Offer");

		offerSelectionPage.deleteSelectedOfferLine1();
		wait.until(ExpectedConditions.textToBePresentInElement(offerSelectionPage.pageItemsInCart, "0"));
		
//		logger.pass("First offer deleted.");
	}
	
}
