package com.stepdefinitions;

import java.io.FileNotFoundException;
import org.testng.asserts.SoftAssert;
import com.APIRequest.LoginRequest;
import com.APIRequest.ProgramRequest;
import com.APIResponse.CommonResponseValidation;
import com.context.ScenarioContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class ProgramStepDefinitions {
	ScenarioContext context = ScenarioContext.getInstance();
	// private ScenarioContext context = new ScenarioContext();
	private Response programResponse;
	LoginRequest logRequest;
	ProgramRequest programRequest;
	SoftAssert softAssert;
	// JsonNode getTestData_Get;
	Response getProgramFilterResponse;
	CommonResponseValidation ResponseValidation = new CommonResponseValidation();

	public ProgramStepDefinitions() throws FileNotFoundException {
		// context = new ScenarioContext();
		logRequest = new LoginRequest();
		programRequest = new ProgramRequest();
	}

	// ********************************** BackGground***********************//
//	@Given("Admin sets Authorization to Bearer Token")
//	public void admin_sets_authorization_to_bearer_token() {
//		logRequest.PostLoginRequest();
//
//	}

	// ********************************** CREATE PROGRAM//
	// ******************************************
	@Given("Admin sets Authorization to Bearer Token Program")
	public void admin_sets_authorization_to_bearer_token_program() {
		logRequest.PostLoginRequest();
	}

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
		programResponse = context.get("programResponse", Response.class);
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
		programResponse = context.get("programResponse", Response.class);
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

	}

	@Then("Admin gets the program details of that programid with status  {string}")
	public void admin_gets_the_program_details_of_that_programid_with_status(String StatusCode) {
		programResponse = context.get("programResponse", Response.class);
		ResponseValidation.validateStatusCode(programResponse, programRequest.getProgramStatusCode());
		ResponseValidation.validateStatusLine(programResponse, programRequest.getProgramStatusLine());
		ResponseValidation.validateResponseTime(programResponse);

		if (!StatusCode.equals("404"))
			ResponseValidation.validateContentType(programResponse);
	}

//	@Given("Admin creates PUT Request with {string} in Program Module with request body")
//	public void admin_creates_put_request_with_in_program_module_with_request_body(String string) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//	@When("Admin sends PUT HTTPS Request update Program Module with {string}")
//	public void admin_sends_put_https_request_update_program_module_with(String string) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//	@Then("Admin receives {string} for Update Program Module request")
//	public void admin_receives_for_update_program_module_request(String string) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}

	// ........ GetAll Program With Users......
//	
//	@Given("Admin creates GETAllProgramswithUsers request {string} for Program Module")
//	public void admin_creates_get_all_programswith_users_request_for_program_module(String requestType) {
//	    System.out.println("Creating request: " + requestType);
//
//	}
//	@When("Admin sends HTTPS Request with endpoint GETAllProgramswithUsers for Program Module {string}")
//	public void admin_sends_https_request_with_endpoint_get_all_programswith_users_for_program_module(String requestType) {
//		programRequest.sendGetProgramReqWithUser(requestType);
//	}
//	@Then("Admin receives statuscode  {string} for  GETAllProgramswithUsers in Program Module")
//	public void admin_receives_statuscode_for_get_all_programswith_users_in_program_module(String StatusCode) {
//		programResponse = context.get("programResponse", Response.class);
//		ResponseValidation.validateStatusCode(programResponse, programRequest.getProgramStatusCode());
//		ResponseValidation.validateStatusLine(programResponse, programRequest.getProgramStatusLine());
//		ResponseValidation.validateResponseTime(programResponse);
//
//		if (!StatusCode.equals("404"))
//			ResponseValidation.validateContentType(programResponse);
//	
//	}

	// ........UPDATE PROGRAM BY PROGRAMID ......

	@Given("Admin creates PUT Request with {string} in Program Module with request body")
	public void admin_creates_put_request_with_in_program_module_with_request_body(String requestType)
			throws Exception {
		programRequest.setNewProgramRequest(requestType);
	}

	@When("Admin sends PUT HTTPS Request update Program Module with {string}")
	public void admin_sends_put_https_request_update_program_module_with(String requestType) {
		programRequest.PutProgramByIdRequest(requestType);

	}

	@Then("Admin receives {string} for Update Program Module request")
	public void admin_receives_for_update_program_module_request(String StatusCode) {
		programResponse = context.get("programResponse", Response.class);
		System.out.println("programResponseprogramResponseprogramResponse" + programResponse.getStatusCode());
		System.out.println("programResponseprogramResponseprogramResponse" + programRequest.getProgramStatusCode());
		ResponseValidation.validateStatusCode(programResponse, programRequest.getProgramStatusCode());
		ResponseValidation.validateStatusLine(programResponse, programRequest.getProgramStatusLine());
		ResponseValidation.validateResponseTime(programResponse);
		if (!StatusCode.equals("404"))
			ResponseValidation.validateContentType(programResponse);
	}

	// ........UPDATE PROGRAM BY PROGRAMNAME ......

	@Given("Admin creates PUT Request with {string} in Program Module by program name with request body")
	public void admin_creates_put_request_with_in_program_module_by_program_name_with_request_body(String requestType)
			throws Exception {
		programRequest.setNewProgramRequest(requestType);

	}

	@When("Admin sends PUT HTTPS Request update Program Module with {string} by program name")
	public void admin_sends_put_https_request_update_program_module_with_by_program_name(String requestType) {
		programRequest.PutProgramByNameRequest(requestType);
	}

	@Then("Admin receives {string} for Update Program Module request by program name")
	public void admin_receives_for_update_program_module_request_by_program_name(String StatusCode) {
		programResponse = context.get("programResponse", Response.class);
		ResponseValidation.validateStatusCode(programResponse, programRequest.getProgramStatusCode());
		ResponseValidation.validateStatusLine(programResponse, programRequest.getProgramStatusLine());
		ResponseValidation.validateResponseTime(programResponse);
		if (!StatusCode.equals("404"))
			ResponseValidation.validateContentType(programResponse);
	}

	@Given("Admin creates Delete by ProgramId Request  {string} for Program module")
	public void admin_creates_delete_by_program_id_request_for_program_module(String requestType) throws Exception {
		programRequest.setNewProgramRequest(requestType);

	}

	@When("Admin sends DELETE HTTPS Request  {string} for Program module")
	public void admin_sends_delete_https_request_for_program_module(String requestType) {
		programRequest.DeleteProgramIdhRequest(requestType);

	}

	@Then("Admin receives Program Delete {string}")
	public void admin_receives_program_delete(String StatusCode) {
		programResponse = context.get("programResponse", Response.class);
		ResponseValidation.validateStatusCode(programResponse, programRequest.getProgramStatusCode());
		ResponseValidation.validateStatusLine(programResponse, programRequest.getProgramStatusLine());
		ResponseValidation.validateResponseTime(programResponse);
		if (!StatusCode.equals("404"))
			ResponseValidation.validateContentType(programResponse);
	}

}
