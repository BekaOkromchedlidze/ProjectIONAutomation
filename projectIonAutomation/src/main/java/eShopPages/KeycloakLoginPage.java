package eShopPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KeycloakLoginPage {
	
	private WebDriver driver;
	public WebDriverWait wait;


	// Locators
	@FindBy(xpath = "//*[@id=\"kc-info-message\"]/p[2]/a")
	public WebElement verifyEmailDeeplink;
	
	@FindBy(xpath = "//input[@id='password']")
	public WebElement inputPassword;
	
	@FindBy(xpath = "//input[@id='username']")
	public WebElement inputEmail;
	
	@FindBy(xpath = "//input[@name='login']")
	public WebElement buttonLogin;
	
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
	public KeycloakLoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		// Initialise Elements
		PageFactory.initElements(driver, this);

	}
	
	public void login(String email, String password) {
		inputEmail(email);
		inputPassword(password);
		clickLogin();
	}
	
	public void inputEmail(String email) {
		wait.until(ExpectedConditions.visibilityOf(inputEmail));
		inputEmail.sendKeys(email);
	}
	
	public void inputPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(inputPassword));
		inputPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonLogin));
		buttonLogin.click();
	}

}