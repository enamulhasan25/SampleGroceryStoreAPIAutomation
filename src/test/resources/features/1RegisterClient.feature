Feature: Create New Client for Simple Grocery Store Collection

  Scenario: Register a new client
    Given I have request payload
    When a POST call is made to the "https://simple-grocery-store-api.glitch.me/api-clients"
    And response code should be 201
    Then capture the access token