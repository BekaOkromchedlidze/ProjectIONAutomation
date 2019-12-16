package eShopPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OfferSelectionPage {
	
	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	@FindBy(xpath = "//button[contains(text(),'Accept Cookies')]")
	public WebElement buttonAcceptCookies;
	
	@FindBy(xpath = "//button[contains(span, 'Buy now')]")
	public WebElement buttonSelectGoMoreProduct;

	@FindBy(xpath = "//span[text() = 'Add another SIM']/..")
	public WebElement buttonSelectAnotherProduct;
	
	@FindBy(xpath = "//button[@class=\"jss27 jss1 jss3 jss4 jss6 jss7 jss24\"]")
	public WebElement buttonNextOffer;

	//@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div[2]/button[1]")
	@FindBy(xpath = "//button[@type='button' and contains(., 'Continue')]")
	public WebElement buttonNext;
	
	@FindBy(xpath = "//button[@aria-label=\"cart\"]/*/*/span")
	public WebElement pageItemsInCart;
	
	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div[1]/div[1]/div[1]/div/div/div/div[1]/div[2]/div/button")
	public WebElement buttonDeleteOfferLine1;
	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div[1]/div[1]/div[2]/div/div/div/div[1]/div[2]/div/button")
	public WebElement buttonDeleteOfferLine2;

	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]")
	private WebElement buttonMobilePorting1;
	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/span[1]")
	private WebElement buttonMobilePorting2;
	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/span[1]")
	private WebElement buttonMobilePorting3;
	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div[2]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[2]/span[1]")
	private WebElement buttonMobilePorting4;
	
	@FindBy(xpath = "//div[contains(p, 'Login')]")
	public WebElement buttonLogin;

	// Constructor
	public OfferSelectionPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		// Initialise Elements
		PageFactory.initElements(driver, this);
		
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
	
	public void selectOffer(String numOfOffers) {
		// Single line or Multiline order?
		if (numOfOffers.trim().compareToIgnoreCase("1") == 0) {
			System.out.println("Placing a Singline order");
			selectOffer();
		} else if (numOfOffers.trim().compareToIgnoreCase("2") == 0){
			System.out.println("Placing a Multiline order");
			selectOffer();
			selectAnotherProduct();
			selectOffer();
		} else {
			System.out.println("Number of offers entered not recognised.");
		}
		
	}

	public void selectOffer() {
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(buttonSelectGoMoreProduct));
		} catch (Exception e)
		{
			// TODO: handle exception
			buttonNextOffer.click();
			wait.until(ExpectedConditions.elementToBeClickable(buttonSelectGoMoreProduct));
		}
//		buttonNextOffer.click();
		new Actions(driver).moveToElement(buttonSelectGoMoreProduct).click().perform();
	}

	public void selectAnotherProduct() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonSelectAnotherProduct));
		buttonSelectAnotherProduct.click();
	}

	public void selectNext() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonNext));
		buttonNext.click();
	}
	
	public void deleteSelectedOfferLine1() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonDeleteOfferLine1));
		buttonDeleteOfferLine1.click();
	}
	
	public void deleteSelectedOfferLine2() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonDeleteOfferLine2));
		buttonDeleteOfferLine2.click();
	}
	
	public void clickLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonLogin));
		buttonLogin.click();
	}
	

	public void toggleMobilePortButton(int lineNum) {

		wait.until(ExpectedConditions.visibilityOf(buttonMobilePorting1));

		if (lineNum == 1) {
			buttonMobilePorting1.click();
		} else if (lineNum == 2) {
			buttonMobilePorting2.click();
		} else if (lineNum == 3) {
			buttonMobilePorting3.click();
		} else if (lineNum == 4) {
			buttonMobilePorting4.click();
		}

	}
	
}