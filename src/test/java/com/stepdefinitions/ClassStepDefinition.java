package com.stepdefinitions;

import java.io.FileNotFoundException;

import org.testng.Assert;
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
	
	//batchid
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
	@Given("Admin creates GET Request {string} in class module LMS")
	public void admin_creates_get_request_in_class_module_lms(String requestType) throws Exception {
		getTestData_Get= classRequest.setGetClassRequest(requestType);
	}

	@When("Admin sends HTTPS Get Request {string} with valid or invalid endpoint in class module")
	public void admin_sends_https_get_request_with_valid_or_invalid_endpoint_in_class_module(String resType) {
		classResponse=classRequest.sendGetClassReqWithOutBody(resType);
	}

	@Then("Admin receives {string} with response body for class.")
	public void admin_receives_with_response_body_for_class(String Statuscode) {
		//Assert.assertEquals(classRequest.geClassStatusCode(), getTestData_Get.get("statusCode").asInt());
		int actualStatusCode = classResponse.getStatusCode();
		//int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
		softAssert =new SoftAssert();
		
		//Status code Validation
		System.out.println("Expected status code: " + Statuscode + " but got: " + actualStatusCode);
		//softAssert.assertEquals(actualStatusCode, Statuscode, "Expected status code: " + Statuscode + " but got: " + actualStatusCode);
		softAssert.assertAll();
	}

//	//GetClassbyClassid
	@Given("Admin creates GET Request {string} and {string} with ClassId in class module")
	public void admin_creates_get_request_and_with_class_id_in_class_module(String requestType, String paramValue) throws Exception {
		getTestData_Get=classRequest.setGetClassRequestBodyByClassId(requestType,paramValue);
	}

	@When("Admin sends HTTPS Get Request with classId {string} and {string}")
	public void admin_sends_https_get_request_with_class_id_and(String requestType, String paramValue ) {
		classResponse=classRequest.sendGetClassReqWithBodyByClassId(requestType,paramValue);
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
		getTestData_Get=classRequest.setGetClassRequestBodyByClassTopic(requestType,paramValue);
	}


