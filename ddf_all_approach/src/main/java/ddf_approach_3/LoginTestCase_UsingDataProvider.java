package ddf_approach_3;

import java.io.FileInputStream;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class LoginTestCase_UsingDataProvider {


	public String[][] getExcelData() throws BiffException, IOException {

		FileInputStream file = new FileInputStream("C:\\Users\\siva0\\eclipse-workspace\\ActualWorkspaceSelenium\\ddf_all_approach\\src\\main\\resources\\LoginData.xls");
		Workbook workbook = Workbook.getWorkbook(file) ;
		Sheet sheet = workbook.getSheet(0);

		int rowCount = sheet.getRows(); 
		int columnCount = sheet.getColumns();

		String testData[][] = new String [rowCount-1][columnCount];

		for(int i=1; i<rowCount; i++) { 
			for(int j=0; j<columnCount; j++) {
				testData[i-1][j] = sheet.getCell(j, i).getContents(); 
			} 
		} 
		return testData; 
	}


	String [][] data = null;

	@DataProvider(name="loginData") 
	public String[][] dataProvider() throws BiffException, IOException {
		data=getExcelData(); 
		return data; 
	}



	@Test(dataProvider = "loginData")
	public static void loginTestCase(String uName, String pWord) throws InterruptedException {
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
}
