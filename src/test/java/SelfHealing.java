import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SelfHealing {

    WebDriver delegate;

    @Test
    public void HealingTest() throws IOException,InterruptedException
    {
        WebDriverManager.chromedriver().setup();

        delegate = new ChromeDriver();
        SelfHealingDriver driver = SelfHealingDriver.create(delegate);
        driver.get("https://www.southwest.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        Thread.sleep(35000);
        try {
            driver.findElement(By.xpath("//*[@id='LandingAirBookingSearchForm_submit-button']")).click();
            System.out.println("Done:");
        } catch (Exception io) {
            System.out.println(io.toString());
        } finally {
            Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");

        }
    }
}
