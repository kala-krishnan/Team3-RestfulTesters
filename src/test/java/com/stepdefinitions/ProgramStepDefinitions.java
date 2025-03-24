package com.stepdefinitions;

import java.io.FileNotFoundException;
import org.testng.asserts.SoftAssert;
import com.APIRequest.LoginRequest;
import com.APIRequest.ProgramRequest;
import com.APIResponse.CommonResponseValidation;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class ProgramStepDefinitions {

	//private ScenarioContext context = new ScenarioContext();
	private Response programResponse;
	LoginRequest logRequest;
	ProgramRequest programRequest;
	SoftAssert softAssert;
	JsonNode getTestData_Get;
	Response getProgramFilterResponse;
	CommonResponseValidation ResponseValidation = new CommonResponseValidation();

	public ProgramStepDefinitions() throws FileNotFoundException {
		// context = new ScenarioContext();
		logRequest = new LoginRequest(LoginRequest.context);
		programRequest = new ProgramRequest(LoginRequest.context);
	}

	// ********************************** CREATE PROGRAM//
	// ******************************************

	@Given("Admin creates POST Request with request body {string} for LMS Program Module")
	public void admin_creates_post_request_with_request_body_for_lms_program_module(String requestType)
			throws Exception {
		programRequest.setNewProgramRequest(requestType);

	}

	@When("Admin calls Post Https request  {string} for Program Module with {string}")
	public void admin_calls_post_https_request_for_program_module_with(String requestType, String Endpoint) {
		programRequest.PostNewProgramRequest(requestType);
	}

	@Then("Admin receive created  status  {string} for Program Module")
	public void admin_receive_created_status_for_program_module(String StatusCode) {
		programResponse = LoginRequest.context.get("programResponse", Response.class);
		ResponseValidation.validateStatusCode(programResponse, programRequest.getProgramStatusCode());
		ResponseValidation.validateStatusLine(programResponse, programRequest.getProgramStatusLine());
		ResponseValidation.validateResponseTime(programResponse);
		if (!StatusCode.equals("404"))
			ResponseValidation.validateContentType(programResponse);
	}

//	//********************************** GETALL PROGRAM ******************************************

	@Given("Admin creates GETAll request {string} for Program Module")
	public void admin_creates_get_all_request_for_program_module(String requestType) throws Exception {
		programRequest.setNewProgramRequest(requestType);
	}

	@When("Admin sends HTTPS Request with endpoint GETAll for Program Module {string}")
	public void admin_sends_https_request_with_endpoint_get_all_for_program_module(String requestType) {
		programRequest.sendGetProgramReqWithOutBody(requestType);
	}

	@Then("Admin receives statuscode  {string} for Program Module get all")
	public void admin_receives_statuscode_for_program_module_get_all(String StatusCode) {
		programResponse = LoginRequest.context.get("programResponse", Response.class);
		ResponseValidation.validateStatusCode(programResponse, programRequest.getProgramStatusCode());
		ResponseValidation.validateStatusLine(programResponse, programRequest.getProgramStatusLine());
		ResponseValidation.validateResponseTime(programResponse);

		if (!StatusCode.equals("404"))
			ResponseValidation.validateContentType(programResponse);
	}

//	
//	//........ GetProgramId......

	@Given("Admin creates GET Request by {string} for LMS Program module")
	public void admin_creates_get_request_by_for_lms_program_module(String requestType) throws Exception {
		programRequest.setNewProgramRequest(requestType);
	}

	@When("Admin sends GET Request by  {string} ProgramId for LMS Program Module")
	public void admin_sends_get_request_by_program_id_for_lms_program_module(String requestType) throws Exception {
		programRequest.sendGetProgrambyIdReqWithOutBody(requestType);
		;
	}

	@Then("Admin gets the program details of that programid with status  {string}")
	public void admin_gets_the_program_details_of_that_programid_with_status(String StatusCode) {
		programResponse = LoginRequest.context.get("programResponse", Response.class);
		ResponseValidation.validateStatusCode(programResponse, programRequest.getProgramStatusCode());
		ResponseValidation.validateStatusLine(programResponse, programRequest.getProgramStatusLine());
		ResponseValidation.validateResponseTime(programResponse);

		if (!StatusCode.equals("404"))
			ResponseValidation.validateContentType(programResponse);
	}
}
