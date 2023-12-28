package ddf_approach_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTestCase {

	
	String [][] data = {{"Admin", "admin123"},{"Admin","dummy"},{"bava","admin123"},{"bava","dummy"}};
	
	@DataProvider(name="loginData")
	public String[][] loginDataProvider() {
		return data;
	}
	
	
	@Test(dataProvider = "loginData")
	public void loginTestCase(String uName, String pWord) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\WebDrivers\\chromedriver-win32\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		Thread.sleep(2000);
		
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(uName);
		
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(pWord);
		
		WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button"));
		loginButton.click();
		
		Thread.sleep(5000);
		driver.close();
	}
	
}
