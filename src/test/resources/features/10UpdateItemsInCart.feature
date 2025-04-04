Feature: Update an item into cart for the Simple Grocery Store Collection

  Scenario: Update an item in a cart
    Given I have request payload to update an item to cart
    When a PATCH call is made to the update cart endpoint "https://simple-grocery-store-api.glitch.me/carts/{cartId}/items/{itemId}" with itemId
    And response code should be 204
    Then print the response payload after quantity added