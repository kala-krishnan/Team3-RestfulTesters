package com.stepdefinitions;

import java.io.FileNotFoundException;

import org.testng.asserts.SoftAssert;

import com.APIRequest.ClassRequest;
import com.APIRequest.LoginRequest;
import com.APIRequest.UserRequest;
import com.commonUtils.ConfigReader;
import com.context.ScenarioContext;
import com.fasterxml.jackson.databind.JsonNode;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class ClassStepDefinition {
	private ScenarioContext context = ScenarioContext.getInstance();
	private Response classResponse;
	LoginRequest logRequest;
	ClassRequest classRequest;
	SoftAssert softAssert ;
	JsonNode getTestData_Get;
	
	public ClassStepDefinition() throws FileNotFoundException {
		logRequest = new LoginRequest();
		classRequest = new ClassRequest();
		
	}
	@Given("Set Auth to bearer token for class")
	public void set_auth_to_bearer_token_for_class() throws Exception {
		System.out.println(context.get("LMStoken"));
		if(context.get("LMStoken")==(null))
			logRequest.PostLoginRequest();
	}
	
	/******************************* POST Class request *********************************/
	@Given("Admin creates POST Request for the LMS with {string} in class module")
	public void admin_creates_post_request_for_the_lms_with_in_class_module(String requestType) throws Exception {
		classRequest.newClassRequest(requestType);
	}

	@When("Admin calls POST request class with endpoint")
	public void admin_calls_post_request_class_with_endpoint() {
		classRequest.PostNewClassRequest();
	}

	@Then("Admin receive created status for class request")
	public void admin_receive_created_status_for_class_request() {
		classResponse = context.get("classResponse", Response.class);
		int actualStatusCode = classResponse.getStatusCode();
		int expectedStatusCode =classRequest.geClassStatusCode();
		softAssert =new SoftAssert();
		
		//Status code Validation
		softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertAll();
	}
 /************************************** GET **********************************************/
	/*
	@Given("Admin creates GET Request {string} and {string} with BatchId in class module")
	public void admin_creates_get_request_and_with_batch_id_in_class_module(String requestType , String paramValue) throws Exception {
		getTestData_Get=classRequest.setGetClassRequestBody(requestType,paramValue);
	}

	@When("Admin sends HTTPS Get Request with batchId {string}")
	public void admin_sends_https_get_request_with_batch_id(String string) {
		classResponse=classRequest.sendGetClassReqWithBody();
	}

	@Then("Admin receives with response body in get batchId")
	public void admin_receives_with_response_body_in_get_batch_id() {
		int actualStatusCode = classResponse.getStatusCode();
		int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
		softAssert =new SoftAssert();
		
		//Status code Validation
		System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertAll();
	}
	//All classes
	@Given("Admin creates GET Request {string} in classmodule")
	public void admin_creates_get_request_in_classmodule(String requestType) throws Exception {
		getTestData_Get= classRequest.setGetClassRequest(requestType);
	}

	@When("Admin sends HTTPS Get Request with valid or invalid endpoint in class module")
	public void admin_sends_https_get_request_with_valid_or_invalid_endpoint_in_class_module() {
		classResponse=classRequest.sendGetClassReqWithOutBody();
	}

	@Then("Admin receives with response body for class.")
	public void admin_receives_with_response_body_for_class() {
		int actualStatusCode = classResponse.getStatusCode();
		int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
		softAssert =new SoftAssert();
		
		//Status code Validation
		System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertAll();
	}



}
 */
	@Given("Admin creates GET Request {string} and {string} with BatchId in class module")
	public void admin_creates_get_request_and_with_batch_id_in_class_module(String requestType , String paramValue) throws Exception {
		getTestData_Get=classRequest.setGetClassRequestBody(requestType,paramValue);
	}

	@When("Admin sends HTTPS Get Request with batchId {string}")
	public void admin_sends_https_get_request_with_batch_id(String string) {
		classResponse=classRequest.sendGetClassReqWithBody();
	}

	@Then("Admin receives with response body in get batchId")
	public void admin_receives_with_response_body_in_get_batch_id() {
		int actualStatusCode = classResponse.getStatusCode();
		int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
		softAssert =new SoftAssert();
		
		//Status code Validation
		System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertAll();
	}
	//All classes
	@Given("Admin creates GET Request {string} in classmodule")
	public void admin_creates_get_request_in_classmodule(String requestType) throws Exception {
		getTestData_Get= classRequest.setGetClassRequest(requestType);
	}

	@When("Admin sends HTTPS Get Request with valid or invalid endpoint in class module")
	public void admin_sends_https_get_request_with_valid_or_invalid_endpoint_in_class_module() {
		classResponse=classRequest.sendGetClassReqWithOutBody();
	}

	@Then("Admin receives with response body for class.")
	public void admin_receives_with_response_body_for_class() {
		int actualStatusCode = classResponse.getStatusCode();
		int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
		softAssert =new SoftAssert();
		
		//Status code Validation
		System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertAll();
	}
	//GetClassbyClassid
	@Given("Admin creates GET Request {string} and {string} with ClassId in class module")
	public void admin_creates_get_request_and_with_class_id_in_class_module(String requestType, String paramValue ) throws Exception {
		//getTestData_Get=classRequest.setGetClassRequestBodyByClassId(requestType,paramValue);
	}

	@When("Admin sends HTTPS Get Request with classId {string}")
	public void admin_sends_https_get_request_with_class_id(String endpointValue) {
		//classResponse=classRequest.sendGetClassReqWithBodyByClassId();
	}

	@Then("Admin receives with response body in get classID")
	public void admin_receives_with_response_body_in_get_class_id() {
		int actualStatusCode = classResponse.getStatusCode();
		int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
		softAssert =new SoftAssert();
		
		//Status code Validation
		System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertAll();
	}
	
	//GetClassByClassTopic
	@Given("Admin creates GET Request {string} and {string} with Classtopic in class module")
	public void admin_creates_get_request_and_with_classtopic_in_class_module(String requestType, String paramValue ) throws Exception {
		//getTestData_Get=classRequest.setGetClassRequestBodyByClassTopic(requestType,paramValue);
	}

	@When("Admin sends HTTPS GET Request with Classtopic {string}")
	public void admin_sends_https_get_request_with_classtopic(String endpointValue) {
		//classResponse=classRequest.sendGetClassReqWithBodyByClassTopic();
	}

	@Then("Admin receives with response body in get all classes by classtopic")
	public void admin_receives_with_response_body_in_get_all_classes_by_classtopic() {

	}

}








