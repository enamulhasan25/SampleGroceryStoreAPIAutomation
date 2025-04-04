Feature: Get List of All Products from the Sample Grocery Store API Collection

  Scenario: Get list of all products
    When a GET call is made to the 'https://simple-grocery-store-api.glitch.me/products'
    And response code should be 200
    Then print the list of products from the response