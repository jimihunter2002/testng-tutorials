package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DropDownTest {
    WebDriver driver;


    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    public void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://demo.guru99.com/selenium/newtours/register.php");
        driver.manage().window().maximize();
        //implicit wait on the driver and not on a web element. that is the condition
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
    }

    @Test
    public void defaultCountry() {
        launchBrowser();
        Select drpCountry = new Select(driver.findElement(By.name("country")));
        String selectedCountry = drpCountry.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedCountry, "ALBANIA");
    }

    @Test
    public void completeForm() {
        launchBrowser();
        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys("Julie");
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys("Bobby");
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("09765437142");
        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys("Julie");
        driver.findElement(By.id("userName")).sendKeys("julie.bobby@abc.com");
        driver.findElement(By.cssSelector("input[name='address1']")).sendKeys("111 Broom street");
        driver.findElement(By.cssSelector("input[name='city']")).sendKeys("Woodgreen");
        driver.findElement(By.cssSelector("input[name='state']")).sendKeys("London");
        driver.findElement(By.cssSelector("input[name='postalCode']")).sendKeys("N11 21P");

        // select a country from dropdown
        Select countryList = new Select(driver.findElement(By.tagName("select")));
        //countryList.selectByValue("UNITED KINGDOM");
        countryList.selectByVisibleText("UNITED KINGDOM");

        driver.findElement(By.id("email")).sendKeys("julie.bobby@abc.com");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("12345678");
        driver.findElement(By.cssSelector("input[name='confirmPassword']")).sendKeys("12345678");
        //driver.findElement(By.cssSelector("input[name='submit']")).click();

    }
}
