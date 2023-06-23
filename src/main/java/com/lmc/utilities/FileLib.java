package com.lmc.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileLib 
{
	private static String CONFIG_PROP_PATH = "src\\test\\resources\\configuration\\config.properties";
	private static FileInputStream fileInputStream;
	private static XSSFWorkbook workBook;
	private static Workbook workBookFactory;
	private static FileOutputStream fileOutputStream;
	private static ExtentManager extentManager;

	public FileLib() {
	
		extentManager = new ExtentManager();
	}

	public void getFileInputStream(String path) throws Exception {
		fileInputStream = new FileInputStream(path);
	}

	public void getFileOutputStream(String path) throws Exception {
		fileOutputStream = new FileOutputStream(path);
	}

	public String readExcelData(String path, String sheetName, int rowNo, int cellNo) throws Throwable {
		getFileInputStream(path);
		workBook = new XSSFWorkbook(fileInputStream);
		String excelValue = workBook.getSheet(sheetName).getRow(rowNo).getCell(cellNo).toString();
		workBook.close();
		return excelValue;

	}

	public void writeExcelData(String path, String sheetName, int rowNo, int cellNo, String data) throws Throwable {

		getFileInputStream(path);
		workBookFactory = WorkbookFactory.create(fileInputStream);
		workBookFactory.getSheet(sheetName).getRow(rowNo).createCell(cellNo).setCellValue(data);

		getFileOutputStream(path);
		workBookFactory.write(fileOutputStream);
		workBookFactory.close();

	}

	public int getRowCount(String path, String sheetName) throws Throwable {

		getFileInputStream(path);
		workBookFactory = WorkbookFactory.create(fileInputStream);
		int rowCount = workBookFactory.getSheet(sheetName).getLastRowNum();
		workBookFactory.close();
		return rowCount;

	}

	public String readPropertyData(String filePath,String key) throws Throwable {

		getFileInputStream(filePath);
		Properties prop = new Properties();
		prop.load(fileInputStream);
		String propValue = prop.getProperty(key, "Incorrect key");
		return propValue;
	}
	
	public String getPropertyData(String key) throws Throwable {
		return new FileLib().readPropertyData(CONFIG_PROP_PATH, key);
	}

	public void deleteOldFies() {
		File file = new File(System.getProperty("user.dir") + ".\\HTMLReport");
		try {
			if (file != null) {
				FileUtils.deleteDirectory(file);
				extentManager.logInfo("Old Reports are Deleted......");
				
			}
		} catch (Exception e) {
			extentManager.logInfo("Old Reports are not Deleted......");

		}
	}
}
