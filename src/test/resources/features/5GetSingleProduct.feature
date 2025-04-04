Feature: Get a single product with productId

  Scenario: Get Single Product
    When a GET call is made to the single product endpoint "https://simple-grocery-store-api.glitch.me/products/{productId}" with productId "4643"
    And response code should be 200
    Then productId "4643" should display in the response payload