package com.stepdefinitions;

import java.io.FileNotFoundException;

import org.testng.asserts.SoftAssert;

import com.APIRequest.BatchRequest;
import com.APIRequest.LoginRequest;
import com.APIResponse.BatchModuleDataValidation;
import com.APIResponse.CommonResponseValidation;
import com.context.ScenarioContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class BatchStepDefinition {

	LoginRequest loginRequest;
	BatchRequest batch;
	private Response batchResponse;
	private SoftAssert softAssert = new SoftAssert();
	BatchModuleDataValidation BatchValidation = new BatchModuleDataValidation();
	CommonResponseValidation Validation = new CommonResponseValidation();
	private ScenarioContext context =ScenarioContext.getInstance();
	
	public BatchStepDefinition() throws FileNotFoundException {
		batch = new BatchRequest();
		loginRequest = new LoginRequest();	
	}
	
@Given("Admin sets Authorization to Bearer Token")
public void admin_sets_authorization_to_bearer_token() throws Exception {
	if(context.get("LMStoken")==(null))
		loginRequest.PostLoginRequest();
}

//********************************** CREATE BATCH ******************************************

@Given("Admin creates Request with {string} in batch request body")
public void admin_creates_request_with_in_batch_request_body(String requestType) throws Exception {
	batch.setNewBatchRequest(requestType);
	
}

@When("Admin sends POST HTTPS Request {string} batch with {string}")
public void admin_sends_post_https_request_batch_with(String Scenario, String Endpoint) {
	batch.PostNewBatchRequest(Scenario);
}

@Then("Admin receives {string} for batch request")
public void admin_receives_for_batch_request(String Code) {
	batchResponse = context.get("batchResponse", Response.class);

	Validation.validateStatusCode(batchResponse, batch.getBatchStatusCode());
	Validation.validateStatusLine(batchResponse, batch.getBatchStatusLine());
	Validation.validateResponseTime(batchResponse);
	
	if(!Code.equals("404"))
		Validation.validateContentType(batchResponse);
	
	if(Code.equals("201")) {
		BatchValidation.DataValidation(batchResponse);
		Validation.validateJsonSchema(batchResponse, "Schemas/Batch_PostPutValid_Schema.json" );	
	}
	else if(Code.equals("400"))
		Validation.validateJsonSchema(batchResponse, "Schemas/Batch_PostPutInvalid_Schema.json" );	
	
	Validation.assertAll();
}

//********************************** GET All BATCH ******************************************

@Given("Admin creates GET Request for batch {string}")
public void admin_creates_get_request_for_batch(String requestType) throws Exception {
	batch.setNewBatchRequest(requestType);
}

@When("Admin sends GET HTTPS Request with batch {string}")
public void admin_sends_get_https_request_with_batch(String Scenario) {
	if (Scenario.equals("GetAllBatchSearchtxt"))
		batch.GetAllBatchSearchString(Scenario);
	else
	batch.GetAllBatchRequest(Scenario);
	
}

@Then("Admin receives {string} with {string} GetAll response body")
public void admin_receives_with_get_all_response_body(String Code, String Scenario) {
	batchResponse = context.get("batchResponse", Response.class);
	
	Validation.validateStatusCode(batchResponse, batch.getBatchStatusCode());
	Validation.validateStatusLine(batchResponse, batch.getBatchStatusLine());
	Validation.validateResponseTime(batchResponse);	
	if(!Code.equals("404"))
		Validation.validateContentType(batchResponse);
	if(Scenario.equals("GetAllBatchSearchtxt"))
		BatchValidation.GetAllDataValidation(batchResponse);
		
	Validation.assertAll();
}

//********************************** GET BATCH BY ID ******************************************

@Given("Admin creates GET Request with Batch ID {string}")
public void admin_creates_get_request_with_batch_id(String requestType) throws Exception {
	batch.setNewBatchRequest(requestType);
}

@When("Admin sends GET HTTPS Request with batch id {string}")
public void admin_sends_get_https_request_with_batch_id(String Scenario) {
	batch.GetBatchByIDRequest(Scenario);
}

@Then("Admin receives {string} with batch Get by ID response body")
public void admin_receives_with_batch_get_by_id_response_body(String Code) {
	batchResponse = context.get("batchResponse", Response.class);
	
	Validation.validateStatusCode(batchResponse, batch.getBatchStatusCode());
	Validation.validateStatusLine(batchResponse, batch.getBatchStatusLine());
	Validation.validateResponseTime(batchResponse);	
	if(Code.equals("200"))
		Validation.validateJsonSchema(batchResponse, "Schemas/Batch_GetByID_Schema.json" );
	if(!Code.equals("404"))
		Validation.validateContentType(batchResponse);
	Validation.assertAll();
}

//********************************** GET BATCH BY NAME ******************************************

@Given("Admin creates GET Request with Batch Name {string}")
public void admin_creates_get_request_with_batch_name(String requestType) throws Exception {
	batch.setNewBatchRequest(requestType);
}

@When("Admin sends GET HTTPS Request with batch Name {string}")
public void admin_sends_get_https_request_with_batch_name(String Scenario) {
	batch.GetBatchByNameRequest(Scenario);
}

@Then("Admin receives {string} with batch Get by Name response body")
public void admin_receives_with_batch_get_by_name_response_body(String Code) {
batchResponse = context.get("batchResponse", Response.class);
	
	Validation.validateStatusCode(batchResponse, batch.getBatchStatusCode());
	Validation.validateStatusLine(batchResponse, batch.getBatchStatusLine());
	Validation.validateResponseTime(batchResponse);	
	if(Code.equals("200"))
		Validation.validateJsonSchema(batchResponse, "Schemas/Batch_GetByName_Schema.json" );	
	if(!Code.equals("404"))
		Validation.validateContentType(batchResponse);
	Validation.assertAll();
}

//********************************** GET BATCH BY PROGRAM ID ***************************************

@Given("Admin creates GET batch Request with Program ID {string}")
public void admin_creates_get_batch_request_with_program_id(String requestType) throws Exception {
	batch.setNewBatchRequest(requestType);
}

@When("Admin sends GET HTTPS Request with Program id {string}")
public void admin_sends_get_https_request_with_program_id(String Scenario) {
	batch.GetBatchByPrgmIDRequest(Scenario);
}

@Then("Admin receives {string} with batch Get by Program ID response body")
public void admin_receives_with_batch_get_by_program_id_response_body(String Code) {
batchResponse = context.get("batchResponse", Response.class);
	
	Validation.validateStatusCode(batchResponse, batch.getBatchStatusCode());
	Validation.validateStatusLine(batchResponse, batch.getBatchStatusLine());
	Validation.validateResponseTime(batchResponse);	
	if(!Code.equals("404"))
		Validation.validateContentType(batchResponse);
	Validation.assertAll();
}

//********************************** UPDATE BATCH ************************************************

@Given("Admin creates PUT Request with {string} in batch request body")
public void admin_creates_put_request_with_in_batch_request_body(String requestType) throws Exception {
	batch.setNewBatchRequest(requestType);
}

@When("Admin sends PUT HTTPS Request update batch with {string}")
public void admin_sends_put_https_request_update_batch_with(String Scenario) {
	batch.PutBatchRequest(Scenario);
}

@Then("Admin receives {string} for Update batch request")
public void admin_receives_for_update_batch_request(String Code) {
batchResponse = context.get("batchResponse", Response.class);
	
	Validation.validateStatusCode(batchResponse, batch.getBatchStatusCode());
	Validation.validateStatusLine(batchResponse, batch.getBatchStatusLine());
	Validation.validateResponseTime(batchResponse);	
	if(!Code.equals("404"))
		Validation.validateContentType(batchResponse);
	if(Code.equals("200"))
	{
		BatchValidation.DataValidation(batchResponse);
		Validation.validateJsonSchema(batchResponse, "Schemas/Batch_PostPutValid_Schema.json" );
	}
	else if(Code.equals("400"))
		Validation.validateJsonSchema(batchResponse, "Schemas/Batch_PostPutInvalid_Schema.json" );	
	
	Validation.assertAll();
}


//********************************** DELETE BATCH *********************************************

@Given("Admin creates Delete Request for batch {string}")
public void admin_creates_delete_request_for_batch(String requestType) throws Exception {
	batch.setNewBatchRequest(requestType);
}

@When("Admin sends DELETE HTTPS Request batch {string}")
public void admin_sends_delete_https_request_batch(String Scenario) {
	batch.DeleteBatchRequest(Scenario);
}

@Then("Admin receives batch Delete {string}")
public void admin_receives_batch_delete(String Code) {
	batchResponse = context.get("batchResponse", Response.class);
	Validation.validateStatusCode(batchResponse, batch.getBatchStatusCode());
	Validation.validateStatusLine(batchResponse, batch.getBatchStatusLine());
	Validation.validateResponseTime(batchResponse);	
	if(!Code.equals("404"))
		Validation.validateContentType(batchResponse);
	Validation.assertAll();
}
	
}
