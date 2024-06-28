import Util.Utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import javax.xml.crypto.Data;
import java.time.Duration;
import java.util.List;

public class Test {
    public static void main(String[] args) throws InterruptedException {


        String URL = "https://flatrocktech.com/";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        WebDriver driver = new ChromeDriver(options);
        Actions actions = new Actions(driver);
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        driver.manage().window().maximize();
        driver.get(URL);

        Thread.sleep(5000);

        //main box xPath

        WebElement element = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/main/div[5]/div[2]/div/div[2]/div/div[1]/div[1]/div[2]/div"));

        Utils.scrollToASpecificElement(driver, element);
        actions.moveToElement(element).build().perform();

        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[1]/main/div[5]/div[2]/div/div[2]/div/div[1]/div[1]/div[2]/div/div[1]/div[2]/div/ul//a"));


        for (WebElement link : elements) {
            String elementLink = link.getAttribute("href");
            Response response = RestAssured.get(URL);
            int responseCode = response.getStatusCode();
            softAssert.assertTrue(responseCode < 400, elementLink + " is not working");
            System.out.println(responseCode);
        }
        softAssert.assertAll();
        driver.quit();
    }
}
