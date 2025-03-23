package com.stepdefinitions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.FileNotFoundException;

import org.testng.asserts.SoftAssert;

import com.APIRequest.LoginRequest;
import com.APIRequest.UserRequest;
import com.commonUtils.ConfigReader;
import com.commonUtils.TestDataLoader;
import com.context.ScenarioContext;
import com.fasterxml.jackson.databind.JsonNode;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class UserStepDefinition {
	private ScenarioContext context = new ScenarioContext();
	private Response userResponse;
	LoginRequest logRequest;
	UserRequest userRequest;
	SoftAssert softAssert ;
	Response getUserFilterResponse;
	JsonNode getTestData_Get;
	String userSchema = ConfigReader.getProperty("userSchema");
	
	public UserStepDefinition() throws FileNotFoundException {
		logRequest = new LoginRequest(context);
		userRequest = new UserRequest(context);
		
	}

/*********************************background*********************************/
	
@Given("Set Auth to bearer token")
public void set_auth_to_bearer_token() throws Exception {
	logRequest.setLoginRequest("LoginValid");
	logRequest.postLoginRequestFromJson();
}

/*********************************post request  *********************************/

@Given("Admin creates POST Request with request body {string} for LMS User Module")
public void admin_creates_post_request_with_request_body_for_lms_user_module(String requestType) throws Exception {
	userRequest.setNewUserRequest(requestType);
}

@When("Admin calls Post Https method  with valid endpoint for LMS User Module")
public void admin_calls_post_https_method_with_valid_endpoint_for_lms_user_module() {
	userRequest.PostNewUserRequest();
}

@Then("Admin receive created  status for LMS User Module")
public void admin_receive_created_status_for_lms_user_module() {
	userResponse = context.get("userResponse", Response.class);
	int actualStatusCode = userResponse.getStatusCode();
	int expectedStatusCode =userRequest.getUserStatusCode();
	softAssert =new SoftAssert();
	
	//Status code Validation
	softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
	softAssert.assertAll();
}

/***********************get all request  *********************************/

@Given("Admin creates GET Request {string} for LMS User Module")
public void admin_creates_get_request_for_lms_user_module(String requestType) throws Exception  {
	 getTestData_Get= userRequest.setGetUserRequest(requestType);
}

@When("Admin sends GET Request with v2 endpoint for LMS User Module")
public void admin_sends_get_request_with_endpoint_for_lms_user_module() {
	 getUserFilterResponse=userRequest.sendGetUserReqWithOutBody();
}

@Then("Admin gets the list of active users with filters")
public void Admin_gets_the_list_of_active_users() {
	int actualStatusCode = getUserFilterResponse.getStatusCode();
	int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
	softAssert =new SoftAssert();
	
	//Status code Validation
	System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
	softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
	softAssert.assertAll();
}

/***********************get request based on parameter  *********************************/

@Given("Admin creates GET Request {string} and {string} for LMS User Module")
public void admin_creates_get_request_and_for_lms_user_module(String requestType, String paramValue) throws Exception {
	 getTestData_Get=userRequest.setGetUserRequestBody(requestType,paramValue);
}

@When("Admin sends GET Request with endpoint and {string} for LMS User Module")
public void admin_sends_get_request_with_endpoint_and_for_lms_user_module(String endpointValue) {
	getUserFilterResponse=userRequest.sendGetUserReqWithBody();
}


@Then("Admin gets the users details")
public void admin_gets_the_users_details() {
	int actualStatusCode = getUserFilterResponse.getStatusCode();
	int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
	softAssert =new SoftAssert();
	
	//Status code Validation
	System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
	softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
	softAssert.assertAll();
}

}