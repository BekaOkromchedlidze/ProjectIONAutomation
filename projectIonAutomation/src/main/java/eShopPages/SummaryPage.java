package eShopPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SummaryPage {
	private WebDriver driver;
	public WebDriverWait wait;

	
	// Locators
	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/h6[1]")
	public WebElement customerName;
	
	@FindBy(xpath = "//button[@id='rxp-primary-btn']")
	public WebElement payButton;
	
	@FindBy(xpath = "//*[contains(text(),'Card Number')]")
	public WebElement checkCardNumber;
	
	
	// Constructor
	public SummaryPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 20);
		// Initialise Elements
		PageFactory.initElements(driver, this);

	}

	public void enterDetailsAndPay(String cardNumber, String expiry, String ccv, String cardHolder) {

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='targetIframe']")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("targetIframe"));

/*		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		 //driver.switchTo().frame("targetIframe"); //switching the frame by ID

			System.out.println("********We are switching to the iframe*******");
			
			wait.until(ExpectedConditions.visibilityOf(payButton));
			wait.until(ExpectedConditions.visibilityOf(checkCardNumber));
			//wait.until(ExpectedConditions.textToBePresentInElement(payButton, "PAY NOW"));
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='row']/div/div")));
			Actions actions = new Actions(driver);
			try {
				Thread.sleep(1600);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			actions.moveToElement(driver.findElement(By.xpath("//*[@placeholder='Card Number']")));
			actions.click();
			//actions.sendKeys(cardNumber);
			actions.sendKeys(cardNumber.substring(0, 8));
			actions.build().perform();

		try
		{
			Thread.sleep(1200);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 			
			actions.sendKeys(cardNumber.substring(8, 16));
			actions.moveToElement(driver.findElement(By.xpath("//input[@id='pas_expiry']")));
			actions.click();
			actions.build().perform();


			
		/*
		 * Actions actions1 = new Actions(driver);
		 * actions1.moveToElement(driver.findElement(By.xpath(
		 * "//input[@id='pas_expiry']"))); actions1.click(); actions1.build().perform();
		 */

			Actions actions2 = new Actions(driver);
			actions2.moveToElement(driver.findElement(By.xpath("//input[@id='pas_expiry']")));
			actions2.sendKeys(expiry.replace("//", "").substring(0, 4));
			actions2.build().perform();
		
		/*
		 * try { Thread.sleep(1100); } catch (InterruptedException e) { // TODO
		 * Auto-generated e.printStackTrace(); }
		 */		 			
			Actions actions21 = new Actions(driver);
			actions21.sendKeys(expiry.replace("//", "").substring(4, 5));
			actions21.build().perform();
			//wait.until(ExpectedConditions.textToBePresentInElement(payButton, "PROCEED TO VERIFICATION"));

	
			Actions actions3 = new Actions(driver);
			actions3.moveToElement(driver.findElement(By.xpath("//input[@id='pas_cccvc']")));
			actions3.click();
			actions3.sendKeys(ccv);
			actions3.build().perform();

			Actions actions4 = new Actions(driver);
			actions4.moveToElement(driver.findElement(By.xpath("//input[@id='pas_ccname']")));
			actions4.click();
			actions4.sendKeys(cardHolder);
			actions4.build().perform();
			
			
			Actions actions5 = new Actions(driver);
			actions5.moveToElement(driver.findElement(By.xpath("//button[@id='rxp-primary-btn']")));
			actions5.click();
			actions5.build().perform();

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Submit']")));

			
			Actions actions6 = new Actions(driver);
			actions6.moveToElement(driver.findElement(By.xpath("//input[@value='Submit']")));
			actions6.click();
			actions6.build().perform();
			



	}

}