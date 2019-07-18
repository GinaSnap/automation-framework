package common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelUtilities {
	
	public void getExcelSpreadsheet() {
		try {
			String filePath = "https://docs.google.com/spreadsheets/d/1165m1ceQaiucvqetm8u9JDE0cMvCDh592LbpSJqCQsI/edit#gid=0";
			InputStream inputStream = new URL(filePath).openStream();

			//Get the workbook instance for XLS stream
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = workbook.getSheetAt(0);
			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				//For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {
					
					Cell cell = cellIterator.next();
					
					switch(cell.getCellType()) {
						case BOOLEAN:
							System.out.print(cell.getBooleanCellValue());
							break;
						case NUMERIC:
							System.out.print(cell.getNumericCellValue());
							break;
						case STRING:
							System.out.print(cell.getStringCellValue());
							break;
						default:
							System.out.print(cell.getStringCellValue());
					}
				}
				System.out.println("");
			}
			inputStream.close();
			workbook.close();
			
//			FileInputStream fs;
//			fs = new FileInputStream(FilePath);
//			Workbook wb = Workbook.getWorkbook(fs);
//			
//			Sheet sh = wb.getSheet(0);
//			String cellContent = sh.getCell(0,0).getContents();
//			System.out.println(cellContent);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
