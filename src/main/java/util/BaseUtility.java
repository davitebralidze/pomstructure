package util;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageSteps.FirstPageSteps;


import java.io.IOException;

import static util.PropertyLoader.returnConfigValue;

public class BaseUtility {

    protected WebDriver driver;
    protected FirstPageSteps firstPageSteps;

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    boolean isAllureFolderDeleted = false;

    @BeforeMethod
    public void setup() {
        if(!isAllureFolderDeleted) {
            Utils.deleteAllureReports();
            isAllureFolderDeleted = true;
        }
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(returnConfigValue("url.base"));
        firstPageSteps = new FirstPageSteps(driver);
    }

    @AfterMethod
    public void finish() {
        takeScreenshot();
        driver.quit();
    }

}
