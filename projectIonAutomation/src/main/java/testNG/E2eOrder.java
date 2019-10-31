package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eShopPages.ConfirmationPage;
import eShopPages.CustomerDetailsPage;
import eShopPages.OfferSelectionPage;
import eShopPages.SummaryPage;
import utilities.ConfigDataProvider;
import utilities.ExcelDataProvider;
import utilities.Helper;

public class E2eOrder 
{
	public static WebDriver driver;
	ConfigDataProvider config = new ConfigDataProvider();
	ExcelDataProvider excel = new ExcelDataProvider();
	String URL = config.getE2eEShopURL();
	
	public OfferSelectionPage offerSelectionPage;
	public CustomerDetailsPage customerDetailsPage;
	public SummaryPage summaryPage;
	public ConfirmationPage confirmationPage;
	
	public String email;
	public String orderNumber;
	
	
	@Test(dataProvider = "getTestData")
	public void placeSingleLineOrder(String numOfOffers, String title, String firstName, String secondName, String contactNumber, String idType, String idNumber, String dateOfBirth, String allowIonContact, String billingAddressEircode, String isDeliveryAddressSame, String deliveryAddressEircode)
	{
		
		driver.get(URL);
		offerSelectionPage.clickAcceptCookies();
		
		// Offer Selection
		if (numOfOffers.trim().compareToIgnoreCase("1") == 0)
		{
			System.out.println("Placing a Singline order");
			offerSelectionPage.selectOffer();
			offerSelectionPage.selectNext();
		} else {
			System.out.println("Placing a Multiline order");
			offerSelectionPage.selectOffer();
			offerSelectionPage.selectAnotherProduct();
			offerSelectionPage.selectOffer();
			offerSelectionPage.selectNext();
		}
		
		email = firstName+"."+secondName+"+"+ Helper.GetCurrentTimeStampMilliseconds() + "@eir.ie";
		
		// Customer Details
		customerDetailsPage.enterEmail(email);
		customerDetailsPage.enterConfirmationEmail(email);
		customerDetailsPage.selectTitle(title);
		customerDetailsPage.enterFirstName(firstName);
		customerDetailsPage.enterSecondName(secondName);
		customerDetailsPage.selectIdType(idType);
		customerDetailsPage.enterIDNumber(idNumber);
		customerDetailsPage.enterDateOfBirth(dateOfBirth);
		customerDetailsPage.setBillingEircodeAddress(billingAddressEircode);
		customerDetailsPage.selectTandCs();
		
		if (allowIonContact.toLowerCase().startsWith("y"))
		{
			customerDetailsPage.selectAllowIon();
		}
		
		if (isDeliveryAddressSame.toLowerCase().startsWith("n"))
		{
			customerDetailsPage.checkboxSameDeliveryAddress.click();
			customerDetailsPage.textDeliveryAddressEircode.sendKeys(deliveryAddressEircode);
			customerDetailsPage.buttonConfirmDeliveryEircode.click();
		}
		customerDetailsPage.selectNext();

		
		// Summary Page
		summaryPage.enterDetailsAndPay("4012001037141112", "12/24", "242", firstName + " " + secondName);
		
		// Confirmation Page
		orderNumber = confirmationPage.getOrderNumber();
		
		System.out.println(email);
		System.out.println(orderNumber);
	}

	@DataProvider
	public Object[][] getTestData()
	{
		Object[][] testData = excel.getTableArray(0, 2, 3, 4, 15);
		return (testData);
	}

	@BeforeMethod
	public void setUp()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		offerSelectionPage =  PageFactory.initElements(driver, OfferSelectionPage.class);
		customerDetailsPage =  PageFactory.initElements(driver, CustomerDetailsPage.class);
		summaryPage =  PageFactory.initElements(driver, SummaryPage.class);
		confirmationPage =  PageFactory.initElements(driver, ConfirmationPage.class);
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE)
		{
			Helper.captureScreenshot(driver);
		}
		
		driver.close();

	}
	
}
