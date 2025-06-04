Feature: Get List of All Products as per the category

  Scenario Outline: Get products by category.
    When a GET call is made to the endpoint "https://simple-grocery-store-api.glitch.me/products" with queryParam of key "category" and value "<category>"
    And response code should be 200
    Then all products should have the category "coffee"

    Examples:
      | category |
      | coffee   |

    # This feature will run for fetching single products by Category. But if i need to run this with multiple test data then this might me useful.
    # If not required this just pass the keyName and the value in the line4.