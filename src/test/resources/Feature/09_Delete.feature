@Delete @LMS
Feature: LMS Delete API Request
    
Background: Authorization SetUp
	Given Admin sets Authorization to Bearer Token
	
	#----------------------------------- DELETE CLASS ---------------------------------
	
	#----------------------------------- DELETE USER ----------------------------------
	
	#----------------------------------- DELETE BATCH ---------------------------------

  @DeleteBatch   
  Scenario Outline: Verify Delete batch functionality
    Given Admin creates Delete Request for batch "<Scenario>"
    When Admin sends DELETE HTTPS Request batch "<Scenario>"
    Then Admin receives batch Delete "<Status>"
    Examples: 
      | Scenario     			        | Status  |
      | DeleteBatchValid 	        |  200    |
      | DeleteBatchInvalidEP      |  404    |
      | DeleteBatchInvalidID      |  404    |
      | DeleteAlreadyRemovedBatch |  404	  |
      			
	#----------------------------------- DELETE PROGRAM ---------------------------------