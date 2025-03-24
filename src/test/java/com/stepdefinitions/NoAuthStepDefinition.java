package com.stepdefinitions;

import java.io.FileNotFoundException;

import com.APIRequest.BatchRequest;
import com.APIRequest.LoginRequest;
import com.APIResponse.CommonResponseValidation;
import com.context.ScenarioContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class NoAuthStepDefinition {
	LoginRequest loginRequest;
	BatchRequest batch;
	private Response Response;
	CommonResponseValidation ResponseValidation = new CommonResponseValidation();
	private ScenarioContext context = new ScenarioContext();
	
	public NoAuthStepDefinition() throws FileNotFoundException {
		batch = new BatchRequest(context);
		loginRequest = new LoginRequest(context);	
	}
	
	@Given("Admin creates {string} Request in batch for NoAuth")
	public void admin_creates_request_in_batch_for_no_auth(String Request) throws Exception {
		batch.setNewBatchRequest(Request);
	}

	@When("Admin sends POST HTTPS batch Request with valid endpoint")
	public void admin_sends_post_https_batch_request_with_valid_endpoint() {
		batch.NoAuthPostNewBatchRequest();
	}

	@Then("Admin receives {int} Unauthorized for NoAuth")
	public void admin_receives_unauthorized_for_no_auth(Integer int1) {
		Response = context.get("batchResponse", Response.class);
		ResponseValidation.validateStatusCode(Response, batch.getBatchStatusCode());
		ResponseValidation.validateStatusLine(Response, batch.getBatchStatusLine());
		ResponseValidation.validateResponseTime(Response);	
	}

	@When("Admin sends GET HTTPS Request with batch valid")
	public void admin_sends_get_https_request_with_batch_valid() {
		batch.NoAuthGetAllBatchRequest();
	}

	@When("Admin sends GET HTTPS Request with batch Id valid")
	public void admin_sends_get_https_request_with_batch_id_valid() {
		batch.NoAuthGetBatchByIDRequest();
	}

	@When("Admin sends GET HTTPS Request with batch Name valid")
	public void admin_sends_get_https_request_with_batch_name_valid() {
		batch.NoAuthGetBatchByNameRequest();
	}

	@When("Admin sends GET HTTPS Request with batch PrgmId valid")
	public void admin_sends_get_https_request_with_batch_prgm_id_valid() {
		batch.NoAuthGetBatchByPrgmIDRequest();
	}

	@When("Admin sends PUT HTTPS batch Request with valid endpoint")
	public void admin_sends_put_https_batch_request_with_valid_endpoint() {
		batch.NoAuthPutBatchRequest();
	}

	@When("Admin sends Delete HTTPS batch Request with valid endpoint")
	public void admin_sends_delete_https_batch_request_with_valid_endpoint() {
		batch.NoAuthDeleteBatchRequest();
	}


}
