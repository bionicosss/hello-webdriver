
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Lesson08jUnit {

        @BeforeAll
        public static void initAll() {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                assertEquals("src/main/resources/chromedriver.exe", System.getProperty("webdriver.chrome.driver"));
        }
        WebDriver driver = new ChromeDriver();

        @BeforeEach
        public void openPage() { driver.get("https://www.bookdepository.com/"); }

        @Tag("checkout")
        @Test
        public void checkingTotalSumOnCheckout() throws InterruptedException {


        new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@aria-label,'Search')]")));

        WebElement logoLink = driver.findElement(By.xpath("//a[@class='brand-link']"));
        WebElement signInLink = driver.findElement(By.xpath("(//a[@href='/account/login/to/account'])[1]"));
        WebElement searchFieldInput = driver.findElement(By.xpath("//input[@class='text-input' and @name='searchTerm']"));
        List<WebElement> searchButton = driver.findElements(By.xpath("//button[@aria-label='Search']"));

        searchFieldInput.sendKeys("Jujutsu Kaisen, Vol. 5");
        searchButton.get(0).click();

        List<WebElement> addToCartButtonForFirstResultInTheList = driver.findElements(By.xpath("(//a[contains(@class,'add-to-basket')])[1]"));
        addToCartButtonForFirstResultInTheList.get(0).click();

        new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='modal-dialog modal-md']")));

        WebElement checkoutButtonOnPopUp = driver.findElement(By.xpath("//a[contains(@class,'continue-to-basket')]"));
        checkoutButtonOnPopUp.click();

        new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[contains(@class,'optimizely-control')])[last()]")));

        WebElement totalSumOnCartPage = driver.findElement(By.xpath("//dl[@class='total']/dd"));
        WebElement checkoutButton = driver.findElement(By.xpath("(//a[contains(@class,'optimizely-control')])[last()]"));
        String totalOrderSum = totalSumOnCartPage.getText();

        assertAll("Checking Total Order sum on Confirm checkout popup",
                () -> assertEquals( "8,65 €", totalOrderSum, "Total on Confirm checkout popup isn't as expected.")
                );

        checkoutButton.click();

        WebElement totalSumOnCheckoutPage = driver.findElement(By.xpath("(//dd[contains(@class,'total-price')])[last()]"));
        WebElement subTotalOnCheckoutPage = driver.findElement(By.xpath("(//dd[@class='text-right'])[3]"));
        WebElement vatOnCheckoutPage = driver.findElement(By.xpath("(//dd[contains(@class,'total-tax')])[last()]"));

        String subTotalOnCheckout = subTotalOnCheckoutPage.getText();
        String vatOnCheckout = vatOnCheckoutPage.getText();
        String totalOrderSumOnCheckout = totalSumOnCheckoutPage.getText();

//        System.out.println("Subtotal = "+ subTotalOnCheckout);
//        System.out.println("VAT = "+ vatOnCheckout);
//        System.out.println("Total = "+ totalOrderSumOnCheckout);

//        assertEquals("Subtotal on Checkout page isn't as expected.","8,65 €", subTotalOnCheckout);
//        assertEquals("VAT isn't zero.", "0,00 €", vatOnCheckout);
//        assertEquals("Total on Checkout page isn't as expected.","8,65 €", totalOrderSumOnCheckout);

        assertAll("Checking Subtotal, VAT, Total",
                 () -> assertEquals("8,65 €", subTotalOnCheckout,"Subtotal on Checkout page isn't as expected."),
                 () -> assertEquals("0,00 €", vatOnCheckout, "VAT isn't zero."),
                 () -> assertEquals("8,65 €", totalOrderSumOnCheckout,"Total on Checkout page isn't as expected."));

    }
        @AfterEach
        public void closeBrowser() { driver.quit(); }
}

