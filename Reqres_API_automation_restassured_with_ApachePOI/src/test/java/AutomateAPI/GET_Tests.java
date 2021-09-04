package AutomateAPI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

//import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Sheet;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.tools.ant.types.resources.selectors.InstanceOf;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseClass.Base;
import io.restassured.response.Response;
import utilities.ExcelApiTest;
import io.restassured.RestAssured;


public class GET_Tests extends Base{
	
	ExcelApiTest eat;
	
	@BeforeTest
	public void setup() throws IOException
	{
		new Base();
		eat=new ExcelApiTest(xlFilePath);
	}
	
	@Test (dataProvider = "userData")
	public void GETaccess(String baseURL,String endpoint, String ExpectedStatusCode, String DataTag,String ExpectedDataTag )
	{
		RestAssured.baseURI = baseURL;
		Response res = RestAssured.given().when().get(endpoint);

		// Get info about json path tag values
		System.out.println(res.jsonPath().getString(DataTag).toString());
	
		Assert.assertTrue(res.jsonPath().getList(DataTag).contains(ExpectedDataTag));
		String str = ExpectedStatusCode;
		Assert.assertEquals(res.statusCode(), (int)Double.parseDouble(str));
		
		
	}
	
	
	
	  @DataProvider(name="userData")
	    public Object[][] userFormData() throws Exception
	    {
	        Object[][] data = testData(xlFilePath, sheetName);
	        return data;
	    }
	     
	    public Object[][] testData(String xlFilePath, String sheetName) throws Exception
	    {
	    	
	    	FileInputStream fs = new FileInputStream(xlFilePath);
			Workbook wb = WorkbookFactory.create(fs);
			Sheet sh = (Sheet) wb.getSheet("SANITY");
			
	        
	        
	        eat = new ExcelApiTest(xlFilePath);
	        int rows = eat.getRowCount(sheetName);
	        int columns = eat.getColumnCount(sheetName);
	        Object[][] excelData = new Object[rows-1][columns];
	             
	        for(int i=1; i<rows; i++)
	        {
	            	baseURL =  (String) eat.getCellData(xlFilePath, 0, i);
	            	excelData[i-1][0] = baseURL;
	           // 	System.out.println("Base is: "+baseURL);
	            	endpoint =  (String) eat.getCellData(xlFilePath, 1, i);        
	            	excelData[i-1][1] = endpoint;
	          //  	System.out.println("endpoint is: "+endpoint);
	            	ExpectedStatusCode = (String) eat.getCellData(xlFilePath, 2, i);
	          
	         //   	System.out.println(ExpectedStatusCode);
	            	excelData[i-1][2] = ExpectedStatusCode;
	            	
	        //    	System.out.println("ExpectedStatusCode is: "+ExpectedStatusCode);
	            	DataTag =  (String) eat.getCellData(xlFilePath, 3, i);       
	            	excelData[i-1][3] = DataTag;
	        //    	System.out.println("DataTag is: "+DataTag);
	            	ExpectedDataTag =  (String) eat.getCellData(xlFilePath, 4, i);  
	            	excelData[i-1][4] = ExpectedDataTag;
	        //    	System.out.println("ExpectedDataTag is: "+ExpectedDataTag);
	             
	        }
	        return excelData;
	    }
	    
	    @AfterTest()
	    public void teardown()
	    {

	    	//Write teardown code here	
	    	System.out.println("TearDown");
	    }

}
