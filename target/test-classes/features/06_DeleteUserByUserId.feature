
@DeleteByUserId
Feature: Delete User API 

  Scenario: Delete user by valid User ID
    Given User is on the DELETE Request to Delete User by ID API
    When user sends a DELETE request with a valid user ID
    Then response status code should be 200
