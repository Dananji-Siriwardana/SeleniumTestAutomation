package TestNGPackage;

//import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterMethod;

public class OrangeHRMTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\do\\TestNGDemo\\drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    public void loginTest() {
        // Wait for username field to be present and visible
        WebElement usernameField = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        // Enter username
        usernameField.sendKeys("Admin");
        
       

        // Wait for password field to be present and visible
        WebElement passwordField = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        // Enter password
        passwordField.sendKeys("admin123");

        // Wait for login button to be present and visible
        WebElement loginButton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.className("orangehrm-login-button")));
        // Click login button
        loginButton.click();
    }
    
    @Test
    public void getUrl() {
    	System.out.println("Current URL = "+driver.getCurrentUrl());
    }
    
    @Test
    public void getTitle() {
    	System.out.println("Current Title = "+driver.getTitle());
    }
    
    @Test
    public void logout() {
    	//driver.findElement(By.name(null))
    	driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/header/div[1]/div[2]/ul/li/ul/li[4]/a")).click();
    	

    	}
    	
    }
    