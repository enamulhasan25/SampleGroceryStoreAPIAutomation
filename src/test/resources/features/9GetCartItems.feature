Feature: Get cart items list with cartId for the Simple Grocery Store Collection

  Scenario: Get cart items
    When a GET call is made to the newly created cartId "https://simple-grocery-store-api.glitch.me/carts/{cartId}/items"
    And response code should be 200
    Then print the response payload

    # For this feature file no need step definition GetCartWithCartId class is enough.