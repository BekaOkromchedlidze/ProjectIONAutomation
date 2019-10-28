package eShopPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WordpressPage
{
	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	@FindBy(xpath = "//a[contains(span,'Our Plan')]")
	public WebElement navBar;

	// Constructor
	public WordpressPage(WebDriver driver) {
			this.driver = driver;
			wait = new WebDriverWait(this.driver, 20);
			// Initialise Elements
			PageFactory.initElements(driver, this);

		}
	
	public void waitUntilPageLoads() {
		wait.until(ExpectedConditions.elementToBeClickable(navBar));
	}
	
}
