package com.stepdefinitions;

import org.testng.asserts.SoftAssert;

import com.APIRequest.LoginRequest;
import com.APIResponse.CommonResponseValidation;
import com.commonUtils.ConfigReader;
import com.commonUtils.SpecificationClass;
import com.context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.FileNotFoundException;
import java.util.List;

public class LoginStepDefinitons {

	ScenarioContext context = ScenarioContext.getInstance();
	CommonResponseValidation Validation = new CommonResponseValidation();
	private Response loginResponse;
	LoginRequest login;
	SoftAssert softAssert ;
	
	String userLoginSchema = ConfigReader.getProperty("userLoginSchema");

	public LoginStepDefinitons() throws FileNotFoundException {

		login = new LoginRequest();
	}
	
	@Given("Admin creates Login Valid Request")
	public void admin_creates_login_valid_request() {
		//context.set("LMStoken",null);
	}

	@When("Admin sends Post HTTPS Login Request")
	public void admin_sends_post_https_login_request() {
		login.PostLoginRequest();
	}

	@Then("Admin receives status with token")
	public void admin_receives_status_with_token() {
		loginResponse = context.get("loginResponse", Response.class);
		Validation.validateStatusCode(loginResponse, 200);
		Validation.validateStatusLine(loginResponse, "HTTP/1.1 200 OK");
		Validation.validateResponseTime(loginResponse);
	}

	@Given("Admin creates Login {string} Request")
	public void admin_creates_login_request(String requestType) throws Exception {
		login.setLoginRequest(requestType);
	}

	@When("Admin sends {string} HTTPS Login Request")
	public void admin_sends_https_login_request(String Scenario) {
		login.postLoginRequestFromJson(Scenario);
	}

	@Then("Admin receives status {string} for Login request")
	public void admin_receives_status_for_login_request(String Code) {
		loginResponse = context.get("loginResponse", Response.class);
		Validation.validateStatusCode(loginResponse, login.getStatusCode());
		Validation.validateStatusLine(loginResponse, login.getStatusLine());
		Validation.validateResponseTime(loginResponse);	
		if(!Code.equals("404"))
			Validation.validateContentType(loginResponse);
	}


	/************* from json file *****************/
	
//	@Given("Admin creates request with valid credentials {string} for LMS")
//	public void admin_creates_request_with_valid_credentials(String requestType) throws Exception {
//		logRequest.setLoginRequest(requestType);
//	}
//
//	@When("Admin calls Post Https method  with valid endpoint for LMS")
//	public void admin_calls_post_https_method_with_valid_endpoint_for_LMS() {
//		logRequest.postLoginRequestFromJson();
//	}
//
//	@Then("Admin receives created with auto generated token")
//	public void admin_receives_created_with_auto_generated_token() {
//		loginResponse = context.get("loginResponse", Response.class);
//		int actualStatusCode = loginResponse.getStatusCode();
//		int expectedStatusCode =logRequest.getStatusCode();
//		softAssert =new SoftAssert();
//
//		//Status code Validation
//		softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
//
//		//  Schema Validation
//		if(actualStatusCode==200) {
////			System.out.println(userLoginSchema);
////			loginResponse.then().assertThat()
////			.body(matchesJsonSchemaInClasspath(userLoginSchema));
//
//		// Validate data types
//		Boolean passwordExpired = loginResponse.jsonPath().getBoolean("passwordExpired");
//		softAssert.assertTrue(loginResponse.jsonPath().getString("token") instanceof String, "Token should be a String");
//		softAssert.assertTrue(loginResponse.jsonPath().getString("type") instanceof String, "Type should be a String");
//		softAssert.assertTrue(loginResponse.jsonPath().getString("userId") instanceof String, "User ID should be a String");
//		softAssert.assertTrue(loginResponse.jsonPath().getString("email") instanceof String, "Email should be a String");
//		softAssert.assertTrue(loginResponse.jsonPath().getList("roles") instanceof List, "Roles should be a List");
//		softAssert.assertTrue(loginResponse.jsonPath().getString("status") instanceof String, "Status should be a String");
//		softAssert.assertTrue(passwordExpired  instanceof Boolean, "PasswordExpired should be Boolean");
//
//		// Validate actual data values
//		softAssert.assertFalse(loginResponse.jsonPath().getString("token").isEmpty(), "Token should not be empty");
//		softAssert.assertEquals(loginResponse.jsonPath().getString("status"), "Active", "Status should be Active");
//		softAssert.assertFalse(loginResponse.jsonPath().getList("roles").isEmpty(), "Roles list should not be empty");
//		}
//
//		softAssert.assertAll();
//
//	}



}
