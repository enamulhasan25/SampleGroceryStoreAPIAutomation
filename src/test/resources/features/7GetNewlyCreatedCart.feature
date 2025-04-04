Feature: Get Cart from the Create Cart API

  Scenario: Get a cart

    When a GET call is made to the newly created cartId "https://simple-grocery-store-api.glitch.me/carts/{cartId}"
    And response code should be 200
    Then print the response payload