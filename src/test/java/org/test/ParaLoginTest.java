package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class ParaLoginTest {
    WebDriver driver;
    String uname = "jimihunter18";
    String testMethodName;


    @AfterMethod
    public void closeBrowser(Method method) {

        driver.quit();
    }

    @BeforeMethod
    public void getTestName(Method method) {
        testMethodName = method.getName();
    }

    public void launchBrowser() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

    @Test(testName = "user login test")
    public void userLogin() throws Exception {
        launchBrowser();
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin");
        driver.findElement(By.cssSelector("[value='Log In']")).click();
        Thread.sleep(2000);
        try{
            Assert.assertEquals(false, true);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File("./screenshots/" + testMethodName +".png");
            FileUtils.copyFile(srcFile,destFile );

        }


    }


}
