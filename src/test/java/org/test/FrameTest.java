package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FrameTest {

    WebDriver driver;


    public void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://docs.oracle.com/javase/8/docs/api");
        driver.manage().window().maximize();
        //implicit wait on the driver and not on a web element. that is the condition
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
    }

    @Test
    public void frameHandle() throws Exception {
        launchBrowser();


        driver.switchTo().frame("packageListFrame");
        driver.findElement(By.linkText("java.io")).click();
        Thread.sleep(2000);

        driver.switchTo().defaultContent();
        driver.switchTo().frame("packageFrame");
        Thread.sleep(2000);
        driver.findElement(By.linkText("FileFilter")).click();

        driver.switchTo().defaultContent();
        driver.switchTo().frame("classFrame");
        Thread.sleep(2000);
        String pageT = driver.findElement(By.xpath("//*[@class='title']")).getText();
        Assert.assertEquals(pageT, "Interface FileFilter");
        driver.close();
    }
}
