package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class RobotUploadTest {
    WebDriver driver;

    @Test
    public void upload() throws Exception {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://drive.google.com/drive/u/1/my-drive"); // internal drive on windows
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//[@data-icon-name='upload']")); // upload to onedrive
        driver.findElement((By.name("files")));
        Thread.sleep(2000);

        Robot rb = new Robot();
        //rb.delay(2000);
        StringSelection ss = new StringSelection("/path-to-file-to-be-uploaded");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        //press control button, press v, release v, release control
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        //release
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);

//        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyPress(KeyEvent.VK_TAB);
        rb.keyRelease(KeyEvent.VK_TAB);
        rb.keyPress(KeyEvent.VK_TAB);
        rb.keyRelease(KeyEvent.VK_TAB);

        rb.keyPress(KeyEvent.VK_SPACE); // to be used if action button is not highlighted
        rb.keyRelease(KeyEvent.VK_SPACE);


    }
}
