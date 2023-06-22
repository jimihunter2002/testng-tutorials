package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.File;


public class OpenBrowserTestNg {
    static WebDriver driver;
    static String uname = "jimihunter18";

    @AfterMethod
    public static void closeBrowser() {
        driver.quit();
    }


    public static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public static void parameterLogin() throws Exception {
        launchBrowser();
        driver.findElement(By.linkText("Register")).click();
        Thread.sleep(2000);
//        driver.quit();

    }

    @Test(priority = 3)
    public static void userLogout() throws Exception {
        userLogin();
        driver.findElement(By.linkText("Log Out")).click();
        Thread.sleep(2000);
//        driver.quit();
    }

    @Test(priority = 4)
    public static void userLogin() {
        launchBrowser();
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin");
        driver.findElement(By.cssSelector("[value='Log In']")).click();

    }

    @Test(priority = 2, enabled = true)
    public static void registerUser() throws Exception {
        //parameterLogin();
        //fill the form and leave out username
        launchBrowser();
        driver.findElement(By.linkText("Register")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("customer.firstName")).sendKeys("Jimi");
        driver.findElement(By.id("customer.lastName")).sendKeys("Hunter");
        driver.findElement(By.id("customer.address.street")).sendKeys("151 Turnpike Lane");
        driver.findElement(By.id("customer.address.city")).sendKeys("Woodgreen");
        driver.findElement(By.id("customer.address.state")).sendKeys("London");
        driver.findElement(By.id("customer.address.zipCode")).sendKeys("N17 6DB");
        driver.findElement(By.id("customer.phoneNumber")).sendKeys("07312341974");
        driver.findElement(By.id("customer.ssn")).sendKeys("12345678");
        driver.findElement(By.id("customer.username")).sendKeys(uname);
        driver.findElement(By.id("customer.password")).sendKeys("123456");
        driver.findElement(By.id("repeatedPassword")).sendKeys("123456");

        driver.findElement(By.cssSelector("[value='Register']")).click();
        Thread.sleep(2000);
        String usernameText = driver.findElement(By.xpath("//*[@id='customer.username.errors']")).getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(usernameText, "Username is already chosen.");

        //take screenshot
//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        File srcPath = takesScreenshot.getScreenshotAs(OutputType.FILE);
//        File destinationPath = new File("./screenshots/"+ "usererror.png");
//        //Assert.assertEquals(usernameText, "Username is already chosen.");
//        // for copying screenshot
//        FileUtils.copyFile(srcPath, destinationPath);
//        softAssert.assertAll();
    }
}
