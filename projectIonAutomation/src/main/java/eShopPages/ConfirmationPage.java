package eShopPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmationPage {
	
	private WebDriver driver;
	public WebDriverWait wait;


	// Locators
	@FindBy(xpath = "//*[contains(h6,'Order Number:')]")
	public WebElement orderNumber;
	
	
	// Constructor
	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		// Initialise Elements
		PageFactory.initElements(driver, this);

	}

	public String getOrderNumber() {
		wait.until(ExpectedConditions.visibilityOf(orderNumber));
		return orderNumber.getText();
	}

}