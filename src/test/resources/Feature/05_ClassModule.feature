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
      | CreateClass |

  @GetAllClasses
  Scenario Outline: Check if admin able to retrieve all classes  with valid or invalid Endpoint
    Given Admin creates GET Request "<requestType>" in class module LMS
    When Admin sends HTTPS Get Request "<requestType>" with valid or invalid endpoint in class module
    Then Admin receives "<Statuscode>" with response body for class.

    Examples: 
      | requestType                  | Statuscode |
      | GetAllClasses                |        200 |
      | GetAllClassesInvalidEndpoint |        404 |

  @GetClassByBatchID
  Scenario Outline: Check if admin able to retrieve class with valid Batchid
    Given Admin creates GET Request "<requestType>" and "<parameter>" with BatchId in class module
    When Admin sends HTTPS Get Request with batchId "<parameter>"
    Then Admin receives with response body in get batchId

    Examples: 
      | requestType              | parameter |
      | GetClassByBatchId        | batchId   |
      | GetClassByInvalidBatchId | batchId   |

  @GetClassByClassID
  Scenario Outline: Check if admin able to retrieve Class details  with valid or invalid classId
    Given Admin creates GET Request "<requestType>" and "<parameter>" with ClassId in class module
    When Admin sends HTTPS Get Request with classId "<requestType>" and "<parameter>"
    Then Admin receives with response body in get classID

    Examples: 
      | requestType                  | parameter |
      | GetClassByClassId            | classId   |
      | GetClassBySpecialCharClassId | classId   |
      | GetClassByInvalidClassId     | classId   |

  @GetAllClassesByClassTopic
  Scenario Outline: Check if admin able to retrieve all classes  with valid/invalid classtopic
    Given Admin creates GET Request "<requestType>" and "<parameter>" with Classtopic in class module
    When Admin sends HTTPS GET Request with Classtopic "<requestType>" and "<parameter>"
    Then Admin receives with response body in get all classes by classtopic

    Examples: 
      | requestType              | parameter  |
      | GetValidClasstopic       | classTopic |
      | GetInvalidClasstopic     | classTopic |
      | GetSpecialCharClasstopic | classTopic |

  @GetClassRecordingByBatchID
  Scenario Outline: Check if admin able to retrieve class recording  with valid Batchid
    Given Admin creates GET Request "<requestType>" and "<parameter>" with classrecordinging BatchId in class module
    When Admin sends GET Request with classrecording by batchId "<requestType>" and "<parameter>"
    Then Admin receives with response body in get class recording by batchId

    Examples: 
      | requestType                     | parameter               |
      | GetClassrecordingValidBatchID   | classRecordingByBatchId |
      | GetClassrecordingInValidBatchID | classRecordingByBatchId |

  @GetClassRecordingByClassID
  Scenario Outline: Check if admin able to retrieve class recording  with valid classid
    Given Admin creates GET Request "<requestType>" and "<parameter>" with classrecordinging classId in class module
    When Admin sends GET Request with classrecording by classId "<requestType>" and "<parameter>"
    Then Admin receives with response body in get class recording by classId

    Examples: 
      | requestType                     | parameter               |
      | GetClassrecordingValidClassID   | classRecordingByClassId |
      | GetClassrecordingInValidClassID | classRecordingByClassId |

  @GetAllRecordingByClass
  Scenario Outline: Check if admin able to retrieve all classes  with valid or invalid Endpoint all recordings
    Given Admin creates GET Request "<requestType>" in all recording by class module LMS
    When Admin sends HTTPS Get Request "<requestType>" with valid or invalid endpoint for all recordings class module
    Then Admin receives "<Statuscode>" with response body for class module for all recording.

    Examples: 
      | requestType                         | Statuscode |
      | GetAllClassRecording                |        200 |
      | GetAllClassRecordingInvalidEndpoint |        404 |

  @GetAllClassesByStaffId
  Scenario Outline: Check if admin able to retrieve all classes with valid or invalid endpoint by staffId
    Given Admin creates GET request "<requestType>" and "<parameter>" in class module by staffId
    When Admin sends HTTPS Get request with class by staffId "<requestType>" and "<parameter>"
    Then Admin receives with response body in get class by staffId

    Examples: 
      | requestType               | parameter      |
      | GetAllClassesValidStaffId | classByStaffId |
      | GetClassInvalidStaffId    | classByStaffId |
      
      
      
