package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ParallelTest {
    WebDriver driver;

    @Test
    public void virtusa() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.virtusa.com");
        driver.manage().window().maximize();

    }

    @Test
    public void yahoo() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.yahoo.com");
        driver.manage().window().maximize();
    }

}
