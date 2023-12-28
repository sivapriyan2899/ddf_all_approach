package ddf_practice_usingPOI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Login_TestCase {

	static List<String> userName = new ArrayList<String>();
	static List<String> passWord = new ArrayList<String>();

	public void readExcelData() throws IOException {
		FileInputStream file = new FileInputStream("C:\\Users\\siva0\\eclipse-workspace\\ActualWorkspaceSelenium\\ddf_all_approach\\src\\main\\resources\\LoginData_new.xlsx");
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rowIterator = sheet.iterator();

		int i=2;
		while (rowIterator.hasNext()) {
			Row rowValue = rowIterator.next();

			Iterator<Cell> cellValue = rowValue.iterator();

			while(cellValue.hasNext())
			{
				if(i%2==0) {
					userName.add(cellValue.next().getStringCellValue());
				}
				else {
					passWord.add(cellValue.next().getStringCellValue());
				}
				i++;
			}
		}
		workbook.close();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Login_TestCase login = new Login_TestCase();
		login.readExcelData();
		System.out.println("username is "+ userName);
		System.out.println("password is "+ passWord);
	}

}
