package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass
{
	@Test(groups={"Regression", "Master"})
	void test_account_Registration() throws InterruptedException
	{
		logger.debug("Application logs..............");
		
		logger.info("*** Starting TC_001_AccountRegistrationTest ***");
		
		try
		{
			HomePage hp = new HomePage(driver);
			
			hp.clickmyAccount();
			logger.info("Clicked on My Account Link");
			
			hp.clickRegister();
			logger.info("Clicked on Register Link");
			
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			
			logger.info("Providing Customer Data");
		
			regpage.setFirstName(randomeString().toUpperCase()); // If you want to set Firstname in uppercase
		
			regpage.setLastName(randomeString().toUpperCase());
		
			regpage.setEmail(randomeString()+"@gmail.com");
		
			// For scenario where using same value for set Password and confirm password then use as below
			//String password = randomAlphaNumeric();
			//regpage.setPassword(password);
			//regpage.setConfirmPassword(password);
						
			regpage.setPassword(randomAlphaNumeric());
		
			regpage.setPrivacyPolicy();
		
			regpage.clickContinue();
			logger.info("Clicked on Continue");
		
			String confmsg = regpage.getConfirmationMsg();
		
			logger.info("Validating Expected Message");
			Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Not getting Expected Message");
		}
		catch(Exception e) 
		{
			logger.error("Test Failed");
			Assert.fail();
		}		
		
		logger.info("*** Finished TC_001_AccountRegistrationTest ***");
	}
}
