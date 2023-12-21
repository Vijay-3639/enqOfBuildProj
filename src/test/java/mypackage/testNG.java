package mypackage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
 
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;
 
public class testNG {
 
	static WebDriver driver;
	static boolean check;
	@BeforeTest
	@Parameters({"browser"})
	void multipleBroswers(String drvr)throws Exception{
		if(drvr.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(drvr.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		else if(drvr.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
	}
	@Test
	void test() throws Exception {
		enqOfBuildProj test = new enqOfBuildProj();
		test.createDriver(driver);
		test.completedProjects();
		check = test.contactInfoTextDisplay();
		test.contactEmail();
		test.getScreenShot();
		test.closeBrowser();
	}
	@Test
	void contactInfoTest() {
		Assert.assertEquals(check,false);
	}

 
}