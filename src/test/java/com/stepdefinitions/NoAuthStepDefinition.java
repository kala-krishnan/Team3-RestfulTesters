package com.stepdefinitions;

import java.io.FileNotFoundException;

import com.APIRequest.BatchRequest;
import com.APIRequest.LoginRequest;
import com.APIRequest.LogoutRequest;
import com.APIResponse.CommonResponseValidation;
import com.context.ScenarioContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class NoAuthStepDefinition {

	BatchRequest batch;
	private Response Response;
	CommonResponseValidation ResponseValidation = new CommonResponseValidation();
	ScenarioContext context = ScenarioContext.getInstance();
	
	public NoAuthStepDefinition() throws FileNotFoundException {
		batch = new BatchRequest();
	}
	
//--------------------------------- BATCH NO AUTH ------------------------------------------

	@Given("Admin creates {string} Request in batch for NoAuth")
	public void admin_creates_request_in_batch_for_no_auth(String Request) throws Exception {
		batch.setNewBatchRequest(Request);
	}

	@When("Admin sends {string} HTTPS batch Request")
	public void admin_sends_https_batch_request(String Request) {
		switch (Request) {
		case "POST": batch.NoAuthPostNewBatchRequest();
			break;
		case "GetAll": batch.NoAuthGetAllBatchRequest();	
			break;
		case "GetById": batch.NoAuthGetBatchByIDRequest();	
			break;
		case "GetByName": batch.NoAuthGetBatchByNameRequest();	
			break;
		case "GetByPrgmId": batch.NoAuthGetBatchByPrgmIDRequest();		
			break;	
		case "PUT": batch.NoAuthPutBatchRequest();	
			break;	
		case "Delete": batch.NoAuthDeleteBatchRequest();
			break;	
		}
	}

	@Then("Admin receives batch {int} Status for NoAuth")
	public void admin_receives_batch_status_for_no_auth(Integer Code) {
		Response = context.get("batchResponse", Response.class);
		ResponseValidation.validateStatusCode(Response, batch.getBatchStatusCode());
		ResponseValidation.validateStatusLine(Response, batch.getBatchStatusLine());
		ResponseValidation.validateResponseTime(Response);
		ResponseValidation.assertAll();
	}
		

	}
