package myAccountPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountAccountDetailsPage extends MyAccountBasePage {
	
	public MyAccountAccountDetailsPage(WebDriver driver) {
		super(driver);
	}


	// Locators
	@FindBy(xpath = "//span[contains(text(),'Buy A New SIM')]//ancestor::div[@role='button']")
	public WebElement buttonBuyNewSim;
	
	@FindBy(xpath = "//span[contains(text(),'Move My Number')]//ancestor::div[@role='button']")
	public WebElement buttonMoveMyNumber;
	
	@FindBy(xpath = "//span[contains(text(),'Cancel My Number')]//ancestor::div[@role='button']")
	public WebElement buttonCancelMyNumber;
	
	@FindBy(xpath = "//span[contains(text(),'Replace/Activate My SIM')]//ancestor::div[@role='button']")
	public WebElement buttonReplaceActivateSim;
	
	@FindBy(xpath = "//span[contains(text(),'My Orders')]//ancestor::div[@role='button']")
	public WebElement buttonMyOrders;
	
	@FindBy(xpath = "//div[@aria-label='Number select']//div[1]")
	public WebElement buttonNumberSelect;
	
	@FindBy(xpath = "//ul[@role='listbox']//li[1]")
	public WebElement buttonSubscription1;
	
	@FindBy(xpath = "//ul[@role='listbox']//li[2]")
	public WebElement buttonSubscription2;
	
	@FindBy(xpath = "//h5[contains(text(),'No phone number available')]")
	public WebElement textNoPhoneNumber;
	
	@FindBy(xpath = "//h4[contains(text(),'No active offer')]")
	public WebElement textNoActiveOffer;
	

	public void clickBuyNewSim() {
		wait.until(ExpectedConditions.visibilityOf(buttonBuyNewSim));
		buttonBuyNewSim.click();;
	}
	
	public void clickMoveMyNumber() {
		wait.until(ExpectedConditions.visibilityOf(buttonMoveMyNumber));
		buttonMoveMyNumber.click();;
	}
	
	public void clickCancelMyNumber() {
		wait.until(ExpectedConditions.visibilityOf(buttonCancelMyNumber));
		buttonCancelMyNumber.click();;
	}
	
	public void clickReplaceActivateSim() {
		wait.until(ExpectedConditions.visibilityOf(buttonReplaceActivateSim));
		buttonReplaceActivateSim.click();;
	}
	
	public void clickMyOrders() {
		wait.until(ExpectedConditions.visibilityOf(buttonMyOrders));
		buttonMyOrders.click();;
	}
	
	public void clickNumberSelect() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonNumberSelect));
		buttonNumberSelect.click();;
	}
	
	public void clickSubscription1() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonSubscription1));
		buttonSubscription1.click();;
	}
	
	public void clickSubscription2() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonSubscription2));
		buttonSubscription2.click();;
	}
	

}