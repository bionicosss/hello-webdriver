package com.cucumber.junit.steps;

import com.cucumber.junit.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Stepdefs {
    private HomePage homePage = new HomePage();

    @Given("the user open bookdepository.com website")
    public void openBookdepositoryComWebsite() {
        homePage.openWebsite();
    }

    @When("the user searches {string} item via search field")
    public void searchBookViaSearchField(String book) {
        homePage.waitUntilElementDisplayed(10, HomePage.SEARCH_FIELD_BUTTON);
        homePage.searchFieldInput().sendKeys(book);
        homePage.searchFieldButton().get(0).click();
    }

    @And("the user adds to the Cart the first result in the search result list")
    public void addToTheCartFirstSearchResult() {
        homePage.AddToCartButtonFirstSearchResult().get(0).click();
    }

    @And("confirm checkout popup is displayed")
    public void confirmCheckoutPopupIsDisplayed() {
        homePage.waitUntilElementDisplayed(10, HomePage.DIALOG_MODAL);
    }

    @And("the user clicks Checkout button")
    public void clickCheckoutButton() {
//        String saleBookPrice = homePage.salePrice().getText();
        homePage.checkoutButtonOnModal().click();
//        return saleBookPrice;
    }

    @And("total order sum is {string} on confirm checkout popup")
    public void checkTotalSumIsEqualItemPrice(String bookPrice) {
        homePage.waitUntilElementDisplayed(10, HomePage.CHECKOUT_BUTTON);
        String totalOrderSum = homePage.totalSumOnCartPage().getText();
        assertAll("Checking Total Order sum on Confirm checkout popup",
                () -> assertEquals( bookPrice, totalOrderSum, "Total on Confirm checkout popup isn't as expected.")
        );
    }

    @And("the user clicks Checkout button on the Cart page")
    public void clickCheckoutButtonOnTheCartPage() {
        homePage.checkoutButton().click();
    }

    @Then("Subtotal value is {string} and VAT value is {string} and Total value is {string}")
    public void checkSubtotalValueIsEqualSaleItemPrice(String bookPrice, String vat, String bookPrice2) {
        String totalOrderSumOnCheckout = homePage.totalSumOnCheckoutPage().getText();
        String subTotalOnCheckout = homePage.subTotalOnCheckoutPage().getText();
        String vatOnCheckout = homePage.vatOnCheckoutPage().getText();
//        System.out.println("Subtotal = "+ subTotalOnCheckout);
//        System.out.println("VAT = "+ vatOnCheckout);
//        System.out.println("Total = "+ totalOrderSumOnCheckout);
        assertAll("Checking Subtotal, VAT, Total",
                () -> assertEquals(bookPrice, subTotalOnCheckout,"Subtotal on Checkout page isn't as expected."),
                () -> assertEquals(vat, vatOnCheckout, "VAT isn't zero."),
                () -> assertEquals(bookPrice2, totalOrderSumOnCheckout,"Total on Checkout page isn't as expected."));
    }
}
