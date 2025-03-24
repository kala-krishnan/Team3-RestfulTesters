@ProgramModule
Feature: LMS API Program Module

Background:
Given Set Auth to bearer token

#Scenario Outline: Check if admin able to create a new program with valid endpoint and request body 
#Given Admin creates POST Request with request body "<requestType>" for LMS Program Module
#When Admin calls Post Https method  with valid endpoint for Program Module
#Then Admin receive created  status for LMS User Module for Program Module
#Examples:
#| requestType  |
#| CreateProgramValid   |
#| InvalidExistingprogramname |

#Scenario: Check if Admin able to create a program with invalid method
  #Given Admin creates POST Request for the LMS Program Module with request body
  #When Admin sends HTTPS Request for Program Module with request Body and endpoint using invalid method status "InvalidMethodGET"
  #Then Admin receives 405 Method Not Allowed for Program Module
@GetAllProgram
Scenario Outline: Admin creates GET all Request for LMS Program Module
Given Admin creates GETAll request "<requestType>" for Program Module
When Admin sends HTTPS Request with endpoint GETAll for Program Module
Then Admin receives statuscode for Program Module get all
Examples:
| requestType |
| GetAllValidProgram       | 

#Scenario Outline: Check if admin able to retrieve Program by ProgramID  
#Given Admin creates GET Request "<requestType>" and "<parameter>" for LMS Program module
#When Admin sends GET Request with endpoint and "<parameter>" for LMS User Module
#Then Admin gets the program details of that programid
#Examples:
#| requestType  |parameter|
#| GetProgramByProgramID   |programID|