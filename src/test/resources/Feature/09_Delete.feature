@Delete @LMSONE
Feature: LMS Delete API Request
    
Background: Authorization SetUp
	Given Admin sets Authorization to Bearer Token
	
#	#----------------------------------- DELETE CLASS ---------------------------------

@DeleteClass
Scenario Outline: Verify Delete class module functionality
Given Admin creates Delete request for class "<requestType>" and "<parameter>"
When Admin sends delete HTTPS request class "<requestType>" and "<parameter>"
Then Admin receives class delete 
Examples:
| requestType                     | parameter  |
      | DeleteClassValid 	        | DeleteClass|
      #| DeleteClassInvalidEP      | DeleteClass|
      #| DeleteClassInvalidID      | DeleteClass|
      | DeleteAlreadyRemovedClass | DeleteClass|

#	#----------------------------------- DELETE USER ----------------------------------
#	
#	#----------------------------------- DELETE BATCH ---------------------------------
#
  #@DeleteBatch   
  #Scenario Outline: Verify Delete batch functionality
    #Given Admin creates Delete Request for batch "<Scenario>"
    #When Admin sends DELETE HTTPS Request batch "<Scenario>"
    #Then Admin receives batch Delete "<Status>"
    #Examples: 
      #| Scenario     			        | Status  |
      #| DeleteBatchValid 	        |  200    |
      #| DeleteBatchInvalidEP      |  404    |
      #| DeleteBatchInvalidID      |  404    |
      #| DeleteAlreadyRemovedBatch |  404	  |
      			
	#----------------------------------- DELETE PROGRAM ---------------------------------
	
#	  @DeleteprogrambyId
  #Scenario Outline: Verify Delete program functionality
    #Given Admin creates Delete by ProgramId Request  "<requestType>" for Program module
    #When Admin sends DELETE HTTPS Request  "<requestType>" for Program module
    #Then Admin receives Program Delete "<Status>"
#
    #Examples: 
      #| requestType     		       |   Status  |
      #| DeleteProgramIdValid       | 200       |
      #| DeleteProgramIdInvalidEP   | 404       |
      #| DeleteProgramIdInvalidID   | 404       |