Feature: Replace a product from the cart for the Simple Grocery Store Collection

  Scenario: Replace a product from cart
    Given I have request payload to replace product from cart
    When a PUT call is made to replacing a product from cart endpoint "https://simple-grocery-store-api.glitch.me/carts/{cartId}/items/{itemId}" with cartId and itemId
    And response code should be 204
    Then print the response payload after product is replaced