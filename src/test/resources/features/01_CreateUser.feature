@Post
Feature: Create User API

  Background:
    Given User has valid credentials

  Scenario Outline: Create User
    Given User is on the Post Request to Create User
    When user sends a create user request with data from row "<Scenario>"
    Then the response status should be equal to ExpectedStatus


  Examples:
    | Scenario 			    |
    | Valid Data        |
    | Invalid Data      |
    