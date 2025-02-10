
@GetById
Feature: Get User API

    Scenario: Retrieve user by ID
    Given User is on the Get Request to Retrieve User by ID API
    When user sends a GET request with the newly created user ID
    Then response status code should match expected status
