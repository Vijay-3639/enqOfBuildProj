package mypackage;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {


	public static void writeExcelData(String[] data, int count) throws IOException {
		// TODO Auto-generated method stub
		FileOutputStream file = new FileOutputStream(System.getProperty("user.dir")+"\\excelFiles\\names.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet names = wb.createSheet();
		XSSFRow r1 = names.createRow(0);
		XSSFRow r2 = names.createRow(1);
		r1.createCell(0).setCellValue("Total number of completed projects");
		r2.createCell(0).setCellValue(count);
		r1.createCell(1).setCellValue("Names of first five completed projects");
		for(int i=1; i<data.length-1; i++) {
			if(i==1) {
				r2.createCell(1).setCellValue(data[i-1]);
				continue;
			}
			names.createRow(i).createCell(1).setCellValue(data[i-1]);
		}
		wb.write(file);
		wb.close();
		file.close();
		
	}

}
