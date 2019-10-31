package testNG;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import eShopPages.OfferSelectionPage;


public class EShopOfferSelectionPageTests extends BaseTest
{

	String URL = config.getE2eEShopURL();
	public OfferSelectionPage offerSelectionPage;
	WebDriverWait wait;
	
	@BeforeClass
	public void beforeClass()
	{
		offerSelectionPage = new OfferSelectionPage(driver);
//		offerSelectionPage =  PageFactory.initElements(driver, OfferSelectionPage.class);
		driver.get(URL);
		wait = new WebDriverWait(driver, 10);
	}


	@Test(priority = 0)
	public void acceptCookies()
	{

		offerSelectionPage.clickAcceptCookies();
	}

	@Test(priority = 1)
	public void addAnOffer()
	{
		offerSelectionPage.selectOffer();
		wait.until(ExpectedConditions.textToBePresentInElement(offerSelectionPage.pageItemsInCart, "1"));
	}

	@Test(priority = 2)
	public void addAnotherOffer()
	{
		offerSelectionPage.selectAnotherProduct();
		offerSelectionPage.selectOffer();
		wait.until(ExpectedConditions.textToBePresentInElement(offerSelectionPage.pageItemsInCart, "2"));
	}
	
	@Test(priority = 3)
	public void deleteOfferLine2()
	{
		offerSelectionPage.deleteSelectedOfferLine2();
		wait.until(ExpectedConditions.textToBePresentInElement(offerSelectionPage.pageItemsInCart, "1"));
	}
	
	@Test(priority = 4)
	public void deleteOfferLine1()
	{
		offerSelectionPage.deleteSelectedOfferLine1();
		wait.until(ExpectedConditions.textToBePresentInElement(offerSelectionPage.pageItemsInCart, "0"));
	}
	
}
