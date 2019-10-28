package eShopPages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerDetailsPage {
	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	@FindBy(xpath = "//*[@id=\"email\"]")
	public WebElement textEmail;
	@FindBy(xpath = "//*[@id=\"emailConfirm\"]")
	public WebElement textEmailConfirmation;

	@FindBy(xpath = "//*[@id=\"select-title\"]")
	public WebElement dropdownTitleSelection;
	@FindBy(xpath = "//*[@id=\"owner.person.title\"]")
	public WebElement inputTitleSelection;
	@FindBy(xpath = "//li[contains(text(), 'Mr')]")
	public WebElement selectMr;
	@FindBy(xpath = "//li[contains(text(), 'Miss')]")
	public WebElement selectMiss;
	@FindBy(xpath = "//li[contains(text(), 'Mrs')]")
	public WebElement selectMrs;
	@FindBy(xpath = "//li[contains(text(), 'Ms')]")
	public WebElement selectMs;

	@FindBy(xpath = "//*[@id=\"firstName\"]")
	public WebElement textFirstname;
	@FindBy(xpath = "//*[@id=\"lastName\"]")
	public WebElement textLastname;
	@FindBy(xpath = "//*[@id=\"mobileNumber\"]")
	public WebElement textContactNumber;

	@FindBy(xpath = "//*[@id='select-identityDocument']")
	public WebElement dropdownIDType;
	@FindBy(xpath = "//input[@id=\"select-title\"]")
	public WebElement inputTitle;
	@FindBy(xpath = "//*[@id=\"menu-\"]/div[2]/ul[1]/li[1]")
	public WebElement selectIrishDrivingLicence;
	@FindBy(xpath = "//*[@id=\"menu-\"]/div[2]/ul[1]/li[2]")
	public WebElement selectIrishPassport;
	@FindBy(xpath = "//*[@id=\"menu-\"]/div[2]/ul[1]/li[3]")
	public WebElement selectPublicServicesCard;
	@FindBy(xpath = "//*[@id=\"menu-\"]/div[2]/ul[1]/li[4]")
	public WebElement selectNationalIdentityCardEUNationals;
	@FindBy(xpath = "//li[contains(text(), 'Foreign Passport')]")
	public WebElement selectForeignPassport;
	@FindBy(xpath = "//input[@id=\"identityDocumentNumber\"]")
	public WebElement textIDNumber;
	@FindBy(xpath = "//div[contains(label, 'Date of Birth *')]/div[1]/input[1]")
	public WebElement textDateOfBirth;
	@FindBy(xpath = "//*[contains(text(), 'GoMo to contact')]//ancestor::label//input[@type='checkbox']")
	public WebElement checkboxAllowION;
	
	
	@FindBy(xpath = "//*[@id=\"sameBillingAddress\"]")
	public WebElement checkboxSameDeliveryAddress;
	@FindBy(xpath = "//h2[contains(text(),'Billing Address')]//ancestor::div[3]//descendant::input[@id='eirCode']")
	public WebElement textBillingAddressEircode;
	@FindBy(xpath = "//h2[contains(text(),'Billing Address')]//ancestor::div[3]//descendant::input[@name='addressLine1']")
	public WebElement textBillingAddressLine1;
	@FindBy(xpath = "//h2[contains(text(),'Billing Address')]//ancestor::div[3]//descendant::input[@name='addressLine2']")
	public WebElement textBillingAddressLine2;
	@FindBy(xpath = "//h2[contains(text(),'Billing Address')]//ancestor::div[3]//descendant::input[@name='addressLine3']")
	public WebElement textBillingAddressLine3;
	@FindBy(xpath = "//h2[contains(text(),'Billing Address')]//ancestor::div[3]//descendant::input[@id='town']")
	public WebElement textBillingTown;
	
	@FindBy(xpath = "//h2[contains(text(),'Delivery Address')]//ancestor::div[3]//descendant::input[@id='eirCode']")
	public WebElement textDeliveryAddressEircode;
	@FindBy(xpath = "//h2[contains(text(),'Delivery Address')]//ancestor::div[3]//descendant::input[@id='addressLine1']")
	public WebElement textDeliveryAddressLine1;
	@FindBy(xpath = "//h2[contains(text(),'Delivery Address')]//ancestor::div[3]//descendant::input[@id='addressLine2']")
	public WebElement textDeliveryAddressLine2;
	@FindBy(xpath = "//h2[contains(text(),'Delivery Address')]//ancestor::div[3]//descendant::input[@id='addressLine3']")
	public WebElement textDeliveryAddressLine3;
	@FindBy(xpath = "//h2[contains(text(),'Delivery Address')]//ancestor::div[3]//descendant::input[@id='town']")
	public WebElement textDeliveryTown;
	

	@FindBy(xpath = "//h2[contains(text(),'Billing Address')]//ancestor::div[3]//following::div[@id='select-county']")
	public WebElement dropdownBillingCounty;
	@FindBy(xpath = "//h2[contains(text(),'Delivery Address')]//ancestor::div[3]//following::div[@id='select-county']")
	public WebElement dropdownDeliveryCounty;
	@FindBy(xpath = "//li[contains(text(),'CARLOW')]")
	public WebElement selectCarlow;
	@FindBy(xpath = "//li[contains(text(),'CAVAN')]")
	public WebElement selectCavan;
	@FindBy(xpath = "//li[contains(text(),'CLARE')]")
	public WebElement selectClare;
	@FindBy(xpath = "//li[contains(text(),'CORK')]")
	public WebElement selectCork;
	@FindBy(xpath = "//li[contains(text(),'DONEGAL')]")
	public WebElement selectDonegal;
	@FindBy(xpath = "//li[contains(text(),'DUBLIN')]")
	public WebElement selectDublin;
	@FindBy(xpath = "//li[contains(text(),'GALWAY')]")
	public WebElement selectGalway;
	@FindBy(xpath = "//li[contains(text(),'KERRY')]")
	public WebElement selectKerry;
	@FindBy(xpath = "//li[contains(text(),'KILDARE')]")
	public WebElement selectKildare;
	@FindBy(xpath = "//li[contains(text(),'KILKENNY')]")
	public WebElement selectKilkenny;
	@FindBy(xpath = "//li[contains(text(),'LAOIS')]")
	public WebElement selectLaois;
	@FindBy(xpath = "//li[contains(text(),'LEITRIM')]")
	public WebElement selectLeitrim;
	@FindBy(xpath = "//li[contains(text(),'LIMERICK')]")
	public WebElement selectLimerick;
	@FindBy(xpath = "//li[contains(text(),'LONGFORD')]")
	public WebElement selectLongford;
	@FindBy(xpath = "//li[contains(text(),'LOUTH')]")
	public WebElement selectLouth;
	@FindBy(xpath = "//li[contains(text(),'MAYO')]")
	public WebElement selectMayo;
	@FindBy(xpath = "//li[contains(text(),'MEATH')]")
	public WebElement selectMeath;
	@FindBy(xpath = "//li[contains(text(),'MONAGHAN')]")
	public WebElement selectMonaghan;
	@FindBy(xpath = "//li[contains(text(),'OFFALY')]")
	public WebElement selectOffaly;
	@FindBy(xpath = "//li[contains(text(),'ROSCOMMON')]")
	public WebElement selectRoscommon;
	@FindBy(xpath = "//li[contains(text(),'SLIGO')]")
	public WebElement selectSligo;
	@FindBy(xpath = "//li[contains(text(),'TIPPERARY')]")
	public WebElement selectTipperary;
	@FindBy(xpath = "//li[contains(text(),'WATERFORD')]")
	public WebElement selectWaterford;
	@FindBy(xpath = "//li[contains(text(),'WESTMEATH')]")
	public WebElement selectWestmeath;
	@FindBy(xpath = "//li[contains(text(),'WEXFORD')]")
	public WebElement selectWexford;
	@FindBy(xpath = "//li[contains(text(),'WICKLOW')]")
	public WebElement selectWicklow;
	
	@FindBy(xpath = "//*[@id=\"menu-\"]/div[2]")
	public WebElement selectBoxCounty;
	@FindBy(xpath = "//*[@id=\"sameBillingAddress\"]")
	public WebElement checkboxSameBillingAddress;
//	@FindBy(xpath = "//*[contains(b, 'read and agree')]//preceding::label//input[@type='checkbox']")
	@FindBy(xpath = "//*[contains(b, 'read and agree')]//ancestor::label//input[@type='checkbox']")
	public WebElement checkboxIonTandCs;
	@FindBy(xpath = "//button[contains(span, 'Continue')]")
	public WebElement buttonConfirm;
	
	/*
	 * @FindBy(xpath = ) public WebElement buttonSelect30GBProduct;
	 * 
	 * @FindBy(xpath = ) public WebElement buttonSelect30GBProduct;
	 * 
	 * @FindBy(xpath = ) public WebElement buttonSelect30GBProduct;
	 */

	// Constructor
	public CustomerDetailsPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 20);
		// Initialise Elements
		PageFactory.initElements(driver, this);

	}
	
	public void enterPersonalDetails(String email, String confirmationEmail, String title, String firstName, String secondName, String contactNumber, String idType, String idNumber, String dateOfBirth) {
		enterEmail(email);
		enterConfirmationEmail(confirmationEmail);
		selectTitle(title);
		enterFirstName(firstName);
		enterSecondName(secondName);
		enterContactNumber(contactNumber);
		selectIdType(idType);
		enterIDNumber(idNumber);
		enterDateOfBirth(dateOfBirth);
	}
	
	public void enterBillingAddress(String billingAddressLine1, String billingAddressLine2, String billingAddressLine3, String billingAddressTown, String billingAddressCounty) {
		enterBillingAddressLine1(billingAddressLine1);
		enterBillingAddressLine2(billingAddressLine2);
		enterBillingAddressLine3(billingAddressLine3);
		enterBillingAddressTown(billingAddressTown);
		selectBillingAddressCounty(billingAddressCounty);
	}
	
	public void enterDeliveryAddress(String deliveryAddressLine1, String deliveryAddressLine2, String deliveryAddressLine3, String deliveryAddressTown, String deliveryAddressCounty) {
		enterDeliveryAddressLine1(deliveryAddressLine1);
		enterDeliveryAddressLine2(deliveryAddressLine2);
		enterDeliveryAddressLine3(deliveryAddressLine3);
		enterDeliveryAddressTown(deliveryAddressTown);
		selectDeliveryAddressCounty(deliveryAddressCounty);
	}
	
	public void enterEmail(String email) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"email\"]")));
		try
		{
			wait.until(ExpectedConditions.visibilityOf(textEmail));
		} catch (Exception e)
		{
			// TODO: handle exception
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		textEmail.sendKeys(email);
	}
	
	public void enterConfirmationEmail(String confirmationEmail) {
		textEmailConfirmation.sendKeys(confirmationEmail);
	}
	
	public void enterFirstName(String firstName) {
		textFirstname.sendKeys(firstName);
	}	
	
	public void enterSecondName(String secondName) {
		textLastname.sendKeys(secondName);
	}
	
	public void enterContactNumber(String contactNumber) {
		textLastname.sendKeys(contactNumber);
	}	
	
	public void enterIDNumber(String idNumber) {
		textLastname.sendKeys(idNumber);
	}
	
	public void enterDateOfBirth(String dateOfBith) {
		textLastname.sendKeys(dateOfBith);
	}
	
	public void enterBillingAddressLine1(String billingAddressLine1) {
		textLastname.sendKeys(billingAddressLine1);
	}
	
	public void enterBillingAddressLine2(String billingAddressLine2) {
		textLastname.sendKeys(billingAddressLine2);
	}
	
	public void enterBillingAddressLine3(String billingAddressLine3) {
		textLastname.sendKeys(billingAddressLine3);
	}
	
	public void enterBillingAddressTown(String billingAddressTown) {
		textLastname.sendKeys(billingAddressTown);
	}
	
	public void selectBillingAddressCounty(String billingAddressCounty) {
  	  	dropdownBillingCounty.click();
	  	WebElement[] countiesElements = {selectCarlow, selectCavan, selectClare, selectCork, selectDonegal, selectDublin, selectGalway, selectKerry, 
	  			selectKildare, selectKilkenny, selectLaois, selectLeitrim, selectLimerick, selectLongford, selectLouth, selectMayo, selectMeath,
	  			selectMonaghan, selectOffaly, selectRoscommon, selectSligo, selectTipperary, selectWaterford, selectWestmeath, selectWexford, 
	  			selectWicklow};

	  	List<String> countiesString = new ArrayList<String>();
	  			countiesString.add("carlow"); countiesString.add("cavan"); countiesString.add("clare"); countiesString.add("cork"); countiesString.add("donegal"); countiesString.add("dublin"); countiesString.add("galway"); countiesString.add("kerry"); 
	  					countiesString.add("kildare"); countiesString.add("kilkenny"); countiesString.add("laois"); countiesString.add("leitrim"); countiesString.add("limerick"); countiesString.add("longford"); countiesString.add("louth"); countiesString.add("mayo"); countiesString.add("meath");
	  					countiesString.add("monaghan"); countiesString.add("offaly"); countiesString.add("roscommon"); countiesString.add("sligo"); countiesString.add("tipperary"); countiesString.add("waterford"); countiesString.add("westmeath"); countiesString.add("wexford"); 
	  					countiesString.add("wicklow");
	  					
		wait.until(ExpectedConditions.elementToBeClickable(selectDublin));
	  	countiesElements[countiesString.indexOf(billingAddressCounty.toLowerCase())].click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void enterDeliveryAddressLine1(String deliveryAddressLine1) {
		textLastname.sendKeys(deliveryAddressLine1);
	}
	
	public void enterDeliveryAddressLine2(String deliveryAddressLine2) {
		textLastname.sendKeys(deliveryAddressLine2);
	}
	
	public void enterDeliveryAddressLine3(String deliveryAddressLine3) {
		textLastname.sendKeys(deliveryAddressLine3);
	}
	
	public void enterDeliveryAddressTown(String deliveryAddressTown) {
		textLastname.sendKeys(deliveryAddressTown);
	}
	
	public void selectDeliveryAddressCounty(String deliveryAddressCounty) {
		checkboxSameDeliveryAddress.click();
  	  	dropdownDeliveryCounty.click();
	  	WebElement[] countiesElements = {selectCarlow, selectCavan, selectClare, selectCork, selectDonegal, selectDublin, selectGalway, selectKerry, 
	  			selectKildare, selectKilkenny, selectLaois, selectLeitrim, selectLimerick, selectLongford, selectLouth, selectMayo, selectMeath,
	  			selectMonaghan, selectOffaly, selectRoscommon, selectSligo, selectTipperary, selectWaterford, selectWestmeath, selectWexford, 
	  			selectWicklow};

	  	List<String> countiesString = new ArrayList<String>();
	  			countiesString.add("carlow"); countiesString.add("cavan"); countiesString.add("clare"); countiesString.add("cork"); countiesString.add("donegal"); countiesString.add("dublin"); countiesString.add("galway"); countiesString.add("kerry"); 
	  					countiesString.add("kildare"); countiesString.add("kilkenny"); countiesString.add("laois"); countiesString.add("leitrim"); countiesString.add("limerick"); countiesString.add("longford"); countiesString.add("louth"); countiesString.add("mayo"); countiesString.add("meath");
	  					countiesString.add("monaghan"); countiesString.add("offaly"); countiesString.add("roscommon"); countiesString.add("sligo"); countiesString.add("tipperary"); countiesString.add("waterford"); countiesString.add("westmeath"); countiesString.add("wexford"); 
	  					countiesString.add("wicklow");
	  					
		wait.until(ExpectedConditions.elementToBeClickable(selectDublin));
	  	countiesElements[countiesString.indexOf(deliveryAddressCounty.toLowerCase())].click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void selectNext() {
		buttonConfirm.click();
	}
	
	public void selectTitle(String title) {
		dropdownTitleSelection.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectMr));

		if (title.compareToIgnoreCase("Mr") == 0) {
			selectMr.click();
		} else if (title.compareToIgnoreCase("Miss") == 0) {
			selectMiss.click();
		} else if (title.compareToIgnoreCase("Mrs") == 0) {
			selectMrs.click();
		} else {
			selectMs.click();
		}
		try
		{
			Thread.sleep(400);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectIdType(String idType) {
		dropdownIDType.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectForeignPassport));
		if (idType.toLowerCase().compareToIgnoreCase("irish driving licence") == 0) {
			selectIrishDrivingLicence.click();
		} else if (idType.toLowerCase().compareToIgnoreCase("irish passport") == 0) {
			selectIrishPassport.click();
		} else if (idType.toLowerCase().compareToIgnoreCase("public services card") == 0) {
			selectPublicServicesCard.click();
		} else if (idType.toLowerCase().compareToIgnoreCase("national identity card eu nationals") == 0) {
			selectNationalIdentityCardEUNationals.click();
		} else {
			selectForeignPassport.click();
		}
	}
	
	public void selectAllowIon()
	{
		// checkboxAllowION.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkboxAllowION);
	}
	
	public void selectTandCs()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkboxIonTandCs);
	}

	
	
	void setElementText(WebDriver driver, WebElement textInputWebElement, String textValue) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    //textInputWebElement.sendKeys("");
	    js.executeScript("arguments[0].focus()", textInputWebElement);
	    js.executeScript("arguments[0].setAttribute('value', '" + textValue + "')", textInputWebElement);
		//js.executeScript("arguments[0].setAttribute('value', '" + textValue + "')",textLastname);

	}
	

}