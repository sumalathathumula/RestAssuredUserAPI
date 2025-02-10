
@Get
Feature: Get User API
  

    Scenario: Retrieve all users
    Given User is on the Get Request to Retrieve All Users API
    When user sends a GET request to retrieve all users
    Then response status should match expected status
