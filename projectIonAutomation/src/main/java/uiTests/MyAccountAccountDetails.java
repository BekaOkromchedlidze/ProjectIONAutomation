package uiTests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import eShopPages.KeycloakLoginPage;
import myAccountPages.MyAccountAccountDetailsPage;
import myAccountPages.MyAccountAccountHomePage;

public class MyAccountAccountDetails extends BaseTest {

	public MyAccountAccountHomePage myAccountAccountHomePage;
	public MyAccountAccountDetailsPage myAccountAccountDetailsPage;
	public KeycloakLoginPage keycloakLoginPage;

	@Test (dependsOnMethods = {"uiTests.CustomerActionsTests.completeAccountSetUp" , "uiTests.ProcessLogistics.provisioningCheckSingleline"})
	public void viewOneActiveOffer(ITestContext iTestContext) {
		String email = iTestContext.getAttribute("emailSingleline").toString();
		String password = iTestContext.getAttribute("password").toString();
		
		
		// Go to myAccount and accept cookies
		driver.get(myAccountURL);
		keycloakLoginPage.login(email, password);
		myAccountAccountHomePage.clickAcceptCookies();
		logger.info("Logged in");
		logger.info("Cookies Accepted");
		myAccountAccountHomePage.clickAccountDetails();
		myAccountAccountDetailsPage.clickNumberSelect();

		myAccountAccountDetailsPage.clickSubscription2();
		myAccountAccountDetailsPage.clickNumberSelect();
		myAccountAccountDetailsPage.clickSubscription1();

	}
	
	@Test (dependsOnMethods = {"uiTests.CustomerActionsTests.completeAccountSetUp" , "uiTests.ProcessLogistics.provisioningCheckMultiline"})
	public void viewTwoActiveOffers(ITestContext iTestContext) {
		String email = iTestContext.getAttribute("emailSingleline").toString();
		String password = iTestContext.getAttribute("password").toString();
		
		
		// Go to myAccount and accept cookies
		driver.get(myAccountURL);
		keycloakLoginPage.login(email, password);
		myAccountAccountHomePage.clickAcceptCookies();
		logger.info("Logged in");
		logger.info("Cookies Accepted");
		myAccountAccountHomePage.clickAccountDetails();
		myAccountAccountDetailsPage.clickNumberSelect();

		myAccountAccountDetailsPage.clickSubscription2();
		myAccountAccountDetailsPage.clickNumberSelect();
		myAccountAccountDetailsPage.clickSubscription1();

	}

	@BeforeMethod
	public void startDriver() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		myAccountAccountHomePage = PageFactory.initElements(driver, MyAccountAccountHomePage.class);
		myAccountAccountDetailsPage = PageFactory.initElements(driver, MyAccountAccountDetailsPage.class);
		keycloakLoginPage = PageFactory.initElements(driver, KeycloakLoginPage.class);

	}

	@AfterMethod
	public void closeDriver() {
		driver.close();
	}

}
