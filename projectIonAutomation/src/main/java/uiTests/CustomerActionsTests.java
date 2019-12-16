package uiTests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import eShopPages.KeycloakCustomersActionsPage;
import utilities.Helper;
import utilities.MailHog;

public class CustomerActionsTests extends BaseTest {

	public KeycloakCustomersActionsPage keycloakCustomersActionsPage;

	@Test(dependsOnMethods = { "uiTests.E2eOrder.placeSinglelineOrder" })
	public void completeSinglelineAccountSetUp(ITestContext iTestContext) {
		// Get email of account created by E2eOrder.placeE2eOrder Test
		String email = iTestContext.getAttribute("emailSingleline").toString();
		String password = "Testpass1";
		iTestContext.setAttribute("password", password);

		System.out.println("Completing account setup for email: " + email);
		String deeplinkURL = null;
		try {
			deeplinkURL = MailHog.getEmailDeeplink(email);
		} catch (NullPointerException e) {
			logger.info(e);
			logger.fail("Deeplink email not found.");
			throw new NullPointerException("Deeplink email not found.");
		}

		followDeeplinkAndComplete(deeplinkURL,iTestContext.getAttribute("password").toString(), iTestContext.getAttribute("dateOfBirthSingleline").toString());
		
		excel.recordSetupComplete("Singleline", password);
	}
	
	@Test(dependsOnMethods = { "uiTests.E2eOrder.placeMultilineOrder" })
	public void completeMultilineAccountSetUp(ITestContext iTestContext) {
		// Get email of account created by E2eOrder.placeE2eOrder Test
		String email = iTestContext.getAttribute("emailMultiline").toString();
		String password = "Testpass1";
		iTestContext.setAttribute("password", password);

		System.out.println("Completing account setup for email: " + email);
		String deeplinkURL = null;
		try {
			deeplinkURL = MailHog.getEmailDeeplink(email);
		} catch (NullPointerException e) {
			logger.info(e);
			logger.fail("Deeplink email not found.");
			throw new NullPointerException("Deeplink email not found.");
		}

		followDeeplinkAndComplete(deeplinkURL,iTestContext.getAttribute("password").toString(), iTestContext.getAttribute("dateOfBirthMultiline").toString());

		excel.recordSetupComplete("Multiline", password);
	}
	
	private void followDeeplinkAndComplete(String deeplinkURL, String password, String dateOfBirth) {
		/*
		 * Go to the deeplink and complete the account setUp process
		 * 
		 */
		// Initiate chromedriver and go to email deeplink
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(deeplinkURL);
		Helper.saveScreenshotToExtentReport(driver, logger);

		keycloakCustomersActionsPage = PageFactory.initElements(driver, KeycloakCustomersActionsPage.class);

		// wait until deeplink is visible and then click
		keycloakCustomersActionsPage.verifyEmail();
		logger.info("Email verification complete.");

		// Set Password
		keycloakCustomersActionsPage.setPassword(password);
		Helper.saveScreenshotToExtentReport(driver, logger);
		keycloakCustomersActionsPage.buttonPasswordConfirm.click();
		logger.info("Password Set: Testpass1");

		// Verify DOB
		keycloakCustomersActionsPage.verifyDOB(dateOfBirth);
		Helper.saveScreenshotToExtentReport(driver, logger);
		keycloakCustomersActionsPage.buttonDOBConfirm.click();

		keycloakCustomersActionsPage.wait.until(ExpectedConditions.visibilityOf(keycloakCustomersActionsPage.titleUpdatedAccount));
		logger.info("Account set up completed.");
		Helper.saveScreenshotToExtentReport(driver, logger);
		
		driver.close();
	}

}
