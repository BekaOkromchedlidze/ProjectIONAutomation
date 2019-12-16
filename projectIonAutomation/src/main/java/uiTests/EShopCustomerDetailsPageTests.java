package uiTests;

import static org.testng.Assert.assertFalse;
//import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eShopPages.CustomerDetailsPage;
import eShopPages.OfferSelectionPage;
import utilities.Helper;

public class EShopCustomerDetailsPageTests extends BaseTest 
{
	public OfferSelectionPage offerSelectionPage;
	public CustomerDetailsPage customerDetailsPage;
	
	@Test(priority = 0)
	private void enterValidCustomerDetails()
	{
//		logger = report.createTest("Enter Valid Customer Details");
		
		customerDetailsPage.enterEmail(excel.getCellValueAsString("CustomerDetails", 2, 2));
		assertFalse(Helper.isElementVisible(driver , customerDetailsPage.errorText));

//logger.pass("Email is Accepted.");

		customerDetailsPage.enterConfirmationEmail(excel.getCellValueAsString("CustomerDetails", 2, 3));
		assertFalse(Helper.isElementVisible(driver , customerDetailsPage.errorText));
//		logger.pass("Confrimation Email is accepted.");
		
		customerDetailsPage.selectTitle(excel.getCellValueAsString("CustomerDetails", 2, 5));
		assertFalse(Helper.isElementVisible(driver , customerDetailsPage.errorText));

//logger.pass("Title accepted");

		customerDetailsPage.enterFirstName(excel.getCellValueAsString("CustomerDetails", 2, 6));
		assertFalse(Helper.isElementVisible(driver , customerDetailsPage.errorText));

//logger.pass("First name accepted");

		customerDetailsPage.enterSecondName(excel.getCellValueAsString("CustomerDetails", 2, 7));
		assertFalse(Helper.isElementVisible(driver , customerDetailsPage.errorText));

//logger.pass("Second name accepted");

		customerDetailsPage.selectIdType(excel.getCellValueAsString("CustomerDetails", 2, 9));
		assertFalse(Helper.isElementVisible(driver , customerDetailsPage.errorText));

//logger.pass("ID Type accepted.");

		customerDetailsPage.enterIDNumber(excel.getCellValueAsString("CustomerDetails", 2, 10));
		assertFalse(Helper.isElementVisible(driver , customerDetailsPage.errorText));

//logger.pass("ID Number accepted.");

		customerDetailsPage.enterDateOfBirth(excel.getCellValueAsString("CustomerDetails", 2, 11));
		assertFalse(Helper.isElementVisible(driver , customerDetailsPage.errorText));

//logger.pass("Date of Birth accepted.");

		customerDetailsPage.setBillingEircodeAddress(excel.getCellValueAsString("CustomerDetails", 2, 13));
		assertFalse(Helper.isElementVisible(driver , customerDetailsPage.errorText));

//logger.pass("Eircode accepted.");

		customerDetailsPage.selectTandCs();
		assertFalse(Helper.isElementVisible(driver , customerDetailsPage.errorText));

//logger.pass("Terms and Conditions selected.");
	}
	
	
	@Test(priority = 1, dataProvider = "getInvalidEmailData", groups = "emailNegative")
	public void enterEmailNegativeTest(String email, String confirmationEmail)
	{

//logger = report.createTest("Invalid Email Test");


//logger.info("Email: " + email + "     Confirmation Email: " + confirmationEmail);
		customerDetailsPage.textEmail.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		customerDetailsPage.textEmailConfirmation.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		customerDetailsPage.enterEmail(email);
		customerDetailsPage.enterConfirmationEmail(confirmationEmail);

		assertTrue(customerDetailsPage.errorText.isDisplayed());

//logger.pass("Invalid email error message displayed.");

		customerDetailsPage.selectNext();
		assertTrue(customerDetailsPage.textEmail.isDisplayed());

//logger.pass("Email not accepted.");
	}
	
	@BeforeClass
	public void beforeClass()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		offerSelectionPage =  PageFactory.initElements(driver, OfferSelectionPage.class);
		customerDetailsPage =  PageFactory.initElements(driver, CustomerDetailsPage.class);
		driver.get(eShopURL);
		offerSelectionPage.clickAcceptCookies();
		offerSelectionPage.selectOffer();
		offerSelectionPage.selectNext();
	}
	
	@AfterClass
	public void tearDown() {
		//Close chromedriver
		driver.close();
	}
	
	
	@DataProvider
    public Object[][] getInvalidEmailData() {
		  Object[][] testData = excel.getTableArray(0, 4, 7, 2, 3);
		return (testData);
	}
}
