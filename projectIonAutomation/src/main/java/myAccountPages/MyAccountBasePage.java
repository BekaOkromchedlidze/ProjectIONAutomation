package myAccountPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * 
 * This class includes the header links and 
 * will be extended by all MyAccount sub pages
 * 
 * */
public class MyAccountBasePage {
	
	private WebDriver driver;
	public WebDriverWait wait;


	// Locators
	@FindBy(xpath = "//h6[contains(text(),'My GoMo Home')]//ancestor::a[@class='subNavigation__link']")
	public WebElement buttonNavBarMyAccountHome;
	
	@FindBy(xpath = "//h6[contains(text(),'My Alerts')]//ancestor::a[@class='subNavigation__link']")
	public WebElement buttonNavBarMyAlerts;
	
	// Locators
	@FindBy(xpath = "//h6[contains(text(),'My Payments')]//ancestor::a[@class='subNavigation__link']")
	public WebElement buttonNavBarMyPayments;
	
	// Locators
	@FindBy(xpath = "//h6[contains(text(),'My Profile')]//ancestor::a[@class='subNavigation__link']")
	public WebElement buttonNavBarMyProfile;
	
	// Locators
	@FindBy(xpath = "//h6[contains(text(),'My Bills')]//ancestor::a[@class='subNavigation__link']")
	public WebElement buttonNavBarMyBills;
	
	// Locators
	@FindBy(xpath = "//h6[contains(text(),'Account Details')]//ancestor::a[@class='subNavigation__link']")
	public WebElement buttonNavBarAccountDetails;
	
	// Locators
	@FindBy(xpath = "//h6[contains(text(),'Contact Us')]//ancestor::a[@class='subNavigation__link']")
	public WebElement buttonNavBarContactUs;
	
	
	// Constructor
	public MyAccountBasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		// Initialise Elements
		PageFactory.initElements(driver, this);

	}

	public void clickNavBarMyAlerts() {
		wait.until(ExpectedConditions.visibilityOf(buttonNavBarMyAlerts));
		buttonNavBarMyAlerts.click();
	}
	
	public void clickNavBarMyPayments() {
		wait.until(ExpectedConditions.visibilityOf(buttonNavBarMyPayments));
		buttonNavBarMyPayments.click();
	}
	
	public void clickNavBarMyProfile() {
		wait.until(ExpectedConditions.visibilityOf(buttonNavBarMyProfile));
		buttonNavBarMyProfile.click();
	}
	
	public void clickNavBarMyBills() {
		wait.until(ExpectedConditions.visibilityOf(buttonNavBarMyBills));
		buttonNavBarMyBills.click();
	}
	
	public void clickNavBarAccountDetails() {
		wait.until(ExpectedConditions.visibilityOf(buttonNavBarAccountDetails));
		buttonNavBarAccountDetails.click();
	}
	
	public void clickNavBarContactUs() {
		wait.until(ExpectedConditions.visibilityOf(buttonNavBarContactUs));
		buttonNavBarContactUs.click();
	}
	
}