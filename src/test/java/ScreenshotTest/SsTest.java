package ScreenshotTest;

import java.io.File;
import java.io.IOException;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SsTest {
    WebDriver driver;

    @BeforeTest
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", "D:\\do\\TestNGDemo\\drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://www.kapruka.com/shops/customerAccounts/accountLogin.jsp");

        driver.findElement(By.name("email")).sendKeys("s@gmail.com");
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.name("Login")).click();
    }

    @Test
    public void getTitleTest() {
       
    	String expectedTitle = "Your Account"; // Change this to the expected title
        
        // Example of a test to get the title
        String actualTitle = driver.getTitle();
        System.out.println("Page Title: " + actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle, "Page title is incorrect");
        
       
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        //String methodName = result.getMethod().getMethodName();

        if (result.getStatus() == ITestResult.SUCCESS) {
            //System.out.println("Test passed: " + methodName);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File trg = new File("./Screenshots/Pass/" +  "_pass.png");
            FileUtils.copyFile(src, trg);
        } 
        else if (result.getStatus() == ITestResult.FAILURE) {
            //System.out.println("Test failed: " + methodName);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File trg = new File("./Screenshots/Fail/" + "_fail.png");
            FileUtils.copyFile(src, trg);
      
        }
        
        
    }
    
}
