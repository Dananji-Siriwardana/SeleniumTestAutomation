import org.testng.annotations.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterTest;


public class DataProviderTest {
	WebDriver driver;
	@DataProvider(name="dpdemo")
	public Object[][] dpMethod()
	{
		return new Object[][] {{"https://www.google.com/"},{"https://www.youtube.com/"}};
	}
@Test(dataProvider="dpdemo")
  public void testMethod(String str) {
	  driver.get(str);
  }
  
  
  @BeforeTest
  public void setup() {
//	  System.setProperty("webdriver.chrome.driver", "F:\\TestNGDemo\\chromedriver-win64\\chromedriver.exe");
//	  driver=new ChromeDriver();
	  System.setProperty("webdriver.chrome.driver", "D:\\do\\TestNGDemo\\drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	  ChromeOptions options=new ChromeOptions();
	  options.addArguments("--remote-allow-origins=*");
	  driver=new ChromeDriver(options);
	  //driver.get("https://www.google.com");
	  

  }

  @AfterTest
  public void tearDown() {
	  driver.close();
  }

  

}