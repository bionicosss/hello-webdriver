import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Lesson07WebDriver {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.bookdepository.com/");

        new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@aria-label,'Search')]")));

        WebElement logoLink = driver.findElement(By.xpath("//a[@class='brand-link']"));
        WebElement signInLink = driver.findElement(By.xpath("(//a[@href='/account/login/to/account'])[1]"));

        WebElement searchFieldInput = driver.findElement(By.xpath("//input[@class='text-input' and @name='searchTerm']"));
        searchFieldInput.sendKeys("Jujutsu Kaisen, Vol. 5");

        List<WebElement> searchButton = driver.findElements(By.xpath("//button[@aria-label='Search']"));
        searchButton.get(0).click();

        List<WebElement> addToCartButtonForFirstResultInTheList = driver.findElements(By.xpath("(//a[contains(@class,'add-to-basket')])[1]"));
        addToCartButtonForFirstResultInTheList.get(0).click();

        new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='modal-dialog modal-md']")));
        WebElement closeModalButton = driver.findElement(By.xpath("//button[contains(@class,'close')]"));
        closeModalButton.click();
//        WebElement continueShoppingButton = driver.findElement(By.xpath("//div[@class='modal-content']/a[@class='btn btn-grey pull-left continue-shopping string-to-localize')]"));
//        continueShoppingButton.click();

        WebElement firstBookInTheSearchResult = driver.findElement(By.xpath("(//h3[@class='title']/a)[1]"));
        firstBookInTheSearchResult.click();

        Thread.sleep(3000);
        driver.quit();

//        System.setProperty("webdriver.edge.driver", "C://Webdriver/msedgedriver.exe");
//        WebDriver driverEdge = new EdgeDriver();
//        driverEdge.get("http://seleniumhq.org");
//        Thread.sleep(2000);
//        driverEdge.quit();

//        WebDriver driverFirefox = new FirefoxDriver();
//        driverFirefox.get("http://seleniumhq.org");
//        Thread.sleep(2000);
//        driverFirefox.quit();

    }
}
