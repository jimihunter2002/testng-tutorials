package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.utils.PropFileReading;

import java.io.IOException;

public class PropReading {

    WebDriver driver;


    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }


    public void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
    }


    @Test
    public void userLogin() {
        launchBrowser();
        String filePath = System.getProperty("user.dir");

        String path = filePath + "/" + "src/test/data/credentials.properties";

        //Read the credentials
        PropFileReading prop = new PropFileReading(path);

        try {
            String uname = prop.getData("uname");
            String pwd = prop.getData("pwd");
            driver.findElement(By.cssSelector("input[name='username']")).sendKeys(uname);
            driver.findElement(By.cssSelector("input[name='password']")).sendKeys(pwd);
            driver.findElement(By.cssSelector("[value='Log In']")).click();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
