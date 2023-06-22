package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ParallelTestTwo {

    WebDriver driver;

    @Test
    public void google() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.manage().window().maximize();

    }

    @Test
    public void hdfc() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.hdfc.com");
        driver.manage().window().maximize();
    }
}
