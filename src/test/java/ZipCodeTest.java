import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class ZipCodeTest
{
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void Start()
    {
        driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void checkZipCode4Digits()
    {
        WebElement zip = driver.findElement(By.name("zip_code"));
        zip.sendKeys("1234");
        driver.findElement(By.cssSelector("[value='Continue']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/center/table/tbody/tr[4]/td/span")));
        String errorMessage;
        errorMessage = driver.findElement(By.cssSelector("error_message")).getText();
        Assert.assertEquals(errorMessage, "Oops, error on page. ZIP code should have 5 digits");
    }

    @Test
    public void checkZipCode5Digits()
    {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value='Continue']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("first_name")));
        boolean elementOnPage;
        elementOnPage = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[1]/td/table/tbody/tr/td[2]/span")).isDisplayed();
        Assert.assertTrue(elementOnPage);
    }

    @AfterMethod(alwaysRun = true)
    public void quit()
    {
        driver.quit();
    }
}
