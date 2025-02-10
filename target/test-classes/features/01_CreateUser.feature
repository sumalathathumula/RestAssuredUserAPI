@Post
Feature: Create User API

  Background:
    Given User has valid credentials

  Scenario Outline: Create User with Valid Data
    Given User is on the Post Request to Create User
    When user sends a create user request with data from row <RowNumber>
    Then the response status should be <ExpectedStatus>
    

  Examples:
    | RowNumber | ExpectedStatus | ExpectedMessage            |
    | 1         | 201            | User created successfully  |
    | 2         | 201            | User created successfully  |
    | 3         | 201            | User created successfully  |
    
     Scenario Outline: Create User with Invalid Data
    Given User is on the Post Request to Create User
    When user sends a create user request with data from row <RowNumber>
    Then the response status should be <ExpectedStatus> 

  Examples:
    | RowNumber | ExpectedStatus | ExpectedMessage               |
    | 4         | 400            | Email format is invalid       |
    | 5         | 400            | Missing required fields       |
    | 6         | 400            | Contact number is not valid   |