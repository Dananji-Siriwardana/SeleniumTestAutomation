package ExtentTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentConfigFirstTest {
	
	ExtentReports extent;
	ExtentSparkReporter spark;
	
	
  @BeforeTest
  public void startReport() {
	  extent = new ExtentReports();
	  spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/target/myfirstreport.html");
	  extent.attachReporter(spark);
	  spark.config().setTheme(Theme.DARK);
	  spark.config().setDocumentTitle("DP");
	  spark.config().setReportName("Test report by Danaji");
  }
  
  @Test
  public void test1() {
	  extent.createTest("MyFirstTest").log(Status.PASS,"Test is passed");
	  extent.createTest("MySecondTest").log(Status.FAIL,"Test is failed");
 
  }
  
  @AfterTest
  public void endReport() {
	  extent.flush();
  }
  
  
}
