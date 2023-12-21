package mypackage;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSetup {
	
	static WebDriver driver;
	
	public static WebDriver getWebDriver() {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://ishahomes.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}

}
