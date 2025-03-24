package com.stepdefinitions;

import java.io.FileNotFoundException;

import org.testng.asserts.SoftAssert;

import com.APIRequest.BatchRequest;
import com.APIRequest.LoginRequest;
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
	CommonResponseValidation ResponseValidation = new CommonResponseValidation();
	private ScenarioContext context = new ScenarioContext();
	
	public BatchStepDefinition() throws FileNotFoundException {
		batch = new BatchRequest(context);
		loginRequest = new LoginRequest(context);	
	}
	
@Given("Admin sets Authorization to Bearer Token")
public void admin_sets_authorization_to_bearer_token() throws Exception {
	System.out.println(context.get("LMStoken"));
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

	ResponseValidation.validateStatusCode(batchResponse, batch.getBatchStatusCode());
	ResponseValidation.validateStatusLine(batchResponse, batch.getBatchStatusLine());
	ResponseValidation.validateResponseTime(batchResponse);	
	if(!Code.equals("404"))
	ResponseValidation.validateContentType(batchResponse);
	
}

//********************************** GET All BATCH ******************************************

@Given("Admin creates GET Request for batch {string}")
public void admin_creates_get_request_for_batch(String requestType) throws Exception {
	batch.setNewBatchRequest(requestType);
}

@When("Admin sends GET HTTPS Request with batch {string}")
public void admin_sends_get_https_request_with_batch(String Scenario) {
	batch.GetAllBatchRequest(Scenario);
}

@Then("Admin receives {string} with batch GetAll response body")
public void admin_receives_with_batch_get_all_response_body(String Code) {
	batchResponse = context.get("batchResponse", Response.class);
	
	ResponseValidation.validateStatusCode(batchResponse, batch.getBatchStatusCode());
	ResponseValidation.validateStatusLine(batchResponse, batch.getBatchStatusLine());
	ResponseValidation.validateResponseTime(batchResponse);	
	if(!Code.equals("404"))
	ResponseValidation.validateContentType(batchResponse);
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
	
	ResponseValidation.validateStatusCode(batchResponse, batch.getBatchStatusCode());
	ResponseValidation.validateStatusLine(batchResponse, batch.getBatchStatusLine());
	ResponseValidation.validateResponseTime(batchResponse);	
	if(!Code.equals("404"))
	ResponseValidation.validateContentType(batchResponse);
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
	
	ResponseValidation.validateStatusCode(batchResponse, batch.getBatchStatusCode());
	ResponseValidation.validateStatusLine(batchResponse, batch.getBatchStatusLine());
	ResponseValidation.validateResponseTime(batchResponse);	
	if(!Code.equals("404"))
	ResponseValidation.validateContentType(batchResponse);
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
	
	ResponseValidation.validateStatusCode(batchResponse, batch.getBatchStatusCode());
	ResponseValidation.validateStatusLine(batchResponse, batch.getBatchStatusLine());
	ResponseValidation.validateResponseTime(batchResponse);	
	if(!Code.equals("404"))
	ResponseValidation.validateContentType(batchResponse);
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
	
	ResponseValidation.validateStatusCode(batchResponse, batch.getBatchStatusCode());
	ResponseValidation.validateStatusLine(batchResponse, batch.getBatchStatusLine());
	ResponseValidation.validateResponseTime(batchResponse);	
	if(!Code.equals("404"))
	ResponseValidation.validateContentType(batchResponse);
}

//********************************** DELETE BATCH *********************************************

	
}
