package pu.hui.httpTest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pu.hui.httpTest.TestSuiteByExcel;

public class ExcelUtil {
	private static XSSFSheet excelWSheet;
	private static XSSFWorkbook excelWBook;
	private static XSSFCell cell;
	private static XSSFRow row;

	public static void setExcelFile(String path, String sheetName) {
		FileInputStream excelFileInputStream;
		try {
			excelFileInputStream = new FileInputStream(path);
			excelWBook = new XSSFWorkbook(excelFileInputStream);
			excelWSheet = excelWBook.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			TestSuiteByExcel.testCaseResult = false;
			System.out.println("Excel 路径设定失败");
			e.printStackTrace();
		}
	}

	public static String getCellData(int rowNum, int colNum) throws Exception {
		try {
			cell = excelWSheet.getRow(rowNum).getCell(colNum);
			String cellDataString = cell.getCellType() == XSSFCell.CELL_TYPE_STRING ? cell
					.getStringCellValue() + ""
					: String.valueOf(Math.round(cell.getNumericCellValue()));
			return cellDataString;

		} catch (Exception ex) {
			TestSuiteByExcel.testCaseResult = false;
			ex.printStackTrace();
			return "";
		}
	}
	
	public static int getLastRowNum() {
		return excelWSheet.getLastRowNum();
	}
	//设定要操作的Excel的文件路径
	public static void setExcelFile(String pathString) {
		//FileInputStream excelFileInputStream;
		try {
			
			InputStream in= ExcelUtil.class.getResourceAsStream(pathString);
			if(null == in) {
				in = new FileInputStream(pathString);
			}
			//实例化Excel文件的XSSFWorkbook对象
			excelWBook = new XSSFWorkbook(in);
		} catch (Exception e) {
			TestSuiteByExcel.testCaseResult = false;
			System.out.println("Excel 路径设定失败");
			e.printStackTrace();
		} 
	}
	//读取指定Sheet中的指定单元格函数，此函数只支持扩展名为.xlsx的Excel文件
	public static String getCellData(String sheetName, int rowNum, int colNum) throws Exception {
		excelWSheet = excelWBook.getSheet(sheetName);
		try{
			//通过函数参数指定单元格的行号和列号，获取指定的单元格对象
			cell = excelWSheet.getRow(rowNum).getCell(colNum);
			//如果单元格的内容为字符串类型，则使用getStringCellValue方法获取单元格内容
			//如果单元格的内容为数字类型，则使用getNumericCellValue方法获取单元格内容
			String cellDataString = cell.getCellType() == XSSFCell.CELL_TYPE_STRING?cell.getStringCellValue() + ""
					:String.valueOf(Math.round(cell.getNumericCellValue()));
			//返回指定单元格的字符串内容
			return cellDataString;
		}catch(Exception e){
			TestSuiteByExcel.testCaseResult = false;
			e.printStackTrace();
			return "";
		}
	}
	
	//获取指定Sheet中的数据总行数
	public static int getRowCount(String sheetName) {
		excelWSheet = excelWBook.getSheet(sheetName);
		int number = excelWSheet.getLastRowNum();
		return number;
	}

	//在Excel的指定Sheet中，获取第一次包含指定测试用例序号文字的行号
	public static int getFirstRowContainsTestCaseID(String sheetName, String testCaseName, int colNum) throws Exception {
		int i;
		try {
			excelWSheet = excelWBook.getSheet(sheetName);
			int rowCount = ExcelUtil.getRowCount(sheetName);
			for (i = 0; i < rowCount; i++) {
				//使用循环的方法遍历测试用例序号列的所有行，判断是否包含某个测试用例序号关键字
				if(ExcelUtil.getCellData(sheetName, i, colNum).equalsIgnoreCase(testCaseName)){
					//如果包含，则退出for循环，并返回包含测试用例序号关键字的行号
					break;
				}
			}
			return i;
		} catch (Exception e) {
			TestSuiteByExcel.testCaseResult = false;
			e.printStackTrace();
			return 0;
		}
	}
	
	//获取指定Sheet中某个测试用例步骤的个数
	public static int getTestCaseLastStepRow(String sheetName, String testCaseID, int testCaseStartRowNumber) throws Exception {
		try {
			excelWSheet = excelWBook.getSheet(sheetName);
			/**
			 * 从包含指定测试用例序号的第一行开始逐行遍历，直到某一行不出现指定测试用例序号
			 * 此时的遍历次数就是此测试用例步骤的个数
			 */
			for(int i=testCaseStartRowNumber;i<ExcelUtil.getRowCount(sheetName)-1;i++){
				if(!testCaseID.equals(ExcelUtil.getCellData(sheetName, i, Constants.COL_TESTCASEID))){
					int number = i;
					return number;
				}
			}
			int number = excelWSheet.getLastRowNum()+1;
			return number;
		} catch (Exception e) {
			TestSuiteByExcel.testCaseResult = false;
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void setCellData(String sheetName, int rowNum, int colNum, String result) {
		excelWSheet = excelWBook.getSheet(sheetName);
		try {
			row = excelWSheet.getRow(rowNum);
			cell = row.getCell(colNum, row.RETURN_BLANK_AS_NULL);
			if(null == cell) {
				cell = row.createCell(colNum);
				cell.setCellValue(result);
			}else {
				cell.setCellValue(result);
			}
			FileOutputStream fileOutputStream = new FileOutputStream(Constants.PATH_EXCELFILE);
			excelWBook.write(fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
