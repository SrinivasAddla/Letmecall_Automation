package com.lmc.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.HashMap;
import java.util.Map;

public class DataDriven 
{
//	@Test(dataProvider = "data")
//	public void integrationTest(Map<String, String> map) {
//		System.out.println("-------------Test case started ----------------");
//		System.out.println(map.get("Restaurant Name"));
//		System.out.println(map.get("Email Id"));
//		System.out.println(map.get("Mobile Number"));
//		System.out.println(map.get("Website URL"));
//		System.out.println(map.get("Food Type"));
//		System.out.println(map.get("Message"));
//		System.out.println("-------------Test case Ended ----------------");
//	}
	
	
	public Object[][] dataSupplier(String filePath) throws IOException
	{
		//File file = new File(filePath);
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);

		int lastRowNum = sheet.getLastRowNum();
		int lastCellNum = sheet.getRow(0).getLastCellNum();
//		System.out.println(lastRowNum);
//		System.out.println(lastCellNum);

		Object[][] obj = new Object[lastRowNum][1];

		for (int i = 0; i < lastRowNum; i++) 
		{
			Map<Object, Object> datamap = new HashMap<>();
			for (int j = 0; j < lastCellNum; j++) 
			{
				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
			}
			obj[i][0] = datamap;
		}
		wb.close();
		fis.close();
		return obj;
	}
}
