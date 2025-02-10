@GetByFirstName
Feature: Get User API
  
 Scenario: Retrieve user by first name
    Given User is on the Get Request to Retrieve User by First Name API
    When user sends a GET request with the newly created user first name
    Then response status should match  the expected status
