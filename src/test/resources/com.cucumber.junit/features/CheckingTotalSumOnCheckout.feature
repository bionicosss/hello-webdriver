Feature: As a user I want to search item and add item from the search results to the Cart and go to the Checkout first step

  @test
  Scenario: Subtotal and Total should be the same as sale item price and VAT = 0 on Checkout page
    Given the user open bookdepository.com website
    When the user searches Jujutsu Kaisen, Vol. 11 item via search field
    And the user adds to the Cart the first result in the search result list
    And the user clicks Checkout button
    And total order sum is 9,40 € on confirm checkout popup
    And the user clicks Checkout button on the Cart page
    Then Subtotal value is 9,40 € and VAT value is 0,00 € and Total value is 9,40 €



