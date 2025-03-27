@NoAuth @LMS1
Feature: LMS API No Auth Request

#----------------------------------- PROGRAM NO AUTH ---------------------------------


  @NoAuthProgram   
  Scenario Outline: Verify program module functionality with no Auth
    Given Admin creates "<Scenario>" Request in program for NoAuth
    When Admin sends "<Request>" HTTPS program Request
    Then Admin receives program 401 Status for NoAuth
    Examples: 
      | Scenario     		          |   Request     |
      | NoAuthCreateProgram 	    |   POST        |
      | NoAuthGetAllProgram       |   GetAll      |
      | NoAuthGetProgramById      |   GetById     |
      | NoAuthPUTProgramID        |    PUT        |
      | NoAuthDeleteProgrambyId   |   Delete      |
      



#----------------------------------- BATCH NO AUTH -----------------------------------

  @NoAuthBatch   
  Scenario Outline: Verify create batch functionality with no Auth
    Given Admin creates "<Scenario>" Request in batch for NoAuth
    When Admin sends "<Request>" HTTPS batch Request
    Then Admin receives batch 401 Status for NoAuth
    Examples: 
      | Scenario     		       |   Request     |
      | NoAuthAddBatch 	       |   POST        |
      | NoAuthGetAllBatch      |   GetAll      |
      | NoAuthGetBatchById     |   GetById     |
      | NoAuthGetBatchByName   |   GetByName   |
      | NoAuthGetBatchByPrgmID |   GetByPrgmId |
      | NoAuthEditBatch        |    PUT        |
      | NoAuthDeleteBatch      |   Delete      |
      
 #----------------------------------- USER NO AUTH ----------------------------------- 
  #@NoAuthUser   
  #Scenario Outline: Verify create User functionality with no Auth
    #Given Admin creates "<Scenario>" Request in User for NoAuth
    #When Admin sends "<Request>" HTTPS User Request
    #Then Admin receives User 401 Status for NoAuth
    #Examples: 
      #| Scenario     		       						|   Request                      |
      #| NoAuthAddUser 	       						|   APICreateUserWithRole        |
      #| NoAuthAPIGetAllUser    						|   APIGetAllUser                |
      #| NoAuthAPIGetAllUserRoles 					|   APIGetAllUserRoles           |
      #| NoAuthAPIGetUserByStatus        	|   APIGetUserByStatus           |
      #| NoAuthAPIGetActiveUser  					|   APIGetActiveUser             |
      #| NoAuthAPIGetAllRoles 							|   APIGetAllRoles               |
      #| NoAuthAPIGetAllUserEmail					|   APIGetAllUserEmail           |
      #| NoAuthAPIUpdateUserByID					  |   APIUpdateUserByID            |
      #| NoAuthAPIGetUserByID					    |   APIGetUserByID               |
      #
      
        #	| NoAuthAPIUpdateUser    |   PUT      |
     #	| NoAuthAPIDeleteUserByID|   Delete     |
     #  | APIGetUserWithFilter   |   GetByName   |
 
 
 #----------------------------------- CLASS NO AUTH -------------------------------------    
 #----------------------------------- LOGOUT NO AUTH ------------------------------------  
 #----------------------------------- LOGOUT NO AUTH -------------------------------------  
       
  @NoAuthLogout   
  Scenario: Verify create batch functionality with no Auth
    Given Admin creates "NoAuthLogout" Request for NoAuth
    When Admin sends GET HTTPS Logout Request
    Then Admin receives batch 401 Status for Logout NoAuth
 