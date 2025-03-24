@NoAuth @LMS
Feature: LMS API No Auth Request

----------------------------------- BATCH NO AUTH ---------------------------------
  @NoAuthAddBatch
  Scenario: Verify create batch functionality with no Auth
    Given Admin creates "NoAuthAddBatch" Request in batch for NoAuth
    When Admin sends POST HTTPS batch Request with valid endpoint
    Then Admin receives 401 Unauthorized for NoAuth
    
  @NoAuthGetAllBatch
  Scenario: Verify search all batch functionality with no Auth
    Given Admin creates "NoAuthGetAllBatch" Request in batch for NoAuth
    When Admin sends GET HTTPS Request with batch valid
    Then Admin receives 401 Unauthorized for NoAuth  
    
  @NoAuthGetBatchByID
  Scenario: Verify search by Batch ID functionality with no Auth
    Given Admin creates "NoAuthGetBatchById" Request in batch for NoAuth
    When Admin sends GET HTTPS Request with batch Id valid
    Then Admin receives 401 Unauthorized for NoAuth      
    
  @NoAuthGetBatchByName
  Scenario: Verify search by Batch Name functionality with no Auth
    Given Admin creates "NoAuthGetBatchByName" Request in batch for NoAuth
    When Admin sends GET HTTPS Request with batch Name valid
    Then Admin receives 401 Unauthorized for NoAuth
 
  @NoAuthGetBatchByPrgmID
  Scenario: Verify search Batch by Program ID functionality with no Auth
    Given Admin creates "NoAuthGetBatchByPrgmId" Request in batch for NoAuth
    When Admin sends GET HTTPS Request with batch PrgmId valid
    Then Admin receives 401 Unauthorized for NoAuth  
    
   @NoAuthEditBatch
   Scenario: Verify Update batch functionality with no Auth
    Given Admin creates "NoAuthEditBatch" Request in batch for NoAuth
    When Admin sends PUT HTTPS batch Request with valid endpoint
    Then Admin receives 401 Unauthorized for NoAuth
    
   @NoAuthDeleteBatch
   Scenario: Verify Delete batch functionality with no Auth
    Given Admin creates "NoAuthDeleteBatch" Request in batch for NoAuth
    When Admin sends Delete HTTPS batch Request with valid endpoint
    Then Admin receives 401 Unauthorized for NoAuth