@When("Admin sends HTTPS GET Request with Classtopic {string} and {string}")
public void admin_sends_https_get_request_with_classtopic_and(String requestType, String paramValue) {
	classResponse=classRequest.sendGetClassReqWithBodyByClassTopic(requestType,paramValue);
}

	@Then("Admin receives with response body in get all classes by classtopic")
	public void admin_receives_with_response_body_in_get_all_classes_by_classtopic() {
		int actualStatusCode = classResponse.getStatusCode();
		int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
		softAssert =new SoftAssert();
		
		//Status code Validation
		System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertAll();
	}
	
	
	//GetClassRecordingByBatchID
	@Given("Admin creates GET Request {string} and {string} with classrecordinging BatchId in class module")
	public void admin_creates_get_request_and_with_classrecordinging_batch_id_in_class_module(String requestType , String paramValue ) throws Exception {
		getTestData_Get=classRequest.setGetClassRequestBodyByClassRecordingByBatchId(requestType,paramValue);
	}

	@When("Admin sends GET Request with classrecording by batchId {string} and {string}")
	public void admin_sends_get_request_with_classrecording_by_batch_id_and(String requestType, String paramValue) {
		classResponse=classRequest.sendGetClassReqWithBodyByClassRecordingByBatchId(requestType,paramValue);
	}

	@Then("Admin receives with response body in get class recording by batchId")
	public void admin_receives_with_response_body_in_get_class_recording_by_batch_id() {
		int actualStatusCode = classResponse.getStatusCode();
		int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
		softAssert =new SoftAssert();
		
		//Status code Validation
		System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertAll();
	}

	//GetClassRecordingByClassID
	@Given("Admin creates GET Request {string} and {string} with classrecordinging classId in class module")
	public void admin_creates_get_request_and_with_classrecordinging_class_id_in_class_module(String requestType , String paramValue) throws Exception {
		getTestData_Get=classRequest.setGetClassRequestBodyByClassRecordingByClassId(requestType,paramValue);
	}

	@When("Admin sends GET Request with classrecording by classId {string} and {string}")
	public void admin_sends_get_request_with_classrecording_by_class_id_and(String requestType, String paramValue) {
		classResponse=classRequest.sendGetClassReqWithBodyByClassRecordingByClassId(requestType,paramValue);
	}

	@Then("Admin receives with response body in get class recording by classId")
	public void admin_receives_with_response_body_in_get_class_recording_by_class_id() {
		int actualStatusCode = classResponse.getStatusCode();
		int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
		softAssert =new SoftAssert();
		
		//Status code Validation
		System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertAll();
	}
	//GetAllRecordingByClass
	
	@Given("Admin creates GET Request {string} in all recording by class module LMS")
	public void admin_creates_get_request_in_all_recording_by_class_module_lms(String requestType) throws Exception {
		getTestData_Get= classRequest.setGetClassRequest(requestType);
	}

	@When("Admin sends HTTPS Get Request {string} with valid or invalid endpoint for all recordings class module")
	public void admin_sends_https_get_request_with_valid_or_invalid_endpoint_for_all_recordings_class_module(String resType) {
		classResponse=classRequest.sendGetClassReqWithOutBodyClass(resType);
	}

	@Then("Admin receives {string} with response body for class module for all recording.")
	public void admin_receives_with_response_body_for_class_module_for_all_recording(String string) {
		int actualStatusCode = classResponse.getStatusCode();
		int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
		softAssert =new SoftAssert();
		
		//Status code Validation
		System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertAll();
	}
	//GetAllClassesByStaffId
	@Given("Admin creates GET request {string} and {string} in class module by staffId")
	public void admin_creates_get_request_and_in_class_module_by_staff_id(String requestType, String paramValue) throws Exception {
		getTestData_Get=classRequest.setGetClassRequestBodyClassByStaffId(requestType,paramValue);
	}

	@When("Admin sends HTTPS Get request with class by staffId {string} and {string}")
	public void admin_sends_https_get_request_with_class_by_staff_id_and(String requestType, String paramValue) {
		classResponse=classRequest.sendGetClassReqWithBodyByClassRecordingByClassId(requestType,paramValue);
	}

	@Then("Admin receives with response body in get class by staffId")
	public void admin_receives_with_response_body_in_get_class_by_staff_id() {
		int actualStatusCode = classResponse.getStatusCode();
		int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
		softAssert =new SoftAssert();
		
		//Status code Validation
		System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertAll();
	}
	
	//DeleteClass
	@Given("Admin creates Delete request for class {string} and {string}")
	public void admin_creates_delete_request_for_class_and(String requestType, String paramValue) throws Exception {
	   //setGetClassRequestBodyDeleteClass
		getTestData_Get=classRequest.setGetClassRequestBodyDeleteClass(requestType,paramValue);
		
	}

	@When("Admin sends delete HTTPS request class {string} and {string}")
	public void admin_sends_delete_https_request_class_and(String requestType, String paramValue) {
		classResponse=classRequest.sendGetClassReqWithBodyDeleteClass(requestType,paramValue);
	}

	@Then("Admin receives class delete")
	public void admin_receives_class_delete() {
		int actualStatusCode = classResponse.getStatusCode();
		int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
		softAssert =new SoftAssert();
		
		//Status code Validation
		System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		softAssert.assertAll();
	}
	
	
	
	//**********Update new class***************************************//
	
	@Given("Admin creates PUT Request with {string} in Class module with request body")
	public void admin_creates_put_request_with_in_class_module_with_request_body(String requestType) throws Exception {
		classRequest.newClassRequest(requestType);
	}
	@When("Admin sends PUT HTTPS Request update Class module with {string}")
	public void admin_sends_put_https_request_update_class_module_with(String requestType) {
		classRequest.putNewClassRequest(requestType);
	}
	@Then("Admin receives {string} for Update Class module request")
	public void admin_receives_for_update_class_module_request(String expectedStatusCode) {
		Response response = context.get("classResponse", Response.class);
	    
	    if (response == null) {
	        throw new IllegalStateException("No response available for validation");
	    }
	    
	    int actualStatusCode = response.getStatusCode();
	    int expectedStatus = Integer.parseInt(expectedStatusCode);
	    
	    if (actualStatusCode != expectedStatus) {
	        throw new AssertionError(String.format(
	            "Status code mismatch! Expected %d but got %d. Response: %s",
	            expectedStatus,
	            actualStatusCode,
	            response.getBody().asString()
	        ));
	    }
	}


}
