package org.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.test.TestBase;

public class LoginPage extends TestBase {
    WebDriver driver;

    public LoginPage(WebDriver rDriver) {
        this.driver = rDriver;
        PageFactory.initElements(rDriver, this);
    }

    @FindBy(name="username")
    WebElement username;
    //driver.findElement(By.cssSelector("input[name='username']")).sendKeys(uname);
    public WebElement getUsername() {
        return username;
    }

    @FindBy(name="password")
    WebElement password;
    //driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin");
    public WebElement getPassword() {
        return password;
    }

    @FindBy(css="[value='Log In']")
    WebElement loginButton;
    //driver.findElement(By.cssSelector("[value='Log In']")).click();

    public WebElement getLoginButton() {
        return  loginButton;
    }

}
