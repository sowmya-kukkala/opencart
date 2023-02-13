package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
	}
	
	// Elements 
	
	@FindBy(name="firstname") WebElement txtFirstname;
	
	@FindBy(name = "lastname") WebElement txtLastname;
	
	@FindBy(name="email") WebElement txtEmail;
	
	@FindBy(name="password") WebElement txtPassword;
	
	@FindBy(name="agree") WebElement chkdPolicy;
	
	@FindBy(xpath="//button[normalize-space()='Continue']") WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;
	
	// Action Methods
	
	public void setFirstName(String fname) 
	{
		txtFirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname) 
	{
		txtLastname.sendKeys(lname);
	}
	
	public void setEmail(String email) 
	{
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String pwd) 
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setPrivacyPolicy() throws InterruptedException 
	{
		Thread.sleep(10000);
		chkdPolicy.click();
		
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click();", chkdPolicy);
		
//		Actions actions = new Actions(driver); 
//		actions.moveToElement(chkdPolicy).click().build().perform();
		
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", chkdPolicy);
//		chkdPolicy.click();
	}
	
	public void clickContinue() 
	{
		btnContinue.click();
	}
	
	public String getConfirmationMsg() 
	{
		try 
		{
			return (msgConfirmation.getText());
		}
		catch(Exception e) 
		{
			return(e.getMessage());
		}
	}

}
