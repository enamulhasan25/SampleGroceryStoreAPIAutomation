Feature: Get List of All Products as per the category

  Scenario: Get products by category
    When a GET call is made to the 'https://simple-grocery-store-api.glitch.me/products?category=coffee&results=4'
    And response code should be 200
    Then all products should have the category "coffee"