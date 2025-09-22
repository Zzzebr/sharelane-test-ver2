import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;


public class RegTest
{
    WebDriver driver;
    @BeforeMethod
    public void Start()
    {
        driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
    }

    @Test
    public void checkPozitiveLoginTest() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value='Continue']")).click();

        driver.findElement(By.name("first_name")).sendKeys("user_name");
        driver.findElement(By.name("last_name")).sendKeys("last_name");
        String uniqueEmail = "test" + System.currentTimeMillis() + "@example.com";
        driver.findElement(By.name("email")).sendKeys(uniqueEmail);
        String password = "password";
        driver.findElement(By.name("password1")).sendKeys(password);
        driver.findElement(By.name("password2")).sendKeys(password);
        driver.findElement(By.cssSelector("[value='Register']")).click();

        String email = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/b")).getText();

        driver.findElement(By.xpath("/html/body/center/table/tbody/tr[1]/td/table/tbody/tr/td[1]/a")).click();

        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[value='Login']")).click();

        boolean elementOnPage = driver.findElement(By.className("title_10")).isDisplayed();
        Assert.assertTrue(elementOnPage, "Элемент title_10 не отображается после успешного входа");
    }

    @AfterMethod(alwaysRun = true)
    public void quit()
    {
        driver.quit();
    }
}
