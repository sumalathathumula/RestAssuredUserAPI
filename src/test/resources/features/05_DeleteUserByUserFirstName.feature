
@DeleteByFirstName
  Feature: Delete User API 
  
  Scenario: Delete user by valid First Name
    Given User is on the DELETE Request to Delete User by First Name API
    When user sends a DELETE request with a valid first name
    Then response status should be 200
