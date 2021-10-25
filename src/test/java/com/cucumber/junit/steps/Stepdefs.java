package com.cucumber.junit.steps;

import com.cucumber.junit.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;


public class Stepdefs {
    private HomePage homePage = new HomePage();
    static final Logger logger = LogManager.getLogger(Stepdefs.class);
//    final static Logger logger = Logger.getLogger(Stepdefs.class);

    @Given("the user open bookdepository.com website")
    public void openBookdepositoryComWebsite() {
        logger.info("Open bookdepository.com website.");
        homePage.openWebsite();
    }

    @When("^the user searches ([\\w\\s]+,?[\\w\\s]*.?[\\w\\s]*) item via search field$")
    public void searchBookViaSearchField(String book) {
        logger.info("Search item via search field");
        homePage.waitUntilElementDisplayed(10, HomePage.SEARCH_FIELD_BUTTON);
        homePage.searchFieldInput().sendKeys(book);
        homePage.searchFieldButton().get(0).click();
    }

    @And("the user adds to the Cart the first result in the search result list")
    public void addToTheCartFirstSearchResult() {
        logger.info("Add to the Cart the first result in the search result list");
        homePage.AddToCartButtonFirstSearchResult().get(0).click();
    }

    @And("the user clicks Checkout button")
    public void clickCheckoutButton() {
//        String saleBookPrice = homePage.salePrice().getText();
        logger.info("Click Checkout button");
        homePage.waitUntilElementDisplayed(10, HomePage.DIALOG_MODAL);
        homePage.checkoutButtonOnModal().click();
//        return saleBookPrice;
    }

    @And("^total order sum is (\\d+,\\d{2}\\s€) on confirm checkout popup$")
    public void checkTotalSumIsEqualItemPrice(String bookPrice) {
        homePage.waitUntilElementDisplayed(10, HomePage.CHECKOUT_BUTTON);
        String totalOrderSum = homePage.totalSumOnCartPage().getText();
        assertAll("Checking Total Order sum on Confirm checkout popup",
                () -> assertEquals( bookPrice, totalOrderSum, "Total on Confirm checkout popup isn't as expected.")
        );

    }

    @And("the user clicks Checkout button on the Cart page")
    public void clickCheckoutButtonOnTheCartPage() {
        logger.info("Click Checkout button on the Cart page");
        homePage.checkoutButton().click();
    }

    @Then("^Subtotal value is (\\d+,\\d{2}\\s€) and VAT value is (\\d+,\\d{2}\\s€) and Total value is (\\d+,\\d{2}\\s€)$")
    public void checkSubtotalValueIsEqualSaleItemPrice(String bookPrice, String vat, String bookPrice2) {
//        logger.info();
        try {
            logger.info("Verifying Subtotal, VAT, Total values");
            String totalOrderSumOnCheckout = homePage.totalSumOnCheckoutPage().getText();
            String subTotalOnCheckout = homePage.subTotalOnCheckoutPage().getText();
            String vatOnCheckout = homePage.vatOnCheckoutPage().getText();
//            logger.info("Subtotal = " + subTotalOnCheckout);
//            logger.info("VAT = " + vatOnCheckout);
//            logger.info("Total = " + totalOrderSumOnCheckout);
            assertAll("Checking Subtotal, VAT, Total",
                    () -> assertEquals(bookPrice, subTotalOnCheckout, "Subtotal on Checkout page isn't as expected."),
                    () -> assertEquals(vat, vatOnCheckout, "VAT isn't zero."),
                    () -> assertEquals(bookPrice2, totalOrderSumOnCheckout, "Total on Checkout page isn't as expected."));
            logger.info("Exiting test.");
        } catch (Exception e){
            String errorMessage = e.getMessage();

            logger.error("Error: {}", e);
        }
    }
}
