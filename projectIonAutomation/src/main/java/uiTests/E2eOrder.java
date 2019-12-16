package uiTests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eShopPages.ConfirmationPage;
import eShopPages.CustomerDetailsPage;
import eShopPages.KeycloakLoginPage;
import eShopPages.OfferSelectionPage;
import eShopPages.SummaryPage;
import utilities.Helper;

public class E2eOrder extends BaseTest {
	public OfferSelectionPage offerSelectionPage;
	public CustomerDetailsPage customerDetailsPage;
	public SummaryPage summaryPage;
	public ConfirmationPage confirmationPage;
	public KeycloakLoginPage keycloakLoginPage;

	@Test(dataProvider = "getSinglelineOrderDetails")
	public void placeSinglelineOrder(ITestContext iTestContext, String numOfOffers, String title, String firstName,
			String secondName, String contactNumber, String idType, String idNumber, String dateOfBirth,
			String allowIonContact, String billingAddressEircode, String isDeliveryAddressSame,
			String deliveryAddressEircode) {
		
		// Go to eshop and accept cookies
		driver.get(eShopURL);
		offerSelectionPage.clickAcceptCookies();
		Helper.saveScreenshotToExtentReport(driver, logger);
		logger.info("Cookies Accepted");

		// Select offer
		offerSelectionPage.selectOffer(numOfOffers);
		Helper.saveScreenshotToExtentReport(driver, logger);
		offerSelectionPage.selectNext();
		logger.info(numOfOffers + " offer(s) selected and advancing to next page");

		// Set generated email attribute so other classes can use
		iTestContext.setAttribute("emailSingleline",
				firstName + "." + secondName + "+" + Helper.GetCurrentTimeStampMilliseconds() + "@eir.ie");
		String email = iTestContext.getAttribute("emailSingleline").toString();
		
		// Enter Customer Details
		customerDetailsPage.enterPersonalDetails(email, email, title, firstName, secondName, contactNumber, idType,
				idNumber, dateOfBirth);

		// Enter Address details
		customerDetailsPage.setBillingEircodeAddress(billingAddressEircode);
		if (isDeliveryAddressSame.toLowerCase().startsWith("n")) {
			customerDetailsPage.checkboxSameDeliveryAddress.click();
			customerDetailsPage.textDeliveryAddressEircode.sendKeys(deliveryAddressEircode);
			customerDetailsPage.buttonConfirmDeliveryEircode.click();
		}

		// Accept T&Cs, select allowIonContact and continue to next page
		customerDetailsPage.selectTandCs();
		if (allowIonContact.toLowerCase().startsWith("y")) {
			customerDetailsPage.selectAllowIon();
		}
		Helper.saveScreenshotToExtentReport(driver, logger);
		customerDetailsPage.selectNext();
		logger.info("Customer details entered and advancing to next page.");

		// Summary Page
		Helper.saveScreenshotToExtentReport(driver, logger);
		summaryPage.enterDetailsAndPay("4012001037141112", "12/24", "242", firstName + " " + secondName);
		Helper.saveScreenshotToExtentReport(driver, logger);
		iTestContext.setAttribute("ccvSingleline", "242");
		
		// Confirmation Page
		confirmationPage.wait.until(ExpectedConditions.visibilityOf(confirmationPage.orderNumber));
		Helper.saveScreenshotToExtentReport(driver, logger);
		logger.info("Payment Successful");

		String orderNumber = confirmationPage.getOrderNumber();
		iTestContext.setAttribute("orderNumberSingleline", confirmationPage.getOrderNumber());
		iTestContext.setAttribute("firstNameSingleline", firstName);
		iTestContext.setAttribute("secondNameSingleline", secondName);
		iTestContext.setAttribute("dateOfBirthSingleline", dateOfBirth);

		logger.info("Confirmation Page Displayed");
		logger.info("Order Placed via eShop");
		logger.info("Order Number: " + iTestContext.getAttribute("orderNumberSingleline").toString());
		logger.info("Email: " + email);
		
		excel.recordOrderDetails("Singleline", email, orderNumber, title, firstName, secondName, contactNumber, idType, 
				idNumber, dateOfBirth, allowIonContact, billingAddressEircode, isDeliveryAddressSame, deliveryAddressEircode);

	}
	
