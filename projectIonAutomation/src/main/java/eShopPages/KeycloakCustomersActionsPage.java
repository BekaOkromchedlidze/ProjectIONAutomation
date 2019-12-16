package eShopPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KeycloakCustomersActionsPage {
	
	private WebDriver driver;
	public WebDriverWait wait;


	// Locators
	@FindBy(xpath = "//*[@id=\"kc-info-message\"]/p[2]/a")
	public WebElement verifyEmailDeeplink;
	
	@FindBy(xpath = "//input[@id='password-new']")
	public WebElement inputPassword;
	
	@FindBy(xpath = "//input[@id='password-confirm']")
	public WebElement inputPasswordConfirm;
	
	@FindBy(xpath = "//input[@value='Confirm']")
	public WebElement buttonPasswordConfirm;
	
	@FindBy(xpath = "//input[@id='day']")
	public WebElement inputDayDOB;
	
	@FindBy(xpath = "//input[@id='month']")
	public WebElement inputMonthDOB;
	
	@FindBy(xpath = "//input[@id='year']")
	public WebElement inputYearDOB;
	
	@FindBy(xpath = "//input[@value='Confirm']")
	public WebElement buttonDOBConfirm;
	
	@FindBy(xpath = "//h1[contains(text(), '        Your account has been updated.')]")
	public WebElement titleUpdatedAccount;
	
	// Constructor
	public KeycloakCustomersActionsPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		// Initialise Elements
		PageFactory.initElements(driver, this);

	}
	
	public void verifyEmail() {
		wait.until(ExpectedConditions.visibilityOf(verifyEmailDeeplink));
		verifyEmailDeeplink.click();
		wait.until(ExpectedConditions.visibilityOf(buttonPasswordConfirm));
	}

	public void setPassword(String password) {
		inputPassword.sendKeys(password);
		inputPasswordConfirm.sendKeys(password);
	}

	public void verifyDOB(String dateOfBirth) {
		wait.until(ExpectedConditions.visibilityOf(buttonDOBConfirm));
		inputDayDOB.sendKeys(dateOfBirth.substring(0,2));
		inputMonthDOB.sendKeys(dateOfBirth.substring(3,5));
		inputYearDOB.sendKeys(dateOfBirth.substring(6,10));
	}

}