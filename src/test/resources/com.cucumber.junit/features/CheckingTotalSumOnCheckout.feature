Feature: As a user I want to search item and add item from the search results to the Cart and go to the Checkout first step

  @test
  Scenario: Subtotal and Total should be the same as sale item price and VAT = 0 on Checkout page
    Given the user open bookdepository.com website
    When the user searches "Jujutsu Kaisen, Vol. 10" item via search field
    And the user adds to the Cart the first result in the search result list
    And confirm checkout popup is displayed
    And the user clicks Checkout button
    And total order sum is "8,95 €" on confirm checkout popup
    And the user clicks Checkout button on the Cart page
    Then Subtotal value is "8,95 €" and VAT value is "0,00 €" and Total value is "8,95 €"



