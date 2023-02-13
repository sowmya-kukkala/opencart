package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	// DataProvider 1
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException
	{
		String path = ".\\testData\\Opencart_LoginData.xlsx"; // taking excel file from testData folder
		
		ExcelUtility xlUtil = new ExcelUtility(path); // Creating an object ExcelUtility
		
		int totalrows = xlUtil.getRowCount("Sheet1");
		int totalcols = xlUtil.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String[totalrows][totalcols]; // Created for two dimensional
		
		for(int row=1;row<=totalrows;row++) // 1  Read the data from excel storing in two dimensional array
		{
			for(int col=0;col<totalcols;col++) //0 
			{
				logindata[row-1][col] = xlUtil.getCellData("Sheet1", row, col); // 1, 0 to save the array memory setting row-1, col
			}
		}
		
		return logindata; // Returning Two Dimenstion Array
	}
	
	// Data Provider 2
	
	// Data Provider 3

}
