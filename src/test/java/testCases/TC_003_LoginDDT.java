package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass{

	@Test(dataProvider = "LoginData", dataProviderClass=DataProviders.class)
	public void test_LoginDDT(String email, String password, String exp) 
	{
		logger.info("**** Started TC_003_LoginDDT ****");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickmyAccount();
		logger.info("Clicked on My Account");
		hp.clickLogin();
		logger.info("Clicked on Login");
		
		LoginPage lp = new LoginPage(driver);
		logger.info("Providing Login Details");
		lp.setEmail(email); 
		lp.setPassword(password); 
		lp.clickLogin();
		logger.info("Clicked on Login Button");
		
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetpage = macc.isMyAccountPageExists();
		logger.info("My Account Page Exists");
		
		if(exp.equals("Valid")) 
		{
			if(targetpage==true) 
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else 
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equals("Invalid")) 
		{
			if(targetpage==true) 
			{
				MyAccountPage maccpage = new MyAccountPage(driver);
				maccpage.clickLogout();
				Assert.assertTrue(false);
			}
			else 
			{
				Assert.assertTrue(true);
			}
		}
		}
		
		catch(Exception e) 
		{
			Assert.fail();
		}
		
		logger.info("**** Finished TC_003_LoginDDT ****");
	}
	
}
