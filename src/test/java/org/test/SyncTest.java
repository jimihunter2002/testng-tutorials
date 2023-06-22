package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SyncTest {
    static WebDriver driver;
    static String uname = "jimihunter18";

    public static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
    }

    @Test
    public void f() throws Exception {
        launchBrowser();
        driver.findElement(By.linkText("Register")).click();

        //implicit wait on the driver and not on a web element. that is the condition
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
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

        //Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='customer.username.errors']"))));

        WebElement elem = driver.findElement(By.xpath("//*[@id='customer.username.errors']"));
        wait.until(ExpectedConditions.textToBePresentInElement(elem, "xcxdThis username already" ));


        String usernameText = driver.findElement(By.xpath("//*[@id='customer.username.errors']")).getText();

        Assert.assertEquals(usernameText,"This username already exists.");

        //SoftAssert softAssert = new SoftAssert();
        //softAssert.assertEquals(usernameText, "Username is already chosen.");

        //take screenshot
        //TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        //File srcPath = takesScreenshot.getScreenshotAs(OutputType.FILE);
        //File destinationPath = new File("./screenshots/"+ "syncerror.png");
        //Assert.assertEquals(usernameText, "Username is already chosen.");
        // for copying screenshot
        //FileUtils.copyFile(srcPath, destinationPath);
        //softAssert.assertAll();
        driver.close();
    }
}
