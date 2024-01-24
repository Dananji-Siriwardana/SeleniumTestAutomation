package ExtentTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportGherkinBDD {
	ExtentReports extent= new ExtentReports();
	ExtentTest test;
	ExtentSparkReporter spark = new ExtentSparkReporter("target/SparkBDD.html");
	
  @BeforeMethod
  public void BDDGherkinTest() {
   
      extent.attachReporter(spark);
	  
	  try {
		  ExtentTest feature = extent.createTest(new GherkinKeyword("Feature"),"Refund item Test by Dananaji");
		  ExtentTest scenario = feature.createNode(new GherkinKeyword("scenario"),"Jeff returns a faulty microwave");
		  scenario.createNode(new GherkinKeyword("Given"),"Jeff has brought a microwave for $100").pass("pass");
		  scenario.createNode(new GherkinKeyword("And"),"he has a receipt").pass("pass");
		  scenario.createNode(new GherkinKeyword("When"),"he returns the microwave").pass("pass");
		  scenario.createNode(new GherkinKeyword("Then"),"Jeff should be refunded $100").pass("fail");
		  		  
	  }
	  catch(Exception e){
		  e.getMessage();
		  
	  }
	  
  }
  @Test
  public void GherkinDialectDemo() {
      extent.attachReporter(spark);
	  
	  try {
	      //System.out.println("Executing GherkinDialectDemo method");
		  extent.setGherkinDialect("de");
		  ExtentTest feature = extent.createTest(new GherkinKeyword("Funktionalitat"),"Refund item VM");
		  ExtentTest scenario = feature.createNode(new GherkinKeyword("szenario"),"Jeff returns a faulty microwave");
		  ExtentTest given = scenario.createNode(new GherkinKeyword("Angenommen"),"Jeff has bought a microwave").skip("skip"); 
		  given.createNode(new GherkinKeyword("Aaa"),"Jeff has bought two microwave").skip("skip");  

	  }
	  catch(Exception e) {
		  e.getMessage();			  
	  }	
		
}
  
  @Test
  public void exceptionMethod() {
	  Throwable t = new RuntimeException("A runtime exception");
	  ExtentTest test = extent.createTest("MyExceptionTest");
	  test.fail(t);
	  test.log(Status.FAIL,t);
  }
  
  @AfterMethod
  public void endReport() {
	  extent.flush();
  }
  
}
