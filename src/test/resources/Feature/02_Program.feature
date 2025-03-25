@ProgramModule
Feature: LMS API Program Module

  Background: 
    Given Admin sets Authorization to Bearer Token

  Scenario Outline: Check if admin able to create a new program with valid endpoint and request body
    Given Admin creates POST Request with request body "<requestType>" for LMS Program Module
    When Admin calls Post Https request  "<requestType>" for Program Module with "<Endpoint>"
    Then Admin receive created  status  "<Status>" for Program Module

    Examples: 
      | requestType                      | Endpoint | Status |
      | CreateProgramValid               | Valid    |    201 |
      | CreateInvalidExistingprogramname | InValid  |    400 |
      | CreateProgramInvalidEp           | InValid  |    404 |
      | CreateProgramEmptyNonMandatory   | Valid    |    201 |
      | CreateProgramStatusInactive      | Valid    |    400 |

  @GetAllProgram
  Scenario Outline: Admin creates GET all Request for LMS Program Module
    Given Admin creates GETAll request "<requestType>" for Program Module
    When Admin sends HTTPS Request with endpoint GETAll for Program Module "<requestType>"
    Then Admin receives statuscode  "<Status>" for Program Module get all

    Examples: 
      | requestType            | Status |
      | GetAllValidProgram     |    200 |
      | GetAllProgramInValidEP |    404 |

  @GetProgrambyProgramID
  Scenario Outline: Check if admin able to retrieve Program by ProgramID
    Given Admin creates GET Request by "<requestType>" for LMS Program module
    When Admin sends GET Request by  "<requestType>" ProgramId for LMS Program Module
    Then Admin gets the program details of that programid with status  "<Status>"

    Examples: 
      | requestType                  | Status |
      | GetProgramByProgramID        |    200 |
      | GetProgramByInvalidProgramID |    400 |
      | GetByProgramIDInactive       |    200 |
      | GetByProgramIDInValidEP      |    404 |
