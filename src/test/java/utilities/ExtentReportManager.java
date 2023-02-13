package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter; // UI of the Report
	public ExtentReports extent; // Populate Common Info on the Report
	public ExtentTest test; // Creating Test Case Entries in the Report and Update Status of the Test Methods
	
	String repName;
	
	public void onStart(ITestContext context) 
	{
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // timestamp
		repName = "Test-Report-"+ timestamp+ ".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); // specify the location where report will be saved.
		
		sparkReporter.config().setDocumentTitle("Opencart Automation Report"); // Title of the Report
		sparkReporter.config().setReportName("Opencart Functional Testing"); // Name of the Report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		// User defined Parameters and its values
		extent.setSystemInfo("Application", "opencart"); 
		extent.setSystemInfo("Module", "User");

		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		test = extent.createTest(result.getName()); // Create a New Entry in the Report
		test.log(Status.PASS, "Test Case PASSED is: "+result.getName()); // Update the Status P/F/S
	}
	
	public void onTestFailure(ITestResult result) 
	{
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case FAILED is: "+result.getName());
		test.log(Status.FAIL, "Test Case FAILED Cause is : "+result.getThrowable());
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
		try 
		{
			String imgPath = new BaseClass().captureScreen(result.getName()); 
			test.addScreenCaptureFromPath(imgPath); // To add the captured screenshot in the Report w.r.t TC
		}
		catch(Exception e1) 
		{
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) 
	{
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case SKIPPED is: "+result.getName());
		test.log(Status.SKIP, "Test Case SKIPPED Cause is : "+result.getThrowable());
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) 
	{
		extent.flush(); // Consolidates and Organizes the Report
	}
	
	
}
