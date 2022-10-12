package salesforce;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public static String[][] readExcel(String excelName,String SheetName) throws IOException {		
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook("./TestData/"+excelName+".xlsx");
		XSSFSheet sheet = xssfWorkbook.getSheet(SheetName);
		int lastRowNum = sheet.getLastRowNum();		
		int lastCellNum = sheet.getRow(0).getLastCellNum();		
		String[][] data=new String[lastRowNum][lastCellNum];
		for (int i = 1; i <=lastRowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < lastCellNum; j++) {
				String stringCellValue = row.getCell(j).getStringCellValue();
				System.out.println(stringCellValue);
				data[i-1][j]=stringCellValue;
				}
			System.out.println("*****************");
		}
		xssfWorkbook.close();
		return data;
	}
}
