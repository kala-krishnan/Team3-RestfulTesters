@userModule
Feature: LMS API User Module

Background:
Given Set Auth to bearer token

Scenario Outline: Check if admin able to create a new user with valid endpoint and request body 
Given Admin creates POST Request with request body "<requestType>" for LMS User Module
When Admin calls Post Https method  with valid endpoint for LMS User Module
Then Admin receive created  status for LMS User Module
Examples:
| requestType  |
| CreateUserValid   |
| CreateUserInValidPhNumber   | 

Scenario Outline: Check if admin able to retrieve all user with facets and filters LMS User Module  
Given Admin creates GET Request "<requestType>" for LMS User Module
When Admin sends GET Request with v2 endpoint for LMS User Module
Then Admin gets the list of active users with filters
Examples:
| requestType  |
| GetAllUserWithFilters   |

Scenario Outline: Check if admin able to retrieve user by parameter LMS User Module  
Given Admin creates GET Request "<requestType>" and "<parameter>" for LMS User Module
When Admin sends GET Request with endpoint and "<parameter>" for LMS User Module
Then Admin gets the users details
Examples:
| requestType  |parameter|
| GetUserByRoleID   |roleId|
