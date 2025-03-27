@userModule @LMS1
Feature: LMS API User Module

Background:
Given Set Auth to bearer token

@createuser
Scenario Outline: Check if admin able to create a new user with valid endpoint and request body 
Given Admin creates POST Request with request body "<requestType>" for LMS User Module
When Admin calls Post Https method  with valid endpoint for LMS User Module
Then Admin receive created  status for LMS User Module
Examples:
| requestType  |
| CreateUserValid   |


@getalluserdetails
Scenario Outline: Check if admin able to retrieve all user with facets and filters LMS User Module  
Given Admin creates GET Request "<requestType>" "<status>"  for LMS User Module
When Admin sends GET Request "<requestType>"  with v2 endpoint for LMS User Module "<status>"
Then Admin gets the list of active users with filters "<status>"
Examples:
| requestType  | status|
| GetUserWithFilter   | 200|


@getuserbyroleidUuserid
Scenario Outline: Check if admin able to retrieve user by parameter LMS User Module
Given Admin creates GET Request "<requestType>" and "<parameter>" for LMS User Module
When Admin sends GET Request with endpoint and "<requestType>" "<parameter>"for LMS User Module
Then Admin gets the users details
Examples:
| requestType  |parameter|
| GetUserByRoleID   |roleId|
| GetUserByID   |userId|  
|GetUserBatchID|userId|


@getuserprmbatch
Scenario Outline: Check if admin able to retrieve user by parameter LMS User Module for prm batch
Given Admin creates GET Request "<requestType>" and "<parameter>" for LMS User Module for prm batch
When Admin sends GET Request with endpoint and "<requestType>"  and "<parameter>" for LMS User Module for prm batch
Then Admin gets the users detailsfor prm batch
Examples:
| requestType  |parameter|
| GetByProgramID   |programId|  
| GetByBatchID   |batchId|  


@getalluser
Scenario Outline: Check if User able to get all users with endpoint and request body 
Given User creates Get Request to get user Details with request body "<requestType>" for LMS User Module
When User calls GET Https method for the requesttype "<requestType>" to get all users with endpoint for LMS User Module
Then User received proper status code "<StatusCode>" with all User Details
Examples:
| requestType  |StatusCode|
|  GetAllUsers  |200|
|  GetAllUserInvalidEndpoint|404|

@getallactiveuser
Scenario Outline: Check if User able to get all active users with endpoint and request body 
Given User creates Get Request to get user Details with request body "<requestType>" for LMS User Module
When User calls GET Https method for the requesttype "<requestType>" to get all active users with endpoint for LMS User Module
Then User received proper status code "<StatusCode>" with all active User Details
Examples:
| requestType  |StatusCode|
| GetAllActiveUsers   |200|
|GetAllActiveUsersInvalidEndpoint|404|


@getallactiveuseremail
Scenario Outline: Check if User able to fetch email id of all users with endpoint and request body 
Given User creates Get Request to get user Details with request body "<requestType>" for LMS User Module
When User calls GET Https method for the requesttype "<requestType>" to fetch email id of all users with endpoint for LMS User Module
Then User received proper status code "<StatusCode>" with all User Email Ids
Examples:
| requestType  |StatusCode|
| GetActiveEmailUsers   |200|
|GetActiveEmailUsersInvalidEndpoint|404|

@getalluserbyrole
Scenario Outline: Check if User able to fetch all roles with endpoint and request body 
Given User creates Get Request to get roles details with request body "<requestType>" for LMS User Module
When User calls GET Https method for the requesttype "<requestType>" to fetch all roles with endpoint for LMS User Module
Then User received proper status code "<StatusCode>" with all roles
Examples:
| requestType  |StatusCode|
| GetAllRoles   |200|
|GetAllRolesInvalidEndpoint|404|

@getallusersrole
Scenario Outline: Check if User able to fetch all users with roles with endpoint and request body 
Given User creates Get Request to get all users with roles details with request body "<requestType>" for LMS User Module
When User calls GET Https method for the requesttype "<requestType>" to fetch all users with roles with endpoint for LMS User Module
Then User received proper status code "<StatusCode>" with all users with roles
Examples:
| requestType  |StatusCode|
| GetAllUserwithRoles   |200|
|GetAllUserwithRolesInvalidEndpoint|404|

@getallstatuscount
Scenario Outline: Check if User able to fetch Users status count with endpoint and request body 
Given User creates Get Request to get users with status count with request body "<requestType>" for LMS User Module
When User calls GET Https method for the requesttype "<requestType>" to fetch users with status count with endpoint for LMS User Module
Then User received proper status code "<StatusCode>" with user with status count
Examples:
| requestType  |StatusCode|
| GetAllUserStatusCount   |200|
| GetAllUserStatusCountInvalidEndpoint   |404|



@getuserbyuserid
Scenario Outline: Check if User able to fetch Users by UserId with endpoint and request body 
Given User creates Get Request to fetch Users by UserId with request body "<requestType>" for LMS User Module
When User calls GET Https method for the requesttype "<requestType>" to fetch Users by UserId with endpoint for LMS User Module
Then User received proper status code "<StatusCode>" with Users by UserId
Examples:
| requestType  |StatusCode|
| GetUserbyUserId   |200|
|GetUserByUserIDInvalidEndpoint|404|
|GetUserByInvalidUserId|404|
|GetUserByInvalidSpecialCharacterUserId|404|


@putuserbyrole
Scenario Outline: Check if User able to update role Id by user Id with endpoint and request body 
Given User creates PUT Request to update role Id by user Id with request body "<requestType>" for LMS User Module
When User calls PUT Https method for the requesttype "<requestType>" to update role Id by user Id with endpoint for LMS User Module
Then User received proper status code "<StatusCode>" with updated role details for the user as response
Examples:
| requestType  |StatusCode|
| PutUserRolebyUserId   |200|
|PutUserRolebyUserIdInvalidRoleID|400|
|PutUserRolebyUserIdSpecialRoleID|400|
|PutUserRolebyUserIdwithoutRoleID|400|
|PutUserRolebyUserIdwithoutRolestatus|400|
|PutUserRolebyUserIdSpecialRolestatus|400|



@updateprogrambatchuser
Scenario Outline: Update User Role Program Batch Status
Given User creates PUT Request User Role Program Batch Status "<requestType>" for LMS User Module
When User calls PUT Https method for the requesttype "<requestType>" to Update User Role Program Batch Status LMS User Module
Then User received proper status code "<StatusCode>" with updated Update User Role Program Batch Status
Examples:
| requestType  |StatusCode|
| UpdateUserByRPBStatus   |200|
