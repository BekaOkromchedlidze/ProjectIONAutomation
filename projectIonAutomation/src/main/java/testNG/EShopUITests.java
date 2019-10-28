package testNG;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import eShopPages.CustomerDetailsPage;
import eShopPages.OfferSelectionPage;

public class EShopUITests extends BaseTest
{

	String URL = "https://eir-eshop-frontend.e2e.ion.comhar.local/";

	@Test
	public void acceptCookies()
	{
		OfferSelectionPage offerSelectionPage = new OfferSelectionPage(driver);
		offerSelectionPage.clickAcceptCookies();
	}

	@Test
	public void addAnOffer()
	{
		OfferSelectionPage offerSelectionPage = new OfferSelectionPage(driver);
		offerSelectionPage.selectOffer();
	}

	@Test
	public void addAnotherOffer()
	{
		OfferSelectionPage offerSelectionPage = new OfferSelectionPage(driver);
		offerSelectionPage.selectAnotherProduct();
		offerSelectionPage.selectOffer();
	}
	
	@Test
	public void addDeleteOfferLine2()
	{
		OfferSelectionPage offerSelectionPage = new OfferSelectionPage(driver);
		offerSelectionPage.deleteSelectedOfferLine2();
	}
	
	@Test
	public void addDeleteOfferLine1()
	{
		OfferSelectionPage offerSelectionPage = new OfferSelectionPage(driver);
		offerSelectionPage.deleteSelectedOfferLine1();
	}
	
	@Test
	public void selectOneOfferAndContinue()
	{
		OfferSelectionPage offerSelectionPage = new OfferSelectionPage(driver);
		offerSelectionPage.selectOffer();
		offerSelectionPage.selectNext();
		
		CustomerDetailsPage customerDetailsPage = new CustomerDetailsPage(driver);
		customerDetailsPage.enterEmail("email@email.com");
	}

	@BeforeClass
	public void beforeClass()
	{
		driver.get(URL);
	}

}
