Feature: Create a new cart for the Simple Grocery Store Collection

  Scenario: Create New Cart and get that cart
    When a POST call without body is made to the "https://simple-grocery-store-api.glitch.me/carts"
    And response code should be 201
    Then capture the newly created cartId from the response