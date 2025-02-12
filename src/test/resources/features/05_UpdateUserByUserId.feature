@Put
Feature: Update User API

  Background:
    Given User has valid credentials

  Scenario Outline: Update User with Valid Data
    Given User is on the put Request to update User with user id
    When user sends a put request with data from row <RowNumber>
    Then the updated user response status code should be <ExpectedStatus>
    

  Examples:
    | RowNumber | ExpectedStatus | ExpectedMessage            |
    | 7        | 200            | User created successfully  |