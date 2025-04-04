Feature: Create an order for the item added to the cart using Simple Grocery Store Collection

  Scenario: Create an order
    Given I read cartId from the text file "src/test/resources/cartId.txt"
    And I generate a random customer name
    When a POST call is made to the order creation "https://simple-grocery-store-api.glitch.me/orders" with payload
      """
      {
        "cartId": "<cartId>",
        "customerName": "<customerName>"
      }
      """
    And response code should be 201
    And print the response payload
    Then capture the orderId from the response payload