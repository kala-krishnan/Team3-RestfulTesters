@ProgramModule
Feature: LMS API Program Module

  Background: 
    Given Admin sets Authorization to Bearer Token Program

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
      | CreateProgramStatusInactive      | Valid    |    201 |

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

  @GetAllProgramsWithUsers
  Scenario Outline: Admin creates GETallProgramswithUser for Program Module  LMS
    Given Admin creates GETAllProgramswithUsers request "<requestType>" for Program Module
    When Admin sends HTTPS Request with endpoint GETAllProgramswithUsers for Program Module "<requestType>"
    Then Admin receives statuscode  "<Status>" for  GETAllProgramswithUsers in Program Module

    Examples: 
      | requestType                             | Status |
      | GETAllProgramswithUsersValidProgram     |    200 |
      | GETAllProgramswithUsersProgramInValidEP |    404 |

  @UpdateProgrammodulebyProgramId
  Scenario Outline: Verify Update Program functionality in Program Module
    Given Admin creates PUT Request with "<requestType>" in Program Module with request body
    When Admin sends PUT HTTPS Request update Program Module with "<requestType>"
    Then Admin receives "<Status>" for Update Program Module request

    Examples: 
      | requestType                       | Status |
      | PutProgramValidProgramID          |    201 |
      | PutInvalidProgramID               |    404 |
      | PutProgramIDEmptyMandatory        |    400 |
      | PutProgramidprpgramNameStrtWithNo |    400 |
      | PutProgramIDInvalidEp             |    404 |
      | PutProgramInactivePrgmID          |    400 |

  @UpdateProgrammodulebyProgramName
  Scenario Outline: Verify Update Program functionality in Program Module with ProgramName
    Given Admin creates PUT Request with "<requestType>" in Program Module by program name with request body
    When Admin sends PUT HTTPS Request update Program Module with "<requestType>" by program name
    Then Admin receives "<Status>" for Update Program Module request by program name

    Examples: 
      | requestType                         | Status |
      | PutProgramValidProgramname          |    200 |
      | PutInvalidProgramname               |    404 |
      | PutProgramnameEmptyMandatory        |    400 |
      | PutProgramnameprpgramNameStrtWithNo |    400 |
      | PutProgramnameInvalidEp             |    404 |
      | PutProgramInactivePrgmname          |    404 |
