package mypackage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class enqOfBuildProj {
	//Use this declarations to store respective values
	static WebDriver driver;
	static List<WebElement> elements;
	static String[] Bnames = new String[7];
	static int count;
	static JavascriptExecutor js = (JavascriptExecutor)driver;
	//Implements code to create driver and assign it to 'static' driver variable
	public void createDriver(WebDriver drvr) {
		driver = drvr;
		driver.get("https://ishahomes.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	//closes the appeared popup and live chat
	public void handlingPopup(){
//		try {
//			driver.switchTo().frame("livservMiddleFrame");
//			WebElement liveChat = driver.findElement(By.xpath("//*[@id=\'td2\']/div"));
//			liveChat.click();
//			driver.switchTo().defaultContent();
//			driver.findElement(By.xpath("//*[@id=\"elementor-popup-modal-33100\"]/div/a/i")).click();
//		}
//		catch (Exception e) {
//		}
		try {
			driver.findElement(By.xpath("//*[@id=\"livchat_close\"]/b")).click();
			driver.findElement(By.xpath("//*[name()='path' and contains(@d,'M1490 1245')]")).click();
		}
		catch (Exception e) {
		}
		try {
//			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[name()='path' and contains(@d,'M1490 1245')]")).click();
			driver.findElement(By.xpath("//*[@id=\"elementor-popup-modal-33100\"]/div/a/i")).click();
		}
		catch (Exception e) {
		}
	}
	// navigates to completed projects page and gets number of completed projects and calls writeExcelData method from ExcelUtils
	public void completedProjects() throws Exception {
		try {
			this.handlingPopup();
		}
		catch (Exception e) {
			driver.findElement(By.xpath("//*[name()='path' and contains(@d,'M1490 1245')]")).click();
		}
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id='menu-item-25810']/a")).click();
		try {
			this.handlingPopup();
		}
		catch (Exception e) {
			driver.findElement(By.xpath("//*[name()='path' and contains(@d,'M1490 1245')]")).click();
		}
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id='module_properties']/div[19]/div/div/div[2]/div/div[2]/div[1]")));
		driver.findElement(By.xpath("//*[@id=\"elementor-popup-modal-33100\"]/div/a/i")).click();
		elements = driver.findElements(By.xpath("//section[2]//div[1]/div[1]/section//div[2]//h2/a"));
		count = elements.size();
		int i=0;
		for(WebElement ele: elements) {
			Bnames[i] = ele.getText();
			i++;
			if(i==6) {
			break;
			}
		}
		ExcelUtils.writeExcelData(Bnames, count);
	}
	//verifies if 'Contact info' test is getting displayed
	public boolean contactInfoTextDisplay() {
		driver.findElement(By.xpath("/html/body/div[7]/div[2]/a")).click();
		String textDisplayed =  driver.findElement(By.xpath("/html[1]/body[1]/div[12]/div[1]")).getText();
		if(textDisplayed.contains("Contact info")) {
			return true;
		}
		else {
			return false;
		}
	}
	//reads and displays the email address for contact
	public void contactEmail() {
	    driver.findElement(By.xpath("/html[1]/body[1]/div[12]/a[1]/i[1]")).click();
	    driver.navigate().back();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//div[21]//div[1]//div[1]//div[2]//div[1]//div[2]//div[3]")));
		driver.findElement(By.xpath("//span[normalize-space()='Contact Us']")).click();
		try {
			this.handlingPopup();
		}
		catch (Exception e) {
			driver.findElement(By.xpath("//*[name()='path' and contains(@d,'M1490 1245')]")).click();
		}
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("(//h2[@class='elementor-heading-title elementor-size-default'])[1]")));
		String email = driver.findElement(By.linkText("marketing@ishahomes.com")).getText();
		System.out.println("Email address for contact: " + email);
	}
	//captures the screenshot of the result
    public void getScreenShot() throws IOException, IllegalArgumentException{
	    TakesScreenshot ss = (TakesScreenshot)driver;
	    File src = ss.getScreenshotAs(OutputType.FILE);
	    File trgFile = new File("C:\\Users\\vijay.LAPTOP-CCJ7C4DN\\eclipse-workspace\\enqofbuildproj\\Screenshots\\ss.png");
	    FileUtils.copyFile(src, trgFile);
	    System.out.println("Screenshot of the result is captured. ");
    }
	    //closes the browser
	public void closeBrowser() {
		driver.quit();
	}
	public static void run(WebDriver drvr) throws Exception{
		enqOfBuildProj test = new enqOfBuildProj();
		test.createDriver(drvr);
		test.completedProjects();
		test.contactEmail();
		test.getScreenShot();
		test.closeBrowser();
	}
 
}