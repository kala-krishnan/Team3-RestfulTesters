package com.stepdefinitions;

import java.io.FileNotFoundException;

import com.APIRequest.BatchRequest;
import com.APIRequest.LoginRequest;
import com.APIRequest.LogoutRequest;
import com.APIRequest.NoAuthRequest;
import com.APIRequest.ProgramRequest;
import com.APIRequest.UserRequest;
import com.APIResponse.CommonResponseValidation;
import com.context.ScenarioContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class NoAuthStepDefinition {
	LoginRequest loginRequest;
	
	BatchRequest batch;
	LogoutRequest logout;
	ProgramRequest program;

	private Response Response;
	CommonResponseValidation ResponseValidation = new CommonResponseValidation();
	ScenarioContext context = ScenarioContext.getInstance();
	UserRequest user; 
	NoAuthRequest noAuthReq;
	public NoAuthStepDefinition() throws FileNotFoundException
	{
		batch = new BatchRequest();
		user = new UserRequest();
		logout = new LogoutRequest();
		noAuthReq = new NoAuthRequest();
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
	
//--------------------------------- USER NO AUTH ------------------------------------------	
	
	@Given("Admin creates {string} Request in User for NoAuth")
	public void admin_creates_request_in_user_for_no_auth(String scenario) throws Exception {
		
		user.updateUserRequest(scenario);
		if(!scenario.equals("NoAuthAPIUpdateUserByID") )
		{
			user.newUserRequest(scenario);
		}
	}

	@When("Admin sends {string} HTTPS User Request")
	public void admin_sends_https_user_request(String request) {
		switch (request) {
		case "APICreateUserWithRole": noAuthReq.PostNewUserRequestNoAuth();
		break;
		case "APIGetAllUser": noAuthReq.getAllUserRequestNoAuth(request);
		break;
		case "APIGetAllUserRoles": noAuthReq.getAllUserRequestNoAuth(request);
		break;
		case "APIGetUserByStatus": noAuthReq.getAllUserRequestNoAuth(request);
		break;
		case "APIGetActiveUser": noAuthReq.getAllUserRequestNoAuth(request);
		break;
		case "APIGetAllRoles": noAuthReq.getAllUserRequestNoAuth(request);
		break;
		case "APIGetAllUserEmail": noAuthReq.getAllUserRequestNoAuth(request);
		break;
		case "APIUpdateUserByID": noAuthReq.updateRoleByUserIdNoAuth(request);
		break;
		case "APIGetUserByID": noAuthReq.getUserByUserIdNoAuth(request);
		break;
		}
		
	}

	@Then("Admin receives User {int} Status for NoAuth")
	public void admin_receives_user_status_for_no_auth(Integer int1) {
		Response = context.get("userResponse", Response.class);
		ResponseValidation.validateStatusCode(Response, user.getUserStatusCode());
		
	}

//--------------------------------- LOGOUT NO AUTH ------------------------------------------
	
	@Given("Admin creates {string} Request for NoAuth")
	public void admin_creates_request_for_no_auth(String Request) throws Exception {
	    logout.setGetLogoutRequest(Request);
	}
	
	@When("Admin sends GET HTTPS Logout Request")
	public void admin_sends_get_https_logout_request() {
		logout.NoAuthGetLogoutRequest();
	}
	
	@Then("Admin receives batch {int} Status for Logout NoAuth")
	public void admin_receives_batch_status_for_logout_no_auth(Integer Code) {
		Response = context.get("logoutResponse", Response.class);
		ResponseValidation.validateStatusCode(Response, logout.getStatusCode());
		ResponseValidation.validateStatusLine(Response, logout.getStatusLine());
		ResponseValidation.validateResponseTime(Response);
		ResponseValidation.assertAll();
	}
	
	
	//--------------------------------- PROGRAM NO AUTH ------------------------------------------
	@Given("Admin creates {string} Request in program for NoAuth")
	public void admin_creates_request_in_program_for_no_auth(String requestType) throws Exception {
		program.setNewProgramRequest(requestType);
	}
	@When("Admin sends {string} HTTPS program Request")
	public void admin_sends_https_program_request(String request) {
		switch (request) {
		case "POST": program.NoAuthCreateProgram();
			break;
		case "GetAll": program.NoAuthGetAllProgram();	
			break;
		case "GetById": program.NoAuthGetProgramById();	
			break;
		case "GetByName": program.NoAuthPUTProgramID();	
			break;	
		case "Delete": program.NoAuthDeleteProgrambyId();
			break;	
		}
	}
	@Then("Admin receives program {int} Status for NoAuth")
	public void admin_receives_program_status_for_no_auth(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}



	}

