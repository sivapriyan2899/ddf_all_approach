package ddf_Practice_usingJXL;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginCheckTest {

	String[][] data = null;
	
	@DataProvider (name = "loginData")
	public String[][] getValue() throws BiffException, IOException {
		data= getExcelValue();
		return data;
	}
	
	public String[][] getExcelValue() throws BiffException, IOException {
		FileInputStream file = new FileInputStream("C:\\\\Users\\\\siva0\\\\eclipse-workspace\\\\ActualWorkspaceSelenium\\\\ddf_all_approach\\\\src\\\\main\\\\resources\\\\LoginData.xls");
		Workbook workbook = Workbook.getWorkbook(file);
		Sheet sheet = workbook.getSheet(0);
		
		int rowCount = sheet.getRows();
		int columncount = sheet.getColumns();
		
		String sheetData[][]= new String [rowCount-1][columncount];
		for(int i=1; i<rowCount; i++) {
			for(int j=0; j<columncount; j++) {
				sheetData[i-1][j] = sheet.getCell(j,i).getContents();
			}
		}
		return sheetData;
	}
	
	
	
	@Test(dataProvider = "loginData")
	public void loginCheck(String uName, String pWord) {
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\WebDrivers\\chromedriver-win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		
		//Thread.sleep(2000);

		WebElement username = driver.findElement(By.id("user-name"));
		username.sendKeys(uName);

		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys(pWord);

		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		//Thread.sleep(5000);
		driver.close();
	}
}
