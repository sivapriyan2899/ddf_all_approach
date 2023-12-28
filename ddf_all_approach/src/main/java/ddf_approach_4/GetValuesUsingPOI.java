package ddf_approach_4;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetValuesUsingPOI {

	static List<String> usernameList = new ArrayList<String>();
	static List<String> passwordList = new ArrayList<String>();
	
	
	public void getExcelValue() throws IOException {

		FileInputStream file = new FileInputStream("C:\\Users\\siva0\\eclipse-workspace\\ActualWorkspaceSelenium\\ddf_all_approach\\src\\main\\resources\\LoginData_new.xlsx");
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);

		
		Iterator<Row> rowIterator = sheet.iterator();
		while(rowIterator.hasNext()) {
			Row rowValue = rowIterator.next();
			Iterator<Cell> columnIterator = rowValue.iterator();
			int i=2;
			while(columnIterator.hasNext()) {

				if(i%2==0) {
					usernameList.add(columnIterator.next().getStringCellValue());
				}else {
					passwordList.add(columnIterator.next().getStringCellValue()) ;
				}
				i++;
			}
		}
		
		workbook.close();
	}
	
	
	public void execute() throws IOException, InterruptedException {
		
		for(int i=0; i<usernameList.size(); i++) {
			login(usernameList.get(i), passwordList.get(i));
		}
		
	}
	
	
	
	public static void login(String uName, String pWord) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\WebDrivers\\chromedriver-win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		Thread.sleep(2000);

		WebElement username = driver.findElement(By.id("user-name"));
		username.sendKeys(uName);

		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys(pWord);

		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		Thread.sleep(5000);
		driver.close();
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		GetValuesUsingPOI readExcel = new GetValuesUsingPOI();
		readExcel.getExcelValue();
		System.out.println("username list "+ usernameList);
		System.out.println("username list "+ passwordList);
		
		readExcel.execute();
		
		
	}

}
