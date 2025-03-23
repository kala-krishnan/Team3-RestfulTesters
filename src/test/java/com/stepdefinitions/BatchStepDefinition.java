package com.stepdefinitions;

import java.io.FileNotFoundException;

import org.testng.asserts.SoftAssert;

import com.APIRequest.BatchRequest;
import com.APIRequest.LoginRequest;
import com.context.ScenarioContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class BatchStepDefinition {

	LoginRequest loginRequest;
	BatchRequest batchRequest;
	private Response batchResponse;
	SoftAssert softAssert;
	private ScenarioContext context = new ScenarioContext();
	
	public BatchStepDefinition() throws FileNotFoundException {
		batchRequest = new BatchRequest(context);
		loginRequest = new LoginRequest(context);
		
	}
	
	
@Given("Admin sets Authorization to Bearer Token")
public void admin_sets_authorization_to_bearer_token() throws Exception {
	loginRequest.PostLoginRequest();
}
	
//********************************** CREATE BATCH ******************************************

@Given("Admin creates Request with {string} in batch request body")
public void admin_creates_request_with_in_batch_request_body(String requestType) throws Exception {
	batchRequest.setNewBatchRequest(requestType);
	
}

@When("Admin sends POST HTTPS Request batch with {string}")
public void admin_sends_post_https_request_batch_with(String string) {
	batchRequest.PostNewBatchRequest();
}

@Then("Admin receives {string} for batch request")
public void admin_receives_for_batch_request(String string) {
	
	
	batchResponse = context.get("batchResponse", Response.class);
	int actualStatusCode = batchResponse.getStatusCode();
	int expectedStatusCode =batchRequest.getBatchStatusCode();
	softAssert =new SoftAssert();
	
	//Status code Validation
	softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
	softAssert.assertAll();

	}

//********************************** GET All BATCH ******************************************

@Given("Admin creates GET Request for batch")
public void admin_creates_get_request_for_batch() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("Admin sends GET HTTPS Request with batch {string}")
public void admin_sends_get_https_request_with_batch(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives {string} with batch GetAll response body")
public void admin_receives_with_batch_get_all_response_body(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

//********************************** GET BATCH BY ID ******************************************

@Given("Admin creates GET Request with Batch ID")
public void admin_creates_get_request_with_batch_id() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("Admin sends GET HTTPS Request with batch id {string}")
public void admin_sends_get_https_request_with_batch_id(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives {string} with batch Get by ID response body")
public void admin_receives_with_batch_get_by_id_response_body(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

//********************************** GET BATCH BY NAME ******************************************

@Given("Admin creates GET Request with Batch Name")
public void admin_creates_get_request_with_batch_name() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("Admin sends GET HTTPS Request with batch Name {string}")
public void admin_sends_get_https_request_with_batch_name(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives {string} with batch Get by Name response body")
public void admin_receives_with_batch_get_by_name_response_body(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

//********************************** GET BATCH BY PROGRAM ID ***************************************

@Given("Admin creates GET Request with Program ID")
public void admin_creates_get_request_with_program_id() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("Admin sends GET HTTPS Request with Program id {string}")
public void admin_sends_get_https_request_with_program_id(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives {string} with batch Get by Program ID response body")
public void admin_receives_with_batch_get_by_program_id_response_body(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

//********************************** UPDATE BATCH ************************************************

@Given("Admin creates PUT Request with {string} in batch request body")
public void admin_creates_put_request_with_in_batch_request_body(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("Admin sends PUT HTTPS Request update batch with {string}")
public void admin_sends_put_https_request_update_batch_with(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Admin receives {string} for Update batch request")
public void admin_receives_for_update_batch_request(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}



	
}
