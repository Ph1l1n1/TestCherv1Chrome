import org.apache.http.util.Asserts;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class TestChromedriver {
    private static String childparent;
    public static void main(String[] args)  {
        String guinput = "//*[contains(@class,'gLFyf gsfi')]";
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String originalWindow = driver.getWindowHandle();
        driver.get("https://www.google.ru/");
        driver.findElement(By.xpath(guinput)).sendKeys("мобайл тинькофф");
        driver.findElement(By.xpath("//*[contains(text(),'тарифы')]")).click();
        driver.findElement(By.xpath("//*[contains(@href,'https://www.tinkoff.ru/mobile-operator/tariffs/')]")).click();

        String parentHandle = driver.getWindowHandle();
        for(String childHandle : driver.getWindowHandles()){
            if (!childHandle.equals(parentHandle)){
                driver.switchTo().window(childHandle);
                childparent = childHandle;
            }
        }
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Тарифы Тинькофф Мобайл", actualTitle);
        driver.switchTo().window(parentHandle).close();
        driver.switchTo().window(childparent);
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://www.tinkoff.ru/mobile-operator/tariffs/", url);
        System.out.println("good");
        driver.close();

    }
}
