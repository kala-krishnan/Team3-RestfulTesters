package com.stepdefinitions;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.testng.asserts.SoftAssert;

import com.APIRequest.LoginRequest;
import com.APIRequest.ProgramRequest;
import com.APIResponse.ProgramResponseValidation;
import com.context.ScenarioContext;
import com.fasterxml.jackson.databind.JsonNode;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class ProgramStepDefinitions {

	// private ScenarioContext context = new ScenarioContext();
	private Response programResponse;
	LoginRequest logRequest;
	ProgramRequest programRequest;
	SoftAssert softAssert;
	JsonNode getTestData_Get;
	Response getProgramFilterResponse;
    private ProgramResponseValidation responseValidator = new ProgramResponseValidation();




	public ProgramStepDefinitions() throws FileNotFoundException {
		// context = new ScenarioContext();
		logRequest = new LoginRequest(LoginRequest.context);
		programRequest = new ProgramRequest(LoginRequest.context);
	}
//	/*********************************background*********************************/
//
//	@Given("Set Auth to bearer token")
//	public void set_auth_to_bearer_token() throws Exception {
//		logRequest.setLoginRequest("LoginValid");
//		logRequest.postLoginRequestFromJson();
//	}
//

	@Given("Admin creates POST Request with request body {string} for LMS Program Module")
	public void admin_creates_post_request_with_request_body_for_lms_program_module(String requestType)
			throws Exception {
		programRequest.setNewProgramRequest(requestType);

	}

	@When("Admin calls Post Https method  with valid endpoint for Program Module")
	public void admin_calls_post_https_method_with_valid_endpoint_for_program_module() {
		programRequest.PostNewProgramRequest();

	}

	@Then("Admin receive created  status for LMS User Module for Program Module")
	public void admin_receive_created_status_for_lms_user_module_for_program_module() {

		programResponse = LoginRequest.context.get("programResponse", Response.class);
		int actualStatusCode = programResponse.getStatusCode();
		int expectedStatusCode = programRequest.getProgramStatusCode();
		softAssert = new SoftAssert();
		softAssert.assertEquals(actualStatusCode, expectedStatusCode,
				"Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertAll();
	}

	@Given("Admin creates POST Request for the LMS Program Module with request body")
	public void admin_creates_post_request_for_the_lms_program_module_with_request_body() {

	}

	@When("Admin sends HTTPS Request for Program Module with request Body and endpoint using invalid method status {string}")
	public void admin_sends_https_request_for_program_module_with_request_body_and_endpoint_using_invalid_method_status(
			String requestType) throws Exception {
		programRequest.setNewProgramRequest(requestType);
		programRequest.PostNewProgramRequestInvalidMethod();

	}

	@Then("Admin receives {int} Method Not Allowed for Program Module")
	public void admin_receives_method_not_allowed_for_program_module(Integer expectedStatusCode) {
		// Retrieve the response from the context or programRequest
		int actualStatusCode = programResponse.getStatusCode();
		softAssert = new SoftAssert();
		softAssert.assertEquals(actualStatusCode, expectedStatusCode,
				"Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertAll();
	}

/***********************get all Programs request  
 * @throws Exception *********************************/

	@Given("Admin creates GETAll request {string} for Program Module")
	public void admin_creates_get_all_request_for_program_module(String requestType) throws Exception {
		
		 getTestData_Get= programRequest.setGetProgramRequest(requestType);

	}

	@When("Admin sends HTTPS Request with endpoint GETAll for Program Module")
	public void admin_sends_https_request_with_endpoint_get_all_for_program_module() {
		getProgramFilterResponse=programRequest.sendGetProgramReqWithOutBody();

		
	}

	@Then("Admin receives statuscode for Program Module get all")
	public void admin_receives_statuscode_for_program_module_get_all() {
	    int expectedStatusCode = getTestData_Get.get("statusCode").asInt();

        // Validate Status Code
        responseValidator.validateStatusCode(getProgramFilterResponse, expectedStatusCode);

        // Validate Headers
        responseValidator.validateHeaders(getProgramFilterResponse);

        // Validate Response Body
        responseValidator.validateResponseBody(getProgramFilterResponse);

        // Assert all validations
        responseValidator.assertAll();
		
	}
}
