Feature: Get Cart from the Create Cart API

  Scenario: Get a cart with existing cartID
    Given user has cart ID generated and stored in the file "src/test/resources/cartId.txt"
    When a GET call is made to the endpoint "https://simple-grocery-store-api.glitch.me/carts/{cartId}" with pathParam of key "cartId" and value ""
    And response code should be 200
    Then print the response payload