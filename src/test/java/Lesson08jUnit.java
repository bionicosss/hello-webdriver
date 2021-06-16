
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


public class Lesson08jUnit {

        @Tag("checkout")
        @Test
        public void checkingTotalSumOnCheckout() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.bookdepository.com/");

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
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[contains(@class,'checkout-btn')])[last()]")));

        WebElement totalSumOnCartPage = driver.findElement(By.xpath("//dl[@class='total']/dd"));
        WebElement checkoutButton = driver.findElement(By.xpath("(//a[contains(@class,'checkout-btn')])[last()]"));
        String totalOrderSum = totalSumOnCartPage.getText();

        assertEquals("8,49 €", totalOrderSum);

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

        assertEquals("Subtotal on Checkout page isn't as expected.","8,49 €", subTotalOnCheckout);
        assertEquals("VAT isn't zero.", "0,00 €", vatOnCheckout);
        assertEquals("Total on Checkout page isn't as expected.","8,49 €", totalOrderSumOnCheckout);

                Thread.sleep(2000);
                driver.quit();

    }
}

