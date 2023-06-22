package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.utils.ExcelFileReader;

public class DataProverTest {

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

    @Test(dataProvider = "inputData")
    public void dataProviderInput(String username, String password)  throws Exception {
        launchBrowser();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("input[name='username']")).sendKeys(username);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("[value='Log In']")).click();
        Thread.sleep(2000);

    }

    //data driven testcases
    @DataProvider(name="inputData")
    public Object[][] inputData() {
//        String [][] data = {
//                {"test123", "pass123"},
//                {"test456", "pass456"},
//                {"admin", "admin"}
//        };
        String filePath = System.getProperty("user.dir");
        String path = filePath + "/" + "src/test/data/ddt.xlsx";
        String [][] data = new String[0][];

        ExcelFileReader dataRader = new ExcelFileReader(path);
        try {
            int rowCount = dataRader.getRowCount("Sheet1");
            int columnCount = dataRader.getColumnCount("Sheet1", 1);
            data = new String[rowCount][columnCount];

            for(int i=1; i <= rowCount; i++){
                for(int j=0; j< columnCount; j++) {
                    data[i-1][j] = dataRader.getCellData("Sheet1", i, j);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return data;
    }
}
