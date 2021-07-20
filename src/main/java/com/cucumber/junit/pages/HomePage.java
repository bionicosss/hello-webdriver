package com.cucumber.junit.pages;

import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends BasePage {
    private static final String SITE_URL = "https://www.bookdepository.com/";
    private static final String SEARCH_FIELD_INPUT = "//input[@class='text-input' and @name='searchTerm']";
    public static final String SEARCH_FIELD_BUTTON = "//button[@aria-label='Search']";
    private static final String ADD_TO_CART_BUTTON_FIRST_SEARCH_RESULT = "(//a[contains(@class,'add-to-basket')])[1]";
    public static final String DIALOG_MODAL = "//div[@class='modal-dialog modal-md']";
    private static final String SALE_PRICE = "//b[contains(@class,'big total')]";
    private static final String CHECKOUT_BUTTON_MODAL = "//a[contains(@class,'continue-to-basket')]";
    private static final String TOTAL_SUM_ON_CART_PAGE = "//dl[@class='total']/dd";
    public static final String CHECKOUT_BUTTON = "(//a[contains(@class,'optimizely-control')])[last()]";
    private static final String TOTAL_SUM_CHECKOUT = "(//dd[contains(@class,'total-price')])[last()]";
    private static final String SUBTOTAL_SUM_CHECKOUT = "(//dd[@class='text-right'])[3]";
    private static final String VAT_CHECKOUT = "(//dd[contains(@class,'total-tax')])[last()]";

    public void openWebsite(){
        DriverManager.getDriver().get(SITE_URL);
    }

    public void waitUntilElementDisplayed(int time, String element){
        new WebDriverWait(DriverManager.getDriver(),time)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
    }

    public WebElement searchFieldInput() {
        return findElement(By.xpath(SEARCH_FIELD_INPUT));
    }

    public List<WebElement> searchFieldButton() {
        return findElements(By.xpath(SEARCH_FIELD_BUTTON));
    }

    public boolean isSearchFieldButtonDisplayed(){
        return isElementDisplayed(By.xpath(SEARCH_FIELD_BUTTON));
    }

    public List<WebElement> AddToCartButtonFirstSearchResult() {
        return findElements(By.xpath(ADD_TO_CART_BUTTON_FIRST_SEARCH_RESULT));
    }

    public WebElement salePrice() {
        return findElement(By.xpath(SALE_PRICE));
    }

    public WebElement checkoutButtonOnModal() {
        return findElement(By.xpath(CHECKOUT_BUTTON_MODAL));
    }

    public WebElement totalSumOnCartPage() {
        return findElement(By.xpath(TOTAL_SUM_ON_CART_PAGE));
    }

    public WebElement checkoutButton() {
        return findElement(By.xpath(CHECKOUT_BUTTON));
    }

    public WebElement totalSumOnCheckoutPage() {
        return findElement(By.xpath(TOTAL_SUM_CHECKOUT));
    }

    public WebElement subTotalOnCheckoutPage() {
        return findElement(By.xpath(SUBTOTAL_SUM_CHECKOUT));
    }

    public WebElement vatOnCheckoutPage() {
        return findElement(By.xpath(VAT_CHECKOUT));
    }

}

