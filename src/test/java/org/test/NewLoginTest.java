package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class NewLoginTest extends TestBase {

    String uname = "jimihunter28";
    String testMethodName;



    @BeforeMethod
    public void getTestName(Method method) {
        testMethodName = method.getName();
    }


    @Test
    public void newLogin() throws Exception {

        loginPage.getUsername().sendKeys(uname);
        loginPage.getPassword().sendKeys("admin");
        loginPage.getLoginButton().click();
        Thread.sleep(2000);

        try{
            Assert.assertEquals(false, true);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File("./screenshots/" + testMethodName + ".png");
            FileUtils.copyFile(srcFile,destFile );

        }


    }

}
