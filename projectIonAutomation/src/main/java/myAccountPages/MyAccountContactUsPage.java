package myAccountPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountContactUsPage extends MyAccountBasePage {
	
	public WebDriverWait wait;
	public MyAccountContactUsPage(WebDriver driver) {
		super(driver);
	}


	// Locators
	@FindBy(xpath = "//*[contains(h6,'Order Number:')]")
	public WebElement orderNumber;
	

	public String getOrderNumber() {
		wait.until(ExpectedConditions.visibilityOf(orderNumber));
		return orderNumber.getText().substring(orderNumber.getText().indexOf(": ") + 2);
	}

}