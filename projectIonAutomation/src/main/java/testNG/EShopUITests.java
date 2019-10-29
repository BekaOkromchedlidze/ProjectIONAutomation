package testNG;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import eShopPages.CustomerDetailsPage;
import eShopPages.OfferSelectionPage;

public class EShopUITests extends BaseTest
{

	String URL = "https://eir-eshop-frontend.e2e.ion.comhar.local/";
	public OfferSelectionPage offerSelectionPage;
	
	@BeforeGroups("OfferSelectionPage")
	public void initializeOfferSelectionPage() {
		offerSelectionPage =  PageFactory.initElements(driver, OfferSelectionPage.class);

	}

	@Test(groups = {"OfferSelectionPage"})
	public void acceptCookies()
	{

//		OfferSelectionPage offerSelectionPage = new OfferSelectionPage(getDriver());
		offerSelectionPage.clickAcceptCookies();
	}

	@Test(groups = {"OfferSelectionPage"})
	public void addAnOffer()
	{
//		OfferSelectionPage offerSelectionPage = new OfferSelectionPage(driver);
		offerSelectionPage.selectOffer();
	}

	@Test(groups = {"OfferSelectionPage"})
	public void addAnotherOffer()
	{
//		OfferSelectionPage offerSelectionPage = new OfferSelectionPage(driver);
		offerSelectionPage.selectAnotherProduct();
		offerSelectionPage.selectOffer();
	}
	
	@Test(groups = {"OfferSelectionPage"})
	public void deleteOfferLine2()
	{
//		OfferSelectionPage offerSelectionPage = new OfferSelectionPage(driver);
		offerSelectionPage.deleteSelectedOfferLine2();
	}
	
	@Test(groups = {"OfferSelectionPage"})
	public void deleteOfferLine1()
	{
//		OfferSelectionPage offerSelectionPage = new OfferSelectionPage(driver);
		offerSelectionPage.deleteSelectedOfferLine1();
	}
	
	@Test(groups = {"CustomerDetailsPage"})
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
