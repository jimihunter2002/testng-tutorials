package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.PhantomJsDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.pages.LoginPage;
import org.testng.annotations.*;

import java.lang.reflect.Method;


public class TestBase {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    @Parameters({"Browser","URL"})
    public void setup(String Browser, String URL) throws Exception{
        if(Browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (Browser.equalsIgnoreCase("Edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (Browser.equalsIgnoreCase("Firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            System.out.println("Specify browser ro run with");
        }

        // Synchronization of webdriver using PageFactory
        loginPage = new LoginPage(driver);
        driver.get(URL);
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

//    @AfterMethod
//    public void closeBrowser() {
//
//        driver.close();
//    }



    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
