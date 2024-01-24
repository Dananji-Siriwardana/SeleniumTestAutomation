package ExtentTest;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportScreenshot {
	WebDriver driver;
	ExtentReports extent;
	ExtentSparkReporter sparkReporter;
	ExtentTest logger;

	@BeforeTest
	public void startReport() {
		
		sparkReporter = new ExtentSparkReporter("target/DPExtentReport.html");
	  	extent = new ExtentReports();
	  	extent.attachReporter(sparkReporter);
	  	extent.setSystemInfo("Environment","Production");
		extent.setSystemInfo("User Name","Dananji");
		sparkReporter.config().setDocumentTitle("DP");
		sparkReporter.config().setReportName("DP-Test Reports");
		sparkReporter.config().setTheme(Theme.STANDARD);
	    
    }
	
	public String getScreenshot(WebDriver driver,String ScreenshotName) throws IOException {
		String dataName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(System.currentTimeMillis()));
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"/Screenshots/"+ScreenshotName+dataName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\do\\TestNGDemo\\drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	    ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(options);
		driver.get("https://www.twitter.com/");
		logger = extent.createTest("To verify Twitter Title");

	}
	
	@Test
	public void verifyTitle() {
		logger = extent.createTest("To verify Twitter Title");
		Assert.assertEquals(driver.getTitle(), "Twitter");
	}
	
	@Test
	public void verifyLogo() {
		logger = extent.createTest("To verify twitter logo");
		boolean image = driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div[1]/div[2]/div/svg")).isDisplayed();
		logger.createNode("Image is present");
		Assert.assertTrue(image);
		logger.createNode("Image is not present");
		Assert.assertFalse(image);
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL,MarkupHelper.createLabel(result.getName()+"Test case failed",ExtentColor.RED));
			logger.log(Status.FAIL,MarkupHelper.createLabel(result.getThrowable()+"Test case failed",ExtentColor.RED));
			String screenshotsPath = getScreenshot(driver,result.getName());
			logger.fail("Test case failed snapshot is below", MediaEntityBuilder.createScreenCaptureFromPath(screenshotsPath).build());
		}
		
		
		else if(result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP,MarkupHelper.createLabel(result.getName()+"Test case skipped",ExtentColor.ORANGE ));

		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS,MarkupHelper.createLabel(result.getName()+"Test case pass",ExtentColor.GREEN ));

		}
		driver.quit();
	}
	
	
	
  @AfterTest
  public void endReport() {
	  extent.flush();
  }
}
