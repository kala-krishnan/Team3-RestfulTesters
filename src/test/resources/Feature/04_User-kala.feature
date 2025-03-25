#Feature: To test LMS User API Endpoints
#
#Background: User set Bearer token Authentication
#Given User Set Authentication Token for Authorisation
#
#
#
#
#Scenario Outline: Check if User able to get all users with endpoint and request body 
#Given User creates Get Request to get user Details with request body "<requestType>" for LMS User Module
#When User calls GET Https method to get all users with valid endpoint for LMS User Module
#Then User received proper status code with all User Details
#Examples:
#| requestType  |
#|  GetAllUsers  |
#
#
#
#
#
#Scenario Outline: Check if User able to get all active users with endpoint and request body 
#Given User creates Get Request to get user Details with request body "<requestType>" for LMS User Module
#When User calls GET Https method to get all active users with valid endpoint for LMS User Module
#Then User received proper status code with all active User Details
#Examples:
#| requestType  |
#| GetAllActiveUsers   |
#
#
#
#Scenario Outline: Check if User able to fetch email id of all users with endpoint and request body 
#Given User creates Get Request to get user Details with request body "<requestType>" for LMS User Module
#When User calls GET Https method to fetch email id of all users with valid endpoint for LMS User Module
#Then User received proper status code with all User Email Ids
#Examples:
#| requestType  |
#| GetActiveEmailUsers   |
#
#
#Scenario Outline: Check if User able to fetch all roles with endpoint and request body 
#Given User creates Get Request to get roles details with request body "<requestType>" for LMS User Module
#When User calls GET Https method to fetch all roles with valid endpoint for LMS User Module
#Then User received proper status code with all roles
#Examples:
#| requestType  |
#| GetAllRoles   |
#
#Scenario Outline: Check if User able to fetch all users with roles with endpoint and request body 
#Given User creates Get Request to get all users with roles details with request body "<requestType>" for LMS User Module
#When User calls GET Https method to fetch all users with roles with valid endpoint for LMS User Module
#Then User received proper status code with all users with roles
#Examples:
#| requestType  |
#| GetAllUserwithRoles   |
#
#Scenario Outline: Check if User able to fetch Users status count with endpoint and request body 
#Given User creates Get Request to get users with status count with request body "<requestType>" for LMS User Module
#When User calls GET Https method to fetch users with status count with valid endpoint for LMS User Module
#Then User received proper status code with user with status count
#Examples:
#| requestType  |
#| GetAllUserStatusCount   |
#
#
#Scenario Outline: Check if User able to fetch Users by UserId with endpoint and request body 
#Given User creates Get Request to fetch Users by UserId with request body "<requestType>" for LMS User Module
#When User calls GET Https method to fetch Users by UserId with valid endpoint for LMS User Module
#Then User received proper status code with Users by UserId
#Examples:
#| requestType  |
#| GetUserbyUserId   |
#
#Scenario Outline: Check if User able to update User by UserId with endpoint and request body 
#Given User creates PUT Request to update User by UserId with request body "<requestType>" for LMS User Module
#When User calls PUT Https method to update User by UserId with valid endpoint for LMS User Module
#Then User received proper status code with updated user details as response
#Examples:
#| requestType  |
#| PutUserbyUserId   |
#
#Scenario Outline: Check if User able to update role Id by user Id with endpoint and request body 
#Given User creates PUT Request to update role Id by user Id with request body "<requestType>" for LMS User Module
#When User calls PUT Https method to update role Id by user Id with valid endpoint for LMS User Module
#Then User received proper status code with updated role details for the user as response
#Examples:
#| requestType  |
#| PutUserRolebyUserId   |
