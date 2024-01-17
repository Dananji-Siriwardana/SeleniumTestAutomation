import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGClassDemo {
	WebDriver driver;
	
@BeforeTest
public void setup() {
	System.setProperty("webdriver.chrome.driver", "D:\\do\\TestNGDemo\\drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
    ChromeOptions options=new ChromeOptions();
	options.addArguments("--remote-allow-origins=*");
	driver=new ChromeDriver(options);
	driver.get("https://www.google.com");
	
}

  @Test
  public void f() {
	  System.out.println("Hello");
  }
  
  @Test
  public void TestGoogle() {
		//String url = "https://www.google.com";
		String expected = "Google";
		//driver.get(url);
		String actual = driver.getTitle();
		Assert.assertEquals(actual,expected);
		
	}
  
  @Test
  public void TestGoogle1() {
		//String url = "https://www.google.com";
		String expected = "Google1";
		//driver.get(url);
		String actual = driver.getTitle();
		Assert.assertEquals(actual,expected);
		
	}
  
  
  @AfterTest
  public void tearDown()
  {
	  driver.close();
  }
}
