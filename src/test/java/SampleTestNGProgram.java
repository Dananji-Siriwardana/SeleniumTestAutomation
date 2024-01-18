import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SampleTestNGProgram {
	WebDriver driver;
	
 @BeforeTest
 public void setup() {                                    
 System.setProperty("webdriver.chrome.driver", "D:\\do\\TestNGDemo\\drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
 ChromeOptions options=new ChromeOptions();
 options.addArguments("--remote-allow-origins=*");
 driver=new ChromeDriver(options);
 driver.manage().window().maximize();
 driver.get("https://www.google.com");
		
 }
	
	
  @Test
  public void titleTest() {
	  String title = driver.getTitle();
	  System.out.println("Title of the page "+title);
	  
  }
    
  
  @AfterTest
  public void tearDown()
  {
	  driver.close();
  }
}
