package KaprukaTest;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KTestNGDemo {
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\do\\TestNGDemo\\drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	    ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(options);
		driver.get("https://www.kapruka.com/shops/customerAccounts/accountLogin.jsp");
		
	}
	
  @Test
  public void systemTesting() {
	  driver.findElement(By.name("email")).sendKeys("8@gmail.com");
	  driver.findElement(By.name("password")).sendKeys("test");
	  driver.findElement(By.name("Login")).click();
	  //cart
	  driver.get("https://www.kapruka.com/srilanka_online_shopping.jsp?d=ducky");
	  driver.findElement(By.className("catalogueV2Button")).click();
	  driver.findElement(By.id("addtocartbutton")).click();

	  String url = driver.getCurrentUrl();
	  System.out.println("current url is: "+url);
  }
  
  @Test
  public void getUrl() {
	  
	  
	  
	  
  }
  
}
