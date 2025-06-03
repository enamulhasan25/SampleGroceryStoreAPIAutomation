Feature: Get all orders list with newly created orderId for the Simple Grocery Store Collection

  Scenario: Get all orders
    When a GET call is made to the "https://simple-grocery-store-api.glitch.me/orders" with auth token
    And response code should be 200
    Then print the response payload

    # For this feature file no need step definition GetCartWithCartId class is enough.