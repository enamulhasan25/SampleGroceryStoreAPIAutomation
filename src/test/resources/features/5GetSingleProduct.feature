Feature: Get a single product by ID

  Scenario Outline: Get Single Product by using productId.
    When a GET call is made to the endpoint "https://simple-grocery-store-api.glitch.me/products/{productId}" with pathParam of key "productId" and value "<productId>"
    And response code should be 200
    Then productId "<productId>" should display in the response payload

    Examples:
      | productId |
      | 4643      |
      | 4646      |

    # This feature will run for fetching single product by ID. But if i need to run this with multiple test data then this might me useful.
    # If not required this just pass the keyName and the value in the line4.