	@Test(dataProvider = "getMultilineOrderDetails")
	public void placeMultilineOrder(ITestContext iTestContext, String numOfOffers, String title, String firstName,
			String secondName, String contactNumber, String idType, String idNumber, String dateOfBirth,
			String allowIonContact, String billingAddressEircode, String isDeliveryAddressSame,
			String deliveryAddressEircode) {
		
		// Go to eshop and accept cookies
		driver.get(eShopURL);
		offerSelectionPage.clickAcceptCookies();
		Helper.saveScreenshotToExtentReport(driver, logger);
		logger.info("Cookies Accepted");

		// Select offer
		offerSelectionPage.selectOffer(numOfOffers);
		Helper.saveScreenshotToExtentReport(driver, logger);
		offerSelectionPage.selectNext();
		logger.info(numOfOffers + " offer(s) selected and advancing to next page");

		// Set generated email attribute so other classes can use
		iTestContext.setAttribute("emailMultiline",
				firstName + "." + secondName + "+" + Helper.GetCurrentTimeStampMilliseconds() + "@eir.ie");
		String email = iTestContext.getAttribute("emailMultiline").toString();
		
		// Enter Customer Details
		customerDetailsPage.enterPersonalDetails(email, email, title, firstName, secondName, contactNumber, idType,
				idNumber, dateOfBirth);

		// Enter Address details
		customerDetailsPage.setBillingEircodeAddress(billingAddressEircode);
		if (isDeliveryAddressSame.toLowerCase().startsWith("n")) {
			customerDetailsPage.checkboxSameDeliveryAddress.click();
			customerDetailsPage.textDeliveryAddressEircode.sendKeys(deliveryAddressEircode);
			customerDetailsPage.buttonConfirmDeliveryEircode.click();
		}

		// Accept T&Cs, select allowIonContact and continue to next page
		customerDetailsPage.selectTandCs();
		if (allowIonContact.toLowerCase().startsWith("y")) {
			customerDetailsPage.selectAllowIon();
		}
		Helper.saveScreenshotToExtentReport(driver, logger);
		customerDetailsPage.selectNext();
		logger.info("Customer details entered and advancing to next page.");
		
		// Summary Page
		Helper.saveScreenshotToExtentReport(driver, logger);
		summaryPage.enterDetailsAndPay("4012001037141112", "12/24", "242", firstName + " " + secondName);
		Helper.saveScreenshotToExtentReport(driver, logger);
		iTestContext.setAttribute("ccvMultiline", "12/24");

		// Confirmation Page
		confirmationPage.wait.until(ExpectedConditions.visibilityOf(confirmationPage.orderNumber));
		Helper.saveScreenshotToExtentReport(driver, logger);
		logger.info("Payment Successful");

		String orderNumber = confirmationPage.getOrderNumber();
		iTestContext.setAttribute("orderNumberMultiline", confirmationPage.getOrderNumber());
		iTestContext.setAttribute("firstNameMultiline", firstName);
		iTestContext.setAttribute("secondNameMultiline", secondName);
		iTestContext.setAttribute("dateOfBirthMultiline", dateOfBirth);

		logger.info("Confirmation Page Displayed");
		logger.info("Order Placed via eShop");
		logger.info("Order Number: " + iTestContext.getAttribute("orderNumberMultiline").toString());
		logger.info("Email: " + email);
		
		
		excel.recordOrderDetails("Multiline", email, orderNumber, title, firstName, secondName, contactNumber, idType, 
				idNumber, dateOfBirth, allowIonContact, billingAddressEircode, isDeliveryAddressSame, deliveryAddressEircode);

	}
	
	@Test(dependsOnMethods = { "uiTests.CustomerActionsTests.completeSinglelineAccountSetUp" , "uiTests.E2eOrder.placeSinglelineOrder" })
	public void placeCrossSellOrder(ITestContext iTestContext) {
		
		// Go to eshop and accept cookies
		driver.get(eShopURL);
		Helper.saveScreenshotToExtentReport(driver, logger);
		
		  String email = iTestContext.getAttribute("emailSingleline").toString();
		  String password = iTestContext.getAttribute("password").toString();
		 		
		/*
		 * String email = "B.Ok+2019-11-15_13-19-193@eir.ie"; String password =
		 * "Testpass1";
		 */		
		// Login
		offerSelectionPage.clickLogin();
		Helper.saveScreenshotToExtentReport(driver, logger);
		keycloakLoginPage.inputEmail(email);
		keycloakLoginPage.inputPassword(password);
		Helper.saveScreenshotToExtentReport(driver, logger);
		keycloakLoginPage.clickLogin();
		
		// Accept Cookies
		offerSelectionPage.clickAcceptCookies();
		logger.info("Cookies Accepted");
		Helper.saveScreenshotToExtentReport(driver, logger);
		
		// Select offer
		offerSelectionPage.selectOffer();
		Helper.saveScreenshotToExtentReport(driver, logger);
		offerSelectionPage.selectNext();
		Helper.saveScreenshotToExtentReport(driver, logger);

		// Accept T&Cs and continue to next page
		customerDetailsPage.selectTandCs();
		logger.info("Offer selected and advancing to next page");
		customerDetailsPage.selectNext();
		logger.info("Customer details entered and advancing to next page.");

		// Summary Page
		Helper.saveScreenshotToExtentReport(driver, logger);
		summaryPage.payWithSavedCard(iTestContext.getAttribute("ccvSingleline").toString());
		//summaryPage.payWithSavedCard("242");

		logger.info("Payment Successful");

		// Confirmation Page
		iTestContext.setAttribute("orderNumberCrossSell", confirmationPage.getOrderNumber());
		Helper.saveScreenshotToExtentReport(driver, logger);

		logger.info("Confirmation Page Displayed");
		logger.info("Order Placed via eShop");
		logger.info("Order Number: " + iTestContext.getAttribute("orderNumberCrossSell").toString());
		logger.info("Email: " + email);
		
		excel.recordCrossSellOrder();

	}

	@DataProvider
	public Object[][] getSinglelineOrderDetails() throws Exception {
		Object[][] testData = excel.getTableArray("placeOrderDetails", 2, 2, 4, 15);
		return (testData);
	}

	@DataProvider
	public Object[][] getMultilineOrderDetails() throws Exception {
		Object[][] testData = excel.getTableArray("placeOrderDetails", 3, 3, 4, 15);
		return (testData);
	}

	@BeforeMethod
	public void startDriver() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		offerSelectionPage = PageFactory.initElements(driver, OfferSelectionPage.class);
		customerDetailsPage = PageFactory.initElements(driver, CustomerDetailsPage.class);
		summaryPage = PageFactory.initElements(driver, SummaryPage.class);
		confirmationPage = PageFactory.initElements(driver, ConfirmationPage.class);
		keycloakLoginPage = PageFactory.initElements(driver, KeycloakLoginPage.class);

	}

	@AfterMethod
	public void closeDriver() {
		driver.close();
	}

}
