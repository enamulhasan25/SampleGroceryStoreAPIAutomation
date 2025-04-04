Feature: Overall API Status for the Simple Grocery Store Collection

  Scenario: Get API Status
    When a GET call is made to the 'https://simple-grocery-store-api.glitch.me/status'
    And response code should be 200
    Then validate the status value as 'UP' from response payload