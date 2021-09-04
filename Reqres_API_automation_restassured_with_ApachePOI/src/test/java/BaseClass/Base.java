package BaseClass;

import utilities.ExcelApiTest;

public class Base {
	
	public static String baseURL;
	public static String endpoint;
	public static String ExpectedStatusCode;
	public static String DataTag;
	public static String ExpectedDataTag;
	public static String xlFilePath;
	//"C:/Users/mnavsupe/eclipse-workspace/demo/ApachePOI_API/src/test/resources/Reqes.xlsx";
	public static String sheetName = "SANITY";
	
	public Base()
	{
		xlFilePath = System.getProperty("user.dir")+"/src/test/resources/Reqes.xlsx";
	}
	
}
