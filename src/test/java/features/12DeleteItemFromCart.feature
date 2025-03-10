Feature: Delete an item from the cart for the Simple Grocery Store Collection

  Scenario: Delete an item from cart
    When a DELETE call is made for product removing from cart "https://simple-grocery-store-api.glitch.me/carts/{cartId}/items/{itemId}" with cartId and itemId
    And response code should be 204
    Then print the response payload after product is deleted