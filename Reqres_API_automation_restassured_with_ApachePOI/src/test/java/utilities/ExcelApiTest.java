package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BaseClass.Base;

public class ExcelApiTest extends Base{

//	public String xlFilePath;
	
	   public FileInputStream fis = null;
	   public XSSFWorkbook workbook = null;
	   public  XSSFSheet sheet ;
	  

	   public XSSFRow Rows = null;
	   public XSSFCell cell = null;
	   String xlFilePath;
	 
	public ExcelApiTest(String xlFilePath) throws IOException {
		this.xlFilePath = xlFilePath;
	       fis = new FileInputStream(xlFilePath);
	       workbook = new XSSFWorkbook(fis);
	       fis.close();
	}

	public int getRowCount(String s)
	{
		sheet = workbook.getSheet(sheetName);
	       int rowCount = sheet.getLastRowNum()+1;
	       System.out.println("Rowcount: " + rowCount);
	       return rowCount;
	}

	public int getColumnCount(String sheetName) {
		// TODO Auto-generated method stub
		sheet = workbook.getSheet(sheetName);
	       Rows = sheet.getRow(0);
	       int colCount = Rows.getLastCellNum();
	       System.out.println("Rowcount: " + colCount);
	       return colCount;
	}

	public Object getCellData(String sheetName, int j, int i) {
		// TODO Auto-generated method stub
		Rows = sheet.getRow(i);
		
		//System.out.println(Rows.getCell(j).toString());
		return Rows.getCell(j).toString();
		

		
	}
}
