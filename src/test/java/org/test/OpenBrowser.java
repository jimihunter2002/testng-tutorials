package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenBrowser {

    static WebDriver driver;
    static String uname = "jimihunter5";

    public static void main(String[] args) throws Exception {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Register")).click();
        Thread.sleep(2000);


        registerUser();
        userLogout();
        userLogin();

    }

    public static void userLogout() throws Exception {
        driver.findElement(By.linkText("Log Out")).click();
        Thread.sleep(2000);
    }

    public static void userLogin() {
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin");
        driver.findElement(By.cssSelector("[value='Log In']")).click();
    }


    public static void registerUser() throws Exception {
        //fill the form and leave out username
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

        try {
            String usernameText = driver.findElement(By.xpath("//*[@id='customer.username.errors']")).getText();
            if(usernameText.equalsIgnoreCase("Username is Required.")) {
                System.out.println("Username text is : " + usernameText);
                System.out.println("Passed");
            } else {
                System.out.println("Username text is not equal: Failed ");
            }
        } catch (NoSuchElementException e) {
            System.out.println("The username web element error is not available");
        }
    }
}
