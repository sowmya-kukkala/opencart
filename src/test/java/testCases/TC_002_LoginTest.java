package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass{
	
	@Test(groups= {"Sanity", "Master"})
	public void test_login() 
	{
		try
		{
		logger.info("****Starting TC_002_LoginTest****");
		
		HomePage hp = new HomePage(driver);
		hp.clickmyAccount();
		logger.info("Clicked on My Account");
		hp.clickLogin();
		logger.info("Clicked on Login");
		
		LoginPage lp = new LoginPage(driver);
		logger.info("Providing Login Details");
		lp.setEmail(rb.getString("email"));  // Valid email, get it from config.properties
		lp.setPassword(rb.getString("password"));  // Valid password, get it from config.properties
		lp.clickLogin();
		logger.info("Clicked on Login Button");
		
		MyAccountPage macc = new MyAccountPage(driver);
		boolean target = macc.isMyAccountPageExists();
		Assert.assertEquals(target, true, "Invalid Login Data");
		}
		catch(Exception e) 
		{
			Assert.fail();
		}
		
		logger.info("****Finished TC_002_LoginTest****");
	}

}
