package testNG;

//import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eShopPages.CustomerDetailsPage;
import eShopPages.OfferSelectionPage;

public class EShopCustomerDetailsPageTests extends BaseTest 
{
	String URL = config.getE2eEShopURL();
	public OfferSelectionPage offerSelectionPage;
	public CustomerDetailsPage customerDetailsPage;
	
	@BeforeClass
	public void beforeClass()
	{
		offerSelectionPage =  PageFactory.initElements(driver, OfferSelectionPage.class);
		customerDetailsPage =  PageFactory.initElements(driver, CustomerDetailsPage.class);
		driver.get(URL);
		offerSelectionPage.clickAcceptCookies();
		offerSelectionPage.selectOffer();
		offerSelectionPage.selectNext();
	}
	
	@DataProvider
    public Object[][] getTestData() {
		  Object[][] testData = excel.getTableArray(0, 3, 7, 2, 3);
		return (testData);
	}
	
	
	
	@Test(priority = 0, dataProvider = "getTestData", groups = "emailNegative")
	public void enterEmailNegativeTest(String email, String confirmationEmail)
	{
		customerDetailsPage.textEmail.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		customerDetailsPage.textEmailConfirmation.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		customerDetailsPage.enterEmail(email);
		customerDetailsPage.enterConfirmationEmail(confirmationEmail);

		assertTrue(customerDetailsPage.errorText.isDisplayed());
		customerDetailsPage.selectNext();
		assertTrue(customerDetailsPage.textEmail.isDisplayed());
	}
	
	
	@BeforeGroups("emailNegative")
	public void fillInDetails() {
		customerDetailsPage.selectTitle(excel.getCellValueAsString("CustomerDetails", 2, 4));
		customerDetailsPage.enterFirstName(excel.getCellValueAsString("CustomerDetails", 2, 5));
		customerDetailsPage.enterSecondName(excel.getCellValueAsString("CustomerDetails", 2, 6));
		customerDetailsPage.selectIdType(excel.getCellValueAsString("CustomerDetails", 2, 8));
		customerDetailsPage.enterIDNumber(excel.getCellValueAsString("CustomerDetails", 2, 9));
		customerDetailsPage.enterDateOfBirth(excel.getCellValueAsString("CustomerDetails", 2, 10));
		customerDetailsPage.setBillingEircodeAddress(excel.getCellValueAsString("CustomerDetails", 2, 12));
		customerDetailsPage.selectTandCs();
	}
	
}
