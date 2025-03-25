@userModule @LMS1
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
#| CreateUserInValidPhNumber   | 
#
#Scenario Outline: Check if admin able to retrieve all user with facets and filters LMS User Module  
#Given Admin creates GET Request "<requestType>" for LMS User Module
#When Admin sends GET Request with v2 endpoint for LMS User Module
#Then Admin gets the list of active users with filters
#Examples:
#| requestType  |
#| GetAllUserWithFilters   |

#Scenario Outline: Check if admin able to retrieve user by parameter LMS User Module  
#Given Admin creates GET Request "<requestType>" and "<parameter>" for LMS User Module
#When Admin sends GET Request with endpoint and "<requestType>" for LMS User Module
#Then Admin gets the users details
#Examples:
#| requestType  |parameter|
#| GetUserByRoleID   |roleId|
#| GetUserByID   |id|   
#  March 25 12:00 am added GetUserByID line

#
#####Kala Feature - March 24 7.15PM
#
#Scenario Outline: Check if User able to get all users with endpoint and request body 
#Given User creates Get Request to get user Details with request body "<requestType>" for LMS User Module
#When User calls GET Https method for the requesttype "<requestType>" to get all users with endpoint for LMS User Module
#Then User received proper status code "<StatusCode>" with all User Details
#Examples:
#| requestType  |StatusCode|
#|  GetAllUsers  |200|
#|  GetAllUserInvalidEndpoint|404|
#
#
#Scenario Outline: Check if User able to get all active users with endpoint and request body 
#Given User creates Get Request to get user Details with request body "<requestType>" for LMS User Module
#When User calls GET Https method for the requesttype "<requestType>" to get all active users with endpoint for LMS User Module
#Then User received proper status code "<StatusCode>" with all active User Details
#Examples:
#| requestType  |StatusCode|
#| GetAllActiveUsers   |200|
#|GetAllActiveUsersInvalidEndpoint|404|
#
#
#
#Scenario Outline: Check if User able to fetch email id of all users with endpoint and request body 
#Given User creates Get Request to get user Details with request body "<requestType>" for LMS User Module
#When User calls GET Https method for the requesttype "<requestType>" to fetch email id of all users with endpoint for LMS User Module
#Then User received proper status code "<StatusCode>" with all User Email Ids
#Examples:
#| requestType  |StatusCode|
#| GetActiveEmailUsers   |200|
#|GetActiveEmailUsersInvalidEndpoint|404|
#
#
#Scenario Outline: Check if User able to fetch all roles with endpoint and request body 
#Given User creates Get Request to get roles details with request body "<requestType>" for LMS User Module
#When User calls GET Https method for the requesttype "<requestType>" to fetch all roles with endpoint for LMS User Module
#Then User received proper status code "<StatusCode>" with all roles
#Examples:
#| requestType  |StatusCode|
#| GetAllRoles   |200|
#|GetAllRolesInvalidEndpoint|404|
#
#Scenario Outline: Check if User able to fetch all users with roles with endpoint and request body 
#Given User creates Get Request to get all users with roles details with request body "<requestType>" for LMS User Module
#When User calls GET Https method for the requesttype "<requestType>" to fetch all users with roles with endpoint for LMS User Module
#Then User received proper status code "<StatusCode>" with all users with roles
#Examples:
#| requestType  |StatusCode|
#| GetAllUserwithRoles   |200|
#|GetAllUserwithRolesInvalidEndpoint|404|
#
#Scenario Outline: Check if User able to fetch Users status count with endpoint and request body 
#Given User creates Get Request to get users with status count with request body "<requestType>" for LMS User Module
#When User calls GET Https method for the requesttype "<requestType>" to fetch users with status count with endpoint for LMS User Module
#Then User received proper status code "<StatusCode>" with user with status count
#Examples:
#| requestType  |StatusCode|
#| GetAllUserStatusCount   |200|
#| GetAllUserStatusCountInvalidEndpoint   |404|
#
#
#Scenario Outline: Check if User able to fetch Users by UserId with endpoint and request body 
#Given User creates Get Request to fetch Users by UserId with request body "<requestType>" for LMS User Module
#When User calls GET Https method for the requesttype "<requestType>" to fetch Users by UserId with endpoint for LMS User Module
#Then User received proper status code "<StatusCode>" with Users by UserId
#Examples:
#| requestType  |StatusCode|
#| GetUserbyUserId   |200|
#
#Scenario Outline: Check if User able to update User by UserId with endpoint and request body 
#Given User creates PUT Request to update User by UserId with request body "<requestType>" for LMS User Module
#When User calls PUT Https method for the requesttype "<requestType>" to update User by UserId with endpoint for LMS User Module
#Then User received proper status code "<StatusCode>" with updated user details as response
#Examples:
#| requestType  |StatusCode|
#| PutUserbyUserId   |200|
#
#Scenario Outline: Check if User able to update role Id by user Id with endpoint and request body 
#Given User creates PUT Request to update role Id by user Id with request body "<requestType>" for LMS User Module
#When User calls PUT Https method for the requesttype "<requestType>" to update role Id by user Id with endpoint for LMS User Module
#Then User received proper status code "<StatusCode>" with updated role details for the user as response
#Examples:
#| requestType  |StatusCode|
#| PutUserRolebyUserId   |200|
#
