@Batch @LMS
Feature: LMS Batch API Request
    
Background: Authorization SetUp
Given Admin sets Authorization to Bearer Token

  @AddBatch   
  Scenario Outline: Verify create batch functionality
    Given Admin creates Request with "<Data>" in batch request body 
    When Admin sends POST HTTPS Request batch with "<Endpoint>"
    Then Admin receives "<Status>" for batch request
    Examples: 
      | Data     				       | Endpoint | Status  |
      | BatchValid 				     |  Valid 	|   201   |
      | BatchNameExisting	     |  Valid 	|   400   |
			| BatchEmptyMandatory	   |  Valid 	|   400   |
			| BatchInvalidEp    	   | InValid  |   400   |
			| BatchEmptyNonMandatory |  Valid		|   201   |
			| BatchNameStrtWithNo    |  Valid		|   400   |
			| BatchStatusInactive    |  Valid		|   400   |			
			
	@GetAllBatch   
  Scenario Outline: Verify search all batch functionality
    Given Admin creates GET Request for batch
    When Admin sends GET HTTPS Request with batch "<Endpoint>"
    Then Admin receives "<Status>" with batch GetAll response body
    Examples: 
      | Data     			       | Endpoint | Status  |
      | GetAllBatchValid 	   |  Valid 	|  200    |
      | GetAllBatchInValidEP | InValid 	|  404    |
    
    
	@GetBatchByID   
  Scenario Outline: Verify search by Batch ID functionality
    Given Admin creates GET Request with Batch ID
    When Admin sends GET HTTPS Request with batch id "<Endpoint>"
    Then Admin receives "<Status>" with batch Get by ID response body
    Examples: 
      | Data     			       | Endpoint | Status  |
      | GetByBatchIDValid    |  Valid 	|  200    |
      | GetByBatchIDInactive |  Valid 	|  200    |
      | GetByBatchIDInvalid  |  Valid 	|  404    |
      | GetByBatchIDInValidEP| InValid 	|  404    |      
      
        
	@GetBatchByName   
  Scenario Outline: Verify search by Batch Name functionality
    Given Admin creates GET Request with Batch Name
    When Admin sends GET HTTPS Request with batch Name "<Endpoint>"
    Then Admin receives "<Status>" with batch Get by Name response body
    Examples: 
      | Data     			          | Endpoint  | Status  |
      | GetByBatchNameValid     |  Valid  	|  200    |
      | GetByBatchNameInactive  |  Valid  	|  200    |
      | GetByBatchNameInvalid   |  Valid 	  |  404    |
      | GetByBatchNameInValidEP | InValid 	|  404    |   
      
  @GetBatchByPrgmID   
  Scenario Outline: Verify search by Program ID functionality
    Given Admin creates GET Request with Program ID
    When Admin sends GET HTTPS Request with Program id "<Endpoint>"
    Then Admin receives "<Status>" with batch Get by Program ID response body
    Examples: 
      | Data     			         | Endpoint | Status  |
      | GetByProgramIDValid    |  Valid 	|  200    |
      | GetByProgramInactive   |  Valid 	|  404    |
      | GetByProgramInvalid    |  Valid 	|  404    |
      | GetByProgramIDInValidEP| InValid 	|  404    |         
      
  @UpdateBatch   
  Scenario Outline: Verify create batch functionality
    Given Admin creates PUT Request with "<Data>" in batch request body 
    When Admin sends PUT HTTPS Request update batch with "<Endpoint>"
    Then Admin receives "<Status>" for Update batch request
    Examples: 
      | Data     				       | Endpoint | Status  |
      | PutBatchValid 		     |  Valid 	|   200   |
      | PutInvalidBatchID      |  Valid   |   404   |
      | PutBatchEmptyMandatory |  Valid 	|   400   |
      | PutBatchNameStrtWithNo |  Valid		|   400   |
      | PutBatchInvalidEp      | InValid  |   404   |
      | PutBatchInactivePrgmID | InValid  |   400   |
      | PutBatchwithInactiveID |  Valid		|   200   | 
      