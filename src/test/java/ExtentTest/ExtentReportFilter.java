package ExtentTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportFilter {
	
	ExtentReports extent = new ExtentReports();
	ExtentTest test;
	ExtentSparkReporter spark,spark1,sparkFail,sparkAll;
	
	@BeforeMethod
	public void systemInfo() {
		spark = new ExtentSparkReporter("target/Passed.html");
		test = extent.createTest("SystemInfo");
		test.assignAuthor("author");
		test.assignAuthor("author-1","author-2");
		
		extent.createTest("test").assignAuthor("Dananji").pass("Test details");
		extent.setSystemInfo("os", "Windowws");
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("DP");
		spark.config().setReportName("Test report by Dananaji");
		
	}
	
  @Test
  public void ReportFailure() {
	  sparkFail = new ExtentSparkReporter("target/SparkFail.html");
	  sparkFail.filter() //filter only failed test cases
	  .statusFilter()
	  .as(new Status[] {Status.FAIL})
	  .apply();
	  extent.attachReporter(sparkFail);
	  
	  sparkAll = new ExtentSparkReporter("target/SparkAll.html");
	  extent.attachReporter(sparkAll);
	  //failed
	  extent.createTest("FailTestDemo1")
	  .log(Status.FAIL,"This is a logging event for MyFirstFailTestDemo");
	  extent.createTest("FailTestDemo2")
	  .log(Status.FAIL,"This is a logging event for MyFirstFailTestDemo");
	  //warning
	  extent.createTest("WARNINGTestDemo1")
	  .log(Status.FAIL,"This is a logging event for WARNINGDemo1, and it's first warning!");
	  extent.createTest("WARNINGTestDemo2")
	  .log(Status.FAIL,"This is a logging event for WARNINGDemo2, and it's first warning!");
	  //skip
	  extent.createTest("SKIPTestDemo1")
	  .log(Status.FAIL,"This is a logging event for SKIP!");
	  //info
	  extent.createTest("INFOTestDemo2")
	  .log(Status.FAIL,"This is a logging event for INFO!");
	  //passed
	  extent.createTest("PASSEDTestDemo1")
	  .log(Status.FAIL,"This is a logging event for PASS!");
	  
  }
  
  @Test
  public void login() {
	  test = extent.createTest("MySecondTest");
	  test.pass("test details");
	  test.log(Status.PASS,"Test details added by Dananji");
	  
  }
  
  @Test
  public void exeptions() {
	  Throwable t = new RuntimeException("A runtime exception");
	  test = extent.createTest("MyExceptionTest");
	  test.fail(t);
	  test.log(Status.FAIL, t);
	  
  }
  
  @Test
  public void screenShots() {
	  test = extent.createTest("ScreenShotTest");
	  test.fail(MediaEntityBuilder.createScreenCaptureFromPath("img.png").build());
	  test.fail(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
  }
  
  @Test
  public void tagging() {
	  test = extent.createTest("ScreenshotTest");
	  test.assignCategory("tag");
	  test.assignCategory("tag-1","tag-2");
	  
	  extent.createTest("Test category").assignCategory("tag-1").pass("details provided for category");
	  extent.createTest("Test category").assignCategory("tag-2").pass("details provided for category");
	  extent.createTest("Test category").assignCategory("tag-3").pass("details provided for category");
  
  }
  
  @Test
  public void assignDevice() {
	  test = extent.createTest("Device Name");
	  test.assignDevice("device-name");
	  test.assignDevice("device-1","device-2");
	  
	  extent.createTest("Test").assignDevice("device-name").pass("details");
  }
  
  
  @AfterMethod
  public void endReport() {
	  extent.flush();
  }
}
