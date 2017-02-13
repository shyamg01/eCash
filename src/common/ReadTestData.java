package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReadTestData {
	
	private static FileInputStream file;
	private static HSSFWorkbook workbook;
	public static HSSFSheet testScenarioSheet;
	public static String testDataObj;
	
	public static void getTestScenarioSheet(String fileName) {
		try {
			file = new FileInputStream(new File(fileName));
			workbook = new HSSFWorkbook(file);
			testScenarioSheet = workbook.getSheet("TestScenario");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HSSFSheet getTestDataSheet(String testScenario, String pageObjTestData){
		testDataObj = getPageObjectSheet(testScenarioSheet, testScenario, pageObjTestData);
        HSSFSheet testDataSheet  = workbook.getSheet(testDataObj.split("_")[0]);
        return testDataSheet;
	}
	
	public static String getTestData(HSSFSheet sheetName,String columneHeader){
		int rowNum = getRowNumber(sheetName, testDataObj);
        int columnNum = getColumnNumber(sheetName, columneHeader);
        Row row = sheetName.getRow(rowNum);
        Cell cell = row.getCell(columnNum);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        return getGlobalData(cell.getStringCellValue());
	}

	private static String getGlobalData(String cellValue) {
		if (cellValue.contains("Global")) {
			String varName=cellValue.split("\\.")[1];
			GlobalVariable globalvar = new GlobalVariable();
			Field staticField[] = GlobalVariable.class.getDeclaredFields();
			for (int i = 0; i < staticField.length; i++) {
				if (varName.equals(staticField[i].getName())) {
					Field field;
					try {
						field = globalvar.getClass().getField(staticField[i].getName());
						return (String) field.get(staticField[i].getName());
					} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return cellValue;
	}
	
	private static String getPageObjectSheet(HSSFSheet sheetName, String RowHeader,String columneHeader){
		int rowNum = getRowNumber(sheetName, RowHeader);
        int columnNum = getColumnNumber(sheetName, columneHeader);
        Row row = sheetName.getRow(rowNum);
        return row.getCell(columnNum).getStringCellValue();
	}
	
	private static int getColumnNumber(HSSFSheet sheetName, String columneHeader) {
		Row headerRow = sheetName.getRow(0);
		Iterator<Cell> cellIterator = headerRow.cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			String headerValue = cell.getStringCellValue();
			if (columneHeader.equals(headerValue))
				return cell.getColumnIndex();
		}
		return 0;

	}
	
	private static int getRowNumber(HSSFSheet sheetName, String RowHeader) {
		 Iterator<Row> rowIterator = sheetName.iterator();
		 while (rowIterator.hasNext())
         {
			 Row row = rowIterator.next();
			 if(row.getCell(0).getStringCellValue().equals(RowHeader))
				 return row.getRowNum();
         }
		 return 0;
	}

}
