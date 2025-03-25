@NoAuth @LMS
Feature: LMS API No Auth Request

#----------------------------------- PROGRAM NO AUTH ---------------------------------

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
      
 #----------------------------------- CLASS NO AUTH ----------------------------------- 
 
 #----------------------------------- USER NO AUTH -------------------------------------    
      
 