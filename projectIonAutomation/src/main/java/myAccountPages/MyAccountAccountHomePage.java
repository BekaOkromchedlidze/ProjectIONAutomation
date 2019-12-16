package myAccountPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountAccountHomePage {
	
	private WebDriver driver;
	public WebDriverWait wait;


	// Locators
	@FindBy(xpath = "//h6[contains(text(),'My Alerts')]//ancestor::div[@role='button']")
	public WebElement buttonMyAlerts;
	
	// Locators
	@FindBy(xpath = "//h6[contains(text(),'My Payments')]//ancestor::div[@role='button']")
	public WebElement buttonMyPayments;
	
	// Locators
	@FindBy(xpath = "//h6[contains(text(),'My Profile')]//ancestor::div[@role='button']")
	public WebElement buttonMyProfile;
	
	// Locators
	@FindBy(xpath = "//h6[contains(text(),'My Bills')]//ancestor::div[@role='button']")
	public WebElement buttonMyBills;
	
	// Locators
	@FindBy(xpath = "//h6[contains(text(),'Account Details')]//ancestor::div[@role='button']")
	public WebElement buttonAccountDetails;
	
	// Locators
	@FindBy(xpath = "//h6[contains(text(),'Contact Us')]//ancestor::div[@role='button']")
	public WebElement buttonContactUs;
	
	@FindBy(xpath = "//button[contains(.,'Accept Cookies')]")
	public WebElement buttonAcceptCookies;
	
	
	// Constructor
	public MyAccountAccountHomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		// Initialise Elements
		PageFactory.initElements(driver, this);

	}

	public void clickMyAlerts() {
		wait.until(ExpectedConditions.visibilityOf(buttonMyAlerts));
		buttonMyAlerts.click();
	}
	
	public void clickMyPayments() {
		wait.until(ExpectedConditions.visibilityOf(buttonMyPayments));
		buttonMyPayments.click();
	}
	
	public void clickMyProfile() {
		wait.until(ExpectedConditions.visibilityOf(buttonMyProfile));
		buttonMyProfile.click();
	}
	
	public void clickMyBills() {
		wait.until(ExpectedConditions.visibilityOf(buttonMyBills));
		buttonMyBills.click();
	}
	
	public void clickAccountDetails() {
		wait.until(ExpectedConditions.visibilityOf(buttonAccountDetails));
		buttonAccountDetails.click();
	}
	
	public void clickContactUs() {
		wait.until(ExpectedConditions.visibilityOf(buttonContactUs));
		buttonContactUs.click();
	}
	
	public void clickAcceptCookies() {
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(buttonAcceptCookies));
			wait.until(ExpectedConditions.visibilityOf(buttonAcceptCookies));
			buttonAcceptCookies.click();
		} catch (Exception e)
		{
			// TODO: handle exception
			try
			{
				Thread.sleep(500);
			} catch (InterruptedException e1)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wait.until(ExpectedConditions.elementToBeClickable(buttonAcceptCookies));
			buttonAcceptCookies.click();
		}
		try
		{
			Thread.sleep(700);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}