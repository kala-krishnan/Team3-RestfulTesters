@ClassModule @LMS
Feature: LMS Class Module

  Background: 
    Given Set Auth to bearer token for class

  @CreateClass
  Scenario Outline: Check if admin is able to create class in LMS
    Given Admin creates POST Request for the LMS with "<requestType>" in class module
    When Admin calls POST request class with endpoint
    Then Admin receive created status for class request

    Examples: 
      | requestType |
      | CreateClass  |
