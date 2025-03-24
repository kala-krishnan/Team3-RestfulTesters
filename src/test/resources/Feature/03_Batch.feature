@Batch @LMS
Feature: LMS Batch API Request
    
Background: Authorization SetUp
	Given Admin sets Authorization to Bearer Token

  #@AddBatch   
  #Scenario Outline: Verify create batch functionality
    #Given Admin creates Request with "<Scenario>" in batch request body 
    #When Admin sends POST HTTPS Request "<Scenario>" batch with "<Endpoint>"
    #Then Admin receives "<Status>" for batch request
    #Examples: 
      #| Scenario     				      | Endpoint  | Status  |
      #| AddBatchValid 				    |  Valid  	|   201   |
      #| AddBatchExistingName	    |  Valid 	  |   400   |
#			| AddBatchEmptyMandatory	  |  Valid 	  |   400   |
#			| AddBatchInvalidEp    	    | Invalid   |   404   |
#			| AddBatchEmptyNonMandatory |  Valid		|   201   |
#			| AddBatchNameStrtWithNo    |  Valid		|   400   |
#			| AddBatchStatusInactive    |  Valid		|   400   |			
#				
#						
#	@GetAllBatch   
  #Scenario Outline: Verify search all batch functionality
    #Given Admin creates GET Request for batch "<Scenario>"
    #When Admin sends GET HTTPS Request with batch "<Scenario>"
    #Then Admin receives "<Status>" with batch GetAll response body
    #Examples: 
      #| Scenario     			   | Status  |
      #| GetAllBatchValid 	   |  200    |
      #| GetAllBatchInValidEP |  404    |
    #
    #
	@GetBatchByID   
  Scenario Outline: Verify search by Batch ID functionality
    Given Admin creates GET Request with Batch ID "<Scenario>"
    When Admin sends GET HTTPS Request with batch id "<Scenario>"
    Then Admin receives "<Status>" with batch Get by ID response body
    Examples: 
      | Scenario     	       | Status  |
      | GetByBatchIDValid    |  200    |
      | GetByBatchIDInactive |  200    |
      | GetByBatchIDInvalid  |  404    |
      | GetByBatchIDInValidEP|  404    |      
      
        #
#	@GetBatchByName   
  #Scenario Outline: Verify search by Batch Name functionality
    #Given Admin creates GET Request with Batch Name "<Scenario>"
    #When Admin sends GET HTTPS Request with batch Name "<Scenario>"
    #Then Admin receives "<Status>" with batch Get by Name response body
    #Examples: 
      #| Scenario 			          | Status  |
      #| GetByBatchNameValid     |  200    |
      #| GetByBatchNameInactive  |  200    |
      #| GetByBatchNameInvalid   |  404    |
      #| GetByBatchNameInValidEP |  404    |   
      
  #@GetBatchByPrgmID   
  #Scenario Outline: Verify search by Program ID functionality
    #Given Admin creates GET Request with Program ID
    #When Admin sends GET HTTPS Request with Program id "<Endpoint>"
    #Then Admin receives "<Status>" with batch Get by Program ID response body
    #Examples: 
      #| Data     			         | Endpoint | Status  |
      #| GetBatchByPrgmIDValid  |  Valid 	|  200    |
      #| GetByProgramInactive   |  Valid 	|  404    |
      #| GetByProgramInvalid    |  Valid 	|  404    |
      #| GetByProgramIDInValidEP| InValid 	|  404    |         
      #
  #@UpdateBatch   
  #Scenario Outline: Verify create batch functionality
    #Given Admin creates PUT Request with "<Data>" in batch request body 
    #When Admin sends PUT HTTPS Request update batch with "<Endpoint>"
    #Then Admin receives "<Status>" for Update batch request
    #Examples: 
      #| Data     				       | Endpoint | Status  |
      #| PutBatchValid 		     |  Valid 	|   201   |
      #| PutInvalidBatchID      |  Valid   |   404   |
      #| PutBatchEmptyMandatory |  Valid 	|   400   |
      #| PutBatchNameStrtWithNo |  Valid		|   400   |
      #| PutBatchInvalidEp      | InValid  |   404   |
      #| PutBatchInactivePrgmID | InValid  |   400   |
      #| PutBatchwithInactiveID |  Valid		|   200   | 
      